var r=Object.defineProperty;var a=(t,e,i)=>e in t?r(t,e,{enumerable:!0,configurable:!0,writable:!0,value:i}):t[e]=i;var o=(t,e,i)=>a(t,typeof e!="symbol"?e+"":e,i);import{MapDtl as p,MapDtlAttr as s}from"./MapDtl-B_Ep8ewM.js";import{PageBaseGroupEdit as h}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";class I extends h{constructor(){super("GPE_MobileShowCols");o(this,"Desc0",`
  #### 帮助
  - 多个字段用逗号分开
  - 比如: File1,FIle2

  `);o(this,"Desc1",`
  #### 帮助
  - 多个字段用逗号分开
  - 比如: File1,FIle2

  ...`);this.PageTitle="字段展现模式"}Init(){this.entity=new p,this.KeyOfEn=s.EditModel,this.AddGroup("A","展示模式"),this.Blank("0","新页面展示",this.Desc0),this.SingleTextArea("1","列表展示",s.ShowCols,"多个字段用逗号分开",this.Desc1)}BtnClick(i,l,n){}AfterSave(i,l){}}export{I as GPE_MobileShowCols};
