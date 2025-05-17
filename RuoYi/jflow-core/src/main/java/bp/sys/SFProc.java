package bp.sys;

import bp.da.AtPara;
import bp.da.DataTable;
import bp.da.DataType;
import bp.da.FieldCaseModel;
import bp.difference.ContextHolderUtils;
import bp.difference.SystemConfig;
import bp.en.EntityNoName;
import bp.en.Map;
import bp.en.OverrideFile;
import bp.en.UAC;
import bp.web.WebUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Hashtable;

/**
 用户自定义表
*/
public class SFProc extends EntityNoName
{
	public final String getParamAlia()  {

		return this.GetValStringByKey("ParamAlia");
	}
	public final String getRequestMethod()  {

		return this.GetValStringByKey("RequestMethod");
	}
	public final String getHeaderDoc()  {

		return this.GetValStringByKey("HeaderDoc");
	}
	public final String getPostDoc()  {

		return this.GetValStringByKey("PostDoc");
	}
	public final String getFK_SFDBSrc()  {

		return this.GetValStringByKey("FK_SFDBSrc");
	}
	public final String getSelectStatement()  {

		return this.GetValStringByKey("SelectStatement");
	}
	///#region 数据源属性.
	/**
	 获得外部数据表
	*/
	public final DataTable GenerHisDataTable() throws Exception {
		return GenerHisDataTable(null);
	}

	public final DataTable GenerHisDataTable(Hashtable ht) throws Exception {
		//创建数据源.
		SFDBSrc src = new SFDBSrc(this.getFK_SFDBSrc());

		///#region WebApi接口
		if (src.getDBSrcType().equals("WebApi") == true)
		{
			//执行POST
			String post = this.GetValStringByKey("RequestMethod");
			String postData = bp.tools.HttpClientUtil.HttpPostConnect_Data(this.getFK_SFDBSrc(), this.getSelectStatement(), ht, post);

			String jsonNode = this.GetValStringByKey("JsonNode"); //json的节点.
			JSONArray jArr = new JSONArray();
			try
			{
				JSONObject json = JSONObject.fromObject(postData);
				if (!DataType.IsNullOrEmpty(jsonNode))
				{
					jArr = JSONArray.fromObject(json.get(jsonNode).toString());
				}
				else
				{
					jArr = JSONArray.fromObject(postData);
				}
			}
			catch (RuntimeException ex)
			{
				throw new RuntimeException("err@转化JSON出现错误Data=【" + postData + "】" + ex.getMessage());
			}
			return bp.tools.Json.ToDataTable(jArr.toString());
		}
		///#endregion WebApi接口

		///#region SQL接口
		String runObj = this.getSelectStatement();
		if (DataType.IsNullOrEmpty(runObj))
		{
			throw new RuntimeException("@外键类型SQL配置错误," + this.getNo() + " " + this.getName() + " 是一个(SQL)类型(" + this.GetValStrByKey("SrcType") + ")，但是没有配置sql.");
		}

		if (runObj == null)
		{
			runObj = "";
		}
		runObj = runObj.replace("~~", "\"");
		runObj = runObj.replace("~", "'");
		runObj = runObj.replace("/#", "+"); //为什么？
		runObj = runObj.replace("/$", "-"); //为什么？
		if (runObj.contains("@WebUser.No"))
		{
			runObj = runObj.replace("@WebUser.No", WebUser.getNo());
		}

		if (runObj.contains("@WebUser.Name"))
		{
			runObj = runObj.replace("@WebUser.Name", WebUser.getName());
		}

		if (runObj.contains("@WebUser.FK_DeptName"))
		{
			runObj = runObj.replace("@WebUser.FK_DeptName", WebUser.getDeptName());
		}

		if (runObj.contains("@WebUser.FK_Dept"))
		{
			runObj = runObj.replace("@WebUser.FK_Dept", WebUser.getDeptNo());
		}

		if (runObj.contains("@WebUser.DeptNo"))
			runObj = runObj.replace("@WebUser.DeptNo", WebUser.getDeptNo());

		if (runObj.contains("@") == true && ht != null)
		{
			for (Object key : ht.keySet())
			{
				//值为空或者null不替换
				if (ht.get(key) == null || ht.get(key).equals("") == true)
				{
					continue;
				}

				if (runObj.contains("@" + key))
				{
					runObj = runObj.replace("@" + key, ht.get(key).toString());
				}
				//不包含@则返回SQL语句
				if (runObj.contains("@") == false)
				{
					break;
				}
			}
		}
		if (runObj.contains("@") && SystemConfig.isBSsystem() == true)
		{
			/*如果是bs*/
			for (String key : ContextHolderUtils.getRequest().getParameterMap().keySet())
			{
				if (DataType.IsNullOrEmpty(key))
				{
					continue;
				}
				runObj = runObj.replace("@" + key, ContextHolderUtils.getRequest().getParameter(key));
			}
		}
		if (runObj.contains("@") == true)
		{
			throw new RuntimeException("@外键类型SQL错误," + runObj + "部分过程条件没有被替换.");
		}
		DataTable dt = null;
		try
		{
			dt = src.RunSQLReturnTable(runObj);
		}
		catch (RuntimeException ex)
		{
			throw new RuntimeException("err@获得SFProcedure(" + this.getNo() + "," + this.getName() + ")出现错误:SQL[" + runObj + "],数据库异常信息:" + ex.getMessage());
		}
		if (SystemConfig.getAppCenterDBFieldCaseModel() != FieldCaseModel.None)
		{
			dt.Columns.get(0).ColumnName = "No";
			dt.Columns.get(1).ColumnName = "Name";
			if (dt.Columns.size()== 3)
			{
				dt.Columns.get(2).ColumnName = "ParentNo";
			}
		}
		return dt;

		///#endregion SQL接口
	}
	///#endregion

