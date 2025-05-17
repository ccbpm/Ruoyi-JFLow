var R=Object.defineProperty;var U=(y,B,o)=>B in y?R(y,B,{enumerable:!0,configurable:!0,writable:!0,value:o}):y[B]=o;var n=(y,B,o)=>U(y,typeof B!="symbol"?B+"":B,o);var P=(y,B,o)=>new Promise((F,i)=>{var m=d=>{try{a(o.next(d))}catch(C){i(C)}},N=d=>{try{a(o.throw(d))}catch(C){i(C)}},a=d=>d.done?F(d.value):Promise.resolve(d.value).then(m,N);a((o=o.apply(y,B)).next())});import{P as W,F as l,G as p,m as c,bj as A,C as L,W as I}from"./entry/index-B5R3Coa4-1746862693206.js";import{Cond as D}from"./Cond-DK8vpfBx.js";import{Direction as T}from"./Direction-CcZW8WKq.js";import{GloComm as S}from"./GloComm-B1xAfTWw.js";import{Node as _}from"./Node-BsvTqXX9.js";import{CCRole as w}from"./CCRole-DkWGiGnM.js";import{MapAttr as G}from"./MapAttr-DcWjEeWW.js";import{a as x}from"./SelecterFree-Bc9GnN8O.js";import{SysEnumMain as O}from"./SysEnumMain-NEBgQX-D.js";import{SFProc as K}from"./SFProc-sEDsGGaw.js";import M from"./HttpHandler-CdnQkxwF.js";import{b as u}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./Events-D9tOL1Ad.js";import"./EntityNodeID-3BfNz0DC.js";import"./FrmTrack-Ct-No0Nq.js";import"./BtnLab-BrqFWTRb.js";import"./EnumLab-CLN2gIih.js";import"./FrmNode-DY1LmIad.js";import"./MapData-D5zymw8O.js";import"./EnumLab-CsLi93T0.js";import"./PageBaseGroupEdit-BXdNWKIo.js";import"./Help-D0bDMZWg.js";import"./DeliveryWay-BAEDV0E6.js";import"./AccepterRoleBindStation-B0KDm-rx.js";import"./GPE_ShenFenModel-CDaf2X1j.js";import"./NodeStation-CfLhx0Jt.js";import"./AccepterRoleBindEmp-Do7qOE3S.js";import"./NodeEmp-D1XusAeU.js";import"./AccepterRoleBindDeptStation-RcuGzc8p.js";import"./NodeDept-DwTBCm8N.js";import"./AccepterRoleBindDept-CS-D68AO.js";import"./AccepterRoleBindDeptOrg-H2qK1m_v.js";import"./AccepterRoleBindSFTable-CKsJdwZV.js";import"./ARBindWebApi-DsXOZoff.js";import"./ARBindStationSpecDept-BW1Gj49d.js";import"./GPE_ARStaModel-C5Lve2iK.js";import"./ARStation-CHDbmjA7.js";import"./AR501-WSxUxXCU.js";import"./AccepterRoleMLeader-BxGIp5Ty.js";import"./ARWebAPI-B1dpwwZ8.js";import"./RouteAttr2Emp-CcEPZqh9.js";import"./GPE_TurnTo-C5_azrAl.js";import"./GPE_TodolistModel-Bzl2xshV.js";import"./GPE_TeamleaderConfirm-aKWUh-Jj.js";import"./GPE_XieZuoOverRole-BuyFA8df.js";import"./NodeToolbar-Coj3wMaH.js";import"./EntityOID-C1xznxai.js";import"./SubFlow-CksT2Xak.js";import"./GPE_OvertimeRole-DEiRNj4I.js";import"./FrmNodeExt-BbLaf_ZC.js";import"./GPE_FrmNodeEnableRole-CsCcYUu6.js";import"./GPE_FrmCtrlSln-bsMQaFKO.js";import"./FrmNodeBatch-B6-rkZGP.js";import"./GPE_FrmTransfer-w090zHWH.js";import"./FrmTransferCustom-BIS71SrX.js";import"./SysEvent-DymzJjDC.js";import"./PushMsg-CTLXMVMh.js";import"./GPE_BlockModel-Biafiq9Y.js";import"./GPE_CCWriteRole-BmiBTZXX.js";import"./SysEnum-DmKE2Ig7.js";import"./SFParaSln-ekMdP4Zl.js";class r{}n(r,"CondByFrm","0"),n(r,"StandAloneFrm","1"),n(r,"CondStation","2"),n(r,"CondDept","3"),n(r,"CondBySQL","4"),n(r,"CondBySQLTemplate","5"),n(r,"CondByPara","6"),n(r,"CondByUrl","7"),n(r,"CondByWebApi","8"),n(r,"CondByWorkCheck","9"),n(r,"CondLeaderOfDept","10"),n(r,"CondEmp","11"),n(r,"CondByProc","12"),n(r,"CondByBuessUnit","13"),n(r,"Operator","100");class rt extends W{constructor(){super("GPN_Cond");n(this,"CondByWorkCheck",`
  #### 帮助
  - 审核组件立场.
  `);n(this,"ByFrm",`
  #### 帮助
  - 按照表单的字段值来计算,是常用的一个方向条件,前提是您不是采用sdk模式开发的,是使用ccbpm内置的表单.
  - 场景: 请假流程的请假天数转向条件, 合同审批流程的合同金额,作为转向条件.
  - 请选择一个表单,点击创建按钮.
  #### 配置实例图
  - 当有多个条件的时这些才有用.
  `);n(this,"ByCond100",`
  #### 帮助
  - 条件表达式包括 ( 、) 、AND、 OR 四个类型.
  - 用于链接条件, 只有正确的配置好条件表达式条件才可以工作.
  - 我们提供检查功能，来帮助您检查条件表达式是否正确.
  - 您可以使用拖动的，来调整位置.
  #### 其它
  - 当有多个条件的时这些才有用.
  `);n(this,"BySQL",`
  #### 帮助
  - 按SQL表达式计算.
  - 设置一个查询SQL, 返回一行一列，获取一个数值，如果大于 0条件=true, 否则 =false.
  - 比如: SELECT count(*) from port_emp WHERE FK_Dept='@WebUser.DeptNo' or xxxx=@MyFieldName
  - 支持ccbpm表达式, 可以获取当前登录人员的信息变量,也可以获取表单字段变量.
  `);n(this,"sqlTemplate",`
  #### 帮助
  - 按照SQL模板计算，与按SQL计算类似. 我们把常用的SQL放入sql模板库存储起来.
  - 这里只是一个引用，不是copy. 就是说当sql模板的配置信息变化后，这里跟着变化.
  - 支持ccbpm表达式, 可以获取当前登录人员的信息变量,也可以获取表单字段变量.
  #### 其它
  - 请在系统管理里维护SQL模板.
  `);n(this,"CondByPara",`
  #### 帮助
  - 所谓的开发者参数，就是开发人员在执行流程过程中(发送、退回),向接口传入的参数作为条件.
  - 比如: 发送方法
 #### DEMO
  //组织参数.
  Hashtable ht = new Hashtable();
  ht.Add("PrjNo", "项目编号"); 
  ht.Add("PrjName", "项目名称"); 
  ht.Add("JinE", 500000.00); //项目金额, 根据项目金额大小自动转向.
  //调用发送接口.
    BP.WF.Dev2Interface.Node_SendWork("001", 1002, ht, null);
  
  `);n(this,"CondByUrl",`
  #### 帮助
  - URL就是通过http协议,运行一个url,返回数据，根据数据内容作为条件的一种方式.
  - 返回值是 err@开头的字符串，说明系统是有异常。
  - 返回值 >0 条件 =true,  否则为false.
  #### 配置参数
  - 配置格式: http://ccflow.org/xxx.jsp
  - 系统解析格式: http://ccflow.org/xxx.do?WorkID=xxxx&FK_Flow=001&FK_Node=101&UserNo=zhangsan&Token=xxx-xx-xxx
  - 系统会自动把当前环境已知的参数加里面去, 开发人员可以通过 WorkID获取流程的实例的其它数据,可以通过UserNo, Token来校验合法性.
  `);n(this,"ByBuessUnit",`
    #### 帮助
   - 通过调用业务单元，根据业务单元返回的值，来判断方向条件.
   - 返回，true, false 或者1,0 . 来决定方向条件是否通过.
