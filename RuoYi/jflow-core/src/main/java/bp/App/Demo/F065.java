package bp.App.Demo;

import bp.da.DBAccess;
import bp.sys.MapData;
import bp.wf.FlowEventBase;
import bp.wf.GenerWorkFlow;
/// <summary>
/// 报销流程001
/// 此类库必须放入到 BP.*.dll 才能被解析发射出来。
/// </summary>
public class F065 extends FlowEventBase
{
    public F065() throws Exception {
        super();
    }
    /// <summary>
    /// 重写流程标记
    /// </summary>
    @Override
    public String getFlowMark()
    {
        return "";
    } //这个位置可以写多个流程编号 045，046，如果需要修改状态的都可以在这个位置增加流程编号

    /**
     * 重写发送前事件
     * @return
     */
    @Override
    public String SendWhen() {
        try {
            if (1 == 3)
                return "err@不符合流程发起条件，阻止流程发送。";
            if (1 == 1)
                return "后端外挂 /App/Demo/F065 SendWhen 已经执行成功,节点ID:" + this.getHisNode().getNodeID() + ",WorkID:" + this.getWorkID();

        }catch (Exception e) {
            e.printStackTrace();
        }
        return super.SendWhen();
    }
    /// <summary>
    /// 发送成功事件，发送成功时，把流程的待办写入其他系统里.
    /// </summary>
    /// <returns>返回执行结果，如果返回null就不提示。</returns>
    @Override
    public String SendSuccess() throws Exception {
        if (1 == 1)
            return "后端外挂:/App/Demo/F065 SendSuccess 已经执行成功,节点ID:" + this.getHisNode().getNodeID() + ",WorkID:" + this.getWorkID();
        return super.SendSuccess();
    }
    /**
     * 删除后
     *
     * @return
     */
    @Override
    public String ReturnBefore() {
        try {
            return "后端外挂:/App/Demo/F065 ReturnBefore 已经执行成功,节点ID:" + this.getHisNode().getNodeID() + ",WorkID:" + this.getWorkID();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return super.ReturnBefore();
    }

    /**
     * 结束后
     *
     * @return
     */
    @Override
    public String FlowOverAfter() throws Exception {

        return "后端外挂:/App/Demo/F065 FlowOverAfter 已经执行成功,节点ID:" + this.getHisNode().getNodeID() + ",WorkID:" + this.getWorkID();
    }
    @Override
    public String AfterFlowDel() {
        try {
            return "后端外挂:/App/Demo/F065 AfterFlowDel 已经执行成功,节点ID:" + this.getHisNode().getNodeID() + ",WorkID:" + this.getWorkID();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return super.AfterFlowDel();
    }
    @Override
    public String  BeforeFlowDel() {
        try{
            return "后端外挂:/App/Demo/F065 BeforeFlowDel 已经执行成功,节点ID:" + this.getHisNode().getNodeID() + ",WorkID:" + this.getWorkID();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return super.BeforeFlowDel();
    }

    @Override
    public String  UndoneBefore() {
        try {
            return "后端外挂:/App/Demo/F065 UndoneBefore 已经执行成功,节点ID:" + this.getHisNode().getNodeID() + ",WorkID:" + this.getWorkID();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return super.UndoneBefore();
    }
    @Override
    public String  SaveAfter() {
        try {
            return "后端外挂:/App/Demo/F065 SaveAfter 已经执行成功,节点ID:" + this.getHisNode().getNodeID() + ",WorkID:" + this.getWorkID();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return super.SaveAfter();
    }

    @Override
    public String  FlowRollBackBefore() {
        try {
            return "后端外挂:/App/Demo/F065 FlowRollBackBefore 已经执行成功,节点ID:" + this.getHisNode().getNodeID() + ",WorkID:" + this.getWorkID();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return super.FlowRollBackBefore();
    }

    // 重写事件，完成业务逻辑.
}
