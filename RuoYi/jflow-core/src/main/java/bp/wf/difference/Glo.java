package bp.wf.difference;
import bp.da.DataType;
import bp.da.Log;
import bp.tools.PubGlo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;
import java.security.SecureRandom;

public class Glo
{
	/**
	 * 生成SHA1签名
	 */
	private static String sha1(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes(StandardCharsets.UTF_8));
			byte[] hash = digest.digest();
			StringBuilder hexString = new StringBuilder();

			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成签名
	 */
	public static String generateSignature(String noncestr, String jsapiTicket, long timestamp, String url) {
		// 1. 准备参数，存入Map
		Map<String, String> params = new TreeMap<>();
		params.put("jsapi_ticket", jsapiTicket);
		params.put("noncestr", noncestr);
		params.put("timestamp", String.valueOf(timestamp));
		params.put("url", url);

		// 2. 按照字典序拼接成字符串string1
		StringBuilder string1 = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (string1.length() > 0) {
				string1.append("&");
			}
			string1.append(entry.getKey()).append("=").append(entry.getValue());
		}

		// 3. 对string1进行sha1签名
		Log.DebugWriteInfo("" + string1.toString());
		return sha1(string1.toString());
	}
	// 定义可用来生成随机字符串的字符集
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final int STRING_LENGTH = 16;
	private static final SecureRandom RANDOM = new SecureRandom();

	/**
	 * 生成随机字符串
	 * @param length 生成的字符串长度
	 * @return 随机字符串
	 */
	public static String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = RANDOM.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(index));
		}
		return sb.toString();
	}

	/**
	 * 获取access_token
	 * @return access_token
	 */
	public static String GetWXGZHToken(){
		String appid = bp.difference.SystemConfig.getWXGZH_Appid();
		String appsecret = bp.difference.SystemConfig.getWXGZH_AppSecret();
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + appsecret;
        return PubGlo.HttpGet(url);
	}

	/**
	 * 获取jsapi_ticket
	 * @param access_token access_token
	 * @return jsapi_ticket
	 */
	public static String GetWXGZHJsapiTicket(String access_token){
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
		return PubGlo.HttpGet(url);
	}
}