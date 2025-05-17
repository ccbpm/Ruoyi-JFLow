package bp.sys.printer;

import bp.en.EntitiesMyPK;
import bp.en.Entity;

import java.util.ArrayList;
import java.util.List;

public class PrintTemplates extends EntitiesMyPK {

    ///#region 构造
    /**
     得到它的 Entity
     */
    @Override
    public Entity getNewEntity()
    {
        return new PrintTemplate();
    }
    /**
     打印模板
     */
    public PrintTemplates()
    {
    }
    public PrintTemplates(int nodeID) throws Exception {
        Retrieve(PrintTemplateAttr.NodeID, nodeID);
    }
    public PrintTemplates(String flowNo) throws Exception {
        Retrieve(PrintTemplateAttr.FlowNo, flowNo);
    }
    ///#endregion

    ///#region 为了适应自动翻译成java的需要,把实体转换成List.
    /**
     转化成 java list,C#不能调用.
     @return List
     */
    public final List<PrintTemplate> ToJavaList()
    {
        return (java.util.List<PrintTemplate>)(Object)this;
    }
    /**
     转化成list

     @return List
     */
    public final ArrayList<PrintTemplate> Tolist()
    {
        ArrayList<PrintTemplate> list = new ArrayList<PrintTemplate>();
        for (int i = 0; i < this.size(); i++)
        {
            list.add((PrintTemplate)this.get(i));
        }
        return list;
    }
    ///#endregion 为了适应自动翻译成java的需要,把实体转换成List.

}
