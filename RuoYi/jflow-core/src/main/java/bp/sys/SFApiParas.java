package bp.sys;

import bp.en.EntitiesMyPK;
import bp.en.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 WebApi参数s
*/
public class SFApiParas extends EntitiesMyPK
{
	///#region 构造
	/**
	 WebApi参数s
	*/
	public SFApiParas()
	{
	}
	/** 
	 得到它的 Entity
	*/
	@Override
	public Entity getNewEntity()
	{
		return new SFApiPara();
	}

	///#endregion
	///#region 为了适应自动翻译成java的需要,把实体转换成List.
	/** 
	 转化成 java list,C#不能调用.
	 
	 @return List
	*/
	public final List<SFApiPara> ToJavaList()
	{
		return (List<SFApiPara>)(Object)this;
	}
	/** 
	 转化成list
	 
	 @return List
	*/
	public final ArrayList<SFApiPara> Tolist()
	{
		ArrayList<SFApiPara> list = new ArrayList<SFApiPara>();
		for (int i = 0; i < this.size(); i++)
		{
			list.add((SFApiPara)this.get(i));
		}
		return list;
	}

		///#endregion 为了适应自动翻译成java的需要,把实体转换成List.
}
