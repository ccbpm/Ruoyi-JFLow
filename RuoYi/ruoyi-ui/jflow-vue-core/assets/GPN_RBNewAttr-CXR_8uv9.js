var E=Object.defineProperty;var P=(r,a,t)=>a in r?E(r,a,{enumerable:!0,configurable:!0,writable:!0,value:t}):r[a]=t;var f=(r,a,t)=>P(r,typeof a!="symbol"?a+"":a,t);var u=(r,a,t)=>new Promise((i,l)=>{var A=s=>{try{n(t.next(s))}catch(o){l(o)}},K=s=>{try{n(t.throw(s))}catch(o){l(o)}},n=s=>s.done?i(s.value):Promise.resolve(s.value).then(A,K);n((t=t.apply(r,a)).next())});import{MapAttr as x}from"./MapAttr-DcWjEeWW.js";import{b as S,M as I}from"./MapExt-DVovzpWn.js";import{SysEnums as _}from"./SysEnum-DmKE2Ig7.js";import{P as M,F as y}from"./entry/index-B5R3Coa4-1746862693206.js";import{b as d}from"./antd-C8r6Ue4p.js";import"./Events-D9tOL1Ad.js";import"./vue-B6GVRDGm.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";class H extends M{constructor(){super("GPN_RBNewAttr");f(this,"DescAttrs",`
  #### 帮助
  - 什么是正则表达式，请baidu，这个名词。
  - 系统已经帮您准备一个常用正则表达式.
  - 请选择正确的事件，然后根据事件选择表达式.
  - 比如: 校验文本框是否是电话号码，应该在失去焦点，而不能在双击事件.
  #### 图例
  - 电话号码校验
  
`);f(this,"DescDtl",`
  #### 帮助
  - 自定义正则表达式, 请在文本框输入正则表达式，然后执行创建。
  - 请正确的选择事件然.
`);f(this,"DescAth",`
  #### 帮助
  - 自定义函数，就是在服务器上创建一个js文件，写入一个函数.
`);this.ForEntityClassID="TS.MapExt.RBAttr",this.PageTitle="新增影响的元素"}Init(){return u(this,null,function*(){this.AddGroup("A","影响的字段");const t=new x;t.setPKVal(this.RefPKVal),yield t.Retrieve();const i=t.FK_MapData;this.SelectItemsByGroupList("Attr","选择影响的字段","",!0,y.SQLOfRBNewGroup(i),y.SQLOfFrmSummaryFields(i)),this.SelectItemsByList("Dtl","影响的从表",this.DescDtl,!0,y.SQLOfDtls(i)),this.SelectItemsByList("Ath","影响的附件",this.DescAth,!0,y.SQLOfFullAth(i))})}GenerSorts(){return u(this,null,function*(){return null})}Save_TextBox_X(t,i,l,A,K){return u(this,null,function*(){const n=this.RequestVal("RefPKVal"),s=new x(n);yield s.Retrieve();const o=new _;yield o.Retrieve("EnumKey",s.UIBindKey);const R=new S;yield R.Retrieve("RefPKVal",n,"ExtType",t);const w=l.split(","),D=A.split(",");for(let h=0;h<o.length;h++){const p=o[h];for(let c=0;c<w.length;c++){const m=w[c],e=new I;e.MyPK=m+"_RBAction_"+p.IntKey,e.setPKVal(m+"_RBAction_"+p.IntKey),R.filter(B=>B.AttrOfOper===m).length>0!=!0&&(e.FK_MapData=s.FK_MapData,e.ExtModel="RBAction",e.ExtType=t,e.RefPKVal=n,e.AttrOfOper=m,e.Tag=D[c],e.Tag1=p.IntKey,e.Tag2=p.Lab,e.Tag3="0",yield e.Insert())}}d.info("设置成功,请关闭或者设置其他元素,进行编辑.")})}}export{H as GPN_RBNewAttr};
