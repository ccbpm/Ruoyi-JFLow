package bp.ccbill;

import bp.da.DBAccess;
import bp.da.DataType;
import bp.en.*;
import bp.sys.*;

public class FrmDtlView extends EntityNoName
{
    @Override
    public UAC getHisUAC()
    {
        UAC uac = new UAC();
        uac.OpenForAppAdmin();
        uac.IsDelete = false;
        uac.IsInsert = false;
        return uac;
    }
    /**
     数据源实体
     */
    public FrmDtlView()
    {
    }
    /**
     数据源实体

     @param no 映射编号
     */
    public FrmDtlView(String no) throws Exception {
        super(no);
    }
    /**
     EnMap

     */
    @Override
    public Map getEnMap()
    {
        if (this.get_enMap() != null)
        {
            return this.get_enMap();
        }
        Map map = new Map("Sys_MapData", "数据源实体");

        map.setCodeStruct("4");

        map.setCodeStruct("4");        ///#region 基本属性.
        map.AddGroupAttr("基本属性");
        map.AddTBStringPK(MapDataAttr.No, null, "编号", true, true, 1, 190, 20);
        map.AddTBString(MapDataAttr.Name, null, "名称", true, false, 0, 200, 20, true);
        map.AddTBString(MapDataAttr.PTable, null, "视图名称", true, true, 0, 500, 20, true);
        map.AddGroupAttr("SQL数据源");
        map.AddTBString("FrmNo", null, "实体ID", true, true, 0, 100, 20);
        map.AddTBString("FrmName", null, "实体名称", true, true, 0, 100, 20);
        map.AddTBString("DtlNo", null, "从表ID", true, true, 0, 100, 20);
        map.AddTBString("DtlName", null, "从表名称", true, true, 0, 100, 20);

        map.AddTBStringDoc("FrmFields", null, "主表字段", true, false, true);
        map.AddTBStringDoc("DtlFields", null, "从表字段", true, false, true);
        ///#endregion 数据源.

        map.AddTBString(MapDataAttr.DBSrc, "local", "数据源", true, true, 0, 100, 100);
        map.AddTBStringDoc(MapDataAttr.ExpEn, null, "实体数据源表达式", true, false, true);
        map.AddTBStringDoc(MapDataAttr.ExpList, null, "列表数据源", true, false, true);
        //增加参数字段.
        map.AddTBAtParas(4000);
        this.set_enMap(map);
        return this.get_enMap();
    }
    ///#endregion

