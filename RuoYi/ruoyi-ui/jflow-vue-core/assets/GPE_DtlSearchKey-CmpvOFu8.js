var a=Object.defineProperty;var n=(t,e,r)=>e in t?a(t,e,{enumerable:!0,configurable:!0,writable:!0,value:r}):t[e]=r;var i=(t,e,r)=>n(t,typeof e!="symbol"?e+"":e,r);import{F as c}from"./entry/index-B5R3Coa4-1746862693206.js";import{PageBaseGroupEdit as h}from"./PageBaseGroupEdit-BXdNWKIo.js";import{MapDtl as l}from"./MapDtl-B_Ep8ewM.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";class C extends h{constructor(){super("GPE_DtlSearchKey");i(this,"Desc0",`
  #### 帮助
  - 关键字查询是接受用户输入一个关键字，在整个报表的显示列中使用like查询(外键、枚举、数值类型的除外)
  - 关键字搜索提示, 默认为:请输入关键字...
  #### 效果图
  -  ![输入图片说明](./resource/CCBill/SearchCond/SearchKey.png "屏幕截图.png")  
 `);i(this,"Desc1",`
  #### 帮助
  - 选择特定字段，在报表中根据 like 模糊查询
  #### 配置图
  ![输入图片说明](./resource/CCBill/SearchCond/StringSearchKeysetting.png "屏幕截图.png")  
  #### 效果图
  ![输入图片说明](./resource/CCBill/SearchCond/StringSearchKey.png "屏幕截图.png")  
  `);this.PageTitle="关键字查询"}AfterSave(r,s){if(r==s)return null}BtnClick(r,s,o){}Init(){this.entity=new l,this.KeyOfEn="IsSearchKey",this.AddGroup("A","关键字查询"),this.Blank("1","关键字查询",this.Desc0),this.SelectItemsByList("2","特定关键字查询",this.Desc1,!0,c.SQLOfGpeSearchKey(this.PKVal),"StringSearchKeys","StringSearchKeysT")}}export{C as GPE_DtlSearchKey};
