var D=Object.defineProperty;var u=(a,r,e)=>r in a?D(a,r,{enumerable:!0,configurable:!0,writable:!0,value:e}):a[r]=e;var c=(a,r,e)=>u(a,typeof r!="symbol"?r+"":r,e);var d=(a,r,e)=>new Promise((n,o)=>{var p=s=>{try{l(e.next(s))}catch(t){o(t)}},m=s=>{try{l(e.throw(s))}catch(t){o(t)}},l=s=>s.done?n(s.value):Promise.resolve(s.value).then(p,m);l((e=e.apply(a,r)).next())});import{P as I,g as P,H as _,G as h,m as g}from"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class G extends I{constructor(){super("GPN_DtlImpExcel");c(this,"Desc0",`  
#### 帮助  
  
1. **下载模板**  
   - 如遇到下载出错的情况，请联系管理员制作模板。  
     - **模板制作规则**：  
       - 将从表的列名复制到Excel的第一行。  
       ![屏幕截图](./resource/WF/Admin/FrmLogic/MapDtl/EditModel/DtlImg.png)  
     - **模板文件存放位置**：  
       - 把制作好的模板文件放入后台的 \`\\DataUser\\TemplateOfImp\` 目录。  
       ![文件路径截图](./resource/WF/Admin/FrmLogic/MapDtl/EditModel/FilePath.png)  
  
2. **输入数据**  
  
3. **上传Excel文件并执行导入**  
`);this.PageTitle="导入"}Init(){return d(this,null,function*(){const e=this.params.dtlInfo.No;this.AddGroup("A","导入"),this.FileUpload("0","清空方式导入","请上传符合格式的Excel文件.",this.Desc0);const{VITE_GLOB_API_URL:n}=P();let o="/DataUser/TempleteOfImp/"+e+".xls";n.endsWith("/")&&(o=o.substring(1));const p=n+o;this.AddGoToUrl("DownTemplate","下载模板",p)})}GenerSorts(){return d(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,n,o,p,m){return d(this,null,function*(){if(e=="DownTemplate")return;const l=this.params.dtlInfo.No,s=this.params.query.WorkID||this.params.query.No,t=new _("BP.WF.HttpHandler.WF_CCForm");t.AddFile(this.UploadFile),t.AddPara("EnsName",l),t.AddPara("PageType","Vue3"),t.AddPara("FK_MapData",l),t.AddPara("WorkID",s),t.AddPara("DDL_ImpWay",e);const i=yield t.DoMethodReturnJson("DtlImpByExcel_Imp");return new h(g.Message,(i==null?void 0:i.Msg)||"导入成功")})}}export{G as GPN_DtlImpExcel};
