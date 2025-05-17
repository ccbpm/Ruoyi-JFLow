package bp.ccbill;

import bp.da.*;
import bp.sys.*;
import bp.en.*; import bp.en.Map;
import bp.tools.DateUtils;
import bp.web.WebUser;
import bp.wf.*;
import bp.wf.httphandler.*;
import bp.*;
import bp.wf.template.Printer.FrmPrintDB;
import bp.wf.template.Printer.FrmPrintDBs;
import bp.wf.template.Printer.FrmPrintTemplate;
import bp.wf.template.Printer.FrmPrintTemplates;
import bp.wf.template.frm.TemplateFileModel;

import java.util.*;
import java.time.*;

/**
 页面功能实体
*/
public class WF_CCBill_Opt extends bp.difference.handler.DirectoryPageBase
{

		///#region 构造方法.
	/**
	 构造函数
	*/
	public WF_CCBill_Opt()
	{
	}

		///#endregion 构造方法.


		///#region 关联单据.
	/**
	 设置父子关系.

	 @return
	*/
	public final String RefBill_Done() throws Exception {
		try
		{
			String frmID = this.GetRequestVal("FrmID");
			long workID = this.GetRequestValInt64("WorkID");
			GERpt rpt = new GERpt(frmID, workID);

			String pFrmID = this.GetRequestVal("PFrmID");
			long pWorkID = this.GetRequestValInt64("PWorkID");

			//把数据copy到当前的子表单里.
			GERpt rptP = new GERpt(pFrmID, pWorkID);
			rpt.Copy(rptP);
			rpt.setPWorkID(pWorkID);
			rpt.SetValByKey("PFrmID", pFrmID);
			rpt.Update();

			//更新控制表,设置父子关系.
			GenerBill gbill = new GenerBill(workID);
			gbill.setPFrmID(pFrmID);
			gbill.setPWorkID(pWorkID);
			gbill.Update();
			return "执行成功";
		}
		catch (RuntimeException ex)
		{
			return "err@" + ex.getMessage();
		}
	}
	/**
	 单据初始化

	 @return
	*/
	public final String RefBill_Init() throws Exception {
		DataSet ds = new DataSet();


			///#region 查询显示的列
		MapAttrs mattrs = new MapAttrs();
		mattrs.Retrieve(MapAttrAttr.FK_MapData, this.getFrmID(), MapAttrAttr.Idx);

		DataRow row = null;
		DataTable dt = new DataTable("Attrs");
		dt.Columns.Add("KeyOfEn", String.class);
		dt.Columns.Add("Name", String.class);
		dt.Columns.Add("Width", Integer.class);
		dt.Columns.Add("UIContralType", Integer.class);
		dt.Columns.Add("LGType", Integer.class);

		//设置标题、单据号位于开始位置


		for (MapAttr attr : mattrs.ToJavaList())
		{
			String searchVisable = attr.getatPara().GetValStrByKey("SearchVisable");
			if (Objects.equals(searchVisable, "0"))
			{
				continue;
			}
			if (attr.getUIVisible() == false)
			{
				continue;
			}
			row = dt.NewRow();
			row.setValue("KeyOfEn", attr.getKeyOfEn());
			row.setValue("Name", attr.getName());
			row.setValue("Width", attr.getUIWidthInt());
			row.setValue("UIContralType", attr.getUIContralType().getValue());
			row.setValue("LGType", attr.getLGType().getValue());
			dt.Rows.add(row);
		}
		ds.Tables.add(dt);

			///#endregion 查询显示的列


			///#region 查询语句

		MapData md = new MapData(this.getFrmID());

		GEEntitys rpts = new GEEntitys(this.getFrmID());

		Attrs attrs = rpts.getNewEntity().getEnMap().getAttrs();

		QueryObject qo = new QueryObject(rpts);


			///#region 关键字字段.
		String keyWord = this.GetRequestVal("SearchKey");

		if (DataType.IsNullOrEmpty(keyWord) == false && keyWord.length() >= 1)
		{
			qo.addLeftBracket();
			if (Objects.equals(bp.difference.SystemConfig.getAppCenterDBVarStr(), "@") || Objects.equals(bp.difference.SystemConfig.getAppCenterDBVarStr(), "?"))
			{
				qo.AddWhere("Title", " LIKE ", bp.difference.SystemConfig.getAppCenterDBType() == DBType.MySQL ? (" CONCAT('%'," + bp.difference.SystemConfig.getAppCenterDBVarStr() + "SKey,'%')") : (" '%'+" + bp.difference.SystemConfig.getAppCenterDBVarStr() + "SKey+'%'"));
			}
			else
			{
				qo.AddWhere("Title", " LIKE ", " '%'||" + bp.difference.SystemConfig.getAppCenterDBVarStr() + "SKey||'%'");
			}
			qo.addOr();
			if (Objects.equals(bp.difference.SystemConfig.getAppCenterDBVarStr(), "@") || Objects.equals(bp.difference.SystemConfig.getAppCenterDBVarStr(), "?"))
			{
				qo.AddWhere("BillNo", " LIKE ", bp.difference.SystemConfig.getAppCenterDBType() == DBType.MySQL ? ("CONCAT('%'," + bp.difference.SystemConfig.getAppCenterDBVarStr() + "SKey,'%')") : ("'%'+" + bp.difference.SystemConfig.getAppCenterDBVarStr() + "SKey+'%'"));
			}
			else
			{
				qo.AddWhere("BillNo", " LIKE ", "'%'||" + bp.difference.SystemConfig.getAppCenterDBVarStr() + "SKey||'%'");
			}

			qo.getMyParas().Add("SKey", keyWord, false);
			qo.addRightBracket();

		}
		else
		{
			qo.AddHD();
		}

			///#endregion 关键字段查询


			///#region 时间段的查询
		String dtFrom = this.GetRequestVal("DTFrom");
		String dtTo = this.GetRequestVal("DTTo");
		if (DataType.IsNullOrEmpty(dtFrom) == false)
		{

			//取前一天的24：00
			if (dtFrom.trim().length() == 10) //2017-09-30
			{
				dtFrom += " 00:00:00";
			}
			if (dtFrom.trim().length() == 16) //2017-09-30 00:00
			{
				dtFrom += ":00";
			}

			dtFrom = DateUtils.addDay(DateUtils.parse(dtFrom, "yyyy-MM-dd"), -1) + " 24:00";

			if (dtTo.trim().length() < 11 || dtTo.trim().indexOf(' ') == -1)
			{
				dtTo += " 24:00";
			}

			qo.addAnd();
			qo.addLeftBracket();
			qo.setSQL( " RDT>= '" + dtFrom + "'");
			qo.addAnd();
			qo.setSQL("RDT <= '" + dtTo + "'");
			qo.addRightBracket();
		}

			///#endregion 时间段的查询

		qo.DoQuery("OID", this.getPageSize(), this.getPageIdx());


			///#endregion

		DataTable mydt = rpts.ToDataTableField("dt");
		mydt.setTableName("DT");

		ds.Tables.add(mydt); //把数据加入里面.

		return bp.tools.Json.ToJson(ds);
	}

