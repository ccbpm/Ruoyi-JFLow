import { AppConfig } from './AppConfig';
import {getJFlowToken} from "@/utils/auth";
/**
 * 说明:
 * 1. 该类是一个接口文件需要引入到自己的前端vue项目中去.
 * 10. 更多的帮助: 请参考: https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=8095471&doc_id=31094
 */
  /*************************************  管理员接口  ******************************/
  /**
   * 流程设计
   * @returns url
   */
  export function Menu_Admin_Flows() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=TreeEns_FlowSort2Flow&token=${getJFlowToken()}`;
    return url;
  }
   /**
   *流程设计器:设计单个流程
   * @param flowNo 流程编号
   * @returns
   */
   export function Admin_Flow_One(flowNo) {
    const host = AppConfig.AppCenterHost
    // const url = `${host}/#WF/Port?DoWhat=FlowDesign&FlowNo=${flowNo}&token=${getJFlowToken()}`;
     const url = `${host}/#/WF/Port?DoWhat=Component&EnName=TS.WF.FD.FlowJiJian&PKVal=${flowNo}&Token=${getJFlowToken()}`;
    return url;
  }

  /**
   * 表单设计
   * @returns url
   */
  export function Menu_Admin_Frms() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=TreeEns_FrmSort2Frm&token=${getJFlowToken()}`;
    return url;
  }
  /**
   *表单设计器:设计单个表单
   * @param frmID 表单ID.
   *@returns
   */
  export function Admin_Frm_One(frmID) {
    const host = AppConfig.AppCenterHost
    // http://localhost:3009/#/WF/Port?DoWhat=FrmDesign&FrmID=En_F002&Token=477c784459444e349a1239248fd6ddd0
    const url = `${host}/#WF/Port?DoWhat=FrmDesign&FrmID=${frmID}&token=${getJFlowToken()}`;

    return url;
  }
  /**
   * 白色大屏设计
   * @param rptID 表单ID. @llj
   * @returns
   */
  export function Menu_Admin_RptWhite(rptID) {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=RptWhite&PageID=${rptID}&edit=1&token=${getJFlowToken()}`;
    return url;
  }
  /**
   * 蓝色大屏设计
   * @param rptID 报表ID
   * @returns 设计的url.
   */
  export function Menu_Admin_RptBlue(rptID) {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=RptBlue&PageID=${rptID}&token=${getJFlowToken()}`;
    return url;
  }
  /**
   * 组织结构设计
   * @returns url
   */
  export function Menu_Admin_Orgs() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=TreeEns_Dept2Emp&token=${getJFlowToken()}`;
    return url;
  }
  /**
   * 数据源
   * @returns url
   */
  export function Menu_Admin_DBSrc() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=TreeEns_DBSrc&token=${getJFlowToken()}`;
    return url;
  }
  /*********************** 菜单接口************************************* */
  //发起
  export function Menu_Flow_Start() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=GL_Start&token=${getJFlowToken()}`;
    return url;
  }
  //待办
  export function Menu_Flow_Todolist() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=GL_Todolist&token=${getJFlowToken()}`;
    return url;
  }
  //在途
  export function Menu_Flow_Runing() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=GL_Runing&token=${getJFlowToken()}`;
    return url;
  }
  //近期
  export function Menu_Flow_Nearly() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=GL_RecentWork&token=${getJFlowToken()}`;
    return url;
  }
  //已完成
  export function Menu_Flow_Complate() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=GL_Complete&token=${getJFlowToken()}`;
    return url;
  }
  //抄送
  export function Menu_Flow_CC() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=GL_CC&token=${getJFlowToken()}`;
    return url;
  }
  //草稿
  export function Menu_Flow_Draft() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=GL_Draft&token=${getJFlowToken()}`;
    return url;
  }
  //消息
  export function Menu_Flow_Msg() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=GL_Msg&token=${getJFlowToken()}`;
    return url;
  }
  //综合查询
  export function Menu_Flow_Search() {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=EntitySearch&EnName=TS.FlowData.GenerWorkFlowView&token=${getJFlowToken()}`;
    return url;
  }
  //我的设置菜单
  export function MySettingMenu(){
    const host = AppConfig.AppCenterHost
    const url =  `${host}/#/WF/Port?DoWhat=En&EnName=TS.Port.MySetting&PKVal=${WebUser.No}&token=${getJFlowToken()}`
    return url;
  }
  //白色大屏
  export function RptWhiteView(No){
    const host = AppConfig.AppCenterHost
    const url =  `${host}/#/WF/Port?DoWhat=RptWhite&Edit=0&PageID=${No}&token=${getJFlowToken()}`
    return url;
  }
  /*************************************  流程页面接口  ******************************/

  /**
   * 工作处理器
   * @param workID 工作ID,如果WorkID是0, 则会自动创建一个新的WorkID.
   * @param paras 可选参数：可以向表单传入的参数,格式：&Tel=18660153393&Addr=山东济南.
   * @returns 直接打开MyFlow工作处理器的url.
   */
    export function Flow_MyFlowByFlowNo(flowNo, paras) {
    const host = AppConfig.AppCenterHost
    let url = `${host}/#/WF/Port?DoWhat=StartFlow&FlowNo=${flowNo}&token=${getJFlowToken()}`;
    if(paras){
       url = `${host}/#/WF/Port?DoWhat=StartFlow&FlowNo=${flowNo}${paras}&token=${getJFlowToken()}`;
    }

    return url;
  }
    export function Flow_MyFlow(workID, paras) {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=StartFlow&WorkID=${workID}${paras}&token=${getJFlowToken()}`;
    return url;
  }
  /**
   * 工作查看器
   * @param workID 工作ID,如果WorkID是0, 则会自动创建一个新的WorkID.
   * @returns 直接打开MyView工作查看器的url.
   */
    export function Flow_MyView(workID) {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=MyView&WorkID=${workID}&token=${getJFlowToken()}`;
    return url;
  }
  /**
   * 抄送查看器
   * @param workID 工作ID,如果WorkID是0, 则会自动创建一个新的WorkID.
   * @returns 直接打开MyCC工作查看器的url.
   */
    export function Flow_MyCC(flowNo, workID) {
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=MyCC&FlowNo=${flowNo}&WorkID=${workID}&token=${getJFlowToken()}`;
    return url;
  }
    export function Flow_Track(flowNo, workID) {
    // http://localhost:3009/#/WF/Port?DoWhat=Vue3Track&FK_Flow=114&WorkID=1888671138&Token=eff9ed6b38934e08a5ab1b595e26f909
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=Vue3Track&FK_Flow=${flowNo}&WorkID=${workID}&token=${getJFlowToken()}`;
    return url;
  }

    export function Flow_Search(flowNo) {
    // http://localhost:3009/#/WF/Port?DoWhat=Vue3Track&FK_Flow=114&WorkID=1888671138&Token=eff9ed6b38934e08a5ab1b595e26f909
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=SearchFlow&FlowNo=${flowNo}&token=${getJFlowToken()}`;
    return url;
  }
  //高代码 实体查询
  export function FixedUrl_Search(EnName) {
    debugger
    const host = AppConfig.AppCenterHost
    const url = `${host}/#/WF/Port?DoWhat=EntitySearch&EnName=${EnName}&token=${getJFlowToken()}`;
    return url;
  }
  //高代码 白色大屏
