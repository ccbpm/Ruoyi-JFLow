var o=Object.defineProperty;var l=(a,t,e)=>t in a?o(a,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):a[t]=e;var r=(a,t,e)=>l(a,typeof t!="symbol"?t+"":t,e);import{l as s,U as d,f as n}from"./entry/index-B5R3Coa4-1746862693206.js";import{SFDBSrc as u}from"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class g extends s{constructor(e){super("TS.MapExt.PopTree");r(this,"NoteSearchTip",` 
 
  #### 帮助
   - 显示在搜索文本框的背景文字，比如:
   - 请输入付款人名称,进行搜索。
   - 输入人员编号,名称，名称全拼,简拼关键字搜索。
   
   `);r(this,"NotTag1",` 
 
  #### 帮助
 
 
   - 点击关键字执行搜索返回的数据源，@Key是关键字,是搜索的关键字.
   - For URL:/DataUser/Handler.ashx?DoType=SearchEmps&Keyword=@Key
   - For SQL: SELECT No,Name FROM Port_Emp WHERE No like '%@Key%' OR Name like '%@Key%'
    
    `);r(this,"NotTag2",` 
 
  #### 帮助
   - 设置一个可以返回json的数据源该数据源有No,Name,ParentNo三个约定的列.
   - For URL:/DataUser/Handler.ashx?DoType=ReqDepts
   - For SQL:SELECT No,Name, ParentNo FROM Port_Dept ORDER BY Idx
  #### 懒加载说明:
   - 如果是懒加载，数据源里必须有 @Key 字段.
   - 初始化的时候，使用 @Key 作为父节点的编号。
   - 比如： SELECT No,Name,ParentNo FROM Port_Dept WHERE No='@Key' OR ParentNo='@Key' ORDER BY Idx
    `);r(this,"NotDoc",` 
 
  #### 帮助
 
 
   - 支持ccbpm的表达式,比如:@WebUser.DeptNo , @FieldName @WebUser.OrgNo
    
    `);r(this,"NotTag5",` 
 
  #### 帮助
 
   - 该选项可以为空,弹出框确定后执行的JS，可以直接写方法名或者方法. 

  `);e&&(this.MyPK=e)}get HisUAC(){const e=new d;return e.IsDelete=!1,e.IsUpdate=!0,e.IsInsert=!1,e}get EnMap(){const e=new n("Sys_MapExt","树干弹窗");return e.AddGroupAttr("数据来源"),e.AddMyPK(),e.AddTBString("FK_MapData",null,"表单ID",!1,!1,0,50,200),e.AddTBString("ExtModel","Pop","模式(大类)",!1,!1,0,50,200),e.AddTBString("ExtType",null,"类型(小类)",!1,!1,0,50,200),e.AddDDLEntities("FK_DBSrc","local","数据源",new u,!0,null,!1),e.AddTBString("SearchTip",null,"搜索提示",!0,!1,0,50,200,!0,`
#### 帮助：搜索提示参数说明
- **搜索提示背景文字**：显示在搜索文本框中的背景提示文字，旨在引导用户输入正确的搜索关键词。例如：
  - “请输入付款人名称，进行搜索。”
  - “输入人员编号、名称、名称全拼或简拼关键字进行搜索。”
    `),e.AddTBStringDoc("Tag1",null,"搜索数据源",!1,!1,!0,this.NotTag1),e.AddTBStringDoc("Tag2",null,"树干列表数据源 ",!0,!1,!0,this.NotTag2),e.AddTBString("Doc",null,"根节点树编号",!0,!1,0,50,200,!1,`
#### 帮助：根节点树编号参数说明
- 此参数用来设置树结构的根节点编号。
- **支持ccbpm表达式**：允许使用特定的ccbpm表达式来指定参数。例如：
  - \`@WebUser.DeptNo\` 表示当前登录人的部门编号
  - \`@表单字段名\` 表示表单中的字段名称，需要填写具体的字段名称
  - \`@WebUser.OrgNo\` 表示当前登录人的组织编号
    `),e.AddTBString("Tag",null,"列名中文对照",!0,!1,0,50,200,!0,`
#### 帮助：列名中文对照参数说明
- 该选项可以为空,列表的表头需要用中文显示.
- **示例**：
  \`\`\`
  No=编号,Name=名称,Tel=电话,Email=邮件
  \`\`\`
`),e.AddTBString("Tag5",null,"确定后执行的JS",!1,!1,0,50,200,!0,this.NotTag5),e.AddBoolean("IsLazy",!1,"是否懒加载?",!0,!0,!0),e.AddBoolean("NodeCascade",!0,"父子节点是否级联",!0,!0,!0),e.AddBoolean("IsShowFullPath",!1,"是否全路径显示(不显示顶级父节点)",!0,!0,!0),e.AddGroupAttr("外观"),e.AddRadioBtn("ShowModel",0,"展示方式",!0,!0,"ShowModel","@0=POP弹出窗@1=下拉搜索选择",null,!0),e.AddRadioBtn("PopSelectType",1,"选择类型",!0,!0,"PopSelectType","@0=单选@1=多选",null,!0),e.AddTBString("Title",null,"标题",!0,!1,0,50,200,!1),e.AddTBString("BtnLab","查找","查找按钮标签",!0,!1,0,50,200),e.AddTBInt("H",400,"弹窗高度",!0,!1),e.AddTBInt("W",500,"弹窗宽度",!0,!1),e.AddTBAtParas(4e3),e.ParaFields=",PopSelectType,Title,BtnLab,SearchTip,ShowModel,PopSelectType,IsLazy,NodeCascade,IsShowFullPath,",this._enMap=e,this._enMap}beforeUpdateInsertAction(){return this.GetParaBoolean("IsLazy")==!0&&this.Tag2.includes("@Key")==!1&&alert("配置错误:树干列表数据源必须包含, @Key 表达式, 请参考说明."),Promise.resolve(!0)}}export{g as PopTree};
