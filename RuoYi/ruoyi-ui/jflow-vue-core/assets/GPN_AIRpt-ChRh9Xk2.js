var w=Object.defineProperty;var R=(s,r,e)=>r in s?w(s,r,{enumerable:!0,configurable:!0,writable:!0,value:e}):s[r]=e;var P=(s,r,e)=>R(s,typeof r!="symbol"?r+"":r,e);var i=(s,r,e)=>new Promise((a,d)=>{var o=n=>{try{t(e.next(n))}catch(l){d(l)}},u=n=>{try{t(e.throw(n))}catch(l){d(l)}},t=n=>n.done?a(n.value):Promise.resolve(n.value).then(o,u);t((e=e.apply(s,r)).next())});import c from"./HttpHandler-CdnQkxwF.js";import{P as p,aV as I,G as A,m as h}from"./entry/index-B5R3Coa4-1746862693206.js";import"./Events-D9tOL1Ad.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class y extends p{constructor(){super("GPN_AIRpt");P(this,"AlertCar",`
    我要做一个车辆管理表单。
    基础信息包含车辆编号，名称，车牌号、车辆类型(轿车、工程车、清洁车)等基础字段，请提供.
    从表有如下几个，请给出每个从表的字段。
    车辆配件：
    维修记录：
    出车记录：
    `);P(this,"SearchHelp",`
    我要做一个车辆管理表单。
    基础信息包含车辆编号，名称，车牌号、车辆类型(轿车、工程车、清洁车)等基础字段，请提供.
    从表有如下几个，请给出每个从表的字段。
    车辆配件：
    维修记录：
    出车记录：
    `);this.PageTitle="表单AI白色大屏"}Init(){return i(this,null,function*(){throw new Error("未实现")})}GenerWindows(){return i(this,null,function*(){const e=new c("BP.WF.HttpHandler.WF_Admin_AI");let a=this.RequestVal("tb1","SelectedBill");(a==null||a==null||a||a==""||a==" ")&&(a=this.RequestVal("tb1","SelectedEntity"),yield(yield I.GetEn(a)).Init()),e.AddPara("FrmID",a),e.AddPara("PageID",this.RequestVal("PageID"));const d=yield e.DoMethodReturnJson("WhiteRpt_GenerWindows");return JSON.stringify(d)})}GenerSorts(){return i(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,a,d,o,u){return i(this,null,function*(){if(e.includes("SelectedBill.Windows")==!0){const t=new c("BP.WF.HttpHandler.WF_Admin_AI");t.AddPara("FrmID",this.RequestVal("tb1","SelectedBill")),t.AddPara("PageID",this.RequestVal("PageID")),t.AddPara("Vals",d);const n=yield t.DoMethodReturnString("WhiteRpt_SaveWindows");return new A(h.CloseAndReload,n)}if(e.includes("SelectedEntity.Windows")==!0){const t=new c("BP.WF.HttpHandler.WF_Admin_AI");t.AddPara("FrmID",this.RequestVal("tb1","SelectedEntity")),t.AddPara("PageID",this.RequestVal("PageID")),t.AddPara("Vals",d);const n=yield t.DoMethodReturnString("WhiteRpt_SaveWindows");return new A(h.CloseAndReload,n)}})}}export{y as GPN_AIRpt};
