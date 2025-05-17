package bp.App.Demo;

import bp.sys.BuessUnitBase;
import bp.sys.ResultObj;
import net.sf.json.JSONObject;

/// <summary>
/// 业务单元基类
/// 1. 重写该类为业务单元子类.
/// 2. 每个业务单元子类可以在流程事件节点时间设置.
/// 3. 被继承的子类的必须在BP.*.DLL 里面,才能确保设置时候被映射到.
/// 4. 子类在DoIt方法中根据WorkID 的书写业务逻辑.
/// </summary>
public class BuessUnit_fsjma_ResultObj extends BuessUnitBase {
    @Override
    public String getTitle()
    {
        return "ResultObj: 方策转换函数";
    }
    /**
     执行的方法,获取token.
     */
    @Override
    public String DoIt()
    {
        //把返回来的对象转化为json. this.resultData = 是您返回的数据.
        JSONObject jsonFS = JSONObject.fromObject(this.resultData);

        //定义要返回的对象,并按照当前对象的标准组织数据.
        ResultObj myObj = new ResultObj();
        if (jsonFS.getInt("code") != 0)
        {
            myObj.code = 500; // 200=执行成功, 404=配置错误，500=执行错误.
        }
        else
        {
            myObj.code = 200;
        }
        myObj.message = jsonFS.getString("message");
        myObj.data = jsonFS.getString("data").toString();

        //设置要返回的数据
        this.resultObj = myObj;

        // 可以通过,this.
        return myObj.message;
    }
}