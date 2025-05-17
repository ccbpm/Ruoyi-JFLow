package bp.wf.httphandler;

import bp.da.*;
import bp.difference.handler.CommonFileUtils;
import bp.pub.PubClass;
import bp.wf.template.*;
import bp.wf.template.Printer.FrmPrintTemplate;
import bp.wf.template.frm.*;
import bp.difference.*;
import bp.*;
import bp.wf.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 页面功能实体
*/
public class WF_Admin_FoolFormDesigner_PrintTemplate extends bp.difference.handler.DirectoryPageBase
{
	/**
	 构造函数
	*/
	public WF_Admin_FoolFormDesigner_PrintTemplate()
	{
	}
	public final String Bill_Save() throws Exception {
		FrmPrintTemplate bt = new FrmPrintTemplate();
		HttpServletRequest request = getRequest();
		// 上传附件
		MultipartHttpServletRequest mrequest = CommonFileUtils.getMultipartHttpServletRequest(request);
		MultipartFile item = mrequest.getFile("file");
		String fileName = item.getOriginalFilename();
		String filepath = SystemConfig.getPathOfCyclostyleFile() + fileName;
		filepath = filepath.replace("\\", "/");
		File tempFile = new File(filepath);
		MultipartFile multipartFile = mrequest.getFile("File");
		CommonFileUtils.upload(request,"file",tempFile);

		bt.setNodeID(this.getFK_Node());
		bt.setFrmID(this.getFK_MapData());
		bt.setMyPK(this.GetRequestVal("TB_No"));

		if (DataType.IsNullOrEmpty(bt.getMyPK()))
		{
			bt.setMyPK(String.valueOf(DBAccess.GenerOID("Template")));
		}

		bt.setName(this.GetRequestVal("TB_Name"));
		bt.setTempFilePath(fileName); //文件.

		//打印的文件类型.
		bt.setHisPrintFileType(PrintFileType.forValue(this.GetRequestValInt("DDL_BillFileType")));

		//打开模式.
		bt.setPrintOpenModel(PrintOpenModel.forValue(this.GetRequestValInt("DDL_BillOpenModel")));

		//二维码模式.
		bt.setQRModel(QRModel.forValue(this.GetRequestValInt("DDL_QRModel")));

		bt.setTemplateFileModel(TemplateFileModel.forValue(this.GetRequestValInt("TemplateFileModel")));


		bt.Save();

		bt.SaveFileToDB("DBFile", filepath); //把文件保存到数据库里.

		Cache.ClearCache(fileName);
		Cache.ClearCache(fileName+ "Para");

		return "保存成功.";
	}
	public final String PrintTemplate_Save() throws Exception {
		FrmPrintTemplate bt = new FrmPrintTemplate();
		HttpServletRequest request = getRequest();
		// 上传附件
		MultipartHttpServletRequest mrequest = CommonFileUtils.getMultipartHttpServletRequest(request);
		MultipartFile item = mrequest.getFile("file");
		String fileName = item.getOriginalFilename();
		String filepath = SystemConfig.getPathOfTemp() + fileName;
		filepath = filepath.replace("\\", "/");
		File tempFile = new File(filepath);
		MultipartFile multipartFile = mrequest.getFile("File");
		CommonFileUtils.upload(request,"file",tempFile);
		bt.setMyPK(this.getMyPK());
		bt.RetrieveFromDBSources();
		bt.SetPara("FileName",fileName.substring(0,fileName.lastIndexOf(".")));
		bt.Update();
		bt.SaveFileToDB("DBFile", filepath); //把文件保存到数据库里.
		tempFile.delete();
		Cache.ClearCache(fileName);
		Cache.ClearCache(fileName+ "Para");
		//DBAccess.RunSQL("UPDATE Sys_FrmPrintDB SET Ver=(Select Max(Ver)+1 From Sys_FrmPrintDB Where FrmPrintTemplateID='"+bt.getMyPK()+"' AND FrmID = '"+bt.getFrmID()+"') Where FrmPrintTemplateID='"+bt.getMyPK()+"' AND FrmID = '"+bt.getFrmID()+"'");
		return "保存成功.";
	}
	/**
	 下载文件.
	*/
	public final String PrintTemplate_Download() throws Exception {
		FrmPrintTemplate en = new FrmPrintTemplate(this.getMyPK());
		String subFix = ".rtf";
		String fileModel = en.GetValStringByKey("FileModel");
		if(fileModel.equals("VSTOExcel")) subFix=".xls";
		if(fileModel.equals("VSTOWord")) subFix=".doc";
		String filepath = SystemConfig.getPathOfTemp() + en.getName()+subFix;
		DBAccess.GetFileFromDB(filepath,"Sys_FrmPrintTemplate","MyPK",this.getMyPK(),"DBFile");
		return "/DataUser/Temp/"+ en.getName()+subFix;
	}
		///#endregion

}
