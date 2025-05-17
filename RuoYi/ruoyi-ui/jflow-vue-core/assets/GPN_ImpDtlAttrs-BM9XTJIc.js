var u=Object.defineProperty;var D=(l,e,t)=>e in l?u(l,e,{enumerable:!0,configurable:!0,writable:!0,value:t}):l[e]=t;var c=(l,e,t)=>D(l,typeof e!="symbol"?e+"":e,t);var n=(l,e,t)=>new Promise((o,m)=>{var f=s=>{try{a(t.next(s))}catch(r){m(r)}},S=s=>{try{a(t.throw(s))}catch(r){m(r)}},a=s=>s.done?o(s.value):Promise.resolve(s.value).then(f,S);a((t=t.apply(l,e)).next())});import{P as h,F as I,G as y,m as P}from"./entry/index-B5R3Coa4-1746862693206.js";import{MapAttr as _}from"./MapAttr-DcWjEeWW.js";import{D as F}from"./DBAccess-CZ0wdWXU.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";class v extends h{constructor(){super("GPN_ImpDtlAttrs");c(this,"Imp",`
  #### 帮助
  - 选择一个从表，然后导入字段.
  - 已经有的就自动创建.
  - 
  #### 配置图
  - sdfsdfsd
`);c(this,"Imp_SelectDtl",`
  #### 帮助
  - csdfsadfsad
  #### 配置图
  - sdfsdfsd
`);c(this,"Imp_SelectDtl_SelectFields",`
  #### 帮助
  - csdfsadfsad
  #### 配置图
  - sdfsdfsd
`);this.PageTitle="导入实体字段"}Init(){this.AddGroup("A","导入实体字段"),this.AddBlank("Imp","准备",this.Imp),this.SelectItemsByList("Imp.SelectDtl","选择从表",this.Imp_SelectDtl,!1,I.SQLOfMapDtl),this.SelectItemsByList("Imp.SelectDtl.SelectFields","选择字段",this.Imp_SelectDtl_SelectFields,!0,this.GenerTableFields)}GenerTableFields(){return n(this,null,function*(){const t=this.RequestVal("tb1","Imp.SelectDtl"),o=yield F.RunSQLReturnTable(I.SQLOfMapDtlFields(t));return JSON.stringify(o)})}GenerSorts(){return n(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,o,m,f,S){return n(this,null,function*(){if(t==="Imp.SelectDtl.SelectFields"){const a=this.PKVal,s=m.split(",");let r="";const i=new _;for(let p=0;p<s.length;p++){const d=s[p];if(i.MyPK=d,yield i.Retrieve(),i.MyPK=a+"_"+i.Key,(yield i.IsExits())==!0){r+=`@字段: ${d} - ${i.Name} 已经存在.`;continue}i.FK_MapData=a;try{yield i.Insert()}catch(N){continue}r+=`@字段: ${d} - ${i.Name} 成功导入...`}return new y(P.Message,r)}})}}export{v as GPN_ImpDtlAttrs};
