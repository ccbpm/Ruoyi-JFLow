var x=Object.defineProperty;var H=(n,a,e)=>a in n?x(n,a,{enumerable:!0,configurable:!0,writable:!0,value:e}):n[a]=e;var c=(n,a,e)=>H(n,typeof a!="symbol"?a+"":a,e);var u=(n,a,e)=>new Promise((p,r)=>{var m=s=>{try{l(e.next(s))}catch(i){r(i)}},R=s=>{try{l(e.throw(s))}catch(i){r(i)}},l=s=>s.done?p(s.value):Promise.resolve(s.value).then(m,R);l((e=e.apply(n,a)).next())});import{P as b,g as w,H as T,G as P,m as f,a8 as _}from"./entry/index-B5R3Coa4-1746862693206.js";import{b as G}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";class D extends b{constructor(){super("GPN_ImpTSEntity");c(this,"Help0",`
  #### 清空方式
  - 首先删除现在的数据，把指定的模板的数据导入到数据库.
  - 执行之前需要慎重.
  `);c(this,"Help1",`
  #### 更新方式导入
  - 在现有的数据基础上追加数据, 如果有主键系统将会更新. 没有主键系统将会自动生成.
  `);c(this,"Help2",`
  #### 追加方式导入
  - 在现有的数据基础上追加数据, 如果有主键系统将会更新. 没有主键系统将会自动生成.
  `);c(this,"HelpIt",`
  #### 数据制作说明
   - 新建一个Excel文件，比如 AAA.xlsx,
   - 在excel的工具栏中，找到文件另存为命令，选择 (Excel 工作簿(*.xlsx))格式. 
   - 在第一行数据填入如下列 </li>
   - 测试该模版是否可用,如果可用就把该文件放到 DatUser\\TempleteOfImp\\ 
   - 如果不可用：请尝试下载一个AccessDatabaseEngine.exe 文件安装到服务器上试试.
   #### 外键字段列
   - 外键字段列，存储的是外键名称， 比如：班级字段，存储的是 '1年级' 
   - 系统导入进去的则是 001 .
   #### 枚举字段
   - 枚举字段，存储的是标签列  比如：性别字段，存储的是 '女' 
   - 系统导入进去的则是 0.
   #### 外部数据源字段
   - 需要两个列, abc 与 abcT
   - abc 存储的是编号, abcT则是中文名称.
   #### Pop字段字段列
   - 列是Pop模式的列,  需要两个字段abc, 与abcT  abc存储 编号列， abcT存储的名称列.
   - 比如：选修科目字段, 要增加一个影子字段 选修科目T, 
   - '选修科目' 是存储的外键数据, 比如:001,002  
   - '选修科目T' 是存储的外键数据, 比如:语文,数学
  `);this.PageTitle="导入实体数据"}Init(){return u(this,null,function*(){this.AddGroup("A","导入数据"),this.FileUpload("0","清空方式导入","请上传符合格式的模板数据,执行之前阅读帮助.",this.Help0),this.FileUpload("1","更新追加方式导入","请上传符合格式的模板数据,执行之前阅读帮助.",this.Help1);const e=this.RequestVal("TSEnName"),{VITE_GLOB_API_URL:p}=w();let r="/DataUser/TempleteOfImp/"+e+".xlsx";p.endsWith("/")&&(r=r.substring(1));const m=p+r;this.AddGroup("B","模板"),this.AddGoToUrl("DownTemplate","下载模板",m),this.AddHelp("Help","模板说明",this.HelpIt)})}GenerSorts(){return u(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,p,r,m,R){return u(this,null,function*(){if(e=="DownTemplate")return;const l=this.RequestVal("TSEnName"),s=this.RequestVal("RefPK"),i=this.RequestVal("RefPKVal");if(l=="TS.Port.AdminGroup.Org"){if(e=="0"&&!window.confirm("此模式会清空所有数据并进行导入，是否继续?"))return;if(e=="1")try{const t=new T("BP.WF.HttpHandler.GPMPage");t.AddFile(this.UploadFile);const d=yield t.DoMethodReturnString("Template_SaveGroupIncByAppend");return new P(f.Message,d)}catch(t){return new P(f.Message,t)}}const h=this.RequestVal("ImpFuncUrl");if(h){const t=new FormData;if(t.append("file",this.UploadFile),t.append("EnsName",l),t.append("ImpWay",e),t.append("RefPK",s),t.append("RefPKVal",i),h.startsWith("http://")||h.startsWith("https://")){const d=yield _.post(h,t,{});if(d.status!=200){G.error(d.data);return}return new P(f.CloseAndReload,d.data||"导入成功")}}const o=new T("BP.WF.HttpHandler.WF_Comm_Sys");o.AddFile(this.UploadFile),o.AddPara("EnsName",l),o.AddPara("ImpWay",e),o.AddPara("RefPK",s),o.AddPara("RefPKVal",i);const A=yield o.DoMethodReturnJson("ImpData_Done");return new P(f.CloseAndReload,(A==null?void 0:A.Msg)||"导入成功")})}}export{D as GPN_ImpTSEntity};
