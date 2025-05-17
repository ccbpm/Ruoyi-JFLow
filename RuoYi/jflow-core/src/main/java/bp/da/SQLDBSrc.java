package bp.da;

import bp.sys.CCBPMRunModel;
import bp.web.WebUser;

import java.util.Hashtable;

public class SQLDBSrc {
    public SQLDBSrc() { }

    public static String Gener_DBSrc(String mark, Hashtable ht) throws Exception {
        String key = null;
        if (ht != null && ht.containsKey("Key") == true)
            key = ht.get("Key").toString();

        switch (mark)
        {
            case "DBSrc_EnumMain": //  //枚举列表
                if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                    return "SELECT No,Name FROM Sys_EnumMain WHERE Name!='' ";
                return "SELECT No,Name FROM Sys_EnumMain WHERE OrgNo='" + WebUser.getOrgNo() + "' AND Name!='' ";
            default:
                break;
        }

        //字典
        if (mark.equals("DBSrc_SQLSFTable") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No, Name, FK_SFDBSrc as GroupNo FROM Sys_SFTable";
            return "SELECT No,Name, FK_SFDBSrc as GroupNo FROM Sys_SFTable WHERE OrgNo='" + WebUser.getOrgNo() + "' ";
        }

        //字典
        if (mark.equals("DBSrc_SFTableSysDict") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return " SELECT No, Name, FK_SFDBSrc as GroupNo FROM Sys_SFTable WHERE DBSrcType='SysDict' ";
            return "SELECT No,Name, FK_SFDBSrc as GroupNo FROM Sys_SFTable WHERE OrgNo='" + WebUser.getOrgNo() + "' AND DBSrcType='SysDict'  ";
        }
        //数据源
        if (mark.equals("DBSrc_SFTable") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name FROM Sys_SFTable";
            else
                return "SELECT No,Name FROM Sys_SFTable  WHERE OrgNo='" + WebUser.getOrgNo() + "'";
        }
        //枚举
        if (mark.equals("DBSrc_Enums") == true)
        {
            if (bp.difference.SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
                return "SELECT No,Name FROM Sys_EnumMain";
            else
                return "SELECT No,Name FROM Sys_EnumMain WHERE OrgNo='" + WebUser.getOrgNo() + "'";
        }
        //数据源
        if (mark.equals("DBSrc_DBSrc") == true)
        {
            return "SELECT No,Name FROM Sys_SFDBSrc ";
        }
        //查询
        if (mark.equals("DBSrc_DBSFSearch") == true)
        {
            return "SELECT No,Name, FK_SFDBSrc FROM Sys_SFSearch";
        }
        //过程
        if (mark.equals("DBSrc_SFProc") == true)
        {
            return "SELECT No,Name, FK_SFDBSrc FROM Sys_SFProc";
        }
        //节点属性 绑定字典 AccepterRoleBindSFTable 请选择字典
        if (mark.equals("DBSrc_BindSFTable") == true)
        {
            return "SELECT No,Name FROM Sys_SFTable WHERE CodeStruct=0";
        }
        //ARWebAPI 绑定WebAPI
        if (mark.equals("DBSrc_WebAPISFTable") == true)
        {
            return "SELECT No,Name From Sys_SFTable WHERE DBSrcType='WebAPI'";
        }
        //表单条件 CondFrmEnum const 操作的值.
        if (mark.equals("DBSrc_CondFrmEnum") == true)
        {
            return "SELECT IntKey as No, Lab as Name FROM Sys_Enum WHERE EnumKey='" + ht.get("Tag1") + "' Order by IntKey";
        }
        //表单条件 CondFrmEnumString const 操作的值.
        if (mark.equals("DBSrc_CondFrmEnumString") == true)
        {
            return "SELECT StrKey as No, Lab as Name FROM Sys_Enum WHERE EnumKey='" + ht.get("Tag1") + "' Order by IntKey";
        }
        //请选择一个字典表 MapExt 请选择字典
        if (mark.equals("DBSrc_MapExtSelectSFTable") == true)
        {
            return "SELECT No,Name FROM Sys_SFTable WHERE IsPara='" + ht.get("isPara") + "'  AND No!='Blank' AND CodeStruct='" + ht.get("codeStruct") + "' ";
        }
        //查询
        if (mark.equals("DBSrc_SFSearch") == true)
        {
            return "SELECT No,Name FROM Sys_SFSearch";
        }
        //模板导入 GPN_FrmExpImp 导入表结构
        if (mark.equals("DBSrc_SelectItemsByList") == true)
        {
            return "SELECT No, Name FROM Sys_SFDBSrc WHERE 1=1 AND DBSrcType!='WebApi'";
        }
        //级联下拉框 GPEActiveDDLSFTable 查询出来有参数的字典
        if (mark.equals("DBSrc_ActiveDDLSFTable") == true)
        {
            return "SELECT No,Name FROM Sys_SFTable WHERE IsPara=1 ";
        }
        //级联下拉框 GPEAutoFullDDLSFTable 填充过滤 字典表
        if (mark.equals("DBSrc_ActiveDDLDoc") == true)
        {
            return "SELECT No,Name FROM Sys_SFTable WHERE No!='Blank' AND IsPara=0 ";
        }
        //填充从表 FullDDLSFTable 查询
        if (mark.equals("DBSrc_FullDDL") == true)
        {
            return "SELECT No, Name FROM Sys_SFTable WHERE IsPara=1 or IsPara=0  ";
        }
        //单实体平铺 PopList 字典表(有参1)
        if (mark.equals("DBSrc_PopList") == true)
        {
            return "SELECT No,Name FROM Sys_SFTable WHERE No!='Blank' AND CodeStruct=0 ";
        }
        throw new Exception("err@没有判断的标记:" + mark);
    }
}
