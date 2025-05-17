var w=Object.defineProperty;var d=(e,r,t)=>r in e?w(e,r,{enumerable:!0,configurable:!0,writable:!0,value:t}):e[r]=t;var c=(e,r,t)=>d(e,typeof r!="symbol"?r+"":r,t);var m=(e,r,t)=>new Promise((a,n)=>{var o=s=>{try{i(t.next(s))}catch(p){n(p)}},u=s=>{try{i(t.throw(s))}catch(p){n(p)}},i=s=>s.done?a(s.value):Promise.resolve(s.value).then(o,u);i((t=t.apply(e,r)).next())});import{P as y,F as l,H as D}from"./entry/index-B5R3Coa4-1746862693206.js";import F from"./Dev2Interface-B9phnAG0.js";import{b as f}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";import"./GenerWorkFlowExt-CWtBNCse.js";import"./EntityWorkID-B0nISxjT.js";import"./FlowSort-DO177AvQ.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FlowAdm-B9fl-Qy8.js";class v extends y{constructor(){super("GPN_WorkShift");c(this,"Docs0",`
  #### 帮助
  - 按照节点表单的字段作为抄送人.
  - 通常是在节点表单上加一个字段,这个字段存储的是人员账号，多个人员使用逗号分开.
  #### 运行图例
  - @liang.

`);c(this,"Docs1",`
  #### 帮助
  - 自动抄送给要绑定的人员.
`);c(this,"Docs2",`
  #### 帮助
  - 按照绑定的部角色下的人员集合作为抄送人.
  - 有一个规则
  
`);this.PageTitle="工作移交"}Init(){return m(this,null,function*(){this.AddGroup("A","请选择规则"),this.SelectItemsByList("Flows","选择待办流程",this.Docs0,!0,this.getTodoList),this.SelectItemsByTreeEns("Flows.Emps","选择被移交人",this.Docs1,!1,l.srcDeptLazily,l.srcDeptRoot,l.srcEmpLazily,"@No=账号@Name=名称@Tel=电话",!0)})}getTodoList(){return m(this,null,function*(){const a=yield new D("BP.WF.HttpHandler.WF").DoMethodReturnJson("Todolist_Init");if(!Array.isArray(a))throw new Error("获取待办列表失败，请检查数据源");const n=[];for(const o of a)(o.AtPara||"").includes("@IsCC=1")||parseInt(o.WFState)!==1&&(o.No=o.WorkID,o.Name=o.FlowName+" (标题："+o.Title+")",n.push(o));return JSON.stringify(n)})}GenerSorts(){return m(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,a,n,o,u){return m(this,null,function*(){if(t==="Flows.Emps"){const i=window.prompt("请输入移交原因");if(!(i!=null&&i.trim())){f.warn("需要输入移交原因");return}const s=this.RequestVal("tb1","Flows"),p=this.RequestVal("tb1","Flows.Emps"),h=yield F.Node_ShiftWork(s,p,i);f.info(h);return}})}}export{v as GPN_WorkShift};
