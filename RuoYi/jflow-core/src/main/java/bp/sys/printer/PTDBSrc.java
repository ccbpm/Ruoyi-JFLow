package bp.sys.printer;

import bp.en.EntityMyPK;
import bp.en.Map;
import bp.en.UAC;

public class PTDBSrc extends EntityMyPK {
    ///#region  属性
    /**
     UI界面上的访问控制
     */
    @Override
    public UAC getHisUAC()
    {
        UAC uac = new UAC();
        uac.OpenForSysAdmin();
        return uac;
    }
    /**
     编号
     */
    public final String getMyPK()
    {
        String no = GetValStrByKey("MyPK");
        no = no.replace("\n", "");
        no = no.replace(" ", "");
        return no;
    }
    public final void setMyPK(String value)
    {
        SetValByKey("MyPK", value);
    }
    public final int getFrmRefPKModel()
    {
        return GetValIntByKey("FrmRefPKModel");
    }

    public final String getFrmRefPKAttrKey()
    {
        return GetValStringByKey("FrmRefPKAttrKey");
    }
    public final String getFrmRefPKAttrName()
    {
        return GetValStringByKey("FrmRefPKAttrName");
    }

    public final String getFrmID()
    {
        return GetValStringByKey(PTDBSrcAttr.FrmID);
    }
    public final void setFrmID(String value)
    {
        SetValByKey(PTDBSrcAttr.FrmID, value);
    }

    public final String getRefFrmID()
    {
        return GetValStringByKey(PTDBSrcAttr.RefFrmID);
    }
    public final void setRefFrmID(String value)
    {
        SetValByKey(PTDBSrcAttr.RefFrmID, value);
    }
    public final String getRefFrmName()
    {
        return GetValStringByKey(PTDBSrcAttr.RefFrmName);
    }
    public final void setRefFrmName(String value)
    {
        SetValByKey(PTDBSrcAttr.RefFrmName, value);
    }
    public final String getDBTypeID()
    {
        return GetValStringByKey(PTDBSrcAttr.DBTypeID);
    }
    public final void setDBTypeID(String value)
    {
        SetValByKey(PTDBSrcAttr.DBTypeID, value);
    }
    ///#endregion
    ///#region 构造函数
    /**
     数据源
     */
    public PTDBSrc()
    {
    }
    /**
     数据源
     @param mypk 主键
     */
    public PTDBSrc(String mypk) throws Exception {
        super(mypk.replace("\n", "").trim());
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
        Map map = new Map("Sys_FrmPrintTemplateDBSrc", "数据源数据源");
        map.IndexField = PTDBSrcAttr.FrmID;

        map.AddMyPK();

        map.AddTBString("FrmID", null,"表单ID", false, false, 0, 60, 60);
        map.AddTBString("FrmName", null,"表单名称", false, false, 0, 60, 60);
        map.AddTBString("FrmPrintTemplateID", null,"模板ID", false, false, 0, 60, 60);

        //Bill=单据,EntityNoName=实体,SQL=数据源,Img=图片,Ath=附件
        map.AddTBString("DBTypeID", null,"数据源类型ID", true, true, 0, 60, 60);
        map.AddTBString("DBTypeName", null,"类型名称", true, true, 0, 60, 60);

        map.AddTBInt("FrmRefPKModel", 0, "关联主键", false, true);
        map.AddTBString("FrmRefPKAttrKey", null, "主键(宿主表的字段)", true, true, 0, 60, 60);
        map.AddTBString("FrmRefPKAttrName", null, "主键名称", true, true, 0, 60, 60);

        //单据/实体ID
        map.AddTBString("RefFrmID", null,"单据/实体ID", false, true, 0, 60, 60);
        map.AddTBString("RefFrmName", null,"单据/实体名称", false, true, 0, 60, 60);

        //SQL.
        map.AddTBString("DBSrc", null,"数据源", false, true, 0, 500, 60);
        map.AddTBString("SQLSelect", null,"查询表达式", false, true, 0, 500, 60);

        map.AddTBInt("Idx", 0,"Idx", false, false);
        map.AddTBAtParas(4000);
        this.set_enMap(map);
        return this.get_enMap();
    }
    @Override
    protected boolean beforeInsert() throws Exception
    {
        return super.beforeInsert();
    }

}
