package com.ruoyi.web.controller.jflow;

import bp.ccbill.GenerBill;
import bp.da.*;
import bp.difference.RefObject;
import bp.difference.SystemConfig;
import bp.difference.handler.CommonFileUtils;
import bp.difference.handler.HttpHandlerBase;
import bp.en.*;
import bp.sys.CCFormAPI;
import bp.sys.*;
import bp.tools.*;
import bp.web.WebUser;
import bp.wf.*;
import bp.wf.httphandler.CCMobile_MyFlow;
import bp.wf.httphandler.HttpHandlerGlo;
import bp.wf.httphandler.WF_Comm;
import bp.wf.httphandler.WF_WorkOpt;
import bp.wf.template.frm.MapFrmExcel;
import bp.wf.template.frm.MapFrmWord;
import bp.wf.template.frm.PrintFileType;
import com.ruoyi.web.controller.jflow.model.RequestData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static bp.wf.Dev2Interface.Port_GenerToken;

@RestController
@RequestMapping(value = "/WF/VSTO")
public class VSTOController extends HttpHandlerBase {

    /// 单据设计
    public final String TemplateDesinger = "TemplateDesinger";
    /// 解析器
    public final String FrmExcel = "FrmExcel";
    /// 单据-excel
    public final String MyBillExcel = "MyBillExcel";
    /// 单据-word
    public final String MyBillWord = "MyBillWord";
    /// 打印组件
    public final String FrmPrinter = "FrmPrinter";
    /// 打印模版设置
    public final String PrinterTemplateDesinger = "PrinterTemplateDesinger";
    /// 附件预览组件
    public final String AthHelper = "AthHelper";
    /// 工作处理器
    public final String MyFlowExcel = "MyFlowExcel";

    //公文存储
    public  final String GWDBFile ="GWDBFile";

    public  final String DBFile ="DBFile";
    private final Lock lock = new ReentrantLock();
    public static Object Return_Info(int code, String msg, Object data)
    {
        Hashtable ht = new Hashtable();
        ht.put("code", code);
        ht.put("message", msg);
        ht.put("msg", msg);
        ht.put("data", data==null?"":data);
        return ht;
    }
    private class ReportImage
    {
        public String ext;
        public String fileName;
        public String bytesData;
        public String mypk;
        ReportImage(String ext,String fileName,String bytesData,String mypk){
            this.ext=ext;this.fileName=fileName;this.bytesData=bytesData;this.mypk=mypk;
        }
    }
    /// <summary>
    /// 获得doc文件
    /// </summary>
    /// <param name="token"></param>
    /// <param name="workID"></param>
    /// <returns></returns>
    @RequestMapping("VSTOWord_GongWen_GetDocFile")
    public Object VSTOWord_GongWen_GetDocFile(String token, long workID) throws Exception {
        //根据token登录.
        Port_GenerToken(token);
        try
        {
            GenerWorkFlow gwf = new GenerWorkFlow(workID);
            Flow fl = new Flow(gwf.getFlowNo());
            //获取文件流
            byte[] bytes = DBAccess.GetByteFromDB(fl.getPTable(), "OID", workID+"", "DocFile");

            return Return_Info(200, "执行成功", bytes);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "失败", ex.getMessage());
        }
    }

    /// <summary>
    /// 获得pdf文件
    /// </summary>
    /// <param name="token"></param>
    /// <param name="workID"></param>
    /// <returns></returns>
    @RequestMapping("VSTOWord_GongWen_GetPDFFile")
    public Object VSTOWord_GongWen_GetPDFFile(String token, long workID) throws Exception {
        //根据token登录.
        Port_GenerToken(token);
        try
        {
            return Return_Info(200, "暂缓", "xxxx");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "失败", ex.getMessage());
        }
    }

    /// <summary>
    /// 保存公文
    /// </summary>
    /// <param name="token">Token</param>
    /// <param name="workID">WorkID</param>
    /// <param name="bytes">文件流</param>
    /// <returns></returns>
    @RequestMapping("VSTOWord_GongWen_SaveFile")
    public Object VSTOWord_GongWen_SaveFile(String token, long workID, byte[] bytes) throws Exception {
        //根据token登录.
        Port_GenerToken(token);
        try
        {
            GenerWorkFlow gwf = new GenerWorkFlow(workID);
            //文件二进制存储到业务表PTable的DocFile字段
            Flow fl = new Flow(gwf.getFlowNo());
            DBAccess.SaveBytesToDB(bytes, fl.getPTable(), "OID", workID+"", "DocFile");

            return Return_Info(200, "公文保存成功", "");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "失败", ex.getMessage());
        }
    }
    /// <summary>
    /// 获得公文
    /// </summary>
    /// <param name="token"></param>
    /// <param name="workId">workid</param>
    /// <param name="fkNodeId">节点id</param>
    /// <param name="templateFileName">指定公文模板文件名称</param>
    /// <returns></returns>
    @RequestMapping("GenerGongWenByte")
    public Object GenerGongWenByte(String token, int workId, int nodeID, String gongWenTemplateFile)
    {
        try
        {
            //lock.lock(); // 加锁
            lock.lock(); // 加锁
            //生成文件流
            byte[] bytes = null;
            if (DataType.IsNullOrEmpty(token))
            {
                return Return_Info(500, "参数不能为空", null);
            }
            Dev2Interface.Port_LoginByToken(token);
            Node nd = new Node(nodeID);
            Work wk = nd.getHisWork();
            wk.setOID(workId);
            wk.RetrieveFromDBSources();
            bytes = wk.GetFileFromDB(GWDBFile, "");

            String fileName = "空白模板.docx";
            if (!DataType.IsNullOrEmpty(gongWenTemplateFile))
            {
                fileName= URLDecoder.decode(gongWenTemplateFile, "UTF-8");

            }
            if (bytes == null || bytes.length == 0)
            {

                String frmVSTOTemplateFilePath = SystemConfig.getPathOfDataUser()+ "DocFlow\\" + fileName;
                File file=new File(frmVSTOTemplateFilePath);
                if (file.exists())
                {
                    bytes = DataType.ConvertFileToByte(frmVSTOTemplateFilePath);
                }
                else
                {
                    return Return_Info(500, "初始化模板文件不存在", null);
                }

            }
            String base64Str = bytes != null ?DataType.ByteToString(bytes) : "";
            return Return_Info(200, "成功", bytes);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }finally {
            lock.unlock(); // 加锁
        }
    }
    /// <summary>
    /// 创建公文
    /// </summary>
    /// <param name="token">Token</param>
    /// <param name="workID">WorkID</param>
    /// <param name="templateFileNo">模板主键</param>
    /// <returns></returns>
    @RequestMapping("VSTOWord_GongWen_Create")
    public Object VSTOWord_GongWen_Create(String token, String workID, String templateFileNo) throws Exception {
        //根据token登录.
        Port_GenerToken(token);
        try
        {
            GenerWorkFlow gwf = new GenerWorkFlow(workID);

            //选择一个模板来创建
            String dbstr = SystemConfig.getAppCenterDBVarStr();
            Paras ps = new Paras();
            ps.SQL = "SELECT * FROM WF_Part a WHERE  MyPK=" + dbstr + "MyPK ";
            ps.Add("MyPK", templateFileNo);
            DataTable dt = DBAccess.RunSQLReturnTable(ps);

            if (dt.Rows.size() == 0)
            {
                throw new Exception("err@没有找到公文模板.请检查WF_Part表,主键 [MyPK] = [" + templateFileNo + "] ,如没有数据请上传公文模板.");
            }

            //拿到文件路径
            String filePath = dt.Rows.get(0).getValue("MyFilePath").toString();

            //转为二进制
            byte[] bytes = DataType.ConvertFileToByte(filePath);

            //文件二进制存储到业务表PTable的DocFile字段
            Flow fl = new Flow(gwf.getFlowNo());
            DBAccess.SaveBytesToDB(bytes, fl.getPTable(), "OID", workID, "DocFile");

            return Return_Info(200, "创建成功", "");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "失败", ex.getMessage());
        }
    }
    /// <summary>
    /// 初始化公文组件
    /// </summary>
    /// <param name="token">登录人员的信息</param>
    /// <param name="workID">工作ID</param>
    /// <returns>执行结果</returns>
    @RequestMapping("VSTOWord_GongWen_Init")
    public Object VSTOWord_GongWen_Init(String token, long workID) throws Exception {
        //根据token登录.
        Port_GenerToken(token);
        try
        {
            GenerWorkFlow gwf = new GenerWorkFlow(workID);

            //是否可以查看
            if (Dev2Interface.Flow_IsCanViewTruck(gwf.getFlowNo(), workID, WebUser.getNo()) == false)
            {
                String msg = "err@您无权查看该工作,";
                msg += "\t\n如下情况可以查看该工作.";
                msg += "\t\n1. 该流程发起人, 审批人，抄送人，可以查看.";
                msg += "\t\n2. 默认与发起人是同一个部门的人可以查看.";
                msg += "\t\n3. 二级管理员可以查看本组织的工作.";
                msg += "\t\n4. 超级管理员可以查看.";
                msg += "\t\n5. 流程属性的权限控制设置权限的人可以查看.";
                msg += "\t\n6. 如果该流程的数据，任何人都可以查看，请在流程属性里设置权限控制，任何人可见.";
                throw new Exception(msg);
            }

            //是否可以操作
            if (Dev2Interface.Flow_IsCanDoCurrentWork(workID, WebUser.getNo()) == false)
            {
                String msg = "err@您无权处理该工作.";
                throw new Exception(msg);
            }

            //是否已经有了模板文件
            if (Dev2Interface.Flow_IsHaveDocFile(workID+"") == false)
            {
                String msg = "err@没有上传公文模板，请您上传模板后重试.";
                throw new Exception(msg);
            }
            return Return_Info(200, "执行成功", gwf.ToJson());
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "失败", ex.getMessage());
        }
    }
    /// <summary>
    /// 获取VSTO插件版本号
    /// </summary>
    /// <returns></returns>
    @RequestMapping("GetVstoCCFormWordExtensionVersion")
    public Object GetVstoCCFormWordExtensionVersion()
    {
        return Return_Info(200, "执行成功", "1.1.0.4");
    }
    /// <summary>
    /// 获得附件byte
    /// </summary>
    /// <param name="token"></param>
    /// <param name="frmAttachmentMyPk"></param>
    /// <returns></returns>
    @RequestMapping("GenerFrmAttachmentByte")
    public Object GenerFrmAttachmentByte(String token, String frmAttachmentMyPk)
    {
        lock.lock(); // 加锁
        try
        {
            //生成文件流
            byte[] bytes = null;
            Dev2Interface.Port_LoginByToken(token);

            if (DataType.IsNullOrEmpty(frmAttachmentMyPk))
            {
                return Return_Info(500, "参数不能为空", null);
            }

            FrmAttachmentDB frmAttachmentDB = new FrmAttachmentDB();

            frmAttachmentDB.setMyPK(frmAttachmentMyPk);
            int count = frmAttachmentDB.Retrieve();

            if (count == 0)
            {
                return Return_Info(500, "文件不存在", null);
            }
            FrmAttachment frmAttachment = new FrmAttachment();
            frmAttachment.setMyPK(frmAttachmentDB.getFKFrmAttachment());
            frmAttachment.Retrieve();

            //临时文件
            String temFilePath = "";

            //获取配置项是否加密
            boolean fileEncrypt = SystemConfig.isEnableAthEncrypt();

            //获取此文件是否加密
            boolean isEncrypt = frmAttachmentDB.GetParaBoolen("IsEncrypt");
            if (frmAttachment.getAthSaveWay() == AthSaveWay.IISServer)
            {

                temFilePath = frmAttachmentDB.getFileFullName() + ".tmp";
                if (fileEncrypt == true && isEncrypt == true)
                {
                    File file=new File(temFilePath);
                    if (file.exists() )
                        file.delete();

                    EncHelper.decryptDES(frmAttachmentDB.getFileFullName(), temFilePath);
                }
                else
                {
                    bytes = DataType.ConvertFileToByte(frmAttachmentDB.getFileFullName());
                }
            }

            if (frmAttachment.getAthSaveWay()  == AthSaveWay.FTPServer)
            {
                //下载文件到临时位置
                String orignalFile = frmAttachmentDB.GenerTempFile(frmAttachment.getAthSaveWay());
                temFilePath = orignalFile + ".temp";
                if (fileEncrypt == true && isEncrypt == true)
                {
                    EncHelper.decryptDES(orignalFile, temFilePath);
                    //将下载的临时文件删除掉
                    File file=new File(orignalFile);
                    if (file.exists() )
                        file.delete();
                }
                else
                    temFilePath = orignalFile;
            }
            if (frmAttachment.getAthSaveWay() == AthSaveWay.DB)
            {
                //加密文件处理
                if (fileEncrypt == true && isEncrypt == true)
                {
                    //根据流生成初试源文件
                    String orignalFile = frmAttachmentDB.GenerTempFile(frmAttachment.getAthSaveWay());
                    temFilePath = orignalFile + ".temp";

                    //根据初试源文件生成解密后的文件
                    EncHelper.decryptDES(orignalFile, temFilePath);
                    File file=new File(orignalFile);
                    if (file.exists() )
                        file.delete();
                }
                else
                {
                    //不是加密文件直接读取流
                    bytes = frmAttachmentDB.GetFileFromDB(DBFile, null);
                }
            }
            if (bytes == null)
            {
                bytes = DataType.ConvertFileToByte(temFilePath);
            }
            String base64Str = bytes != null ?DataType.ByteToString(bytes): "";
            //将临时文件删掉
            File file=new File(temFilePath);
            if (file.exists() )
                file.delete();
            Hashtable hs = new Hashtable();
            hs.put("bytes", bytes);
            hs.put("name", frmAttachmentDB.getFileFullName());
            return Return_Info(200, "成功", hs);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }finally {
            lock.unlock(); // 确保在 finally 块中解锁
        }
    }
    /// <summary>
    /// 保存一个文件
    /// </summary>
    /// <param name="token"></param>
    /// <param name="frmID"></param>
    /// <param name="nodeID"></param>
    /// <param name="workID"></param>
    /// <param name="base64Data"></param>
    /// <param name="guid"></param>
    @RequestMapping("SaveFrmAth")
    public Object SaveFrmAth(String token, String frmID, int nodeID, long workID, String base64Data, String guid)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            MapData md = new MapData(frmID);
            FrmAttachments aths = new FrmAttachments(frmID);
            if (aths.size() == 0)
            {
                CCFormAPI.CreateOrSaveAthMulti(md.getNo(), "Ath", "附件");
                aths = new FrmAttachments(frmID);
            }
            FrmAttachment ath = (FrmAttachment) aths.get(0);

            //把文件写入.
            String rootPath = ath.getSaveTo();
            String fileName = guid + "." + "JPEG";
            String filePath = rootPath + fileName;
            File file=new File(filePath);
            if (file.exists() )
                file.delete();

            // 如果 Base64 字符串包含前缀 "data:image/jpeg;base64,", 则需要去掉它
             base64Data = base64Data.split(",")[1];
            // 将 Base64 字符串解码为 byte[]
            byte[] byt = Base64.getDecoder().decode(base64Data);


            DataType.getFileByBytes(byt,filePath);

            File info=new File(filePath);
            FrmAttachmentDB dbUpload = new FrmAttachmentDB();
            dbUpload.setMyPK(guid);
            dbUpload.setNodeID(nodeID);
            dbUpload.setSort(null);
            dbUpload.setFrmID(ath.getFrmID());
            dbUpload.setFKFrmAttachment(ath.getMyPK());

