var l=Object.defineProperty;var i=(a,t,e)=>t in a?l(a,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):a[t]=e;var s=(a,t,e)=>i(a,typeof t!="symbol"?t+"":t,e);import{l as n,U as o,f as d,F as u}from"./entry/index-B5R3Coa4-1746862693206.js";import{a as r}from"./MapExt-DVovzpWn.js";import{SFDBSrc as D}from"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";class K extends n{constructor(e){super("TS.MapExt.GPEActiveDDLSelfSetting");s(this,"DescDoc",` 
  #### 说明
  - SQL格式为:
  - SELECT No,Name FROM Port_Emp WHERE PinYin LIKE '%@Key%' OR No LIKE '%@Key%' OR Name LIKE '%@Key%' 
  - SELECT No,Name FROM CN_City WHERE PinYin LIKE '%@Key%' OR Name LIKE '%@Key%'
  - URL格式为:
  - /DataUser/Handler.ashx?xxx=sss 
  - 方法的格式为:
  - MyFunName
   `);e&&(this.MyPK=e)}get HisUAC(){const e=new o;return e.IsDelete=!1,e.IsUpdate=!0,e.IsInsert=!1,e}get EnMap(){const e=new d("Sys_MapExt","级联下拉框");return e.GroupBarShowModel=1,e.AddGroupAttr("基本设置"),e.AddMyPK(),e.AddTBString(r.FK_MapData,null,"表单ID",!0,!0,0,50,200),e.AddTBString(r.ExtModel,null,"ActiveDDL",!1,!1,0,50,200),e.AddTBString(r.ExtType,null,"ActiveDDL",!1,!1,0,50,200),e.AddTBString(r.AttrOfOper,null,"当前字段",!0,!0,0,50,200),e.AddDDLSQL(r.AttrsOfActive,null,"联动的下拉框",u.SQLOfActiveDDL,!0),e.AddDDLEntities(r.FK_DBSrc,"local","数据源",new D,!0,null,!1),e.AddBoolean("IsSelectVal",!1,"级联是否默认选择值",!0,!0,!1,!1),e.AddTBStringDoc(r.Doc,null,"数据源表达式",!0,!1,!0,this.DescDoc),e.ParaFields=",IsSelectVal,",e.AddTBAtParas(),this._enMap=e,this._enMap}beforeUpdateInsertAction(){return this.DoWay=1,Promise.resolve(!0)}}export{K as GPEActiveDDLSelfSetting};
