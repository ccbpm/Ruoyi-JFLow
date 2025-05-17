var g=Object.defineProperty;var S=(s,t,e)=>t in s?g(s,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):s[t]=e;var i=(s,t,e)=>S(s,typeof t!="symbol"?t+"":t,e);var m=(s,t,e)=>new Promise((r,c)=>{var n=o=>{try{h(e.next(o))}catch(a){c(a)}},F=o=>{try{h(e.throw(o))}catch(a){c(a)}},h=o=>o.done?r(o.value):Promise.resolve(o.value).then(n,F);h((e=e.apply(s,t)).next())});import{F as p}from"./entry/index-B5R3Coa4-1746862693206.js";import{PageBaseGroupEdit as A}from"./PageBaseGroupEdit-BXdNWKIo.js";import{Node as d,NodeAttr as l}from"./Node-BsvTqXX9.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";import"./EntityNodeID-3BfNz0DC.js";class B extends A{constructor(){super("GPE_ShenFenModel");i(this,"Desc3",`
  #### 说明
   - 表单采集的是一个部门编号.
   - 使用这个部门编号作为当前人员的身份.
    `);i(this,"Desc5",`
    #### 说明
     - 表单采集的是一个部门编号
     - 使用这个部门编号的维护的Leader字段，作为当前人员的身份.
      `);i(this,"Desc4",`
  #### 说明
   - 获取当前人员信息的是按照指定的部门与指定的角色的交集计算.
   - 指定的部门是从ccbpm的系统参数获取的.
   - 在流程运行的过程中系统参数，是通过 Flow_SavePara() 的方法保存到ccbpm中的.
   - 流程的系统参数存储在 表:WF_GenerWorkFlow 字段:AtPara 中.
   #### 其他
   - 请阅读ccbpm的接口方法，保存参数.

    `);i(this,"Desc0",`
  #### 说明
   - 上一步的发送人作为接收人。
   - 默认为该模式。
    `);i(this,"Desc1",`
  #### 说明
   - 指定节点的处理人作为本步骤的身份.
   - 需要选择一个节点ID.
  #### 流程图
  ![输入图片说明](./resource/WF/Admin/AttrNode/AccepterRole/Img/ShengqingFlow1.png "屏幕截图")
  #### 配置图
  ![输入图片说明](./resource/WF/Admin/AttrNode/AccepterRole/Img/ShengqingPeizhi1.png "屏幕截图")
      `);i(this,"Desc2",`
  #### 说明
  - 选择的字段存储的是作为人员身份(该字段里存储的是账号)
  - 指定节点表单的字段作为本步骤的本步骤的身份.
  - 需要选择一个节点ID.
  #### 流程图
  ![输入图片说明](./resource/WF/Admin/AttrNode/AccepterRole/Img/ShengqingFlow2.png "屏幕截图")
  #### 表单图
  ![输入图片说明](./resource/WF/Admin/AttrNode/AccepterRole/Img/ShengqingBiaodan2.png "屏幕截图")
  #### 配置图
  ![输入图片说明](./resource/WF/Admin/AttrNode/AccepterRole/Img/ShengqingPeizhi2.png "屏幕截图")
      `);this.PageTitle="人员身份规则"}Init(){return m(this,null,function*(){this.entity=new d,this.KeyOfEn=l.ShenFenModel,this.AddGroup("A","按组织结构绑定"),this.Blank("0"," 当前人员的身份（默认）",this.Desc0);const e=new d(this.PKVal);yield e.Retrieve();const r=e.FK_Flow;this.SelectItemsByList("1","指定节点的人员身份",this.Desc1,!1,p.SQLOfNodesOfFlow(r),l.ShenFenVal);const c="ND"+parseInt(e.FK_Flow)+"Rpt";let n=p.SQLOfMapAttrsGener(c);n=n.replace("MyPK","KeyOfEn"),this.SelectItemsByList("2","按表单字段值(人员编号)作为人员身份",this.Desc2,!1,n,l.ShenFenVal),this.SelectItemsByList("3","按表单字段值(部门编号)作为人员身份",this.Desc3,!1,n,l.ShenFenVal),this.SelectItemsByList("5","按表单字段值(部门编号,取该部门的领导)作为人员身份",this.Desc5,!1,n,l.ShenFenVal),this.SingleTB("4","按系统参数(部门编号)作为人员身份",l.ShenFenVal,this.Desc4,"请输入系统参数")})}AfterSave(e,r){}BtnClick(e,r,c){if(e==r||e===c)throw new Error("Method not implemented.")}}export{B as GPE_ShenFenModel};
