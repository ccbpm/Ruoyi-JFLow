package bp.ccbill;

import bp.da.DBAccess;
import bp.da.DataColumn;
import bp.da.DataTable;
import bp.da.DataType;
import bp.en.EditType;
import bp.en.EntityNoName;
import bp.en.Map;
import bp.en.UAC;
import bp.sys.MapAttr;
import bp.sys.MapAttrs;
import bp.sys.MapDataAttr;

public class DBEntity  extends EntityNoName {
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
    public DBEntity()
    {
    }
    /**
     数据源实体

     @param no 映射编号
     */
    public DBEntity(String no) throws Exception {
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

        ///#region 基本属性.
        map.AddGroupAttr("基本属性");
        map.AddTBStringPK(MapDataAttr.No, null, "编号", true, true, 1, 190, 20);
        map.AddTBString(MapDataAttr.Name, null, "名称", true, false, 0, 200, 20, true);
        map.AddDDLSysEnum(MapDataAttr.FrmType, 0, "表单类型", true, true, "BillFrmType", "@0=经典表单@1=自由表单@8=开发者表单");
        ///#endregion 基本属性.

        ///#region 数据源.
        map.AddGroupAttr("数据源");
        map.AddTBString(MapDataAttr.DBSrc, null, "数据源", false, false, 0, 600, 20);
        map.AddTBString(MapDataAttr.ExpEn, null, "SQL", false, false, 0, 600, 20, true);
        ///#endregion 数据源.

        //增加参数字段.
        map.AddTBAtParas(4000);

        this.set_enMap(map);
        return this.get_enMap();
    }
    ///#endregion

    /**
     TS方法调用.

     @return
     */
    public final String CheckIt()
    {

        try
        {
            ///#region 1.重新创建视图.
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

            String sql = "";
            String exp = this.GetValStrByKey("ExpEn").replace("~", "'");
            exp = exp.replace("~", "'");
            exp = exp.replace("~", "'");
            exp = exp.replace("~", "'");
            exp = exp.replace("~", "'");
            exp = exp.replace("~", "'");
            try
            {
                sql = "CREATE VIEW " + this.getNo() + " AS " + exp;
                DBAccess.RunSQL(sql);
            }
            catch (RuntimeException ex)
            {
                return "err@表达式错误：无法创建视图." + ex.getMessage();
            }
            ///#endregion 重新创建视图.

            ///#region 2.检查列是否完整.
            sql = "SELECT * FROM " + this.getNo() + " WHERE 1=1 ";
            DataTable dt = DBAccess.RunSQLReturnTable(sql);
            String msg = "";
            String cols = ",OID,Title,DeptNo,BillNo,";
            String[] strs = cols.split("[,]", -1);
            for (String str : strs)
            {
                if (DataType.IsNullOrEmpty(str) == true)
                {
                    continue;
                }
                if (dt.Columns.contains(str) == false)
                {
                    msg += "" + str + ",";
                }
            }
            if (DataType.IsNullOrEmpty(msg) == false)
            {
                return "err@请检查SQL查询语句不符合要求，如下列不存在:" + msg;
            }
            ///#endregion 检查列是否完整.

            ///#region 3.创建新字段.
            for (DataColumn dc : dt.Columns)
            {
                MapAttr attr = new MapAttr();
                attr.setMyPK(this.getNo() + "_" + dc.ColumnName);
                if (attr.RetrieveFromDBSources() == 1)
                {
                    continue;
                }

                attr.setKeyOfEn(this.getNo());
                attr.setKeyOfEn(dc.ColumnName);

                //获得列的中文名字.
                String name = DBAccess.RunSQLReturnStringIsNull("SELECT Name FROM Sys_MapAttr WHERE KeyOfEn='" + dc.ColumnName + "'", dc.ColumnName);
                attr.setName(name);
                attr.setFrmID(this.getNo());

                if (dc.DataType == Integer.class)
                {
                    attr.setMyDataType(DataType.AppInt);
                }

                if (dc.DataType == Float.class)
                {
                    attr.setMyDataType(DataType.AppFloat);
                }

                if (dc.DataType == java.math.BigDecimal.class)
                {
                    attr.setMyDataType(DataType.AppDouble);
                }

                if (dc.ColumnName.equals("OID") || dc.ColumnName.equals("Rec") || dc.ColumnName.equals("AtPara") || dc.ColumnName.equals("OrgNo") || dc.ColumnName.equals("RDT") || dc.ColumnName.equals("BillState") || dc.ColumnName.equals("FID") || dc.ColumnName.equals("FK_Dept"))
                {
                    attr.setUIVisible(false);
                    attr.setEditType(EditType.UnDel);
                    attr.setUIVisible(false);
                }
                else if (dc.ColumnName.equals("Title"))
                {
                    attr.setEditType(EditType.UnDel);
                }
                else
                {
                }
                //设置不能删除.
                if (cols.contains("," + attr.getKeyOfEn() + ",") == true)
                {
                    attr.setEditType(EditType.UnDel);
                }
                attr.Insert();
                msg += "增加字段:" + dc.ColumnName;
            }
            ///#endregion 创建字段.

            ///#region 4.删除没有的字段.
            MapAttrs attrs = new MapAttrs();
            attrs.Retrieve("FK_MapData", this.getNo());
            for (MapAttr attr : attrs.ToJavaList())
            {
                if (dt.Columns.contains(attr.getKeyOfEn()) == true)
                {
                    continue;
                }
                attr.Delete(); //删除他.
                msg += "@字段：" + attr.getKeyOfEn() + " - " + attr.getName() + "被删除.";
            }
            ///#endregion 创建字段.

            return "检查成功:" + msg;
        }
        catch (Exception ex)
        {
            return "err@" + ex.getMessage();
        }
    }

}

