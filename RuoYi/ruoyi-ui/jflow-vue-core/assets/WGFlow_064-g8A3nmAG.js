var o=(i,r,e)=>new Promise((p,c)=>{var x=s=>{try{n(e.next(s))}catch(m){c(m)}},F=s=>{try{n(e.throw(s))}catch(m){c(m)}},n=s=>s.done?p(s.value):Promise.resolve(s.value).then(x,F);n((e=e.apply(i,r)).next())});import{WaiGuaBaseFlow as g}from"./WaiGuaBaseFlow-CU6VN_nU.js";import{W as t}from"./entry/index-B5R3Coa4-1746862693206.js";import"./WaiGuaBaseFrm-CVp7kXTK.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class l extends g{constructor(){super("WGFlow_064","064")}FrmLoadBefore(){return`
    ##### 提示信息
    1. 成功激活了/src/App/Demo/WGFlow_064.ts 的 FrmLoadBefore 事件.
    2. 在该时间里可以改变  this.FrmBodyJson['xxxxxx'] 的值用于显示在表单上.
    `}FrmLoadAfter(){return`
    ##### 提示信息
    1. 成功激活了/src/App/Demo/WGFlow_064.ts 的 FrmLoadAfter 事件.
    `}ReturnBefore(){return`
    ##### 提示信息
    1. 成功激活了/src/App/Demo/WGFlow_064.ts 的 ReturnBefore 事件.
    2. 如果返回 err@xxxxxxx , 则系统提示错误信息，并阻止退回.
    `}ReturnAfter(){return`
  ##### 提示信息
  1. 成功激活了/src/App/Demo/WGFlow_064.ts 的 ReturnAfter 事件.
  `}SendWhen(){return o(this,null,function*(){return this.NodeID==6401?`
      ##### 提示信息
      1. 成功激活了/src/App/Demo/WGFlow_064.ts 的 SendWhen事件.
      2. 如果return err@xxxxxxx 则流程不向下发送.
      3. 可以在该方法里写ts事件阻止发送动作.
      `:""})}SendSuccess(){return o(this,null,function*(){return`
      1. 成功激活了/src/App/Demo/WGFlow_064.ts 的 SendSuccess 事件.
      2. 可以在该事件里调用api接口，实现更改状态、执行过程能业务操作.
      `})}FlowOverAfter(){return o(this,null,function*(){return`
      1. 成功激活了/src/App/Demo/WGFlow_064.ts 的 FlowOverAfter 事件.
      2. 可以编写业务逻辑实现对
      `})}GenerInfo(){return o(this,null,function*(){return`
    %%%%% 环境信息 %%%%%
    1.获得表单数据:
      日期从：${this.FrmBodyJson.RiQiCong} 到 ${this.FrmBodyJson.Dao} 
      请假类型ID: ${this.FrmBodyJson.QingJiaLeiXing}, 请假类型ID: ${this.FrmBodyJson.QingJiaLeiXingT}
    2.流程信息:
       流程编号: ${this.FlowNo},WorkID: ${this.WorkID}, 停留节点ID:${this.NodeID}. 
    3. 当前操作员信息:
       登陆人员账号: ${t.No}, 名称: ${t.Name}, 部门编号: ${t.DeptNo}, 部门名称: ${t.DeptName}
    `})}}export{l as WGFlow_064};
