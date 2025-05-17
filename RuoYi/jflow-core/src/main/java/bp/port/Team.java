package bp.port;


import bp.da.DBAccess;
import bp.da.DataType;
import bp.difference.SystemConfig;
import bp.en.EnType;
import bp.en.EntityNoName;
import bp.en.Map;
import bp.sys.CCBPMRunModel;
import bp.web.WebUser;

/**
 用户组

*/
public class Team extends EntityNoName
{

	/**
	 类型
	*/
	public final String getTeamTypeNo()
	{
		return this.GetValStringByKey(TeamAttr.TeamTypeNo);
	}
	/**
	 用户组

	*/
	public Team()
	{
	}
	/**
	 用户组

	 @param no
	*/
	public Team(String no) throws Exception {
		this.setNo(no);
		this.Retrieve();
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

		Map map = new Map("Port_Team", "标签");
		map.setEnType(EnType.Sys);
		map.setItIsAutoGenerNo(true);

		map.AddTBStringPK(TeamAttr.No, null, "编号", true, true, 3, 50, 100);
		map.AddTBString(TeamAttr.Name, null, "名称", true, false, 0, 300, 20);
		map.AddDDLEntities(TeamAttr.TeamTypeNo, null, "类型", new TeamTypes(), true);
		map.AddTBString(TeamAttr.ParentNo, null, "ParentNo", true, false, 0, 300, 20);
		map.AddTBInt(TeamAttr.Idx, 0, "顺序", true, false);

		if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.SAAS)
		{
			map.AddTBString(StationAttr.OrgNo, null, "隶属组织", true, true, 0, 50, 250);
			map.AddHidden(StationAttr.OrgNo, "=", "@WebUser.OrgNo"); //加隐藏条件.
		}

		if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.GroupInc)
		{
			map.AddTBString(StationAttr.OrgNo, null, "隶属组织", true, true, 0, 50, 250);

			if (SystemConfig.getGroupStationModel()== 0)
			{
				map.AddHidden(StationAttr.OrgNo, "=", "@WebUser.OrgNo"); //每个组织都有自己的岗责体系的时候. 加隐藏条件.
			}
			if (SystemConfig.getGroupStationModel() == 2)
			{
				map.AddTBString(StationAttr.FK_Dept, null, "隶属部门", true, true, 0, 50, 250);
				map.AddHidden(StationAttr.FK_Dept, "=", "@WebUser.FK_Dept");
			}
		}

		map.AddSearchAttr(TeamAttr.TeamTypeNo);
		this.set_enMap(map);
		return this.get_enMap();
	}
	@Override
	protected boolean beforeInsert() throws Exception {
		if (SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single)
		{
			if (DataType.IsNullOrEmpty(this.GetValStringByKey("OrgNo")) == true)
			{
				this.SetValByKey("OrgNo", WebUser.getOrgNo());
			}
		}
		if (DataType.IsNullOrEmpty(this.GetValStringByKey("No")) == true)
		{
			this.SetValByKey("No", DBAccess.GenerGUID());
		}

		return super.beforeInsert();
	}

	@Override
	protected boolean beforeUpdateInsertAction() throws Exception {
		if (DataType.IsNullOrEmpty(this.getName()) == true)
		{
			throw new RuntimeException("请输入名称");
		}

		if (DataType.IsNullOrEmpty(this.getTeamTypeNo()) == true)
		{
			throw new RuntimeException("请选择类型");
		}

		if (SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single)
		{
			if (DataType.IsNullOrEmpty(this.GetValStringByKey("OrgNo")) == true)
			{
				this.SetValByKey("OrgNo", WebUser.getOrgNo());
			}
		}

		return super.beforeUpdateInsertAction();
	}
}
