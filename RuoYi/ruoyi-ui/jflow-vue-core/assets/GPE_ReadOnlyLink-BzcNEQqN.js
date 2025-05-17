var E=Object.defineProperty;var y=(n,i,t)=>i in n?E(n,i,{enumerable:!0,configurable:!0,writable:!0,value:t}):n[i]=t;var o=(n,i,t)=>y(n,typeof i!="symbol"?i+"":i,t);var s=(n,i,t)=>new Promise((r,p)=>{var d=e=>{try{l(t.next(e))}catch(a){p(a)}},x=e=>{try{l(t.throw(e))}catch(a){p(a)}},l=e=>e.done?r(e.value):Promise.resolve(e.value).then(d,x);l((t=t.apply(n,i)).next())});import{M as O,a as h}from"./MapExt-DVovzpWn.js";import{PageBaseGroupEdit as B}from"./PageBaseGroupEdit-BXdNWKIo.js";import{LinkAttr as m}from"./LinkAttr-B7azWVNR.js";import"./entry/index-B5R3Coa4-1746862693206.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./Help-D0bDMZWg.js";class g extends B{constructor(){super("GPE_ReadOnlyLink");o(this,"Desc0",`
  #### 帮助
  - 定义: 对输入的字段进行详尽的描述，文字特别多，就需要此功能.
  - 比如: 项目申报流程中，对项目的预期效益进行描述.
  #### 效果图
  - 暂无
  `);o(this,"HelpInfo",`
  #### 帮助 
  - 请输入该字段的详细描述信息.
  - 支持markdown 语法, 支持html.
  #### 图例
  - 暂无
  #### 运行图
  - 暂无
  `);o(this,"UrlRightOpen",`
  #### 帮助 
  - 请输入该字段的详细描述信息.
  - 支持markdown 语法, 支持html.
  #### 图例
  - 暂无
  #### 运行图
  - 暂无
  `);o(this,"UrlOpen",`
  #### 帮助 
  - 请输入该字段的详细描述信息.
  - 格式: http://11.112.11.2/xx.do?DoType=xx&JinE=@JinE&BianHao=@BillNo
  - 解析后的格式: http://11.112.11.2/xx.do?DoType=xx&JinE=123.99&BianHao=100-02
  - 解析说明, @BillNo,@JinE 就是字段名. 解析的时候，会把字段名替换掉.
  #### 图例
  - 暂无
  #### 运行图
  - 暂无
  `);o(this,"UrlWinOpen",`
  #### 帮助 
  - 请输入该字段的详细描述信息.
  - 格式: http://11.112.11.2/xx.do?DoType=xx&JinE=@JinE&BianHao=@BillNo
  - 解析后的格式: http://11.112.11.2/xx.do?DoType=xx&JinE=123.99&BianHao=100-02
  - 解析说明, @BillNo,@JinE 就是字段名. 解析的时候，会把字段名替换掉.
  #### 图例
  - 暂无
  #### 运行图
  - 暂无
  `);this.PageTitle="字段值链接"}Init(){return s(this,null,function*(){this.entity=new O,this.KeyOfEn=h.DoWay,yield this.entity.InitDataForMapAttr("ReadOnlyLink",this.GetRequestVal("PKVal")),this.AddGroup("A","字段值链接"),this.Blank("0","禁用",this.Desc0),this.SingleTextArea("HelpInfo","弹出帮助信息",h.Doc,"请按照格式输入内容",this.HelpInfo),this.SingleTB("UrlRightOpen","侧滑弹出url",h.Doc,this.UrlRightOpen,"请输入url"),this.AddEntity("UrlOpen","模态弹窗",new m,this.UrlOpen,""),this.AddEntity("UrlWinOpen","新窗口弹出url",new m,this.UrlWinOpen,"")})}AfterSave(t,r){if(t==r)throw new Error("Method not implemented.")}BtnClick(t,r,p){if(t==r||t===p)throw new Error("Method not implemented.")}}export{g as GPE_ReadOnlyLink};
