package bp.en;


import bp.da.AtPara;
import bp.da.DBAccess;
import bp.da.DataSet;
import bp.da.DataType;
import bp.difference.SystemConfig;
import bp.pub.RTFEngine;
import bp.sys.*;
import bp.tools.Json;
import bp.tools.QrCodeUtil;
import bp.tools.StringUtils;
import bp.web.WebUser;
import bp.wf.Glo;
import bp.wf.template.Part;
import com.google.gson.JsonObject;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Hashtable;

/**
 可以被重写的类
*/
public class OverrideFile
{
	public static String GetMd5Hash(String input)
	{
		return DBAccess.GenerMD5(input);
	}
	/**
	 登录方法
	 @param dbSrc 数据源
	 @return token
	 @exception Exception
	 */
	public static String Data_WebApi_Login(SFDBSrc dbSrc) throws Exception {
		//1. 先从cokes取.
		String token = bp.web.WebUser.GetSessionByKey(dbSrc.getNo(), null);

		//2. 如果没有token就生成token.
		if (DataType.IsNullOrEmpty(token) == false)
		{
			return token;
		}

		///#region 本机数据源获取token.
		//3. 获取token的方法.
		if (dbSrc.getNo().equals("localWebApi") == true)
		{
			String url = dbSrc.getConnString() + "/";
			String data = bp.tools.PubGlo.HttpPostConnect(url, null, "GET", true, null);
			JSONObject jsonFS = JSONObject.fromObject(data);
			if (jsonFS.getInt("code") != 200)
			{
				throw new RuntimeException("err@获取token出现错误.");
			}

			token = WebUser.getToken();
			bp.web.WebUser.SetSessionByKey(dbSrc.getNo(), token);
			return token;
		}
		///#endregion 本机数据源获取token.

		///#region 方策：获取token.
		if (dbSrc.getNo().equals("fsjma.cn") == true)
		{
			String passWord = DBAccess.RunSQLReturnStringIsNull("SELECT Pass FROM Port_Emp WHERE No='" + WebUser.getNo() + "'", null);
			if (passWord == null)
			{
				throw new RuntimeException("err@用户不存在或者没有密码.");
			}

			String url = "http://comms.fsjma.cn:8089/CommonAppServer/Account/Login?commstest=XCDemo";
			String userNo = "fccgy"; // WebUser.No;
			String userName = "方策-陈刚毅"; // WebUser.Name;
			passWord = "chan9808";

			String md5 = userNo + GetMd5Hash(userNo.toLowerCase()) + GetMd5Hash(passWord);
			// fccgy
			//fccgy + md5(fccgy) + md5(chan9808)
			//username + MD5(username).toString() + MD5(password).toString()
			// string val = userNo + GetMd5Hash(userNo) + GetMd5Hash(passWord)+userName;
			//  string bodyData = "{'AppName': 'Web','UniqueID':'123456','UserType': 'comms','UserID': '" + WebUser.No + "','Pwd': '" + val + "'｝";
			String bodyData = "{'AppName': 'Web','UniqueID':'123456','UserType': 'comms','UserID': '" + userNo + "','Pwd':'" + md5 + "'｝";
			String header = "{md5:'" + GetMd5Hash(bodyData) + "'}";

			header = "{md5:'7420a13941120697768cad813578c2ce'}";
			bodyData = "{ 'UserType': 'comms','UserID': 'fccgy','Pwd': 'fccgy0fedaa6cb0d2b06b6065096056c0aea36671d7345e7ff3ac0b6e874d971394ed','UniqueID': 'fATq6gFQK7A6n7Zx0000796534427765','AppName':'Web'}";
			// bodyData = "{ UserType: 'comms', UserID: 'fccgy',Pwd :'fccgy0fedaa6cb0d2b06b6065096056c0aea36671d7345e7ff3ac0b6e874d971394ed', UniqueID: 'fATq6gFQK7A6n7Zx0000796534427765',AppName:'Web'}";
			//通过下面的方法获取token.
			String data = bp.tools.PubGlo.HttpPostConnect(url, bodyData, "POST", true, header);
			JSONObject jsonFS = JSONObject.fromObject(data);

			if (jsonFS != null)
			{
				if (jsonFS.getInt("code") != 0)
				{
					throw new RuntimeException("err@密码或者用户名错误，数据:" + data);
				}
				bp.web.WebUser.SetSessionByKey(dbSrc.getNo(), jsonFS.get("accessToken").toString());
				return jsonFS.get("accessToken").toString();
			}
			throw new RuntimeException("err@获取token错误:" + data + " url:" + url + ", bodyData=" + bodyData + "  header=" + header);
		}
		///#endregion 方策：获取token.

		throw new RuntimeException("err@没有判断的数据源[" + dbSrc.getNo() + "]获取token的方法.");
	}
	/**
	 处理别名参数

	 @param ht
	 @param paramAlia
	 @return
	 @exception Exception
	 */
	public static Hashtable DealHT_ParamAlia(Hashtable ht, String paramAlia)
	{
		try
		{
			if (ht == null)
			{
				ht = new Hashtable();
			}
			// ht 里面如果有别名，就把它修正为参数.
			if (DataType.IsNullOrEmpty(paramAlia) == true)
			{
				return ht;
			}
			String err = "";
			paramAlia = paramAlia.replace(";", "@");

			if (paramAlia.contains("=") == false)
			{
				throw new RuntimeException("参数别名不符合规则:" + paramAlia);
			}

			if (paramAlia.contains("@") == false)
			{
				paramAlia = "@" + paramAlia;
			}

			AtPara paraAlia = new AtPara(paramAlia);
			for (String key : paraAlia.getHisHT().keySet())
			{
				String aliaKeys = "," + paraAlia.getHisHT().get(key).toString() + ",";

				//检查传递过来的参数里是否有这样的字段,如果有就复制这个值，按照实体参数名增加进去.
				Hashtable tempHt = new Hashtable(); //直接插入容易出错.

				for (Object paraKey : ht.keySet())
				{
					if (aliaKeys.contains("," + paraKey + ",") == false)
					{
						continue;
					}

					if (ht.containsKey(key) == true)
					{
						continue; //如果包含了参数的值.
					}

					if (tempHt.containsKey(key) == false)
					{
						tempHt.put(key, ht.get(paraKey)); //复制值，并插入里面去.
					}
				}

				//增加补充到ht里面.
				for (Object tempKey : tempHt.keySet())
				{
					if (ht.containsKey(tempKey) == true)
					{
						continue; //如果包含了参数的值.
					}
					ht.put(tempKey, tempHt.get(tempKey)); //复制值，并插入里面去.
				}
			}
			return ht;
		}
		catch (RuntimeException ex)
		{
			throw new RuntimeException("err@处理参数别名期间出现错误:" + ex.getMessage());
		}
	}

