package bp.da;

import bp.sys.CCBPMRunModel;
import bp.web.WebUser;

import java.util.Hashtable;

public class SQLFrm {
    public SQLFrm() { }

    public static String Gener_Frm(String mark, Hashtable ht )
    {
        String key = null;
        if (ht != null && ht.containsKey("Key") == true)
            key = ht.get("Key").toString();

        //节点编号。
        if (mark.equals("Frm_NodeFrmList") == true)
        {
            if (bp.difference.SystemConfig.getAppCenterDBType() == DBType.MySQL || bp.difference.SystemConfig.getAppCenterDBType() == DBType.MSSQL)
                return "SELECT CONCAT('ND',NodeID) as No, Name FROM WF_Node WHERE FK_Flow='" + ht.get("FK_Flow") + "'";
            else if (bp.difference.SystemConfig.getAppCenterDBType() == DBType.Oracle)
                return "SELECT CONCAT(\"ND\",NodeID) as No, Name FROM WF_Node WHERE FK_Flow='" + ht.get("FK_Flow") + "'";
            else
                return "SELECT  CONCAT('ND',NodeID) as No, Name FROM WF_Node WHERE FK_Flow='" + ht.get("FK_Flow") + "'";
        }

        //表单列表
        if (mark.equals("Frm_FrmList") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData ";
            else
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE OrgNo= '" + WebUser.getOrgNo() + "' ";
        }
        //表单树
        if (mark.equals("Frm_FrmTree") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No, Name, ParentNo FROM Sys_FormTree  ";
            else
                return "SELECT No, Name,ParentNo FROM Sys_FormTree WHERE OrgNo= '" + WebUser.getOrgNo() + "' ";
        }
        //表单实体
        if (mark.equals("Frm_FrmEntityNoName") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE EntityType=5";
            else
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE  EntityType=5 AND OrgNo= '" + WebUser.getOrgNo() + "' ";
        }
        //绑定表单列表
        if (mark.equals("Frm_BindFrmList") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE EntityType=1 or EntityType=1 or EntityType=2 or EntityType=0";
            else
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE OrgNo='" + WebUser.getOrgNo() + "' AND  (EntityType=1 or EntityType=1 or EntityType=2) ";
        }
        //单据表单列表
        if (mark.equals("Frm_FrmListOfDictBill") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE EntityType=1 OR EntityType=5";
            else
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE OrgNo='" + WebUser.getOrgNo() + "'  AND (EntityType=1 OR EntityType=5) ";
        }
        //单据引用列表
        if (mark.equals("Frm_FrmListOfBill") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE EntityType=1";
            else
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE OrgNo='" + WebUser.getOrgNo() + "' AND EntityType=1  ";
        }
        //实体列表
        if (mark.equals("Frm_FrmDictList") == true)
        {
            return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE EntityType=2 OR EntityType=5";
        }
        //单据表单列表
        if (mark.equals("Frm_FrmListBill") == true)
        {
            return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE EntityType=1";
        }
        //根据EntityType表单列表
        if (mark.equals("Frm_FrmListByEntityType") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE EntityType IN (0,1)";
            else
                return "SELECT No,Name,FK_FormTree AS GroupNo FROM Sys_MapData WHERE EntityType IN (0,1) AND OrgNo='" + WebUser.getOrgNo() + "'";
        }
        //数值类型的字段
        if (mark.equals("Frm_MapAttrNumberFields") == true)
        {
            return "SELECT KeyOfEn as No, CONCAT(KeyOfEn,' ',Name) Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("FK_MapData") + "' AND UIContralType=0 AND MyDataType IN (2,3,5,8)  AND KeyOfEn NOT IN ('OID','FID')";
        }
        //选择字段
        if (mark.equals("Frm_MapAttrDTFields") == true)
        {
            return "SELECT KeyOfEn as No, CONCAT(KeyOfEn,' ',Name) Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("Doc") + "' AND UIContralType=0 AND MyDataType IN (6,7)  AND KeyOfEn NOT IN ('OID','FID')";
        }
        //表单字段
        if (mark.equals("Frm_FrmFields") == true)
        {
            return "SELECT KeyOfEn as No, Name, GroupID FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("FrmID") + "' AND UIVisible=1 ORDER BY GroupID, Idx";
        }
        //表单分组
        if (mark.equals("Frm_FrmGroups") == true)
        {
            return "SELECT OID as No, Lab as Name  FROM Sys_GroupField WHERE FrmID='" + key + "' ORDER BY OID, Idx";
        }
        //字段列表
        if (mark.equals("Frm_MapAttrs") == true)
        {
            return "SELECT MyPK as No, CONCAT(MyPK,' ',Name) as Name FROM Sys_MapAttr WHERE FK_MapData='" + key + "' AND KeyOfEn NOT IN ('OID','FID','AtPara')";
        }
        //检查字段
        if (mark.equals("Frm_CheckField") == true)
        {
            return "SELECT KeyOfEn AS No,Name From Sys_MapAttr WHERE UIContralType=14 AND FK_MapData='" + ht.get("FK_Frm") + "'";
        }
        //字段分组
        if (mark.equals("Frm_GroupField") == true)
        {
            return "SELECT OID as No, Lab as Name  FROM Sys_GroupField WHERE FrmID='" + key + "' ORDER BY Idx ";
        }
        //字段
        if (mark.equals("Frm_Fields") == true)
        {
            return "SELECT KeyOfEn as No, Name, GroupID as GroupNo FROM Sys_MapAttr WHERE FK_MapData='" + key + "'  AND KeyOfEn NOT IN('OID','Title','StarterName','Starter','FK_Dept','OrgNo','RDT','BillState','BillNo','AtPara','FID')  AND UIVisible = 1 ORDER BY GroupID, Idx";
        }
        //表单字段
        if (mark.equals("Frm_FrmFields_M") == true)
        {
            return "SELECT KeyOfEn as No, Name, GroupID FROM Sys_MapAttr WHERE FK_MapData='" + key + "'  AND KeyOfEn NOT IN ('OID','Title','StarterName','Starter','FK_Dept','OrgNo','RDT','BillState','BillNo','AtPara','FID')  AND UIVisible=1 ORDER BY GroupID, Idx ";
        }
        //单据分组
        if (mark.equals("Frm_DictGroups_M") == true)
        {
            return "SELECT KeyOfEn as No, Name, GroupID FROM Sys_MapAttr WHERE FK_MapData='" + key + "'  AND UIVisible=1 ORDER BY GroupID, Idx ";
        }
        //日期查询GPE_DTSearchWay
        if (mark.equals("Frm_GpeDTSearchWay") == true)
        {
            return "SELECT KeyOfEn as No,Name FROM Sys_MapAttr  WHERE FK_MapData ='" + key + "' AND UIVisible = 1 AND(MyDataType = 6 or MyDataType = 7)  AND UIContralType = 0 ";
        }
        //展现方式GPE_ListShowWay
        if (mark.equals("Frm_GpeListShowWay") == true)
        {
            return "SELECT KeyOfEn as No,Name FROM Sys_MapAttr  WHERE FK_MapData = '" + key + "' AND UIVisible = 1  AND(UIContralType = 1 OR UIContralType = 3) ";
        }
        //关键字查询GPE_SearchKey
        if (mark.equals("Frm_GpeSearchKey") == true)
        {
            return "SELECT KeyOfEn as No,Name FROM Sys_MapAttr WHERE FK_MapData= '" + key + "' AND UIVisible=1 AND MyDataType=1 AND UIContralType=0" +
                    "  AND KeyOfEn NoT IN(SELECT B.KeyOfEn FROM Sys_MapAttr A, Sys_MapAttr B WHERE A.FK_MapData= B.FK_MapData AND A.FK_MapData =  '" + key + "' AND A.KeyOfEn = CONCAT(B.KeyOfEn, 'T'))" +
                    "  UNION SELECT A.KeyOfEn as No, B.Name AS Name FROM Sys_MapAttr A, Sys_MapAttr B WHERE A.FK_MapData = B.FK_MapData AND A.FK_MapData =  '" + key + "' AND A.KeyOfEn = CONCAT(B.KeyOfEn, 'T')";
        }
        //新建查询条件GPN_SearchFKEnum
        if (mark.equals("Frm_GpnSearchFKEnum") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr  WHERE FK_MapData ='" + key + "'  AND UIVisible = 1   AND(UIContralType = 1 OR UIContralType = 2 OR UIContralType = 3)";
        }
        //新建列表组件GPN_Collection  需要集合支持
        if (mark.equals("Frm_GpnCollection") == true)
        {
            return "SELECT No,Name FROM Frm_Method  WHERE MethodModel='Func' AND FrmID=='" + key + "' ORDER BY Idx ";
        }
        //单记录只读规则GPN_RecReadonly  按照字段计算.
        if (mark.equals("Frm_GpnRecReadonly") == true)
        {
            return "SELECT MyPK as No, Name FROM Sys_MapAttr WHERE FK_MapData='" + key + "'  AND KeyOfEn NOT IN('OID','FID','AtPara') ";
        }
        //数据列表GL_DBList 单记录的数据
        if (mark.equals("Frm_GlDBList") == true)
        {
            return "SELECT * FROM " + ht.get("Ptable") + " WHERE OID=" + ht.get("OID");
        }
        //新建实体方法GPN_Method  groupSQL.
        if (mark.equals("Frm_GpnMethodGroupSQL") == true)
        {
            return "SELECT OID as No, Lab as Name FROM Sys_GroupField WHERE FrmID='" + key + "'  AND  (CtrlID = '' OR CtrlID IS NULL)";
        }
        //新建实体方法GPN_Method  attrSQL.
        if (mark.equals("Frm_GpnMethodAttrSQL") == true)
        {
            return "SELECT MyPK AS No, Name, GroupID FROM Sys_MapAttr WHERE FK_MapData='" + key + "' AND UIContralType <=4 AND KeyOfEn NOT IN ('OID','Rec','RDT','FID','Title','BillNo','BillState','FlowStarter', 'FlowEmps',  'FlowStartRDT','WFState','Emps')  AND UIVisible=1 ORDER BY GroupID,Idx";
        }
        //审核退回  退回到  GenerWorkerReturn
        if (mark.equals("Frm_GenerWorkerReturn") == true)
        {
            return "SELECT Idx as No, EmpName as Name FROM Frm_GenerWorker WHERE WorkID='"+ ht.get("WorkID") + "' Order BY Idx";
        }
        //数据篡改  GPN_BillRecJuggle
        if (mark.equals("Frm_GpnBillRecJuggle") == true)
        {
            return "SELECT RecName , RDT , Msg, AtPara  FROM Frm_Track WHERE WorkID='" + ht.get("WorkID") + "' AND ActionType IN ('RecMainUpdate','RecDtlUpdate')";
        }
        //选择从表   1、数据篡改 选择从表修改 GPN_BillRecJuggle  2、新建菜单 GPN_Menu 选择从表
        if (mark.equals("Frm_Dtls") == true)
        {
            return "SELECT No,Name FROM Sys_MapDtl WHERE FK_MapData='" + key + "'";
        }

        //获取系统    1、菜单迁移到其他系统 GPN_MenuToSystem  系统
        if (mark.equals("Frm_Systems") == true)
        {
            return "SELECT No,Name From GPM_System";
        }
        //获取菜单  1、菜单迁移到其他系统 GPN_MenuToSystem  模板
        if (mark.equals("Frm_Modules") == true)
        {
            return "SELECT No, Name FROM GPM_Module WHERE SystemNo='"+key+"'";
        }

        //数据源向导 GPN_WindowDBSrc  查询需要的字段
        if (mark.equals("Frm_WindowDBSrcAttrsSQL") == true)
        {
            return "SELECT KeyOfEn as No,Name FROM Sys_MapAttr WHERE MyDataType IN (2,3,5,8) AND LGType=0 AND FK_MapData='" + key + "'";
        }
        //数据源向导 GPN_WindowDBSrc  查询需要的字段
        if (mark.equals("Frm_WindowDBSrcAttrsSQL1") == true)
        {
            return "SELECT KeyOfEn as No,Name FROM Sys_MapAttr WHERE MyDataType IN (2,1) AND LGType in (1,2) AND FK_MapData='" + key + "'";
        }

        //表单(实体&单据)向导 GPN_WindowFrm 查询需要的字段
        if (mark.equals("Frm_WindowFrmNumberSQL") == true)
        {
            return "SELECT KeyOfEn as No,Name FROM Sys_MapAttr WHERE MyDataType IN (2,3,5,8)    AND LGType = 0 AND" +
                    " FK_MapData = '" + key + "'  AND KeyOfEn NOT IN('OID','FID','PWorkID','BillState','WFState','WFSta')  " +
                    " UNION   SELECT 'MyCountNum' as No, '条数' as Name FROM WF_Emp WHERE No = 'admin' ";
        }
        //表单(实体&单据)向导 GPN_WindowFrm 查询需要的字段
        if (mark.equals("Frm_WindowFrmFkEnumSQL") == true)
        {
            return "SELECT KeyOfEn as No,Name FROM Sys_MapAttr WHERE MyDataType IN (2,1)  AND UIContralType in (1, 3) AND FK_MapData = '" + key + "'";
        }

        //获取菜单  不传参
        if (mark.equals("Frm_ModuleNo") == true)
        {
            return "SELECT No,Name FROM GPM_Module WHERE SystemNo='" + ht.get("SystemNo") + "'";
        }
        //业务字段 GPE_FlowBuessFields  attrSQL.
        if (mark.equals("Frm_GpeFlowBuessFields") == true)
        {
            return "SELECT KeyOfEn AS No, Name, GroupID AS GroupNo FROM Sys_MapAttr WHERE FK_MapData='" + key + "' AND UIContralType <=4 AND KeyOfEn NOT IN ('OID','Rec','RDT','FID','Title','BillNo','BillState','FlowStarter','FlowEmps','FlowStartRDT','WFState','Emps')  AND UIVisible=1 ORDER BY GroupID,Idx";
        }

        //获取流程的节点   1、同步数据规则  GPE_SyncRole 指定的节点发送后
        if (mark.equals("Frm_NodesOfFlow") == true)
        {
            return "SELECT NodeID as No,Name FROM WF_Node WHERE FK_Flow= '" + key + "'";
        }

        //选择要同步的数据  GPN_SelectTableAndFields
        if (mark.equals("Frm_SelectTableAndFields") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE FK_MapData='ND" + key + "Rpt'  ORDER BY GroupID,Idx";
        }
        //选择要同步的表单  GPN_SyncData 数据源表单
        if (mark.equals("Frm_FrmsFields") == true)
        {
            return "SELECT DISTINCT A.FK_Frm as No, B.Name as Name FROM WF_FrmNode A,Sys_MapData B WHERE A.FK_Flow='" + ht.get("flowNo") +"' AND A.FK_Frm=B.No" +
                    "  UNION SELECT '" + ht.get("frmIDRpt") + "' as No, '" + ht.get("frmIDRpt") +"流程业务表' as Name FROM Port_Emp where No = 'admin' ";
        }
        //字段同步  SyncDataField 选字段
        if (mark.equals("Frm_ToFieldKey") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("FrmID") + "' ORDER BY GroupID,Idx";
        }
        //审核组件 NodeWorkCheck 签批字段
        if (mark.equals("Frm_NodeWorkCheckField") == true)
        {
            return "SELECT KeyOfEn AS No,Name From Sys_MapAttr Where UIContralType=14 AND (FK_MapData='ND" + ht.get("NodeID") + "' OR FK_MapData='" + ht.get("NodeFrmID") + "' )";
        }
        //从表里的人员编号字段 AccepterRoleByEmpsFrmDtl  从表
        if (mark.equals("Frm_FrmMapDtl") == true)
        {
            return "SELECT No,Name From Sys_MapDtl Where FK_MapData!='' AND (FK_MapData='" + ht.get("NodeFrmID") + "' OR FK_MapData='ND" + ht.get("NodeID") +"' )";
        }
        //从表里的人员编号字段 AccepterRoleByEmpsFrmDtl  从表列 选择字段
        if (mark.equals("Frm_FrmMapDtlColumn") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("DeliveryParas") + "' AND MyDataType=1 AND KeyOfEn Not IN ('AtPara','OID','RDT','GUID','Title','Rec','CDT')";
        }
        //获取流程的节点  GPE_AccepterRole 接收人规则 不传参
        if (mark.equals("Frm_NodesOfFlowNoPara") == true)
        {
            return "SELECT NodeID as No, Name FROM WF_Node WHERE FK_Flow='" + ht.get("FK_Flow") + "'";
        }
        //获取表单字段  GPE_AccepterRole 接收人规则
        if (mark.equals("Frm_ArrtOfNode") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE FK_MapData='"+key+"'  AND KeyOfEn Not IN('AtPara','OID','RDT','GUID','Title','Rec','CDT')";
        }
        //获取流程节点 部门集合范围 GPE_ARDeptModel 按指定节点提交人计算
        if (mark.equals("Frm_NodesAcceptRole") == true)
        {
            return "SELECT NodeID No,CONCAT(NodeID,'_',Name) as Name FROM WF_Node WHERE FK_Flow='" + key + "'";
        }
        //可选人员范围 GPE_SelectorModel 按SQL模板计算
        if (mark.equals("Frm_SQLTemplate") == true)
        {
            return "SELECT No,Name FROM WF_SQLTemplate WHERE SQLType=5";
        }
        //指定字段的时间考核 EvaluationRole4   选字段(对表单字段有效)
        if (mark.equals("Frm_RoleField") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE FK_MapData ='ND" + ht.get("NodeID") + "' AND MyDataType IN (7,8) ";
        }
        //附件权限  GPN_FrmNewAttachment 影响的附件
        if (mark.equals("Frm_NewAthSQL") == true)
        {
            return "SELECT MyPK AS No,Name FROM Sys_FrmAttachment WHERE FK_MapData='"+key+"' AND FK_Node=0 ";
        }
        //从表权限  GPN_FrmNewDtl 影响的从表
        if (mark.equals("Frm_NewDtlSQL") == true)
        {
            return "SELECT No,Name FROM Sys_MapDtl WHERE FK_MapData='" + key + "' AND FK_Node=0 ";
        }
        //摘要字段 GPE_FrmSummaryField 选择摘要字段
        if (mark.equals("Frm_FrmSummaryFields") == true)
        {
            return "SELECT KeyOfEn as No, Name, GroupID as GroupNo  FROM Sys_MapAttr WHERE FK_MapData='" + key + "' and UIVisible=1 Order BY  GroupID, Idx";
        }
        //推送对象选择方式 GPN_PushMsg  表单字段
        if (mark.equals("Frm_PushMsgFields") == true)
        {
            return "SELECT OID as No, Lab as Name from Sys_GroupField WHERE FrmID='ND"+ key + "'";
        }
        //推送对象选择方式 GPN_PushMsg 表单上的字段作为接受对象
        if (mark.equals("Frm_PushMsgSrcList") == true)
        {
            return "SELECT KeyOfEn as No,Name,GroupID as GroupNo FROM Sys_MapAttr WHERE FK_MapData='" + key + "'";
        }
        //超时处理规则 GPE_OvertimeRole 自动跳转到指定节点
        if (mark.equals("Frm_OvertimeRole") == true)
        {
            return "SELECT NodeID as No,CONCAT(NodeID,'-',Name) AS Name FROM WF_Node WHERE FK_Flow='" + ht.get("FK_Flow") + "'";
        }
        //引用单据 GPN_CtrlAutoFullBill 获得表的字段.
        if (mark.equals("Frm_BillFields") == true)
        {
            return "SELECT MyPK as No, Name FROM Sys_MapAttr WHERE FK_MapData='" + key + "'  " +
                    "AND KeyOfEn NOT IN ('OID','FID','AtPara','DeptNo','DeptName','Rec','OrgNo','BillState','EntityState', 'No','Name','BillNo','Title','PFrmID','Starter'," +
                    "'PWorkID','StarterName','RDT') AND UIVisible=1 ";
        }
        //新建从表字段 GPN_DtlField 普通类型的字段.
        if (mark.equals("Frm_DtlFieldMaxIdx") == true)
        {
            return "SELECT Max(Idx) as Idx From Sys_MapAttr Where FK_MapData='" + key + "' " ;
        }
        //新建枚举/外键字段 GPN_NewDDL 新建外键字段
        if (mark.equals("Frm_NewDDLDict") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return " SELECT No, Name, DBSrcType as GroupNo FROM Sys_SFTable WHERE No!= 'Blank'";
            else
                return " SELECT No, Name, DBSrcType as GroupNo FROM Sys_SFTable WHERE No!= 'Blank' AND OrgNo = '" + WebUser.getOrgNo() + "'";
        }
        //新建条件/表达式 GPN_Cond 表单字段条件
        if (mark.equals("Frm_CondByFrm") == true)
        {
            return "SELECT  No, Name  FROM Sys_MapData  WHERE No='ND" + ht.get("nodeID")+ "' " +
                    "UNION A.FK_Frm as No, B.Name as Name FROM WF_FrmNode A,Sys_MapData B  WHERE A.FK_Node='" + ht.get("nodeID")+ "' AND A.FK_Frm=B.No  " +
                    "UNION SELECT '" + ht.get("frmIDRpt")+ "' as No, '" + ht.get("frmIDRpt")+ "流程业务表' as Name FROM Port_Emp where No='admin' ";
        }
        //新建条件/表达式 GPN_Cond 表选择字段
        if (mark.equals("Frm_CondSelectField") == true)
        {
            return " SELECT OID as No, Lab AS Name FROM Sys_GroupField WHERE FrmID=" + key + "' AND 1=1  UNION  SELECT 0 AS No, '无分组' as Name FROM Port_Emp WHERE No='admin' ";
        }
        //新建条件/表达式 GPN_Cond 表选择字段
        if (mark.equals("Frm_CondByFrmSelectField") == true)
        {
            return " SELECT MyPK as No, Name, GroupID FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("frmID")+ "'  AND KeyOfEn NOT IN ("+ ht.get("exp")+ ") " +
                    " AND GroupID  IN (select OID from Sys_GroupField where FrmID= '" + ht.get("frmID")+ "' UNION " +
                    " SELECT MyPK as No, Name, 0 as GroupID FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("frmID")+ "' " +
                    " AND KeyOfEn NOT IN ("+ ht.get("exp")+ ") AND GroupID Not IN (select OID from Sys_GroupField where FrmID='" + ht.get("frmID")+ "'";
        }
        //新建扩展自定义组件 GPN_ComponentMapExt选择表
        if (mark.equals("Frm_GenerDBSrcTabls") == true)
        {
            return " SELECT MyPK as No, Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("frmID")+ "'" +
                    "AND KeyOfEn NOT IN('OID','FID','AtPara','DeptNo','DeptName','Rec','OrgNo','BillState','EntityState', 'No','Name','BillNo','Title','PFrmID','Starter','PWorkID','StarterName','RDT') AND UIVisible = 1 ";
        }
        //模板导入导出 GPN_FrmExpImp Office导入
        if (mark.equals("Frm_FieldModel") == true)
        {
            return " SELECT 0 AS No, '把列作为字段' AS Name FROM WF_Emp WHERE No='admin' UNION SELECT 1 AS No, '把内容作为字段' as Name FROM WF_Emp WHERE No='admin' ";
        }
        //模板导入导出 GPN_FrmExpImp 表单模式
        if (mark.equals("Frm_FrmModel") == true)
        {
            return " SELECT 0 AS No, '经典表单' AS Name FROM WF_Emp WHERE No='admin' ";
        }
        //数据开放规则 GPE_DtlOpenType 指定人员账号字段
        if (mark.equals("Frm_DtlOpenPara") == true)
        {
            return " SELECT KeyOfEn AS No,Name From Sys_MapAttr WHERE FK_MapData= '"+key+"'";
        }
        //批量编辑规则 GPE_IsBatchUpdate 启用
        if (mark.equals("Frm_IsBatchUpdateAttrs") == true)
        {
            return "SELECT KeyOfEn AS No,Name From Sys_MapAttr WHERE FK_MapData='" + key + "' AND UIVisible=1 ";
        }
        //导入实体字段 GPN_ImpDtlAttrs 选择从表
        if (mark.equals("Frm_MapDtl") == true)
        {
            return "SELECT No,Name FROM Sys_MapDtl  ";
        }
        //导入实体字段 GPN_ImpDtlAttrs 获得从表的字段
        if (mark.equals("Frm_MapDtlFields") == true)
        {
            return "SELECT MyPK as No, Name FROM Sys_MapAttr WHERE FK_MapData='" + key + "' AND KeyOfEn NOT IN ('OID','FID','RefPK','Rec','RDT','CDT') ";
        }
        //从表属性 MapDtlExt  子线程处理人字段
        if (mark.equals("Frm_SubThreadWorker") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr  WHERE FK_MapData='" + ht.get("No")+ "' AND  ( (MyDataType =1 and UIVisible=1 ) or (UIContralType=1))";
        }
        //展示模式 GPE_ListShowModel 损益表模式
        if (mark.equals("Frm_IncomeStatement") == true)
        {
            return "SELECT KeyOfEn No, Name FROM Sys_MapAttr WHERE FK_MapData='" + key + "' AND UIContralType=1 ";
        }
        //级联下拉框 GPEActiveDDL 联动的字段
        if (mark.equals("Frm_AttrsOfActive") == true)
        {
            return "SELECT KeyOfEn AS No, Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("FK_MapData") + "' AND UIContralType=2  ";
        }
        //节点属性 ListShoModel2D 2维表字段
        if (mark.equals("Frm_ListShoModel2D") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("No") + "' AND UIVisible=1";
        }
        //节点属性 ListShoModel2D 2维表字段
        if (mark.equals("Frm_ListlNumberField") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("No") + "' AND UIVisible=1 AND MyDataType IN(2,3,5,8)";
        }
        //节点属性 ListShoModel3D 2维表字段
        if (mark.equals("Frm_ListShoModel3D") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("No") + "' AND UIVisible=1 AND UIContralType=1";
        }
        //表格弹窗 GPEActiveDDLSFTable 级联下拉框
        if (mark.equals("Frm_ActiveDDL") == true)
        {
            return "SELECT KeyOfEn AS No, Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("FK_MapData") + "' AND UIContralType=1  AND KeyOfEn !='" + ht.get("AttrOfOper") + "'";
        }
        //表格弹窗 GPEAutoFullDtlField 对从表列求值 从表
        if (mark.equals("Frm_AutoFullDtlField") == true)
        {
            return "SELECT No,Name From Sys_MapDtl WHERE FK_MapData='" + ht.get("FK_MapData") + "'";
        }
        //关联更新的类 EnumHidItem 点击事件隐藏选项 联动的控件
        if (mark.equals("Frm_EnumHidItem") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE LGType=1 AND UIVisible=1 AND UIIsEnable=1 AND FK_MapData='" + ht.get("FK_MapData") + "' AND KeyOfEn!='" + ht.get("AttrOfOper") + "'";
        }
        //选择附件 GPN_FullAth 选择附件
        if (mark.equals("Frm_FullAth") == true)
        {
            return "SELECT NoOfObj AS No,Name FROM Sys_FrmAttachment WHERE FK_MapData='" + key + "'";
        }
        //选择下拉框 GPN_FullDataDDL 选择下拉框
        if (mark.equals("Frm_SelectDDL") == true)
        {
            return "SELECT KeyOfEn as No,Name  FROM Sys_mapAttr WHERE FK_MapData='" + key + "' AND ( UIContralType=1 or UIContralType=2) ";
        }
        //新增影响的元素 GPN_RBNewAttr 影响的字段
        if (mark.equals("Frm_RBNewGroup") == true)
        {
            return "SELECT OID AS No,Lab as Name FROM Sys_GroupField WHERE (CtrlType='' OR CtrlType IS NULL) AND FrmID='" + key + "'  ORDER BY Idx ";
        }
        //两个日期之差 GPEReqDays 日期
        if (mark.equals("Frm_GPEReqDays") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("FK_MapData") + "'  AND (MyDataType=6 OR MyDataType=7) ORDER BY GroupID,Idx ";
        }
        //节假日 GPEReqDays
        if (mark.equals("Frm_ReqDaysHolidays") == true)
        {
            switch (bp.difference.SystemConfig.getAppCenterDBType())
            {
                case MySQL:
                case MSSQL:
                case PostgreSQL:
                    return "SELECT 0 AS No,'不计算节假日' AS Name  UNION SELECT  1 AS No,'计算节假日' AS Name ";
                case Oracle:
                case KingBaseR3:
                case KingBaseR6:
                    return "SELECT 0 AS No,'不计算节假日' AS Name  FROM DUAL UNION SELECT 1 AS No,'计算节假日' AS Name FROM DUAL";
                case UX:
                case HGDB:
                    return "SELECT 0 AS No,'不计算节假日' AS Name  UNION SELECT  1 AS No,'计算节假日' AS Name ";
                default:
                    break;
            }
        }
        //日期输入规则 GPEDateFieldInputRole  日期字段
        if (mark.equals("Frm_DateFieldInputRole") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE MyDataType IN(6,7) AND UIVisible=1 AND UIIsEnable=1 AND FK_MapData='" + ht.get("FK_MapData") + "' ";
        }
        //人民币大写 GPE_RMBDaXie  选择只读的人民币大写字段
        if (mark.equals("Frm_RMBDaXie") == true)
        {
            return "SELECT KeyOfEn as No, Name FROM Sys_MapAttr WHERE FK_MapData='" + ht.get("FK_MapData") + "'  AND UIVisible=1 AND UIIsEnable = 0 AND MyDataType=1 ";
        }
        //参数 SFPara  内参表达式
        if (mark.equals("Frm_SFPara") == true)
        {
            return "SELECT No,Name FROM Sys_GloVar WHERE GroupKey='DefVal' ";
        }
        //表单ID
        if (mark.equals("Frm_SelectCheckField") == true)
        {
            return "SELECT '' AS No, '-请选择-' as Name FROM Port_Emp WHERE 1=2  union  SELECT KeyOfEn AS No,Name From Sys_MapAttr WHERE UIContralType=14 AND FK_MapData= '" + ht.get("FK_Frm") + "' ";
        }
        //表单ID
        if (mark.equals("Frm_SelectBillNo") == true)
        {
            return "SELECT '' AS No, '-请选择-' as Name FROM Port_Emp WHERE 1=2  union SELECT KeyOfEn AS No,Name From Sys_MapAttr WHERE UIContralType=0 AND UIVisible=1 AND UIIsEnable=1 AND MyDataType=1  AND FK_MapData= '" + ht.get("FK_Frm") + "' ";
        }
        //数据篡改 GPN_FlowDataRecJuggle 修改记录
        if (mark.equals("Frm_DataRecJuggleHistory") == true)
        {
            return "SELECT EmpFromT , RDT , Msg, NodeData  FROM  'ND" + ht.get("FK_Flow") + "Track' WHERE WorkID='" + ht.get("WorkID") + "'  AND ActionType IN (35,36)";
        }
        //调整 GPN_ReSend 选择节点
        if (mark.equals("Frm_GenerNode") == true)
        {
            return "SELECT NodeID as No, Name FROM WF_Node WHERE FK_Flow='" + key + "' AND (NodeType=0 or  NodeType IS Null)";
        }
        return null;
    }
}
