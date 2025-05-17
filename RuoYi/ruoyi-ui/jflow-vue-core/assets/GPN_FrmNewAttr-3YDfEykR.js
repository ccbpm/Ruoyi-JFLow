var P=Object.defineProperty;var A=(n,s,e)=>s in n?P(n,s,{enumerable:!0,configurable:!0,writable:!0,value:e}):n[s]=e;var f=(n,s,e)=>A(n,typeof s!="symbol"?s+"":s,e);var p=(n,s,e)=>new Promise((F,a)=>{var d=o=>{try{t(e.next(o))}catch(l){a(l)}},N=o=>{try{t(e.throw(o))}catch(l){a(l)}},t=o=>o.done?F(o.value):Promise.resolve(o.value).then(d,N);t((e=e.apply(n,s)).next())});import{FrmNodeAttr as D}from"./FrmNode-DY1LmIad.js";import{FrmNodeFields as h,FrmNodeField as S}from"./FrmNodeField-jmHxYHc6.js";import{P as V,F as w}from"./entry/index-B5R3Coa4-1746862693206.js";import{FrmNodeCtrlSln as I}from"./FrmNodeCtrlSln-rfD3YJDt.js";import{MapAttrs as g}from"./MapAttr-DcWjEeWW.js";import{b as v}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";import"./MapDtlSln-BaND6Q4a.js";import"./FrmAttachmentSln-D_fymGio.js";import"./FrmAttachment-MhkNqka4.js";import"./Events-D9tOL1Ad.js";class j extends V{constructor(){super("GPN_FrmNewAttr");f(this,"DescAttrs",`
  #### 帮助
  - 什么是正则表达式，请baidu，这个名词。
  - 系统已经帮您准备一个常用正则表达式.
  - 请选择正确的事件，然后根据事件选择表达式.
  - 比如: 校验文本框是否是电话号码，应该在失去焦点，而不能在双击事件.
  #### 图例
  - 电话号码校验
  - 
`);f(this,"DescDtl",`
  #### 帮助
  - 自定义正则表达式, 请在文本框输入正则表达式，然后执行创建。
  - 请正确的选择事件然.
`);f(this,"DescAth",`
  #### 帮助
  - 自定义函数，就是在服务器上创建一个js文件，写入一个函数.
`);this.ForEntityClassID="TS.AttrNode.FrmNodeField",this.PageTitle="字段权限"}Init(){return p(this,null,function*(){this.AddGroup("A","字段权限11");const e=new I;e.setPKVal(this.RefPKVal),yield e.Retrieve();const F=e.FK_Frm,a=w.sqlGroupField(F),d=w.sqlFields(F);this.SelectItemsByGroupList("Attr","字段权限","",!0,a,d,!0,e.SelectedAttrs)})}GenerSorts(){return p(this,null,function*(){return Promise.resolve(JSON.stringify([]))})}Save_TextBox_X(e,F,a,d,N){return p(this,null,function*(){const t=new I;t.setPKVal(this.RefPKVal),yield t.Retrieve();const o=t.FK_Frm,l=new h;yield l.Retrieve(D.FK_Node,t.FK_Node,"FK_Frm",o);for(let i=0;i<l.length;i++){const m=l[i];a.indexOf(m.KeyOfEn+",")>=0||(yield m.Delete())}const _=new g;yield _.Retrieve("FK_MapData",o);const u=a.split(","),y=d.split(",");for(let i=0;i<u.length;i++){const m=u[i],r=new S;if(r.MyPK=t.FK_Frm+"_"+t.FK_Node+"_"+m,r.setPKVal(r.MyPK),l.filter(K=>K.KeyOfEn===m).length>0==!0)continue;const c=_.filter(K=>K.KeyOfEn==m)[0];r.FK_Flow=t.FK_Flow,r.FK_Node=t.FK_Node,r.FK_MapData=t.FK_Frm,r.KeyOfEn=m,r.Name=y[i],r.EleType="Field",r.UIIsEnable=c.UIIsEnable,r.UIVisible=c.UIVisible,r.IsNotNull=c.UIIsInput,r.DefVal=c.DefVal,r.Idx=i,yield r.Insert()}t.SelectedAttrs=a,yield t.Update(),v.info("设置成功,请关闭或者设置其他元素,进行编辑.")})}}export{j as GPN_FrmNewAttr};
