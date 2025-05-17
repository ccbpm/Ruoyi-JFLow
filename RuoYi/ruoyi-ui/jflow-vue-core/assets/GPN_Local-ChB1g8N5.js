var U=Object.defineProperty;var P=(T,c,a)=>c in T?U(T,c,{enumerable:!0,configurable:!0,writable:!0,value:a}):T[c]=a;var n=(T,c,a)=>P(T,typeof c!="symbol"?c+"":c,a);var y=(T,c,a)=>new Promise((d,E)=>{var m=l=>{try{e(a.next(l))}catch(t){E(t)}},h=l=>{try{e(a.throw(l))}catch(t){E(t)}},e=l=>l.done?d(l.value):Promise.resolve(l.value).then(m,h);e((a=a.apply(T,c)).next())});import{a as I}from"./DBAccess-CZ0wdWXU.js";import{SFTable as R}from"./SFTable-BpxUt1jb.js";import{GPN_Enum as _}from"./GPN_Enum-Cptwu4tM.js";import{SysEnumMain as L}from"./SysEnumMain-NEBgQX-D.js";import{P as B,G as i,m as S,W as w,C as V,a2 as C,D as x,B as G}from"./entry/index-B5R3Coa4-1746862693206.js";import{GloComm as D}from"./GloComm-B1xAfTWw.js";import{SFProc as K}from"./SFProc-sEDsGGaw.js";import{b as Q}from"./antd-C8r6Ue4p.js";import"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./SysEnum-DmKE2Ig7.js";import"./FrmTrack-Ct-No0Nq.js";import"./SFParaSln-ekMdP4Zl.js";class oe extends B{constructor(){super("GPN_Local");n(this,"HelpSearch",`
  #### 帮助
  - 查询与字典表不同，他需要参数据才能执行.
  #### 用到场景
  - 文本框自动完成, 级联下拉框、自动填充.
  `);n(this,"WebApi_Url",`
  #### 帮助
   - 请输入路径参数.
   - 仅仅输入主机端口号后面的部分.
   - 比如: /xxxx.do
  `);n(this,"SrcHelp",`
  #### 帮助
   - 请选择数据源，如果没有，请新建数据源.
   - 
  `);n(this,"SFTable",`
  #### 帮助
   - 内置字典表,比如: 省份，片区、城市、税种，税目
   - 内置字典表，是自己可以维护的表.
   - 存储在 Sys_SFTableDtl 表里. 
   - 用户可以通过ccfrom自己定义，自己维护的基础数据.
  `);n(this,"Handler",`
  #### 帮助
   - 优点:格式灵活,展现效果随心所欲.
   - 适用于:效果
   #### lisdxcx
  `);n(this,"SQL",`
  #### 帮助
   - 设置一个SQL语句从数据源中查询出来.
   - 支持ccbpm的表达式. @WebUser.No 当前用户编号， @WebUser.Name 登录名称， @WebUser.DeptNo 登录人所在部门.
   #### DEMO
   - 本部门的人员.
   - SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'
   - 我的下级部门
   - SELECT No,Name FROM Port_Dept WHERE PartentNo='@WebUser.DeptNo'
xxx      
  `);n(this,"SQL_Doc",`
  #### 帮助
   - 设置一个SQL语句从数据源中查询出来.
   - 支持ccbpm的表达式. @WebUser.No 当前用户编号， @WebUser.Name 登录名称， @WebUser.DeptNo 登录人所在部门.
   #### DEMO
   - 本部门的人员.
   - SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'
   - 我的下级部门
   - SELECT No,Name FROM Port_Dept WHERE PartentNo='@WebUser.DeptNo'
xxx      
  `);n(this,"JavaScript",`
    #### 帮助
     - 暂无
     #### lisdxcx
     function Xxx()
     {
        
     }
xxx      
    `);n(this,"WebApi",`
  #### 帮助
   - 调用服务获得数据.
    
  `);n(this,"WebApi_Doc",`
  #### 帮助
  - 调用服务获得数据.
    
  `);n(this,"Docs1",`
  #### 帮助 
  - 暂无
  `);n(this,"Docs2",`
  #### 帮助
  - 暂无
    
  `);n(this,"Docs4",`
  #### 帮助
  - 填写格式: 枚举值,枚举标签; 
  - 例如: ty,团员;dy=党员;qz,群众; 
  - 系统解析为: ty是团员, dy是党员, qz是群众.

  #### 数据存储.
  - string类型的枚举也称为标记枚举,字母存储一个列,标签存储一个列.
  - 在表单里字段是abc,那系统就会自动创建一个影子字段 abcT.
  - abc字段存储的是标记, abcT存储的是标签.
  `);this.PageTitle="新建"}Init(){return y(this,null,function*(){this.AddGroup("A","新建字典","icon-list"),this.TextBox2_NameNo("SFTable","内置字典表",this.SFTable,"SF_","字典ID","字典名称",""),this.SelectItemsByList("SFTable.CodeStruct","数据结构",this.SFTable,!1,this.GetCodeStruct()),this.TextBox2_NameNo("SQL","SQL查询字典表",this.SQL,"SQL_","字典ID","字典名称",""),this.TextArea("SQL.Doc","填写SQL",this.SQL_Doc,"查询SQL","SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'","查询语句"),this.SelectItemsByList("SQL.Doc.CodeStruct","数据结构",this.SFTable,!1,this.GetCodeStruct()),this.AddGroup("B","新建查询","Search"),this.TextBox3_NameNoNote("Search","创建查询",this.HelpSearch,"Search_","编号","名称","备注","我的查询"),this.SelectItemsByList("Search.ResultNum","查询类型",this.SFTable,!1,yield this.ResultNum()),this.AddIcon("icon-layers","Search"),this.AddGroup("C","新建过程","Proc"),this.TextBox2_NameNo("Proc","创建过程",this.HelpSearch,"Proc_","编号","名称","我的过程"),this.AddIcon("icon-options","Proc"),this.AddGroup("D","新建枚举","icon-list"),this.TextBox3_NameNoNote("Enums","新建int类型枚举",_.NewIntEnum,"","枚举ID","枚举名称","请输入内容(比如:男,女)","")})}ResultNum(){return y(this,null,function*(){return JSON.stringify([{No:"0",Name:"多行记录(集合查询)"},{No:"1",Name:"单行记录(实体查询)"}])})}GetCodeStruct(){return JSON.stringify([{No:"0",Name:"编号名称"},{No:"1",Name:"树结构"}])}GenerSorts(){return y(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(a,d,E,m,h){return y(this,null,function*(){if(a=="Search"){const t=new I;if(t.No=m,(yield t.IsExits())==!0)return new i(S.Error,"编号["+m+"]已经存在.")}if(a=="Search.ResultNum"){const t=new I;t.No=this.RequestVal("tb2","Search"),t.Name=this.RequestVal("tb1","Search"),t.Remark=this.RequestVal("tb3","Search"),t.FK_SFDBSrc="local",t.ResultNum=E,t.SetPara("EnName","TS.FrmUI.SFSearchSQL"),yield t.Insert();const s=D.UrlEn("TS.FrmUI.SFSearchSQL",t.No);return new i(S.GoToUrl,s)}if(a=="Proc"){const t=new K;if(t.No=m,(yield t.IsExits())==!0)return new i(S.Error,"编号["+m+"]已经存在.");t.Name=E,t.FK_SFDBSrc="local",t.Remark=h,t.SetPara("EnName","TS.FrmUI.SFProcSQL"),yield t.Insert();const s=D.UrlEn("TS.FrmUI.SFProcSQL",t.No);return new i(S.GoToUrl,s)}if(a=="Enums"||a=="NewStrEnum"){const t=E,s=m;let o=h.trim();const r=new L;if(w.CCBPMRunModel==V.Single?r.No=s:r.No=w.OrgNo+"_"+s,r.EnumKey=s,(yield r.IsExits())===!0){Q.warning("枚举值已经存在"+s);return}if(r.Name=t,r.OrgNo=w.OrgNo,o=o.replaceAll("，",","),o=o.replaceAll("＠","@"),o=o.replaceAll("＝","="),a==="Enums"){if(!o.includes(",")&&!o.includes("@"))return new i(S.Error,"多个枚举值使用 , 或 @ 符号分开.");for(let u=0;u<20;u++)r.SetValByKey("Idx"+u,u);o.indexOf("@")==-1?o.split(",").forEach((F,N)=>{r.SetValByKey("Idx"+N,N),r.SetValByKey("Val"+N,F)}):C(o).forEach((F,N)=>{const[f,p]=F.split("=");r.SetValByKey("Idx"+N,f),r.SetValByKey("Val"+N,p)}),r.EnumType=0,r.CfgVal=o,r.SetPara("EnName","TS.FrmUI.SysEnumMainInt");try{yield r.Insert()}catch(u){yield r.Insert()}yield r.SaveDtls();const b=D.UrlEn("TS.FrmUI.SysEnumMainInt",r.No);return new i(S.GoToUrl,b)}if(a==="NewStrEnum"){if(o.indexOf(",")==-1)return new i(S.Error,"多个枚举值使用逗号分开.");if(o.indexOf("@")==-1){const u=o.split(",");let F="",N=-1;u.forEach(f=>{if(N++,f.indexOf("=")==-1)return new i(S.Error,"枚举键和枚举值使用等号连接.");const p=f.split("=");if(x.IsNullOrEmpty(p[0]))return new i(S.Error,"请填写枚举键.");if(x.IsNullOrEmpty(p[1]))return new i(S.Error,"请填写枚举值.");F+="@"+f,r.SetValByKey("Idx"+N,p[0]),r.SetValByKey("Val"+N,p[1])}),o=F}r.EnumType=1,r.EnumKey=s,r.Name=t,r.CfgVal=o,r.SetPara("EnName","TS.FrmUI.SysEnumMainString");try{yield r.Insert()}catch(u){yield r.Insert()}yield r.SaveDtls();const b=D.UrlEn("TS.FrmUI.SysEnumMainString",r.No);return new i(S.GoToUrl,b)}}const e=new R;if(e.Name=E,e.No=m,e.TableDesc=h,e.RDT=x.CurrentDateTime,a.includes(".")==!1&&(yield e.IsExits()))throw new Error("编号:"+e.No+"已存在.");if(a==="SFTable.CodeStruct"){e.Name=this.RequestVal("tb1","SFTable"),e.No=this.RequestVal("tb2","SFTable"),e.FK_SFDBSrc="local",e.DBSrcType="SysDict",e.DBType=0,e.FK_Val=e.No,e.CodeStruct=this.RequestVal("tb1","SFTable.CodeStruct"),e.CodeStruct==0?e.SetPara("EnName","TS.FrmUI.SFTableNoName"):e.SetPara("EnName","TS.FrmUI.SFTableTree"),yield e.Insert();const t=new G("BP.Sys.SFTable",e.No);yield t.Retrieve(),yield t.DoMethodReturnString("GenerDataOfJson");let s="";return s="/@/WF/Comm/En.vue?EnName="+e.GetParaString("EnName","")+"&PKVal="+e.No,new i(S.GoToUrl,s)}if(a=="SQL.Doc.CodeStruct"){e.Name=this.RequestVal("tb1","SQL"),e.No=this.RequestVal("tb2","SQL"),e.DBSrcType="SQL",e.CodeStruct=this.RequestVal("tb1","SQL.Doc.CodeStruct");let t="TS.FrmUI.SFTableSQLNoName";e.CodeStruct==1&&(t="TS.FrmUI.SFTableSQLTree"),e.SetPara("EnName",t),e.SelectStatement=this.RequestVal("tb1","SQL.Doc"),e.FK_SFDBSrc="local",e.FK_Val=e.No,e.SrcType=3,yield e.Insert();const s=D.UrlEn(t,e.No);return new i(S.GoToUrl,s)}a=="Handler.Doc"&&(e.Name=this.RequestVal("tb1","Handler"),e.No=this.RequestVal("tb2","Handler"),e.DBSrcType="Handler"),a=="JavaScript.Doc"&&(e.Name=this.RequestVal("tb1","JavaScript"),e.No=this.RequestVal("tb2","JavaScript"),e.DBSrcType="JavaScript");let l="";if(a==="Handler.Doc"&&(l="TS.FrmUI.SFTableHandler"),a==="JavaScript.Doc"&&(l="TS.FrmUI.SFTableJS"),e.SelectStatement=m,e.FK_SFDBSrc=E,e.FK_Val=e.No,l!==""){e.SetPara("EnName",l),e.SelectStatement=m,e.FK_SFDBSrc=E,e.FK_Val=e.No,yield e.Insert();const t="/@/WF/Comm/En.vue?EnName="+l+"&PKVal="+e.No;return new i(S.GoToUrl,t)}})}}export{oe as GPN_Local};
