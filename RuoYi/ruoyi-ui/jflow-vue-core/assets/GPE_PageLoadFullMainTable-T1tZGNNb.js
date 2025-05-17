var E=Object.defineProperty;var d=(a,e,t)=>e in a?E(a,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):a[e]=t;var l=(a,e,t)=>d(a,typeof e!="symbol"?e+"":e,t);var n=(a,e,t)=>new Promise((p,o)=>{var c=i=>{try{r(t.next(i))}catch(s){o(s)}},h=i=>{try{r(t.throw(i))}catch(s){o(s)}},r=i=>i.done?p(i.value):Promise.resolve(i.value).then(c,h);r((t=t.apply(a,e)).next())});import{F as m}from"./entry/index-B5R3Coa4-1746862693206.js";import{M as u,a as D}from"./MapExt-DVovzpWn.js";import{PageLoadFull as F}from"./PageLoadFull-rXMpPjS-.js";import{PageBaseGroupEdit as g}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./Help-D0bDMZWg.js";class x extends g{constructor(){super("GPE_PageLoadFullMainTable");l(this,"Desc0",`
  #### 帮助
   - 定义:当表单加载的时，给当前表单一些数据填充到控件上，我们称为装载填充.
   - 不设置(默认)：不对主表数据进行填充。
   - 一个表单的控件分为主表，从表、主表的下拉框框数据。
   #### 应用场景
   - 比如填写申请单的时候，需要把自己的地址、邮件、电话信息设置的表单上。
   - 格式: SELECT Email, Addr, Tel FROM MyTable WHERE EmpNo='@WebUer.No' 
   - 返回一行一列的数据，列名与字段名对应的上.
   #### 其他说明
   - 在2022年以前的版本，在一个表单属性上设置装载填充，填充主表数据，从表数据，下拉框数据。
   - 在vue3以后的版本，这些装载填充功能，分配到各个控件属性中了。
  `);l(this,"Desc1",`
  #### 帮助
   - 请点击【编辑】按钮执行编辑填充主表数据信息.
   - 填写格式，点击字段icon帮助.
  `);this.PageTitle="装载填充"}Init(){return n(this,null,function*(){this.entity=new u,this.KeyOfEn=D.DoWay,yield this.entity.InitDataForMapAttr("PageLoadFullMainTable",this.GetRequestVal("PKVal")),this.AddGroup("A","装载填充"),this.Blank("None","不设置",this.Desc0),this.AddEntity("Self","按照SQL填充",new F,this.Desc1),this.SelectItemsByGroupList("SFTable","按照查询填充",this.HelpUn,!1,m.srcDBSrc,m.srcDBSFSearch,"Doc")})}BtnClick(t,p,o){}AfterSave(t){}}export{x as GPE_PageLoadFullMainTable};
