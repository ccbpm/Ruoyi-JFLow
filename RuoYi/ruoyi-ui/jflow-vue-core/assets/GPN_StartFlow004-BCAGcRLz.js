var l=Object.defineProperty;var u=(t,o,e)=>o in t?l(t,o,{enumerable:!0,configurable:!0,writable:!0,value:e}):t[o]=e;var m=(t,o,e)=>u(t,typeof o!="symbol"?o+"":o,e);var a=(t,o,e)=>new Promise((p,n)=>{var c=r=>{try{i(e.next(r))}catch(s){n(s)}},h=r=>{try{i(e.throw(r))}catch(s){n(s)}},i=r=>r.done?p(r.value):Promise.resolve(r.value).then(c,h);i((e=e.apply(t,o)).next())});import{P as T,G as S,m as g}from"./entry/index-B5R3Coa4-1746862693206.js";import w from"./Dev2Interface-B9phnAG0.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./GenerWorkFlowExt-CWtBNCse.js";import"./EntityWorkID-B0nISxjT.js";import"./FlowSort-DO177AvQ.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FlowAdm-B9fl-Qy8.js";class W extends T{constructor(){super("GPN_StartFlow004");m(this,"Imp",`
  #### 帮助
   - 从其他部门的人员里导入人员，放入本部门中.
   - 一个人拥有多个部门.
  `);m(this,"ImpExcel",`
  #### 帮助
   - 从excel导入数据.
   - 按照ccbpm的excel格式要求.
   - 格式文件位于 .  完善测试该方法.

  `);this.PageTitle="新建主营分包流程",this.ForEntityClassID="TS.PM.FenBaoSQ"}Init(){return a(this,null,function*(){this.AddGroup("A","选择方式"),this.Table("Track0","选择合同",this.HelpTodo,!1,"SELECT No,HeTongMingChen Name,XiangMuBianHao 项目编号,XiangMuMingChen 项目名称,QianDingRiQi 签订日期,case HeTongLaiYuan when 0 then '投标' when 1 then '委托' else '其他' end  合同来源,JianSheShanWeiT 建设单位,JianSheGuanLiShanWeiT 建设管理单位,JiaFangDanWeiLianXi 甲方单位联系人 FROM PM_HTIncome order by No")})}GenerSorts(){return a(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,p,n,c,h){return a(this,null,function*(){if(e=="Track0"){const i=yield w.Node_CreateBlank("004"),r="/#/WF/MyFlow?FlowNo=004&ZhuShouRuHeTongBianH="+n+"&WorkID="+i;return new S(g.OpenIframeByDrawer75,r,"流程")}})}}export{W as GPN_StartFlow004};
