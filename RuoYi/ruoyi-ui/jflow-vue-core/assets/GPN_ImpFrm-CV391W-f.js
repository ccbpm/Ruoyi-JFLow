var F=Object.defineProperty;var u=(s,r,t)=>r in s?F(s,r,{enumerable:!0,configurable:!0,writable:!0,value:t}):s[r]=t;var l=(s,r,t)=>u(s,typeof r!="symbol"?r+"":r,t);var c=(s,r,t)=>new Promise((n,a)=>{var i=e=>{try{o(t.next(e))}catch(p){a(p)}},m=e=>{try{o(t.throw(e))}catch(p){a(p)}},o=e=>e.done?n(e.value):Promise.resolve(e.value).then(i,m);o((t=t.apply(s,r)).next())});import{FrmSorts as h}from"./FrmSort-BhvgVTIc.js";import{P as y,F as x,H as A,G as d,m as I}from"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class w extends y{constructor(){super("GPN_ImpFrm");l(this,"ImpLocal",`
  #### 帮助 
   ##### 上传模板
   - 请上传您的表单模板.
   ##### 选择导入模式
   - 请选择导入模式.
   - 按照模版的表单编号导入1：如果该编号已经存在就提示错误.
   - 按照模版的表单编号导入2：如果该编号已经存在就直接覆盖.
   - 按照模版的表单编号导入3：如果该编号已经存在就增加@WebUser.OrgNo(组织编号)导入.
        `);l(this,"ImpFrmID",`
  #### 帮助
   - 从表单库导入
  `);this.PageTitle="导入表单"}Init(){return c(this,null,function*(){this.AddGroup("A","导入表单","icon-xxx"),this.FileUpload("ImpLocal","从本机导入","",this.ImpLocal);const n=x.AtParaStringToJson("@0=按照模版的表单编号导入1@1=按照模版的表单编号导入2@2=按照模版的表单编号导入3"),a=Object.keys(n),i=[];for(const m of a)i.push({No:m,Name:n[m]});this.SelectItemsByList("ImpLocal.Way","选择模式",this.ImpLocal,!1,JSON.stringify(i)),this.AddIcon("ImpFrmID","icon-user"),this.AddIcon("ImpFrmIDxx","icon-user"),this.AddIcon("ImpFrmIDxx","icon-user")})}GenerSorts(){return c(this,null,function*(){const t=new h;return yield t.Init(),yield t.RetrieveAll(),t})}Save_TextBox_X(t,n,a,i,m){return c(this,null,function*(){if(t==="ImpLocal.Way"){const o=new A("BP.WF.HttpHandler.WF_Admin_Template");o.AddFile(this.UploadFile),o.AddPara("RB_ImpType",a),o.AddPara("FrmSort",n);const e=yield o.DoMethodReturnString("ImpFrmLocal_Done");return e.includes("err@")?new d(I.Error,e):new d(I.Message,e)}})}}export{w as GPN_ImpFrm};
