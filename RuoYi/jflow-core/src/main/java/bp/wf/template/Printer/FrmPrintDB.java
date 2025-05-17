package bp.wf.template.Printer;
import bp.da.DBAccess;
import bp.da.DataType;
import bp.en.EntityMyPK;
import bp.en.Map;

import java.io.File;

/**
 打印模板
*/
public class FrmPrintDB extends EntityMyPK
{
	/**
	 打印模板
	*/
	public FrmPrintDB()
	{
	}
	/// <summary>
	/// 打印模板
	/// </summary>
	/// <param name="mypk">主键</param>
	public FrmPrintDB(String mypk) throws Exception {
		super(mypk.replace("\n", "").trim());
	}


	/**
	 获得单据文件流
	 @return
	 */
	public final byte[] GenerFile() throws Exception {
		byte[] bytes = DBAccess.GetByteFromDB("Sys_FrmPrintDB", "MyPK", this.getMyPK(), "DBFile");
		if (bytes != null)
		{
			return bytes;
		}

		//如果没有找到，就看看默认的文件是否有.
		String tempExcel = bp.difference.SystemConfig.getPathOfDataUser() + "FrmVSTOTemplate/" + this.getMyPK() + ".xlsx";
		if ((new File(tempExcel)).isFile() == false)
		{
			tempExcel = bp.difference.SystemConfig.getPathOfDataUser() + "FrmVSTOTemplate/EmptyTemplate.xlsx";
		}
		bytes = DataType.ConvertFileToByte(tempExcel);

		return bytes;
	}

	/**
	 重写基类方法
	*/
	@Override
	public Map getEnMap()
	{
		if (get_enMap() != null)
		{
			return get_enMap();
		}
		Map map = new Map("Sys_FrmPrintDB", "打印模板数据源");
		map.AddMyPK();

		map.AddTBString("FrmID", null,"表单ID", false, false, 0, 60, 60);
		map.AddTBString("FrmName", null,"表单名称", false, false, 0, 60, 60);
		map.AddTBString("WorkID", null,"实例ID", false, false, 0, 60, 60);
		map.AddTBString("FrmPrintTemplateID", null,"模板ID", false, false, 0, 60, 60);
		map.AddTBString("FrmPrintTemplateName", null,"模板名称", true, false, 0, 200, 100);
		map.AddTBString("RTFFileName", null,"文件名称", false, false, 0, 60, 60);
		map.AddTBString("RecNo", null,"打印人ID", false, false, 0, 60, 60);
		map.AddTBString("RecName", null,"打印人名称", false, false, 0, 60, 60);
		map.AddTBDateTime("RDT", null,"打印日期", false, false);
		map.AddTBString("FileExt", null, "文件后缀", false, false, 0, 60, 60);
		map.AddTBString("State", null, "状态", false, false, 0, 60, 60);

		this.set_enMap(map);
		return this.get_enMap();
	}


		///#endregion
	@Override
	protected boolean beforeInsert() throws Exception
	{
		if (DataType.IsNullOrEmpty(this.getMyPK()) == true)
		{
			this.setMyPK(DBAccess.GenerGUID());
		}
		return super.beforeInsert();
	}
}
