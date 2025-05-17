var f=Object.defineProperty;var R=(a,l,t)=>l in a?f(a,l,{enumerable:!0,configurable:!0,writable:!0,value:t}):a[l]=t;var n=(a,l,t)=>R(a,typeof l!="symbol"?l+"":l,t);var w=(a,l,t)=>new Promise((e,m)=>{var N=o=>{try{i(t.next(o))}catch(r){m(r)}},S=o=>{try{i(t.throw(o))}catch(r){m(r)}},i=o=>o.done?e(o.value):Promise.resolve(o.value).then(N,S);i((t=t.apply(a,l)).next())});import{P,G as c,m as p}from"./entry/index-B5R3Coa4-1746862693206.js";import F from"./HttpHandler-CdnQkxwF.js";import{Menu as g}from"./Menu-B21f89xo.js";import{Flow as I}from"./Flow-BIaTOSmj.js";import{GloComm as W}from"./GloComm-B1xAfTWw.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Events-D9tOL1Ad.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./PCenter-DbUtxw5j.js";import"./PowerCenter-Dl8ZnZaz.js";import"./FrmTrack-Ct-No0Nq.js";class $ extends P{constructor(){super("GPN_AIFlow");n(this,"AlertCar",`
    我要做一个车辆申请流程，如
    员工审批路线： 填写申请、部门审批、办公室审批、反馈给申请人.
    `);n(this,"AlertEmp",`
    我要做一个员工入职流程。
    `);n(this,"AlertQingJia0",`
    请假流程，流程节点包括，填写申请单、部门审批、人力资源审批、反馈给申请人节点等，请补齐。
    `);n(this,"AlertQingJia1",`
    请假表单内容包括，请假日期从，到，请假天数，请假原因，请假类型等内容。
   `);n(this,"WordAlert",`
  #### 帮助
  - 字段附件，附件以字段名的形式在页面中显示; 
  #### 图例
  ![输入图片说明](./resource/WF/Admin/FrmLogic/SFTable/Img/Ath1.png "屏幕截图.png") 
  #### 数据存储
  - 附件的默认保存在web服务器上。
  - 可以保存到ftp服务器上, ftp的服务器的连接配置在全局的配置文件中。
  - 如果需要保存到数据库，就需要考虑数据库的存储与备份的问题，文件将会存储在 Sys_FrmAttachmentDB 表中。
    `);n(this,"HelpFile",`
    #### 帮助
    - 请上传excel，word,表单图片文件，AI将会根据内容，自动生成内容.
  `);n(this,"SelectAttrs",`
  #### 帮助
  - 系统根据提示词，生成如下字段.
  #### 数据存储
  - int类型的枚举值是常用的数据类型，ccfrom是格式化的存储到数据表里.
  - 创建一个int类型的字段，用于存储枚举的数据.
    `);this.PageTitle="AI流程"}Init(){return w(this,null,function*(){this.AddGroup("A","AI流程"),this.TextArea("AIFlow","输入提示词",this.WordAlert,"提示词","我要创建一个请假流程 包括但不限于：填写申请单，部门审批，人力资源审批等节点。","请点击帮助参考如何输入提示词?"),this.TextArea("AIFlow.Frm","输入表单要求",this.WordAlert,"提示词","创建请假表单，包括但不限于如下字段：请假日期从、到、请假天数请假原因。","请点击帮助参考如何输入提示词?"),this.FileUpload("File","根据文件生成表单","请上传Excel,word,图片文件.",this.HelpFile),this.AddGroup("B","提示词参考"),this.TextArea("Demo1","用车审批流程",this.WordAlert,"提示词",this.AlertCar,"请点击帮助参考如何输入提示词?"),this.Table("Demo1.SelectedAttrs","选择字段",this.SelectAttrs,!0,this.GenerNodes),this.TextArea("QingJia","请假流程",this.WordAlert,"请输入流程要求",this.AlertQingJia0,"请点击帮助参考如何输入提示词?"),this.TextArea("QingJia.Frm","表单要求",this.WordAlert,"表单要求",this.AlertQingJia1,"请点击帮助参考如何输入提示词?"),this.TextArea("Demo3","收文流程",this.WordAlert,"提示词",this.AlertQingJia1,"请点击帮助参考如何输入提示词?"),this.Table("Demo3.SelectedAttrs","选择字段",this.SelectAttrs,!0,this.GenerNodes)})}GenerNodes(){return w(this,null,function*(){const t=new F("BP.WF.HttpHandler.WF_Admin_AI");t.AddPara("FlowNo",this.RequestVal("FlowNo"));let e=this.RequestVal("tb1","AIFlow");(e==null||e==null||e=="")&&(e=this.RequestVal("tb1","Demo1")),(e==null||e==null||e=="")&&(e=this.RequestVal("tb1","Demo2")),(e==null||e==null||e=="")&&(e=this.RequestVal("tb1","Demo3")),t.AddPara("Words",e);const m=yield t.DoMethodReturnJson("AiFlow_GenerNodes");return JSON.stringify(m)})}GenerSorts(){return w(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,e,m,N,S){return w(this,null,function*(){if(t=="AIFlow.Frm"){const i=this.RequestVal("tb1","AIFlow"),o=this.RequestVal("tb1","AIFlow.Frm"),r=new F("BP.WF.HttpHandler.WF_Admin_AI");r.AddPara("SortNo",this.RequestVal("SortNo")),r.AddPara("FlowWords",i),r.AddPara("FrmWords",o);const d=yield r.DoMethodReturnString("AiFlow_SaveFlow");if(this.RequestVal("SystemNo")!=""){const u=new I(d);u.No=d;const A=d;yield u.RetrieveFromDBSources();const s=new g;s.Name=u.Name,s.Alias="SearchFlow"+A,s.UrlPath="/src/WF/Rpt/SearchFlow.vue?FlowNo="+A,s.UrlExt=`/SearchFlow_${A}?FlowNo=${A}`,s.ModuleNo=this.RequestVal("ModelNo"),s.SystemNo=this.RequestVal("SystemNo"),s.MenuModel="RefFlow",s.FlowNo=A,s.IsEnable=1,s.SetPara("FlowNo",A),yield s.Insert()}const h=W.UrlFlowD(d);return new c(p.OpenUrlByNewWindow,h)}if(t.includes("QingJia.Frm")){const i=this.RequestVal("tb1","QingJia"),o=this.RequestVal("tb1","QingJia.Frm"),r=new F("BP.WF.HttpHandler.WF_Admin_AI");r.AddPara("SortNo",this.RequestVal("SortNo")),r.AddPara("FlowWords",i),r.AddPara("FrmWords",o);const d=yield r.DoMethodReturnString("AiFlow_SaveFlow"),h=W.UrlFlowD(d);return new c(p.OpenUrlByNewWindow,h)}if(t=="File"){alert("开发中.");const i=new F("BP.WF.HttpHandler.Admin_AI");i.AddFile(this.UploadFile),i.AddPara("FlowNo",this.RequestVal("FlowNo"));let o=yield i.DoMethodReturnString("AiFlow_File");return typeof o=="string"&&o.includes("@")&&(o=o.split("@").join(`
`)),new c(p.Message,o)}})}}export{$ as GPN_AIFlow};
