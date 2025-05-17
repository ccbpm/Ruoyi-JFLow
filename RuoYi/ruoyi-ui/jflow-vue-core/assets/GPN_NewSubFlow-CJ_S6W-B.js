var p=Object.defineProperty;var f=(l,s,o)=>s in l?p(l,s,{enumerable:!0,configurable:!0,writable:!0,value:o}):l[s]=o;var a=(l,s,o)=>f(l,typeof s!="symbol"?s+"":s,o);var S=(l,s,o)=>new Promise((u,r)=>{var c=e=>{try{F(o.next(e))}catch(i){r(i)}},m=e=>{try{F(o.throw(e))}catch(i){r(i)}},F=e=>e.done?u(e.value):Promise.resolve(e.value).then(c,m);F((o=o.apply(l,s)).next())});import{P as N,F as b}from"./entry/index-B5R3Coa4-1746862693206.js";import{SubFlow as P}from"./SubFlow-CksT2Xak.js";import{Flow as W}from"./Flow-BIaTOSmj.js";import{Node as h}from"./Node-BsvTqXX9.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./EntityNodeID-3BfNz0DC.js";class g extends N{constructor(){super("GPN_NewSubFlow");a(this,"Docs0",`
  #### 帮助
   - 手工启动的子流程，依附于子流程组件。
   - 绑使用子流程组件，可以启用一个或者多个子流程。
  #### 效果图
  ![输入图片说明](./resource/WF/Admin/AttrNode/SubFlow/Img/SubFlowHand1.png "屏幕截图.png")
   
`);a(this,"Docs1",`
  #### 帮助
   - 自动触发子流程，在指定的事件，指定的条件满足的时候，就自动启动子流。
   - 指定的事件： 发送时， 工作到达时。
   - 指定的条件：按照表单字段，按照参数（类似于配置方向条件）。
   - 其他的条件与属性概念与手工启动子流程相同。
  #### 帮助文档
   - <a  href="https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3982374&doc_id=31094"> 更多内容，详见说明 </a>
  #### 图例1
  ![输入图片说明](./resource/WF/Admin/AttrNode/SubFlow/Img/SubFlow2.png "屏幕截图.png")

  #### 图例2
  ![输入图片说明](./resource/WF/Admin/AttrNode/SubFlow/Img/SubFlow3.png "屏幕截图.png")
  
`);a(this,"Docs2",`
  #### 帮助
   - 延续子流程是一个特殊的下级子流程.
   - 主流程的特定节点，只能发起一个延续子流程，启动后该节点的活动就被冻结了，需要等待延续流程运行后，根据配置主流程自动运行或者自动结束。
   - 子流程的开始节点也可以退回到父流程上去。
  #### 帮助文档
  - <a  href="https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=3982375&doc_id=31094"> 更多内容，详见说明 </a>
  #### 运行图例
  ![输入图片说明](./resource/WF/Admin/AttrNode/SubFlow/Img/SubFlowYanXu1.png "屏幕截图.png")
  #### 流程图
  ![输入图片说明](./resource/WF/Admin/AttrNode/SubFlow/Img/SubFlowYanXu2.png "屏幕截图.png")


`);a(this,"Docs3",`
  #### 帮助
   - 在流程属性的前置导航中，有几个选项需要在开始节点选择子流程。
   - 请参考流程属性的前置导航功能.
  #### 应用场景
   - 我们做一个费用报销单，需要选择一个或多个采购申请单.
   - 当前流程就是父流程，要选择的单笔或者多笔数据就是子流程.
   - 这个现象是先出现子流程，等其完成后出现父流程.
 
 `);this.ForEntityClassID="TS.WF.SFlow.SubFlow",this.PageTitle="新建子流程"}Init(){this.AddGroup("A","请选择子流程模式");const o=this.RequestVal("FlowNo"),u=b.srcFlowSorts,r=b.SQLOfSubFlowSrcList(o);this.SelectItemsByGroupList("0","手工启动子流程",this.Docs0,!1,u,r),this.SelectItemsByGroupList("1","自动启动子流程",this.Docs1,!1,u,r),this.SelectItemsByGroupList("2","延续子流程",this.Docs2,!1,u,r),this.SelectItemsByGroupList("3","发起导航创建子流程(对开始节点有效)",this.Docs3,!1,u,r)}GenerSorts(){return S(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(o,u,r,c,m){return S(this,null,function*(){const F=r;if(F===""){alert("请输入流程编号");return}const e=this.RequestVal("RefPKVal"),i=new W(F);yield i.Retrieve();const n=new h(e);yield n.Retrieve();const t=new P;if(t.MyPK=e+"_"+F+"_"+o,(yield t.IsExits())===!0){alert("子流程已经存在");return}if(t.FK_Flow=n.FK_Flow,t.FK_Node=e,t.SubFlowNo=i.No,t.SubFlowName=i.Name,t.SubFlowType=o,o==="0"&&t.SetPara("EnName","TS.WF.SFlow.SubFlowHand"),o==="1"&&t.SetPara("EnName","TS.WF.SFlow.SubFlowAuto"),o==="2"&&t.SetPara("EnName","TS.WF.SFlow.SubFlowYanXu"),o==="3"&&t.SetPara("EnName","TS.WF.SFlow.SubFlowGuide"),yield t.Insert(),o==="1"){const d=n.GetParaInt("SubFlowAutoNum",0);n.SetPara("SubFlowAutoNum",d+1),yield n.Update()}let w="";return o==="0"&&(w="/src/WF/Comm/En.vue?EnName=TS.WF.SFlow.SubFlowHand&PKVal="+t.MyPK),o==="1"&&(w="/src/WF/Comm/En.vue?EnName=TS.WF.SFlow.SubFlowAuto&PKVal="+t.MyPK),o==="2"&&(w="/src/WF/Comm/En.vue?EnName=TS.WF.SFlow.SubFlowYanXu&PKVal="+t.MyPK),o==="3"&&(w="/src/WF/Comm/En.vue?EnName=TS.WF.SFlow.SubFlowGuide&PKVal="+t.MyPK),"url@"+w})}}export{g as GPN_NewSubFlow};
