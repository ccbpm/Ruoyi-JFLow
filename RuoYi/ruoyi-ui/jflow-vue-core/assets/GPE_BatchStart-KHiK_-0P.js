var n=Object.defineProperty;var a=(r,e,t)=>e in r?n(r,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):r[e]=t;var o=(r,e,t)=>a(r,typeof e!="symbol"?e+"":e,t);import{D as h}from"./entry/index-B5R3Coa4-1746862693206.js";import{PageBaseGroupEdit as p}from"./PageBaseGroupEdit-BXdNWKIo.js";import{Flow as m}from"./Flow-BIaTOSmj.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";class S extends p{constructor(){super("GPE_BatchStart");o(this,"Desc0",` 
  #### 帮助
   - 不启动：不启动流程的批量发起。
   - 启用批量发起:一次批量发起多个流程。
 
     
    `);o(this,"Desc1",` 
  #### 帮助
   
    
    `);this.PageTitle="批量发起"}Init(){this.entity=new m,this.KeyOfEn="BatStartRole",this.AddGroup("A","批量发起"),this.Blank("0","不启用",this.Desc0),this.SingleTB("1","启用批量发起","BatchListCount",this.Desc1,"一次发起多少条记录",h.AppString)}AfterSave(t,i){if(t==i)throw new Error("Method not implemented.")}BtnClick(t,i,s){if(t==i||t===s)throw new Error("Method not implemented.")}}export{S as GPE_BatchStart};
