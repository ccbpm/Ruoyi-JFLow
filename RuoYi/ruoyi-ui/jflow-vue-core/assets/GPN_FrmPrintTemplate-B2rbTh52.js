var c=Object.defineProperty;var S=(m,i,t)=>i in m?c(m,i,{enumerable:!0,configurable:!0,writable:!0,value:t}):m[i]=t;var r=(m,i,t)=>S(m,typeof i!="symbol"?i+"":i,t);var a=(m,i,t)=>new Promise((s,o)=>{var p=n=>{try{e(t.next(n))}catch(l){o(l)}},T=n=>{try{e(t.throw(n))}catch(l){o(l)}},e=n=>n.done?s(n.value):Promise.resolve(n.value).then(p,T);e((t=t.apply(m,i)).next())});import{FrmPrintTemplate as P}from"./FrmPrintTemplate-DuKpQVFr.js";import{P as d,G as y,m as F}from"./entry/index-B5R3Coa4-1746862693206.js";import{D as u}from"./DBAccess-CZ0wdWXU.js";import{GloComm as x}from"./GloComm-B1xAfTWw.js";import"./FrmPrintDB-DmkZTfPT.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FrmTrack-Ct-No0Nq.js";class E extends d{constructor(){super("GPN_FrmPrintTemplate");r(this,"HelpAdminEnum",`
  #### 帮助
  - 枚举库维护, 进入枚举库后，在工具栏点击新建按钮.
  `);r(this,"HelpDBSrc",`
  #### 帮助
  - 数据源分为关系数据库类型的数据源与web服务的数据源.
  - 首先需要维护数据源，然后在数据源上创建字典.
  `);r(this,"HelpDict",`
  #### 帮助
  - 字典表管理,在数据源上创建字典表.
  - 如果没有数据源，请首先创建数据源.
  `);r(this,"Blank",`
  #### 帮助
  - 空白的字段: 用于加载表单的时候，他的数据源是通过，由参数的字典获得的.
  - 比如：表单里由，片区、省份、地市、区县四个下拉框字段. 当表单加载的时候，在没有确定片区其他的三个字段都无法确定值。
  - 省份、地区、区县就需要绑定空白数据源外键.
  - 使用级联关系，把其他的字段数据实现数据级联查询.
  `);r(this,"Docs1",`
  #### 帮助 
  - 待完善.
  `);r(this,"SelectedDict_Name",`
  #### 帮助
  - 请输入要生成的字段名, 格式:英文字母开头下划线数字.
  - 该名称将会是数据库的真实字段.
  `);r(this,"SelectedDict",`
  #### 帮助
  - 请选择一个外键字典表，如果没有请在左侧创建一个字典表.
  - 创建外键字段,必须选定外键的字典表.
  - 列出来的都是无参数的字典.

  #### 关于外键字典表
  - 具有编码，名称数据我们称为字典表，比如：片区、省份、税种、税目、部门.
  - 创建外键字典表必须依托一个数据源.
  - 从其他系统获得的数据, 
  - 外键字典表可以链接到

  #### 字典数据来源类型
  - SQL语句：从关系数据库中获得来的.
  - WebApi: 通过web服务获得.
  - Javascript: 通过脚本的function方法获得.
  - ccform内置的数据维护,维护在ccbpm系统中的字典.

  #### 两种格式数据格式
  - 编号名称格式: 比如片区、省份.
  - 树结构模式: 比如部门、产品目录树.
  #### 数据源的类型
  - 关系数据库
  - web服务.

  `);r(this,"NewStrEnum",`
  #### 帮助
   - 填写格式: 枚举值=枚举标签; 
   - 例如: ty=团员,dy=党员,qz=群众
   - 系统解析为: ty是团员, dy是党员, qz是群众.

  #### 数据存储.
   - string类型的枚举也称为标记枚举,字母存储一个列,标签存储一个列.
   - 在表单里字段是abc,那系统就会自动创建一个影子字段 abcT.
   - abc字段存储的是标记, abcT存储的是标签.
   - 这一点与外部数据源存储一致.
    `);r(this,"NewIntEnum",`
  #### 帮助
   - 填写格式1: 团员,党员,群众
   - 系统解析为: 0是团员， 1是党员，2是群众.
   - 填写格式2: @0=团员@1=党员@2=群众
   - 系统解析为: 10是团员， 20是党员，30是群众，这样就可以自己定义枚举值.
  #### 数据存储
   - int类型的枚举值是常用的数据类型，ccfrom是格式化的存储到数据表里.
   - 创建一个int类型的字段，用于存储枚举的数据.
    `);r(this,"Docs2",`

  #### 帮助
   - 依托富文本编辑器,实现对表单的编辑.
   - 优点:格式灵活,展现效果随心所欲.
   - 缺点:业务人员入手需要一定的学习成本.
   - 适用于:效果
    
  `);this.PageTitle="新建打印模板",this.ForEntityClassID="TS.Sys.Printer.FrmPrintTemplate"}Init(){this.AddGroup("A","新建打印模板"),this.TextBox1_Name("RTF","Rtf打印模板",this.HelpUn,"输入名称","我的Rtf模板","请输入模板名称"),this.TextBox1_Name("VSTOExcel","VSTO模式Excel",this.HelpUn,"输入名称","我的excel模板","请输入模板名称"),this.TextBox1_Name("VSTOWord","VSTO模式Word",this.HelpUn,"输入名称","我的word模板","请输入模板名称")}GenerSorts(){return a(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,s,o,p,T){return a(this,null,function*(){const e=new P;e.FrmID=this.RefPKVal,e.Name=o,t=="RTF"&&(e.TemplateFileModel=0,e.FileModel="RTF",e.SetPara("EnName","TS.Sys.Printer.PrintTemplateRTF")),t=="VSTOExcel"&&(e.TemplateFileModel=2,e.PrintFileType=2,e.FileModel="VSTOExcel",e.SetPara("EnName","TS.Sys.Printer.PrintTemplateVSTOExcel")),t=="VSTOWord"&&(e.TemplateFileModel=1,e.PrintFileType=0,e.FileModel="VSTOWord",e.SetPara("EnName","TS.Sys.Printer.PrintTemplateVSTOWord")),e.MyPK=u.GenerGUID(),e.FileModel=t,yield e.Insert();const n=x.UrlComponent(e.GetParaString("EnName"),e.MyPK);return new y(F.GoToUrl,n)})}}r(E,"SelectedEnum",`
  #### 帮助 
   - 选择枚举库的枚举.
   - 如果没有该数据，请点击枚举库管理，新建枚举.
  #### 定义.
   - 枚举分为int类型的枚举与string类型的枚举.
   - Int类型的枚举:政治面貌, 0=群众1=团员,2=党员.
   - String类型的枚举:政治面貌, qz=群众 ty=团员, dy=党员 .
   - 格式为(Item用逗号分开): 事假,病假,婚假,其它

  #### 枚举的存储
   - 枚举主表: Sys_EnumMain 
   - 枚举从表: Sys_Enum
  `);export{E as GPN_FrmPrintTemplate};
