var B=Object.defineProperty;var h=(o,e,n)=>e in o?B(o,e,{enumerable:!0,configurable:!0,writable:!0,value:n}):o[e]=n;var N=(o,e,n)=>h(o,typeof e!="symbol"?e+"":e,n);var d=(o,e,n)=>new Promise((f,S)=>{var I=r=>{try{s(n.next(r))}catch(t){S(t)}},E=r=>{try{s(n.throw(r))}catch(t){S(t)}},s=r=>r.done?f(r.value):Promise.resolve(r.value).then(I,E);s((n=n.apply(o,e)).next())});import{SysEnumMain as M}from"./SysEnumMain-NEBgQX-D.js";import{P as K,W as T,C as O,G as l,m,a2 as U,D as g}from"./entry/index-B5R3Coa4-1746862693206.js";import{GloComm as x}from"./GloComm-B1xAfTWw.js";import{b as C}from"./antd-C8r6Ue4p.js";import"./SysEnum-DmKE2Ig7.js";import"./vue-B6GVRDGm.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";const p=class p extends K{constructor(){super("GPN_Enum"),this.PageTitle="新建枚举",this.ForEntityClassID="TS.FrmUI.SysEnumMain"}Init(){this.AddGroup("B","新建枚举"),this.TextBox3_NameNoNote("NewIntEnum","新建int类型枚举",p.NewIntEnum,"","枚举ID","枚举名称","请输入内容(比如:男,女)","")}GenerSorts(){return d(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,n,f,S,I){return d(this,null,function*(){const E=f,s=S;let r=I.trim();const t=new M;if(T.CCBPMRunModel==O.Single?t.No=s:t.No=T.OrgNo+"_"+s,t.EnumKey=s,(yield t.IsExits())===!0){C.warning("枚举值已经存在"+s);return}if(t.Name=E,t.OrgNo=T.OrgNo,r=r.replaceAll("，",","),r=r.replaceAll("＠","@"),r=r.replaceAll("＝","="),e==="NewIntEnum"){if(!r.includes(",")&&!r.includes("@"))return new l(m.Error,"多个枚举值使用 , 或 @ 符号分开.");for(let a=0;a<20;a++)t.SetValByKey("Idx"+a,a);r.indexOf("@")==-1?r.split(",").forEach((u,i)=>{t.SetValByKey("Idx"+i,i),t.SetValByKey("Val"+i,u)}):U(r).forEach((u,i)=>{const[c,y]=u.split("=");t.SetValByKey("Idx"+i,c),t.SetValByKey("Val"+i,y)}),t.EnumType=0,t.CfgVal=r,t.SetPara("EnName","TS.FrmUI.SysEnumMainInt");try{yield t.Insert()}catch(a){yield t.Insert()}yield t.SaveDtls();const w=x.UrlEn("TS.FrmUI.SysEnumMainInt",t.No);return new l(m.GoToUrl,w)}if(e==="NewStrEnum"){if(r.indexOf(",")==-1)return new l(m.Error,"多个枚举值使用逗号分开.");if(r.indexOf("@")==-1){const a=r.split(",");let u="",i=-1;a.forEach(c=>{if(i++,c.indexOf("=")==-1)return new l(m.Error,"枚举键和枚举值使用等号连接.");const y=c.split("=");if(g.IsNullOrEmpty(y[0]))return new l(m.Error,"请填写枚举键.");if(g.IsNullOrEmpty(y[1]))return new l(m.Error,"请填写枚举值.");u+="@"+c,t.SetValByKey("Idx"+i,y[0]),t.SetValByKey("Val"+i,y[1])}),r=u}t.EnumType=1,t.EnumKey=s,t.Name=E,t.CfgVal=r,t.SetPara("EnName","TS.FrmUI.SysEnumMainString");try{yield t.Insert()}catch(a){yield t.Insert()}yield t.SaveDtls();const w=x.UrlEn("TS.FrmUI.SysEnumMainString",t.No);return new l(m.GoToUrl,w)}})}};N(p,"NewStrEnum",`
  #### 帮助
  - 填写格式: 枚举值=枚举标签;
  - 例如1: ty=团员,dy=党员,qz=群众
  - 例如2: shijia=事假,bingjia=病假,hunjia=婚假
  #### 数据存储.
  - string类型的枚举也称为标记枚举,字母存储一个列,标签存储一个列.
  - 在表单里字段是abc,那系统就会自动创建一个影子字段 abcT.
  - abc字段存储的是标记, abcT存储的是标签.
  - 这一点与外部数据源存储一致.
  `),N(p,"NewIntEnum",`
  #### 帮助
  - 填写格式0: 事假,病假,其它
  - 填写格式1: 团员,党员,群众
  - 填写格式2: @0=团员@1=党员@2=群众
  #### 数据存储
  - int类型的枚举值是常用的数据类型，ccfrom是格式化的存储到数据表里.
  - 创建一个int类型的字段，用于存储枚举的数据.
  `);let V=p;export{V as GPN_Enum};
