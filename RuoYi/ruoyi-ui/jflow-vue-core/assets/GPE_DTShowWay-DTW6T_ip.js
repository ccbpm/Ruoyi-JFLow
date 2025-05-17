var m=Object.defineProperty;var e=(r,t,i)=>t in r?m(r,t,{enumerable:!0,configurable:!0,writable:!0,value:i}):r[t]=i;var o=(r,t,i)=>e(r,typeof t!="symbol"?t+"":t,i);import{F as s}from"./FrmBill-slTzXsFG.js";import{PageBaseGroupEdit as a}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./FrmAdm-w-27tFK4.js";import"./MapData-D5zymw8O.js";import"./EnumLab-CsLi93T0.js";import"./SysEvent-DymzJjDC.js";import"./Collection-C-J3HY9U.js";import"./PG_Group2Method-D-hH9YF8.js";import"./PageBasePanelGroup-C-loKAxc.js";import"./GroupMethod-D4dNiFLl.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./PCenter-DbUtxw5j.js";import"./PowerCenter-Dl8ZnZaz.js";import"./Method-Duk019iw.js";import"./GloComm-B1xAfTWw.js";import"./FrmTrack-Ct-No0Nq.js";import"./SearchFKEnum-iAO6-ceA.js";import"./GPE_ActiveDDL-CBrWe-fm.js";import"./MapExt-DVovzpWn.js";import"./GPEActiveDDLSFTable-DOc4bXna.js";import"./GPEActiveDDLSelfSetting-BmBkm8oa.js";import"./Help-D0bDMZWg.js";import"./GPE_AutoFullDLL-CGsKVwxV.js";import"./GPEAutoFullDLL-BqUF6zia.js";import"./GPEAutoFullDDLSFTable-CSezP8HV.js";import"./DBRole-DQUduH7f.js";import"./GPE_FrmType-D4Ez4Fbe.js";import"./Flow-BIaTOSmj.js";import"./SelfCheck-C0INbd8A.js";import"./ByEmpNo-CP2nCXHT.js";import"./MapExtSearchCol-D1qWhKw6.js";class $ extends a{constructor(){super("GPE_DTShowWay");o(this,"Desc0",`
  #### 帮助
  - 按照选择的日期型字段进行查询。
  - 在报表中根据 like 日期字段值 进行查询。
  
  #### 效果图
  ![输入图片说明](./resource/CCBill/SearchCond/SearchKeyData.png "屏幕截图.png")  
 `);o(this,"Desc1",`
  #### 帮助
  - 按照选择的日期型字段进行查询。
  - 在报表中根据 like 日期时间值 进行查询。
  `);this.PageTitle="日期展示方式"}AfterSave(i,p){if(i==p)return null}BtnClick(i,p,n){}Init(){this.entity=new s,this.KeyOfEn="DTShowWay",this.AddGroup("A","日期查询格式"),this.Blank("0","字段方式（日期从，到）",this.Desc0),this.Blank("1","日期Tab页（月份、季度、年度）",this.Desc0),this.Blank("2","日期Tab页（月份、季度、年度、自定义）(未解析)",this.Desc0)}}export{$ as GPE_DTShowWay};
