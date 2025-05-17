var h=Object.defineProperty;var D=(a,s,e)=>s in a?h(a,s,{enumerable:!0,configurable:!0,writable:!0,value:e}):a[s]=e;var m=(a,s,e)=>D(a,typeof s!="symbol"?s+"":s,e);var n=(a,s,e)=>new Promise((d,l)=>{var i=o=>{try{r(e.next(o))}catch(t){l(t)}},c=o=>{try{r(e.throw(o))}catch(t){l(t)}},r=o=>o.done?d(o.value):Promise.resolve(o.value).then(i,c);r((e=e.apply(a,s)).next())});import{P as x,g as u,H as A,G as P,m as I}from"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class F extends x{constructor(){super("GPN_ImpExcel");m(this,"Desc0",`  
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
`);this.PageTitle="导入"}Init(){return n(this,null,function*(){const e=this.params.dtlInfo.No;this.AddGroup("A","导入主表"),this.FileUpload("Excel","上传文件","请上传符合格式的Excel文件.",this.Desc0),this.AddBlank("Excel.SelectModel","选择方式",this.HelpTodo);const{VITE_GLOB_API_URL:d}=u();let l="/DataUser/TempleteOfImp/"+e+".xls";d.endsWith("/")&&(l=l.substring(1));const i=d+l;this.AddGoToUrl("DownTemplate","下载模板",i),this.AddGroup("B","导入从表"),this.AddBlank("SelectDtl","选择从表",this.HelpTodo),this.FileUpload("SelectDtl.Uploade","上传文件","请上传符合格式的Excel文件.",this.Desc0),this.AddBlank("SelectDtl.Uploade.SelectSheet","选择Sheet",this.HelpTodo),this.TextBox2_NameNote("sxxx","设置关系","ddd","主表字段","从表字段")})}GenerSorts(){return n(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,d,l,i,c){return n(this,null,function*(){if(e=="DownTemplate")return;const r=this.params.dtlInfo.No,o=this.params.query.WorkID||this.params.query.No,t=new A("BP.WF.HttpHandler.WF_CCForm");t.AddFile(this.UploadFile),t.AddPara("EnsName",r),t.AddPara("PageType","Vue3"),t.AddPara("FK_MapData",r),t.AddPara("WorkID",o),t.AddPara("DDL_ImpWay",e);const p=yield t.DoMethodReturnJson("ImpExcel_Done");return new P(I.Message,(p==null?void 0:p.Msg)||"导入成功")})}}export{F as GPN_ImpExcel};
