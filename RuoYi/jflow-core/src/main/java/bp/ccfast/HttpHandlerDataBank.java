package bp.ccfast;

import bp.ccfast.databank.DataBank;
import bp.da.*;
import bp.difference.handler.DirectoryPageBase;
import bp.sys.*;
import bp.difference.*;
import bp.web.WebUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class HttpHandlerDataBank extends DirectoryPageBase {
    /**
     * 是不是开发状态？
     *
     * @return
     */
    public final boolean IsDev() {
        if (SystemConfig.getHostURL().toLowerCase().contains("http://localhost") == true) {
            return true;
        }
        return false;
    }

    public HttpHandlerDataBank() {

    }

    /**
     * 获得字符串
     *
     * @return
     */
    public final String GenerString() throws Exception {
        String mark = GetRequestVal("Mark");
        if (mark == null) {
            return "";
        }

        if (mark.startsWith("DBSrc.") == false) {
            return "不符合预期的格式.";
        }

        String[] strs = mark.split("[.]", -1);
        String flag = strs[0];
        String dbSrcNo = strs[1];
        String type = strs[2];
        String objID = strs[3];
        String paras = strs[4].replace("[。]",".");

        if(SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single) {
            objID = WebUser.getOrgNo() + "_" + objID;
        }

        if (type.equals("SFTable") == true) {
            SFTable table = new SFTable(objID);
            if (table.RetrieveFromDBSources() == 0) {
                return "err@不存在[" + objID + "]的查询.";
            }

            if (DataType.IsNullOrEmpty(paras) == true) {
                return table.GenerDataOfJson();
            }

            AtPara para = new AtPara(paras);
            return table.GenerDataOfJsonExt(para.getHisHT());
        }

        if (type.equals("Search") == true) {
            SFSearch search = new SFSearch(objID);
            if (search.RetrieveFromDBSources() == 0) {
                return "err@不存在[" + objID + "]的查询.";
            }

            String json = search.GenerDataOfJson(paras); //获得json.
            return json;
        }

        if (type.equals("Proc") == true) {
            throw new RuntimeException("err@没有开发proc的解析.");
        }
        throw new RuntimeException("err@标记错误:" + type);
    }

    /**
     * 压入到数据库把表达式.
     *
     * @return
     */
    public final String DataBank_Push() throws Exception {
        String dbNo = GetRequestVal("DBNo");
        String dbName = GetRequestVal("DBName");
        String boxs = GetRequestVal("DataBoxs");
        String classID = GetRequestVal("ClassID");

        DataBank db = new DataBank();
        db.setNo(classID);
        boolean isInsert = false;
        if (db.RetrieveFromDBSources() == 0) {
            db.SetValByKey("Doc", boxs);
            db.Insert();
            isInsert = true;
        } else {
            if (db.GetValStringByKey("Doc").equals(boxs) == true) {
                return "无需Push.";
            }
        }
        JSONArray jsonObject = JSONArray.fromObject(boxs);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        jsonObject.parallelStream().forEach(obj -> {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            try {
                processData((JSONObject) obj, classID, dbNo);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        if (isInsert == false) {
            db.SetValByKey("Doc", boxs);
            db.Update();
        }
        return "压入成功";
    }

    private void processData(JSONObject obj, String classID, String dbNo) throws Exception {
        String no = obj.getString("No"); //盒子编号.
        String name = obj.getString("Name"); //盒子编号.
        String exp = obj.getString("Exp"); //盒子编号.
        String dbType = obj.getString("DBType"); //盒子编号.
        no = classID + "_" + no; //设置编号.
        if (SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single) {
            no = WebUser.getOrgNo() + "_" + no;
        }
        //如果是字典》
        if (dbType.equals("SFTable") == true) {
            SFTable table = new SFTable();
            table.SetValByKey("No", no);
            if (table.RetrieveFromDBSources() == 0) {
                table.SetValByKey("No", no);
                table.SetValByKey("Name", name);
                table.SetValByKey("SelectStatement", exp);
                table.SetValByKey("FK_SFDBSrc", dbNo);
                table.setSrcType("SQL");
                table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
                table.DirectInsert();
            }
            if (table.getSelectStatement().equals(exp) == true)
                return;

            table.SetValByKey("Name", name);
            table.SetValByKey("SelectStatement", exp);
            table.SetValByKey("FK_SFDBSrc", dbNo);
            table.setSrcType("SQL");
            table.SetPara("EnName", "TS.FrmUI.SFTableSQLNoName");
            table.SetPara("IsDelete", "0");
            table.SetPara("IsDataBank", "0");
            table.Update(); //更新.
        }

        //查询.
        if (dbType.equals("Search") == true) {
            SFSearch search = new SFSearch();

            //其他的都是查询.
            search.SetValByKey("No", no);
            if (search.RetrieveFromDBSources() == 0) {
                search.SetValByKey("No", no);
                search.SetValByKey("Name", name);
                search.SetValByKey("SelectStatement", exp);
                search.SetValByKey("FK_SFDBSrc", dbNo);
                search.SetPara("EnName", "TS.FrmUI.SFSearchSQL");
                search.DirectInsert();
            }

            if (search.getSelectStatement().equals(exp) == true)
                return;
            search.SetValByKey("Name", name);
            search.SetValByKey("SelectStatement", exp);
            search.SetValByKey("FK_SFDBSrc", dbNo);
            search.SetPara("EnName", "TS.FrmUI.SFSearchSQL");
            search.Update(); //更新.
        }

        //过程》
        if (dbType.equals("Proc") == true) {
            SFProc proc = new SFProc();
            proc.SetValByKey("No", no);
            if (proc.RetrieveFromDBSources() == 0) {
                proc.SetValByKey("No", no);
                proc.SetValByKey("Name", name);
                proc.SetValByKey("SelectStatement", exp);
                proc.SetValByKey("FK_SFDBSrc", dbNo);
                proc.SetPara("EnName", "TS.FrmUI.SFProcSQL");
                proc.DirectInsert();
            }

            if (proc.getSelectStatement().equals(exp) == true)
                return;
            proc.SetValByKey("Name", name);
            proc.SetValByKey("SelectStatement", exp);
            proc.SetValByKey("FK_SFDBSrc", dbNo);
            proc.SetPara("EnName", "TS.FrmUI.SFProcSQL");
            proc.Update(); //更新.
        }
    }

}
