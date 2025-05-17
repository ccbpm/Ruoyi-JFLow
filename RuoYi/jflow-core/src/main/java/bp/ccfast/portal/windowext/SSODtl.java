package bp.ccfast.portal.windowext;

import bp.da.DBAccess;
import bp.en.EntityMyPK;
import bp.en.Map;
import bp.en.UAC;

/**
 变量信息
*/
public class SSODtl extends EntityMyPK
{

		///#region 属性.
	/**
	 表达式
	*/
	public final String getRefPK()  {
		return this.GetValStrByKey(DtlAttr.RefPK);
	}
	public final void setRefPK(String value)  {
		this.SetValByKey(DtlAttr.RefPK, value);
	}
	public final String getExp0()  {
		return this.GetValStrByKey(DtlAttr.Exp0);
	}
	public final void setExp0(String value)  {
		this.SetValByKey(DtlAttr.Exp0, value);
	}
	public final int getDBType() {
		return this.GetValIntByKey(DtlAttr.DBType);
	}
	public final void setDBType(int value)  {
		this.SetValByKey(DtlAttr.DBType, value);
	}
	public final String getFontColor()  {
		return this.GetValStrByKey(DtlAttr.FontColor);
	}
	public final void setFontColor(String value)  {
		this.SetValByKey(DtlAttr.FontColor, value);
	}
	public final String getUrlExt()  {
		return this.GetValStrByKey(DtlAttr.UrlExt);
	}
	public final void setUrlExt(String value)  {
		this.SetValByKey(DtlAttr.UrlExt, value);
	}
	/**
	 数据源
	 */
	public final String getDBSrc()
	{
		return this.GetValStrByKey(DtlAttr.DBSrc);
	}
	public final void setDBSrc(String value)
	{
		this.SetValByKey(DtlAttr.DBSrc, value);
	}

		///#endregion 属性.


		///#region 权限控制.
	/**
	 控制权限
	*/
	@Override
	public UAC getHisUAC()
	{
		UAC uac = new UAC();

		uac.IsInsert = true;
		uac.IsDelete = true;
		uac.IsView = true;
		uac.IsUpdate = true;
		return uac;
	}

		///#endregion 权限控制.


		///#region 属性

		///#endregion 属性


		///#region 构造方法
	/**
	 变量信息
	*/
	public SSODtl()
	{
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

		Map map = new Map("GPM_WindowTemplateDtl", "数据项");

		map.AddMyPK(false);
		map.AddTBString(DtlAttr.RefPK, null, "关联键", false, false, 0, 40, 20);
		map.AddTBString(DtlAttr.Name, null, "系统名称", true, false, 0, 300, 120, true);
		map.AddTBString("Icon", "icon-link", "图标", true, false, 0, 150, 120, true);
		map.AddTBString("Url", "http://ccflow.org/?1=2", "地址", true, false, 0, 300, 120, true);

		String strs = "@NewWindow=新窗口@Self=本页打开";
		map.AddDDLStringEnum("OpenModel", "NewWindow", "链接打开方式", strs, true);
		map.AddTBInt("Idx", 0, "Idx", true, true);

		this.set_enMap(map);
		return this.get_enMap();
	}

		///#endregion

	@Override
	protected boolean beforeInsert() throws Exception {
		this.setMyPK(DBAccess.GenerGUID(0, null, null));
		return super.beforeInsert();
	}

}
