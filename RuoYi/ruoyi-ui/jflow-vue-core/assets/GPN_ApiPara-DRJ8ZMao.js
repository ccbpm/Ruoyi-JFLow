var E=Object.defineProperty;var P=(s,a,e)=>a in s?E(s,a,{enumerable:!0,configurable:!0,writable:!0,value:e}):s[a]=e;var r=(s,a,e)=>P(s,typeof a!="symbol"?a+"":a,e);var N=(s,a,e)=>new Promise((l,p)=>{var o=i=>{try{t(e.next(i))}catch(S){p(S)}},x=i=>{try{t(e.throw(i))}catch(S){p(S)}},t=i=>i.done?l(i.value):Promise.resolve(i.value).then(o,x);t((e=e.apply(s,a)).next())});import{SFApiPara as m}from"./SFApiPara-DAVcqP8_.js";import{P as u,H as y,G as n,m as c}from"./entry/index-B5R3Coa4-1746862693206.js";import{GloComm as D}from"./GloComm-B1xAfTWw.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";class G extends u{constructor(){super("GPN_ApiPara");r(this,"HelpSearch",`
  #### 帮助
  - 查询与字典表不同，他需要参数据才能执行.
  #### 用到场景
  - 文本框自动完成, 级联下拉框、自动填充.
  `);r(this,"WebApi_Url",`
  #### 帮助
   - 请输入路径参数.
   - 仅仅输入主机端口号后面的部分.
   - 比如: /xxxx.do
  `);r(this,"SrcHelp",`
  #### 帮助
   - 请选择数据源，如果没有，请新建数据源.
   - 
  `);r(this,"SFTable",`
  #### 帮助
   - 内置字典表,比如: 省份，片区、城市、税种，税目
   - 内置字典表，是自己可以维护的表.
   - 存储在 Sys_SFTableDtl 表里. 
   - 用户可以通过ccfrom自己定义，自己维护的基础数据.
  `);r(this,"Handler",`
  #### 帮助
   - 优点:格式灵活,展现效果随心所欲.
   - 适用于:效果
   #### lisdxcx
  `);r(this,"SQL",`
  #### 帮助
   - 设置一个SQL语句从数据源中查询出来.
   - 支持ccbpm的表达式. @WebUser.No 当前用户编号， @WebUser.Name 登录名称， @WebUser.DeptNo 登录人所在部门.
   #### DEMO
   - 本部门的人员.
   - SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'
   - 我的下级部门
   - SELECT No,Name FROM Port_Dept WHERE PartentNo='@WebUser.DeptNo'
xxx      
  `);r(this,"SQL_Doc",`
  #### 帮助
   - 设置一个SQL语句从数据源中查询出来.
   - 支持ccbpm的表达式. @WebUser.No 当前用户编号， @WebUser.Name 登录名称， @WebUser.DeptNo 登录人所在部门.
   #### DEMO
   - 本部门的人员.
   - SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'
   - 我的下级部门
   - SELECT No,Name FROM Port_Dept WHERE PartentNo='@WebUser.DeptNo'
xxx      
  `);r(this,"JavaScript",`
    #### 帮助
     - 暂无
     #### lisdxcx
     function Xxx()
     {
        
     }
xxx      
    `);r(this,"WebApi",`
  #### 帮助
   - 调用服务获得数据.
    
  `);r(this,"WebApi_Doc",`
  #### 帮助
  - 调用服务获得数据.
    
  `);r(this,"Docs1",`
  #### 帮助 
  - 暂无
  `);r(this,"Docs2",`
  #### 帮助
  - 暂无
    
  `);r(this,"Docs4",`
  #### 帮助
  - 填写格式: 枚举值,枚举标签; 
  - 例如: ty,团员;dy=党员;qz,群众; 
  - 系统解析为: ty是团员, dy是党员, qz是群众.

  #### 数据存储.
  - string类型的枚举也称为标记枚举,字母存储一个列,标签存储一个列.
  - 在表单里字段是abc,那系统就会自动创建一个影子字段 abcT.
  - abc字段存储的是标记, abcT存储的是标签.
  `);this.ForEntityClassID="TS.FrmUI.SFApiPara",this.PageTitle="新建"}Init(){return N(this,null,function*(){this.AddGroup("A","新建内置参数","icon-list"),this.TextBox2_NameNo("LetVar","变量参数",this.HelpTodo,"","参数ID","参数名称",""),this.SelectItemsByList("LetVar.CodeStruct","存储位置",this.HelpTodo,!1,yield this.GetCodeStruct()),this.SelectItemsByList("LetVar.CodeStruct.BU","业务单元-BuessUnit",this.HelpTodo,!1,yield this.GenerBuessUnit()),this.TextBox2_NameNo("ConstVar","常量参数",this.HelpTodo,"","参数ID","参数名称",""),this.TextBox2_NameNo("SQLExp","SQL表达式",this.HelpTodo,"","参数ID","参数名称","")})}GetCodeStruct(){return N(this,null,function*(){return JSON.stringify([{No:"0",Name:"Cookies(比如:token 获取后可以反复使用)"},{No:"1",Name:"不存储:(每次获取的时候重新计算)"}])})}GenerBuessUnit(){return N(this,null,function*(){const l=yield new y("BP.WF.HttpHandler.WF_Admin_AttrNode").DoMethodReturnJson("ActionDtl_Init");return JSON.stringify(l)})}GenerSorts(){return N(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,l,p,o,x){return N(this,null,function*(){if(e=="ConstVar"){const t=new m;return t.DBSrcNo=this.RefPKVal,t.AttrKey=o,t.MyPK=t.DBSrcNo+"_"+o,(yield t.IsExits())==!0?new n(c.Error,"编号["+o+"]已经存在."):(t.AttrName=p,t.DataType=1,yield t.Insert(),new n(c.GoToUrl,D.UrlEn(t.classID,t.MyPK)))}if(e=="SQLExp"){const t=new m;return t.DBSrcNo=this.RefPKVal,t.AttrKey=o,t.MyPK=t.DBSrcNo+"_"+o,(yield t.IsExits())==!0?new n(c.Error,"编号["+o+"]已经存在."):(t.AttrName=p,t.DataType=1,t.ApiParaModel=2,yield t.Insert(),new n(c.GoToUrl,D.UrlEn(t.classID,t.MyPK)))}if(e=="LetVar"){const t=new m;if(t.DBSrcNo=this.RefPKVal,t.AttrKey=o,t.MyPK=t.DBSrcNo+"_"+o,(yield t.IsExits())==!0)return new n(c.Error,"编号["+o+"]已经存在.")}if(e=="LetVar.CodeStruct.BU"){const t=new m;return t.DBSrcNo=this.RefPKVal,t.AttrKey=this.RequestVal("tb1","LetVar"),t.MyPK=t.DBSrcNo+"_"+t.AttrKey,(yield t.IsExits())==!0?new n(c.Error,"编号["+t.MyPK+"]已经存在."):(t.AttrName=o,t.ApiParaModel=1,t.DataType=1,t.ApiParaStore=this.RequestVal("tb1","LetVar.CodeStruct"),t.ExpDoc=p,yield t.Insert(),new n(c.GoToUrl,D.UrlEn(t.classID,t.MyPK)))}})}}export{G as GPN_ApiPara};
