var o=Object.defineProperty;var s=(r,t,e)=>t in r?o(r,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):r[t]=e;var a=(r,t,e)=>s(r,typeof t!="symbol"?t+"":t,e);import{l as n,U as p,f as N}from"./entry/index-B5R3Coa4-1746862693206.js";import{a as i}from"./MapExt-DVovzpWn.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";class D extends n{constructor(e){super("TS.MapExt.TextAreaFullBlank");a(this,"Desc1",`
  #### 说明
   - 设置一个数据源：必须包含No,Name两个列. 
   - 数据源支持ccbpm表达式,比如: SELECT * FROM Port_Dept WHERE ParentNo='@WebUser.DeptNo'
   - 比如: SELECT No,Name FROM CN_ShengFen 
   - 关于ccbpm表达式: @WebUser.No,@WebUser.Name,@WebUser.DeptNo 是当前用户登陆信息.
   - 暂时不支持字典.
  `);a(this,"Desc2",`
  #### 说明
   1. 设置一个查询的SQL语句，该SQL必须包No,Name 两个列。
   1. 该SQL必须包含 @Key 关键字，@Key 就是上次选择的第1级数据项的编号.
   1. 比如: SELECT No,Name FROM CN_City WHERE ShengFenNo='@Key'
   1. 暂时不支持字典.
  `);a(this,"Desc3",`
  #### 说明
   1. 设置一个查询的SQL语句，该SQL必须包No,Name 两个列。  
   1. 该SQL必须包含 @Key 关键字，@Key 就是上次选择的第2级数据项的编号.
   1. 比如: SELECT No,Name FROM CN_QuXian WHERE CityNo='@Key'
   1. 暂时不支持字典.
  `);e&&(this.MyPK=e)}get HisUAC(){const e=new p;return e.IsDelete=!0,e.IsUpdate=!0,e.IsInsert=!0,e}get EnMap(){const e=new N("Sys_MapExt","级联数据源");return e.AddMyPK(),e.AddTBStringDoc(i.Doc,null,"内容:",!0,!1,!0,this.Desc1),this._enMap=e,this._enMap}}export{D as TextAreaFullBlank};
