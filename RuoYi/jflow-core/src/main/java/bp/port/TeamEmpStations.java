package bp.port;

import bp.da.DBAccess;
import bp.en.EntitiesMyPK;
import bp.en.*;

/**
 部门角色人员对应

*/
public class TeamEmpStations extends EntitiesMyPK
{
	/**
	 工作部门角色人员对应
	*/
	public TeamEmpStations()
	{
	}

	/**
	 得到它的 Entity
	*/
	@Override
	public Entity getNewEntity()
	{
		return new TeamEmpStation();
	}
	/**
	 转化成 java list,C#不能调用.

	 @return List
	*/
	public final java.util.List<TeamEmpStation> ToJavaList()
	{
		return (java.util.List<TeamEmpStation>)(Object)this;
	}
	/**
	 转化成list

	 @return List
	*/
	public final java.util.ArrayList<TeamEmpStation> Tolist()
	{
		java.util.ArrayList<TeamEmpStation> list = new java.util.ArrayList<TeamEmpStation>();
		for (int i = 0; i < this.size(); i++)
		{
			list.add((TeamEmpStation)this.get(i));
		}
		return list;
	}
	public final String DelteNotInEmp()
	{
		String sql = "DELETE FROM Port_DeptEmpStation WHERE FK_Emp NOT IN (SELECT No FROM Port_Emp)";
		DBAccess.RunSQL(sql);
		return "删除成功";
	}
}
