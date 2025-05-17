var q=Object.defineProperty;var J=(M,E,n)=>E in M?q(M,E,{enumerable:!0,configurable:!0,writable:!0,value:n}):M[E]=n;var N=(M,E,n)=>J(M,typeof E!="symbol"?E+"":E,n);var L=(M,E,n)=>new Promise((r,i)=>{var s=y=>{try{e(n.next(y))}catch(G){i(G)}},P=y=>{try{e(n.throw(y))}catch(G){i(G)}},e=y=>y.done?r(y.value):Promise.resolve(y.value).then(s,P);e((n=n.apply(M,E)).next())});import{P as z,F,cu as l,G as m,m as c,B as O,H as _,bj as D,C as I,W as f}from"./entry/index-B5R3Coa4-1746862693206.js";import{Menu as g}from"./Menu-B21f89xo.js";import{Func as j}from"./Func-D4L-_Msn.js";import{SFTable as K}from"./SFTable-BpxUt1jb.js";import{useClassFactoryLoader as p}from"./useClassFactoryLoader-m3Qht-LP.js";import{D as U}from"./DBAccess-CZ0wdWXU.js";import{GenerListEn as Q}from"./GenerListEn-BcDQg7jA.js";import{GloComm as T}from"./GloComm-B1xAfTWw.js";import{MapData as R}from"./MapData-D5zymw8O.js";import{Flow as Z}from"./Flow-BIaTOSmj.js";import{FlowAdm as H}from"./FlowAdm-B9fl-Qy8.js";import{FlowDtlView as X}from"./FlowDtlView-D4Nlr7th.js";import{buildShortUUID as Y}from"./uuid-CODpppBC.js";import{FrmDtlView as V}from"./FrmDtlView-CB_JCBbS.js";import{GPN_NewFlow as x}from"./GPN_NewFlow-dcJVQvOT.js";import{FlowDevModel as W}from"./EnumLab-CsLi93T0.js";import{FlowSort as ee}from"./FlowSort-DO177AvQ.js";import{MySystem as $}from"./MySystem-BG96NbYk.js";import{FrmSort as te}from"./FrmSort-BhvgVTIc.js";import C from"./GPNMenuExt-C-qt-WA5.js";import{TreeEnsDBView as oe}from"./TreeEnsDBView-DfAYR9QC.js";import{GLDBView as ae}from"./GLDBView-3Rv9aZMQ.js";import{b as d}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";import"./PCenter-DbUtxw5j.js";import"./PowerCenter-Dl8ZnZaz.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FrmAdm-w-27tFK4.js";import"./PageBaseGenerList-B6Q4ihPi.js";import"./FrmTrack-Ct-No0Nq.js";import"./GL_VSTOFrm-CBbWa0O7.js";import"./MapDtl-B_Ep8ewM.js";import"./PG_Module2Menu-BszPUh1p.js";import"./PageBasePanelGroup-C-loKAxc.js";import"./Module-BKib3HHY.js";import"./FrameworkExt-CCcdiwaa.js";import"./BaseEntityExt-3AR52S3C.js";import"./ModuleLang-CkU9wfxA.js";import"./MapFrmFool-BjBGkcYr.js";import"./GPE_PageLoadFullMainTable-T1tZGNNb.js";import"./MapExt-DVovzpWn.js";import"./PageLoadFull-rXMpPjS-.js";import"./PageBaseGroupEdit-BXdNWKIo.js";import"./Help-D0bDMZWg.js";import"./FrmPrintTemplate-DuKpQVFr.js";import"./FrmPrintDB-DmkZTfPT.js";import"./MapDataVer-BkV3qQwi.js";import"./AttrString-NXi21Ynk.js";import"./AttrHide-DRi6GSvK.js";import"./AttrEnum-B4doLO9s.js";import"./AttrNum-vVmD7j_0.js";import"./AttrSFSQL-BWYwZ5Uv.js";import"./AttrDT-Mb76V41e.js";import"./GPE_FrmReferencePanel-CcfD2s1m.js";import"./GPN_FrmExpImp-CGZJ9J9z.js";import"./Entity-Chp-BVny.js";import"./Request-Cs1ZNhZ7.js";import"./form-D-kP1HSJ.js";import"./GroupField-lMJeJtcW.js";import"./EntityOID-C1xznxai.js";import"./download-Cb1ocZ2f.js";import"./base64Conver-t-3tszFb.js";import"./BSEntities-D1vdB9S4.js";import"./Node-BsvTqXX9.js";import"./EntityNodeID-3BfNz0DC.js";import"./GroupFieldLang-BIVTczhJ.js";import"./MapAttrLang-CyQViPZP.js";import"./MapDtlLang-B8eUxYwG.js";import"./FrmAttachmentLang-BwFOc-rh.js";import"./FrmAttachment-MhkNqka4.js";import"./SysEvent-DymzJjDC.js";import"./GPE_FrmBodySecret-CSPNfIvP.js";import"./GPE_FrmWorkModel-BliMxdHf.js";import"./GPE_FrmType-D4Ez4Fbe.js";import"./SysEnumLang-nO8oYjDE.js";import"./FrmOrg-BK3uQclO.js";import"./MenuLang-BGIT3RSn.js";import"./MySystemLang-C3K84JCP.js";class Rt extends z{constructor(){super("GPN_Menu");N(this,"LinkFlowFunc",`
#### 帮助
1. xxxxxx.
`);N(this,"Components",`
  #### 帮助.
  1. xxxx
  2. xxxx
  #### 效果图.
  `);N(this,"Search",`
  #### 帮助.
  1. 查询实体:就是把Entity实体,放入查询组件/Comm/Search.vue 上实现数据的增删改查.
  1. 可以对实体进行，把关键字，日期时间范围，枚举外键进行查询.
  #### 效果图.
  1. xxxx
  1. xxxx
  #### 步骤1. 创建实体.
  1. 创建一个实体子类,根据实体特征确定继承路径, 请参考 BP.Demo.* /src/bp/demo/*.*
  1. 注意命名空间不要与系统的命名空间重复.
  1. 查询条件,隐藏条件的设置与bp架构的一样.
  #### 步骤2. 注册到ClassFactory.
  1. 把改实体注册到 /src/bp/da/ClassFactory  类里面.
  1. 注意Ens, En 都要注册.
  1. 请参考:BP.Demo.Student 的写法.
  #### 步骤3. 按照向导创建菜单.
  1. 新建菜单，选择模块，按照向导创建菜单.
  2. 测试.
  `);N(this,"Search_Ens",`
#### 帮助.
1. 选择一个实体类。
#### 找不到您创建的TS实体类?
- 原因1：未注册到 \`/src/bp/da/ClassFactory.ts\`里面去。
- 解决：在文件\`/src/bp/da/ClassFactory.ts\`中加入您创建的TS类。
- 原因2：未使用规范的命名（classId）。
- 解决： 使用系统规范的命名，以【TS.Demo.Student】为例，系统固定写法是TS.文件路径.文件名。
`);N(this,"Search_Ens_Paras",`
#### 帮助.
1. 输入参数,可以作为条件的参数,改参数可以为空.
1. 比如: &FK_Dept=@WebUser.DeptNo&SortNo=xxxxx 
1. 更多的参数请参考Search.vue的设计说明.
`);N(this,"GPN",`
#### 帮助.
1. 暂无
#### 效果图.
1. 暂无
`);N(this,"GPN_Ens",`
#### 帮助.
1. 暂无
#### 效果图.
1. 暂无
`);N(this,"GPN_Ens_Paras",`
#### 帮助.
1. 暂无
#### 效果图.
1. 暂无
`);N(this,"TreeEns",`
#### 帮助.
1. 暂无
#### 效果图.
1. 暂无
`);N(this,"TabsEns",`
#### 帮助.
1. 暂无
#### 效果图.
1. 暂无
`);N(this,"TreeEns_Ens",`
#### 帮助.
1. 暂无
#### 效果图.
1. 暂无
`);N(this,"Desc100","暂未开放");N(this,"DocSelfUrl",`
#### 帮助
1. 可以使用相对路径，也可以使用绝对路径。
1. 用户输入的Url:  http://ccbpm.cn/MyUrl.htm
1. 打开的Url : http://ccbpm.cn/MyUrl.htm?UserNo=xxxx&Token=xxxx。
1. SID就类似于token, UserNo就是当前登录用户的编号。


#### 自定义URL菜单 For H5
1. 菜单连接： http://ccbpm.cn/MyUrl.htm   
1. 菜单连接： http://ccbpm.cn/MyUrl.htm  
1. H5链接： /WF/Comm/Search.htm?EnsName=TS.ZS.Projcets 查询
1. H5链接： /WF/Comm/Group.htm?EnsName=TS.ZS.Projcets  分析
1. H5链接： /WF/MyFlow.htm?FK_Flow=001 发起指定的流程. 

#### 自定义URL菜单 For Vue3.x

- <strong>vue3菜单支持<span style="color:red">两种</span>配置</strong>
- <span style="color:red">配置后需要刷新页面</span>
- <strong>方式1：配置打开的vue文件和参数</strong>

  - 此方式本质就是构建vue的路由， 需要填写指向文件和链接
  - 指向文件是系统存在的vue文件， 表示下方链接实际由这个文件来处理
    - /src/WF/Comm/Search.vue
  - URL表示： 通过哪个路径访问上述的vue文件 可自定义，
    - 例如：需要通过上面的Search.vue 实现一个系统字典管理的链接 
    - 需要自定义个路径 /DictManage + 参数EnName=TS.FrmUI.SysEnumMain
    - 完整路径为 /DictManage?EnName=TS.FrmUI.SysEnumMain
    

- <strong>方式2： 配置url链接</strong>
  - <span style="color:red">如果配置url，指向文件填写一个空格即可</span>
  - url链接可以配置外链，以http:// 或者 https:// 开头
    外链可以为任意互联网地址 - 例如ccflow官网: http://ccflow.org
  - url链接也可以配置系统内部的链接， 以self://开头
    系统内部链接可以配置系统工作地址 - 例如打开编号001的流程: self://WF/MyFlow?FK_Flow=001 

- 常用vue文件地址如下：
  - /src/WF/Comm/Search.vue 查询
  - /src/WF/Comm/Group.vue  分组

- 常用的url如下：
  - self://WF/MyFlow?FK_Flow=xxx 发起id为xxx的流程
        
#### 效果图
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/SelfUrl.png "屏幕截图.png")
`);N(this,"DocAloneflow",`
####  创建独立运行的流程 
  - 创建流程后，系统自动对该流程的相关操作创建到菜单上去。 
  - 比如：发起流程，流程查询、分析。 
`);N(this,"DocRptwhite",`
####  信息窗/大屏(白色风格) 
  - 信息窗支持数据的图形展示，比如折线图、柱状图、饼图。
  - 支持变量文本输出，支持自定义HTLM代码的输出。
  - 是多种形式的统计分析展示功能。
#### 效果图
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/Windows.png "屏幕截图.png")
`);N(this,"DocRptblue",`
#### 信息窗/大屏(蓝色风格) 
  
  - 支持拖拽形式组装大屏窗口
  - 大屏组件丰富:各种图表,信息,列表,小组件,图标,图片等
  - 数据支持静态文件上传及接口动态获取的形式
#### 效果图
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/blueRPT.jpg "屏幕截图.png")
`);N(this,"DocTab",`
 #### Tabs页面容器 
  -  定义：每个tab下面都有一个自定义的url。
 #### 效果图
  - ![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/Tabs.png "屏幕截图.png")
`);N(this,"DocRpt3d",`

#### 三维报表 
   
 - 定义：三维报表是需要指定三个数据源，通过三维关系显示数据。
#### 事例
  
 - 数据源：SELECT FK_Flow,RunModel, FWCSta ,count(*) AS Num FROM wf_node GROUP BY FK_Flow,RunModel,FWCSta
 - 维度1：SELECT No,Name FROM WF_Flow ;
 - 维度2：SELECT IntKey AS No, Lab as Name FROM sys_enum WHERE EnumKey='RunModel';
 - 维度3：SELECT IntKey AS No, Lab as Name FROM sys_enum WHERE EnumKey='FWCSta';
 
#### 效果图
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/Rpt3D.png "屏幕截图.png")
`);N(this,"DocEntityNoName",`
#### 帮助 
 - 实体是管理对象. 比如固定资产管理、合同管理、人力学生、项目等.
 - 实体的基础管理就是对它的增、删、改、查. 
 - 对实体的管理包括实体的流程管理、相关功能管理、方法管理三部分.
 - 实体不能绑定到流程节点上,单据与独立表单可以.
 - 通过对外提供url模式的api接口,绑定到菜单里, 实体与流程的关系请参考: http://doc.ccbpm.cn
 #### 数据库字段
 - 实体数据存储在数据表里,数据表的字段分为系统字段+业务字段.
 - 比如： 编号、名称、创建人、创建日期、创建人部门、创建人组织就是系统字段.  实体电话、邮件、地址就是业务字段
 - No,varchar,主键,实体编号(编号的生成规则可以自定义默认为001,0002)
 - Name,varchar,实体名称
 - EntityState,int,枚举类型 -1=删除,0初始化,1=草稿,2=编辑,3=归档.
 - RecNo,varchar记录人编号,
 - RecName,varchar,记录人名称
 - DeptNo,varchar,记录人部门编号,
 - OrgNo,varchar,记录人组织
 #### 示例-车辆管理.
 - 实体列表:
 ![实体](./resource/WF/Admin/FrmLogic/EntityType5.png "屏幕截图.png")  
 - 单个记录:
 ![实体](./resource/WF/Admin/FrmLogic/EntityType5_1.png "屏幕截图.png")  
`);N(this,"DocBill",`
#### 帮助
 - 定义: 单据具有流水性质的数据增删改查,比如:报销单、请假单、出差申请单.
 - 单据与流程: 单据可以被流程节点绑定,也可也从实体上发起.
 - 基本字段: 制单人Starter、制单日期RDT、单号BillNo、标题Title、状态BillSta.
 - 单据编号: 可以自动定义,存储在BillNo字段中.
 - 单据标题: 可以自定义规则，类似于流程标题.
 - 单据状态: BillState 0=草稿,1=编辑中,2=退回,3=归档.
 - 单据主键: OID 是个自动生的字段,类似于WorkID.
 - 发起人: Starter, StarterName.
 - 冗余字段: PWorkID, PFrmID父表单.
 #### 操作界面.
 1. 创建一个单据数据存储到 Frm_GenerBill 一份.
 2. 待办:单据管理
 3. 我的单据: 我发起的单据,等待我审批的单据,已经创建的单据.
 4. 发起单据: 我能创建的单据列表.
 5. 单据草稿: 启动的草稿.
 6. 近期单据: 近期发起的单据.
`);N(this,"DBList",`
  
#### 数据源实体 
 -  定义：数据源实体就是视图，不能对数据执行，增加，删除，修改操作。
 -  具备实体的其他的功能，可以当作查询所用。
 `);N(this,"DictCopy",`
#### 复制实体
- 定义：自动启动工作流程，一个流程的开始节点的填写与发起是在特定规则的设置下自动发起的流程。
- 解释：通常模式下的流程启动是手工的启动，就是用户从一个发起列表，点击流程名字，就启动了该流程。但是有的时候，是系统自动发起该流程。
- 应用场景：
   1 周例会流程，用户希望每个周都要启动例会通知流程这个启动是让系统自动发起而非人工发起。
`);N(this,"Bill",this.DictCopy);N(this,"DictRef","");N(this,"DictTable",`

#### 字典表 
 -  定义：只具有编号,名称两个属性的字典实体，比如：角色类型、系统类别、税种、税目、省份、片区

#### 效果图
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/DictTable.png "屏幕截图.png")
 
`);N(this,"DictTableTree",`
#### 树结构字典表 
 -  定义：只具有编号,名称,父节点，三个属性的字典实体，比如：部门
`);N(this,"Task",`
#### 任务 
 -  定义：记录任务参与人，任务时间，紧急程度等事件的记事本。
#### 效果图
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/Task.png "屏幕截图.png")
`);N(this,"Info",`
#### 信息发布 
  
 -  定义：可以编辑发布信息，信息以列表的形式展示在页面上。 
 -  也可以定义信息发布类形，比如：会议记要，工作进度，文件传达等 

#### 信息列表图
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/Info2.png "屏幕截图.png") 
#### 信息编辑
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/Info.png "屏幕截图.png") 
#### 信息类型
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/Info3.png "屏幕截图.png") 
`);N(this,"Calendar",` 
  -  定义：可以在日历上编辑记事，工作提醒等。
#### 效果图
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/Calendar.png "屏幕截图.png")
`);N(this,"Notepad",`
#### 记事本 
   -  定义：是一款在线记事本，可以记录生活，工作，事件。
#### 效果图
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/Notepad.png "屏幕截图.png") 
`);N(this,"KnowledgeManagement",`
#### 知识库 
 -  定义：各种知识的集合，方便了解和查询
#### 效果图
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/KnowledgeManagement.png "屏幕截图.png") 
`);N(this,"DBSrcSearch",`
#### 帮助
- 把数据源查询作为视图的数据.
`);N(this,"WorkRec",`
#### 工作日志 
 -  定义：记录工作的日志
#### 效果图
![输入图片说明](./resource/CCFast/GPM/CCMenu/Img/WorkRec.png "屏幕截图.png") 
  `);this.PageTitle="新建菜单",this.ForEntityClassID="TS.GPM.Menu"}Init(){return L(this,null,function*(){this.AddGroup("A","流程","","公文、请假、订单、物联网流程"),this.TextBox1_Name("JiJian","极简模式",x.JiJian,"流程名称","我的极简流程"),this.AddIcon("icon-paper-plane","JiJian"),this.TextBox1_Name("Profession","专业模式",x.Profession,"流程名称","我的专业流程"),this.AddIcon("icon-plane","Profession"),this.TextBox1_Name("RefOneFrmTree","绑定单表单模式",x.RefFrm,"流程名称","我的单表单流程"),this.AddIcon("icon-notebook","RefOneFrmTree"),this.SelectItemsByGroupList("RefOneFrmTree.SelectOneFrm","选择表单",x.RefFrm_SelectOneFrm,!1,F.srcFrmTree,F.srcBindFrmList);const n=T.UrlGPN("GPN_ImpFlow",this.SystemNo);this.AddGoToUrl("FlowImp","导入流程",n),this.AddIcon("icon-arrow-down-circle","FlowImp"),this.SelectItemsByList("LinkFlow","引入单流程组件",this.HelpTodo,!1,C.GenerFlowFunc()),this.AddIcon("icon-link","LinkFlow"),this.SelectItemsByGroupList("LinkFlow.FlowNo","选择流程",this.HelpTodo,!1,F.srcFlowSorts,F.srcFlows,!1),this.SelectItemsByList("LinkFlowFunc","引入流程菜单(系统)",this.LinkFlowFunc,!1,C.GenerFlowMenu()),this.AddIcon("icon-link","LinkFlowFunc"),this.AddGroup("B","单据","","报销单、出库单、出门单,具有单号,标题字段的表单."),this.TextBox2_NameNo(l.Bill,"创建单据",this.DocBill,"Bill_","编号","名称","维修单"),this.AddIcon("icon-event","Bill"),this.SelectItemsByList("BillLink","引入单据组件",this.HelpTodo,!1,C.BillFunc()),this.AddIcon("icon-link","BillLink"),this.SelectItemsByGroupList("BillLink.FrmID","选择单据","选择单据",!1,F.srcFrmTree,F.srcFrmListOfBill),this.FileUpload("BillImp","导入单据实体模板",this.HelpTodo,"请上传单据模板."),this.AddIcon("icon-cloud-upload","BillImp"),this.TextBox2_NameNo("AskFrm","创建调查问卷(规划中)",this.DocEntityNoName,"Tree_","编号","名称","商品类别"),this.AddIcon("icon-bubbles","AskFrm"),this.AddGroup("C","实体","","资产、学生、车辆,具有编号、名称字段的表单."),this.TextBox2_NameNo("EntityNoName","创建实体",this.DocEntityNoName,"En_","编号","名称","学生台账"),this.AddIcon("icon-notebook","EntityNoName"),this.TextBox2_NameNo("EntityTree","创建树实体(规划中)",this.DocEntityNoName,"Tree_","编号","名称","商品类别"),this.AddIcon("icon-organization","EntityTree"),this.SelectItemsByList("EntityNoNameLink","引入实体组件",this.HelpTodo,!1,C.EntityNoNameFunc()),this.AddIcon("icon-link","EntityNoNameLink"),this.SelectItemsByGroupList("EntityNoNameLink.Ref","选择实体","选择要导入的实体",!1,F.srcFrmTree,F.srcFrmEntityNoName),this.AddIcon("GenerListEn","icon-wallet"),this.SelectItemsByList("SFTable","内置字典维护","把内置字典维护的连接增加到菜单上.",!1,F.SQLSFTableSysDict),this.AddIcon("icon-link","SFTable"),this.AddGroup("D","高代码","","TS全栈配置开发,无Vue3,基于13个页面模式,写文档一样编程."),this.AddBlank("Entity","Entity数据实体",this.Search),this.AddTableByOptions({no:"Entity.EnName",name:"选择实体",columns:C.TableCols(),helpDocs:"选择实体(单选)",IsMultiSelect:!1,srcOfList:yield C.GenerEnsList("Entity")}),this.Table("Entity.EnName.componentName","组件类型",this.Search_Ens,!1,C.GenerEntitySort()),this.TextBox2_NameNo("Entity.EnName.componentName.Paras","路径及参数",this.Search_Ens_Paras,"","参数","URL定义",""),this.AddBlank("GPN","GPN新建组件",this.GPN),this.SelectItemsByList("GPN.Ens","选择实体",this.GPN_Ens,!1,yield C.GenerEnsList("GPN")),this.TextBox1_Name("GPN.Ens.Paras","可选参数",this.GPN_Ens_Paras,"参数","&1=1"),this.AddBlank("TreeEns","TreeEns树干叶子组件",this.TreeEns),this.SelectItemsByList("TreeEns.Ens","选择实体",this.TreeEns_Ens,!1,yield C.GenerEnsList("TreeEns")),this.TextBox1_Name("TreeEns.Ens.Paras","可选参数",this.Search_Ens,"参数","&1=1"),this.AddBlank("GL","GL通用列表组件",this.TreeEns),this.SelectItemsByList("GL.Ens","选择实体",this.TreeEns_Ens,!1,yield C.GenerEnsList("GL")),this.TextBox1_Name("GL.Ens.Paras","可选参数",this.Search_Ens,"参数","&1=1"),this.AddBlank("PG","PG实体分组展示组件",this.TreeEns),this.SelectItemsByList("PG.Ens","选择实体",this.TreeEns_Ens,!1,yield C.GenerEnsList("PG")),this.TextBox1_Name("PG.Ens.Paras","可选参数",this.Search_Ens,"参数","&1=1"),this.AddBlank("TabPage","Tabs页面",this.TabsEns),this.SelectItemsByList("TabPage.Ens","选择实体",this.TabsEns,!1,yield C.GenerEnsList("Tabs")),this.TextBox1_Name("TabPage.Ens.Paras","可选参数",this.TabsEns,"参数","&1=1"),this.AddBlank("DataV","DataV大屏实体",this.DocRptwhite),this.Table("DataV.Ens","选择实体",this.DocRptwhite,!1,yield C.GenerEnsList("DataV")),this.TextBox1_Name("DataV.Ens.Paras","可选参数",this.DocRptwhite,"参数","&1=1"),this.AddBlank("OpenHelp","帮助","#### 帮助地址:  - https://docs.qq.com/doc/DRGZzblhkdWlOZXFG ","icon-support"),this.AddGroup("E","大屏列表"),this.TextBox1_Name(l.RptWhite,"信息窗/大屏(白色风格)",this.DocRptwhite,"页面名称","统计分析"),this.TextBox2_NameNo("DBList","创建数据源列表",this.DBList,"DB_","编号","名称","人员台账"),this.TextBox1_Name("GenerListEn","GLEn通用列表组件",this.DBList,"输入名称","我的列表","请输入列表名称."),this.AddGroup("J","SQL视图-测试规划中"),this.TextBox2_NameNo("GLDBView","不分页视图",this.DBList,"GL_","编号","名称","人员台账"),this.TextBox2_NameNo("SearchBillView","分页视图",this.DBList,"Search_","编号","名称","人员台账"),this.TextBox2_NameNo("TreeEnsDBView","左树右表视图",this.DBList,"TreeEns_","编号","名称","人员台账"),this.AddGroup("F","工具视图-测试规划中"),this.TextBox2_NameNo("FrmEntityDtlView","实体从表",this.HelpTodo,"View_","请输入视图ID","输入视图名称",""),this.SelectItemsByGroupList("FrmEntityDtlView.SelectFrm","选择实体","选择实体",!1,F.srcFrmTree,F.srcFrmEntityNoName),this.SelectItemsByList("FrmEntityDtlView.SelectFrm.Dtl","选择从表",this.HelpTodo,!1,()=>F.SQLOfDtls(this.RequestVal("tb1","FrmEntityDtlView.SelectFrm"))),this.TextBox2_NameNo("FrmBillDtlView","单据从表",this.HelpTodo,"View_","请输入视图ID","输入视图名称",""),this.SelectItemsByGroupList("FrmBillDtlView.SelectFrm","选择实体","选择实体",!1,F.srcFrmTree,F.srcFrmListBill),this.SelectItemsByList("FrmBillDtlView.SelectFrm.Dtl","选择从表",this.HelpTodo,!1,()=>F.SQLOfDtls(this.RequestVal("tb1","FrmBillDtlView.SelectFrm"))),this.TextBox3_NameNoNote("FlowDtlView","流程从表",this.HelpTodo,"View_","请输入视图ID","输入视图名称","输入流程编号","流程从表视图"),this.SelectItemsByList("FlowDtlView.SelectFlowDtl","选择从表",this.HelpTodo,!1,()=>{const r="ND"+Number.parseInt(this.RequestVal("tb3","FlowDtlView"))+"01";return F.SQLOfDtls(r)}),this.AddIcon("FlowDtlView","icon-grid"),this.TextBox2_NameNo("DBSrcSearch","数据源查询",this.DBSrcSearch,"View_","请输入视图ID","输入视图名称",""),this.SelectItemsByGroupList("DBSrcSearch.Select","选择查询",this.HelpTodo,!1,F.srcDBSrc,F.srcDBSFSearch),this.AddGroup("G","页面引用"),this.TextBox3_NameNoNote(l.SelfUrl,"自定义URL菜单",this.DocSelfUrl,null,"指向文件","链接标签","URL地址","我的链接"),this.TextBox1_Name(l.Tabs,"Tabs页面容器",this.DocTab,"名称","我的tab容器页"),this.AddGroup("H","OA应用-测试规划中"),this.TextBox1_Name(l.Info,"信息发布",this.Info,"名称","信息发布"),this.TextBox1_Name(l.Notepad,"记事本",this.Notepad,"名称","记事本"),this.TextBox1_Name(l.KnowledgeManagement,"知识库",this.KnowledgeManagement,"名称","知识库"),this.TextBox1_Name(l.WorkRec,"工作日志",this.WorkRec,"名称","工作日志"),this.AddIcon("icon-link","book-open"),this.AddIcon("icon-heart","Entity"),this.AddIcon("icon-docs","GPN"),this.AddIcon("icon-control-pause","TreeEns"),this.AddIcon("icon-cup","GL"),this.AddIcon("icon-layers","PG"),this.AddIcon("icon-link","SelfUrl"),this.AddIcon("icon-doc","RptWhite"),this.AddIcon("icon-drop","Tabs"),this.AddIcon("icon-docs","Rpt3D"),this.AddIcon("icon-docs","Info"),this.AddIcon("icon-doc","Notepad"),this.AddIcon("icon-layers","KnowledgeManagement"),this.AddIcon("icon-film","WorkRec")})}get SystemNo(){return this.params.SystemNo||this.RequestVal("SystemNo")||""}GenerSorts(n){return L(this,null,function*(){return[]})}Save_TextBox_X(n,r,i,s,P){return L(this,null,function*(){var v,k;if(r=this.RequestVal("SortNo"),n=="AiFlow"){const t=T.UrlGPN("GPN_AIFlow","&SystemNo="+this.SystemNo+"&ModelNo="+r);return new m(c.GoToUrl,t)}const e=new g;if(e.Icon="icon-user",e.ModuleNo=r,e.ModuleNoT=this.GetSortName(r),e.SystemNo=this.SystemNo,e.SetPara("EnName","TS.GPM.MenuGenerPage"),n=="GLDBView"){const t=new ae;if(t.No=s,(yield t.IsExits())==!0)return new m(c.Message,"ID:"+s+"已经存在,请重命名.");t.Name=i,t.FK_FrmSort=r,t.SetValByKey("ExpEn","SELECT A.No as OID, A.No as BillNo, a.Name AS Title, A.Tel,A.Email , A.FK_Dept as DeptNo, B.Name AS DeptT, Case A.EmpSta WHEN 0 THEN ~正常~ ELSE~禁用~ END AS EmpSta FROM Port_Emp A, Port_Dept B WHERE A.FK_Dept=B.No "),t.SetValByKey("LabField","EmpSta"),t.SetValByKey("LabContent","正常=green;禁用=red;"),t.SetPara("LabField","EmpSta"),t.SetPara("LabContent","正常=green;禁用=red;"),yield t.Insert();const a=new O("BP.CCBill.SearchBillView");a.setPK(t.No),yield a.Retrieve(),yield a.DoMethodReturnString("CheckGLGenerList"),e.MenuModel=n,e.Name=i,e.FrmID=s,e.No=s,e.Icon="icon-wallet",e.SetPara("EnName","TS.CCBill.GLDBView"),e.Tag1=s,e.Alias="GLDBView_"+s,e.UrlPath="/@/WF/views/GenerList.vue",e.UrlExt="/"+t.No+"?EnName=GL_DBGenerList&displayMode=table&FrmID="+t.No,yield e.Insert();const o=T.UrlEn("TS.CCBill.GLDBView",s);return new m(c.GoToUrl,o,"属性")}if(n=="SearchBillView"){const t=new R;if(t.No=s,(yield t.IsExits())==!0)return new m(c.Message,"ID:"+s+"已经存在,请重命名.");const a=new _("BP.WF.HttpHandler.WF_Admin_CCFormDesigner");a.AddPara("TB_Name",i),a.AddPara("TB_No",s),a.AddPara("DDL_DBSrc","local"),a.AddPara("FK_FrmSort",r),a.AddPara("EntityType",100);const o=yield a.DoMethodReturnString("NewFrmGuide_Create_DBList");if(o.includes("err@")==!0)return new m(c.Message,o);e.MenuModel=n,e.Name=i,e.FrmID=s,e.No=s,e.Icon="icon-wallet",e.SetPara("EnName","TS.CCBill.SearchBillView"),e.Tag1=s,e.Alias="SearchBillView_"+s,e.UrlPath="/@/CCFast/CCBill/SearchDBList.vue",e.UrlExt="/"+t.No+"?displayMode=table&FrmID="+t.No,yield e.Insert();const w=T.UrlEn("TS.CCBill.SearchBillView",s);return new m(c.GoToUrl,w,"属性")}if(n=="TreeEnsDBView"){const t=new oe;if(t.No=s,(yield t.IsExits())==!0)return new m(c.Message,"ID:"+s+"已经存在,请重命名.");t.Name=i,t.FK_FrmSort=r,t.SetValByKey("ExpList","Select No,Name,ParentNo From Port_Dept Where ParentNo=~@Key~"),t.SetValByKey("ExpEn","Select No,Name,Email,Tel From Port_Emp Where FK_Dept=~@Key~"),t.SetValByKey("Tag0","Select No,Name,Email,Tel From Port_Emp Where No like ~%@Key%~ OR Name like  ~%@Key%~"),t.SetValByKey("Note","No=编号,Name=名称,Email=邮件,Tel=电话"),t.SetValByKey("IsLazy","1"),t.SetValByKey("RootNo","0"),t.SetPara("Note","No=编号,Name=名称,Email=邮件,Tel=电话"),t.SetPara("IsLazy","1"),t.SetPara("RootNo","0"),yield t.Insert(),e.MenuModel=n,e.Name=i,e.FrmID=s,e.No=s,e.Icon="icon-wallet",e.SetPara("EnName","TS.CCBill.TreeEnsDBView"),e.Tag1=s,e.Alias="TreeEnsDBView_"+s,e.UrlPath="/@/CCFast/DBView/TreeEnsDBView.vue",e.UrlExt="/"+t.No+"?displayMode=table&FrmID="+t.No,yield e.Insert();const a=T.UrlEn("TS.CCBill.TreeEnsDBView",s);return new m(c.GoToUrl,a,"属性")}if(n=="LinkFlowFunc")return e.MenuModel=n,e.Name=s,e.No=U.GenerGUID(),e.Icon="icon-link",e.SetPara("EnName","TS.CCBill.GenerListEn"),e.Tag1=i,e.Alias=r+"_"+i,e.UrlPath="/@/WF/views/GenerList.vue",e.UrlExt="/WF/Comm/GenerList?EnName="+i,yield e.Insert(),new m(c.Message,"创建成功.");if(n==="Profession"||n==="JiJian"||n=="RefOneFrmTree.SelectOneFrm"){const t=new ee;if(t.No=e.SystemNo,(yield t.IsExits())==!1){let S="CCFast";D.CCBPMRunModel!=I.Single&&(S="CCFast_"+f.OrgNo),t.No=S,(yield t.IsExits())==!1&&(t.Name="低代码流程",D.CCBPMRunModel==I.Single?t.ParentNo="1":t.ParentNo=f.OrgNo,t.OrgNo=f.OrgNo,yield t.Insert()),t.No=e.SystemNo;const h=new $;h.No=e.SystemNo,yield h.Retrieve(),t.Name=h.Name,D.CCBPMRunModel==I.Single?t.ParentNo=S:t.ParentNo=f.OrgNo,t.OrgNo=f.OrgNo,yield t.Insert()}let a=i;const o=s;let w=W.Prefessional,u="";if(n=="JiJian"?(w=W.JiJian,e.Icon="icon-paper-plane"):e.Icon="icon-plane",n=="RefOneFrmTree.SelectOneFrm"){a=this.RequestVal("tb1","RefOneFrmTree");const S=i,h=yield x.creteFlow(r,W.RefOneFrmTree,a,S,P);if(h==null)return;u=h,e.Icon="icon-bulb"}else{const S=yield x.creteFlow(t.No,w,a,o,P);if(S==null)return;u=S}e.Name=a,e.Alias="SearchFlow"+u,e.UrlPath="/src/WF/Rpt/SearchFlow.vue?FlowNo="+u,e.UrlExt=`/SearchFlow_${u}?FlowNo=${u}`,e.ModuleNo=r,e.SystemNo=this.SystemNo,e.MenuModel="RefFlow",e.FlowNo=u,e.IsEnable=1,e.SetPara("FlowNo",u),yield e.Insert();const B=T.UrlFlowD(u);return new m(c.OpenUrlByNewWindow,B)}if(n=="FrmEntityDtlView"||n=="FrmBillDtlView"){const t=new V(i);if((yield t.IsExits())==!0)return new m(c.Message,"菜单ID:["+i+"]已经存在");if(t.setPKVal(P),(yield t.RetrieveFromDBSources())==0)return new m(c.Message,"表单ID:["+P+"]不存在.")}if(n=="FrmEntityDtlView.SelectFrm.Dtl"||n=="FrmBillDtlView.SelectFrm.Dtl"){let t="FrmEntityDtlView";n=="FrmBillDtlView.SelectFrm.Dtl"&&(t="FrmBillDtlView");const a=new V;a.No=this.RequestVal("tb2",t),a.Name=this.RequestVal("tb1",t);const o=this.RequestVal("tb1",t+".SelectFrm"),w=new R(o);yield w.RetrieveFromDBSources(),a.FrmNo=w.No,a.FrmName=w.Name,a.DtlNo=i,a.DtlName=s,a.PTable=a.No,yield a.Insert(),e.MenuModel=t,e.Name=a.Name,e.FrmID=a.No,e.No=a.No,e.Icon="icon-wallet",e.SetPara("EnName","TS.CCBill.FrmDtlView"),e.Tag1=s,e.Alias="FrmEntityDtlView_"+a.No,e.UrlPath="/@/CCFast/CCBill/SearchDBList.vue",e.UrlExt="/FrmDtlView_"+a.No+"?EnName="+a.No,yield e.Insert();const u=new O("BP.CCBill.FrmDtlView",a.No);yield u.Retrieve(),u.DoMethodReturnString("CheckIt");const B=T.UrlEn("TS.CCBill.FrmDtlView",a.No);return new m(c.GoToUrl,B,"属性")}if(n=="FlowDtlView"){if((yield new V(i).IsExits())==!0)return new m(c.Message,"菜单ID:["+i+"]已经存在");if((yield new H(P).IsExits())==!1)return new m(c.Message,"流程编号错误:"+P)}if(n=="FlowDtlView.SelectFlowDtl"){const t=new X;t.No=this.RequestVal("tb2","FlowDtlView"),t.Name=this.RequestVal("tb1","FlowDtlView");const a=this.RequestVal("tb3","FlowDtlView"),o=new H(a);yield o.RetrieveFromDBSources(),t.FlowNo=o.No,t.DictID="ND"+Number.parseInt(o.No+"01"),t.DictName=o.Name,t.DictDtlID=i,t.DictDtlName=s,yield t.Insert(),e.MenuModel="FlowDtlView",e.Name=t.Name,e.FrmID=t.No,e.No=t.No,e.Icon="icon-wallet",e.SetPara("EnName","TS.CCBill.FlowDtlView"),e.Tag1=s,e.Alias="FlowDtlView_"+t.No,e.Name=t.Name,e.UrlExt="/"+t.No+"?displayMode=table&FrmID="+t.No,e.UrlPath="/src/CCFast/CCBill/SearchDict.vue",e.FrmID=t.No,e.ListModel=0,e.WorkType="0",yield e.Insert();const w=T.UrlEn("TS.CCBill.FlowDtlView",t.No);return new m(c.GoToUrl,w,"属性")}if(n=="GenerListEn"){e.MenuModel=n,e.Name=i;const t=new Q;t.No=U.GenerGUID(),t.Name=i,yield t.Insert(),e.No=t.No,e.Icon="icon-wallet",e.SetPara("EnName","TS.CCBill.GenerListEn"),e.Tag1=t.No,e.Alias=r+"_"+t.No,e.UrlPath="/@/WF/views/GenerList.vue",e.UrlExt=`/WF/Comm/GenerList_${t.No}?EnName=GL_GLEn&EnID=`+t.No+"&Title="+t.Name,e.MobileUrlExt="self://CCMobile/GenerList?EnName=GL_GLEn&EnID="+t.No+"&Title="+t.Name,yield e.Insert();const a=T.UrlEn("TS.CCBill.GenerListEn",t.No);return new m(c.GoToUrl,a,"设计通用列表")}const y=["TabPage.Ens.Paras","DataV.Ens.Paras","Entity.EnName.CompentType.Paras","Search.EnName.Paras","GPN.Ens.Paras","TreeEns.Ens.Paras","GL.Ens.Paras","PG.Ens.Paras","Tabs.Ens.Paras"],G=new Map([["DataV",{file:"/@/views/data_visualization/index.vue",urlPrefix:"/WF/Comm/DataV",MUrlPrefix:"CCMobile/DataV",factory:yield p("ClassFactoryOfDataV")}],["TabPage",{file:"/@/components/Tabs/index.vue",urlPrefix:"/WF/Comm/Tabs",MUrlPrefix:"CCMobile/Tabs",factory:yield p("ClassFactoryOfTabs")}],["Search",{file:"/@/WF/Comm/Search.vue",urlPrefix:"/WF/Comm/Search",MUrlPrefix:"CCMobile/Search",factory:yield p("ClassFactory")}],["Group",{file:"/@/WF/Comm/Group.vue",urlPrefix:"/WF/Comm/Group",factory:yield p("ClassFactory")}],["Tree",{file:"/@/WF/Comm/Tree.vue",urlPrefix:"/WF/Comm/Tree",factory:yield p("ClassFactory")}],["GPN",{file:"/@/WF/Comm/UIEntity/GroupPageNew.vue",urlPrefix:"/WF/Comm/GroupPageNew",factory:yield p("ClassFactoryOfGroupPageNew")}],["TreeEns",{file:"/@/WF/Comm/TreeEns.vue",urlPrefix:"/WF/Comm/TreeEns",factory:yield p("ClassFactoryOfPageBaseTreeEns")}],["GL",{file:"/@/WF/views/GenerList.vue",urlPrefix:"/WF/Comm/GenerList",factory:yield p("ClassFactoryOfGenerList")}],["PG",{file:"/@/WF/Comm/PanelGroup.vue",urlPrefix:"/WF/Comm/PanelGroup",factory:yield p("ClassFactoryOfPanelGroup")}]]);if(y.includes(n)){const t=n.split(".")[0],a=n.split(".")[1],o=G.get(t);if(!o)return d.error("抱歉，你输入的类型不存在"),new m(c.DoNothing,null);const{file:w,urlPrefix:u,MUrlPrefix:B,factory:S}=o,h=this.RequestVal("tb1",t+"."+a),A=yield S.GetEn(h);if(e.ModuleNo=r,e.Name=((v=A==null?void 0:A._enMap)==null?void 0:v.EnDesc)||A.PageTitle,e.Alias=r+"_"+h,e.UrlPath=w,e.UrlExt=u+"?EnName="+h+"&"+i,e.SystemNo=this.SystemNo,e.MenuModel=l.FixedUrl,B){const b=i.trim().startsWith("&")?"":"&";e.MobileUrlExt="self://"+B+"?EnName="+h+b+i}return e.IsEnable=1,h.includes("DataV_")==!0&&(e.Icon=" icon-pie-chart"),h.includes("GPN_")==!0&&(e.Icon=" icon-doc"),h.includes("Tabs_")==!0&&(e.Icon=" icon-wallet"),h.includes("GL_")==!0&&(e.Icon=" icon-playlist"),h.includes("TreeEns_")==!0&&(e.Icon=" icon-organization"),h.includes("Tree_")==!0&&(e.Icon=" icon-organization"),yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n==="Entity.EnName.componentName.Paras"){const t="/@/WF/Comm/",a=this.RequestVal("tb1","Entity.EnName"),o=this.RequestVal("tb1","Entity.EnName.componentName"),u=yield(yield p("ClassFactory")).GetEn(a);if(e.ModuleNo=r,e.Name=((k=u==null?void 0:u._enMap)==null?void 0:k.EnDesc)||u.PageTitle,e.Alias=r+"_"+a,e.UrlPath=t+o+".vue",i+="",i=i.trim(),i.length===0){const B=a.lastIndexOf(".");i=a.substring(B)+o}return e.UrlExt=U.GenerGUID().substring(5)+"?EnName="+a,typeof s=="string"&&s.trim().length>0&&(e.UrlExt=i+"?EnName="+a+"&"+s.replace(/\?/g,"").replace(/^&+/g,"")),e.SystemNo=this.SystemNo,e.MenuModel=l.FixedUrl,e.IsEnable=1,o=="Group"&&(e.Icon="icon-pie-chart"),o=="Search"&&(e.Icon="icon-magnifier"),o=="Ens"&&(e.Icon="icon-pencil"),o=="TabPage"&&(e.Icon="icon-wallet"),o=="Tree"&&(e.Icon="icon-organization"),o=="En"&&(e.Icon="icon-home"),o=="EnOnly"&&(e.Icon="icon-home"),yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n===l.SelfUrl){e.ModuleNo=r,e.Name=i,e.UrlPath=s,e.UrlExt=P,e.SystemNo=this.SystemNo,e.MenuModel=l.SelfUrl,e.IsEnable=1;const t=new _("BP.WF.HttpHandler.WF_Admin_FoolFormDesigner");return t.AddPara("name",i),t.AddPara("flag",!0),e.Alias=yield t.DoMethodReturnString("ParseStringToPinyin"),yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n==="LinkFlow.FlowNo"){const t=this.RequestVal("tb1","LinkFlow"),a=i,o=new Z(a);return yield o.Retrieve(),e.MenuModel="RefFlow",t=="Home"&&(e.Name="主页:"+o.Name,e.Alias=r+"_GL_CC"+o.No,e.UrlPath="/src/WF/Comm/En.vue",e.UrlExt=`/Flow_Home_${o.No}?EnName=TS.TSClass.FlowOneSetting&PKVal=${o.No}`),t=="FlowSearch"&&(e.Name=o.Name+"查询",e.Alias="SearchFlow"+o.No,e.UrlPath="/src/WF/Rpt/SearchFlow.vue?FlowNo="+o.No,e.UrlExt=`/SearchFlow_${o.No}?FlowNo=${o.No}`,e.Icon="icon-magnifier-add"),t=="FlowGroup"&&(e.Name=o.Name+"分析",e.Icon="icon-chart",e.Alias="FlowGroup"+o.No,e.UrlPath="/src/WF/Rpt/GroupFlow.vue?FlowNo="+o.No,e.UrlExt=`/GroupFlow_${o.No}?FlowNo=${o.No}`),t=="Start"&&(e.Alias=r+"_"+a,e.UrlPath="",e.UrlExt="self://WF/MyFlow?FlowNo="+a,e.Name="发起:"+o.Name,e.Icon="icon-paper-plane",e.MobileUrlExt=`self://CCMobile/MyFlow?FlowNo=${a}&Title=待办&FK_Flow=${o.No}`),t=="Todolist"&&(e.Name="待办:"+o.Name,e.Alias=r+"_GL_Todolist"+o.No,e.UrlPath="/@/WF/views/GenerList.vue",e.UrlExt=`/WF/Comm/GL_Todolist_${a}?EnName=GL_Todolist&FlowNo=${o.No}`,e.Icon="icon-clock",e.MobileUrlExt=`self://CCMobile/GenerList?EnName=GL_Todolist&FlowNo=${a}&Title=待办&FK_Flow=${o.No}`),t=="Runing"&&(e.Name="在途:"+o.Name,e.Alias=r+"_GL_Runing"+o.No,e.UrlPath="/@/WF/views/GenerList.vue",e.UrlExt=`/WF/Comm/GL_Running_${a}?EnName=GL_Runing&FlowNo=${o.No}`,e.Icon="icon-hourglass",e.MobileUrlExt=`self://CCMobile/GenerList?EnName=GL_Running&FlowNo=${a}&Title=在途&FK_Flow=${o.No}`),t=="Complete"&&(e.Name="已完成:"+o.Name,e.Alias=r+"_GL_Complete"+o.No,e.UrlPath="/@/WF/views/GenerList.vue",e.UrlExt=`/WF/Comm/GL_Complete_${a}?EnName=GL_Complete&FlowNo=${o.No}`,e.Icon="icon-check",e.MobileUrlExt=`self://CCMobile/GenerList?EnName=GL_Complete&FlowNo=${a}&Title=已完成&FK_Flow=${o.No}`),t=="DataV"&&(e.Name="报表"+o.Name,e.Alias=r+"_GL_DataV"+o.No,e.UrlPath="/@/WF/views/DataV.vue",e.UrlExt=`/WF/Comm/DataV_OneFlowEmp_${a}?EnName=DataV_OneFlowEmp_&FlowNo=${o.No}`+this.SystemNo,e.Icon="icon-check",e.MobileUrlExt=`self://CCMobile/DataV?EnName=GL_Complete&FlowNo=${a}&Title=已完成&FK_Flow=${o.No}`),t=="CC"&&(e.Name="抄送:"+o.Name,e.Alias=r+"GL_CC"+o.No,e.UrlPath="/@/WF/views/GenerList.vue",e.UrlExt=`/WF/Comm/GL_CC_${a}?EnName=GL_CC&FlowNo=${o.No}`,e.Icon="icon-bag",e.MobileUrlExt=`self://CCMobile/GenerList?EnName=GL_CC&FlowNo=${a}&Title=抄送&FK_Flow=${o.No}`),e.ModuleNo=r,e.SystemNo=this.SystemNo,e.MenuModel="RefFlow",e.FlowNo=o.No,e.IsEnable=1,e.SetPara("FlowNo",o.No),yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n==="EntityNoNameLink.Ref"){const t=this.RequestVal("tb1","EntityNoNameLink"),a=i,o=new R(a);return yield o.Retrieve(),t=="Home"&&(e.Name="主页:"+o.Name,e.Alias=r+"_Dict_Home_"+o.No,e.UrlPath="/@/WF/Comm/En.vue",e.UrlExt=`/Dict_Home_${o.No}?EnName=TS.CCBill.EntityNoNameSettingOne&PKVal=${o.No}`),t=="Search"&&(e.Name="列表:"+o.Name,e.Alias=r+"_List_"+o.No,e.UrlPath="/@/CCFast/CCBill/SearchEntityNoName.vue",e.UrlExt=`/NoName_List_${o.No}?FrmID=${o.No}`),t=="Group"&&(e.Name="分析:"+o.Name,e.Alias=r+"_Analy_"+o.No,e.UrlPath="/@/CCFast/CCBill/SearchEntityNoName.vue",e.UrlExt=`/NoName_Analy_${o.No}?FrmID=${o.No}&displayMode=group`),t=="Rpt"&&(e.Name="报表:"+o.Name,e.Alias=r+"_Rpt_"+o.No,e.UrlPath="/@/CCFast/CCBill/SearchEntityNoName.vue",e.UrlExt=`/NoName_Rpt_${o.No}?FrmID=${o.No}&displayMode=rpt`),t=="BS"&&(e.Name="大屏:"+o.Name,e.Alias=r+"_BigScreen_"+o.No,e.UrlPath="/src/CCFast/Views/RptWhiteMain.vue",e.UrlExt=`/NoName_BigScreen_${o.No}?PageID=FrmEntityNoName${o.No}`),e.ModuleNo=r,e.SystemNo=this.SystemNo,e.MenuModel=l.SelfUrl,e.IsEnable=1,yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n==="BillLink.FrmID"){const t=this.RequestVal("tb1","BillLink"),a=i,o=new R(a);return yield o.Retrieve(),t=="Home"&&(e.Name="主页:"+o.Name,e.Alias=r+"_Dict_Home_"+o.No,e.UrlPath="/@/WF/Comm/En.vue",e.UrlExt=`/Dict_Home_${o.No}?EnName=TS.CCBill.BillSettingOne&PKVal=${o.No}`),t=="Search"&&(e.Name="列表:"+o.Name,e.Alias=r+"_List_"+o.No,e.UrlPath="/@/CCFast/CCBill/SearchBill.vue",e.UrlExt=`/Dict_List_${o.No}?FrmID=${o.No}`),t=="Group"&&(e.Name="分析:"+o.Name,e.Alias=r+"_Analy_"+o.No,e.UrlPath="/@/CCFast/CCBill/SearchBill.vue",e.UrlExt=`/Dict_Analy_${o.No}?FrmID=${o.No}&displayMode=group`),t=="Rpt"&&(e.Name="报表:"+o.Name,e.Alias=r+"_Rpt_"+o.No,e.UrlPath="/@/CCFast/CCBill/SearchBill.vue",e.UrlExt=`/Dict_Rpt_${o.No}?FrmID=${o.No}&displayMode=rpt`),t=="BS"&&(e.Name="大屏:"+o.Name,e.Alias=r+"_BigScreen_"+o.No,e.UrlPath="/src/CCFast/Views/RptWhiteMain.vue",e.UrlExt=`/Dict_BigScreen_${o.No}?PageID=${o.No}`),e.ModuleNo=r,e.SystemNo=this.SystemNo,e.MenuModel=l.SelfUrl,e.IsEnable=1,yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n===l.StandAloneFlow){const t=new _("BP.WF.HttpHandler.WF_GPM_CreateMenu");t.AddPara("SortNo",r),t.AddPara("FlowName",i),t.AddPara("FlowDevModel",0),t.AddPara("ModuleNo",r);const a=yield t.DoMethodReturnString("StandAloneFlow_Save");if(typeof a=="string"&&a.startsWith("err@")){alert(a);return}return e.ModuleNo=r,e.Name=i,e.UrlExt=s,e.MenuModel=l.StandAloneFlow,yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n===l.RptWhite)return e.Name=i,e.MenuModel=l.RptWhite,e.IsEnable=1,e.Icon="icon-screen-desktop",e.SetPara("EnName","TS.CCFast.Rpt3D"),e.UrlPath="/src/CCFast/Views/RptWhiteMain.vue",yield e.Insert(),e.UrlExt="/RptWhite"+e.No.substring(0,6)+"?PageID="+e.No,yield e.Update(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null);if(n==="SFTable")return e.Name=s,e.MenuModel="SFTable",e.IsEnable=1,e.Icon="icon-screen-desktop",e.UrlPath="/src/WF/Admin/FrmLogic/SFTable/DictNoName.vue",yield e.Insert(),e.UrlExt="/DictNoName?FK_SFTable="+i,yield e.Update(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null);if(n===l.RptBlue){e.Name=i,e.Tag1=Y(),e.MenuModel=l.RptBlue,e.IsEnable=1,e.Icon="icon-screen-desktop",e.SetPara("EnName","TS.CCFast.RptBlue"),e.UrlPath="/src/CCFast/Views/RptBlueMain.vue",e.path="#/chart/home/"+e.Tag1,yield e.Insert(),e.UrlExt="/RptBlue"+e.No.substring(0,6)+"?PageID="+e.No,yield e.Update();const t=new _("BP.WF.HttpHandler.Third.Third_GoView");return t.AddPara("myPk",e.Tag1),yield t.DoMethodReturnString("CreateProject"),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n===l.Tabs)return e.Name=i,e.MenuModel=l.Tabs,e.IsEnable=1,yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null);if(n===l.Rpt3D)return e.Name=i,e.Icon="icon-screen-desktop",e.MenuModel=l.Rpt3D,e.SetPara("EnName","TS.CCFast.Rpt3D"),yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null);if(n===l.FlowUrl){alert("未实现");return}if(n===l.Func){const t=new j;return t.Name=i,t.FuncID=s,yield t.Insert(),e.Name=t.Name,e.MenuModel=l.Func,e.Icon="icon-energy",e.UrlExt=t.No,e.SetPara("EnName","TS.CCFast.Func"),e.SetPara("EnPKVal",t.No),e.No=t.No,yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n==="Dict"||n==="EntityNoName"||n==="Bill"){const t=new te;if(t.No=e.SystemNo,(yield t.IsExits())==!1){let a="CCFast";D.CCBPMRunModel!=I.Single&&(a="CCFast_"+f.OrgNo),t.No=a,(yield t.IsExits())==!1&&(t.Name="低代码表单",D.CCBPMRunModel==I.Single?t.ParentNo="1":t.ParentNo=f.OrgNo||"1",t.OrgNo=f.OrgNo,yield t.Insert()),t.No=e.SystemNo;const o=new $;o.No=e.SystemNo,yield o.Retrieve(),t.Name=o.Name,D.CCBPMRunModel==I.Single?t.ParentNo=a:t.ParentNo=f.OrgNo||"1",t.OrgNo=f.OrgNo,yield t.Insert()}}if(n==="Dict"){const t=s,a=i,o=new _("BP.WF.HttpHandler.WF_Admin_CCFormDesigner");return o.AddPara("TB_No",t),o.AddPara("TB_Name",a),o.AddPara("TB_PTable",t),o.AddPara("FK_FrmSort",e.SystemNo),o.AddPara("EntityType",2),yield o.DoMethodReturnString("NewFrmGuide_Create"),e.Name=a,e.UrlExt=t+"?displayMode=table&FrmID="+t,e.UrlPath="/src/CCFast/CCBill/SearchDict.vue",e.FrmID=t,e.MenuModel=l.Dict,e.ListModel=0,e.WorkType="0",e.SetPara("EnName","TS.CCBill.FrmDict"),e.SetPara("EnPKVal",t),yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n==="EntityNoName"){const t=s,a=i,o=new _("BP.WF.HttpHandler.WF_Admin_CCFormDesigner");return o.AddPara("TB_No",t),o.AddPara("TB_Name",a),o.AddPara("TB_PTable",t),o.AddPara("EntityType",5),o.AddPara("FK_FrmSort",e.SystemNo),yield o.DoMethodReturnString("NewFrmGuide_Create"),e.Name=a,e.UrlExt=t+"?displayMode=table&FrmID="+t,e.UrlPath="/src/CCFast/CCBill/SearchEntityNoName.vue",e.FrmID=t,e.MenuModel=l.EntityNoName,e.ListModel=0,e.WorkType="0",e.SetPara("EnName","TS.CCBill.FrmEntityNoName"),e.SetPara("EnPKVal",t),yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n===l.DBList){const t=s,a=i,o=new _("BP.WF.HttpHandler.WF_Admin_CCFormDesigner");o.AddPara("TB_No",t),o.AddPara("TB_Name",a),o.AddPara("TB_PTable",t),o.AddPara("EntityType",2);const w=yield o.DoMethodReturnString("NewFrmGuide_Create_DBList");if(typeof w=="string"&&w.startsWith("err@")){d.error(w);return}e.Name=a,e.UrlExt=t+"?FrmID="+t,e.UrlPath="/src/CCFast/CCBill/SearchDBList.vue",e.MenuModel=l.DBList,e.FrmID=t,e.ListModel=0,e.WorkType="0",e.Icon="icon-book-open",yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权.");const u=T.UrlEn("TS.CCBill.DBList",t);return new m(c.GoToUrl,u)}if(n===l.DictCopy){alert("未翻译..");return}if(n===l.Bill){const t=s,a=i,o=new _("BP.WF.HttpHandler.WF_Admin_CCFormDesigner");return o.AddPara("TB_No",t),o.AddPara("TB_Name",a),o.AddPara("TB_PTable",t),o.AddPara("EntityType",1),o.AddPara("FK_FrmSort",e.SystemNo),yield o.DoMethodReturnString("NewFrmGuide_Create"),e.Name=a,e.UrlExt=t+"?displayMode=table&FrmID="+t,e.UrlPath="/src/CCFast/CCBill/SearchBill.vue",e.FrmID=t,e.MenuModel=l.Bill,e.ListModel=0,e.WorkType="0",e.SetPara("EnName","TS.CCBill.FrmBill"),e.SetPara("EnPKVal",t),e.Icon="icon-layers",yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n===l.DictRef){alert("未翻译..");return}if(n===l.DictTable){const t=new K;if(t.No=s,t.Name=i,t.No==i||t.Name==""){alert("err@名称与编号不能为空.");return}if(yield t.IsExits()){d.warning("编号已经存在["+t.No+"]请使用其他的编号.");return}return t.DBSrcType="SysDict",t.CodeStruct=0,yield t.Insert(),e.Name=t.Name,e.UrlExt=t.No,e.MenuModel=l.DictTable,e.SystemNo=this.SystemNo,e.SetPara("CodeStruct",0),yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n===l.DictTableTree){const t=new K;if(t.No=s,t.Name=i,t.No==i||t.Name==""){alert("err@名称与编号不能为空.");return}if(yield t.IsExits()){alert("编号已经存在["+t.No+"]请使用其他的编号.");return}return t.SrcType="SysDict",t.CodeStruct=1,e.SetPara("CodeStruct",1),yield t.Insert(),e.Name=t.Name,e.UrlExt=t.No,e.MenuModel=l.DictTableTree,yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n===l.Task)return e.Name=i,e.UrlExt="/src/CCFast/Task/Task.htm",e.UrlPath=e.UrlExt,e.MenuModel="Task",e.Mark="Task",e.Icon="icon-note",e.SetPara("EnName","TS.GPM.MenuExt"),yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null);if(n===l.Info){const t=new g;t.ModuleNo=r,t.SystemNo=this.SystemNo,t.Name=i,t.UrlExt="/src/WF/Comm/Search.vue?EnsName=TS.CCOA.CCInfo.Info",t.UrlPath=t.UrlExt,t.MenuModel="Info",t.Mark="Info",t.Icon="icon-note",t.Insert();const a=new g;return a.ModuleNo=r,a.SystemNo=this.SystemNo,a.Name=i+"类型",a.UrlExt="/src/WF/Comm/Ens.vue?EnsName=TS.CCOA.CCInfo.InfoType",a.UrlPath=a.UrlExt,a.MenuModel="Info",a.Mark="Info",a.Icon="icon-note",a.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)}if(n===l.Notepad)return e.Name=i,e.UrlExt="/src/CCOA/Notepad/Notepad.vue",e.UrlPath=e.UrlExt,e.MenuModel="Notepad",e.Mark="Notepad",e.Icon="icon-note",e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null);if(n===l.KnowledgeManagement)return e.Name=i,e.UrlExt="/src/CCOA/KnowledgeManagement/Default.vue",e.UrlPath=e.UrlExt,e.MenuModel="KnowledgeManagement",e.Mark="KnowledgeManagement",e.Icon="icon-eyeglass",e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null);if(n===l.WorkRec)return e.ModuleNo=r,e.Name=i,e.UrlPath="/src/WF/Comm/Search.vue?EnsName=TS.CCOA.WorkLog.WorkRec&1=1",e.MobileUrlExt="/@/CCMobile/Comm/Search.vue",e.UrlExt="/src/WF/Comm/Search.vue?EnsName=TS.CCOA.WorkLog.WorkRec&1=1",e.UrlPath=e.UrlExt,e.SystemNo=this.SystemNo,e.MenuModel="WorkRec",e.IsEnable=1,e.Alias=U.GenerGUID(),yield e.Insert(),d.info("创建成功，您可以在在菜单里执行高级编辑与授权."),new m(c.CloseAndReload,null)})}}export{Rt as GPN_Menu};
