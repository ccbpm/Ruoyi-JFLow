var T=Object.defineProperty;var h=(a,s,e)=>s in a?T(a,s,{enumerable:!0,configurable:!0,writable:!0,value:e}):a[s]=e;var p=(a,s,e)=>h(a,typeof s!="symbol"?s+"":s,e);var d=(a,s,e)=>new Promise((t,r)=>{var i=n=>{try{c(e.next(n))}catch(S){r(S)}},u=n=>{try{c(e.throw(n))}catch(S){r(S)}},c=n=>n.done?t(n.value):Promise.resolve(n.value).then(i,u);c((e=e.apply(a,s)).next())});import{E as P,U as I,f as b,h as f,i as A,bo as m,B as N,j as g}from"./entry/index-B5R3Coa4-1746862693206.js";import{SFTableAttr as l}from"./SFTable-BpxUt1jb.js";import{SFDBSrc as B}from"./SFDBSrc-DbkqYXE6.js";import{GloDBsrcHelper as o}from"./GloDBSrcHelper-CD3_17zK.js";import{SFApiParas as w}from"./SFApiPara-DAVcqP8_.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class D extends P{constructor(e){super("TS.FrmUI.SFTableWebApiNoName");p(this,"descPost",`
  #### 帮助
   -  格式:  /xxx.do?userID=@WebUser.No&tike=@Token&workID=@WorkID&ndID=@NodeID&je=@JinE
   -  参数分为系统参与与自定义参数.
   -  系统参数: 登陆人员的信息,比如:@WebUser.No 登陆人员账号,@WebUser.Name 名称,@WebUser.DeptNo 部门编号,@WebUser.OrgNo 组织编号, @Token token.
   -  自定义参数: &je=@JE   金额是自定义参数. 

   #### 什么是调用主体？
  - 使用字典的对象就是调用主体, 调用主体大概是: 接受人规则、绑定下拉框字典、级联字典.
  - 调用主体在调用的环境里有一些数据，这些数据对当前字典来说都可以是参数.
  `);p(this,"descApi",`
  #### 帮助
   -  格式:  /xxx.do?userID=@WebUser.No&tike=@Token&workID=@WorkID&ndID=@NodeID&je=@JinE
   -  参数分为系统参与与自定义参数.
   -  系统参数: 登陆人员的信息,比如:@WebUser.No 登陆人员账号,@WebUser.Name 名称,@WebUser.DeptNo 部门编号,@WebUser.OrgNo 组织编号, @Token token.
   -  自定义参数: &je=@JE   金额是自定义参数. 

   #### 什么是调用主体？
  - 使用字典的对象就是调用主体, 调用主体大概是: 接受人规则、绑定下拉框字典、级联字典.
  - 调用主体在调用的环境里有一些数据，这些数据对当前字典来说都可以是参数.
  `);e&&this.setPKVal(e)}get HisUAC(){const e=new I;return e.IsDelete=!0,e.IsUpdate=!0,e.IsInsert=!0,e}get EnMap(){const e=new b("Sys_SFTable","WebAPI字典表");e.AddTBStringPK(l.No,null,"编号",!0,!0,1,200,200),e.AddTBString(l.Name,null,"名称",!0,!1,0,200,200),e.AddDDLSysEnum(l.CodeStruct,0,"字典表类型",!0,!1,l.CodeStruct,"@0=编号名称类型@1=树结构类型"),e.AddDDLEntities(l.FK_SFDBSrc,"local","数据源",new B,!1),e.AddDDLStringEnum("RequestMethod","Get","请求方式","@Get=Get@POST=POST",!0),e.AddDDLSysEnum("IsPara",0,"参数个数",!0,!1,"IsPara","@0=无参数@1=有参数"),e.AddTBString("SelectStatement",null,"接口名称",!0,!1,0,200,600,!0,o.Help_APISelectStatement),e.AddTBStringDoc("PostDoc",null,"Body内容",!0,!1,!0,o.Help_PostDoc),e.AddTBStringDoc("HeaderDoc",null,"Header内容",!0,!1,!0,o.Help_HeaderDoc),e.AddTBString("ParamAlia",null,"参数别名",!0,!1,0,200,200,!0,o.Help_ParamAlia),e.AddTBString("JsonNode","","WebApi节点名称",!0,!1,0,1e3,600,!1,o.Help_JsonNode),e.AddGroupAttr("返回数据&列对应"),e.AddTBString("FieldNo","","编号属性No",!0,!1,0,200,600,!0,o.FieldNo),e.AddTBString("FieldName","","名称属性Name",!0,!1,0,200,600,!0,o.FieldNo),e.AddGroupAttr("测试设置"),e.AddTBString("TestParas",null,"测试参数",!0,!1,0,1e3,600,!0,o.Help_TestParas),e.AddTBAtParas(4e3),e.AddGroupMethod("测试");const t=new f;t.Title="原始数据",t.RefMethodType=A.Func,t.Warning="",t.ClassMethod="DoUrl",e.AddRefMethod(t);const r=new f;return r.Title="结构化数据",r.RefMethodType=A.Func,r.Warning="",r.ClassMethod="DoCodeStruct",e.AddRefMethod(r),this._enMap=e,this._enMap}beforeUpdate(){return m.IsJsonOrBlank(this.PostDoc,"PostDoc")==!1||m.IsJsonOrBlank(this.HeaderDoc,"HeaderDoc")==!1?Promise.resolve(!1):Promise.resolve(!0)}DoUrl(){return d(this,null,function*(){if(this.IsPara==1&&!this.TestParas)return"tabOpen@请设置测试参数,执行保存然后执行测试.";const e=new N("BP.Sys.SFTable",this.No);yield e.Init(),yield e.Retrieve();const t=yield e.DoMethodReturnString("TS_YuanShi_Data_WebApi");return`tabOpen@
原始数据:
`+JSON.stringify(t,null,2)})}DoCodeStruct(){return d(this,null,function*(){if(this.IsPara==1&&!this.TestParas)return"tabOpen@请设置测试参数.";const e=new N("BP.Sys.SFTable",this.No);yield e.Init(),yield e.Retrieve();const t=yield e.DoMethodReturnString("TS_Stuct_Data_WebApi");return`tabOpen@
结构数据:
`+JSON.stringify(t,null,2)})}static CheckIsParas(e,t){return d(this,null,function*(){const r=new w;yield r.Init(),yield r.Retrieve("DBSrcNo",t);for(let i=0;i<r.length;i++){const u=r[i];e=e.replaceAll("@"+u.AttrKey,"xxx")}return e&&e.includes("@")?1:0})}beforeUpdateInsertAction(){return d(this,null,function*(){const e=this.SelectStatement+this.PostDoc+this.HeaderDoc;return this.IsPara=yield D.CheckIsParas(e,this.FK_SFDBSrc),Promise.resolve(!0)})}}class H extends g{get GetNewEntity(){return new D}constructor(){super()}}export{D as SFTableWebApiNoName,H as SFTableWebApiNoNames};
