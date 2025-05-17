var n=(l,e,r)=>new Promise((i,a)=>{var u=t=>{try{s(r.next(t))}catch(o){a(o)}},m=t=>{try{s(r.throw(t))}catch(o){a(o)}},s=t=>t.done?i(t.value):Promise.resolve(t.value).then(u,m);s((r=r.apply(l,e)).next())});import{l as c,U as f,f as S}from"./entry/index-B5R3Coa4-1746862693206.js";import{a as p}from"./MapExt-DVovzpWn.js";import{SFDBSrc as d}from"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";class x extends c{constructor(e){super("TS.MapExt.FullBodySelf"),e&&(this.MyPK=e)}get HisUAC(){const e=new f;return e.IsDelete=!1,e.IsUpdate=!0,e.IsInsert=!1,e}get EnMap(){const e=new S("Sys_MapExt","落值填充-主表");return e.AddMyPK(),e.AddDDLEntities(p.FK_DBSrc,"local","数据源",new d,!0,null,!1),e.AddTBStringDoc(p.Tag6,null,"填充表达式",!0,!1,!0,this.DescSrc),e.SetHelperAlert("Tag6",`#### 帮助
    - 查询的数据返回一行多列,列名要与主表字段名称保持一致,就会填充.
    - 比如： SELECT Email,Tel FROM Demo_Student WHERE No=@Key
    - @Key 是系统默认选择的字段.   返回的列与目前表单的字段值对应系统就会自动填充.
    - 
    `),this._enMap=e,this._enMap}beforeInsert(){return n(this,null,function*(){return Promise.resolve(!0)})}}export{x as FullBodySelf};
