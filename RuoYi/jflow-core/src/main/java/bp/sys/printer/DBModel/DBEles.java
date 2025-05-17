package bp.sys.printer.DBModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class DBEles extends ArrayList<Object> implements Serializable {

    public DBEles()
    {
    }
    /**
     获得数据元素
     @param refFrmID
     @param attrKey
     @return
     */
    public final DBEle GetValByKey(String refFrmID, String attrKey)
    {
        for (DBEle item : this.ToJavaList())
        {
            if (Objects.equals(item.AttrKey, attrKey) && item.RefFrmID.equals(refFrmID) == true)
            {
                return item;
            }
        }
        return null;
    }
    public final void Add(DBEle en)
    {
        this.ToJavaList().add(en);
    }
    public final void Add(String attrKey, String attrName, Object val, int dataType, String refFrmID)
    {
        DBEle en = new DBEle();
        en.AttrKey = attrKey;
        en.AttrName = attrName;
        en.AttrValue = val;
        en.DBDataType = dataType;
        en.RefFrmID = refFrmID;
        this.Add(en);
    }
    /**
     转化成 java list,C#不能调用.

     @return List
     */
    public final java.util.List<DBEle> ToJavaList()
    {
        return (java.util.List<DBEle>)(Object)this;
    }
    /**
     转化成list

     @return List
     */
    public final ArrayList<DBEle> Tolist()
    {
        ArrayList<DBEle> list = new ArrayList<DBEle>();
        for (int i = 0; i < this.size(); i++)
        {
            list.add((DBEle)this.get(i));
        }
        return list;
    }
}
