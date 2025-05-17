package bp.wf;

import bp.da.*;
import bp.difference.StringHelper;
import bp.difference.SystemConfig;
import bp.en.ClassFactory;
import bp.en.Entity;
import bp.port.*;
import bp.sys.*;
import bp.tools.BCryptUtils;
import bp.tools.DateUtils;
import bp.web.WebUser;
import bp.wf.data.CH;
import bp.wf.data.GenerWorkFlowViewNY;
import bp.wf.template.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Install {

    /// <summary>
    /// 检查是否可以安装驰骋BPM系统
    /// </summary>
    /// <returns></returns>
    /**
     检查是否可以安装驰骋BPM系统
     @return
     */
    public static boolean IsCanInstall() throws Exception {
        if (SystemConfig.getRunOnPlant().equals("JFlow") == true)
        {
            if (SystemConfig.getAppCenterDSN().contains("/" + SystemConfig.getAppCenterDBDatabase() + "?") == false)
                new RuntimeException("数据库配置错误，配置的数据库名称必须包含在链接串里面。");
        }

        String sql = "";
        String errInfo = "";
        try
        {
            errInfo = " 当前用户没有[查询系统表]的权限. ";

            if (DBAccess.IsExitsObject("AA"))
            {
                errInfo = " 当前用户没有[删除表]的权限. ";
                sql = "DROP TABLE AA";
                DBAccess.RunSQL(sql);
            }

            errInfo = " 当前用户没有[创建表]的权限. ";
            sql = "CREATE TABLE AA (OID int NOT NULL)"; //检查是否可以创建表.
            DBAccess.RunSQL(sql);

            errInfo = " 当前用户没有[插入数据]的权限. ";
            sql = "INSERT INTO AA (OID) VALUES(100)";
            DBAccess.RunSQL(sql);

            try
            {
                //检查是否可以批量执行sql.
                sql = "UPDATE AA SET OID=0 WHERE OID=1;UPDATE AA SET OID=0 WHERE OID=1;";
                DBAccess.RunSQL(sql);
            }
            catch (java.lang.Exception e)
            {
                throw new RuntimeException("err@需要让数据库链接支持批量执行SQL语句，请修改数据库链接配置：&allowMultiQueries=true");
            }

            errInfo = " 当前用户没有[update 表数据]的权限. ";
            sql = "UPDATE AA SET OID=101";
            DBAccess.RunSQL(sql);

            errInfo = " 当前用户没有[delete 表数据]的权限. ";
            sql = "DELETE FROM AA";
            DBAccess.RunSQL(sql);

            errInfo = " 当前用户没有[创建表主键]的权限. ";
            DBAccess.CreatePK("AA", "OID", SystemConfig.getAppCenterDBType());

            errInfo = " 当前用户没有[创建索引]的权限. ";
            DBAccess.CreatIndex("AA", "OID",SystemConfig.getAppCenterDBType()); //可否创建索引.

            errInfo = " 当前用户没有[查询数据表]的权限. ";
            sql = "select * from AA "; //检查是否有查询的权限.
            DBAccess.RunSQLReturnTable(sql);

            errInfo = " 当前数据库设置区分了大小写，不能对大小写敏感，如果是mysql数据库请参考 https://blog.csdn.net/ccflow/article/details/100079825 ";
            sql = "select * from aa "; //检查是否区分大小写.
            DBAccess.RunSQLReturnTable(sql);

            if (DBAccess.IsExitsObject("AAVIEW"))
            {
                errInfo = " 当前用户没有[删除视图]的权限. ";
                sql = "DROP VIEW AAVIEW";
                DBAccess.RunSQL(sql);
            }

            errInfo = " 当前用户没有[创建视图]的权限.";
            sql = "CREATE VIEW AAVIEW AS SELECT * FROM AA "; //检查是否可以创建视图.
            DBAccess.RunSQL(sql);

            errInfo = " 当前用户没有[删除视图]的权限.";
            sql = "DROP VIEW AAVIEW"; //检查是否可以删除视图.
            DBAccess.RunSQL(sql);

            errInfo = " 当前用户没有[删除表]的权限.";
            sql = "DROP TABLE AA"; //检查是否可以删除表.
            DBAccess.RunSQL(sql);

            if (SystemConfig.getAppCenterDBType() == DBType.MySQL)
            {
                try
                {
                    sql = " set @@global.sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';";
                    DBAccess.RunSQL(sql);
                }
                catch (java.lang.Exception e2)
                {
                }
            }

            if (SystemConfig.getAppCenterDBDatabase().contains("-") == true)
            {
                throw new RuntimeException("err@数据库名称不能包含 '-' 号，您可以使用 '_' .");
            }

            return true;
        }
        catch (RuntimeException ex)
        {
            if (DBAccess.IsExitsObject("AA") == true)
            {
                sql = "DROP TABLE AA";
                DBAccess.RunSQL(sql);
            }

            if (DBAccess.IsExitsObject("AAVIEW") == true)
            {
                sql = "DROP VIEW AAVIEW";
                DBAccess.RunSQL(sql);
            }

            String info = "驰骋工作流引擎 - 检查数据库安装权限出现错误:";
            info += "\t\n1. 当前登录的数据库帐号，必须有创建、删除视图或者table的权限。";
            info += "\t\n2. 必须对表有增、删、改、查的权限。 ";
            info += "\t\n3. 必须有删除创建索引主键的权限。 ";
            info += "\t\n4. 我们建议您设置当前数据库连接用户为管理员权限。 ";
            info += "\t\n ccbpm检查出来的信息如下：" + errInfo;
            info += "\t\n etc: 数据库测试异常信息:" + ex.getMessage();
            throw new RuntimeException("err@" + info);
            //  return false;
        }
    }

    /**
     安装包

     @param lang 语言
     @param demoType 0安装Demo, 1 不安装Demo.
     */
    public static void DoInstallDataBase(String lang, int demoType) throws Exception {
        boolean isInstallFlowDemo = true;
        if (demoType == 2)
        {
            isInstallFlowDemo = false;
        }


        ///#region 首先创建Port类型的表, 让admin登录.

        SFDBSrc sf = new SFDBSrc();
        sf.CheckPhysicsTable();

        FrmRB rb = new FrmRB();
        rb.CheckPhysicsTable();

        Emp portEmp = new Emp();
        portEmp.CheckPhysicsTable();


        Dept mydept = new Dept();
        mydept.CheckPhysicsTable();

        Station mySta = new Station();
        mySta.CheckPhysicsTable();

        StationType myStaType = new StationType();
        myStaType.CheckPhysicsTable();

        DeptEmp myde = new DeptEmp();
        myde.CheckPhysicsTable();

        DeptEmpStation mydes = new DeptEmpStation();
        mydes.CheckPhysicsTable();

        bp.wf.port.WFEmp wfemp = new bp.wf.port.WFEmp();
        wfemp.CheckPhysicsTable();

        if (SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single)
        {
            bp.wf.port.admingroup.Org org = new bp.wf.port.admingroup.Org();
            org.CheckPhysicsTable();

            bp.wf.port.admin2group.OrgAdminer oa = new bp.wf.port.admin2group.OrgAdminer();
            oa.CheckPhysicsTable();

            FlowSort fs = new FlowSort();
            fs.CheckPhysicsTable();

            SysFormTree ft = new SysFormTree();
            ft.CheckPhysicsTable();
        }

        if (DBAccess.IsExitsTableCol("WF_Emp", "StartFlows") == false)
        {
            String sql = "";
            //增加StartFlows这个字段
            switch (SystemConfig.getAppCenterDBType())
            {
                case Oracle:
                case DM:
                case KingBaseR3:
                case KingBaseR6:
                    sql = "ALTER TABLE  WF_EMP add StartFlows BLOB";
                    break;
                case MySQL:
                case GBASE8CByMySQL:
                case GBASE8CByOracle:
                case GBASE8A:
                    sql = "ALTER TABLE WF_Emp ADD StartFlows TEXT COMMENT '可以发起的流程'";
                    break;
                case Informix:
                    sql = "ALTER TABLE WF_Emp ADD StartFlows VARCHAR(4000) DEFAULT  NULL";
                    break;
                case PostgreSQL:
                case UX:
                case HGDB:
                case MSSQL:
                    sql = "ALTER TABLE WF_Emp ADD StartFlows Text DEFAULT  NULL";
                    break;
                default:
                    throw new RuntimeException("@没有涉及到的数据库类型");
            }
            DBAccess.RunSQL(sql);
        }

        ///#endregion 首先创建Port类型的表.


        ///#region 3, 执行基本的 sql
        String sqlscript = "";

        Emp empGPM = new Emp();
        empGPM.CheckPhysicsTable();

        DeptEmp de = new DeptEmp();
        de.CheckPhysicsTable();


        if (DBAccess.IsExitsTableCol("Port_Emp", "Pass") == false)
        {
            if (SystemConfig.getAppCenterDBType() == DBType.DM ||SystemConfig.getAppCenterDBType() == DBType.Oracle ||  SystemConfig.getAppCenterDBType() == DBType.GBASE8CByOracle)
            {
                DBAccess.RunSQL("ALTER TABLE Port_Emp ADD Pass VARCHAR(90) DEFAULT '123' NULL ");
            }
            else if (SystemConfig.getAppCenterDBType() == DBType.PostgreSQL || SystemConfig.getAppCenterDBType() == DBType.HGDB)
            {
                DBAccess.RunSQL("ALTER TABLE Port_Emp ADD Pass VARCHAR(90) DEFAULT '123' NULL ");
            }
            else
            {
                DBAccess.RunSQL("ALTER TABLE Port_Emp ADD Pass NVARCHAR(90) DEFAULT '123' NULL ");
            }
        }
        if (DBAccess.IsExitsTableCol("Port_Emp", "LeaderT") == false)
        {
            if (SystemConfig.getAppCenterDBType() == DBType.DM ||SystemConfig.getAppCenterDBType()  == DBType.Oracle||  SystemConfig.getAppCenterDBType() == DBType.GBASE8CByOracle)
            {
                DBAccess.RunSQL("ALTER TABLE Port_Emp ADD LeaderT VARCHAR(300) DEFAULT '' NULL  ");
            }
            else if (SystemConfig.getAppCenterDBType()  == DBType.PostgreSQL || SystemConfig.getAppCenterDBType()  == DBType.HGDB)
            {
                DBAccess.RunSQL("ALTER TABLE Port_Emp ADD LeaderT VARCHAR(300) DEFAULT '' NULL   ");
            }
            else if (SystemConfig.getAppCenterDBType()  == DBType.MSSQL)
            {

                DBAccess.RunSQL("ALTER TABLE [dbo].[Port_Emp] ADD [LeaderT] nvarchar(300)  DEFAULT ''  NULL");

                //添加字段注释
                DBAccess.RunSQL("EXEC sp_addextendedproperty 'MS_Description', N'直接主管','SCHEMA', N'dbo','TABLE', N'Port_Emp','COLUMN', N'LeaderT'");
            }
            // 处理人大金仓字段问题
            else if (SystemConfig.getAppCenterDBType()  == DBType.KingBaseR6)
            {
                DBAccess.RunSQL("ALTER TABLE Port_Emp ADD LeaderT NVARCHAR(300) DEFAULT '' NULL");
                //添加字段注释
                DBAccess.RunSQL("COMMENT ON COLUMN Port_Emp.LeaderT IS '直接主管'");
            }
            else
            {
                DBAccess.RunSQL("ALTER TABLE Port_Emp ADD LeaderT NVARCHAR(300) DEFAULT '' NULL  ");
            }
        }
        if (DBAccess.IsExitsTableCol("Port_Dept", "LinkMan") == false)
        {
            if (SystemConfig.getAppCenterDBType() == DBType.Oracle ||  SystemConfig.getAppCenterDBType() == DBType.GBASE8CByOracle)
            {
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD LinkMan VARCHAR(50) DEFAULT '' NULL  ");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjNo VARCHAR(50) DEFAULT '' NULL   ");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjName VARCHAR(50) DEFAULT '' NULL  ");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjShortName VARCHAR(50) DEFAULT '' NULL ");
            }
            else if (SystemConfig.getAppCenterDBType() == DBType.PostgreSQL || SystemConfig.getAppCenterDBType() == DBType.HGDB)
            {
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD LinkMan VARCHAR(50) DEFAULT '' NULL  ");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjNo VARCHAR(50) DEFAULT '' NULL  ");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjName VARCHAR(50) DEFAULT '' NULL   ");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjShortName VARCHAR(50) DEFAULT ''   ");
            }
            else if (SystemConfig.getAppCenterDBType() == DBType.MSSQL)
            {
                DBAccess.RunSQL("ALTER TABLE [dbo].[Port_Dept] ADD [LinkMan] nvarchar(50) DEFAULT '' NULL ");
                DBAccess.RunSQL("EXEC sp_addextendedproperty 'MS_Description', N'部门联络员', 'SCHEMA', N'dbo','TABLE', N'Port_Dept','COLUMN', N'LinkMan'");

                DBAccess.RunSQL("ALTER TABLE [dbo].[Port_Dept] ADD [PrjNo] nvarchar(50) DEFAULT '' NULL ");
                DBAccess.RunSQL("EXEC sp_addextendedproperty 'MS_Description', N'项目编号', 'SCHEMA', N'dbo','TABLE', N'Port_Dept','COLUMN', N'PrjNo'");

                //添加注释
                DBAccess.RunSQL("ALTER TABLE [dbo].[Port_Dept] ADD [PrjName] nvarchar(50) DEFAULT '' NULL ");
                DBAccess.RunSQL("EXEC sp_addextendedproperty 'MS_Description', N'项目名称', 'SCHEMA', N'dbo','TABLE', N'Port_Dept','COLUMN', N'PrjName'");

                DBAccess.RunSQL("ALTER TABLE [dbo].[Port_Dept] ADD [PrjShortName] nvarchar(50) DEFAULT '' NULL ");
                DBAccess.RunSQL("EXEC sp_addextendedproperty 'MS_Description', N'项目简称', 'SCHEMA', N'dbo','TABLE', N'Port_Dept','COLUMN', N'PrjShortName'");

            }
            // 处理人大金仓字段问题
            else if (SystemConfig.getAppCenterDBType() == DBType.KingBaseR6 || SystemConfig.getAppCenterDBType() == DBType.DM)
            {
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD LinkMan NVARCHAR(50) DEFAULT '' NULL  ");
                DBAccess.RunSQL("COMMENT ON COLUMN Port_Dept.LinkMan IS '部门联络员'");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjNo NVARCHAR(50) DEFAULT '' NULL ");
                DBAccess.RunSQL("COMMENT ON COLUMN Port_Dept.PrjNo IS '项目编号'");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjName NVARCHAR(50) DEFAULT '' NULL ");
                DBAccess.RunSQL("COMMENT ON COLUMN Port_Dept.PrjName IS '项目名称'");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjShortName NVARCHAR(50) DEFAULT '' NULL ");
                DBAccess.RunSQL("COMMENT ON COLUMN Port_Dept.PrjShortName IS '项目简称'");
            }
            else
            {
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD LinkMan NVARCHAR(50) DEFAULT '' NULL COMMENT '部门联络员' ");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjNo NVARCHAR(50) DEFAULT '' NULL COMMENT '项目编号' ");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjName NVARCHAR(50) DEFAULT '' NULL COMMENT '项目名称' ");
                DBAccess.RunSQL("ALTER TABLE Port_Dept ADD PrjShortName NVARCHAR(50) DEFAULT '' NULL COMMENT '项目简称' ");
            }
        }
        //初始化数据
        sqlscript = SystemConfig.getCCFlowAppPath() + "WF/Data/Install/SQLScript/Port_Inc_CH_RunModel_" + SystemConfig.getCCBPMRunModel().getValue() + ".sql";
        DBAccess.RunSQLScript(sqlscript);


        if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
        {
            Emp empAdmin = new Emp("admin");
            WebUser.setCurrentUser(empAdmin.getUserID());
            WebUser.SignInOfGener(empAdmin, "CH", false, false, null, null);
        }

        if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.SAAS)
        {
            bp.wf.Dev2Interface.Port_Login("admin", "100", null);
        }

        if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.GroupInc)
        {
            bp.wf.Dev2Interface.Port_Login("admin", "100", null);
        }

        ///#endregion 执行基本的 sql

        String info = "BP.En.Entity";
        ArrayList al = ClassFactory.GetObjects(info);


        ///#region 先创建表，否则列的顺序就会变化.
        FlowExt fe = new FlowExt();
        fe.CheckPhysicsTable();

        NodeExt ne = new NodeExt();
        ne.CheckPhysicsTable();

        MapDtl mapdtl = new MapDtl();
        mapdtl.CheckPhysicsTable();

        MapData mapData = new MapData();
        mapData.CheckPhysicsTable();

        SysEnum sysenum = new SysEnum();
        sysenum.CheckPhysicsTable();

        NodeWorkCheck fwc = new NodeWorkCheck();
        fwc.CheckPhysicsTable();

        MapAttr attr = new MapAttr();
        attr.CheckPhysicsTable();

        GenerWorkFlow gwf = new GenerWorkFlow();
        gwf.CheckPhysicsTable();

        CH ch = new CH();
        ch.CheckPhysicsTable();


        ///#endregion 先创建表，否则列的顺序就会变化.


        ///#region 1, 创建or修复表
        for (Object obj : al)
        {
            Entity en = obj instanceof Entity ? (Entity)obj : null;
            if (en == null)
            {
                continue;
            }

            //获得类名.
            String clsName = en.toString();
            if (DataType.IsNullOrEmpty(clsName) == true)
            {
                continue;
            }

            if (clsName.contains("FlowSheet") == true)
            {
                continue;
            }
            if (clsName.contains("NodeSheet") == true)
            {
                continue;
            }
            if (clsName.contains("FlowFormTree") == true)
            {
                continue;
            }

            //不安装CCIM的表.
            if (clsName.contains("BP.CCIM"))
            {
                continue;
            }

            //抽象的类不允许创建表.
            switch (clsName.toUpperCase())
            {
                case "BP.WF.STARTWORK":
                case "BP.WF.WORK":
                case "BP.WF.GESTARTWORK":
                case "BP.EN.GENONAME":
                case "BP.EN.GETREE":
                case "BP.WF.GERpt":
                case "BP.WF.GEENTITY":
                case "BP.SYS.TSENTITYNONAME":
                    continue;
                default:
                    break;
            }

            if (isInstallFlowDemo == false)
            {
                /*如果不安装demo.*/
                if (clsName.contains("BP.CN") || clsName.contains("BP.Demo"))
                {
                    continue;
                }
            }

            String table = null;
            try
            {
                table = en.getEnMap().getPhysicsTable();
                if (table == null)
                {
                    continue;
                }
            }
            catch (java.lang.Exception e)
            {
                continue;
            }

            try
            {
                switch (table)
                {
                    case "WF_EmpWorks":
                    case "WF_GenerEmpWorkDtls":
                    case "WF_GenerEmpWorks":
                        continue;
                    case "Sys_Enum":
                        en.CheckPhysicsTable();
                        break;
                    default:
                        en.CheckPhysicsTable();
                        break;
                }
                en.CheckPhysicsTable();
            }
            catch (java.lang.Exception e2)
            {
            }
        }

        ///#endregion 修复


        ///#region 2, 注册枚举类型 SQL
        // 2, 注册枚举类型。
        bp.sys.xml.EnumInfoXmls xmls = new bp.sys.xml.EnumInfoXmls();
        xmls.RetrieveAll();
        for (bp.sys.xml.EnumInfoXml xml : xmls.ToJavaList())
        {
            SysEnums ses = new SysEnums();
            int count = ses.RetrieveByAttr(SysEnumAttr.EnumKey, xml.getKey());
            if (count > 0)
            {
                continue;
            }
            ses.RegIt(xml.getKey(), xml.getVals());
        }

        ///#endregion 注册枚举类型


        //region 重新构建视图.
        ReCreateView();
        //endregion 重新构建视图.

        ///#region 5, 初始化数据.
        if (DBAccess.IsExitsObject("Demo_BanJi") == false)
        {
            sqlscript = "CREATE TABLE Demo_BanJi( ";
            sqlscript += "No varchar(20),";
            sqlscript += "Name varchar(50),";
            sqlscript += "BZR varchar(100)";
            sqlscript += ") ";
            DBAccess.RunSQL(sqlscript);
        }

        if (DBAccess.IsExitsObject("Demo_Student") == false)
        {
            sqlscript = "CREATE TABLE Demo_Student( ";
            sqlscript += "No varchar(50),";
            sqlscript += "Name varchar(50),";
            sqlscript += "PWD varchar(50),";
            sqlscript += "Addr varchar(50),";
            sqlscript += "Tel varchar(50),";
            sqlscript += "Email varchar(50),";
            sqlscript += "FK_BanJi varchar(50),";
            if(SystemConfig.getAppCenterDBType() == DBType.PostgreSQL) {
                sqlscript += "Age integer,";
                sqlscript += "XB integer,";
                sqlscript += "ZZMM integer,";
            } else {
                sqlscript += "Age int(11),";
                sqlscript += "XB int(11),";
                sqlscript += "ZZMM int(11)";
            }
            sqlscript += ") ";
            DBAccess.RunSQL(sqlscript);
        }
        if (DBAccess.IsExitsObject("Demo_KeMu") == false)
        {
            sqlscript = "CREATE TABLE Demo_KeMu( ";
            sqlscript += "No varchar(20),";
            sqlscript += "Name varchar(50)";
            sqlscript += ") ";
            DBAccess.RunSQL(sqlscript);
        }
        if (DBAccess.IsExitsObject("Demo_PQ") == false)
        {
            sqlscript = "CREATE TABLE Demo_PQ( ";
            sqlscript += "No varchar(20),";
            sqlscript += "Name varchar(50)";
            sqlscript += ") ";
            DBAccess.RunSQL(sqlscript);
        }
        if (DBAccess.IsExitsObject("Demo_ShengFen") == false)
        {
            sqlscript = "CREATE TABLE Demo_ShengFen( ";
            sqlscript += "No varchar(20),";
            sqlscript += "Name varchar(20),";
            sqlscript += "Names varchar(20),";
            sqlscript += "JC varchar(20),";
            sqlscript += "FK_PQ varchar(50)";
            sqlscript += ") ";
            DBAccess.RunSQL(sqlscript);
        }
        if (DBAccess.IsExitsObject("Demo_City") == false)
        {
            sqlscript = "CREATE TABLE Demo_City( ";
            sqlscript += "No varchar(20),";
            sqlscript += "Name varchar(100),";
            sqlscript += "Names varchar(100),";
            if(SystemConfig.getAppCenterDBType() == DBType.PostgreSQL) {
                sqlscript += "Grade integer,";
            } else {
                sqlscript += "Grade int(11),";
            }
            sqlscript += "ShengFen varchar(100),";
            sqlscript += "FK_PQ varchar(100)";
            sqlscript += ") ";
            DBAccess.RunSQL(sqlscript);
        }
        if (DBAccess.IsExitsObject("Demo_Product") == false)
        {
            sqlscript = "CREATE TABLE Demo_Product( ";
            sqlscript += "No varchar(20),";
            sqlscript += "Name varchar(100),";
            sqlscript += "GuiGe varchar(100)"; //规格.
            sqlscript += ") ";
            DBAccess.RunSQL(sqlscript);
        }
        if (DBAccess.IsExitsObject("Demo_Customer") == false)
        {
            sqlscript = "CREATE TABLE Demo_Customer( ";
            sqlscript += "No varchar(20),";
            sqlscript += "Name varchar(100),";
            sqlscript += "Linker varchar(100),";
            sqlscript += "Tel varchar(100),";
            sqlscript += "Addr varchar(100),";
            sqlscript += "RDT varchar(100)";
            sqlscript += ") ";
            DBAccess.RunSQL(sqlscript);
        }
        sqlscript =  bp.difference.SystemConfig.getPathOfData() + "Install/SQLScript/InitPublicData.sql";
        DBAccess.RunSQLScript(sqlscript);
        ///#endregion 初始化数据

        ///#region 6, 生成临时的 wf_emp 数据。
        if (isInstallFlowDemo == true)
        {
            Emps emps = new Emps();
            emps.RetrieveAllFromDBSource();
            int i = 0;
            for (Emp emp : emps.ToJavaList())
            {
                i++;
                bp.wf.port.WFEmp wfEmp = new bp.wf.port.WFEmp();
                wfEmp.Copy(emp);
                wfEmp.setNo(emp.getUserID());

				/* if (wfEmp.Email.length() == 0)
				     wfEmp.Email = emp.getUserID() + "@ccbpm.cn";*/

                if (wfEmp.getTel().length() == 0)
                {
                    wfEmp.setTel("82374939-6" + StringHelper.padLeft(String.valueOf(i), 2, '0'));
                }

                if (wfEmp.getIsExits())
                {
                    wfEmp.Update();
                }
                else
                {
                    wfEmp.Insert();
                }
            }

            // 生成简历数据.
            for (Emp emp : emps.ToJavaList())
            {
                for (int myIdx = 0; myIdx < 6; myIdx++)
                {
                    String sql = "";
                    sql = "INSERT INTO Demo_Resume (OID,RefPK,NianYue,GongZuoDanWei,ZhengMingRen,BeiZhu,QT) ";
                    sql += "VALUES(" + DBAccess.GenerOID("Demo_Resume") + ",'" + emp.getUserID() + "','200" + myIdx + "-01','成都.驰骋" + myIdx + "公司','张三','表现良好','其他-" + myIdx + "无')";
                    DBAccess.RunSQL(sql);
                }
            }

            DataTable dtStudent = DBAccess.RunSQLReturnTable("SELECT No FROM Demo_Student");
            for (DataRow dr : dtStudent.Rows)
            {
                String no = dr.getValue(0).toString();
                for (int myIdx = 0; myIdx < 6; myIdx++)
                {
                    String sql = "";
                    sql = "INSERT INTO Demo_Resume (OID,RefPK,NianYue,GongZuoDanWei,ZhengMingRen,BeiZhu,QT) ";
                    sql += "VALUES(" + DBAccess.GenerOID("Demo_Resume") + ",'" + no + "','200" + myIdx + "-01','成都.驰骋" + myIdx + "公司','张三','表现良好','其他-" + myIdx + "无')";
                    DBAccess.RunSQL(sql);
                }
            }
            GenerWorkFlowViewNY ny = new GenerWorkFlowViewNY();
            ny.CheckPhysicsTable();
            // 生成年度月份数据.
            String sqls = "";
            LocalDateTime dtNow = LocalDateTime.now();
            for (int num = 0; num < 12; num++)
            {
                sqls = "@ INSERT INTO Pub_NY (No,Name) VALUES ('" + DateUtils.format(new Date(), "yyyy-MM")+ "','" + DateUtils.format(new Date(), "yyyy-MM") + "')  ";
                dtNow = dtNow.plusMonths(1);
            }
            DBAccess.RunSQLs(sqls);
        }

        ///#endregion 初始化数据


        ///#region 7, 装载 demo.flow
        if (isInstallFlowDemo == true && SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
        {
            Emp emp = new Emp("admin");
            WebUser.SignInOfGener(emp, "CH", false, false, null, null);
            bp.sys.base.Glo.WriteLineInfo("开始装载模板...");
            String msg = "";

            //装载数据模版.
            bp.wf.dts.LoadTemplete l = new bp.wf.dts.LoadTemplete();
            Object tempVar = l.Do();
            msg = tempVar instanceof String ? (String)tempVar : null;
        }

        if (isInstallFlowDemo == false && SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
        {
            //创建一个空白的流程，不然，整个结构就出问题。
            DBAccess.RunSQL("DELETE FROM WF_FlowSort ");
            FlowSort fs = new FlowSort();
            fs.setName("流程树");
            fs.setNo("1");
            fs.setParentNo("0");
            fs.Insert();

            fs = new FlowSort();
            fs.setNo("101");
            fs.setParentNo("1");
            fs.setName("日常办公类");
            fs.Insert();

            //加载一个模版,不然用户不知道如何新建流程.
            TemplateGlo.LoadFlowTemplate(fs.getNo(), SystemConfig.getPathOfData() + "Install/QingJiaFlowDemoInit.xml", ImpFlowTempleteModel.AsTempleteFlowNo);

            Flow fl = new Flow("001");
            fl.DoCheck(); //做一下检查.

            fs.setNo("102");
            fs.setParentNo("1");
            fs.setName("财务类");
            fs.Insert();

            fs.setNo("103");
            fs.setParentNo("1");
            fs.setName("人力资源类");
            fs.Insert();

            DBAccess.RunSQL("DELETE FROM Sys_FormTree ");
            SysFormTree ftree = new SysFormTree();
            ftree.setName("表单树");
            ftree.setNo("1");
            ftree.setParentNo("0");
            ftree.Insert();

            SysFormTree subFrmTree = new SysFormTree();
            subFrmTree.setName("流程独立表单");
            subFrmTree.setParentNo("1");
            subFrmTree.setNo("101");
            subFrmTree.Insert();

            subFrmTree = new SysFormTree();
            ;
            subFrmTree.setNo("102");
            subFrmTree.setName("常用信息管理");
            subFrmTree.setParentNo("1");
            subFrmTree.Insert();

            subFrmTree = new SysFormTree();
            ;
            subFrmTree.setName("常用单据");
            subFrmTree.setNo("103");
            subFrmTree.setParentNo("1");
            subFrmTree.Insert();
        }

        ///#endregion 装载demo.flow


        ///#region 增加图片签名
        if (isInstallFlowDemo == true)
        {
            try
            {
                //增加图片签名
                bp.wf.dts.GenerSiganture gs = new bp.wf.dts.GenerSiganture();
                gs.Do();
            }
            catch (java.lang.Exception e3)
            {
            }
        }

        ///#endregion 增加图片签名.

        //生成拼音，以方便关键字查找.
        bp.wf.dts.GenerPinYinForEmp pinyin = new bp.wf.dts.GenerPinYinForEmp();
        pinyin.Do();


        ///#region 执行补充的sql, 让外键的字段长度都设置成100.
        DBAccess.RunSQL("UPDATE Sys_MapAttr SET maxlen=100 WHERE LGType=2 AND MaxLen<100");
        DBAccess.RunSQL("UPDATE Sys_MapAttr SET maxlen=100 WHERE KeyOfEn='FK_Dept'");

        ///#endregion 执行补充的sql, 让外键的字段长度都设置成100.


        ///#region 如果是第一次运行，就执行检查。
        if (isInstallFlowDemo == true)
        {
            Flows fls = new Flows();
            fls.RetrieveAllFromDBSource();
            for (Flow fl : fls.ToJavaList())
            {
                try
                {
                    fl.DoCheck();
                }
                catch (RuntimeException ex)
                {
                    Log.DebugWriteError(ex.getMessage());
                }
            }
        }

        ///#endregion 如果是第一次运行，就执行检查。


        ///#region 增加大文本字段列.
        try
        {
            if (DBAccess.IsExitsTableCol("Sys_MapData", "HtmlTemplateFile") == false)
            {
                String sql = "ALTER TABLE Sys_MapData ADD  HtmlTemplateFile TEXT ";
                if (SystemConfig.getAppCenterDBType() == DBType.Oracle){
                    sql="ALTER TABLE Sys_MapData ADD  HtmlTemplateFile CLOB ";
                }

                DBAccess.RunSQL(sql);
            }
            DBAccess.GetBigTextFromDB("Sys_MapData", "No", "001", "HtmlTemplateFile");
        }
        catch (Exception e)
        {
            throw new Exception("err@增加大文本字段列时出现异常;@异常信息:" + e.getMessage());
        }

        ///#endregion 增加大文本字段列.

        ///#region 检查工作处理器的ShiftTo和ShiftNote字段是否存在
        try
        {
            if (DBAccess.IsExitsTableCol("WF_WorkOpt", "ShiftTo") == false)
            {
                String sql = "ALTER TABLE WF_WorkOpt ADD  ShiftTo VARCHAR(100) ";
                DBAccess.RunSQL(sql);
            }
            if (DBAccess.IsExitsTableCol("WF_WorkOpt", "ShiftNote") == false)
            {
                String sql = "ALTER TABLE WF_WorkOpt ADD  ShiftNote  text ";
                if (SystemConfig.getAppCenterDBType() == DBType.Oracle){
                    sql="ALTER TABLE WF_WorkOpt ADD  HtmlTemplateFile CLOB ";
                }
                DBAccess.RunSQL(sql);
            }
        }
        catch (Exception e)
        {
            throw new Exception("err@检查工作处理器表的ShiftTo和ShiftNote字段时出现异常;@异常信息:" + e.getMessage());
        }
        ///#endregion 检查工作处理器的ShiftTo和ShiftNote字段是否存在

        ///#region 检查菜单表的UrlPath，path，Alias，IframeOpenType等字段是否存在
        try
        {
            switch(SystemConfig.getAppCenterDBType()){
                case PostgreSQL:
                case MSSQL:
                case DM:
                case Oracle:
                case KingBaseR3:
                case KingBaseR6:
                case KingBaseR8:
                case GBASE8CByOracle:
                    if (DBAccess.IsExitsTableCol("GPM_Menu", "UrlPath") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  UrlPath VARCHAR(500)");

                    if (DBAccess.IsExitsTableCol("GPM_Menu", "path") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  path VARCHAR(50)");

                    if (DBAccess.IsExitsTableCol("GPM_Menu", "Alias") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  Alias VARCHAR(500)");

                    if (DBAccess.IsExitsTableCol("GPM_Menu", "IframeOpenType") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  IframeOpenType VARCHAR(50)");
                    if (DBAccess.IsExitsTableCol("GPM_Menu", "IframeOpenTypeT") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  IframeOpenTypeT VARCHAR(200)");
                    if (DBAccess.IsExitsTableCol("GPM_Menu", "IframeOpenTypeT") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  IframeOpenTypeT VARCHAR(200)");
                    if (DBAccess.IsExitsTableCol("GPM_Menu", "ModuleNoT") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  ModuleNoT VARCHAR(200)");
                    if (DBAccess.IsExitsTableCol("GPM_Menu", "EnName") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  EnName VARCHAR(300)");
                    break;
                default:
                    if (DBAccess.IsExitsTableCol("GPM_Menu", "UrlPath") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  UrlPath VARCHAR(500) COMMENT 'Vue文件路径'");
                    if (DBAccess.IsExitsTableCol("GPM_Menu", "path") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  path VARCHAR(50) COMMENT 'path'");
                    if (DBAccess.IsExitsTableCol("GPM_Menu", "Alias") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  Alias VARCHAR(500) COMMENT '别名'");
                    if (DBAccess.IsExitsTableCol("GPM_Menu", "IframeOpenType") == false)
                        DBAccess.RunSQL( "ALTER TABLE GPM_Menu ADD  IframeOpenType VARCHAR(50) COMMENT 'iframe打开方式'");

                    if (DBAccess.IsExitsTableCol("GPM_Menu", "IframeOpenTypeT") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  IframeOpenTypeT VARCHAR(200) COMMENT 'iframe打开方式'");

                    if (DBAccess.IsExitsTableCol("GPM_Menu", "IframeOpenTypeT") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  IframeOpenTypeT VARCHAR(200) COMMENT 'iframe打开方式'");

                    if (DBAccess.IsExitsTableCol("GPM_Menu", "ModuleNoT") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  ModuleNoT VARCHAR(200) COMMENT '隶属模块编号'");

                    if (DBAccess.IsExitsTableCol("GPM_Menu", "EnName") == false)
                        DBAccess.RunSQL("ALTER TABLE GPM_Menu ADD  EnName VARCHAR(300) COMMENT '对应的实体类'");
                    break;
            }

        }
        catch (Exception e)
        {
            throw new Exception("err@检查菜单表的字段时出现异常;@异常信息:" + e.getMessage());
        }
        ///#endregion 检查菜单表的UrlPath，path，Alias，IframeOpenType等字段是否存在

        ///#region 检查表单树的字段是否存在
        try
        {
            if (DBAccess.IsExitsTableCol("Sys_FormTree", "ShortName") == false)
            {
                String sql = "ALTER TABLE Sys_FormTree ADD  ShortName VARCHAR(200)";
                if(supportsComments())
                    sql += " COMMENT '简称'";
                DBAccess.RunSQL(sql);
            }
            if (DBAccess.IsExitsTableCol("Sys_FormTree", "DomainExt") == false)
            {
                String sql = "ALTER TABLE Sys_FormTree ADD  DomainExt  VARCHAR(100) ";
                if(supportsComments())
                    sql += " COMMENT '域/系统编号'";
                DBAccess.RunSQL(sql);
            }
        }
        catch (Exception e)
        {
            throw new Exception("err@检查表单树的字段时出现异常;@异常信息:" + e.getMessage());
        }
        ///#endregion 检查表单树的字段是否存在

        ///#region 检查流程类别表的字段是否存在
        try
        {
            if (DBAccess.IsExitsTableCol("Sys_FormTree", "DirType") == false)
            {
                String sql = "ALTER TABLE Sys_FormTree ADD  DirType INTEGER";
                if(supportsComments())
                    sql += " COMMENT '目录类型'";
                DBAccess.RunSQL(sql);
            }
        }
        catch (Exception e)
        {
            throw new Exception("err@检查流程类别表的字段时出现异常;@异常信息:" + e.getMessage());
        }
        ///#endregion 检查流程类别表的字段是否存在

        ///#region 检查表单共享、流程共享字段是否存在
        try
        {
            //流程共享
            if (DBAccess.IsExitsTableCol("WF_Flow", "ShareSln") == false)
            {
                String sql = "ALTER TABLE WF_Flow ADD  ShareSln INTEGER";
                if(supportsComments())
                    sql += " COMMENT '流程共享方案'";
                DBAccess.RunSQL(sql);
            }
            //表单共享
            if (DBAccess.IsExitsTableCol("Sys_MapData", "ShareSln") == false)
            {
                String sql = "ALTER TABLE Sys_MapData ADD  ShareSln INTEGER";
                if(supportsComments())
                    sql += " COMMENT '表单共享方案'";
                DBAccess.RunSQL(sql);
            }
            //显示列
            if (DBAccess.IsExitsTableCol("Sys_MapData", "ShowColModel") == false)
            {
                String sql = "ALTER TABLE Sys_MapData ADD  ShowColModel INTEGER";
                if(supportsComments())
                    sql += " COMMENT '显示列'";
                DBAccess.RunSQL(sql);
            }
        }
        catch (Exception e)
        {
            throw new Exception("err@检查表单共享、流程共享字段时出现异常;@异常信息:" + e.getMessage());
        }
        ///#endregion 检查表单共享、流程共享字段是否存在

        ///#region 检查WF_Node
        try
        {
            if (DBAccess.IsExitsTableCol("WF_Node", "CancelNodes") == false)
            {
                String sql = "ALTER TABLE WF_Node ADD  CancelNodes VARCHAR(200)";
                if(supportsComments())
                    sql += " COMMENT '可撤销的节点'";
                DBAccess.RunSQL(sql);
            }
        }
        catch (Exception e)
        {
            throw new Exception("err@检查WF_Node表中[CancelNodes]字段时出现异常;@异常信息:" + e.getMessage());
        }
        ///#endregion 检查WF_Node

        ///#region 检查SQL、JS、TS字段是否存在
        try
        {
            //SQL脚本内容
            if (DBAccess.IsExitsTableCol("Frm_Method", "SQLScript") == false)
            {
                String sql = "ALTER TABLE Frm_Method ADD  SQLScript TEXT";
                if(SystemConfig.getAppCenterDBType() == DBType.Oracle || SystemConfig.getAppCenterDBType() == DBType.GBASE8CByOracle)
                    sql="ALTER TABLE Frm_Method ADD  SQLScript CLOB";
                if(supportsComments())
                    sql += " COMMENT 'SQL脚本内容'";
                DBAccess.RunSQL(sql);
            }
            //JS脚本内容
            if (DBAccess.IsExitsTableCol("Frm_Method", "JSScript") == false)
            {
                String sql = "ALTER TABLE Frm_Method ADD  JSScript TEXT";
                if(SystemConfig.getAppCenterDBType() == DBType.Oracle || SystemConfig.getAppCenterDBType() == DBType.GBASE8CByOracle)
                    sql="ALTER TABLE Frm_Method ADD  JSScript CLOB ";
                if(supportsComments())
                    sql += " COMMENT 'JS脚本内容'";
                DBAccess.RunSQL(sql);
            }
            //TS脚本内容
            if (DBAccess.IsExitsTableCol("Frm_Method", "TSScript") == false)
            {
                String sql = "ALTER TABLE Frm_Method ADD  TSScript TEXT";
                if(SystemConfig.getAppCenterDBType() == DBType.Oracle || SystemConfig.getAppCenterDBType() == DBType.GBASE8CByOracle)
                    sql="ALTER TABLE Frm_Method ADD  TSScript CLOB";
                if(supportsComments())
                    sql += " COMMENT 'TS脚本内容'";
                DBAccess.RunSQL(sql);
            }
            //执行按钮
            if (DBAccess.IsExitsTableCol("Frm_Method", "BtnDoneText") == false)
            {
                String sql = "ALTER TABLE Frm_Method ADD  BtnDoneText VARCHAR(100)";
                if(supportsComments())
                    sql += " COMMENT '执行按钮'";
                DBAccess.RunSQL(sql);
            }
        }
        catch (Exception e)
        {
            throw new Exception("err@检查检查SQL、JS、TS字段时出现异常;@异常信息:" + e.getMessage());
        }
        ///#endregion 检查SQL、JS、TS字段是否存在

        //#region 处理密码加密.
        String pass = SystemConfig.getUserDefaultPass();
        if (SystemConfig.getIsPasswordEncryptionType().equals("1")){
            pass = bp.tools.MD5Utill.MD5Encode(pass, "UTF8");//bp.tools.Encodes.encodeBase64(pass);
        }else if(SystemConfig.getIsPasswordEncryptionType().equals("2")){
            pass= BCryptUtils.hashpw(pass);
        }

        DBAccess.RunSQL("UPDATE Port_Emp SET Pass='" + pass + "'");
        //#endregion 处理密码加密.
    }

    /**
     * 支持字段注释的数据库类型
     * @return
     */
    private static boolean supportsComments() {
        DBType dbType = SystemConfig.getAppCenterDBType();
        return dbType == DBType.MySQL || dbType == DBType.GBASE8CByMySQL || dbType == DBType.GBASE8CByOracle || dbType == DBType.GBASE8A;
    }

    public static void ReCreateView() throws Exception {
        //抄送规则.
        bp.wf.template.CCRole cc = new CCRole();
        cc.CheckPhysicsTable();

        bp.wf.CCList list = new CCList();
        list.CheckPhysicsTable();

        if (DBAccess.IsExitsTableCol("WF_GenerWorkerList", "FK_NodeText") == true)
        {
            DBAccess.RenameTableField("WF_GenerWorkerList", "FK_NodeText", "NodeName");
            DBAccess.RenameTableField("WF_GenerWorkerList", "FK_EmpTex", "EmpName");
            DBAccess.RenameTableField("WF_GenerWorkerList", "FK_DeptT", "DeptName");
        }

        if (DBAccess.IsExitsObject("WF_EmpWorks") == true)
        {
            DBAccess.RunSQL("DROP VIEW WF_EmpWorks");
        }

        if (DBAccess.IsExitsObject("V_WF_Delay") == true)
        {
            DBAccess.RunSQL("DROP VIEW V_WF_Delay");
        }

        if (DBAccess.IsExitsObject("V_FlowStarter") == true)
        {
            DBAccess.RunSQL("DROP VIEW V_FlowStarter");
        }

        if (DBAccess.IsExitsObject("V_FlowStarterBPM") == true)
        {
            DBAccess.RunSQL("DROP VIEW V_FlowStarterBPM");
        }

        if (DBAccess.IsExitsObject("V_TOTALCH") == true)
        {
            DBAccess.RunSQL("DROP VIEW V_TOTALCH");
        }

        if (DBAccess.IsExitsObject("V_TOTALCHYF") == true)
        {
            DBAccess.RunSQL("DROP VIEW V_TOTALCHYF");
        }

        if (DBAccess.IsExitsObject("V_TotalCHWeek") == true)
        {
            DBAccess.RunSQL("DROP VIEW V_TotalCHWeek");
        }

        if (DBAccess.IsExitsObject("WF_Todolist") == true)
        {
            DBAccess.RunSQL("DROP VIEW WF_Todolist");
        }

        String prix = "";
        if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.SAAS)
        {
            prix = "";
        }

        //执行必须的sql.
        String sqlscript = "";
        //执行必须的sql.
        switch (bp.difference.SystemConfig.getAppCenterDBType())
        {
            case Oracle:
            case DM:
            case KingBaseR3:
            case KingBaseR6:
            case GBASE8CByOracle:
                sqlscript = bp.difference.SystemConfig.getCCFlowAppPath() + "WF/Data/Install/SQLScript/InitView_Ora" + prix + ".sql";
                break;
            case MSSQL:
            case Informix:
                sqlscript = bp.difference.SystemConfig.getCCFlowAppPath() + "WF/Data/Install/SQLScript/InitView_SQL" + prix + ".sql";
                break;
            case MySQL:
                sqlscript = bp.difference.SystemConfig.getCCFlowAppPath() + "WF/Data/Install/SQLScript/InitView_MySQL" + prix + ".sql";
                break;
            case PostgreSQL:
            case UX:
            case HGDB:
                sqlscript = bp.difference.SystemConfig.getCCFlowAppPath() + "WF/Data/Install/SQLScript/InitView_PostgreSQL" + prix + ".sql";
                break;
            default:
                break;
        }
        DBAccess.RunSQLScript(sqlscript);
    }

}
