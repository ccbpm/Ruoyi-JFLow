import request from '@/utils/request'
import {getJFlowToken, getJFlowUser} from '@/utils/auth'


/**
 * 说明:
 * 1. 该类是一个接口文件需要引入到自己的前端vue项目中去.
 * 2. 它提供了与bpm服务器交互的接口, bpm服务器的接口分为两部分, 功能页面接口，API接口.
 * 3. 功能页面接口是指: 比如: 待办,在途,抄送,草稿,我的工作等.通过一个功能页面就可以查看相关的工作.
 * 4. API接口是指: 比如: 发起流程,查询流程,抄送流程,撤销流程,撤回流程,删除流程等. 使用API接口需要在功能页面中点击按钮来调用.
 * 5. 以 Open_开头的方法是打开一个功能页面,比如: Open_MyFlow(workID,paras),它会打开一个功能页面,并传入workID和paras参数.
 * 6. 以 Number_开头的方法是获取一个数字,比如Todolist(flowNo,paras),它会返回一个数字,比如: 10,表示有10条待办.
 * 7. 以 Port_开头的方法是与bpm服务器交互的接口,比如: Port_Login(userNo,password),它会返回一个数字,比如: 0,表示登录成功.
 * 8. 以 Node_或则Flow_开头的方法是对流程操作的方法，比如：发起、设置草稿、保存流程环境变量.
 * 9. 以 DB_ 开头的方法是获得一个Json集合的，菜单接口，比如: DB_Start(),它会返回一个Json集合,比如: [{No:'001',Name:'请假申请'},{No:'002',Name:'出差申请'}]
 * 10. 更多的帮助: 请参考: https://gitee.com/opencc/JFlow/wikis/pages/preview?sort_id=8095471&doc_id=31094
 */
  // (用于数量气泡显示)
  export function Flow_TodoNums(domain) {
    return  request({
      url: '/WF/API/Flow_TodoNums',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        domain: domain || ''
      }
    })

  }

  //待办数量(用于数量气泡显示)
  export function Number_Todolist() {
    let num = 0
     Flow_TodoNums().then(res => {
       num = res.Todolist_EmpWorks;
     })
    return num
  }

  // 消息数量(用于数量气泡显示)
  export function Number_Message() {
    let num = 0
     Flow_TodoNums().then(res => {
      num = res.Todolist_Msg;
    });
    return num
  }
