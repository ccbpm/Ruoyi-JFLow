var m=Object.defineProperty;var T=(r,t,e)=>t in r?m(r,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):r[t]=e;var s=(r,t,e)=>T(r,typeof t!="symbol"?t+"":t,e);var l=(r,t,e)=>new Promise((u,i)=>{var E=a=>{try{o(e.next(a))}catch(n){i(n)}},p=a=>{try{o(e.throw(a))}catch(n){i(n)}},o=a=>a.done?u(a.value):Promise.resolve(a.value).then(E,p);o((e=e.apply(r,t)).next())});import{l as d,U as c,f as g,D as M}from"./entry/index-B5R3Coa4-1746862693206.js";import{SFTable as S}from"./SFTable-BpxUt1jb.js";import{M as N}from"./MapExt-DVovzpWn.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./SFDBSrc-DbkqYXE6.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";class R extends d{constructor(e){super("TS.MapExt.MENoNameP1");s(this,"HelpTag",` 
  #### resufull数据源说明
  - 点击保存按钮系统自动出现【数据源表达式】.
  - 比如数据源表达式:/get_BU_PDT/{accesstoken}/{BU}
  - 标识他需要二个参数,{accesstoken} 与  {BU} ， 这个{BU}就是我们的联动的 @Key, 就是当前下拉框的选中的值.
  - 在【参数格式】里我们输入:{BU}=@Key;{accesstoken}=@WebUser.Token
  - 说明: @WebUser.Token 是系统参数, @Key 是ccform约定的参数.
  #### SQL数据源说明
  - 点击保存按钮系统自动出现【数据源表达式】.
  - 比如数据源表达式:SELECT No,Name FROM Port_Emp WHERE Name='@Key'
  - 参数格式:就可以为空,必须要输入.
  - 比如数据源表达式:SELECT No,Name FROM Port_Emp WHERE Name='@BU'
  - 参数格式: @BU=@Key;
   `);s(this,"HelpTag1",` 
   #### 说明
   - 该字段只读
   - 当您选择一个字典的时候，点击保存该字典要获得数据的表达就会自动填充上来.
    `);s(this,"DescSearchtip",` 
  #### 说明
  - 显示在搜索文本框的背景文字.
  - 输入城市名称,比如:beijing,bj,进行搜索.
  - 人员的编号,名称,拼音,进行模糊搜索.
   `);s(this,"DescTag1",` 
   #### 说明
   - zhoupeng 补充
    `);s(this,"DescDoc",` 
  #### 说明
  - SQL格式为:
  - SELECT No,Name FROM Port_Emp WHERE PinYin LIKE '%@Key%' OR No LIKE '%@Key%' OR Name LIKE '%@Key%' 
  - SELECT No,Name FROM CN_City WHERE PinYin LIKE '%@Key%' OR Name LIKE '%@Key%'
  - URL格式为:
  - /DataUser/Handler.ashx?xxx=sss 
  - 方法的格式为:
  - MyFunName
   `);e&&(this.MyPK=e)}get HisUAC(){const e=new c;return e.IsDelete=!1,e.IsUpdate=!0,e.IsInsert=!1,e}get EnMap(){const e=new g("Sys_MapExt","编号名称字典(有参)");return e.GroupBarShowModel=1,e.AddGroupAttr("基本设置"),e.AddMyPK(),e.AddTBString("FK_MapData",null,"表单ID",!0,!0,0,50,200),e.AddTBString("ExtModel",null,"ExtModel",!1,!1,0,50,200),e.AddTBString("ExtType",null,"ExtModel",!1,!1,0,50,200),e.AddTBString("AttrOfOper",null,"当前字段",!0,!0,0,50,200),N.AddAttrSFTable(e,"Tag4","字典表",1),e.AddTBString("Tag1",null,"数据源表达式",!0,!0,0,50,200,!0,this.HelpTag1),e.AddTBString("Tag2",null,"主机",!0,!0,0,50,200,!0),e.AddTBStringDoc("Tag3",null,"设置信息",!0,!0,!0),this._enMap=e,this._enMap}beforeUpdate(){return l(this,null,function*(){if(M.IsNullOrEmpty(this.Doc)==!0)return Promise.resolve(!0);const e=new S;return e.No=this.Doc,(yield e.RetrieveFromDBSources())==0||(this.Tag1=e.SelectStatement,this.Tag2=e.ConnString),Promise.resolve(!0)})}}export{R as MENoNameP1};
