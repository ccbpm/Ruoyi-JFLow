var I=Object.defineProperty;var R=(n,s,e)=>s in n?I(n,s,{enumerable:!0,configurable:!0,writable:!0,value:e}):n[s]=e;var m=(n,s,e)=>R(n,typeof s!="symbol"?s+"":s,e);var S=(n,s,e)=>new Promise((i,r)=>{var o=t=>{try{c(e.next(t))}catch(l){r(l)}},y=t=>{try{c(e.throw(t))}catch(l){r(l)}},c=t=>t.done?i(t.value):Promise.resolve(t.value).then(o,y);c((e=e.apply(n,s)).next())});import{P as w,F,B as N,G as d,m as p,D as T}from"./entry/index-B5R3Coa4-1746862693206.js";import{SyncData as P}from"./SyncData-DnrRURbg.js";import{SyncDataField as K}from"./SyncDataField-BV5oJZwH.js";import{GloComm as h}from"./GloComm-B1xAfTWw.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FrmTrack-Ct-No0Nq.js";class Y extends w{constructor(){super("GPN_SyncData");m(this,"APIOfSystem",`
  #### 帮助
  - 选择同步模式: ccbpm提供了三种同步模式.
  - 请仔细阅读每种模式.
  #### 同步到数据源
  - 选择一个数据源,如果列表里没有就配置一个数据源.
  - 选择要同步的表
  - 之后流程字段与表字段的同步关系.
  #### 同步到自定义的API
  - 输入一个API 地址.
  - 系统就会把流程运行的数据同步到这个地址里面去.
  #### 同步到内置的API
  - 系统提供一个内置的API接口，按照设置的同步时间点,系统就会数据写入到这个接口里面去.
  - 您可以重写这个接口，把后把数据写入到指定的位置.
  `);m(this,"APIOfSelf",`
 ### 帮助
 - 请输入API地址。
 - 格式为: \`http://xxx.xxx.xxxx\`

 ### 写入数据说明
- 系统会把流程的\`主表数据\`、\`从表数据\`、\`从表附件数据\`、\`表格附件附件数据\`形成一个JSON传入到您的接口里。
- 您需要接收这个JSON数据实体来处理它。
- 下面是JSON示例：

\`\`\`json
{
  "mainTable": {
    "Emps": "",
    "FK_DeptName": "集团总部",
    "FK_NY": "2024-11",
    "SQDept": "集团总部",
    "SQR": "admin",
    "SQRQ": "2024-11-20",
    "ChuFaShiQiDong": "4324",
    "QingJiaYuanYin": "6565",
    "QingJiaLeiXing": "1",
    "oid": "1853315159"
  },
  "dtls": [
    {
      "dtlNo": "ND401MingXiBiao",
      "dtl": [
        {
          "dtlData": {
            "ZiDuan1": "666",
            "ZiDuan2": "777",
            "Idx": "0"
          },
          "dtlAths": []
        }
      ]
    }
  ],
  "aths": [
    {
      "attachmentid": "ND401_QJCLTJ",
      "athdb": {
        "fileFullName": "D%3A%5CJFlow2024%5Cjflow-web%5Csrc%5Cmain%5Cwebapp%5CDataUser%2FUploadFile%5CND401%5C%2F1853315159%2Fbe4b3ad8a85740df950bd029bf46d055.pdf",
        "fileName": "NewPortableDocument1.pdf",
        "sort": "",
        "fileExts": "pdf",
        "rdt": "11-20 10:42",
        "myPK": "be4b3ad8a85740df950bd029bf46d055",
        "refPKVal": "1853315159",
        "rec": "admin",
        "recName": "admin",
        "fk_dept": "100",
        "fk_deptName": "集团总部"
      }
    }
  ]
}
\`\`\`

#### JSON说明
- \`mainTable\` 为主表数据，包含表单中字段值和系统字段值。
- \`dtls\` 为从表数据集合。
- \`aths\` 为附件数据集合，需要注意的是附件全路径（\`fileFullName\`）使用了URL编码，接收到后需要做解码处理。

#### URL解码 NetCore
\`\`\`c#
System.Net.WebUtility.UrlDecode(fileFullName);
\`\`\`
#### URL解码 JAVA
\`\`\`java
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
/**
 * Java接收API的例子
 * 注：以下代码仅供参考
 */
@RestController
public class TestController {

    @PostMapping("/testReceive")
    @ResponseBody
    public Object testReceive(@RequestBody Map<String,Object> map){
        System.out.println(map);
        JSONObject obj = JSONUtil.parseObj(map);
        JSONObject mainTable = obj.getJSONObject("mainTable"); //主表数据
        JSONArray dtls = obj.getJSONArray("dtls"); //从表数据集合
        JSONArray aths = obj.getJSONArray("aths"); //附件数据集合
        if(!aths.isEmpty()){
            String fileFullName = aths.getJSONObject(0).getJSONObject("athdb").getStr("fileFullName");
            java.net.URLDecoder.decode(fileFullName, "UTF-8"); //URL解码
        }
        SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("message", "执行成功");
                put("code", 200);
                put("data", JSONUtil.toJsonStr(JSONUtil.parseObj(map)));
                put("msg", "");
            }};
        return JSONUtil.toJsonStr(sortedMap);
    }
}
\`\`\`
`);m(this,"DBSrc",`
  #### 帮助
  - 数据源：就是链接数据库的工具.
  - 如果没有您的数据源，需要在系统管理里创建一个数据源.
`);m(this,"DBSrcTable",`
  #### 帮助
  - 选择要同步数据的表.
  - 注意不能选择视图
  - 完成之后就需要设置字段的同步对应关系.

`);this.PageTitle="新建数据同步",this.ForEntityClassID="TS.AttrFlow.SyncData"}Init(){return S(this,null,function*(){this.AddGroup("A","新建数据同步"),this.AddBlank("APIOfSystem","同步到内置的API接口",this.APIOfSystem),this.TextBox1_Name("APIOfSelf","同步到API接口",this.APIOfSelf,"输入API","http://","请输入API接口地址");const e=F.srcDBSrc;this.SelectItemsByList("DBSrc","数据源",this.DBSrc,!1,e),this.SelectItemsByList("DBSrc.Table","表",this.DBSrcTable,!1,this.DBSrc_GenerTables),this.SelectItemsByList("DBSrc.Table.PKField","表的主键",this.DBSrcTable,!1,this.DBSrc_GenerTable_PKField,!0,!0),this.SelectItemsByList("DBSrc.Table.PKField.Fields","要同步的字段",this.DBSrcTable,!0,this.DBSrc_GenerTable_PKField,!0,!0);const i=this.RequestVal("RefPKVal"),r="ND"+Number(i)+"Rpt",o=F.SQLOfFrmsFields(i,r);this.SelectItemsByList("DBSrc.Table.PKField.Fields.Frm","数据源表单",this.HelpUn,!1,o),this.AddGroup("B","数据源管理"),this.AddBlank("AdminDBSrc","数据源维护",""),this.AddBlank("AdminDBSrc.ToUrl","修改","")})}GenerSorts(){return S(this,null,function*(){return Promise.resolve([])})}DBSrc_GenerTables(){return S(this,null,function*(){const e=this.RequestVal("tb1","DBSrc"),i=new N("BP.Sys.SFDBSrc",e);yield i.Retrieve();const r=yield i.DoMethodReturnJSON("GetTablesJson");return JSON.stringify(r)})}DBSrc_GenerTable_PKField(){return S(this,null,function*(){const e=this.RequestVal("tb1","DBSrc"),i=this.RequestVal("tb1","DBSrc.Table"),r=new N("BP.Sys.SFDBSrc",e);yield r.Retrieve();const o=yield r.DoMethodReturnJSON("GetTableFieldsJson",i);return JSON.stringify(o)})}Save_TextBox_X(e,i,r,o,y){return S(this,null,function*(){if(e=="AdminDBSrc"||e=="AdminDBSrc.ToUrl"){const t="/@/WF/Comm/Search.vue?EnName=TS.Sys.SFDBSrc";return new d(p.GoToUrl,t)}const c=this.RequestVal("RefPKVal");if(r==="APIOfSystem"){const t=new P;t.FlowNo=c,t.SyncType="APIOfSystem",t.SyncTypeT="系统内置的API",t.Note=r+o,t.DBSrc=r,t.SetPara("EnName","TS.AttrFlow.SyncDataByAPI"),yield t.Insert();const l="/@/WF/Comm/En.vue?EnName="+t.GetParaString("EnName","")+"&PKVal="+t.MyPK;return new d(p.GoToUrl,l)}if(e==="APIOfSelf"){const t=new P;t.FlowNo=c,t.SyncType="APIOfSelf",t.Note="同步到API接口："+r,t.APIUrl=r,t.PTable="无",t.PTableName="无",t.TablePKName="无",t.TablePKType="无",t.FrmID="无",t.FrmName="无",t.SetPara("EnName","TS.AttrFlow.SyncDataByAPI"),yield t.Insert();const l=h.UrlEnOnly(t.GetParaString("EnName",""),t.MyPK);return new d(p.GoToUrl,l)}if(e!=="DBSrc"&&!(e=="DBSrc.Table"||e==="DBSrc.Table.PKField"||e=="DBSrc.Table.PKField.Fields")){if(e==="DBSrc.Table.PKField.Fields.Frm"){const t=new P;t.FlowNo=c,t.SyncType="DBSrc",t.SyncTypeT="数据源",t.Note="把流程数据同步到数据源上:"+this.RequestVal("tb1","DBSrc"),t.DBSrc=this.RequestVal("tb1","DBSrc"),t.DBSrcT=this.RequestVal("tb2","DBSrc"),t.Src=this.RequestVal("tb1","DBSrc"),t.SetPara("EnName","TS.AttrFlow.SyncDataByDBSrc"),t.PTable=this.RequestVal("tb1","DBSrc.Table"),t.PTableName=this.RequestVal("tb2","DBSrc.Table"),t.FrmID=this.RequestVal("tb1","DBSrc.Table.PKField.Fields.Frm"),t.FrmName=this.RequestVal("tb2","DBSrc.Table.PKField.Fields.Frm");const l=this.RequestVal("tb1","DBSrc.Table.PKField").split("=");t.TablePKName=l[0],t.TablePKType=l[1],yield t.Insert();const B=this.RequestVal("tb1","DBSrc.Table.PKField.Fields").split(","),A=this.RequestVal("tb2","DBSrc.Table.PKField.Fields").split(","),a=new K;for(let b=0;b<B.length;b++){const f=B[b],D=f.split("=")[0],u=f.split("=")[1].toLowerCase();D==t.TablePKName||(a.MyPK=t.PKVal+"_"+D,(yield a.IsExits())==!0)||(a.RefPKVal=t.PKVal,a.AttrKey=D,a.AttrName=A[b],u.includes("int")==!0&&(a.AttrType=T.AppFloat,a.AttrTypeT="数值"),u.includes("char")==!0&&(a.AttrType=T.AppString,a.AttrTypeT="文本"),u.includes("int")==!0&&(a.AttrType=T.AppInt,a.AttrTypeT="整形"),a.FrmID=t.FrmID,a.FlowNo=this.RefPKVal,yield a.Insert())}const O=h.UrlEnOnly(t.GetParaString("EnName",""),t.MyPK);return new d(p.GoToUrl,O)}alert("没有判断的页面类型:["+e+"]")}})}}export{Y as GPN_SyncData};
