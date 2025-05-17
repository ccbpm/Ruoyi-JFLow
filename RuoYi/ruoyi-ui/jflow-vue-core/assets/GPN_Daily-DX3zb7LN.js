var E=Object.defineProperty;var R=(o,a,e)=>a in o?E(o,a,{enumerable:!0,configurable:!0,writable:!0,value:e}):o[a]=e;var p=(o,a,e)=>R(o,typeof a!="symbol"?a+"":a,e);var n=(o,a,e)=>new Promise((y,l)=>{var D=r=>{try{i(e.next(r))}catch(m){l(m)}},u=r=>{try{i(e.throw(r))}catch(m){l(m)}},i=r=>r.done?y(r.value):Promise.resolve(r.value).then(D,u);i((e=e.apply(o,a)).next())});import{P as f,F as N,W as s,D as G,G as d,m as x}from"./entry/index-B5R3Coa4-1746862693206.js";import{Template as S}from"./Template-3LnUcITn.js";import{GloComm as w}from"./GloComm-B1xAfTWw.js";import{Project as g}from"./Project-BfxNn-s1.js";import{TaskAPI as I}from"./TaskAPI-Gufc2g0P.js";import{DailyItems as b}from"./DailyItem-CSDqMMMZ.js";import{PrjDailyDtl as v}from"./PrjDailyDtl-Db65_OO8.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./TemplateNode-ByvBBgX0.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./Track-DUR04BdF.js";import"./ProjectTask-CFghj2wW.js";import"./EntityOID-C1xznxai.js";class rt extends f{constructor(){super("GPN_Daily");p(this,"Daily",`
  #### 帮助
   - 有科目作为明细，记账人员都可以记账，有项目组人员范围。
   - 没有时限要求，每个人的工作按照时间段记账，可以按照科目汇总项目成本，比如研发类项目，日常持续的投入。
 
  `);p(this,"Section",`
  #### 帮助
   - 有先后顺序，不同工种协作完成，有一定的时限要求 
   - 比如开发类项目 技术支持类项目，有一定的明确的目标，类似于，project项目管理。 
   
  `);p(this,"Nondeterminacy",`
  #### 帮助 
  - 有目标，没有时限，多人协作，有项目里程碑，完成度. 
  - 比如，客户跟踪类型项目，项目组有明确的人员分工，但是没有顺序，没有时限。不同的角色协助完成一个客户的跟踪，有完成度，概率，有阶段。
  `);p(this,"Task",`
  #### 帮助
   - 也称为简单项目或者不确定性项目，没有模式的临时性任务，
    - 比如 申报知识产权，找几个人协作完成，可以有固定时限，每个子任务分给不同的人，处理完毕后需要汇报，认可，确认，可以树形结构的分发收回，子任务数不确定。
  `);this.PageTitle="新建项目"}Init(){return n(this,null,function*(){const e=this.RequestVal("TemplateNo");yield new S(e).Retrieve(),this.AddGroup("A","模板类型"),this.TextBox1_Name("Daily","新建项目",this.Daily,"项目名称","产品研发"),this.SelectItemsByTreeEns("Daily.Starter","项目负责人",this.Daily,!1,N.srcDeptLazily,"0",N.srcEmpLazily,"@No=账号@Name=名称@Tel=电话",!0),this.SelectItemsByTreeEns("Daily.Starter.Emps","参与人",this.Daily,!0,N.srcDeptLazily,"0",N.srcEmpLazily,"@No=账号@Name=名称@Tel=电话",!0)})}GenerSorts(){return n(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,y,l,D,u){return n(this,null,function*(){if(e==="Daily.Starter.Emps"){const i=this.RequestVal("TemplateNo"),r=new S(i);yield r.Retrieve();const m=yield I.Prj_CreateNo(i),t=new g(m);t.No=m,yield t.Retrieve(),t.Name=this.RequestVal("tb1","Daily"),t.Manager=this.RequestVal("tb1","Daily.Starter"),t.ManagerT=this.RequestVal("tb2","Daily.Starter"),t.StarterNo=s.No,t.StarterName=s.Name,t.DeptNo=s.DeptNo,t.DeptName=s.DeptName,t.OrgNo=s.OrgNo,t.Emps=l,t.EmpsT=D,t.TemplateNo=r.No,t.TemplateName=r.Name,t.RDT=G.CurrentDate,t.PrjSta=2,t.SetPara("EnName","TS.TA.PrjDaily"),yield t.Update();const c=new b;yield c.Retrieve("TemplateNo",i,"Idx");for(let T=0;T<c.length;T++){const j=c[T],P=new v;P.Name=j.Name,P.PrjNo=t.No,yield P.Insert()}yield w.WriteFrmTrack("TS.TA.PrjDaily",t.No,"StartPrj","启动项目",s.Name+"启动项目.");const h=w.UrlEn("TS.TA.PrjDaily",t.No);return new d(x.OpenUrlByDrawer75,h)}})}}export{rt as GPN_Daily};
