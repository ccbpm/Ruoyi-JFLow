package bp.wf.dts;

import bp.da.*;
import bp.port.*;
import bp.en.*; import bp.en.Map;
import bp.web.*;
import bp.*;
import bp.wf.*;
import bp.wf.template.FrmWorkCheckSta;
import bp.wf.template.NodeWorkCheckAttr;

import java.util.*;

/** 
 处理延期的任务 的摘要说明
*/
public class DTS_DealDeferredWork extends Method
{
	/** 
	 处理延期的任务
	*/
	public DTS_DealDeferredWork()
	{
		this.Title = "处理逾期的任务";
		this.Help = "需要每天执行一次,对于已经逾期的工作,按照逾期的规则处理。";
		this.GroupName = "流程自动执行定时任务";

	}
	/** 
	 设置执行变量
	 
	 @return 
	*/
	@Override
	public void Init()
	{
		//this.Warning = "您确定要执行吗？";
		//HisAttrs.AddTBString("P1", null, "原密码", true, false, 0, 10, 10);
		//HisAttrs.AddTBString("P2", null, "新密码", true, false, 0, 10, 10);
		//HisAttrs.AddTBString("P3", null, "确认", true, false, 0, 10, 10);
	}
	/** 
	 当前的操纵员是否可以执行这个方法
	*/
	@Override
	public boolean getIsCanDo()
	{
		return true;
	}

	private String generateMessage(String name, String nodeName) {
		return "您好，" + name + "先生/女士，\n\n" +
				"这是一条来自ccflow系统的自动提醒消息。我们注意到，您负责的工作任务在[" + nodeName + "]节点上未能按时处理，" +
				"目前已经超出了预定的完成时间。\n\n" +
				"为了确保项目的顺利进行和团队的整体效率，请您尽快查看并处理该任务。如有特殊情况或需要协助，" +
				"请及时与团队成员或项目负责人沟通。\n\n" +
				"感谢您的理解和配合，期待您尽快完成相关工作。\n\n" +
				"祝您工作顺利！\n\n" +
				"ccflow系统敬上";
	}

