var u=Object.defineProperty;var I=(i,t,r)=>t in i?u(i,t,{enumerable:!0,configurable:!0,writable:!0,value:r}):i[t]=r;var n=(i,t,r)=>I(i,typeof t!="symbol"?t+"":t,r);var p=(i,t,r)=>new Promise((s,e)=>{var l=o=>{try{a(r.next(o))}catch(m){e(m)}},c=o=>{try{a(r.throw(o))}catch(m){e(m)}},a=o=>o.done?s(o.value):Promise.resolve(o.value).then(l,c);a((r=r.apply(i,t)).next())});import{P as N,G as b,m as f}from"./entry/index-B5R3Coa4-1746862693206.js";import{GenerWorkers as P}from"./GenerWorker-Cwh_K7rr.js";import h from"./Dev2InterfaceCCBill-BLVtAqN2.js";import{b as G}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";class C extends N{constructor(){super("GPN_BillReturn");n(this,"Imp",`
  #### 帮助
   - 上传模板、选择模式进行导入流程操作.
  ##### 选择模式说明
   - 作为新流程导入1：由ccbpm自动生成新的流程编号
   - 作为新流程导入2：使用流程模版里面的流程编号，如果该编号已经存在系统则会提示错误
   - 作为新流程导入3：使用流程模版里面的流程编号，如果该编号已经存在系统则会覆盖此流程
  `);n(this,"BPMN2",`
  #### 帮助
  - 导入符合bpmn2.0格式的文件.
  `);this.PageTitle="退回",this.SortNameLabel="退回到"}Init(){this.TextArea("Info","退回信息",this.HelpTodo,"退回原因","不同意，请重新修改","请输入退回原因，不能为空")}GenerSorts(){return p(this,null,function*(){const r=new P;yield r.Retrieve("WorkID",this.RequestVal("WorkID"),"PassSta",2,"Idx");for(let s=0;s<r.length;s++){const e=r[s];e.No=e.Idx,e.Name=e.EmpNo+","+e.EmpName}return r})}Save_TextBox_X(r,s,e,l,c){return p(this,null,function*(){if(r=="Info")try{const a=this.RequestVal("WorkID"),o=yield h.ReturnWork(Number.parseInt(a),Number.parseInt(s),e);return alert(o),new b(f.Message,o)}catch(a){G.error(a)}})}}export{C as GPN_BillReturn};
