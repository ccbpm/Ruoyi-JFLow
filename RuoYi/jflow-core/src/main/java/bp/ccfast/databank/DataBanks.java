package bp.ccfast.databank;
import bp.en.EntitiesNoName;
import bp.en.Entity;

import java.util.ArrayList;

public class DataBanks  extends EntitiesNoName {
    /**
     信息
     */
    public DataBanks()
    {
    }
    /**
     得到它的 Entity
     */
    @Override
    public Entity getNewEntity()
    {
        return new DataBank();
    }

    /**
     转化成 java list,C#不能调用.
     @return List
     */
    public final java.util.List<DataBank> ToJavaList()
    {
        return (java.util.List<DataBank>)(Object)this;
    }
    /**
     转化成list
     @return List
     */
    public final ArrayList<DataBank> Tolist()
    {
        ArrayList<DataBank> list = new ArrayList<DataBank>();
        for (int i = 0; i < this.size(); i++)
        {
            list.add((DataBank)this.get(i));
        }
        return list;
    }
}
