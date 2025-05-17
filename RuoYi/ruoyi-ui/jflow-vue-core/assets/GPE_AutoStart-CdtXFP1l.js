var n=Object.defineProperty;var c=(o,i,s)=>i in o?n(o,i,{enumerable:!0,configurable:!0,writable:!0,value:s}):o[i]=s;var e=(o,i,s)=>c(o,typeof i!="symbol"?i+"":i,s);import{F as r}from"./entry/index-B5R3Coa4-1746862693206.js";import{PageBaseGroupEdit as p}from"./PageBaseGroupEdit-BXdNWKIo.js";import{Flow as d,FlowAttr as t}from"./Flow-BIaTOSmj.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";class W extends p{constructor(){super("GPE_AutoStart");e(this,"Desc10",`
  #### 帮助
  - 单次发起比如：开会报到、新生报到、调查问卷流程.
  - 填写的表单是单次的,每个微信ID仅仅可以发起一次流程.
  - 如果第2次进入,提交的数据看到的数据是只读的,审批过程数据.
  - 用户提交后,是否可以撤销重写编写是由撤销规则决定的.
  `);e(this,"Desc11",`
  - 多次发起比如: 订单采购.
  - 可以发起多次流程,进入的是订单列表页面.
  `);e(this,"Desc0",`
#### 帮助指南
- 手工发起说明：
  - **默认发起方式**：用户通过发起列表选择并点击流程名称来启动流程。
- 自动发起定义：
  - **自动启动流程**：在预设规则的作用下，流程的开始节点及其相关填写将自动进行，无需人工干预。
- 解释：
  - 通常情况下，用户需要从发起列表中手动选择并启动流程。但在某些场景下，流程可以设置为由系统自动发起，无需人工操作。
- 应用场景举例说明：
  - **周例会流程**：当用户希望每周自动启动例会通知流程时，可以选择将流程设置为自动启动，而非依赖人工手动发起。
  - **订单处理**：当客户在线下订单并支付成功后，需要系统自动发起订单处理流程，包括库存检查、订单确认、发货准备等步骤。
  - **员工入职**：新员工被录用后，人力资源系统需要自动发起入职流程，包括发送欢迎邮件、分配工位、设置系统权限、安排入职培训等。
  - **财务报销**：员工提交报销申请后，财务系统需要自动检查申请是否符合公司政策（如金额限制、发票合规性等），若符合则自动发起报销审批流程。
  - **库存补充**：当库存量低于预设的安全库存水平时，需要系统自动发起库存补充流程，包括生成采购订单、发送供应商、跟踪到货情况等。
  `);e(this,"Desc1",`
#### 帮助指南

- **按人员定时启动说明**：在指定时间和指定人员下自动启动流程。
- **操作手册**：请查阅 [操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=6431420&doc_id=31094) 以获取更多信息。
- **参数格式**：
  - 单人启动：\`人员编号@时间点1@时间点2@...@时间点n\`
  - 多人启动：\`人员编号@时间点1@时间点2@...@时间点n, 人员编号@时间点1@时间点2@...@时间点n, ...\`
- **示例**：
  - \`zhangshan@01:01@12:01\`：张三每天1点01分和12点01分发起流程。
  - \`zhangshan@-01 01:01\`：张三每月1号1点01分发起流程。
  - \`zhangshan@06-01 01:01\`：张三每年6月1号1点01分发起流程。
- **日期格式**：使用\`yyyy-MM-dd HH:mm\`，若时间匹配设置的时间点，系统将自动触发流程。
#### 配置说明
- **服务启动**：为确保自动工作， **必须** 在您的服务器中配置定时任务，定时启动ccbpm的流程服务脚本
- **Windows定时任务**：查阅[操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)
- **Linux定时任务**：查阅[操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)

#### 定义

- **自动启动工作流程**：指在特定规则下，流程的开始节点和发起均自动进行，无需人工干预。
- **对比**：流程的默认发起方式是需用户手动从发起列表中点击流程名字启动。而自动启动则是由系统完成。

#### 应用场景

- **周例会流程**：用户希望每周自动启动例会通知流程，而非手动发起。
  `);e(this,"Desc2","\n#### 帮助指南\n\n- **启动流程**：首先，根据数据集启动流程，需要输入一个SQL查询。该查询执行后将返回一个数据集合。\n- **流程数量**：集合中的每条数据都会触发一个独立的流程。\n- **设置发起人**：数据源中 **必须** 包含`Starter`列，用于标记流程实例的发起人（可以为空）。\n- **避免重复**：数据源中 **必须** 包含`MainPK`列，用于标记流程实例的主键，防止重复发起流程。`MainPK`可以是订单号、事故号、单据号等唯一编号，若已存在则不再发起。\n- **设置隐藏字段**：在开始节点的表单中 **必须** 增加一个`MainPK`隐藏字段，用于保存SQL的主键，。\n- **节点与接收人**：主表中的每行记录都可以包含一个流程实例，可选字段`ToNode`表示流程发送到的节点，`ToEmps`表示接收人。\n#### 配置说明\n- **服务启动**：为确保自动工作， **必须** 在您的服务器中配置定时任务，定时启动ccbpm的流程服务脚本\n- **Windows定时任务**：查阅[操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)\n- **Linux定时任务**：查阅[操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)\n\n#### DEMO\n\n- **示例SQL**：\n  ```sql\n  SELECT MainPK, MyField1, MyField2, MyField3, 'admin' AS Starter, 103 AS ToNode, 'zhangsan' AS ToEmps FROM MyTable WHERE 1=1\n  ```\n  - **解释**：此查询表示`admin`用户将流程发送到第三个节点，由`zhangsan`处理。`ToNode`和`ToEmps`字段是可选的。\n\n#### 应用场景\n\n- **定期扫描付款表**：系统定期扫描客户付款表，一旦发现新的付款记录，即自动启动发货流程。\n  ");e(this,"WFTask",'\n  #### 帮助指南\n   - 第三方软件负责向特定的表 `WF_Task` 中写入数据。每当有新数据写入，系统会自动触发一条流程。该流程由 ccBPM 读取 `WF_Task` 表来发起。一旦流程成功发起，相应的记录将被标记为“已发起”状态。\n  #### 表结构说明\n   - **MyPK**：这是一个唯一的标识符，不允许重复，数据类型不限。\n   - **FK_Flow**：这是需要启动的流程编号，例如 `001`。\n   - **Starter**：这是要启动的人员编号（发起人员编号），例如 `zhangsan`。\n   - **ToNode**：这是流程启动后要到达的节点。如果此字段为 `0`，则流程会发送到第二个节点。\n   - **ToEmps**：这是需要接收该流程的人员列表，多个接收人之间用逗号分隔。如果此字段为空，则不指定接收人。例如：`zhangsan,lisi`。\n   - **Paras**：这是表单数据，即需要装载的表单内容。开始节点的表单字段名必须与参数名一致，系统才能自动为其赋值。例如：`@XingMing=张三@Addr=山东济南@Tel=186xxxxxxxx`。解释：如果开始节点的表单中包含这些字段，则流程可以成功启动。\n   - **TaskSta**：这是状态字段，默认为 `0`（表示未启动）。启动成功后，该字段值变为 `1`；启动失败时，变为 `2`。启动的消息将自动写入到 `Msg` 字段中。\n   - **Msg**：这是流程自动发起后写入的消息内容。\n   - **StartRDT**：这是要启动的日期。如果此字段为空，则流程将在定时任务启动时立即启动。\n   - **RDT**：这是发送日期。\n  #### 配置说明\n   - **服务启动**：为确保自动工作， **必须** 在您的服务器中配置定时任务，定时启动ccbpm的流程服务脚本\n   - **Windows定时任务**：查阅[操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)\n   - **Linux定时任务**：查阅[操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)\n  #### 图例说明\n  - 第1笔记录已经启动成功.\n  - 第2笔记录等待启动.\n  ![触发式启动](./resource/WF/Admin/AttrFlow/AutoStart/Img/WF_Task.png "触发式启动.png")  \n');e(this,"Desc4",`
#### 帮助指南

##### 日期匹配规则说明
- 如果时间匹配到您设置的时间点，系统就会触发流程。多个时间点用\`@\`符号隔开。

##### 请输入人员集合 - 参数说明
- **SQL方式**：
  \`\`\`sql
  SELECT No, Name FROM Port_Emp WHERE FK_Dept='@WebUser.FK_Dept'
  \`\`\`
  支持表达式：\`@WebUser.FK_Dept\` 当前登录人的部门编号, \`@WebUser.No\` 当前登录人的编号
- **指定人员**：
  例如：\`zhangsan,lisi,wangwu\`

##### 请输入时间点 - 参数说明
- **每日时间点**：
  - 例如：\`@01:01@12:01\` 在每天的1点01分和12点01分发起此流程。
- **每月某日时间点**：
  - 例如：\`@-01 01:01\` 在每月1号的1点01分发起此流程。
- **每年某月某日时间点**：
  - 例如：\`@06-01 01:01\` 在每年的6月1号的1点01分发起此流程。
- **按周设置**：
  - 格式：\`Week.X HH:mm\`
  - 例如：\`@Week.1 08:00\` 每周一早晨8点00分启动。
  - 支持多个时间段，例如：\`@Week.1 08:00@Week.3 08:00@Week.4 08:00@Week.7 08:00\`
- **每月指定日期**：
  - 例如：\`@-01 08:00\` 每月1号8点00分启动。
- **每月最后一天**：
  - 例如：\`@LastDayOfMonth 20:00\` 每月最后一天20点00分启动。

##### 配置说明
- **服务启动**：为确保自动工作， **必须** 在您的服务器中配置定时任务，定时启动ccbpm的流程服务脚本
- **Windows定时任务**：查阅[操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)
- **Linux定时任务**：查阅[操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)
`);e(this,"Desc5",`  
#### 帮助指南

##### 日期匹配规则说明
- 如果时间匹配到您设置的时间点，系统就会触发流程。多个时间点用\`@\`符号隔开。

##### 第2个节点人员集合 - 参数说明
- **SQL方式**：
  \`\`\`sql
  SELECT No, Name FROM Port_Emp WHERE FK_Dept='@WebUser.FK_Dept'
  \`\`\`
  支持表达式：\`@WebUser.FK_Dept\` 当前登录人的部门编号, \`@WebUser.No\` 当前登录人的编号
- **指定人员**：
  例如：\`zhangsan,lisi,wangwu\`

##### 发起时间点 - 参数说明
- **每日时间点**：
  - 例如：\`@01:01@12:01\` 在每天的1点01分和12点01分发起此流程。
- **每月某日时间点**：
  - 例如：\`@-01 01:01\` 在每月1号的1点01分发起此流程。
- **每年某月某日时间点**：
  - 例如：\`@06-01 01:01\` 在每年的6月1号的1点01分发起此流程。
- **按周设置**：
  - 格式：\`Week.X HH:mm\`
  - 例如：\`@Week.1 08:00\` 每周一早晨8点00分启动。
  - 支持多个时间段，例如：\`@Week.1 08:00@Week.3 08:00@Week.4 08:00@Week.7 08:00\`
- **每月指定日期**：
  - 例如：\`@-01 08:00\` 每月1号8点00分启动。
- **每月最后一天**：
  - 例如：\`@LastDayOfMonth 20:00\` 每月最后一天20点00分启动。

##### 配置说明
- **服务启动**：为确保自动工作， **必须** 在您的服务器中配置定时任务，定时启动ccbpm的流程服务脚本
- **Windows定时任务**：查阅[操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)
- **Linux定时任务**：查阅[操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)
`);this.PageTitle="发起模式"}BtnClick(s,a,h){}AfterSave(s,a){if(s==a)throw new Error("Method not implemented.")}Init(){this.entity=new d,this.KeyOfEn="FlowRunWay",this.AddGroup("C","基本发起"),this.Blank("0","手工发起",this.Desc0),this.SingleTextArea("10","微信-单次发起",t.RunObj,"前置说明文字",this.Desc10),this.SingleTextArea("11","微信-多次发起",t.RunObj,"前置说明文字",this.Desc11),this.AddGroup("A","配置模式"),this.SingleTB("1","按人员定时启动",t.RunObj,this.Desc1,"请按照格式输入参数"),this.SingleTBSQL("2","按数据集启动",t.RunObj,this.Desc2),this.TextBox2("4","指定人员集合按时启动",t.StartGuidePara1,"请输入人员集合",t.StartGuidePara2,"请输入时间点",this.Desc4),this.TextBox2("5","以admin启动发送给指定人员集合",t.StartGuidePara1,"第2个节点人员集合",t.StartGuidePara2,"发起时间点",this.Desc5),this.AddGroup("B","开发者模式"),this.Blank("3","触发式启动",this.WFTask),this.SelectItemsByGroupList("20","按照查询填充",this.HelpUn,!1,r.srcDBSrc,r.srcDBSFSearch,"StartGuidePara1")}}export{W as GPE_AutoStart};
