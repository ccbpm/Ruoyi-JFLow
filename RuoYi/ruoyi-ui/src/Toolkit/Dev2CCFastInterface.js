import request from '@/utils/request';
import { AppConfig } from './AppConfig';
import { getJFlowToken } from "@/utils/auth";
import {
  Admin_Frm_One,
  Admin_Flow_One,
  Menu_Admin_RptWhite,
  Menu_Admin_RptBlue,
  Flow_Search
} from "@/Toolkit/Dev2UrlInterface";

/**
 * 说明:
 * 1. 该接口是一个为其他系统引入使用ccfast的功能菜单提供的菜单接口
 * 10. 更多的帮助: 请参考:https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=13933053&doc_id=31094
 */
  /**
   * 新建菜单
   * @param sortNo 模块编号
   * @param sortName 模块名称
   * @returns 菜单工具箱.
   */
  export function Open_GPN_Menus(sortNo,sortName) {

    const appNo= AppConfig.AppNo;
    const appName= AppConfig.AppName;
    const query = {
      appNo: appNo,
      appName: appName,
      sortNo: sortNo,
      sortName: sortName,
    };
    console.log('Open_GPN_Menus:', query);
    //处理模块类别编号，是否存在，不存在就创建一个.
    request({
      url: '/WF/API/CCFast_Mouldle_Init',
      method: 'get',
      params: query
    })

    //返回菜单连接.
    const host = AppConfig.AppCenterHost;
    const url = `${host}/#/WF/Port?DoWhat=Component&EnName=GPN_Menu&SortNo=${sortNo}&SortName=${sortName}&SystemNo=${appNo}&token=${getJFlowToken()}`;
    return url;
  }
  /**
   * 获得菜单信息
   * @param mypk 菜单主键.
   * @returns 菜单实体信息json.
   */
  export function GenerMenuInfo(mypk) {
    const query = {
      menuMyPK: mypk,
    };
    console.log('GenerMenuInfo:', query);
    const response = request({
      url: '/WF/API/CCFast_MenuInfo',
      method: 'get',
      params: query
    })
    console.log('GenerMenuInfo:', response);
    return response;
  }

  /**
   * 获得模块的最后一个加入菜单
   * @param sortNo 模块编号.
   * @returns 返回: @MenuNo=xxx@MenuName=yyyy 或者为空.
   */
    export function GenerLastMenuInfo(sortNo) {
      const query = {
        menuMyPK: sortNo,
      };
      console.log('GenerLastMenuInfo:', query);
      const response = request({
        url: '/WF/API/CCFast_LastOneMenu',
        method: 'get',
        params: query
      })
      return response;
    }
  /**
   * 删除菜单
   * @param myPK 主键
   * @returns 删除菜单.
   */
  export function Menu_Delete(myPK) {
    const query = {
      menuMyPK: myPK,
    };
    const response = request({
      url: '/WF/API/CCFast_MenuDelete',
      method: 'get',
      params: query
    })
    return response;
  }

  /**
   * 设计菜单
   * @param row
   * @returns string
   */
  export function Menu_Designer_Url(row) {
    const menuType = row.MenuModel ; //'RefFlow'; //菜单类型.
    //如果是流程.
    if (menuType=='RefFlow')
    {
      const flowNo=row.FlowNo; //获得流程编号.
      return Admin_Flow_One(flowNo);
    }

     //如果是表单.
     if (menuType=='Bill' ||  menuType=='EntityNoName' )
      {
        const frmID = row.FrmID; //获得流程编号.
        // /#/WF/Port?Token=&DoWhat=FrmDesign&FrmID=
        return Admin_Frm_One(frmID);
      }
      const No= row.No;
      // 大屏.
      if (menuType=='RptWhite') return Menu_Admin_RptWhite(No);
      if (menuType=='RptBlue') return Menu_Admin_RptBlue(No);

      alert('不可以设计');
      return "";
  }

