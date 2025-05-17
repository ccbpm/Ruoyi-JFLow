var p=Object.defineProperty;var n=(i,t,r)=>t in i?p(i,t,{enumerable:!0,configurable:!0,writable:!0,value:r}):i[t]=r;var e=(i,t,r)=>n(i,typeof t!="symbol"?t+"":t,r);import{FrmTrack as o}from"./FrmTrack-Ct-No0Nq.js";import{PageBaseGroupEdit as c}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./Help-D0bDMZWg.js";class g extends c{constructor(){super("GPE_FrmTrack");e(this,"Desc0",` 
  #### 帮助
  - 

  #### 流程图
  - 

  
  `);e(this,"Desc1",` 
  #### 帮助
  - 只读状态下，仅仅可以查看工序，不能对工序进行编排.
  `);e(this,"Desc2",` 
  #### 帮助
  - 
....`);this.PageTitle="轨迹组件"}Init(){this.entity=new o,this.KeyOfEn="FrmTrackSta",this.AddGroup("A","组件状态"),this.Blank("0","禁用",this.Desc0),this.AddEntity("1","显示轨迹图",new o,this.Desc2),this.AddEntity("2","显示轨迹表",new o,this.Desc2)}BtnClick(r,m,s){if(r==m||r===s)throw new Error("Method not implemented.")}AfterSave(r,m){}}export{g as GPE_FrmTrack};
