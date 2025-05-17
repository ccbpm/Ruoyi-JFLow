package bp.sys;

import bp.difference.SystemConfig;
import bp.en.EntitiesNoName;
import bp.en.Entity;
import bp.web.WebUser;

import java.util.ArrayList;
import java.util.List;

/** 
 用户自定义表s
*/
public class SFProcs extends EntitiesNoName
{

		///#region 构造
	/**
	 用户自定义表s
	*/
	public SFProcs()
	{
	}
	/** 
	 得到它的 Entity
	*/
	@Override
	public Entity getNewEntity()
	{
		return new SFProc();
	}
	/** 
	  重写过程全部的方法
	 
	 @return 
	*/
	@Override
	public int RetrieveAll() throws Exception {
		if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
		{
			return super.RetrieveAll("RDT");
		}
		return this.Retrieve("OrgNo", WebUser.getOrgNo(), "RDT");
	}

		///#endregion


		///#region 为了适应自动翻译成java的需要,把实体转换成List.
	/** 
	 转化成 java list,C#不能调用.
	 
	 @return List
	*/
	public final List<SFProc> ToJavaList()
	{
		return (List<SFProc>)(Object)this;
	}
	/** 
	 转化成list
	 
	 @return List
	*/
	public final ArrayList<SFProc> Tolist()
	{
		ArrayList<SFProc> list = new ArrayList<SFProc>();
		for (int i = 0; i < this.size(); i++)
		{
			list.add((SFProc)this.get(i));
		}
		return list;
	}

		///#endregion 为了适应自动翻译成java的需要,把实体转换成List.
}
