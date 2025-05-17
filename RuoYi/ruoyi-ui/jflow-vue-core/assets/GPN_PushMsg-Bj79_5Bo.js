var d=Object.defineProperty;var W=(a,s,t)=>s in a?d(a,s,{enumerable:!0,configurable:!0,writable:!0,value:t}):a[s]=t;var N=(a,s,t)=>W(a,typeof s!="symbol"?s+"":s,t);var u=(a,s,t)=>new Promise((i,m)=>{var n=o=>{try{r(t.next(o))}catch(S){m(S)}},h=o=>{try{r(t.throw(o))}catch(S){m(S)}},r=o=>o.done?i(o.value):Promise.resolve(o.value).then(n,h);r((t=t.apply(a,s)).next())});import{P as c,F as l,G as f,m as p}from"./entry/index-B5R3Coa4-1746862693206.js";import{PushMsg as F}from"./PushMsg-CTLXMVMh.js";import{GloComm as T}from"./GloComm-B1xAfTWw.js";import{Node as P}from"./Node-BsvTqXX9.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FrmTrack-Ct-No0Nq.js";import"./EntityNodeID-3BfNz0DC.js";class _ extends c{constructor(){super("GPN_PushMsg");N(this,"TodoEmps",`
  #### 帮助
  - 定义: 当事人，就是操作流程后要影响的人,就叫当事人.
  - 退回: 退回给的人就是当事人.
  - 发送: 接收人就是当事人.
  - 移交: 被移交的人
  - 撤销: 撤销前的处理人.
`);N(this,"Field",`
#### 帮助
- 定义: 流程表单上的字段采集的操作员信息作为消息的接收人.
#### 解释
- 在节点表单上增加一个字段，该字段采集的是人员信息，如果有多个人员就用逗号分开.
- 系统在发送的时间，从NDxxRpt表里获取该字段的信息作为接收人.
`);N(this,"NodeWorker",`
 #### 帮助
 - 定义: 选择一个或者多个节点,这个节点的处理人就是消息的接收人.
 - 注意: 选择的节点一定是当前节点以前的节点,不能选择未来的节点.
 #### 其它
 - 可以多选节点.
 `);N(this,"SpecEmpNo",`
  #### 帮助
  - 发送给指定人员，可以多选.
  #### 其它
  - 人员必须是组织结构里面的人员,就是Port_Emp表里的人员.
    
`);N(this,"Starter",`
  #### 帮助
  - 向开始节点处理人发送消息.
  - 开始节点就是申请人，启动人.
`);N(this,"BySQL",`
#### 帮助
 - 输入一个SQL查询语句, 返回的是No,Name两个列,人员编号,人员名称.
 - 这些账号必须在组织结构表里.
 - 系统就会发送给这些人.
 #### 其它
 - SQL语句支持ccbpm表达式.
 - 比如： 发送给自己本部门的人员 select No,Name FROM Port_Emp where FK_Dept='@WebUser.DeptNo'
`);this.PageTitle="推送对象选择方式",this.ForEntityClassID="TS.WF.PushMsg"}Init(){return u(this,null,function*(){this.AddGroup("A","推送对象选择方式"),this.AddBlank("TodoEmps","当事人",this.TodoEmps);const t=this.RefPKVal,i=new P(t);yield i.RetrieveFromDBSources();const m=i.FK_Flow,n="ND"+parseInt(m)+"Rpt";this.SelectItemsByGroupList("Field","表单上的字段作为接受对象",this.Field,!1,l.SQLOfPushMsgFields(t),l.SQLOfPushMsgSrcList(n)),this.SelectItemsByList("NodeWorker","其他节点的处理人",this.NodeWorker,!0,l.SQLOfNodesOfFlow(m)),this.SelectItemsByTreeEns("SpecEmpNo","发给指定的人",this.SpecEmpNo,!0,l.srcDepts,l.srcDeptRoot,l.srcEmps,""),this.AddBlank("Starter","开始节点的发起人",this.Starter),this.TextSQL("BySQL","按照SQL计算",this.BySQL,"请输入查询SQL语句","","请阅读帮助,注意格式.")})}GenerSorts(){return u(this,null,function*(){return this.RequestVal("MsgModel")=="NodeMsg"?Promise.resolve([{No:"SendWhen",Name:"当节点发送前"},{No:"SendSuccess",Name:"节点发送成功时"},{No:"ReturnAfter",Name:"当节点退回后"},{No:"UndoneAfter",Name:"当节点撤销发送后"},{No:"WhenReadWork",Name:"工作打开后"}]):Promise.resolve([{No:"FlowOnCreateWorkID",Name:"创建工作ID后"},{No:"FlowOverAfter",Name:"流程结束后"},{No:"AfterFlowDel",Name:"流程删除后"},{No:"FlowWarning",Name:"整体流程预警"},{No:"FlowOverDue",Name:"整体流程逾期"}])})}Save_TextBox_X(t,i,m,n,h){return u(this,null,function*(){let r=this.RequestVal("NodeID");r||(r=this.RequestVal("RefPKVal"));const o=new P(r);(yield o.RetrieveFromDBSources())==0&&(o.NodeID=Number.parseInt(r+"01"),yield o.RetrieveFromDBSources());const e=new F;return e.FlowNo=o.FK_Flow,e.NodeID=r,e.RefPKVal=r,e.EventNo=i,e.EventName=yield this.GetSortName(i),e.PushWayExp1=m,e.PushWayExp2=n,t==="TodoEmps"&&e.SetPara("EnName","TS.WF.NMGener"),t==="Starter"&&e.SetPara("EnName","TS.WF.NMGener"),t==="Field"&&e.SetPara("EnName","TS.WF.NMField"),t==="NodeWorker"&&e.SetPara("EnName","TS.WF.NMNodeWorker"),t==="SpecEmpNo"&&e.SetPara("EnName","TS.WF.NMSpecEmpNo"),t==="BySQL"&&e.SetPara("EnName","TS.WF.NMBySQL"),e.PushWayNo=t,e.PushWayName=this.GetPageName(t),e.EventNo==="WorkArrive"&&(e.PushWayNo=t,t==="TodoEmps"?e.PushWayName="工作处理人":e.PushWayName=this.GetPageName(t),e.SMSDoc="新工作到达"),e.EventNo==="SendSuccess"&&(e.PushWayNo=t,t==="TodoEmps"?e.PushWayName="下一步骤工作处理人":e.PushWayName=this.GetPageName(t),e.SMSDoc="来自流程{FlowName}节点{NodeName}发送人{@WebUser.Name}的新工作{url}",e.MailTitle="标题{Title}-发送人{@WebUser.Name}",e.MailDoc="标题{Title}来自流程{FlowName}节点{NodeName}发送人{@WebUser.Name}的新工作{url}"),e.EventNo==="ReturnAfter"&&(e.PushWayNo=t,t==="TodoEmps"?e.PushWayName="退回的工作接收人":e.PushWayName=this.GetPageName(t),e.SMSDoc="工作退回{Titile} - {url}",e.MailTitle="工作退回,{Title}-退回人{@WebUser.Name}",e.MailDoc="工作退回，标题{Title}来自流程{FlowName}节点{NodeName}退回人{@WebUser.Name} - {url}"),e.EventNo==="UndoneAfter"&&(e.PushWayNo=t,t==="TodoEmps"?e.PushWayName="撤销前的工作处理人":e.PushWayName=this.GetPageName(t),e.SMSDoc="工作被撤销{Titile} - {url}",e.MailTitle="工作被撤销,{Title}-撤销人{@WebUser.Name}",e.MailDoc="工作被撤销,标题{Title}来自流程{FlowName}节点{NodeName}撤销人{@WebUser.Name} - {url}"),e.EventNo==="WhenReadWork"&&(e.PushWayNo=t,t==="TodoEmps"?e.PushWayName="工作处理人":e.PushWayName=this.GetPageName(t),e.SMSDoc="工作被读取{Titile} - {url}",e.MailTitle="工作被读取,{Title}-读取人{@WebUser.Name}",e.MailDoc="工作被读取,标题{Title}来自流程{FlowName}节点{NodeName}读取人{@WebUser.Name} - {url}"),yield e.Insert(),new f(p.GoToUrl,T.UrlEn(e.GetParaString("EnName",""),e.PKVal))})}}export{_ as GPN_PushMsg};