    public final String CheckIt() throws Exception {
        String frmID = this.GetValStringByKey("FrmNo"); //主表ID.
        String dtlID = this.GetValStringByKey("DtlNo"); //从表ID.
        MapData md = new MapData(frmID);

        if (md.getEntityType() == EntityType.FrmEntityNoName)
        {
            return CheckIt_EntityNoName();
        }

        return CheckIt_Bill();
    }
    /**
     TS方法调用.
     @return
     */
    public final String CheckIt_EntityNoName()
    {
        try
        {
            ///#region 0.变量定义.
            String frmID = this.GetValStringByKey("FrmNo"); //主表ID.
            String dtlID = this.GetValStringByKey("DtlNo"); //从表ID.

            MapData md = new MapData(frmID);
            MapAttrs mdAttrs = new MapAttrs(frmID);

            MapDtl dtl = new MapDtl(dtlID);
            MapAttrs dtlAttrs = new MapAttrs(dtlID);

            //先检查表单，从表的表的字段
            GEEntityNoName ge = new GEEntityNoName(frmID);
            ge.CheckPhysicsTable();
            GEDtl ged = new GEDtl(dtlID);
            ged.CheckPhysicsTable();

            // 现有的字段.
            MapAttrs attrs = new MapAttrs();
            attrs.Delete("FK_MapData", this.getNo());

            long grouID = DBAccess.RunSQLReturnValInt("SELECT OID FROM Sys_GroupField WHERE FrmID='" + this.getNo() + "' AND CtrlType='' ", 0);
            if (grouID == 0)
            {
                GroupField gf = new GroupField();
                gf.setLab("基本信息");
                gf.setFrmID(this.getNo());
                gf.Insert();
                grouID = gf.getOID();
            }
            String select = "SELECT B.OID, A.No as BillNo,A.Name as Title,A.RecName as StarterName,A.RecNo AS Starter,A.DeptNo,A.OrgNo,A.RDT,A.EntityState as BillState,A.AtPara, 0 as FID";
            String from = " FROM " + md.getPTable() + " A, " + dtl.getPTable() + " B ";
            String where = " WHERE A.No=B.RefPK AND A.EntityState >=1  ";
            String pubs = ",No,Name,StarterName,Starter,DeptNo,OrgNo,RDT,EntityState,AtPara,";

            ///#endregion 0.变量定义.

            ///#region 1. 处理公共字段.
            String[] strs = pubs.split("[,]", -1); //从表ID.
            for (String str : strs)
            {
                if (DataType.IsNullOrEmpty(str) == true)
                {
                    continue;
                }
                //当前数据源里是否存在
                Object tempVar = attrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                MapAttr attr = (MapAttr)((tempVar instanceof MapAttr) ? tempVar : null);
                if (attr == null)
                {
                    //从实体里获取该属性.
                    Object tempVar2 = mdAttrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                    MapAttr mdattr = (MapAttr)((tempVar2 instanceof MapAttr) ? tempVar2 : null);
                    if (mdattr == null)
                    {
                        continue;
                    }
                    if (str.equals("No"))
                    {
                        mdattr.setKeyOfEn("BillNo");
                    }
                    if (str.equals("Name"))
                    {
                        mdattr.setKeyOfEn("Title");
                    }
                    if (str.equals("RecName"))
                    {
                        mdattr.setKeyOfEn("StarterName");
                    }
                    if (str.equals("RecNo"))
                    {
                        mdattr.setKeyOfEn("Starter");
                    }
                    if (str.equals("EntityState"))
                    {
                        mdattr.setKeyOfEn("BillState");
                    }

                    mdattr.setFrmID(this.getNo());
                    mdattr.setMyPK(mdattr.getFrmID() + "_" + str);
                    mdattr.setGroupID(grouID);
                    mdattr.DirectInsert();
                }
            }
            //增加OID字段
            MapAttr mapattr = new MapAttr();
            mapattr.setFrmID(this.getNo());
            mapattr.setKeyOfEn("OID");
            mapattr.setName("OID");
            mapattr.setMyDataType(DataType.AppInt);
            mapattr.setUIContralType(UIContralType.TB);
            mapattr.setLGType(FieldTypeS.Normal);
            mapattr.setUIVisible(false);
            mapattr.setUIIsEnable(false);
            mapattr.setDefVal("0");
            mapattr.setEditType(EditType.Readonly);
            mapattr.setMyPK(mapattr.getFrmID() + "_OID");
            mapattr.setGroupID(grouID);
            mapattr.DirectInsert();
            ///#endregion 1. 处理公共字段.

            ///#region 2. 主表字段..
            String mainTable = "";
            strs = this.GetValStringByKey("FrmFields").split("[,]", -1); //从表ID.
            for (String str : strs)
            {
                if (DataType.IsNullOrEmpty(str) == true)
                {
                    continue;
                }

                //从实体里获取该属性.
                Object tempVar3 = mdAttrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                MapAttr mdattr = (MapAttr)((tempVar3 instanceof MapAttr) ? tempVar3 : null);
                //当前数据源里是否存在
                Object tempVar4 = attrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                MapAttr attr = (MapAttr)((tempVar4 instanceof MapAttr) ? tempVar4 : null);
                //说明 源的Frm已经删除了.
                if (mdattr == null)
                {
                    if (attr != null)
                    {
                        attr.Delete(); //如果当前映射里还有,就把他删除掉.
                        continue;
                    }
                    continue;
                }

                if (attr == null)
                {
                    mdattr.setFrmID(this.getNo());
                    mdattr.setMyPK(mdattr.getFrmID() + "_" + str);
                    mdattr.setGroupID(grouID);
                    mdattr.Insert();

                    //检查是否是外键字段.
                    if (DataType.IsNullOrEmpty(mdattr.getUIBindKey()) == false && mdattr.getMyDataType() == DataType.AppString)
                    {
                        mdattr.setMyPK(mdattr.getFrmID() + "_" + str + "T");
                        mdattr.setUIVisible(false);
                        mdattr.setUIContralType(UIContralType.TB);
                        mdattr.setKeyOfEn(str + "T");
                        mdattr.setGroupID(grouID);
                        mdattr.DirectInsert();
                    }
                }
                //增加到查询里.
                mainTable += ",A." + str;
                //判单是否是外键字段.
                if (DataType.IsNullOrEmpty(mdattr.getUIBindKey()) == false && mdattr.getUIContralType() == UIContralType.DDL && mdattr.getMyDataType() == DataType.AppString)
                {
                    where += " AND " + bp.difference.SystemConfig.getAppCenterDBLengthStr() + "(A." + str + ") >=1 ";
                    where += " AND " + bp.difference.SystemConfig.getAppCenterDBLengthStr() + "(A." + str + "T) >=1 ";
                }
            }
            ///#endregion 2. 主表字段.

            ///#region 3. 从表字段.
            String dtlTable = "";
            strs = this.GetValStringByKey("DtlFields").split("[,]", -1); //从表ID.
            for (String str : strs)
            {
                if (DataType.IsNullOrEmpty(str) == true)
                {
                    continue;
                }

                //从实体里获取该属性.
                Object tempVar5 = dtlAttrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                MapAttr mdattr = (MapAttr)((tempVar5 instanceof MapAttr) ? tempVar5 : null);
                //当前数据源里是否存在
                Object tempVar6 = attrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                MapAttr attr = (MapAttr)((tempVar6 instanceof MapAttr) ? tempVar6 : null);
                //说明 源的Frm已经删除了.
                if (mdattr == null)
                {
                    if (attr != null)
                    {
                        attr.Delete(); //如果当前映射里还有,就把他删除掉.
                        continue;
                    }
                    continue;
                }

                if (attr == null)
                {
                    mdattr.setFrmID(this.getNo());
                    mdattr.setMyPK(mdattr.getFrmID() + "_" + str);
                    mdattr.setGroupID(grouID);
                    mdattr.Insert();

                    //检查是否是外键字段.
                    if (DataType.IsNullOrEmpty(mdattr.getUIBindKey()) == false && mdattr.getMyDataType() == DataType.AppString)
                    {
                        mdattr.setMyPK(mdattr.getFrmID() + "_" + str + "T");
                        mdattr.setUIVisible(false);
                        mdattr.setKeyOfEn(str + "T"); //增加为影子字段.
                        mdattr.setUIBindKey("");
                        mdattr.setUIContralType(UIContralType.TB);
                        mdattr.setGroupID(grouID);
                        mdattr.DirectInsert();
                    }
                }
                dtlTable += ",B." + str;
                //判单是否是外键字段.
                if (DataType.IsNullOrEmpty(mdattr.getUIBindKey()) == false && mdattr.getUIContralType() == UIContralType.DDL && mdattr.getMyDataType() == DataType.AppString)
                {
                    where += " AND " + bp.difference.SystemConfig.getAppCenterDBLengthStr() + "(B." + str + ") >=1 ";
                    where += " AND " + bp.difference.SystemConfig.getAppCenterDBLengthStr() + "(B." + str + "T) >=1 ";
                }
            }
            ///#endregion 2. 主表字段..

            ///#region 4.重新创建视图.
            if (bp.da.DBAccess.IsExitsObject(this.getNo()) == true)
            {
                try
                {
                    DBAccess.RunSQL("DROP VIEW " + this.getNo());
                }
                catch (java.lang.Exception e)
                {
                    DBAccess.RunSQL("DROP TABLE " + this.getNo());
                }
            }

            //增加影子字段.
            attrs = new MapAttrs();
            attrs.Retrieve("FK_MapData", this.getNo());
            String tFields = "";
            for (MapAttr attr : attrs.ToJavaList())
            {
                //如果是外键，外部数据源字段.
                if (attr.getMyDataType() == DataType.AppString && attr.getUIContralType() == UIContralType.DDL && DataType.IsNullOrEmpty(attr.getUIBindKey()) == false)
                {
                    tFields += "," + attr.getKeyOfEn() + "T";
                }
            }

            String exp = select + mainTable + dtlTable + tFields + from + where; //查询表达式.
            try
            {
                String sql = "CREATE VIEW " + this.getNo() + " AS " + exp;
                DBAccess.RunSQL(sql);
            }
            catch (RuntimeException ex)
            {
                return "err@表达式错误：无法创建视图." + ex.getMessage();
            }
            ///#endregion 4.重新创建视图..

            ///#region 5.删除没有的字段.

            String allFields = "," + pubs + this.GetValStringByKey("FrmFields") + "," + this.GetValStringByKey("DtlFields") + ",";
            for (MapAttr attr : attrs.ToJavaList())
            {
                if (allFields.contains("," + attr.getKeyOfEn() + ",") == true)
                {
                    continue;
                }

                //如果是影子字段.
                if (attr.getMyDataType() == DataType.AppString && attr.getUIContralType() == UIContralType.TB && attr.getUIVisible() == false && DataType.IsNullOrEmpty(attr.getUIBindKey()) == false)
                {
                    //判断主字段是否存在？ 不存在就删除.
                    String key = attr.getKeyOfEn().substring(0, attr.getKeyOfEn().length() - 1);
                    Object tempVar7 = attrs.GetEntityByKey("KeyOfEn", key);
                    MapAttr myattr1 = (MapAttr)((tempVar7 instanceof MapAttr) ? tempVar7 : null);
                    if (myattr1 == null)
                    {
                        attr.Delete();
                    }
                    continue;
                }
            }
            ///#endregion 5.删除没有的字段.

            ///#region 6.创建数据源
            this.SetValByKey("DBSrc", "local");
            this.SetValByKey("ExpEn", "SELECT * From " + this.getNo() + " WHERE OID='@Key'");
            this.SetValByKey("ExpList", "SELECT * From " + this.getNo());
            this.Update();
            ///#endregion 6.创建数据源
            return "检查成功";
        }
        catch (Exception ex)
        {
            return "err@" + ex.getMessage();
        }
    }
    /**
     单据检查

     @return
     */
    public final String CheckIt_Bill()
    {
        try
        {
            ///#region 0.变量定义.
            String frmID = this.GetValStringByKey("FrmNo"); //主表ID.
            String dtlID = this.GetValStringByKey("DtlNo"); //从表ID.

            MapData md = new MapData(frmID);
            MapAttrs mdAttrs = new MapAttrs(frmID);

            MapDtl dtl = new MapDtl(dtlID);
            MapAttrs dtlAttrs = new MapAttrs(dtlID);

            //先检查表单，从表的表的字段
            GEEntity ge = new GEEntity(frmID);
            ge.CheckPhysicsTable();
            GEDtl ged = new GEDtl(dtlID);
            ged.CheckPhysicsTable();
            // 现有的字段.
            MapAttrs attrs = new MapAttrs();
            attrs.Delete("FK_MapData", this.getNo());

            long grouID = DBAccess.RunSQLReturnValInt("SELECT OID FROM Sys_GroupField WHERE FrmID='" + this.getNo() + "' AND CtrlType='' ", 0);
            if (grouID == 0)
            {
                GroupField gf = new GroupField();
                gf.setLab("基本信息");
                gf.setFrmID(this.getNo());
                gf.Insert();
                grouID = gf.getOID();
            }
            String select = "SELECT B.OID,A.Title,A.StarterName,A.Starter,A.DeptNo,A.OrgNo,A.RDT,A.BillState,A.BillNo,A.AtPara,A.FID";
            String from = " FROM " + md.getPTable() + " A, " + dtl.getPTable() + " B ";
            String where = " WHERE A.OID=B.RefPK AND A.BillState >=1  ";

            ///#region 1. 处理公共字段.
            String pubs = ",OID,Title,StarterName,Starter,DeptNo,OrgNo,RDT,BillState,BillNo,AtPara,FID,";
            String[] strs = pubs.split("[,]", -1); //从表ID.
            for (String str : strs)
            {
                if (DataType.IsNullOrEmpty(str) == true)
                {
                    continue;
                }
                //当前数据源里是否存在
                Object tempVar = attrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                MapAttr attr = (MapAttr)((tempVar instanceof MapAttr) ? tempVar : null);
                if (attr == null)
                {
                    //从实体里获取该属性.
                    Object tempVar2 = mdAttrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                    MapAttr mdattr = (MapAttr)((tempVar2 instanceof MapAttr) ? tempVar2 : null);
                    if (mdattr == null)
                    {
                        continue;
                    }

                    mdattr.setFrmID(this.getNo());
                    mdattr.setMyPK(mdattr.getFrmID() + "_" + str);
                    mdattr.setGroupID(grouID);
                    mdattr.DirectInsert();
                }
            }
            ///#endregion 1. 处理公共字段.
            ///#region 2. 主表字段..
            String mainTable = "";
            strs = this.GetValStringByKey("FrmFields").split("[,]", -1); //从表ID.
            for (String str : strs)
            {
                if (DataType.IsNullOrEmpty(str) == true)
                {
                    continue;
                }

                //从实体里获取该属性.
                Object tempVar3 = mdAttrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                MapAttr mdattr = (MapAttr)((tempVar3 instanceof MapAttr) ? tempVar3 : null);
                //当前数据源里是否存在
                Object tempVar4 = attrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                MapAttr attr = (MapAttr)((tempVar4 instanceof MapAttr) ? tempVar4 : null);
                //说明 源的Frm已经删除了.
                if (mdattr == null)
                {
                    if (attr != null)
                    {
                        attr.Delete(); //如果当前映射里还有,就把他删除掉.
                        continue;
                    }
                    continue;
                }

                if (attr == null)
                {
                    mdattr.setFrmID(this.getNo());
                    mdattr.setMyPK(mdattr.getFrmID() + "_" + str);
                    mdattr.setGroupID(grouID);
                    mdattr.Insert();

                    //检查是否是外键字段.
                    if (DataType.IsNullOrEmpty(mdattr.getUIBindKey()) == false && mdattr.getMyDataType() == DataType.AppString)
                    {
                        mdattr.setMyPK(mdattr.getFrmID() + "_" + str + "T");
                        mdattr.setUIVisible(false);
                        mdattr.setUIContralType(UIContralType.TB);
                        mdattr.setKeyOfEn(str + "T");
                        mdattr.setGroupID(grouID);
                        mdattr.DirectInsert();
                    }
                }
                //增加到查询里.
                mainTable += ",A." + str;
                //判单是否是外键字段.
                if (DataType.IsNullOrEmpty(mdattr.getUIBindKey()) == false && mdattr.getUIContralType() == UIContralType.DDL && mdattr.getMyDataType() == DataType.AppString)
                {
                    where += " AND " + bp.difference.SystemConfig.getAppCenterDBLengthStr() + "(A." + str + ") >=1 ";
                    where += " AND " + bp.difference.SystemConfig.getAppCenterDBLengthStr() + "(A." + str + "T) >=1 ";
                }
            }
            ///#endregion 2. 主表字段.

            ///#region 3. 从表字段.
            String dtlTable = "";
            strs = this.GetValStringByKey("DtlFields").split("[,]", -1); //从表ID.
            for (String str : strs)
            {
                if (DataType.IsNullOrEmpty(str) == true)
                {
                    continue;
                }

                //从实体里获取该属性.
                Object tempVar5 = dtlAttrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                MapAttr mdattr = (MapAttr)((tempVar5 instanceof MapAttr) ? tempVar5 : null);
                //当前数据源里是否存在
                Object tempVar6 = attrs.GetEntityByKey(MapAttrAttr.KeyOfEn, str);
                MapAttr attr = (MapAttr)((tempVar6 instanceof MapAttr) ? tempVar6 : null);
                //说明 源的Frm已经删除了.
                if (mdattr == null)
                {
                    if (attr != null)
                    {
                        attr.Delete(); //如果当前映射里还有,就把他删除掉.
                        continue;
                    }
                    continue;
                }

                if (attr == null)
                {
                    mdattr.setFrmID(this.getNo());
                    mdattr.setMyPK(mdattr.getFrmID() + "_" + str);
                    mdattr.setGroupID(grouID);
                    mdattr.Insert();

                    //检查是否是外键字段.
                    if (DataType.IsNullOrEmpty(mdattr.getUIBindKey()) == false && mdattr.getMyDataType() == DataType.AppString)
                    {
                        mdattr.setMyPK(mdattr.getFrmID() + "_" + str + "T");
                        mdattr.setUIVisible(false);
                        mdattr.setKeyOfEn(str + "T"); //增加为影子字段.
                        mdattr.setUIBindKey("");
                        mdattr.setUIContralType(UIContralType.TB);
                        mdattr.setGroupID(grouID);
                        mdattr.DirectInsert();
                    }
                }
                dtlTable += ",B." + str;
                //判单是否是外键字段.
                if (DataType.IsNullOrEmpty(mdattr.getUIBindKey()) == false && mdattr.getUIContralType() == UIContralType.DDL && mdattr.getMyDataType() == DataType.AppString)
                {
                    where += " AND " + bp.difference.SystemConfig.getAppCenterDBLengthStr() + "(B." + str + ") >=1 ";
                    where += " AND " + bp.difference.SystemConfig.getAppCenterDBLengthStr() + "(B." + str + "T) >=1 ";
                }
            }
            ///#endregion 2. 主表字段..

            ///#region 4.重新创建视图.
            if (bp.da.DBAccess.IsExitsObject(this.getNo()) == true)
            {
                try
                {
                    DBAccess.RunSQL("DROP VIEW " + this.getNo());
                }
                catch (java.lang.Exception e)
                {
                    DBAccess.RunSQL("DROP TABLE " + this.getNo());
                }
            }

            //增加影子字段.
            attrs = new MapAttrs();
            attrs.Retrieve("FK_MapData", this.getNo());
            String tFields = "";
            for (MapAttr attr : attrs.ToJavaList())
            {
                //如果是外键，外部数据源字段.
                if (attr.getMyDataType() == DataType.AppString && attr.getUIContralType() == UIContralType.DDL && DataType.IsNullOrEmpty(attr.getUIBindKey()) == false)
                {
                    tFields += "," + attr.getKeyOfEn() + "T";
                }
            }

            String exp = select + mainTable + dtlTable + tFields + from + where; //查询表达式.
            try
            {
                String sql = "CREATE VIEW " + this.getNo() + " AS " + exp;
                DBAccess.RunSQL(sql);
            }
            catch (RuntimeException ex)
            {
                return "err@表达式错误：无法创建视图." + ex.getMessage();
            }
            ///#endregion 4.重新创建视图..

            ///#region 5.删除没有的字段.

            String allFields = "," + pubs + this.GetValStringByKey("FrmFields") + "," + this.GetValStringByKey("DtlFields") + ",";
            for (MapAttr attr : attrs.ToJavaList())
            {
                if (allFields.contains("," + attr.getKeyOfEn() + ",") == true)
                {
                    continue;
                }

                //如果是影子字段.
                if (attr.getMyDataType() == DataType.AppString && attr.getUIContralType() == UIContralType.TB && attr.getUIVisible() == false && DataType.IsNullOrEmpty(attr.getUIBindKey()) == false)
                {
                    //判断主字段是否存在？ 不存在就删除.
                    String key = attr.getKeyOfEn().substring(0, attr.getKeyOfEn().length() - 1);
                    Object tempVar7 = attrs.GetEntityByKey("KeyOfEn", key);
                    MapAttr myattr1 = (MapAttr)((tempVar7 instanceof MapAttr) ? tempVar7 : null);
                    if (myattr1 == null)
                    {
                        attr.Delete();
                    }
                    continue;
                }
            }
            ///#endregion 5.删除没有的字段.

            ///#region 6.创建数据源
            this.SetValByKey("DBSrc", "local");
            this.SetValByKey("ExpEn", "SELECT * From "+this.getNo()+" WHERE OID='@Key'");
            this.SetValByKey("ExpList", "SELECT * From " + this.getNo());
            this.Update();
            ///#endregion 6.创建数据源

            return "检查成功";
        }
        catch (Exception ex)
        {
            return "err@" + ex.getMessage();
        }
    }

}
