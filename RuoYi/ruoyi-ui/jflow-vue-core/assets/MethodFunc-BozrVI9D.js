var g=Object.defineProperty;var A=(n,e,r)=>e in n?g(n,e,{enumerable:!0,configurable:!0,writable:!0,value:r}):n[e]=r;var i=(n,e,r)=>A(n,typeof e!="symbol"?e+"":e,r);var p=(n,e,r)=>new Promise((l,o)=>{var a=s=>{try{d(r.next(s))}catch(u){o(u)}},f=s=>{try{d(r.throw(s))}catch(u){o(u)}},d=s=>s.done?l(s.value):Promise.resolve(s.value).then(a,f);d((r=r.apply(n,e)).next())});import{q as m,F as c,G as M,m as T,E as B,U as D,f as I}from"./entry/index-B5R3Coa4-1746862693206.js";import{MethodAttr as t}from"./Method-Duk019iw.js";import{PageBaseGroupEdit as _}from"./PageBaseGroupEdit-BXdNWKIo.js";import{GloComm as F}from"./GloComm-B1xAfTWw.js";import{MethodFuncParas as P}from"./MethodFuncPara-BdRtCrvC.js";class h extends _{constructor(){super("GPE_MethodFunc");i(this,"SheetTree",`
  #### 帮助
   - 当一个节点需要绑定多个表单的时候，我们把这样的节点称为多表单节点，或者表单树节点。
   - 比如: 项目申报流程，提报项目的时候（开始节点）需要提交，项目基本信息、项目环评信息、项目风险评估、项目实施计划书.
   #### 页面展示
   ![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/duobiaodanzhanshi.png "屏幕截图.png")

   #### 功能说明
   - 如果在每个节点上，绑定不同的表单，就要在每个节点上单独绑定. 比如: 开始节点绑定3个表单，第2个节点绑定10个表单.
   #### 表单节点关系
   - 一个表单可以绑定一个流程的不同节点上，每个节点与这个表单，都有一个设置关系,我们称为表单节点关系.
   - 如下图，节点绑定表单
   ![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/duobiaodanliebiao.png "屏幕截图.png")

   #### 表单展现方式
   - 我们把一个节点需要绑定多个表单的节点称为多表单节点，它有两种展现方式，标签页与表单树。
   - 对应的流程demo:\\流程树\\表单解决方案\\树形表单与多表单。
  `);i(this,"helpDocs",`
    #### 说明
    - 您可以编写SQL语句在这个方法里。
    - 实体:在SQL的表达式里，有@OID作为参数字段。
    - 单据: 在SQL的表达式里，有@No作为参数字段。
    - 可以使用参数按钮，为执行该方法增加参数。
    `);this.PageTitle="方法内容",this.Btns=[{pageNo:"5",list:["设置"]}]}Init(){const{loadComponent:r}=m();this.entity=new S,this.KeyOfEn="MethodDocTypeOfFunc",this.AddGroup("A","执行方法"),this.SelfComponent("0","SQL脚本",r("/@/CCFast/CCBill/Method/GPN_MethodSQL.vue"),{classID:"TS.CCBill.MethodFunc",PKVal:this.PKVal,enable:!1},this.helpDocs),this.SingleTextArea("1","JavaScript脚本(开发中)","Docs","请输入方法内容",this.HelpUn),this.SingleTextArea("2","Typescript脚本(开发中)","Docs","请输入方法内容",this.HelpUn),this.AddGroup("B","数据源"),this.SelectItemsByGroupList("3","配置的过程",this.HelpUn,!1,c.srcDBSrc,c.srcSFProc,"Docs")}AfterSave(r,l){return p(this,null,function*(){})}BtnClick(r,l,o){if(r=="5"){const a=F.UrlEn("TS.AttrNode.Sln5",this.PKVal);return new M(T.OpenUrlByModal,a,"设置")}}}const O=Object.freeze(Object.defineProperty({__proto__:null,GPE_MethodFunc:h},Symbol.toStringTag,{value:"Module"}));class S extends B{constructor(e){super("TS.CCBill.MethodFunc"),this.setPKVal(e)}get HisUAC(){const e=new D;return e.IsDelete=!1,e.IsUpdate=!0,e.IsInsert=!1,e}get EnMap(){const e=new I("Frm_Method","功能方法");return e.AddGroupAttr("基本设置"),e.AddTBStringPK(t.No,null,"编号",!0,!0,0,50,10),e.AddTBString(t.Name,null,"方法名",!0,!1,0,300,10),e.AddTBString(t.MethodID,null,"方法ID",!0,!0,0,300,10),e.AddTBString(t.GroupID,null,"分组ID",!1,!1,0,50,10),e.AddTBString(t.MethodModel,null,"方法模式",!1,!1,0,300,10),e.AddTBString(t.Tag1,null,"Tag1",!1,!1,0,300,10),e.AddTBString(t.FrmID,null,"表单ID",!1,!1,0,300,10),e.AddTBString(t.Icon,null,"图标",!0,!1,0,50,10,!0),e.AddTBString(t.Mark,null,"功能说明",!0,!1,0,900,10,!0),e.SetHelperAlert(t.Mark,"对于该功能的描述."),e.AddBoolean(t.IsList,!1,"是否显示在列表?",!0,!0),e.AddGroupAttr("提示信息"),e.AddTBString("BtnDoneText","执行","执行按钮",!0,!1,0,100,10,!0),e.SetHelperAlert("BtnDoneText","执行按钮,默认为：执行"),e.AddTBString(t.WarningMsg,null,"执行警告信息",!0,!1,0,300,10,!0),e.AddTBString(t.MsgSuccess,null,"成功提示信息",!0,!1,0,300,10,!0),e.AddTBString(t.MsgErr,null,"失败提示信息",!0,!1,0,300,10,!0),e.AddTBInt(t.MethodDocTypeOfFunc,0,"内容类型?",!1,!1),e.AddTBString(t.Docs,null,"执行内容",!1,!1,0,300,10,!0),e.AddTBAtParas(),e.AddRM_GPE(new h,"icon-drop"),e.AddRM_DtlSearch("参数",new P,"FK_MapData","","","Name,KeyOfEn,DataType","icon-drop",!0,"&UIVisible=1&IsMove=true"),this._enMap=e,this._enMap}ZhuXiaoXueJi(){return"执行成功. ZhuXiaoXueJi "+this.Name}}const U=Object.freeze(Object.defineProperty({__proto__:null,MethodFunc:S},Symbol.toStringTag,{value:"Module"}));export{O as G,S as M,U as a};
