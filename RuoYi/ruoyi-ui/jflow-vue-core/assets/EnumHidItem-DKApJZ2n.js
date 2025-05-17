var n=Object.defineProperty;var u=(r,e,t)=>e in r?n(r,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):r[e]=t;var a=(r,e,t)=>u(r,typeof e!="symbol"?e+"":e,t);import{l as i,U as m,f as p,F as o}from"./entry/index-B5R3Coa4-1746862693206.js";import{a as s}from"./MapExt-DVovzpWn.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";class D extends i{constructor(t){super("TS.MapExt.EnumHidItem");a(this,"Desc1",`
  #### 说明
   - 设置格式
   - @0=1,2,3
   - @1=4,5
   - 标识当选项是0的时候,枚举值隐藏1,2,3 当选项=1的时候，隐藏4,5.
   #### 枚举值
   - 请打开枚举库查看, 对应的IntKey.
   - 也可以通过,select * from sys_enum WHERE EnumKey='xxxxx' 查看 枚举的键值.
  `);this.RefEnName="TS.Sys.MapExt",t&&(this.MyPK=t)}get HisUAC(){const t=new m;return t.IsDelete=!0,t.IsUpdate=!0,t.IsInsert=!0,t}get EnMap(){const t=new p("Sys_MapExt","点击事件隐藏选项");return t.AddMyPK(),t.AddTBString(s.FK_MapData,null,"表单ID",!0,!0,100),t.AddDDLSQL(s.Tag,null,"联动的控件",o.SQLOfEnumHidItem,!0,null,!1,100),t.AddTBStringDoc(s.Tag4,null,"配置项",!0,!1,!0,this.Desc1),this._enMap=t,this._enMap}}export{D as EnumHidItem};
