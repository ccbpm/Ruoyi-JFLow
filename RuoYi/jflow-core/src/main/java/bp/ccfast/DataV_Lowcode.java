package bp.ccfast;

import bp.ccfast.portal.WinDocModel;
import bp.ccfast.portal.WindowTemplate;
import bp.ccfast.portal.WindowTemplateAttr;
import bp.ccfast.portal.WindowTemplates;
import bp.ccfast.portal.windowext.HtmlVarDtl;
import bp.ccfast.portal.windowext.HtmlVarDtls;
import bp.ccfast.portal.windowext.SSODtls;
import bp.da.*;
import bp.difference.SystemConfig;
import bp.difference.handler.DirectoryPageBase;
import bp.sys.CCBPMRunModel;
import bp.sys.SFDBSrcs;
import bp.web.WebUser;
import bp.wf.Dev2Interface;

/**
 页面功能实体

 */
public class DataV_Lowcode extends DirectoryPageBase
{
    /**
     页面功能实体
     */
    public DataV_Lowcode()
    {
    }

    public final String Init_LowcodePage() throws Exception {
        DataSet ds = new DataSet(); //定义容器.

        String pageID = this.GetRequestVal("PageID");

        WindowTemplates windows = new WindowTemplates();
        windows.Retrieve(WindowTemplateAttr.PageID, pageID, "Idx");

        for (WindowTemplate window : windows.ToJavaList())
        {
            if (window.getWinDocModel().equals(WinDocModel.Html))
            {
                continue;
            }
            if (window.getWinDocModel().equals(WinDocModel.SSO) == true)
            {
                SSODtls dtls = new SSODtls(); //获得明细.
                dtls.Retrieve("RefPK", window.getNo(), "Idx");
                //加入集合.
                DataTable mydt = dtls.ToDataTableField("SSODtls" + window.getNo());
                ds.Tables.add(mydt);
            }
            ///#region 变量文本
            if (window.getWinDocModel().equals(WinDocModel.HtmlVar) == true)
            {
                HtmlVarDtls dtls = new HtmlVarDtls(); //获得明细.
                dtls.Retrieve("RefPK", window.getNo(), "Idx");
                for (HtmlVarDtl dtl : dtls.ToJavaList())
                {
                    if (dtl.getExp0().toLowerCase().contains("select") == false)
                    {
                        continue;
                    }
                    if (DataType.IsNullOrEmpty(window.getWinDocModel()) == true)
                    {
                        continue;
                    }
                    dtl.setExp0(bp.sys.SFDBSrcs.RunSQLReturnTableJson(dtl.getExp0(), dtl.getDBSrc()));
                }

                //加入集合.
                DataTable mydt = dtls.ToDataTableField("HtmlVarDtls" + window.getNo());
                ds.Tables.add(mydt);
            }
            ///#endregion 变量文本

            ///#region 折线图
            if (window.getWinDocModel().equals(WinDocModel.ChartLine) == true || window.getWinDocModel().equals(WinDocModel.ChartRing) == true
                    || window.getWinDocModel().equals(WinDocModel.ChartPie) == true || window.getWinDocModel().equals(WinDocModel.ChartLine) == true
                    || window.getWinDocModel().equals(WinDocModel.ChartZZT) == true || window.getWinDocModel().equals(WinDocModel.Table) == true
                    || window.getWinDocModel().equals(WinDocModel.ChartRose) == true || window.getWinDocModel().equals(WinDocModel.ChartLineAdv) == true
                    || window.getWinDocModel().equals(WinDocModel.ChartRadar) == true)
            {

                if (DataType.IsNullOrEmpty(window.getWinDocModel()) == true)
                {
                    continue;
                }
                window.setDocs(SFDBSrcs.RunSQLReturnTableJson(window.getDocs(), window.getDBSrc()));
                String C0Ens = window.GetValStrByKey("C0Ens");
                if (DataType.IsNullOrEmpty(C0Ens) == false)
                {
                    window.SetValByKey("C0Ens", SFDBSrcs.RunSQLReturnTableJson(C0Ens, window.getDBSrc()));
                }
            }
            if( window.getWinDocModel().equals(WinDocModel.ChartRate) == true){
                String SQLOfFZ = window.GetValStringByKey("SQLOfFZ");
                String SQLOfFM = window.GetValStringByKey("SQLOfFM");
                if(DataType.IsNullOrEmpty(SQLOfFZ) || DataType.IsNullOrEmpty(SQLOfFM) ){
                    continue;
                }
                int fzCount = DBAccess.RunSQLReturnValInt(SQLOfFZ.replace("~","'"));
                int fmCount = DBAccess.RunSQLReturnValInt(SQLOfFM.replace("~","'"));
                window.setDocs("[{\"Name\":\""+window.GetValStringByKey("LabOfFZ")+"\",\"Num\":"+fzCount+"},{\"Name\":\""+window.GetValStringByKey("LabOfFM")+"\",\"Num\":"+fmCount+"}]");

            }
            ///#endregion 折线图

        }

        //增加到json.
        DataTable dt = windows.ToDataTableField("Windows");
        ds.Tables.add(dt);

        //json.
        return bp.tools.Json.ToJson(ds);
    }
}
