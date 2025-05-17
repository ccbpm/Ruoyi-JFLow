var i=Object.defineProperty;var c=(r,t,e)=>t in r?i(r,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):r[t]=e;var s=(r,t,e)=>c(r,typeof t!="symbol"?t+"":t,e);import{F as h}from"./entry/index-B5R3Coa4-1746862693206.js";import{PageBaseGroupEdit as l}from"./PageBaseGroupEdit-BXdNWKIo.js";import{MapDtl as o}from"./MapDtl-B_Ep8ewM.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";class f extends l{constructor(){super("GPE_DtlDTSearchWay");s(this,"Desc0",`
  #### 帮助
  - 按照选择的日期型字段进行查询。
  - 在报表中根据 like 日期字段值 进行查询。
  
  #### 效果图
  ![输入图片说明](./resource/CCBill/SearchCond/SearchKeyData.png "屏幕截图.png")  
 `);s(this,"Desc1",`
  #### 帮助
  - 按照选择的日期型字段进行查询。
  - 在报表中根据 like 日期时间值 进行查询。
  `);this.PageTitle="日期查询"}AfterSave(e,a){if(e==a)return null}BtnClick(e,a,D){}Init(){this.entity=new o,this.KeyOfEn="DTSearchWay",this.AddGroup("A","日期查询"),this.Blank("0","不启用",this.Desc0);const e=h.SQLOfGpeDTSearchWay(this.PKVal);this.SelectItemsByList("1","按日期查询",this.Desc0,!0,e,"DTSearchKey","DTSearchKeyT"),this.SelectItemsByList("2","按日期时间查询",this.Desc1,!0,e,"DTSearchKey","DTSearchKeyT")}}export{f as GPE_DtlDTSearchWay};