	/// <summary>
	/// 获得webApi数据: 这个方法需要重写,返回的是结果数据
	///更多信息请参考：https://docs.qq.com/doc/DRG52QmlyeG1kSUJE
	/// </summary>
	/// <param name="ht">参数KeyVal的模式</param>
	/// <param name="requestMethod">POST/GET</param>
	/// <param name="dbSrcNo">数据源编号</param>
	/// <param name="apiUrl">执行部分比如: /Ports</param>
	/// <param name="bodyData">对于post有效： ｛key1:xxx,key2:yyy｝</param>
	/// <param name="headerData">对于post有效： ｛key1:xxx,key2:yyy｝</param>
	/// <param name="paramAlia">别名格式：@Tel=DH,DianHua@Email=YouJian,YJ,参数名有多种表达方式，在ht里获取值。</param>
	/// <returns>返回的数据格式：{ code:'000', msg:'xxx',data:'yyyy' } </returns>
	public static ResultObj Data_WebApi(Hashtable ht, String requestMethod, String dbSrcNo, String apiUrl) throws Exception {
		return Data_WebApi( ht,  requestMethod,  dbSrcNo,  apiUrl, "");
	}
	public static ResultObj Data_WebApi(Hashtable ht, String requestMethod, String dbSrcNo, String apiUrl,  String paramAlia) throws Exception {
		return Data_WebApi( ht,  requestMethod,  dbSrcNo,  apiUrl, paramAlia, "");
	}
	public static ResultObj Data_WebApi(Hashtable ht, String requestMethod, String dbSrcNo, String apiUrl,  String paramAlia, String bodyData) throws Exception {
		return Data_WebApi( ht,  requestMethod,  dbSrcNo,  apiUrl,paramAlia, bodyData, "");
	}
	/// <summary>
	/// 获得webApi数据: 这个方法需要重写,返回的是结果数据
	/// </summary>
	/// <param name="ht">参数KeyVal的模式</param>
	/// <param name="requestMethod">POST/GET</param>
	/// <param name="dbSrcNo">数据源编号</param>
	/// <param name="apiUrl">执行部分</param>
	/// <returns>返回的数据</returns>
	public static ResultObj Data_WebApi(Hashtable ht, String requestMethod, String dbSrcNo, String apiUrl, String paramAlia, String bodyData, String headerData) throws Exception {

		ht = DealHT_ParamAlia(ht, paramAlia);
		//先设置为空.
		WebUser.SetSessionByKey("commsToken", "");

		SFDBSrc mysrc = new SFDBSrc(dbSrcNo);
		apiUrl = mysrc.getConnString() + "/" + apiUrl;
		///#region 处理变量 - 替换已经有的参数。
		if (ht == null)
		{
			ht = new Hashtable();
		}
		if(!StringUtils.isEmpty(bodyData)){
			bodyData = bodyData.replace("~~", "\"");
			bodyData = bodyData.replace("~", "\'");
			bodyData = DealContext(bodyData, ht);
		}else
			bodyData = "";

		if(!StringUtils.isEmpty(headerData)) {
			headerData = headerData.replace("~~", "\"");
			headerData = headerData.replace("~", "\'");
			headerData = DealContext(headerData, ht);
		}else
			headerData = "";
		//处理url.
		apiUrl = DealUrl(apiUrl, ht);
		///#endregion 处理变量. -替换已经有的参数
		///#region 处理变量 - 处理数据源固定参数.
		if (headerData.contains("@") || bodyData.contains("@")|| apiUrl.contains("@"))
		{
			//获得参数.
			SFApiParas paras = new SFApiParas();
			paras.Retrieve("DBSrcNo", dbSrcNo);
			for (SFApiPara para : paras.ToJavaList())
			{
				// ApiParaModel @0=常量@1=业务单元@2=SQL表达式
				if (para.getApiParaModel() == 0)
				{
					ht.put(para.getAttrKey(), para.getExpDoc());
					continue;
				}
				//如果是业务单元: ApiParaModel= @0=常量@1=业务单元@2=SQL表达式
				if (para.getApiParaModel() == 1)
				{
					// 是存储到cookes里面的
					if (para.getApiParaStore() == 0)
					{
						String val = WebUser.GetSessionByKey(para.getAttrKey() + "_" + dbSrcNo, null);
						if (val != null)
						{
							ht.put(para.getAttrKey(), val);
							continue;
						}
					}
					//获取值.
					BuessUnitBase enBuesss = bp.sys.base.Glo.GetBuessUnitEntityByEnName(para.getExpDoc());
					if (enBuesss == null)
						throw new Exception("err@没有实例化业务单元实体:["+para.getExpDoc()+"]请检查，该类是否移除? ");

					enBuesss.HisSFDSrc=mysrc;
					enBuesss.BodyDoc=bodyData;
					enBuesss.HeardDoc=headerData;
					Object tempVar = enBuesss.DoIt();
					String val2 = tempVar instanceof String ? (String)tempVar : null;
					if (para.getApiParaStore() == 0)
					{
						WebUser.SetSessionByKey(para.getAttrKey() + "_" + dbSrcNo, val2);
					}
					ht.put(para.getAttrKey(), val2);
					continue;
				}

				//如果是SQL表达式。
				if (para.getApiParaModel() == 2)
				{
					// 是存储到cookes里面的
					if (para.getApiParaStore() == 0)
					{
						String val = WebUser.GetSessionByKey(para.getAttrKey() + "_" + dbSrcNo, null);
						if (val != null)
						{
							ht.put(para.getAttrKey(), val);
							continue;
						}
					}
					String sql = para.getExpDoc();
					sql = bp.difference.Glo.DealExp(sql, ht);
					String val2 = DBAccess.RunSQLReturnStringIsNull(sql, null);
					if (val2 == null)
					{
						throw new RuntimeException("err@获取系统参数期间出现错误:SQL表达式:" + sql + ",没有获得数据.");
					}

					if (para.getApiParaStore() == 0)
					{
						WebUser.SetSessionByKey(para.getAttrKey() + "_" + dbSrcNo, val2);
					}
					ht.put(para.getAttrKey(), val2);
					continue;
				}
			}
			bodyData = DealContext(bodyData, ht);
			headerData = DealContext(headerData, ht);
			apiUrl = DealContext(apiUrl, ht);
		}
		if (apiUrl.contains("@") == true || bodyData.contains("@") || headerData.contains("@") == true)
		{
			ResultObj obj = new ResultObj();
			obj.code = 500;
			obj.message = "配置或者参数错误";
			obj.data = "如下参数里的变量没有替换: apiURL=["+apiUrl+ "],bodyData=["+ bodyData + "],headerData=["+ headerData + "]";
			return obj;
		}
		///#endregion 处理变量 - 处理数据源固定参数.
		//调用通用的处理模式.,获取原始数据.
		String data = null;
		try
		{
			data = Data_WebApi_Gener(requestMethod,  apiUrl, bodyData, headerData);
			if (data.contains("err@") == true)
			{
				ResultObj obj = new ResultObj();
				obj.message = "获取数据失败";
				obj.code = 500;
				if (data.contains("404")==true)
				{
					obj.code = 404;
					obj.message = "链接错误";
				}
				obj.data = "错误内容:[" + data + "]URL:[" + apiUrl + "], 参数bodyData:" + bodyData + " 参数headerData:" + headerData;
				return obj;
			}
			if (DataType.IsNullOrEmpty(data) == true)
				throw new Exception("原始数据为空");
		}
		catch (Exception ex)
		{
			ResultObj obj = new ResultObj();
			obj.code = 500;
			obj.message = "获取数据失败";

			String msg = "";
			if (mysrc.getNo().equals("localWebApi") == true)
			{
				String host = mysrc.getConnString();
				msg += "当前webApi是localWebApi请检查全局配置:app.config或jflow.properties, HostUrl配置项的值=[" + host + "]是否正确?";
			}
			obj.data = msg + "错误内容:[" + ex.getMessage() + "]原始数据:[" + data + "]URL:[" + apiUrl + "], 参数bodyData:" + bodyData + " 参数headerData:" + headerData;
			return obj;
		}

		//如果不需要格式转换，按照ccbpm的标准开发的.
		if (mysrc.getWebApiResultModel()== 0)
		{
			try
			{
				JSONObject jsonObject = JSONObject.fromObject(data);

				ResultObj obj = new ResultObj();
				obj.code = jsonObject.getInt("code")==0?200:jsonObject.getInt("code"); // 200=执行成功, 404=配置错误，500=执行错误.
				obj.message = jsonObject.getString("message");
				obj.data = jsonObject.getString("data");
				return obj;
			}
			catch (RuntimeException ex)
			{
				ResultObj obj = new ResultObj();
				obj.code = 500;
				obj.message = "返回数据格式错误.";
				obj.data = "错误内容:" + ex.getMessage() + " 原始数据:" + data;
				return obj;
			}
		}
		//如果需要自定义格式转换.
		if (mysrc.getWebApiResultModel() == 1)
		{
			BuessUnitBase enBuesss = bp.sys.base.Glo.GetBuessUnitEntityByEnName(mysrc.getWebApiResultObjEnName());
			enBuesss.WebApiDBSrcUrl = apiUrl; //访问的Url.
			enBuesss.HisSFDSrc = mysrc; //数据源编号
			enBuesss.BodyDoc = bodyData; //body 参数。
			enBuesss.HeardDoc = headerData;// 参数
			enBuesss.resultData = data;//远程返回的结果
			enBuesss.DoIt(); //执行.
			if (enBuesss.resultObj == null)
			{
				throw new RuntimeException("err@数据获取成功,对结果进行结果标准转换错误:使用方法[" + mysrc.getWebApiResultObjEnName() + "],数据结果:" + data);
			}
			return enBuesss.resultObj;
		}

		throw new RuntimeException("err@没有判断的格式转换模式：" + mysrc.getWebApiResultModel());
	}
	private static String DealContext(String bodyData, Hashtable ht) {
		if (DataType.IsNullOrEmpty(bodyData)) {
			bodyData = "";
		}
		bodyData = bodyData.replace("~~", "\"");
		bodyData = bodyData.replace("~", "\"");
		if (DataType.IsNullOrEmpty(bodyData) == false) {
			bodyData = bodyData.replace("@WebUser.No", WebUser.getNo());
			bodyData = bodyData.replace("@WebUser.Name", WebUser.getName());
			bodyData = bodyData.replace("@WebUser.DeptNo", WebUser.getDeptNo());
			bodyData = bodyData.replace("@WebUser.DeptName", WebUser.getDeptName());
			bodyData = bodyData.replace("@WebUser.OrgNo", WebUser.getOrgNo());
			bodyData = bodyData.replace("@WebUser.Token", WebUser.getToken());

			for (Object key : ht.keySet()) {
				bodyData = bodyData.replace("@" + key, ht.get(key).toString());
			}
		}
		return bodyData;
	}

