var A=Object.defineProperty;var b=(c,m,e)=>m in c?A(c,m,{enumerable:!0,configurable:!0,writable:!0,value:e}):c[m]=e;var s=(c,m,e)=>b(c,typeof m!="symbol"?m+"":m,e);var I=(c,m,e)=>new Promise((d,i)=>{var o=n=>{try{a(e.next(n))}catch(t){i(t)}},E=n=>{try{a(e.throw(n))}catch(t){i(t)}},a=n=>n.done?d(n.value):Promise.resolve(n.value).then(o,E);a((e=e.apply(c,m)).next())});import{GloComm as l}from"./GloComm-B1xAfTWw.js";import{MapAttr as f}from"./MapAttr-DcWjEeWW.js";import{SFTables as B,SFTable as N}from"./SFTable-BpxUt1jb.js";import{SysEnumMain as M}from"./SysEnumMain-NEBgQX-D.js";import{P as V,F as U,bj as _,W as R,G as u,m as S,aU as T}from"./entry/index-B5R3Coa4-1746862693206.js";import P from"./Events-D9tOL1Ad.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./SysEnum-DmKE2Ig7.js";const y=class y extends V{constructor(){super("GPN_NewDDL");s(this,"HelpAdminEnum",`
  #### 帮助
  - 枚举库维护, 进入枚举库后，在工具栏点击新建按钮.
  `);s(this,"HelpDBSrc",`
  #### 帮助
  - 数据源分为关系数据库类型的数据源与web服务的数据源.
  - 首先需要维护数据源，然后在数据源上创建字典.
  `);s(this,"HelpDict",`
  #### 帮助
  - 字典表管理,在数据源上创建字典表.
  - 如果没有数据源，请首先创建数据源.
  `);s(this,"Blank",`
  #### 帮助
  - 空白的字段: 用于加载表单的时候，他的数据源是通过，由参数的字典获得的.
  - 比如：表单里由，片区、省份、地市、区县四个下拉框字段. 当表单加载的时候，在没有确定片区其他的三个字段都无法确定值。
  - 省份、地区、区县就需要绑定空白数据源外键.
  - 使用级联关系，把其他的字段数据实现数据级联查询.
  `);s(this,"Docs1",`
  #### 帮助 
  - 待完善.
  `);s(this,"SelectedDict_Name",`
  #### 帮助
  - 请输入要生成的字段名, 格式:英文字母开头下划线数字.
  - 该名称将会是数据库的真实字段.
  `);s(this,"SelectedDict",`
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

  `);s(this,"NewStrEnum",`
  #### 帮助
   - 填写格式: 枚举值=枚举标签; 
   - 例如: ty=团员,dy=党员,qz=群众
   - 系统解析为: ty是团员, dy是党员, qz是群众.

  #### 数据存储.
   - string类型的枚举也称为标记枚举,字母存储一个列,标签存储一个列.
   - 在表单里字段是abc,那系统就会自动创建一个影子字段 abcT.
   - abc字段存储的是标记, abcT存储的是标签.
   - 这一点与外部数据源存储一致.
    `);s(this,"NewIntEnum",`
  #### 帮助
   - 填写格式1: 团员,党员,群众
   - 系统解析为: 0是团员， 1是党员，2是群众.
   - 填写格式2: @0=团员@1=党员@2=群众
   - 系统解析为: 10是团员， 20是党员，30是群众，这样就可以自己定义枚举值.
  #### 数据存储
   - int类型的枚举值是常用的数据类型，ccfrom是格式化的存储到数据表里.
   - 创建一个int类型的字段，用于存储枚举的数据.
    `);s(this,"Docs2",`

  #### 帮助
   - 依托富文本编辑器,实现对表单的编辑.
   - 优点:格式灵活,展现效果随心所欲.
   - 缺点:业务人员入手需要一定的学习成本.
   - 适用于:效果
    
  `);this.PageTitle="新建枚举/外键字段"}static SelectedEnum_FieldName(e,d,i,o,E,a){throw new Error("Method not implemented.")}Init(){return I(this,null,function*(){this.AddGroup("A","枚举字段");const e=U.SQLEnumMain;this.SelectItemsByList("SelectedEnum","新建枚举字段",y.SelectedEnum,!1,e),this.AddIcon("icon-options","SelectedEnum"),this.TextBox1_Name("SelectedEnum.FieldName","输入字段ID",this.SelectedDict_Name,"字段ID",()=>_.CCBPMRunModel===0?this.RequestVal("tb1","SelectedEnum"):this.RequestVal("tb1","SelectedEnum").replace(R.OrgNo+"_",""),"英文字母或者下划线开头."),this.AddGoToUrl("NewEnum","新建枚举",l.UrlGPN("GPN_Enum","","&FrmID="+this.RequestVal("FrmID"))),this.AddGoToUrl("AdminEnum","枚举库维护",l.UrlSearch("TS.FrmUI.SysEnumMain")),this.AddGoToUrl("AdminEnum1","枚举库维护(GL)",l.UrlGenerList("GL_NewEnum")),this.AddGroup("B","外部数据源(外键)字段"),this.SelectItemsByList("SelectedDict","新建外键字段",this.SelectedDict,!1,U.SQLOfNewDDLDict),this.AddIcon("icon-list","SelectedDict"),this.TextBox1_Name("SelectedDict.Name","输入字段ID",this.SelectedDict_Name,"字段ID",()=>this.RequestVal("tb1","SelectedDict"),"英文字母或者下划线开头."),this.AddGoToUrl("AdminDict","字典库维护",l.UrlSearch("TS.FrmUI.SFTable")),this.AddGoToUrl("AdminDBSrc","数据源维护",l.UrlSearch("TS.Sys.SFDBSrc"))})}GenerSorts(){return I(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,d,i,o,E){return I(this,null,function*(){if(e!="SelectedEnum"){if(e=="GoBack"){P.emit("goback",{drawerVisible:!1});return}if(e=="Blank")return yield B.Init_Blank(),this.InitDDL(o,i,"Blank");if(e==="AdminEnum.ToUrl"||e==="AdminEnum")return new u(S.GoToUrl,l.UrlSearch("TS.FrmUI.SysEnumMain"));if(e==="AdminDict.ToUrl"||e==="AdminDict")return new u(S.GoToUrl,l.UrlSearch("TS.FrmUI.SFTable"));if(e==="AdminDBSrc")return new u(S.GoToUrl,l.UrlSearch("TS.Sys.SFDBSrc"));if(e==="SelectedEnum.FieldName"){const a=this.RequestVal("FrmID"),n=this.RequestVal("GroupField"),t=this.RequestVal("CtrlType");if(!i)return;const p=this.RequestVal("tb1","SelectedEnum"),G=this.RequestVal("tb2","SelectedEnum"),D=new M(p);D.No=p,yield D.Retrieve(),D.EnumKey===""&&(D.EnumKey=i);const r=new f;if(r.MyPK=a+"_"+i,yield r.IsExits())return new u(S.Error,"字段在表单已经存在"+i);r.Name=G,r.KeyOfEn=i,r.FK_MapData=a,r.UIVisible=1,r.UIIsEnable=1,r.GroupID=n,r.DefVal=0,r.LGType=1,r.MyDataType=t==="2"?1:D.EnumType===0?2:1,r.SetPara("RBShowModel",3),r.UIContralType=parseInt(t)||T.DDL,r.UIBindKey=D.EnumKey,yield r.Insert();const F="/@/WF/Comm/En.vue?EnName=TS.FrmUI.MapAttrEnum&PKVal="+r.MyPK;return new u(S.GoToUrl,F)}if(e==="SelectedDict.Name"){const a=this.RequestVal("tb1","SelectedDict");return this.InitDDL(i,"",a)}}})}InitDDL(e,d,i){return I(this,null,function*(){const o=this.RequestVal("FrmID"),E=this.RequestVal("GroupField");if(!e)return;const n=new N(i);n.No=i,yield n.Retrieve();const t=new f;if(t.MyPK=o+"_"+e,(yield t.IsExits())==!0)return new u(S.Error,"字段在表单已经存在"+e);d?t.Name=d:t.Name=n.Name,t.KeyOfEn=e,t.FK_MapData=o,t.UIVisible=1,t.UIIsEnable=1,t.GroupID=E,t.LGType=0,t.MyDataType=1,t.UIContralType=T.DDL,t.UIBindKey=n.No,t.SetPara("SrcType",n.DBSrcType),yield t.Insert();const h=t.MyPK;t.UIVisible=0,t.MyPK=o+"_"+e+"T",t.KeyOfEn=e+"T",t.Name=t.Name+"T",t.UIContralType=T.TB,yield t.Insert();const p="/@/WF/Comm/En.vue?EnName=TS.FrmUI.MapAttrSFSQL&PKVal="+h;return new u(S.GoToUrl,p)})}};s(y,"SelectedEnum",`
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
  `);let w=y;export{w as GPN_NewDDL};
