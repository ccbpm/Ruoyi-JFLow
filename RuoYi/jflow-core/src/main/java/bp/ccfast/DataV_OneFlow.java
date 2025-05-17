package bp.ccfast;

import bp.da.DBAccess;
import bp.da.DataRow;
import bp.da.DataTable;
import bp.difference.SystemConfig;
import bp.difference.handler.DirectoryPageBase;
import bp.sys.CCBPMRunModel;
import bp.web.WebUser;
import bp.wf.Dev2Interface;

/**
 页面功能实体

 */
public class DataV_OneFlow extends DirectoryPageBase
{
    /**
     页面功能实体

     */
    public DataV_OneFlow()
    {
    }
    ///#region 管理员.数字类
    public final String Admin_WFState()
    {
        DataTable dt = new DataTable();
        dt.Columns.Add("title");
        dt.Columns.Add("exp");
        dt.Columns.Add("fontColor");
        dt.Columns.Add("icon");

        DataRow dr = dt.NewRow();
        dr.setValue("title","待办");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE WFState=2 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","black");
        dr.setValue("icon","icon-clock");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","退回");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE WFState=5 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","black");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","已完成");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE WFState=3 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-check");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","运行中");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE WFState NOT IN (0,1,3) AND  AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-check");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","冻结");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE WFState=7 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","#1890ff");
        dr.setValue("icon","icon-bell");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","移交");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE WFState=6 AND FK_Flow='" + this.getFlowNo()  + "'"));
        dr.setValue("fontColor","black");
        dr.setValue("icon","icon-login");
        dt.Rows.add(dr);
        return bp.tools.Json.ToJson(dt);
    }
    /// <summary>
    /// 完成分析:单个流程的
    /// </summary>
    /// <returns></returns>
    public final String Admin_Complete()
    {
        DataTable dt = new DataTable();
        dt.Columns.Add("title");
        dt.Columns.Add("exp");
        dt.Columns.Add("fontColor");
        dt.Columns.Add("icon");

        DataRow dr = dt.NewRow();
        dr.setValue("title","累计已完成");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT Count(*) as n FROM WF_GenerWorkFlow WHERE WFState=3 AND FK_Flow='"+this.getFlowNo()+"'"));
        dr.setValue("fontColor","black");
        dr.setValue("icon","icon-check");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","正常结束");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT Count(*) as n FROM WF_GenerWorkFlow WHERE WFState=3 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","非正常结束");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT Count(*) as n FROM WF_GenerWorkFlow WHERE WFState=3 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","按期完成");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT Count(*) as n FROM WF_GenerWorkFlow WHERE WFState=3 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","逾期完成");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT Count(*) as n FROM WF_GenerWorkFlow WHERE WFState=3 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","按期完成率");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT Count(*) as n FROM WF_GenerWorkFlow WHERE WFState=3 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","上月新增");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT Count(*) as n FROM WF_GenerWorkFlow WHERE WFState=3 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","本月新增");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT Count(*) as n FROM WF_GenerWorkFlow WHERE WFState=3 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","昨日新增");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT Count(*) as n FROM WF_GenerWorkFlow WHERE WFState=3 AND FK_Flow='" + this.getFlowNo() + "'"));
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","今日新增");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT Count(*) as n FROM WF_GenerWorkFlow WHERE WFState=3 AND FK_Flow='" + this.getFlowNo()) + "'");
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);
        return bp.tools.Json.ToJson(dt);
    }
    /**
     部门流程发起分布
     @return
     */
    public final String Admin_DeptStartFlows()
    {
        String orgExp = "";
        if (SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single)
        {
            orgExp = " AND OrgNo='" + WebUser.getOrgNo() + "'";
        }
        String sql = "SELECT DeptName, COUNT(*) AS Num  FROM WF_GenerWorkflow WHERE DeptName!='' " + orgExp + " GROUP BY DeptName";
        return DBAccess.RunSQLReturnTableJson(sql);
    }
    public final String Admin_StartFlows()
    {
        String orgExp = "";
        if (SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single)
        {
            orgExp = " AND OrgNo='" + WebUser.getOrgNo() + "'";
        }
        String sql = "SELECT FlowName, COUNT(*) AS Num  FROM WF_GenerWorkflow  WHERE FlowName!='' " + orgExp + "GROUP BY FlowName";
        return DBAccess.RunSQLReturnTableJson(sql);
    }
    ///#endregion 管理员.
    /// <summary>
    /// 单据信息
    /// </summary>
    /// <returns></returns>
    public final String EmpHome_MyBillInfo() throws Exception {
        DataTable dt = new DataTable();
        dt.Columns.Add("title");
        dt.Columns.Add("exp");
        dt.Columns.Add("fontColor");
        dt.Columns.Add("icon");

        DataRow dr = dt.NewRow();
        dr.setValue("title","单据待办");
        dr.setValue("exp",bp.ccbill.Dev2Interface.DB_Todolist());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-clock");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","单据退回");
        dr.setValue("exp",bp.ccbill.Dev2Interface.DB_Todolist());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","单据未完成");
        dr.setValue("exp",bp.ccbill.Dev2Interface.DB_Todolist());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-minus");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","已完成");
        dr.setValue("exp",bp.ccbill.Dev2Interface.DB_Todolist());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-notebook");
        dt.Rows.add(dr);
        return bp.tools.Json.ToJson(dt);
    }
    /**
     待办分布

     @return
     */
    public final String EmpHome_TodolistByNodeName()
    {
        String sql = "SELECT NodeName, Count(*) as Num FROM WF_GenerWorkerList WHERE IsPass=0 AND FK_Emp='@WebUser.No' group by NodeName";
        sql = bp.difference.Glo.DealExp(sql, null);
        return DBAccess.RunSQLReturnTableJson(sql);
    }

    /**
     我的发起数

     @return
     */
    public final String EmpHome_MyStart() throws Exception {
        DataTable dt = new DataTable();
        dt.Columns.Add("title");
        dt.Columns.Add("exp");
        dt.Columns.Add("fontColor");
        dt.Columns.Add("icon");

        DataRow dr = dt.NewRow();
        dr.setValue("title","发起总数");
        dr.setValue("exp",Dev2Interface.getMyStartRuning());
        dr.setValue("fontColor","black");
        dr.setValue("icon","icon-paper-plane");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","已完成");
        dr.setValue("exp",Dev2Interface.getTodolistComplete());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","未完成");
        dr.setValue("exp",Dev2Interface.getTodolistRuning());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","按期完成");
        dr.setValue("exp","0");
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","逾期完成");
        dr.setValue("exp","0");
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","按期完成率");
        dr.setValue("exp","0");
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","上月新增");
        dr.setValue("exp",Dev2Interface.getLastMonthMyStartRuning());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","本月新增");
        dr.setValue("exp",Dev2Interface.getMonthMyStartRuning());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","昨日新增");
        dr.setValue("exp",Dev2Interface.getYesMyStartRuning());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","今日新增");
        dr.setValue("exp",Dev2Interface.getTodayMyStartRuning());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);
        return bp.tools.Json.ToJson(dt);
    }
    /**
     我的待办信息

     @return
     */
    public final String EmpHome_MyTodolist() throws Exception {
        DataTable dt = new DataTable();
        dt.Columns.Add("title");
        dt.Columns.Add("exp");
        dt.Columns.Add("fontColor");
        dt.Columns.Add("icon");

        DataRow dr = dt.NewRow();
        dr.setValue("title","待办");
        dr.setValue("exp",Dev2Interface.getTodolistEmpWorks());
        dr.setValue("fontColor","black");
        dr.setValue("icon","icon-clock");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","退回");
        dr.setValue("exp",Dev2Interface.getTodolistReturnNum());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","挂起");
        dr.setValue("exp",Dev2Interface.getTodolistHungupNum());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","会签");
        dr.setValue("exp",Dev2Interface.getTodolistHuiQian());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);


        dr = dt.NewRow();
        dr.setValue("title","未读");
        dr.setValue("exp",Dev2Interface.getTodolistUnRead());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","移交");
        dr.setValue("exp",Dev2Interface.getShiftlistNum());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","抄送");
        dr.setValue("exp",Dev2Interface.getCCListUnRead());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","昨日新增");
        dr.setValue("exp",Dev2Interface.getYesTodolistWorks());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","今日新增");
        dr.setValue("exp",Dev2Interface.getTodayTodolistWorks());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        return bp.tools.Json.ToJson(dt);
    }
    /**
     已完成

     @return
     */
    public final String EmpHome_MyComplate() throws Exception {
        DataTable dt = new DataTable();
        dt.Columns.Add("title");
        dt.Columns.Add("exp");
        dt.Columns.Add("fontColor");
        dt.Columns.Add("icon");

        DataRow dr = dt.NewRow();
        dr.setValue("title","累计已完成");
        dr.setValue("exp",Dev2Interface.getTodolistComplete());
        dr.setValue("fontColor","black");
        dr.setValue("icon","icon-clock");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","正常结束");
        dr.setValue("exp",Dev2Interface.getComlistComplete());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","非正常结束");
        dr.setValue("exp",Dev2Interface.getOverlistComplete());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","按期完成");
        dr.setValue("exp","0");
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","逾期完成");
        dr.setValue("exp","0");
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","按期完成率");
        dr.setValue("exp","0");
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","昨日新增");
        dr.setValue("exp",Dev2Interface.getYeslistComplete());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","今日新增");
        dr.setValue("exp",Dev2Interface.getTodaylistComplete());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);
        return bp.tools.Json.ToJson(dt);
    }
    public final String EmpHome_MyRuning() throws Exception {
        DataTable dt = new DataTable();
        dt.Columns.Add("title");
        dt.Columns.Add("exp");
        dt.Columns.Add("fontColor");
        dt.Columns.Add("icon");

        DataRow dr = dt.NewRow();
        dr.setValue("title","在途数");
        dr.setValue("exp",Dev2Interface.getTodolistRuning());
        dr.setValue("fontColor","black");
        dr.setValue("icon","icon-hourglass");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","退回中");
        dr.setValue("exp",Dev2Interface.getTodolistReturnNum());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","移交中");
        dr.setValue("exp",Dev2Interface.getShiftlistNum());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","逾期中");
        dr.setValue("exp",Dev2Interface.getTodolistOverWorkNum());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","挂起中");
        dr.setValue("exp",Dev2Interface.getTodolistHungupNum());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        return bp.tools.Json.ToJson(dt);
    }

    /**
     统计

     @return
     */
    public final String EmpHome_TongJi()
    {
        DataTable dt = new DataTable();
        dt.Columns.Add("title");
        dt.Columns.Add("exp");
        dt.Columns.Add("fontColor");
        dt.Columns.Add("icon");

        DataRow dr = dt.NewRow();
        dr.setValue("title","发起数(累计)");
        dr.setValue("exp",DBAccess.RunSQLReturnValInt("SELECT COUNT(*) AS Num FROM WF_GenerWorkFlow WHERE Starter='" + WebUser.getNo() + "' AND WFState >1 "));
        dr.setValue("fontColor","black");
        dr.setValue("icon","icon-clock");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","退回");
        dr.setValue("exp",Dev2Interface.getTodolistReturnNum());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","未完成");
        dr.setValue("exp",Dev2Interface.getTodolistRuning());
        dr.setValue("fontColor","red");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);

        dr = dt.NewRow();
        dr.setValue("title","未读");
        dr.setValue("exp",Dev2Interface.getTodolistUnRead());
        dr.setValue("fontColor","yellow");
        dr.setValue("icon","icon-action-undo");
        dt.Rows.add(dr);
        return bp.tools.Json.ToJson(dt);
    }

    ///#region 我的-流程动态组件.
    /**
     自定义组件-流程动态
     更多打开近期就好.

     @return
     */
    public final String Self_MovementFlow_My()
    {
        String sql = "";
        switch (SystemConfig.getAppCenterDBType())
        {
            case MySQL:
            case DM:
                sql = "SELECT SendDT,Sender,WFState,TodoEmps,FlowName,NodeName,WorkID,Title,StarterName,Starter FROM WF_GenerWorkFlow WHERE WFState >1 AND TodoEmps LIKE '%" + WebUser.getNo() + ",%' ORDER BY SendDT DESC  LIMIT 20";
                break;
            case MSSQL:
                sql = "SELECT Top 20 SendDT,Sender,WFState,TodoEmps,FlowName,NodeName,WorkID,Title,StarterName,Starter FROM WF_GenerWorkFlow WHERE WFState >1  AND TodoEmps LIKE '%" + WebUser.getNo() + ",%' ORDER BY SendDT DESC  ";
                break;
            case KingBaseR8:
            case KingBaseR6:
            case KingBaseR3:
                sql = "SELECT SendDT,Sender,WFState,TodoEmps,FlowName,NodeName,WorkID,Title,StarterName,Starter FROM WF_GenerWorkFlow WHERE WFState >1 TodoEmps LIKE '%" + WebUser.getNo() + ",%' ORDER BY SendDT DESC  FETCH FIRST 20 ROWS ONLY ";
                break;
            default:
                sql = "SELECT SendDT,Sender,WFState,TodoEmps,FlowName,NodeName,WorkID,Title,StarterName,Starter FROM WF_GenerWorkFlow WHERE WFState >1 AND TodoEmps LIKE '%" + WebUser.getNo() + ",%' ORDER BY SendDT DESC  LIMIT 20";
                break;
        }
        DataTable dt = DBAccess.RunSQLReturnTable(sql);
        return bp.tools.Json.ToJson(dt);
    }
    /**
     流程动态-更多

     @return
     */
    public final String Self_MovementFlow_More()
    {
        String sql = "";
        switch (SystemConfig.getAppCenterDBType())
        {
            case MySQL:
            case DM:
                sql = "SELECT SendDT,Sender,WFState,TodoEmps,FlowName,NodeName,WorkID,Title,StarterName,Starter FROM WF_GenerWorkFlow WHERE WFState >1 AND TodoEmps LIKE '%" + WebUser.getNo() + ",%' ORDER BY SendDT DESC  LIMIT 20";
                break;
            case MSSQL:
                sql = "SELECT Top 20 SendDT,Sender,WFState,TodoEmps,FlowName,NodeName,WorkID,Title,StarterName,Starter FROM WF_GenerWorkFlow WHERE WFState >1  AND TodoEmps LIKE '%" + WebUser.getNo() + ",%' ORDER BY SendDT DESC  ";
                break;
            case KingBaseR8:
            case KingBaseR6:
            case KingBaseR3:
                sql = "SELECT SendDT,Sender,WFState,TodoEmps,FlowName,NodeName,WorkID,Title,StarterName,Starter FROM WF_GenerWorkFlow WHERE WFState >1 TodoEmps LIKE '%" + WebUser.getNo() + ",%' ORDER BY SendDT DESC  FETCH FIRST 20 ROWS ONLY ";
                break;
            default:
                sql = "SELECT SendDT,Sender,WFState,TodoEmps,FlowName,NodeName,WorkID,Title,StarterName,Starter FROM WF_GenerWorkFlow WHERE WFState >1 AND TodoEmps LIKE '%" + WebUser.getNo() + ",%' ORDER BY SendDT DESC  LIMIT 20";
                break;
        }
        DataTable dt = DBAccess.RunSQLReturnTable(sql);
        return bp.tools.Json.ToJson(dt);
    }
    ///#endregion 流程动态组件.
}