//抄送数量(用于数量气泡显示)
export function Number_cclist() {
  let num = 0
  Flow_TodoNums().then(res => {
    num = res.CCList_UnRead + res.CCList_Read;
  });
  return num
}

  // ======================================================== 门户接口. =======================================================

  /**
   * 用户退出
   * @param userNo
   * @returns
   */
  export function Port_Logout(userNo) {
    return
    request({
      url: '/WF/API/Port_LoginOut',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        userNo
      }
    })

  }
  /**
   * 切换登录部门
   * @param deptNo 要切换的部门编号
   * @returns  是否切换成功?如果部门不匹配就抛出错误.
   */
  export function Port_ChangeDept(deptNo) {
    return 0;
  }
  /**
   * 切换登录部门岗位
   * @param deptNo 部门编号
   * @param stationNo 岗位编号
   * @returns 是否切换成功?如果岗位与部门不匹配就抛出错误.
   */
  export function Port_ChangeDeptAndStation(deptNo, stationNo) {
    return 0;
  }
  /**
   * 设置主题样式，为当前的用户系统保持一致.
   * @param style 主题样式
   * @returns 无
   */
  export function Port_SetStyle(style) {
    return 0;
  }
  /**
   * 用户信息获取
   */
  export function Port_GetWebUser() {
    const WebUser = getJFlowUser();
    return WebUser;
  }
  // ======================================================== 流程操作. =======================================================

  /**
   * 创建一个WorkID,并写入流程参数或者表单数据
   * @param flowNo 流程编号
   * @param paras 创建流程写入的环境变量或者流程系统参数.
   * @returns 创建的流程实例,我们称为WorkID, 这个时间WorkID是空白状态,未被使用,如果他发送下去或者设置草稿,在创建workid就是一个新的WorkID.
   */
  export function Node_CreateBlank(flowNo, paras) {
    return
    request({
      url: '/WF/API/Node_CreateBlankWorkID',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        flowNo: flowNo,
        Paras: paras,
      }
    })
  }

  export function Node_SaveWork(workID, jsonData) {
    return
    request({
      url: '/WF/API/Node_SaveWork',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workID,
        token: getJFlowToken(),
        ...jsonData,
      }
    })

  }

  /**
    * 发送
    * @returns
    */
  export function Node_SendWork(flowNo, workID, toNodeID = 0, toEmps, checkNote) {
    return
    request({
      url: '/WF/API/Node_SendWork',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        FlowNo: flowNo,
        WorkID: workID,
        toNodeIDStr: toNodeID,
        ToEmps: toEmps,
        checkNote: checkNote,
      }
    })
  }

  /**
    * 退回
    * @returns
    */
  export function Node_ReturnWork(workID, toNodeID, msg) {
    return
    request({
      url: '/WF/API/Node_ReturnWork',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        WorkID: workID,
        ToNodeID: toNodeID,
        Msg: msg,
      }
    })

  }

  /**
    * 移交
    * @returnsx
    */
  export function Node_ShiftWork(workID, toEmps, msg) {
    return
    request({
      url: '/WF/API/Node_Shift',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'post',
      data: {
        WorkID: workID,
        toEmpNo: toEmps,
        Msg: msg,
      }
    })

  }

  /**
   * 获得流程信息
   * @param workid 流程实例
   * @returns
   */
  export function Flow_GenerWorkFlow(workid) {
    return
    request({
      url: '/WF/API/Flow_GenerWorkFlow',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workID: workid,
      }
    })

  }
  /**
    * 批量删除
    * @returns
    */
  export function Flow_DeleteFlow(workIDs) {
    return
    request({
      url: '/WF/API/Flow_BatchDeleteByFlag',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workIDs: workIDs,
      }
    })

  }

  /**
   * 设置流程参数
   * @param workID
   * @param paras 格式: @JinE=100@key1=val1
   * @returns 执行结果.
   */
  export function Flow_SaveParas(workID, paras) {
    return
    request({
      url: '/WF/API/Flow_SaveParas',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workID,
        paras,
      }
    })

  }
  /**
   * 创建草稿
   * @param workID
   */
  export function Node_SetDraft(workID) {
    return
    request({
      url: '/WF/API/Node_SetDraft',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'post',
      data: {
        WorkID: workID,
      }
    })
  }

  /**
   * 删除草稿
   * @param workIDs  草稿实例编号,多个用逗号隔开
   * @returns 成功或者失败信息
   */
  export function Flow_DeleteDraft(workIDs) {
    return
    request({
      url: '/WF/API/Flow_DeleteDraft',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'post',
      data: {
        workIDs,
      }
    })

  }

  /**
   * 撤销发送
   * @param workIDs 要执行的实例,多个实例用逗号分开比如：1001,1002,1003
   * @returns 失败返回失败信息
   */
  export function Flow_DoUnSend(workIDs) {
    return
    request({
      url: '/WF/API/Flow_DoUnSend',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workIDs,
      }
    })

  }

  /**
   * 催办
   * @param workIDs 催办的实例
   * @param msg 催办信息
   * @returns
   */
  export function Flow_DoPress(workIDs, msg) {
    return
    request({
      url: '/WF/API/Flow_DoPress',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workIDs,
        msg
      }
    })


  }

  /**
   * 获取批处理
   * @returns 批处理节点
   */
  export function Batch_Init() {
    return
    request({
      url: '/WF/API/Batch_Init',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
      }
    })

  }

  /**
   * 获取抄送
   * @returns 抄送节点
   * @param  domain 域 非必需
   * @param flowNo 流程编号 非必需
   */
  export function DB_CCList(domain, flowNo) {
    return
    request({
      url: '/WF/API/DB_CCList',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        domain,
        flowNo,
      }
    })

  }

  /**
   * 查询数据
   * @param key
   * @param dtFrom
   * @param dtTo
   * @param scop
   * @param pageIdx
   * @returns
   */
  export function Search_Init(key, dtFrom, dtTo, scop, pageIdx) {
    return
    request({
      url: '/WF/API/Search_Init',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        key,
        dtFrom,
        dtTo,
        scop,
        pageIdx,
      }
    })

  }
  /**
   * 近期工作
   * @returns
   */
  export function Flow_RecentWorkInit() {
    return
    request({
      url: '/WF/API/Flow_RecentWorkInit',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
      }
    })
  }

  /**
   * 已完成
   * @returns
   */
  export function Complete_Init() {
    return
    request({
      url: '/WF/API/DB_Complete',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
      }
    })
  }

  export function DtlAth_Fill(params) {
    return
    request({
      url: '/WF/API/Node_SaveWork',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'post',
      data: {
        ...params,
      }
    })

  }

  // ======================================================== 菜单数据接口. =======================================================
  //发起流程
  export function DB_Start(domain) {
    return
    request({
      url: '/WF/API/DB_Start',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        domain: domain || ''
      }
    })

  }

  //待办
  export function DB_Todolist(domain) {
    return
    request({
      url: '/WF/API/DB_Todolist',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        domain: domain || ''
      }
    })


  }

  //在途
  export function DB_Runing(domain) {
    return
    request({
      url: '/WF/API/DB_Runing',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        domain: domain || ''
      }
    })

  }

  /**
   * 获取草稿列表
   * @param domain 流程的域/系统编号
   * @returns 草稿数组
   */
  export function DB_Draft(domain) {
    return
    request({
      url: '/WF/API/DB_Draft',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        domain: domain || ''
      }
    })

  }

  // ======================================================== Bill 单据接口. =======================================================
  /**
   * 创建单据实例.
   * @param frmID 单据ID
   * @returns 返回执行结果Int类型的OID.
   */
  export function Bill_CreateBlankBillID(frmID) {
    return
    request({
      url: '/WF/API/Bill_CreateBlankBillID',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        frmID: frmID || ''
      }
    })

  }
  /**
   * 将单据保存草稿.
   * @param frmID 单据ID
   * @returns null
   */
  export function Bill_SaveAsDraft(workID) {
    return
    request({
      url: '/WF/API/Bill_SaveAsDraft',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workID: workID
      }
    })


  }
  /**
   * 保存单据
   * @param workID 表单实例
   * @param bodyData 主表数据
   * @param dtlJosn 从表数据
   * @param athsJSON 附件数据
   * @returns 执行结果.
   */
  export function Bill_Save(workID, bodyData, dtlJosn, athsJSON) {
    return
    request({
      url: '/WF/API/Bill_SaveAsDraft',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workID: workID,
        bodyData: bodyData,
        dtlJosn: dtlJosn,
        athsJSON: athsJSON,
      }
    })

  }

  /**
   * 预置审批人
   * 1. 等开始节点的填写人提交审核的时候，系统就会按照这些预置的审批人进行启动审核。
   * 2. 设置的审核人是按照顺序审批。
   * 3. 当前单据的审批模式必须是按照外部的程序调用模式。
   * @param workID 表单实例
   * @param checkEmpNos  要预置的审核人员，比如:zhangsan,lisi,wangwu 多个人员用逗号分开.
   * @returns 执行结果.
   */
  export function Bill_PreplaceChecker(workID, checkEmpNos) {
    return
    request({
      url: '/WF/API/Bill_PreplaceChecker',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workID: workID,
        checkEmpNos: checkEmpNos,
      }
    })
  }

  /**
   * 提交单据：由编辑状态转为归档状态,归档后表单只读.
   * @param workID 流程实例
   * @returns 执行结果
   */
  export function Bill_SubmitWork(workID) {
    return
    request({
      url: '/WF/API/Bill_SubmitWork',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workID: workID,
      }
    })

  }
  /**
 * 撤销提交: 由单据的归档状态转变为可编辑.
 * @param workID 流程实例
 * @returns 执行结果
 */
  export function Bill_UnSubmitWork(workID) {
    return
    request({
      url: '/WF/API/Bill_UnSubmitWork',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workID: workID,
      }
    })

  }

  /**
  * 初始化单据的审核人
  * 1. 如果单据需要审批，审批之前需要初始化审批人.
  * 2. 执行后，该单据的状态转化为审批中.
  * @param workID 流程实例
  * @param empNosOfChecker 可以审批的人员,格式:zhangsan,lisi,wangwu
  * @returns 执行结果
  */
  export function Bill_CheckerInit(workID, empNosOfChecker) {
    return
    request({
      url: '/WF/API/Bill_CheckerInit',
      headers: {
        isToken: false,
        repeatSubmit: false
      },
      method: 'get',
      params: {
        workID: workID,
        empNosOfChecker: empNosOfChecker,
      }
    })

  }

