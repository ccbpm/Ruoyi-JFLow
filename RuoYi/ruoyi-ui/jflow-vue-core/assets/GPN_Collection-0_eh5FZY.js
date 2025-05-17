var A=Object.defineProperty;var _=(c,s,o)=>s in c?A(c,s,{enumerable:!0,configurable:!0,writable:!0,value:o}):c[s]=o;var e=(c,s,o)=>_(c,typeof s!="symbol"?s+"":s,o);var x=(c,s,o)=>new Promise((N,a)=>{var p=l=>{try{m(o.next(l))}catch(t){a(t)}},S=l=>{try{m(o.throw(l))}catch(t){a(t)}},m=l=>l.done?N(l.value):Promise.resolve(l.value).then(p,S);m((o=o.apply(c,s)).next())});import{P as G,F as I,G as h,m as w,bj as E,C as D,W as f,H as U}from"./entry/index-B5R3Coa4-1746862693206.js";import{Menus as k}from"./Menu-B21f89xo.js";import{Collection as F}from"./Collection-C-J3HY9U.js";import{D as L}from"./DBAccess-CZ0wdWXU.js";import{F as g}from"./FrmBill-slTzXsFG.js";import{Methods as K}from"./Method-Duk019iw.js";import{GloComm as C}from"./GloComm-B1xAfTWw.js";import{FlowSort as V}from"./FlowSort-DO177AvQ.js";import{MySystem as O}from"./MySystem-BG96NbYk.js";import{b as v}from"./antd-C8r6Ue4p.js";import"./vue-B6GVRDGm.js";import"./PCenter-DbUtxw5j.js";import"./PowerCenter-Dl8ZnZaz.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";import"./FrmAdm-w-27tFK4.js";import"./MapData-D5zymw8O.js";import"./EnumLab-CsLi93T0.js";import"./SysEvent-DymzJjDC.js";import"./PG_Group2Method-D-hH9YF8.js";import"./PageBasePanelGroup-C-loKAxc.js";import"./GroupMethod-D4dNiFLl.js";import"./FrmTrack-Ct-No0Nq.js";import"./SearchFKEnum-iAO6-ceA.js";import"./GPE_ActiveDDL-CBrWe-fm.js";import"./PageBaseGroupEdit-BXdNWKIo.js";import"./Help-D0bDMZWg.js";import"./MapExt-DVovzpWn.js";import"./GPEActiveDDLSFTable-DOc4bXna.js";import"./GPEActiveDDLSelfSetting-BmBkm8oa.js";import"./GPE_AutoFullDLL-CGsKVwxV.js";import"./GPEAutoFullDLL-BqUF6zia.js";import"./GPEAutoFullDDLSFTable-CSezP8HV.js";import"./DBRole-DQUduH7f.js";import"./GPE_FrmType-D4Ez4Fbe.js";import"./Flow-BIaTOSmj.js";import"./SelfCheck-C0INbd8A.js";import"./ByEmpNo-CP2nCXHT.js";import"./MapExtSearchCol-D1qWhKw6.js";import"./FlowAdm-B9fl-Qy8.js";import"./PG_Module2Menu-BszPUh1p.js";import"./Module-BKib3HHY.js";import"./FrameworkExt-CCcdiwaa.js";import"./BaseEntityExt-3AR52S3C.js";import"./ModuleLang-CkU9wfxA.js";import"./MapFrmFool-BjBGkcYr.js";import"./GPE_PageLoadFullMainTable-T1tZGNNb.js";import"./PageLoadFull-rXMpPjS-.js";import"./FrmPrintTemplate-DuKpQVFr.js";import"./FrmPrintDB-DmkZTfPT.js";import"./MapDataVer-BkV3qQwi.js";import"./AttrString-NXi21Ynk.js";import"./AttrHide-DRi6GSvK.js";import"./AttrEnum-B4doLO9s.js";import"./AttrNum-vVmD7j_0.js";import"./AttrSFSQL-BWYwZ5Uv.js";import"./AttrDT-Mb76V41e.js";import"./GPE_FrmReferencePanel-CcfD2s1m.js";import"./GPN_FrmExpImp-CGZJ9J9z.js";import"./Entity-Chp-BVny.js";import"./Request-Cs1ZNhZ7.js";import"./form-D-kP1HSJ.js";import"./GroupField-lMJeJtcW.js";import"./EntityOID-C1xznxai.js";import"./download-Cb1ocZ2f.js";import"./base64Conver-t-3tszFb.js";import"./BSEntities-D1vdB9S4.js";import"./Node-BsvTqXX9.js";import"./EntityNodeID-3BfNz0DC.js";import"./GroupFieldLang-BIVTczhJ.js";import"./MapAttrLang-CyQViPZP.js";import"./MapDtlLang-B8eUxYwG.js";import"./MapDtl-B_Ep8ewM.js";import"./FrmAttachmentLang-BwFOc-rh.js";import"./FrmAttachment-MhkNqka4.js";import"./GPE_FrmBodySecret-CSPNfIvP.js";import"./GPE_FrmWorkModel-BliMxdHf.js";import"./SysEnumLang-nO8oYjDE.js";import"./FrmOrg-BK3uQclO.js";import"./MenuLang-BGIT3RSn.js";import"./MySystemLang-C3K84JCP.js";class r{}e(r,"SearchCond","SearchCond"),e(r,"Link","Link"),e(r,"QRCodeAddDict","QRCodeAddDict"),e(r,"FlowNewEntity","FlowNewEntity"),e(r,"Func","Func"),e(r,"Bill","Bill"),e(r,"LinkCollection","LinkCollection"),e(r,"FlowEntityBatchStart","FlowEntityBatchStart");class Bo extends G{constructor(){super("GPN_Collection");e(this,"DocsLink","暂未开放");e(this,"FlowNewEntity",`
  #### 帮助
  - 比如：xxx登记、供应商申请、xx申请、入党申请、材料入库申请
  - 流程运行完毕后，就写入该条数据到实体列表中.
  #### 开发说明
  - 点击确定后，系统自动创建一个流程，并且开流程为绑定表单库的表单模式模式。
  - 该流程绑定的表单就是该实体表单
  - 用户发起流程实例就是直接在该表单上增加一笔记录，流程结束后，或者指定的节点结束后，该记录变为提交状态。
  `);e(this,"Docs0",`
  
  #### 帮助
   - 该模式的表单定义是自由的,每个节点上都可以定义不同的表单方案. 
   - 每个节点上都可以灵活定义个性化的表单,而不需要统一管理. 
        
        `);e(this,"Desc100","暂未开放");e(this,"DocSelfUrl",`
  #### 帮助
   - 自定义URL菜单， 您可以使用右上角的下拉框选择自己要定义的菜单类型. 
   
   - 菜单连接： http://ccbpm.cn/MyUrl.htm  
   - 菜单连接： http://ccbpm.cn/MyUrl.htm  
   - 链接： /WF/Comm/Search.htm?EnsName=TS.ZS.Projcets 查询
   - 链接： /WF/Comm/Group.htm?EnsName=TS.ZS.Projcets  分析
   - 链接： /WF/MyFlow.htm?FK_Flow=001 发起指定的流程. 
   
   -  可以使用相对路径，也可以使用绝对路径。
   -  用户输入的Url:  http://ccbpm.cn/MyUrl.htm
   -  打开的Url : http://ccbpm.cn/MyUrl.htm?UserNo=xxxx&Token=xxxx。
   -  SID就类似于token, UserNo就是当前登录用户的编号。
   -  <img src="SelfUrl.png" class="HelpImg" />
  `);e(this,"DescQRCodeAddDict",`
      扫码在手机上新建.
        `);e(this,"Docs1",`
  
   #### 帮助
    - 该表单是固定格式的表单,可以展现4列6列展现. 
    - 使用批量设置审核组件的状态,来满足不同的审批需要,审核组件有启用禁用只读三个状态. 
    - 用于简单的表单审批场景,第1个节点填写表单,第2个节点之后表单都是只读的,使用审核组件填写审核意见. 
    - 优点:开发效率高,展现简洁,学习成本低,业务人员可以入手. 
    - 缺点:展示样式固定.
  `);e(this,"Docs2",`
  
   #### 帮助
    - 该流程所有的节点都禁用了审核组件,审核信息写入到了审核分组里的字段里.
    - 流程在运动过程中,每个节点的人员都在当前节点上填写一些信息,走到最后一个节点才是完整的表单,所以整个表单就像累加起来的一样.
    - 我们把符合整个特征的流程,称为累加表单流程.
    
  `);e(this,"Docs3",`
   #### 帮助
   
    - 第三方软件向特定的表 WF_Task 中写入数据，每写入一条数据系统就会自动发起一条流程。
    - ccBPM就会读取这张表来完成流程的发起,发起成功后就把这条记录设置成已经发起的状态。
    - 详见设置以及该表的结构参考操作手册.
    
  `);this.PageTitle="新建列表组件",this.ForEntityClassID="TS.CCBill.Collection"}Init(){return x(this,null,function*(){const o=this.RefPKVal,N=new g(o);yield N.Retrieve(),this.AddGroup("A","无需集合支持"),this.TextBox2_NameNo(r.Link,"自定义链接",this.DocsLink,"","链接名称","URL链接","我的链接"),this.TextBox1_Name(r.QRCodeAddDict,"扫码填报",this.DescQRCodeAddDict,"标签","扫码填报");const a=`新建${N.Name}流程`;this.TextBox1_Name(r.FlowNewEntity,"注册/新增实体类流程",this.FlowNewEntity,"流程名称",a),this.AddGroup("B","需要集合支持");const p=I.SQLOfGpnCollection(this.RefPKVal);if(yield new K().Retrieve("MethodModel","Func","FrmID",this.RefPKVal),this.SelectItemsByList(r.Func,"实体方法",this.Desc100,!1,p),this.TextBox1_Name(r.Bill,"单据:批量发起(未解析)",this.Docs0,"单据名称","出入证明"),this.TextBox2_NameNo(r.LinkCollection,"自定义链接",this.Docs0,"","链接标签","URL链接","我的链接"),N.EntityType!=1){const m=`批量发起:${N.Name}流程`;this.TextBox1_Name(r.FlowEntityBatchStart,"批量发起流程",this.Docs0,"流程名称",m)}})}GenerSorts(o){return Promise.resolve([])}Save_TextBox_X(o,N,a,p,S){return x(this,null,function*(){if(o===r.Link){const t=new F;t.FrmID=this.RefPKVal,t.MethodID="Link",t.Mark="Link",t.Name=a,t.MethodModel="Link",t.UrlExt=p,t.Tag1=p,t.Icon="icon-drop",t.SetPara("EnName","TS.CCBill.CollectionLink"),t.No=a+"_"+this.RefPKVal,t.Idx=100,yield t.Insert();const d=C.UrlEn(t.GetParaString("EnName",""),t.No);return new h(w.GoToUrl,d)}if(o===r.QRCodeAddDict){const t=new F;t.FrmID=this.RefPKVal,t.MethodID=o,t.Mark=o,t.Name=a,t.MethodModel=o,t.SetPara("EnName","TS.CCBill.CollectionQRCodeAddDict"),t.Icon="icon-drop",t.Idx=100,t.No=a+"_"+this.RefPKVal,yield t.Insert();const d=C.UrlEn(t.GetParaString("EnName",""),t.No);return new h(w.GoToUrl,d)}let m=this.RefPKVal;m||(m=this.RequestVal("FrmID"));let l="";if(o===r.FlowNewEntity||o===r.FlowEntityBatchStart){const t=new k;if(yield t.Retrieve("FrmID",m),t.filter(i=>i.MenuModel==="Bill"||i.MenuModel==="EntityNoName"||i.MenuModel==="Dict").length==0){v.error("没有查询到关于FrmID="+m+"菜单.");return}const u=t[0];let R="",M="CCFast",T="";E.CCBPMRunModel!=D.Single&&(M="CCFast_"+f.OrgNo),l=u.SystemNo;const B=new O;B.No=l,yield B.Retrieve(),R=B.Name,T="低代码流程";const n=new V;if(n.No=l,(yield n.IsExits())==!1&&(n.No=M,(yield n.IsExits())==!1&&(n.Name=T,E.CCBPMRunModel==D.Single?n.ParentNo="1":n.ParentNo=f.OrgNo,n.OrgNo=f.OrgNo,yield n.Insert()),n.No=l,n.Name=R,E.CCBPMRunModel==D.Single?n.ParentNo=M:n.ParentNo=f.OrgNo,n.OrgNo=f.OrgNo,yield n.Insert()),o===r.FlowNewEntity){const i=new U("BP.CCBill.WF_CCBill_Admin_Collection");i.AddPara("FlowName",a),i.AddPara("Name",a),i.AddPara("FrmID",m),i.AddPara("FlowDevModel",1),i.AddPara("ModuleNo",u.ModuleNo),i.AddPara("SortNo",n.No);const P=yield i.DoMethodReturnString("FlowNewEntity_Save"),y=C.UrlEn("TS.CCBill.CollectionFlowNewEntity",P);return new h(w.GoToUrl,y)}if(o===r.FlowEntityBatchStart){const i=new U("BP.CCBill.WF_CCBill_Admin_Collection");i.AddPara("SortNo",n.No),i.AddPara("FlowName",a),i.AddPara("Name",a),i.AddPara("FrmID",m),i.AddPara("FlowDevModel",1),i.AddPara("ModuleNo",u.ModuleNo),i.AddPara("IsCanBatch",1);const P=yield i.DoMethodReturnString("FlowEntityBatchStart_Save");if(P.indexOf("err@")==0){alert(P);return}const y=C.UrlEn("TS.CCBill.CollectionFlowNewEntity",P);return new h(w.GoToUrl,y)}}if(o===r.Func){const t=new F;t.FrmID=this.RefPKVal,t.MethodID=a,t.Mark=o,t.Name=p,t.MethodModel=o,t.SetPara("EnName","TS.CCBill.CollectionFunc"),t.Icon="icon-energy",t.Idx=100,t.No=a+"_"+this.RefPKVal,yield t.Insert();const d=C.UrlEn(t.GetParaString("EnName",""),t.No);return new h(w.GoToUrl,d)}if(o===r.LinkCollection){const t=new F;t.FrmID=this.RefPKVal,t.MethodID=a,t.Mark=o,t.Name=p,t.MethodModel=o,t.SetPara("EnName","TS.CCBill.CollectionLink"),t.Icon="icon-energy",t.Idx=100,t.No=L.GenerGUID(),yield t.Insert();const d=C.UrlEn(t.GetParaString("EnName",""),t.No);return new h(w.GoToUrl,d)}alert("没有判断的PageID:"+o)})}}export{r as CollectionModel,Bo as GPN_Collection};
