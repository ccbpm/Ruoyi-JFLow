var c=Object.defineProperty;var g=(i,e,t)=>e in i?c(i,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):i[e]=t;var o=(i,e,t)=>g(i,typeof e!="symbol"?e+"":e,t);var m=(i,e,t)=>new Promise((l,r)=>{var a=s=>{try{n(t.next(s))}catch(d){r(d)}},h=s=>{try{n(t.throw(s))}catch(d){r(d)}},n=s=>s.done?l(s.value):Promise.resolve(s.value).then(a,h);n((t=t.apply(i,e)).next())});import{PageBaseGroupEdit as L}from"./PageBaseGroupEdit-BXdNWKIo.js";import{MapDtl as M,MapDtlAttr as S}from"./MapDtl-B_Ep8ewM.js";import{ListShoModel2D as p}from"./ListShoModel2D-DDMe6pkX.js";import{ListShoModel3D as D}from"./ListShoModel3D-CM_xKMfY.js";import{F,H as w}from"./entry/index-B5R3Coa4-1746862693206.js";import"./Help-D0bDMZWg.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class T extends L{constructor(){super("GPE_ListShowModel");o(this,"Desc0",`
  #### 帮助
  - 定义: 从表展示方式为表格的形式。
  #### 表格模式效果图
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/ListShowModel/Img/ListShowModelBiaoge.png "从表模式")  
  `);o(this,"Desc1",`
  #### 帮助
  - 定义: 从表展示方式为卡片的形式。
  - 场景：数据量比较多, 有从表.
  #### 卡片模式效果图
  - 卡片
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/ListShowModel/Img/ListShowModelkapian.png "卡片模式")  

  #### 局部放大
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/ListShowModel/Img/ListShowModelkapian1.png "卡片模式")  

  `);o(this,"Desc2",`
  #### 帮助
  - 定义: 从表展示方式为自定义URL。
  - 说明：该模式下，是当现在的从表不能满足客户对数据展现采集的要求，需要写一个自定义的url实现，但是数据还是要存储在当前从表里面来。
  - 比如：对输入的复杂的计算,目前的从表不能控制到位.
  #### 效果图
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/ListShowModel/Img/SelfUrl.png "自定义rul")  
  `);o(this,"Desc6",`
  #### 帮助
  - 表单描述: 损益表模式，左侧是一个树结构的目录，右侧有两列数值的类型数据，本期，基期（同期）.
  #### 创建步骤
  1. 首先在字典库创建一个树结构字段, =>数据源 ==>本机数据源 ==>字典 ==>新建字典 ==>选择树结构字典，并维护数据内容.
  2. 根目录数据不计算.
  3. 在当前表单里，增加一个外键类型的字段，并选择该树结构的外键字典.
  4. 在从表属性里，找到展示模式=》选择损益表的模式。

  #### 效果图
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/ListShowModel/Img/ListShowMode6.png "自定义rul")  
  `);o(this,"DescD2",`
  #### 帮助
  - 如下图所示.
  - 从表里有三个字段，两个外键或者枚举类型的字段，一个数值类型的字段. 
  - 需要交叉数据展现模式，用于数据采集或者展现。
  - 从左侧开始,第1个字段是第1维度,头部是第2维度. 
  #### 表格模式效果图
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/ListShowModel/Img/D2.png "自定义rul")  
  - 维度1：年级.
  - 维度2：政治面貌.
  `);o(this,"DescD3Top",`
  #### 帮助
  - 如下图所示.
  - 从表里有四个字段，三个外键或者枚举类型，一个数值类型的字段. 
  - 需要交叉数据展现模式，用于数据采集或者展现。
  #### 表格模式效果图
  - 展现效果
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/ListShowModel/Img/D3Top.png "3维度左侧")  
  - 存储效果
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/ListShowModel/Img/YSTable.png "3维度左侧")  
  `);o(this,"DescD3Left",`
  #### 帮助
  - 如下图所示.
  - 从表里有四个字段，三个外键或者枚举类型，一个数值类型的字段. 
  - 需要交叉数据展现模式，用于数据采集或者展现。
  #### 表格模式效果图
  - 展现效果
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/ListShowModel/Img/D3Left.png "3维度左侧")  
  - 存储效果
  - ![输入图片说明](./resource/WF/Admin/FrmLogic/MapDtl/ListShowModel/Img/YSTable.png "3维度左侧")  
  `);this.PageTitle="展示模式"}Init(){this.entity=new M,this.KeyOfEn="ListShowModel",this.AddGroup("A","常规模式"),this.Blank("0","表格(默认)",this.Desc0),this.Blank("1","卡片模式",this.Desc1),this.SingleTB("2","自定义URL",S.UrlDtl,this.Desc2,"请输入自定义的url"),this.AddGroup("B","报表模式"),this.AddEntity("3","2维表",new p,this.DescD2),this.AddEntity("4","3维表(左)",new D,this.DescD3Left),this.AddEntity("5","3维表(上)",new D,this.DescD3Top),this.AddGroup("C","固定行表格");const t=this.RefPKVal;this.SelectItemsByList("6","损益表模式",this.Desc6,!1,F.SQLOfIncomeStatement(t),"InitDBAttrs"),this.AddEntity("7","大类小类",new p,this.DescD3Top)}BtnClick(t,l,r){}AfterSave(t,l){return m(this,null,function*(){if(t==="1"){const r=new w("BP.WF.HttpHandler.WF_Admin_FoolFormDesigner");r.AddPara("FrmID",this.RefPKVal),yield r.DoMethodReturnString("Designer_Init")}})}}export{T as GPE_ListShowModel};
