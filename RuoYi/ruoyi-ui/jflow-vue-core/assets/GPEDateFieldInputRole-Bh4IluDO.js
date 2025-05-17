var s=Object.defineProperty;var o=(a,e,t)=>e in a?s(a,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):a[e]=t;var r=(a,e,t)=>o(a,typeof e!="symbol"?e+"":e,t);import{l as E,U as i,f as p,F as u}from"./entry/index-B5R3Coa4-1746862693206.js";import{a as n}from"./MapExt-DVovzpWn.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";class S extends E{constructor(t){super("TS.MapExt.GPEDateFieldInputRole");r(this,"JSDesc",` 
  #### 说明
  计算后要触发的脚本函数(比如:求和以后要激活的function)，该脚本要求写入到:DataUserJSLabMyFromID_Self.js
   `);r(this,"DescTag1",` 
   #### 说明
   - zhoupeng 补充
    `);r(this,"DescDoc",` 
  #### 说明
  - SQL格式为:
  - SELECT No,Name FROM Port_Emp WHERE PinYin LIKE '%@Key%' OR No LIKE '%@Key%' OR Name LIKE '%@Key%' 
  - SELECT No,Name FROM CN_City WHERE PinYin LIKE '%@Key%' OR Name LIKE '%@Key%'
  - URL格式为:
  - /DataUser/Handler.ashx?xxx=sss 
  - 方法的格式为:
  - MyFunName
   `);t&&(this.MyPK=t)}get HisUAC(){const t=new i;return t.IsDelete=!0,t.IsUpdate=!0,t.IsInsert=!0,t}get EnMap(){const t=new p("Sys_MapExt","日期输入规则");return t.AddGroupAttr("基本设置"),t.AddMyPK(),t.AddTBString(n.FK_MapData,null,"表单ID",!0,!0,0,100,100),t.AddDDLStringEnum(n.Tag,"GTE","运算符","@GT=大于@GTE=大于等于@IT=小于@ITE=小于等于@EQ=等于@NEQ=不等于",!0,null,!1),t.AddDDLSQL(n.Tag1,null,"日期字段",u.SQLOfDateFieldInputRole,!0,null,!0),this._enMap=t,this._enMap}}export{S as GPEDateFieldInputRole};
