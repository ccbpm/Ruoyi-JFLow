package bp.App.Demo;

import bp.sys.BuessUnitBase;
/// <summary>
/// 业务单元基类
/// 1. 重写该类为业务单元子类.
/// 2. 每个业务单元子类可以在流程事件节点时间设置.
/// 3. 被继承的子类的必须在BP.*.DLL 里面,才能确保设置时候被映射到.
/// 4. 子类在DoIt方法中根据WorkID 的书写业务逻辑.
/// </summary>
public class BuessUnit_LocalWebApi_GenerToken extends BuessUnitBase {
    @Override
    public String getTitle()
    {
        return "(loalWebApi数据源)获取Token";
    }

    /**
     执行的方法,获取token.
     */
    @Override
    public String DoIt()
    {
        return bp.web.WebUser.getToken();
    }
}
