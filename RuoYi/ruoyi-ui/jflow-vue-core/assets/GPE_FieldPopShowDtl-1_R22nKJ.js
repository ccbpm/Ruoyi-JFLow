var D=Object.defineProperty;var G=(i,e,t)=>e in i?D(i,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):i[e]=t;var s=(i,e,t)=>G(i,typeof e!="symbol"?e+"":e,t);var a=(i,e,t)=>new Promise((o,n)=>{var h=r=>{try{p(t.next(r))}catch(l){n(l)}},m=r=>{try{p(t.throw(r))}catch(l){n(l)}},p=r=>r.done?o(r.value):Promise.resolve(r.value).then(h,m);p((t=t.apply(i,e)).next())});import{FieldPopShowDtl as c}from"./FieldPopShowDtl-BLYSiNGp.js";import{M as d,a as L}from"./MapExt-DVovzpWn.js";import{PageBaseGroupEdit as A}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./Help-D0bDMZWg.js";class I extends A{constructor(){super("GPE_FieldPopShowDtl");s(this,"Desc0",`
  #### 帮助
   - 数据pop展示，也称为数据挖掘或者说数据下攥，数据攥取.
   - 一般用于数值类型的字段,比如:费用,点击费用数值显示费用的组成.
   - 显示在Search列表上或者单实体数据主表字段里.
   
  #### 运行图例
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/AutoFullDtlField/Img/AutoFull.png "屏幕截图.png") 

  `);s(this,"Desc1",`
  #### 内置GenerList帮助
   - 首先请参考高代码认识什么是GenerList,内置的GenrList,是指系统自定义的 GL_FieldPopShowDtl.ts 
   - 通过读取配置项,生成简单的列表,让实施人员不用开发代码就可以完成.
   - 仅仅需要配置一个数据源.

  `);s(this,"Desc2GL",`
  #### 帮助: 自定义的GenerList.
   - 首先请参考高代码认识什么是GenerList.
   - 自定义的GenerList是指自己写一个,然后把类名配置到这里.
   - 适合比较复杂的应用场景,对数据安全，效率比较高.
  #### 编写注意事项
  - 系统向GenerList 实体类写入 FrmID, PKVal, AttrKey 参数.
  - GenerList可以通过这些参数获取数据.
  `);s(this,"DescSelf",`
  #### 帮助
  - 自己写一个url，系统自动带入 FrmID, PKVal, AttrKey  三个参数.
  `);this.PageTitle="数据pop展示"}Init(){return a(this,null,function*(){this.entity=new d,this.KeyOfEn=L.DoWay,yield this.entity.InitDataForMapAttr("FieldPopShowDtl",this.GetRequestVal("PKVal")),this.AddGroup("A","数据pop展示"),this.Blank("0","不启用",this.Desc0),this.AddEntity("1","内置的GenerList.",new c,this.Desc1),this.SingleTextArea("2","自定义的GenerList.","Doc","请输入GenerList类名:",this.Desc2GL),this.SingleTextArea("3","按照自定义URL设置.","Doc","请输入URL",this.DescSelf)})}AfterSave(t,o){if(t==o)throw new Error("Method not implemented.")}BtnClick(t,o,n){if(t==o||t===n)throw new Error("Method not implemented.")}}export{I as GPE_FieldPopShowDtl};
