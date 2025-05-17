package bp.wf;

import bp.ccbill.FrmBill;
import bp.ccbill.GenerBill;
import bp.ccbill.GenerWorker;
import bp.da.*;
import bp.difference.SystemConfig;
import bp.en.*;
import bp.port.*;
import bp.sys.*;
import bp.sys.frmui.ExtImg;
import bp.web.WebUser;
import bp.wf.data.CH;
import bp.wf.template.*;
import bp.wf.template.Printer.FrmPrintTemplate;
import bp.wf.template.frm.MapDataExt;
import bp.wf.template.frm.MapDtlExt;
import bp.wf.template.frm.MapFrmFool;
import bp.wf.template.sflow.FrmSubFlow;
import bp.wf.template.sflow.SubFlowHand;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Upgrader {
    /// <summary>
    /// 升级助手
    /// </summary>
    /// <summary>
    /// 当前版本号-为了升级使用.
    /// </summary>
    public static int Ver = 2;
    /// <summary>
    /// 升级文件入口
    /// </summary>
    /// <returns></returns>
    public static String UpdataCCFlowVer() throws Exception {

        SFSearch sfSearch = new SFSearch();
        sfSearch.CheckPhysicsTable();
        if (bp.wf.Glo.getCCBPMRunModel() == CCBPMRunModel.SAAS)
        {
            return "info@SAAS模式需要手工升级.";
        }
        DBAccess.RunSQL("UPDATE GPM_PowerCenter SET CtrlModel='AdminerAndAdmin2' where CtrlModel='AdminerAndAmin2'");


        ///#region 检查是否需要升级，并更新升级的业务逻辑.
        String updataNote = "";
        /*
         * 升级版本记录:
         * 20150330: 优化发起列表的效率, by:zhoupeng.
         * 2, 升级表单树,支持动态表单树.
         * 1, 执行一次Sender发送人的升级，原来由GenerWorkerList 转入WF_GenerWorkFlow.
         * 0, 静默升级启用日期.2014-12
         */
        if (DBAccess.IsExitsObject("Sys_Serial") == false)
        {
            return "";
        }
        //升级SQL
        UpdataCCFlowVerSQLScript();

        //判断数据库的版本.
        String sql = "SELECT IntVal FROM Sys_Serial WHERE CfgKey='Ver'";
        int currDBVer = DBAccess.RunSQLReturnValInt(sql, 0);
        if (currDBVer != 0 && currDBVer >= Ver)
        {
            return null; //不需要升级.
        }

        WebUser.setNo("admin");
        //初始化数据.
        InitDBSrc_Loacl();
        InitDBSrc_LocalWebApi();
        InitDBSrc_Weather();

        FlowOrg floworg = new FlowOrg();
        floworg.CheckPhysicsTable();
        //  #endregion 检查是否需要升级，并更新升级的业务逻辑.

        //用户组的升级
        Team team =new Team();
        if(DBAccess.IsExitsTableCol("Port_Team","ParentNo")==false){
            team.CheckPhysicsTable();
        }
        int num = team.Retrieve(TeamAttr.ParentNo,"0");
        if(num == 0){
            TeamType teamType = new TeamType();
            teamType.SetValByKey(TeamAttr.Name,"类型1");
            teamType.SetValByKey(TeamAttr.Idx,"0");
            if(SystemConfig.getCCBPMRunModel()!=CCBPMRunModel.Single)
                teamType.SetValByKey(TeamAttr.OrgNo,WebUser.getOrgNo());
            teamType.Insert();

            team.SetValByKey(TeamAttr.No,"001");
            team.SetValByKey(TeamAttr.Name,"用户组");
            team.SetValByKey(TeamAttr.ParentNo,"0");
            team.SetValByKey(TeamAttr.TeamTypeNo,teamType.getNo());
            if(SystemConfig.getCCBPMRunModel()!=CCBPMRunModel.Single)
                team.SetValByKey(TeamAttr.OrgNo,WebUser.getOrgNo());
            team.SetValByKey(TeamAttr.Idx,0);
            team.Insert();
        }

        DBAccess.RunSQL("UPDATE GPM_PowerCenter SET CtrlModel='AdminerAndAdmin2' WHERE CtrlModel='AdminerAndAmin2'");
        DBAccess.RunSQL("UPDATE Sys_Enum SET Lab='经典表单' WHERE Lab='傻瓜表单'");
        if(DBAccess.IsExitsTableCol("WF_Node", "SelfFormEnRoot") ==true)
            DBAccess.RunSQL("UPDATE WF_Node SET SelfFormEnRoot=0 WHERE SelfFormEnRoot IS NULL");

        //#region 升级后缀.
        DBAccess.RunSQL("UPDATE Sys_MapAttr SET DefVal='@WebUser.DeptName' WHERE DefVal='@WebUser.FK_DeptName'");
        DBAccess.RunSQL("UPDATE Sys_MapAttr SET UIWidth = 280 WHERE UIWidth = 100 AND KeyOfEn = 'Title'");
        sql = "SELECT MyPK FROM Sys_MapAttr WHERE AtPara NOT LIKE '%@suffix=%' AND MyDataType= 8";
        MapAttrs attrs = new MapAttrs();
        attrs.RetrieveInSQL(sql);
        for (MapAttr attr : attrs.ToJavaList())
        {
            attr.SetPara("suffix", "￥");
            attr.Update();
        }
        DBAccess.RunSQL("UPDATE Sys_MapAttr SET DefVal='@WebUser.DeptNo'  WHERE DefVal='@WebUser.FK_Dept'");
        DBAccess.RunSQL("UPDATE Sys_MapAttr SET DefVal='@WebUser.DeptName'  WHERE DefVal='@WebUser.FK_DeptName'");
        //#endregion 升级后缀

        //修复EntityType枚举值
        String isExitValue = "SELECT COUNT(MyPK) FROM " + bp.sys.base.Glo.SysEnum() + " WHERE EnumKey='EntityType' AND (IntKey=4 OR IntKey=5)";
        if(DBAccess.RunSQLReturnValInt(isExitValue) == 0){
            //新增枚举4和5
            String insertEntityType = "INSERT INTO " + bp.sys.base.Glo.SysEnum() + " (MyPK, Lab, EnumKey, IntKey, Lang, OrgNo, StrKey, RefPK, ValColor " +
                    ") VALUES ";
            DBAccess.RunSQL(insertEntityType + "('EntityType_CH_4', '调查问卷', 'EntityType', 4, 'CH', NULL, NULL, NULL, '#000')");
            DBAccess.RunSQL(insertEntityType + "('EntityType_CH_5', 'EntityNoName实体', 'EntityType', 5, 'CH', NULL, NULL, NULL, '#000')");
        }

        //修复PGSQL字段默认值
        if(SystemConfig.getAppCenterDBType() == DBType.PostgreSQL){
            try {
                //获取要修复的表
                ArrayList arrayList = ClassFactory.GetObjects("bp.en.Entity");
                // 执行默认值更新
                executUpdateDefaultVal(arrayList);
            }catch (Exception e){
                if(e.getMessage() != null && !e.getMessage().contains("err@没有找到 TS.")){
                    Log.DebugWriteError(e);
                }
            }
        }
        //#region 升级从表的OID.
        MapDtls mapdtls = new MapDtls();
        mapdtls.RetrieveAll();
        for(MapDtl dtl : mapdtls.ToJavaList())
        {
            sql = "SELECT IntVal FROM Sys_Serial WHERE CfgKey='" + dtl.getPTable() + "'";
            int val = DBAccess.RunSQLReturnValInt(sql,0);
            if (val == 0)
            {
                try{
                    val = DBAccess.RunSQLReturnValInt("SELECT MAX(OID) AS A FROM " + dtl.getPTable(), 0);
                    DBAccess.RunSQL("INSERT INTO Sys_Serial (CfgKey,IntVal) VALUES ('"+dtl.getPTable()+"',"+val+") ");
                }catch(Exception e){

                }

            }
        }
        //#endregion 升级从表的OID.


        ///#endregion 检查是否需要升级，并更新升级的业务逻辑.
        //#region 升级track表.
        DataTable dtFlows = DBAccess.RunSQLReturnTable("SELECT No FROM WF_Flow ");
        for (DataRow dr : dtFlows.Rows)
        {
            String flowNo = dr.getValue(0).toString();
            String tableTrack = "ND" + Integer.valueOf(flowNo) + "Track";
            if(DBAccess.IsExitsObject(tableTrack) == false)
                continue;
            switch (SystemConfig.getAppCenterDBType())
            {
                case MySQL:
                case DM:
                    DBAccess.RunSQL("ALTER TABLE " + tableTrack + " MODIFY MyPK BIGINT");
                    break;
                case MSSQL:
                    DBAccess.DropTablePK(tableTrack);
                    DBAccess.AlterColumnToBigInt(tableTrack, "MyPK");
                    break;
                case PostgreSQL:
                case KingBaseR3:
                case KingBaseR6:
                case KingBaseR8:
                case HGDB:
                    DBAccess.RunSQL("ALTER TABLE " + tableTrack + " ALTER COLUMN MyPK TYPE BIGINT");
                    break;
                case Oracle:
                    DBAccess.RunSQL("ALTER TABLE " + tableTrack + " MODIFY (MyPK BIGINT)");
                    break;
                default:
                    break;
            }
        }
        //#endregion 升级track表.
        ///#region 为科伦修复字段.
        if (DBAccess.IsExitsTableCol("WF_PushMsg", "FlowNo") == false)
        {
            DBAccess.RenameTableField("WF_PushMsg", "FK_Flow", "FlowNo");
        }
        if (DBAccess.IsExitsTableCol("Sys_FrmEvent", "FK_Flow") == true)
        {
            FrmEvent myfe = new FrmEvent();
            myfe.CheckPhysicsTable();

            DBAccess.RunSQL("UPDATE Sys_FrmEvent SET FlowNo=FK_Flow WHERE FlowNo IS NULL ");
            DBAccess.RunSQL("UPDATE Sys_FrmEvent SET NodeID=FK_Node WHERE NodeID IS NULL OR NodeID=0 ");

            //DBAccess.DropTableColumn("Sys_FrmEvent", "FK_Flow");
            //DBAccess.DropTableColumn("Sys_FrmEvent", "FK_Node");
        }
        if (DBAccess.IsExitsTableCol("WF_GenerWorkerList", "NodeName") == false)
        {
            GenerWorkerList wl = new GenerWorkerList();
            wl.CheckPhysicsTable();
        }
        if (DBAccess.IsExitsTableCol("Sys_FrmEvent", "FK_Flow") == true)
        {
            FrmEvent myfe = new FrmEvent();
            myfe.CheckPhysicsTable();

            DBAccess.RunSQL("UPDATE Sys_FrmEvent SET FlowNo=FK_Flow WHERE FlowNo IS NULL ");
            DBAccess.RunSQL("UPDATE Sys_FrmEvent SET NodeID=FK_Node WHERE NodeID IS NULL OR NodeID=0 ");

            DBAccess.DropTableColumn("Sys_FrmEvent", "FK_Flow");
            DBAccess.DropTableColumn("Sys_FrmEvent", "FK_Node");
        }

        CCList listcc = new CCList();
        listcc.CheckPhysicsTable();
        ///#endregion 为科伦修复字段.
        GenerWorker gw = new GenerWorker();
        gw.CheckPhysicsTable();
        ///#region 升级单据 .2024.09.
        //升级单据.
        GenerBill gb = new GenerBill();
        gb.CheckPhysicsTable();
        FrmBill fbill = new FrmBill();
        fbill.CheckPhysicsTable();
        FrmBill bill = new FrmBill();
        bill.CheckPhysicsTable();

        //   #region 导入数据错误的问题.
        String sql1 = "SELECT No FROM Sys_MapData WHERE EnPK='No' AND No LIKE 'ND%'";
        DataTable dt23 = DBAccess.RunSQLReturnTable(sql1);
        for (DataRow dr : dt23.Rows)
        {
            String frmID = dr.getValue(0).toString();
            MapData md = new MapData(frmID);
            md.setEnPK("OID");
            md.setEntityType(EntityType.SingleFrm);
            md.Update();

            sql1 = "DELETE FROM Sys_MapAttr WHERE FK_Mapdata='" + md.getNo() + "' AND KeyOfEn IN('No','Name','EntityState','RecNo','RecName','OrgNo','DeptNo')";
            DBAccess.RunSQL(sql1);
        }

        ///#endregion
        ///#region 单据，字典找不到目录.
        if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
        {
            DataTable dtFrms = DBAccess.RunSQLReturnTable("SELECT No,Name,FK_FormTree FROM Sys_MapData WHERE EntityType in (1,2,5)  AND FK_FormTree NOT IN (SELECT no from Sys_FormTree  )");
            String treeNo = DBAccess.RunSQLReturnString("SELECT NO FROM Sys_FormTree WHERE ParentNo='0'");
            for (DataRow dr : dtFrms.Rows)
            {
                String no = dr.getValue(0)==null?"":dr.getValue(0).toString();
                if(DataType.IsNullOrEmpty(no) == false)
                    DBAccess.RunSQL("update Sys_MapData set FK_FormTree='" + treeNo + "' where No='" + no + "' ");
            }
        }
        ///#endregion
        ///#region 升级FK_Dept 转换到 DeptNo.
        MapDatas md44s = new MapDatas();
        md44s.RetrieveIn("EntityType","2,1"); //frmDict类型的实体.
        for (MapData md : md44s.ToJavaList())
        {
            MapAttr attr = new MapAttr();
            attr.setMyPK (md.getNo() + "_FK_Dept");
            if (attr.RetrieveFromDBSources() == 1)
            {
                attr.Delete(); //删除.
                attr.setKeyOfEn ("DeptNo");
                attr.setMyPK(md.getNo() + "_" + attr.getKeyOfEn());
                try
                {
                    attr.DirectInsert();
                }
                catch (Exception ex)
                {
                }
                //处理数据.
                if (DBAccess.IsExitsTableCol(md.getPTable(), "DeptNo") == false)
                {
                    if (DBAccess.IsExitsTableCol(md.getPTable(), "FK_Dept") == true)
                        DBAccess.RenameTableField(md.getPTable(), "FK_Dept", "DeptNo");
                    else
                        DBAccess.CreateTableColumn(md.getPTable(), "DeptNo", DataType.AppString, "", "");
                }
            }
            //  item.Turn2EntityNoName();
        }
        ///#endregion 升级FK_Dept 转换到 DeptNo.
        //升级:FrmDict
//		MapDatas mds = new MapDatas();
//		mds.Retrieve("EntityType", 2); //frmDict类型的实体.
//		for (MapData item : mds.ToJavaList())
//			item.Turn2EntityNoName();

        //处理升级失败，修复菜单问题.
        MapDatas mds = new MapDatas();
        mds.Retrieve("EntityType", 5); //frmDict类型的实体.
        for(MapData md : mds.ToJavaList())
        {
            //修改菜单.
            String sq1l = "UPDATE GPM_Menu SET MenuModel='EntityNoName', AtPara='@EnName=TS.CCBill.FrmEntityNoName@EnPKVal=" + md.getNo() + "', UrlPath ='/src/CCFast/CCBill/SearchEntityNoName.vue' WHERE FrmID='" + md.getNo() + "'";
            DBAccess.RunSQL(sq1l);
            // md.Turn2EntityNoName();

            //删除描述字段.
            DBAccess.RunSQL("DELETE FROM  Sys_MapAttr WHERE KeyOfEn IN ('Title','BillNo','OID','FID') AND FK_MapData='" + md.getNo() + "'");

            //删除描述字段.
            DBAccess.RunSQL("DELETE FROM  Sys_MapAttr WHERE KeyOfEn IN ('BillState','Starter','StarterName','FK_Dept','EnOID') AND FK_MapData='" + md.getNo() + "'");
        }

        //执行sql.升级节点高度.
        if (DBAccess.IsExitsTableCol("WF_Node", "UIWidth") == true)
            DBAccess.RunSQL("UPDATE WF_Node SET UIWidth=120,UIHeight=60 WHERE UIWidth=0 OR UIWidth Is Null ");

        if (DBAccess.IsExitsTableCol("WF_Node", "NodeType") == true)
        {
            DBAccess.RunSQL("UPDATE WF_Node SET NodeType=0 WHERE NodeType IS NULL ");
            //UIContralType.Score
        }
        else
        {
            Node nd = new Node();
            nd.CheckPhysicsTable();
            DBAccess.RunSQL("UPDATE WF_Node SET NodeType=0 WHERE NodeType IS NULL ");
        }

        ///#region 升级枚举值 - 兼容H5版本.
        DBAccess.RunSQL("UPDATE Sys_EnumMain SET AtPara ='@EnName=TS.FrmUI.SysEnumMainInt' WHERE AtPara IS NULL OR AtPara =''");
        DBAccess.RunSQL("UPDATE Sys_EnumMain SET EnumKey =No WHERE EnumKey IS NULL OR EnumKey =''");
        //#endregion

        // Sys_MapExt的pop弹窗的升级
        MapExts mapExts = new MapExts();
        mapExts.RetrieveIn(MapExtAttr.ExtModel, "'Pop','MultipleChoiceSearch'");
        for(MapExt mapExt : mapExts.ToJavaList())
        {
            String mypk = mapExt.getFrmID() + "_" + mapExt.getAttrOfOper();
            MapAttr mapAttr = new MapAttr();
            mapAttr.setMyPK(mypk + "T");
            if (mapAttr.RetrieveFromDBSources() == 0)
            {
                mapAttr.setMyPK(mypk);
                mapAttr.RetrieveFromDBSources();
                mapAttr.setMyPK(mapAttr.getMyPK() + 'T');
                mapAttr.setKeyOfEn(mapAttr.getKeyOfEn() + 'T');
                mapAttr.setUIVisible(false);
                mapAttr.setUIIsEnable(false);
                mapAttr.Insert();
            }
        }
        ///#endregion Sys_MapExt的pop弹窗的升级

        ///#region 2023.07.25 - 翻译java的兼容性.
        if (DBAccess.IsExitsTableCol("WF_GenerWorkerList", "FK_NodeText") == true)
        {
            DBAccess.RenameTableField("WF_GenerWorkerList", "FK_NodeText", "NodeName");
            DBAccess.RenameTableField("WF_GenerWorkerList", "FK_EmpTex", "EmpName");
            DBAccess.RenameTableField("WF_GenerWorkerList", "FK_DeptT", "DeptName");
        }

        ///#endregion 2023.07.25

        ///#region 2023.07.02 升级字典表,查询.
        SFSearch search = new SFSearch();
        search.CheckPhysicsTable();
        bp.sys.SFTable table = new bp.sys.SFTable();
        table.CheckPhysicsTable();
        SFProc enProduce = new SFProc();
        enProduce.CheckPhysicsTable();

        ///#endregion 2023.07.02

        ///#region 升级SFTable中SrcType为DBSrcType
        if (DBAccess.IsExitsTableCol("Sys_SFTable", "SrcType") == true)
        {
            if (DBAccess.IsExitsTableCol("Sys_SFTable", "DBSrcType") == false)
            {
                switch (SystemConfig.getAppCenterDBType())
                {
                    case MSSQL:
                        DBAccess.RunSQL("ALTER TABLE Sys_SFTable ADD DBSrcType NVARCHAR(20) DEFAULT 'BPClass' NULL");
                        break;
                    case Oracle:
                    case DM:
                    case Informix:
                    case PostgreSQL:
                    case HGDB:
                    case UX:
                    case KingBaseR3:
                    case KingBaseR6:
                    case GBASE8CByOracle:
                        DBAccess.RunSQL("ALTER TABLE Sys_SFTable ADD DBSrcType VARCHAR(20) DEFAULT 'BPClass' NULL");
                        break;
                    case MySQL:
                    case GBASE8CByMySQL:
                    case GBASE8A:
                        DBAccess.RunSQL("ALTER TABLE Sys_SFTable ADD DBSrcType NVARCHAR(20) DEFAULT 'BPClass' NULL");
                        break;
                    default:
                        break;
                }

            }
            DBAccess.RunSQL("UPDATE Sys_SFTable SET DBSrcType=(CASE SrcType WHEN  0 THEN 'BPClass' WHEN 1 THEN 'CreateTable' WHEN 1 THEN 'CreateTable' " + "WHEN 2 THEN 'TableOrView' WHEN 3 THEN 'SQL' WHEN 4 THEN 'WebServices' WHEN 5 THEN 'Handler' WHEN 6 THEN 'JQuery' " + "WHEN 7 THEN 'SysDict' ELSE 'WebApi' END)");
            DBAccess.DropTableColumn("Sys_SFTable", "SrcType");

        }

        if (DBAccess.IsExitsTableCol("WF_Node", "NodeType") )
        {
            DBAccess.RunSQL("UPDATE WF_Node SET NodeType=0 WHERE NodeType IS NULL ");
        }
        else
        {
            Node nd = new Node();
            nd.CheckPhysicsTable();
            DBAccess.RunSQL("UPDATE WF_Node SET NodeType=0 WHERE NodeType IS NULL ");
        }

        ///#endregion 升级SFTable中SrcType为DBSrcType

        ///#region 升级流程模式的存储方式
        if (DBAccess.IsExitsTableCol("WF_Flow", "FlowDevModel") == false)
        {
            switch (SystemConfig.getAppCenterDBType())
            {
                case MSSQL:
                    DBAccess.RunSQL("ALTER TABLE WF_Flow ADD FlowDevModel INT NULL");
                    break;
                case Oracle:
                case DM:
                case Informix:
                case PostgreSQL:
                case HGDB:
                case UX:
                case KingBaseR3:
                case KingBaseR6:
                case GBASE8CByOracle:
                    DBAccess.RunSQL("ALTER TABLE WF_Flow ADD FlowDevModel INTEGER NULL");
                    break;
                case MySQL:
                case GBASE8CByMySQL:
                case GBASE8A:
                    DBAccess.RunSQL("ALTER TABLE WF_Flow ADD FlowDevModel INT NULL");
                    break;
                default:
                    break;
            }
        }
        Flows flows = new Flows();
        QueryObject qo = new QueryObject(flows);
        qo.AddWhere("AtPara", "Like", "%FlowDevModel=%");
        qo.DoQuery();
        for (Flow flow : flows.ToJavaList())
        {
            int flowDevModel = flow.GetParaInt("FlowDevModel", 0);
            flow.setFlowDevModel(FlowDevModel.forValue(flowDevModel));
            String atPara = flow.GetValStringByKey("AtPara");
            atPara = atPara.replace("@FlowDevModel=" + flowDevModel, "");
            flow.SetValByKey("AtPara", atPara);
            flow.DirectUpdate();
        }

        ///#endregion 升级流程模式的存储方式


        ///#region 升级文本框字段类型.  TextModel=0普通文本，1密码，2=TextArea,3=富文本.
        //说明没有升级. TextModel=0
        if (DBAccess.IsExitsTableCol("Sys_MapAttr", "IsRichText") == true)
        {
            MapAttr ma = new MapAttr();
            ma.CheckPhysicsTable();

            sql = "UPDATE Sys_MapAttr SET TextModel=3 WHERE IsRichText=1 OR AtPara LIKE '%IsRichText=1%'";
            DBAccess.RunSQL(sql);

            sql = "UPDATE Sys_MapAttr SET TextModel=2 WHERE UIHeight >=40 OR IsSupperText=1";
            DBAccess.RunSQL(sql);

            sql = "UPDATE Sys_MapAttr SET TextModel=1 WHERE IsSecret=1";
            DBAccess.RunSQL(sql);

            DBAccess.DropTableColumn("Sys_MapAttr", "IsRichText");
            DBAccess.DropTableColumn("Sys_MapAttr", "IsSecret");
        }

        ///#endregion 升级文本框字段类型


        ///#region 统一升级主键. 给多对多的实体增加主键.
        if (DBAccess.IsExitsTableCol("WF_NodeStation", "MyPK") == false)
        {
            //1.首先要删除主键.
            DBAccess.DropTablePK("WF_NodeStation");
            if (Objects.equals(SystemConfig.getAppCenterDBType().toString(), "MySQL"))
            {
                DBAccess.RunSQL("ALTER TABLE WF_NodeStation ADD COLUMN MyPK VARCHAR(100)");
                DBAccess.RunSQL("UPDATE WF_NodeStation SET MyPK= CONCAT(FK_Node,'_',FK_Station)");
            }
            else
            {
                if (Objects.equals(SystemConfig.getAppCenterDBType().toString(), "MSSQL"))
                {
                    DBAccess.RunSQL("ALTER TABLE WF_NodeStation ADD  MyPK VARCHAR(100)");
                    DBAccess.RunSQL("UPDATE WF_NodeStation SET MyPK= CONVERT(varchar,FK_Node)+'_'+FK_Station");
                }
            }

            //2. 自动创建.
            NodeStation ns = new NodeStation();
            ns.CheckPhysicsTable();
        }

        if (DBAccess.IsExitsObject("WF_NodeTeam") == false)
        {
            NodeTeam nt = new NodeTeam();
            nt.CheckPhysicsTable();
        }

        if (DBAccess.IsExitsTableCol("WF_NodeTeam", "MyPK") == false)
        {
            DBAccess.DropTablePK("WF_NodeTeam");
            if (SystemConfig.getAppCenterDBType() == DBType.MySQL)
            {
                DBAccess.RunSQL("ALTER TABLE WF_NodeTeam ADD COLUMN MyPK VARCHAR(100)");
                DBAccess.RunSQL("UPDATE WF_NodeTeam SET MyPK= CONCAT(FK_Node,'_',FK_Team)");
            }
            else if (SystemConfig.getAppCenterDBType() == DBType.MSSQL)
            {
                DBAccess.RunSQL("ALTER TABLE WF_NodeTeam ADD  MyPK VARCHAR(100)");
                DBAccess.RunSQL("UPDATE WF_NodeTeam SET MyPK= CONVERT(varchar,FK_Node)+'_'+FK_Team");
            }
            //3. 执行更新.
        }

        if (DBAccess.IsExitsTableCol("WF_NodeEmp", "MyPK") == false)
        {

            DBAccess.DropTablePK("WF_NodeEmp");
            if (Objects.equals(SystemConfig.getAppCenterDBType().toString(), "MySQL"))
            {
                DBAccess.RunSQL("ALTER TABLE WF_NodeEmp ADD COLUMN MyPK VARCHAR(100)");
                DBAccess.RunSQL("UPDATE WF_NodeEmp SET MyPK= CONCAT(FK_Node,'_',FK_Emp)");
            }
            else
            {
                if (Objects.equals(SystemConfig.getAppCenterDBType().toString(), "MSSQL"))
                {

                    DBAccess.RunSQL("ALTER TABLE WF_NodeEmp ADD  MyPK VARCHAR(100)");
                    DBAccess.RunSQL("UPDATE WF_NodeEmp SET MyPK= CONVERT(varchar,FK_Node)+'_'+FK_Emp");
                }
            }
            NodeEmp ne1 = new NodeEmp();
            ne1.CheckPhysicsTable();
            //3. 执行更新.

        }

        ///#endregion 统一升级主键.


        ///#region 系统更新.
        //升级支持ts.
        // UpdataTSModel();
        //升级日志.
        UserLogLevel ul = new UserLogLevel();
        ul.CheckPhysicsTable();
        UserLogType ut = new UserLogType();
        ut.CheckPhysicsTable();

        //添加IsEnable
        FlowTab fb = new FlowTab();
        fb.CheckPhysicsTable();

        if (DBAccess.IsExitsTableCol("Sys_GroupField", "EnName") == true)
        {
            GroupField groupField = new GroupField();
            groupField.CheckPhysicsTable();
            DBAccess.RunSQL("UPDATE Sys_GroupField SET FrmID=enName WHERE FrmID IS null");
        }

        //升级.
        Auth ath = new Auth();
        ath.CheckPhysicsTable();

        //检查BPM.现在暂时不使用原菜单结构
        // if (!SystemConfig.OrganizationIsView)
        //    CheckGPM();

        MapData mapData = new MapData();
        mapData.CheckPhysicsTable();

        Direction dir = new Direction();
        dir.CheckPhysicsTable();

        ///#endregion 系统更新.


        ///#region 升级优化集团版的应用. 2020.04.03

        //--2020.05.28 升级方向条件;
        Cond cond = new Cond();
        cond.CheckPhysicsTable();
        if (DBAccess.IsExitsTableCol("WF_Cond", "PRI") == true)
        {
            DBAccess.RunSQL("UPDATE WF_Cond SET Idx=PRI ");
            DBAccess.DropTableColumn("WF_Cond", "PRI");
        }

        //修改节点类型,合并属性.
		/*if (DBAccess.IsExitsTableCol("WF_Node", "SubThreadType") == true)
		{
		    DBAccess.RunSQLReturnTable("UPDATE WF_Node SET RunModel=5 WHERE SubThreadType=1 ");
		    DBAccess.DropTableColumn("WF_Node", "SubThreadType");
		}*/

        //--2020.05.25 修改节点自定义按钮功能;
        NodeToolbar bar = new NodeToolbar();
        bar.CheckPhysicsTable();
        if (DBAccess.IsExitsTableCol("WF_NodeToolbar", "ShowWhere") == true)
        {
            DBAccess.RunSQL("UPDATE WF_NodeToolbar SET IsMyFlow = 1 Where ShowWhere = 1");
            DBAccess.RunSQL("UPDATE WF_NodeToolbar SET IsMyCC = 1 Where ShowWhere = 2");

            DBAccess.DropTableColumn("WF_NodeToolbar", "ShowWhere");
        }
        switch (SystemConfig.getAppCenterDBType())
        {
            case Oracle:
            case DM:
            case GBASE8CByOracle:
                DBAccess.RunSQL("UPDATE Sys_MapAttr set tag=''");
                break;
            default:
                break;
        }
        Direction direction = new Direction();
        direction.CheckPhysicsTable();

        MapAttr myattr = new MapAttr();
        myattr.CheckPhysicsTable();

        MapDtlExt mde = new MapDtlExt();
        mde.CheckPhysicsTable();

        NodeExt ne = new NodeExt();
        ne.CheckPhysicsTable();

        FlowExt fe = new FlowExt();
        fe.CheckPhysicsTable();

        //检查frmTrack.
        bp.ccbill.Track tk = new bp.ccbill.Track();
        tk.CheckPhysicsTable();

        DeptEmpStation des = new DeptEmpStation();
        des.CheckPhysicsTable();

        DeptEmp de = new DeptEmp();
        de.CheckPhysicsTable();

        Emp emp1 = new Emp();
        emp1.CheckPhysicsTable();

        bp.wf.port.admin2group.Org org = new bp.wf.port.admin2group.Org();
        org.CheckPhysicsTable();

        FlowSort fs = new FlowSort();
        fs.CheckPhysicsTable();

        FlowOrg fo = new FlowOrg();
        fo.CheckPhysicsTable();

        SysEnumMain sem = new SysEnumMain();
        sem.CheckPhysicsTable();

        SysEnum myse = new SysEnum();
        myse.CheckPhysicsTable();

        //检查表.
        GloVar gv = new GloVar();
        gv.CheckPhysicsTable();

        //检查表.
        EnCfg cfg = new EnCfg();
        cfg.CheckPhysicsTable();

        //检查表.
        SysFormTree frmTree = new SysFormTree();
        frmTree.CheckPhysicsTable();

        SFTable sf = new SFTable();
        sf.CheckPhysicsTable();

        FrmSubFlow sb = new FrmSubFlow();
        sb.CheckPhysicsTable();

        PushMsg pm = new PushMsg();
        pm.CheckPhysicsTable();

        //修复数据表.
        GroupField gf = new GroupField();
        gf.CheckPhysicsTable();


        ///#endregion 升级优化集团版的应用


        ///#region 升级子流程.
        //检查子流程表.
        if (DBAccess.IsExitsObject("WF_NodeSubFlow") == true)
        {
            if (DBAccess.IsExitsTableCol("WF_NodeSubFlow", "OID") == true)
            {
                DBAccess.RunSQL("DROP TABLE WF_NodeSubFlow");
                SubFlowHand sub = new SubFlowHand();
                sub.CheckPhysicsTable();
            }
        }

        ///#endregion 升级子流程.


        ///#region 升级方向条件. 2020.06.02
        if (DBAccess.IsExitsTableCol("WF_Cond", "CondOrAnd") == true)
        {
            DataTable dt = DBAccess.RunSQLReturnTable("SELECT DISTINCT FK_Node, toNodeID, CondOrAnd, CondType  FROM wf_cond WHERE DataFrom!=100 ");
            for (DataRow dr : dt.Rows)
            {
                int nodeID = Integer.parseInt(dr.getValue("FK_Node").toString());
                int toNodeID = Integer.parseInt(dr.getValue("toNodeID").toString());

                Conds conds = new Conds();
                conds.Retrieve(CondAttr.FK_Node, nodeID, CondAttr.ToNodeID, toNodeID, CondAttr.Idx);

                //判断是否需要修复？
                if (conds.size() == 1 || conds.size() == 0)
                {
                    continue;
                }

                //判断是否有？
                boolean isHave = false;
                for (Cond myCond : conds.ToJavaList())
                {
                    if (myCond.getHisDataFrom() == ConnDataFrom.CondOperator)
                    {
                        isHave = true;
                    }
                }
                if (isHave == true)
                {
                    continue;
                }

                //获得类型.
                int OrAndType = DBAccess.RunSQLReturnValInt("SELECT  CondOrAnd  FROM wf_cond WHERE FK_Node=" + nodeID, -1);
                if (OrAndType == -1)
                {
                    continue;
                }

                int idx = 0;
                int idxSave = 0;
                int count = conds.size();
                for (Cond item : conds.ToJavaList())
                {
                    idx++;

                    idxSave++;
                    item.setIdx(idxSave);
                    item.Update();

                    if (count == idx)
                    {
                        continue;
                    }

                    Cond operCond = new Cond();
                    operCond.Copy(item);
                    operCond.setMyPK(DBAccess.GenerGUID(0, null, null));
                    operCond.setHisDataFrom(ConnDataFrom.CondOperator);

                    if (OrAndType == 0)
                    {
                        operCond.setOperatorValue(" OR ");
                        operCond.setNote(" OR ");
                        operCond.setOperatorValue(" OR ");
                        operCond.setNote(" OR ");
                    }
                    else
                    {
                        operCond.setOperatorValue(" AND ");
                        operCond.setNote(" AND ");
                        operCond.setOperatorValue(" AND ");
                        operCond.setNote(" AND ");
                    }

                    idxSave++;
                    operCond.setIdx(idxSave);
                    operCond.Insert();
                }
            }

            //升级后删除指定的列.
            DBAccess.DropTableColumn("WF_Cond", "CondOrAnd");
            DBAccess.DropTableColumn("WF_Cond", "NodeID");
        }

        ///#endregion 升级方向条件.


        ///#region 升级视图. 解决厦门信息港的 - 流程监控与授权.
        if (DBAccess.IsExitsObject("V_MyFlowData") == false)
        {
            PowerModel pm11 = new PowerModel();
            pm11.CheckPhysicsTable();

            sql = "CREATE VIEW V_MyFlowData ";
            sql += " AS ";
            sql += " SELECT A.*, B.EmpNo as MyEmpNo FROM WF_GenerWorkflow A, WF_PowerModel B ";
            sql += " WHERE  A.FK_Flow=B.FlowNo AND B.PowerCtrlType=1 AND A.WFState>= 2";
            sql += "    UNION  ";
            sql += " SELECT A.*, c.No as MyEmpNo FROM WF_GenerWorkflow A, WF_PowerModel B, Port_Emp C, Port_DeptEmpStation D";
            sql += " WHERE  A.FK_Flow=B.FlowNo  AND B.PowerCtrlType=0 AND C.No=D.FK_Emp AND B.StaNo=D.FK_Station AND A.WFState>=2";
            DBAccess.RunSQL(sql);
        }

        ///#endregion 升级视图.

        //升级从表的 fk_node .
        //获取需要修改的从表
        String dtlNos = DBAccess.RunSQLReturnString("SELECT B.NO  FROM SYS_GROUPFIELD A, SYS_MAPDTL B WHERE A.CTRLTYPE='Dtl' AND A.CTRLID=B.NO AND FK_NODE!=0");
        if (DataType.IsNullOrEmpty(dtlNos) == false)
        {
            dtlNos = dtlNos.replace(",", "','");
            dtlNos = "('" + dtlNos + "')";
            DBAccess.RunSQL("UPDATE SYS_MAPDTL SET FK_NODE=0 WHERE NO IN " + dtlNos);
        }
        FrmNode nff = new FrmNode();
        nff.CheckPhysicsTable();


        ///#region 更新节点名称.
        switch (SystemConfig.getAppCenterDBType())
        {
            case MSSQL:
            case PostgreSQL:
            case HGDB:
            case KingBaseR3:
            case KingBaseR6:
                sql = " UPDATE WF_Direction SET ToNodeName = WF_Node.Name FROM WF_Node ";
                sql += " WHERE WF_Direction.ToNode = WF_Node.NodeID ";
                break;
            case Oracle:
            case DM:
            case GBASE8CByOracle:
                sql = "UPDATE WF_Direction E SET ToNodeName=(SELECT U.Name FROM WF_Node U WHERE E.ToNode=U.NodeID) WHERE EXISTS (SELECT 1 FROM WF_Node U WHERE E.ToNode=U.NodeID)";
                break;
            default:
                sql = "UPDATE WF_Direction A, WF_Node B SET A.ToNodeName=B.Name WHERE A.ToNode=B.NodeID ";
                break;
        }
        DBAccess.RunSQL(sql);

        //更新groupField.
        switch (SystemConfig.getAppCenterDBType())
        {
            case MySQL:
            case GBASE8CByMySQL:
            case GBASE8A:
                sql = "UPDATE Sys_MapDtl, Sys_GroupField B SET Sys_MapDtl.GroupField=B.OID WHERE Sys_MapDtl.No=B.CtrlID AND Sys_MapDtl.GroupField=''";
                break;
            case Oracle:
            case DM:
            case GBASE8CByOracle:
                sql = "UPDATE Sys_MapDtl E SET GroupField=(SELECT U.OID FROM Sys_GroupField U WHERE E.No=U.CtrlID) WHERE EXISTS (SELECT 1 FROM Sys_GroupField U WHERE E.No=U.CtrlID AND E.GroupField='')";
                DBAccess.RunSQL("UPDATE Sys_MapAttr set tag=''");
                break;
            case MSSQL:
            default:
                sql = "UPDATE Sys_MapDtl SET GroupField=Sys_GroupField.OID FROM Sys_GroupField WHERE Sys_MapDtl.No=Sys_GroupField.CtrlID AND Sys_MapDtl.GroupField=''";
                break;
        }
        DBAccess.RunSQL(sql);

        ///#endregion 更新节点名称.


        ///#region 升级审核组件
        if (SystemConfig.getAppCenterDBType() == DBType.MySQL  ||  SystemConfig.getAppCenterDBType() == DBType.GBASE8CByMySQL  ||  SystemConfig.getAppCenterDBType() == DBType.GBASE8A)
        {
            sql = "UPDATE WF_FrmNode F INNER JOIN(SELECT FWCSta,NodeID FROM WF_Node ) N on F.FK_Node = N.NodeID and  F.IsEnableFWC =1 SET F.IsEnableFWC = N.FWCSta;";
        }
        if (SystemConfig.getAppCenterDBType() == DBType.MSSQL)
        {
            sql = "UPDATE    F SET IsEnableFWC = N. FWCSta  FROM WF_FrmNode F,WF_Node N    WHERE F.FK_Node = N.NodeID AND F.IsEnableFWC =1";
        }
        if (SystemConfig.getAppCenterDBType() == DBType.DM ||SystemConfig.getAppCenterDBType() == DBType.Oracle || SystemConfig.getAppCenterDBType() == DBType.KingBaseR3 || SystemConfig.getAppCenterDBType() == DBType.KingBaseR6 ||  SystemConfig.getAppCenterDBType() == DBType.GBASE8CByOracle)
        {
            sql = "UPDATE WF_FrmNode F  SET (IsEnableFWC)=(SELECT FWCSta FROM WF_Node N WHERE F.FK_Node = N.NodeID AND F.IsEnableFWC =1)";
        }
        if (SystemConfig.getAppCenterDBType() == DBType.PostgreSQL || SystemConfig.getAppCenterDBType() == DBType.HGDB || SystemConfig.getAppCenterDBType() == DBType.HGDB || SystemConfig.getAppCenterDBType() == DBType.UX)
        {
            sql = "UPDATE WF_FrmNode SET IsEnableFWC=(SELECT FWCSta FROM WF_Node N Where N.NodeID=WF_FrmNode.FK_Node AND WF_FrmNode.IsEnableFWC=1)";
        }

        DBAccess.RunSQL(sql);

        ///#endregion 升级审核组件

        String msg = "";
        try
        {


            ///#region 升级事件.
            if (DBAccess.IsExitsTableCol("Sys_FrmEvent", "DoType") == true)
            {
                FrmEvent frmevent = new FrmEvent();
                frmevent.CheckPhysicsTable();

                DBAccess.RunSQL("UPDATE Sys_FrmEvent SET EventDoType=DoType  ");
                DBAccess.RunSQL("ALTER TABLE Sys_FrmEvent  DROP COLUMN	DoType  ");
            }

            ///#endregion


            ///#region 修复丢失的发起人.
            Flows fls = new Flows();
            fls.getNewEntity().CheckPhysicsTable();

            for (Flow item : fls.ToJavaList())
            {
                if (DBAccess.IsExitsObject(item.getPTable()) == false)
                {
                    continue;
                }
                try
                {
                    DBAccess.RunSQL(" UPDATE " + item.getPTable() + " SET FlowStarter =(SELECT Starter FROM WF_GENERWORKFLOW WHERE " + item.getPTable() + ".Oid=WF_GENERWORKFLOW.WORKID)");
                }
                catch (RuntimeException ex)
                {
                    //   GERpt rpt=new GERpt(
                }
            }

            ///#endregion 修复丢失的发起人.


            ///#region 给city 设置拼音.
            if (DBAccess.IsExitsObject("Demo_City") == true && 1 == 2)
            {
                if (DBAccess.IsExitsTableCol("Demo_City", "PinYin") == true)
                {
                    /*为cn_city 设置拼音.*/
                    sql = "SELECT No,Names FROM Demo_City ";
                    DataTable dtCity = DBAccess.RunSQLReturnTable(sql);

                    for (DataRow dr : dtCity.Rows)
                    {
                        String no = dr.getValue("No").toString();
                        String name = dr.getValue("Names").toString();
                        String pinyin1 = DataType.ParseStringToPinyin(name);
                        String pinyin2 = DataType.ParseStringToPinyinJianXie(name);
                        String pinyin = "," + pinyin1 + "," + pinyin2 + ",";
                        DBAccess.RunSQL("UPDATE Demo_City SET PinYin='" + pinyin + "' WHERE NO='" + no + "'");
                    }
                }
            }

            ///#endregion 给city 设置拼音.

            //增加列FlowStars
            bp.wf.port.WFEmp wfemp = new bp.wf.port.WFEmp();
            wfemp.CheckPhysicsTable();

            DBType dbtype = SystemConfig.getAppCenterDBType();

            FrmRB rb = new FrmRB();
            rb.CheckPhysicsTable();


            MapDtlExt dtlExt = new MapDtlExt();
            dtlExt.CheckPhysicsTable();

            //删除枚举.
            DBAccess.RunSQL("DELETE FROM " + bp.sys.base.Glo.SysEnum() + " WHERE EnumKey IN ('SelectorModel','CtrlWayAth')");

            //2017.5.19 打印模板字段修复
            FrmPrintTemplate bt = new FrmPrintTemplate();
            bt.CheckPhysicsTable();
            if (DBAccess.IsExitsTableCol("Sys_FrmPrintTemplate", "url") == true)
            {
                DBAccess.RunSQL("UPDATE Sys_FrmPrintTemplate SET TempFilePath = Url WHERE TempFilePath IS null");
            }

            //规范升级根目录.
            DataTable dttree = DBAccess.RunSQLReturnTable("SELECT No FROM Sys_FormTree WHERE ParentNo='-1' ");
            if (dttree.Rows.size() == 1)
            {
                DBAccess.RunSQL("UPDATE Sys_FormTree SET ParentNo='1' WHERE ParentNo='0' ");
                DBAccess.RunSQL("UPDATE Sys_FormTree SET No='1' WHERE No='0'  ");
                DBAccess.RunSQL("UPDATE Sys_FormTree SET ParentNo='0' WHERE No='1'");
            }

            //删除垃圾数据.
            MapExt.DeleteDB();

            //升级经典表单.
            MapFrmFool mff = new MapFrmFool();
            mff.CheckPhysicsTable();


            ///#region 表单方案中的不可编辑, 旧版本如果包含了这个列.
            if (DBAccess.IsExitsTableCol("WF_FrmNode", "IsEdit") == true)
            {
                /*如果存在这个列,就查询出来=0的设置，就让其设置为不可以编辑的。*/
                sql = "UPDATE WF_FrmNode SET FrmSln=1 WHERE IsEdit=0 ";
                DBAccess.RunSQL(sql);

                sql = "UPDATE WF_FrmNode SET IsEdit=100000";
                DBAccess.RunSQL(sql);
            }

            ///#endregion

            //执行升级 2016.04.08
            Cond cnd = new Cond();
            cnd.CheckPhysicsTable();


            ///#region  增加week字段,方便按周统计.
            sql = "SELECT WorkID,RDT FROM WF_GenerWorkFlow WHERE WeekNum=0 or WeekNum is null ";
            DataTable dt = DBAccess.RunSQLReturnTable(sql);
            for (DataRow dr : dt.Rows)
            {
                sql = "UPDATE WF_GenerWorkFlow SET WeekNum=" + DataType.getCurrentWeekGetWeekByDay(dr.getValue(1).toString().replace("+", " ")) + " WHERE WorkID=" + dr.getValue(0).toString();
                DBAccess.RunSQL(sql);
            }

            //查询.
            CH ch = new CH();
            ch.CheckPhysicsTable();

            sql = "SELECT MyPK,DTFrom FROM WF_CH WHERE WeekNum=0 or WeekNum is null ";
            dt = DBAccess.RunSQLReturnTable(sql);
            for (DataRow dr : dt.Rows)
            {
                sql = "UPDATE WF_CH SET WeekNum=" + DataType.getCurrentWeekGetWeekByDay(dr.getValue(1).toString()) + " WHERE MyPK='" + dr.getValue(0).toString() + "'";
                DBAccess.RunSQL(sql);
            }

            ///#endregion  增加week字段.


            ///#region 检查数据源.
            SFDBSrc src = new SFDBSrc();
            src.setNo("local");
            src.setName("本机数据源");
            if (src.RetrieveFromDBSources() == 0)
            {
                src.Insert();
            }

            ///#endregion 检查数据源.


            ///#region 20170613.增加审核组件配置项"是否显示退回的审核信息”对应字段 by:liuxianchen
            try
            {
                if (DBAccess.IsExitsTableCol("WF_Node", "FWCIsShowReturnMsg") == false)
                {
                    switch (src.getHisDBType())
                    {
                        case MSSQL:
                            DBAccess.RunSQL("ALTER TABLE WF_Node ADD FWCIsShowReturnMsg INT NULL");
                            break;
                        case Oracle:
                        case DM:
                        case Informix:
                        case PostgreSQL:
                        case HGDB:
                        case UX:
                        case KingBaseR3:
                        case KingBaseR6:
                        case GBASE8CByOracle:
                            DBAccess.RunSQL("ALTER TABLE WF_Node ADD FWCIsShowReturnMsg INTEGER NULL");
                            break;
                        case MySQL:
                        case GBASE8CByMySQL:
                        case GBASE8A:
                            DBAccess.RunSQL("ALTER TABLE WF_Node ADD FWCIsShowReturnMsg INT NULL");
                            break;
                        default:
                            break;
                    }

                    DBAccess.RunSQL("UPDATE WF_Node SET FWCIsShowReturnMsg = 0");
                }
            }
            catch (java.lang.Exception e)
            {
            }

            ///#endregion

            ///#region 20170522.增加SL表单设计器中对单选/复选按钮进行字体大小调节的功能 by:liuxianchen

            FrmRB frmRB = new FrmRB();
            frmRB.CheckPhysicsTable();

            try
            {
                DataTable columns = src.GetColumns("Sys_FrmRB");
                if (columns.Select("No='AtPara'").length == 0)
                {
                    switch (src.getHisDBType())
                    {
                        case MSSQL:
                            DBAccess.RunSQL("ALTER TABLE Sys_FrmRB ADD AtPara NVARCHAR(1000) NULL");
                            break;
                        case Oracle:
                        case DM:
                        case KingBaseR3:
                        case KingBaseR6:
                        case GBASE8CByOracle:
                            DBAccess.RunSQL("ALTER TABLE Sys_FrmRB ADD AtPara NVARCHAR2(1000) NULL");
                            break;
                        case PostgreSQL:
                        case UX:
                        case HGDB:
                            DBAccess.RunSQL("ALTER TABLE Sys_FrmRB ADD AtPara VARCHAR2(1000) NULL");
                            break;
                        case MySQL:
                        case Informix:
                        case GBASE8CByMySQL:
                        case GBASE8A:
                            DBAccess.RunSQL("ALTER TABLE Sys_FrmRB ADD AtPara TEXT NULL");
                            break;
                        default:
                            break;
                    }
                }
            }
            catch (java.lang.Exception e2)
            {
            }

            ///#endregion

            ///#region 其他.
            // 更新 PassRate.
            sql = "UPDATE WF_Node SET PassRate=100 WHERE PassRate IS NULL";
            DBAccess.RunSQL(sql);

            ///#endregion 其他.
            ///#region 升级统一规则.

            ///#region 检查必要的升级。
            NodeWorkCheck fwc = new NodeWorkCheck();
            fwc.CheckPhysicsTable();

            Flow myfl = new Flow();
            myfl.CheckPhysicsTable();

            Node nd = new Node();
            nd.CheckPhysicsTable();

            //Sys_SFDBSrc
            SFDBSrc sfDBSrc = new SFDBSrc();
            sfDBSrc.CheckPhysicsTable();

            ///#endregion 检查必要的升级。
            MapExt mapExt = new MapExt();
            mapExt.CheckPhysicsTable();

            ///#endregion


            ///#region 其他.
            //升级表单树. 2015.10.05
            SysFormTree sft = new SysFormTree();
            sft.CheckPhysicsTable();


            //表单信息表.
            MapDataExt mapext = new MapDataExt();
            mapext.CheckPhysicsTable();

            TransferCustom tc = new TransferCustom();
            tc.CheckPhysicsTable();

            //增加部门字段。
            CCList cc = new CCList();
            cc.CheckPhysicsTable();

            ///#endregion 其他.


            ///#region 升级sys_sftable
            //升级
            SFTable tab = new SFTable();
            tab.CheckPhysicsTable();

            ///#endregion


            ///#region 基础数据更新.
            Node wf_Node = new Node();
            wf_Node.CheckPhysicsTable();
            // 设置节点ICON.
            sql = "UPDATE WF_Node SET ICON='审核.png' WHERE ICON IS NULL";
            DBAccess.RunSQL(sql);

            NodeSheet nodeSheet = new NodeSheet();
            nodeSheet.CheckPhysicsTable();


            ///#endregion 基础数据更新.


            ///#region 升级SelectAccper

            SelectAccper selectAccper = new SelectAccper();
            selectAccper.CheckPhysicsTable();

            ///#endregion


            ///#region  升级 NodeToolbar
            FrmField ff = new FrmField();
            ff.CheckPhysicsTable();

            SysFormTree ssft = new SysFormTree();
            ssft.CheckPhysicsTable();

            FrmAttachment myath = new FrmAttachment();
            myath.CheckPhysicsTable();

            FrmField ffs = new FrmField();
            ffs.CheckPhysicsTable();

            ///#endregion


            ///#region 执行sql．
            DBAccess.RunSQL("delete  from " + bp.sys.base.Glo.SysEnum() + " WHERE EnumKey in ('PrintFileType','EventDoType','FormType','BatchRole','StartGuideWay','NodeFormType')");
            DBAccess.RunSQL("UPDATE Sys_FrmSln SET FK_Flow =(SELECT FK_FLOW FROM WF_Node WHERE NODEID=Sys_FrmSln.FK_Node) WHERE FK_Flow IS NULL");

            if (SystemConfig.getAppCenterDBType() == DBType.MSSQL)
            {
                DBAccess.RunSQL("UPDATE WF_FrmNode SET MyPK=FK_Frm+'_'+convert(varchar,FK_Node )+'_'+FK_Flow");
            }

            if (SystemConfig.getAppCenterDBType() == DBType.DM ||SystemConfig.getAppCenterDBType() == DBType.Oracle || SystemConfig.getAppCenterDBType() == DBType.HGDB || SystemConfig.getAppCenterDBType() == DBType.PostgreSQL || SystemConfig.getAppCenterDBType() == DBType.UX || SystemConfig.getAppCenterDBType() == DBType.KingBaseR3 || SystemConfig.getAppCenterDBType() == DBType.KingBaseR6||  SystemConfig.getAppCenterDBType() == DBType.GBASE8CByOracle)
            {
                DBAccess.RunSQL("UPDATE WF_FrmNode SET MyPK=FK_Frm||'_'||FK_Node||'_'||FK_Flow");
            }

            if (SystemConfig.getAppCenterDBType() == DBType.MySQL ||  SystemConfig.getAppCenterDBType() == DBType.GBASE8CByMySQL  ||  SystemConfig.getAppCenterDBType() == DBType.GBASE8A)
            {
               // DBAccess.RunSQL("UPDATE WF_FrmNode SET MyPK=CONCAT(FK_Frm,'_',FK_Node,'_',FK_Flow)");
            }


            ///#endregion


            ///#region 执行更新.wf_node
            sql = "UPDATE WF_Node SET FWCType=0 WHERE FWCType IS NULL";
            sql += "@UPDATE WF_Node SET FWC_H=0 WHERE FWC_H IS NULL";
            DBAccess.RunSQLs(sql);

            sql = "UPDATE WF_Node SET SFSta=0 WHERE SFSta IS NULL";
            sql += "@UPDATE WF_Node SET SF_H=0 WHERE SF_H IS NULL";
            DBAccess.RunSQLs(sql);

            ///#endregion 执行更新.


            ///#region 升级站内消息表 2013-10-20
            bp.wf.SMS sms = new SMS();
            sms.CheckPhysicsTable();

            ///#endregion


            ///#region 重新生成view WF_EmpWorks,  2013-08-06.
            if (SystemConfig.getCCBPMRunModel() == CCBPMRunModel.Single)
            {
                if (DBAccess.IsExitsObject("WF_EmpWorks") == true)
                {
                    DBAccess.RunSQL("DROP VIEW WF_EmpWorks");
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

                if (DBAccess.IsExitsObject("V_WF_Delay") == true)
                {
                    DBAccess.RunSQL("DROP VIEW V_WF_Delay");
                }
                if (DBAccess.IsExitsObject("WF_Todolist") == true)
                {
                    DBAccess.RunSQL("DROP VIEW WF_Todolist");
                }

                String sqlscript = "";
                //执行必须的sql.
                switch (SystemConfig.getAppCenterDBType())
                {
                    case Oracle:
                    case DM:
                    case KingBaseR3:
                    case KingBaseR6:
                    case GBASE8CByOracle:
                        sqlscript = SystemConfig.getPathOfData() + "Install/SQLScript/InitView_Ora.sql";
                        break;
                    case MSSQL:
                    case Informix:
                        sqlscript = SystemConfig.getPathOfData() + "Install/SQLScript/InitView_SQL.sql";
                        break;
                    case MySQL:
                    case GBASE8CByMySQL:
                    case GBASE8A:
                        sqlscript = SystemConfig.getPathOfData() + "Install/SQLScript/InitView_MySQL.sql";
                        break;
                    case PostgreSQL:
                    case UX:
                    case HGDB:
                        sqlscript = SystemConfig.getPathOfData() + "Install/SQLScript/InitView_PostgreSQL.sql";
                        break;
                    default:
                        break;
                }
                DBAccess.RunSQLScript(sqlscript);
            }

            ///#endregion


            ///#region 升级Img
            FrmImg img = new FrmImg();
            img.CheckPhysicsTable();

            ExtImg myimg = new ExtImg();
            myimg.CheckPhysicsTable();
            if (DBAccess.IsExitsTableCol("Sys_FrmImg", "SrcType") == true)
            {
                DBAccess.RunSQL("UPDATE Sys_FrmImg SET ImgSrcType = SrcType WHERE ImgSrcType IS NULL");
                DBAccess.RunSQL("UPDATE Sys_FrmImg SET ImgSrcType = 0 WHERE ImgSrcType IS NULL");
            }

            ///#endregion


            ///#region 修复 mapattr UIHeight, UIWidth 类型错误.
            switch (SystemConfig.getAppCenterDBType())
            {
                case Oracle:
                case DM:
                case KingBaseR3:
                case KingBaseR6:
                case GBASE8CByOracle:
                    msg = "@Sys_MapAttr 修改字段";
                    break;
                case MSSQL:
                    msg = "@修改sql server控件高度和宽度字段。";
                    DBAccess.RunSQL("ALTER TABLE Sys_MapAttr ALTER COLUMN UIWidth float");
                    DBAccess.RunSQL("ALTER TABLE Sys_MapAttr ALTER COLUMN UIHeight float");
                    break;
                default:
                    break;
            }

            ///#endregion


            ///#region 升级常用词汇
            switch (SystemConfig.getAppCenterDBType())
            {
                case Oracle:
                case DM:
                case KingBaseR3:
                case KingBaseR6:
                case GBASE8CByOracle:
                    int i = DBAccess.RunSQLReturnCOUNT("SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'SYS_DEFVAL' AND COLUMN_NAME = 'PARENTNO'");
                    if (i == 0)
                    {
                        if (DBAccess.IsExitsObject("SYS_DEFVAL") == true)
                        {
                            DBAccess.RunSQL("drop table Sys_DefVal");
                        }

                        DefVal dv = new DefVal();
                        dv.CheckPhysicsTable();
                    }
                    msg = "@Sys_DefVal 修改字段";
                    break;
                case MSSQL:
                    msg = "@修改 Sys_DefVal。";
                    i = DBAccess.RunSQLReturnCOUNT("SELECT * FROM SYSCOLUMNS WHERE ID=OBJECT_ID('Sys_DefVal') AND NAME='ParentNo'");
                    if (i == 0)
                    {
                        if (DBAccess.IsExitsObject("Sys_DefVal") == true)
                        {
                            DBAccess.RunSQL("drop table Sys_DefVal");
                        }

                        DefVal dv = new DefVal();
                        dv.CheckPhysicsTable();
                    }
                    break;
                default:
                    break;
            }

            ///#endregion


            ///#region 登录更新错误
            msg = "@登录时错误。";
            DBAccess.RunSQL("DELETE FROM " + bp.sys.base.Glo.SysEnum() + " WHERE EnumKey IN ('DeliveryWay','RunModel','OutTimeDeal','FlowAppType')");

            ///#endregion 登录更新错误


            ///#region 升级表单树
            // 首先检查是否升级过.
            //sql = "SELECT * FROM Sys_FormTree WHERE No = '1'";
            //DataTable formTree_dt = DBAccess.RunSQLReturnTable(sql);
            //if (formTree_dt.Rows.size() == 0)
            //{
            //    /*没有升级过.增加根节点*/
            //    SysFormTree formTree = new SysFormTree();
            //    formTree.setNo("1";
            //    formTree.setName("表单库";
            //    formTree.ParentNo = "0";
            //    // formTree.TreeNo = "0";
            //    formTree.setIdx(0;
            //    formTree.IsDir = true;
            //        formTree.DirectInsert();

            //    //将表单库中的数据转入表单树
            //    SysFormTrees formSorts = new SysFormTrees();
            //    formSorts.RetrieveAll();

            //    foreach (SysFormTree item in formSorts)
            //    {
            //        if (item.getNo() == "0")
            //            continue;
            //        SysFormTree subFormTree = new SysFormTree();
            //        subFormTree.setNo(item.getNo();
            //        subFormTree.setName(item.Name;
            //        subFormTree.ParentNo = "0";
            //        subFormTree.Save();
            //    }
            //    //表单于表单树进行关联
            //    sql = "UPDATE Sys_MapData SET FK_FormTree=FK_FrmSort WHERE FK_FrmSort <> '' AND FK_FrmSort IS not null";
            //    DBAccess.RunSQL(sql);
            //}

            ///#endregion


            ///#region 执行admin登录. 2012-12-25 新版本.
            Emp emp = new Emp("admin");
            if (emp.RetrieveFromDBSources() == 1)
            {
                WebUser.SignInOfGener(emp, "CH", false, false, null, null);
            }
            else
            {
                emp.SetValByKey("No", "admin");
                emp.setName("admin");
                emp.setDeptNo("01");
                emp.setPass("123");
                emp.Insert();
                WebUser.SignInOfGener(emp, "CH", false, false, null, null);
                //throw new Exception("admin 用户丢失，请注意大小写。");
            }

            ///#endregion 执行admin登录.
            ///#region 升级枚举值 - 兼容H5版本.
            DBAccess.RunSQL("UPDATE Sys_EnumMain SET AtPara ='@EnName=TS.FrmUI.SysEnumMainInt' WHERE AtPara IS NULL OR AtPara =''");
            DBAccess.RunSQL("UPDATE Sys_EnumMain SET EnumKey =No WHERE EnumKey IS NULL OR EnumKey =''");

            SysEnumMains mains = new SysEnumMains();
            mains.RetrieveInSQL("No", "SELECT no FROM Sys_EnumMain WHERE Val0 = '' OR Val0 IS NULL ");
            for (SysEnumMain en : mains.ToJavaList())
                en.DoInitDtls();
            ///#endregion

            ///#region 修复 Sys_FrmImg 表字段 ImgAppType Tag0
            switch (SystemConfig.getAppCenterDBType())
            {
                case Oracle:
                case DM:
                case KingBaseR3:
                case KingBaseR6:
                case GBASE8CByOracle:
                    int i = DBAccess.RunSQLReturnCOUNT("SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'SYS_FRMIMG' AND COLUMN_NAME = 'TAG0'");
                    if (i == 0)
                    {
                        DBAccess.RunSQL("ALTER TABLE SYS_FRMIMG ADD (ImgAppType number,TAG0 nvarchar(500))");
                    }
                    msg = "@Sys_FrmImg 修改字段";
                    break;
                case MSSQL:
                    msg = "@修改 Sys_FrmImg。";
                    i = DBAccess.RunSQLReturnCOUNT("SELECT * FROM SYSCOLUMNS WHERE ID=OBJECT_ID('Sys_FrmImg') AND NAME='Tag0'");
                    if (i == 0)
                    {
                        DBAccess.RunSQL("ALTER TABLE Sys_FrmImg ADD ImgAppType int");
                        DBAccess.RunSQL("ALTER TABLE Sys_FrmImg ADD Tag0 nvarchar(500)");
                    }
                    break;
                default:
                    break;
            }
            FrmImgAth imgAth = new FrmImgAth();
            imgAth.CheckPhysicsTable();

            sql = "UPDATE Sys_FrmImgAth SET IsRequired = 0 WHERE IsRequired IS NULL";
            DBAccess.RunSQL(sql);
            try
            {
                DataTable columns = src.GetColumns("Sys_FrmAttachment");
                if (columns.Select("No='DeleteWay'").length > 0 && columns.Select("No='IsDelete'").length > 0)
                {
                    DBAccess.RunSQL("UPDATE SYS_FRMATTACHMENT SET DeleteWay=IsDelete WHERE DeleteWay IS NULL");
                }
            }
            catch (java.lang.Exception e3)
            {
            }
            ///#region 密码加密
            if (SystemConfig.getIsEnablePasswordEncryption() == true && DBAccess.IsView("Port_Emp", SystemConfig.getAppCenterDBType()) == false)
            {
                Emps emps = new Emps();
                emps.RetrieveAllFromDBSource();
                for (Emp empEn : emps.ToJavaList())
                {
                    if (DataType.IsNullOrEmpty(empEn.getPass()) || empEn.getPass().length() < 30)
                    {
                        empEn.setPass(bp.tools.MD5Utill.MD5Encode(empEn.getPass(),"UTF8"));
                        empEn.DirectUpdate();
                    }
                }
            }

            ///#endregion

            // 最后更新版本号，然后返回.
            sql = "UPDATE Sys_Serial SET IntVal=" + Ver + " WHERE CfgKey='Ver'";
            if (DBAccess.RunSQL(sql) == 0)
            {
                sql = "INSERT INTO Sys_Serial (CfgKey,IntVal) VALUES('Ver'," + Ver + ") ";
                DBAccess.RunSQL(sql);
            }
            // 返回版本号.
            return "旧版本:(" + currDBVer + ")新版本:(" + Ver + ")"; // +"\t\n解决问题:" + updataNote;
        }
        catch (RuntimeException ex)
        {
            String err = "问题出处:" + ex.getMessage() + "<hr>" + msg + "<br>详细信息:@" + ex.getStackTrace() + "<br>@<a href='../DBInstall.htm' >点这里到系统升级界面。</a>";
            Log.DebugWriteError("系统升级错误:" + err);
            return err;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void executUpdateDefaultVal(ArrayList arrayList) throws Exception {

        for (int i = 0; i < arrayList.size(); i++){
            Object tmp = arrayList.get(i);
            Entity en = tmp instanceof  Entity ? (Entity)tmp:null;
            if(DataType.IsNullOrEmpty(en.toString())){
                continue;
            }
            Attrs attrs = en.getEnMap().getAttrs();
            for (int j = 0; j < attrs.size(); j++){
                Attr attr = attrs.get(j);
                switch (attr.getMyDataType())
                {
                    case DataType.AppInt:
                    case DataType.AppBoolean:
                    case DataType.AppFloat:
                    case DataType.AppMoney:
                    case DataType.AppDouble:
                        if(attr.getDefaultVal() != null) {
                            if(DBAccess.IsExitsTableCol(en.getEnMap().getPhysicsTable(), attr.getField())){
                                DBAccess.RunSQL("ALTER TABLE " + en.getEnMap().getPhysicsTable() + " ALTER COLUMN " + attr.getField() + " SET DEFAULT " + attr.getDefaultVal());
                            }else {
                                String dataType = "";
                                if(attr.getMyDataType() == DataType.AppInt || attr.getMyDataType() == DataType.AppBoolean){
                                    dataType = "INT";
                                }else {
                                    dataType = "FLOAT";
                                }
                                if(en.getIsExits() == false)
                                    continue;

                                DBAccess.RunSQL("ALTER TABLE " + en.getEnMap().getPhysicsTable() + " ADD COLUMN " + attr.getField() + " " +  dataType +" DEFAULT " + attr.getDefaultVal());
                                Log.DebugWriteInfo("修复了表["+en.getEnMap().getPhysicsTable()+"]字段["+attr.getField()+"]的默认值，修改为["+attr.getDefaultVal()+"]");
                            }
                        }
                }
            }


        }

    }

    /**
     如果发现升级sql文件日期变化了，就自动升级.
     就是说该文件如果被修改了就会自动升级.
     */
    public static void UpdataCCFlowVerSQLScript()
    {

        String sql = "SELECT IntVal FROM Sys_Serial WHERE CfgKey='UpdataCCFlowVer'";
        String currDBVer = DBAccess.RunSQLReturnStringIsNull(sql, "");

        String sqlScript = SystemConfig.getPathOfData() + "UpdataCCFlowVer.sql";
        if(SystemConfig.getAppCenterDBType()==DBType.KingBaseR6
                ||SystemConfig.getAppCenterDBType()==DBType.KingBaseR3){
            sqlScript = SystemConfig.getPathOfData() + "UpdataCCFlowVerForKingBase.sql";
        }
        File fi = new File(sqlScript);
        if ((new File(sqlScript)).isFile() == false)
        {
            return;
        }
        long lastModified = fi.lastModified();
        Date date = new Date(lastModified);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        String myVer = sdf.format(date);

        //判断是否可以执行，当文件发生变化后，才执行。
        if (Objects.equals(currDBVer, "") || Integer.parseInt(currDBVer) < Integer.parseInt(myVer))
        {
            DBAccess.RunSQLScript(sqlScript);
            sql = "UPDATE Sys_Serial SET IntVal=" + myVer + " WHERE CfgKey='UpdataCCFlowVer'";

            if (DBAccess.RunSQL(sql) == 0)
            {
                sql = "INSERT INTO Sys_Serial (CfgKey,IntVal) VALUES('UpdataCCFlowVer'," + myVer + ") ";
                DBAccess.RunSQL(sql);
            }
        }
    }
    public static void InitDBSrc_Loacl() throws Exception {
        SFTable table = new SFTable();

        ///#region 字典 - 无参数的字典.
        int idx = 0;
        table.setNo("PianQu");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0)
        {
            table.setName("片区");
            table.setSelectStatement("SELECT No,Name FROM Demo_PQ ");
            table.setSFDBSrcNo("local");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }

        table.setNo("ShengFen");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.setName("全部省份");
            table.setSelectStatement("SELECT No,Name FROM Demo_ShengFen");
            table.setSFDBSrcNo("local");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            if(table.getIsExits() == false)
                table.Insert();
        }
        table.setNo("City");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0)
        {
            table.setName("全部城市");
            table.setSelectStatement("SELECT No,Name FROM Demo_City");
            table.setSFDBSrcNo("local");
            table.setCodeStruct( CodeStruct.NoName);
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }
        table.setNo("MyDeptEmps");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.setName("我部门的同事");
            table.setSelectStatement("SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'");
            table.setSFDBSrcNo("local");
            table.SetValByKey("DBSrcType", "SQL");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
            table.SetValByKey("FK_SFDBSrc", "local");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }
        table.setNo("MyDepts");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.setName("我的部门");
            table.setSelectStatement("SELECT A.No,A.Name FROM Port_Dept A, Port_DeptEmp B WHERE A.No=B.FK_Dept AND B.FK_Emp='@WebUser.No' ");
            table.setSFDBSrcNo("local");
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            table.SetValByKey("RootNo", "@WebUser.DeptNo");
            table.setCodeStruct(CodeStruct.NoName);
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }
        table.setNo("MyStations");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.setName("我的岗位");
            table.setSelectStatement("SELECT A.No,A.Name FROM Port_Station A, Port_DeptEmpStation B WHERE A.No=B.FK_Station AND B.FK_Emp='@WebUser.No' ");
            table.setSFDBSrcNo("local");
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            table.setCodeStruct(CodeStruct.NoName);
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }

        table.setNo("Depts");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.setName("部门");
            table.setSelectStatement("SELECT No,Name,ParentNo FROM Port_Dept ");
            table.setSFDBSrcNo("local");
            table.setCodeStruct(CodeStruct.Tree);
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLTree");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            table.SetValByKey("RootNo", "100"); //演示环境.
            table.SetValByKey("IsPara", "0");
            table.Insert();
        }
        table.setNo("Depts");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0)
        {
            table.setName("部门");
            table.setSelectStatement("SELECT No,Name,ParentNo FROM Port_Dept ");
            table.SetValByKey("SFDBSrcNo", "local");
            table.setCodeStruct(CodeStruct.Tree);
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLTree");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            table.SetValByKey("RootNo", "100"); //演示环境.
            table.SetValByKey("IsPara", "0");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }
        table.setNo("MySubDepts");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.setName("我的子部门树");
            table.setSelectStatement("SELECT No,Name,ParentNo FROM Port_Dept WHERE ParentNo='@WebUser.DeptNo' OR No='@WebUser.DeptNo' ");
            table.setSFDBSrcNo("local");
            table.setCodeStruct(CodeStruct.Tree);
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLTree");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            table.SetValByKey("RootNo", "@WebUser.DeptNo");
            table.SetValByKey("IsPara", "0");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }
        ///#endregion 字典 - 无参数的字典.

        ///#region 字典 - 有参数的字典.
        table.setNo("DeptEmps");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.setName("人员");
            table.setSelectStatement("SELECT No,Name FROM Port_Emp WHERE FK_Dept='@Key'");
            table.setSFDBSrcNo("local");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            table.SetValByKey("IsPara", "1");
            table.SetValByKey("TestParas", " @Key=100");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }

        table.setNo("SubDepts");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.setName("子部门");
            table.setSelectStatement("SELECT No,Name,ParentNo FROM Port_Dept WHERE ParentNo='@Key'");
            table.setSFDBSrcNo("local");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            table.SetValByKey("IsPara", "1");
            table.SetValByKey("TestParas", " @Key=100");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }


        table.setNo("PianQuPara");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0)
        {
            table.setName("省份(根据片区)");
            table.setSelectStatement("SELECT No,Name FROM Demo_ShengFen WHERE FK_PQ='@Key'");
            table.setSFDBSrcNo("local");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            table.SetValByKey("IsPara", "1");
            table.SetValByKey("TestParas", "@Key=DB");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }

        table.setNo("CityPara");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0)
        {
            table.setName("城市(根据省份)");
            table.setSelectStatement("SELECT No,Name FROM Demo_City WHERE ShengFen='@Key'");
            table.setSFDBSrcNo("local");
            table.setCodeStruct( CodeStruct.NoName);
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
            table.SetValByKey("FK_SFDBSrc", "local");
            table.SetValByKey("DBSrcType", "SQL");
            table.SetValByKey("IsPara", "1");
            table.SetValByKey("TestParas", "@Key=50");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }
        ///#endregion 字典 - 有参数的字典.
        SFSearch search = new SFSearch();
        ///#region 查询
        search.setNo("MyDeptStations");
        if (search.RetrieveFromDBSources() == 0)
        {
            search.setName("我的部门岗位");
            search.SetValByKey("SelectStatement", "SELECT A.FK_Dept as DeptNo, B.Name as DeptName, A.FK_Station AS StationNo, C.Name as StationName FROM Port_DeptEmpStation A, Port_Dept B, Port_Station C WHERE A.FK_Dept=B.No AND A.FK_Station=C.No AND A.FK_Emp='@WebUser.No'");
            search.SetValByKey("ResultNum", 1); //实体类型.
            search.SetPara("EnName", "TS.FrmUI.SFSearchSQL");
            search.SetValByKey("FK_SFDBSrc", "local");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            search.Insert();
        }

        search.setNo("Emp");
        if (search.RetrieveFromDBSources() == 0)
        {
            search.setName("人员实体");
            search.SetValByKey("SelectStatement", "SELECT No as EmpNo,Name as EmpName,Tel,Email FROM Port_Emp WHERE No='@Key'");
            search.SetValByKey("ResultNum", 0); //实体类型.
            search.SetPara("EnName", "TS.FrmUI.SFSearchSQL");
            search.SetValByKey("FK_SFDBSrc", "local");
            search.SetValByKey("TestParas", "@Key=yuwen");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            search.Insert();
        }
        search.setNo("Emps");
        if (search.RetrieveFromDBSources() == 0)
        {
            search.setName("人员集合");
            search.SetValByKey("SelectStatement", "SELECT No as EmpNo,Name as EmpName,Tel,Email FROM Port_Emp WHERE FK_Dept='@Key'");
            search.SetValByKey("ResultNum", 1); //集合类型.
            search.SetPara("EnName", "TS.FrmUI.SFSearchSQL");
            search.SetValByKey("FK_SFDBSrc", "local");
            search.SetValByKey("TestParas", "@Key=1001");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            search.Insert();
        }

        search.setNo("Student");
        if (search.RetrieveFromDBSources() == 0)
        {
            search.setName("学生实体");
            search.SetValByKey("SelectStatement", "SELECT No as StudentNo,Name as StudentName,Tel,Email,Age,FK_BanJi FROM Demo_Student WHERE No='@Key'");
            search.SetValByKey("ResultNum", 0); //实体类型.
            search.SetPara("EnName", "TS.FrmUI.SFSearchSQL");
            search.SetValByKey("FK_SFDBSrc", "local");
            search.SetValByKey("TestParas", "@Key=0001");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            search.Insert();
        }
        search.setNo("Students");
        if (search.RetrieveFromDBSources() == 0)
        {
            search.setName("学生集合");
            search.SetValByKey("SelectStatement", "SELECT No as StudentNo,Name as StudentName,Tel,Email,Age,FK_BanJi FROM Demo_Student WHERE FK_BanJi='@Key'");
            search.SetValByKey("ResultNum", 1); //集合类型.
            search.SetPara("EnName", "TS.FrmUI.SFSearchSQL");
            search.SetValByKey("FK_SFDBSrc", "local");
            search.SetValByKey("TestParas", "@Key=001");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            search.Insert();
        }
        ///#endregion 查询.

        SFProc porc = new SFProc();

        ///#region 过程 -sql
        porc.setNo("ChenagePass");
        if (porc.RetrieveFromDBSources() == 0) {
            porc.setName("修改密码");
            porc.SetValByKey("SelectStatement", "UPDATE Port_Emp SET Pas11s='@Password' WHERE No='@Key' AND 1=2");
            porc.SetPara("EnName", "TS.FrmUI.SFProcSQL");
            porc.SetValByKey("FK_SFDBSrc", "local");
            idx = idx++;
            porc.SetValByKey("Idx", idx);
            porc.Insert();
        }
        porc.setNo("DisEmp");
        if (porc.RetrieveFromDBSources() == 0)
        {
            porc.setName("禁用人员登录");
            porc.SetValByKey("SelectStatement", "UPDATE Port_Emp SET EmpSta=1 WHERE No='@Key' ");
            porc.SetPara("EnName", "TS.FrmUI.SFProcSQL");
            porc.SetValByKey("FK_SFDBSrc", "local");
            idx = idx++;
            porc.SetValByKey("Idx", idx);
            porc.Insert();
        }
        porc.setNo("UnDisEmp");
        if (porc.RetrieveFromDBSources() == 0)
        {
            porc.setName("启用人员登录");
            porc.SetValByKey("SelectStatement", "UPDATE Port_Emp SET EmpSta=0 WHERE No='@Key' ");
            porc.SetPara("EnName", "TS.FrmUI.SFProcSQL");
            porc.SetValByKey("FK_SFDBSrc", "local");
            idx = idx++;
            porc.SetValByKey("Idx", idx);
            porc.Insert();
        }

        porc.setNo("KickOutUserOnline");
        if (porc.RetrieveFromDBSources() == 0)
        {
            porc.setName("踢出人员登录");
            porc.SetValByKey("SelectStatement", "DELETE FROM Port_Token WHERE EmpNo='@Key' ");
            porc.SetPara("EnName", "TS.FrmUI.SFProcSQL");
            porc.SetValByKey("FK_SFDBSrc", "local");
            idx = idx++;
            porc.SetValByKey("Idx", idx);
            porc.Insert();
        }
        porc.setNo("DeleteMsgByWorkID");
        if (porc.RetrieveFromDBSources() == 0)
        {
            porc.setName("删除流程消息提示");
            porc.SetValByKey("SelectStatement", "DELETE FROM Sys_SMS WHERE WorkID='@Key' ");
            porc.SetPara("EnName", "TS.FrmUI.SFProcSQL");
            porc.SetValByKey("FK_SFDBSrc", "local");
            idx = idx++;
            porc.SetValByKey("Idx", idx);
            porc.Insert();
        }
        ///#endregion 过程
        // #region 枚举
        SysEnumMain sems = new SysEnumMain();
        sems.setNo("XB");
        if (sems.RetrieveFromDBSources() == 0)
        {
            sems.setCfgVal("@0=女@1=男");
            sems.Insert(); //插入.
            sems.DoInitDtls();
        }

        sems = new SysEnumMain();
        sems.setNo("ZZMM");
        if (sems.RetrieveFromDBSources() == 0)
        {
            sems.setCfgVal("@0=群中@1=团员@2=党员@3=无党派人士");
            sems.Insert(); //插入.
            sems.DoInitDtls();
        }
        //#endregion 枚举
    }

    public static void InitDBSrc_LocalWebApi() throws Exception {
        // #region 数据源
        SFDBSrc dbSrc = new SFDBSrc();
        dbSrc.setNo("localWebApi");
        dbSrc.setName("本机WebApi");

        if (dbSrc.RetrieveFromDBSources() == 0)
        {
            //dbSrc.DBSrcType = "WebApi";
            dbSrc.SetValByKey("DBSrcType", "WebApi"); //设置类型.
            dbSrc.SetValByKey("ConnString", SystemConfig.getHostURL());
            dbSrc.SetPara("EnName", "TS.Sys.SFDBSrcWebApi");
            dbSrc.SetValByKey("TokenGetWay", "None");
            dbSrc.SetValByKey("WebApiResultModel", "0");
            dbSrc.DirectInsert();
        }
        //检查环境变量.
        SFApiParas paras = new SFApiParas();
        paras.Retrieve("AttrKey", "Token", "DBSrcNo", dbSrc.getNo());
        if (paras.size() == 0)
        {
            SFApiPara para = new SFApiPara();
            para.SetValByKey("AttrKey", "Token");
            para.SetValByKey("DBSrcNo", "localWebApi");
            para.SetValByKey("AttrName", "(loalWebApi数据源)获取Token");
            para.SetValByKey("ApiParaModel", "1");
            para.SetValByKey("ApiParaStore", "0");
            para.SetValByKey("DataType", "1");
            para.SetValByKey("ExpDoc", "bp.App.Demo.BuessUnit_LocalWebApi_GenerToken");
            para.setMyPK(DBAccess.GenerGUID());
            para.Insert();
        }
        //#endregion 数据源
        String enName = "TS.FrmUI.SFTableWebApiNoName";
        String enNameTree = "TS.FrmUI.SFTableWebApiTree";
        int idx = 0;

        SFTable table = new SFTable();

        ///#region 字典 - 无参数的字典.
        table.setNo("Demo_SFTable_MyDeptEmps");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.ResetDefaultVal();
            table.setNo("Demo_SFTable_MyDeptEmps");

            table.setName("我部门的同事");
            table.setSelectStatement("/WF/API/Demo_SFTable_MyDeptEmps?token=@Token");
            table.SetValByKey("DBSrcType", "WebApi");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetPara("EnName", enName);
            table.SetValByKey("FK_SFDBSrc", "localWebApi");
            table.SetValByKey("FieldNo", "");
            table.SetValByKey("FieldName", "");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }
        table.setNo("Demo_SFTable_MyDepts");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.ResetDefaultVal();
            table.setNo("Demo_SFTable_MyDepts");

            table.setName("我的部门");
            table.setSelectStatement("/WF/API/Demo_SFTable_MyDepts?token=@Token");
            table.setSFDBSrcNo("local");
            table.SetPara("EnName", enName);
            table.SetValByKey("FK_SFDBSrc", "localWebApi");
            table.SetValByKey("DBSrcType", "WebApi");
            table.SetValByKey("RootNo", "@WebUser.DeptNo");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetValByKey("FieldNo", "");
            table.SetValByKey("FieldName", "");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }
        table.setNo("Demo_SFTable_MyStations");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.ResetDefaultVal();
            table.setNo("Demo_SFTable_MyStations");

            table.setName("我的岗位");
            table.setSelectStatement("/WF/API/Demo_SFTable_MyStations?token=@Token");
            table.setSFDBSrcNo( "local");
            table.SetPara("EnName", enNameTree);
            table.SetValByKey("FK_SFDBSrc", "localWebApi");
            table.SetValByKey("DBSrcType", "WebApi");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetValByKey("FieldNo", "");
            table.SetValByKey("FieldName", "");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }

        table.setNo("Demo_SFTable_Depts");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.ResetDefaultVal();
            table.setNo("Demo_SFTable_Depts");

            table.setName("部门");
            table.setSelectStatement("/WF/API/Demo_SFTable_Depts?token=@Token");
            table.setSFDBSrcNo("local");
            table.SetPara("EnName", enNameTree);
            table.SetValByKey("FK_SFDBSrc", "localWebApi");
            table.SetValByKey("DBSrcType", "WebApi");
            table.setCodeStruct(CodeStruct.Tree);
            table.SetValByKey("RootVal", "100");
            table.SetValByKey("FieldNo", "");
            table.SetValByKey("FieldName", "");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }

        table.setNo("Demo_SFTable_ShengFen");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.ResetDefaultVal();
            table.setNo("Demo_SFTable_ShengFen");

            table.setName("省份");
            table.setSelectStatement("/WF/API/Demo_SFTable_ShengFen?token=@Token");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetPara("EnName", enName);
            table.SetValByKey("FK_SFDBSrc", "localWebApi");
            table.SetValByKey("DBSrcType", "WebApi");
            table.SetValByKey("FieldNo", "BianHao");
            table.SetValByKey("FieldName", "MingCheng");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }
        ///#endregion 字典 - 无参数的字典.

        ///#region 字典 - 有参数的字典.
        table.setNo("Demo_SFTable_EmpsByDeptNo");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.ResetDefaultVal();
            table.setNo("Demo_SFTable_EmpsByDeptNo");
            table.setName("根据部门编号获得人员");
            table.setSelectStatement("/WF/API/Demo_SFTable_EmpsByDeptNo?token=@Token&deptNo=@DeptNo");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetPara("EnName", enName);
            table.SetValByKey("FK_SFDBSrc", "localWebApi");
            table.SetValByKey("DBSrcType", "WebApi");
            table.SetValByKey("ParamAlia", "DeptNo=FK_Dept");
            table.SetValByKey("IsPara", "1");
            table.SetValByKey("FieldNo", "");
            table.SetValByKey("FieldName", "");
            table.SetValByKey("TestParas", "@DeptNo=100");
            idx = idx++;
            table.SetValByKey("Idx", idx);

            table.Insert();
        }

        table.setNo("Demo_SFTable_City");
        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single && table.getNo().startsWith(WebUser.getOrgNo() + "_")==false)
            table.setNo(WebUser.getOrgNo() + "_" + table.getNo());
        if (table.RetrieveFromDBSources() == 0) {
            table.ResetDefaultVal();
            table.setNo("Demo_SFTable_City");

            table.setName("城市");
            table.setSelectStatement("/WF/API/Demo_SFTable_City?token=@Token&shengFenNo=@ShengFenNo");
            table.setCodeStruct(CodeStruct.NoName);
            table.SetPara("EnName", enName);
            table.SetValByKey("FK_SFDBSrc", "localWebApi");
            table.SetValByKey("DBSrcType", "WebApi");
            table.SetValByKey("ParamAlia", "ShengFenNo=Key,SF,SFNo,");
            table.SetValByKey("IsPara", "1");
            table.SetValByKey("FieldNo", "BianHao");
            table.SetValByKey("FieldName", "MingCheng");
            table.SetValByKey("TestParas", "@ShengFenNo=53");
            idx = idx++;
            table.SetValByKey("Idx", idx);
            table.Insert();
        }
        //#endregion  字典 - 有参数的字典.

        //#region 查询(待办，在途，抄送. 草稿)
        SFSearch search = new SFSearch();
        search.SetValByKey("No", "Todolist");
        if (search.RetrieveFromDBSources() == 0)
        {
            search.setName("待办");
            search.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            search.SetValByKey("SelectStatement", "/WF/API/DB_Todolist?token=@Token");
            search.SetPara("EnName", "TS.FrmUI.SFSearchWebApi");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            search.Insert();
        }

        //在途
        search.SetValByKey("No", "Runinglist");
        if (search.RetrieveFromDBSources() == 0)
        {
            search.setName("在途");
            search.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            search.SetValByKey("SelectStatement", "/WF/API/DB_Runing?token=@Token");
            search.SetPara("EnName", "TS.FrmUI.SFSearchWebApi");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            search.Insert();
        }

        //抄送
        search.SetValByKey("No", "CClist");
        if (search.RetrieveFromDBSources() == 0)
        {
            search.setName("抄送");
            search.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            search.SetValByKey("SelectStatement", "/WF/API/DB_CCList?token=@Token");
            search.SetPara("EnName", "TS.FrmUI.SFSearchWebApi");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            search.Insert();
        }
        //#endregion 查询.

        //#region 过程(创建workID,发起,退回,移交,设置流程参数,删除,完成,发消息)
        SFProc proc = new SFProc();
        proc.SetValByKey("No", "CreateBlankWorkID");
        if (proc.RetrieveFromDBSources() == 0)
        {
            proc.setName("创建workID");
            proc.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            proc.SetValByKey("SelectStatement", "/WF/API/Node_CreateBlankWorkID?token=@Token&flowNo=@FlowNo");
            proc.SetPara("EnName", "TS.FrmUI.SFProcWebApi");
            proc.SetValByKey("TestParas", "@FlowNo=001");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            proc.Insert();
        }
        //发送
        proc.SetValByKey("No", "Node_SendWork");
        if (proc.RetrieveFromDBSources() == 0)
        {
            proc.setName("发送");
            proc.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            proc.SetValByKey("SelectStatement", "/WF/API/Node_SendWork?token=@Token&workID=@WorkID&toNodeIDStr=@ToNodeIDStr&toEmps=@ToEmps&paras=@Paras&checkNote=@CheckNote");
            proc.SetPara("EnName", "TS.FrmUI.SFProcWebApi");
            proc.SetValByKey("TestParas", "@WorkID=@ToNodeIDStr=@ToEmps=admin@Paras=@CheckNote=审核通过");
            idx = idx++;
            proc.SetValByKey("Idx", idx);
            proc.Insert();
        }

        //退回
        proc.SetValByKey("No", "Node_ReturnWork");
        if (proc.RetrieveFromDBSources() == 0)
        {
            proc.setName("退回");
            proc.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            proc.SetValByKey("SelectStatement", "/WF/API/Node_ReturnWork?token=@Token&workID=@WorkID&returnToNodeIDStr=@ReturnToNodeIDStr&returnToEmp=@ReturnToEmp&msg=@Msg&isBackToThisNode=@IsBackToThisNode");
            proc.SetPara("EnName", "TS.FrmUI.SFProcWebApi");
            proc.SetValByKey("TestParas", "@WorkID=@ReturnToNodeIDStr=@ReturnToEmp=admin@Paras=@Msg=@IsBackToThisNode");
            idx = idx++;
            proc.SetValByKey("Idx", idx);
            proc.Insert();
        }
        //撤销
        proc.SetValByKey("No", "Flow_DoUnSend");
        if (proc.RetrieveFromDBSources() == 0)
        {
            proc.setName("撤销");
            proc.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            proc.SetValByKey("SelectStatement", "/WF/API/Flow_DoUnSend?token=@Token&workIDs=@WorkIDs");
            proc.SetPara("EnName", "TS.FrmUI.SFProcWebApi");
            proc.SetValByKey("TestParas", "@WorkIDs=");
            idx = idx++;
            proc.SetValByKey("Idx", idx);
            proc.Insert();
        }
        //移交
        proc.SetValByKey("No", "Node_Shift");
        if (proc.RetrieveFromDBSources() == 0)
        {
            proc.setName("移交");
            proc.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            proc.SetValByKey("SelectStatement", "/WF/API/Node_Shift?token=@Token&workID=@WorkID&toEmpNo=@ToEmpNo&msg=@Msg");
            proc.SetPara("EnName", "TS.FrmUI.SFProcWebApi");
            proc.SetValByKey("TestParas", "@WorkID=@ToEmpNo=yuwen@Msg=有事外出");
            idx = idx++;
            proc.SetValByKey("Idx", idx);
            proc.Insert();
        }
        //结束
        proc.SetValByKey("No", "Flow_DoFlowOver");
        if (proc.RetrieveFromDBSources() == 0)
        {
            proc.setName("结束");
            proc.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            proc.SetValByKey("SelectStatement", "/WF/API/Flow_DoFlowOver?token=@Token&workIDs=@WorkIDs&stopFlowType=@StopFlowType");
            proc.SetPara("EnName", "TS.FrmUI.SFProcWebApi");
            proc.SetValByKey("TestParas", "@WorkIDs=@StopFlowType=1");
            idx = idx++;
            proc.SetValByKey("Idx", idx);
            proc.Insert();
        }
        //删除
        proc.SetValByKey("No", "Batch_Delete");
        if (proc.RetrieveFromDBSources() == 0)
        {
            proc.setName("删除");
            proc.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            proc.SetValByKey("SelectStatement", "/WF/API/Batch_Delete?token=@Token&workIDs=@WorkIDs");
            proc.SetPara("EnName", "TS.FrmUI.SFProcWebApi");
            proc.SetValByKey("TestParas", "@WorkIDs=");
            idx = idx++;
            proc.SetValByKey("Idx", idx);
            proc.Insert();
        }
        //发送消息
        proc.SetValByKey("No", "Port_SendMessage");
        if (proc.RetrieveFromDBSources() == 0)
        {
            proc.setName("发送消息");
            proc.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            proc.SetValByKey("SelectStatement", "/WF/API/Port_SendMessage?token=@Token&toEmpNo=@ToEmpNo&title=@Title&message=@Message");
            proc.SetPara("EnName", "TS.FrmUI.SFProcWebApi");
            proc.SetValByKey("TestParas", "@ToEmpNo=admin@Title=消息发送测试@Message=消息测试xxxxxx");
            idx = idx++;
            proc.SetValByKey("Idx", idx);
            proc.Insert();
        }
        //#endregion 过程.
    }
    /// <summary>
    /// 新建天气预报数据源
    /// </summary>
    public static void InitDBSrc_Weather() throws Exception {
        //#region 数据源
        SFDBSrc dbSrc = new SFDBSrc();
        dbSrc.setNo("Weather");
        dbSrc.setName("天气预报-和风API");
        if (dbSrc.RetrieveFromDBSources() == 0)
        {
            //dbSrc.DBSrcType = "WebApi";
            dbSrc.SetValByKey("DBSrcType", "WebApi"); //设置类型.
            dbSrc.SetValByKey("ConnString", "https://devapi.qweather.com/v7/weather");
            dbSrc.SetPara("EnName", "TS.Sys.SFDBSrcWebApi");
            dbSrc.SetValByKey("TokenGetWay", "None");
            dbSrc.SetValByKey("WebApiResultModel", "1");
            dbSrc.SetValByKey("WebApiResultObjEnName", "bp.App.Demo.Weather.BuessUnit_ResultObj_WeatherWebApi");
            dbSrc.SetValByKey("WebApiResultObjEnNameT", "ResultObj:天气预报数据结果转换");
            dbSrc.Insert();
        }
        //全局参数.
        SFApiParas paras = new SFApiParas();
        paras.Retrieve("AttrKey", "Key", "DBSrcNo", dbSrc.getNo());
        if (paras.size() == 0)
        {
            SFApiPara para = new SFApiPara();
            para.SetValByKey("AttrKey", "Key");
            para.SetValByKey("DBSrcNo", dbSrc.getNo());
            para.SetValByKey("AttrName", "(天气预报API)获取Key");
            para.SetValByKey("ApiParaModel", "1");
            para.SetValByKey("ApiParaStore", "0");
            para.SetValByKey("DataType", "1");
            para.SetValByKey("ExpDoc", "bp.App.Demo.Weather.BuessUnit_WeatherWebApi_GenerKey");
            para.setMyPK(DBAccess.GenerGUID());
            para.Insert();
        }
        //#endregion 数据源

        //#region 无参数.
        SFSearch search = new SFSearch();
        int idx = 0;
        search.SetValByKey("No", "BJCityWeather");
        if (search.RetrieveFromDBSources() == 0)
        {
            search.setName("北京天气预报");
            search.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            search.SetValByKey("SelectStatement", "3d?location=101010100&key=@Key");
            search.SetPara("EnName", "TS.FrmUI.SFSearchWebApi");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            search.Insert();
        }

        search.SetValByKey("No", "JNCityWeather");
        if (search.RetrieveFromDBSources() == 0)
        {
            search.setName("济南天气预报");
            search.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            search.SetValByKey("SelectStatement", "3d?location=101120101&key=@Key");
            search.SetPara("EnName", "TS.FrmUI.SFSearchWebApi");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            search.Insert();
        }
        //#endregion 无参数.
        // #region 有参数.
        search = new SFSearch();
        search.SetValByKey("No", "SpecCity");
        if (search.RetrieveFromDBSources() == 0)
        {
            search.setName("指定城市");
            search.SetValByKey("FK_SFDBSrc", dbSrc.getNo());
            search.SetValByKey("SelectStatement", "3d?location=@location&key=@Key");
            search.SetValByKey("TestParas", "@location=101120101");
            search.SetPara("EnName", "TS.FrmUI.SFSearchWebApi");
            idx = idx++;
            search.SetValByKey("Idx", idx);
            search.Insert();
        }
        //#endregion 有参数.
    }
}