export function DataVUrl_Search(EnName) {
      debugger
  const host = AppConfig.AppCenterHost
  const url = `${host}/#/WF/Port?DoWhat=HomePage&EnName=${EnName}&token=${getJFlowToken()}`;
  return url;
}
//流程相关菜单
export function LcUrl_Search(EnName) {
  debugger
  const host = AppConfig.AppCenterHost
  const url = `${host}/#/WF/Port?DoWhat=Component&EnName=${EnName}&token=${getJFlowToken()}`;
    return url;
}

  /*************************************  实体单据接口  ******************************/
  /**
   * 单据列表
   * @param frmID 单据ID
   * @param paras  查询参数, 比如: &Key=123
   * @returns 单据查询的url.
   */
  export function Bill_Search(frmID, paras) {
    const url = `${AppConfig.AppCenterHost}/#/WF/Port?DoWhat=Vue3Track&FK_Flow=${frmID}${paras}&token=${getJFlowToken()}`;
    return url;
  }

  export function EntityBill_Search(frmID) {
    const url = `${AppConfig.AppCenterHost}/#/WF/Port?DoWhat=EntityBill&FrmID=${frmID}&token=${getJFlowToken()}`;
    return url;
  }
  export function EntityNoName_Search(frmID) {
    const url = `${AppConfig.AppCenterHost}/#/WF/Port?DoWhat=EntityNoName&FrmID=${frmID}&token=${getJFlowToken()}`;
    return url;
  }
  /**
   * 单据分析
   * @param frmID 单据ID
   * @param paras  查询参数, 比如: &Key=123
   * @returns 单据查询的url.
   */
  export function Bill_Group(frmID, paras) {
    const url = `${AppConfig.AppCenterHost}/#/WF/Port?DoWhat=Vue3Track&FK_Flow=${frmID}${paras}&token=${getJFlowToken()}`;
    return url;
  }
  /**
  * 新建单据实例
  * @param frmID 单据ID
  * @param paras 查询参数, 比如: &Addr=山东济南&Tel=18660153393
  * @returns 新建单据的url.
  */
  export function Bill_NewBill(frmID, paras) {
    const url = `${AppConfig.AppCenterHost}/#/WF/Port?DoWhat=Vue3Track&FK_Flow=${frmID}${paras}&token=${getJFlowToken()}`;
    return url;
  }
  /**
  * 单据卡片信息
  * @param workID 流程实例ID
  * @param frmID 单据ID
  * @returns 卡片信息url.
  */
  export function Bill_MyBill(workID, frmID, param) {
    const url = `${AppConfig.AppCenterHost}/#/WF/Port?DoWhat=Vue3Track&FK_Flow=${frmID}${param}&token=${getJFlowToken()}`;
    return url;
  }

