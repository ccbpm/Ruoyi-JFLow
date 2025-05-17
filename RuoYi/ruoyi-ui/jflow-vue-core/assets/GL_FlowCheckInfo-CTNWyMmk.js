var s=(a,r,e)=>new Promise((t,o)=>{var m=i=>{try{n(e.next(i))}catch(l){o(l)}},d=i=>{try{n(e.throw(i))}catch(l){o(l)}},n=i=>i.done?t(i.value):Promise.resolve(i.value).then(m,d);n((e=e.apply(a,r)).next())});import{PageBaseGenerList as h,GenerListPageShowModel as w}from"./PageBaseGenerList-B6Q4ihPi.js";import{B as c,G as f,m as y}from"./entry/index-B5R3Coa4-1746862693206.js";import{GloComm as u}from"./GloComm-B1xAfTWw.js";import{a1 as I}from"./antd-C8r6Ue4p.js";import{l as p,d as C}from"./vue-B6GVRDGm.js";import"./FrmTrack-Ct-No0Nq.js";import"./DBAccess-CZ0wdWXU.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";import"./SFPara-B3cj-DF1.js";import"./SFColumn-1gJai08U.js";import"./DataBankBase--yEAzp5n.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./GloDBSrcHelper-CD3_17zK.js";class _ extends h{constructor(){super("GL_FlowCheckInfo"),this.PageTitle="流程检查"}Init(){return s(this,null,function*(){const r=this.RequestVal("FlowNo");this.GroupFields="NodeName",this.LabFields="InfoType",this.HisGLShowModel=w.Table,this.BtnOfToolbar="帮助,测试运行",this.Columns=[{Key:"NodeID",Name:"节点ID",IsShow:!1,width:"10%"},{Key:"NodeName",Name:"节点名称",IsShow:!0,width:"30%"},{Key:"ChekOption",Name:"检查内容",IsShow:!0,width:"35%"},{Key:"Msg",Name:"信息",IsShow:!0,width:"10%"},{Key:"InfoType",Name:"状态",IsShow:!0,width:"15%"}];const e=new c("BP.WF.Flow",r);yield e.Retrieve();const t=yield e.DoMethodReturnString("DoCheck");t.forEach(o=>{o.InfoType=="警告"&&(o.InfoType="@警告=yellow"),o.InfoType=="信息"&&(o.InfoType="@信息=green"),o.InfoType=="错误"&&(o.InfoType="@错误=red")}),this.Data=t})}LinkFieldClick(r){return s(this,null,function*(){})}BtnClick(r,e){return s(this,null,function*(){if(r=="帮助"){const t=`
        <div>
          <h2>说明：</h2>
          <p>1. 检测局限性：请注意，由于内外部环境的复杂性，我们的质检程序无法百分之百确保流程设计中不存在任何问题。</p>
          <p>2. 问题分级：质检程序将检测到的问题分为以下三个级别，以便您更好地理解和处理：</p>
          <ul>
            <li><strong style="color: green">信息</strong>：记录节点检测信息，不影响流程运转。</li>
            <li><strong style="color: #dada88">警告</strong>：较严重问题，根据实际情况进行调整，建议注意但不一定立即修改，不影响流程运转。</li>
            <li><strong style="color: red">错误</strong>：严重问题，必须<span style="color: red">立即修改</span>，否则可能影响流程运转。</li>
          </ul>
          <p>3. 错误处理：对于"错误"级别的问题，它们代表程序运行中的实际错误，必须立即进行修正以确保流程设计的准确性和有效性。</p>
          <p>4. 自动更正：CCBPM系统具备自动更正功能，能够识别并自动修复系统设置中的错误部分，减轻您的工作负担。</p>
          <p>5.数据表结构修复：此外，CCBPM还具备自动修复数据表结构的能力，确保数据的一致性和完整性，进一步提升流程设计的稳定性和可靠性。</p>
        </div>
      `,o=C({render(){return p("div",{innerHTML:t})}});I.success({title:"CCBPMP流程设计检测报告",content:p(o),width:window.innerWidth*.5});return}if(r=="测试运行"){const t=this.RequestVal("FlowNo"),o=u.UrlGenerList("GL_FlowTester","&FlowNo="+t);return new f(y.Replace,o)}})}}export{_ as GL_FlowCheckInfo};
