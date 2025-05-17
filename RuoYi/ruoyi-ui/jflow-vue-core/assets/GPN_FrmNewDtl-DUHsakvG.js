var S=Object.defineProperty;var f=(o,s,e)=>s in o?S(o,s,{enumerable:!0,configurable:!0,writable:!0,value:e}):o[s]=e;var l=(o,s,e)=>f(o,typeof s!="symbol"?s+"":s,e);var F=(o,s,e)=>new Promise((n,i)=>{var c=a=>{try{t(e.next(a))}catch(m){i(m)}},_=a=>{try{t(e.throw(a))}catch(m){i(m)}},t=a=>a.done?n(a.value):Promise.resolve(a.value).then(c,_);t((e=e.apply(o,s)).next())});import{FrmNodeAttr as w}from"./FrmNode-DY1LmIad.js";import{FrmNodeFields as P}from"./FrmNodeField-jmHxYHc6.js";import{P as u,F as h,B as M}from"./entry/index-B5R3Coa4-1746862693206.js";import{FrmNodeCtrlSln as K}from"./FrmNodeCtrlSln-rfD3YJDt.js";import{b as x}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";import"./MapDtlSln-BaND6Q4a.js";import"./FrmAttachmentSln-D_fymGio.js";import"./FrmAttachment-MhkNqka4.js";class b extends u{constructor(){super("GPN_FrmNewDtl");l(this,"DescAttrs",`
  #### 帮助
  - 什么是正则表达式，请baidu，这个名词。
  - 系统已经帮您准备一个常用正则表达式.
  - 请选择正确的事件，然后根据事件选择表达式.
  - 比如: 校验文本框是否是电话号码，应该在失去焦点，而不能在双击事件.
  #### 图例
  - 电话号码校验
  - 
`);l(this,"DescDtl",`
  #### 帮助
  - 自定义正则表达式, 请在文本框输入正则表达式，然后执行创建。
  - 请正确的选择事件然.
`);l(this,"DescAth",`
  #### 帮助
  - 自定义函数，就是在服务器上创建一个js文件，写入一个函数.
`);this.ForEntityClassID="TS.AttrNode.MapDtlSln",this.PageTitle="从表权限"}Init(){return F(this,null,function*(){this.AddGroup("A","从表权限");const e=new K;e.setPKVal(this.RefPKVal),yield e.Retrieve();const n=e.FK_Frm;this.SelectItemsByList("Dtl","影响的从表",this.DescDtl,!0,h.SQLOfNewDtlSQL(n),!0,!0,e.SelectedDtls)})}GenerSorts(){return F(this,null,function*(){return Promise.resolve(JSON.stringify([]))})}Save_TextBox_X(e,n,i,c,_){return F(this,null,function*(){const t=new K;t.setPKVal(this.RefPKVal),yield t.Retrieve(),t.SelectedDtls=i,yield t.Update();const a=t.FK_Frm;yield new P().Retrieve(w.FK_Node,t.FK_Node,"FK_MapData",a);const N=i.split(",");for(let p=0;p<N.length;p++){const d=N[p],D=d+"_"+t.FK_Node,r=new M("BP.WF.Template.Frm.MapDtlExt");r.setPK(D),(yield r.RetrieveFromDBSources())==0?(r.setPK(d),yield r.RetrieveFromDBSources(),r.setPK(D),r.FK_Node=t.FK_Node,r.FK_MapData=t.FK_Frm,r.No=D,r.setPara("EnName","TS.Frm.MapDtlExt"),yield r.Insert(),yield r.DoMethodReturnString("InitAttrsOfSelf")):r.FK_MapData!=t.FK_Frm+"_"+t.FK_Node&&(r.FK_MapData=t.FK_Frm+"_"+t.FK_Node,r.setPara("EnName","TS.Frm.MapDtlExt"),yield r.Update())}x.info("设置成功,请关闭或者设置其他元素,进行编辑.")})}}export{b as GPN_FrmNewDtl};
