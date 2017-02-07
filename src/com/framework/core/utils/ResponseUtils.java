package com.framework.core.utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * HttpServletResponse帮助类
 * 
 * @author cms 1.0
 * @modify 
 */
public class ResponseUtils {

	private static Log log = LogFactory.getLog(ResponseUtils.class);

	/**
	 * 输出内容到response
	 * 
	 * @param response
	 * @param text
	 * @return
	 * @throws IOException
	 */
	private static void print(HttpServletResponse response, String contentType, String text)
			throws IOException {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter pw = response.getWriter();
		pw.write(text);
		pw.close();
	}

	/**
	 * 输出文本内容
	 * 
	 * @param response
	 * @param text
	 * @throws IOException
	 */
	public static void printText(HttpServletResponse response, String text) {
		try {
			print(response, "text/plain;charset=UTF-8", text);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
	}

	public static void printHtml(HttpServletResponse response, String text) {
		try {
			print(response, "text/html;charset=UTF-8", text);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
	}

	/**
	 * 将 Map 类型数据转化为 Json 格式输出
	 * 
	 * @param response
	 * @param text
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public static void printJson(HttpServletResponse response, Map<String, Object> map) {
		try {
			print(response, "text/html;charset=UTF-8", "[" + JSONObject.fromObject(map).toString()
					+ "]");
		} catch (Exception ex) {
			log.error(ex.toString());
		}
	}

	/**
	 * 将 List 类型数据转化为 Json 格式输出
	 * 
	 * @param response
	 * @param map
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public static void printJson(HttpServletResponse response, List list) {
		try {
			print(response, "text/html;charset=UTF-8", "[" + JSONObject.fromObject(list).toString()
					+ "]");
		} catch (Exception ex) {
			log.error(ex.toString());
		}
	}

	/**
	 * 输出 Xml 格式的数据
	 * 
	 * @param response
	 * @param text
	 */
	public static void printXml(HttpServletResponse response, String text) {
		try {
			print(response, "text/xml;charset=UTF-8", text);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
	}

	public static void printJson(HttpServletResponse response, String text) {
		try {
			print(response, "text/html;charset=UTF-8", text);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
	}

	public static void printJsonData(HttpServletResponse response, String text) {
		try {
			print(response, "application/json;charset=UTF-8", text);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
	}

	/**
	 * 发送json。使用UTF-8编码。
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderJson(HttpServletResponse response, String text) {
		try {
			render(response, "application/json;charset=UTF-8", text);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
	}

	public static void render(HttpServletResponse response, String contentType, String text)
			throws IOException {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.getWriter().write(text);
	}

}
