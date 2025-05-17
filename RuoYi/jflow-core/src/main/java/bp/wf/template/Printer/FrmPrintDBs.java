package bp.wf.template.Printer;

import bp.en.EntitiesMyPK;
import bp.en.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 打印模板s
*/
public class FrmPrintDBs extends EntitiesMyPK
{

		///#region 构造
	/**
	 得到它的 Entity
	*/
	@Override
	public Entity getNewEntity()
	{
		return new FrmPrintDB();
	}
	/**
	 打印模板
	*/
	public FrmPrintDBs()
	{
	}
	/**
	 转化成 java list,C#不能调用.

	 @return List
	*/
	public final List<FrmPrintDB> ToJavaList()
	{
		return (List<FrmPrintDB>)(Object)this;
	}
	/**
	 转化成list

	 @return List
	*/
	public final ArrayList<FrmPrintDB> Tolist()
	{
		ArrayList<FrmPrintDB> list = new ArrayList<FrmPrintDB>();
		for (int i = 0; i < this.size(); i++)
		{
			list.add((FrmPrintDB)this.get(i));
		}
		return list;
	}
}