	private String generateTitle(String name, String nodeName) {
		return "来自ccflow的自动消息:(" + name + ")工作未按时处理(" + nodeName + ")";
	}
	/** 
	 执行
	 
	 @return 返回执行结果
	*/
	@Override
	public Object Do() throws Exception {
		//String sql = "SELECT * FROM WF_EmpWorks WHERE FK_Node IN (SELECT NodeID FROM WF_Node WHERE OutTimeDeal >0 ) AND SDT <='" + DataType.getCurrentDate() + "' ORDER BY FK_Emp";
		//改成小于号SDT <'" + DataType.getCurrentDate()
		String sql = "SELECT * FROM WF_EmpWorks WHERE FK_Node IN (SELECT NodeID FROM WF_Node WHERE OutTimeDeal >0 ) AND SDT <'" + DataType.getCurrentDate() + "' ORDER BY FK_Emp";
		//String sql = "SELECT * FROM WF_EmpWorks WHERE FK_Node IN (SELECT NodeID FROM WF_Node WHERE OutTimeDeal >0 ) AND SDT <='2013-12-30' ORDER BY FK_Emp";
		DataTable dt = DBAccess.RunSQLReturnTable(sql);
		String msg = "";
		String dealWorkIDs = "";
		for (DataRow dr : dt.Rows)
		{
			String FK_Emp = dr.getValue("FK_Emp").toString();
			String fk_flow = dr.getValue("FK_Flow").toString();
			int fk_node = Integer.parseInt(dr.getValue("FK_Node").toString());
			long workid = Long.parseLong(dr.getValue("WorkID").toString());
			long fid = Long.parseLong(dr.getValue("FID").toString());

			// 方式两个人同时处理一件工作, 一个人处理后，另外一个人还可以处理的情况.
			if (dealWorkIDs.contains("," + workid + ","))
			{
				continue;
			}
			dealWorkIDs += "," + workid + ",";

			if (!Objects.equals(WebUser.getNo(), FK_Emp))
			{
				Emp emp = new Emp(FK_Emp);
				WebUser.SignInOfGener(emp, "CH", false, false, null, null);
			}

			Node nd = new Node();
			nd.setNodeID(fk_node);
			nd.Retrieve();

			// 首先判断是否有启动的表达式, 它是是否自动执行的总阀门。
			if (DataType.IsNullOrEmpty(nd.getDoOutTimeCond()) == false)
			{
				Node nodeN = new Node(nd.getNodeID());
				Work wk = nodeN.getHisWork();
				wk.setOID(workid);
				wk.Retrieve();
				Object tempVar = nd.getDoOutTimeCond();
				String exp = tempVar instanceof String ? (String)tempVar : null;
				if (this.ExeExp(exp, wk) == false)
				{
				  //  msg += "err@条件表达式配置错误:"+exp;
					continue; // 不能通过条件的设置.
				}
			}

			switch (nd.getHisOutTimeDeal())
			{
				case None:
					break;
				case AutoTurntoNextStep: //自动转到下一步骤.
					if (DataType.IsNullOrEmpty(nd.getDoOutTime()))
					{
						/*如果是空的,没有特定的点允许，就让其它向下执行。*/
						msg += Dev2Interface.Node_SendWork(fk_flow, workid).ToMsgOfText();
					}
					else
					{
						int nextNode = Dev2Interface.Node_GetNextStepNode(fk_flow, workid);
						if (nd.getDoOutTime().contains(String.valueOf(nextNode))) //如果包含了当前点的ID,就让它执行下去.
						{
							msg += Dev2Interface.Node_SendWork(fk_flow, workid).ToMsgOfText();
						}
					}
					break;
				case AutoJumpToSpecNode: //自动的跳转下一个节点.
					if (DataType.IsNullOrEmpty(nd.getDoOutTime()))
					{
						throw new RuntimeException("@设置错误,没有设置要跳转的下一步节点.");
					}
					/*如果是空的,没有特定的点允许，就让其它向下执行。*/
					if(nd.getFrmWorkCheckSta() == FrmWorkCheckSta.Enable){
						/*检查审核意见 */
						String sql1 = "SELECT Msg \"Msg\",EmpToT \"EmpToT\" FROM ND" + Integer.parseInt(nd.getFlowNo()) + "Track WHERE  EmpFrom='" + WebUser.getNo() + "' AND NDFrom=" + nd.getNodeID() + " AND WorkID=" + workid + " AND ActionType=" + ActionType.WorkCheck.getValue();
						DataTable dtt = DBAccess.RunSQLReturnTable(sql1);
						if (dtt.Rows.size() <= 0)
							Dev2Interface.Node_WriteWorkCheck(workid, nd.GetValStringByKey(NodeWorkCheckAttr.FWCDefInfo,"同意"),"","");
					}

					int nextNodeID = Integer.parseInt(nd.getDoOutTime());
					msg += Dev2Interface.Node_SendWork(fk_flow, workid, null, null, nextNodeID, null).ToMsgOfText();
					break;
				case AutoShiftToSpecUser: //移交给指定的人员.
					msg += Dev2Interface.Node_Shift(workid, nd.getDoOutTime(), "来自ccflow的自动消息:(" + WebUser.getName() + ")工作未按时处理(" + nd.getName() + "),现在移交给您。");
					break;
				case SendMsgToSpecUser: //向指定的人员发消息.
					Dev2Interface.Port_SendMsg(nd.getDoOutTime(), generateTitle(WebUser.getName(), nd.getName()),
							generateMessage(WebUser.getName(), nd.getName()), "SpecEmp" + workid, SMSMsgType.Etc, fk_flow, fk_node, workid, fid);
					break;
				case SendMsgToStartNode:  //向开始节点发送消息
					GenerWorkFlow generWorkFlow = new GenerWorkFlow(workid);
					Dev2Interface.Port_SendMsg(generWorkFlow.getStarter(), generateTitle(WebUser.getName(), nd.getName()),
							generateMessage(WebUser.getName(), nd.getName()), "SpecEmp" + workid, SMSMsgType.Etc, fk_flow, fk_node, workid, fid);
					break;
				case SendMsgToCurrentNodeHandler: //向当前节点处理人发消息
					GenerWorkFlow generWorkFlow3 = new GenerWorkFlow(workid);
					Dev2Interface.Port_SendMsg(generWorkFlow3.getTodoEmps(), generateTitle(WebUser.getName(), nd.getName()),
							generateMessage(WebUser.getName(), nd.getName()), "SpecEmp" + workid, SMSMsgType.Etc, fk_flow, fk_node, workid, fid);
					break;
				case SendMsgToAll: //向所有人发消息
					String emps = "";
					DataTable dtWL = DBAccess.RunSQLReturnTable("SELECT FK_Emp FROM WF_GenerWokerList WHERE WorkID=" + workid);
					for (DataRow drWL : dtWL.Rows)
					{
						emps += drWL.get(0).toString() + ",";
					}
					Dev2Interface.Port_SendMsg(emps, generateTitle(WebUser.getName(), nd.getName()),
						generateMessage(WebUser.getName(), nd.getName()), "SpecEmp" + workid, SMSMsgType.Etc, fk_flow, fk_node, workid, fid);
					break;
				case DeleteFlow: //删除流程.
					msg += Dev2Interface.Flow_DoDeleteFlowByReal(workid, true);
					break;
				case RunSQL:
					msg += DBAccess.RunSQL(nd.getDoOutTime());
					break;
				default:
					throw new RuntimeException("@错误没有判断的超时处理方式." + nd.getHisOutTimeDeal());
			}
		}
		Emp emp1 = new Emp("admin");
		WebUser.SignInOfGener(emp1, "CH", false, false, null, null);
		return msg;

	}

