var h=Object.defineProperty;var u=(o,t,e)=>t in o?h(o,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):o[t]=e;var m=(o,t,e)=>u(o,typeof t!="symbol"?t+"":t,e);var p=(o,t,e)=>new Promise((a,l)=>{var d=r=>{try{s(e.next(r))}catch(n){l(n)}},x=r=>{try{s(e.throw(r))}catch(n){l(n)}},s=r=>r.done?a(r.value):Promise.resolve(r.value).then(d,x);s((e=e.apply(o,t)).next())});import{P as E,G as i,m as c,H as P}from"./entry/index-B5R3Coa4-1746862693206.js";import{b as S}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";class w extends E{constructor(){super("GPN_ImpStation");m(this,"Imp",`
### 角色导入功能说明
#### 使用前提
1. **后端配置要求**  
   ➠ <span style="color:red">必须启用配置参数：\`GroupStationModel=1\`</span>
   （未配置将导致功能不可用）
2. **功能说明**  
   ➠ 本功能用于导入组织架构中的岗位(角色)、岗位(角色)类型数据，是追加导入，不会覆盖原有数据。
#### 文件格式要求
1. **文件类型**  
   ➠ 仅支持 **.xlsx** 或 **.xls** 格式的Excel文件  
   ➠ 其他格式（如CSV/PDF）将自动拒绝
### Excel工作表格式规范
#### Sheet1 - 岗位关系表
| **必填列**       | **说明**                          |
|------------------|-----------------------------------|
| 岗位名称<span style="color:red">*</span>     | 岗位的名称（允许岗位名称相同但是需要<span style="color:red">不同岗位类型</span>） |
| 岗位类型<span style="color:red">*</span>        | 岗位所属分类（需与Sheet2类型匹配）|

​			**Sheet1 -注意事项**  
  - <span style="color:red">禁止添加额外列或修改列名 </span>   
  - <span style="color:red">岗位类型必须在Sheet2中存在</span>


#### **Sheet2 - 岗位类型表**
  | **必填列**       | **说明**                          |
  |------------------|-----------------------------------|
  | 岗位类型<span style="color:red">*</span>         | 组织架构中定义的所有岗位类型      |`);this.PageTitle="导入角色数据"}Init(){this.AddGroup("A","导入"),this.FileUpload("ImpExcel","导入本机Excel文件","111",this.Imp)}GenerSorts(){return p(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,a,l,d,x){return p(this,null,function*(){if(e=="ImpExcel"){if(!this.isExcelFile(this.UploadFile.name))return new i(c.Error,"您上传的文件不是Excel，请上传正确的Excel文件");try{const s=new P("BP.WF.HttpHandler.GPMPage");s.AddFile(this.UploadFile),s.AddPara("ImpWay",l);const r=yield s.DoMethodReturnString("ExcelImpStation");return r.includes("err@")?new i(c.Error,r.replace("err@","")):new i(c.Message,r||"导入成功")}catch(s){S.error(s)}}})}isExcelFile(e){const a=e.split(".").pop().toLowerCase();return a==="xls"||a==="xlsx"}}export{w as GPN_ImpStation};
