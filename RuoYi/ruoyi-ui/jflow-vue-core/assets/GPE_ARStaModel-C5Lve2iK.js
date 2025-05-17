var u=Object.defineProperty;var f=(r,e,t)=>e in r?u(r,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):r[e]=t;var i=(r,e,t)=>f(r,typeof e!="symbol"?e+"":e,t);var l=(r,e,t)=>new Promise((o,s)=>{var p=n=>{try{c(t.next(n))}catch(A){s(A)}},a=n=>{try{c(t.throw(n))}catch(A){s(A)}},c=n=>n.done?o(n.value):Promise.resolve(n.value).then(p,a);c((t=t.apply(r,e)).next())});import{U as m,f as g,F as d}from"./entry/index-B5R3Coa4-1746862693206.js";import{EntityNodeID as I}from"./EntityNodeID-3BfNz0DC.js";import{PageBaseGroupEdit as N}from"./PageBaseGroupEdit-BXdNWKIo.js";import{Node as P}from"./Node-BsvTqXX9.js";import{NodeDepts as R,NodeDept as D}from"./NodeDept-DwTBCm8N.js";class S extends I{constructor(e){super("TS.WF.ARBindStationSpecSta"),e&&(this.NodeID=e)}get HisUAC(){const e=new m;return e.IsDelete=!1,e.IsUpdate=!0,e.IsInsert=!1,e}get EnMap(){const e=new g("WF_Node","按指定的岗位集合与设置的部门交集计算");return e.AddTBIntPK("NodeID",0,"节点ID",!0),e.AddTBString("ARStaModel",null,"部门规则",!1,!1,0,100,100,!0),e.AddTBString("ARStaPara",null,"参数",!1,!1,0,100,100,!0),e.AddTBString("NodeDepts",null,"部门",!0,!1,0,500,100,!0),e.SetPopTree("NodeDepts",d.srcDepts,d.srcDeptRoot,!0,"300px","500px","选择部门","icon-people"),e.AddTBAtParas(4e3),e.ParaFields=",ARStaModel,ARStaPara,",e.AddRM_GPE(new h,"icon-drop"),this._enMap=e,this._enMap}afterUpdate(){return l(this,null,function*(){return yield new R().Delete("FK_Node",this.NodeID),typeof this.NodeDepts!="string"||this.NodeDepts.split(",").forEach(o=>l(this,null,function*(){const s=new D;s.FK_Node=this.NodeID,s.FK_Dept=o,s.MyPK=this.NodeID+"_"+o,yield s.Insert()})),Promise.resolve(!0)})}}const E=Object.freeze(Object.defineProperty({__proto__:null,ARBindStationSpecSta:S},Symbol.toStringTag,{value:"Module"}));class h extends N{constructor(){super("GPE_ARStaModel");i(this,"Help0",`
  #### 说明
   - 提交人所有的岗位集合,与当前的岗位集合匹配.
    `);i(this,"Help1",`
    #### 说明
     - 提交人登录部门下的岗位集合.
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
      `);this.PageTitle="角色集合范围"}Init(){return l(this,null,function*(){this.entity=new S,this.KeyOfEn="ARStaModel";const t=new P(this.PKVal);yield t.Retrieve();const o=t.FK_Flow;this.AddGroup("A","按发送节点发送人计算"),this.Blank("0","发送人所有的角色",this.Help0),this.Blank("1","发送人使用的角色",this.Help1),this.AddGroup("B","按指定节点提交人计算");const s=d.SQLOfNodesOfFlow(o);this.SelectItemsByList("10","指定节点提交人的使用角色",this.HelpUn,!1,s,"ARStaPara",""),this.SelectItemsByList("11","指定节点提交人的所有角色",this.HelpUn,!1,s,"ARStaPara",""),this.AddGroup("C","按表单字段计算");const p="ND"+parseInt(o)+"Rpt",a=d.SQLOfMapAttrsGener(p);this.SelectItemsByList("20","字段(参数)值是人员编号-所有角色",this.HelpUn,!1,a,"ARStaPara",""),this.SelectItemsByList("21","字段(参数)值是角色编号",this.HelpUn,!1,a,"ARStaPara","")})}AfterSave(t,o){if(t==o)throw new Error("Method not implemented.")}BtnClick(t,o,s){if(t==o||t===s)throw new Error("Method not implemented.")}}const G=Object.freeze(Object.defineProperty({__proto__:null,GPE_ARStaModel:h},Symbol.toStringTag,{value:"Module"}));export{S as A,h as G,E as a,G as b};
