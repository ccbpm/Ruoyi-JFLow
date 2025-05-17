var p=Object.defineProperty;var r=(e,t,s)=>t in e?p(e,t,{enumerable:!0,configurable:!0,writable:!0,value:s}):e[t]=s;var a=(e,t,s)=>r(e,typeof t!="symbol"?t+"":t,s);import{F as o}from"./entry/index-B5R3Coa4-1746862693206.js";import{MapDtl as c}from"./MapDtl-B_Ep8ewM.js";import{PageBaseGroupEdit as h}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";class g extends h{constructor(){super("GPE_IsBatchUpdate");a(this,"Desc0",`
  #### 帮助
  - 不启用批量编辑

  `);a(this,"Desc1",`
  #### 帮助
  - 选择多个可以编辑的字段，批量处理的时候显示选择的字段
  ...`);this.PageTitle="批量编辑规则"}Init(){this.entity=new c,this.KeyOfEn="IsBatchUpdate",this.AddGroup("A","批量编辑规则"),this.Blank("0","不启用",this.Desc0),this.SelectItemsByList("1","启用",this.Desc1,!0,o.SQLOfIsBatchUpdateAttrs(this.RefPKVal),"IsBatchUpdateAttrs")}BtnClick(s,i,m){}AfterSave(s,i){}}export{g as GPE_IsBatchUpdate};
