package bp.App.Demo;

import bp.da.DBAccess;
import bp.da.DataType;
import bp.sys.BuessUnitBase;
import net.sf.json.JSONObject;

/**
 业务单元基类
 1. 重写该类为业务单元子类.
 2. 每个业务单元子类可以在流程事件节点时间设置.
 3. 被继承的子类的必须在BP.*.DLL 里面,才能确保设置时候被映射到.
 4. 子类在DoIt方法中根据WorkID 的书写业务逻辑.
 */

public class BuessUnit_fsjma_GenerToken extends BuessUnitBase {
    @Override
    public String getTitle()
    {
        return "GenerToken:获取[方策]数据源的Token";
    }

    /**
     执行的方法,获取token.
     */
    @Override
    public String DoIt() throws Exception {
        String passWord = DBAccess.RunSQLReturnStringIsNull("SELECT Pass FROM Port_Emp WHERE No='" + bp.web.WebUser.getNo() + "'", null);
        if (passWord == null)
        {
            throw new RuntimeException("err@用户不存在或者没有密码.");
        }

        String url = "http://comms.fsjma.cn:8089/CommonAppServer/Account/Login?commstest=XCDemo";
        String userNo = "fccgy"; // WebUser.No;
        String userName = "方策-陈刚毅"; // WebUser.Name;
        String userType = "comms";
        passWord = "chan9808";

        String pwd = userNo + DBAccess.GenerMD5(userNo.toLowerCase(), true) + DBAccess.GenerMD5(passWord, true);
        JSONObject json = new JSONObject();
        json.put("AppName", "Web");
        json.put("UniqueID", DBAccess.GenerGUID());

        json.put("UserType", userType);
        json.put("UserID", userNo);
        json.put("Pwd", pwd);

        String bodyData = json.toString();
        String header = "{md5:'" + DBAccess.GenerMD5(bodyData) + "'}";

        //通过下面的方法获取token.
        String data = bp.tools.PubGlo.HttpPostConnect(url, bodyData, "POST", true, header);
        if (DataType.IsNullOrEmpty(data) == true)
        {
            throw new RuntimeException("err@获取token错误,data为空 url:" + url + ", bodyData=" + bodyData + "  header=" + header);
        }

        JSONObject jsonFS = JSONObject.fromObject(data);
        if (jsonFS != null)
        {
            if (jsonFS.getInt("code") != 0)
            {
                throw new RuntimeException("err@密码或者用户名错误，数据:" + data);
            }
            JSONObject jsonData = JSONObject.fromObject(jsonFS.getString("data"));
            return jsonData.getString("accessToken");
        }
        throw new RuntimeException("err@获取token错误:" + data + " url:" + url + ", bodyData=" + bodyData + "  header=" + header);
    }

}