/**
 * 设计菜单
 * @param myPK
 * @returns

      alert('没有解析的类型:'+menuType);
  }

  /**
   * 运行菜单
   * @param row 行数据（菜单GPM_Menu）
   * @param menuModel 菜单模式
   * @returns
   */
  export function Menu_Runing_Url(row) {
    let url = Menu_GenerPortURL(row);
    if (url.endsWith('=')){
      return '';
    }
    return url + '&token=' + getJFlowToken();
  }

  export function Menu_GenerPortURL(row) {
    const host = AppConfig.AppCenterHost
    let url = `${host}/#/WF/Port?DoWhat=`;
    const frmID = row.FrmID;
    switch (row.MenuModel) {
      case 'Bill':
        url += 'EntityBill&FrmID=' + frmID;
        break;
      case 'EntityNoName':
        url += 'EntityNoName&FrmID=' + frmID;
        break;
      case 'RptWhite':
        url += 'RptWhite&Edit=0&PageID=' + row.No;
        break;
      case 'RefFlow':
        url += 'SearchFlow&FlowNo=' + row.FlowNo;
        break;
      case 'FixedUrl':
        const UrlExt=row.UrlExt;
        const EnName = row.Alias.replace(row.ModuleNo+"_","");
        if(UrlExt.indexOf("WF/Comm/DataV")>=0){
          url += 'HomePage&EnName=' +  EnName;
        }else{
          url += 'EntitySearch&EnName=' +  EnName;
        }

        break;
      case 'LinkFlowFunc':
        const EnName2 = row.Alias.replace(row.ModuleNo+"_","");
        url += 'Component&EnName=' +  EnName2+'&token='+getJFlowToken();
        break;
      default:
        console.log('没有解析的类型:' + row.MenuModel);
        break;
    }
    return url;
  }
 /*************************************  ruoyi工具类接口  ******************************/

  /**
   * 根据菜单模式生成若以的路由参数
   * @param {菜单实体数据} menuRow
   * @returns
   */
  export function generQuery(menuRow) {
    const menuModel = menuRow.MenuModel;
        // Bill: '单据',
        // RefFlow: '关联流程',
        // SelfUrl: '自定义菜单',
        // DictTable: '字典表',
        // Func: '独立功能',
        // Windows: '统计分析',
        // StandAloneFlow: '独立流程',
        // Tabs: '标签容器',
        // EntityNoName: '实体'
    const frmID = menuRow.FrmID;
    switch(menuModel) {
      case 'Bill':
        return `{"action":"EntityBill","frmid":"${frmID}"}`;
      case 'EntityNoName':
        return `{"action":"EntityNoName","frmid":"${frmID}"}`;
      case 'RefFlow':
        const flowNo = menuRow.FlowNo;
        return `{"action":"RefFlow","flowno":"${flowNo}"}`;
      case 'SelfUrl':
        const url = menuRow.Url;
        return `{"action":"SelfUrl","url":"${url}"}`;
      case 'DictTable':
        const dictTable = menuRow.DictTable;
        return `{"action":"DictTable","dictTable":"${dictTable}"}`;
      case 'Func':
        const func = menuRow.Func;
        return `{"action":"Func","func":"${func}"}`;
      case 'Windows':
        const windows = menuRow.Windows;
        return `{"action":"Windows","windows":"${windows}"}`;
      case 'StandAloneFlow':
        const standAloneFlow = menuRow.StandAloneFlow;
        return `{"action":"StandAloneFlow","standAloneFlow":"${standAloneFlow}"}`;
      case 'Tabs':
        const tabs = menuRow.Tabs;
        return `{"action":"Tabs","tabs":"${tabs}"}`;
      case 'RptWhite':
        const No = menuRow.No;
        return `{"action":"RptWhite","No":"${No}"}`;
      case 'FixedUrl':
        const UrlExt=menuRow.UrlExt;
        const EnName = menuRow.Alias.replace(menuRow.ModuleNo+"_","");
        debugger
        if(UrlExt.indexOf("WF/Comm/DataV")>=0){
          return `{"action":"DataV","EnName":"${EnName}"}`;
        }
        return `{"action":"FixedUrl","EnName":"${EnName}"}`;
      case 'LinkFlowFunc':
        const EnName1 = menuRow.Alias.replace(menuRow.ModuleNo+"_","");
        return `{"action":"LinkFlowFunc","EnName":"${EnName1}"}`;
      default:
        return '';
    }
  }

