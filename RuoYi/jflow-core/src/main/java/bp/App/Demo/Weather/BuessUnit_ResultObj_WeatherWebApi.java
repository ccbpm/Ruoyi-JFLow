package bp.App.Demo.Weather;

import bp.sys.BuessUnitBase;
import bp.sys.ResultObj;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/// <summary>
/// WebApi数据标准转换API接口
/// </summary>
public class BuessUnit_ResultObj_WeatherWebApi extends BuessUnitBase {
    @Override
    public String getTitle()
    {
        return "ResultObj:天气预报数据结果转换.";
    }
    /**
     执行的方法,获取token.
     */
    @Override
    public String DoIt()
    {
        //获得原始数据.
        JSONObject json = JSONObject.fromObject(this.resultData);

        //创建数据对象,并给数据对象赋值，如果用户的标准不与cc的标准相同，就按照cc的格式转换.
        ResultObj obj = new ResultObj();
        obj.code = json.getInt("code"); // 200=执行成功, 404=配置错误，500=执行错误.
        obj.message = json.getString("fxLink");

        obj.data = JSONArray.fromObject(json.getString("daily")).toString();
        //把数据对象
        this.resultObj = obj;
        return obj.message;
    }
}
