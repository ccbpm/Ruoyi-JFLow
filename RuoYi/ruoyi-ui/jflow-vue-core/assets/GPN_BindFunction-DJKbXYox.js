var f=Object.defineProperty;var y=(r,o,t)=>o in r?f(r,o,{enumerable:!0,configurable:!0,writable:!0,value:t}):r[o]=t;var l=(r,o,t)=>y(r,typeof o!="symbol"?o+"":o,t);var x=(r,o,t)=>new Promise((u,a)=>{var c=n=>{try{i(t.next(n))}catch(p){a(p)}},E=n=>{try{i(t.throw(n))}catch(p){a(p)}},i=n=>n.done?u(n.value):Promise.resolve(n.value).then(c,E);i((t=t.apply(r,o)).next())});import{MapAttr as T}from"./MapAttr-DcWjEeWW.js";import{BindFunction as d}from"./BindFunction-CUJbDW2I.js";import{RegularExpressionFactory as S}from"./RegularExpressionFactory-Bb-ijBMN.js";import{P as h}from"./entry/index-B5R3Coa4-1746862693206.js";import"./Events-D9tOL1Ad.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./MapExt-DVovzpWn.js";class I extends h{constructor(){super("GPN_BindFunction");l(this,"RegularExpressionLab",`
  #### 帮助
  - 什么是正则表达式，请baidu，这个名词。
  - 系统已经帮您准备一个常用正则表达式.
  - 请选择正确的事件，然后根据事件选择表达式.
  - 比如: 校验文本框是否是电话号码，应该在失去焦点，而不能在双击事件.
  #### 图例
  - 电话号码校验
  - 
`);l(this,"RegularExpressionSelf",`
  #### 帮助
  - 自定义正则表达式, 请在文本框输入正则表达式，然后执行创建。
  - 请正确的选择事件然.
`);l(this,"SelfFunc",`
  #### 帮助
  - 自定义函数，就是在服务器上创建一个js文件，写入一个函数.
  -  
`);l(this,"JSBody",`
#### 帮助
-  输入函数的脚本.
-  系统就会执行这些脚本，在您指定的事件里.
`);this.ForEntityClassID="TS.MapExt.BindFunction",this.PageTitle="新建绑定正则/函数"}Init(){this.AddGroup("A","绑定正则"),this.SelectItemsByGroupList("RegularExpressionLab","绑定正则表达式库","",!1,this.Group(),this.List()),this.TextBox2_NameNo("RegularExpressionSelf","自定义正则表达式",this.RegularExpressionSelf,"","正则表达式","提示信息","")}List(){return JSON.stringify(S.getLabs())}Group(){return JSON.stringify([{No:"onblur,onchange",Name:"onblur失去焦点,与onchange内容变化"},{No:"onblur",Name:"onblur失去焦点"},{No:"onchange",Name:"onchange内容变化"},{No:"onclick",Name:"onclick点击"},{No:"ondblclick",Name:"ondblclick双击"},{No:"onkeypress",Name:"onkeypress当键盘按键被按下并释放一个键时发生"},{No:"onkeyup",Name:"onkeyup释放键盘按键"}])}GenerSorts(){return x(this,null,function*(){return Promise.resolve([{No:"blur",Name:"blur失去焦点"},{No:"change",Name:"change内容变化"}])})}Save_TextBox_X(t,u,a,c,E){return x(this,null,function*(){const i=this.RequestVal("RefPKVal"),n=u,p=yield this.GetSortName(u),m=new T(i);yield m.Retrieve();const e=new d;e.ExtModel="BindFunction",e.Tag=n,e.Tag1=p,e.FK_MapData=m.FK_MapData,e.AttrOfOper=m.KeyOfEn,e.RefPKVal=i,e.Tag6=this.GetPageName(t);let s="TS.MapExt.BindFunction";if(t==="RegularExpressionLab"){e.ExtType="RegularExpression";const N=S.GetEn(a);e.Doc=N.Exp,e.Tag2=N.Message,s="TS.MapExt.RegularExpression"}t==="RegularExpressionSelf"&&(e.ExtType="RegularExpression",e.Doc=c,e.Tag2=a,s="TS.MapExt.RegularExpression"),t==="JSBody"&&(e.ExtType="JSBody",e.Doc=a,s="TS.MapExt.JSBody"),t==="SelfFunc"&&(e.ExtType="SelfFunc",e.Doc=c,s="TS.MapExt.SelfFunc"),e.SetPara("EnName",s),yield e.Insert();let g="";return g="/@/WF/Comm/En.vue?EnName="+s+"&PKVal="+e.MyPK,"url@"+g})}}export{I as GPN_BindFunction};
