var D=Object.defineProperty;var d=(r,a,e)=>a in r?D(r,a,{enumerable:!0,configurable:!0,writable:!0,value:e}):r[a]=e;var n=(r,a,e)=>d(r,typeof a!="symbol"?a+"":a,e);var m=(r,a,e)=>new Promise((i,p)=>{var g=t=>{try{s(e.next(t))}catch(o){p(o)}},c=t=>{try{s(e.throw(t))}catch(o){p(o)}},s=t=>t.done?i(t.value):Promise.resolve(t.value).then(g,c);s((e=e.apply(r,a)).next())});import{M as y,a as E}from"./MapExt-DVovzpWn.js";import{PageBaseGroupEdit as h}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./Help-D0bDMZWg.js";class _ extends h{constructor(){super("GPN_RegularExpression");n(this,"Desc1",`
  #### 帮助
  - 应用场景：在请假单中，根据请假日期从，请假日期到的差值，自动计算请假天数。

  #### 配置图例1
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/ReqDays/Img/ReqDaysBiaodan.png "屏幕截图.png") 
  - 配置图例2
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/ReqDays/Img/ReqDaysBiaodan2.png "屏幕截图.png") 

  #### 运行图例
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/ReqDays/Img/ReqDaysYanshi.png "屏幕截图.png") 
  `);n(this,"Desc2",`
  #### 帮助
   - 对两个日期求差，日期1-日期2= 天数.
   - 得出的值是一个整形的数值。
  #### 应用场景
   - 根据请假日期从，请假日期到自动计算请假天数.
  #### 效果图
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/ReqDays/Img/ReqDaysYanshi.png "屏幕截图.png") 
  
  `);this.PageTitle="正则表达式"}BtnClick(e,i,p){throw new Error("Method not implemented.{pageID}")}Init(){this.entity=new y,this.KeyOfEn=E.DoWay,this.AddGroup("A","正则表达式"),this.Blank("0","不启用",this.Desc2)}AfterSave(e,i){return m(this,null,function*(){})}}export{_ as GPN_RegularExpression};
