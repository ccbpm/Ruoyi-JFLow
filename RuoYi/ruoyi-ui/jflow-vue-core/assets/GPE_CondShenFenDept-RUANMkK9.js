var g=Object.defineProperty;var F=(i,t,e)=>t in i?g(i,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):i[t]=e;var a=(i,t,e)=>F(i,typeof t!="symbol"?t+"":t,e);var c=(i,t,e)=>new Promise((r,o)=>{var p=s=>{try{l(e.next(s))}catch(m){o(m)}},S=s=>{try{l(e.throw(s))}catch(m){o(m)}},l=s=>s.done?r(s.value):Promise.resolve(s.value).then(p,S);l((e=e.apply(i,t)).next())});import{F as h}from"./entry/index-B5R3Coa4-1746862693206.js";import{Cond as d,CondAttr as n}from"./Cond-DK8vpfBx.js";import{PageBaseGroupEdit as A}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./Node-BsvTqXX9.js";import"./EntityNodeID-3BfNz0DC.js";import"./Help-D0bDMZWg.js";class M extends A{constructor(){super("GPE_CondShenFenDept");a(this,"Desc0",`
  #### 说明
   - 默认为该模式。
   - 提交人员登录部门就是，条件的判断参数.
    `);a(this,"Desc1",`
  #### 说明
   - 指定节点的处理人作为本步骤的身份。
   - 如下图，设备维修申请人，为公司不同部门，当设备部人员检查后，认定该设备可以维护并给出报价，这时审批权就交还给申请部门的领导。
   - 那么在转向条件就设置为按角色选择，也就是把各部门领导角色的人选择出来，再确认申请人的部门，这样，具有审批权的接收人就是申请人的部门领导。
  #### 流程图
  ![输入图片说明](./resource/WF/Admin/Cond2020/Img/CondShenFenModel.png "屏幕截图")
  #### 配置图
  - 选择新增方向条件
  ![输入图片说明](./resource/WF/Admin/Cond2020/Img/CondShenFenModelSetting.png "屏幕截图")
  - 选择人员身份
  ![输入图片说明](./resource/WF/Admin/Cond2020/Img/CondShenFenModelSetting2.png "屏幕截图")
      `);a(this,"Desc2",`
  #### 说明
  - 选择的字段存储的是作为人员身份(该字段里存储的是账号)
  - 指定节点表单的字段作为本步骤的本步骤的身份。
  - 需要选择一个节点ID.
  #### 流程图
  ![输入图片说明](./resource/WF/Admin/AttrNode/AccepterRole/Img/ShengqingFlow2.png "屏幕截图")
  #### 表单图
  ![输入图片说明](./resource/WF/Admin/AttrNode/AccepterRole/Img/ShengqingBiaodan2.png "屏幕截图")
  #### 配置图
  ![输入图片说明](./resource/WF/Admin/AttrNode/AccepterRole/Img/ShengqingPeizhi2.png "屏幕截图")
      `);a(this,"Desc3",`
  #### 说明
   - 获取当前人员信息的是按照指定的部门与指定的角色的交集计算.
   - 指定的部门是从ccbpm的系统参数获取的.
   - 在流程运行的过程中系统参数，是通过 Flow_SavePara() 的方法保存到ccbpm中的.
   - 流程的系统参数存储在 表:WF_GenerWorkFlow 字段:AtPara 中.
   #### 其他
   - 请阅读ccbpm的接口方法，保存参数.

    `);this.PageTitle="人员身份"}Init(){return c(this,null,function*(){this.entity=new d,this.KeyOfEn=n.SpecOperWay;const e=new d(this.PKVal);yield e.Retrieve(),this.AddGroup("A","按发送节点提交人计算"),this.Blank("0"," 发送人登录的部门",this.HelpUn),this.Blank("1"," 发送人的所有部门",this.HelpUn),this.Blank("2"," 发送人使用的部门",this.HelpUn),this.Blank("3"," 发送人使用部门的父级",this.HelpUn),this.AddGroup("B","按指定节点提交人计算");const r=h.SQLOfNodesOfFlow(e.FK_Flow);this.SelectItemsByList("10","指定节点提交人的使用部门",this.HelpUn,!1,r,n.SpecOperPara,"Tag1"),this.SelectItemsByList("11","指定节点提交人的所有部门",this.HelpUn,!1,r,n.SpecOperPara,"Tag1"),this.SelectItemsByList("12","指定节点提交人的主部门",this.HelpUn,!1,r,n.SpecOperPara,"Tag1"),this.AddGroup("C","按表单字段人员计算");const o="ND"+parseInt(e.FK_Flow)+"Rpt",p=h.SQLOfMapAttrsGener(o);this.SelectItemsByList("20","字段(参数)值是人员编号-主部门",this.HelpUn,!1,p,n.SpecOperPara,"Tag1"),this.SelectItemsByList("21","字段(参数)值是人员编号-所有部门",this.HelpUn,!1,p,n.SpecOperPara,"Tag1"),this.SelectItemsByList("22","字段(参数)值是部门编号",this.HelpUn,!1,p,n.SpecOperPara,"Tag1"),this.SingleTB("23","系统参数值是部门编号",n.SpecOperPara,this.Desc3,"请输入系统参数")})}AfterSave(e,r){if(e==r)throw new Error("Method not implemented.")}BtnClick(e,r,o){if(e==r||e===o)throw new Error("Method not implemented.")}}export{M as GPE_CondShenFenDept};