	/** 
	 计算表达式是否通过(或者是否正确.)
	 
	 @param exp 表达式
	 @param en 实体
	 @return true/false
	*/
	public final boolean ExeExp(String exp, Entity en) throws Exception {
		exp = exp.replace("@WebUser.No", WebUser.getNo());
		exp = exp.replace("@WebUser.Name", WebUser.getName());
		exp = exp.replace("@WebUser.FK_DeptNameOfFull", WebUser.getDeptNameOfFull());
		exp = exp.replace("@WebUser.FK_DeptName", WebUser.getDeptName());
		exp = exp.replace("@WebUser.FK_Dept", WebUser.getDeptNo());
		exp = exp.replace("@WebUser.DeptNo",  WebUser.getDeptNo());

		exp = exp.replace("@RDT", DataType.getCurrentDate());
		exp = exp.replace("@DateTime", DataType.getCurrentDateTime());


		String[] strs = exp.split("[ ]", -1);
		boolean isPass = false;

		String key = strs[0].trim();
		String oper = strs[1].trim();
		String val = strs[2].trim();
		val = val.replace("'", "");
		val = val.replace("%", "");
		val = val.replace("~", "");
		Row row = en.getRow();
		for (String item : row.keySet())
		{
			if (!Objects.equals(key, item.trim()))
			{
				continue;
			}

			String valPara = row.get(key).toString();
			if (Objects.equals(oper, "="))
			{
				if (Objects.equals(valPara, val))
				{
					return true;
				}
			}

			if (Objects.equals(oper.toUpperCase(), "LIKE"))
			{
				if (valPara.contains(val))
				{
					return true;
				}
			}

			if (Objects.equals(oper, ">"))
			{
				if (Float.parseFloat(valPara) > Float.parseFloat(val))
				{
					return true;
				}
			}
			if (Objects.equals(oper, ">="))
			{
				if (Float.parseFloat(valPara) >= Float.parseFloat(val))
				{
					return true;
				}
			}
			if (Objects.equals(oper, "<"))
			{
				if (Float.parseFloat(valPara) < Float.parseFloat(val))
				{
					return true;
				}
			}
			if (Objects.equals(oper, "<="))
			{
				if (Float.parseFloat(valPara) <= Float.parseFloat(val))
				{
					return true;
				}
			}

			if (Objects.equals(oper, "!="))
			{
				if (Float.parseFloat(valPara) != Float.parseFloat(val))
				{
					return true;
				}
			}
			throw new RuntimeException("@参数格式错误:" + exp + " Key=" + key + " oper=" + oper + " Val=" + val);
		}
		return false;
	}
}
