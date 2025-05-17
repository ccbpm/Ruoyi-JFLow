var n=Object.defineProperty;var m=(t,e,r)=>e in t?n(t,e,{enumerable:!0,configurable:!0,writable:!0,value:r}):t[e]=r;var o=(t,e,r)=>m(t,typeof e!="symbol"?e+"":e,r);import{MapData as p,MapDataAttr as a}from"./MapData-D5zymw8O.js";import{PageBaseGroupEdit as s}from"./PageBaseGroupEdit-BXdNWKIo.js";import"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./EnumLab-CsLi93T0.js";import"./Help-D0bDMZWg.js";class u extends s{constructor(){super("GPE_FrmType");o(this,"zhangjie",`  
  #### 帮助
  - 填报的数据是有规则的大文档，比如合同、项目申报、操作手册等具有目录层次的表单.
  - 这个文档有章、节、内容三部分组成。
  - 节内容支持： 大文本、多字段、附件、图片附件、从表、自定义URL.
  - 内容展示丰富，填写直观方便。
    #### 图例1
  - ![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/zhangjie.png "屏幕截图.png")
  -
  `);o(this,"VSTO",`
  #### 帮助
  - VSTO表单也叫excel表单.
  - VSTO表单可以实现样式复杂的页面需求，通过Excel的展示方式，可以实现复杂的科学计算法。
  - 前端操作依赖于本机的excel系统，从网页上点击启动本机的excel程序，加载表单模版与数据，完成表单的展现。  
  #### 图例1
  - ![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/VSTO-Excel.png "表格模式")  
  `);o(this,"FoolForm",`

  #### 帮助
   - 视频教程: https://drive.weixin.qq.com/s?k=AOsAZQczAAY9NtLjEw
   - 设计方便，界面简洁清晰。
   - 字段的顺序可以通过拖拽实现移动,通过栅栏格来布局界面元素。
   - 可以通过定义文本属性来体现不同控件的展示要求（文本，单选，多选，定位，评分，多附件，地图，身份证识别等）满足表单要求。
 
  #### 图例1
  - ![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/FoolFrmD.png "屏幕截图.png")
   
  #### 图例2
  - ![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/FoolFrmD2.png "屏幕截图.png")
   
  `);o(this,"Developer",`
  #### 帮助
   - 支持编写js,Html模式，使用富文本编辑器开发.
  #### 样式
  ![输入图片说明](./resource/WF/Admin/AttrNode/FrmSln/Img/kaifaze.png "屏幕截图.png")
  `);this.PageTitle="表单工作模式"}Init(){this.entity=new p,this.KeyOfEn=a.FrmType,this.AddGroup("A","表单工作模式"),this.Blank("0","经典表单",this.FoolForm),this.Blank("9","开发者表单",this.Developer),this.Blank("10","章节表单",this.zhangjie),this.Blank("6","VSTO表单",this.VSTO)}BtnClick(r,i,l){}AfterSave(r,i){}}export{u as GPE_FrmType};
