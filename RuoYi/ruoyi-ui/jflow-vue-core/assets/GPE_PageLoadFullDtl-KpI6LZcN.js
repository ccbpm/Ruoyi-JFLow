var h=Object.defineProperty;var u=(a,e,t)=>e in a?h(a,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):a[e]=t;var l=(a,e,t)=>u(a,typeof e!="symbol"?e+"":e,t);var m=(a,e,t)=>new Promise((p,s)=>{var D=i=>{try{o(t.next(i))}catch(r){s(r)}},c=i=>{try{o(t.throw(i))}catch(r){s(r)}},o=i=>i.done?p(i.value):Promise.resolve(i.value).then(D,c);o((t=t.apply(a,e)).next())});import{F as n}from"./entry/index-B5R3Coa4-1746862693206.js";import{M as E,a as d}from"./MapExt-DVovzpWn.js";import{PageBaseGroupEdit as F}from"./PageBaseGroupEdit-BXdNWKIo.js";import{PageLoadFull as P}from"./PageLoadFull-rXMpPjS-.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./Help-D0bDMZWg.js";class x extends F{constructor(){super("GPE_PageLoadFullDtl");l(this,"Desc0",`

  #### 帮助
   - 不设置(默认)：不对从表数据进行填充。
   - 设置从表填充：返回一个数据源，该数据源的列，需要与从表的列匹配，对于匹配上的数据自动填充。
  
  `);l(this,"Desc1",`
  #### 帮助
   - 填充从表的SQL。
   - 返回一个数据源，该数据源的列，需要与从表的列匹配，对于匹配上的数据自动填充。
   - 实例:SELECT * FROM Demo_Resume WHERE RefPK='@Key'
   - @Key 是系统约定的标记，就是选择的编号或者ID。
   - 这个数据源就会清空的方式复制到从表里面去。
  `);this.PageTitle="装载填充从表"}Init(){return m(this,null,function*(){this.entity=new E,this.KeyOfEn=d.DoWay,yield this.entity.InitDataForMapAttr("PageLoadFullDtl",this.GetRequestVal("PKVal")),this.AddGroup("A","装载填充"),this.Blank("None","不设置(默认)",this.Desc0),this.AddEntity("Self","按SQL填充",new P,this.Desc1),this.SelectItemsByGroupList("SFTable","按照查询填充",this.HelpUn,!1,n.srcDBSrc,n.srcDBSFSearch,"Doc")})}BtnClick(t,p,s){}AfterSave(t){}}export{x as GPE_PageLoadFullDtl};
