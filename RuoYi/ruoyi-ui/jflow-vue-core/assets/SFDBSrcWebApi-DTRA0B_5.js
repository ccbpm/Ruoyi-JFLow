var D=Object.defineProperty;var A=(r,e,t)=>e in r?D(r,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):r[e]=t;var S=(r,e,t)=>A(r,typeof e!="symbol"?e+"":e,t);var c=(r,e,t)=>new Promise((p,a)=>{var d=o=>{try{i(t.next(o))}catch(s){a(s)}},u=o=>{try{i(t.throw(o))}catch(s){a(s)}},i=o=>o.done?p(o.value):Promise.resolve(o.value).then(d,u);i((t=t.apply(r,e)).next())});import{E as m,U as I,f as l,B as W}from"./entry/index-B5R3Coa4-1746862693206.js";import{SFDBSrcAttr as n}from"./SFDBSrc-DbkqYXE6.js";import{SFApiParas as B}from"./SFApiPara-DAVcqP8_.js";import{GPE_WebApiResultModel as E}from"./GPE_WebApiResultModel-U1w1Zpoh.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./PageBaseGroupEdit-BXdNWKIo.js";import"./Help-D0bDMZWg.js";class x extends m{constructor(t){super("TS.Sys.SFDBSrcWebApi");S(this,"WebAPI",`
  #### 字典
  - 应用的场景-外部数据源,表单下拉字段级联关系,接收人规则
  #### 查询
  - 应用的场景-pop填充,装载填充,从表填充,
  #### 过程
  - 应用的场景-流程事件,节点事件
  #### 通用配置
  - 1.请求方式分为Get、Post方法
  - 2.请求方式为Get方式时，路径的配置方式为/xxxx?UserID=@WebUser.No&Ticket=@Token&workID=@WorkID&JE=@JinE
       请求方式为Post方式时，路径的配置方式为/xxx,post内容为JSON格式,例如：
       {
          "UserID":"@WebUser.No",
          "Ticket":"@Token",
          "WorkID":"@WorkID",
          "JE":@JinE
        }
  - 3.参数别名:当使用的位置的替换的参数值与设置的参数不一致时，需要设置参数别名,例如:@JinE=JE,ZongJinE,HuaFeiJinE@WorkID=OID,FID等
  `);t&&this.setPKVal(t)}get HisUAC(){const t=new I;return t.IsDelete=!0,t.IsUpdate=!0,t.IsInsert=!0,t}get EnMap(){const t=new l("Sys_SFDBSrc","WebApi数据源");return t.AddTBStringPK(n.No,null,"编号",!0,!0,1,20,20),t.AddTBString(n.Name,null,"名称",!0,!1,0,30,20),t.AddTBString(n.DBSrcType,"WebApi","类型",!0,!0,0,30,20),t.AddTBString(n.ConnString,null,"连接",!0,!1,0,300,20,!0),t.AddGroupMethod("基本设置"),t.AddRM_GPE(new E,"icon-drop"),t.AddRM_DtlSearch("内置参数",new B,"DBSrcNo","","","","icon-drop"),t.AddRM_HelpDocs("帮助","/src/WF/Comm/HelpDocs.vue?key=WebAPI",this.WebAPI,"icon-support"),this._enMap=t,this._enMap}TestConn(){return c(this,null,function*(){const t=new W("BP.Sys.SFDBSrc",this.No);return yield t.Init(),t.No=this.No,yield t.Retrieve(),yield t.DoMethodReturnString("DoConn")})}}export{x as SFDBSrcWebApi};
