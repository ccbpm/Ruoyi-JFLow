package bp.sys.printer.DBModel;
import java.io.Serializable;
import java.util.ArrayList;

public class DBDtls extends ArrayList<Object>  implements Serializable
{
    /**
     从表s
     */
    public DBDtls()
    {
    }
    /**
     获得数据元素
     @param dtlNo
     @return
     */
    public final DBDtl GetValByKey(String dtlNo)
    {
        for (DBDtl item : this.ToJavaList())
        {
            if (item.No.equals(dtlNo))
            {
                return item;
            }
        }
        return null;
    }
    /**
     增加从表

     @param en
     */
    public final void Add(DBDtl en)
    {
        this.add(en);
    }
    ///#region 为了适应自动翻译成java的需要,把实体转换成List.
    /**
     转化成 java list,C#不能调用.

     @return List
     */
    public final java.util.List<DBDtl> ToJavaList()
    {
        return (java.util.List<DBDtl>)(Object)this;
    }
    /**
     转化成list
     @return List
     */
    public final ArrayList<DBDtl> Tolist()
    {
        ArrayList<DBDtl> list = new ArrayList<DBDtl>();
        for (int i = 0; i < this.size(); i++)
        {
            list.add((DBDtl)this.get(i));
        }
        return list;
    }
}
