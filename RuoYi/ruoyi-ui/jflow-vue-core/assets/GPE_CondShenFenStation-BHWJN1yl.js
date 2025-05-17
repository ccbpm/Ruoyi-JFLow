var g=Object.defineProperty;var D=(r,n,e)=>n in r?g(r,n,{enumerable:!0,configurable:!0,writable:!0,value:e}):r[n]=e;var l=(r,n,e)=>D(r,typeof n!="symbol"?n+"":n,e);var S=(r,n,e)=>new Promise((i,s)=>{var o=p=>{try{a(e.next(p))}catch(h){s(h)}},c=p=>{try{a(e.throw(p))}catch(h){s(h)}},a=p=>p.done?i(p.value):Promise.resolve(p.value).then(o,c);a((e=e.apply(r,n)).next())});import{F as d}from"./entry/index-B5R3Coa4-1746862693206.js";import{Cond as m,CondAttr as t}from"./Cond-DK8vpfBx.js";import{PageBaseGroupEdit as A}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./Node-BsvTqXX9.js";import"./EntityNodeID-3BfNz0DC.js";import"./Help-D0bDMZWg.js";class N extends A{constructor(){super("GPE_CondShenFenStation");l(this,"Desc0",`
  #### 说明
   - 默认为该模式。
   - 提交人员登录部门就是，条件的判断参数.
    `);l(this,"Desc1",`
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
  
  `);l(this,"Desc2",`
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
      `);this.PageTitle="岗位条件人员身份"}Init(){return S(this,null,function*(){this.entity=new m,this.KeyOfEn=t.SpecOperWay;const e=new m(this.PKVal);yield e.Retrieve(),this.AddGroup("A","按发送节点提交人计算"),this.Blank("0","发送人登录部门下所有岗位",this.HelpUn),this.Blank("1","发送人的所有部门的岗位",this.HelpUn),this.Blank("2","发送人选择部门+岗位",this.HelpUn),this.AddGroup("B","按指定节点提交人计算");const i=d.SQLOfNodesOfFlow(e.FK_Flow);this.SingleDDLSQL("10","指定节点提交人的使用部门下所有岗位",t.SpecOperPara,this.HelpUn,i,!1),this.SingleDDLSQL("11","指定节点提交人的所有部门的岗位",t.SpecOperPara,this.HelpUn,i,!1),this.SingleDDLSQL("24","指定节点提交人的使用部门所使用的岗位",t.SpecOperPara,this.HelpUn,i,!1);const s="ND"+parseInt(e.FK_Flow)+"Rpt",o=d.SQLOfMapAttrsGener(s);this.AddGroup("C","按表单字段人员计算"),this.SingleDDLSQL("20","字段(参数)人员的主部门下所有的岗位",t.SpecOperPara,this.HelpUn,o,!1),this.SingleDDLSQL("21","字段(参数)人员的所有部门的岗位",t.SpecOperPara,this.HelpUn,o,!1),this.SingleDDLSQL("22","字段(参数)就是岗位编号",t.SpecOperPara,this.HelpUn,o,!1),this.AddGroup("D","按表单字段/系统参数部门计算"),this.SingleDDLSQL("30","字段部门下当前提交人的所有岗位",t.SpecOperPara,this.HelpUn,o,!1),this.SingleDDLSQL("31","字段部门下所有人员的所有部门的岗位",t.SpecOperPara,this.HelpUn,o,!1),this.SingleTB("32","系统参数部门下当前提交人的所有岗位",t.SpecOperPara,this.HelpUn,"请输入系统参数"),this.SingleTB("33","系统参数部门下所有人员的所有部门的岗位",t.SpecOperPara,this.HelpUn,"请输入系统参数")})}AfterSave(e,i){if(e==i)throw new Error("Method not implemented.")}BtnClick(e,i,s){if(e==i||e===s)throw new Error("Method not implemented.")}}export{N as GPE_CondShenFenStation};
