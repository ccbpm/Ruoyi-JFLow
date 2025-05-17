var T=Object.defineProperty;var N=(i,r,e)=>r in i?T(i,r,{enumerable:!0,configurable:!0,writable:!0,value:e}):i[r]=e;var n=(i,r,e)=>N(i,typeof r!="symbol"?r+"":r,e);var p=(i,r,e)=>new Promise((S,l)=>{var a=t=>{try{m(e.next(t))}catch(o){l(o)}},c=t=>{try{m(e.throw(t))}catch(o){l(o)}},m=t=>t.done?S(t.value):Promise.resolve(t.value).then(a,c);m((e=e.apply(i,r)).next())});import{P as R,F as s,G as C,m as D}from"./entry/index-B5R3Coa4-1746862693206.js";import{CCRole as E}from"./CCRole-DkWGiGnM.js";import{GloComm as h}from"./GloComm-B1xAfTWw.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FrmTrack-Ct-No0Nq.js";class W extends R{constructor(){super("GPN_CCRole");n(this,"ByField",`
  #### 帮助
  - 按照节点表单的字段作为抄送人.
  - 通常是在节点表单上加一个字段,这个字段存储的是人员账号，多个人员使用逗号分开.
  #### 实例
  - 填写表单的字段ID ：ChaoSong
`);n(this,"Docs1",`
  #### 帮助
  - 自动抄送给要绑定的人员.
`);n(this,"Desc5",`
  #### 帮助
  - 绑定节点的接收人规则.
  - 请点击设置接受人规则.
`);n(this,"BySQL",`
  #### 帮助
  - 按SQL计算抄送人员.
  - 有一个规则
  #### DEMO
  - 抄送本部门的人员.
  - SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo';
  
`);this.ForEntityClassID="TS.AttrNode.CCRole",this.PageTitle="新建抄送规则"}Init(){this.AddGroup("A","请选择规则"),this.SelectItemsByTreeEns("1","按人员计算",this.Docs1,!0,s.srcDeptLazily,s.srcDeptRoot,s.srcEmpLazily,"@No=账号@Name=名称@Tel=电话",!0),this.SelectItemsByGroupList("2","按角色计算",this.Docs1,!0,s.srcStationTypes,s.srcStations),this.SelectItemsByTree("3","按部门计算",this.Docs1,!0,s.srcDepts,s.srcDeptRoot),this.TextSQL("4","按SQL计算",this.BySQL,"查询SQL","SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo' ","输入的SQL返回人员集合具有No,Name两个列."),this.TextBox1_Name("0","按表单字段计算",this.ByField,"表单字段","","请输入节点表单的字段名."),this.AddBlank("5","按接受人规则计算",this.Desc5),this.SelectItemsByTree("6","抄送给指定部门负责人",this.Docs1,!0,s.srcDepts,s.srcDeptRoot)}GenerSorts(){return p(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,S,l,a,c){return p(this,null,function*(){const m=this.RefPKVal,t=new E;t.NodeID=m,t.CCRoleExcType=e,t.EnIDs=l,t.Tag2=a,t.EnIDsT=a,t.FlowNo=this.RequestVal("FlowNo");let o="";if(e==="0"&&(o="TS.AttrNode.CCRoleByField"),e==="1"&&(o="TS.AttrNode.CCRoleByEmp"),e==="2"&&(o="TS.AttrNode.CCRoleByStation"),e==="3"&&(o="TS.AttrNode.CCRoleByDept"),e==="6"&&(o="TS.AttrNode.CCRoleByDept"),e==="4"&&(o="TS.AttrNode.CCRoleBySQL",t.DBSrc=l,t.Tag1=a),e==="5"){if(o="TS.AttrNode.CCRoleByDeliveryWay",t.MyPK=this.RefPKVal,t.Tag1="设置接受人规则.",(yield t.IsExits())==!0)return new C(D.Message,"err@该规则已经存在,只允许有一个规则.");t.SetPara("EnName",o),yield t.Insert();return}t.SetPara("EnName",o),yield t.Insert();const y=h.UrlEn(o,t.MyPK);return new C(D.GoToUrl,y)})}}export{W as GPN_CCRole};
