package bp.sys.printer.DBModel;

import bp.da.DataType;

public class DBEle {
    /**
     字段标记
     */
    public String AttrKey = null;
    /**
     字段名称
     */
    public String AttrName = null;
    /**
     字段值
     */
    public Object AttrValue = null;
    /**
     数据类型
     */
    public int DBDataType = DataType.AppString;
    /**
     数据源的关联表单或查询
     */
    public String RefFrmID = null;
    public DBEle()
    {
    }
}
