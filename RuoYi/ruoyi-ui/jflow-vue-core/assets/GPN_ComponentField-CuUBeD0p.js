var M=Object.defineProperty;var A=(c,o,e)=>o in c?M(c,o,{enumerable:!0,configurable:!0,writable:!0,value:e}):c[o]=e;var m=(c,o,e)=>A(c,typeof o!="symbol"?o+"":o,e);var x=(c,o,e)=>new Promise((I,s)=>{var r=t=>{try{a(e.next(t))}catch(i){s(i)}},K=t=>{try{a(e.throw(t))}catch(i){s(i)}},a=t=>t.done?I(t.value):Promise.resolve(t.value).then(r,K);a((e=e.apply(c,o)).next())});import{GloComm as w}from"./GloComm-B1xAfTWw.js";import{MapAttr as E}from"./MapAttr-DcWjEeWW.js";import{GroupFields as F}from"./GroupField-lMJeJtcW.js";import{P as _,G as d,m as p,aU as f,D as y}from"./entry/index-B5R3Coa4-1746862693206.js";import{M as B}from"./MapExt-DVovzpWn.js";import{MapDtl as u}from"./MapDtl-B_Ep8ewM.js";import{FrmAttachment as S}from"./FrmAttachment-MhkNqka4.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./Events-D9tOL1Ad.js";import"./EntityOID-C1xznxai.js";import"./MapData-D5zymw8O.js";import"./EnumLab-CsLi93T0.js";class b extends _{constructor(){super("GPN_ComponentField");m(this,"ScoreDesc",`
  #### 帮助
  - 评分控件: 对当前记录的数据进行打分的控件.
  - 使用属性可以控制打分的长度，比如：5分，10分.
  - 设置图片:
  - 运行图片：
  #### 数据存储.
  - 设置信息存储在：Sys_MapAttr表里.
  - 数据存储在,控件ID对应的字段里.
    `);m(this,"MapDesc",`
  #### 帮助
  - 地图控件: 对当前记录的数据进行打分的控件.
  - 使用属性可以控制打分的长度，比如：5分，10分.
  - 设置图片:
  - 运行图片：
  #### 数据存储.
  - 设置信息存储在：Sys_MapAttr表里.
  - 数据存储在,控件ID对应的字段里.
    `);m(this,"FrmBtnDesc",`
  #### 帮助
  - 按钮控件: 可以点击后执行Js脚本的控件, 利用onclick来承载编写的js.
  - 应用场景: 使用属性可以控制打分的长度，比如：5分，10分.
  - 如下图:
  - 
  #### 数据存储.
  - 设置信息存储在：Sys_MapAttr表里.
    `);m(this,"FrmLinkDesc",`
  #### 帮助
  - 按钮控件: 可以点击后执行Js脚本的控件, 利用onclick来承载编写的js.
  - 如下图:
  - 
  #### 数据存储.
  - 设置信息存储在：Sys_MapAttr表里.
  - 数据信息存储：无
    `);m(this,"LocationDesc",`
    #### 帮助
    - 按钮控件: 定位信息
    - 如下图:
    - 
    #### 数据存储.
    - 设置信息存储在：Sys_MapAttr表里.
    - 数据信息存储：控件对应的字段.
      `);m(this,"FieldAth",`
  #### 帮助
  - 字段附件; 
  #### 数据存储.
    `);m(this,"NewIntEnum",`
  #### 帮助
  - 填写格式1: 团员,党员,群众
  - 系统解析为: 0是团员， 1是党员，2是群众.
  - 填写格式2: @0=团员@1=党员@2=群众
  - 系统解析为: 10是团员， 20是党员，30是群众，这样就可以自己定义枚举值.
  #### 数据存储
  - int类型的枚举值是常用的数据类型，ccfrom是格式化的存储到数据表里.
  - 创建一个int类型的字段，用于存储枚举的数据.
    `);m(this,"Docs1",`
  #### 帮助 
   - 该表单是固定格式的表单,可以展现4列6列展现.
   - 优点:开发效率高,展现简洁,学习成本低,业务人员可以入手.
   - 缺点:展示样式固定.
  `);m(this,"Docs2",`

  #### 帮助
   - 依托富文本编辑器,实现对表单的编辑.
   - 优点:格式灵活,展现效果随心所欲.
   - 缺点:业务人员入手需要一定的学习成本.
   - 适用于:效果
  `);this.PageTitle="新建字段自定义组件"}Init(){this.AddGroup("A","通用组件"),this.TextBox2_NameNo("ExtScore","评分",this.ScoreDesc,"Score","字段ID","名称","评分"),this.AddIcon("icon-like","ExtScore"),this.TextBox2_NameNo("FrmBtn","按钮",this.FrmBtnDesc,"FrmBtn","组件ID","组件名称","按钮1"),this.AddIcon("icon-drop","FrmBtn"),this.TextBox2_NameNo("FrmLink","超链接",this.FrmLinkDesc,"FrmLink","字段ID","连接标签","我的连接"),this.AddIcon("icon-link","FrmLink"),this.TextBox2_NameNo("Location","定位",this.LocationDesc,"Location","字段ID","名称","定位组件"),this.AddIcon("icon-location-pin","Location"),this.TextBox2_NameNo("FrmHtml","大块说明",this.HelpUn,"","字段ID","名称","大块说明"),this.AddIcon("icon-doc","FrmHtml"),this.TextBox2_NameNo("ExtMap","地图",this.MapDesc,"Map","字段ID","名称","地图"),this.AddIcon("icon-doc","ExtMap"),this.AddGroup("F","OCR组件"),this.TextBox2_NameNo("id_card_upload","身份证",this.HelpUn,"Card","组件ID","组件名称","身份证1"),this.AddIcon("icon-user","id_card_upload"),(this.RequestVal("PageFrom")||"")==="Dtl"&&this.AddBlank("Invoice","发票",this.HelpUn,"icon-layers"),this.AddGroup("B","流程组件"),this.TextBox2_NameNo("FlowRefLink","关联流程",this.HelpUn,"FlowRefLink","字段ID","名称","关联流程"),this.AddIcon("icon-share","FlowRefLink"),this.TextBox2_NameNo("BillRefLink","关联单据",this.HelpUn,"BillRefLink","字段ID","名称","关联单据"),this.AddIcon("icon-share","BillRefLink"),this.AddBlank("GovAth","公文正文",this.HelpUn),this.AddIcon("icon-doc","GovAth"),this.AddBlank("WordNum","公文字号",this.HelpUn),this.AddIcon("icon-star","WordNum"),this.AddBlank("FlowBBS","流程评论",this.HelpUn),this.AddIcon("icon-bubble","FlowBBS"),this.TextBox2_NameNo("SignCheck","签批组件",this.HelpUn,"SC","字段ID","名称","签批组件"),this.AddIcon("icon-check","SignCheck"),this.AddGroup("C","实验中"),this.TextBox3_NameNoNote("img","图片",this.HelpUn,"Img","组件ID","图片名称","图片URL","通用图片"),this.AddIcon("icon-picture","img"),this.TextBox2_NameNo("Progress","流程进度图",this.HelpUn,"Progress","字段ID","名称","流程进度图"),this.AddIcon("icon-check","Progress"),this.TextBox2_NameNo("iframe","框架",this.FieldAth,"Iframe","字段ID","名称","我的框架"),this.AddIcon("icon-loop","iframe")}GenerSorts(){return x(this,null,function*(){const e=this.RequestVal("FrmID"),I=new F;return yield I.Retrieve("FrmID",e,"Idx"),I.filter(s=>s.CtrlType===""||s.CtrlType==="Attr").map(s=>({No:s.PKVal,Name:s.Lab}))})}Save_TextBox_X(e,I,s,r,K){return x(this,null,function*(){const a=this.RequestVal("FrmID");if(e==="ExtScore"||e==="ExtMap"||e=="FrmHtml"||e=="FlowRefLink"||e=="BillRefLink"||e=="FrmBtn"||e=="FrmLink"||e=="SignCheck"){const t=new E;t.GroupID=I,t.FK_MapData=a,t.MyPK=a+"_"+r;let i=t.MyPK;if(yield t.IsExits())return new d(p.Message,"字段ID=["+r+"]已经存在");t.KeyOfEn=r,t.Name=s;let n=0;if(e=="ExtScore"&&(n=101),e=="ExtMap"&&(n=4),e=="FlowRefLink"&&(n=200),e=="BillRefLink"&&(n=201),e=="SignCheck"&&(n=f.SignCheck),e=="FrmBtn"&&(n=f.Btn),e=="FrmLink"&&(n=f.HyperLink),e=="FrmHtml"){n=f.FrmHtml,i="HtmlText_"+t.MyPK;const l=new B("BP.Sys.MapExt");l.setPKVal(i),(yield l.RetrieveFromDBSources())==0&&(l.MyPK=i,l.FK_MapData=a,l.ExtType="HtmlText",l.ExtModel="HtmlText",l.AttrOfOper=t.KeyOfEn,yield l.Insert())}const h="TS.FrmUI.SelfCommonent."+e;t.UIContralType=n,t.SetPara("CtrlType",e),t.SetPara("EnName",h),yield t.Insert();const D=w.UrlEn(h,i);return new d(p.GoToUrl,D)}if(e=="Invoice"){const t=new E;if(t.GroupID=I,t.FK_MapData=a,t.SetPara("GroupName","Invoice"),t.DataType=y.AppString,t.UIIsEnable=0,t.Name="发票代码",t.KeyOfEn="InvoiceID",t.MyPK=a+"_"+t.KeyOfEn,yield t.IsExits())return new d(p.Message,"发票已经存在.");yield t.Insert(),t.Name="发票号码",t.KeyOfEn="InvoiceCode",t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert()),t.Name="开票日期",t.KeyOfEn="InvoiceRelDT",t.DataType=y.AppDate,t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert()),t.Name="发票类型",t.KeyOfEn="InvoiceTypeStr",t.DataType=y.AppString,t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert()),t.Name="发票金额",t.KeyOfEn="InvoiceJE",t.DataType=y.AppMoney,t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert()),t.Name="税额",t.KeyOfEn="InvoiceTaxJE",t.DataType=y.AppMoney,t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert()),t.Name="税率",t.KeyOfEn="InvoiceTaxRate",t.DataType=y.AppMoney,t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert()),t.Name="购买方名称",t.KeyOfEn="InvoiceBuyName",t.MaxLen=200,t.MyPK=a+"_"+t.KeyOfEn,t.UIWidth=200,(yield t.IsExits())==!1&&(yield t.Insert()),t.KeyOfEn="InvoiceBuyCode",t.Name="购买方ID",t.MaxLen=200,t.MyPK=a+"_"+t.KeyOfEn,t.UIWidth=150,(yield t.IsExits())==!1&&(yield t.Insert()),t.KeyOfEn="InvoiceBuyAddr",t.Name="购买方地址电话",t.DataType=y.AppString,t.MaxLen=200,t.UIWidth=200,(yield t.IsExits())==!1&&(yield t.Insert()),t.KeyOfEn="InvoiceBuyBankInfo",t.Name="购买方开户行及账号",t.DataType=y.AppString,t.MaxLen=200,t.UIWidth=200,(yield t.IsExits())==!1&&(yield t.Insert()),t.Name="销售方名称",t.KeyOfEn="InvoiceSaleName",t.MaxLen=200,t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert()),t.KeyOfEn="InvoiceSaleCode",t.Name="销售方ID",t.MaxLen=150,t.UIWidth=150,t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert()),t.KeyOfEn="InvoiceSaleAddr",t.Name="销售方地址电话",t.UIWidth=200,t.MaxLen=200,t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert()),t.KeyOfEn="InvoiceSaleBankInfo",t.Name="销售方开户行及账号",t.MaxLen=200,t.UIWidth=200,t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert()),t.KeyOfEn="Note",t.Name="备注",t.MaxLen=500,t.UIWidth=500,t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert()),t.KeyOfEn="InvoiceFJ",t.Name="附件",t.MaxLen=500,t.UIWidth=500,t.UIContralType=6,t.MyPK=a+"_"+t.KeyOfEn,(yield t.IsExits())==!1&&(yield t.Insert());const i=new S;i.MyPK=t.MyPK,(yield i.IsExits())==!1&&(i.FK_MapData=a,i.NoOfObj=r,i.Name=s,i.IsDtlAth=1,i.IsVisable=0,yield i.Insert());const n=new u(t.FK_MapData);yield n.Retrieve(),n.SetPara("IsInvoice",1),n.IsInsert=0,yield n.Update()}if(e==="FlowBBS"||e=="Location"||e=="WordNum"||e=="GovAth"){const t=new E;if(t.GroupID=I,t.Name=this.GetPageName(e),t.FK_MapData=a,t.MyPK=a+"_"+e,yield t.IsExits())return new d(p.Message,"字段ID=["+e+"]已经存在");t.KeyOfEn=e,t.Name=s;let i=0;e=="FlowBBS"&&(i=f.FlowBBS),e=="Location"&&(i=f.Location),e=="WordNum"&&(i=f.WordNum),e=="GovAth"&&(i=f.GovDocFile,t.Name="公文正文");const n="TS.FrmUI.SelfCommonent."+e;t.UIContralType=i,t.SetPara("CtrlType",e),t.SetPara("EnName",n),yield t.Insert();const h=w.UrlEn(n,t.MyPK);return new d(p.GoToUrl,h)}if(e==="id_card_upload"){const t=this.RequestVal("FrmID"),i=new E;if(i.GroupID=I,i.FK_MapData=t,i.MyPK=t+"_"+r,yield i.IsExits())return new d(p.Message,"字段ID=["+e+"]已经存在");i.KeyOfEn=r,i.Name=s,i.UIContralType=13,i.UIIsEnable=0,yield i.Insert(),i.MyPK=t+"_"+r+"Name",i.KeyOfEn=r+"Name",i.Name="姓名",yield i.Insert(),i.MyPK=t+"_"+r+"Address",i.KeyOfEn=r+"Address",i.ColSpan=3,i.Name="地址",yield i.Insert();const n=w.UrlEn("TS.FrmUI.MapAttrString",t+"_"+r);return new d(p.GoToUrl,n)}})}}export{b as GPN_ComponentField};