//            dbUpload.setFileExts(info.);
            dbUpload.setFileFullName(filePath);
            dbUpload.setFileName(fileName);
            dbUpload.setFileSize(info.length());
            dbUpload.setRDT(DataType.getCurrentDataTime());
            dbUpload.setRec(WebUser.getNo());
            dbUpload.setRecName(WebUser.getName());
            dbUpload.setRefPKVal(workID+"");

            dbUpload.setUploadGUID(guid);
            dbUpload.DirectSave();
            return Return_Info(200, "成功", null);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }

    }
    /// <summary>
///
/// </summary>
/// <param name="workID"></param>
/// <param name="createReportType"></param>
/// <returns></returns>
    @RequestMapping("GetReportImagesData")
    public Object GetReportImagesData(long workID, String createReportType)
    {
        try
        {
            if (StringUtils.isEmpty(createReportType))
                return null;

            String dbStr = SystemConfig.getAppCenterDBVarStr();
            Paras ps = new Paras();


            switch (createReportType)
            {
                case "1":
                    ps.SQL = "SELECT FileFullName,FileExts,MyPK,FileName  FROM Sys_FrmAttachmentDB  WHERE  RefPKVal=" + dbStr + "RefPKVal";
                    ps.Add(FrmAttachmentDBAttr.RefPKVal, workID);
                    break;
                case "2":
                    ps.SQL = "SELECT FileFullName,FileExts,MyPK,FileName  FROM Sys_FrmAttachmentDB  WHERE " +
                            "RefPKVal in(SELECT WorkID FROM WF_GenerWorkFlow WHERE PWORKID=" + dbStr + "PWORKID)";
                    ps.Add("PWORKID", workID);
                    break;
                default:
                    break;
            }
            DataTable dt = DBAccess.RunSQLReturnTable(ps);

            List<ReportImage> reImgsList = new ArrayList<>();
            for (DataRow dr : dt.Rows)
            {
                String fileFullName = dr.getValue("FileFullName").toString();
                File file=new File(fileFullName);
                if (file.exists())
                {

                    byte[] bytes =DataType.ConvertByteByFileFullPath(fileFullName);
                    reImgsList.add(
                        new ReportImage
                                (
                                    dr.getValue("FileExts").toString()
                                    ,dr.getValue("FileName").toString()
                                    ,org.apache.commons.codec.binary.Base64.encodeBase64String(bytes)
                                     ,dr.getValue("MyPK").toString())

                         );
                }
            }

            return Return_Info(200, "执行成功",Json.ToJson(reImgsList));
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
    /// 生成vsto模式的数据
    /// </summary>
    /// <param name="token"></param>
    /// <param name="frmID"></param>
    /// <param name="pkValue"></param>
    /// <param name="atParas"></param>
    /// <returns></returns>
    @RequestMapping("GenerDBForVSTOExcelFrmModel")
    public Object GenerDBForVSTOExcelFrmModel(String token, String frmID, String pkValue, String atParas)
    {
        //让他登录.
        Dev2Interface.Port_LoginByToken(token);

        //解析这个表单.
        try
        {
            DataSet ds = bp.wf.CCFormAPI.GenerDBForVSTOExcelFrmModel(frmID, pkValue, atParas);

            return Return_Info(200, "执行成功", Json.ToJson(ds));
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
    /// 执行保存Excel从表数据.
    /// </summary>
    /// <param name="requestData"></param>
    /// <returns></returns>
    @RequestMapping("Frm_SaveExcelFileDtls")
    public Object Frm_SaveExcelFileDtls(@RequestBody RequestData requestData)
    {
        try
        {
//         #region 1.参数登录检查.
            if (DataType.IsNullOrEmpty(requestData.getToken()) || DataType.IsNullOrEmpty(requestData.getFrmID()) || DataType.IsNullOrEmpty(requestData.getPkValue()))
                return Return_Info(500, "参数不能为空", null);

            DataSet dsDtlsNew=Json.ToDataSet(requestData.getData());
            Dev2Interface.Port_LoginByToken(requestData.getToken()); //登录.
//         #endregion 1.参数登录检查.

            MapDtls dtls = new MapDtls(requestData.getFrmID());
            for (MapDtl mapDtl : dtls.Tolist())
            {
                if (dsDtlsNew.contains(mapDtl.getNo()) == false)
                    continue;

                GEDtls gEDtls = new GEDtls(mapDtl.getNo());

                //接收的table数据
                DataTable dataTable = dsDtlsNew.get(mapDtl.getNo());

                QueryObject queryObject = new QueryObject(gEDtls);
                queryObject.AddWhere(mapDtl.getRefPK(), requestData.getPkValue());
                DataTable oldDataTable = queryObject.DoQueryToTable();

                List<String> oldOidList1=new ArrayList<>();
                for(DataRow dr : oldDataTable.Rows ){
                    oldOidList1.add(dr.getValue("OID").toString());
                }

                List<String> oldOidList = oldOidList1.stream().distinct().collect(Collectors.toList());
                for (DataRow dr : dataTable.Rows)
                {
                    //在原有数据基础上删除不存在的oid数据
                    if (oldOidList.size() > 0 && oldOidList.contains(dr.getValue("OID").toString()))
                    {
                        oldOidList.remove(dr.getValue("OID").toString());
                    }
                    GEDtl daDtl = (GEDtl) gEDtls.getNewEntity();
                    daDtl.setOID( Integer.parseInt(dr.getValue("OID").toString()));
                    int count = daDtl.RetrieveFromDBSources();
                    daDtl.setRDT(DataType.getCurrentDataTime()) ;
                    //更新字段值
                    for (DataColumn dc : dataTable.Columns)
                    {
                        //设置属性.
                        daDtl.SetValByKey(dc.ColumnName, dr.getValue(dc.ColumnName));
                    }
                    if (count > 0)
                    {
                        daDtl.Update();
                    }
                    else
                    {
                        daDtl.setRefPK(requestData.getPkValue());
                        daDtl.InsertAsOID(DBAccess.GenerOID(daDtl.getEnMap().getPhysicsTable()));
                    }
                }

                if (oldOidList.size() > 0)
                {
                    Paras ps = new Paras();
                    ps.SQL = "DELETE FROM " + mapDtl.getPTable() + " WHERE OID in ( " + String.join(",", oldOidList) + ")";
                    //ps.Add("V", string.Join(",", oldOidList));
                    DBAccess.RunSQL(ps);
                    //    string deleteSql = "DELETE FROM " + mapDtl.PTable + " WHERE OID in ( " + string.Join(",", oldOidList) + ")";
                    //DBAccess.RunSQL(deleteSql);
                }
            }
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }

        return Return_Info(200, "执行成功", null);
    }

    @RequestMapping("FrmWord_FileGener")
    public Object FrmWord_FileGener(String token, String frmID, Integer workID)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            RefObject<byte[]> bytes = new RefObject<>(null);
            MapData md = new MapData(frmID);

            MapFrmWord mfe = new MapFrmWord(frmID);
            md.WordGenerFileEntity(workID.toString(), bytes, MapDataAttr.VSTOExcelFile);

            if(bytes.argvalue==null){
                md.GenerFileByMB(frmID, bytes, MapDataAttr.VSTOExcelFile);
            }
            if (bytes.argvalue != null)
            {
                String docStr = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes.argvalue);
                return Return_Info(200, "执行成功", docStr);
            }
            return Return_Info(404, "没有获取到文件流", "");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), "");
        }
    }

    /// <summary>
    /// 获得单据文件
    /// </summary>
    /// <param name="token"></param>
    /// <param name="frmID"></param>
    /// <param name="workID"></param>
    /// <returns></returns>
    @RequestMapping("FrmExcel_FileGener")
    public Object FrmExcel_FileGener(String token, String frmID, int workID)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            RefObject<byte[]> bytes = new RefObject<>(null);
            MapData md = new MapData(frmID);

            //创建excel表单描述，让其保存到excel表单指定的字段里, 扩展多个表单映射同一张表.
            MapFrmExcel mfe = new MapFrmExcel(frmID);
            md.ExcelGenerFile(workID+"", bytes, MapDataAttr.VSTOExcelFile);
            //还没有文件，获取模版进行使用
            if(bytes.argvalue==null){
              md.GenerFileByMB(frmID, bytes, MapDataAttr.VSTOExcelFile);
            }
            if (bytes.argvalue != null)
            {
                String docStr = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes.argvalue);
                return Return_Info(200, "执行成功", docStr);
            }
            return Return_Info(404, "没有获取到文件流", "");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), "");
        }
    }
    /// <summary>
    /// 执行保存
    /// </summary>
    /// <param name="token"></param>
    /// <param name="frmID">表单ID</param>
    /// <param name="workID">工作ID</param>
    /// <param name="AppID">工作ID</param>
    /// <returns></returns>
    @RequestMapping("Frm_FileSave")
    public Object Frm_FileSave(String token, String frmID, int workID, String AppID) throws Exception {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            HttpServletRequest request = getRequest();
            String contentType = request.getContentType();
            if (contentType == null || contentType.indexOf("multipart/form-data") == -1) {
                throw new RuntimeException("ContentType不符合要求[ContentType=" + contentType + "]");
            }
            MultipartHttpServletRequest multipartRequest = CommonFileUtils.getMultipartHttpServletRequest(request);
            // 不能直接保存这个requestFile吗
            MultipartFile requestFile = multipartRequest.getFile("media");
            if (requestFile == null || requestFile.getSize() == 0) {
                throw new Exception("err@文件数据bytes没有传递过来.");
            }

            MapData md = new MapData(frmID);
            if (AppID.equals(MyBillExcel) ||AppID.equals(MyBillWord))
            {
                bp.ccbill.Dev2Interface.MyBill_SetEditing(workID);
                bp.ccbill.Dev2Interface.SaveBillWork(workID);
            }
            byte[] bys =requestFile.getBytes() ;
            DBAccess.SaveBytesToDB(bys, md.getPTable(), "OID", workID+"", "VSTOExcelFile");
            return Return_Info(200, "成功", "执行成功.");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
    /// VSTO,执行保存Excel主表数据
    /// </summary>
    /// <param name="token"></param>
    /// <param name="frmID"></param>
    /// <param name="pkValue"></param>
    /// <param name="mainTableAtParas"></param>
    @RequestMapping("Frm_SaveExcelFileMainTable")
    public Object Frm_SaveExcelFileMainTable(@RequestBody RequestData requestData, String token, String frmID, String pkValue)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            Hashtable ht = Json.JsonToHashtable(requestData.getData());
            Enumeration<String> keys = ht.keys();
//         #region 求主表的主键类型.
            String pkType = null;
            String sql = "SELECT KeyOfEn FROM Sys_MapAttr WHERE FK_MapData='" + frmID + "' AND KeyOfEn='OID' ";
            if (DBAccess.RunSQLReturnTable(sql).Rows.size() == 1)
                pkType = "OID";

            if (pkType == null)
            {
                sql = "SELECT KeyOfEn FROM Sys_MapAttr WHERE FK_MapData='" + frmID + "' AND KeyOfEn='MyPK' ";
                if (DBAccess.RunSQLReturnTable(sql).Rows.size()== 1)
                    pkType = "MyPK";
            }

            if (pkType == null)
                pkType = "No";
//         #endregion 求主表的主键类型.
//         #region 处理EntityMyPK 类型的实体保存。
            if (pkType.equals("MyPK") == true)
            {
                /* 具有MyPK 的实体，为了简便判断. */
                GEEntityMyPK wk = new GEEntityMyPK(frmID, pkValue);
                wk.ResetDefaultVal();

                if (ht != null)
                {
                    while (keys.hasMoreElements()) {
                        String key = keys.nextElement();
                        if (wk.getRow().containsKey(key))
                            wk.SetValByKey(key, ht.get(key));
                        else
                            wk.getRow().put(key, ht.get(key));
                    }
                }
                wk.setMyPK(pkValue);
                // 保存实体.
                wk.Save();
            }
//         #endregion 处理 EntityMyPK 类型的实体保存。
//         #region 处理 EntityNoName 类型的实体保存。
            if (pkType.equals("No") == true)
            {
                /* 具有MyPK 的实体，为了简便判断. */
                GEEntityNoName wk = new GEEntityNoName(frmID);
                wk.ResetDefaultVal();
                wk.SetValByKey("No", pkValue);
                wk.RetrieveFromDBSources();

                if (ht != null)
                {

                    while (keys.hasMoreElements()) {
                        String key = keys.nextElement();
                        if (wk.getRow().containsKey(key))
                            wk.SetValByKey(key, ht.get(key));
                        else
                            wk.getRow().put(key, ht.get(key));
                    }
                }
                wk.SetValByKey("No", pkValue);
                // 保存实体.
                wk.Save();
            }
//         #endregion 处理 EntityMyPK 类型的实体保存。
//         #region 处理 EntityOID 类型的实体保存。
            if (pkType.equals("OID") == true)
            {
                GEEntityOID wk = new GEEntityOID(frmID, pkValue);
                wk.ResetDefaultVal();
                wk.SetValByKey("BillState", 1);

                if (ht != null)
                {
                    while (keys.hasMoreElements()) {
                        String key = keys.nextElement();
                        if (wk.getRow().containsKey(key))
                            wk.SetValByKey(key, ht.get(key));
                        else
                            wk.getRow().put(key, ht.get(key));
                    }
                }
                wk.setOID(Long.valueOf(pkValue));
                //保存.
                wk.Save();
            }
//         #endregion 处理 EntityOID 类型的实体保存。
            return Return_Info(200, "执行成功", "执行成功.");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "执行失败", ex.getMessage());
        }
    }
    /// <summary>
