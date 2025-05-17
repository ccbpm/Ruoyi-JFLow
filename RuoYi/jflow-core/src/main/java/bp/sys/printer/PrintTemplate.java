package bp.sys.printer;

import bp.da.DBAccess;
import bp.da.DataType;
import bp.en.EntityMyPK;
import bp.en.Map;
import bp.en.UAC;
import bp.wf.template.frm.PrintFileType;
import bp.wf.template.frm.PrintOpenModel;
import bp.wf.template.frm.QRModel;
import bp.wf.template.frm.TemplateFileModel;
import java.io.*;
public class PrintTemplate extends EntityMyPK {

    //C# TO JAVA CONVERTER TASK: There is no preprocessor in Java:
    ///#region  属性
    /**
     UI界面上的访问控制
     */
    @Override
    public UAC getHisUAC()
    {
        UAC uac = new UAC();
        uac.OpenForSysAdmin();
        return uac;
    }
    /**
     编号
     */
    public final String getMyPK()
    {
        String no = GetValStrByKey("MyPK");
        no = no.replace("\n", "");
        no = no.replace(" ", "");
        return no;
    }
    public final void setMyPK(String value)
    {
        SetValByKey("MyPK", value);
        SetValByKey(PrintTemplateAttr.TempFilePath, value);
    }
    /**
     生成的单据类型
     */
    public final PrintFileType getHisPrintFileType()
    {
        return PrintFileType.forValue(GetValIntByKey(PrintTemplateAttr.PrintFileType));
    }
    public final void setHisPrintFileType(PrintFileType value)
    {
        SetValByKey(PrintTemplateAttr.PrintFileType, Integer.valueOf(value.getValue()));
    }
    /**
     二维码生成方式
     */
    public final QRModel getQRModel()
    {
        return QRModel.forValue(GetValIntByKey(PrintTemplateAttr.QRModel));
    }
    public final void setQRModel(QRModel value)
    {
        SetValByKey(PrintTemplateAttr.QRModel,Integer.valueOf(value.getValue()));
    }
    public final TemplateFileModel getTemplateFileModel()
    {
        return TemplateFileModel.forValue(GetValIntByKey(PrintTemplateAttr.TemplateFileModel));
    }
    public final void setTemplateFileModel(TemplateFileModel value)
    {
        SetValByKey(PrintTemplateAttr.TemplateFileModel, Integer.valueOf(value.getValue()));
    }

    /**
     生成的单据打开方式
     */
    public final PrintOpenModel getPrintOpenModel()
    {
        return PrintOpenModel.forValue(GetValIntByKey(PrintTemplateAttr.PrintOpenModel));
    }
    public final void setPrintOpenModel(PrintOpenModel value)
    {
        SetValByKey(PrintTemplateAttr.PrintOpenModel, (int)value.getValue());
    }
    /**
     打开的连接
     */
    public final String getTempFilePath()
    {
        String s = GetValStrByKey(PrintTemplateAttr.TempFilePath);
        if (DataType.IsNullOrEmpty(s) == true)
        {
            return getMyPK();
        }
        return s;
    }
    public final void setTempFilePath(String value)
    {
        SetValByKey(PrintTemplateAttr.TempFilePath, value);
    }
    /**
     节点ID
     */
    public final int getNodeID()
    {
        return GetValIntByKey(PrintTemplateAttr.NodeID);
    }
    public final void setNodeID(int value)
    {
        SetValByKey(PrintTemplateAttr.NodeID, value);
    }
    public final String getName()
    {
        return GetValStringByKey(PrintTemplateAttr.Name);
    }
    public final void setName(String value)
    {
        SetValByKey(PrintTemplateAttr.Name, value);
    }
    public final String getFrmID()
    {
        return GetValStringByKey(PrintTemplateAttr.FrmID);
    }
    public final void setFrmID(String value)
    {
        SetValByKey(PrintTemplateAttr.FrmID, value);
    }
    ///#endregion

    ///#region 构造函数
    /**
     打印模板
     */
    public PrintTemplate()
    {
    }
    /**
     打印模板
     @param mypk 主键
     */
    public PrintTemplate(String mypk) throws Exception {
        super(mypk.replace("\n", "").trim());
    }
    /**
     获得单据文件流
     @return
     */
    public final byte[] GenerFile() throws Exception {
        byte[] bytes = DBAccess.GetByteFromDB("Sys_FrmPrintTemplate", "MyPK", this.getMyPK(), PrintTemplateAttr.DBFile);
        if (bytes != null) {
            return bytes;
        }
        String tempExcel = "";
        //验证表单类型 Word

        //如果没有找到，就看看默认的文件是否有.
        tempExcel = bp.difference.SystemConfig.getPathOfDataUser() + "FrmVSTOTemplate/" + this.getMyPK() + ".xlsx";
        if ((new File(tempExcel)).isFile() == false) {
            tempExcel = bp.difference.SystemConfig.getPathOfDataUser() + "FrmVSTOTemplate/EmptyTemplate.xlsx";
        }
        bytes = DataType.ConvertFileToByte(tempExcel);

        return bytes;
    }

    public byte[] GenerWordFile() throws Exception {
        byte[] bytes = DBAccess.GetByteFromDB("Sys_FrmPrintTemplate", "MyPK", this.getMyPK(), PrintTemplateAttr.DBFile);
        if (bytes != null)
            return bytes;
        String tempExcel = "";

        //如果没有找到，就看看默认的文件是否有.
        tempExcel = bp.difference.SystemConfig.getPathOfDataUser() + "FrmVSTOTemplate/" + this.getMyPK() + ".docx";
        if ((new File(tempExcel)).isFile() == false) {
            tempExcel = bp.difference.SystemConfig.getPathOfDataUser() + "FrmVSTOTemplate/EmptyTemplate.docx";
        }
        bytes = DataType.ConvertFileToByte(tempExcel);

        return bytes;
    }
    /**
     EnMap
     */
    @Override
    public Map getEnMap()
    {
        if (this.get_enMap() != null)
        {
            return this.get_enMap();
        }
        Map map = new Map("Sys_FrmPrintTemplate", "打印模板数据源");
        map.IndexField = PrintTemplateAttr.FrmID;

        map.AddMyPK();
        map.AddTBString(PrintTemplateAttr.FrmID, null, "表单ID", false, false, 0, 60, 60);
        map.AddTBString(PrintTemplateAttr.Name, null, "模板名称", true, false, 0, 200, 100);
        map.AddTBString(PrintTemplateAttr.TemplateFileModel, null, "模板类型(rtf,excel,word,wps)", false, false, 0, 200, 200);

        String cfg1 = "@0=Word@1=PDF@2=Excel";
        map.AddDDLSysEnum(PrintTemplateAttr.PrintFileType, 0, "生成的文件类型", true, true, "PrintFileType", cfg1);
        this.set_enMap(map);
        return this.get_enMap();
    }

    @Override
    protected boolean beforeInsert() throws Exception
    {
        if (DataType.IsNullOrEmpty(this.getMyPK()) == true)
            this.setMyPK(DBAccess.GenerGUID());
        return super.beforeInsert();
    }
}
