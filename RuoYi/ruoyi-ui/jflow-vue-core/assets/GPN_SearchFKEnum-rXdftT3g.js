var K=Object.defineProperty;var h=(o,e,r)=>e in o?K(o,e,{enumerable:!0,configurable:!0,writable:!0,value:r}):o[e]=r;var l=(o,e,r)=>h(o,typeof e!="symbol"?e+"":e,r);var p=(o,e,r)=>new Promise((n,s)=>{var a=t=>{try{m(r.next(t))}catch(i){s(i)}},c=t=>{try{m(r.throw(t))}catch(i){s(i)}},m=t=>t.done?n(t.value):Promise.resolve(t.value).then(a,c);m((r=r.apply(o,e)).next())});import{SearchFKEnum as S}from"./SearchFKEnum-iAO6-ceA.js";import{MapAttr as E}from"./MapAttr-DcWjEeWW.js";import{P,F as y,G as F,m as f}from"./entry/index-B5R3Coa4-1746862693206.js";import"./GPE_ActiveDDL-CBrWe-fm.js";import"./PageBaseGroupEdit-BXdNWKIo.js";import"./Help-D0bDMZWg.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapExt-DVovzpWn.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./GPEActiveDDLSFTable-DOc4bXna.js";import"./Events-D9tOL1Ad.js";import"./GPEActiveDDLSelfSetting-BmBkm8oa.js";import"./GloComm-B1xAfTWw.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./GPE_AutoFullDLL-CGsKVwxV.js";import"./GPEAutoFullDLL-BqUF6zia.js";import"./GPEAutoFullDDLSFTable-CSezP8HV.js";class X extends P{constructor(){super("GPN_SearchFKEnum");l(this,"Docs2",`
  #### 帮助
  - 按照外键或枚举字段做为查询条件，一个表单里有枚举或者外键字段(能放入到下拉框的字段) 都可以作为查询条件.
  - 比如:按性别、政治面貌、班级来查询.
  - 在上面选择您要查询的字段，点击确定按钮. 每次只能创建一个，如果需要创建多个.
  #### 实现级联查询
  - 请编辑属性: 参考如何设置级联查询.
  #### 实现对查询范围进行控制.
  - 请编辑属性: 参考如何设置查询范围的权限控制.

  #### 配置图
  ![输入图片说明](./resource/CCBill/SearchCond/SearchFKEnumSetting.png "屏幕截图.png")  
  #### 效果图
  - 按照配置的字段显示的查询条件.
  ![输入图片说明](./resource/CCBill/SearchCond/SearchFKEnum.png "屏幕截图.png")  

`);this.ForEntityClassID="TS.CCBill.SearchFKEnum",this.PageTitle="新建查询条件"}Init(){this.AddGroup("A","新建查询条件");const r=this.RefPKVal,n=y.SQLOfGpnSearchFKEnum(r);this.SelectItemsByList("1","选择外键枚举字段",this.Docs2,!1,n)}GenerSorts(){return p(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(r,n,s,a,c){return p(this,null,function*(){const m=this.RequestVal("RefPKVal"),t=new S;t.FrmID=m,t.KeyOfEn=s,t.Name=a,t.MyPK=t.FrmID+"_"+t.KeyOfEn,(yield t.IsExits())==!0?alert("该查询条件已经存在."):yield t.Insert();const i=new E;i.MyPK=t.MyPK,yield i.Retrieve(),t.UIBindKey=i.UIBindKey,i.LGType==1&&(t.IsEnum=1),t.Update();const u="/@/WF/Comm/En.vue?EnName=TS.CCBill.SearchFKEnum&PKVal="+t.MyPK;return new F(f.CloseAndReload,u)})}}export{X as GPN_SearchFKEnum};
