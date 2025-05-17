var S=Object.defineProperty;var B=(s,r,e)=>r in s?S(s,r,{enumerable:!0,configurable:!0,writable:!0,value:e}):s[r]=e;var p=(s,r,e)=>B(s,typeof r!="symbol"?r+"":r,e);var m=(s,r,e)=>new Promise((l,a)=>{var N=i=>{try{o(e.next(i))}catch(t){a(t)}},u=i=>{try{o(e.throw(i))}catch(t){a(t)}},o=i=>i.done?l(i.value):Promise.resolve(i.value).then(N,u);o((e=e.apply(s,r)).next())});import{P as T,G as d,m as h,D as f,W as n,ag as y}from"./entry/index-B5R3Coa4-1746862693206.js";import{GloComm as _}from"./GloComm-B1xAfTWw.js";import{a as D}from"./Student-BQhDTR9T.js";import w from"./Dev2InterfaceCCBill-BLVtAqN2.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./BanJi-DeaZfCS0.js";import"./ShengFen-DZU04_JK.js";import"./City-BLaIhva6.js";import"./StudentKeMu-BjcAx5Ro.js";import"./KeMu-BqOmpl9q.js";import"./GL_Todolist-DkoUoh83.js";import"./PageBaseGenerList-B6Q4ihPi.js";import"./Flow-BIaTOSmj.js";import"./index-COCIKPRQ.js";import"./Dev2Interface-B9phnAG0.js";import"./GenerWorkFlowExt-CWtBNCse.js";import"./EntityWorkID-B0nISxjT.js";import"./FlowSort-DO177AvQ.js";import"./FlowAdm-B9fl-Qy8.js";import"./Member-SKiFhL41.js";import"./FrmAttachmentDB-sRAzHvFJ.js";import"./WaiGuaBaseEntity-CY2oZiAn.js";import"./WaiGuaBaseFrm-CVp7kXTK.js";import"./MapExt-DVovzpWn.js";class at extends T{constructor(){super("GPN_Student");p(this,"Daily",`
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
  `);this.PageTitle="新建学生",this.ForEntityClassID="TS.Demo.Student"}getZZMM(){return m(this,null,function*(){return JSON.stringify([{No:"0",Name:"团员"},{No:"1",Name:"党员"}])})}Init(){return m(this,null,function*(){this.AddGroup("A","选择方式"),this.TextBox2_NameNo("NoName","输入账号",this.Daily,"","学生编号","学生名称",""),this.SelectItemsByList("NoName.BanJi","选择班级",this.HelpTodo,!1,"DemoStudent_Student_BanJi");const e=[{No:"0",Name:"男"},{No:"1",Name:"女"},{No:"2",Name:"未知"}];this.SelectItemsByList("NoName.BanJi.XB","性别",this.HelpTodo,!1,JSON.stringify(e)),this.SelectItemsByList("NoName.BanJi.XB.ZZMM","政治面貌",this.HelpTodo,!1,this.getZZMM),this.AddGroup("B","批量导入"),this.FileUpload("ImpExcel","导入Excel","请上传符合格式的Excel文件.",this.HelpTodo)})}GenerSorts(){return m(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,l,a,N,u){return m(this,null,function*(){if(e==="NoName"){const o=N;if((yield new D(o).IsExits())==!0)return new d(h.Error,"学生人员编号["+o+"],已经存在.")}if(e==="NoName.BanJi"){const o=this.RequestVal("tb2","NoName"),i=this.RequestVal("tb1","NoName"),t=new D(o);return t.BanJiNo=a,t.BanJiNoT=N,t.Name=i,t.No=o,t.RDT=f.CurrentDateTime,t.RecNo=n.No,t.RecName=n.Name,t.RecDeptNo=n.DeptNo,t.RecDeptName=n.DeptName,t.OrgNo=n.OrgNo,yield t.DirectInsert(),yield w.WriteTrack("Demo_Student",t.No,"创建记录."),new d(h.GoToUrl,_.UrlEn("TS.Demo.Student",o))}if(e==="Imp"){const o=this.RequestVal("DeptNo");a.split(",").forEach(t=>m(this,null,function*(){const c=new y(o+"_"+t);(yield c.RetrieveFromDBSources())==0&&(c.FK_Dept=o,c.FK_Emp=t,yield c.Insert())}))}})}}export{at as GPN_Student};
