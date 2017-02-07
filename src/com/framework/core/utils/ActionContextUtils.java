package com.framework.core.utils;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;

/**
 * 处理ActionContext中的关于request,session,application等范围对象中的方法
 * 
 * @author gaofeng
 * @since 2011-4-12
 * 
 */
@SuppressWarnings("unchecked")
public final class ActionContextUtils {
	/**
	 * HttpServletRequest对象对应的键.
	 */
	public static final String HTTP_REQUEST = "com.opensymphony.xwork2.dispatcher.HttpServletRequest";

	/**
	 * HttpServletResponse对象对应的键.
	 */
	public static final String HTTP_RESPONSE = "com.opensymphony.xwork2.dispatcher.HttpServletResponse";
	/**
	 * ServletContext对象对应的键.
	 */
	public static final String SERVLET_CONTEXT = "com.opensymphony.xwork2.dispatcher.ServletContext";

	/**
	 * 获取ActionContext对象
	 * 
	 * @return
	 */
	public static ActionContext getContext() {

		return ActionContext.getContext();
	}

	/**
	 * 获取请求对象参数的值 <br>
	 * 对应HttpServletRequest对象中的request.getParameterMap()方法
	 * 
	 * @param key
	 * @return 字符串数组
	 */
	public static String[] getParameters(String key) {
		String[] result = null;
		// 获取parameterMap中的数据,值都是String[]类型的
		Map params = getContext().getParameters();
		// 根据key获取值数组
		result = (String[]) params.get(key);
		if (result == null) {
			result = new String[1];
			result[0] = "";
		}
		return result;
	}

	/**
	 * 从指定的范围对象中获取对象,对应范围对象的getAttribute()方法
	 * 
	 * @param scopeName
	 *            范围对象的名称 request,session,application
	 * @param key
	 *            范围的
	 * @return
	 */

	public static Object getAttribute(String key, String scopeName) {
		if (StringUtils.isEmpty(scopeName)) {
			scopeName = "session";
		}
		if (!"request".equals(scopeName) && !"session".equals(scopeName)
				&& !"application".equals(scopeName)) {
			throw new RuntimeException(
					"scopeName的值只能是request或session或application!");
		}
		Map map = (Map) getContext().get(scopeName);
		return map.get(key);

	}

	/**
	 * 把对象保存在request中,对应setAttribute()方法
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public static void setAtrributeToRequest(String key, Object value) {
		// 返回request对象
		Map map = (Map) getContext().get("request");
		map.put(key, value);
	}

	/**
	 * 把对象保存在session对象中,对应setAttribute()方法
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public static void setAttributeToSession(String key, Object value) {
		// 返回session对象
		Map map = (Map) getContext().getSession();
		map.put(key, value);
	}

	/**
	 * 把对象保存在application对象中,对应setAttribute()方法
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public static void setAttributeToApplication(String key, Object value) {
		// 返回application对象
		Map map = (Map) getContext().getApplication();
		map.put(key, value);
	}

	/**
	 * 获取HttpRequest对象
	 * 
	 * @return .
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) ActionContext.getContext()
				.get(HTTP_REQUEST);
	}

	/**
	 * 获取HttpServletResponse对象.
	 * 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) ActionContext.getContext().get(
				HTTP_RESPONSE);
	}

	/**
	 * 获取ServletContext对象.
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {

		return (ServletContext) ActionContext.getContext().get(SERVLET_CONTEXT);
	}

}
