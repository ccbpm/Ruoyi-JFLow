var l=Object.defineProperty;var h=(o,r,s)=>r in o?l(o,r,{enumerable:!0,configurable:!0,writable:!0,value:s}):o[r]=s;var i=(o,r,s)=>h(o,typeof r!="symbol"?r+"":r,s);var P=(o,r,s)=>new Promise((S,m)=>{var a=t=>{try{p(s.next(t))}catch(c){m(c)}},C=t=>{try{p(s.throw(t))}catch(c){m(c)}},p=t=>t.done?S(t.value):Promise.resolve(t.value).then(a,C);p((s=s.apply(o,r)).next())});import{PCenter as u}from"./PCenter-DbUtxw5j.js";import{P as g,F as n,W as d,G,m as A}from"./entry/index-B5R3Coa4-1746862693206.js";import{D as T}from"./DBAccess-CZ0wdWXU.js";import{AuthType as e}from"./AuthType-CrkD6Sy7.js";import{b as D}from"./antd-C8r6Ue4p.js";import"./PowerCenter-Dl8ZnZaz.js";import"./vue-B6GVRDGm.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";class K extends g{constructor(){super("GPN_PCenter");i(this,"Docs0",`
  #### 帮助
  - 所有人都可以有权限。
  #### 配置图
   ![输入图片说明](./resource/CCFast/GPM/PCenter/Img/Anyone.png "屏幕截图.png") 

`);i(this,"Docs1",`
  #### 帮助
  - 只有管理员有权限。
  #### 配置图
  ![输入图片说明](./resource/CCFast/GPM/PCenter/Img/Admin.png "屏幕截图.png") 

  `);i(this,"Docs2",`
  #### 帮助
  - 管理员和二级管理员有权限。
  #### 配置图
  ![输入图片说明](./resource/CCFast/GPM/PCenter/Img/AdminerAndAmin2.png "屏幕截图.png") 

  
  `);i(this,"Docs3",`
  #### 帮助
  - 按选择的人员赋权。
  #### 配置图
  - ![输入图片说明](./resource/CCFast/GPM/PCenter/Img/Emp.png "屏幕截图.png") 
  `);i(this,"Docs4",`
  #### 帮助
  - 按选择的角色人员赋权。
  #### 配置图
   ![输入图片说明](./resource/CCFast/GPM/PCenter/Img/Stations.png "屏幕截图.png") 
  `);i(this,"Docs5",`
  #### 帮助
  - 按选择的部门人员赋权。
  #### 配置图
   ![输入图片说明](./resource/CCFast/GPM/PCenter/Img/Dept.png "屏幕截图.png") 
  `);i(this,"Docs6",`
  #### 帮助
  - 自动抄送给要绑定的人员.1. 输入的SQL是一个查询语句，返回的一行的第一列数据。
  - 该数据大于0 ，就是真(可以拥有此权限)，否则就是假（不能操作此权限）。
  - SQL语句支持ccbpm的表达式，比如：SELECT count(*) FROM Port_Dept WHERE No='@WebUser.DeptNo'。
  #### 说明
  - @WebUser.No 当前登录的人员编号
  - @WebUser.DeptNo 当前登录的部门编号
  - @RDT 是当前日期， 比如：2020-01-01
  - @DateTime 是当前时间， 比如：2020-01-01 10:09
  #### 配置图
   ![输入图片说明](./resource/CCFast/GPM/PCenter/Img/Sql.png "屏幕截图.png") 
  `);this.ForEntityClassID="TS.GPM.PCenter",this.PageTitle="新建权限"}Init(){this.AddGroup("A","本组织权限"),this.AddBlank(e.Anyone,"所有人(本组织)",this.Docs0),this.AddBlank(e.Adminer,"管理员",this.Docs1),this.AddBlank(e.AdminerAndAdmin2,"管理员、二级管理员",this.Docs2),this.SelectItemsByTreeEns(e.Emps,"按人员计算",this.Docs3,!0,n.srcDeptLazily,"0",n.srcEmpLazily,"",!0),this.SelectItemsByGroupList(e.Stations,"按角色计算",this.Docs4,!0,n.srcStationTypes,n.srcStations),this.SelectItemsByTree(e.Depts,"按部门计算",this.Docs5,!0,n.srcDepts,n.srcDeptRoot),this.TextBox1_Name(e.SQL,"按SQL计算",this.Docs6,"查询SQL","SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo' ","输入的SQL返回"),this.AddGroup("B","高级权限"),this.AddBlank(e.AnyOrgs,"所有组织",this.HelpUn),this.SelectItemsByList(e.SpecOrgs,"指定组织所有人员",this.Docs4,!0,n.srcOrgs),this.SelectItemsByGroupList(e.SpecOrgStations,"指定组织的指定岗位",this.HelpTodo,!0,n.srcStationTypes,n.srcStations),this.SelectItemsByTree(e.SpecOrgDepts,"指定组织的指定指定部门",this.Docs5,!0,n.srcDepts,"0")}GenerSorts(){return P(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(s,S,m,a,C){return P(this,null,function*(){const p=this.RefPKVal,t=new u;if(t.CtrlObj=this.RequestVal("CtrlObj"),t.CtrlPKVal=p,t.CtrlModel=s,t.CtrlModelT=this.GetPageName(s),t.IDs=m,t.IDsT=a,t.OrgNo=d.OrgNo,t.MyPK=T.GenerGUID(),(s===e.Anyone||s===e.Adminer||s===e.AdminerAndAdmin2||s===e.AnyOrgs||s===e.SpecOrgs)&&(t.IDs="无",t.IDNames="无",t.MyPK=t.CtrlPKVal+"_"+s,(yield t.IsExits())==!0)){D.info("已经存在这个模式");return}const c=new Map([[e.Emps,"TS.GPM.PCenterEmp"],[e.Depts,"TS.GPM.PCenterDept"],[e.Stations,"TS.GPM.PCenterStation"],[e.SQL,"TS.GPM.PCenterSQL"],[e.SpecOrgStations,"TS.GPM.SpecOrgStation"],[e.SpecOrgs,"TS.GPM.SpecOrg"]]);return t.SetPara("EnName",c.get(s)||"None"),yield t.Insert(),D.info("创建成功"),new G(A.CloseAndReload)})}}export{K as GPN_PCenter};
