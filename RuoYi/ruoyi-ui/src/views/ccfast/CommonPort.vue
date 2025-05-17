<template>
  <iframe v-if="url" :src="url" scrolling="auto" frameborder="no" style="width: 100%; height: calc(100vh - 100px)" ></iframe>
</template>
<script>
import {
  Flow_MyFlowByFlowNo,
  Menu_Flow_Start,
  Menu_Flow_Todolist,
  Menu_Flow_Runing,
  Menu_Flow_Nearly,
  Menu_Flow_Complate,
  Menu_Flow_CC,
  Menu_Flow_Draft,
  Menu_Flow_Msg,
  Menu_Flow_Search,
  Menu_Admin_Flows,
  Menu_Admin_Frms,
  EntityBill_Search,
  Flow_Search,
  Menu_Admin_Orgs,
  Menu_Admin_DBSrc, EntityNoName_Search, MySettingMenu, RptWhiteView, FixedUrl_Search, DataVUrl_Search,LcUrl_Search
} from '@/Toolkit/Dev2UrlInterface';
import {getRequestParams} from "@/utils/index";
export default {
  name: "jflow",
  data() {
    return {
      url: ``,
      host : process.env.VITE_GLOB_VUE3_URL
    };
  },
  created() {
    this.InitPage();
  },
  methods: {
     InitPage  () {
       debugger
     this.url= '';
      //方案一:通过路由直接拼接路径跳转Port页面.
      // const queryString= QueryMerge(route.query);
      // const host = 'http://localhost:3009'
      // this.url = `${host}/#/WF/Port?${queryString}&token=${Token}`;
      //方案二:直接使用配置好的路径，使Port路径使用的更加明显.
      let action = getRequestParams("action");
      //action转小写
      action = action ? action.toLowerCase() : action;
      debugger
      switch (action) {
        case 'start':  //发起
          this.url =  Menu_Flow_Start()
          break;
        case 'todo':   //待办
          this.url =  Menu_Flow_Todolist()
          break;
        case 'runing':  //在途
          this.url =  Menu_Flow_Runing()
          break;
        case 'recent':  //近期
          this.url = Menu_Flow_Nearly()
          break;
        case 'complete': //已完成
          this.url =  Menu_Flow_Complate()
          break;
        case 'cc':  //抄送
          this.url =  Menu_Flow_CC()
          break;
        case 'draft':  //草稿
          this.url =  Menu_Flow_Draft()
          break;
        case 'info':  //消息
          this.url =  Menu_Flow_Msg()
          break;
        case 'searchall':  //综合查询
          this.url =  Menu_Flow_Search()
          break;
        case 'flowlist':  //流程
          this.url =  Menu_Admin_Flows()
          break;
        case 'formlist':  //表单
          this.url =  Menu_Admin_Frms()
          break;
        case 'oragn':  //组织
          this.url =  Menu_Admin_Orgs()
          break;
        case 'dblist':  //数据源
          this.url =  Menu_Admin_DBSrc()
          break;
        case 'flow':  //发起具体流程
          const id =  getRequestParams("id");
          this.url = Flow_MyFlowByFlowNo(id)
          break;
        case 'entitybill':  //单据
          const frmid = getRequestParams("frmid");
          this.url = EntityBill_Search(frmid);
          break;
        case 'entitynoname':  //实体
          const frmid2 = getRequestParams("frmid");
          this.url = EntityNoName_Search(frmid2);
          break;
        case 'refflow':  //关联流程
          const flowno = getRequestParams("flowno");
          this.url = Flow_Search(flowno);
          break;
        case 'setting':  //我的设置
          this.url = MySettingMenu()
          break;
        case 'rptwhite':  //白色大屏
          const No = getRequestParams("No");
          this.url = RptWhiteView(No)
          break;
        case 'fixedurl':  //高代码 实体查询
          const EnName = getRequestParams("EnName");
          this.url = FixedUrl_Search(EnName)
          break;
        case 'datav':  //高代码  白色大屏
          const DataVEnName = getRequestParams("EnName");
          this.url = DataVUrl_Search(DataVEnName)
          break;
        case 'linkflowfunc':  //流程菜单
          const lkEnName = getRequestParams("EnName");
          this.url = LcUrl_Search(lkEnName)
          break;
        default:
          alert('没有找到对应的页面');
          break;
      }
      console.log(this.url);
    }
  }
};
</script>
