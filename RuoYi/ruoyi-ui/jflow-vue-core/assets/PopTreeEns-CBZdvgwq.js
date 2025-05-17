import{l as t,U as a,f as r}from"./entry/index-B5R3Coa4-1746862693206.js";import{SFDBSrc as o}from"./SFDBSrc-DbkqYXE6.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";class E extends t{constructor(e){super("TS.MapExt.PopTreeEns"),e&&(this.MyPK=e)}get HisUAC(){const e=new a;return e.IsUpdate=!0,e}get EnMap(){const e=new r("Sys_MapExt","树干叶子弹窗");return e.AddGroupAttr("数据来源"),e.AddMyPK(),e.AddTBString("FK_MapData",null,"表单ID",!1,!1,0,50,200),e.AddTBString("ExtModel","Pop","模式(大类)",!1,!1,0,50,200),e.AddTBString("ExtType",null,"类型(小类)",!1,!1,0,50,200),e.AddDDLEntities("FK_DBSrc","local","数据源",new o,!0,null,!1),e.AddTBString("SearchTip",null,"搜索提示",!0,!1,0,50,200,!0,`
#### 帮助：搜索提示参数说明
- **搜索提示背景文字**：显示在搜索文本框中的背景提示文字，旨在引导用户输入正确的搜索关键词。例如：
  - “请输入付款人名称，进行搜索。”
  - “输入人员编号、名称、名称全拼或简拼关键字进行搜索。”
    `),e.AddTBStringDoc("Tag1",null,"搜索数据源",!0,!1,!0,"\n#### 帮助：搜索数据源参数说明\n- **描述**：通过点击弹窗中的查询按钮执行搜索操作，返回相应的数据源。\n- **@Key 参数说明**：\n  - 描述：这是搜索的关键字。\n  - 示例：输入关键字后，系统将基于该关键字进行搜索。\n##### 使用示例\n- **URL 示例**：\n  ```\n  /DataUser/Handler.ashx?DoType=SearchEmps&Keyword=@Key\n  ```\n  - 说明：在 URL 中，`@Key` 将被替换为实际的关键字，用于执行搜索操作。\n- **SQL 示例**：\n  ```sql\n  SELECT No, Name FROM Port_Emp WHERE No LIKE '%@Key%' OR Name LIKE '%@Key%'\n  ```\n  - 说明：在 SQL 查询中，`@Key` 将被替换为实际的关键字，用于在 `Port_Emp` 表中搜索匹配的记录。\n    "),e.AddTBStringDoc("Tag2",null,"左侧树列表数据源",!0,!1,!0,"\n#### 帮助：左侧树列表数据源参数说明\n- **数据源要求**：\n  - 必须能够返回 JSON 格式的数据。\n  - 数据源应包含以下三个约定的列：`No`（编号）、`Name`（名称）、`ParentNo`（父级编号）。\n##### 使用示例\n- **URL 示例**：\n  ```\n  /DataUser/Handler.ashx?DoType=ReqDepts\n  ```\n- **SQL 示例**：\n  ```sql\n  SELECT No, Name, ParentNo FROM Port_Dept\n  ```\n    "),e.AddTBString("Doc",null,"根节点树编号",!0,!1,0,50,200,!1,`
#### 帮助：根节点树编号参数说明
- 此参数用来设置树结构的根节点编号。
- **支持ccbpm表达式**：允许使用特定的ccbpm表达式来指定参数。例如：
  - \`@WebUser.DeptNo\` 表示当前登录人的部门编号
  - \`@表单字段名\` 表示表单中的字段名称，需要填写具体的字段名称
  - \`@WebUser.OrgNo\` 表示当前登录人的组织编号
    `),e.AddTBStringDoc("Tag3",null,"实体数据源",!0,!1,!0,"\n#### 帮助：实体数据源参数说明\n- **选择左边的树返回的详细信息列表数据源**：\n  - `@Key` 是关键字，代表选择的树节点编号。\n##### 使用示例\n- **URL 示例**：\n  ```\n  /DataUser/Handler.ashx?DoType=ReqEmpsByDeptNo&DeptNo=@Key\n  ```\n- **SQL 示例**：\n  ```sql\n  SELECT No, Name, Tel, Email FROM Port_Emp WHERE FK_Dept = '@Key'\n  ```\n    "),e.AddTBString("Tag",null,"列名中文对照",!0,!1,0,50,200,!0,`
#### 帮助：列名中文对照参数说明
- 该选项可以为空,列表的表头需要用中文显示.
- **示例**：
  \`\`\`
  No=编号,Name=名称,Tel=电话,Email=邮件
  \`\`\`
`),e.AddTBString("Tag5",null,"确定后执行的JS",!1,!1,0,50,200,!0,`
#### 帮助
 - 该选项可以为空,弹出框确定后执行的JS，可以直接写方法名或者方法.
        `),e.AddGroupAttr("外观"),e.AddRadioBtn("ShowModel",0,"展示方式",!1,!1,"ShowModel","@0=POP弹出窗@1=下拉搜索选择",null,!0),e.AddRadioBtn("PopSelectType",1,"选择类型",!0,!0,"PopSelectType","@0=单选@1=多选",null,!0),e.AddTBString("Title",null,"标题",!0,!1,0,50,200,!1),e.AddTBString("BtnLab","查找","查找按钮标签",!0,!1,0,50,200),e.AddTBInt("H",400,"弹窗高度",!0,!1),e.AddTBInt("W",500,"弹窗宽度",!0,!1),e.AddTBAtParas(4e3),e.ParaFields=",Title,BtnLab,SearchTip,ShowModel,PopSelectType,",this._enMap=e,this._enMap}}export{E as PopTreeEns};
