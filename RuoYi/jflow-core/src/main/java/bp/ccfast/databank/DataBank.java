package bp.ccfast.databank;
import bp.en.EntityNoName;
import bp.en.Map;
import bp.en.UAC;
import bp.web.WebUser;
public class DataBank extends EntityNoName
{/**
 权限控制

 */
@Override
public UAC getHisUAC()
{
    UAC uac = new UAC();
    if (WebUser.getIsAdmin())
    {
        uac.IsUpdate = false;
        return uac;
    }
    return super.getHisUAC();
}
    /**
     独立方法

     */
    public DataBank()
    {
    }
    public DataBank(String no) throws Exception {
        this.setNo(no);
        this.Retrieve();
    }
    /**
     重写基类方法

     */
    @Override
    public Map getEnMap()
    {
        if (this.get_enMap() != null)
        {
            return this.get_enMap();
        }

        Map map = new Map("Sys_DataBank", "数据银行");

        map.AddTBStringPK(DataBankAttr.No, null, "编号", true, true, 0, 50, 10);
        map.AddTBString(DataBankAttr.Name, null, "方法名", true, false, 0, 300, 10, true);
        map.AddTBStringDoc(DataBankAttr.Docs, null, "内容", true, false, true);
        map.AddTBString("MD5", null, "加密值", true, false, 0, 300, 10, true);

        this.set_enMap(map);
        return this.get_enMap();
    }
    ///#region 执行方法.
    @Override
    protected boolean beforeInsert() throws Exception {
        return super.beforeInsert();
    }

}
