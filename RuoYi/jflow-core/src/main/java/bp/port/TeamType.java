package bp.port;
import bp.da.DBAccess;
import bp.da.DataType;
import bp.difference.SystemConfig;
import bp.en.EntityNoName;
import bp.en.Map;
import bp.en.UAC;
import bp.sys.CCBPMRunModel;
import bp.web.WebUser;

/**
  用户组类型

*/
public class TeamType extends EntityNoName
{
	@Override
	public UAC getHisUAC()
	{
		UAC uac = new UAC();
		uac.OpenForSysAdmin();
		return uac;
	}
	/**
	 用户组类型

	*/
	public TeamType()
	{
	}
	/**
	 用户组类型

	 @param _No
	*/
	public TeamType(String _No) throws Exception {
		super(_No);
	}

	/**
	 用户组类型Map

	*/
	@Override
	public Map getEnMap()
	{
		if (this.get_enMap() != null)
		{
			return this.get_enMap();
		}
		Map map = new Map("Port_TeamType", "标签类型");
		map.setCodeStruct("2");

		map.AddTBStringPK(TeamTypeAttr.No, null, "编号", true, true, 1, 50, 100);
		map.AddTBString(TeamTypeAttr.Name, null, "名称", true, false, 1, 50, 20);

		map.AddTBInt(TeamTypeAttr.Idx, 0, "顺序", true, false);

		if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.SAAS)
		{
			map.AddTBString(StationAttr.OrgNo, null, "隶属组织", true, true, 0, 50, 250);
			map.AddHidden(StationAttr.OrgNo, "=", "@WebUser.OrgNo"); //加隐藏条件.
		}

		if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.GroupInc)
		{
			map.AddTBString(StationAttr.OrgNo, null, "隶属组织", true, true, 0, 50, 250);

			if (SystemConfig.getGroupStationModel() == 0)
			{
				map.AddHidden(StationAttr.OrgNo, "=", "@WebUser.OrgNo"); //每个组织都有自己的岗责体系的时候. 加隐藏条件.
			}
			if (SystemConfig.getGroupStationModel() == 2)
			{
				map.AddTBString(StationAttr.FK_Dept, null, "隶属部门", false, false, 0, 50, 250);
				map.AddHidden(StationAttr.FK_Dept, "=", "@WebUser.FK_Dept");
			}
		}

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
			this.SetValByKey("No",DBAccess.GenerGUID());
		}

		return super.beforeInsert();
	}

	@Override
	protected boolean beforeUpdateInsertAction() throws Exception {
		if (DataType.IsNullOrEmpty(this.getName()) == true)
		{
			throw new RuntimeException("请输入名称");
		}
		return super.beforeUpdateInsertAction();
	}
}
