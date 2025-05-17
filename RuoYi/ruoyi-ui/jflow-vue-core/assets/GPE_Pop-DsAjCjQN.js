var P=Object.defineProperty;var l=(t,r,o)=>r in t?P(t,r,{enumerable:!0,configurable:!0,writable:!0,value:o}):t[r]=o;var i=(t,r,o)=>l(t,typeof r!="symbol"?r+"":r,o);var m=(t,r,o)=>new Promise((s,p)=>{var e=n=>{try{c(o.next(n))}catch(g){p(g)}},a=n=>{try{c(o.throw(n))}catch(g){p(g)}},c=n=>n.done?s(n.value):Promise.resolve(n.value).then(e,a);c((o=o.apply(t,r)).next())});import{M as d,P as h}from"./MapExt-DVovzpWn.js";import{PopGroupList as F}from"./PopGroupList-D9HMmBZQ.js";import{PopSelfUrl as L}from"./PopSelfUrl-BfCl-PpZ.js";import{PopTree as A}from"./PopTree-DuZ7yhrR.js";import{PopTreeEns as u}from"./PopTreeEns-CBZdvgwq.js";import{PopTreeEnsSFTable as E}from"./PopTreeEnsSFTable-BpiWyAAD.js";import{GloComm as f}from"./GloComm-B1xAfTWw.js";import{PageBaseGroupEdit as T}from"./PageBaseGroupEdit-BXdNWKIo.js";import{G as M,m as b}from"./entry/index-B5R3Coa4-1746862693206.js";import{MapAttr as w}from"./MapAttr-DcWjEeWW.js";import{PopTableSearch as B}from"./PopTableSearch-BYs5ZIad.js";import{PopTableSimple as I}from"./PopTableSimple-C7HyLKhr.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./Events-D9tOL1Ad.js";import"./Help-D0bDMZWg.js";class $ extends T{constructor(){super("GPE_Pop");i(this,"Desc1",`

  #### 帮助
  
  - **弹窗（Pop）返回值**：点击文本框后面的小齿轮会弹出一个窗体，用户在窗体中选择内容并点击“确定”按钮后，所选的值将被填充到该文本框中。
  - **填充设置**：在文本框中填充值后，想要填充将其他相关值填充到其他控件中时，可以在配置页面设置填充。
  - **数据源维护**：默认会使用本机数据源（local），如果您需要查询其他数据源请参考 [数据源配置](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=7671494&doc_id=31094)
  #### 应用场景
  - **获取外部数据**：当需要对一个文本框输入的数据进行外部数据获取时。
    - 例如：选择参与人、选择产品、选择客户等。
  - **数据选择**：当输入的数据需要进行选择时。

  #### 运行效果图
  ![运行效果图](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/Demo.png "效果图.png") 
  
  #### 弹窗-效果图
  - **多种弹窗返回值**：为了满足不同模式下的弹窗内容显示需求，我们提供了多种弹窗返回值。您可以根据不同的场景设置不同的模式。
  #### 树干叶子模式
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/BranchesAndLeaf.png "屏幕截图.png") 
  #### **树干模式**
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/Branches.png "树结构效果图.png")
  #### **分组列表模式**
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/GroupList.png "屏幕截图.png") 
  #### **单实体平铺模式**
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/TableList.png "屏幕截图.png")
  #### **表格分页模式**
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/TableSearch.png "屏幕截图.png")
  #### 数据存储格式
  - 在表单创建字段时，系统默认创建一个影子字段。
  - 字段abc, 系统在创建一个abcT, 在abc字段中存储的是编号, 在abcT字段中存储的是名称, 多个数据用逗号分开.
  - 如下图所示：
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/DataSave.png "屏幕截图.png")
     `);i(this,"PopBranchesAndLeaf",` 
  #### 说明
  - **树干叶子模式**：该模式下最经典的是部门树与人员的结构。部门作为树干，人员作为叶子。我们将这种模式称为“树干叶子模式”。
  - **类似模式**：与此相类似的还有流程树与流程的关系、表单库与表单的关系。
  - **数据源维护**：默认会使用本机数据源（local），如果您需要查询其他数据源请参考 [数据源配置](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=7671494&doc_id=31094)
  
  #### 配置图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/BranchesAndLeaf2.png "屏幕截图.png")    
    
  #### 效果图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/BranchesAndLeaf.png "屏幕截图.png")
  `);i(this,"PopBranches",` 
  #### 说明
   - 弹窗的数据展现为树结构，比如：部门、类别等等。
   - 数据结构为常见通用的编号、名称、父节点编号规则。
   - 点击上方按钮可以设置属性。
   
  #### 配置图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/Branches2.png "屏幕截图.png") 
  
  #### 效果图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/Branches.png "树结构效果图.png")
  `);i(this,"PopTableSearch",` 

  #### 帮助
   - 数据是以表格的模式展现，可以设置查询条件, 比如选择单据、产品、所在班级。
   - 适应数据量较大，需要搜索完成。
  #### 配置图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/TableSearch2.png "屏幕截图.png")
  #### 运行效果图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/TableSearch.png "屏幕截图.png")
  
  `);i(this,"PopSelfUrl",` 
  #### 帮助
   - 当ccflow提供的模式不能满足您的要求的时候，这个方案就是终极解决办法。
   - 您自己定义一个页面，配置到系统中去. 
   - 返回的数据，需要满足ccflow的规范,请参考示例. /DataUser/

  #### 配置图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/Url2.png "屏幕截图.png")


  #### 效果图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/Url.png "屏幕截图.png")

  
  `);i(this,"PopTableList",` 

  #### 帮助
   - 单实体平铺，就是对数据源进行简单的宫格列表展示，方便用户选择。
   - 是最简单的一种弹窗数据展现模式，适用于数据量较小，没有数据展示分组的需要。
  
  #### 配置图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/TableList2.png "屏幕截图.png")  

  #### 效果图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/TableList.png "屏幕截图.png")


   `);i(this,"PopGroupList",` 

  #### 帮助
   - 分组列表平铺,就是对实体进行分组展示. 例如: 产品类别与产品。 角色类型与角色。
   - 产品类别角色类型就是分组数据源，产品与角色就是实体数据源。
   - 实体数据源要求返回三个列，最后一列就是与分组数据源对应的外键列。
   - 请参考配置图。
  
  #### 配置图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/GroupList2.png "屏幕截图.png") 

  #### 效果图
  ![输入图片说明](./resource/WF/Admin/FrmLogic/MapExt/Pop/Img/GroupList.png "屏幕截图.png")
  
    `);this.PageTitle="弹窗返回值"}AfterSave(o,s){return m(this,null,function*(){if(o!="None"){let p=this.GetRequestVal("PKVal");p.endsWith("_Pop")&&(p=p.replace("_Pop",""));const e=new w,a=p;e.setPKVal(p+"T"),(yield e.RetrieveFromDBSources())==0&&(e.setPKVal(a),yield e.RetrieveFromDBSources(),e.MyPK=e.MyPK+"T",e.KeyOfEn=e.KeyOfEn+"T",e.Name=e.Name+"T",e.UIVisible=0,e.UIIsEnable=0,yield e.Insert())}if(o==s)throw new Error("Method not implemented.")})}BtnClick(o,s,p){return m(this,null,function*(){var e;if(p==="落值填充"||p==="填充"){const a=f.UrlEn("TS.MapExt.FullData",(e=this.entity)==null?void 0:e.MyPK);return new M(b.OpenUrlByDrawer75,a)}})}Init(){return m(this,null,function*(){this.entity=new d,this.KeyOfEn="DoWay",this.Btns=[{pageNo:"PopBranchesAndLeaf",list:["填充"]},{pageNo:"PopBranches",list:["填充"]},{pageNo:"PopGroupList",list:["填充"]},{pageNo:"PopTableList",list:["填充"]},{pageNo:"PopTable",list:["填充"]},{pageNo:"PopTableSimple",list:["填充"]},{pageNo:"PopSelfUrl",list:["填充"]},{pageNo:"PopBranches",list:["填充"]}],yield this.entity.InitDataForMapAttr("Pop",this.GetRequestVal("PKVal"),"None"),this.AddGroup("A","树形结构"),this.Blank("None","无,不设置(默认).",this.Desc1),this.AddEntity("PopBranchesAndLeaf","树干叶子模式",new u,this.PopBranchesAndLeaf),this.AddEntity("PopBranchesAndLeafSFTable","树干叶子模式(绑定字典表)",new E,this.PopBranchesAndLeaf),this.AddEntity("PopBranches","树干模式",new A,this.PopBranches),this.AddGroup("B","分组模式"),this.AddEntity("PopGroupList","分组列表平铺",new F,this.PopGroupList),this.AddEntity("PopTableList","单实体平铺",new h,this.PopTableList),this.AddGroup("C","其他模式"),this.AddEntity("PopTable","表格-分页模式",new B,this.PopTableSearch),this.AddEntity("PopTableSimple","表格-简洁模式",new I,this.PopTableSearch),this.AddEntity("PopSelfUrl","自定义URL",new L,this.PopSelfUrl)})}}export{$ as GPE_Pop};
