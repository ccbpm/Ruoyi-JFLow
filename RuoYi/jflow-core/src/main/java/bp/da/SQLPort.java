package bp.da;

import bp.sys.CCBPMRunModel;
import bp.web.WebUser;

import java.util.Hashtable;

public class SQLPort {
    public SQLPort()
    {
    }
    public static String Gener_Port(String mark, Hashtable ht)
    {
        String key = null;
        if (ht != null && ht.containsKey("Key") == true)
            key = ht.get("Key").toString();

        //部门树懒加载。
        if (mark.equals("Port_DeptLazily") == true)
        {
            return "SELECT No,Name,ParentNo FROM Port_Dept WHERE ParentNo='" + key + "' OR No='" + key + "' Order By Idx ";
        }
        //根据部门获得人员
        if (mark.equals("Port_EmpsByDeptNo") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return " SELECT B.No  AS No,B.Name AS Name FROM port_deptemp a, Port_Emp B  where A.FK_Emp=B.No and A.FK_Dept ='" + key + "' Order By B.Idx ";
            else
                return "SELECT UserID as No,Name FROM Port_Emp WHERE FK_Dept='" + key + "' Order By Idx ";
        }
        //搜索人员账号
        if (mark.equals("Port_EmpSearchKey") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return " SELECT B.No,B.Name FROM port_deptemp a, Port_Emp B WHERE A.FK_Emp=B.No AND Name LIKE '%" + key + "%' Order By B.Idx  ";
            else
                return "SELECT UserID as No,Name FROM Port_Emp WHERE Name LIKE '%" + key + "%' AND OrgNo='" + WebUser.getOrgNo() + "' Order By Idx ";
        }
        //部门
        if (mark.equals("Port_Depts") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name,ParentNo FROM Port_Dept Order by Idx ";
            else
                return "SELECT No,Name,ParentNo FROM Port_Dept WHERE OrgNo='" + WebUser.getOrgNo() + "' Order by Idx ";
        }
        //人员信息
        if (mark.equals("Port_Emps") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name,FK_Dept GroupNo FROM Port_Emp Order By Idx ";
            else
                return "SELECT No,Name,FK_Dept GroupNo FROM Port_Emp WHERE OrgNo='" + WebUser.getOrgNo() + "' Order by Idx ";
        }
        //岗位类型
        if (mark.equals("Port_StationTypes"))
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No, Name FROM Port_StationType Order by Idx ";
            else
                return "SELECT No,Name FROM Port_StationType WHERE OrgNo = '" + WebUser.getOrgNo() + "' Order by Idx ";
        }
        //岗位
        if (mark.equals("Port_Stations"))
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name,FK_StationType AS GroupNo FROM Port_Station Order By Idx ";
            else
                return "SELECT No,Name,FK_StationType AS GroupNo FROM Port_Station WHERE OrgNo= '" + WebUser.getOrgNo() + "' Order By Idx ";
        }
        //组织列表
        if (mark.equals("Port_Orgs"))
        {
            return "SELECT No,Name FROM Port_Org WHERE No != '" + WebUser.getOrgNo() + "'";
        }
        //人员信息 GenerListEn  beforeInsert
        if (mark.equals("Port_EmpByDept"))
        {
            return "SELECT No,Name,Tel,Email FROM Port_Emp WHERE FK_Dept='"+WebUser.getDeptNo()+"'";
        }
        //组织列表   流程复制 选择租户
        if (mark.equals("Port_SelectOrgs"))
        {
            return "SELECT No,Name FROM port_org WHERE ParentNo = 100";
        }
        //设置二级管理员 GPN_Adminer 选择管理员
        if (mark.equals("Port_SelectAdminer"))
        {
            return "SELECT No,Name,ParentNo FROM Port_Dept WHERE ParentNo=='" + key + "' OR No='" + key + "' UNION  SELECT No,Name,ParentNo FROM Port_Dept WHERE ParentNo IN ( SELECT No FROM Port_Dept WHERE ParentNo='" + key + "' OR No='" + key +"'";
        }
        //设置二级管理员 GPN_Adminer 选择管理员
        if (mark.equals("Port_AdminerEmpByDept"))
        {
            return "SELECT No,Name FROM Port_Emp B WHERE FK_Dept='" + key + "'";
        }

        //查看人员 GL_StationRole
        if (mark.equals("Port_StationRole"))
        {
            return "SELECT c.Name AS DeptName, b.Name FROM port_deptempstation a , port_emp b, port_dept c WHERE a.FK_Emp = b.No AND a.FK_Dept=c.No  AND a.FK_Station = '" + key + "' ORDER BY b.Name";
        }
        //查看人员 GL_StationRole
        if (mark.equals("Port_TreeDept"))
        {
            return "SELECT No,Name,ParentNo FROM Port_Dept Order By ParentNo, Idx";
        }
        return null;
    }

}
