package bp.difference;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bp.da.DataType;
import bp.da.Log;
import bp.difference.rabbitMQ.RabbitUtils;
import bp.pub.PubClass;
import bp.web.WebUser;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bp.difference.handler.CommonUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.*;
import java.net.SocketException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JFlow上下文工具类
 * @version 2016-5-9
 */
public class ContextHolderUtils implements ApplicationContextAware, DisposableBean {

	private static ContextHolderUtils contextHolder;
	private static ApplicationContext springContext;

	// 数据源设置
	private DataSource dataSource;

	private static RedissonClient redisson;

	// 第三方系统session中的用户编码，设置后将不用再调用登录方法，直接获取当前session进行登录。
	private static String userNoSessionKey;

	public synchronized static ContextHolderUtils getInstance() {
		if (contextHolder == null) {
			if(springContext == null){
				ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
				springContext = ctx;
			}

			contextHolder = springContext.getBean(ContextHolderUtils.class);
		}
		return contextHolder;
	}

	/**
	 * SpringMvc下获取request
	 *
	 * @return
	 */
//	public static HttpServletRequest getRequest() {
//		HttpServletRequest request = null;
//		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//		if (requestAttributes != null) {
//			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
//			request = servletRequestAttributes.getRequest();
//		}
//		if (request == null)
//			request = CommonUtils.getRequest();
//		return request;
//	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = null;
		try {
			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			if (requestAttributes instanceof ServletRequestAttributes) {
				ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
				request = servletRequestAttributes.getRequest();
			}
			// 备用方案获取请求
			if (request == null) {
				request = CommonUtils.getRequest();
			}

			// 验证请求是否有效
			if (request != null) {
				try {
					// 简单测试请求是否可用
					request.getMethod();
				} catch (Exception e) {
					request = null;
				}
			}
		} catch (Exception ignored) {
        }
		return request;
	}

	public static HttpServletResponse getResponse() {
        return CommonUtils.getResponse();
	}

	/**
	 * SpringMvc下获取session
	 * @return
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}

	public static void addCookie(String name, int expiry, String value) {

		if(getRequest()!= null){
			Cookie cookies[] = getRequest().getCookies() ;
			if (cookies == null) {
				Cookie cookie = new Cookie(name, value);
				cookie.setMaxAge(expiry);
				getResponse().addCookie(cookie);
				getRequest().setAttribute(name,value);
			} else {
				for (Cookie cookie : cookies) {
					if (name.equals(cookie.getName())) {
						cookie.setValue(value);
						cookie.setMaxAge(expiry);
						cookie.setPath("/");
						getResponse().addCookie(cookie);
						return;
					}
				}
				Cookie cookie = new Cookie(name, value);
				cookie.setMaxAge(expiry);
				cookie.setPath("/");
				getResponse().addCookie(cookie);
			}
		}
	}

	public static void addCookie(String name, String value)  {
		int expiry = 60 * 60 * 24 * 8;
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(value);
		if(m.find()) {
			value = "base64:" + Base64.getEncoder().encodeToString(value.getBytes());
		}
		if(getRequest()!= null){
			Cookie[] cookies = getRequest().getCookies() ;
			if (cookies == null) {
				Cookie cookie = new Cookie(name, value);
				cookie.setPath("/");
				cookie.setMaxAge(expiry);
				getResponse().addCookie(cookie);
				getRequest().setAttribute(name,value);
			} else {
				for (Cookie cookie : cookies) {
					if (name.equals(cookie.getName())) {
						cookie.setValue(value);
						cookie.setMaxAge(expiry);
						cookie.setPath("/");
						getResponse().addCookie(cookie);
						return;
					}
				}
				Cookie cookie = new Cookie(name, value);
				cookie.setMaxAge(expiry);
				cookie.setPath("/");
				getResponse().addCookie(cookie);
			}
		}
	}

//	public static Cookie getCookie(String name) {
//		Cookie cookies[] = getRequest().getCookies();
//		if (cookies == null || name == null || name.isEmpty())
//			return null;
//		for (Cookie cookie : cookies) {
//			if (name.equals(cookie.getName())) {
//				String val = cookie.getValue();
//				if(val.startsWith("base64:")) {
//					byte[] result = Base64.getDecoder().decode(val.replace("base64:", ""));
//					val = new String(result);
//					return new Cookie(cookie.getName(), val);
//				}
//				return cookie;
//			}
//		}
//		return null;
//	}
public static Cookie getCookie(String name) {
	if (name == null || name.isEmpty()) {
		return null;
	}

	HttpServletRequest request = getRequest();
	if (request == null) {
		// 无法获取请求，返回null
		return null;
	}

	Cookie[] cookies = null;
	try {
		cookies = request.getCookies();
	} catch (Exception e) {
		// 高并发环境下可能出现的异常
		return null;
	}

	if (cookies == null) {
		return null;
	}

	for (Cookie cookie : cookies) {
		if (cookie != null && name.equals(cookie.getName())) {
			String value = cookie.getValue();
			if (value != null && value.startsWith("base64:")) {
				try {
					byte[] result = Base64.getDecoder().decode(value.replace("base64:", ""));
					value = new String(result);
					return new Cookie(cookie.getName(), value);
				} catch (IllegalArgumentException e) {
					// Base64解码失败
					// 返回原始cookie而不是null，保持某种程度的功能性
					return cookie;
				}
			}
			return cookie;
		}
	}

	return null;
}

	/**
	 * 线程本地存储备用解决方案 - 可以在拦截器中设置
	 */
	private static final ThreadLocal<Map<String, String>> threadLocalCookies = new ThreadLocal<Map<String, String>>() {
		@Override
		protected Map<String, String> initialValue() {
			return new HashMap<>();
		}
	};

	/**
	 * 在过滤器或拦截器中设置cookie到ThreadLocal
	 * 这提供了一个备用机制，以防RequestContextHolder失败
	 */
	public static void setThreadLocalCookie(String name, String value) {
		threadLocalCookies.get().put(name, value);
	}

	/**
	 * 增强版的getCookie方法，包含ThreadLocal备用方案
	 */
	public static Cookie getEnhancedCookie(String name) {
		// 首先尝试标准方法
		Cookie cookie = getCookie(name);

		// 如果标准方法失败，尝试从ThreadLocal获取
		if (cookie == null) {
			String value = threadLocalCookies.get().get(name);
			if (value != null) {
				cookie = new Cookie(name, value);
			}
		}

		return cookie;
	}

	/**
	 * 清理ThreadLocal资源，应在请求结束时调用
	 * 例如在Filter的finally块中
	 */
	public static void clearThreadLocalCookies() {
		threadLocalCookies.remove();
	}

	public static void deleteCookie(String name) {
		Cookie cookies[] = getRequest().getCookies();
		if (cookies == null || name == null || name.length() == 0)
			return;
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				cookie.setValue("");
				cookie.setMaxAge(0);
				getResponse().addCookie(cookie);
			}
		}
	}

	public static void clearCookie() {
		Cookie[] cookies = getRequest().getCookies();
		if (null == cookies)
			return;
		for (Cookie cookie : cookies) {
			cookie.setValue("");
			cookie.setMaxAge(0);
			getResponse().addCookie(cookie);

		}
	}

	/**
	 * 获取数据源
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 获取数据源
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}



	public void setRedisson(RedissonClient redission){
		this.redisson = redission;
	}
	public static RedissonClient getRedisson() {
		return redisson;
	}
	/**
	 * 获取第三方系统session中的用户编码
	 * @return
	 */
	public String getUserNoSessionKey() {
		return userNoSessionKey;
	}

	/**
	 * 设置第三方系统session中的用户编码
	 * param userNoSessionKey
	 */
	public void   setUserNoSessionKey(String userNoSessionKey) {
		this.userNoSessionKey = userNoSessionKey;
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) springContext.getBean(name);
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		return springContext.getBean(requiredType);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		ContextHolderUtils.springContext = arg0;
	}
	public ApplicationContext getApplicationContext(){
		return ContextHolderUtils.springContext;
	}
	@Override
	public void destroy() throws Exception {
		ContextHolderUtils.springContext = null;
	}

	public static void ResponseWriteFile(byte[] fileData, String fileName) throws IOException {
		ResponseWriteFile(fileData,fileName, "application/octet-stream");
	}
	public static void ResponseWriteFile(byte[] fileData, String fileName, String contentType) throws IOException {
		HttpServletResponse response = getResponse();
		response.setContentType(contentType+";charset=utf8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + PubClass.toUtf8String(getRequest(),fileName));
		response.setHeader("Connection", "close");
		response.setHeader("Code", "OK");
		// 读取目标文件，通过response将目标文件写到客户端
		// 读取文件
		OutputStream out = response.getOutputStream();
		out.write(fileData);
		out.close();

	}
	public static void ResponseWriteFile(String filePath, String fileName, String contentType) throws IOException {
		HttpServletResponse response = getResponse();
		if (response == null) {
			throw new IllegalStateException("无法获取HttpServletResponse对象");
		}

		InputStream in = null;
		OutputStream out = null;

		try {
			response.reset();
			response.setContentType(contentType + ";charset=utf8");
			String name = PubClass.toUtf8String(getRequest(), fileName);
			if (DataType.IsNullOrEmpty(name) || name.contains(" "))
				throw new RuntimeException("文件名字不合法，含有空字符");

			response.setHeader("Content-Disposition",
					"attachment;filename=" + PubClass.toUtf8String(getRequest(), fileName));
			response.setHeader("Connection", "close");
			response.setHeader("Code", "OK");

			File f = new File(filePath);
			in = Files.newInputStream(f.toPath());
			out = response.getOutputStream();

			// 使用缓冲区优化性能
			byte[] buffer = new byte[8192];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			Log.DebugWriteInfo("用户[" + WebUser.getNo() + "]下载了[" + fileName + "]文件，大小：" + f.length() + "KB，文件地址：" + filePath);
			out.flush();
		} catch (Exception ex) {
			if (ex instanceof SocketException ||
					ex.getCause() instanceof SocketException ||
					ex.getMessage().contains("Broken pipe")) {
				Log.DebugWriteInfo("客户端断开连接: " + ex.getMessage());
				// 不需要额外处理，这是客户端断开的正常情况
				return;
			}
			// 记录错误但不尝试重新处理
			System.err.println("文件下载失败: " + ex.getMessage());
			ex.printStackTrace();
			Log.DebugWriteError(ex);

			// 设置错误状态码和消息
			if (!response.isCommitted()) {
				response.reset();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.setContentType("text/plain;charset=utf-8");
				try {
					// 使用PrintWriter，而不是混用OutputStream和Writer
					PrintWriter writer = response.getWriter();
					writer.write("文件下载失败: " + ex.getMessage());
					writer.flush();
				} catch (Exception e) {
					// 已尽力处理，忽略进一步的错误
				}
			}
		} finally {
			// 确保资源被正确关闭
			if (in != null) {
				try { in.close(); } catch (IOException e) { /* 忽略 */ }
			}
			if (out != null) {
				try { out.close(); } catch (IOException e) { /* 忽略 */ }
			}
		}
	}

	private static RabbitUtils rabbitUtils;
	public void setRabbitUtils(RabbitUtils rabbitUtils) {
		this.rabbitUtils = rabbitUtils;
	}
	public static RabbitUtils getRabbitUtils(){
		return rabbitUtils;
	}
}
