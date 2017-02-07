package com.framework.core.web.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import com.framework.core.exception.BaseAppRuntimeException;
import com.framework.core.utils.ActionContextUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>
 * 该类是所有Action的基类，它主要负责分派不同的方法来处理具体的业务.
 * 该类主要通过获取HttpServletRequest的请求参数method,根据method的取值，从而调用
 * 合适的方法.例如：如下请求字符串:xxx.jsp?method=delete，那么该Action就会根据method
 * 的值为delete调用delete()方法进行业务处理. <br/> <b>注意：<b>method是硬编码的，在编程过程中需注意这点.
 * <ul> *
 * <li>public String delete()</li>
 * <li>public String insert()</li>
 * <li>public String update()</li> *
 * </ul>
 * <p>
 * 以上述方法为例，对于以下路径:
 * </p>
 * <p>
 * <code> http://localhost:8080/myapp/saveSubscription.do?method=update
 * </code>
 * </p>
 * 系统将要调用update()方法进行业务处理.
 * <p>
 * <strong>注意：</strong> -如果method参数值为空, 该基类的<code>unspecified</code> 方法将被调用.
 * 默认情况下，会抛出系统异常. <br/> 系统中的所有getMessage方法处理国际化信息，从而使得Struts2和Spring的国际化信息完全一致.
 * 国际化文件只加载一遍即可.
 * 
 * @author gaof
 * @version 1.0
 * @since 2011-4-21
 */

@SuppressWarnings( { "serial", "unchecked" })
public abstract class BaseAction extends ActionSupport {
	/**
	 *Session中保存的消息Map
	 */
	public static final String MESSAGE_MAP = "MESSAGE_MAP";
	/**
	 * Commons Logging日志实例
	 */
	private static Log logger = LogFactory.getLog(BaseAction.class);

	/**
	 * <code>BaseAction</code>类型的实例.
	 */
	private Class clazz = this.getClass();

	/**
	 * Method对象的集合，该集合封装了类中不同的方法对象，当第一次调用后就被封装进该Map中.
	 * 
	 */
	private HashMap<String, Method> methods = new HashMap<String, Method>();

	/**
	 * 反射方法调用时,默认的类型集合.<strong>注意：</strong> 所有的方法参数类型都一致.
	 */
	private Class[] types = {};
	/**
	 * 默认动作为index
	 */
	private String method = "index";

	/**
	 * 配置的负责处理国际化的bean名称为messageSource，该名称是Spring中规定的不能改变
	 */
	private final String MESSAGE_SOURCE_BEAN_NAME = "messageSource";
	/**
	 * 验证方法前缀
	 */
	private final static String VALIDATE_PREFIX = "validate";

	/**
	 * 默认构造方法，初始化applicationContext和messageSource对象
	 */
	public BaseAction() {
	}

	/**
	 * Struts2默认调用的execute方法
	 * 
	 * @return 返回处理结果.
	 */
	@Override
	public String execute() throws Exception {
		// 获取方法的名称.
		String methodName = getMethod();
		// 防止递归调用
		if ("execute".equals(methodName)) {
			String message = this.getMessage("dispatch.recursive");
			logger.error(message);
			throw new BaseAppRuntimeException(message);
		}
		return dispatchMethod(methodName);
	}

	/**
	 * 利用反射机制，分派调用具体的业务方法.
	 * 
	 * @param name
	 *            被调用的方法的名称
	 * @return 返回调用的结果.
	 * @throws Exception
	 *             如果系统出现异常则抛出.
	 */
	private String dispatchMethod(String name) throws Exception {
		// 确保系统有正确的方法可以调用.
		// 如果方法不正确，调用unspecified进行默认处理
		if (name == null) {
			return this.unspecified();
		}
		// 具体方法调用
		// 获取Method对象,进行方法的动态调用
		Method method = null;
		try {
			method = getMethod(name);
		} catch (NoSuchMethodException e) {
			String message = this.getMessage("dispatch.nosuchmethod",
					new Object[] { name });
			logger.error(message, e);
			throw new NoSuchMethodException(message);
		}
		String forward = null;
		try {
			// 参数数组
			Object[] args = {};
			// 方法调用
			forward = (String) method.invoke(this, args);
		} catch (ClassCastException e) {
			String message = this.getMessage("dispatch.return");
			logger.error(message, e);
			throw e;
		} catch (IllegalAccessException e) {
			String message = this.getMessage("dispatch.error");
			logger.error(message, e);
			throw e;
		} catch (InvocationTargetException e) {
			// 尽可能的抛出异常从而使得JVM的异常处理机制及时处理它!
			Throwable t = e.getTargetException();
			if (t instanceof Exception) {
				System.out.println(e.getTargetException());
				throw ((Exception) t);
				
			} else {
				String message = this.getMessage("dispatch.error");
				logger.error(message, e);
				throw new RuntimeException(t);
			}
		}
		// 返回处理的结果
		return (forward);
	}

	/**
	 * 当method为null时，表示方法没有指派，默认情况下回抛出异常.<br/> 对于子类可以重写该方法，自定义当方法没有指定时的行为.
	 * 
	 * @throws Exception
	 *             如果系统出现异常则抛出.
	 */
	protected String unspecified() throws Exception {
		String message = this.getMessage("dispatch.method");
		logger.error(message);
		throw new RuntimeException(message);
	}

