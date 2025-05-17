var l=Object.defineProperty;var p=(a,t,e)=>t in a?l(a,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):a[t]=e;var r=(a,t,e)=>p(a,typeof t!="symbol"?t+"":t,e);import{l as d,U as s,f as T}from"./entry/index-B5R3Coa4-1746862693206.js";import{M as o}from"./MapExt-DVovzpWn.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";class P extends d{constructor(e){super("TS.MapExt.PopTreeEnsSFTable");r(this,"NoteTag1",`
  #### 帮助
   - 点击关键字执行搜索返回的数据源，@Key是关键字,是搜索的关键字.
   - For URL:/DataUser/Handler.ashx?DoType=SearchEmps&Keyword=@Key
   - For SQL: SELECT No,Name FROM Port_Emp WHERE No like '%@Key%' OR Name like '%@Key%'
  `);r(this,"NoteTag2",`
  #### 帮助
   - 设置一个可以返回json的数据源该数据源有No,Name,ParentNo三个约定的列.
   - For URL:/DataUser/Handler.ashx?DoType=ReqDepts
   - For SQL:SELECT No,Name, ParentNo FROM Port_Dept
  `);r(this,"NoteDoc",`
  #### 帮助
   - 支持ccbpm的表达式,比如:@WebUser.DeptNo , @FieldName ,@WebUser.OrgNo 
  `);r(this,"NoteTag3",`
  #### 帮助
   - 选择右边的树返回的详细信息列表数据源 ， @Key是关键字,是选择的树节点编号.
   - For URL:/DataUser/Handler.ashx?DoType=ReqEmpsByDeptNo&DeptNo=@Key
   - For SQL:SELECT No,Name FROM Port_Emp WHERE FK_Dept='@Key'
  `);r(this,"NoteTag5",`
  #### 帮助
   - 该选项可以为空,弹出框确定后执行的JS，可以直接写方法名或者方法. 
  `);e&&(this.MyPK=e)}get HisUAC(){const e=new s;return e.IsUpdate=!0,e}get EnMap(){const e=new T("Sys_MapExt","树干叶子模式(绑定字典表)");return e.AddGroupAttr("数据来源"),e.AddMyPK(),e.AddTBString("FK_MapData",null,"表单ID",!1,!1,0,50,200),e.AddTBString("ExtModel","Pop","模式(大类)",!1,!1,0,50,200),e.AddTBString("ExtType",null,"类型(小类)",!1,!1,0,50,200),e.AddTBString("SearchTip",null,"搜索提示",!0,!1,0,50,200,!0,`
#### 帮助：搜索提示参数说明
- **搜索提示背景文字**：显示在搜索文本框中的背景提示文字，旨在引导用户输入正确的搜索关键词。例如：
  - “请输入付款人名称，进行搜索。”
  - “输入人员编号、名称、名称全拼或简拼关键字进行搜索。”
    `),o.AddAttrSFTable(e,"Tag1","搜索数据源",1),o.AddAttrSFTable(e,"Tag2","左侧树列表数据源",0,1),e.AddTBString("Doc",null,"根节点树编号",!0,!1,0,50,200,!1,this.NotDoc),o.AddAttrSFTable(e,"Tag3","实体数据源",1),e.AddTBString("Tag",null,"列名中文对照",!0,!1,0,50,200,!0,`
#### 帮助：列名中文对照参数说明
- 该选项可以为空,列表的表头需要用中文显示.
- **示例**：
  \`\`\`
  No=编号,Name=名称,Tel=电话,Email=邮件
  \`\`\`
`),e.AddTBString("Tag5",null,"确定后执行的JS",!1,!1,0,50,200,!0,this.NoteTag5),e.AddGroupAttr("外观"),e.AddRadioBtn("ShowModel",1,"展示方式",!1,!0,"ShowModel","@0=POP弹出窗@1=下拉搜索选择",null,!0),e.AddRadioBtn("PopSelectType",1,"选择类型",!0,!0,"PopSelectType","@0=单选@1=多选",null,!0),e.AddRadioBtn("OpenPopType",1,"打开Pop弹出窗的方式",!0,!0,"OpenPopType","@0=双击打开@1=点击按钮打开",null,!0),e.AddTBString("Title",null,"标题",!0,!1,0,50,200,!1),e.AddTBString("BtnLab","查找","查找按钮标签",!0,!1,0,50,200),e.AddTBInt("H",400,"弹窗高度",!0,!1),e.AddTBInt("W",500,"弹窗宽度",!0,!1),e.AddTBAtParas(4e3),e.ParaFields=",Title,BtnLab,SearchTip,ShowModel,PopSelectType,OpenPopType,",this._enMap=e,this._enMap}}export{P as PopTreeEnsSFTable};
