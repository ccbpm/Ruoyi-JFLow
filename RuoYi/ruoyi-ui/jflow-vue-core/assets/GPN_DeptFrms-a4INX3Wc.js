var S=Object.defineProperty;var D=(r,s,t)=>s in r?S(r,s,{enumerable:!0,configurable:!0,writable:!0,value:t}):r[s]=t;var m=(r,s,t)=>D(r,typeof s!="symbol"?s+"":s,t);var p=(r,s,t)=>new Promise((n,a)=>{var o=e=>{try{i(t.next(e))}catch(l){a(l)}},c=e=>{try{i(t.throw(e))}catch(l){a(l)}},i=e=>e.done?n(e.value):Promise.resolve(e.value).then(o,c);i((t=t.apply(r,s)).next())});import{P as O,W as u,G as f,m as h}from"./entry/index-B5R3Coa4-1746862693206.js";import{DeptFrm as F}from"./DeptFrm-CRHuIrDA.js";import{b as R}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";class y extends O{constructor(){super("GPN_DeptFrms");m(this,"SelectOneDept",`
  #### 帮助
   - 由admin控制一个为部门设置申报表单.
   - 一个人拥有多个部门.
  `);m(this,"ImpExcel",`
  #### 帮助
   - 从excel导入数据.
   - 按照ccbpm的excel格式要求.
   - 格式文件位于 .  完善测试该方法.

  `);this.PageTitle="新建部门表单",this.ForEntityClassID="TS.YS.DeptFrm"}Init(){return p(this,null,function*(){if(u.IsAdmin==!1){R.error("err@您好:"+u.Name+",非管理员用户不能查看.");return}this.AddGroup("A","创建申报任务");let t=this.RequestVal("OrgNo");t||(t=this.RefPKVal);const n=`SELECT No,Name,ParentNo FROM Port_Dept WHERE OrgNo='${t}'`,a="SELECT No, Name,ParentNo FROM Sys_FormTree where name='预算表单'",o="SELECT No,Name,FK_FormTree GroupNo FROM Sys_MapData";this.SelectItemsByTree("SelectOneDept","选择部门",this.SelectOneDept,!1,n,t,!1),this.SelectItemsByGroupList("SelectOneDept.SelectOneFrm","选择表单","请选择要申报的表单",!1,a,o)})}GenerSorts(){return p(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(t,n,a,o,c){return p(this,null,function*(){if(t==="SelectOneDept.SelectOneFrm"){if(this.RequestVal("tb1","SelectOneDept").length<6){alert("必须选到部门！");return}const e=new F;if(e.MyPK=this.RequestVal("tb1","SelectOneDept")+"_"+a,(yield e.IsExits())==!0){alert("该数据已经存在.");return}e.FrmID=a,e.FrmName=o,e.DeptNo=this.RequestVal("tb1","SelectOneDept"),e.DeptName=this.RequestVal("tb2","SelectOneDept");let l=this.RequestVal("OrgNo");l||(l=this.RefPKVal);let N=this.RequestVal("tb2","SelectOneDept");return N.includes("总包")&&!N.includes("项目")&&(e.IsHZ=1),e.OrgNo=l,yield e.Insert(),new f(h.CloseAndReload,"增加成功")}})}}export{y as GPN_DeptFrms};