	/**
	 * 根据方法的名称，动态获取name对应的Method实例
	 * 
	 * @param name
	 *            方法的名称
	 * @return 返回name对应的Method实例.
	 * @throws NoSuchMethodException
	 *             如果方法没有找到
	 */
	private Method getMethod(String name) throws NoSuchMethodException {
		// 如果单例模式下可以使用
		synchronized (methods) {
			Method method = (Method) methods.get(name);
			if (method == null) {
				method = clazz.getMethod(name, types);
				methods.put(name, method);
			}
			return (method);
		}
	}

	/**
	 * 获取method参数值
	 * 
	 * @return
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * 设置method参数值
	 * 
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * 把成功或失败信息保存到Session中
	 */
	protected void saveMessage(String code) {
		String msg = getMessage(code);
		// 获取request对象对应的Map
		ActionContextUtils.setAtrributeToRequest(MESSAGE_MAP, msg);
	}

	/**
	 * 把成功或失败信息保存到Session中
	 */
	protected void saveMessage(String code, Object[] args) {
		String msg = getMessage(code, args);
		// 获取request对象对应的Map
		ActionContextUtils.setAtrributeToRequest(MESSAGE_MAP, msg);
	}

	/**
	 * 把成功或失败信息保存到Session中
	 */
	protected void saveMessage(String code, Object[] args, String defaultMessage) {
		String msg = getMessage(code, args, defaultMessage);
		// 获取request对象对应的Map
		ActionContextUtils.setAtrributeToRequest(MESSAGE_MAP, msg);
	}

	/**
	 * 返回国际化消息，如果没有找到则返回默认消息.
	 * 
	 * @param code
	 *            例如： 'app.title=nihao',其中，app.title为code
	 * @param args
	 *            参数占位数组，例如：'app.title=nihao,{0},how are you,{1}'
	 *            <br/>args中的args[0]和args[1]分别替代{0},{1}。
	 * @param defaultMessage
	 *            找不到信息是，返回默认信息。
	 * @return 返回国际化信息
	 */
	protected String getMessage(String code, Object[] args,
			String defaultMessage) {
		String msg = getMessageSource().getMessage(code, args, defaultMessage,
				this.getLocale());
		return msg;
	}

	/**
	 * 根据key值，返回国际化消息
	 * 
	 * @param code
	 *            例如： 'app.title=nihao',其中，app.title为code
	 * @param args
	 *            参数占位数组，例如：'app.title=nihao,{0},how are you,{1}'
	 *            <br/>args中的args[0]和args[1]分别替代{0},{1}。
	 * @return 返回国际化信息
	 */
	protected String getMessage(String code, Object[] args) {
		String msg = getMessageSource()
				.getMessage(code, args, this.getLocale());
		return msg;
	}

	/**
	 * 返回国际化消息，如果没有找到则返回默认消息.
	 * 
	 * @param code
	 *            例如： 'app.title=nihao',其中，app.title为code
	 * @return 返回国际化信息
	 */
	protected String getMessage(String code) {
		String msg = getMessageSource()
				.getMessage(code, null, this.getLocale());
		return msg;
	}

	private MessageSource getMessageSource() {
		// 获取与WebApplicationContext对应的键值
		String key = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
		/**
		 * Spring的WebApplicationContext接口
		 */
		WebApplicationContext applicationContext = (WebApplicationContext) ActionContextUtils
				.getAttribute(key, "application");
		if (applicationContext == null) {
			logger.error("必须初始化WebApplicationContext对象!");
			throw new RuntimeException("必须初始化WebApplicationContext对象!");
		}
		/**
		 * Spring中负责处理国际化的接口
		 */
		MessageSource messageSource = (MessageSource) applicationContext
				.getBean(MESSAGE_SOURCE_BEAN_NAME);
		if (messageSource == null) {
			logger.error("在Spring配置文件中必须配置messageSource对象!");
			throw new RuntimeException("在Spring配置文件中必须配置messageSource对象!");
		}
		return messageSource;
	}

	/**
	 * Struts2框架自动调用，执行验证方法
	 * @see com.opensymphony.xwork2.interceptor.DefaultWorkflowInterceptor
	 */
	public void validateExecute() {
		String methodName = this.getMethod();
		// 获取首字母大写的方法名
		String capitalizedMethodName = capitalizeMethodName(methodName);
		// 构造验证方法名
		String prefixedMethodName = VALIDATE_PREFIX + capitalizedMethodName;
		Method validateMethod = null;
		try {
			validateMethod = getMethod(prefixedMethodName);
			if (validateMethod != null) {
				validateMethod.invoke(this, new Object[0]);
			}
		} catch (Exception e) {
			logger.debug(prefixedMethodName + " method not exsists");
		}

	}

	/**
	 * 把方法名第一个字母变成大写
	 * 
	 * @param methodName
	 * @return
	 */
	public static String capitalizeMethodName(String methodName) {
		assert (methodName != null);
		return methodName = methodName.substring(0, 1).toUpperCase()
				+ methodName.substring(1);
	}

}