#### 其他
- ccbpm提供了一个让后台开发人员使用的代码表达业务逻辑实现的方式,业务单元是其中的一种.
- 定义: 处理一段业务逻辑脚本, 我们称为业务单元,比如:付款,出库. 
- 这个业务模块有通用性,可以被很多流程所调用,我们把它封装为一个业务单元.
- 这个代码块从一个基类上继承下来（BP.Sys.BuessUnitBase）. 按照要求重写方法. 
- 在配置的时候，ccbpm通过基类的反射功能，放入到下拉框，方便流程设计人员进行选择配置.
#### DEMO.
- 定义一个子类，如下图:
![输入图片说明](./resource/WF/Admin/FrmLogic/MapData/FrmEvent/Img/UnitDemo.png "屏幕截图.png")  
- 在BP类里定义一个业务单元类 如下图中的 出库信息初始化 BuessUnitFrmND1407 ，继承自 BP.Sys.BuessUnitBase
![输入图片说明](./resource/WF/Admin/FrmLogic/MapData/FrmEvent/Img/BuessUnitBase.Java.png "屏幕截图.png")  
- 在后台选择这个类配置到表单事件中。

#### 配置图例
![输入图片说明](./resource/WF/Admin/FrmLogic/MapData/FrmEvent/Img/Event.png "屏幕截图.png")  
#### 配置图例
![输入图片说明](./resource/WF/Admin/FrmLogic/MapData/FrmEvent/Img/Event1.png "屏幕截图.png")  
#### 事件存储
 所有的事件配置信息都存储在Sys_FrmEvent表里。
