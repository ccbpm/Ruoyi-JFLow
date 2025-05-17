var h=Object.defineProperty;var u=(i,r,t)=>r in i?h(i,r,{enumerable:!0,configurable:!0,writable:!0,value:t}):i[r]=t;var n=(i,r,t)=>u(i,typeof r!="symbol"?r+"":r,t);var p=(i,r,t)=>new Promise((c,m)=>{var a=e=>{try{o(t.next(e))}catch(s){m(s)}},N=e=>{try{o(t.throw(e))}catch(s){m(s)}},o=e=>e.done?c(e.value):Promise.resolve(e.value).then(a,N);o((t=t.apply(i,r)).next())});import{P as B,W as l,G as x,m as G}from"./entry/index-B5R3Coa4-1746862693206.js";import{Y as I}from"./YSBatch-D14NCbYT.js";import{GloComm as S}from"./GloComm-B1xAfTWw.js";import f from"./Dev2InterfaceCCBill-BLVtAqN2.js";import{b as P}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";import"./Task-COGiiLiL.js";import"./GenerBill-BmQJhMDr.js";import"./EntityWorkID-B0nISxjT.js";import"./YSOrg-DLfj-WT-.js";import"./DeptFrm-CRHuIrDA.js";import"./PageBaseTreeEns-BuhhoiWI.js";import"./FrmSort-BhvgVTIc.js";import"./FrmAdm-w-27tFK4.js";import"./MapData-D5zymw8O.js";import"./EnumLab-CsLi93T0.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";class M extends B{constructor(){super("GPN_NewBatch");n(this,"Imp",`
  #### 帮助
   - 从其他部门的人员里导入人员，放入本部门中.
   - 一个人拥有多个部门.
  `);n(this,"ImpExcel",`
  #### 帮助
   - 从excel导入数据.
   - 按照ccbpm的excel格式要求.
   - 格式文件位于 . 完善测试该方法.

  `);this.PageTitle="新建批次号",this.ForEntityClassID="TS.YS.YSBatch"}Init(){return p(this,null,function*(){if(l.IsAdmin==!1){P.error("err@您好:"+l.Name+",非管理员用户不能查看.");return}this.AddGroup("A","创建申报任务"),this.TextBox2_NameNo("BatchNoName","输入批次ID与名称",this.HelpUn,"","任务编号","任务名称","")})}GenerSorts(){return p(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,c,m,a,N){return p(this,null,function*(){const o=new I;if(o.No=a,o.Name=m,(yield o.IsExits())==!0){alert("批次号已经存在.");return}yield o.Insert(),f.WriteTrack("YS_Batch",o.No,"创建批次任务.");const e=S.UrlEn("TS.YS.YSBatch",o.No);return new x(G.GoToUrl,e)})}}export{M as GPN_NewBatch};
