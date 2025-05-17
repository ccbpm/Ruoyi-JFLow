var n=Object.defineProperty;var i=(s,e,t)=>e in s?n(s,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):s[e]=t;var a=(s,e,t)=>i(s,typeof e!="symbol"?e+"":e,t);import{l,U as o,f as u,F as D}from"./entry/index-B5R3Coa4-1746862693206.js";import{a as r}from"./MapExt-DVovzpWn.js";import{SFDBSrc as p}from"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";class K extends l{constructor(t){super("TS.MapExt.GPEActiveDDL");a(this,"DescSearchtip",` 
  #### 说明
  - 显示在搜索文本框的背景文字.
  - 输入城市名称,比如:beijing,bj,进行搜索.
  - 人员的编号,名称,拼音,进行模糊搜索.
   `);a(this,"DescTag1",` 
   #### 说明
   - zhoupeng 补充
    `);a(this,"DescDoc",` 
  #### 说明
  - SQL格式为:
  - SELECT No,Name FROM Port_Emp WHERE PinYin LIKE '%@Key%' OR No LIKE '%@Key%' OR Name LIKE '%@Key%' 
  - SELECT No,Name FROM CN_City WHERE PinYin LIKE '%@Key%' OR Name LIKE '%@Key%'
  - URL格式为:
  - /DataUser/Handler.ashx?xxx=sss 
  - 方法的格式为:
  - MyFunName
   `);t&&(this.MyPK=t)}get HisUAC(){const t=new o;return t.IsDelete=!1,t.IsUpdate=!0,t.IsInsert=!1,t}get EnMap(){const t=new u("Sys_MapExt","级联下拉框");return t.AddGroupAttr("基本设置"),t.AddMyPK(),t.AddTBString(r.FK_MapData,null,"表单ID",!0,!0,0,50,200),t.AddTBString(r.ExtModel,null,"ActiveDDL",!1,!1,0,50,200),t.AddTBString(r.ExtType,null,"ActiveDDL",!1,!1,0,50,200),t.AddTBString(r.AttrOfOper,null,"当前字段",!0,!0,0,50,200),t.AddDDLSQL(r.AttrsOfActive,null,"联动的字段",D.SQLOfAttrsOfActive(),!0),t.AddDDLSysEnum(r.DBType,0,"数据源类型",!0,!0,"DBType","@0=执行SQL@1=执行url返回JSON@2=执行CCFromRef.js返回JSON",null,!1),t.AddDDLEntities(r.FK_DBSrc,"local","数据源",new p,!0,null,!1),t.AddTBString(r.Doc,null,"数据源表达式",!0,!1,0,50,200,!0,this.DescDoc),this._enMap=t,this._enMap}}export{K as GPEActiveDDL};
