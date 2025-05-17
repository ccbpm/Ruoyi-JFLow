var y=Object.defineProperty;var N=(a,e,t)=>e in a?y(a,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):a[e]=t;var l=(a,e,t)=>N(a,typeof e!="symbol"?e+"":e,t);var u=(a,e,t)=>new Promise((n,r)=>{var o=i=>{try{d(t.next(i))}catch(S){r(S)}},g=i=>{try{d(t.throw(i))}catch(S){r(S)}},d=i=>i.done?n(i.value):Promise.resolve(i.value).then(o,g);d((t=t.apply(a,e)).next())});import{P as T,W as c,E as B,C as M,U as I,f as E,ck as w,h as m,i as A,B as F,g as D,dS as R,H as C}from"./entry/index-B5R3Coa4-1746862693206.js";import{Auths as G,AuthAttr as v}from"./Auth-C4UQdex-.js";import{GPN_WorkShift as x}from"./GPN_WorkShift-BHRB07ak.js";import{PageBaseGroupEdit as L}from"./PageBaseGroupEdit-BXdNWKIo.js";class f extends L{constructor(){super("GPE_MyFrmStyle");l(this,"Desc0",`
  #### 帮助
   - 表单风格有两种风格：经典风格,简洁风格;
   - 经典风格有三种模式：1.默认模式，2.时尚模式，3.黑色模式。
   - 简洁风格有两种模式：1.简洁清晰模式，2.简洁紧凑模式。
   - 现在采用的是经典风格的默认模式。
   #### 运行效果图
   - ![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/FrmStyle.png "屏幕截图.png")
 `);l(this,"Desc1",`
 #### 帮助
  - 表单风格有两种风格;
  - 经典风格有三种模式：1.默认模式，2.时尚模式，3.黑色模式，5.传统模式。
  - 简洁风格有两种模式：1.简洁清晰模式，2.简洁紧凑模式。
  - 现在采用的是经典风格的时尚模式。
  #### 运行效果图
  - ![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/FrmStyle1.png "屏幕截图.png")
`);l(this,"Desc2",`
#### 帮助
 - 表单风格有两种风格;
 - 经典风格有三种模式：1.默认模式，2.时尚模式，3.黑色模式，5.传统模式。
 - 简洁风格有两种模式：1.简洁清晰模式，2.简洁紧凑模式。
 - 现在采用的是经典风格的黑色模式。
 #### 运行效果图
![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/FrmStyle2.png "屏幕截图.png")
`);l(this,"Desc3",`
#### 帮助
 - 表单风格有两种风格;
 - 经典风格有三种模式：1.默认模式，2.时尚模式，3.黑色模式，5.传统模式。
 - 简洁风格有两种模式：1.简洁清晰模式，2.简洁紧凑模式。
 - 现在采用的是时尚风格的简洁清晰模式。
 #### 运行效果图
![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/FrmStyle3.png "屏幕截图.png")
`);l(this,"Desc4",`
#### 帮助
 - 表单风格有两种风格;
 - 经典风格有三种模式：1.默认模式，2.时尚模式，3.黑色模式，5.传统模式。
 - 简洁风格有两种模式：1.简洁清晰模式，2.简洁紧凑模式。
 - 现在采用的是经典风格的简洁紧凑模式。
 #### 运行效果图
![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/FrmStyle4.png "屏幕截图.png")
`);l(this,"Desc5",`
#### 帮助
 - 表单风格有两种风格;
 - 经典风格有三种模式：1.默认模式，2.时尚模式，3.黑色模式，5.传统模式。
 - 简洁风格有两种模式：1.简洁清晰模式，2.简洁紧凑模式。
 - 现在采用的是经典风格的传统模式。
 #### 运行效果图
![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/FrmStyle5.png "屏幕截图.png")
`);l(this,"Desc11",`

  #### 说明

   - 仅仅对当前节点启用了审批组件(或者签批组件)有效.
   - 审核组件的信息会记录到审核信息表里面.
   - 通过设置批量审批属性可以灵活的满足不同的客户需求.
   
  #### 运行效果图
  
  ![输入图片说明](./resource/WF/Admin/AttrNode/BatchRole/Img/NodeBatchRole.png "屏幕截图.png")

  #### 流程案例图
  - 减刑假释流程
  ![输入图片说明](./resource/WF/Admin/AttrNode/BatchRole/Img/NodeBatchRoleFlow.png "屏幕截图.png")
  - 批次减刑流程
  ![输入图片说明](./resource/WF/Admin/AttrNode/BatchRole/Img/NodeBatchRoleFlow1.png "屏幕截图.png")

  
  #### 配置说明

  ![输入图片说明](./resource/WF/Admin/AttrNode/BatchRole/Img/NodeBatchRoleFlow2.png "屏幕截图.png")

 `);l(this,"Desc21",`
  #### 帮助
  - 对于节点表单有效.
  - 建议使用审核组件.
  #### 其它 
  - 该功能在2022.10以后的版本取消了.
  `);this.PageTitle="表单风格"}Init(){this.entity=new p,this.KeyOfEn="FrmStyle",this.AddGroup("A","经典风格"),this.Blank("0","默认",this.Desc0),this.Blank("1","时尚",this.Desc1),this.Blank("5","传统",this.Desc5),this.AddGroup("B","简洁风格"),this.Blank("3","清晰",this.Desc3),this.Blank("4","紧凑",this.Desc4)}AfterSave(t,n){if(t==n)throw new Error("Method not implemented.")}BtnClick(t,n,r){if(t==n||t===r)throw new Error("Method not implemented.")}}const U=Object.freeze(Object.defineProperty({__proto__:null,GPE_MyFrmStyle:f},Symbol.toStringTag,{value:"Module"}));class h extends T{constructor(){super("GPN_SelfMenu");l(this,"Docs0",`
  #### 帮助
  - 低代码顶部左侧菜单展示.
  - 如果不选择，显示默认配置，如果选择最少选择一个.
  #### 运行图例
  ![输入图片说明](./resource/WF/Comm/Setting/default.png "屏幕截图.png")  
  ![输入图片说明](./resource/WF/Comm/Setting/allocate.png "屏幕截图.png")  
  - .

`);this.PageTitle="常用菜单"}Init(){return u(this,null,function*(){this.SelectItemsByList("SelfMenu","常用菜单",this.Docs0,!0,this.getSelfMenu)})}getSelfMenu(){return u(this,null,function*(){return JSON.stringify([{No:"GL_Start",Name:"发起",Icon:"icon-paper-plane",Idx:1},{No:"GL_Todolist",Name:"待办",Icon:"icon-clock",Idx:2},{No:"GL_Runing",Name:"在途",Icon:"icon-hourglass",Idx:3},{No:"GL_Recent",Name:"近期",Icon:"icon-envelope",Idx:4},{No:"GL_Complete",Name:"已完成",Icon:"icon-check",Idx:5},{No:"GL_CC",Name:"抄送",Icon:"icon-bag",Idx:6},{No:"GL_Draft",Name:"草稿",Icon:"icon-note",Idx:7},{No:"GL_Focus",Name:"收藏",Icon:"icon-star",Idx:8}])})}GenerSorts(){return u(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,n,r,o,g){return u(this,null,function*(){const d=r.split(","),S=JSON.parse(yield this.getSelfMenu()).filter(s=>d.includes(s.No)).map(s=>({icon:s.Icon,idx:s.Idx})),_=S.map(s=>s.icon).join(","),P=S.map(s=>s.idx).join(",");if(t==="SelfMenu"){const s=new p(c.No);yield s.RetrieveFromDBSources(),s.SetPara("EnName",r),s.SetPara("Title",o),s.SetPara("Icon",_),s.SetPara("Idx",P),yield s.Update(),window.location.reload();return}})}}const j=Object.freeze(Object.defineProperty({__proto__:null,GPN_SelfMenu:h},Symbol.toStringTag,{value:"Module"}));class p extends B{constructor(e){super("TS.Port.MySetting"),c.CCBPMRunModel==M.SAAS&&(e==null?void 0:e.includes("_"))==!1&&(e=c.OrgNo+"_"+e),e&&this.setPKVal(e)}get HisUAC(){const e=new I;return e.IsDelete=!1,e.IsUpdate=!0,e.IsInsert=!1,e}get EnMap(){const e=new E("WF_Emp","我的设置");e.AddGroupAttr("基本信息"),e.AddTBStringPK("No",null,"账号",!0,!0,1,3,50),e.AddTBString("UserID",null,"UserID",!0,!1,0,50,200),e.AddTBString("Name",null,"名称",!0,!1,0,50,200),e.AddDDLEntities("FK_Dept",null,"部门",new w,!1),e.AddTBString("Tel",null,"电话",!0,!1,0,50,200),e.AddTBString("Email",null,"邮件",!0,!1,0,50,200),e.AddTBInt("FrmStyle",0,"表单风格",!1,!1),e.AddDDLStringEnum("SysLang","CH","系统语言","@CH=中文@En=英文@FT=繁体@JP=日文",!0),e.AddGroupAttr("邮件设置"),e.AddDDLStringEnum("EM_SeverType","IMAP","服务器类型","@IMAP=IMAP@POP3=POP3",!0),e.AddTBString("EM_Email",null,"邮件账号",!0,!1,0,50,200),e.AddTBString("EM_Pass",null,"密码",!0,!1,0,50,200),e.AddTBString("EM_IMAP_SeverIP",null,"IMAP服务器",!0,!1,0,50,200,!0),e.AddTBString("EM_IMAP_Port",null,"端口",!0,!1,0,50,200),e.AddBoolean("EM_IMAP_SSL",!1,"SSL",!0,!0),e.AddTBString("EM_SMTP_SeverIP",null,"SMTP服务器",!0,!1,0,50,200,!0),e.AddTBString("EM_SMTP_Port",null,"端口",!0,!1,0,50,200),e.AddBoolean("EM_SMTP_SSL",!1,"SSL",!0,!0),e.AddTBString("EM_SMTP_SenderName",null,"发件人名称",!0,!1,0,50,200,!0),e.AddTBStringDoc("EM_SMTP_SenderSigner",null,"签名",!0,!1,!0),e.AddTBAtParas(3500),e.AddRM_DtlSearch("授权",new G,v.Auther,"","","AutherToEmpNo,AutherToEmpName,AuthTypeText","icon-drop",!1,"");const t=new m;t.Title="修改密码",t.ClassMethod="CPass",t.HisMap.AddTBString("p1",null,"原密码",!0,!1,0,100,1e3,!0),t.HisMap.AddTBString("p2",null,"新密码",!0,!1,0,100,1e3,!0),t.HisMap.AddTBString("p3",null,"确认密码",!0,!1,0,100,1e3,!0),e.AddRefMethod(t);const n=new m;n.Title="设置头像",n.RefMethodType=A.TabOpen,n.ClassMethod="/src/views/sys/user/UploadAvatar.vue",e.AddRefMethod(n);const r=new m;r.Title="设置签名（电子签名图片）",r.RefMethodType=A.TabOpen,r.ClassMethod="/src/views/sys/user/CreateSignature.vue",e.AddRefMethod(r);const o=new m;return o.Title="上传签名",o.RefMethodType=A.TabOpen,o.ClassMethod="/src/views/sys/user/UploadSignature.vue",e.AddRefMethod(o),e.AddRM_GPE(new f,"icon-drop"),e.AddRM_GPN(new x,"icon-login"),e.AddRM_GPN(new h,"icon-drop"),this._enMap=e,this._enMap}SetIcon(){return"未实现."}SetSigner(){return"未实现."}SetSigineBody(){return"未实现."}CPass(e,t,n){return u(this,null,function*(){if(!e||!t||!n)return"新密码或旧密码不能为空,请填写完整.";{let r=c.No;c.CCBPMRunModel==M.SAAS&&(r=c.OrgNo+"_"+c.No);const o=new F("BP.Port.Emp",r);yield o.Retrieve();const{VITE_GLOB_ENCRYPTION_KEY:g}=D(),d=new R({key:g});return e=d.encryptByAES(e),t=d.encryptByAES(t),n=d.encryptByAES(n),yield o.DoMethodReturnString("ChangePass",e,t,n)}})}DingDingSetting(e,t){return u(this,null,function*(){if(!e||!t)return"人员或流程不能为空,请选择.";{const n=new C("bp.cdtb.Handler");return yield n.AddPara("EmpNo",e),yield n.AddPara("FlowNo",t),yield n.DoMethodReturnString("taskCycle")}})}}const K=Object.freeze(Object.defineProperty({__proto__:null,MySetting:p},Symbol.toStringTag,{value:"Module"}));export{f as G,p as M,U as a,j as b,K as c};
