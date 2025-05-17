var N=Object.defineProperty;var d=(a,r,o)=>r in a?N(a,r,{enumerable:!0,configurable:!0,writable:!0,value:o}):a[r]=o;var u=(a,r,o)=>d(a,typeof r!="symbol"?r+"":r,o);var p=(a,r,o)=>new Promise((s,l)=>{var n=t=>{try{i(o.next(t))}catch(e){l(e)}},m=t=>{try{i(o.throw(t))}catch(e){l(e)}},i=t=>t.done?s(t.value):Promise.resolve(t.value).then(n,m);i((o=o.apply(a,r)).next())});import{MapAttr as P}from"./MapAttr-DcWjEeWW.js";import{FieldNumColor as g}from"./FieldNumColor-CdFW_jfC.js";import{P as c,G as h,m as x}from"./entry/index-B5R3Coa4-1746862693206.js";import"./Events-D9tOL1Ad.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./MapExt-DVovzpWn.js";class V extends c{constructor(){super("GPN_FieldNumColor");u(this,"Desc1",`
  #### 帮助
  - 用设定的颜色来显示主表或者列表，从表的数据值变化。
  - 颜色    数值：>=MinVal   and 数值 < MinVal
  #### 常用色值
  - 浅粉红  LightPink
  - 深红(猩红) Crimson
  -  	紫色 Purple
  - 靛青/紫兰色  Indigo
  - 	中蓝色  MediumBlue
  - 亮钢蓝 LightSteelBlue
  - 浅绿色(水色) Aqua
  - 春绿色SpringGreen
  - 橄榄 Olive
  - 棕色 Brown
  -  	灰色  Gray
  #### 颜色值
  - 可以通过url: http://xh.5156edu.com/page/z1015m9220j18754.html  获取.
  - 
`);this.ForEntityClassID="TS.MapExt.FieldNumColor",this.PageTitle="新建颜色范围"}Init(){this.AddGroup("A","绑定正则"),this.TextBox3_NameNoNote("RegularExpressionSelf","设置颜色",this.Desc1,null,"最小值","颜色值(点帮助)","最大值")}GenerSorts(){return p(this,null,function*(){return[]})}Save_TextBox_X(o,s,l,n,m){return p(this,null,function*(){const i=this.RequestVal("RefPKVal"),t=new P(i);yield t.Retrieve();const e=new g;return e.ExtModel="FieldNumColor",e.Tag=l,e.Tag1=n,e.Tag2=m,e.FK_MapData=t.FK_MapData,e.AttrOfOper=t.KeyOfEn,e.RefPKVal=i,e.SetPara("EnName","TS.MapExt.FieldNumColor"),yield e.Insert(),new h(x.CloseAndReload,"增加成功.")})}}export{V as GPN_FieldNumColor};
