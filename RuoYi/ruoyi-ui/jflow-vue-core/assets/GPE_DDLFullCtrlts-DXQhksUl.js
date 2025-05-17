var u=Object.defineProperty;var c=(i,r,t)=>r in i?u(i,r,{enumerable:!0,configurable:!0,writable:!0,value:t}):i[r]=t;var a=(i,r,t)=>c(i,typeof r!="symbol"?r+"":r,t);var D=(i,r,t)=>new Promise((o,e)=>{var p=l=>{try{m(t.next(l))}catch(n){e(n)}},s=l=>{try{m(t.throw(l))}catch(n){e(n)}},m=l=>l.done?o(l.value):Promise.resolve(l.value).then(p,s);m((t=t.apply(i,r)).next())});import{M as g,a as F}from"./MapExt-DVovzpWn.js";import{GloComm as h}from"./GloComm-B1xAfTWw.js";import{PageBaseGroupEdit as E}from"./PageBaseGroupEdit-BXdNWKIo.js";import{G as L,m as y}from"./entry/index-B5R3Coa4-1746862693206.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./Help-D0bDMZWg.js";class V extends E{constructor(){super("GPE_DDLFullCtrlts");a(this,"Desc0",` 
  #### 帮助
  - 不填充：对控件没有填充要求。
  - 启用填充控件: 当选项发生变化后，同表单的其它控件的数据需要变化，我们把这样的行为称为其他控件填充。
  - 比如：下拉框的人员变化后，其它的字段就跟着变化。
  - 如下图，当人员选择变化时，Email自动变化。
  #### 效果图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/DDLFullCtrl/Img/DDLFullCtrl.png "屏幕截图.png") 
   `);a(this,"Desc1",` 
  #### 帮助
  - 定义: 当选项发生变化后，同表单的其它控件的数据需要变化，我们把这样的行为称为其他控件填充。
  - 应用场景
  - 在做一个选择操作员的时候，需要把操作员的电话，邮件填充到主表其他字段里面，需要把操作员的角色显示到下拉框里面。
  - 人员是一个下拉框，人员变动的时候，其他的控件也在跟着变动。
  - 填写数据源。
  #### 配置图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/DDLFullCtrl/Img/DDLFullCtrlSetting.png "屏幕截图.png") 
  #### 运行图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/DDLFullCtrl/Img/DDLFullCtrl.png "屏幕截图.png") 
  设置下拉框在值变化后，填充其他控件与从表 `);this.PageTitle="下拉框填充"}Init(){return D(this,null,function*(){this.entity=new g,this.KeyOfEn=F.DoWay,this.Btns=[{pageNo:"1",list:["填充"]}],this.entity=yield this.entity.InitDataForMapAttr("DDLFullCtrl",this.GetRequestVal("PKVal")),this.AddGroup("A","下拉框填充"),this.Blank("0","不填充",this.Desc0),this.Blank("1","启用填充",this.Desc0)})}BtnClick(t,o,e){return D(this,null,function*(){var p;if(e==="落值填充"||e==="填充"){const s=h.UrlEn("TS.MapExt.FullData",(p=this.entity)==null?void 0:p.MyPK);return new L(y.OpenUrlByDrawer75,s)}if(t==o||t===e)throw new Error("Method not implemented.")})}AfterSave(t,o){}}export{V as GPE_DDLFullCtrlts};
