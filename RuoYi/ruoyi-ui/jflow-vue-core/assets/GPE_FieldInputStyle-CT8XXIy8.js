var h=Object.defineProperty;var u=(i,e,t)=>e in i?h(i,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):i[e]=t;var l=(i,e,t)=>u(i,typeof e!="symbol"?e+"":e,t);var a=(i,e,t)=>new Promise((n,o)=>{var m=r=>{try{p(t.next(r))}catch(s){o(s)}},d=r=>{try{p(t.throw(r))}catch(s){o(s)}},p=r=>r.done?n(r.value):Promise.resolve(r.value).then(m,d);p((t=t.apply(i,e)).next())});import{M as c,a as y}from"./MapExt-DVovzpWn.js";import{PageBaseGroupEdit as F}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./Help-D0bDMZWg.js";class G extends F{constructor(){super("GPE_FieldInputStyle");l(this,"Desc0",`
  #### 帮助
   - 数值输入样式:提供两种种输入样式，上下按钮增减和左右加减。
   - 一般用于数值类型的字段,比如:请假天数，采购数量等的处理字段.
   - 此功能金额类型字段除外。
  #### 运行图例
  - 上下按钮增减：光标移到当前字段时，显示上下按钮增减
   ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/FieldInputStyle/Img/FieldInputStyle1.png "屏幕截图.png") 
  - 左右增减
   ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/FieldInputStyle/Img/FieldInputStyle2.png "屏幕截图.png") 
  

  `);this.PageTitle="数值输入样式"}Init(){return a(this,null,function*(){this.entity=new c,this.KeyOfEn=y.DoWay,yield this.entity.InitDataForMapAttr("FieldInputStyle",this.GetRequestVal("PKVal")),this.AddGroup("A","数值输入样式"),this.Blank("0","手动输入",this.Desc0),this.Blank("1","左右增减",this.Desc0),this.Blank("2","上下按钮增减",this.Desc0)})}AfterSave(t,n){if(t==n)throw new Error("Method not implemented.")}BtnClick(t,n,o){if(t==n||t===o)throw new Error("Method not implemented.")}}export{G as GPE_FieldInputStyle};
