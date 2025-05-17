package bp.port;

import bp.da.*;
import bp.difference.SystemConfig;
import bp.en.*;
import bp.sys.CCBPMRunModel;

/**
 部门角色人员对应 的摘要说明。
*/
public class TeamEmpStation extends EntityMyPK
{
	///#region 基本属性
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
	public final String getOrgNo()
	{
		return this.GetValStringByKey(DeptEmpStationAttr.OrgNo);
	}
	public final void setOrgNo(String value)
	{
		SetValByKey(DeptEmpStationAttr.OrgNo, value);
	}
	/**
	 人员

	*/
	public final String getEmpNo()
	{
		return this.GetValStringByKey(TeamEmpStationAttr.EmpNo);
	}
	public final void setEmpNo(String value)
	{
		SetValByKey(TeamEmpStationAttr.EmpNo, value);
		this.setMyPK(this.getTeamNo() + "_" + this.getEmpNo() + "_"+this.getStationNo());
	}
	/**
	 部门

	*/
	public final String getTeamNo()
	{
		return this.GetValStringByKey(TeamEmpStationAttr.TeamNo);
	}
	public final void setTeamNo(String value)
	{
		SetValByKey(TeamEmpStationAttr.TeamNo, value);
		this.setMyPK(this.getTeamNo() + "_" + this.getEmpNo() + "_" + this.getStationNo());
	}
	public final String getStationT()
	{
		return this.GetValStringByKey(TeamEmpStationAttr.StationNo);
	}
	/**
	角色

	*/
	public final String getStationNo()
	{
		return this.GetValStringByKey(TeamEmpStationAttr.StationNo);
	}
	public final void setStationNo(String value)
	{
		SetValByKey(TeamEmpStationAttr.StationNo, value);
		this.setMyPK(this.getTeamNo() + "_" + this.getEmpNo() + "_" + this.getStationNo());
	}
		///#region 构造函数
	/**
	 工作部门角色人员对应

	*/
	public TeamEmpStation()
	{
	}
	/**
	 重写基类方法

	*/
	@Override
	public Map getEnMap()
	{
		if (this.get_enMap() != null)
		{
			return this.get_enMap();
		}

		Map map = new Map("Port_DeptEmpStation", "组角色人员对应");

		map.AddTBStringPK("MyPK", null, "主键MyPK", false, true, 1, 150, 10);
		map.AddTBString(TeamEmpStationAttr.TeamNo, null, "组", true, true, 1, 100, 1);
		map.AddTBString(TeamEmpStationAttr.StationNo, null, "角色", true, true, 1, 50, 1);
		map.AddTBString(TeamEmpStationAttr.EmpNo, null, "操作员", true, true, 1, 100, 1);
		map.AddTBString(TeamEmpStationAttr.OrgNo, null, "组织编码", true, true, 0, 50, 50);

		this.set_enMap(map);
		return this.get_enMap();
	}
		///#endregion

	/**
	 更新删除前做的事情

	 @return
	*/
	@Override
	protected boolean beforeUpdateInsertAction() throws Exception {
		if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.SAAS)
		{
			this.setMyPK(this.getTeamNo() + "_" + this.getEmpNo().replace(this.getOrgNo() + "_", "") + "_" + this.getStationNo());
		}
		else
		{
			this.setMyPK(this.getTeamNo() + "_" + this.getEmpNo() + "_" + this.getStationNo());
		}
		return super.beforeUpdateInsertAction();
	}
}
