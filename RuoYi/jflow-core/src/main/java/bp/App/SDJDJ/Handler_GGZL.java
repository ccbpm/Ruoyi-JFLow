package bp.App.SDJDJ;

import bp.da.*;
import bp.difference.SystemConfig;
import bp.sys.*;
import bp.tools.DateUtils;
import bp.tools.HttpClientUtil;
import bp.tools.Json;
import bp.web.WebUser;
import bp.wf.Dev2Interface;
import bp.wf.GenerWorkFlowAttr;
import bp.wf.GenerWorkerListAttr;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Handler_GGZL {

    //蓝天云广告违法数据url
    private static String getWeiFaMsg_url="http://ccamp.org.cn/a/shandong/api/mediaMsg";

    private static String temp = SystemConfig.getPathOfTemp();
//    private static String temp = "D://tmp/";

    /**
     构造函数
     */
//ORIGINAL LINE: public Handler_AD()
    public Handler_GGZL()
    {
    }

    /**
     * 下载蓝天云违法数据
     */
    public static void DownloadData(String startTime,String endTime) throws Exception {
        try {

            //审查时间
            //审核结果 0：合法 1：违法
            String judgeStauts="1";
            Map<String, String> importJsonMap = new HashMap<>();
            importJsonMap.put("startTime", startTime);
            importJsonMap.put("endTime", endTime);
            importJsonMap.put("judgeStauts", judgeStauts);
            //入库
            Map<String, String> hMap = new HashMap<>();
            hMap.put("Content-Type", "application/json;charset=UTF-8");
            bp.da.Log.DefaultLogWriteLine(LogType.Info, "获取违法广告信息请求json：" + bp.tools.Json.ToJson(importJsonMap));
            String data= HttpClientUtil.doPost(getWeiFaMsg_url, bp.tools.Json.ToJson(importJsonMap), hMap);
            System.out.println(data);
            bp.da.Log.DefaultLogWriteLine(LogType.Info, "获取违法广告信息返回结果：" + data);
            JSONObject jd = JSONObject.parseObject(data);
            if (jd.get("code").toString().equals("1")==false)
                throw new Exception("err@"+jd.get("msg"));
            if(!jd.containsKey("results")){
                throw new Exception("err@没有数据");
            }
            JSONArray results = JSONArray.parseArray(jd.get("results").toString());
            FileWriter file;
            bp.da.Log.DefaultLogWriteLine(LogType.Info, "项目下载地址：" + temp);

            for (int i = 0; i < results.size(); i++) {
                JSONObject jsonObject = results.getJSONObject(i);
                String  billNo = jsonObject.getString("billNo");
                String fileName = temp +"LTYDATA"+File.separator+billNo+File.separator;
                //附件地址
                String FJfileName =fileName+ "FJ"+File.separator;
                // 1. 判断目录是否存在，如果存在说明已经调度过了。
                if(new File(FJfileName).isDirectory() == false) {
                    // 2. 创建目录。
                    (new File(FJfileName)).mkdirs();
                }
                String WFFJ = jsonObject.getString("WFFJ");
                //4. 读取附件，存储到临时目录。
                JSONArray wffjJsonArray = JSON.parseArray(WFFJ);
                for (int j = 0; j < wffjJsonArray.size(); j++) {
                    JSONObject wffjJsonObject = wffjJsonArray.getJSONObject(j);
                    String name = wffjJsonObject.getString("name");
                    String url = wffjJsonObject.getString("url");
                    String myfilePath = FJfileName+ DBAccess.GenerGUID() + url.substring(url.lastIndexOf("."));
                    //下载附件
                    downloadFile(url, myfilePath);
                }
                //5. 创建json文件
                file = new FileWriter(fileName+billNo+".json");
                file.write(jsonObject.toString());
                file.flush();
            }
        }
        catch (Exception ex){
            bp.da.Log.DefaultLogWriteLine(LogType.Info, "数据入库出现异常："+ ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * 循环本地文件生成流程
     */
    public static void ReadDataAndStartFlow() throws Exception {
        String ltyfiles= temp +"LTYDATA"+File.separator;
        File file = new File(ltyfiles);
        String fileName = "";
        //如果文件夹存在并且有下级目录
        if (file.exists() == false)
            throw new Exception("err@不存在蓝天云总文件夹");
        //使用蓝天云的账号登录.
        bp.wf.Dev2Interface.Port_Login("lty");
        //获取该目录下一级目录
        File[] files = file.listFiles();
        if (files == null)
            throw new Exception("err@蓝天云总文件夹下无文件");
        //循环所有单据目录
        for (File f : files) {
            String billNo="";
            try {
                if (f.isDirectory() == false)
                    continue;
                fileName = f.getName();
                //获取并组织json数据
                String jsonData = getJsonFile(f.getPath(), fileName);
                JSONObject jsonObject = JSONObject.parseObject(jsonData);
                billNo = jsonObject.getString("billNo");
                String sql = "SELECT COUNT(*) FROM ND7Rpt where ltykey='" + billNo + "'";
                if (DBAccess.RunSQLReturnValInt(sql) >= 1)
                    continue;

                //创建流程
                long workid = Dev2Interface.Node_CreateBlankWork("007");
                //主表字段处理
                GEEntityOID en = new GEEntityOID("ND701", workid);
                en.SetValByKey("ZXMC", empt(jsonObject, "ZXMC", false));
                en.SetValByKey("GuangGaoMeiTiMingChe", empt(jsonObject, "GuangGaoMeiTiMingChe", false));
                en.SetValByKey("GuangGaoZhuMingCheng", empt(jsonObject, "GuangGaoZhuMingCheng", false));
                en.SetValByKey("GuangGaoMeiTiDiZhi", empt(jsonObject, "GuangGaoMeiTiDiZhi", false));
                en.SetValByKey("GuangGaoZhuDiZhi", empt(jsonObject, "GuangGaoZhuDiZhi", false));
                en.SetValByKey("GuangGaoMingCheng", empt(jsonObject, "GuangGaoMingCheng", false));
                en.SetValByKey("WeiFaShiZhang", empt(jsonObject, "WeiFaShiZhang", true));
                en.SetValByKey("SQRQ", DateUtils.getCurrentDate());
                en.SetValByKey("WeiFaMiaoShuXinXi", empt(jsonObject, "WeiFaMiaoShuXinXi", false));
                en.SetValByKey("advertisingType", empt(jsonObject, "MeiJieLeiBie", true));
                en.SetValByKey("advertisingTypeT", empt(jsonObject, "MeiJieLeiBieT", false));
                en.SetValByKey("MeiJieLeiBie", empt(jsonObject, "advertisingType", true));
                en.SetValByKey("MeiJieLeiBieT", empt(jsonObject, "advertisingTypeT", false));
                en.SetValByKey("cn_diqu", empt(jsonObject, "cn_diqu", true));
                en.SetValByKey("cn_diquT", empt(jsonObject, "cn_diquT", false));
                en.SetValByKey("cn_quxian", empt(jsonObject, "cn_quxian", true));
                en.SetValByKey("cn_quxianT", empt(jsonObject, "cn_quxianT", false));
                en.SetValByKey("JCRQ", empt(jsonObject, "JCRQ", false));
                en.SetValByKey("ltykey", empt(jsonObject, "billNo", false));
                en.SetValByKey("SQR", WebUser.getName());
                en.setOID(workid);
                en.Update();

                //从表：违规行为从表处理

                JSONArray wfxwJsonObject = JSONArray.parseArray(jsonObject.getString("WFXW"));
                for (int j = 0; j < wfxwJsonObject.size(); j++) {
                    JSONObject wffjJsonObject = wfxwJsonObject.getJSONObject(j);
                    GEDtl dtl = new GEDtl("ND701WeiFaXingWei");
                    dtl.SetValByKey("RefPK", workid);
                    dtl.SetValByKey("WeiGui",empt(wffjJsonObject, "weigui", true));
                    dtl.SetValByKey("WeiGuiT",empt(wffjJsonObject, "weiguiT", false) );
                    dtl.SetValByKey("YiJu", empt(wffjJsonObject, "weifayiju", false));
                    dtl.setOID(0);
                    dtl.InsertAsOID(DBAccess.GenerOID(dtl.getEnMap().getPhysicsTable()));
                }

                //附件上传
                File fjfile = new File(f.getAbsolutePath() + File.separator + "FJ");
                if (!fjfile.exists() || !fjfile.isDirectory()) {
                    bp.da.Log.DefaultLogWriteLine(LogType.Info, "没有附件地址：" + fjfile);
                }else{
                    File[] fjFiles = fjfile.listFiles();
                    for (File fjf : fjFiles) {
                        if (!fjf.isDirectory()) {
                            bp.wf.Dev2Interface.CCForm_AddAth(701, "007", workid, "ND701_ShiPinTuPian", "ND701", fjf.getAbsolutePath(), fjf.getName());
                        }
                    }
                }

                //执行发送.
                bp.wf.Dev2Interface.Node_SendWork("007", workid, null, null, 0, null, WebUser.getNo(), WebUser.getName()
                        , WebUser.getDeptNo(), WebUser.getDeptName(),  empt(jsonObject,"GuangGaoMingCheng",false), 0, 0);
                //修改标题
                String dbstr = SystemConfig.getAppCenterDBVarStr();
                Paras ps = new Paras();
                ps.SQL = "UPDATE WF_GenerWorkFlow SET title=" + dbstr + "title WHERE WorkID=" + dbstr + "WorkID";
                ps.Add("title", empt(jsonObject,"GuangGaoMingCheng",false));
                ps.Add("WorkID", workid);
                DBAccess.RunSQL(ps);
                ps.SQL = "UPDATE nd7rpt SET title=" + dbstr + "title WHERE oid=" + dbstr + "WorkID";
                ps.Add("title",empt(jsonObject,"GuangGaoMingCheng",false));
                ps.Add("WorkID", workid);
                DBAccess.RunSQL(ps);
            } catch (Exception ee) {
                bp.da.Log.DefaultLogWriteLine(LogType.Error, billNo+"蓝天云数据导入错误：" +ee.getMessage() );
            }
        }
    }
   private static String empt(JSONObject jsonObject, String key, boolean isint){
        if(jsonObject.containsKey(key)){
            if(jsonObject.getString(key).equals("")){
             return " ";
            }
            return jsonObject.getString(key);
        }else{
            if(isint){
                return "0";
            }else{
                return " ";
            }
        }
   }
    /**
     * 组织json文件
     * @param path
     * @param billno
     * @return
     */
    private static String getJsonFile(String path, String billno) throws Exception {
        String filePath = path+File.separator+billno+".json";
        String jsonData = "";
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(filePath)), "GBK");
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                jsonData += line;
            }
            return jsonData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    /**
     * 下载附件
     * @param remoteFilePath
     * @param localFilePath
     */
    private static void downloadFile(String remoteFilePath, String localFilePath) {
        URL website = null;
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;
        try {
            website = new URL(remoteFilePath);
            rbc = Channels.newChannel(website.openStream());
            fos = new FileOutputStream(localFilePath);//本地要存储的文件地址 例如：test.txt
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(rbc!=null){
                try {
                    rbc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) throws Exception {
        DownloadData("2023-11-11","2024-11-15");
    }
}
