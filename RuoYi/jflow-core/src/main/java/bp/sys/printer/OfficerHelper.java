package bp.sys.printer;

import bp.da.DataRow;
import bp.da.DataRowCollection;
import bp.da.DataTable;
import bp.da.DataType;
import bp.sys.MapAttr;
import bp.sys.SysEnum;
import bp.sys.SysEnums;
import bp.sys.printer.DBModel.DB;
import bp.sys.printer.Sdt.SDTContentControl;
import bp.sys.printer.Sdt.WordFillContentControls;
import bp.wf.template.Printer.FrmPrintTemplate;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;

public class OfficerHelper {
    public FrmPrintTemplate HisFrmPrintTemplate = null;
    /**
     数据源
     */
    private DB HisDB = null;
    private String FrmID = null;
    public final void MakeDocFile(String frmID, String templateFile, FrmPrintTemplate template, long workID) throws Exception {
        XWPFDocument document = null;
        InputStream in = null;
        this.FrmID = frmID;
        this.HisFrmPrintTemplate = template;
        //生成数据源.
        this.HisDB = new DB(template.getFrmID(), template.getName(), template.getMyPK(), workID);
        try {
            //获取docx解析对象
            in = new FileInputStream(templateFile);
            document = new XWPFDocument(in);
            List<SDTContentControl> allsdts = WordFillContentControls.extractSDTsFromBody(document);
            for (SDTContentControl sdt : allsdts) {
                String title = sdt.getTitle();
                //主表字段格式： MetaData$Bill_VSTO1.xingming           (frmid.字段key)
                //从表字段格式：
                //第一行标题：MetaCol$Bill_VSTO1CongBiao.1.BeiZhu   (frmid从表key.index.字段key 从1开始，1是标题不用处理 从2开始)
                //第二行开始：MetaData$Bill_VSTO1CongBiao.1.BeiZhu   (frmid从表key.index.字段key 从1开始，1是标题不用处理 从2开始)
                String tag = sdt.getTag();
                String content = sdt.getContentText();
//                System.out.println(title + ": " + tag + ": " + content);
                //主从表字段
                if(tag.startsWith("MetaData")){
                    String[] tagArr=tag.replace("MetaData$","").split("\\.");
                    //从表
                    if(tagArr.length==3){
                        content=getWordTagDtlsValue(tagArr);
                    }else{//主表
                        content=getWordTagValue(tagArr);
                    }
                }else if(tag.startsWith("MetaCol")){  //从表标题 不用处理

                }
                sdt.setContent(content);
            }
            FileOutputStream out = new FileOutputStream(templateFile);
            document.write(out);
            out.close();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取主表字段内容   tag: MetaData$frmid.字段key
    private  String getWordTagValue(String[] tagArr){
        try{
            if(tagArr.length!=2){
                return "";
            }
            Object v= this.HisDB.HisDBEles.ToJavaList().stream().filter(x->x.RefFrmID.equals(tagArr[0])&&x.AttrKey.equals(tagArr[1])).findFirst().get().AttrValue;
            if(v !=null){
                return v.toString();
            }else{
                return "";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       return "";
    }
    //获取从表字段内容
    //第一行标题：MetaCol$Bill_VSTO1CongBiao.1.BeiZhu   (frmid从表key.index.字段key 从1开始，1是标题不用处理 从2开始)
    //第二行开始：MetaData$Bill_VSTO1CongBiao.1.BeiZhu   (frmid从表key.index.字段key 从1开始，1是标题不用处理 从2开始)
    private  String getWordTagDtlsValue(String[] tagArr){
        try{
            String no=tagArr[0];
            int index=Integer.parseInt(tagArr[1]);
            String fieldKey=tagArr[2];
            index=index-2;
            //获取从表数据
            DataRowCollection rows= this.HisDB.HisDBDtls.ToJavaList().stream().filter(x->x.No.equals(no)).findFirst().get().HisDB.Rows;
            if(rows.size()<index+1){
                return "";
            }
            Object v= rows.get(index).get(fieldKey);
            if(v !=null){
                return v.toString();
            }else{
                return "";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public final String ContainsKey(HashMap<String, ArrayList<String>> DtlMap, String key)
    {
        for (Map.Entry<String, ArrayList<String>> kvp: DtlMap.entrySet())
        {
            // Console.WriteLine($"Key = {kvp.Key}, Value = {kvp.Value}");
            if (kvp.getKey().split("@")[0].equals(key))
            {
                return kvp.getKey();
            }
        }
        return "";
    }
    public final String EnumKey(HashMap<String, SysEnums> EnumMap, String key, String val)
    {
        //枚举字段
        if (EnumMap.containsKey(key))
        {
            SysEnums Enums = EnumMap.get(key);
            for (SysEnum e : Enums.ToJavaList())
            {
                String lab = e.getLab();
                int IntKey = e.getIntKey();
                if (String.valueOf(IntKey).equals(val))
                {
                    return lab;
                }
            }
        }
        else
        {
            return val;
        }
        return "";
    }

    public final void MakeExcelFile(String frmID, String templateFile, FrmPrintTemplate template, long workID) throws Exception {
        this.FrmID = frmID;
        this.HisFrmPrintTemplate = template;
        Workbook workbook =null;
        FileInputStream file =null;
        try
        {
            //生成数据源.
            this.HisDB = new DB(template.getFrmID(), template.getName(), template.getMyPK(), workID);
            file = new FileInputStream(templateFile);
            ///#region 1. 打开excel准备工作.
            workbook = new XSSFWorkbook(file);
            Sheet worksheet = workbook.getSheetAt(0); // 获取第一个工作表
            //#endregion 打开excel准备工作.
            //#region 2. 从表及字段处理
            // 通过名称获取命名范围
            List<? extends Name> namedRanges =workbook.getAllNames();
            //获取从表区域  单据id+从表id 例子： Name: "bilL_chumendancongbiao" Ranges:"sheet1!$s$10:$V$20"
            HashMap<String, ArrayList<String>> DtlMap = new HashMap<String, ArrayList<String>>();
            for (Name namedRange : namedRanges)
            {
                String CellID = namedRange.getNameName();
                if (DataType.IsNullOrEmpty(CellID) == false && !CellID.contains("."))
                {
                    String DtlName = CellID;
                    String range = namedRange.getRefersToFormula();
                    String sheet = range.split("!")[0];
                    String DtlRange = range.split("!")[1];
                    String name = DtlName + "@" + DtlRange;
                    ArrayList<String> list = new ArrayList<String>();
                    DtlMap.put(name, list);
                }
            }
            //获取从表中的字段  例子：Name:"Dict_XsTZ2Dict_XsTZ2Dtl1.CanYuRenEmail"  Ranges:"sheet1!$s$10:$s$10"
            //主表，从表字段如果是枚举，单独记录
            HashMap<String, SysEnums> EnumMap = new HashMap<String, SysEnums>();
            for (Name namedRange : namedRanges)
            {
                String CellID = namedRange.getNameName();
                if (DataType.IsNullOrEmpty(CellID) == false && CellID.contains("."))
                {
                    String DtlName = CellID.split("\\.")[0];
                    String key = CellID.split("\\.")[1];
                    String range = namedRange.getRefersToFormula();
                    String sheet = range.split("!")[0];
                    String CellRange = range.split("!")[1];
                    String name = key + "@" + CellRange.split(":")[0];
                    String DtlMapKe = ContainsKey(DtlMap, DtlName);
                    //从表字段
                    if (!DtlMapKe.equals(""))
                    {
                        ArrayList<String> list = new ArrayList<String>();
                        list = DtlMap.get(DtlMapKe);
                        if (list == null)
                        {
                            list = new ArrayList<String>();
                        }
                        list.add(name);
                        DtlMap.put(DtlMapKe, list);
                    }
                    //枚举的判断
//                    MapAttr attr = new MapAttr(DtlName + "_" + key);
//                    attr.RetrieveFromDBSources();
//                    String uibindkey = attr.getUIBindKey();
//                    if (uibindkey != null && !uibindkey.equals(""))
//                    {
//                        bp.sys.SysEnums myee = new SysEnums(uibindkey);
//                        EnumMap.put(CellID, myee);
//                    }

                }
            }
            ///#endregion 从表及字段处理.
            ///#region 3. 替换主表数据.
            for (Name namedRange : namedRanges)
            {
                String cellID = namedRange.getNameName();
                if (DataType.IsNullOrEmpty(cellID) == true || cellID.contains(".") == false)
                {
                    continue;
                }
                // 找到数据.
                String[] strs = cellID.split("\\.");
                String attrFrmID = strs[0];
                String attrKey = strs[1];
                //不属于从表key
                String DtlMapKe = ContainsKey(DtlMap, attrFrmID);
                if (!DtlMapKe.equals(""))
                {
                    continue;
                }
                //获取值.
                String val = this.HisDB.HisDBEles.GetValByKey(attrFrmID, attrKey).AttrValue.toString();
//                val = EnumKey(EnumMap, cellID, val);
                // 获取命名范围的单元格 例子：Sheet1!$E$43
                String range = namedRange.getRefersToFormula();
                //$E$43
                String cellRange = range.split("!")[1];
                //E
                String cellColindexStr = cellRange.split("\\$")[1];
                int cellColindex = getLetterIndex(cellColindexStr.charAt(0));
                //43
                int cellRowindex = Integer.parseInt(cellRange.split("\\$")[2])-1;
                // 获取指定单元格（例如：第一行第一列，即A1）
                Row row = worksheet.getRow(cellRowindex); // 获取行
                Cell cell = row.getCell(cellColindex); // 获取列
                // 输出当前单元格的值
//                System.out.println("当前单元格的值: " + cell.getStringCellValue());
                // 修改单元格的值
                cell.setCellValue(val);
            }

            ///#endregion 2. 替换主表数据.
            ///#region 3. 替换从表数据.
            //循环从表
            for (Map.Entry<String, ArrayList<String>> kvp : DtlMap.entrySet())
            {
                // Console.WriteLine($"Key = {kvp.Key}, Value = {kvp.Value}");
                //Dict_XsTZ2Dict_xsTZ2Dtl1@$s$10:$V$20
                String name = kvp.getKey().split("@")[0];
                String sindexStr = kvp.getKey().split("@")[1].split(":")[0];
                String eindexStr = kvp.getKey().split("@")[1].split(":")[1];
                int sindex = Integer.parseInt(sindexStr.split("\\$")[2]);
                int eindex = Integer.parseInt(eindexStr.split("\\$")[2]);
                //获取值.
                DataTable dtable = this.HisDB.HisDBDtls.GetValByKey(name).HisDB;
                ArrayList<String> list = new ArrayList<String>();
                list = DtlMap.get(kvp.getKey());
                //字段重复次数
                int dtlfieldCount = DtlFieldCount(list);
                int vsindex = 1;
                //根据行数和字段重复次数获取excel中占用的行数
                int dtlCountFieldCount=dtable.Rows.size()/dtlfieldCount;
                for(int i=0;i<=dtlCountFieldCount;i++) {
                    //不能超过选择的总行数
                    if (vsindex > (eindex - sindex))
                    {
                        break;
                    }

                    for(int j=0;j<dtlfieldCount;j++) {
                        String strj=j+"";
                        //从第几行取数据
                        int rindex=i+i*(dtlfieldCount-1)+j;
                        //不能超过从表数据行数
                        if(rindex>dtable.Rows.size()-1)
                        {
                            break;
                        }

                        for (String str : list) //CanYuRenEmail0@$s$10  CanYuRenEmail1@$s$10  CanYuRenEmail2@$s$10
                        {
                            String key = str.split("@")[0];
                            //获取从表字段key
                            String nkey=key.substring(0,key.length()-1);
                            //获取从表字段重复索引值
                            String nindex=key.substring(key.length()-1);
                            if(!strj.equals(nindex)){
                                continue;
                            }
                            //Console.WriteLine($"Key = {key}"
                            String vkey = str.split("@")[1].split("\\$")[1];
                            String vindexStr = str.split("@")[1].split("\\$")[2];
                            int vindex = Integer.parseInt(vindexStr);
                            if (eindex >= (vindex + vsindex))
                            {
//                            value = EnumKey(EnumMap, name + "." + key, value);

                               String value= dtable.Rows.get(rindex).getValue(nkey).toString();
                                //列
                                int cellColindex = getLetterIndex(vkey.charAt(0));
                                //行
                                int cellRowindex = vindex + vsindex-1;
                                // 获取指定单元格（例如：第一行第一列，即A1）
                                Row wrow = worksheet.getRow(cellRowindex); // 获取行
                                Cell cell = wrow.getCell(cellColindex); // 获取列
                                // 修改单元格的值
                                cell.setCellValue(value);
                            }
                        }
                    }
                    vsindex = vsindex++;
                }

//                for (DataRow row : dtable.Rows)
//                {
//                    //已超过区域
//                    if (vsindex > (eindex - sindex))
//                    {
//                        break;
//                    }
//                    for (String str : list) //CanYuRenEmail@$s$10
//                    {
//                        String key = str.split("@")[0];
//                        key=key.substring(0,key.length()-2);
//                        //Console.WriteLine($"Key = {key}"
//                        String vkey = str.split("@")[1].split("\\$")[1];
//                        String vindexStr = str.split("@")[1].split("\\$")[2];
//                        int vindex = Integer.parseInt(vindexStr);
//                        String value = row.get(key).toString();
//                        int nkey = vindex + vsindex;
//                        if (eindex >= (vindex + vsindex))
//                        {
////                            value = EnumKey(EnumMap, name + "." + key, value);
//                            //列
//                            int cellColindex = getLetterIndex(vkey.charAt(0));
//                            //行
//                            int cellRowindex = nkey-1;
//                            // 获取指定单元格（例如：第一行第一列，即A1）
//                            Row wrow = worksheet.getRow(cellRowindex); // 获取行
//                            Cell cell = wrow.getCell(cellColindex); // 获取列
//                            // 修改单元格的值
//                            cell.setCellValue(value);
//                        }
//                    }
//                    vsindex++;
//                }

            }
            // 保存修改
            FileOutputStream out = new FileOutputStream(templateFile);
            workbook.write(out);
            out.close();
            workbook.close();
        }
        catch (RuntimeException ex)
        {
            throw new RuntimeException("err@VSTO模版打印错误:" + ex.getMessage());
        }
        finally
        {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ///#endregion 3. 替换从表数据.
    }
    //判断从表字段重复的次数
    //从表字段会出现重复的情况  例子：   xingming0,dizhi0,xingming1,dizhi1,xingming2,dizhi2,
    private int DtlFieldCount(List<String> list)
    {
        String f0=list.get(0).split("@")[0];

        f0=f0.substring(0,f0.length()-2);
        int i=0;
        for(String str:list){
            String nstr=str.split("@")[0];
             nstr=nstr.substring(0,nstr.length()-2);
            if(f0.equals(nstr)){
                i++;
            }
        }
        return i;
    }
    //获取字母在字母表中的序号  从0开始
    private static int getLetterIndex(char letter) {
        if (letter >= 'A' && letter <= 'Z') {
            return letter - 'A' ; // 大写字母
        } else if (letter >= 'a' && letter <= 'z') {
            return letter - 'a' ; // 小写字母
        } else {
            return -1; // 不是字母
        }
    }
}
