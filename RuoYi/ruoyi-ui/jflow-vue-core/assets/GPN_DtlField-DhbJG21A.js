var x=Object.defineProperty;var F=(u,c,t)=>c in u?x(u,c,{enumerable:!0,configurable:!0,writable:!0,value:t}):u[c]=t;var p=(u,c,t)=>F(u,typeof c!="symbol"?c+"":c,t);var w=(u,c,t)=>new Promise((S,o)=>{var I=i=>{try{n(t.next(i))}catch(T){o(T)}},K=i=>{try{n(t.throw(i))}catch(T){o(T)}},n=i=>i.done?S(i.value):Promise.resolve(i.value).then(I,K);n((t=t.apply(u,c)).next())});import{MapAttr as D}from"./MapAttr-DcWjEeWW.js";import{P,F as M,G as y,m as l,D as m,aU as d}from"./entry/index-B5R3Coa4-1746862693206.js";import{GPN_NewDDL as U}from"./GPN_NewDDL-CFlFuyrV.js";import{SFTables as O,SFTable as N}from"./SFTable-BpxUt1jb.js";import{SysEnumMain as _}from"./SysEnumMain-NEBgQX-D.js";import{FrmAttachment as B}from"./FrmAttachment-MhkNqka4.js";import{GroupFields as v}from"./GroupField-lMJeJtcW.js";import{MapDtl as L}from"./MapDtl-B_Ep8ewM.js";import{D as G}from"./DBAccess-CZ0wdWXU.js";import"./Events-D9tOL1Ad.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./GloComm-B1xAfTWw.js";import"./FrmTrack-Ct-No0Nq.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./SysEnum-DmKE2Ig7.js";import"./EntityOID-C1xznxai.js";import"./MapData-D5zymw8O.js";import"./EnumLab-CsLi93T0.js";class me extends P{constructor(){super("GPN_DtlField");p(this,"HelpString",`
  #### 帮助
   - 文本类型的字段.
   - 比如:姓名、编号、地址、电话、邮件.
  `);p(this,"HelpInt",`
  #### 帮助
   - 整数类型数据.
  `);p(this,"HelpNumber",`
  #### 帮助
  - 数值类型数据.
  `);p(this,"HelpEnumDDL",`
    #### 帮助
    - 枚举类型数据: 枚举值,枚举标签; 
    `);p(this,"HelpEnumNew",`
        #### 帮助
        - 填写格式: 枚举值,枚举标签; 
        - 例如: ty,团员;dy=党员;qz,群众;
        - 系统解析为: ty是团员, dy是党员, qz是群众.
      
        #### 数据存储.
        - string类型的枚举也称为标记枚举,字母存储一个列,标签存储一个列.
        - 在表单里字段是abc,那系统就会自动创建一个影子字段 abcT.
        - abc字段存储的是标记, abcT存储的是标签.
        `);p(this,"Blank",`
  #### 帮助
  - 空白的字段: 用于加载表单的时候，他的数据源是通过，由参数的字典获得的.
  - 比如：表单里由，片区、省份、地市、区县四个下拉框字段. 当表单加载的时候，在没有确定片区其他的三个字段都无法确定值。
  - 省份、地区、区县就需要绑定空白数据源外键.
  - 使用级联关系，把其他的字段数据实现数据级联查询.
  `);p(this,"HelpBoolean",`
    #### 帮助
    - 填写格式:开关类型数据.
    `);p(this,"HelpJE",`
  #### 帮助
  - 金额类型数据; 
  `);p(this,"HelpTime",`
  #### 帮助
  - 时间类型数据.
  `);p(this,"HelpDT",`
  #### 帮助
  - 日期类型数据; 
  `);p(this,"FieldAth",`
  #### 帮助
  - 字段附件，附件以字段名的形式在页面中显示; 
  #### 图例
  ![输入图片说明](./resource/WF/Admin/FrmLogic/SFTable/Img/Ath1.png "屏幕截图.png") 
  #### 数据存储
  - 附件的默认保存在web服务器上。
  - 可以保存到ftp服务器上, ftp的服务器的连接配置在全局的配置文件中。
  - 如果需要保存到数据库，就需要考虑数据库的存储与备份的问题，文件将会存储在 Sys_FrmAttachmentDB 表中。
 
    `)}Init(){this.PageTitle="新建从表字段",this.ForEntityClassID="",this.AddGroup("Nurel","基本字段"),this.TextBox2_NameNo("String","文本字段",this.HelpString,"","字段名","中文名",""),this.AddIcon("iconfont icon-fuwenbenkuang","String"),this.TextBox2_NameNo("Int","整数",this.HelpInt,"","字段名","中文名",""),this.AddIcon("iconfont icon-zhengshu","Int"),this.TextBox2_NameNo("Number","数值",this.HelpNumber,"","字段名","中文名",""),this.AddIcon("iconfont icon-ziduanleixing-zhengshu","Number"),this.TextBox2_NameNo("JE","金额",this.HelpJE,"","字段名","中文名",""),this.AddIcon("iconfont icon-yifabupiaoju-renminbi-xi","JE"),this.TextBox2_NameNo("Time","日期时间",this.HelpTime,"","字段名","中文名",""),this.AddIcon("iconfont icon-shijian1","Time"),this.TextBox2_NameNo("DT","日期",this.HelpDT,"","字段名","中文名",""),this.AddIcon("iconfont icon-riqiqishu","DT"),this.TextBox2_NameNo("Boolean","开关",this.HelpBoolean,"","字段名","中文名",""),this.AddIcon("iconfont icon-fuxuankuang","Boolean"),this.TextBox2_NameNo("write","写字板",this.FieldAth,"Ath","字段ID","写字板名称","写字板"),this.AddIcon("iconfont icon-xiezi","write"),this.AddGroup("Enum","枚举字段");const t=M.SQLEnumMain;this.SelectItemsByList("SelectedEnum","新建枚举字段",U.SelectedEnum,!1,t),this.TextBox1_Name("SelectedEnum.FieldName","输入字段ID",this.HelpEnumDDL,"字段ID",()=>this.RequestVal("tb1","SelectedEnum"),"英文字母或者下划线开头."),this.AddFunction("AdminEnum","枚举库维护",this.AdminEnum),this.AddGroup("FK","外键字段"),this.SelectItemsByGroupList("SelectedDict","新建外键字段",this.HelpEnumDDL,!1,M.srcDBSrc,M.SQLSFTable),this.TextBox1_Name("SelectedDict.Name","输入字段ID",this.HelpEnumDDL,"字段ID",()=>this.RequestVal("tb1","SelectedDict"),"英文字母或者下划线开头."),this.AddFunction("AdminDict","外键库维护",this.AdminDict),this.AddGroup("Component","组件"),this.TextBox2_NameNo("AthField","字段附件",this.FieldAth,"Ath","字段ID","附件名称","我的附件"),this.AddIcon("iconfont icon-attach","AthField")}AdminEnum(){const t="/@/WF/Comm/Search.vue?EnName=TS.FrmUI.SysEnumMain";return new y(l.GoToUrl,t)}AdminDict(){const t="/@/WF/Comm/Search.vue?EnName=TS.FrmUI.SFTable";return new y(l.GoToUrl,t)}GenerSorts(){return w(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,S,o,I,K){return w(this,null,function*(){const n=this.RequestVal("FrmID"),i=new v;yield i.Retrieve("FrmID",n,"Idx");const T=i.filter(e=>e.CtrlType===null||e.CtrlType===""),h=T.length===0?0:T[0].OID;if(t=="Blank")return yield O.Init_Blank(),this.InitDDL(I,o,"Blank");const E=(yield G.RunSQLReturnTable(M.SQLOfDtlFieldMaxIdx(n)))[0].Idx+1;if(t==="String"||t==="Int"||t==="Number"||t==="DT"||t==="Boolean"||t==="JE"||t==="Time"||t==="write"){const e=new D;if(e.MyPK=n+"_"+I,e.FK_MapData=n,e.GroupID=h,e.KeyOfEn=I,(yield e.IsExits())==!0)return new y(l.Error,"字段已经存在");e.KeyOfEn=I,e.Name=o;let a="";return t==="String"&&(e.MyDataType=m.AppString,e.MaxLen=50,a="TS.FrmUI.MapAttrString",e.SetPara("EnName",a),e.Idx=E,yield e.Insert()),t==="Int"&&(e.MyDataType=m.AppInt,a="TS.FrmUI.MapAttrNum",e.SetPara("EnName",a),e.Idx=E,yield e.Insert()),t==="Number"&&(e.MyDataType=m.AppFloat,a="TS.FrmUI.MapAttrNum",e.SetPara("EnName",a),e.Idx=E,yield e.Insert()),t==="JE"&&(e.MyDataType=m.AppMoney,a="TS.FrmUI.MapAttrNum",e.SetPara("EnName",a),e.Idx=E,yield e.Insert()),t==="DT"&&(e.MyDataType=m.AppDate,e.IsSupperText="0",a="TS.FrmUI.MapAttrDT",e.SetPara("EnName",a),e.Idx=E,yield e.Insert()),t==="Time"&&(e.MyDataType=m.AppDateTime,e.IsSupperText="1",a="TS.FrmUI.MapAttrDT",e.SetPara("EnName",a),e.Idx=E,yield e.Insert()),t==="Boolean"&&(e.MyDataType=m.AppBoolean,a="TS.FrmUI.MapAttrBoolean",e.SetPara("EnName",a),e.Idx=E,yield e.Insert()),t==="write"&&(e.MyDataType=m.AppString,e.UIContralType=d.HandWriting,a="TS.FrmUI.FrmHandWriting",e.SetPara("EnName",a),e.Idx=E,yield e.Insert()),new y(l.Message,"创建成功")}if(t==="Invoice"){const e=new D;if(e.GroupID=h,e.FK_MapData=n,e.SetPara("GroupName","Invoice"),e.DataType=m.AppString,e.UIIsEnable=0,e.Name="发票代码",e.KeyOfEn="InvoiceID",e.MyPK=n+"_"+e.KeyOfEn,yield e.IsExits())return new y(l.Message,"发票已经存在.");yield e.Insert(),e.Name="发票号码",e.KeyOfEn="InvoiceCode",e.MyPK=n+"_"+e.KeyOfEn,yield e.Insert(),e.Name="开票日期",e.KeyOfEn="InvoiceRelDT",e.DataType=m.AppDate,e.MyPK=n+"_"+e.KeyOfEn,yield e.Insert(),e.Name="发票类型",e.KeyOfEn="InvoiceTypeStr",e.DataType=m.AppString,e.MyPK=n+"_"+e.KeyOfEn,yield e.Insert(),e.Name="发票金额",e.KeyOfEn="InvoiceJE",e.DataType=m.AppMoney,e.MyPK=n+"_"+e.KeyOfEn,yield e.Insert(),e.Name="税额",e.KeyOfEn="InvoiceTaxJE",e.DataType=m.AppMoney,e.MyPK=n+"_"+e.KeyOfEn,yield e.Insert(),e.Name="税率",e.KeyOfEn="InvoiceTaxRate",e.DataType=m.AppMoney,e.MyPK=n+"_"+e.KeyOfEn,yield e.Insert(),e.Name="购买方名称",e.KeyOfEn="InvoiceBuyName",e.MaxLen=200,e.MyPK=n+"_"+e.KeyOfEn,e.UIWidth=200,yield e.Insert(),e.KeyOfEn="InvoiceBuyCode",e.Name="购买方ID",e.MaxLen=200,e.MyPK=n+"_"+e.KeyOfEn,e.UIWidth=150,yield e.Insert(),e.KeyOfEn="InvoiceBuyAddr",e.Name="购买方地址电话",e.DataType=m.AppString,e.MaxLen=200,e.UIWidth=200,yield e.Insert(),e.KeyOfEn="InvoiceBuyBankInfo",e.Name="购买方开户行及账号",e.DataType=m.AppString,e.MaxLen=200,e.UIWidth=200,yield e.Insert(),e.Name="销售方名称",e.KeyOfEn="InvoiceSaleName",e.MaxLen=200,e.MyPK=n+"_"+e.KeyOfEn,yield e.Insert(),e.KeyOfEn="InvoiceSaleCode",e.Name="销售方ID",e.MaxLen=150,e.UIWidth=150,e.MyPK=n+"_"+e.KeyOfEn,yield e.Insert(),e.KeyOfEn="InvoiceSaleAddr",e.Name="销售方地址电话",e.UIWidth=200,e.MaxLen=200,e.MyPK=n+"_"+e.KeyOfEn,yield e.Insert(),e.KeyOfEn="InvoiceSaleBankInfo",e.Name="销售方开户行及账号",e.MaxLen=200,e.UIWidth=200,e.MyPK=n+"_"+e.KeyOfEn,yield e.Insert();const a=new L(e.FK_MapData);yield a.Retrieve(),a.SetPara("IsInvoice",1),a.IsInsert=0,yield a.Update()}if(t==="SelectedEnum.FieldName"){if(!o)return;const a=this.RequestVal("tb1","SelectedEnum"),f=this.RequestVal("tb2","SelectedEnum"),r=new _(a);r.No=a,yield r.Retrieve(),r.EnumKey===""&&(r.EnumKey=o);const s=new D;if(s.MyPK=n+"_"+o,(yield s.IsExits())==!0)return new y(l.Error,"字段在表单已经存在"+o);s.GroupID=h,s.Name=f,s.KeyOfEn=o,s.FK_MapData=n,s.UIVisible=1,s.UIIsEnable=1,s.LGType=1,s.MyDataType=r.EnumType===0?2:1,s.SetPara("RBShowModel",3),s.UIContralType=1,s.UIBindKey=r.EnumKey,yield s.Insert();const A="/@/WF/Comm/En.vue?EnName=TS.FrmUI.MapAttrEnum&PKVal="+s.MyPK;return new y(l.GoToUrl,A)}if(t==="SelectedDict.Name"){if(!o)return;const a=this.RequestVal("tb1","SelectedDict"),f=new N(a);f.No=a,yield f.Retrieve();const r=new D;if(r.MyPK=n+"_"+o,(yield r.IsExits())==!0)return new y(l.Error,"字段在表单已经存在"+o);r.Name=f.Name,r.KeyOfEn=o,r.FK_MapData=n,r.GroupID=h,r.UIVisible=1,r.UIIsEnable=1,r.LGType=0,r.MyDataType=1,r.UIContralType=d.DDL,r.UIBindKey=f.No,r.SetPara("SrcType",f.DBSrcType),yield r.Insert();const s=r.MyPK;r.UIVisible=0,r.UIContralType=d.TB,r.MyPK=n+"_"+o+"T",r.KeyOfEn=o+"T",r.Name=r.Name+"T",yield r.Insert();const A="/@/WF/Comm/En.vue?EnName=TS.FrmUI.MapAttrSFSQL&PKVal="+s;return new y(l.GoToUrl,A)}if(t==="AthField"){const e=new D;if(e.FK_MapData=n,e.MyPK=n+"_"+I,e.KeyOfEn=I,e.Name=o,e.GroupID=h,(yield e.IsExits())==!0)return new y(l.Error,"附件ID["+e.MyPK+"]已存在");const a=new B;a.MyPK=e.MyPK,(yield e.IsExits())==!1&&(a.FK_MapData=n,a.NoOfObj=I,a.Name=o,a.IsDtlAth=1,a.SetPara("IsDtlAth","1"),e.UploadType=1,yield a.Insert()),e.UIContralType=d.AthShow,e.SetPara("EnName","TS.FrmUI.FrmAttachmentExt"),yield e.Insert()}})}InitDDL(t,S,o){return w(this,null,function*(){const I=this.RequestVal("FrmID");if(!t)return;const n=new N(o);n.No=o,yield n.Retrieve();const i=new D;if(i.MyPK=I+"_"+t,(yield i.IsExits())==!0)return new y(l.Error,"字段在表单已经存在"+t);S?i.Name=S:i.Name=n.Name,i.KeyOfEn=t,i.FK_MapData=I,i.UIVisible=1,i.UIIsEnable=1,i.LGType=0,i.MyDataType=1,i.UIContralType=d.DDL,i.UIBindKey=n.No,i.SetPara("SrcType",n.DBSrcType),yield i.Insert(),i.UIVisible=0,i.MyPK=I+"_"+t+"T",i.KeyOfEn=t+"T",i.UIContralType=d.TB,yield i.Insert();const T="/@/WF/Comm/En.vue?EnName=TS.FrmUI.MapAttrSFSQL&PKVal="+i.MyPK;return new y(l.GoToUrl,T)})}}export{me as GPN_DtlField};
