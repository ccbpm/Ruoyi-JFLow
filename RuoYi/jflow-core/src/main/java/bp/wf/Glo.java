package bp.wf;

import bp.ccbill.FrmBill;
import bp.ccbill.GenerBill;
import bp.ccbill.GenerWorker;
import bp.difference.StringHelper;
import bp.difference.handler.CommonUtils;
import bp.sys.*;
import bp.da.*;
import bp.en.*;
import bp.sys.CCFormAPI;
import bp.sys.FrmImgAth;
import bp.sys.SFTable;
import bp.sys.frmui.ExtImg;
import bp.tools.*;
import bp.web.*;
import bp.port.*;
import bp.wf.data.*;
import bp.wf.template.*;
import bp.difference.*;
import bp.wf.template.Printer.FrmPrintTemplate;
import bp.wf.template.sflow.*;
import bp.wf.template.frm.*;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.time.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 全局(方法处理)
 */
public class Glo
{
	/**
	 单据编号对应字段SQL
	 */
	public static String getSQLOfBillNo()
	{
		String sql = "";
		switch (SystemConfig.getAppCenterDBType())
		{
			case MSSQL:
			case MySQL:
			case GBASE8CByMySQL:
			case GBASE8A:
				sql = "SELECT '' AS No, '-请选择-' as Name ";
				break;
			case Oracle:
			case DM:
			case KingBaseR3:
			case KingBaseR6:
			case GBASE8CByOracle:
				sql = "SELECT '' AS No, '-请选择-' as Name FROM DUAL ";
				break;
			case PostgreSQL:
			case UX:
			case HGDB:
			default:
				sql = "SELECT '' AS No, '-请选择-' as Name FROM Port_Emp WHERE 1=2 ";
				break;
		}
		sql += " UNION ";
		sql += " SELECT KeyOfEn AS No,Name FROM Sys_MapAttr WHERE UIContralType=0 AND UIVisible=1 AND UIIsEnable=1 AND FK_MapData='@FK_Frm'";
		return sql;
	}
	/**
	 签批组件SQL
	 */
	public static String getSQLOfCheckField()
	{
		String sql = "";
		switch (SystemConfig.getAppCenterDBType())
		{
			case MSSQL:
			case MySQL:
			case GBASE8CByMySQL:
			case GBASE8A:
				sql = "SELECT '' AS No, '-请选择-' as Name ";
				break;
			case Oracle:
			case DM:
			case KingBaseR3:
			case KingBaseR6:
			case GBASE8CByOracle:
				sql = "SELECT '' AS No, '-请选择-' as Name FROM DUAL ";
				break;
			case PostgreSQL:
			case UX:
			case HGDB:
			default:
				sql = "SELECT '' AS No, '-请选择-' as Name FROM Port_Emp WHERE 1=2 ";
				break;
		}
		sql += " UNION ";
		sql += " SELECT KeyOfEn AS No,Name From Sys_MapAttr WHERE UIContralType=14 AND FK_MapData='@FK_Frm'";
		return sql;
	}


	///#region 获取[新建-节点-流程]默认值.
	/**
	 新建节点的审核意见默认值.
	 */
	public static String getDefValWFNodeFWCDefInfo()
	{
		return SystemConfig.GetValByKey("DefVal_WF_Node_FWCDefInfo", "同意");
	}

	///#endregion 获取[新建流程]默认值.



	///#region 多语言处理.
	private static Hashtable _Multilingual_Cache = null;
	public static DataTable getMultilingual_DT(String className)
	{
		if (_Multilingual_Cache == null)
		{
			_Multilingual_Cache = new Hashtable();
		}

		if (_Multilingual_Cache.containsKey(className) == false)
		{
			DataSet ds = DataType.CXmlFileToDataSet(SystemConfig.getPathOfData() + "lang/xml/" + className + ".xml");
			DataTable dt = ds.Tables.get(0);

			_Multilingual_Cache.put(className, dt);
		}

		return _Multilingual_Cache.get(className) instanceof DataTable ? (DataTable)_Multilingual_Cache.get(className) : null;
	}
	/**
	 转换语言.
	 */

	public static String multilingual(String defaultMsg, String className, String key, String p0, String p1, String p2)
	{
		return multilingual(defaultMsg, className, key, p0, p1, p2, null);
	}

	public static String multilingual(String defaultMsg, String className, String key, String p0, String p1)
	{
		return multilingual(defaultMsg, className, key, p0, p1, null, null);
	}

	public static String multilingual(String defaultMsg, String className, String key, String p0)
	{
		return multilingual(defaultMsg, className, key, p0, null, null, null);
	}

	public static String multilingual(String defaultMsg, String className, String key)
	{
		return multilingual(defaultMsg, className, key, null, null, null, null);
	}

	public static String multilingual(String defaultMsg, String className, String key, String p0, String p1, String p2, String p3)
	{
		int num = 4;
		String[] paras = new String[num];
		if (p0 != null)
		{
			paras[0] = p0;
		}

		if (p1 != null)
		{
			paras[1] = p1;
		}

		if (p2 != null)
		{
			paras[2] = p2;
		}

		if (p3 != null)
		{
			paras[3] = p3;
		}

		return multilingual(defaultMsg, className, key, paras);
	}
	/**
	 获取多语言

	 @param key
	 @param paramList
	 @return
	 */
	public static String multilingual(String defaultMsg, String className, String key, String[] paramList)
	{
		if (WebUser.getSysLang().equals("zh-cn") || WebUser.getSysLang().equals("CH"))
		{
			defaultMsg = defaultMsg.replace("{0}","%1$s").replace("{1}","%2$s").replace("{2}","%3$s").replace("{3}","%4$s");
			return String.format(defaultMsg, paramList);
		}

		DataTable dt = getMultilingual_DT(className);

		String val = "";
		for (DataRow dr : dt.Rows)
		{
			if (key.equals((String) dr.getValue(0)))
			{
				switch (WebUser.getSysLang())
				{
					case "zh-cn":
						val = (String) dr.getValue(1);
						break;
					case "zh-tw":
						val = (String) dr.getValue(2);
						break;
					case "zh-hk":
						val = (String) dr.getValue(3);
						break;
					case "en-us":
						val = (String) dr.getValue(4);
						break;
					case "ja-jp":
						val = (String) dr.getValue(5);
						break;
					case "ko-kr":
						val = (String) dr.getValue(6);
						break;
					default:
						val = (String) dr.getValue(1);
						break;
				}
				break;
			}
		}
		return String.format(val, paramList);
	}

	///#region 公共属性.
	/**
	 打印文件
	 */
	public static String getPrintBackgroundWord() throws Exception {
		String s = SystemConfig.GetValByKey("PrintBackgroundWord","");
		if (DataType.IsNullOrEmpty(s))
		{
			s = "驰骋工作流引擎@开源驰骋 - ccflow@openc";
		}
		return s;
	}
	/**
	 运行平台.
	 */
	public static Platform getPlatform()
	{
		return Platform.CCFlow;
	}

	/**
	 短消息写入类型
	 */
	public static ShortMessageWriteTo getShortMessageWriteTo()
	{
		return ShortMessageWriteTo.forValue(SystemConfig.GetValByKeyInt("ShortMessageWriteTo", 0));
	}
	/**
	 当前选择的流程.
	 */
	public static String getCurrFlow() throws Exception {
		Object tempVar = bp.sys.Glo.getRequest().getSession().getAttribute("CurrFlow");
		return tempVar instanceof String ? (String) tempVar : null;
	}
	public static void setCurrFlow(String value)
	{
		bp.sys.Glo.getRequest().getSession().setAttribute("CurrFlow", value);
	}
	/**
	 用户编号.
	 */
	public static String UserNo = null;
	/**
	 运行平台(用于处理不同的平台，调用不同的URL)
	 */
	public static Plant Plant = bp.wf.Plant.CCFlow;

	///#endregion 公共属性.

	/**
	 CCBPMRunModel
	 */
	public static CCBPMRunModel getCCBPMRunModel()
	{
		return SystemConfig.getCCBPMRunModel();
	}

	/**
	 CCFlowAppPath
	 */
	public static String getCCFlowAppPath()
	{
		return SystemConfig.GetValByKey("CCFlowAppPath", "/");
	}

	public static boolean isEnableHuiQianList()
	{
		if (Objects.equals(SystemConfig.getCustomerNo(), "TianYe"))
		{
			return true;
		}

		return SystemConfig.GetValByKeyBoolen("IsEnableHuiQianList", false);
	}