	///#region 构造方法
	@Override
	public UAC getHisUAC()
	{
		UAC uac = new UAC();
		uac.Readonly();
		return uac;
	}
	/**
	 用户自定义表
	*/
	public SFProc()
	{
	}
	public SFProc(String no) throws Exception {
		this.setNo(no);
		this.Retrieve();
	}
	/**
	 EnMap
	*/
	@Override
	public Map getEnMap() {
		if (this.get_enMap() != null)
		{
			return this.get_enMap();
		}
		Map map = new Map("Sys_SFProc", "过程");

		map.AddTBStringPK(SFTableAttr.No, null, "表英文名称", true, false, 1, 200, 20);
		map.AddTBString(SFTableAttr.Name, null, "表中文名称", true, false, 0, 200, 20);

		map.AddDDLEntities(SFTableAttr.FK_SFDBSrc, "local", "数据源", new bp.sys.SFDBSrcs(), true);

		map.AddDDLStringEnum("RequestMethod", "Get", "请求模式", "@Get=Get@POST=POST", true);


		map.AddTBString("ConnString", null, "Host", true, false, 0, 200, 150, true);
		map.AddTBStringDoc("SelectStatement", "", "表达式", true, false, true);

		// map.AddDDLSysEnum("IsPara", 0, "参数个数", true, true, "IsPara", "@0=无参数@1=有参数");

		map.AddTBString("HeaderDoc", null, "头部表达式", true, false, 0, 1000, 600, true);
		map.AddTBString("PostDoc", null, "Body表达式", true, false, 0, 1000, 600, true);
		map.AddTBString("ParamAlia", null, "参数别名", true, false, 0, 1000, 600, true);


		map.AddTBString("ExpNote", null, "表达式说明", true, false, 0, 1000, 600, true);
		map.AddTBStringDoc("ParamAlia", "", "参数别名", true, false, true);


		map.AddGroupAttr("POST方式");
		//map.AddDDLStringEnum("ParaModel", "", "参数方式", "@0=主表参数@1=主从表参数@2=其他格式", true, "", false);
		map.AddTBString("TestParas", null, "测试用例", true, false, 0, 500, 600, true);
		map.AddTBString("MsOfOK", null, "执行成功提示", true, false, 0, 500, 600, true);
		map.AddTBString("MsOfErr", null, "执行失败提示", true, false, 0, 500, 600, true);

		// 创建信息
		map.AddGroupAttr("创建信息");
		map.AddTBString("Remark", null, "备注", true, false, 0, 100, 20, true);
		map.AddTBDateTime("RDT", null, "创建日期", true, true);
		map.AddTBString("OrgNo", null, "组织编号", true, true, 0, 100, 20);
		map.AddTBAtParas();
		//查找.
		map.AddSearchAttr(SFTableAttr.FK_SFDBSrc);

		this.set_enMap(map);
		return this.get_enMap();
	}

