var o=Object.defineProperty;var s=(e,t,i)=>t in e?o(e,t,{enumerable:!0,configurable:!0,writable:!0,value:i}):e[t]=i;var p=(e,t,i)=>s(e,typeof t!="symbol"?t+"":t,i);import{MapDtl as l}from"./MapDtl-B_Ep8ewM.js";import{D as r}from"./entry/index-B5R3Coa4-1746862693206.js";import{PageBaseGroupEdit as d}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";class S extends d{constructor(){super("GPE_DtlVSTOEditModel");p(this,"Desc0",`  
  #### 帮助
  - 使用表格的方式编辑数据，如下图.
  - 适用于列较少，数据量小，编辑简单直观. 
  #### 图例
  - 表格模式
  -
  `);p(this,"Desc1",`
  #### 帮助
  - 使用表单的方式编辑数据，如下图.
  - 适用于列较多，有孙表，编辑新建需要弹窗.  
  #### 列表图例
  - 点击红色的区域，新建与编辑.
  - 列表的数据都是只读的.
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/EditModel/Card1.png "表格模式")  
  #### 编辑图例
  - 从表就是一个新的表单.
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/EditModel/Card2.png "表格模式")  
  - 可以使用【保存并新建】，【删除】等操作.
  `);p(this,"Desc2",`
  #### 帮助
  - 同经典表单，只是表单的展示不同.
  `);this.PageTitle="VSTO从表工作模式"}Init(){this.entity=new l,this.KeyOfEn="VSTOEditModel",this.AddGroup("A","VSTO从表工作模式"),this.Blank("0","普通类型展示",this.HelpUn),this.SingleTB("1","平铺从表展示","VSTOEditPara",this.HelpUn,"输入平铺的从表数量，默认为2.",r.AppInt),this.SingleTB("2","平铺卡片记录展示","VSTOEditPara",this.HelpUn,"输入平铺的卡片数量，默认为0",r.AppInt)}BtnClick(i,a,n){}AfterSave(i,a){}}export{S as GPE_DtlVSTOEditModel};
