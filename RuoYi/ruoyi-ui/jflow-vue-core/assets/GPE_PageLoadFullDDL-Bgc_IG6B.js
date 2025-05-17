var h=Object.defineProperty;var E=(e,o,t)=>o in e?h(e,o,{enumerable:!0,configurable:!0,writable:!0,value:t}):e[o]=t;var s=(e,o,t)=>E(e,typeof o!="symbol"?o+"":o,t);var l=(e,o,t)=>new Promise((n,a)=>{var r=i=>{try{m(t.next(i))}catch(p){a(p)}},N=i=>{try{m(t.throw(i))}catch(p){a(p)}},m=i=>i.done?n(i.value):Promise.resolve(i.value).then(r,N);m((t=t.apply(e,o)).next())});import{M as c,a as D}from"./MapExt-DVovzpWn.js";import{MENoNameP0 as P}from"./MENoNameP0-Dk7hOaBz.js";import{GloComm as S}from"./GloComm-B1xAfTWw.js";import{PageBaseGroupEdit as u}from"./PageBaseGroupEdit-BXdNWKIo.js";import{G as A,m as g}from"./entry/index-B5R3Coa4-1746862693206.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./Help-D0bDMZWg.js";class b extends u{constructor(){super("GPE_PageLoadFullDDL");s(this,"Desc0",`
 
  #### 帮助
   - 不设置(默认)：不对主表数据进行填充。
   - 设置下拉框填充：返回一个数据源用来填充下拉框，该行里必须有No,Name两个字段 No就是下拉框的值，Name就是下拉框的标签。
  
  `);s(this,"Desc1",`
  #### 帮助
   - 填充下拉框的SQL。
   - 返回一个数据源用来填充下拉框，该行里必须有No,Name两个字段 No就是下拉框的值，Name就是下拉框的标签。
   - 实例(选择的人员的角色下拉框)： SELECT B.FK_Station AS No, A.Name FROM Port_Station A, Port_DeptEmpStation B WHERE B.FK_Emp='@Key' AND B.Station=A.No
   - @Key 系统约定的选择的编号。
  `);this.PageTitle="装载填充"}Init(){return l(this,null,function*(){this.entity=new c,this.KeyOfEn=D.DoWay,this.Btns=[{pageNo:"2",list:["字典维护"]}],yield this.entity.InitDataForMapAttr("PageLoadFullDDL",this.GetRequestVal("PKVal"),"0"),this.AddGroup("A","装载填充"),this.Blank("0","不设置(默认)",this.Desc0),this.SingleTBSQL("1","自定义填充",D.Doc,this.Desc1),this.AddEntity("2","绑定字典填充",new P,this.Desc1)})}BtnClick(t,n,a){if(a=="字典维护"){const r=S.UrlSearch("TS.FrmUI.SFTable");return new A(g.OpenUrlByDrawer75,r)}}AfterSave(t){}}export{b as GPE_PageLoadFullDDL};
