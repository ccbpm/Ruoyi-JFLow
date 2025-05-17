var l=Object.defineProperty;var T=(t,o,e)=>o in t?l(t,o,{enumerable:!0,configurable:!0,writable:!0,value:e}):t[o]=e;var p=(t,o,e)=>T(t,typeof o!="symbol"?o+"":o,e);var s=(t,o,e)=>new Promise((m,i)=>{var c=r=>{try{a(e.next(r))}catch(n){i(n)}},F=r=>{try{a(e.throw(r))}catch(n){i(n)}},a=r=>r.done?m(r.value):Promise.resolve(r.value).then(c,F);a((e=e.apply(t,o)).next())});import{P as B,G as w,m as N}from"./entry/index-B5R3Coa4-1746862693206.js";import u from"./Dev2Interface-B9phnAG0.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./GenerWorkFlowExt-CWtBNCse.js";import"./EntityWorkID-B0nISxjT.js";import"./FlowSort-DO177AvQ.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FlowAdm-B9fl-Qy8.js";class v extends B{constructor(){super("GPN_StartFlow006");p(this,"Imp",`
  #### 帮助
   - 从其他部门的人员里导入人员，放入本部门中.
   - 一个人拥有多个部门.
  `);p(this,"ImpExcel",`
  #### 帮助
   - 从excel导入数据.
   - 按照ccbpm的excel格式要求.
   - 格式文件位于 .  完善测试该方法.

  `);this.PageTitle="新建主营分包流程",this.ForEntityClassID="TS.PM.BoFuSQ"}Init(){return s(this,null,function*(){this.AddGroup("A","选择方式"),this.Table("Track0","分包合同名称",this.HelpTodo,!1,"SELECT No,FBHTMC Name,FBHTJEY 分包合同金额,FBHTJF 分包合同甲方,FBHTYF 分包合同乙方,case SFBCHT when 0 then '否' when 1 then '是' end  是否补充合同 FROM PM_FenBaoSQ order by No desc ")})}GenerSorts(){return s(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,m,i,c,F){return s(this,null,function*(){if(e=="Track0"){const a=yield u.Node_CreateBlank("006"),r="/#/WF/MyFlow?FlowNo=006&FenBaoHeTongBianHao="+i+"&WorkID="+a;return new w(N.OpenIframeByDrawer75,r,"流程")}})}}export{v as GPN_StartFlow006};