	private static String DealUrl(String apiUrl, Hashtable ht)
	{
		apiUrl = apiUrl.replace("@WebUser.No", WebUser.getNo());
		apiUrl = apiUrl.replace("@WebUser.Name", WebUser.getName());
		apiUrl = apiUrl.replace("@WebUser.DeptNo", WebUser.getDeptNo());
		apiUrl = apiUrl.replace("@WebUser.DeptName", WebUser.getDeptName());
		apiUrl = apiUrl.replace("@WebUser.OrgNo", WebUser.getOrgNo());
		apiUrl = apiUrl.replace("@WebUser.Token", WebUser.getToken());

		if (apiUrl.contains("{") == true)
		{
			for (Object key : ht.keySet())
			{
				apiUrl = apiUrl.replace("{" + key + "}", ht.get(key).toString());
			}
		}
		else
		{
			for (Object key : ht.keySet())
			{
				apiUrl = apiUrl.replace("@" + key, ht.get(key).toString());
			}
		}

		apiUrl = apiUrl.replace("//", "/");
		apiUrl = apiUrl.replace("//", "/");
		apiUrl = apiUrl.replace(":/", "://");
		return apiUrl;
	}
	private static String Data_WebApi_Gener( String requestMethod, String apiUrl, String bodyData)
	{
		return Data_WebApi_Gener( requestMethod,  apiUrl, bodyData, "");
	}