	/**
	 * 获得打印列表
	 * @return
	 */
	public String Printer_Init() throws Exception {
		//1. 获得打印记录.
		FrmPrintDBs dbs = new FrmPrintDBs();
		dbs.Retrieve("FrmID", this.getFrmID(),"WorkID",this.getWorkID());
		//2. 获得打印模版.
		FrmPrintTemplates templates = new FrmPrintTemplates();
		try
		{
			templates.Retrieve("FrmID", this.getFrmID(), "IsEnable", 1);
		}catch(Exception e)
		{
			templates.getNewEntity().CheckPhysicsTable();
			templates.Retrieve("FrmID", this.getFrmID(), "IsEnable", 1);
		}

		FrmPrintDB db = null;
		//3. 组织数据.
		for(FrmPrintTemplate template : templates.ToJavaList())
		{
			Entity en = dbs.GetEntityByKey("FrmPrintTemplateID", template.getMyPK());
			if(en == null)
			{
				db = new FrmPrintDB();
				db.SetValByKey("FrmPrintTemplateID", template.getMyPK());
				db.SetValByKey("FrmPrintTemplateName", template.getName());
				db.SetValByKey("FrmID", template.getFrmID());
				db.SetValByKey("WorkID", this.getWorkID());
				db.SetValByKey("RTFFileName",template.GetParaString("FileName"));
				db.SetValByKey("State","未打印");
				dbs.AddEntity(db);
			}

		}
		return bp.tools.Json.ToJson(dbs.ToDataTableField());
	}

