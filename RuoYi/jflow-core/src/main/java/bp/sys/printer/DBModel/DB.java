package bp.sys.printer.DBModel;

import bp.da.DataColumn;
import bp.da.DataTable;
import bp.en.Attr;
import bp.en.FieldType;
import bp.sys.*;
import bp.sys.printer.PTDBSrc;
import bp.sys.printer.PTDBSrcs;

public class DB {
    /// <summary>
    /// 数据
    /// </summary>
    public DBEles HisDBEles = null;
    /// <summary>
    /// 从表数据源
    /// </summary>
    public DBDtls HisDBDtls = null;
    /**
     打印数据
     */
    public DB(String frmID, String frmName, String templateID, long workID) throws Exception {
        //所有数据源.
        PTDBSrcs srcs = new PTDBSrcs(templateID); //数据源集合,包含宿主数据源.
        GEEntity en = new GEEntity(frmID, workID); //获得宿主 - 主表数据,为其他数据源提供参数.

        ///#region 1.生成主表数据源.
        this.HisDBEles = new DBEles();
        for (PTDBSrc src : srcs.ToJavaList())
        {
            //如果是查询.
            if (src.getDBTypeID().equals("Search") == true)
            {
                SFSearch sFSearch = new SFSearch(src.getRefFrmID());
                if (sFSearch.getResultNum() == 0)
                {
                    continue; //如果是返回多行数据的.
                }

                //加入到集合里.
                DataTable dt = sFSearch.GenerHisDataTable();
                if (dt.Rows.size() != 1)
                {
                    throw new RuntimeException("err@查询[" + sFSearch.getName() + "，没有获得一行数据，请检查该配置项，为查询提供的参数:" + en.ToJson());
                }
                for (DataColumn dc : dt.Columns)
                {
                    this.HisDBEles.Add(dc.ColumnName, dc.ColumnName, dt.Rows.get(0).get(dc.ColumnName).toString(), 1, src.getRefFrmID());
                }
                continue;
            }
            //单据与实体.
            GEEntity myen = null;
            if (src.getRefFrmID().equals(frmID) == true)
            {
                myen = en; //如果是宿主实体.
            }
            else
            {
                //不需要关联的.
                if (src.getFrmRefPKModel() == 0)
                {
                    myen = new GEEntity(src.getRefFrmID());
                    myen.setPKVal(workID);
                    if (myen.RetrieveFromDBSources() == 0)
                    {
                        throw new RuntimeException("err@请检查关联的单据数据源是否正确,单据[" + src.getRefFrmID() + "," + src.getRefFrmName() + "],打印主键：" + workID);
                    }
                }

                if (src.getFrmRefPKModel() == 1)
                {
                    String pkval = en.GetValStringByKey(src.getFrmRefPKAttrKey()); ////从宿主实体里，求实体主键
                    if (bp.da.DataType.IsNullOrEmpty(pkval) == true)
                    {
                        throw new RuntimeException("err@获取实体数据源[" + src.getRefFrmID() + "," + src.getRefFrmName() + "]出现错误,没有从宿主数据源中获得实体主键.");
                    }
                    myen = new GEEntity(src.getRefFrmID());
                    myen.setPKVal(pkval);
                    if (myen.RetrieveFromDBSources() == 0)
                    {
                        throw new RuntimeException("err@请检查关联的单据数据源是否正确，没有获得数据,关联宿主主键[" + pkval + "]字段[" + src.getFrmRefPKAttrKey() + "],单据[" + src.getRefFrmID() + "," + src.getRefFrmName() + "]。");
                    }
                }

            }
            //遍历实体属性.
            for (Attr attr : myen.getEnMap().getAttrs())
            {
                if (attr.getMyFieldType() == FieldType.Enum)
                {
                    this.HisDBEles.Add(attr.getKey(), attr.getDesc(), myen.GetValRefTextByKey(attr.getKey()), attr.getMyDataType(), src.getRefFrmID());
                    continue;
                }

                if (attr.getMyDataType() == bp.da.DataType.AppBoolean)
                {
                    if (myen.GetValIntByKey(attr.getKey()) == 1)
                    {
                        this.HisDBEles.Add(attr.getKey(), attr.getDesc(), "是", attr.getMyDataType(), src.getRefFrmID());
                    }
                    else
                    {
                        this.HisDBEles.Add(attr.getKey(), attr.getDesc(), "否", attr.getMyDataType(), src.getRefFrmID());
                    }
                    continue;
                }

                //其他类型.
                this.HisDBEles.Add(attr.getKey(), attr.getDesc(), myen.GetValByKey(attr.getKey()), attr.getMyDataType(), src.getRefFrmID());
            }
        }
        ///#endregion 生成主表数据源.
        ///#region 2.生成从表数据源.
        this.HisDBDtls = new DBDtls();
        for (PTDBSrc src : srcs.ToJavaList())
        {
            //如果是查询.
            if (src.getDBTypeID().equals("Search") == true)
            {
                SFSearch sFSearch = new SFSearch(src.getRefFrmID());
                if (sFSearch.getResultNum() == 1)
                {
                    continue; //如果是返回单行数据的.
                }

                //加入到集合里.
                DBDtl dtl = new DBDtl();
                dtl.No = src.getRefFrmID();
                dtl.Name = src.getRefFrmName();
                dtl.HisDB = sFSearch.GenerHisDataTable(en.getRow());
                this.HisDBDtls.Add(dtl);
                continue;
            }

            MapDtls dtls = new MapDtls(src.getRefFrmID());
            for (MapDtl dtl : dtls.ToJavaList())
            {
                GEDtls gEDtls = null;
                //如果是宿主实体.
                if (src.getRefFrmID().equals(frmID) == true)
                {
                    gEDtls = new GEDtls(dtl.getNo(), workID);
                }
                else
                {
                    gEDtls = new GEDtls(dtl.getNo(), Integer.parseInt(this.HisDBEles.GetValByKey(src.getFrmID(), src.getFrmRefPKAttrKey()).AttrValue.toString()));
                }
                //加入到集合里.
                DBDtl mydtl = new DBDtl();
                mydtl.No = dtl.getNo();
                mydtl.Name = dtl.getAlias();
                mydtl.HisDB = gEDtls.ToDataTableField(); // sFSearch.GenerHisDataTable(en.Row);
                this.HisDBDtls.Add(mydtl);
                // this.HisDBEles.Add(key, key, myen.GetValByKey(key), 1, attr.getRefFrmID());
            }
        }
        ///#endregion 生成主表数据源.
    }

    }
