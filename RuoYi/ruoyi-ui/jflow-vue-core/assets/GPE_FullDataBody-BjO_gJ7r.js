var w=Object.defineProperty;var L=(i,r,t)=>r in i?w(i,r,{enumerable:!0,configurable:!0,writable:!0,value:t}):i[r]=t;var F=(i,r,t)=>L(i,typeof r!="symbol"?r+"":r,t);var D=(i,r,t)=>new Promise((a,l)=>{var s=e=>{try{o(t.next(e))}catch(n){l(n)}},p=e=>{try{o(t.throw(e))}catch(n){l(n)}},o=e=>e.done?a(e.value):Promise.resolve(e.value).then(s,p);o((t=t.apply(i,r)).next())});import{M as C,a as A}from"./MapExt-DVovzpWn.js";import{a as y}from"./DBAccess-CZ0wdWXU.js";import{FullBodySFTable as M}from"./FullBodySFTable-DSyxKnpd.js";import{FullBodySelf as B}from"./FullBodySelf-DzwgEgdn.js";import{GloComm as d}from"./GloComm-B1xAfTWw.js";import{PageBaseGroupEdit as T}from"./PageBaseGroupEdit-BXdNWKIo.js";import{G as f,m as S}from"./entry/index-B5R3Coa4-1746862693206.js";import{b as x}from"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./vue-B6GVRDGm.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FrmTrack-Ct-No0Nq.js";import"./Help-D0bDMZWg.js";class X extends T{constructor(){super("GPE_FullDataBody");F(this,"Desc0",` 
  #### 帮助
  - 不填充：对控件没有填充要求。
  - 启用填充控件: 当选项发生变化后，同表单的其它控件的数据需要变化，我们把这样的行为称为其他控件填充。
  - 比如：下拉框的人员变化后，其它的字段就跟着变化。
  - 如下图，当人员选择变化时，Email自动变化。
  #### 效果图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/DDLFullCtrl/Img/DDLFullCtrl.png "屏幕截图.png") 
   `);F(this,"Desc1",` 
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
  设置下拉框在值变化后，填充其他控件与从表 `);F(this,"Desc2",` 
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
  设置下拉框在值变化后，填充其他控件与从表 `);this.PageTitle="填充主表"}Init(){return D(this,null,function*(){this.entity=new C,this.KeyOfEn=A.Tag5,this.Btns=[{pageNo:"SFTable",list:["字段对应","查询维护"]}],this.AddGroup("A","填充主表"),this.Blank("None","不填充",this.Desc0),this.AddEntity("Self","自定义设置",new B,this.Desc1),this.AddEntity("SFTable","绑定查询",new M,this.Desc1)})}BtnClick(t,a,l){return D(this,null,function*(){var s,p,o,e,n,u,g;if(l==="设置参数"||l=="字段对应"){yield(s=this.entity)==null?void 0:s.Retrieve();const c=(p=this.entity)==null?void 0:p.Tag6;if(!c){x.error("当前字段不存在，请先保存再执行关联");return}const m=new y(c);yield m.Retrieve();const h=(o=this.entity)==null?void 0:o.MyPK;yield m.AddSln(h,(e=this.entity)==null?void 0:e.FK_MapData);const E=d.UrlDtlBatch("TS.FrmUI.SFColumnSln","&RefPKVal="+h+"&FrmID="+((n=this.entity)==null?void 0:n.FK_MapData));return new f(S.OpenUrlByDrawer75,E)}if(l==="查询属性"||l==="查询维护"){yield new y((u=this.entity)==null?void 0:u.Tag6).Retrieve();const m=d.UrlEn("TS.FrmUI.SFSearch",(g=this.entity)==null?void 0:g.Tag6);return new f(S.OpenUrlByDrawer75,m)}if(t==a||t===l)throw new Error("Method not implemented.")})}AfterSave(t,a){}}export{X as GPE_FullDataBody};