![输入图片说明](./resource/WF/Admin/FrmLogic/MapData/FrmEvent/Img/Eventcc.png "屏幕截图.png")  
`);n(this,"ByProc",`
  #### 帮助
   - 改模式的条件是通过数据源的过程返回值来判断方向条件的.
   - 数据源的过程返回，true, false 或者1,0 . 来决定方向条件是否通过.
  #### 数据源概念.
   - 首先了解数据源概念，请打开=》数据源设计=》帮助.
  `);n(this,"WebApi",`
  #### 帮助
   - 返回值说明,"false"是不通过，"true"是通过。
   - WebAPI的输入格式：http://demo.ccflow.org/DataUser/GetEmps?id=51184
   - 接口地址支持固定参数，或者ccbpm内置参数，或者流程表单参数，比如:http://demo.ccflow.org/DataUser/GetEmps?id=@FK_Node
   #### 其它
   - 返回值是 err@开头的字符串，说明系统是有异常。
   - 返回值 >0 条件 =true,  否则为false.
  `);n(this,"ByStation",`
  #### 帮助
   - 按角色计算: 就是指定身份的人员拥有的角色是否与条件设置的角色集合是否有交集来判断条件的一种方式. 
   - 有交集=true,无交集=false.
   - 该方式使用比较广泛, 比如,请假人是中层角色，走那个路线，高层角色走那个路线.
   - 配置方式:选择角色集合,点创建按钮.
  ##### 人员身份
   - 就是按那个操作员的身份计算,确定人员身份求他的角色集合.
   - 默认为当前操作员的身份计算.
  `);n(this,"ByLeaderOfDept",`
  #### 帮助
  - 按部门计算: 就是指定身份的人员,是不是设置部门集合的负责人.
 #### 人员身份
  - 就是按那个操作员的身份计算,确定人员身份求他的部门集合.
  - 默认为当前操作员的身份计算.
