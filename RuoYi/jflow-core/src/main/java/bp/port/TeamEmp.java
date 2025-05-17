package bp.port;

import bp.da.*;
import bp.web.*;
import bp.en.*;
import bp.port.*;
import bp.sys.*;
import bp.difference.*;

/**
 用户组人员

*/
public class TeamEmp extends EntityMyPK
{
	///#region 属性
	public final String getEmpNo()
	{
		return this.GetValStringByKey(TeamEmpAttr.EmpNo);
	}
	public final void setEmpNo(String value)
	{
		this.SetValByKey(TeamEmpAttr.EmpNo, value);
	}
	public final String getTeamNo()
	{
		return this.GetValStringByKey(TeamEmpAttr.TeamNo);
	}
	public final void setTeamNo(String value)
	{
		this.SetValByKey(TeamEmpAttr.TeamNo, value);
	}

	/**
	 用户组人员

	*/
	public TeamEmp()
	{
	}
	/**
	 用户组人员

	*/
	@Override
	public Map getEnMap()
	{
		if (this.get_enMap() != null)
		{
			return this.get_enMap();
		}
		Map map = new Map("Port_TeamEmp", "用户组人员");
		map.setEnType(EnType.App);
		map.AddMyPK();
		map.AddTBString(TeamEmpAttr.TeamNo, null, "用户组", true, false, 0, 50, 20);
		map.AddTBString(TeamEmpAttr.EmpNo, null, "人员", true, false, 0, 50, 20);
		map.AddTBString(TeamEmpAttr.TeamName, null, "组名称", true, true, 0, 50, 100);
		map.AddTBString(TeamEmpAttr.StationNo, null,"角色名称", true, false, 0, 50, 50, true);
		map.AddTBString(TeamEmpAttr.StationNoT, null, "角色名称T", true, false, 0, 50, 50, true);

			//角色选择.
		if (SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single)
		{
			map.AddTBString("OrgNo", null, "组织编号'", false, false, 0, 50, 200);
		}
		this.set_enMap(map);
		return this.get_enMap();
	}
}
