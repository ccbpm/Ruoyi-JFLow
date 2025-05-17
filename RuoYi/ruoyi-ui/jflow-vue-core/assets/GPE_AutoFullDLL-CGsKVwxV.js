var F=Object.defineProperty;var E=(e,r,t)=>r in e?F(e,r,{enumerable:!0,configurable:!0,writable:!0,value:t}):e[r]=t;var a=(e,r,t)=>E(e,typeof r!="symbol"?r+"":r,t);var u=(e,r,t)=>new Promise((p,n)=>{var l=o=>{try{i(t.next(o))}catch(s){n(s)}},m=o=>{try{i(t.throw(o))}catch(s){n(s)}},i=o=>o.done?p(o.value):Promise.resolve(o.value).then(l,m);i((t=t.apply(e,r)).next())});import{M as A,a as h}from"./MapExt-DVovzpWn.js";import{PageBaseGroupEdit as d}from"./PageBaseGroupEdit-BXdNWKIo.js";import{GPEAutoFullDLL as g}from"./GPEAutoFullDLL-BqUF6zia.js";import{SFTable as f}from"./SFTable-BpxUt1jb.js";import{GloComm as c}from"./GloComm-B1xAfTWw.js";import{G as L,m as D}from"./entry/index-B5R3Coa4-1746862693206.js";import{GPEAutoFullDDLSFTable as w}from"./GPEAutoFullDDLSFTable-CSezP8HV.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";import"./SFDBSrc-DbkqYXE6.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";class Q extends d{constructor(){super("GPE_AutoFullDLL");a(this,"Desc1",`
  #### 帮助
  - 该SQL必须返回No,Name 两个列。
  - 支持ccbpm表达式。
  - SELECT No,Name FROM Port_Emp WHERE FK_Dept='@WebUser.DeptNo'
  #### 应用场景
   - 选择一个会议主持人，从本部门中选择。
  #### 配置图
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/AutoFullDLL/Img/AutoFullDLLSetting.png "屏幕截图.png")
  #### 效果图
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/AutoFullDLL/Img/AutoFullDLL.png "屏幕截图.png")
  `);a(this,"Desc0",`
  #### 帮助
   - 不启用，不启用过滤功能。
   - 启用过滤功能，应用SQL语句等，得到系统返回值。对数据进行过滤处理。
   - 比如，选取主讲人，选取班主任。
   - 加载的时候填充的数据.
  
  #### 效果图
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/AutoFullDLL/Img/AutoFullDLL.png "屏幕截图.png") 
  
  `);a(this,"Desc2",`
  #### 帮助
   - 绑定字典表的显示过滤.
   - 您可以维护字典表.
  
  `);this.PageTitle="设置显示过滤"}Init(){return u(this,null,function*(){this.entity=new A,this.KeyOfEn=h.DoWay,this.Btns=[{pageNo:"2",list:["字典维护"]}],yield this.entity.InitDataForMapAttr("AutoFullDLL",this.GetRequestVal("PKVal")),this.AddGroup("A","设置显示过滤"),this.Blank("0","不启用",this.Desc0),this.AddEntity("1","自定义设置",new g,this.Desc1),this.AddEntity("2","绑定字典表",new w,this.Desc2)})}AfterSave(t,p){if(t==p)throw new Error("Method not implemented.")}BtnClick(t,p,n){return u(this,null,function*(){var l;if(n=="字典属性"){const m=(l=this.entity)==null?void 0:l.Doc;if(!m){alert("请选择绑定的字典，然后执行保存按钮.");return}const i=new f(m);yield i.Retrieve();const o=i.GetParaString("EnName",""),s=c.UrlEn(o,i.No);return new L(D.OpenUrlByDrawer75,s)}if(n=="字典维护"){const m=c.UrlSearch("TS.FrmUI.SFTable");return new L(D.OpenUrlByDrawer75,m)}if(t==p||t===n)throw new Error("Method not implemented.")})}}export{Q as GPE_AutoFullDLL};
