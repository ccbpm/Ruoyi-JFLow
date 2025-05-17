var p=Object.defineProperty;var d=(o,s,e)=>s in o?p(o,s,{enumerable:!0,configurable:!0,writable:!0,value:e}):o[s]=e;var i=(o,s,e)=>d(o,typeof s!="symbol"?s+"":s,e);import{F as n}from"./entry/index-B5R3Coa4-1746862693206.js";import{PageBaseGroupEdit as w}from"./PageBaseGroupEdit-BXdNWKIo.js";import{Node as r}from"./Node-BsvTqXX9.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./Help-D0bDMZWg.js";import"./EntityNodeID-3BfNz0DC.js";class k extends w{constructor(){super("GPE_OvertimeRole");i(this,"Desc0",`
#### 帮助指南
- **超时/逾期定义**：如果当前节点在您设定的“考核规则”时间内未能完成工作处理，则该任务将被视为超时或逾期状态。
- **不处理说明**：当任务进入超时或逾期状态后，选择“不处理”即意味着系统不会对此任务执行任何额外的操作或动作
 `);i(this,"Desc1",`
#### 帮助指南
- **场景说明**：当某个节点发生超时情况时，系统会自动将该节点流转至下一个处理环节。然而，如果您希望在特定条件下阻止这种自动流转，只需在当前节点的“发送前事件”中编写相应的业务逻辑即可。
为了实现自动流转功能，系统需要明确知道下一个节点的接收人以及目标节点。因此，在以下两种情况下，系统不会自动进行流转：

1. 当前节点的“转向规则”采用了主观选择分类下的模式。
2. 目标节点的接收人规则依赖于上一个节点的选择结果。
- **服务启动指南**：
  - **必要性**：为确保超时处理规则能够顺利执行，您必须在服务器上配置定时任务，定期启动ccbpm的流程服务脚本。
  - **Windows系统**：如果您使用的是Windows系统，请查阅[详细的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)，了解如何设置定时任务。
  - **Linux系统**：如果您使用的是Linux系统，同样请查阅[相应的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)，以正确配置定时任务。
 `);i(this,"Desc2",`
#### 帮助指南
- 当发生超时情况时，系统将自动引导您跳转至预设的指定节点。
- **服务启动指南**：
  - **必要性**：为确保超时处理规则能够顺利执行，您必须在服务器上配置定时任务，定期启动ccbpm的流程服务脚本。
  - **Windows系统**：如果您使用的是Windows系统，请查阅[详细的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)，了解如何设置定时任务。
  - **Linux系统**：如果您使用的是Linux系统，同样请查阅[相应的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)，以正确配置定时任务。
 `);i(this,"Desc3",`
#### 帮助指南
- **输入要求**：请输入接收人的工作帐号。
- **多接收人格式**：若需指定多名接收人，请使用半角逗号（,）分隔，例如：zhangsan,lisi。
- **自动移交**：工作一旦超时，系统将自动将其移交给指定的接收人员。
- **服务启动指南**：
  - **必要性**：为确保超时处理规则能够顺利执行，您必须在服务器上配置定时任务，定期启动ccbpm的流程服务脚本。
  - **Windows系统**：如果您使用的是Windows系统，请查阅[详细的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)，了解如何设置定时任务。
  - **Linux系统**：如果您使用的是Linux系统，同样请查阅[相应的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)，以正确配置定时任务。
**注意**：请确保输入的工作帐号准确无误，以便工作能够顺利移交。
  `);i(this,"Desc4",`
#### 帮助指南
- **输入要求**：请输入相关人员的工作帐号作为接收消息的对象。
- **多人输入格式**：若需向多人发送消息，请使用半角逗号（,）分隔各工作帐号，例如：zhangsan,lisi。
- **超时操作**：一旦达到设定的超时时间，系统会自动向上述指定人员发送消息提醒。
- **服务启动指南**：
  - **必要性**：为确保超时处理规则能够顺利执行，您必须在服务器上配置定时任务，定期启动ccbpm的流程服务脚本。
  - **Windows系统**：如果您使用的是Windows系统，请查阅[详细的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)，了解如何设置定时任务。
  - **Linux系统**：如果您使用的是Linux系统，同样请查阅[相应的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)，以正确配置定时任务。
  `);i(this,"Desc5",`
#### 帮助指南
- 当流程发生超时情况时，系统会自动删除该流程。
- **服务启动指南**：
  - **必要性**：为确保超时处理规则能够顺利执行，您必须在服务器上配置定时任务，定期启动ccbpm的流程服务脚本。
  - **Windows系统**：如果您使用的是Windows系统，请查阅[详细的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)，了解如何设置定时任务。
  - **Linux系统**：如果您使用的是Linux系统，同样请查阅[相应的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)，以正确配置定时任务。
  `);i(this,"Desc6","\n  #### 帮助\n- **支持的表达式**：当前的SQL支持ccbpm表达式，例如：`@WebUser.No`、`@WebUser.Name`、`@WebUser.DeptNo`、`@WebUser.OrgNo`。\n- **执行流程**：工作一旦超时，系统将执行SQL语句，并处理相应的业务逻辑。\n- **服务启动指南**：\n  - **必要性**：为确保超时处理规则能够顺利执行，您必须在服务器上配置定时任务，定期启动ccbpm的流程服务脚本。\n  - **Windows系统**：如果您使用的是Windows系统，请查阅[详细的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)，了解如何设置定时任务。\n  - **Linux系统**：如果您使用的是Linux系统，同样请查阅[相应的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)，以正确配置定时任务。\n  ");i(this,"Desc7",`
#### 帮助指南
- **场景描述**：若流程运行中出现超时状况，
- **系统动作**：系统将自动向该流程的发起人发送提醒消息。
- **服务启动指南**：
  - **必要性**：为确保超时处理规则能够顺利执行，您必须在服务器上配置定时任务，定期启动ccbpm的流程服务脚本。
  - **Windows系统**：如果您使用的是Windows系统，请查阅[详细的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)，了解如何设置定时任务。
  - **Linux系统**：如果您使用的是Linux系统，同样请查阅[相应的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)，以正确配置定时任务。
  `);i(this,"Desc8",`
#### 帮助指南
- 一旦流程出现超时情况，系统会自动触发提醒，向当前节点的处理人（即当前任务指定的接收者）发送消息通知。
- **服务启动**：为确保超时处理规则正常运行， **必须** 在您的服务器中配置定时任务，定时启动ccbpm的流程服务脚本
- **服务启动指南**：
  - **必要性**：为确保超时处理规则能够顺利执行，您必须在服务器上配置定时任务，定期启动ccbpm的流程服务脚本。
  - **Windows系统**：如果您使用的是Windows系统，请查阅[详细的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)，了解如何设置定时任务。
  - **Linux系统**：如果您使用的是Linux系统，同样请查阅[相应的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)，以正确配置定时任务。
  `);i(this,"Desc9",`
#### 帮助指南
- **超时情况通知**：在流程执行过程中，一旦遇到超时情况，系统会自动向所有参与该流程的用户发送提醒消息，确保大家及时了解流程状态。
- **服务启动指南**：
  - **必要性**：为确保超时处理规则能够顺利执行，您必须在服务器上配置定时任务，定期启动ccbpm的流程服务脚本。
  - **Windows系统**：如果您使用的是Windows系统，请查阅[详细的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3678116&doc_id=31094)，了解如何设置定时任务。
  - **Linux系统**：如果您使用的是Linux系统，同样请查阅[相应的操作手册](https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=12691557&doc_id=31094)，以正确配置定时任务。
  `);this.PageTitle="超时处理规则"}Init(){this.entity=new r,this.KeyOfEn="OutTimeDeal",this.AddGroup("A","超时处理规则"),this.Blank("0","不处理",this.Desc0),this.Blank("1","自动流转至下一环节",this.Desc1),this.SingleDDLSQL("2","自动跳转到指定节点","DoOutTime",this.Desc2,n.SQLOfOvertimeRole(),!1),this.SingleTB("3","工作移交指定人员","DoOutTime",this.Desc3,"输入人员编号"),this.Blank("5","删除流程",this.Desc5),this.SingleTBSQL("6","按SQL","DoOutTime",this.Desc6),this.AddGroup("B","消息提醒"),this.SingleTB("4","向指定人员发送提醒消息","DoOutTime",this.Desc4,"输入人员编号"),this.Blank("7","向开始节点发送提醒消息",this.Desc7),this.Blank("8","向当前节点处理人发送提醒消息",this.Desc8),this.Blank("9","向参与流程的所有人发消息",this.Desc9)}AfterSave(e,t){if(e==t)throw new Error("Method not implemented.")}BtnClick(e,t,c){if(e==t||e===c)throw new Error("Method not implemented.")}}export{k as GPE_OvertimeRole};
