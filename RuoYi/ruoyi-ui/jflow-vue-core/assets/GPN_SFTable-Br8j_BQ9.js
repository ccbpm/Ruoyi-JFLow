var p=Object.defineProperty;var F=(s,S,t)=>S in s?p(s,S,{enumerable:!0,configurable:!0,writable:!0,value:t}):s[S]=t;var a=(s,S,t)=>F(s,typeof S!="symbol"?S+"":S,t);var D=(s,S,t)=>new Promise((N,i)=>{var l=o=>{try{e(t.next(o))}catch(r){i(r)}},b=o=>{try{e(t.throw(o))}catch(r){i(r)}},e=o=>o.done?N(o.value):Promise.resolve(o.value).then(l,b);e((t=t.apply(s,S)).next())});import{SFTable as u}from"./SFTable-BpxUt1jb.js";import{P as d,G as n,m,D as h,B as E}from"./entry/index-B5R3Coa4-1746862693206.js";import{GloComm as T}from"./GloComm-B1xAfTWw.js";import"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";class I extends d{constructor(){super("GPN_SFTable");a(this,"WebApi_Url",`
  #### 帮助
   - 请输入路径参数.
   - 仅仅输入主机端口号后面的部分.
   - 比如: /xxxx.do
  `);a(this,"SrcHelp",`
  #### 帮助
   - 请选择数据源，如果没有，请新建数据源.
   - 
  `);a(this,"SFTable",`
  #### 帮助
   - 内置字典表,比如: 省份，片区、城市、税种，税目
   - 内置字典表，是自己可以维护的表.
   - 存储在 Sys_SFTableDtl 表里. 
   - 用户可以通过ccfrom自己定义，自己维护的基础数据.
   
  `);a(this,"Handler",`
  #### 帮助
   - 优点:格式灵活,展现效果随心所欲.
   - 适用于:效果
   #### lisdxcx
   xxxxx
xxx      
  `);a(this,"SQL",`
  #### 帮助
   - 设置一个SQL语句从数据源中查询出来.
   - 支持ccbpm的表达式. @WebUser.No 当前用户编号， @WebUser.Name 登录名称， @WebUser.DeptNo 登录人所在部门.
   #### DEMO
   - 本部门的人员.
   - SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'
   - 我的下级部门
   - SELECT No,Name FROM Port_Dept WHERE PartentNo='@WebUser.DeptNo'
xxx      
  `);a(this,"SQL_Doc",`
  #### 帮助
   - 设置一个SQL语句从数据源中查询出来.
   - 支持ccbpm的表达式. @WebUser.No 当前用户编号， @WebUser.Name 登录名称， @WebUser.DeptNo 登录人所在部门.
   #### DEMO
   - 本部门的人员.
   - SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'
   - 我的下级部门
   - SELECT No,Name FROM Port_Dept WHERE PartentNo='@WebUser.DeptNo'
xxx      
  `);a(this,"JavaScript",`
    #### 帮助
     - 暂无
     #### lisdxcx
     function Xxx()
     {
        
     }
xxx      
    `);a(this,"WebApi",`
  #### 帮助
   - 调用服务获得数据.
    
  `);a(this,"WebApi_Doc",`
  #### 帮助
  - 调用服务获得数据.
    
  `);a(this,"Docs1",`
  #### 帮助 
  - 暂无
  `);a(this,"Docs2",`
  #### 帮助
  - 暂无
    
  `);a(this,"Docs4",`
  #### 帮助
  - 填写格式: 枚举值,枚举标签; 
  - 例如: ty,团员;dy=党员;qz,群众; 
  - 系统解析为: ty是团员, dy是党员, qz是群众.

  #### 数据存储.
  - string类型的枚举也称为标记枚举,字母存储一个列,标签存储一个列.
  - 在表单里字段是abc,那系统就会自动创建一个影子字段 abcT.
  - abc字段存储的是标记, abcT存储的是标签.
  `);this.ForEntityClassID="TS.FrmUI.SFTable",this.PageTitle="新建字典"}Init(){this.AddGroup("A","新建字典"),this.TextBox2_NameNo("SFTableDict","内置字典表",this.SFTable,"SF_","字典ID","字典名称",""),this.SelectItemsByList("SFTableDict.CodeStruct","数据结构",this.SFTable,!1,this.GetCodeStruct()),this.TextBox2_NameNo("SQL","SQL查询字典表",this.SQL,"SQL_","字典ID","字典名称",""),this.TextSQL("SQL.Doc","填写SQL",this.SQL_Doc,"查询SQL","SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'","查询语句"),this.SelectItemsByList("SQL.Doc.CodeStruct","数据结构",this.SFTable,!1,this.GetCodeStruct()),this.TextBox2_NameNo("WebApi","WebApi接口字典表",this.WebApi,"","字典ID","字典名称",""),this.TextSQL("WebApi.Doc","服务链接",this.WebApi_Url,"路径与参数","/xxx.do","输入主机的后部分"),this.SelectItemsByList("WebApi.Doc.CodeStruct","数据结构",this.SFTable,!1,this.GetCodeStruct()),this.TextBox2_NameNo("Handler","微服务Handler字典表",this.Handler,"Handler_","字典ID","字典名称",""),this.TextSQL("Handler.Doc","填写内容",this.SQL_Doc,"查询SQL","SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'","查询语句"),this.AddGroup("B","相关功能"),this.AddFunction("AdminDBSrc_ToUrl","数据源维护",this.GotoUrl)}GetCodeStruct(){return JSON.stringify([{No:"0",Name:"编号名称"},{No:"1",Name:"树结构"}])}GotoUrl(){const t="/@/WF/Comm/Search.vue?EnName=TS.Sys.SFDBSrc";return new n(m.GoToUrl,t)}GenerSorts(){return D(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,N,i,l,b){return D(this,null,function*(){const e=new u;if(e.Name=i,e.No=l,e.TableDesc=b,e.RDT=h.CurrentDateTime,t.includes(".")==!1&&(yield e.IsExits()))throw new Error("编号:"+e.No+"已存在.");if(t==="SFTableDict.CodeStruct"){e.Name=this.RequestVal("tb1","SFTableDict"),e.No=this.RequestVal("tb2","SFTableDict"),e.FK_SFDBSrc="local",e.DBSrcType="SysDict",e.DBType=0,e.FK_Val=e.No,e.NoGenerModel=1,e.CodeStruct=this.RequestVal("tb1","SFTableDict.CodeStruct"),e.CodeStruct==0?e.SetPara("EnName","TS.FrmUI.SFTableNoName"):e.SetPara("EnName","TS.FrmUI.SFTableTree"),yield e.Insert();const r=new E("BP.Sys.SFTable",e.No);yield r.Retrieve(),yield r.DoMethodReturnString("GenerDataOfJson");let c="";return c="/@/WF/Comm/En.vue?EnName="+e.GetParaString("EnName","")+"&PKVal="+e.No,new n(m.GoToUrl,c)}if(t=="SQL.Doc.CodeStruct"){e.Name=this.RequestVal("tb1","SQL"),e.No=this.RequestVal("tb2","SQL"),e.DBSrcType="SQL",e.CodeStruct=this.RequestVal("tb1","SQL.Doc.CodeStruct");let r="TS.FrmUI.SFTableSQLNoName";e.CodeStruct==1&&(r="TS.FrmUI.SFTableSQLTree"),e.SetPara("EnName",r),e.SelectStatement=this.RequestVal("tb2","SQL.Doc"),e.FK_SFDBSrc=this.RequestVal("tb1","SQL.Doc"),e.FK_Val=e.No,yield e.Insert();const c=T.UrlEn(r,e.No);return new n(m.GoToUrl,c)}if(t=="WebApi.Doc.CodeStruct"){e.Name=this.RequestVal("tb1","WebApi"),e.No=this.RequestVal("tb2","WebApi"),e.DBSrcType="WebApi",e.CodeStruct=this.RequestVal("tb1","WebApi.Doc.CodeStruct");let r="TS.FrmUI.SFTableWebApiNoName";e.CodeStruct==1&&(r="TS.FrmUI.SFTableWebApiTree"),e.SetPara("EnName",r),e.SelectStatement=this.RequestVal("tb2","WebApi.Doc"),e.FK_SFDBSrc=this.RequestVal("tb1","WebApi.Doc"),e.FK_Val=e.No,yield e.Insert();const c=T.UrlEn(r,e.No);return new n(m.GoToUrl,c)}t=="Handler.Doc"&&(e.Name=this.RequestVal("tb1","Handler"),e.No=this.RequestVal("tb2","Handler"),e.DBSrcType="Handler"),t=="JavaScript.Doc"&&(e.Name=this.RequestVal("tb1","JavaScript"),e.No=this.RequestVal("tb2","JavaScript"),e.DBSrcType="JavaScript");let o="";if(t==="Handler.Doc"&&(o="TS.FrmUI.SFTableHandler"),t==="JavaScript.Doc"&&(o="TS.FrmUI.SFTableJS"),e.SelectStatement=l,e.FK_SFDBSrc=i,e.FK_Val=e.No,o!==""){e.SetPara("EnName",o),e.SelectStatement=l,e.FK_SFDBSrc=i,e.FK_Val=e.No,yield e.Insert();const r="/@/WF/Comm/En.vue?EnName="+o+"&PKVal="+e.No;return new n(m.GoToUrl,r)}})}}export{I as GPN_SFTable};
