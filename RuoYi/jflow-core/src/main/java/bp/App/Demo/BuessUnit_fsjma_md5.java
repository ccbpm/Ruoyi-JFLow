package bp.App.Demo;

import bp.da.DBAccess;
import bp.sys.BuessUnitBase;

public class BuessUnit_fsjma_md5 extends BuessUnitBase {

    @Override
    public String getTitle()
    {
        return "方策:把body内容加密成md5数据.";
    }
    /**
     执行的方法,获取token.
     */
    @Override
    public String DoIt()
    {
        return DBAccess.GenerMD5(this.BodyDoc);
    }

}
