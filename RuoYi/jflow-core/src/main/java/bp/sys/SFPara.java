package bp.sys;

import bp.en.*;
import bp.en.Map;

/** 
 用户自定义表
*/
public class SFPara extends EntityMyPK
{
	public final String getParaKey()  {
		return this.GetValStrByKey("ParaKey");
	}
	public final String getParaName()  {
		return this.GetValStrByKey("ParaName");
	}
	public final int getItIsSys()  {
		return this.GetValIntByKey("IsSys");
	}
	public final String getExp()  {
		return this.GetValStrByKey("Exp");
	}

	public final String getExpVal() throws Exception {
		String exp = this.GetValStrByKey("Exp");

        switch (exp) {
            case "@WebUser.No":
                return bp.web.WebUser.getNo();
            case "@WebUser.Name":
                return bp.web.WebUser.getName();
            case "@WebUser.FK_Dept":
                return bp.web.WebUser.getDeptNo();
            case "@WebUser.DeptNo":
                return bp.web.WebUser.getDeptNo();
            case "@WebUser.FK_DeptName":
                return bp.web.WebUser.getDeptName();
            case "@WebUser.OrgNo":
                return bp.web.WebUser.getOrgNo();
            case "@WebUser.OrgName":
                return bp.web.WebUser.getOrgName();
            case "@Token":
                return "xxxxxx";
        }

        return exp;
	}


		///#region 构造方法
	@Override
	public UAC getHisUAC()
	{
		UAC uac = new UAC();
		uac.OpenForSysAdmin();
		uac.IsInsert = false;
		return uac;
	}
	/** 
	 用户自定义表
	*/
	public SFPara()
	{
	}
	/** 
	 EnMap
	*/
	@Override
	public Map getEnMap() {
		if (this.get_enMap() != null)
		{
			return this.get_enMap();
		}
		Map map = new Map("Sys_SFPara", "参数");

		map.AddMyPK();
		map.AddTBString("RefPKVal", null, "实体主键", false, false, 1, 200, 20);
		map.AddTBString("ParaKey", null, "参数标记", true, false, 1, 200, 100);
		map.AddTBString("ParaName", null, "参数名称", true, false, 0, 200, 100);
		map.AddTBString("DataType", null, "数据类型", true, false, 0, 200, 100);
		map.AddTBString("IsSys", null, "获取类型", true, false, 0, 200, 100);
		map.AddTBString("Exp", null, "表达式", true, false, 0, 200, 100);

		//map.AddDDLStringEnum("DataType", "String", "数据类型", "@String=String@Int=Int@Float=Float", true, "", false, 100);
		//map.AddDDLStringEnum("IsSys", "String", "获取类型", "@0=内部@1=外部", true, "", false, 100);

		map.AddTBInt("Idx", 0, "序号", true, false);

		this.set_enMap(map);
		return this.get_enMap();
	}

		///#endregion
}
