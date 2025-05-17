var D=Object.defineProperty;var h=(s,i,t)=>i in s?D(s,i,{enumerable:!0,configurable:!0,writable:!0,value:t}):s[i]=t;var B=(s,i,t)=>h(s,typeof i!="symbol"?i+"":i,t);var n=(s,i,t)=>new Promise((m,a)=>{var p=o=>{try{r(t.next(o))}catch(e){a(e)}},d=o=>{try{r(t.throw(o))}catch(e){a(e)}},r=o=>o.done?m(o.value):Promise.resolve(o.value).then(p,d);r((t=t.apply(s,i)).next())});import{P as c,G as y,m as R}from"./entry/index-B5R3Coa4-1746862693206.js";import{DBRole as u}from"./DBRole-DQUduH7f.js";import{GloComm as P}from"./GloComm-B1xAfTWw.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";class x extends c{constructor(){super("GPN_DBFlowRole");B(this,"DeptLeader",`
  #### 帮助
  - 部门负责人的数据存储在:  Port_Dept.Leader字段
`);B(this,"None",`
  #### 帮助
  - 控制的内容为,列表查看权限控制.
  - 数据权限的控制方式有菜单栏里几种.
  #### 控制解析规则.
  1. 首先判断当前登录人员是否有查看全局的权限?
  1. 其次判断按部门权限.
  1. 最后判断人员权限.
  #### 可以查看全部数据的权限规则
  1. 控制权限数据为 0 条记录.
  1. 按照岗位，部门判断有任何一个条件成立.
`);this.ForEntityClassID="TS.CCBill.DBRole",this.PageTitle="数据权限规则"}GenerSorts(){return n(this,null,function*(){return Promise.resolve([])})}Init(){return n(this,null,function*(){this.AddGroup("A","数据权限规则"),this.AddBlank("SelfOnly","只能查看自己创建的流程",this.None),this.AddIcon("SelfOnly","icon-user"),this.AddBlank("DeptOnly","本部门的人员可以查看本部门的流程",this.None),this.AddIcon("ByEmps","icon-user-follow"),this.AddBlank("DeptLeader","部门负责人可以查看本部门的里流程",this.DeptLeader),this.AddIcon("DeptLeader","icon-user-following"),this.AddBlank("ByStations","指定岗位下的人员可以查看全部流程",this.None),this.AddIcon("ByStations","icon-people"),this.AddBlank("ByDepts","指定部门下的人员可以查看编辑数据全部流程",this.None),this.AddIcon("ByDepts","icon-people"),this.AddBlank("ByEmps","指定人员可以查看编辑数据全部流程",this.None)})}Save_TextBox_X(t,m,a,p,d){return n(this,null,function*(){const r=this.RequestVal("RefPKVal");let o=this.RequestVal("DBRole");o&&(o="DBList");const e=new u;if(e.MyPK=r+"_"+t+"_"+o,alert(e.MyPK),(yield e.RetrieveFromDBSources())==1){alert("该选项已经存在,请点击修改.");return}e.FrmID=r,e.MarkID=t,e.DBRole="DBList",e.MarkName=this.GetPageName(t),e.Docs="无";let l="TS.CCBill.DBRole";return t=="ByStations"&&(l="TS.CCBill.DBRoleStation"),t=="ByDepts"&&(l="TS.CCBill.DBRoleDept"),t=="ByEmps"&&(l="TS.CCBill.DBRoleEmp"),e.SetPara("EnName",l),yield e.Insert(),new y(R.GoToUrl,P.UrlEn(l,e.MyPK))})}}export{x as GPN_DBFlowRole};
