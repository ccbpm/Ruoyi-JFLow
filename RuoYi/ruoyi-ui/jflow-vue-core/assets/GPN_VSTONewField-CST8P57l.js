var B=Object.defineProperty;var x=(D,c,t)=>c in D?B(D,c,{enumerable:!0,configurable:!0,writable:!0,value:t}):D[c]=t;var l=(D,c,t)=>x(D,typeof c!="symbol"?c+"":c,t);var d=(D,c,t)=>new Promise((I,r)=>{var m=n=>{try{o(t.next(n))}catch(y){r(y)}},N=n=>{try{o(t.throw(n))}catch(y){r(y)}},o=n=>n.done?I(n.value):Promise.resolve(n.value).then(m,N);o((t=t.apply(D,c)).next())});import{GloComm as E}from"./GloComm-B1xAfTWw.js";import{GPN_NewDDL as M}from"./GPN_NewDDL-CFlFuyrV.js";import{MapAttr as f}from"./MapAttr-DcWjEeWW.js";import{MapDtls as P,MapDtl as _}from"./MapDtl-B_Ep8ewM.js";import{SFTables as G,SFTable as A}from"./SFTable-BpxUt1jb.js";import{SysEnumMain as K}from"./SysEnumMain-NEBgQX-D.js";import{P as b,F,G as p,m as u,D as S,aU as h,B as L}from"./entry/index-B5R3Coa4-1746862693206.js";import{GroupFields as H}from"./GroupField-lMJeJtcW.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./Events-D9tOL1Ad.js";import"./SysEnum-DmKE2Ig7.js";import"./EntityOID-C1xznxai.js";import"./MapData-D5zymw8O.js";import"./EnumLab-CsLi93T0.js";class ot extends b{constructor(){super("GPN_VSTONewField");l(this,"HelpString",`
  #### 帮助
   - 文本类型的字段.
   - 比如:姓名、编号、地址、电话、邮件.
  `);l(this,"HelpInt",`
  #### 帮助
   - 整数类型数据.
  `);l(this,"HelpNumber",`
  #### 帮助
  - 数值类型数据.
  `);l(this,"HelpEnumDDL",`
    #### 帮助
    - 枚举类型数据: 枚举值,枚举标签; 
    `);l(this,"HelpEnumNew",`
        #### 帮助
        - 填写格式: 枚举值,枚举标签; 
        - 例如: ty,团员;dy=党员;qz,群众;
        - 系统解析为: ty是团员, dy是党员, qz是群众.
      
        #### 数据存储.
        - string类型的枚举也称为标记枚举,字母存储一个列,标签存储一个列.
        - 在表单里字段是abc,那系统就会自动创建一个影子字段 abcT.
        - abc字段存储的是标记, abcT存储的是标签.
        `);l(this,"Blank",`
  #### 帮助
  - 空白的字段: 用于加载表单的时候，他的数据源是通过，由参数的字典获得的.
  - 比如：表单里由，片区、省份、地市、区县四个下拉框字段. 当表单加载的时候，在没有确定片区其他的三个字段都无法确定值。
  - 省份、地区、区县就需要绑定空白数据源外键.
  - 使用级联关系，把其他的字段数据实现数据级联查询.
  `);l(this,"HelpBoolean",`
    #### 帮助
    - 填写格式:开关类型数据.
    `);l(this,"HelpJE",`
  #### 帮助
  - 金额类型数据; 
  `);l(this,"HelpTime",`
  #### 帮助
  - 时间类型数据.
  `);l(this,"HelpDT",`
  #### 帮助
  - 日期类型数据; 
  `);l(this,"FieldAth",`
  #### 帮助
  - 字段附件，附件以字段名的形式在页面中显示; 
  #### 图例
  ![输入图片说明](./resource/WF/Admin/FrmLogic/SFTable/Img/Ath1.png "屏幕截图.png") 
  #### 数据存储
  - 附件的默认保存在web服务器上。
  - 可以保存到ftp服务器上, ftp的服务器的连接配置在全局的配置文件中。
  - 如果需要保存到数据库，就需要考虑数据库的存储与备份的问题，文件将会存储在 Sys_FrmAttachmentDB 表中。
 
    `)}Init(){this.PageTitle="新建从表字段",this.ForEntityClassID="",this.AddGroup("Nurel","基本字段"),this.TextBox2_NameNo("String","文本字段",this.HelpString,"","字段名","中文名",""),this.AddIcon("iconfont icon-fuwenbenkuang","String"),this.TextBox2_NameNo("Int","整数",this.HelpInt,"","字段名","中文名",""),this.AddIcon("iconfont icon-zhengshu","Int"),this.TextBox2_NameNo("Number","数值",this.HelpNumber,"","字段名","中文名",""),this.AddIcon("iconfont icon-ziduanleixing-zhengshu","Number"),this.TextBox2_NameNo("JE","金额",this.HelpJE,"","字段名","中文名",""),this.AddIcon("iconfont icon-yifabupiaoju-renminbi-xi","JE"),this.TextBox2_NameNo("Time","日期时间",this.HelpTime,"","字段名","中文名",""),this.AddIcon("iconfont icon-shijian1","Time"),this.TextBox2_NameNo("DT","日期",this.HelpDT,"","字段名","中文名",""),this.AddIcon("iconfont icon-riqiqishu","DT"),this.TextBox2_NameNo("Boolean","开关",this.HelpBoolean,"","字段名","中文名",""),this.AddIcon("iconfont icon-fuxuankuang","Boolean"),this.AddGroup("Enum","枚举字段");const t=F.SQLEnumMain;this.SelectItemsByList("SelectedEnum","新建枚举字段",M.SelectedEnum,!1,t),this.TextBox1_Name("SelectedEnum.FieldName","输入字段ID",M.SelectedEnum_FieldName,"字段ID",()=>this.RequestVal("tb1","SelectedEnum"),"英文字母或者下划线开头."),this.AddFunction("AdminEnum","枚举库维护",this.AdminEnum),this.AddGroup("FK","外键字段"),this.SelectItemsByList("SelectedDict","新建外键字段",this.HelpEnumDDL,!1,F.SQLSFTable),this.TextBox1_Name("SelectedDict.Name","输入字段ID",this.HelpEnumDDL,"字段ID",()=>this.RequestVal("tb1","SelectedDict"),"英文字母或者下划线开头."),this.AddFunction("AdminDict","外键库维护",this.AdminDict),this.AddGroup("Component","组件"),this.TextBox2_NameNo("Dtl","从表",this.FieldAth,"Dtl","从表ID","从表名称","我的从表"),this.AddIcon("iconfont icon-attach","Dtl")}AdminEnum(){const t=E.UrlSearch("TS.FrmUI.SysEnumMain");return new p(u.GoToUrl,t)}AdminDict(){const t=E.UrlSearch("TS.FrmUI.SFTable");return new p(u.GoToUrl,t)}GenerSorts(){return d(this,null,function*(){const t=this.RequestVal("FrmID"),I=new P;yield I.Retrieve("FK_MapData",t);const r=[{Name:"主表:"+t,No:t}];for(const m of I)r.push({Name:m.Name+":"+m.No,No:m.No});return Promise.resolve(r)})}Save_TextBox_X(t,I,r,m,N){return d(this,null,function*(){const o=I,n=new H;yield n.Retrieve("FrmID",o,"Idx");const y=n.filter(e=>e.CtrlType===null||e.CtrlType===""),w=y.length===0?0:y[0].OID;if(t=="Blank")return yield G.Init_Blank(),this.InitDDL(m,r,"Blank");if(t==="String"||t==="Int"||t==="Number"||t==="DT"||t==="Boolean"||t==="JE"||t==="Time"){const e=new f;if(e.MyPK=o+"_"+m,e.FK_MapData=o,e.GroupID=w,e.KeyOfEn=m,(yield e.IsExits())==!0)return new p(u.Error,"字段已经存在");e.KeyOfEn=m,e.Name=r;let a="";return t==="String"&&(e.MyDataType=S.AppString,e.MaxLen=50,a="TS.FrmUI.MapAttrString",e.SetPara("EnName",a),yield e.Insert()),t==="Int"&&(e.MyDataType=S.AppInt,a="TS.FrmUI.MapAttrNum",e.SetPara("EnName",a),yield e.Insert()),t==="Number"&&(e.MyDataType=S.AppFloat,a="TS.FrmUI.MapAttrNum",e.SetPara("EnName",a),yield e.Insert()),t==="JE"&&(e.MyDataType=S.AppMoney,a="TS.FrmUI.MapAttrNum",e.SetPara("EnName",a),yield e.Insert()),t==="DT"&&(e.MyDataType=S.AppDate,e.IsSupperText="0",a="TS.FrmUI.MapAttrDT",e.SetPara("EnName",a),yield e.Insert()),t==="Time"&&(e.MyDataType=S.AppDateTime,e.IsSupperText="1",a="TS.FrmUI.MapAttrDT",e.SetPara("EnName",a),yield e.Insert()),t==="Boolean"&&(e.MyDataType=S.AppBoolean,a="TS.FrmUI.MapAttrBoolean",e.SetPara("EnName",a),yield e.Insert()),new p(u.Message,"创建成功")}if(t==="SelectedEnum.FieldName"){if(!r)return;const a=this.RequestVal("tb1","SelectedEnum"),T=this.RequestVal("tb2","SelectedEnum"),i=new K(a);i.No=a,yield i.Retrieve(),i.EnumKey===""&&(i.EnumKey=r);const s=new f;if(s.MyPK=o+"_"+r,(yield s.IsExits())==!0)return new p(u.Error,"字段在表单已经存在"+r);s.GroupID=w,s.Name=T,s.KeyOfEn=r,s.FK_MapData=o,s.UIVisible=1,s.UIIsEnable=1,s.LGType=1,s.MyDataType=i.EnumType===0?2:1,s.SetPara("RBShowModel",3),s.UIContralType=1,s.UIBindKey=i.EnumKey,yield s.Insert();const U=E.UrlEn("TS.FrmUI.MapAttrEnum",s.MyPK);return new p(u.GoToUrl,U)}if(t==="SelectedDict.Name"){if(!r)return;const a=this.RequestVal("tb1","SelectedDict"),T=new A(a);T.No=a,yield T.Retrieve();const i=new f;if(i.MyPK=o+"_"+r,(yield i.IsExits())==!0)return new p(u.Error,"字段在表单已经存在"+r);i.Name=T.Name,i.KeyOfEn=r,i.FK_MapData=o,i.GroupID=w,i.UIVisible=1,i.UIIsEnable=1,i.LGType=0,i.MyDataType=1,i.UIContralType=h.DDL,i.UIBindKey=T.No,i.SetPara("SrcType",T.DBSrcType),yield i.Insert(),i.UIVisible=0,i.UIContralType=h.TB,i.MyPK=o+"_"+r+"T",i.KeyOfEn=r+"T",i.Name=i.Name+"T",yield i.Insert();const s=E.UrlEn("TS.FrmUI.MapAttrSFSQL",i.MyPK);return new p(u.GoToUrl,s)}if(t==="Dtl"){const e=new _;if(e.No=o+m,(yield e.IsExits())==!0)return new p(u.Error,"从表ID["+e.No+"]已存在");const a=new L("BP.Sys.MapDtl");a.No=e.No,a.Name=r,a.FK_MapData=o,yield a.Insert(),a.DoMethodReturnString("IntMapAttrs");const T=E.UrlEn("TS.Frm.MapDtlExt",e.No);return new p(u.GoToUrl,T)}})}InitDDL(t,I,r){return d(this,null,function*(){const m=this.RequestVal("FrmID");if(!t)return;const o=new A(r);o.No=r,yield o.Retrieve();const n=new f;if(n.MyPK=m+"_"+t,(yield n.IsExits())==!0)return new p(u.Error,"字段在表单已经存在"+t);I?n.Name=I:n.Name=o.Name,n.KeyOfEn=t,n.FK_MapData=m,n.UIVisible=1,n.UIIsEnable=1,n.LGType=0,n.MyDataType=1,n.UIContralType=h.DDL,n.UIBindKey=o.No,n.SetPara("SrcType",o.DBSrcType),yield n.Insert(),n.UIVisible=0,n.MyPK=m+"_"+t+"T",n.KeyOfEn=t+"T",n.UIContralType=h.TB,yield n.Insert();const y="/@/WF/Comm/En.vue?EnName=TS.FrmUI.MapAttrSFSQL&PKVal="+n.MyPK;return new p(u.GoToUrl,y)})}}export{ot as GPN_VSTONewField};
