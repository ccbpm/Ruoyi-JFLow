package bp.App.Demo;

import bp.sys.GEDtl;
import bp.wf.SendReturnObjs;

import java.util.Hashtable;

public class DevDemo {

    /// <summary>
    /// 流程开发demo.
    /// 1. 演示了对流程的基础操作:发起，退回、移交、催办、转发、撤销等操作.
    /// 2. 发起流程的时候，存储主表数据、从表数据数据、附件数据。
    /// </summary>
    public void FlowDemo_1() throws Exception {
        /*
         * 说明：
         * 1. 首先在流程设计器里定义一个流程模板, 模版编号是一个String类型的3位数编号,在流程属性里可以看到. 比如:001
         * 2. 其次记录下来节点ID, 它是一个int类型的，在节点属性里可以看到比如: 101,102.
         */
        //#region 1.开始节点发起.
        String flowNo = "001";
        //登陆:获得用户token.
        bp.wf.Dev2Interface.Port_Login("liping");

        //生成一个流程实例：WorkID，它是一个长整型的数据。 如果liping不能执行发起此流程系统就抛出错误。018
        long workID = bp.wf.Dev2Interface.Node_CreateBlankWork(flowNo);

        //如果需要用到了草稿功能：就执行设置草稿。
        bp.wf.Dev2Interface.Node_SetDraft(workID);

        //如果需要向流程引擎写入参数,就执行写入参数,比如：按照参数控制方向条件、或者接受人。
        String paras = "@JinE=1000@JSR=zhangsna";
        bp.wf.Dev2Interface.Flow_SaveParas(workID, paras);

        //**提交表单时同时做以下两步
        //如果当前流程使用的是节点表单,需要向开始节点表单写入数据.
        Hashtable myht = new Hashtable();
        myht.put("Tel", "18660153393");
        myht.put("Addr", "山东济南");
        myht.put("Age", 18);
        myht.put("JE", 111.111);
        bp.wf.Dev2Interface.Node_SaveWork(workID, myht); //保存主表数据.

        //写入从表数据.
        String frmDtlID = "ND101Dtl1";
        GEDtl dtl = new GEDtl(frmDtlID); //创建实体.
        for (int i = 0; i < 10; i++)
        {
            Hashtable htDtl = new Hashtable();
            htDtl.put("CPMC", "产品名称");
            htDtl.put("ShuLiang", 1000);
            htDtl.put("DanJia", 225.25);
            bp.wf.Dev2Interface.CCForm_AddDtl("ND101Dtl1", workID, htDtl); //增加从表数据.
        }

        //写入附件数据.
        for (int i = 0; i < 1; i++)
        {
            String filePath = "c:\\xxx.txt";
            String fileName = "我的文档";
            bp.wf.Dev2Interface.CCForm_AddAth(101, flowNo, workID, "Ath1", "ND101", filePath, fileName); //增加附件数据.
        }

        //执行发送1: 系统就按照设计器定义的接受人规则与方向流转,返回执行结果,提示给操作员.
        SendReturnObjs objs = bp.wf.Dev2Interface.Node_SendWork(flowNo, workID, null, null);
        //审核信息.
        String info = objs.ToMsgOfText();
        //**提交表单时同时做以上两步
        //如需需要撤销.
        String msgUnSend = bp.wf.Dev2Interface.Flow_DoUnSend(flowNo, workID);

        //执行抄送，需要的情景下执行完发送执行 ,抄送标题可以为空，空时默认流程标题
        String msgCCSend = bp.wf.Dev2Interface.Node_CC_WriteTo_CClist(102, workID, "zhanghaicheng", "张海成", "关于XXX的请示", "请悉知");

        //执行发送2: 发送或者跳转到执行的节点，返回执行结果,提示给操作员.
        //msgSend = Dev2InterfaceRestfulAPI.Node_SendWork(token, workID, "603", "lisi,wangwu");

        //如果需要催办.
        String msgPress = bp.wf.Dev2Interface.Flow_DoPress(workID, "请于7日前办理");

        //如果需要设置标题.
        bp.wf.Dev2Interface.Flow_SetFlowTitle(flowNo, workID, "关于2024年3月报销流程通知");
        //#endregion 2.开始节点发起.

        //#region 2. 第2个节点执行.
        //切换账号.
        bp.wf.Dev2Interface.Port_Login("yuwen");

        Hashtable ht = new Hashtable();
        ht.put("Tel", "18660153393");
        ht.put("Addr", "济南高新区碧桂园凤凰中心.");
        ht.put("Age", "25");
        ht.put("JinE", "12000");

        bp.wf.Dev2Interface.Node_SaveWork(workID, ht);

        //可以执行继续发送,执行发送.
        objs = bp.wf.Dev2Interface.Node_SendWork(flowNo, workID, null, null);

        bp.wf.Dev2Interface.Node_SaveWork(workID, ht);

        //再次发送
        objs = bp.wf.Dev2Interface.Node_SendWork(flowNo, workID, null, null);
        //#endregion 2. 第2个节点执行.

        //#region 3. 第3个节点执行.
                //切换账号.
                        bp.wf.Dev2Interface.Port_Login("fuhui");

        //如果需要退回：执行退回, 返回执行结果.，第三个参数节点ID可以为空，为空时自动计算可以退回的节点ID执行退回操作
        String msgReturn = bp.wf.Dev2Interface.Node_ReturnWork(workID, 202, "", "工作有误需要退回", true);
        //#endregion 3. 第3个节点执行.

        //如果需要移交.
        String msgShift = bp.wf.Dev2Interface.Node_Shift(workID, "zhangyifan", "需要您处理一下");

        //如果需要增加处理人.
        bp.wf.Dev2Interface.Node_AddTodolist(workID, "zhanghaicheng");

        //如果需要结束流程.101,102,103,stopFlowType:结束类型,写入到WF_GenerWorkFlow,AtPara字段 1=正常结束,0=非正常结束
        String msgFlowOver = bp.wf.Dev2Interface.Flow_DoFlowOver(workID, "执行流程结束");

        //如果需要删除流程.
        bp.wf.Dev2Interface.Flow_DoDeleteFlowByReal(workID);
    }
}