	public final String TS_WebApi_Test() throws Exception {
		try {
			SFDBSrc src = new SFDBSrc(this.getFK_SFDBSrc());

			String testParas = this.GetValStringByKey("TestParas"); //获得测试信息.
			if (src.getDBSrcType().equals("WebApi") == true)
			{
				AtPara para = new AtPara(testParas);
				ResultObj objDB = bp.en.OverrideFile.Data_WebApi(para.getHisHT(), this.getRequestMethod(), this.getFK_SFDBSrc(),
						this.getSelectStatement(), this.getParamAlia(), this.getPostDoc(), this.getHeaderDoc());
				return objDB.ToSpecString();
			}
			AtPara at = new AtPara(testParas);
			String sql = this.getSelectStatement();
			sql = Glo.DealExp(sql, at.getHisHT());
			int i = src.RunSQL(sql);

			ResultObj obj = new ResultObj();
			obj.data = "";
			obj.code = 200;
			obj.message = "执行成功:影响[" + i + "]行数据";
			return obj.ToSpecString();
		}catch(Exception ex)
		{
			ResultObj obj = new ResultObj();
			obj.data = ex.getMessage();
			obj.code = 500;
			obj.message = "错误";
			return obj.ToSpecString();
		}
	}

	/**
	 执行SQL

	 @param ht 参数
	 @return 返回执行结果
	*/
	public final String ExecSQL(Hashtable ht, SFDBSrc src) throws Exception {
		String sql = this.getSelectStatement();
		try
		{
			sql = bp.difference.Glo.DealExp(sql, ht); //处理sql.
			String remark = this.GetValStringByKey("ParamAlia");
			if(sql.contains("@")==true && DataType.IsNullOrEmpty(remark)==false){
				//别名的替换
				AtPara atpara = new AtPara(remark);
				for(String key : atpara.getHisHT().keySet())
				{
					String alias = atpara.GetValStrByKey(key);
					String[] strs = alias.split(",");
					for(String str: strs)
					{
						if (DataType.IsNullOrEmpty(str) == true)
							continue;
						sql = sql.replace("@" + key, ht.get(key).toString());
					}
				}
			}

			int num = src.RunSQL(sql);
			if (num == 0)
			{
				throw new RuntimeException( "执行失败:" + this.GetValStrByKey("MsgOfErr") + "，返回结果为0");
			}
			return "执行成功:" + this.GetValStrByKey("MsgOfOK") + "，返回结果为" + num;
		}
		catch (RuntimeException ex)
		{
			throw new RuntimeException("执行错误:" + this.GetValStrByKey("MsgOfErr") + "，请检查配置的SQL是否正确:[" + sql + "]");
		}
	}
	public final String Exec(Hashtable ht) throws Exception {
		SFDBSrc src = new SFDBSrc();
		src.setNo(this.getFK_SFDBSrc());
		src.RetrieveFromDBSources();

		if (src.getDBSrcType().equals("WebApi") == true)
		{
			return ExecWebApi(ht);
		}
		return ExecSQL(ht, src);
	}

	public final String ExecWebApi(Hashtable ht) throws Exception {
		try
		{
			ResultObj obj = bp.en.OverrideFile.Data_WebApi(ht, this.getRequestMethod(), this.getFK_SFDBSrc(),
					this.getSelectStatement(), this.getParamAlia(), this.getPostDoc(), this.getHeaderDoc());
			return "" + this.GetValByKey("MsgOfOK") + " -:" + obj.data;
		}
		catch (RuntimeException ex)
		{
			return "err@[" + this.GetValByKey("MsgOfErr") + "]:失败信息" + ex.getMessage();
		}
	}
	/**
	 检查是否有依赖的引用？

	 @return
	*/
	public final String IsCanDelete() throws Exception {
		MapAttrs mattrs = new MapAttrs();
		mattrs.Retrieve(MapAttrAttr.UIBindKey, this.getNo());
		if (mattrs.size()!= 0)
		{
			String err = "";
			for (MapAttr item : mattrs.ToJavaList())
			{
				err += " @ " + item.getMyPK() + " " + item.getName();
			}
			return "err@如下实体字段在引用:" + err + "。您不能删除该表。";
		}
		return null;
	}
	@Override
	protected boolean beforeDelete() throws Exception
	{
		String delMsg = this.IsCanDelete();
		if (delMsg != null)
		{
			throw new RuntimeException(delMsg);
		}

		return super.beforeDelete();
	}
}
