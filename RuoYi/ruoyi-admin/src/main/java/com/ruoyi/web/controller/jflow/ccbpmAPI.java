package com.ruoyi.web.controller.jflow;

import bp.tools.HttpClientUtil;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// 此文件用于放到自己的开发后台代码里， 可以直接调用这些静态的方法，ccbpm帮您封装好了.
public class ccbpmAPI {
    //后台地址
    static String host="http://localhost:8080/WF/API";
    static String webPath="";
    //秘钥：用于双方系统登录的通讯, 只有在用户登录的时候用到.
    static String privateKey= "DiGuaDiGua,IamCCBPM";
    //流程id
    static String flowNo ="001";
    //管理员id
    static String userNo="admin";
    //token
    static String token="";

    //发起的流程工作id
    static long workID=0l;
    public static void main(String[] args) throws Exception {
        /**
         *  按步骤 逐步去掉注释进行测试
         */

        //1.模拟登录,获取token,下边的步骤，不需要注释此方法，token不会变
        //其它测试用户: zhanghaicheng,流程的接收人变更后，下一步发送时需要先登录 Port_LoginReToken(loginUser)
         String loginUser=userNo;
         port_LoginReToken(loginUser);

        //2.创建流程并保存到草稿,获取到workid后，下边的步骤 需要注释掉此方法，才能执行一个流程
        createWorkID();

        //填入控制台获取的workID，进行下一步操作
        workID=0;

         //3.继续发给下一个节点 ,测试时重复执行此方法，直到流程结束
        String  toEmps="zhanghaicheng";
//        to_sendNodeWork(toEmps);

        //4退回流程
        //当前节点
        String nowId="102";
        //指定退回的节点
        String returnToNodeID="101";
//        startToReturn(nowId,returnToNodeID);

        //0获取歌各种列表数据
        queryList();
    }
    //获取各种列表数据
    public static void queryList( ){
        //1.获取可以发起的流程
        String startDb = dB_Start(token,null);

        //获取待办列表
        Integer nodeID=0;//要取得指定节点的待办就传入,默认为0
        String todoDb = dB_Todolist(token,flowNo, String.valueOf(nodeID),null);

        //获取在途列表
        String todoRuning = dB_Runing(token,null);

        //获取创建的工作信息
        String flow_WorkInfo = flow_WorkInfo(token, String.valueOf(workID));



    }
    //创建一个流程工作，并保存到草稿箱
    public static void createWorkID(){
        try {

            //创建流程，获取工作id(可以从 1中获取flowNo,这里测试默认001)
            workID=node_CreateBlankWorkID(token, flowNo);

            //执行设置流程标题
            String title ="关于深化中国中亚合作 八点建议指明方向的申请";
            String msg = flow_SetTitle(token, workID, title, flowNo);


            //执行保存表单参数.
            String keyVals="RiQiCong=2024-03-01@Dao=2024-03-04@TianShu=4@QingJiaYuanYin=有事情需要处理@QJLX=1";
            String dtlJSON="";
            String dsAths="";
            String msgPra =  Node_SaveWork( token,workID, keyVals,"","");

            //执行保存草稿.
            String msgDra = node_SetDraft(token,flowNo,workID);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
    }
    public static void to_sendNodeWork(String toEmp){
        //6.发送流程到下一步
        String toNodeID ="0";//0时系统自动计算要发送到的节点ID
        String toEmps =toEmp ;//"zhanghaicheng" 可不传，不传时流程要设定好接收人，系统可自动寻找对应的接收人
        String msgSend =  node_SendWork(token, workID,flowNo, toNodeID, toEmps);

    }
    //流程开始，中间驳回
    public static void startToReturn( String nowId,String returnToNodeID){
        //7.流程退回操作.
       String msgReturn =  node_ReturnWork(token, workID, nowId,returnToNodeID, "", "不同意", false);
    }
  //其它方法
   public static void other(){
       String flowNo ="001";
       try {

           //执行发送.到节点103
           // String ToNodeID3 ="103";//0时系统自动计算要发送到的节点ID
           //String ToEmps3 ="zhoupeng";//可不传，不传时流程要设定好接收人，系统可自动寻找对应的接收人
           //String msgSend3 =  Node_SendWork(Token, String.valueOf(WorkID),flowNo, ToNodeID3, ToEmps3);
           //执行退回到 102
           Integer FK_Node=103;
           Integer ReturnToNodeID =102;////为空时系统自动计算上一个节点
           String ReturnToEmp="zhanghaicheng";//为空时系统自动计算上一个节点的处理人
           String Msg="请补充说明情况";
           boolean IsBackToThisNode =false;//是否按原路返回

           //执行流程结束.
           String WorkIDs="142,153";
           String msgOver = flow_DoFlowOver(token, WorkIDs);
           /*测试流程End*/

           /*组织结构维护Start*/
           //人员信息保存
           String kvs="@Tel=18660153303@Email=123@qq.com";
           String userNo="cheche";
           String userName="管理员";
           String orgNo="";//非集团模式传空
           String deptNo="1071";
           String stats="08,09";
           Port_Emp_Save(token, orgNo, userNo, userName, deptNo, kvs, stats);

           //人员删除
           Port_Emp_Delete(token, orgNo, userNo);

           //集团模式下同步组织以及管理员信息
           String orgNo2="7201";
           String name="北方分公司";
           String adminer="zhanghaicheng";
           String adminerName="张海成";
           String keyVals="@Leaer=zhangsan@Tel=12233333@Idx=1";
           Port_Org_Save(token, orgNo2,name, adminer, adminerName,  keyVals);

           //保存部门,如果有此数据则修改,无此数据则增加
           String no="10051";
           String nameD="开发部";
           String parentNo="1005";
           String keyValsD="@Leaer=zhangsan@Idx=1";
           Port_Dept_Save(token,orgNo, no, nameD,  parentNo, keyValsD);
           //删除部门
           Port_Dept_Delete(token, no);
           //保存岗位, 如果有此数据则修改，无此数据则增加
           String noSta="99";
           String nameSta="客服";
           String keyValsSta="@FK_StationType=3";
           Port_Station_Save(token,orgNo, noSta, nameSta, keyValsSta);
           //删除岗位
           Port_Station_Delete(token,noSta);
           /*组织结构维护End*/
	        /*Service service = new Service();
	        Call call = (Call) service.createCall();
	        call.setTargetEndpointAddress(webPath);
	        call.setOperationName(new QName("http://WebServiceImp","SendWork"));// WSDL里面描述的接口名称
	        call.addParameter("flowNo",
	                org.apache.axis.encoding.XMLType.XSD_DATE,
	                javax.xml.rpc.ParameterMode.IN);// 接口的参数
	        call.addParameter("workid",
	                org.apache.axis.encoding.XMLType.XSD_DATE,
	                javax.xml.rpc.ParameterMode.IN);// 接口的参数
	        call.addParameter("ht",
	                org.apache.axis.encoding.XMLType.XSD_DATE,
	                javax.xml.rpc.ParameterMode.IN);// 接口的参数
	        call.addParameter("toNodeID",
	                org.apache.axis.encoding.XMLType.XSD_DATE,
	                javax.xml.rpc.ParameterMode.IN);// 接口的参数
	        call.addParameter("toEmps",
	                org.apache.axis.encoding.XMLType.XSD_DATE,
	                javax.xml.rpc.ParameterMode.IN);// 接口的参数
	        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
	        String temp = "admin";
	        Hashtable ht = new Hashtable();
	        ht.put("ZiDuan1", "111");
	        ht.put("ZiDuan2", "222");
	        String result = (String) call.invoke(new Object[] { "226",(long)798,ht,22602,"zhangyifan" });
	        // 给方法传递参数，并且调用方法
	        System.out.println("result is " + result);  */
       } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.toString());
       }
   }
    public static String port_LoginReToken(String loginUser)
    {
        JSONObject postData = port_Login( loginUser);
        System.out.println("执行登录");
        System.out.println(postData);
        if (postData.toString().startsWith("err@"))
            System.out.println("登录失败");
        token =JSONObject.fromObject(postData.getString("data")).get("Token").toString(); //通过token执行相关的操作.
        System.out.println("token:"+token);
        return token;
    }


    public static JSONObject port_Login(String UserNo)
    {
        Map<String, String> param = new HashMap<String, String>();
        param.put("privateKey",privateKey);
        param.put("userNo",UserNo);
        webPath=host+"/Port_Login";
        String postData = HttpClientUtil.doPost(webPath,param,null,null);
        JSONObject j = JSONObject.fromObject(postData);

        //获取返回的数据
		/*String Name = j.get("Name").toString();
		String No = j.get("No").toString();
		String FK_Dept = j.get("FK_Dept").toString();
		String FK_DeptName = j.get("FK_DeptName").toString();
		String token = j.get("Token").toString(); //通过token执行相关的操作.
		System.out.println("***"+j.toString()+"***");*/
        return j;
    }

    public static  long node_CreateBlankWorkID(String token, String FlowNo)
    {
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("flowNo",FlowNo);
        webPath=host+"/Node_CreateBlankWorkID";
        String postData = HttpClientUtil.doPost(webPath,param,null,null);
        System.out.println("创建根据流程编号");
        System.out.println(postData);
        JSONObject j = JSONObject.fromObject(postData);
        long workID=Long.valueOf((String) j.get("data"));
        System.out.println("新建流程ID");
        System.out.println(workID);
        return workID;
    }
    public static String flow_SetTitle(String token, long workID, String title, String flowNo){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("workID",String.valueOf(workID));
        param.put("title",title);
        param.put("flowNo",flowNo);
        webPath=host+"/Flow_SetTitle";
        String msg = HttpClientUtil.doPost(webPath,param,null,null);
        System.out.println("流程标题修改:"+title);
        System.out.println(msg);
        return msg;
    }

    public static String node_SetDraft(String token,String flowNo,long workID){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("workID",String.valueOf(workID));
        param.put("flowNo",flowNo);
        webPath=host+"/Node_SetDraft";
        String msg = HttpClientUtil.doPost(webPath,param,null,null);
        System.out.println("流程修改为草稿");
        System.out.println(msg);
        return msg;
    }

    public static String Node_SaveWork(String token,long workid,String keyVals,String dtlJSON,String dsAths){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("workID",String.valueOf(workid));
        param.put("keyVals",keyVals);
        param.put("dtlJSON",dtlJSON);
        param.put("dsAths",dsAths);
        webPath=host+"/Node_SaveWork";
        String msg = HttpClientUtil.doPost(webPath,param,null,null);
        System.out.println("流程保存表单参数");
        System.out.println(msg);
        return msg;
    }

    public static  String node_SendWork(String token,long workID,String flowNo,String toNodeID,String toEmps)
    {
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("workID",String.valueOf(workID));
        param.put("FK_Flow",flowNo);
        param.put("toNodeIDStr",toNodeID);
        param.put("toEmps",toEmps);
        param.put("ht","");
        param.put("dtls","");
        param.put("paras","");
        param.put("checkNote","同意");
        webPath=host+"/Node_SendWork";
        String msg = HttpClientUtil.doPost(webPath,param,null,null);
        System.out.println("发送流程至："+toEmps);
        System.out.println(msg);
        return msg;
    }

    public static String node_ReturnWork(String token,long workID, String nodeID,String returnToNodeID,String returnToEmp,String msg,boolean isBackToThisNode){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("workID",String.valueOf(workID));
        param.put("nodeIDStr",nodeID);
        param.put("returnToNodeIDStr", returnToNodeID);
        param.put("returnToEmp",returnToEmp);
        param.put("msg",msg);
        param.put("isBackToThisNode", String.valueOf(isBackToThisNode));
        webPath=host+"/Node_ReturnWork";
        String msg1 = HttpClientUtil.doPost(webPath,param,"Token",token);
        System.out.println("流程退回=>当前节点："+nodeID);
        System.out.println(msg1);
        return msg1;
    }
    public static String flow_DoFlowOver(String token,String workIDs){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("workIDs",workIDs);
        webPath=host+"/Flow_DoFlowOver";
        String msg = HttpClientUtil.doPost(webPath,param,null,null);
        return msg;
    }
    public static String dB_Start(String token,String domain){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("domain",domain);
        webPath=host+"/DB_Start";
        String dtStrat = HttpClientUtil.doPost(webPath,param,null,null);
        System.out.println("发起列表");
        System.out.println(dtStrat);
        return dtStrat;

    }
    public static String dB_Todolist(String token,String flowNo,String nodeID,String Domain){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("flowNo",flowNo);
        param.put("nodeID",nodeID);
        param.put("Domain",Domain);
        webPath=host+"/DB_Todolist";
        String dtTodo = HttpClientUtil.doPost(webPath,param,null,null);
        System.out.println("待办列表");
        System.out.println(dtTodo);
        return dtTodo;
    }
    public static String dB_Runing(String token,String Domain){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("domain",Domain);
        webPath=host+"/DB_Runing";
        String dtRun = HttpClientUtil.doPost(webPath,param,null,null);
        System.out.println("在途列表");
        System.out.println(dtRun);
        return dtRun;
    }
    public static String flow_WorkInfo(String token,String workID){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("workID",workID);
        webPath=host+"/Flow_WorkInfo";
        String dtRun = HttpClientUtil.doPost(webPath,param,null,null);
        System.out.println("获得已创建的工作信息:"+workID);
        System.out.println(dtRun);
        return dtRun;
    }
    public static String Port_Emp_Save(String token, String orgNo, String userNo, String userName, String deptNo, String kvs, String stats){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("orgNo",orgNo);
        param.put("userNo",userNo);
        param.put("userName",userName);
        param.put("deptNo",deptNo);
        param.put("kvs",kvs);
        param.put("stats",stats);
        webPath=host+"/Port_Emp_Save";
        String dtSave = HttpClientUtil.doPost(webPath,param,null,null);
        return dtSave;
    }
    public static String Port_Emp_Delete(String token,String orgNo, String userNo){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("orgNo",orgNo);
        param.put("userNo",userNo);
        webPath=host+"/Port_Emp_Delete";
        String dtDel = HttpClientUtil.doPost(webPath,param,null,null);
        return dtDel;
    }
    public static String Port_Org_Save(String token,String orgNo,  String name, String adminer, String adminerName, String keyVals){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("orgNo",orgNo);
        param.put("name",name);
        param.put("adminer",adminer);
        param.put("adminerName",adminerName);
        param.put("keyVals",keyVals);
        webPath=host+"/Port_Org_Save";
        String dtSave = HttpClientUtil.doPost(webPath,param,null,null);
        return dtSave;
    }
    public static String Port_Dept_Save(String token,String orgNo, String no, String name, String parentNo, String keyVals){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("orgNo",orgNo);
        param.put("no",no);
        param.put("name",name);
        param.put("parentNo",parentNo);
        param.put("keyVals",keyVals);
        webPath=host+"/Port_Dept_Save";
        String dtSave = HttpClientUtil.doPost(webPath,param,null,null);
        return dtSave;
    }
    public static String Port_Dept_Delete(String token,String no){
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("no",no);
        webPath=host+"/Port_Dept_Delete";
        String dtDel = HttpClientUtil.doPost(webPath,param,null,null);
        return dtDel;
    }
    public static String Port_Station_Save(String token,String orgNo, String no, String name, String keyVals) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("orgNo",orgNo);
        param.put("no",no);
        param.put("name",name);
        param.put("keyVals",keyVals);
        webPath=host+"/Port_Station_Save";
        String dtSave = HttpClientUtil.doPost(webPath,param,null,null);
        return dtSave;
    }

    public static String Port_Station_Delete(String token,String no) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("token",token);
        param.put("no",no);
        webPath=host+"/Port_Station_Delete";
        String dtDel = HttpClientUtil.doPost(webPath,param,null,null);
        return dtDel;
    }
}