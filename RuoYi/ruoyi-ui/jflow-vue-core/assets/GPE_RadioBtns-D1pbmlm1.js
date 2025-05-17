var R=Object.defineProperty;var B=(i,a,t)=>a in i?R(i,a,{enumerable:!0,configurable:!0,writable:!0,value:t}):i[a]=t;var p=(i,a,t)=>B(i,typeof a!="symbol"?a+"":a,t);var m=(i,a,t)=>new Promise((e,o)=>{var s=n=>{try{r(t.next(n))}catch(g){o(g)}},d=n=>{try{r(t.throw(n))}catch(g){o(g)}},r=n=>n.done?e(n.value):Promise.resolve(n.value).then(s,d);r((t=t.apply(i,a)).next())});import{M as l,a as c,b as A}from"./MapExt-DVovzpWn.js";import{PageBaseGroupEdit as E}from"./PageBaseGroupEdit-BXdNWKIo.js";import{MapAttr as F,MapAttrs as M}from"./MapAttr-DcWjEeWW.js";import{q as u}from"./entry/index-B5R3Coa4-1746862693206.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";import"./Events-D9tOL1Ad.js";class V extends E{constructor(){super("GPE_RadioBtns");p(this,"Desc0",`
  #### 帮助
   - 不启用：不对其它控件进行控制。
   - 选项联动控件，就是选择一个Item,对其他控件实现隐藏，显示，设置特定的值的功能。
   - 比如: 请假表单的请假类型，选择病假，让其上传病例，填写所在医院，请事假就不需要显示附件上传与所在医院控件。
  #### 选择病假-效果图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RadioBtns/Img/RadioBtns.png "屏幕截图.png") 
  #### 选择事假-效果图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RadioBtns/Img/RadioBtns1.png "屏幕截图.png") 
  `);p(this,"Desc1",`

  #### 帮助
  - 就是选择一个Item,对其他控件实现隐藏，显示，设置特定的值的功能。
  - 点击设置进入详细设置.
  - 配置图例1
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RadioBtns/Img/RadioBtnsSetting.png "屏幕截图.png") 
  - 配置图例2
   ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RadioBtns/Img/RadioBtnsSetting1.png "屏幕截图.png") 
  - 运行图例
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RadioBtns/Img/RadioBtns.png "屏幕截图.png") 
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RadioBtns/Img/RadioBtns1.png "屏幕截图.png") 

  `);p(this,"helpDocs","");this.PageTitle="选项联动控件"}Init(){return m(this,null,function*(){const{loadComponent:t}=u();this.entity=new l,this.KeyOfEn=c.DoWay,yield this.entity.InitDataForMapAttr("RBAction",this.GetRequestVal("PKVal")),this.AddGroup("A","选项联动控件"),this.Blank("0","不启用",this.Desc0),this.SelfComponent("1","启用设置",t("/@/WF/Admin/FrmLogic/Views/RadioBtns.vue"),{EnClassID:this.EnClassID,PKVal:this.PKVal,enable:!1},this.helpDocs)})}BtnClick(t,e,o){return m(this,null,function*(){if(t=="0"||e=="0"||o=="0")return;const s=new F(this.PKVal);yield s.Retrieve(),new M().Retrieve("FK_MapData",s.FK_MapData),new A().Retrieve(c.FK_MapData,s.FK_MapData,c.ExtModel,"RBAction",c.ExtType,"RBActionAttr")})}AfterSave(t,e){return m(this,null,function*(){})}}export{V as GPE_RadioBtns};
