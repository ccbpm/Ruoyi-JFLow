package bp.port;

import bp.en.EntitiesMyPK;
import bp.en.Entity;

/**
 用户组人员s

*/
public class TeamEmps extends EntitiesMyPK
{
	/**
	 用户组s

	*/
	public TeamEmps()
	{
	}
	/**
	 得到它的 Entity

	*/
	@Override
	public Entity getNewEntity()
	{
		return new TeamEmp();
	}
	/**
	 转化成 java list,C#不能调用.

	 @return List
	*/
	public final java.util.ArrayList<TeamEmp> ToJavaList()
	{
		return (java.util.ArrayList<TeamEmp>)(Object)this;
	}
	/**
	 转化成list

	 @return List
	*/
	public final java.util.ArrayList<TeamEmp> Tolist()
	{
		java.util.ArrayList<TeamEmp> list = new java.util.ArrayList<TeamEmp>();
		for (int i = 0; i < this.size(); i++)
		{
			list.add((TeamEmp)this.get(i));
		}
		return list;
	}
}
