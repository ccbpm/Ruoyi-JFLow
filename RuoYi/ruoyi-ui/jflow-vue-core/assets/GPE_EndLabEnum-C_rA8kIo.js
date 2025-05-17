var c=Object.defineProperty;var l=(r,i,t)=>i in r?c(r,i,{enumerable:!0,configurable:!0,writable:!0,value:t}):r[i]=t;var p=(r,i,t)=>l(r,typeof i!="symbol"?i+"":i,t);var m=(r,i,t)=>new Promise((o,a)=>{var h=e=>{try{n(t.next(e))}catch(s){a(s)}},d=e=>{try{n(t.throw(e))}catch(s){a(s)}},n=e=>e.done?o(e.value):Promise.resolve(e.value).then(h,d);n((t=t.apply(r,i)).next())});import{M as u,a as E}from"./MapExt-DVovzpWn.js";import{PageBaseGroupEdit as x}from"./PageBaseGroupEdit-BXdNWKIo.js";import{D as f}from"./entry/index-B5R3Coa4-1746862693206.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./Help-D0bDMZWg.js";class L extends x{constructor(){super("GPE_EndLabEnum");p(this,"Desc1",`
  #### 后缀提示
  - 人民币,美元,欧元
  - 吨,公斤,千克,克
  - 
  #### 配置项存储
  - Sys_MapExt
  - 
  #### 数据存储
  - AtPara里面.
  - xxx
   `);this.PageTitle="后置枚举选项"}Init(){return m(this,null,function*(){this.entity=new u,this.KeyOfEn=E.DoWay,yield this.entity.InitDataForMapAttr("EndLabEnum",this.GetRequestVal("PKVal")),this.AddGroup("A","后置枚举选项"),this.Blank("0","不启用",this.Desc1),this.SingleTB("1","启用",E.Doc,this.Desc1,"人民币,美元,欧元",f.AppString)})}AfterSave(t,o){if(t==o)throw new Error("Method not implemented.")}BtnClick(t,o,a){if(t==o||t===a)throw new Error("Method not implemented.")}}export{L as GPE_EndLabEnum};
