var E=Object.defineProperty;var R=(a,o,e)=>o in a?E(a,o,{enumerable:!0,configurable:!0,writable:!0,value:e}):a[o]=e;var n=(a,o,e)=>R(a,typeof o!="symbol"?o+"":o,e);var N=(a,o,e)=>new Promise((l,p)=>{var S=r=>{try{i(e.next(r))}catch(m){p(m)}},y=r=>{try{i(e.throw(r))}catch(m){p(m)}},i=r=>r.done?l(r.value):Promise.resolve(r.value).then(S,y);i((e=e.apply(a,o)).next())});import{P as f,F as c,W as s,D as G,G as d,m as x}from"./entry/index-B5R3Coa4-1746862693206.js";import{Template as w}from"./Template-3LnUcITn.js";import{GloComm as D}from"./GloComm-B1xAfTWw.js";import{Project as g}from"./Project-BfxNn-s1.js";import{TaskAPI as I}from"./TaskAPI-Gufc2g0P.js";import{SectionItems as b}from"./SectionItem-DtK6Y79y.js";import{PrjSectionDtl as v}from"./PrjSectionDtl-BRZz2Q5v.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./TemplateNode-ByvBBgX0.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./Track-DUR04BdF.js";import"./ProjectTask-CFghj2wW.js";import"./EntityOID-C1xznxai.js";class rt extends f{constructor(){super("GPN_Section");n(this,"Daily",`
  #### 帮助
   - 有科目作为明细，记账人员都可以记账，有项目组人员范围。
   - 没有时限要求，每个人的工作按照时间段记账，可以按照科目汇总项目成本，比如研发类项目，日常持续的投入。
 
  `);n(this,"Section",`
  #### 帮助
   - 有先后顺序，不同工种协作完成，有一定的时限要求 
   - 比如开发类项目 技术支持类项目，有一定的明确的目标，类似于，project项目管理。 
   
  `);n(this,"Nondeterminacy",`
  #### 帮助 
  - 有目标，没有时限，多人协作，有项目里程碑，完成度. 
  - 比如，客户跟踪类型项目，项目组有明确的人员分工，但是没有顺序，没有时限。不同的角色协助完成一个客户的跟踪，有完成度，概率，有阶段。
  `);n(this,"Task",`
  #### 帮助
   - 也称为简单项目或者不确定性项目，没有模式的临时性任务，
    - 比如 申报知识产权，找几个人协作完成，可以有固定时限，每个子任务分给不同的人，处理完毕后需要汇报，认可，确认，可以树形结构的分发收回，子任务数不确定。
  `);this.PageTitle="新建固定项目"}Init(){return N(this,null,function*(){const e=this.RequestVal("TemplateNo");yield new w(e).Retrieve(),this.AddGroup("A","模板类型"),this.TextBox1_Name("Section","新建固定项目",this.Daily,"项目名称","技术支持项目"),this.SelectItemsByTreeEns("Section.Starter","项目负责人",this.Daily,!1,c.srcDeptLazily,"0",c.srcEmpLazily,"@No=账号@Name=名称@Tel=电话",!0),this.SelectItemsByTreeEns("Section.Starter.Emps","参与人",this.Daily,!0,c.srcDeptLazily,"0",c.srcEmpLazily,"@No=账号@Name=名称@Tel=电话",!0)})}GenerSorts(){return N(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,l,p,S,y){return N(this,null,function*(){if(e==="Section.Starter.Emps"){const i=this.RequestVal("TemplateNo"),r=new w(i);yield r.Retrieve();const m=yield I.Prj_CreateNo(i),t=new g(m);t.No=m,yield t.Retrieve(),t.Name=this.RequestVal("tb1","Section"),t.Manager=this.RequestVal("tb1","Section.Starter"),t.ManagerT=this.RequestVal("tb2","Section.Starter"),t.StarterNo=s.No,t.StarterName=s.Name,t.DeptNo=s.DeptNo,t.DeptName=s.DeptName,t.OrgNo=s.OrgNo,t.Emps=p,t.EmpsT=S,t.TemplateNo=r.No,t.TemplateName=r.Name,t.RDT=G.CurrentDate,t.PrjSta=2,t.SetPara("EnName","TS.TA.PrjSection"),yield t.Update();const T=new b;yield T.Retrieve("TemplateNo",i,"Idx");for(let P=0;P<T.length;P++){const j=T[P],u=new v;u.Name=j.Name,u.PrjNo=t.No,yield u.Insert()}yield D.WriteFrmTrack("TS.TA.PrjSection",t.No,"StartPrj","启动项目",s.Name+"启动项目.");const h=D.UrlEn("TS.TA.PrjSection",t.No);return new d(x.OpenUrlByDrawer75,h)}})}}export{rt as GPN_Section};
