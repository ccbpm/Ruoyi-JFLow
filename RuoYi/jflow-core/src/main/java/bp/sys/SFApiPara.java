package bp.sys;

import bp.en.EntityMyPK;
import bp.en.Map;
import bp.en.UAC;

public class SFApiPara extends EntityMyPK {
    ///#region 参数.
    public final String getAttrKey()
    {
        return this.GetValStrByKey("AttrKey");
    }
    public final String getAttrName()
    {
        return this.GetValStrByKey("AttrName");
    }
    /**
     常量表达式
     */
    public final String getExpDoc()
    {
        return this.GetValStrByKey("ExpDoc");
    }
    /**
     模式：@0=常量@1=业务单元
     */
    public final int getApiParaModel()
    {
        return this.GetValIntByKey("ApiParaModel");
    }
    /**
     存储位置: @0=存储到Cookies(比如:token)@1=(不存储)即时计算
     */
    public final int getApiParaStore()
    {
        return this.GetValIntByKey("ApiParaStore");
    }

    public final int getDataType()
    {
        return this.GetValIntByKey("DataType");
    }
    ///#endregion 参数.

    ///#region 构造方法
    @Override
    public UAC getHisUAC()
    {
        UAC uac = new UAC();
        uac.Readonly();
        return uac;
    }
    /**
     用户自定义表
     */
    public SFApiPara()
    {
    }
    public SFApiPara(String no) throws Exception {
        this.setMyPK(no);
        this.Retrieve();
    }
    /**
     EnMap
     */
    @Override
    public Map getEnMap() {
        if (this.get_enMap() != null) {
            return this.get_enMap();
        }
        Map map = new Map("Sys_SFApiPara", "WebApi参数");

// MyPK= RefPK+"_"+BH;
        map.AddMyPK();
        map.AddTBString("DBSrcNo", null, "数据源", false, false, 1, 200, 20);
        map.AddTBString("AttrKey", null, "列英文名", true, true, 1, 200, 100);
        map.AddTBString("AttrName", null, "列中文名", true, false, 0, 200, 100);
        map.AddDDLSysEnum("ApiParaModel", 0, "模式", true, true, "ApiParaModel", "@0=常量@1=业务单元");
        map.AddDDLSysEnum("ApiParaStore", 0, "存储位置", true, true, "ApiParaStore", "@0=存储到Cookies(比如:token)@1=(不存储)即时计算");
        map.AddDDLSysEnum("DataType", 1, "数据类型", true, true, "ParaDataType", "@1=String@2=Int@3=Float");
        map.AddTBString("ExpDoc", null, "常量表达式", true, false, 0, 200, 100, true, "-对常量类型的参数有效");

        this.set_enMap(map);
        return this.get_enMap();
    }
}
