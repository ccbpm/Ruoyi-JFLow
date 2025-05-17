var l=(u,e,n)=>new Promise((i,a)=>{var D=r=>{try{s(n.next(r))}catch(o){a(o)}},S=r=>{try{s(n.throw(r))}catch(o){a(o)}},s=r=>r.done?i(r.value):Promise.resolve(r.value).then(D,S);s((n=n.apply(u,e)).next())});import{l as d,U as c,f as p}from"./entry/index-B5R3Coa4-1746862693206.js";import{SFDBSrc as m}from"./SFDBSrc-DbkqYXE6.js";import{SysEventAttr as t}from"./SysEvent-DymzJjDC.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class B extends d{constructor(e){super("TS.Sys.SysEventDBSrc"),e&&(this.MyPK=e)}get HisUAC(){const e=new c;return e.IsDelete=!0,e.IsUpdate=!0,e.IsInsert=!0,e}get EnMap(){const e=new p("Sys_FrmEvent","数据源事件");return e.AddMyPK(),e.AddTBString(t.RefPKVal,null,"关键值",!1,!1,0,100,10),e.AddTBString(t.EventID,null,"事件ID",!0,!0,0,100,150),e.AddTBString(t.EventName,null,"事件名称",!0,!0,0,100,150),e.AddTBString(t.EventDoType,null,"执行ID",!0,!0,0,100,100),e.AddTBString(t.EventDoTypeT,null,"执行标记",!0,!0,0,100,100),e.AddDDLEntities(t.FK_DBSrc,"local","数据源",new m,!0,null,!1),e.AddTBStringDoc(t.DoDoc,null,"SQL/存储过程",!0,!1,!0,`
    #### 帮助
    - 请填写SQL或者存储过程
    - 支持ccbpm表达式.
    #### SQL的demo.
    - 支持ccbpm表达式.
    - UPDATE MyTable SET XXX='@QingJiaTianshu', DoWorkerID='@WebUser.No', 
        DoWorkerName='@WebUser.Name', DoWorkerDept='@WebUser.DeptNo',
       WHERE xxxx=@WorkID
    #### 存储过程DEMO
    - 填写不同数据库执行存储过程的SQL命令即可，有参数就正常写，无参不加.如：
    - MySql:Call procedure_name(@OlD).
    - SqlServer：EXEC procedure_name(@OlD);
    - 执行的结果返回string类型的数据，如果
    `),e.AddTBAtParas(4e3),this._enMap=e,this._enMap}beforeInsert(){return l(this,null,function*(){return Promise.resolve(!0)})}beforeUpdateInsertAction(){return l(this,null,function*(){return Promise.resolve(!0)})}}export{B as SysEventDBSrc};
