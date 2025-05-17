var h=Object.defineProperty;var T=(t,o,e)=>o in t?h(t,o,{enumerable:!0,configurable:!0,writable:!0,value:e}):t[o]=e;var p=(t,o,e)=>T(t,typeof o!="symbol"?o+"":o,e);var n=(t,o,e)=>new Promise((m,a)=>{var c=i=>{try{r(e.next(i))}catch(s){a(s)}},l=i=>{try{r(e.throw(i))}catch(s){a(s)}},r=i=>i.done?m(i.value):Promise.resolve(i.value).then(c,l);r((e=e.apply(t,o)).next())});import{P as u,G as P,m as g}from"./entry/index-B5R3Coa4-1746862693206.js";import w from"./Dev2Interface-B9phnAG0.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./GenerWorkFlowExt-CWtBNCse.js";import"./EntityWorkID-B0nISxjT.js";import"./FlowSort-DO177AvQ.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FlowAdm-B9fl-Qy8.js";class E extends u{constructor(){super("GPN_StartFlow003");p(this,"Imp",`
  #### 帮助
   - 从其他部门的人员里导入人员，放入本部门中.
   - 一个人拥有多个部门.
  `);p(this,"ImpExcel",`
  #### 帮助
   - 从excel导入数据.
   - 按照ccbpm的excel格式要求.
   - 格式文件位于 .  完善测试该方法.

  `);this.PageTitle="新建开票流程",this.ForEntityClassID="TS.PM.KaiPiaoSQ"}Init(){return n(this,null,function*(){this.AddGroup("A","选择方式"),this.Table("Track0","选择合同",this.HelpTodo,!1,"SELECT MyPk as No,HeTongMingChen Name,XiangMuBianHao 项目编号,XiangMuMingChen 项目名称,QianDingRiQi 签订日期,case HeTongLaiYuan when 0 then '投标' when 1 then '委托' else '其他' end  合同来源,JianSheShanWeiT 建设单位,JianSheGuanLiShanWeiT 建设管理单位,JiaFangDanWeiLianXi 甲方单位联系人 FROM PM_HTIncome ")})}GenerSorts(){return n(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,m,a,c,l){return n(this,null,function*(){if(e=="Track0"){const r=yield w.Node_CreateBlank("003"),i="/#/WF/MyFlow?FlowNo=003&HeTongBianHao="+a+"&WorkID="+r;return new P(g.OpenUrlByDrawer90,i,"流程")}})}}export{E as GPN_StartFlow003};
