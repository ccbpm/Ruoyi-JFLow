var u=Object.defineProperty;var m=(s,t,e)=>t in s?u(s,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):s[t]=e;var p=(s,t,e)=>m(s,typeof t!="symbol"?t+"":t,e);var l=(s,t,e)=>new Promise((n,a)=>{var c=i=>{try{r(e.next(i))}catch(o){a(o)}},d=i=>{try{r(e.throw(i))}catch(o){a(o)}},r=i=>i.done?n(i.value):Promise.resolve(i.value).then(c,d);r((e=e.apply(s,t)).next())});import{SFDBSrc as A}from"./SFDBSrc-DbkqYXE6.js";import{PageBaseGroupEdit as b}from"./PageBaseGroupEdit-BXdNWKIo.js";import{H as h}from"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";class R extends b{constructor(){super("GPE_WebApiResultModel");p(this,"Desc0",`  
  #### 帮助
  - 驰骋提供的格式.
{
  code:200,
  message:'执行成功',
  data:'xxxxx'
  }

  - code=200 执行成功. code=500 执行失败, code=404 标识配置错误，没有连接.
   - 如果对方按照这个格式返回的数据,就不需要转换。
   #### 请参考
     ![输入图片说明](./resource/Admin/DBSrc/Dbsrc_resultObj.png "屏幕截图.png")  
  `);p(this,"Desc1",`
  #### 帮助
  - 要写一个 BuessUnit 类
  - 请参考:  BP.App.Demo.BuessUnit_LocalWebApi_ResultObj
  - 在这个类里，把您的格式转换为驰骋的格式返回数据.
  `);this.PageTitle="数据标准转换模式"}Init(){return l(this,null,function*(){this.entity=new A,this.KeyOfEn="WebApiResultModel",this.AddGroup("A","数据标准转换模式"),this.Blank("0","不转换,采用标准格式.",this.Desc0),this.SelectItemsByList("1","使用业务单元转换",this.Desc1,!1,yield this.GenerBuessUnit(),"WebApiResultObjEnName","WebApiResultObjEnNameT")})}GenerBuessUnit(){return l(this,null,function*(){const n=yield new h("BP.WF.HttpHandler.WF_Admin_AttrNode").DoMethodReturnJson("ActionDtl_Init");return JSON.stringify(n)})}BtnClick(e,n,a){}AfterSave(e,n){}}export{R as GPE_WebApiResultModel};