	private static String Data_WebApi_Gener(String requestMethod, String apiUrl, String bodyData, String headerData)
	{
		try
		{
			///#region  GET 解析路径变量 /{xxx}/{yyy} ? xxx=xxx
			if (requestMethod.toUpperCase().equals("GET") == true)
			{
				return bp.tools.PubGlo.HttpGet(apiUrl);
			}

			return bp.tools.PubGlo.HttpPostConnect(apiUrl, bodyData, "POST", true, headerData);
		}
		catch (RuntimeException ex)
		{
			return ex.getMessage();
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	public static ResultObj Data_WebApi_GenerStandReturnObj(String requestMethod, String apiUrl, String bodyData, String headerData)
	{
		String data = null;
		try
		{
			data = Data_WebApi_Gener(requestMethod, apiUrl, bodyData, headerData);
			if (data.contains("err@") == true)
			{
				ResultObj obj = new ResultObj();
				obj.message = "获取数据失败";
				obj.code = 500;
				if (data.contains("404") == true)
				{
					obj.code = 404;
					obj.message = "链接错误";
				}
				obj.data = "错误内容:[" + data + "]URL:[" + apiUrl + "], 参数bodyData:" + bodyData + " 参数headerData:" + headerData;
				return obj;
			}
			if (DataType.IsNullOrEmpty(data) == true)
				throw new Exception("原始数据为空");
		}
		catch (Exception ex)
		{
			ResultObj obj = new ResultObj();
			obj.code = 500;
			obj.message = "获取数据失败";
			obj.data = "错误内容:[" + ex.getMessage() + "]原始数据:[" + data + "]URL:[" + apiUrl + "], 参数bodyData:" + bodyData + " 参数headerData:" + headerData;
			return obj;
		}

		//转化为标准格式.
		try
		{
			JSONObject jsonObject = JSONObject.fromObject(data);
			ResultObj obj = new ResultObj();
			obj.code = jsonObject.getInt("code"); // 200=执行成功, 404=配置错误，500=执行错误.
			obj.message = jsonObject.getString("message");
			obj.data =jsonObject.getString("data");
			return obj;
		}
		catch (Exception ex)
		{
			ResultObj obj = new ResultObj();
			obj.code = 500;
			obj.message = "返回数据格式错误.";
			obj.data = "错误内容:" + ex.getMessage() + " 原始数据:" + data;
			return obj;
		}
	}

	///可以重写的表单事件.
	/**
	 执行的事件

	 param frmID
	 param en
	*/
	public static String FrmEvent_LoadBefore(String frmID, Entity en)
	{
		return null;
	}
	/**
	 装载填充的事件.

	 param frmID
	 param en
	*/
	public static String FrmEvent_FrmLoadAfter(String frmID, Entity en)
	{
		return null;
	}
	/**
	 保存前事件

	 param frmID
	 param en
	*/
	public static String FrmEvent_SaveBefore(String frmID, Entity en)
	{
		return null;
	}
	/**
	 保存后事件

	 param frmID
	 param en
	*/
	public static String FrmEvent_SaveAfter(String frmID, Entity en)
	{
		return null;
	}

	public static String FrmBill_CheckStart(String frmID, Entity en)
	{
		return null;
	}
	/**
	 单据实体审核结束以后

	 @param frmID 单据ID
	 @param en 实体
	 */
	public static String FrmBill_CheckOver(String frmID, Entity en)
	{
		return null;
	}
	/// <summary>
	/// 单据重新审核(回滚)
	/// </summary>
	/// <param name="frmID"></param>
	/// <param name="en"></param>
	/// <returns></returns>
	public static String FrmBill_Reback(String frmID, Entity en)
	{
		return null;
	}
	/**
	 单据实体撤销审核

	 @param frmID
	 @param en
	 */
	public static String FrmBill_UnSend(String frmID, Entity en)
	{
		return null;
	}
	public static String FrmBill_OverBefore(String frmID, Entity en)
	{
		return null;
	}
	public static String FrmBill_OverAfter(String frmID, Entity en)
	{
		return null;
	}

	/**
	 * RTF打印的后的事件
	 * @param workID 主表WorkID
	 * @param ds
	 * @return
	 */
	public static String PrintRTF_After(String workID,String frmID, DataSet ds, StringBuilder rtfstr) throws Exception {
		if (!rtfstr.toString().contains("QRCode")) {
			return rtfstr.toString();
		}
		Part part = new Part();
		part.setTag0(workID);
		part.setTag1(frmID);
		String data = bp.tools.Json.ToJson(ds);
		String doc = Glo.GetMD5Hash(data);
		doc += "@" +workID + "@" + frmID;
		//根据文本生成二维码
		String fileName = DBAccess.GenerGUID()+".png";
		String path = SystemConfig.getPathOfTemp() + fileName;
		QrCodeUtil.createQrCode(doc, SystemConfig.getPathOfTemp(), fileName, "png");
		//生成完成后转成二进制保存到数据库中
		BufferedImage bi;
		Base64.Encoder encoder = Base64.getEncoder();
		try (InputStream stream = Files.newInputStream(Paths.get(path))) {
			byte[] bytes = new byte[stream.available()];
			stream.read(bytes);
			part.setTag2(encoder.encodeToString(bytes).trim());
			part.setTag3(data); //原始数据
			// 替换<QRCode>标记
			rtfstr = new StringBuilder(ReplaceQRCode(rtfstr, path));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			new File(path).delete();
		}
		if(part.IsExit("Tag0",workID,"Tag1",frmID)){
			part.setTag4(DataType.getCurrentDateTime()); // 二维码修改时间
			part.Update();
			return rtfstr.toString();
		}
		part.setTag4(DataType.getCurrentDateTime()); // 二维码创建时间
		part.Insert();
		//String content = GetQCodeContent(workID,frmID);

		return rtfstr.toString();
	}

	public static String ReplaceQRCode(StringBuilder rtf, String path) throws Exception {
		// 如果打印的二维码太小，请调整RTF模板中QRCode标记所在的行间距
		StringBuilder sb = new StringBuilder();
		BufferedImage bi = ImageIO.read(new File(path));
		String imgHex = RTFEngine.ImageTo16String(path);
		//生成rtf中图片字符串
		sb.append("{\\pict");
		sb.append("\\jpegblip");
		sb.append("\\picscalex120");
		sb.append("\\picscaley120");
		sb.append("\\picwgoal").append(bi.getWidth() * 3);
		sb.append("\\pichgoal").append(bi.getHeight() * 3);
		sb.append(imgHex).append("}");
		return rtf.toString().replace("QRCode", sb.toString());
	}

	//根据数据获取二维码内的信息
	public static String  GetQCodeContent(String workID,String frmID) throws Exception {
		Part part = new Part();
		int i = part.Retrieve("Tag0",workID,"Tag1",frmID);
		if(i==0)
			return "";
		String path= SystemConfig.getPathOfTemp()+DBAccess.GenerGUID()+".png";
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] imgByte = decoder.decode(part.getTag2());
		InputStream in = new ByteArrayInputStream(imgByte);
		byte[] b = new byte[1024];
		int nRead = 0;

		OutputStream o = new FileOutputStream(path);
		while ((nRead = in.read(b)) != -1) {
			o.write(b, 0, nRead);
		}
		o.flush();
		o.close();
		in.close();
		//获取二维码中的信息
		String doc = QrCodeUtil.decodeQRCode(path);
		return doc;
	}
	public static String PrintPDF_After(String workID,String frmID, DataSet ds){
		return null;
	}
}
