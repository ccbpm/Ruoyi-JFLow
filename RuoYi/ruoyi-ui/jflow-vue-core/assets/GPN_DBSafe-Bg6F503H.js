var N=Object.defineProperty;var p=(n,i,t)=>i in n?N(n,i,{enumerable:!0,configurable:!0,writable:!0,value:t}):n[i]=t;var d=(n,i,t)=>p(n,typeof i!="symbol"?i+"":i,t);var r=(n,i,t)=>new Promise((a,h)=>{var B=s=>{try{o(t.next(s))}catch(e){h(e)}},A=s=>{try{o(t.throw(s))}catch(e){h(e)}},o=s=>s.done?a(s.value):Promise.resolve(s.value).then(B,A);o((t=t.apply(n,i)).next())});import{DBSafe as m}from"./DBSafe-LPNV1dPp.js";import{P as D,G as k,m as y}from"./entry/index-B5R3Coa4-1746862693206.js";import{GloComm as S}from"./GloComm-B1xAfTWw.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";class x extends D{constructor(){super("GPN_DBSafe");d(this,"SelfOnly_DBList",`
  #### 帮助
  - 只能查询自己创建的数据.
  - 创建记录的字段:EmpNo,RecNo,FK_Emp,StarterNo,CreaterNo 
  - 只能按照关键字段与WebUser.No查询.
`);d(this,"DeptOnly_DBList",`
#### 帮助
- 只能查询本部门的数据.
- 创建记录的字段:FK_Dept,DeptNo,CreateDeptNo.
- 当前字段里包含: 字段时只能按照这个字段与WebUser.DeptNo.
`);d(this,"DeleteByFlag",`
  #### 帮助
  - 默认为物理删除.
  - 逻辑删除: 删除Frm_GenerBill, Frm_GenerWorker, 并且把单据的表Bill_XXXX 的BillState 设置为-1
  - 逻辑删除不删除真实的数据.
`);d(this,"DeptLeader",`
  #### 帮助
  - 部门负责人的数据存储在:  Port_Dept.Leader字段
`);d(this,"NOrg",`
#### 帮助
- 直线上级组织的人员可以看到.
- 基于当前登录人员的帐号所在组织确定他有多少直线组织集合,如下SQL表达式.
- SELECT No FROM Port_Org WHERE TreeNos LIKE '%,@WebUser.Org,%'
- 如果数据列表有错误，请检查单据表的OrgNo字段数据是否正确,完整, 可以带入如下sql验证:
- SELECT * FROM BillTable WHERE OrgNo IN (SELECT No FROM Port_Org WHERE TreeNos LIKE '%,@WebUser.Org,%')
#### 其他
- 该表达式与其他表达式形成互斥关系.
`);d(this,"None",`
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
`);this.ForEntityClassID="TS.Sys.DBSafe",this.PageTitle="数据安全策略"}GenerSorts(){return r(this,null,function*(){return Promise.resolve([])})}Init(){return r(this,null,function*(){const t=this.RequestVal("DBRole");t=="DBList"&&(this.AddGroup("A","通用控制"),this.AddBlank("None","不控制(默认)",this.None),this.AddBlank("SelfOnly","只能查看自己创建的数据,RecNo,FK_Emp关键字段计算",this.SelfOnly_DBList),this.AddBlank("DeptOnly","本部门的人员可以查看本部门的数据,DeptNo",this.DeptOnly_DBList),this.AddBlank("DeptLeader","部门负责人可以查看本部门的数据",this.DeptLeader),this.AddBlank("ByStations","指定岗位下的人员可以查看全部数据",this.None),this.AddBlank("ByDepts","指定部门下的人员可以查看数据全部数据",this.None),this.AddBlank("ByEmps","指定人员可以查看数据全部数据",this.None),this.TextArea("ByExp","按表达式计算",this.None,"输入表达式:","","请点帮助信息如何填写..."),this.AddIcon("icon-feed","ByExp"),this.AddBlank("Adminer","管理员admin可以查看数据全部数据",this.None),this.AddGroup("B","集团组织模式"),this.AddBlank("OrgOnly","本组织的人员可以查看本组织数据(默认)",this.None),this.AddBlank("Admin2","二级管理员可以查看本组织的全部数据",this.None),this.AddBlank("POrg","可以查看本组织以及下1级组织数据",this.None),this.AddBlank("NOrg","可以被直线父级组织所看到",this.NOrg)),t=="RecNew"&&(this.AddGroup("A","新建规则"),this.AddBlank("None","不控制(任何人都可以新建)",this.None),this.AddBlank("ByStations","指定的岗位可以新建",this.None),this.AddBlank("ByDepts","指定的部门可以新建",this.None),this.AddBlank("ByEmps"," 指定的人员可以新建",this.None),this.AddBlank("DeptLeader","部门负责人可以新建",this.None),this.AddBlank("Adminer","管理员可以新建",this.None),this.AddBlank("Admin2","二级管理员可以新建",this.None)),t=="RecDelete"&&(this.AddGroup("A","删除规则"),this.AddBlank("None","不控制(任何人都可以删除)",this.None),this.AddBlank("SelfOnly","只能删除自己创建的",this.None),this.AddBlank("DeptLeader","部门负责人可以删除本部门的数据",this.DeptLeader),this.AddBlank("ByStations","指定岗位的人员可以删除所有数据",this.None),this.AddBlank("ByDepts","指定部门的人员可以删除所有数据.",this.None),this.AddBlank("ByEmps"," 指定人员可以删除所有数据.",this.None),this.AddBlank("Adminer","管理员可以删除",this.None),this.AddBlank("Admin2","二级管理员可以删除",this.None),this.AddGroup("B","集团模式"),this.AddBlank("ByOrg","可以删除本组织的数据",this.None)),t=="RecJuggle"&&(this.AddGroup("A","数据篡改(归档后修改特定字段)"),this.AddBlank("None","不能篡改",this.None),this.AddBlank("SelfOnly","创建人可以篡改",this.None),this.AddBlank("DeptLeader","部门负责人可以篡改本部门的数据",this.DeptLeader),this.AddBlank("ByStations","指定岗位的人员可以篡改所有数据",this.None),this.AddBlank("ByDepts","指定部门的人员可以篡改所有数据.",this.None),this.AddBlank("ByEmps"," 指定人员可以篡改所有数据.",this.None),this.AddBlank("Adminer","管理员可以篡改",this.None),this.AddBlank("Admin2","二级管理员可以篡改",this.None)),t=="RecSave"&&(this.AddGroup("A","保存权限"),this.AddBlank("None","不控制(任何人都可以保存)",this.None),this.AddBlank("SelfOnly","只能保存自己创建的",this.None),this.AddBlank("DeptLeader","部门负责人可以保存本部门的数据",this.DeptLeader),this.AddBlank("ByStations","指定岗位的人员可以保存所有数据",this.None),this.AddBlank("ByDepts","指定部门的人员可以保存所有数据.",this.None),this.AddBlank("ByEmps"," 指定人员可以保存所有数据.",this.None),this.AddBlank("Adminer","管理员可以保存",this.None),this.AddBlank("Admin2","二级管理员可以保存",this.None)),this.AddIcon("None","icon-drop"),this.AddIcon("SelfOnly","icon-user"),this.AddIcon("ByEmps","icon-user-follow"),this.AddIcon("ByDepts","icon-people"),this.AddIcon("ByStations","icon-people"),this.AddIcon("DeptLeader","icon-user-following")})}Save_TextBox_X(t,a,h,B,A){return r(this,null,function*(){const o=this.RequestVal("RefPKVal"),s=this.RequestVal("DBRole"),e=new m;if(e.MyPK=o+"_"+t+"_"+s,(yield e.RetrieveFromDBSources())==1){alert("该选项【"+e.MyPK+"】已经存在,请点击修改.");return}e.FrmID=o,e.TableName=o,e.MarkID=t,e.DBRole=s,e.MarkName=this.GetPageName(t),e.Docs="无";let l="TS.Sys.DBSafe";return t=="ByStations"&&(l="TS.CCBill.DBRoleStation"),t=="ByDepts"&&(l="TS.CCBill.DBRoleDept"),t=="ByEmps"&&(l="TS.CCBill.DBRoleEmp"),t=="ByExp"&&(l="TS.CCBill.DBRoleExp",e.Docs=h),e.SetPara("EnName",l),yield e.Insert(),new k(y.GoToUrl,S.UrlEn(l,e.MyPK))})}}export{x as GPN_DBSafe};