	public String Printer_SelectOne() throws Exception {
		String templateID = this.GetRequestVal("TemplateID");
		String dbMyPK = this.GetRequestVal("DBMyPK");
		String OpenType = this.GetRequestVal("OpenType");
		FrmPrintTemplate template = new FrmPrintTemplate(templateID);
		boolean IsRePrint = this.GetRequestValBoolen("IsRePrint");

		FrmPrintDB db = new FrmPrintDB();

		if (DataType.IsNullOrEmpty(dbMyPK) == false && IsRePrint==false && OpenType.equals("0"))
		{
			db.setMyPK(dbMyPK);
			db.Retrieve();

			String fileName = DBAccess.GenerGUID() + "." + db.GetValByKey("FileExt");

			//1. 生成文件下载.
			String file = bp.difference.SystemConfig.getPathOfTemp() + "//" + fileName;
			DBAccess.GetFileFromDB(file, "Sys_FrmPrintDB", "MyPK", db.getMyPK(), "DBFile");

			//2. 记录下载日志.
			bp.ccbill.Dev2Interface.WriteTrack(this.getFrmID(),"", this.getWorkIDStr(), "Printer", "打印:" + template.getName());
			return "/DataUser/Temp/" + fileName;
		}
		db.SetValByKey("FrmID",this.getFrmID());
		db.SetValByKey("WorkID", this.getWorkIDStr());
		db.SetValByKey("RecNo", WebUser.getNo());
		db.SetValByKey("RecName", WebUser.getName());
		db.SetValByKey("RDT", DataType.getCurrentDateTime());
		db.SetValByKey("FrmPrintTemplateID", template.getMyPK());
		db.SetValByKey("FrmPrintTemplateName", template.getName());
		db.SetValByKey("MyPK",DBAccess.GenerGUID());
		db.SetValByKey("RTFFileName",template.GetParaString("FileName"));
		db.SetValByKey("State","已打印");
		if (template.getTemplateFileModel() == TemplateFileModel.RTF)
		{
			MapData mapdata = new MapData(this.getFrmID());

			WF_WorkOpt wkOpt = new WF_WorkOpt();
			String billUrl = "";
			if(mapdata.getEntityType()== EntityType.FrmEntityNoName)
				billUrl = wkOpt.PrintDoc_EntityNoNameDoneIt(this.getWorkIDStr(),this.getFrmID(),template);
			else
				billUrl = wkOpt.PrintDoc_FormDoneIt(null, this.getWorkID(), this.getFID(), this.getFrmID(), template);
			billUrl = billUrl.replace("file@rtf@", "").replace("file@pdf@","/");
			if (billUrl.endsWith(".doc"))
				db.SetValByKey("FileExt", "doc");
			if (billUrl.endsWith(".pdf"))
				db.SetValByKey("FileExt", "pdf");
			if(DataType.IsNullOrEmpty(dbMyPK) == false)
				db.setMyPK(dbMyPK);
			db.Save();
			DBAccess.SaveFileToDB(bp.difference.SystemConfig.getPathOfDataUser() + billUrl.replace("/DataUser",""), "Sys_FrmPrintDB", "MyPK", db.getMyPK(), "DBFile");
			return Printer_UrlReturn(template.getTemplateFileModel(), db.getMyPK(), billUrl, OpenType);
		}

		//
		if (template.getTemplateFileModel() == TemplateFileModel.VSTOForExcel)
		{
			String fileTemplagePath = DBAccess.GenerGUID() + ".xlsx";
			String fileFullName = bp.difference.SystemConfig.getPathOfTemp() + "//" + fileTemplagePath;
			DBAccess.GetFileFromDB(bp.difference.SystemConfig.getPathOfTemp() + "//" + fileTemplagePath, "Sys_FrmPrintTemplate", "MyPK", templateID, "DBFile");
			//@zsy. 根据模版数据生成文件.

			// 生成打印文件.
			bp.sys.printer.OfficerHelper help = new bp.sys.printer.OfficerHelper();

			help.MakeExcelFile(this.getFrmID(), fileFullName, template, this.getWorkID());
			//保存文件到数据库.
			db.SetValByKey("FileExt", "xlsx");
			if(DataType.IsNullOrEmpty(dbMyPK) == false)
				db.setMyPK(dbMyPK);
			db.Save();
			DBAccess.SaveFileToDB(bp.difference.SystemConfig.getPathOfTemp() + "//" + fileTemplagePath, "Sys_FrmPrintDB", "MyPK", db.getMyPK(), "DBFile");
			String path = "/DataUser/Temp/" + fileTemplagePath;
			return Printer_UrlReturn(template.getTemplateFileModel(), db.getMyPK(), path, OpenType);
		}

		if (template.getTemplateFileModel() == TemplateFileModel.VSTOForWord)
		{
			String fileTemplagePath = DBAccess.GenerGUID() + ".docx";
			String fileFullName = bp.difference.SystemConfig.getPathOfTemp() + "//" + fileTemplagePath;
			try
			{
				DBAccess.GetFileFromDB(fileFullName, "Sys_FrmPrintTemplate", "MyPK", templateID, "DBFile");
			}
			catch (Exception ex)
			{
				throw new Exception("err@获取Word模板错误：应该是设计人员没有设计打印模板.");
			}
			//@zsy. 根据模版数据生成文件.
			// 生成打印文件.
			bp.sys.printer.OfficerHelper help = new bp.sys.printer.OfficerHelper();

			help.MakeDocFile(this.getFrmID(), fileFullName, template, this.getWorkID());
			//保存文件到数据库.
			db.SetValByKey("FileExt", "docx");
			if(DataType.IsNullOrEmpty(dbMyPK) == false)
				db.setMyPK(dbMyPK);
			db.Save();
			DBAccess.SaveFileToDB(fileFullName, "Sys_FrmPrintDB", "MyPK", db.getMyPK(), "DBFile");
			String path = "/DataUser/Temp/" + fileTemplagePath;
			return Printer_UrlReturn(template.getTemplateFileModel(), db.getMyPK(), path, OpenType);
		}
		if (template.getTemplateFileModel() == TemplateFileModel.WPS)
		{
			//@zsy.
		}
		throw new Exception("err@没有判断的类型:" + template.getTemplateFileModel());
	}
	private String Printer_UrlReturn(TemplateFileModel tmodel,  String dbMyPK, String path, String OpenType)
	{
		if (tmodel == tmodel.VSTOForExcel && OpenType.equals("1"))
		{
			String url = "excelform://-fromccflow,AppID=FrmPrinter,MyPK=" + dbMyPK + ",Token=" + WebUser.getToken() + ",FrmID=" + this.getFrmID();
			return url;
		}

		if (tmodel ==tmodel.VSTOForWord && OpenType.equals("1"))
		{
			String url = "wordform://-fromccflow,AppID=FrmPrinter,MyPK=" + dbMyPK + ",Token=" + WebUser.getToken() + ",FrmID=" + this.getFrmID();
			return url;
		}
		return path;
	}
}
