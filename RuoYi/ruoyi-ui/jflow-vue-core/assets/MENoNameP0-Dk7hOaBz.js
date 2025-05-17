var c=Object.defineProperty;var d=(r,t,e)=>t in r?c(r,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):r[t]=e;var s=(r,t,e)=>d(r,typeof t!="symbol"?t+"":t,e);var u=(r,t,e)=>new Promise((i,l)=>{var E=a=>{try{o(e.next(a))}catch(n){l(n)}},p=a=>{try{o(e.throw(a))}catch(n){l(n)}},o=a=>a.done?i(a.value):Promise.resolve(a.value).then(E,p);o((e=e.apply(r,t)).next())});import{l as m,U as T,f as S,F as g,D}from"./entry/index-B5R3Coa4-1746862693206.js";import{SFTable as M}from"./SFTable-BpxUt1jb.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./SFDBSrc-DbkqYXE6.js";class U extends m{constructor(e){super("TS.MapExt.MENoNameP0");s(this,"HelpTag",` 
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
   `);e&&(this.MyPK=e)}get HisUAC(){const e=new T;return e.IsDelete=!1,e.IsUpdate=!0,e.IsInsert=!1,e}get EnMap(){const e=new S("Sys_MapExt","编号名称字典(无参)");return e.GroupBarShowModel=1,e.AddGroupAttr("基本设置"),e.AddMyPK(),e.AddTBString("FK_MapData",null,"表单ID",!0,!0,0,50,200),e.AddTBString("ExtModel",null,"ExtModel",!1,!1,0,50,200),e.AddTBString("ExtType",null,"ExtModel",!1,!1,0,50,200),e.AddTBString("AttrOfOper",null,"当前字段",!0,!0,0,50,200),e.AddDDLSQL("Doc",null,"字典表(无参)",g.SQLOfActiveDDLDoc,!0),e.AddTBString("Tag1",null,"数据源表达式",!0,!0,0,50,200,!0,this.HelpTag1),e.AddTBString("Tag2",null,"主机",!0,!0,0,50,200,!0),e.AddTBStringDoc("Tag3",null,"设置信息",!0,!0,!0),this._enMap=e,this._enMap}beforeUpdate(){return u(this,null,function*(){if(D.IsNullOrEmpty(this.Doc)==!0)return Promise.resolve(!0);const e=new M;return e.No=this.Doc,(yield e.RetrieveFromDBSources())==0||(this.Tag1=e.SelectStatement,this.Tag2=e.ConnString),Promise.resolve(!0)})}}export{U as MENoNameP0};
