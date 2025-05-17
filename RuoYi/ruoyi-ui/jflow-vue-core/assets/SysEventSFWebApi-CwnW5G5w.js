var m=Object.defineProperty;var T=(n,r,e)=>r in n?m(n,r,{enumerable:!0,configurable:!0,writable:!0,value:e}):n[r]=e;var c=(n,r,e)=>T(n,typeof r!="symbol"?r+"":r,e);var o=(n,r,e)=>new Promise((t,l)=>{var p=s=>{try{i(e.next(s))}catch(u){l(u)}},S=s=>{try{i(e.throw(s))}catch(u){l(u)}},i=s=>s.done?t(s.value):Promise.resolve(s.value).then(p,S);i((e=e.apply(n,r)).next())});import{l as A,U as D,f as P,h as g,i as y,B as f}from"./entry/index-B5R3Coa4-1746862693206.js";import{SysEventAttr as a}from"./SysEvent-DymzJjDC.js";import{SFProc as h}from"./SFProc-sEDsGGaw.js";import{GloDBsrcHelper as d}from"./GloDBSrcHelper-CD3_17zK.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFParaSln-ekMdP4Zl.js";class U extends A{constructor(e){super("TS.Sys.SysEventSFWebApi");c(this,"Help",`  
  ### 帮助  
  
  #### POST模式  
  - 输入完整的URL，例如: \`http://ccbpm.cn:9090?xx=@FK_Flow&xx=@WebUser.No&xx=@WebUser.Name&...\`  
    （注意：这里只展示了部分参数，实际使用时需要包含所有必要的参数）  
  - 全量模式会对表单的全部字段数据进行格式封装，例如JSON格式：  
    \`\`\`json  
    {  
      "AtPara": "",  
      "SQR": "admin",  
      "Rec": "admin",  
      "RDT": "2024-05-14",  
      ...  
    }  
    \`\`\`  
  - Java接口定义规则：  
    \`\`\`java  
    @PostMapping("/ceshi")  
    @ResponseBody  
    public Object ceshi(@RequestBody Map<String, Object> map, String token, String nodeId) {  
      // 方法实现  
    }  
    \`\`\`  
  - .NET接口定义规则：  
    \`\`\`csharp  
    [HttpPost]  
    public object ceshi([FromBody] dynamic data, string token, string workId) {  
      // 方法实现  
    }  
    \`\`\`  
  
  #### GET模式  
  - 输入完整的URL，与POST模式类似，但通常不包含请求体。  
  
  ### 返回值格式  
  - 成功时：  
    \`\`\`json  
    {  
      "message": "执行成功",  
      "code": 200,  
      "data": "",  
      "msg": "具体执行成功的消息（可选）"  
    }  
    \`\`\`  
  - 失败时：  
    \`\`\`json  
    {  
      "message": "执行失败",  
      "code": 500,  
      "data": "",  
      "msg": "具体执行失败的消息"  
    }  
    \`\`\`  
`);e&&(this.MyPK=e)}get HisUAC(){const e=new D;return e.IsDelete=!1,e.IsUpdate=!0,e.IsInsert=!1,e}get EnMap(){const e=new P("Sys_FrmEvent","服务事件");e.AddMyPK(),e.AddTBString(a.RefPKVal,null,"关键值",!1,!0,0,100,10),e.AddTBString(a.EventID,null,"事件标记",!0,!0,0,100,150),e.AddTBString(a.EventName,null,"事件名称",!0,!0,0,100,150),e.AddTBString(a.EventDoType,null,"执行标记",!0,!0,0,100,100),e.AddTBString(a.EventDoTypeT,null,"执行标记T",!1,!0,0,100,100),e.AddTBString("FrmID",null,"表单ID",!0,!0,0,100,10),e.AddTBString("FlowNo",null,"流程编号",!0,!0,0,5,10),e.AddTBInt("NodeID",0,"节点ID",!0,!0),e.AddGroupAttr("WebAPI配置"),e.AddDDLStringEnum("RequestMethod","Get","请求模式","@GET=GET@POST=POST",!0,"",!1,0),e.AddDDLStringEnum("ParaDTModel","1","数据格式","@0=From格式@1=JSON格式",!0,this.HelpParaModel),e.AddTBString("DoDoc",null,"接口名称",!0,!1,0,200,600,!0,d.Help_APISelectStatement),e.AddTBStringDoc("PostDoc",null,"POST内容",!0,!1,!0,d.Help_PostDoc),e.AddTBStringDoc("HeaderDoc",null,"Header内容",!0,!1,!0,d.Help_HeaderDoc),e.AddGroupAttr("测试设置"),e.AddTBString("TestParas",null,"测试参数",!0,!1,0,1e3,600,!0,d.Help_TestParas),e.AddTBString("Remark",null,"备注",!0,!1,0,100,20,!0);const t=new g;return t.Title="测试实例",t.ClassMethod="DoTest",t.RefMethodType=y.Func,t.IsCanBatch=!1,t.IsForEns=!0,t.Icon="icon-link",e.AddRefMethod(t),this._enMap=e,this._enMap}DoTest(){return o(this,null,function*(){try{const e=new f("BP.Sys.FrmEvent");return e.setPK(this.MyPK),yield e.RetrieveFromDBSources(),(yield e.DoMethodReturnString("ToTestWebApi")).data}catch(e){return e}})}GenerParas(){return o(this,null,function*(){const e=new h(this.DoDoc);yield e.Retrieve();const t=this.MyPK;return yield e.AddSln(t,this.FrmID)})}beforeInsert(){return o(this,null,function*(){return Promise.resolve(!0)})}beforeUpdateInsertAction(){return o(this,null,function*(){return Promise.resolve(!0)})}}export{U as SysEventSFWebApi};
