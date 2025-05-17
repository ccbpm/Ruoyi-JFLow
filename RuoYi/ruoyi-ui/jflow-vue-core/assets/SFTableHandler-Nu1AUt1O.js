import{E as n,U as s,f as a,j as o}from"./entry/index-B5R3Coa4-1746862693206.js";import{SFTableAttr as t}from"./SFTable-BpxUt1jb.js";import{SFDBSrc as l}from"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class d extends n{constructor(e){super("TS.FrmUI.SFTableHandler"),e&&this.setPKVal(e)}get HisUAC(){const e=new s;return e.IsDelete=!0,e.IsUpdate=!0,e}get EnMap(){const e=new a("Sys_SFTable","Handler字典表");return e.AddTBStringPK(t.No,null,"编号",!0,!0,1,200,20),e.AddTBString(t.Name,null,"名称",!0,!1,0,200,20),e.AddDDLEntities(t.FK_SFDBSrc,"local","数据源",new l,!0),e.AddDDLSysEnum(t.CodeStruct,0,"字典表类型",!0,!0,t.CodeStruct,"@0=编号名称类型@1=树结构类型"),e.AddTBString(t.SelectStatement,null,"地址",!0,!1,0,1e3,600,!0,`
        #### 帮助
         -  WebAPI的输入格式：@WebApiHost/DataUser/GetEmps?id=51184
         - 此处只支持用户定义好的内置参数，比如：id=51184
        #### 其它
        - 访问ccfrom提供的内置的handler,开发人员进行重写返回数据.
        - 表单在运行的时候,通过访问这个服务，携带设置的参数，解析返回的数据，展现在表单的控件上.
        `),e.AddTBString(t.RootVal,null,"根节点值",!0,!1,0,1e3,600,!0,` 
        #### 帮助
        - 对树形结构的字段有效.
        - 根目录的parentNo数据.
        `),this._enMap=e,this._enMap}DoEdit(){}}class T extends o{get GetNewEntity(){return new d}constructor(){super()}}export{d as SFTableHandler,T as SFTableHandlers};