#### 解释
 -  锁定一个人员,判断该人员是否是部门负责人.
  `);n(this,"ByEmp",`
  #### 帮助
  - 选择的人员可以走这个路径.
  - 就是按那个操作员的身份计算,确定人员身份求他的部门集合.
  - 默认为当前操作员的身份计算.
  `);n(this,"ByDept",`
  #### 帮助
  - 按部门计算: 就是指定身份的人员拥有的部门是否与条件设置的部门集合是否有交集来判断条件的一种方式. 
  - 有交集=true,无交集=false.
  - 该方式使用比较广泛, 比如：什么角色的人走那个路线.
  - 配置方式:选择部门集合,点创建按钮.
 ##### 人员身份
  - 就是按那个操作员的身份计算,确定人员身份求他的部门集合.
  - 默认为当前操作员的身份计算.
  `);this.PageTitle="新建条件/表达式",this.ForEntityClassID="TS.WF.Cond"}Init(){return P(this,null,function*(){this.AddGroup("A","条件表达式"),this.AddBlank("Left","左括号",this.ByCond100),this.AddBlank("Right","右括号",this.ByCond100),this.AddBlank("AND","AND",this.ByCond100),this.AddBlank("OR","OR",this.ByCond100),this.AddGroup("B","内置表单条件");let o="001",F=101;if(this.RefMainEnName.includes("CCRole")==!0){const a=new w;a.MyPK=this.RefPKVal,a.RetrieveFromDBSources(),F=a.NodeID,o=a.FlowNo}else if(this.RefMainEnName.includes("NodeExt")==!0){F=this.RefPKVal;const a=new x(F);yield a.Retrieve(),o=a.FK_Flow}else{const a=new T;a.MyPK=this.RefPKVal,yield a.RetrieveFromDBSources(),F=a.Node,o=a.FK_Flow}const i="ND"+Number(o)+"Rpt";this.SelectItemsByList("CondByFrm","表单字段条件",this.ByFrm,!1,l.SQLOfCondByFrm(F,i)),this.SelectItemsByGroupList("CondByFrm.SelectField","选择字段",this.ByFrm,!1,()=>{const a=this.RequestVal("tb1","CondByFrm");return l.SQLOfCondSelectField(a)},()=>{const a=this.RequestVal("tb1","CondByFrm");return l.SQLOfCondByFrmSelectField(a,"'OID','FID','AtPara','GUID','WFState','WFSta'")}),this.TextBox1_Name(r.CondByWorkCheck,"审核组件的立场",this.CondByWorkCheck,"输入立场中文名称","同意","在审核组件中的立场配置."),this.AddGroup("C","组织结构条件"),this.SelectItemsByGroupList(r.CondStation,"按角色计算",this.ByCond100,!0,l.srcStationTypes,l.srcStations),this.SelectItemsByTree(r.CondDept,"按部门计算",this.ByDept,!0,l.srcDepts,l.srcDeptRoot),this.SelectItemsByTree(r.CondLeaderOfDept,"判断部门负责人",this.ByDept,!0,l.srcDepts,l.srcDeptRoot),this.SelectItemsByTreeEns(r.CondEmp,"按人员计算",this.ByEmp,!0,l.srcDeptLazily,"0",l.srcEmpLazily,"@No=账号@Name=名称@Tel=电话",!0),this.AddGroup("D","开发接口条件"),this.SelectItemsByList(r.CondByBuessUnit,"业务单元-BuessUnit",this.ByBuessUnit,!1,yield this.GenerBuessUnit()),this.SelectItemsByGroupList(r.CondByProc,"按自定义过程计算",this.ByProc,!1,l.srcDBSrc,l.srcSFProc),this.AddIcon("icon-energy",r.CondByProc),this.TextSQL(r.CondBySQL,"按SQL表达式计算",this.BySQL,"SQL表达式"," SELECT count(*) AS NUM FROM MyTable WHERE MyField='@WebUser.No' ","请设置一个SQL语句,返回是number"),this.AddIcon("icon-doc",r.CondBySQL),this.SelectItemsByList(r.CondBySQLTemplate,"按SQL模板条件计算",this.sqlTemplate,!1,"SELECT No as No,Name as Name FROM WF_SQLTemplate WHERE SQLType=0 "),this.AddIcon("icon-cup",r.CondBySQLTemplate),this.TextBox1_Name(r.CondByPara,"按开发者参数计算",this.CondByPara,"参数","","请输入表达式,比如:Jine > 1000 "),this.AddIcon("icon-playlist",r.CondByPara),this.TextBox1_Name(r.CondByUrl,"按Url条件计算",this.CondByUrl,"URL","http://","请输入url地址."),this.AddIcon("icon-graph",r.CondByUrl),this.TextBox3_NameNoNote(r.CondByWebApi,"按WebApi返回值计算",this.WebApi,"","请输入webapi接口地址 ","判断值","备注(不为空)",""),this.AddIcon("icon-graph",r.CondByWebApi)})}GenerBuessUnit(){return P(this,null,function*(){const F=yield new M("BP.WF.HttpHandler.WF_Admin_AttrNode").DoMethodReturnJson("ActionDtl_Init");return JSON.stringify(F)})}Save_TextBox_X(o,F,i,m,N){return P(this,null,function*(){const a=this.RequestVal("RefPKVal");let d="",C=0,h=0;if(this.RefMainEnName.includes("CCRole")==!0){const t=new w(a);yield t.Init(),yield t.Retrieve(),d=t.FlowNo,C=t.NodeID,h=t.NodeID}else if(this.RefMainEnName.includes("NodeExt")==!0){const t=new x(a);yield t.Init(),yield t.Retrieve(),d=t.FK_Flow,C=t.NodeID,h=t.NodeID}else{const t=new T(a);yield t.Init(),yield t.Retrieve();const s=new _(t.Node);yield s.RetrieveFromDBSources(),s.CondModel!=0&&(s.CondModel=0,yield s.Update());const f=new T(a);yield f.Init(),yield f.Retrieve(),d=f.FK_Flow,C=f.Node,h=f.ToNode}if(o=="CondByFrm")return;const e=new D;if(yield e.Init(),e.FK_Flow=d,e.FK_Node=C,e.ToNodeID=h,e.CondType=2,this.RefMainEnName.includes("CCRole")==!0&&(e.CondType=4),this.RefMainEnName.includes("NodeExt")==!0&&(e.CondType=1),e.DataFrom=o,e.DataFromText=this.GetPageName(o),e.Idx=100,e.RefPKVal=a,o==="Left"||o==="Right"||o==="AND"||o==="OR")return e.DataFrom=100,e.DataFromText="运算符",e.Note=o,o==="Left"&&(e.Note="("),o==="Right"&&(e.Note=")"),e.FK_Operator=e.Note,e.OperatorValue=e.Note,e.SetPara("EnName","TS.WF.Cond100"),yield e.Insert(),u.info("保存成功!!!"),new p(c.CloseAndReload);if(o==="CondByFrm.SelectField"){const t=new G(i);t.MyPK=i,yield t.Retrieve();let s="TS.WF.CondFrmString";if(e.Tag1=t.UIBindKey,t.LGType==1){s="TS.WF.CondFrmEnum";const E=new O;A.CCBPMRunModel==L.SAAS?E.No=I.OrgNo+"_"+t.UIBindKey:E.No=t.UIBindKey,(yield E.RetrieveFromDBSources())==1&&E.EnumType==1&&(s="TS.WF.CondFrmEnumString")}t.LGType>=2&&(s="TS.WF.CondFrmString"),t.LGType==0&&t.IsNum&&(s="TS.WF.CondFrmNum"),e.Note="",t.FK_MapData.includes("ND")&&t.FK_MapData.includes("Rpt")?e.DataFrom=r.CondByFrm:e.DataFrom=r.StandAloneFrm,e.FK_Attr=i,e.AttrKey=t.KeyOfEn,e.AttrName=m,e.FK_Operator="=",e.FK_OperatorT="等于",e.OperatorValue="0",e.OperatorValueT="未设置",e.Idx=100,e.SetPara("EnName",s),e.FrmID=this.RequestVal("tb1","CondByFrm"),e.FrmID.startsWith("ND")==!1&&(e.DataFrom=r.StandAloneFrm),e.FrmName=this.RequestVal("tb2","CondByFrm"),yield e.Insert();const f=S.UrlEn(s,e.MyPK);return new p(c.GoToUrl,f)}if(o===r.CondByWorkCheck){e.Note="当立场=["+i+"]时.",e.FK_Operator=o,e.OperatorValue=i,e.Idx=100,e.SetPara("EnName","TS.WF.CondWorkCheckSelected"),e.AttrKey="",yield e.Insert(),u.info("保存成功");const t=S.UrlEn(e.GetParaString("EnName"),e.MyPK);return new p(c.GoToUrl,t)}if(o===r.CondBySQL){e.Note=N,e.FK_Operator=o,e.OperatorValue=m,e.Idx=100,e.SetPara("EnName","TS.WF.CondSQL"),e.FK_DBSrc=i,yield e.Insert(),u.info("保存成功");const t=S.UrlEn(e.GetParaString("EnName"),e.MyPK);return new p(c.GoToUrl,t)}if(o===r.CondByProc){e.Note=N,e.FK_Operator=o,e.OperatorValue=i,e.Idx=100,e.SetPara("EnName","TS.WF.CondProc");const t=new K(i);t.No=i,yield t.RetrieveFromDBSources(),e.Note="过程名称:"+m+",数据源编号:"+t.FK_SFDBSrc+","+t.FK_SFDBSrcText,e.FK_SFDBSrc=t.FK_SFDBSrc,yield e.Insert(),u.info("保存成功");const s=S.UrlEn(e.GetParaString("EnName"),e.MyPK);return new p(c.GoToUrl,s)}if(o===r.CondByBuessUnit){e.Note=N,e.FK_Operator=o,e.OperatorValue=i,e.Idx=100,e.SetPara("EnName","TS.WF.CondProc");const t=new K(i);t.No=i,yield t.RetrieveFromDBSources(),e.Note="过程名称:"+m+",数据源编号:"+t.FK_SFDBSrc+","+t.FK_SFDBSrcText,e.FK_SFDBSrc=t.FK_SFDBSrc,yield e.Insert(),u.info("保存成功");const s=S.UrlEn(e.GetParaString("EnName"),e.MyPK);return new p(c.GoToUrl,s)}if(o===r.CondByPara){if(!i){u.error("参数条件不能为空");return}if(i.split(" ").length<3){u.error("请检查设置的条件是否正确,操作符前后必须有空格");return}e.Note=i,e.FK_Operator=o,e.OperatorValue=i,e.Idx=100,e.SetPara("EnName","TS.WF.CondParas"),yield e.Insert(),u.info("保存成功");const t=S.UrlEn(e.GetParaString("EnName"),e.MyPK);return new p(c.GoToUrl,t)}if(o===r.CondByUrl){e.Note=i,e.FK_Operator=o,e.OperatorValue=i,e.Idx=100,e.SetPara("EnName","TS.WF.CondWebApi"),yield e.Insert(),u.info("保存成功");const t=S.UrlEn(e.GetParaString("EnName"),e.MyPK);return new p(c.GoToUrl,t)}if(o===r.CondByWebApi){e.Note=N,e.OperatorValue=i,e.Idx=100,e.SetPara("EnName","TS.WF.CondWebApi"),yield e.Insert();const t=S.UrlEn(e.GetParaString("EnName"),e.MyPK);return new p(c.GoToUrl,t)}if(o===r.CondStation){e.OperatorValue=i,e.OperatorValueT=m,e.Note=m,e.Idx=100,e.SetPara("EnName","TS.WF.CondStation"),yield e.Insert();const t=S.UrlEn(e.GetParaString("EnName"),e.MyPK);return new p(c.GoToUrl,t)}if(o===r.CondDept||o===r.CondLeaderOfDept){e.OperatorValue=i,e.OperatorValueT=m,e.Note=m,e.Idx=100,e.SetPara("EnName","TS.WF.CondDept"),yield e.Insert(),u.info("保存成功");const t=S.UrlEn(e.GetParaString("EnName"),e.MyPK);return new p(c.GoToUrl,t)}if(o===r.CondEmp){e.OperatorValue=i,e.OperatorValueT=m,e.Note=m,e.Idx=100,e.SetPara("EnName","TS.WF.CondEmp"),yield e.Insert();const t=S.UrlEn(e.GetParaString("EnName"),e.MyPK);return new p(c.GoToUrl,t)}u.error("没有判断的类型:"+e.DataFromText)})}GenerSorts(){return Promise.resolve([])}}export{r as DataFrom,rt as GPN_Cond};
