var P=Object.defineProperty;var w=(r,t,a)=>t in r?P(r,t,{enumerable:!0,configurable:!0,writable:!0,value:a}):r[t]=a;var i=(r,t,a)=>w(r,typeof t!="symbol"?t+"":t,a);var B=(r,t,a)=>new Promise((S,N)=>{var c=s=>{try{n(a.next(s))}catch(m){N(m)}},o=s=>{try{n(a.throw(s))}catch(m){N(m)}},n=s=>s.done?S(s.value):Promise.resolve(s.value).then(c,o);n((a=a.apply(r,t)).next())});import{SFDBSrc as d}from"./SFDBSrc-DbkqYXE6.js";import{P as L,G as l,m as x}from"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";const e=class e extends L{constructor(){super("GPN_SFDBSrc"),this.ForEntityClassID="TS.Sys.SFDBSrc",this.PageTitle="创建数据源"}Init(){this.AddGroup("A","关系数据库"),this.TextBox3_NameNoNote("MySQL","MySQL数据库",e.MySQL,"MySQL","编号","名称","连接串","MySQL数据库"),this.TextBox3_NameNoNote("MSSQL","SQLServer数据库",e.MSSQL,"MSSQL","编号","名称","连接串","SQLServer数据库"),this.TextBox3_NameNoNote("Oracle","Oracle数据库",e.Oracle,"Oracle","编号","名称","连接串","Oracle数据库"),this.TextBox3_NameNoNote("Informix","Informix数据库",e.DBSrc_NoName,"Informix","编号","名称","连接串","Oracle数据库"),this.TextBox3_NameNoNote("KingBaseR3","人大金仓库R3",e.KindingBase,"KingBaseR3","编号","名称","连接串","人大金仓库R3"),this.TextBox3_NameNoNote("KingBaseR6","人大金仓库R6",e.KindingBase,"KingBaseR6","编号","名称","连接串","人大金仓库R6"),this.TextBox3_NameNoNote("UX","优漩",e.DBSrc_NoName,"UX","编号","名称","连接串","优漩数据库"),this.TextBox3_NameNoNote("DM","达梦",e.DBSrc_NoName,"DM","编号","名称","连接串","达梦"),this.AddGroup("B","服务url"),this.TextBox3_NameNoNote("WebApi","WebApi模式",e.DBSrc_NoName,"WebApi","编号","名称","主机地址","WebApi模式")}GenerSorts(){return B(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,a,S,N,c){return B(this,null,function*(){const o=new d;if(o.No=N,(yield o.IsExits())==!0)return new l(x.Error,"编号已经存在");o.Name=S,o.DBSrcType=t,o.ConnString=c,o.SetPara("EnName","TS.Sys.SFDBSrcSQL"),t=="WebApi"&&o.SetPara("EnName","TS.Sys.SFDBSrcWebApi"),yield o.Insert();const n="/@/WF/Comm/En.vue?EnName="+o.GetParaString("EnName","")+"&PKVal="+o.No;return new l(x.GoToUrl,n)})}};i(e,"MySQL",`
  #### 连接到MySQL数据库
   - 支持mysql各种版本.
   - **C#连接串实例**
     Data Source=127.0.0.1;Port=3306;Initial Catalog=testDB;User ID=root;Password=ccflow;SslMode=none;
   - **Java连接串实例**
     IP=127.0.0.1;Port=3306;DBName=testDB;Username=root;Password=ccflow;
  `),i(e,"MSSQL",`
  #### 连接到 MSSQL 数据库
   - 支持 MSSQL 各种版本.
   - **C#连接串实例**
     Data Source=127.0.0.1;Port=1433;Initial Catalog=testDB;User ID=root;Password=ccflow;SslMode=none;
   - **Java连接串实例**
     IP=127.0.0.1;Port=1433;DBName=testDB;Username=root;Password=ccflow;
  `),i(e,"Oracle",`
  #### 连接到 Oracle 数据库
   - 支持 Oracle 各种版本.
   - **C#连接串实例**
     Data Source=127.0.0.1:1521/ORCL;User Id=CCFLOW;Password=CCFLOW123;
   - **Java连接串实例**
     IP=127.0.0.1;Port=1521;DBName=testDB;Username=root;Password=ccflow;
  `),i(e,"KindingBase",`
  #### 连接到 KindingBase 数据库
   - 支持 KindingBase 各种版本.
   - **C#连接串实例**
     Data Source=127.0.0.1;Port=54321;Initial Catalog=testDB;User ID=root;Password=ccflow;
   - **Java连接串实例**
     IP=127.0.0.1;Port=54321;DBName=testDB;Username=root;Password=ccflow;
  `),i(e,"DBSrc_NoName",`
  #### 帮助
   - URL数据源也称为webapi数据源,微服务数据源.
   - 通过web服务获得数据源的一种方式.
   - 在数据源定义主机地址,在后面定义.
  `);let D=e;export{D as GPN_SFDBSrc};
