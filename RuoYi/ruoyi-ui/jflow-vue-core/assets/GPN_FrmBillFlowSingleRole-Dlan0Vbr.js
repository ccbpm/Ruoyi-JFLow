var R=Object.defineProperty;var w=(o,l,e)=>l in o?R(o,l,{enumerable:!0,configurable:!0,writable:!0,value:e}):o[l]=e;var a=(o,l,e)=>w(o,typeof l!="symbol"?l+"":l,e);var m=(o,l,e)=>new Promise((p,s)=>{var S=t=>{try{i(e.next(t))}catch(n){s(n)}},c=t=>{try{i(e.throw(t))}catch(n){s(n)}},i=t=>t.done?p(t.value):Promise.resolve(t.value).then(S,c);i((e=e.apply(o,l)).next())});import{FrmBillFlowSingleRole as h}from"./FrmBillFlowSingleRole-bqIr9hbL.js";import{P as B,G as d,m as F}from"./entry/index-B5R3Coa4-1746862693206.js";import{MethodFlowHostBill as O}from"./MethodFlowHostBill-BL9TYRfm.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Method-Duk019iw.js";import"./GroupMethod-D4dNiFLl.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./PCenter-DbUtxw5j.js";import"./PowerCenter-Dl8ZnZaz.js";class H extends B{constructor(){super("GPN_FrmBillFlowSingleRole");a(this,"OnlyStartOneRec",`
  #### 帮助
  - 只能发起一次.
  - 
`);a(this,"OnlyStartSelfCreateRec",`
  #### 帮助
  - 只能发起自己创建的记录.
  - 
`);a(this,"RegularExpressionSelf",`
  #### 帮助
  - 自定义正则表达式, 请在文本框输入正则表达式，然后执行创建。
  - 请正确的选择事件然.
`);a(this,"SelfFunc",`
  #### 帮助
  - 自定义函数，就是在服务器上创建一个js文件，写入一个函数.
  -  
`);a(this,"JSBody",`
#### 帮助
-  输入函数的脚本.
-  系统就会执行这些脚本，在您指定的事件里.
`);this.ForEntityClassID="TS.MapExt.FrmBillFlowSingleRole",this.PageTitle="单次流程规则"}Init(){this.AddGroup("SingleRole","单次流程规则"),this.AddBlank("AfterOverCanStartFlow","只能在归档后发起流程.",this.HelpUn,"icon-drop"),this.AddBlank("FlowOverUpdateFrmBillData","流程结束后,更新主表数据.",this.HelpUn,"icon-drop"),this.AddBlank("FlowOverDeleteFrmBillData","流程删除后删除单据数据.",this.HelpUn,"icon-drop"),this.AddGroup("StartLimit","发起限制规则"),this.AddBlank("OnlyStartSelfCreateRec","只能发起自己创建的数据.",this.OnlyStartSelfCreateRec,"icon-drop"),this.AddBlank("UnOverFlow","如果该流程没有完成则不能发起新流程.",this.OnlyStartSelfCreateRec,"icon-drop"),this.AddBlank("OnlyOnce","只能发起一次.",this.OnlyStartOneRec,"icon-drop")}GenerSorts(){return m(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,p,s,S,c){return m(this,null,function*(){const i=this.RequestVal("RefPKVal"),t=new O(i);yield t.Retrieve();const n=t.FlowNo+"_"+t.FrmID+"_"+e,r=new h(n);return(yield r.IsExits())==!0?new d(F.Message,"改项目已经存在."):(r.MyPK=n,e=="OnlyStartSelfCreateRec"||e=="UnOverFlow"||e=="OnlyOnce"?r.DBRole="StartLimit":r.DBRole="SingleRole",r.MarkID=e,r.MarkName=this.GetPageName(e),r.FrmID=t.FrmID,r.Docs=t.FlowNo,r.RefPKVal=i,r.SetPara("EnName","TS.MapExt.FrmBillFlowSingleRole"),yield r.Insert(),new d(F.Message,"增加成功."))})}}export{H as GPN_FrmBillFlowSingleRole};
