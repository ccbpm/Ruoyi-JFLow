var F=Object.defineProperty;var a=(s,t,e)=>t in s?F(s,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):s[t]=e;var p=(s,t,e)=>a(s,typeof t!="symbol"?t+"":t,e);var m=(s,t,e)=>new Promise((o,r)=>{var h=i=>{try{l(e.next(i))}catch(n){r(n)}},u=i=>{try{l(e.throw(i))}catch(n){r(n)}},l=i=>i.done?o(i.value):Promise.resolve(i.value).then(h,u);l((e=e.apply(s,t)).next())});import{F as d}from"./entry/index-B5R3Coa4-1746862693206.js";import{Flow as c}from"./Flow-BIaTOSmj.js";import{PageBaseGroupEdit as f}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";class Q extends f{constructor(){super("GPE_FlowBuessFields");p(this,"Desc0",`

  #### 概念
   - 业务字段是流程引擎系统字段以外的，用户自定义的表单字段.
   - 比如：请假日期从、到、请假人、请假原因等.
   - 流程引擎系统字段包括： 标题、发起人、发起日期、发起人部门、停留节点、当前处理人、流程状态等.
  #### 设置作用
   - 通用的待办列表显示的是系统字段, 比如：标题、发起人、发起日期、状态、停留节点.
   - 如果显示指定流程的待办、在途、抄送、就可以使用业务字段显示.
   - 点发起菜单，转到流程一户式操作,就可以查看该流程的信息.
`);this.PageTitle="业务字段"}Init(){return m(this,null,function*(){this.entity=new c,this.KeyOfEn="BuessFieldRole",this.AddGroup("A","显示列"),this.Blank("0","不启用",this.Desc0);let e="";e==""&&(e="ND"+parseInt(this.PKVal)+"01");const o=d.SQLOfGpnMethodGroupSQL(e),r=d.SQLOfGpeFlowBuessFields(e);this.SelectItemsByGroupList("1","选择字段",this.Desc0,!0,o,r,"BuessFields","BuessFieldNames")})}AfterSave(e,o){if(e==o)throw new Error("Method not implemented.")}BtnClick(e,o,r){if(e==o||e===r)throw new Error("Method not implemented.")}}export{Q as GPE_FlowBuessFields};
