package bp.da;

import bp.sys.CCBPMRunModel;
import bp.web.WebUser;

import java.util.Hashtable;

public class SQLFlow {
    public SQLFlow() { }

    public static String Gener_Flow(String mark, Hashtable ht ) throws Exception {
        String key = null;
        if (ht != null && ht.containsKey("Key") == true)
            key = ht.get("Key").toString();

        //流程类别
        if (mark.equals("Flow_FlowSorts") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name,ParentNo FROM WF_FlowSort Order by NO,Idx  ";
            else
                return "SELECT No,Name,ParentNo FROM WF_FlowSort WHERE OrgNo= '" + WebUser.getOrgNo() + "' Order By NO,Idx ";
        }
        //流程列表
        if (mark.equals("Flow_Flows") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name,FK_FlowSort GroupNo FROM WF_Flow Order By Idx ";
            else
                return "SELECT No,Name,FK_FlowSort GroupNo FROM WF_Flow WHERE OrgNo= '" + WebUser.getOrgNo() + "' Order By Idx ";
        }
        //节点列表
        if (mark.equals("Flow_Nodes") == true)
        {
            return "SELECT NodeID as No,Name FROM WF_Node WHERE FK_Flow='" + ht.get("FK_Flow") + "' Order by Step ";
        }
        if (mark.equals("Flow_NodesByKey") == true)
        {
            return "SELECT NodeID as No,Name FROM WF_Node WHERE FK_Flow='" + key + "' Order by Step ";
        }
        //单流程管理员大屏类的sql.
        if (mark.startsWith("Flow_DataV_OneFlowAdmin") == true)
        {
            return DataV_OneFlowAdmin(mark, ht);
        }
        //用户大屏类的sql.
        if (mark.startsWith("Flow_DataV_OneFlowEmp") == true)
            return DataV_OneFlowEmp(mark, ht);
        //新建子流程 GPN_NewSubFlow  新建子流程时排除父流程
        if (mark.equals("Flow_SubFlowSrcList") == true)
        {
            return "SELECT No,Name,FK_FlowSort as GroupNo FROM WF_Flow WHERE No != '" + key + "' ORDER BY Idx ";
        }
        //工作批量移交 GPN_WorkShiftBatch  请选择规则
        if (mark.equals("Flow_WorkShiftBatchFlows") == true)
        {
            return "SELECT WorkID AS No,Title AS Name FROM wf_generworkflow WHERE TodoEmps LIKE '%" + key + "%' AND (WFState=2 OR WFState=5)";
        }
        return null;
    }
    //单流程管理员大屏类的sql.

    public static String DataV_OneFlowAdmin(String mark, Hashtable ht ) throws Exception {
        String key = null;
        if (ht != null && ht.containsKey("Key") == true)
            key = ht.get("Key").toString();

        switch (mark)
        {
            case "Flow_DataV_OneFlowAdmin_Todo": //待办.
                return "SELECT count(*) as Num FROM WF_GenerWorkerList WHERE IsPass=0 AND FK_Flow=" + key;
            case "Flow_DataV_OneFlowAdmin_Return": //退回.
                return "SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE WFState=5 AND FK_Flow=" + key;
            case "Flow_DataV_OneFlowAdmin_Runing": //未完成.
                return "SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE  WFState In (2,4,5,6,7,8,9,10,11) AND FK_Flow=" + key;
            case "Flow_DataV_OneFlowAdmin_Complete": //已完成.
                return "SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE  WFState=3 AND FK_Flow=" + key;
            case "Flow_DataV_OneFlowAdmin_TodoFlow": //待办流程发布.
                return "SELECT NodeName, Count(*) as Num FROM WF_GenerWorkerList WHERE IsPass=0 AND FK_Flow='" + key + "'group by NodeName";
            case "Flow_DataV_OneFlowAdmin_Average": //节点平均工作时长(H).
                return "SELECT NodeIDT, Count(*) as Num FROM WF_CH WHERE FlowNo='" + key + "' Group by NodeIDT ";
            default:
                break;
        }

        throw new Exception("err@没有判断的SQL标记:" + mark);
    }
    //用户大屏类的sql.
    public static String DataV_OneFlowEmp(String mark, Hashtable ht) throws Exception {
        String key = null;
        if (ht != null && ht.containsKey("Key") == true)
            key = ht.get("Key").toString();

        switch (mark)
        {
            case "Flow_DataV_OneFlowEmp_Todo": //待办.
                return "SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE TodoEmps LIKE '%" + WebUser.getNo() + "%' AND WFState>=1 AND FK_Flow='" + key +"'";
            case "Flow_DataV_OneFlowEmp_Return": //退回.
                return "SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE TodoEmps LIKE  '%" + WebUser.getNo() + "%' AND WFState=5 AND FK_Flow='" + key + "'";
            case "Flow_DataV_OneFlowEmp_Runing": //未完成.
                return "SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE TodoEmps LIKE  '%" + WebUser.getNo() + "%' AND WFState=5 AND FK_Flow='" + key + "'";
            case "Flow_DataV_OneFlowEmp_Complete": //已完成.
                return "SELECT count(*) as Num FROM WF_GenerWorkFlow WHERE TodoEmps LIKE '%" + WebUser.getNo() + "%' AND WFState=3 AND FK_Flow='" + key + "'";
            case "Flow_DataV_OneFlowEmp_TodoFlow": //待办流程发布.
                return "SELECT NodeName, Count(*) as Num FROM WF_GenerWorkFlow WHERE TodoEmps LIKE  '%" + WebUser.getNo() + ",%' AND FK_Flow='" + key + "' Group BY NodeName";
            default:
                break;
        }
        throw new Exception("err@没有判断的SQL标记:" + mark);
    }
}
