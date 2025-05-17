var M=Object.defineProperty;var _=(C,w,o)=>w in C?M(C,w,{enumerable:!0,configurable:!0,writable:!0,value:o}):C[w]=o;var e=(C,w,o)=>_(C,typeof w!="symbol"?w+"":w,o);var f=(C,w,o)=>new Promise((F,s)=>{var p=a=>{try{t(o.next(a))}catch(B){s(B)}},A=a=>{try{t(o.throw(a))}catch(B){s(B)}},t=a=>a.done?F(a.value):Promise.resolve(a.value).then(p,A);t((o=o.apply(C,w)).next())});import{P as U,F as u,H as T,G as h,m as N,bj as R,C as I,W as P}from"./entry/index-B5R3Coa4-1746862693206.js";import{GroupMethods as W,GroupMethodAttr as V}from"./GroupMethod-D4dNiFLl.js";import{Method as y}from"./Method-Duk019iw.js";import{D as x}from"./DBAccess-CZ0wdWXU.js";import{GloComm as D}from"./GloComm-B1xAfTWw.js";import{MapData as E}from"./MapData-D5zymw8O.js";import{FlowSort as L}from"./FlowSort-DO177AvQ.js";import{MySystem as g}from"./MySystem-BG96NbYk.js";import{Menus as b}from"./Menu-B21f89xo.js";import{FrmSort as k}from"./FrmSort-BhvgVTIc.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./PCenter-DbUtxw5j.js";import"./PowerCenter-Dl8ZnZaz.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FrmTrack-Ct-No0Nq.js";import"./EnumLab-CsLi93T0.js";import"./FlowAdm-B9fl-Qy8.js";import"./PG_Module2Menu-BszPUh1p.js";import"./PageBasePanelGroup-C-loKAxc.js";import"./Module-BKib3HHY.js";import"./FrameworkExt-CCcdiwaa.js";import"./BaseEntityExt-3AR52S3C.js";import"./ModuleLang-CkU9wfxA.js";import"./MapFrmFool-BjBGkcYr.js";import"./GPE_PageLoadFullMainTable-T1tZGNNb.js";import"./MapExt-DVovzpWn.js";import"./PageLoadFull-rXMpPjS-.js";import"./PageBaseGroupEdit-BXdNWKIo.js";import"./Help-D0bDMZWg.js";import"./FrmPrintTemplate-DuKpQVFr.js";import"./FrmPrintDB-DmkZTfPT.js";import"./MapDataVer-BkV3qQwi.js";import"./AttrString-NXi21Ynk.js";import"./AttrHide-DRi6GSvK.js";import"./AttrEnum-B4doLO9s.js";import"./AttrNum-vVmD7j_0.js";import"./AttrSFSQL-BWYwZ5Uv.js";import"./AttrDT-Mb76V41e.js";import"./GPE_FrmReferencePanel-CcfD2s1m.js";import"./GPN_FrmExpImp-CGZJ9J9z.js";import"./Entity-Chp-BVny.js";import"./Request-Cs1ZNhZ7.js";import"./form-D-kP1HSJ.js";import"./GroupField-lMJeJtcW.js";import"./EntityOID-C1xznxai.js";import"./download-Cb1ocZ2f.js";import"./base64Conver-t-3tszFb.js";import"./BSEntities-D1vdB9S4.js";import"./Node-BsvTqXX9.js";import"./EntityNodeID-3BfNz0DC.js";import"./GroupFieldLang-BIVTczhJ.js";import"./MapAttrLang-CyQViPZP.js";import"./MapDtlLang-B8eUxYwG.js";import"./MapDtl-B_Ep8ewM.js";import"./FrmAttachmentLang-BwFOc-rh.js";import"./FrmAttachment-MhkNqka4.js";import"./SysEvent-DymzJjDC.js";import"./GPE_FrmBodySecret-CSPNfIvP.js";import"./GPE_FrmWorkModel-BliMxdHf.js";import"./GPE_FrmType-D4Ez4Fbe.js";import"./SysEnumLang-nO8oYjDE.js";import"./FrmOrg-BK3uQclO.js";import"./MenuLang-BGIT3RSn.js";import"./MySystemLang-C3K84JCP.js";class i{}e(i,"Link","Link"),e(i,"Func","Func"),e(i,"Bill","Bill"),e(i,"FrmBBS","FrmBBS"),e(i,"DataVer","DataVer"),e(i,"DictLog","DictLog"),e(i,"QRCode","QRCode"),e(i,"DBList","DBList"),e(i,"Toolbar","Toolbar"),e(i,"ImpFromFile","ImpFromFile"),e(i,"PrintRTF","PrintRTF"),e(i,"PrintHtml","PrintHtml"),e(i,"PrintPDF","PrintPDF"),e(i,"PrintZip","PrintZip"),e(i,"FlowBaseData","FlowBaseData"),e(i,"RefFlowTrack","RefFlowTrack"),e(i,"FlowEtc","FlowEtc"),e(i,"FlowSingle","FlowSingle"),e(i,"FlowNewEntity","FlowNewEntity"),e(i,"SingleDictGenerWorkFlows","SingleDictGenerWorkFlows");class no extends U{constructor(){super("GPN_Method");e(this,"SingleDictGenerWorkFlows",`
  #### 帮助
  - 一个实体发起的所有流程
  - 比如：在一个学生身上发起的，请假流程、入党申请流程、基本资料变更流程.
  - 比如：在一个固定资产身发起的：领用流程、维修流程、折旧流程、移交流程.
  - 所有的流程都组合一个表显示出来
  `);e(this,"Desc100","暂未开放");e(this,"DocSelfUrl",`
 
 自定义URL菜单， 您可以使用右上角的下拉框选择自己要定义的菜单类型.
  #### 帮助
   - 菜单连接： http://ccbpm.cn/MyUrl.htm  
   - 菜单连接： http://ccbpm.cn/MyUrl.htm  
   - 链接： /WF/Comm/Search.htm?EnsName=TS.ZS.Projcets 查询
   - 链接： /WF/Comm/Group.htm?EnsName=TS.ZS.Projcets  分析
   - 链接： /WF/MyFlow.htm?FK_Flow=001 发起指定的流程. 
  #### 帮助
   -  可以使用相对路径，也可以使用绝对路径。
   -  用户输入的Url:  http://ccbpm.cn/MyUrl.htm
   -  打开的Url : http://ccbpm.cn/MyUrl.htm?UserNo=xxxx&Token=xxxx。
   -  SID就类似于token, UserNo就是当前登录用户的编号。
   -  <img src="SelfUrl.png" class="HelpImg" />
</fieldset>

  `);e(this,"Docs0",`
  
  #### 帮助
   - 用于解决不能实现的对实体的操作个性化较强的功能。
   - 比如：您输入的url为外部链接: http://ccbpm.cn/MyUrl.htm
   - 比如：您需要打开系统内部的一个vue文件: /src/WF/Comm/En.vue 这个文件必须位于项目内部
   - 系统将解析为: http://ccbpm.cn/MyUrl.htm?WorkID=xxxx&FrmID=xxxx&UserNo=xxxx&Token=xxxx
   - 该链接显示在查询工具栏上。
  #### 效果图
  ![输入图片说明](./resource/CCFast/CCBill/Method/Img/Link.png "屏幕截图.png")      
        `);e(this,"Docs1",`
  #### 帮助
  - 对一个实体记录，执行相关的操作。
  - 执行一段SQL, Javascript, Url, 类。
  #### 无参数的方法效果
  ![输入图片说明](./resource/CCFast/CCBill/Method/Img/Func.png "屏幕截图.png")    
  #### 有参数的方法效果
  ![输入图片说明](./resource/CCFast/CCBill/Method/Img/Func2.png "屏幕截图.png")    
        `);e(this,"Docs2",`
  
  #### 帮助
   - 单据就是依赖与实体存在流水性质的记账凭证。
   - 比如：出门证、介绍信、证明函。
   - 比如：出库单、入库单。
   - 创建单据后，不需要审批，或者简单的审批就可以设置为入库状态的数据。
    
  `);e(this,"Docs3",`
   
  #### 帮助
  - 一个实体里只有一个该组件菜单
  - 应用场景：填写客户跟踪信息、实体跟踪记录、实体留言记录、多个人对一个实体的操作记录。
  #### 效果图
  ![输入图片说明](./resource/CCFast/CCBill/Method/Img/FrmBBS.png "屏幕截图.png")     
  `);e(this,"Docs4",`
   
  #### 帮助
  - 类似与数据库的备份
  - 可以在一定的事件对当前的实体进行数据备份，可以恢复到数据到指定的数据备份。
  #### 效果图
  ![输入图片说明](./resource/CCFast/CCBill/Method/Img/DataVer.png "屏幕截图.png") 
    
  `);e(this,"Docs5",`
   
  #### 帮助
  - 操作日志，留存操作痕迹。
  #### 效果图
  ![输入图片说明](./resource/CCFast/CCBill/Method/Img/DictLog.png "屏幕截图.png") 
    
  `);e(this,"Docs6",`
   
  #### 帮助
  - 该二维码是一个用于数据扫描查看的二维码。
  -  用户扫一扫就可以在手机上查看该表单的信息。
  -  如果您需要填报二维码，请在菜单新建【表单填报二维码】。
  #### 效果图
  ![输入图片说明](./resource/CCFast/CCBill/Method/Img/QRCode.png "屏幕截图.png") 
    
  `);e(this,"Docs7",`
  #### 帮助
  - ccfrom提供三种打印模式,以模版类型划分，分别是:rtf, vstoExcel,vstoWord.
  - rtf 不需要安装插件,需要通过替换标记生成vsto文件.
  - vstoWord,vstoExcel需要安装插件才能完成.

  #### 打印模版设置.
  - 三种模式的打印，必须首先设置打印模版.
  - 设置步骤: 表单设计器=》表单属性=》打印模版.

  #### 运行图1
  ![输入图片说明](./resource/CCFast/CCBill/Method/Img/ToolbarRuning.png "屏幕截图.png") 
  #### 效果图2
  ![输入图片说明](./resource/CCFast/CCBill/Method/Img/ToolbarSetting.png "屏幕截图.png")    
  `);e(this,"Docs8",`
  #### 帮助
  -
  `);e(this,"Docs9",`
  #### 帮助
  `);e(this,"Docs10",`
  #### 帮助
  `);e(this,"Docs11",`
   
  #### 帮助
  - 比如:基础资料变更、法人变更、企业变更、状态变更。
  - 就是对当前按选择一行（一个实体的基础数据变更)。
  - 流程结束后，系统就会把这些字段同步到实体中去。
  - <a href=https://www.bilibili.com/video/BV12P4y1p74h/>流程与实体的关系</a>
  #### 开发说明
  - 点击确定后，系统自动创建一个流程，并且开流程为极简模式。
  - 此流程的表单，是从当前实体表单中复制而来的。
  - 您可以根据自己的需要新增与删除字段。
  - 可以在方法属性里，设置数据同步方式与同步内容。
  #### 效果图
  ![输入图片说明](./resource/CCFast/CCBill/Method/Img/FlowBaseData.png "屏幕截图.png")    
  `);e(this,"DocsFlowHostBill",`
  #### 应用场景.
  - 一个单据(合同),需要绑定一个流程上做审批,在流程运行过程中对表单数据的修改,即可反映到单据上,既修改的数据就是单据的记录.
  - 单据为流程运动提供审批数据， 流程在运动过程中，影响单据的BillState的状态.
  - 单据是宿主, 流程寄生虫. 一个宿主可以有多个寄生虫.
  - 单据在一个时间点，只能把数据提供给一个寄生虫使用.
    - ccbpm把上述模式称为【宿主流程】.

  - 流程发起后，单据的BillState不能修改，属于审批状态，流程走完以后，设置归档状态。
  - 流程没有完成，就不能发起，只能发起一次。
  - 该流程运动过程中退回，要更新单据的 BillState 状态, 设置为退回.
  #### 设计实现.
  - 该流程是绑定独立表单, 该表单就是的单据.
  - 在表单与节点的关系中, 设置 WhoIsPK 是父流程ID或单据OID.
  - 启动流程的时候，系统就会设置 WF_GenerWorkFlow 的 PWorkID= 单据的OID, PFlowNo=单据的FrmID, PNodeID=0, PEmpNo=@WebUser.No
  - 该流程不能被独立发起.
  - 流程运动过程中影响单据的状态Frm_GenerBill 的 BillState.
  - 流程与宿主流程的规则，记录在
  `);e(this,"DocsFlowSingleCopyData",`
    #### 应用场景.
    - 一个单据(合同),需要绑定一个流程上做审批,在流程运行过程中对表单数据的修改,即可反映到单据上,既修改的数据就是单据的记录.
    - ccbpm把上述模式称为应用单据数据审批模式.
    #### 设计实现.
    - 该流程是绑定独立表单，该表单就是的单据.
    - 在表单与节点的关系中，设置 WhoIsPK 是父流程ID或单据OID.
    - 启动流程的时候，系统就会设置 PWorkID= 单据的OID, PFlowNo=单据的FrmID, PNodeID=0, PEmpNo=@WebUser.No
    `);e(this,"Docs12",`
   
  #### 帮助
  - 比如：物业费缴纳、维修流程、派车流程、处罚流程、嘉奖流程、三好学生评定。
  - 流程运行完毕后，就作为业务查询数据。
  - 启动流程的时候，单据数据copy到开始节点上.

  #### 开发说明
  - 点击确定后，系统自动创建一个流程，并且开流程为极简模式。
  - 此流程的表单，是从当前实体表单中复制而来的。
  - 您可以根据自己的需要新增与删除字段。
  - <a href=https://www.bilibili.com/video/BV12P4y1p74h/>流程与实体的关系</a>
    
  `);e(this,"DocsFlowSingle",`
   
  #### 帮助
  - 在一个单据中，仅仅发起一次的流程，我们成为单次流程。
  - 比如：合同，发起审核流程，注销流程。
  - 创建单次流程系统为当前表单设置为如下字段,其中XXX是流程编号.
  - WFStateXXXX, 状态.
  - FlowStarterXXXX, 发起人名称.
  - FlowStartRDT, 发起日期.
  - FlowEndNodeIDXXX, 结束节点.
  - FlowEndNodeNameXXX, 节点名称.
  - WorkIDXXX, 工作ID.
  - 流程在运行过程中会更新这些字段.
  #### 1.数据引用模式
  - 创建的流程类型为绑定单表单流程，绑定的表单就是当前的表单.
  - 把当前的低代码开发的表单绑定到每个节点上.
  - 表单与节点的关系属性里设置, WhoIsPK 为 父流程节点ID或者单据OID.
   #### 2.数据复制模式
  - 创建的是极简模式的流程，开始节点表单的内容(字段)是从当前实体或者单据复制过去的.
  - 启动流程的时候，把当前的表单数据复制到开始节点表单上去,流程运转的数据与当前实体或者单据无关系.
  #### 开发说明
  - <a href=https://www.bilibili.com/video/BV12P4y1p74h/>流程与实体的关系</a>
  `);this.PageTitle="新建实体方法",this.ForEntityClassID="TS.CCBill.Method"}Init(){this.AddGroup("A","常规组件","icon-doc"),this.TextBox2_NameNo(i.Link,"自定义链接",this.Docs0,"","URL链接","链接名称","我的链接"),this.TextBox2_NameNo(i.Func,"方法",this.Docs1,"Func_","方法ID","方法名称","缴纳班费"),this.TextBox1_Name(i.FrmBBS,"BBS/评论/日志组件",this.Docs3,"名称","评论"),this.TextBox1_Name(i.DataVer,"数据快照",this.Docs4,"名称","数据快照"),this.TextBox1_Name(i.DictLog,"操作日志",this.Docs5,"名称","操作日志"),this.TextBox1_Name(i.QRCode,"二维码",this.Docs6,"名称","二维码"),this.TextBox1_Name(i.DBList,"数据列表",this.Docs6,"名称","数据列表"),this.AddGroup("D","关联单据","icon-doc"),this.TextBox2_NameNo("NewBill","新建单据",this.HelpTodo,"Bill_","编号","名称","维修单");const o=this.RequestVal("FrmID"),F=u.SQLOfGpnMethodGroupSQL(o),s=u.SQLOfGpnMethodAttrSQL(o);this.SelectItemsByGroupList("NewBill.SelectAttrs","选择字段",this.HelpUn,!0,F,s),this.SelectItemsByList("NewBill.SelectAttrs.Group","选择目录",this.HelpUn,!1,u.srcFrmTree),this.SelectItemsByGroupList("RefBill","关联单据",this.Desc100,!1,u.srcFrmTree,u.srcFrmListBill),this.SelectItemsByList("RefBill.DictID","选择关联字段ID",this.HelpUn,!1,this.RefBillAttrs),this.SelectItemsByList("RefBill.DictID.DictName","选择关联字段Name",this.HelpUn,!1,this.RefBillAttrs),this.AddGroup("C","流程类","icon-plane"),this.TextBox1_Name(i.FlowBaseData,"基础数据变更流程",this.Docs11,"流程名称","基础数据变更流程"),this.TextBox1_Name(i.FlowEtc,"多次业务流程",this.Docs12,"流程名称","多次业务流程"),this.TextBox1_Name("FlowHostBill","寄宿流程",this.DocsFlowHostBill,"流程名称","寄宿流程"),this.AddBlank("RefFlow","关联流程","请使用创建后导入模式实现."),this.TextBox1_Name(i.SingleDictGenerWorkFlows,"实体流程汇总列表(综合流程列表)",this.SingleDictGenerWorkFlows,"流程名称","流程列表"),this.AddIcon("icon-link","Link"),this.AddIcon("icon-film","Func"),this.AddIcon("icon-bubbles","FrmBBS"),this.AddIcon("icon-docs","DataVer"),this.AddIcon("icon-film","DictLog"),this.AddIcon("icon-frame","QRCode"),this.AddIcon("icon-list","DBList"),this.AddIcon("icon-print","PrintRTF"),this.AddIcon("icon-print","PrintVSTOExcel"),this.AddIcon("icon-print","PrintVSTOWord"),this.AddIcon("icon-doc","NewBill"),this.AddIcon("icon-doc","RefBill"),this.AddIcon("icon-plane","FlowBaseData"),this.AddIcon("icon-plane","FlowEtc"),this.AddIcon("icon-plane","FlowSingle"),this.AddIcon("icon-plane","SingleDictGenerWorkFlows")}FlowFrmModel(){return f(this,null,function*(){return JSON.stringify([{No:"RefBill",Name:"实体表单引用模式"},{No:"DataCopy",Name:"实体数据复制模式"}])})}RefBillAttrs(){return f(this,null,function*(){const o=this.RequestVal("tb1","RefBill"),F=new T("BP.CCBill.WF_CCBill_Admin_Method");return F.AddPara("FrmID",o),yield F.DoMethodReturnString("GPN_Menthd_RefBill_BillAttrs")})}GenerSorts(){return f(this,null,function*(){const o=new W;return yield o.Retrieve(V.FrmID,this.PKVal,"Idx"),o})}Save_TextBox_X(o,F,s,p,A){return f(this,null,function*(){const t=new y;if(t.GroupID=F,t.GroupIDT=this.GetSortName(F),t.FrmID=this.PKVal,t.Icon=this.GetPageIcon(o),t.IsEnable=!0,t.Idx=100,t.Name=s,t.MethodModel=o,o===i.Link){t.Name=s,t.Docs=p,t.No=x.GenerGUID(),t.SetPara("EnName","TS.CCBill.MethodLink"),yield t.Insert();const r=D.UrlEn("TS.CCBill.MethodLink",t.No);return new h(N.GoToUrl,r)}if(o===i.Func){t.Name=s,t.Docs=p,t.No=x.GenerGUID(),t.SetPara("EnName","TS.CCBill.MethodFunc"),t.MethodID=p,yield t.Insert();const r=D.UrlEn("TS.CCBill.MethodFunc",t.No);return new h(N.GoToUrl,r)}if(o==="NewBill"){const r=new E;if(r.No=p,(yield r.RetrieveFromDBSources())!=0)return new h(N.Error,"表单ID["+s+"]已经存在.")}if(o=="RefBill.DictID.DictName"){const r=this.RequestVal("FrmID"),c=this.RequestVal("tb2","RefBill"),l=this.RequestVal("tb1","RefBill"),m=this.RequestVal("tb1","RefBill.DictID"),S=this.RequestVal("tb1","RefBill.DictID.DictName");t.Name=l,t.MethodModel="DictRefBill",t.No=r+"_"+c,t.Tag1=c,t.Tag2=l,t.SetPara("EnName","TS.CCBill.MethodDictRefBill"),t.SetPara("RefDictNo",m),t.SetPara("RefDictName",S),t.MethodID="DictRefBill",yield t.Insert();const n=D.UrlEn("TS.CCBill.MethodDictRefBill",t.No);return new h(N.GoToUrl,n)}if(o=="NewBill.SelectAttrs.Group"){const r=this.RequestVal("FrmID"),c=this.RequestVal("tb2","NewBill"),l=this.RequestVal("tb1","NewBill"),m=this.RequestVal("tb3","NewBill"),S=this.RequestVal("tb1","NewBill.SelectAttrs.Group"),n=new T("BP.WF.HttpHandler.WF_Admin_CCFormDesigner");n.AddUrlData(),n.AddPara("FK_FrmSort",S),n.AddPara("TB_No",c),n.AddPara("TB_Name",l),n.AddPara("TB_PTable",m),n.AddPara("DDL_PTableModel",0),n.AddPara("EntityType",1),n.AddPara("SelectAttrs",this.RequestVal("tb1","NewBill.SelectAttrs")),n.AddPara("DictFrmID",r),yield n.DoMethodReturnString("NewFrmGuide_Create"),t.Name=l,t.MethodModel="DictRefBill",t.No=r+"_"+c,t.Tag1=c,t.Tag2=l,t.SetPara("EnName","TS.CCBill.MethodDictRefBill"),t.SetPara("RefDictNo",r+"No"),t.SetPara("RefDictName",r+"Name"),t.MethodID="DictRefBill",yield t.Insert();const d=D.UrlEn("TS.CCBill.MethodDictRefBill",t.No);return new h(N.GoToUrl,d)}if(o===i.FrmBBS||o===i.DBList||o===i.DictLog||o===i.QRCode||o==i.DataVer){if(t.No=this.PKVal+"_"+o,yield t.IsExits()){if(o!=i.DBList){alert("该组件已经存在,不可重复添加.");return}t.No=x.GenerGUID(),t.MethodID=o}t.Name=s,o===i.FrmBBS&&(t.Icon="icon-film"),o===i.DictLog&&(t.Icon="icon-eye"),o===i.QRCode&&(t.Icon="icon-frame"),o===i.DataVer&&(t.Icon="icon-camera"),o===i.DBList&&(t.Icon="icon-drop"),t.SetPara("EnName","TS.CCBill.Method"+o),yield t.Insert();const r=D.UrlEn(t.GetParaString("EnName",""),t.No);return new h(N.GoToUrl,r)}if(o===i.PrintHtml||o===i.PrintPDF||o==="PrintVSTOWord"||o==="PrintVSTOExcel"||o===i.PrintRTF||o==i.PrintZip){if(t.No=this.PKVal+"_"+o,(o===i.PrintRTF||o=="PrintVSTOWord"||o=="PrintVSTOExcel")&&(yield t.IsExits())==!0)t.No=x.GenerGUID();else if((yield t.IsExits())==!0){alert("该组件已经存在,不可重复添加.");return}t.Name=s,o===i.PrintHtml&&(t.Icon="icon-printer"),o===i.PrintPDF&&(t.Icon="icon-printer"),o===i.PrintRTF&&(t.Icon="icon-printer"),o==="PrintVSTOWord"&&(t.Icon="icon-printer"),o==="PrintVSTOExcel"&&(t.Icon="icon-printer"),o===i.PrintZip&&(t.Icon="icon-cloud-download"),t.Tag1=o,o=="PrintRTF"||o==="PrintVSTOWord"||o==="PrintVSTOExcel"?t.SetPara("EnName","TS.CCBill.MethodPrintRTF"):t.SetPara("EnName","TS.CCBill.MethodPrint"),yield t.Insert();const r="/src/WF/Comm/En.vue?EnName=TS.CCBill.MethodFlowBaseData&PKVal="+t.No;return new h(N.GoToUrl,r)}let a=this.RefPKVal;a||(a=this.RequestVal("FrmID"));let B="";if(o===i.FlowBaseData||o===i.FlowEtc||o==="FlowHostBill"||o==="FlowSingleCopyData"){const r=new b;yield r.Retrieve("FrmID",a);const c=r.filter(d=>d.MenuModel==="Bill"||d.MenuModel==="EntityNoName"||d.MenuModel==="Dict");let l="",m="CCFast",S="";if(c.length>=1){R.CCBPMRunModel!=I.Single&&(m="CCFast_"+P.OrgNo),B=c[0].SystemNo;const d=new g;d.No=B,yield d.Retrieve(),l=d.Name,S="低代码流程"}else{const d=new E;d.No=a,yield d.Retrieve(),B=d.FK_FormTree;const G=new k;G.No=B,yield G.Retrieve(),l=G.Name,m=R.CCBPMRunModel!=I.Single&&P.OrgNo||"1"}const n=new L;n.No=B,(yield n.IsExits())==!1&&(n.No=m,(yield n.IsExits())==!1&&m.startsWith("CCFast")&&(n.Name=S,R.CCBPMRunModel==I.Single?n.ParentNo="1":n.ParentNo=P.OrgNo,n.OrgNo=P.OrgNo,yield n.Insert()),n.No=B,n.Name=l,R.CCBPMRunModel==I.Single?n.ParentNo=m:n.ParentNo=P.OrgNo,n.OrgNo=P.OrgNo,yield n.Insert())}if(o===i.FlowBaseData){const r=s,c=F,l=new T("BP.CCBill.WF_CCBill_Admin_Method");l.AddPara("SortNo",B),l.AddPara("FlowName",r),l.AddPara("Name",r),l.AddPara("FrmID",a),l.AddPara("FlowDevModel",1),l.AddPara("GroupID",c),l.AddPara("ModuleNo","");const m=yield l.DoMethodReturnString("FlowBaseData_Save"),S=D.UrlEn("TS.CCBill.MethodFlowBaseData",m);return new h(N.GoToUrl,S)}if(o==i.RefFlowTrack){if(t.No=this.PKVal+"_"+o,yield t.IsExits()){alert("该组件已经存在,不可重复添加.");return}t.Name=s,t.Icon="icon-doc",t.SetPara("EnName","TS.CCBill.Method"+o),yield t.Insert();const r=D.UrlEn(t.GetParaString("EnName",""),t.No);return new h(N.GoToUrl,r)}if(o===i.FlowEtc||o==="FlowHostBill"||o==="FlowSingleCopyData"){const r=s,c=F,l=new T("BP.CCBill.WF_CCBill_Admin_Method");l.AddPara("SortNo",B),l.AddPara("FlowName",r),l.AddPara("Name",r),l.AddPara("FrmID",a),l.AddPara("FlowDevModel",o==="FlowHostBill"?3:1),l.AddPara("GroupID",c),l.AddPara("ModuleNo",""),l.AddPara("FlowModel",o);const m=yield l.DoMethodReturnString("FlowEtc_Save"),S=D.UrlEn("TS.CCBill.Method"+o,m);return new h(N.GoToUrl,S)}if(o===i.SingleDictGenerWorkFlows){if(t.No=this.PKVal+"_"+o,(yield t.IsExits())==!0){alert("该组件已经存在,不可重复添加.");return}t.Name=s,t.MethodID=i.SingleDictGenerWorkFlows,t.MethodModel=i.SingleDictGenerWorkFlows,t.RefMethodType=1,t.Icon="icon-drop",t.SetPara("EnName","TS.CCBill.MethodSingleDictGenerWorkFlow"),t.Insert();const r="/src/WF/Comm/En.vue?EnName=TS.CCBill.MethodSingleDictGenerWorkFlow&PKVal="+t.No;return new h(N.GoToUrl,r)}})}}export{no as GPN_Method,i as MethodModel};
