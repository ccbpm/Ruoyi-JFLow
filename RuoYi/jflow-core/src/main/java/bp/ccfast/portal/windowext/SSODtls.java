package bp.ccfast.portal.windowext;
import bp.en.EntitiesMyPK;
import bp.en.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 变量信息s
*/
public class SSODtls extends EntitiesMyPK
{

		///#region 构造
	/**
	 变量信息s
	*/
	public SSODtls()
	{
	}
	/**
	 得到它的 Entity
	*/
	@Override
	public Entity getNewEntity()
	{
		return new SSODtl();
	}

		///#endregion


		///#region 为了适应自动翻译成java的需要,把实体转换成List.
	/**
	 转化成 java list,C#不能调用.

	 @return List
	*/
	public final List<SSODtl> ToJavaList()
	{
		return (List<SSODtl>)(Object)this;
	}
	/**
	 转化成list

	 @return List
	*/
	public final ArrayList<SSODtl> Tolist()
	{
		ArrayList<SSODtl> list = new ArrayList<SSODtl>();
		for (int i = 0; i < this.size(); i++)
		{
			list.add((SSODtl)this.get(i));
		}
		return list;
	}

		///#endregion 为了适应自动翻译成java的需要,把实体转换成List.
}
