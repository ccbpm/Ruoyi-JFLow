var F=Object.defineProperty;var g=(a,r,t)=>r in a?F(a,r,{enumerable:!0,configurable:!0,writable:!0,value:t}):a[r]=t;var P=(a,r,t)=>g(a,typeof r!="symbol"?r+"":r,t);var o=(a,r,t)=>new Promise((m,d)=>{var _=e=>{try{n(t.next(e))}catch(i){d(i)}},c=e=>{try{n(t.throw(e))}catch(i){d(i)}},n=e=>e.done?m(e.value):Promise.resolve(e.value).then(_,c);n((t=t.apply(a,r)).next())});import{P as T,H as u,G as s,m as l,g as w}from"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class f extends T{constructor(){super("GPN_PrintRTF");P(this,"ImpLocal",`
  #### 帮助 
   ##### 上传模板
   - 请上传您的表单模板.
   ##### 选择导入模式
   - 请选择导入模式.
   - 按照模版的表单编号导入1：如果该编号已经存在就提示错误.
   - 按照模版的表单编号导入2：如果该编号已经存在就直接覆盖.
   - 按照模版的表单编号导入3：如果该编号已经存在就增加@WebUser.OrgNo(组织编号)导入.
        `);P(this,"ImpFrmID",`
  #### 帮助
   - 从表单库导入
  `);this.PageTitle="打印模版管理"}Init(){return o(this,null,function*(){this.AddGroup("A","导入表单","icon-xxx"),this.FileUpload("Imp","导入模版","",this.ImpLocal),this.AddBlank("Exp","下载模版","",this.ImpLocal)})}GenerSorts(){return o(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,m,d,_,c){return o(this,null,function*(){if(t==="Imp"){const n=new u("BP.WF.HttpHandler.WF_Admin_FoolFormDesigner_PrintTemplate");n.AddFile(this.UploadFile),n.AddPara("MyPK",this.PKVal);const e=yield n.DoMethodReturnString("PrintTemplate_Save");return e.includes("err@")?new s(l.Error,e):new s(l.Message,e)}if(t==="Exp"){const n=new u("BP.WF.HttpHandler.WF_Admin_FoolFormDesigner_PrintTemplate");n.AddPara("MyPK",this.PKVal);const e=yield n.DoMethodReturnString("PrintTemplate_Download");if(e.includes("err@"))return new s(l.Error,e);const{VITE_GLOB_API_URL:i}=w();let p=e;i.endsWith("/")&&(p=p.substring(1));const h=i+p;return new s(l.GoToUrl,h)}})}}export{f as GPN_PrintRTF};
