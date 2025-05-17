package bp.da;

import java.util.Hashtable;

public class SQLManager {

/// <summary>
/// 执行标记的入口.
/// 个性化的需求，需要在这里定义一个空间，做一个开始判断，执行个数据。
/// </summary>
/// <param name="mark"></param>
/// <param name="ht"></param>
/// <returns></returns>
/// <exception cref="Exception"></exception>
public static String GenerSQLByMark(String mark, Hashtable ht) throws Exception {
//C# TO JAVA CONVERTER TASK: There is no preprocessor in Java:
    ///#region 处理参数.
    int idx = mark.indexOf("@");
    if (idx != -1)
    {
        String paras = mark.substring(idx);
        paras = paras.replace("@key", "@Key");

        mark = mark.substring(0, idx);
        AtPara ap = new AtPara(paras);

        for (String key : ap.getHisHT().keySet())
        {
            if (ht.containsKey(key) == true)
            {
                continue;
            }
            ht.put(key, ap.getHisHT().get(key));
        }
    }
    ///#endregion 处理参数.

    ///#region 根据标记命名空间，执行相关的方法.
    if (mark.startsWith("Port_") == true)
    {
        return SQLPort.Gener_Port(mark, ht);
    }

    if (mark.startsWith("Flow_") == true)
    {
        return SQLFlow.Gener_Flow(mark, ht);
    }

    if (mark.startsWith("Frm_") == true)
    {
        return SQLFrm.Gener_Frm(mark, ht);
    }

    if (mark.startsWith("DBSrc_") == true)
    {
        return SQLDBSrc.Gener_DBSrc(mark, ht);
    }

    //应用系统标记：期望而开用户把SQL标记都写如这个方法里面单独保管，不要提交.
    if (mark.startsWith("App_") == true)
    {
        return bp.demo.SQLApp.Gener_App(mark, ht);
    }

    //为demo 单独做一个命名空间.
    if (mark.startsWith("DemoStudent_") == true)
    {
        return bp.demo.SQLDemo.DemoSQL(mark, ht); //获得demo的数据.
    }

//C# TO JAVA CONVERTER TASK: There is no preprocessor in Java:
    ///#endregion 根据标记命名空间，执行相关的方法.

    throw new RuntimeException("没有判断的标记:" + mark);
}

}
