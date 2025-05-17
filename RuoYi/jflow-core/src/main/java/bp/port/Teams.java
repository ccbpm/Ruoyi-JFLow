package bp.port;

import bp.da.DBAccess;
import bp.difference.SystemConfig;
import bp.en.EntitiesNoName;
import bp.en.*;
import bp.sys.CCBPMRunModel;
import bp.web.WebUser;

/**
 用户组s

*/
public class Teams extends EntitiesNoName
{
	/**
	 用户组s

	*/
	public Teams()
	{
	}
	/**
	 得到它的 Entity

	*/
	@Override
	public Entity getNewEntity()
	{
		return new Team();
	}

	/**
	 查询全部

	 @return
	*/
	@Override
	public int RetrieveAll() throws Exception {
		if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
		{
			return super.RetrieveAll("Idx");
		}

		if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.SAAS)
		{
			return this.Retrieve("OrgNo", WebUser.getOrgNo(), "Idx");
		}

		//集团模式下的角色体系: @0=每套组织都有自己的角色体系@1=所有的组织共享一套岗则体系.
		if (SystemConfig.getGroupStationModel() == 1)
		{
			return super.RetrieveAll("Idx");
		}

		//按照orgNo查询.
		return this.Retrieve("OrgNo", WebUser.getOrgNo(), "Idx");
	}
	@Override
	public int RetrieveAllFromDBSource() throws Exception {
		return this.RetrieveAll();
	}
	/**
	 转化成 java list,C#不能调用.

	 @return List
	*/
	public final java.util.ArrayList<Team> ToJavaList()
	{
		return (java.util.ArrayList<Team>)(Object)this;
	}
	/**
	 转化成list

	 @return List
	*/
	public final java.util.ArrayList<Team> Tolist()
	{
		java.util.ArrayList<Team> list = new java.util.ArrayList<Team>();
		for (int i = 0; i < this.size(); i++)
		{
			list.add((Team)this.get(i));
		}
		return list;
	}
}
