var T=Object.defineProperty;var f=(o,t,r)=>t in o?T(o,t,{enumerable:!0,configurable:!0,writable:!0,value:r}):o[t]=r;var m=(o,t,r)=>f(o,typeof t!="symbol"?t+"":t,r);var a=(o,t,r)=>new Promise((p,s)=>{var c=e=>{try{i(r.next(e))}catch(n){s(n)}},l=e=>{try{i(r.throw(e))}catch(n){s(n)}},i=e=>e.done?p(e.value):Promise.resolve(e.value).then(c,l);i((r=r.apply(o,t)).next())});import{P as x,W as u,G as w,m as B}from"./entry/index-B5R3Coa4-1746862693206.js";import I from"./Dev2Interface-B9phnAG0.js";import{b as N}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";import"./GenerWorkFlowExt-CWtBNCse.js";import"./EntityWorkID-B0nISxjT.js";import"./FlowSort-DO177AvQ.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FlowAdm-B9fl-Qy8.js";class O extends x{constructor(){super("GPN_StartFlowHT");m(this,"Imp",`
  #### 帮助
   - 从其他部门的人员里导入人员，放入本部门中.
   - 一个人拥有多个部门.
  `);m(this,"ImpExcel",`
  #### 帮助
   - 从excel导入数据.
   - 按照ccbpm的excel格式要求.
   - 格式文件位于 .  完善测试该方法.

  `);this.PageTitle="新建收入合同",this.ForEntityClassID="TS.PM.HTIncome"}Init(){return a(this,null,function*(){this.AddGroup("A","选择方式"),this.TextBox1_Name("Track1","收入合同",this.HelpUn,"合同名称","我的收入合同","请输入您的合同名称")})}GenerSorts(){return a(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(r,p,s,c,l){return a(this,null,function*(){if(r=="Track1"){const i=u.Roles;if((i==null?void 0:i.indexOf("201111-41111-1031115"))<0&&!u.IsAdmin)return N.error("只有经营人员能发起收入合同"),"";const e=yield I.Node_CreateBlank("002"),n="/#/WF/MyFlow?FlowNo=002&StrZhuBanBuMen=68ffdf05-96f9-4063-8132-706263a885cc&StrZhuBanBuMenT=经营服务部&HeTongMingChen="+s+"&WorkID="+e;return new w(B.OpenUrlByDrawer90,n,"流程")}})}}export{O as GPN_StartFlowHT};
