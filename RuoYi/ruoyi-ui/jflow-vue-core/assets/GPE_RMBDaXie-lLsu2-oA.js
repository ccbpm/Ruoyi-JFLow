var c=Object.defineProperty;var B=(t,e,i)=>e in t?c(t,e,{enumerable:!0,configurable:!0,writable:!0,value:i}):t[e]=i;var m=(t,e,i)=>B(t,typeof e!="symbol"?e+"":e,i);var s=(t,e,i)=>new Promise((a,o)=>{var g=r=>{try{n(i.next(r))}catch(p){o(p)}},D=r=>{try{n(i.throw(r))}catch(p){o(p)}},n=r=>r.done?a(r.value):Promise.resolve(r.value).then(g,D);n((i=i.apply(t,e)).next())});import{F as h}from"./entry/index-B5R3Coa4-1746862693206.js";import{M as R,a as M}from"./MapExt-DVovzpWn.js";import{PageBaseGroupEdit as X}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./Help-D0bDMZWg.js";class I extends X{constructor(){super("GPE_RMBDaXie");m(this,"Desc0",`
  #### 帮助
   - 定义：当前输入金额类型的数据，要实现对其他只读的文本框进行大写输出（在一个金额类型或者数值类型的字段输入时自动在另外一个文本框上显示该输入值的人民币大写.）。
   - 设置过程: 1.创建一个大写输出的文本框;2.设置只读状态;3.在需要转大小写的字段上点击属性;4.设置人民币大写.
   - 比如: **合同付款金额**需要转人民币大写，就可以在**合同付款金额**的字段属性中 => 基本设置 => 人民币大写 => 选择只读的人民币大写字段，选择输出人民币大写的字段点击保存即可。
   - 应用场景：银行、单位和个人在填写各种票据和结算凭证时必须遵守严格的标准和规范.
   #### 效果图
   - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RMBDaXie/Img/RMBDaXieyanshi.png "屏幕截图.png") 

   ### 配置图
   - 配置图例1
   - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RMBDaXie/Img/RMBDaXie2.png "屏幕截图.png") 
   - 配置图例2
   - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RMBDaXie/Img/RMBDaXie1.png "屏幕截图.png") 
   - 配置图例3
   - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RMBDaXie/Img/RMBDaXie.png "屏幕截图.png") 
   - 运行图例
   ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RMBDaXie/Img/RMBDaXieyanshi.png "屏幕截图.png") 
 
   `);m(this,"Desc1",`
  #### 帮助
   - 不启用：不对其他文本框实现大写转换.
   - 人民币大写：当前输入金额类型的数据，要实现对其他只读的文本框进行大写输出。
  #### 效果图
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/RMBDaXie/Img/RMBDaXieyanshi.png "屏幕截图.png") 
  `);this.PageTitle="人民币大写"}Init(){return s(this,null,function*(){this.entity=new R,this.KeyOfEn=M.DoWay,yield this.entity.InitDataForMapAttr("RMBDaXie",this.GetRequestVal("PKVal")),this.AddGroup("A","人民币大写"),this.Blank("0","不启用",this.Desc0),this.SingleDDLSQL("1","选择只读的人民币大写字段",M.Tag,this.Desc0,h.SQLOfRMBDaXie,!1)})}AfterSave(i,a){if(i==a)throw new Error("Method not implemented.")}BtnClick(i,a,o){if(i==a||i===o)throw new Error("Method not implemented.")}}export{I as GPE_RMBDaXie};
