var h=Object.defineProperty;var d=(s,t,e)=>t in s?h(s,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):s[t]=e;var p=(s,t,e)=>d(s,typeof t!="symbol"?t+"":t,e);var n=(s,t,e)=>new Promise((i,r)=>{var m=a=>{try{l(e.next(a))}catch(D){r(D)}},o=a=>{try{l(e.throw(a))}catch(D){r(D)}},l=a=>a.done?i(a.value):Promise.resolve(a.value).then(m,o);l((e=e.apply(s,t)).next())});import{M as c,a as g}from"./MapExt-DVovzpWn.js";import{DtlImpEn1 as I}from"./DtlImpEn1-CJ3DjXYL.js";import{DtlImpEn3 as u}from"./DtlImpEn3-CYjpiKKW.js";import{DtlTreeEns as E}from"./DtlTreeEns-ZxE9S2pl.js";import{PageBaseGroupEdit as P}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./Help-D0bDMZWg.js";class w extends P{constructor(){super("GPE_DtlImp");p(this,"helpGPN",`
  #### 帮助
  - 首先写一个GPN, 增加一个 AddFileUpload 的方法.
  - 参考
  - 系统提供一个Demo,请参考/DataUser/DtlImpDemo.vue
  - 如何使用参考Demo.
  `);p(this,"help5",`
  #### 帮助
  - 对于比较复杂的导入，系统满足不了，需要个性化实现,就使用该模式.
  - 系统提供一个Demo,请参考/DataUser/DtlImpDemo.vue
  - 如何使用参考Demo.
  `);p(this,"Desc1",`
  #### 帮助
  - 按照要求配置数据源.
  - 在从表上显示导入功能,如下图:
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/EditModel/Card1.png "表格模式")

  #### 说明
   - 系统首先检查数据是否正确，如果有非法的数据，系统就会 0 导入.
   #### 检查内容：
    1. 数值类型的字段是否为空合法.
    1. 日期字段格式是否合理. 
    1. 枚举字段是否完整,比如：性别字段，枚举是男,女. 
    1. 外键字段(外部数据源)是否符合要求. 
  #### 效果图
  - ![从表导入:效果图.](./resource/WF/CCForm/DtlImpByTable.png "从表导入")
  `);p(this,"Desc2",`
  #### 帮助
   - 制作 excel模板, 放入到: \\DataUser\\TempleteOfImp\\从表ID.xls
   - 在excel模板中填写数据.
   - 执行导入
   #### 说明
   - 系统首先检查数据是否正确，如果有非法的数据，系统就会 0 导入.
   #### 检查内容：
    1. 数值类型的字段是否为空合法.
    1. 日期字段格式是否合理. 
    1. 枚举字段是否完整,比如：性别字段，枚举是男,女. 
    1. 外键字段(外部数据源)是否符合要求. 

  `);p(this,"Desc3",`
  #### 表格查询(简单模式-SQL)
   - 按照要求配置数据源.
   - 在从表上显示导入功能,如下图:
   #### 说明
   - 系统首先检查数据是否正确，如果有非法的数据，系统就会 0 导入.
   #### 检查内容：
    1. 数值类型的字段是否为空合法.
    1. 日期字段格式是否合理. 
    1. 枚举字段是否完整,比如：性别字段，枚举是男,女. 
    1. 外键字段(外部数据源)是否符合要求. 

    
  #### 设置
  - ![从表导入:效果图.](./resource/WF/CCForm/DtlImpByTableSetting.png "从表导入")
  #### 效果图1
  - ![从表导入:效果图.](./resource/WF/CCForm/DtlImpByTable.png "从表导入")
  `);this.PageTitle="从表导入"}Init(){this.entity=new c,this.KeyOfEn=g.DoWay,this.AddGroup("A","从表导入模式"),this.Blank("0","无,不设置(默认)","不设置导入."),this.AddEntity("1","表格查询(简单模式-SQL)",new I,this.Desc1),this.AddEntity("3","表格查询模式（高级）",new u,this.Desc3),this.AddEntity("4","左树右表(TreeEns)",new E,this.Desc3),this.AddGroup("B","自定义模式"),this.SingleTB("5","自定义url模式","Tag1",this.help5,"请输入URL:比如/src/DataUser/DtlImpDemo.vue"),this.SingleTB("6","自定义GPN模式","Tag1",this.helpGPN,"请输入GPN的ClassID,比如:GPN_XXXX")}AfterSave(e,i){return n(this,null,function*(){const r=new c,m=this.params.RefPKVal,o=this.params.RefPKVal+this.params.suffix;r.setPKVal(o),yield r.Retrieve(),r.SetValByKey("FK_MapData",m),r.DirectUpdate()})}BtnClick(e,i,r){}}export{w as GPE_DtlImp};
