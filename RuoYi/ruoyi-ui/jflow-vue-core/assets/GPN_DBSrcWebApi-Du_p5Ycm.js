var F=Object.defineProperty;var x=(s,a,t)=>a in s?F(s,a,{enumerable:!0,configurable:!0,writable:!0,value:t}):s[a]=t;var o=(s,a,t)=>x(s,typeof a!="symbol"?a+"":a,t);var N=(s,a,t)=>new Promise((p,c)=>{var i=r=>{try{e(t.next(r))}catch(n){c(n)}},S=r=>{try{e(t.throw(r))}catch(n){c(n)}},e=r=>r.done?p(r.value):Promise.resolve(r.value).then(i,S);e((t=t.apply(s,a)).next())});import{SFProc as h}from"./SFProc-sEDsGGaw.js";import{a as D}from"./DBAccess-CZ0wdWXU.js";import{SFTable as T}from"./SFTable-BpxUt1jb.js";import{P as u,G as m,m as l,D as E}from"./entry/index-B5R3Coa4-1746862693206.js";import{GloComm as b}from"./GloComm-B1xAfTWw.js";import"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./SFPara-B3cj-DF1.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFParaSln-ekMdP4Zl.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FrmTrack-Ct-No0Nq.js";class O extends u{constructor(){super("GPN_DBSrcWebApi");o(this,"HelpSearch",`
  #### 帮助
  - 查询与字典表不同，他需要参数据才能执行.
  #### 用到场景
  - 文本框自动完成, 级联下拉框、自动填充.
  `);o(this,"WebApi_Url",`
  #### 帮助
   - 请输入路径参数.
   - 仅仅输入主机端口号后面的部分.
   - 比如: /xxxx.do
  `);o(this,"SrcHelp",`
  #### 帮助
   - 请选择数据源，如果没有，请新建数据源.
   - 
  `);o(this,"SFTable",`
  #### 帮助
   - 内置字典表,比如: 省份，片区、城市、税种，税目
   - 内置字典表，是自己可以维护的表.
   - 存储在 Sys_SFTableDtl 表里. 
   - 用户可以通过ccfrom自己定义，自己维护的基础数据.
  `);o(this,"Handler",`
  #### 帮助
   - 优点:格式灵活,展现效果随心所欲.
   - 适用于:效果
   #### lisdxcx
  `);o(this,"SQL",`
  #### 帮助
   - 设置一个SQL语句从数据源中查询出来.
   - 支持ccbpm的表达式. @WebUser.No 当前用户编号， @WebUser.Name 登录名称， @WebUser.DeptNo 登录人所在部门.
   #### DEMO
   - 本部门的人员.
   - SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'
   - 我的下级部门
   - SELECT No,Name FROM Port_Dept WHERE PartentNo='@WebUser.DeptNo'
xxx      
  `);o(this,"SQL_Doc",`
  #### 帮助
   - 设置一个SQL语句从数据源中查询出来.
   - 支持ccbpm的表达式. @WebUser.No 当前用户编号， @WebUser.Name 登录名称， @WebUser.DeptNo 登录人所在部门.
   #### DEMO
   - 本部门的人员.
   - SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'
   - 我的下级部门
   - SELECT No,Name FROM Port_Dept WHERE PartentNo='@WebUser.DeptNo'
xxx      
  `);o(this,"JavaScript",`
    #### 帮助
     - 暂无
     #### lisdxcx
     function Xxx()
     {
        
     }
xxx      
    `);o(this,"WebApi",`
  #### 帮助
   - 调用服务获得数据.
    
  `);o(this,"WebApi_Doc",`
  #### 帮助
  - 调用服务获得数据.
    
  `);o(this,"Docs1",`
  #### 帮助 
  - 暂无
  `);o(this,"Docs2",`
  #### 帮助
  - 暂无
    
  `);o(this,"Docs4",`
  #### 帮助
  - 填写格式: 枚举值,枚举标签; 
  - 例如: ty,团员;dy=党员;qz,群众; 
  - 系统解析为: ty是团员, dy是党员, qz是群众.

  #### 数据存储.
  - string类型的枚举也称为标记枚举,字母存储一个列,标签存储一个列.
  - 在表单里字段是abc,那系统就会自动创建一个影子字段 abcT.
  - abc字段存储的是标记, abcT存储的是标签.
  `);this.PageTitle="新建"}Init(){this.AddGroup("A","新建字典","icon-list"),this.TextBox2_NameNo("SFTable","WebApi接口字典表",this.WebApi,"","字典ID","字典名称",""),this.TextArea("SFTable.Doc","服务链接",this.WebApi_Url,"路径与参数","/xxx.do","输入主机的后部分"),this.SelectItemsByList("SFTable.Doc.CodeStruct","数据结构",this.HelpTodo,!1,this.GetCodeStruct()),this.AddGroup("B","新建查询","icon-layers"),this.TextBox3_NameNoNote("Search","创建查询",this.HelpSearch,"APISearch","编号","名称","备注","我的查询"),this.AddIcon("icon-layers","Search"),this.AddGroup("C","新建过程","icon-options"),this.TextBox3_NameNoNote("Proc","创建过程",this.HelpSearch,"","编号","名称","备注","我的过程"),this.AddIcon("icon-options","Proc")}GetCodeStruct(){return JSON.stringify([{No:"0",Name:"编号名称"},{No:"1",Name:"树结构"}])}GenerSorts(){return N(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,p,c,i,S){return N(this,null,function*(){if(t=="Search"){const e=new D;if(e.No=i,(yield e.IsExits())==!0)return new m(l.Error,"编号["+i+"]已经存在.");e.Name=c,e.FK_SFDBSrc=this.RefPKVal,e.Remark=S,e.SetPara("EnName","TS.FrmUI.SFSearchWebApi"),yield e.Insert();const r=b.UrlEn("TS.FrmUI.SFSearchWebApi",e.No);return new m(l.GoToUrl,r)}if(t=="Proc"){const e=new h;if(e.No=i,(yield e.IsExits())==!0)return new m(l.Error,"编号["+i+"]已经存在.");e.Name=c,e.FK_SFDBSrc=this.RefPKVal,e.Remark=S,e.SetPara("EnName","TS.FrmUI.SFProcWebApi"),yield e.Insert();const r=b.UrlEn("TS.FrmUI.SFProcWebApi",e.No);return new m(l.GoToUrl,r)}if(t=="SFTable"){const e=new T;if(e.Name=c,e.No=i,e.TableDesc=S,e.RDT=E.CurrentDateTime,t.includes(".")==!1&&(yield e.IsExits()))throw new Error("编号:"+e.No+"已存在.")}if(t=="SFTable.Doc.CodeStruct"){const e=new T;e.Name=this.RequestVal("tb1","SFTable"),e.No=this.RequestVal("tb2","SFTable"),e.DBSrcType="WebApi",e.CodeStruct=this.RequestVal("tb1","SFTable.Doc.CodeStruct");let r="TS.FrmUI.SFTableWebApiNoName";e.CodeStruct==1&&(r="TS.FrmUI.SFTableWebApiTree"),e.SetPara("EnName",r),e.SelectStatement=this.RequestVal("tb1","SFTable.Doc"),e.FK_SFDBSrc=this.RefPKVal,e.FK_Val=e.No,yield e.Insert();const n=b.UrlEn(r,e.No);return new m(l.GoToUrl,n)}})}}export{O as GPN_DBSrcWebApi};