	public static void KillProcess(String processName) //杀掉进程的方法
	{
		/*System.Diagnostics.Process[] processes = System.Diagnostics.Process.GetProcesses();
		for (System.Diagnostics.Process pro : processes)
		{
			String name = pro.ProcessName + ".exe";
			if (Objects.equals(name.toLowerCase(), processName.toLowerCase()))
			{
				pro.Kill();
			}
		}*/
		try {
			// 执行系统命令
			Process process = Runtime.getRuntime().exec("taskkill /F /IM " + processName);
			// 获取命令执行结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			// 等待命令执行完成
			int exitCode = process.waitFor();
			System.out.println("Exited with error code: " + exitCode);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	///#endregion 执行安装.


	///#region 流程模版的ftp服务器配置.
	public static String getTemplateFTPHost()
	{
		return SystemConfig.GetValByKey("TemplateFTPHost", "116.239.32.14");
	}
	public static int getTemplateFTPPort()
	{
		return SystemConfig.GetValByKeyInt("TemplateFTPPort", 9997);
	}
	public static String getTemplateFTPUser()
	{
		return SystemConfig.GetValByKey("TemplateFTPUser", "oa");
	}
	public static String getTemplateFTPPassword()
	{
		return SystemConfig.GetValByKey("TemplateFTPPassword", "Jszx1234");
	}

	///#endregion 流程模版的ftp服务器配置.


	///#region 全局的方法处理
	/**
	 流程数据表系统字段,中间用,分开.
	 */
	public static String getFlowFields()
	{
		String str = ",";
		str += GERptAttr.OID + ",";
		str += GERptAttr.AtPara + ",";
		str += GERptAttr.BillNo + ",";
		//  str += GERptAttr.CFlowNo + ",";
		//  str += GERptAttr.CWorkID + ",";
		str += GERptAttr.FID + ",";
		str += GERptAttr.FK_Dept + ",";
		str += GERptAttr.FK_DeptName + ",";
		str += GERptAttr.FK_NY + ",";
		str += GERptAttr.FlowDaySpan + ",";
		str += GERptAttr.FlowEmps + ",";
		str += GERptAttr.FlowEnder + ",";
		str += GERptAttr.FlowEnderRDT + ",";
		str += GERptAttr.FlowEndNode + ",";
		str += GERptAttr.FlowStarter + ",";
		str += GERptAttr.FlowStartRDT + ",";
		str += GERptAttr.GuestName + ",";
		str += GERptAttr.GuestNo + ",";
		str += GERptAttr.GUID + ",";
		str += GERptAttr.PEmp + ",";
		str += GERptAttr.PFID + ",";
		str += GERptAttr.PFlowNo + ",";
		str += GERptAttr.PNodeID + ",";
		str += GERptAttr.PrjName + ",";
		str += GERptAttr.PrjNo + ",";
		str += GERptAttr.PWorkID + ",";
		str += GERptAttr.Title + ",";
		str += GERptAttr.WFSta + ",";
		str += GERptAttr.WFState + ",";
		str += "Rec,";
		str += "CDT,RDT,WFStateText";
		return str;
	}

	///#endregion 全局的方法处理


	///#region 与流程事件实体相关.
	private static Hashtable Htable_FlowFEE = null;
	/**
	 获得节点事件实体

	 @param enName 实例名称
	 @return 获得节点事件实体,如果没有就返回为空.
	 */
	public static FlowEventBase GetFlowEventEntityByEnName(String enName)
	{
		if (Htable_FlowFEE == null || Htable_FlowFEE.isEmpty())
		{
			Htable_FlowFEE = new Hashtable();

			String name = "BP.WF.FlowEventBase";

			ArrayList<FlowEventBase> al = bp.en.ClassFactory.GetObjects("bp.wf.FlowEventBase");
			for (FlowEventBase en : al)
			{
				if (Htable_FlowFEE.containsKey(en.toString()) == true)
				{
					continue;
				}
				Htable_FlowFEE.put(en.getClass().getName(), en);
			}
		}
		FlowEventBase myen = Htable_FlowFEE.get(enName) instanceof FlowEventBase ? (FlowEventBase)Htable_FlowFEE.get(enName) : null;
		if (myen == null)
		{
			//throw new Exception("@根据类名称获取流程事件实体实例出现错误:" + enName + ",没有找到该类的实体.");
			Log.DebugWriteError("@根据类名称获取流程事件实体实例出现错误:" + enName + ",没有找到该类的实体.");
			return null;
		}
		return myen;
	}
	/**
	 获得事件实体String，根据编号或者流程标记

	 @param flowMark 流程标记
	 @return null, 或者流程实体.
	 */
	public static String GetFlowEventEntityStringByFlowMark(String flowMark)
	{
		FlowEventBase en = GetFlowEventEntityByFlowMark(flowMark);
		if (en == null)
		{
			return "";
		}
		return en.getClass().getName();
	}
	/**
	 获得事件实体，根据编号或者流程标记.

	 @param flowMark 流程标记
	 @return null, 或者流程实体.
	 */
	public static FlowEventBase GetFlowEventEntityByFlowMark(String flowMark)
	{

		if (Htable_FlowFEE == null || Htable_FlowFEE.isEmpty())
		{
			Htable_FlowFEE = new Hashtable();

			String name = "";
			name = "BP.WF.FlowEventBase";

			ArrayList<FlowEventBase> al = bp.en.ClassFactory.GetObjects("bp.wf.FlowEventBase");
			Htable_FlowFEE.clear();
			for (FlowEventBase en : al)
			{
				if (Htable_FlowFEE.containsKey(en.toString()) == true)
				{
					continue;
				}
				Htable_FlowFEE.put(en.getClass().getName(), en);
			}
		}

		for (Object key : Htable_FlowFEE.keySet())
		{
			FlowEventBase fee = Htable_FlowFEE.get(key) instanceof FlowEventBase ? (FlowEventBase)Htable_FlowFEE.get(key) : null;

			String mark = "," + fee.getFlowMark() + ",";
			if (mark.contains("," + flowMark + ",") == true)
			{
				return fee;
			}
		}
		return null;
	}

	///#endregion 与流程事件实体相关.


	///#region web.config 属性.
	public static boolean isEnableTrackRec()
	{
		String s = SystemConfig.GetValByKey("IsEnableTrackRec", "");
		if (DataType.IsNullOrEmpty(s))
		{
			return false;
		}
		if (s.equals("0")) {
			return false;
		}

		return true;
	}
	public static String MapDataLikeKey(String flowNo, String colName)
	{
		flowNo = String.valueOf(Integer.parseInt(flowNo));
		String len = SystemConfig.getAppCenterDBLengthStr();

		//edited by liuxc,2016-02-22,合并逻辑，原来分流程编号的位数，现在统一处理
		return " (" + colName + " LIKE 'ND" + flowNo + "%' AND " + len + "(" + colName + ")=" + ("ND".length() + flowNo.length() + 2) + ") OR (" + colName + " = 'ND" + flowNo + "Rpt' ) OR (" + colName + " LIKE 'ND" + flowNo + "__Dtl%' AND " + len + "(" + colName + ")>" + ("ND".length() + flowNo.length() + 2 + "Dtl".length()) + ")";
	}


	///#endregion webconfig属性.


	///#region 常用方法
	/**
	 加入track

	 @param at 事件类型
	 @param flowNo 流程编号
	 @param workID 工作ID
	 @param fid 流程ID
	 @param fromNodeID 从节点编号
	 @param fromNodeName 从节点名称
	 @param fromEmpID 从人员ID
	 @param fromEmpName 从人员名称
	 @param toNodeID 到节点编号
	 @param toNodeName 到节点名称
	 @param toEmpID 到人员ID
	 @param toEmpName 到人员名称
	 @param note 消息
	 @param tag 参数用@分开
	 */
	public static String AddToTrack(ActionType at, String flowNo, long workID, long fid, int fromNodeID, String fromNodeName, String fromEmpID, String fromEmpName, int toNodeID, String toNodeName, String toEmpID, String toEmpName, String note, String tag) throws Exception {
		if (toNodeID == 0)
		{
			toNodeID = fromNodeID;
			toNodeName = fromNodeName;
		}

		Track t = new Track();
		t.setWorkID(workID);
		t.setFID(fid);
		t.setRDT(DataType.getCurrentDateTimess());
		t.setHisActionType(at);

		t.setNDFrom(fromNodeID);
		t.setNDFromT(fromNodeName);

		t.setEmpFrom(fromEmpID);
		t.setEmpFromT(fromEmpName);

		t.setNDTo(toNodeID);
		t.setNDToT(toNodeName);

		t.FlowNo = flowNo;

		String[] empNos = toEmpID.split(",");
		if (empNos.length <= 100)
		{
			t.setEmpTo(toEmpID);
			t.setEmpToT(toEmpName);
		}
		else
		{
			String[] empNames = toEmpName.split("[、]", -1);
			String[] takes = Arrays.copyOfRange(empNos, 0, Math.min(100, empNos.length));
			//获取
			t.setEmpTo(StringHelper.join(",", takes) + "..." + empNos[empNos.length - 1]);
			t.setEmpToT(StringHelper.join("'、", takes) + "..." + empNames[empNames.length - 1]);
		}

		t.setMsg(note);
		t.setNodeData("@DeptNo=" + WebUser.getDeptNo() + "@DeptName=" + WebUser.getDeptName());
		//参数.
		if (tag != null)
		{
			t.setTag(tag);
		}

		try
		{
			t.Insert();
		}
		catch (java.lang.Exception e)
		{
			t.CheckPhysicsTable();
			t.Insert();
		}
		return t.getMyPK();
	}

	/**
	 SQL表达式是否正确

	 @param sqlExp
	 @param ht
	 @return
	 */
	public static boolean CondExpSQL(String sqlExp, Hashtable ht, long myWorkID) throws Exception {
		String sql = sqlExp;
		sql = sql.replace("~", "'");
		sql = sql.replace("@WebUser.No", WebUser.getNo());
		sql = sql.replace("@WebUser.Name", WebUser.getName());
		sql = sql.replace("@WebUser.FK_Dept", WebUser.getDeptNo());
		sql = sql.replace("@WebUser.DeptNo", WebUser.getDeptNo());

		for (Object key : ht.keySet())
		{
			if (Objects.equals(key, "OID"))
			{
				sql = sql.replace("@WorkID", ht.get("OID").toString());
				sql = sql.replace("@OID", ht.get("OID").toString());
				continue;
			}
			sql = sql.replace("@" + key, ht.get(key).toString());
		}

		//从工作流参数里面替换
		if (sql.contains("@") == true && myWorkID != 0)
		{
			GenerWorkFlow gwf = new GenerWorkFlow(myWorkID);
			AtPara ap = gwf.getatPara();
			for (String str : ap.getHisHT().keySet())
			{
				sql = sql.replace("@" + str, ap.GetValStrByKey(str));
			}
		}

		int result = DBAccess.RunSQLReturnValInt(sql, -1);
		if (result <= 0)
		{
			return false;
		}
		return true;
	}
	/**
	 判断表达式是否成立

	 @param exp 表达式
	 @param ht 变量
	 @return 是否成立
	 */
	public static boolean CondExpPara(String exp, Hashtable ht, long myWorkID)
	{
		try
		{
			String[] strs = exp.trim().split("[ ]", -1);

			if(strs.length<3)
				throw new RuntimeException("计算参数计算出现错误:参数["+exp+"],操作符前后需要增加空格");
			String key = strs[0].trim();
			String oper = strs[1].trim();
			String val = strs[2].trim();

			val = val.replace("'", "");
			val = val.replace("%", "");
			val = val.replace("~", "");

			String valPara = null;
			if (ht.containsKey(key) == false)
			{

				boolean isHave = false;
				if (myWorkID != 0)
				{
					//把外部传来的参数传入到 rptGE 让其做方向条件的判断.
					GenerWorkFlow gwf = new GenerWorkFlow(myWorkID);
					AtPara at = gwf.getatPara();
					for (String str : at.getHisHT().keySet())
					{
						if (key.equals(str) == false)
						{
							continue;
						}

						valPara = at.GetValStrByKey(key);
						isHave = true;
						break;
					}
				}

				if (isHave == false)
				{
					try
					{
						if (SystemConfig.isBSsystem() == true && CommonUtils.getRequest().getParameterMap().keySet().contains(key) == true)
						{
							valPara = ContextHolderUtils.getRequest().getParameter(key);
						}
						else
						{
							throw new RuntimeException("@判断条件时错误,请确认参数是否拼写错误,没有找到对应的表达式:" + exp + " Key=(" + key + ") oper=(" + oper + ")Val=(" + val + ")");
						}
					}
					catch (java.lang.Exception e)
					{
						//有可能是常量.
						valPara = key;
					}
				}
			}
			else
			{
				valPara = ht.get(key).toString().trim();
			}


			///#region 开始执行判断.
			if (Objects.equals(oper, "="))
			{
				if (Objects.equals(valPara, val))
				{
					return true;
				}
				else
				{
					return false;
				}
			}

			if (Objects.equals(oper.toUpperCase(), "LIKE"))
			{
				if (valPara.contains(val))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			if (Objects.equals(oper, "!="))
			{
				if (!Objects.equals(valPara, val))
				{
					return true;
				}
				else
				{
					return false;
				}
			}


			if (DataType.IsNumStr(valPara) == false)
			{
				throw new RuntimeException("err@表达式错误:[" + exp + "]没有找到参数[" + valPara + "]的值，导致无法计算。");
			}

			if (Objects.equals(oper, ">"))
			{
				if (Float.parseFloat(valPara) > Float.parseFloat(val))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			if (Objects.equals(oper, ">="))
			{
				if (Float.parseFloat(valPara) >= Float.parseFloat(val))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			if (Objects.equals(oper, "<"))
			{
				if (Float.parseFloat(valPara) < Float.parseFloat(val))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			if (Objects.equals(oper, "<="))
			{
				if (Float.parseFloat(valPara) <= Float.parseFloat(val))
				{
					return true;
				}
				else
				{
					return false;
				}
			}

			if (Objects.equals(oper, "!="))
			{
				if (Float.parseFloat(valPara) != Float.parseFloat(val))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			throw new RuntimeException("@参数格式错误:" + exp + " Key=" + key + " oper=" + oper + " Val=" + val);

			///#endregion 开始执行判断.

		}
		catch (RuntimeException ex)
		{
			throw new RuntimeException("计算参数的时候出现错误:" + ex.getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 表达式替换

	 @param exp
	 @param en
	 @return
	 */

	public static String DealExp(String exp, Entity en) throws Exception {
		return DealExp(exp, en, "");
	}

	public static String DealExp(String exp, Entity en, String errInfo)  {
		//替换字符
		exp = exp.replace("~~", "\"");
		exp = exp.replace("~", "'");

		if (exp.contains("@") == false)
		{
			return exp;
		}

		//首先替换加; 的。
		exp = exp.replace("@WebUser.No;", WebUser.getNo());
		exp = exp.replace("@WebUser.Name;", WebUser.getName());
		exp = exp.replace("@WebUser.FK_DeptName;", WebUser.getDeptName());
		exp = exp.replace("@WebUser.FK_Dept;", WebUser.getDeptNo());
		exp = exp.replace("@WebUser.DeptNo;", WebUser.getDeptNo());
		// 替换没有 ; 的 .
		exp = exp.replace("@WebUser.No", WebUser.getNo());
		exp = exp.replace("@WebUser.Name", WebUser.getName());
		exp = exp.replace("@WebUser.FK_DeptName", WebUser.getDeptName());
		exp = exp.replace("@WebUser.FK_Dept", WebUser.getDeptNo());
		exp = exp.replace("@WebUser.DeptNo", WebUser.getDeptNo());
		exp = exp.replace("@WebUser.OrgNo", WebUser.getOrgNo());

		exp = exp.replace("@RDT", DataType.getCurrentDateTime());

		if (exp.contains("@") == false)
		{
			return exp;
		}

		//增加对新规则的支持. @MyField; 格式.
		if (en != null)
		{
			Attrs attrs = en.getEnMap().getAttrs();
			Row row = en.getRow();
			//特殊判断.
			if (row.containsKey("OID") == true)
			{
				exp = exp.replace("@WorkID", row.get("OID").toString());
			}

			if (exp.contains("@") == false)
			{
				return exp;
			}


			boolean isHaveFenHao = exp.contains(";");


			for (String key : row.keySet())
			{
				//值为空或者null不替换
				if (row.get(key) == null)
				{
					continue;
				}
				if (exp.contains("@" + key + ";"))
				{
					//先替换有单引号的.
					exp = exp.replace("'@" + key + ";'", "'" + row.get(key).toString() + "'");
					//在更新没有单引号的.
					exp = exp.replace("@" + key + ";", row.get(key).toString());
				}
				if (exp.contains("@" + key))
				{
					//先替换有单引号的.
					exp = exp.replace("'@" + key + "'", "'" + row.get(key).toString() + "'");
					//在更新没有单引号的.
					exp = exp.replace("@" + key, row.get(key).toString());
				}
				//不包含@则返回SQL语句
				if (exp.contains("@") == false)
				{
					return exp;
				}
			}
		}

		if (exp.contains("@") && SystemConfig.isBSsystem() == true)
		{
			/*如果是bs*/
			for (String key : ContextHolderUtils.getRequest().getParameterMap().keySet())
			{
				if (DataType.IsNullOrEmpty(key))
				{
					continue;
				}
				exp = exp.replace("@" + key, ContextHolderUtils.getRequest().getParameter(key));
			}

		}

		exp = exp.replace("~", "'");
		return exp;
	}
	//
	/**
	 处理表达式

	 @param exp 表达式
	 @param en 数据源
	 @param errInfo 错误
	 @return
	 */
	public static String DealSQLExp(String exp, Entity en, String errInfo) throws Exception {
		//替换字符
		exp = exp.replace("~~", "\"");
		exp = exp.replace("~", "'");

		//替换我们只处理WHERE 后面的内容
		//需要判断SQL 中含有几个WHERE字符
		String regex = "\\bWHERE\\b";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(exp.toUpperCase());
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		int num = count;
		if (num == 0)
		{
			return exp;
		}
		//我们暂时处理含有一个WHERE的情况
		String expFrom = "";
		if (num == 1)
		{
			expFrom = exp.substring(0, exp.toUpperCase().indexOf("WHERE"));
			exp = exp.substring(expFrom.length());
		}

		String expWhere = "";

		if (exp.contains("@") == false)
		{
			return expFrom + exp;
		}

		//首先替换加; 的。
		exp = exp.replace("@WebUser.No;", WebUser.getNo());
		exp = exp.replace("@WebUser.Name;", WebUser.getName());
		exp = exp.replace("@WebUser.FK_DeptName;", WebUser.getDeptName());
		exp = exp.replace("@WebUser.FK_Dept;", WebUser.getDeptNo());
		exp = exp.replace("@WebUser.DeptNo;", WebUser.getDeptNo());
		exp = exp.replace("@WebUser.DeptName;", WebUser.getDeptName());

		// 替换没有 ; 的 .
		exp = exp.replace("@WebUser.No", WebUser.getNo());
		exp = exp.replace("@WebUser.Name", WebUser.getName());
		exp = exp.replace("@WebUser.FK_DeptName", WebUser.getDeptName());
		exp = exp.replace("@WebUser.FK_Dept", WebUser.getDeptNo());

		exp = exp.replace("@WebUser.DeptName", WebUser.getDeptName());
		exp = exp.replace("@WebUser.DeptNo", WebUser.getDeptNo());

		if (bp.wf.Glo.getCCBPMRunModel() != CCBPMRunModel.Single)
		{
			exp = exp.replace("@WebUser.OrgNo", WebUser.getOrgNo());
		}

		if (exp.contains("@") == false)
		{
			return expFrom + exp;
		}

		//增加对新规则的支持. @MyField; 格式.
		if (en != null)
		{
			Row row = en.getRow();
			//特殊判断.
			if (row.containsKey("OID") == true)
			{
				exp = exp.replace("@WorkID", row.get("OID").toString());
			}

			if (exp.contains("@") == false)
			{
				return expFrom + exp;
			}

			for (String key : row.keySet())
			{
				//值为空或者null不替换
				if (row.get(key) == null || row.get(key).equals("") == true)
				{
					continue;
				}

				if (exp.contains("@" + key + ";"))
				{
					exp = exp.replace("@" + key + ";", row.get(key).toString());
				}

				//不包含@则返回SQL语句
				if (exp.contains("@") == false)
				{
					return expFrom + exp;
				}
			}



			///#region 解决排序问题.
			Attrs attrs = en.getEnMap().getAttrs();
			String mystrs = "";
			for (Attr attr : attrs)
			{
				if (attr.getMyDataType() == DataType.AppString)
				{
					mystrs += "@" + attr.getKey() + ",";
				}
				else
				{
					mystrs += "@" + attr.getKey();
				}
			}
			String[] strs = mystrs.split("[@]", -1);
			DataTable dt = new DataTable();
			dt.Columns.Add(new DataColumn("No", String.class));
			for (String str : strs)
			{
				if (DataType.IsNullOrEmpty(str))
				{
					continue;
				}

				DataRow dr = dt.NewRow();
				dr.setValue(0, str);
				dt.Rows.add(dr);
			}

			///#endregion  解决排序问题.


			///#region 替换变量.
			for (DataRow dr : dt.Rows)
			{
				String key = dr.getValue(0).toString();
				boolean isStr = key.contains(",");
				if (isStr == true)
				{
					key = key.replace(",", "");
				}

				if (DataType.IsNullOrEmpty(en.GetValStrByKey(key)))
				{
					continue;
				}

				exp = exp.replace("@" + key, en.GetValStrByKey(key));
			}

			///#endregion

			if (exp.contains("@") == false)
			{
				return expFrom + exp;
			}
		}

		if (exp.contains("@") && SystemConfig.isBSsystem() == true)
		{
			/*如果是bs*/
			for (String key : ContextHolderUtils.getRequest().getParameterMap().keySet())
			{
				if (DataType.IsNullOrEmpty(key))
				{
					continue;
				}
				exp = exp.replace("@" + key, ContextHolderUtils.getRequest().getParameter(key));
			}
		}
		exp = exp.replace("~", "'");
		exp = exp.replace("\r", "");
		exp = exp.replace("\n", "");
		return expFrom + exp;
	}
	/**
	 加密MD5

	 @param wk
	 @return
	 */
	public static String GenerMD5(bp.wf.Work wk) throws Exception {
		String s = null;
		for (Attr attr : wk.getEnMap().getAttrs())
		{
			switch (attr.getKey())
			{
				case WorkAttr.MD5:
				case WorkAttr.Rec:
				case GERptAttr.Title:
					// case GERptAttr.Emps:
				case GERptAttr.FK_Dept:
					//case GERptAttr.PRI:
				case GERptAttr.FID:
					continue;
				default:
					break;
			}

			Object tempVar = attr.getDefaultVal();
			String obj = tempVar instanceof String ? (String)tempVar : null;
			//if (obj == null)
			//    continue;
			if (obj != null && obj.contains("@"))
			{
				continue;
			}

			s += wk.GetValStrByKey(attr.getKey());
		}
		s += "ccflow";

		return GetMD5Hash(s);
	}
	/**
	 取得MD5加密串

	 @param input 源明文字符串
	 @return 密文字符串
	 */
	public static String GetMD5Hash(String input)
	{
		/*System.Security.Cryptography.MD5CryptoServiceProvider md5 = new System.Security.Cryptography.MD5CryptoServiceProvider();
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: byte[] bs = System.Text.Encoding.UTF8.GetBytes(input);
		byte[] bs = input.getBytes(java.nio.charset.StandardCharsets.UTF_8);
		bs = md5.ComputeHash(bs);
		StringBuilder s = new StringBuilder();
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: foreach (byte b in bs)
		for (byte b : bs)
		{
			s.append(String.format("%1$.2x", b).toLowerCase());
		}
		return s.toString();*/
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
			BigInteger number = new BigInteger(1, hashBytes);
			String hashString = number.toString(16);
			while (hashString.length() < 32) {
				hashString = "0" + hashString;
			}
			return hashString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	///#endregion 常用方法


	///#region 其他配置.
	public static String getFlowFileBill()
	{
		if(SystemConfig.isJarRun() == false)
			return SystemConfig.getPathOfDataUser() + "Bill/";
		return SystemConfig.getPhysicalPath() + "DataUser/Bill/";
	}

	/**
	 语言
	 */
	public static String Language = "CH";
	/**
	 是否启用共享任务池？
	 */
	public static boolean isEnableTaskPool()
	{
		return SystemConfig.GetValByKeyBoolen("IsEnableTaskPool", false);
	}
	/**
	 用户信息显示格式
	 */
	public static UserInfoShowModel getUserInfoShowModel()
	{
		return UserInfoShowModel.forValue(SystemConfig.GetValByKeyInt("UserInfoShowModel", 0));
	}
	/**
	 处理显示格式

	 @param no
	 @param name
	 @return 现实格式
	 */
	public static String DealUserInfoShowModel(String no, String name)
	{
		switch (bp.wf.Glo.getUserInfoShowModel())
		{
			case UserIDOnly:
				return no;
			case UserIDUserName:
				// return "(" + no + "," + name + ")";
				return no + "," + name;
			case UserNameOnly:
				//return "(" + name + ")";
				return name;
			default:
				throw new RuntimeException("@没有判断的格式类型.");
		}
	}
	/**
	 钉钉是否启用
	 */
	public static boolean isEnableDingDing()
	{
		//如果两个参数都不为空说明启用
		String corpid = SystemConfig.getDing_CorpID();
		String corpsecret = SystemConfig.getDing_CorpSecret();
		if (DataType.IsNullOrEmpty(corpid) || DataType.IsNullOrEmpty(corpsecret))
		{
			return false;
		}

		return true;
	}
	/**
	 微信是否启用
	 */
	public static boolean isEnableWeiXin()
	{
		//如果两个参数都不为空说明启用
		String corpid = SystemConfig.getWX_CorpID();
		String corpsecret = SystemConfig.getWX_AppSecret();
		if (DataType.IsNullOrEmpty(corpid) || DataType.IsNullOrEmpty(corpsecret))
		{
			return false;
		}

		return true;
	}
	/**
	 是否启用消息系统消息。
	 */
	public static boolean isEnableSysMessage()
	{
		return SystemConfig.GetValByKeyBoolen("IsEnableSysMessage", true);
	}
	/**
	 主机
	 */
	public static String getHostURL()
	{
		if (SystemConfig.isBSsystem())
		{
			/* 如果是BS 就要求 路径.*/
		}

		String baseUrl = SystemConfig.getAppSettings().get("HostURL").toString();
		if (DataType.IsNullOrEmpty(baseUrl) == true)
		{
			baseUrl = "http://127.0.0.1/";
		}

		if (!Objects.equals(baseUrl.substring(baseUrl.length() - 1), "/"))
		{
			baseUrl = baseUrl + "/";
		}
		return baseUrl;
	}

	///#endregion


	///#region 时间计算.
	/**
	 设置成工作时间

	 @param dt
	 @return
	 */
	public static Date SetToWorkTime(Date dt) throws Exception {
		if (bp.sys.GloVar.getHolidays().contains(DateUtils.format(dt, "MM-dd")))
		{
			dt = DateUtils.addDay(dt, 1);
			/*如果当前是节假日，就要从下一个有效期计算。*/
			while (true) {
				if (bp.sys.GloVar.getHolidays().contains(DateUtils.format(dt, "MM-dd")) == false) {
					{
						break;
					}
				}
				// 从下一个上班时间计算.
				dt = DataType.ParseSysDate2DateTime(DateUtils.format(dt, "yyyy-MM-dd") + " " + Glo.getAMFrom());
				return dt;
			}
		}

		int timeInt = Integer.parseInt(DateUtils.format(dt, "HHmm"));

		//判断是否在A区间, 如果是，就返回A区间的时间点.
		if (Glo.getAMFromInt() >= timeInt) {
			return DataType.ParseSysDate2DateTime(DateUtils.format(dt, "yyyy-MM-dd") + " " + Glo.getPMFrom());
		}

		// 判断是否在E区间, 如果是就返回第2天的上班时间点.
		if (Glo.getPMToInt() <= timeInt) {
			return DataType.ParseSysDate2DateTime(DateUtils.format(dt, "yyyy-MM-dd") + " " + Glo.getPMTo());
		}

		// 如果在午休时间点中间.
		if (Glo.getAMToInt() <= timeInt && Glo.getPMFromInt() > timeInt) {
			return DataType.ParseSysDate2DateTime(DateUtils.format(dt, "yyyy-MM-dd") + " " + Glo.getPMFrom());
		}
		return dt;
	}
	/**
	 在指定的日期上增加小时数。
	 1，扣除午休。
	 2，扣除节假日。

	 @param dt
	 @param hh
	 @param minutes
	 @return
	 */
	private static Date AddMinutes (Date dt,int hh, int minutes) throws Exception {
		if (1 == 1) {
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			c.add(Calendar.HOUR, hh);
			c.add(Calendar.MINUTE, minutes);
			return c.getTime();
		}

		//如果没有设置,就返回.
		if (minutes == 0 && hh == 0) {
			return dt;
		}

		//设置成工作时间.
		dt = SetToWorkTime(dt);

		//首先判断是否是在一天整的时间完成.
		if (minutes == Glo.getAMPMHours() * 60) {
			/*如果需要在一天完成*/
			dt = DataType.AddDays(dt, 1, TWay.Holiday);
			return dt;
		}

		//判断是否是AM.
		boolean isAM = false;
		int timeInt = Integer.parseInt(DateUtils.format(dt, "HHmm"));
		if (Glo.getAMToInt() > timeInt) {
			isAM = true;
		}


		///#region 如果是当天的情况.
		//如果规定的时间在 1天之内.
		if (minutes / 60 / Glo.getAMPMHours() < 1) {
			if (isAM == true) {
				/* 如果是中午, 中午到中午休息之间的时间. */

				long ts = DataType.ParseSysDateTime2DateTime(DateUtils.format(dt, "yyyy-MM-dd") + " " + Glo.getAMTo())
						.getTime() - dt.getTime();
				if (ts / (60 * 1000) >= minutes) {
					/* 如果剩余的分钟大于 要增加的分钟数，就是说+上分钟后，仍然在中午，就直接增加上这个分钟，让其返回。 */
					return DateUtils.addMinutes(dt, minutes);
				} else {
					// 求出到下班时间的分钟数。
					long myts = DataType
							.ParseSysDateTime2DateTime(DateUtils.format(dt, "yyyy-MM-dd") + " " + Glo.getAMTo())
							.getTime() - dt.getTime();

					// 扣除午休的时间.
					int leftMuit = (int) (myts / (60 * 1000) - Glo.getAMPMTimeSpan() * 60);
					if (leftMuit - minutes >= 0) {
						/* 说明还是在当天的时间内. */
						java.util.Date mydt = DataType
								.ParseSysDateTime2DateTime(DateUtils.format(dt, "yyyy-MM-dd") + " " + Glo.getPMTo());
						return DateUtils.addMinutes(mydt, (minutes - leftMuit));
					}

					// 说明要跨到第2天上去了.
					dt = DataType.AddDays(dt, 1, TWay.Holiday);
					// return Glo.AddMinutes(DateUtils.format(dt,"yyyy-MM-dd") +
					// " " + Glo.getAMFrom(), minutes - leftMuit);
				}

				// 把当前的时间加上去.
				dt = DateUtils.addMinutes(dt, minutes);

				// 判断是否是中午.
				boolean isInAM = false;
				timeInt = Integer.parseInt(DateUtils.format(dt, "HHmm"));
				if (Glo.getAMToInt() >= timeInt) {
					isInAM = true;
				}

				if (isInAM == true) {
					// 加上时间后仍然是中午就返回.
					return dt;
				}

				// 延迟一个午休时间.
				dt = DateUtils.addHours(dt, (int) Glo.getAMPMTimeSpan());

				// 判断时间点是否落入了E区间.
				timeInt = Integer.parseInt(DateUtils.format(dt, "HHmm"));
				if (Glo.getPMToInt() <= timeInt) {
					/* 如果落入了E区间. */

					// 求出来时间点到，下班之间的分钟数.
					long tsE = dt.getTime() - DataType
							.ParseSysDate2DateTime(DateUtils.format(dt, "yyyy-MM-dd") + " " + Glo.getPMTo()).getTime();

					// 从次日的上班时间计算+ 这个时间差.
					dt = DataType.ParseSysDate2DateTime(DateUtils.format(dt, "yyyy-MM-dd") + " " + Glo.getPMTo());
					return DateUtils.addMinutes(dt, (int) tsE / (60 * 1000));
				} else {
					/* 过了第2天的情况很少，就不考虑了. */
					return dt;
				}
			} else {
				// 如果是下午, 计算出来到下午下班还需多少分钟，与增加的分钟数据相比较.
				long ts = DataType.ParseSysDateTime2DateTime(DateUtils.format(dt, "yyyy-MM-dd") + " " + Glo.getPMTo())
						.getTime() - dt.getTime();
				if (ts / (60 * 1000) >= minutes) {
					// 如果剩余的分钟大于 要增加的分钟数，就直接增加上这个分钟，让其返回。
					return DateUtils.addMinutes(dt, minutes);
				} else {

					// 剩余的分钟数 = 总分钟数 - 今天下午剩余的分钟数.
					int leftMin = minutes - (int) ts / (60 * 1000);

					// 否则要计算到第2天上去了， 计算时间要从下一个有效的工作日上班时间开始.
					dt = DataType
							.AddDays(
									DataType.ParseSysDateTime2DateTime(
											DateUtils.format(dt, "yyyy-MM-dd") + " " + Glo.getAMFrom()),
									1, TWay.Holiday);
					// 递归调用,让其在次日的上班时间在增加，分钟数。
					return Glo.AddMinutes(dt, 0, leftMin);
				}

			}
		}

		///#endregion 如果是当天的情况.

		return dt;
	}
	/**
	 增加分钟数.

	 @param sysdt
	 @param minutes
	 @return
	 */
	public static Date AddMinutes (String sysdt,int minutes) throws Exception {
		Date dt = DataType.ParseSysDate2DateTime(sysdt);
		return AddMinutes(dt, 0, minutes);
	}
	/**
	 在指定的日期上增加n天n小时，并考虑节假日

	 @param specDT 指定的日期
	 @param day 天数
	 @param minutes 分钟数
	 @return 返回计算后的日期
	 */
	public static Date AddDayHoursSpan (String specDT,int day, int hh, int minutes, TWay tway) throws Exception {
		Date mydt = DataType.AddDays(specDT, day, tway);
		return Glo.AddMinutes(mydt, hh, minutes);
	}
	/**
	 在指定的日期上增加n天n小时，并考虑节假日

	 @param specDT 指定的日期
	 @param day 天数
	 @param minutes 分钟数
	 @return 返回计算后的日期
	 */
	public static Date AddDayHoursSpan (Date specDT,int day, int hh, int minutes, TWay tway) throws Exception {
		Date mydt = bp.da.DataType.AddDays(specDT, day, tway);
		mydt = AddMinutes(mydt, 0, minutes);
		return mydt;
	}

	///#endregion ssxxx.


	///#region 与考核相关.
	/**
	 当流程发送下去以后，就开始执行考核。

	 @param fl
	 @param nd
	 @param workid
	 @param fid
	 @param title
	 */

	public static void InitCH(Flow fl, Node nd, long workid, long fid, String title) throws Exception {
		InitCH(fl, nd, workid, fid, title, null);
	}

	public static void InitCH(Flow fl, Node nd, long workid, long fid, String title, GenerWorkerList gwl) throws Exception {
		InitCH2017(fl, nd, workid, fid, title, null, null, new Date(), gwl);
	}
	/**
	 执行考核

	 @param fl 流程
	 @param nd 节点
	 @param workid 工作ID
	 @param fid FID
	 @param title 标题
	 @param prvRDT 上一个时间点
	 @param sdt 应完成日期
	 @param dtNow 当前日期
	 */
	private static void InitCH2017(Flow fl, Node nd, long workid, long fid, String title, String prvRDT, String sdt, Date dtNow, GenerWorkerList gwl) throws Exception {

		// 开始节点不考核.
		if (nd.getItIsStartNode() || nd.getHisCHWay() == CHWay.None)
		{
			return;
		}

		//如果设置为0,则不考核.
		if (nd.getTimeLimit() == 0 && nd.getTimeLimitHH() == 0 && nd.getTimeLimitMM() == 0)
		{
			return;
		}

		if (dtNow == null)
		{
			dtNow = new Date();
		}


		///#region 求参与人员 todoEmps ，应完成日期 sdt ，与工作派发日期 prvRDT.
		//参与人员.
		String todoEmps = "";
		String dbstr = SystemConfig.getAppCenterDBVarStr();
		if (nd.getItIsEndNode() == true && gwl == null)
		{
			/* 如果是最后一个节点，可以使用这样的方式来求人员信息 , */


			///#region 求应完成日期，与参与的人集合.
			Paras ps = new Paras();
			switch (SystemConfig.getAppCenterDBType())
			{
				case MSSQL:
					ps.SQL = "SELECT TOP 1 SDTOfNode, TodoEmps FROM WF_GenerWorkFlow  WHERE WorkID=" + dbstr + "WorkID ";
					break;
				case Oracle:
				case KingBaseR3:
				case KingBaseR6:
					ps.SQL = "SELECT SDTOfNode, TodoEmps FROM WF_GenerWorkFlow  WHERE WorkID=" + dbstr + "WorkID  ";
					break;
				case MySQL:
					ps.SQL = "SELECT SDTOfNode, TodoEmps FROM WF_GenerWorkFlow  WHERE WorkID=" + dbstr + "WorkID  ";
					break;
				case PostgreSQL:
				case UX:
				case HGDB:
					ps.SQL = "SELECT SDTOfNode, TodoEmps FROM WF_GenerWorkFlow  WHERE WorkID=" + dbstr + "WorkID  ";
					break;
				default:
					throw new RuntimeException("err@没有判断的数据库类型.");
			}

			ps.Add("WorkID", workid);
			DataTable dt = DBAccess.RunSQLReturnTable(ps);
			if (dt.Rows.size() == 0)
			{
				return;
			}
			sdt = dt.Rows.get(0).getValue("SDTOfNode").toString(); //应完成日期.
			todoEmps = dt.Rows.get(0).getValue("TodoEmps").toString(); //参与人员.

			///#endregion 求应完成日期，与参与的人集合.


			///#region 求上一个节点的日期.
			dt = Dev2Interface.Flow_GetPreviousNodeTrack(workid, nd.getNodeID());
			if (dt.Rows.size() == 0)
			{
				return;
			}
			//上一个节点的活动日期.
			prvRDT = dt.Rows.get(0).getValue("RDT").toString();

			///#endregion
		}


		if (nd.getItIsEndNode() == false)
		{
			if (gwl == null)
			{
				gwl = new GenerWorkerList();
				gwl.Retrieve(GenerWorkerListAttr.WorkID, workid, GenerWorkerListAttr.FK_Node, nd.getNodeID(), GenerWorkerListAttr.FK_Emp, WebUser.getNo());
			}

			prvRDT = gwl.getRDT(); // dt.Rows.get(0).getValue("RDT").toString(); //上一个时间点的记录日期.
			sdt = gwl.getSDT(); //  dt.Rows.get(0).getValue("SDT").toString(); //应完成日期.
			todoEmps = WebUser.getNo() + "," + WebUser.getName() + ";";
		}

		///#endregion 求参与人员，应完成日期，与工作派发日期.


		///#region 求 preSender上一个发送人，preSenderText 发送人姓名
		String preSender = "";
		String preSenderText = "";
		DataTable dt_Sender = Dev2Interface.Flow_GetPreviousNodeTrack(workid, nd.getNodeID());
		if (dt_Sender.Rows.size() > 0)
		{
			preSender = dt_Sender.Rows.get(0).getValue("EmpFrom").toString();
			preSenderText = dt_Sender.Rows.get(0).getValue("EmpFromT").toString();
		}

		///#endregion


		///#region 初始化基础数据.
		CH ch = new CH();
		ch.setWorkID(workid);
		ch.setFID(fid);
		ch.setTitle(title);

		//记录当时设定的值.
		ch.setTimeLimit(nd.getTimeLimit());

		ch.setFK_NY(DateUtils.format(dtNow, "yyyy-MM"));

		ch.setDTFrom(prvRDT); //任务下达时间.
		ch.setDTTo(DateUtils.format(dtNow,"yyyy-MM-dd HH:mm:ss")); //时间到.

		ch.setSDT(sdt); //应该完成时间.

		ch.setFK_Flow(nd.getFlowNo()); //流程信息.
		ch.setFK_FlowT(nd.getFlowName());

		ch.setNodeID(nd.getNodeID()); //节点.
		ch.setFK_NodeT(nd.getName());

		ch.setFK_Dept(WebUser.getDeptNo()); //部门.
		ch.setFK_DeptT(WebUser.getDeptName());

		ch.setFK_Emp(WebUser.getNo()); //当事人.
		ch.setFK_EmpT(WebUser.getName());

		// 处理相关联的当事人.
		ch.setGroupEmpsNames(todoEmps);
		//上一步发送人
		ch.setSender(preSender);
		ch.setSenderT(preSenderText);
		//考核状态
		ch.setDTSWay(nd.getHisCHWay().getValue());

		//求参与人员数量.
		String[] strs = todoEmps.split("[;]", -1);
		ch.setGroupEmpsNum(strs.length - 1); //个数.

		//求参与人的ids.
		String empids = ",";
		for (String str : strs)
		{
			if (DataType.IsNullOrEmpty(str))
			{
				continue;
			}

			String[] mystr = str.split(",");
			empids += mystr[0] + ",";
		}
		ch.setGroupEmps(empids);

		// mypk.
		ch.setMyPK(nd.getNodeID()+ "_" + workid + "_" + fid + "_" + WebUser.getNo());

		///#endregion 初始化基础数据.


		///#region 求计算属性.
		//求出是第几个周.
		ch.setWeekNum(DataType.WeekOfYear(dtNow));

		// UseDays . 求出实际使用天数.
		Date dtFrom = DataType.ParseSysDate2DateTime(ch.getDTFrom());
		Date dtTo = DataType.ParseSysDate2DateTime(ch.getDTTo());

		long ts = dtTo.getTime() - dtFrom.getTime();
		ch.setUseDays(ts / 1000 / 60 / 60 / 24); // 用时，天数
		ch.setUseMinutes(ts / 1000 / 60); // 用时，分钟
		//int hour = ts.Hours;
		//ch.UseDays += ts.Hours / 8; //使用的天数.
		if (DataType.IsNullOrEmpty(ch.getSDT()) == false && ch.getSDT().equals("无") == false)
		{
			// OverDays . 求出 逾期天 数.
			Date sdtOfDT = DataType.ParseSysDate2DateTime(ch.getSDT());

			long myts = dtTo.getTime() - sdtOfDT.getTime();
			ch.setOverDays(myts / 1000 / 60 / 60 / 24); // 逾期的天数.
			ch.setOverMinutes(myts / 1000 / 60); // 逾期的分钟数
			if (sdtOfDT.compareTo(dtTo) >= 0)
			{
				/* 正常完成 */
				ch.setCHSta(CHSta.AnQi); //按期完成.
				ch.setPoints(0);
			}
			else
			{
				/*逾期完成.*/
				ch.setCHSta(CHSta.YuQi); //逾期完成.
				float sum = ch.getOverDays() * nd.getTCent();
				ch.setPoints((float) (Math.round(sum * 100)) / 100);
			}
		}
		else
		{
			/* 正常完成 */
			ch.setCHSta(CHSta.AnQi); //按期完成.
			ch.setPoints(0);
		}


		///#endregion 求计算属性.
		if (SystemConfig.getCCBPMRunModel() != CCBPMRunModel.Single)
		{
			ch.SetValByKey(CHAttr.OrgNo, WebUser.getOrgNo());
		}
		//执行保存.
		try
		{
			ch.DirectInsert();
		}
		catch (java.lang.Exception e)
		{
			if (ch.getIsExits() == true)
			{
				ch.Update();
			}
			else
			{
				//如果遇到退回的情况就可能涉及到主键重复的问题.
				ch.setMyPK(DBAccess.GenerGUID(0, null, null));
				ch.Insert();
			}
		}
	}
	/**
	 处理异常

	 @param errInfo
	 @param srcUrl
	 @param srcPage
	 @param hanlerPage
	 */
	public static void DealErrInfo(String errInfo, String srcUrl, String srcPage, String hanlerPage)
	{
		DealErrInfo(errInfo, srcUrl, srcPage, hanlerPage, "");
	}

	public static void DealErrInfo(String errInfo, String srcUrl, String srcPage)
	{
		DealErrInfo(errInfo, srcUrl, srcPage, "", "");
	}

	public static void DealErrInfo(String errInfo, String srcUrl)
	{
		DealErrInfo(errInfo, srcUrl, "", "", "");
	}

	public static void DealErrInfo(String errInfo, String srcUrl, String srcPage, String hanlerPage, String etc)
	{
		String title = "ccbpm错误:" + srcUrl;

		String msg = "ErrInfo:" + errInfo;
		msg += "\t\n url:" + srcUrl;
		msg += "\t\n srcPage:" + srcPage;
		msg += "\t\n hanlerPage:" + hanlerPage;
		msg += "\t\n etc:" + etc;

		Dev2Interface.Port_SendEmail("ccbpm@ccbpm.cn", title, msg);
	}

	/**
	 中午时间从
	 */
	public static String getAMFrom()
	{
		return SystemConfig.GetValByKey("AMFrom", "08:30");
	}
	/**
	 中午时间从
	 */
	public static int getAMFromInt()
	{
		return Integer.parseInt(Glo.getAMFrom().replace(":", ""));
	}
	/**
	 一天有效的工作小时数
	 是中午工作小时+下午工作小时.
	 */
	public static float getAMPMHours()
	{
		return SystemConfig.GetValByKeyFloat("AMPMHours", 8);
	}
	/**
	 中午间隔的小时数
	 */
	public static float getAMPMTimeSpan()
	{
		return SystemConfig.GetValByKeyFloat("AMPMTimeSpan", 1);
	}
	/**
	 中午时间到
	 */
	public static String getAMTo()
	{
		return SystemConfig.GetValByKey("AMTo", "11:30");
	}
	/**
	 中午时间到
	 */
	public static int getAMToInt()
	{
		return Integer.parseInt(Glo.getAMTo().replace(":", ""));
	}
	/**
	 下午时间从
	 */
	public static String getPMFrom()
	{
		return SystemConfig.GetValByKey("PMFrom", "13:30");
	}
	/**
	 到
	 */
	public static int getPMFromInt()
	{
		return Integer.parseInt(Glo.getPMFrom().replace(":", ""));
	}
	/**
	 到
	 */
	public static String getPMTo()
	{
		return SystemConfig.GetValByKey("PMTo", "17:30");
	}
	/**
	 到
	 */
	public static int getPMToInt()
	{
		return Integer.parseInt(Glo.getPMTo().replace(":", ""));
	}

	///#endregion 与考核相关.


	///#region 其他方法。

	/**
	 删除临时文件
	 */
	public static void DeleteTempFiles()
	{
		try
		{
			//删除目录.
			String temp = SystemConfig.getPathOfTemp();
			FileAccess.deletesFile(new File(temp));

			//创建目录.
			(new File(temp)).mkdirs();

			//删除pdf 目录.
			temp = SystemConfig.getPathOfDataUser() + "InstancePacketOfData/";
			File info = new File(temp);
			File[] dirs = info.listFiles();
			for (File dir : dirs)
			{
				if (dir.getName().indexOf("ND") == 0)
				{
					dir.delete();
				}
			}
		}
		catch (RuntimeException ex)
		{

		}
	}

	/**
	 复制表单权限-从一个节点到另一个节点.

	 @param fk_flow 流程编号
	 @param frmID 表单ID
	 @param currNodeID 当前节点
	 @param fromNodeID 从节点
	 */
	public static void CopyFrmSlnFromNodeToNode(String fk_flow, String frmID, int currNodeID, int fromNodeID) throws Exception {

		///#region 处理字段.
		//删除现有的.
		FrmFields frms = new FrmFields();
		frms.Delete(FrmFieldAttr.FK_Node, currNodeID, FrmFieldAttr.FrmID, frmID);

		//查询出来,指定的权限方案.
		frms.Retrieve(FrmFieldAttr.FK_Node, fromNodeID, FrmFieldAttr.FrmID, frmID, null);

		//开始复制.
		for (FrmField item : frms.ToJavaList())
		{
			item.setMyPK(frmID + "_" + fk_flow + "_" + currNodeID + "_" + item.getKeyOfEn());
			item.setNodeID(currNodeID);
			item.Insert(); // 插入数据库.
		}

		///#endregion 处理字段.

		//没有考虑到附件的权限 20161020 hzm

		///#region 附件权限

		FrmAttachments fas = new FrmAttachments();
		//删除现有节点的附件权限
		fas.Delete(FrmAttachmentAttr.FK_Node, currNodeID, FrmAttachmentAttr.FK_MapData, frmID);
		//查询出 现在表单上是否有附件的情况
		fas.Retrieve(FrmAttachmentAttr.FK_Node, fromNodeID, FrmAttachmentAttr.FK_MapData, frmID, null);

		//复制权限
		for (FrmAttachment fa : fas.ToJavaList())
		{
			fa.setMyPK(fa.getFrmID() + "_" + fa.getNoOfObj() + "_" + currNodeID);
			fa.setNodeID(currNodeID);
			fa.Insert();
		}

	}

	private static final String StrRegex = "-|;|,|/|(|)|[|]|}|{|%|@|*|!|'|`|~|#|$|^|&|.|?";
	private static final String StrKeyWord = "select|insert|delete|from|count(|drop table|update|truncate|asc(|mid(|char(|xp_cmdshell|exec master|netlocalgroup administrators|:|net user|\"|or|and";
	/**
	 检查KeyWord是否包涵特殊字符

	 @param KeyWord 需要检查的字符串
	 @return
	 */
	public static String CheckKeyWord(String KeyWord)
	{
		//特殊符号
		String[] strRegx = StrRegex.split("|");
		//特殊符号 的注入情况
		for (String key : strRegx)
		{
			if (KeyWord.indexOf(key) >= 0)
			{
				//替换掉特殊字符
				KeyWord = KeyWord.replace(key, "");
			}
		}
		return KeyWord;
	}
	/**
	 检查_sword是否包涵SQL关键字

	 @param _sWord 需要检查的字符串
	 @return 存在SQL注入关键字时返回 true，否则返回 false
	 */
	public static boolean CheckKeyWordInSql(String _sWord)
	{
		boolean result = false;
		//Sql注入de可能关键字
		String[] patten1 = StrKeyWord.split("[|]", -1);
		//Sql注入的可能关键字 的注入情况
		for (String sqlKey : patten1)
		{
			if (_sWord.indexOf(" " + sqlKey) >= 0 || _sWord.indexOf(sqlKey + " ") >= 0)
			{
				//只要存在一个可能出现Sql注入的参数,则直接退出
				result = true;
				break;
			}
		}
		return result;
	}

	///#endregion 其他方法。
	/**
	 * 获得ftp连接对象
	 *
	 * @throws Exception
	 */
	public static FtpUtil getFtpUtil() throws Exception {
		// 获取
		String ip = bp.sys.Glo.String_JieMi_FTP(SystemConfig.getFTPServerIP());

		String userNo = bp.sys.Glo.String_JieMi_FTP(SystemConfig.getFTPUserNo());
		String pass = bp.sys.Glo.String_JieMi_FTP(SystemConfig.getFTPUserPassword());
		String port=bp.sys.Glo.String_JieMi_FTP(String.valueOf(SystemConfig.getFTPServerPort()));

		if(DataType.IsNullOrEmpty(port)||port.equals("0")){
			port="21";
		}

		FtpUtil ftp = new FtpUtil(ip, Integer.parseInt(port), userNo, pass);
		return ftp;

		// return Platform.JFlow;
	}
	/**
	 * 获得ftp连接对象
	 *
	 * @throws Exception
	 */
	public static SftpUtil getSftpUtil() throws Exception {
		// 获取
		String ip = SystemConfig.getFTPServerIP();

		String userNo = SystemConfig.getFTPUserNo();
		String pass = bp.sys.base.Glo.String_JieMi_FTP(SystemConfig.getFTPUserPassword());

		SftpUtil ftp = new SftpUtil(ip, SystemConfig.getFTPServerPort(), userNo, pass);
		return ftp;

	}
	/**
	 * 操作Word文件
	 * param Wordpath word文件路径
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String,Object>> execWord(String Wordpath, String FrmModel, String FrmID, StringBuilder rtfAddr) throws Exception {
		XWPFDocument document=null;
		InputStream in = null;
		/**
		 * 经典表单 分组解析集合
		 * itme中Map 说明
		 * key:
		 *    ctrlTYpe:
		 *        分组类型   Dtl:从表   Ath：表格附件(多图片上传模式)   pt:普通分组
		 *        类型： String
		 *    ctrlInfo:
		 *        分组信息：
		 *        类型：Map=>key:
		 *             name:名称
		 *             keyOfEn:名称拼音
		 *    ctrlValue:
		 *        分组具体值（只有普通分组，表格附件 有值）
		 *        类型；list<Map<String,Object>
		 *        Map=>key:
		 *         name:名称（表格附件 对应的列名称）
		 *         KeyOfEn:名称拼音（表格附件 对应的列名称拼音）
		 *         MyDataType:数据类型： 1：字符串 （暂均为1）
		 */
		List<Map<String,Object>>list=new ArrayList<>();
		/**
		 * docx文件解析规则
		 *
		 * 1.xy表格
		 *   类型：主表字段
		 *   依据：第一行，第一列以”.XY”结尾
		 * 2..*
		 *   类型：表格单元格
		 *   描述：以.*开头的行数据，不需要处理，固定展示
		 * 3..X表格
		 *   类型：主表字段
		 *   依据：第一行，第一列以”.X”结尾
		 * 4..DTL
		 *    类型：从表类型
		 *    依据：第一行，第一列以”.DTL”结尾
		 * 5..T
		 *    类型：主表字段
		 *    依据：第一行，第一列以”.T”结尾
		 *         单元格不为空为字段，右侧空单元格录入
		 * 6.#PIC#
		 *    类型：表格附件（图片）
		 *    依据： 段落中包含#PIC#
		 * 7.遇到即为需要导入，一个顶级项目符号下有多个表格
		 */
		try {
			//获取docx解析对象
			in = new FileInputStream(Wordpath);
			document = new XWPFDocument(in);
			//经典表单
			if(FrmModel.equals("0")){
				queryWord2MapDataListForJd(document,list,FrmID);
//				System.out.println("最终数据：");
//				System.out.println(list);
			}
			saveRtf(document,rtfAddr,FrmID);

		} catch (Exception e) {
//			System.out.println("已存储：");
//			System.out.println(list);
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			if (in != null) {
				in.close();
			}
		}
		return list;
	}

	private void saveRtf(XWPFDocument document,StringBuilder rtfAddr,String FrmID) throws IOException {
		//创建临时文件
		String fileRtf = SystemConfig.getPathOfDataUser() +"TS.CCBill.MethodPrintRTF/" ;
//	System.out.println("rtf路径:"+fileRtf);
		File f = new File(fileRtf);
		if(f.isDirectory() == false){
			f.mkdirs();
		}
		fileRtf=fileRtf+ FrmID + ".rtf";
		//存在文件则删除
		if ((new File(fileRtf)).isFile() == true) {
			(new File(fileRtf)).delete();
		}
		rtfAddr.append(fileRtf);

		//保存为docx
		String pdocx = SystemConfig.getPathOfDataUser() +"Bill/WordRtf/" ;
		File f2 = new File(pdocx);
		if(f2.isDirectory() == false){
			f2.mkdirs();
		}
		pdocx=pdocx+ FrmID + ".docx";
		//存在文件则删除
		if ((new File(pdocx)).isFile() == true) {
			(new File(pdocx)).delete();
		}
		FileOutputStream outStream = new FileOutputStream(new File(pdocx));
		document.write(outStream);
		outStream.close();
		document.close();

		//保存rtf
		InputStream docxInputStream = new FileInputStream(pdocx);
		OutputStream outputStream = new FileOutputStream(fileRtf);
		IConverter converter = LocalConverter.builder().build();
		converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.RTF).execute();
		outputStream.close();
		docxInputStream.close();
		//删除临时文件
//	(new File(pdocx)).delete();
	}
	/**
	 * 解析word对象获取经典表单内容
	 * @param document
	 */
	private void queryWord2MapDataListForJd(XWPFDocument document,List<Map<String,Object>>list,String FrmID) throws Exception {
		Map<String,String> py=new HashedMap();
		py.put("count","0");
		Map<String,String>fz=new HashedMap();
		fz.put("name","");
		List<IBodyElement> bodyElements =  document.getBodyElements();
		for(IBodyElement bodyElement:bodyElements){
			if(bodyElement instanceof XWPFParagraph){
//				System.out.println("是段落;"+((XWPFParagraph) bodyElement).getText());
				execPARAGRAPH((XWPFParagraph) bodyElement,fz,py,list, FrmID);
			}else if(bodyElement instanceof XWPFTable){
//				System.out.println("\n是表格：");
				execTABLE((XWPFTable) bodyElement,fz,py,list,FrmID);
			}
		}
	}
	//段落解析
	private void execPARAGRAPH(XWPFParagraph p,Map<String,String> fz,Map<String,String> py,List<Map<String,Object>>list,String FrmID) throws InterruptedException {
//        boolean hasPic=hasPic(p);
		BigInteger numlvl= p.getNumIlvl();
		String numIDStr= String.valueOf(p.getNumID());
		String name=p.getText();
//		System.out.println(hasPic);
//		System.out.println(numlvl);
//		System.out.println(numIDStr);
		//有numID=1且level=0 是新分组
		if(numlvl!=null && numlvl.intValue()==0){
//			System.out.println("是分组;"+name);
			fz.put("name",name);
		}else if(name.contains("#PIC#")){
//			System.out.println("是图片;");
			for(int i=0;i<p.getRuns().size();i++){
				XWPFRun run=p.getRuns().get(i);
				String text=run.text();
				if(text.contains("#PIC#")){
					p.removeRun(i);
					XWPFRun runnew=p.createRun();
					addNewFz(fz.get("name"),"Ath",py,list,FrmID);
					String txt=queryLastFz(list);
					String ntxt=queryAddInfo(txt,txt,"Ath");
					runnew.setText(ntxt);
				}
			}
		}
	}

	//删除图片
	private void delPic(XWPFParagraph p){
		// 获取段落中所有内容
		List<XWPFRun> runs = p.getRuns();
		for (int i=0;i<runs.size();i++) {
			p.removeRun(i);
		}
	}

	//判断段落中是否有图片
	private boolean hasPic(XWPFParagraph p){
		// 获取段落中所有内容
		List<XWPFRun> runs = p.getRuns();
		for (XWPFRun run : runs) {
			// 判断当前段落是否图片
			List<XWPFPicture> pictures = run.getEmbeddedPictures();
//			System.out.println("图片大小："+pictures.size());
			if(pictures!=null && pictures.size()>0){
				return true;
			}
		}
		return  false;
	}
	//表格处理
	private void execTABLE(XWPFTable t,Map<String,String> fz,Map<String,String> py,List<Map<String,Object>>list,String FrmID) throws InterruptedException {
		List<XWPFTableRow> rows = t.getRows();
		//第一行的标题
		List<String> firstTitel=new ArrayList<>();
		XWPFTableCell cell=rows.get(0).getCell(0);
		String text=cell.getText();
		int xyType=xyType(rows);
		queryFirstTitle(rows,firstTitel,xyType);

//		System.out.println("第一行标题");
//		System.out.println(firstTitel);
		//是否粗体
//		boolean isBold=xwpfRun.isBold();
		//是否有事做
		boolean isDo=false;
		if(text.toUpperCase().endsWith(".XY")){
			isDo=true;
			clear1CellTag(isDo,cell);
//			System.out.println("是.XY;");
			addNewFz(fz.get("name"),"pt",py,list,FrmID);
			execXYTable(rows,firstTitel,py,list,fz,xyType);
			addInfoByXYTable_rtf(rows,list,xyType);
		}else if(text.toUpperCase().endsWith(".X")){
			isDo=true;
			clear1CellTag(isDo,cell);
//			System.out.println("是.X;");
			addNewFz(fz.get("name"),"pt",py,list,FrmID);
			execXTable(rows,firstTitel,py,list,fz);
			addInfoByXTable_rtf(rows,list);
		}else if(text.toUpperCase().endsWith(".DTL")){
			isDo=true;
			clear1CellTag(isDo,cell);
//			System.out.println("是Dtl;");
			addNewFz(fz.get("name"),"Dtl",py,list,FrmID);
			execDtlTable(rows,firstTitel,py,list,fz);
			addInfoByDtlTable_rtf(rows,list);
		}else  if(text.toUpperCase().endsWith(".T")){
			isDo=true;
			clear1CellTag(isDo,cell);
//			System.out.println("是pt;");
			addNewFz(fz.get("name"),"pt",py,list,FrmID);
			execOtherTable(rows,firstTitel,py,list,fz);
			addInfoByOtherTable_rtf(rows,list);
		}
		clearNowFm(isDo,fz);

	}

	/**
	 * 判断 XY类型的具体分类
	 *  0：x,y各一行数据)
	 *  1：x轴2行数据，y轴一列数据
	 *  2: x轴一行数据，y轴2列数据
	 * @param rows
	 * @return
	 */
	private int xyType(List<XWPFTableRow> rows ){
		XWPFTableRow row1=rows.get(0);
		XWPFTableRow row2=rows.size()>1?rows.get(1):null;
		XWPFTableRow row3=rows.size()>2?rows.get(2):null;
		if(row3!=null && row3.getTableCells().size()>1 && !DataType.IsNullOrEmpty(row3.getTableCells().get(1).getText())){
			return 2;
		}
		if(row2!=null && row2.getTableCells().size()>2 && !DataType.IsNullOrEmpty(row2.getTableCells().get(2).getText())){
			return 1;
		}
		return 0;
	}
	//处理第一个单元格的标志
	private void clear1CellTag(boolean isDo,XWPFTableCell cell){
		if(!isDo){
			return ;
		}
		String text=cell.getText();
		String ctext=text.substring(text.lastIndexOf("."));
		XWPFParagraph p = cell.getParagraphArray(0 );
		for(int i=0;i<p.getRuns().size();i++){
			XWPFRun run=p.getRuns().get(i);
			String rtext=run.text();
			if(rtext.contains(ctext)){
				p.removeRun(i);
			}
		}
	}
	//xy表格处理
	private void execXYTable(List<XWPFTableRow> rows,List<String>  fm,Map<String,String> py,List<Map<String,Object>>list,Map<String,String> fz,int xyType) throws InterruptedException {
		//从第几行开始处理
		int srow=1;
		//从第几列开始处理
		int scel=0;
		if(xyType==1){
			srow=2;
		}
		if(xyType==2){
			scel=1;
		}
		//从第二行开始
		for(XWPFTableRow row:rows.subList(srow, rows.size())){
			String text=row.getCell(scel).getText();
			String text1="";
			if(xyType==2){
				//获取第一列信息
				text1=row.getCell(0).getText();
				if(!DataType.IsNullOrEmpty(text1)){
					text1+="/";
				}
			}
			if (DataType.IsNullOrEmpty(text) || text.startsWith(".*")) {
				continue;
			}
			//排除第一列第一个标题
			for(String str:fm.subList(1,fm.size())){
				String name=text1+text+"_"+str;
				addData(name,py,list,fz);
			}
		}
	}

	//x表格处理
	private void execXTable(List<XWPFTableRow> rows,List<String>  fm,Map<String,String> py,List<Map<String,Object>>list,Map<String,String> fz) throws InterruptedException {
		for(String str:fm){
			addData(str,py,list,fz);
		}
	}
	//从表表格处理
	private void execDtlTable(List<XWPFTableRow> rows,List<String>  fm,Map<String,String> py,List<Map<String,Object>>list,Map<String,String> fz) throws InterruptedException {
		for(String str:fm){
			addData(str,py,list,fz);
		}
	}
	//普通表格处理
	private void execOtherTable(List<XWPFTableRow> rows,List<String>  fm,Map<String,String> py,List<Map<String,Object>>list,Map<String,String> fz) throws InterruptedException {
		for(XWPFTableRow row:rows){
			for(int i=0;i<row.getTableCells().size();i++){
				XWPFTableCell cell=row.getTableCells().get(i);
				String text=cell.getText();
				if(!DataType.IsNullOrEmpty(text) && !text.startsWith(".*")){
					addData(text,py,list,fz);
				}
			}

		}

	}
	//当前分组value添加完毕后，清除分组名称，
	private void clearNowFm(boolean isDo,Map<String,String> fz){
//		if(isDo){
//			fz.remove("name");
//		}
	}
	//获取表格第一行数据
	private void queryFirstTitle(List<XWPFTableRow> rows,List<String>  fm,int xyType){
		int startIndex=0;
		XWPFTableRow row1=rows.get(0);
		XWPFTableRow row2=rows.size()>1?rows.get(1):null;
		//第一行的所有单元格
		List<XWPFTableCell> cells1 = row1.getTableCells();
		int spanindex=0;
		for(int i=0;i<cells1.size();i++){
			XWPFTableCell cell=cells1.get(i);
			String text=cell.getText();
			CTTc cttc = cell.getCTTc();
			CTTcPr cpr=cttc.getTcPr();
			//占据的单元格梳理
			BigInteger gspan=cpr.getGridSpan()==null?BigInteger.valueOf(1l):cpr.getGridSpan().getVal();
			if(gspan.intValue()==1 || xyType==2){
				fm.add(text.contains(".")?text.substring(0,text.lastIndexOf(".")):text);
			}else if(gspan.intValue()>1 && row1.getTableCells().size()!=row2.getTableCells().size()){ //从第二行中取值
				List<XWPFTableCell> cells2 = row2.getTableCells().subList(spanindex,spanindex+gspan.intValue());
				for (XWPFTableCell cell2 : cells2) {
					String text2=cell2.getText();
					fm.add(text+"/"+text2);
				}
			}
			spanindex=spanindex+gspan.intValue();
		}

	}

	//添加新分组
	private void addNewFz(String name,String ctrlTYpe, Map<String,String> py,List<Map<String,Object>>list,String FrmID) throws InterruptedException {
//		System.out.println("添加新分组:"+name);
		Map<String,Object> km=new HashedMap();
		km.put("ctrlTYpe",ctrlTYpe);
		Map<String,Object> vm=new HashedMap();
		vm.put("name",queryPy("",name,false,py));
		String rname=FrmID+ctrlTYpe+name;
		String KeyOfEn=queryPy(rname,"",true,py);
		vm.put("KeyOfEn",KeyOfEn);
		km.put("ctrlInfo",vm);
		km.put("ctrlValue",new ArrayList<>());
		list.add(km);
	}

	/**
	 *  获取拼音还是名称
	 * @param pyName  需要获取汉字的拼音
	 * @param labName 需要获取的名称重复+1
	 * @param isPy  是否获取拼音
	 * @param py
	 * @return
	 * @throws InterruptedException
	 */
	private String queryPy(String pyName,String labName,boolean isPy, Map<String,String> py) throws InterruptedException {
		String rstr="";
		if(isPy){
			String english="[a-zA-Z]";
			String numlish="[0-9]";
			String pinyinJX = CCFormAPI.ParseStringToPinyinField(pyName,false).toLowerCase();
			String pystr="";
			for (int i = 0; i < pinyinJX.length(); i++) {
				char c = pinyinJX.charAt(i);
				//是字母或数字
				if(Character.toString(c).matches(english) || Character.toString(c).matches(numlish)) {
					pystr=pystr+Character.toString(c);
				}
			}
			pinyinJX=pystr;
			int count= Integer.parseInt(py.get("count"))+1;
			if(py.containsKey(pinyinJX)){
				pinyinJX=pinyinJX+count;
			}
			py.put(pinyinJX,"");
			py.put("count",count+"");
			rstr=pinyinJX;
		}else{
			int ct=0;
			if(py.containsKey(labName)){
				ct= Integer.parseInt(py.get(labName).toString())+1;
				rstr=labName+ct;
			}else{
				rstr=labName;
			}
			py.put(labName,ct+"");
		}
		return rstr;
	}
	//添加分组值
	private void addData(String name,Map<String,String> py,List<Map<String,Object>>list,Map<String,String> fz) throws InterruptedException {
//		System.out.println("添加分组值:"+name);
		if(!fz.containsKey("name")){
//			System.out.println("无分组name:");
			return;
		}
		Map<String,Object> lm=list.get(list.size()-1);
		List<Map<String,Object>>klist= (List<Map<String, Object>>) lm.get("ctrlValue");
		Map<String,Object> vm=new HashedMap();
		vm.put("name",name);
		String KeyOfEn=queryPy(name,"",true,py);
		vm.put("KeyOfEn",KeyOfEn);
		vm.put("MyDataType","1");
		klist.add(vm);
	}
	//获取最后一个分组信息
	private String queryLastFz(List<Map<String,Object>>list){
		Map<String,Object>lm= (Map<String, Object>) list.get(list.size()-1).get("ctrlInfo");
		String rtxt=lm.get("KeyOfEn").toString();
		return rtxt;
	}
	//生成信息
	private String queryAddInfo(String text,String parentKeyOfEn,String ctrlTYpe){
		StringBuilder rtxt=new StringBuilder();
		if(ctrlTYpe.equals("pt")){
			rtxt.append("<").append(text).append(">");
		}else if(ctrlTYpe.equals("Dtl")){
			rtxt.append("<").append(parentKeyOfEn).append(".").append(text).append(">");
		}else if(ctrlTYpe.equals("Ath")){
			rtxt.append("<Ath.").append(text).append(".ImgAth>");
		}
		return rtxt.toString();
	}

	//生成普通1,2表格的信息
	private void addInfoByOtherTable_rtf(List<XWPFTableRow> rows,List<Map<String,Object>>list){
		List<Map<String,Object>>vlist= (List<Map<String, Object>>) list.get(list.size()-1).get("ctrlValue");
		int ids=0;
		String txt="";
		for(XWPFTableRow row:rows){
			for (XWPFTableCell cell: row.getTableCells()) {
				String text=cell.getText();
				if(!DataType.IsNullOrEmpty(text) && !text.startsWith(".*")){
					txt=((Map<String,Object>)vlist.get(ids)).get("KeyOfEn").toString();
					ids++;
				}else if(!txt.equals("")){
					XWPFParagraph cellPara =cell.getParagraphArray(0);
					XWPFRun xWPFRun= cellPara.createRun();
					xWPFRun.setFontFamily("宋体");
					String ntxt=queryAddInfo(txt,txt,"pt");
					xWPFRun.setText(ntxt);
					txt="";
				}
			}
		}
	}
	//生成XY表格的信息
	private void addInfoByXYTable_rtf(List<XWPFTableRow> rows,List<Map<String,Object>>list,int xyType){
		List<Map<String,Object>>vlist= (List<Map<String, Object>>) list.get(list.size()-1).get("ctrlValue");
		//从第几行开始处理
		int srow=1;
		//从第几列开始处理
		int scel=0;
		if(xyType==1){
			srow=2;
		}
		if(xyType==2){
			scel=1;
		}
		int ids=0;
		//从第二行开始
		for(XWPFTableRow row:rows.subList(srow, rows.size())){
			String text=row.getCell(scel).getText();
			if (DataType.IsNullOrEmpty(text) || text.startsWith(".*")) {
				continue;
			}
			//根据xyType判断从第几列开始
			for (XWPFTableCell cell: row.getTableCells().subList(scel+1,row.getTableCells().size())) {
				XWPFParagraph cellPara =cell.getParagraphArray(0);
				XWPFRun xWPFRun= cellPara.createRun();
				xWPFRun.setFontFamily("宋体");
				String txt=((Map<String,Object>)vlist.get(ids)).get("KeyOfEn").toString();
				String ntxt=queryAddInfo(txt,txt,"pt");
				xWPFRun.setText(ntxt);
				ids++;
			}
		}
	}
	//生成X表格的信息
	private void addInfoByXTable_rtf(List<XWPFTableRow> rows,List<Map<String,Object>>list){
		List<Map<String,Object>>vlist= (List<Map<String, Object>>) list.get(list.size()-1).get("ctrlValue");
		int ids=0;
		XWPFTableRow row=rows.get(rows.size()-1);
		for (XWPFTableCell cell: row.getTableCells()) {
			XWPFParagraph cellPara =cell.getParagraphArray(0);
			XWPFRun xWPFRun= cellPara.createRun();
			xWPFRun.setFontFamily("宋体");
			String txt=((Map<String,Object>)vlist.get(ids)).get("KeyOfEn").toString();
			String ntxt=queryAddInfo(txt,txt,"pt");
			xWPFRun.setText(ntxt);
			ids++;
		}

	}
	//生成Dtl表格的信息
	private void addInfoByDtlTable_rtf(List<XWPFTableRow> rows,List<Map<String,Object>>list){
		List<Map<String,Object>>vlist= (List<Map<String, Object>>) list.get(list.size()-1).get("ctrlValue");
		Map<String,Object> ctrlInfo=(Map<String, Object>) list.get(list.size()-1).get("ctrlInfo");
		String parentKeyOfEn=ctrlInfo.get("KeyOfEn").toString();
		int ids=0;
		XWPFTableRow row=rows.get(rows.size()-1);
		for (XWPFTableCell cell: row.getTableCells()) {
			XWPFParagraph cellPara =cell.getParagraphArray(0);
			XWPFRun xWPFRun= cellPara.createRun();
			xWPFRun.setFontFamily("宋体");
			String txt=((Map<String,Object>)vlist.get(ids)).get("KeyOfEn").toString();
			String ntxt=queryAddInfo(txt,parentKeyOfEn,"Dtl");
			xWPFRun.setText(ntxt);
			ids++;
		}
	}
}
