package bp.sys.printer;

import bp.en.EntitiesMyPK;
import bp.en.Entity;
import bp.sys.printer.DBModel.DBDtl;

import java.util.*;

public class PTDBSrcs extends EntitiesMyPK {

//C# TO JAVA CONVERTER TASK: There is no preprocessor in Java:
    ///#region 构造
    /**
     得到它的 Entity
     */
    @Override
    public Entity getNewEntity()
    {
        return new PTDBSrc();
    }
    /**
     数据源
     */
    public PTDBSrcs()
    {
    }
    public PTDBSrcs(String templateID) throws Exception {
        this.Retrieve(PTDBSrcAttr.FrmPrintTemplateID, templateID);

        PrintTemplate template = new PrintTemplate(templateID);
        PTDBSrc src = new PTDBSrc();
        src.setFrmID(template.getFrmID());
        src.setRefFrmID(template.getFrmID());
        this.AddEntity(src);
    }
    ///#endregion

    ///#region 为了适应自动翻译成java的需要,把实体转换成List.
    /**
     转化成 java list,C#不能调用.

     @return List
     */
    public final List<PTDBSrc> ToJavaList()
    {
        return (java.util.List<PTDBSrc>)(Object)this;
    }
    /**
     转化成list
     @return List
     */
    public final ArrayList<PTDBSrc> Tolist()
    {
        ArrayList<PTDBSrc> list = new ArrayList<PTDBSrc>();
        for (int i = 0; i < this.size(); i++)
        {
            list.add((PTDBSrc)this.get(i));
        }
        return list;
    }
    ///#endregion 为了适应自动翻译成java的需要,把实体转换成List.
}
