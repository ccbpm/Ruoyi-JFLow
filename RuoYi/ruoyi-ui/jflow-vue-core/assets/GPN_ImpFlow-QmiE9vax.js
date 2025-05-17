var h=Object.defineProperty;var F=(e,s,t)=>s in e?h(e,s,{enumerable:!0,configurable:!0,writable:!0,value:t}):e[s]=t;var l=(e,s,t)=>F(e,typeof s!="symbol"?s+"":s,t);var c=(e,s,t)=>new Promise((i,a)=>{var n=r=>{try{o(t.next(r))}catch(m){a(m)}},p=r=>{try{o(t.throw(r))}catch(m){a(m)}},o=r=>r.done?i(r.value):Promise.resolve(r.value).then(n,p);o((t=t.apply(e,s)).next())});import{P as w,F as y,H as N,G as d,m as P}from"./entry/index-B5R3Coa4-1746862693206.js";import{FlowSorts as f}from"./FlowSort-DO177AvQ.js";import{b as u}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FlowAdm-B9fl-Qy8.js";class R extends w{constructor(){super("GPN_ImpFlow");l(this,"Imp",`
  #### 帮助指南

**上传模板并导入流程**

- 上传模板后，选择以下模式之一进行导入：

  ##### 选择模式说明

  1. **作为新流程导入（自动生成编号）**：
     - 系统将由ccbpm自动生成新的流程编号。

  2. **作为新流程导入（使用模板编号，若已存在则报错）**：
     - 使用流程模板中的流程编号。如果该编号已存在于系统中，系统将提示错误。

  3. **作为新流程导入（使用模板编号，若已存在则覆盖）**：
     - 使用流程模板中的流程编号。如果该编号已存在于系统中，系统将覆盖此流程。
  `);l(this,"BPMN2",`
  #### 帮助
  - 导入符合bpmn2.0格式的文件.
  `);this.PageTitle="流程导入"}Init(){this.AddGroup("A","流程导入"),this.FileUpload("Local","导入本机模板","请上传符合ccform表单格式的模式",this.Imp);const i=y.AtParaStringToJson("@0=作为新流程导入（自动生成编号）@1=作为新流程导入（使用模板编号，若已存在则报错）@2=作为新流程导入（使用模板编号，若已存在则覆盖）"),a=Object.keys(i),n=[];for(const p of a)n.push({No:p,Name:i[p]});this.SelectItemsByList("Local.Way","选择模式",this.Imp,!1,JSON.stringify(n)),this.FileUpload("BPMN2","导入BPM2格式模板","请上传文件",this.BPMN2),this.FileUpload("DingDing","导入钉钉格式模板","请上传文件",this.BPMN2)}GenerSorts(){return c(this,null,function*(){const t=new f;return yield t.RetrieveAll(),t})}Save_TextBox_X(t,i,a,n,p){return c(this,null,function*(){if(t=="Local.Way")try{const o=new N("BP.WF.HttpHandler.WF_Admin_AttrFlow");o.AddFile(this.UploadFile),o.AddPara("FK_Sort",i),o.AddPara("ImpWay",a);const r=yield o.DoMethodReturnString("Imp_Done");return new d(P.Message,(r==null?void 0:r.Msg)||"创建成功")}catch(o){u.error(o)}if(t=="BPMN2"){const o="已经取消了支持.";return new d(P.Error,o)}})}}export{R as GPN_ImpFlow};