/// 初始化表单数据
/// </summary>
/// <param name="token"></param>
/// <param name="frmID"></param>
/// <param name="pkVal"></param>
/// <param name="atParas"></param>
/// <returns></returns>
    @RequestMapping("Frm_InitFrmDataSet")
    public Object Frm_InitFrmDataSet(String token, String frmID, String pkVal, String atParas)
    {
        //让他登录.
        Dev2Interface.Port_LoginByToken(token);

        //解析这个表单.
        try
        {
            DataSet ds = bp.wf.CCFormAPI.GenerDBForVSTOExcelFrmModel(frmID, pkVal, atParas);
            return Return_Info(200, "执行成功",  Json.ToJson(ds));
        }
        catch (Exception ex)
        {
            Log.DefaultLogWriteLineError(ex.getMessage(),ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    @RequestMapping("Frm_SFTableDB")
    public Object Frm_SFTableDB(String token, String sfTablePK)
    {
        try
        {
            SFTable sf = new SFTable(sfTablePK);
            DataTable dt = sf.GenerHisDataTable();
            return Return_Info(200, "执行成功", Json.ToJson(dt));
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "执行失败:" + ex.getMessage(), ex.getMessage());
        }
    }
    /// <summary>
    /// 获得Excel文件
    /// </summary>
    /// <param name="token"></param>
    /// <param name="frmID"></param>
    /// <returns></returns>
    @RequestMapping("GenerTemplateFile")
    public Object GenerTemplateFile(String token, String frmID)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);

            MapData md;
            //如果是一个实体类.
            if (frmID.contains("BP."))
            {
                // 执行map同步.
                Entities ens = ClassFactory.GetEns(frmID + "s");
                Entity en = ens.getNewEntity();
                md = en.DTSMapToSys_MapData();
            }
            else
            {
                md = new MapData(frmID);
            }

            RefObject<byte[]> bytes = null;
            md.ExcelGenerFile(bytes);
            String base64Str = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes.argvalue);
            return Return_Info(200, "执行成功", base64Str);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "获得Excel模板文件时出现异常，调用接口[GenerTemplateFile]\n" + ex.getMessage(), "");
        }
    }

    /// <summary>
    /// 获得Excel文件
    /// </summary>
    /// <param name="token"></param>
    /// <param name="frmID"></param>
    /// <param name="pkValue"></param>
    /// <returns></returns>
    @RequestMapping("GenerExcelFile")
    public Object GenerExcelFile(String token, String frmID, String pkValue)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);

            RefObject<byte[]> bytes = null;
            MapData md;
            //如果是一个实体类.
            if (frmID.contains("BP."))
            {
                // 执行map同步.
                Entities ens = ClassFactory.GetEns(frmID + "s");
                Entity en = ens.getNewEntity();
                md = en.DTSMapToSys_MapData();
            }
            else
            {
                md = new MapData(frmID);
            }
            //创建excel表单描述，让其保存到excel表单指定的字段里, 扩展多个表单映射同一张表.
            MapFrmExcel mfe = new MapFrmExcel(md.getNo());

            md.ExcelGenerFile(pkValue, bytes, mfe.getDBSave());

            if (bytes != null)
            {
                //转为字符
                String docStr = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes.argvalue);

                return Return_Info(200, "执行成功", docStr);
            }
            else
            {
                return Return_Info(404, "没有获取到文件流", "");
            }
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), "");
        }
    }
    /// <summary>
    /// 返回类型
    /// </summary>
    /// <param name="token"></param>
    /// <param name="name"></param>
    /// <returns></returns>
    @RequestMapping("Gener_DBType")
    public Object Gener_DBType(String token, String name)
    {
        try
        {
            List<String> arrayList =new ArrayList<String>();
            arrayList.add(name);
            String json = CCFormAPI.GenerDataTypeByCHName(arrayList);
            return Return_Info(200, "成功", json);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    @RequestMapping("Gener_PinYin")
    public Object Gener_PinYin(String token, String name, boolean flag)
    {
        try
        {
            //Dev2Interface.Port_LoginByToken(token);
            //此处为字段中文转拼音，设置为最大20个字符，edited by liuxc,2017-9-25
            String str = CCFormAPI.ParseStringToPinyinField(name, flag, true, 20);
            return Return_Info(200, "成功", str);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
    /// 获取表单模版
    /// </summary>
    /// <param name="token"></param>
    /// <param name="frmID"></param>
    /// <returns></returns>
    @RequestMapping("Gener_FileTemplate")
    public Object Gener_FileTemplate(String token, String frmID)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            MapData md;
            //如果是一个实体类.
            if (frmID.contains("BP."))
            {
                // 执行map同步.
                Entities ens = ClassFactory.GetEns(frmID + "s");
                Entity en = ens.getNewEntity();
                md = en.DTSMapToSys_MapData();
            }
            else
            {
                md = new MapData(frmID);
            }

            RefObject<byte[]> bytes = null;

            md.ExcelGenerFile(bytes);

            String base64Str = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes.argvalue);

            return Return_Info(200, "执行成功", base64Str);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "获得Excel模板文件时出现异常，调用接口[GenerTemplateFile]\n" + ex.getMessage(), "");
        }
    }
    /// <summary>
    /// 获得用户信息
    /// </summary>
    /// <param name="token"></param>
    /// <returns></returns>
    @RequestMapping("Gener_WebUser")
    public Object Gener_WebUser(String token)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);

            Hashtable ht = new Hashtable();
            ht.put("No", WebUser.getNo());
            ht.put("Name", WebUser.getName());
            ht.put("DeptNo", WebUser.getDeptNo());
            ht.put("DeptName", WebUser.getDeptName());
            ht.put("OrgNo", WebUser.getOrgNo());
            ht.put("OrgName", WebUser.getOrgName());

            String json = Json.ToJson(ht);
            return Return_Info(200, "成功", json);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
    /// 获得版本
    /// </summary>
    /// <param name="token"></param>
    /// <returns></returns>
    @RequestMapping("Gener_Ver")
    public Object Gener_Ver(String token)
    {
        try
        {
            return Return_Info(200, "成功", "1.1.0.7");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    @RequestMapping("Entities_RetrieveAll")
    public Object Entities_RetrieveAll(String token, String ensName)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            Entities ens = ClassFactory.GetEns(ensName);
            if (ens == null)
                throw new Exception("类 ensName=[" + ensName + "]拼写不正确,或者不存在.");

            ens.RetrieveAll();

            DataTable dt = ens.ToDataTableField();
            return Return_Info(200, "成功", Json.ToJson(dt));
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
    /// 获得实体信息
    /// </summary>
    /// <param name="token">token</param>
    /// <param name="ensName">实体类名</param>
    /// <param name="key1">字段名称</param>
    /// <param name="val1">字段值</param>
    /// <param name="key2">字段名称</param>
    /// <param name="val2">字段值</param>
    /// <returns>集合</returns>
    @RequestMapping("Entities_Retrieve")
    public Object Entities_Retrieve(String token, String ensName, String key1, String val1,  String key2,String val2, String orderBy)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            Entities ens = ClassFactory.GetEns(ensName);
            if (ens == null)
                throw new Exception("类 ensName=[" + ensName + "]拼写不正确,或者不存在.");
            if (val1 == null)
                throw new Exception("属性[" + key1 + "]不能为空.");

            if (key2 == null)
                ens.Retrieve(key1, val1, orderBy);
            else
                ens.Retrieve(key1, val1, key2, val2, orderBy);

            DataTable dt = ens.ToDataTableField();
            return Return_Info(200, "成功", Json.ToJson(dt));
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
    /// 插入
    /// </summary>
    /// <param name="token"></param>
    /// <param name="enName"></param>
    /// <param name="pkval"></param>
    /// <param name="row"></param>
    /// <returns></returns>
    @RequestMapping("Entity_Insert")
    public Object Entity_Insert(String token, String enName, String pkval, String row)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            Entity en = ClassFactory.GetEn(enName);
            if (en == null)
                throw new Exception("类EnName=[" + enName + "]拼写不正确,或者不存在.");

            // row 是一个json数据，怎么把json转化为 ht.
            Attrs attrs = en.getEnMap().getAttrs();
            DataTable dt = Json.ToDataTable(row); //获得数据.
            for (DataColumn dc : dt.Columns)
            {
                String key = null;
                for (Attr attr : attrs)
                {
                    if (dc.ColumnName.toLowerCase().equals(attr.getKey().toLowerCase()) == true)
                        key = attr.getKey();
                }
                if (key == null)
                    continue;
                en.SetValByKey(key, dt.Rows.get(0).getValue(dc.ColumnName));
            }

            en.setPKVal(pkval) ;
            en.Insert();
            return Return_Info(200, "成功", 1);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
/// 执行更新.
/// </summary>
/// <param name="token"></param>
/// <param name="enName"></param>
/// <param name="pkval"></param>
/// <param name="row"></param>
/// <returns></returns>
    @RequestMapping("Entity_Update")
    public Object Entity_Update(String token, String enName, String pkval, String row)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);

            Entity en = ClassFactory.GetEn(enName);
            if (en == null)
                throw new Exception("类EnName=[" + enName + "]拼写不正确,或者不存在.");
            en.setPKVal(pkval);
            en.Retrieve();

            // row 是一个json数据，怎么把json转化为 ht.
            Attrs attrs = en.getEnMap().getAttrs();
            DataTable dt = Json.ToDataTable(row); //获得数据.
            for (DataColumn dc : dt.Columns)
            {
                String key = null;
                for (Attr attr : attrs)
                {
                    if (dc.ColumnName.toLowerCase().equals(attr.getKey().toLowerCase()) == true)
                    {
                        key = attr.getKey();
                    }
                }
                if (key == null)
                    continue;
                en.SetValByKey(key, dt.Rows.get(0).getValue(dc.ColumnName));
            }

            en.Update();
            return Return_Info(200, "成功", 1);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
    /// 实体删除
    /// </summary>
    /// <param name="token"></param>
    /// <param name="enName"></param>
    /// <param name="pkval"></param>
    /// <returns></returns>
    @RequestMapping("Entity_Delete")
    public Object Entity_Delete(String token, String enName, String pkval)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);

            Entity en = ClassFactory.GetEn(enName);
            if (en == null)
                throw new Exception("类EnName=[" + enName + "]拼写不正确,或者不存在.");
            en.setPKVal(pkval);
            if (en.RetrieveFromDBSources() > 0)
            {
                en.Delete();
            }
            return Return_Info(200, "成功", 1);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
    /// 初始化
    /// </summary>
    /// <param name="token"></param>
    /// <param name="enName"></param>
    /// <returns></returns>
    @RequestMapping("Entity_Init")
    public Object Entity_Init(String token, String enName)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);

            //获得 en.
            Entity en = ClassFactory.GetEn(enName);
            if (en == null)
                throw new Exception("类EnName=[" + enName + "]拼写不正确,或者不存在.");

            //获得数据.
            DataTable dt = en.ToDataTableField();

            ////获得数据.
            //DataTable dt = en.ToEmptyTableField();
            //DataRow dr = dt.NewRow();
            //dt.Rows.Add(dr);
            return Return_Info(200, "成功", Json.ToJson(dt));
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
    /// 获得实体信息
    /// </summary>
    /// <param name="token">会话ID</param>
    /// <param name="enName">实体名称</param>
    /// <param name="pkval">主键值</param>
    /// <returns>一行数据</returns>
    @RequestMapping("Entity_RetrieveFromDBSources")
    public Object Entity_RetrieveFromDBSources(String token, String enName, String pkval)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            Entity en = ClassFactory.GetEn(enName);
            if (en == null)
                throw new Exception("类EnName=[" + enName + "]拼写不正确,或者不存在.");
            en.setPKVal(pkval);
            int i = en.RetrieveFromDBSources();

            DataTable dt = en.ToDataTableField();
            dt.Columns.Add("NumOfRows", String.class); //增加列名,标记查询数量.
            dt.Rows.get(0).setValue("NumOfRows",i);//前端，用于检查是否存在数据.

            return Return_Info(200, "成功", Json.ToJson(dt));
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /// <summary>
    /// 获得实体信息
    /// </summary>
    /// <param name="token">会话ID</param>
    /// <param name="enName">实体名称</param>
    /// <param name="pkval">主键值</param>
    /// <returns>一行数据</returns>
    @RequestMapping("Entity_Retrieve")
    public Object Entity_Retrieve(String token, String enName, String pkval)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            Entity en = ClassFactory.GetEn(enName);
            if (en == null)
                throw new Exception("类EnName=[" + enName + "]拼写不正确,或者不存在.");

            en.setPKVal(pkval);
            en.Retrieve();

            DataTable dt = en.ToDataTableField();
            return Return_Info(200, "成功", Json.ToJson(dt));
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /**
     * 执行删除
     * @param token
     * @param frmID 表单ID
     * @param workID 工作ID
     * @return
     */
    @RequestMapping("MyBill_Delete")
    public Object MyBill_Delete(String token, String frmID, int workID)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            //执行归档
            bp.ccbill.Dev2Interface.MyBill_Delete(frmID, workID);

            return Return_Info(200, "成功", "执行成功.");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /**
     * 撤销归档
     * @param token
     * @param workID
     * @return
     */
    @RequestMapping("MyBill_SetEditing")
    public Object MyBill_SetEditing(String token, int workID)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            //执行归档
            bp.ccbill.Dev2Interface.MyBill_SetEditing(workID);

            return Return_Info(200, "成功", "执行成功.");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }

    /**
     * 撤销归档
     * @param token
     * @param workID
     * @return
     */
    @RequestMapping("MyBill_ArchiveUn")
    public Object MyBill_ArchiveUn(String token, int workID)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            //执行归档
            bp.ccbill.Dev2Interface.MyBill_ArchiveUn(workID);

            return Return_Info(200, "成功", "执行成功.");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /**
     * 执行归档
     * @param token
     * @param workID
     * @return
     */
    @RequestMapping("MyBill_FrmOver")
    public Object MyBill_FrmOver(String token, int workID)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);
            //执行归档
            bp.ccbill.Dev2Interface.MyBill_SetFrmOver(workID);

            return Return_Info(200, "成功", "执行成功.");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /**
     * 初始化toolbar内容
     * @param token
     * @param frmID
     * @param workID
     * @return
     */
    @RequestMapping("MyBill_Toolbar")
    public Object MyBill_Toolbar(String token, String frmID, int WorkID)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            //获得工具栏的按钮.
            GenerBill gb = new GenerBill(WorkID);
            DataTable dt = gb.GenerToolbar();

            return Return_Info(200, "成功", Json.ToJson(dt));
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    @RequestMapping("MyFlow_SendUn")
    public Object MyFlow_SendUn(String token, int workID)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            GenerWorkFlow gwf = new GenerWorkFlow(workID);
            //撤销.
            String msg = Dev2Interface.Flow_DoUnSend(gwf.getFlowNo(), workID);

            // DataTable dataTable = new DataTable();
            return Return_Info(200, "成功", msg);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /**
     * 发送
     * @param token
     * @param workID
     * @param toNodeID 到达的节点ID
     * @param toEmps   到达的人员
     * @return
     */
    @RequestMapping("MyFlow_Send")
    public Object MyFlow_Send(String token, int workID, int toNodeID, String toEmps)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            //执行登录.
            SendReturnObjs objs = Dev2Interface.Node_SendWork(null, workID, toNodeID, toEmps);

            // DataTable dataTable = new DataTable();
            return Return_Info(200, "成功", objs.ToMsgOfText());
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    @RequestMapping("MyFlow_WorkOptInit")
    public Object MyFlow_WorkOptInit(String token, int workID, int toNodeID, String mypk)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            GenerWorkFlow gwf = new GenerWorkFlow(workID);
            WF_WorkOpt opt = new WF_WorkOpt();
            opt.AddPara("WorkID", String.valueOf(workID));
            opt.AddPara("NodeID", gwf.getNodeID()+"");
            opt.AddPara("ToNodeID", toNodeID+"");
            opt.AddPara("MyPK", mypk);
            String data = opt.SendWorkOpt_Init();
            return Return_Info(200, "成功", data);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    @RequestMapping("MyFlow_ReturnWork")
    public Object MyFlow_ReturnWork(String token, int workID, int toNodeID, String toEmpNo, String msg, int isBackNode)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            boolean _isBackNode = false;
            if (isBackNode == 1)
                _isBackNode = true;

            //获得要退回的节点.
            String str = Dev2Interface.Node_ReturnWork(workID, toNodeID, toEmpNo, msg, _isBackNode);

            return Return_Info(200, "成功", str);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    @RequestMapping("MyFlow_GenerWillReturnNodes")
    public Object MyFlow_GenerWillReturnNodes(String token, int workID)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            //获得要退回的节点.
            DataTable dt = Dev2Interface.DB_GenerWillReturnNodes(workID);

            return Return_Info(200, "成功", Json.ToJson(dt));
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    @RequestMapping("MyFlow_Mesage")
    public Object MyFlow_Mesage(String token, int workID)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            GenerWorkFlow gwf = new GenerWorkFlow(workID);

            //获得消息.
            DataTable dt = CCFlowAPI.GetFlowAlertMsg(gwf, new Node(gwf.getNodeID()), gwf.getFlowNo(), workID, gwf.getFID());

            //返回信息.
            return Return_Info(200, "成功", Json.ToJson(dt));
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /**
     * 工作流程
     * @param token
     * @param workID
     * @return
     */
    @RequestMapping("MyFlow_Toolbar")
    public Object MyFlow_Toolbar(String token, int WorkID)
    {
        try
        {
            //执行登录.
            Dev2Interface.Port_LoginByToken(token);

            GenerWorkFlow gwl = new GenerWorkFlow(WorkID);
            if (gwl.getIsOver() == true)
            {

            }
            CCMobile_MyFlow hanlder = new CCMobile_MyFlow();
            hanlder.AddPara("FlowNo", gwl.getFlowNo());
            hanlder.AddPara("WorkID", WorkID+"");
            hanlder.AddPara("NodeID", gwl.getNodeID()+"");
            hanlder.AddPara("FK_Node", gwl.getNodeID()+"");
            hanlder.AddPara("FID", gwl.getFID()+"");
            hanlder.AddPara("FK_Flow", gwl.getFlowNo());
            String str = hanlder.InitToolBar();
            // DataTable dataTable = new DataTable();
            return Return_Info(200, "成功", str);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /**
     * 保存打印设置
      * @param token
     * @param mypk
     * @return
     */
    @RequestMapping("FrmPrinter_Save")
    public Object FrmPrinter_Save(String token, String myPK)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            HttpServletRequest request = getRequest();
            MultipartHttpServletRequest multipartRequest = CommonFileUtils.getMultipartHttpServletRequest(request);
            // 不能直接保存这个requestFile吗
            MultipartFile file = multipartRequest.getFile("media");
            if (file == null || file.isEmpty())
                throw new Exception("err@文件数据bytes没有传递过来.");

            bp.wf.template.Printer.FrmPrintDB frmPrintDB = new bp.wf.template.Printer.FrmPrintDB(myPK);
            //现将上传的文件转存为临时文件.
            String tempFile = SystemConfig.getPathOfTemp() + frmPrintDB.getMyPK() + ".tmp";


            //下载文件.
            DataType.getFileByBytes(file.getBytes(), tempFile);
            DBAccess.SaveFileToDB(tempFile, "Sys_FrmPrintDB", "MyPK", myPK, "DBFile");

            //删除掉临时文件
            File fileInfo = new File(tempFile);
            if (fileInfo.exists())
                fileInfo.delete();

            return Return_Info(200, "成功", "更新成功");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "失败", ex.getMessage());
        }
    }

    /**
     * 初始化文件
     * @param token
     * @param frmAttachmentMyPk
     * @return
     */
    @RequestMapping("AthHelper_Init")
    public Object AthHelper_Init(String token, String frmAttachmentMyPk)
    {
        if (DataType.IsNullOrEmpty(frmAttachmentMyPk))
            return Return_Info(500, "参数不能为空", null);
        try
        {
            Dev2Interface.Port_LoginByToken(token);

            FrmAttachmentDB db = new FrmAttachmentDB(frmAttachmentMyPk);
            String pathfile = db.GenerTempFile(); //下载到一个临时文件.

            byte[] bytes = DataType.ConvertFileToByte(pathfile);

//            String base64String = encodeFileToBase64(new File(pathfile));

            return Return_Info(200, "成功", bytes);
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "失败", ex.getMessage());
        }
    }
    /**
     * 保存附件
     * @param token
     * @param mypk
     * @return
     */
    @RequestMapping("AthHelper_Save")
    public Object AthHelper_Save(String token, String mypk)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            Dev2Interface.Port_LoginByToken(token);
            HttpServletRequest request = getRequest();
            MultipartHttpServletRequest multipartRequest = CommonFileUtils.getMultipartHttpServletRequest(request);
            // 不能直接保存这个requestFile吗
            MultipartFile file = multipartRequest.getFile("media");
            if (file == null || file.isEmpty())
                throw new Exception("err@文件数据bytes没有传递过来.");

            FrmAttachmentDB frmAttachmentDB = new FrmAttachmentDB(mypk);

            //现将上传的文件转存为临时文件.
            String tempFile = SystemConfig.getPathOfTemp() + frmAttachmentDB.getMyPK() + ".tmp";

            //下载文件.
            DataType.getFileByBytes(file.getBytes(), tempFile);
            //把文件上传上去，覆盖现在的文件数据.
            frmAttachmentDB.GenerFileUpload(tempFile, null, false);

            //删除掉临时文件
            File fileInfo = new File(tempFile);
            if (fileInfo.exists())
                fileInfo.delete();

            return Return_Info(200, "成功", "更新成功");
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "失败", ex.getMessage());
        }
    }

    @RequestMapping(value = "/GenerGongWenTrackByte")
    public Object GenerGongWenTrackByteWPS(String token, String mypk, int flowNo, HttpServletResponse response, HttpServletRequest request) {
        try {
            //生成文件流
            byte[] bytes = null;
            if (DataType.IsNullOrEmpty(token) || DataType.IsNullOrEmpty(mypk) || flowNo == 0) {
                return Return_Info(500, "参数不能为空", "");

            }

            Dev2Interface.Port_LoginByToken(token);
            String tableTrack = "ND" + flowNo + "Track";
            bytes = DBAccess.GetByteFromDB(tableTrack, "MyPk", mypk, GWDBFile);

            if (bytes == null || bytes.length == 0) {

                return Return_Info(500, "历史公文不存在", "");
            }
            HttpHandlerGlo.DownLoadFileByBytes(response, bytes, DBAccess.GenerGUID() + ".docx");
        } catch (Exception ex) {
            return Return_Info(500, ex.getMessage(), "");
        }
        return Return_Info(500, "历史公文不存在", "");
    }
    @RequestMapping(value = "/GenerGongWenByteWPS")
    public Object GenerGongWenByteWPS(String token, int workId, int fkNodeId, String gongWenTemplateFile,HttpServletResponse response, HttpServletRequest request) {
        try {
            //生成文件流
            byte[] bytes = null;
            if (DataType.IsNullOrEmpty(token) || workId == 0 || fkNodeId == 0) {
                return Return_Info(500, "参数不能为空", "");

            }
            Dev2Interface.Port_LoginByToken(token);
            Node nd = new Node(fkNodeId);
            Work wk = nd.getHisWork();
            wk.setOID(workId);
            wk.RetrieveFromDBSources();

            bytes = wk.GetFileFromDB(GWDBFile, "");

            String fileName = "空白模板.docx";
            if (!DataType.IsNullOrEmpty(gongWenTemplateFile))
            {
                fileName = URLDecoder.decode(gongWenTemplateFile, "UTF-8");
            }
            if (bytes == null || bytes.length == 0) {

                String frmVSTOTemplateFilePath = SystemConfig.getPathOfDataUser() + "DocFlow\\" + fileName;
                File fileInfo = new File(frmVSTOTemplateFilePath);

                if (fileInfo.exists()) {
                    bytes = DataType.ConvertByteByFileFullPath(frmVSTOTemplateFilePath);

                } else {
                    return Return_Info(500, "初始化模板文件不存在", "");
                }
            }
            HttpHandlerGlo.DownLoadFileByBytes(response, bytes, DBAccess.GenerGUID() + ".docx");
        } catch (Exception ex) {
            return Return_Info(500, ex.getMessage(), "");
        }
        return Return_Info(500, "公文不存在", "");
    }

    /// <summary>
    /// 保存公文文件
    /// </summary>
    /// <param name="token"></param>
    /// <param name="fkNodeId"></param>
    /// <param name="workId"></param>
    /// <returns></returns>
    @RequestMapping(value = "/SaveGongWenWord")
    public Object SaveGongWenWord(String token, String nodeId, String workId)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            HttpServletRequest request = getRequest();
            String contentType = request.getContentType();
            if (contentType == null || contentType.indexOf("multipart/form-data") == -1) {
                throw new RuntimeException("ContentType不符合要求[ContentType=" + contentType + "]");
            }
            MultipartHttpServletRequest multipartRequest = CommonFileUtils.getMultipartHttpServletRequest(request);
            // 不能直接保存这个requestFile吗
            MultipartFile requestFile = multipartRequest.getFile("media");
            if (requestFile == null || requestFile.getSize() == 0) {
                throw new Exception("err@文件数据bytes没有传递过来.");
            }

            byte[] buffer = requestFile.getBytes();

            Node nd = new Node(Integer.parseInt(nodeId));
            Work wk = nd.getHisWork();
            wk.setOID(Integer.parseInt(workId));
            wk.SaveFileToDB(GWDBFile,buffer);
            return "已成功保存到服务器";
        }
        catch (Exception ex)
        {
            Log.DefaultLogWriteLineError(ex.getMessage(),ex);
            return "err@" + ex.getMessage();

        }
    }

    /***
     *
     * @return
     */
    @RequestMapping("/SaveGongWenWordWPS")
    public Object SaveGongWenWordWPS( String token, int workId, int fkNodeId) {
        try {
            if (DataType.IsNullOrEmpty(token) || workId == 0 || fkNodeId == 0) {
                return Return_Info(500, "参数不能为空", "");
            }
            HttpServletRequest request = getRequest();
            MultipartHttpServletRequest multipartRequest = CommonFileUtils.getMultipartHttpServletRequest(request);
            // 不能直接保存这个requestFile吗
            MultipartFile file = multipartRequest.getFile("media");
            Dev2Interface.Port_LoginByToken(token);
            byte[] bytes = file.getBytes();
            Node nd = new Node(fkNodeId);
            Work wk = nd.getHisWork();
            wk.setOID(workId);
            int i = wk.RetrieveFromDBSources();
            if (i == 0) {
                throw new Exception("该流程的表单数据不存在");

            }
            wk.SaveFileToDB(GWDBFile, bytes);
            return "File uploaded successfully";
        } catch (Exception ex) {
            return Return_Info(500, ex.getMessage(), "");
        }
    }

    @RequestMapping("/GenerFrmAttachmentByteWPS")
    public synchronized Object GenerFrmAttachmentByteWPS(String token, String frmAttachmentMyPk, HttpServletResponse response, HttpServletRequest request) {
        try {
            //生成文件流
            byte[] bytes = null;
            if (DataType.IsNullOrEmpty(frmAttachmentMyPk) || DataType.IsNullOrEmpty(token)) {
                return Return_Info(500, "参数不能为空", "");
            }
            Dev2Interface.Port_LoginByToken(token);

            FrmAttachmentDB frmAttachmentDB = new FrmAttachmentDB();

            frmAttachmentDB.setMyPK(frmAttachmentMyPk);
            int count = frmAttachmentDB.Retrieve();

            if (count == 0) {
                return Return_Info(500, "文件不存在", "");
            }
            FrmAttachment frmAttachment = new FrmAttachment();
            frmAttachment.setMyPK(frmAttachmentDB.getFKFrmAttachment());
            frmAttachment.Retrieve();

            //临时文件
            String temFilePath = "";

            //获取配置项是否加密
            boolean fileEncrypt = SystemConfig.isEnableAthEncrypt();
            ;

            //获取此文件是否加密
            boolean isEncrypt = frmAttachmentDB.GetParaBoolen("IsEncrypt");
            if (frmAttachment.getAthSaveWay() == AthSaveWay.IISServer) {

                temFilePath = frmAttachmentDB.getFileFullName() + ".tmp";
                if (fileEncrypt == true && isEncrypt == true) {
                    File fileTemFilePath = new File(temFilePath);
                    if (fileTemFilePath.exists()) {
                        fileTemFilePath.delete();
                    }
                    AesEncodeUtil.decryptFile(frmAttachmentDB.getFileFullName(), temFilePath);
                } else {
                    bytes = DataType.ConvertByteByFileFullPath(frmAttachmentDB.getFileFullName());
                }
            }

            if (frmAttachment.getAthSaveWay() == AthSaveWay.FTPServer) {
                //下载文件到临时位置
                String orignalFile = frmAttachmentDB.GenerTempFile(frmAttachment.getAthSaveWay());
                temFilePath = orignalFile + ".temp";
                if (fileEncrypt == true && isEncrypt == true) {
                    AesEncodeUtil.decryptFile(orignalFile, temFilePath);
                    File file = new File(orignalFile);

                    //将下载的临时文件删除掉
                    if (file.exists() == true)
                        file.delete();
                } else
                    temFilePath = orignalFile;
            }
            if (frmAttachment.getAthSaveWay() == AthSaveWay.DB) {

                //加密文件处理
                if (fileEncrypt == true && isEncrypt == true) {
                    //根据流生成初试源文件
                    String orignalFile = frmAttachmentDB.GenerTempFile(frmAttachment.getAthSaveWay());
                    temFilePath = orignalFile + ".temp";
                    byte[] bytesEncrypt = frmAttachmentDB.GetFileFromDB(DBFile, null);
                    if (bytesEncrypt == null || bytesEncrypt.length == 0) {
                        throw new Exception("文件不存在");
                    }
                    DataType.getFileByBytes(bytesEncrypt, orignalFile);

                    //根据初试源文件生成解密后的文件
                    AesEncodeUtil.decryptFile(orignalFile, temFilePath);
                    File file = new File(orignalFile);
                    if (file.exists() == true)
                        file.delete();
                } else {
                    //不是加密文件直接读取流
                    bytes = frmAttachmentDB.GetFileFromDB(DBFile, null);
                }
            }

            if (bytes == null) {
                bytes = DataType.ConvertByteByFileFullPath(temFilePath);
            }

            HttpHandlerGlo.DownLoadFileByBytes(response, bytes, frmAttachmentDB.getFileName());

        } catch (Exception ex) {
            return Return_Info(500, ex.getMessage(), "");
        }
        return Return_Info(500, "文件不存在", "");

    }

    @RequestMapping("/SaveFrmAttachment")
    public synchronized Object SaveFrmAttachmentWPS(String token, String frmAttachmentMyPk) {
        try {
            HttpServletRequest request = getRequest();
            MultipartHttpServletRequest multipartRequest = CommonFileUtils.getMultipartHttpServletRequest(request);
            // 不能直接保存这个requestFile吗
            MultipartFile multipartFile = multipartRequest.getFile("media");
            if (multipartFile == null || DataType.IsNullOrEmpty(token) || DataType.IsNullOrEmpty(frmAttachmentMyPk)) {
                return Return_Info(500, "参数不能为空", "");
            }
            Dev2Interface.Port_LoginByToken(token);

            FrmAttachmentDB frmAttachmentDB = new FrmAttachmentDB();

            frmAttachmentDB.setMyPK(frmAttachmentMyPk);
            int count = frmAttachmentDB.Retrieve();
            if (count == 0) {
                return Return_Info(500, "文件不存在", "");
            }
            FrmAttachment frmAttachment = new FrmAttachment();
            frmAttachment.setMyPK(frmAttachmentDB.getFKFrmAttachment());
            frmAttachment.Retrieve();

            //获取配置项是否加密
            boolean fileEncrypt = SystemConfig.isEnableAthEncrypt();
            String tempFile = "";
            //获取此文件是否加密
            boolean isEncrypt = frmAttachmentDB.GetParaBoolen("IsEncrypt");

            if (frmAttachment.getAthSaveWay() == AthSaveWay.IISServer) {
                if (fileEncrypt == true && isEncrypt == true) {
                    //现将上传的文件转存为临时文件
                    tempFile = frmAttachmentDB.MakeTempFileFullPath() + ".tmp";
                    File filetempFile = new File(tempFile);
                    if (filetempFile.exists()) {
                        filetempFile.delete();
                    }

                    //将上传的文件转为临时文件
                    DataType.getFileByBytes(multipartFile.getBytes(), tempFile);

                    File fileFullName = new File(frmAttachmentDB.getFileFullName());
                    //将原始文件删除掉
                    if (fileFullName.exists() == true)
                        fileFullName.delete();

                    //对临时文件进行加密并输出成新文件
                    AesEncodeUtil.encryptFile(tempFile, frmAttachmentDB.getFileFullName());
                    //删除掉临时文件
                    filetempFile.delete();
                } else {
                    //将原始文件删除掉然后保存新文件
                    File fileFullName = new File(frmAttachmentDB.getFileFullName());
                    if (fileFullName.exists() == true)
                        fileFullName.delete();
                    DataType.getFileByBytes(multipartFile.getBytes(), frmAttachmentDB.getFileFullName());
                }
            }

            //数据库存储
            if (frmAttachment.getAthSaveWay() == AthSaveWay.DB) {
                if (fileEncrypt == true && isEncrypt == true) {
                    //将上传的文件存储为临时文件
                    tempFile = frmAttachmentDB.MakeTempFileFullPath() + ".tmp";
                    File filetempFile = new File(tempFile);
                    if (filetempFile.exists()) {
                        filetempFile.delete();//删除临时文件

                    }
                    //将将上传的文件存为临时文件
                    DataType.getFileByBytes(multipartFile.getBytes(), tempFile);

                    //声明加密的文件路径
                    String tempEncryptFile = tempFile + ".encryptTemp";
                    File fileTempEncryptFile = new File(tempEncryptFile);
                    if (fileTempEncryptFile.exists()) {
                        fileTempEncryptFile.delete();
                    }

                    //根据初试源文件生成加密后的文件
                    AesEncodeUtil.encryptFile(tempFile, tempEncryptFile);

                    //保存流到数据库
                    frmAttachmentDB.SaveFileToDB(DBFile, tempEncryptFile);

                    //删除掉临时加密后的文件
                    if (fileTempEncryptFile.exists()) {
                        fileTempEncryptFile.delete();
                    }
                    if (filetempFile.exists()) {
                        filetempFile.delete();
                    }

                } else {
                    frmAttachmentDB.SaveFileToDB(DBFile, multipartFile.getBytes());
                }
            }

            if (frmAttachment.getAthSaveWay() == AthSaveWay.FTPServer) {
                //建立ftp链接
                String[] stringList = frmAttachmentDB.getFileFullName().split("/");
                String fileName = stringList[stringList.length - 1];

                FtpUtil ftpUtil = bp.wf.Glo.getFtpUtil();
                ftpUtil.openConnection();
                //将上传的文件先转为临时文件
                tempFile = frmAttachmentDB.MakeTempFileFullPath() + ".tmp";
                File filetempFile = new File(tempFile);
                if (filetempFile.exists()) {
                    filetempFile.delete();
                }
                DataType.getFileByBytes(multipartFile.getBytes(), tempFile);
                if (fileEncrypt == true && isEncrypt == true) {

                    //声明加密的文件路径
                    String tempEncryptFile = tempFile + ".encryptTemp";
                    File fileTempEncryptFile = new File(tempEncryptFile);
                    if (fileTempEncryptFile.exists()) {
                        fileTempEncryptFile.delete();
                    }

                    //根据初试源文件生成加密后的文件
                    AesEncodeUtil.encryptFile(tempFile, tempEncryptFile);
                    ftpUtil.changeWorkingDirectory(frmAttachmentDB.getFileFullName().replace(fileName, ""), true);
                    ftpUtil.uploadFile(fileName, tempEncryptFile);

                    if (filetempFile.exists()) {
                        filetempFile.delete();
                    }
                    if (fileTempEncryptFile.exists()) {
                        fileTempEncryptFile.delete();
                    }

                } else {

                    //修改ftp工作目录
                    ftpUtil.changeWorkingDirectory(frmAttachmentDB.getFileFullName().replace(fileName, ""), true);
                    ftpUtil.uploadFile(fileName, tempFile);
                    if (filetempFile.exists()) {
                        filetempFile.delete();
                    }
                }
            }

            return "File uploaded successfully";

        } catch (Exception ex) {
            return Return_Info(500, ex.getMessage(), "");
        }
    }
    @RequestMapping("/GetVstoExtensionVersion")
    public Object GetVstoExtensionVersion()
    {
        return Return_Info(200, "执行成功", SystemConfig.getVstoExtensionVersion());
    }

    /**
     * 以下为服务器端判断客户端浏览器类型的方法
     */
    private String getBrowser(HttpServletRequest request) {

        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (userAgent != null) {
            if (userAgent.indexOf("chrome") >= 0)
                return "CE";
            if (userAgent.indexOf("msie") >= 0)
                return "IE";
            if (userAgent.indexOf("firefox") >= 0)
                return "FF";
            if (userAgent.indexOf("safari") >= 0)
                return "SF";
        }
        return null;
    }
    @Override
    public Class getCtrlType() {
        return null;
    }
    //#region 报表.
    @RequestMapping("/PrinterTemplateDesinger_FileSave")
    public Object PrinterTemplateDesinger_FileSave( String token, String templateID) throws Exception {
        Dev2Interface.Port_LoginByToken(token);
        HttpServletRequest request = getRequest();
        String contentType = request.getContentType();
        if (contentType == null || contentType.indexOf("multipart/form-data") == -1) {
            throw new RuntimeException("ContentType不符合要求[ContentType=" + contentType + "]");
        }
        MultipartHttpServletRequest mrequest = CommonFileUtils.getMultipartHttpServletRequest(request);
        MultipartFile requestFile = mrequest.getFile("media");
        if (requestFile == null || requestFile.getSize() == 0) {
            throw new Exception("err@文件数据bytes没有传递过来.");
        }
        try
        {
            bp.sys.printer.PrintTemplate pt = new bp.sys.printer.PrintTemplate(templateID);

//            String fileName = templateID + ".xlsx";
//            String frmVSTOTemplateFilePath = bp.difference.SystemConfig.getPathOfDataUser() + "FrmVSTOTemplate/" + fileName;
//            if ((new File(frmVSTOTemplateFilePath)).isFile())
//            {
//                (new File(frmVSTOTemplateFilePath)).delete();
//            }
//            File tempFile = new File(frmVSTOTemplateFilePath);
//            CommonFileUtils.upload(request, "file", tempFile);
//
//            InputStream is = new FileInputStream(frmVSTOTemplateFilePath);
//            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//            byte[] data = new byte[4096];
//            int nRead;
//            while ((nRead = is.read(data)) != -1) {
//                buffer.write(data, 0, nRead);
//            }
            byte[] bytes = requestFile.getBytes();

            DBAccess.SaveBytesToDB(bytes, "Sys_FrmPrintTemplate", "MyPK", templateID, "DBFile");
            return Return_Info(200, "成功", "执行成功.");
        }
        catch (RuntimeException ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    @RequestMapping("/PrinterTemplateDesinger_FileGener")
    public Object PrinterTemplateDesinger_FileGener(String token, String mypk)
    {
        try
        {
            bp.sys.printer.PrintTemplate printer = new bp.sys.printer.PrintTemplate(mypk);
            byte[] bytes = null;
            //验证表单类型
            if (printer.getHisPrintFileType() == PrintFileType.Excel)
            {
                //Excel格式
                bytes = printer.GenerFile();
                return Return_Info(200, "成功", bytes);
            }
            else if (printer.getHisPrintFileType() == PrintFileType.Word)
            {
                //Word格式
                bytes = printer.GenerWordFile();
                return Return_Info(200, "成功", bytes);
            }
            else {
                return Return_Info(500, "获取文件错误PrinterTemplateDesinger_FileGener:", "");
            }

        }
        catch (Exception ex)
        {
            Log.DefaultLogWriteLineError(ex.getMessage(),ex);
            return Return_Info(500, "获取文件错误Designer_FileTemplateGener:", ex.getMessage());
        }
    }

    @RequestMapping("/Printer_FileGener")
    public Object Printer_FileGener(String token, String mypk)
    {
        try
        {
            bp.wf.template.Printer.FrmPrintDB printer = new bp.wf.template.Printer.FrmPrintDB(mypk);
            return Return_Info(200, "成功", printer.GenerFile());
        }
        catch (Exception ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "获取文件错误Designer_FileTemplateGener:", ex.getMessage());
        }
    }
    //      #endregion

    //    #region 表单设计器.
    @RequestMapping("/Designer_FileTemplateSave")
    public Object Designer_FileTemplateSave( String token, String frmID) throws Exception {
        Dev2Interface.Port_LoginByToken(token);
        HttpServletRequest request = getRequest();
        String contentType = request.getContentType();
        if (contentType == null || contentType.indexOf("multipart/form-data") == -1) {
            throw new RuntimeException("ContentType不符合要求[ContentType=" + contentType + "]");
        }
        try
        {
            MultipartHttpServletRequest multipartRequest = CommonFileUtils.getMultipartHttpServletRequest(request);
            // 不能直接保存这个requestFile吗
            MultipartFile requestFile = multipartRequest.getFile("media");
            if (requestFile == null || requestFile.getSize() == 0) {
                throw new Exception("err@文件数据bytes没有传递过来.");
            }
            MapData md = new MapData(frmID);
            byte[] bytes = requestFile.getBytes();
//            String  fileName = frmID;
//            //验证表单类型
//            if (md.getHisFrmType() == FrmType.VSTOForExcel)
//            {
//                //Excel格式
//                fileName += ".xlsx";
//            }else{
//                //Word格式
//                fileName += ".docx";
//            }
//            String frmVSTOTemplateFilePath = bp.difference.SystemConfig.getPathOfDataUser() + "FrmVSTOTemplate/" + fileName;
//            File file = new File(frmVSTOTemplateFilePath);
//            if (file.exists())
//            {
//                file.delete();
//            }
//            DataType.getFileByBytes(bytes, frmVSTOTemplateFilePath);
            DBAccess.SaveBytesToDB(bytes, "Sys_MapData", "No", frmID, MapDataAttr.VSTOExcelFile);
            return Return_Info(200, "成功", "执行成功.");
        }
        catch (RuntimeException ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        }
    }
    /**
     获得表单模版
     @param token
     @param frmID
     @return
     */
    @RequestMapping("/Designer_FileTemplateGener")
    public final Object Designer_FileTemplateGener(String token, String frmID)
    {
        try
        {
            Dev2Interface.Port_LoginByToken(token);
            RefObject<byte[]> bytes = new RefObject(null);
            MapData md = new MapData(frmID);
//            md.ExcelGenerFile(bytes);
            //验证表单类型
            if (md.getHisFrmType() == FrmType.VSTOForExcel)
            {
                //Excel格式
                md.ExcelGenerFile(bytes);
                return Return_Info(200, "成功", bytes.argvalue);
            }
            else if (md.getHisFrmType()  ==FrmType.VSTOForWord)
            {
                //Word格式
                md.WordGenerFile(bytes);
                return Return_Info(200, "成功", bytes.argvalue);
            }
            else {
                return Return_Info(500, "获取文件错误Designer_FileTemplateGener:非VSTO表单", "");
            }
        }
        catch (RuntimeException ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, "获取文件错误Designer_FileTemplateGener:", ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     创建从表
     @param token
     @param frmID
     @param dtlID
     @param dtlName
     @return
     */
    @RequestMapping("/Designer_CreateDtl")
    public final Object Designer_CreateDtl(String token, String frmID, String dtlID, String dtlName)
    {
        try
        {
            MapDtl dtl = new MapDtl();
            dtl.setNo(dtlID);
            if (dtl.RetrieveFromDBSources() == 1)
            {
                return Return_Info(200, "成功", "err@从表[" + dtlID + "]已经存在.");
            }

            CCFormAPI.CreateOrSaveDtl(frmID, dtlID, dtlName);
            return Return_Info(200, "成功", "创建成功.");
        }
        catch (RuntimeException ex)
        {
            Log.DebugWriteException(ex);
            return Return_Info(500, ex.getMessage(), null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("/Designer_FrmExcelDataSet")
    public final Object Designer_FrmExcelDataSet(String token, String frmID) throws Exception {
        synchronized (WF_Comm.frmVSTOTemplateLock)
        {
            Dev2Interface.Port_LoginByToken(token);
        }

        DataSet ds = new DataSet();
        MapData mapData = new MapData(frmID);
        DataTable mapDataTable = mapData.ToDataTableField("Sys_MapData");
        ds.Tables.add(mapDataTable);

        MapAttrs mapAttrs = new MapAttrs(frmID);
        DataTable mapAttrDataTable = mapAttrs.ToDataTableField("Sys_MapAttr");
        ds.Tables.add(mapAttrDataTable);

        DataTable mapDtlDataTable = mapData.getMapDtls().ToDataTableField("Sys_MapDtl");
        ds.Tables.add(mapDtlDataTable);

        //加入字段分组信息
        DataTable Sys_GroupField = mapData.getGroupFields().ToDataTableField("Sys_GroupField");
        ds.Tables.add(Sys_GroupField);

        for (MapDtl item : mapData.getMapDtls().Tolist())
        {
            MapAttrs mapDtlAttrs = new MapAttrs(item.getNo());
            DataTable mapDtlAttrDataTable = mapDtlAttrs.ToDataTableField("Sys_MapAttr_" + item.getNo());
            ds.Tables.add(mapDtlAttrDataTable);
        }
        return Return_Info(200, "成功", Json.ToJson(ds));

    }
     //   #endregion 设计器.
}
