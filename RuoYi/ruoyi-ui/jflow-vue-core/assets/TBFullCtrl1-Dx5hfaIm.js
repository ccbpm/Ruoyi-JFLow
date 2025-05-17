var a=Object.defineProperty;var o=(r,t,e)=>t in r?a(r,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):r[t]=e;var s=(r,t,e)=>o(r,typeof t!="symbol"?t+"":t,e);import{l as p,U as n,f as i}from"./entry/index-B5R3Coa4-1746862693206.js";import{a as l}from"./MapExt-DVovzpWn.js";import{SFDBSrc as u}from"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";class D extends p{constructor(e){super("TS.MapExt.TBFullCtrl1");s(this,"Desc1",`
  #### 说明
   - 填充SQL帮助
   1. 设置一个查询的SQL语句，该SQL必须包含 No, Name 列, 用与展示快速补全的部分。
   1. 该SQL必须包含 @Key 关键字，@Key 输入文本框的值.
   1. SQL返回的列与其他字段名称保持一致，就可以完成控件数据的自动填充。
   1. 比如: SELECT No, Name FROM WF_Emp WHERE No LIKE '@key%'
   1. 为防止URL编码规定like的第一个%写成[%],如果like '%@Key%' 写成'[%]@Key%'
   - 填充Url帮助
   1. 设置URL，返回的必须是json格式。
   1. 比如: /App/Handler.ashx?DoType=Emps&Key=@Key
   1. @Key 是输入的关键字

 
  `);this.RefEnName="TS.Sys.MapExt",e&&(this.MyPK=e)}get HisUAC(){const e=new n;return e.IsDelete=!0,e.IsUpdate=!0,e.IsInsert=!0,e}get EnMap(){const e=new i("Sys_MapExt","文本框自动完成");return e.AddMyPK(),e.AddDDLEntities(l.FK_DBSrc,"local","数据源",new u,!0,null,!1),e.AddTBStringDoc(l.Tag4,null,"搜索列表数据源配置:",!0,!1,!0,this.Desc1),e.AddDDLSysEnum("ShowModel",0,"显示内容",!0,!0,"TBFullShowModel","@0=显示编号@1=显示名称",null,!1),e.SetHelperAlert("ShowModel","当选择一个Item时候，把那个数据填充到当前文本框里?"),e.AddTBAtParas(4e3),e.ParaFields=",ShowModel,",this._enMap=e,this._enMap}}export{D as TBFullCtrl1};
