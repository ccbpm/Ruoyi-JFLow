var f=Object.defineProperty;var A=(e,r,t)=>r in e?f(e,r,{enumerable:!0,configurable:!0,writable:!0,value:t}):e[r]=t;var n=(e,r,t)=>A(e,typeof r!="symbol"?r+"":r,t);var u=(e,r,t)=>new Promise((i,s)=>{var d=o=>{try{l(t.next(o))}catch(p){s(p)}},m=o=>{try{l(t.throw(o))}catch(p){s(p)}},l=o=>o.done?i(o.value):Promise.resolve(o.value).then(d,m);l((t=t.apply(e,r)).next())});import{l as E,U as D,f as c,k as M}from"./entry/index-B5R3Coa4-1746862693206.js";import{a}from"./MapExt-DVovzpWn.js";import{FullDataDDLs as y}from"./FullDataDDL-CoQAq7kS.js";import{GPE_FullDataBody as T}from"./GPE_FullDataBody-BjO_gJ7r.js";import{FullDtls as x}from"./FullDtl-BqGYGrrg.js";import{FullAths as F}from"./FullAth-DxLB_tXD.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./DBAccess-CZ0wdWXU.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FullBodySFTable-DSyxKnpd.js";import"./FullBodySelf-DzwgEgdn.js";import"./GloComm-B1xAfTWw.js";import"./FrmTrack-Ct-No0Nq.js";import"./PageBaseGroupEdit-BXdNWKIo.js";import"./Help-D0bDMZWg.js";class P extends E{constructor(t){super("TS.MapExt.FullData");n(this,"DescSrc",`
  #### 定义
  - 用户对控件进行操作的时候，比如: pop弹窗选择一个值, 是一个单据编号，人员编号，根据这个值获取数据填充其它数据控件的行为，我们称为落值填充.
  - 数据来源: Pop弹窗返回值、文本框自动完成、下拉框联动.
  #### 应用场景
  - 我们做一个档案系统, 在文本框输入一个人员编号,这里可以使用文本框自动完成.
  - 输入完成后，根据文本框的人员编号，获取该人员的地址，电话，邮件(主表信息), 他拥有的角色(主表下拉框信息) 以及教育经历(从表数据)
  - 填充到表单中去，完成用户的操作.
  #### 可填充的数据
  - 主表数据,比如:电话，邮件，地址.
  - 从表数据,比如:教育经历.
  - 下拉框内容,比如:人员拥有的角色集合(用下拉框展现)
  #### 配置说明
   - 填一个数据源返回的数据是一行多列，列的名字与主表字段对应，就会实现数据的自动填充.
   - 数据源必须有 @Key 参数，该值是传递来的数据.
   - 比如：SELECT Tel as DianHua, Email, Addr FROM Port_Emp WHERE No='@Key'
   - 如果配置的是url, 配置内容为: http://118.11.1.1/XXX/XX.do    
   - 系统解析为:http://118.11.1.1/XXX/XX.do?Key=zhangsan
   - 如果是函数: 请输入函数名称, 比如: GetEmpInfo()
  `);t&&(this.MyPK=t)}get HisUAC(){const t=new D;return t.IsDelete=!0,t.IsUpdate=!0,t.IsInsert=!0,t}get EnMap(){const t=new c("Sys_MapExt","数据填充");return t.AddGroupAttr("填充主表"),t.AddMyPK(),t.AddTBString(a.FK_MapData,null,"表单ID",!0,!0,0,10,50,!1),t.AddTBString(a.ExtModel,null,"ExtModel",!0,!0,0,10,50,!1),t.AddTBString(a.ExtType,null,"ExtType",!0,!0,0,10,50,!1),t.AddTBString(a.AttrOfOper,null,"AttrOfOper",!0,!0,0,10,50,!1),t.AddTBString(a.AttrOfOper,null,"AttrOfOper",!0,!0,0,10,50,!1),t.AddBoolean("IsLoadFull",!0,"加载页面时是否自动填充",!0,!0),t.AddRM_GPE(new T,"icon-drop"),t.AddRM_DtlSearch("填充从表",new x,a.RefPKVal,"","","","icon-drop",!1,"&ExtType=FullDataDtl"),t.AddRM_DtlSearch("填充下拉框",new y,a.RefPKVal,"","","","icon-drop",!1,"&ExtType=FullDataDDL"),t.AddRM_DtlSearch("填充附件",new F,a.RefPKVal,"","","","icon-drop",!1,"&ExtType=FullDataAth"),t.AddTBAtParas(4e3),this._enMap=t,this._enMap}beforeInsert(){return u(this,null,function*(){return Promise.resolve(!0)})}}class v extends M{get GetNewEntity(){return new P}constructor(){super()}}export{P as FullData,v as FullDatas};
