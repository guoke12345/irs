package com.framework.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 异常处理类
 * 
 * @author gaofeng
 * 
 */
public final class ExceptionUtils {
	/**
	 * 把异常栈的信息转换成字符串信息
	 * 
	 * @param t
	 *            抛出的异常类
	 * @return 处理后的字符串
	 */
	public static String formatStackTrace(Throwable t) {
		// 存储异常信息
		StringWriter sw = new StringWriter();
		try {
			PrintWriter p = new PrintWriter(sw);
			t.printStackTrace(p);
		} catch (Exception e) {
			// do nothing
		}
		return sw.toString();
	}

	/**
	 * 主要处理外键异常，当删除主表时，如果子表中含有记录,则提示 <br/>通过直接抛出异常效果更好
	 * 例如：Teacher表和Student表，设置外键为：FK_teacher_student <b>注：</b>外键的设置格式.<br/>
	 * 最后提示给用户的信息为："不能删除teacher表，以为其子表student中还有记录"
	 * 
	 * @param t
	 *            异常信息对象
	 * @return 两个表的表名
	 */
	public static String[] foreignKeyException(Throwable t) {
		// 异常字符串
		String sw = formatStackTrace(t);
		// 根据正则表达式得到父表和子表
		String[] result = null;
		Pattern pattern = Pattern.compile("FK_[a-zA-Z]*_[a-zA-Z]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = pattern.matcher(sw);
		String message = null;
		if (m.find()) {
			int i = m.groupCount();
			if (i == 0) {
				message = m.group(0);
			}
			if (i > 0) {
				message = m.group(0);
			}
		}
		if (message != null) {
			String[] results = message.split("_");
			result = new String[2];
			result[0] = results[1].toLowerCase();
			result[1] = results[2].toLowerCase();
		}
		return result;
	}

	/**
	 * 打印日志函数。<br/>
	 * <ul>
	 * <li>(打印日志函数，打印debug级的日志。)</li>
	 * </ul>
	 * 
	 * @param Exception
	 *            异常。
	 * @return 无。
	 * @throws 无。
	 */
	public static final String printDebug(Exception e) {
		// 得到异常轨迹并把轨迹放到一个对象中。
		StackTraceElement[] stt = e.getStackTrace();
		// 定义盛放轨迹的StringBuffer。

		StringBuffer infors = new StringBuffer("\n\t" + e.getClass().toString());
		infors.append("\n\t" + e.getMessage() + "\n\t");
		// 用于把栈轨迹串起来，这样只打印一次，提高性能．
		for (int i = 0; i < stt.length; i++) {
			infors.append(stt[i]);
			infors.append("\n\t");
		}
		return infors.toString();
	}

	/**
	 * 打印日志函数。<br/>
	 * <ul>
	 * <li>(打印日志函数，打印error级日志，try...catch中的异常为错误异常。)</li>
	 * </ul>
	 * 
	 * @param Exception
	 *            异常。
	 * @return 无。
	 * @throws 无。
	 */
	public static final String printError(Exception e) {
		// 得到异常轨迹并把轨迹放到一个对象中。
		StackTraceElement[] stt = e.getStackTrace();
		// 定义盛放轨迹的StringBuffer。
		StringBuffer infors = new StringBuffer("\n\t" + e.getClass().toString());
		infors.append("\n\t" + e.getMessage() + "\n\t");
		// 用于把栈轨迹串起来，这样只打印一次，提高性能．
		for (int i = 0; i < stt.length; i++) {
			infors.append(stt[i]);
			infors.append("\n\t");
		}
		// 打印异常轨迹。
		return infors.toString();
	}

	public static void main(String[] args) {
		String str = null;
		try {
			str.charAt(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(formatStackTrace(e));
		}
	}

	public void test() {
		String str = "org.springframework.dao.DataIntegrityViolationException: Could not execute JDBC batch update;"
				+ " nested exception is org.hibernate.exception.ConstraintViolationException: Could not execute JDBC batch update"
				+ ""
				+ " 	at org.springframework.orm.hibernate3.SessionFactoryUtils.convertHibernateAccessException(SessionFactoryUtils.java:636)	"
				+ "at org.springframework.orm.hibernate3.HibernateTransactionManager.convertHibernateAccessException(HibernateTransactionManager.java:789)	"
				+ "at org.springframework.orm.hibernate3.HibernateTransactionManager.doCommit(HibernateTransactionManager.java:663)	"
				+ "at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:732)	"
				+ "at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:701)"
				+ "	at org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:321)	"
				+ "at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:116)	"
				+ "at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:171)	"
				+ "at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:89)"
				+ "	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:171)"
				+ "	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:204)	at $Proxy5.remove(Unknown Source)	"
				+ "at com.qrsx.crm.system.module.ModuleAction.delete(ModuleAction.java:137)	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)"
				+ "	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)"
				+ "	at java.lang.reflect.Method.invoke(Unknown Source)	at org.apache.struts.actions.DispatchAction.dispatchMethod(DispatchAction.java:269)"
				+ "	at org.apache.struts.actions.DispatchAction.execute(DispatchAction.java:170)	at com.framework.actions.BaseAction.execute(BaseAction.java:87)	at org.apache.struts.action.RequestProcessor.processActionPerform(RequestProcessor.java:425)	at org.apache.struts.action.RequestProcessor.process(RequestProcessor.java:228)	at org.apache.struts.action.ActionServlet.process(ActionServlet.java:1913)	at org.apache.struts.action.ActionServlet.doGet(ActionServlet.java:449)	at javax.servlet.http.HttpServlet.service(HttpServlet.java:617)	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)"
				+ "	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)	"
				+ "at org.springframework.orm.hibernate3.support.OpenSessionInViewFilter.doFilterInternal(OpenSessionInViewFilter.java:198)"
				+ "	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:76)	"
				+ "at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:235)	"
				+ "at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)	"
				+ "at org.ecside.filter.ECSideFilter.doFilter(ECSideFilter.java:188)	"
				+ "at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:235)"
				+ "	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)	"
				+ "at com.qrsx.crm.web.filters.SetCharacterEncodingFilter.doFilter(SetCharacterEncodingFilter.java:170)	"
				+ "at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:235)"
				+ "	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)"
				+ "	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)	"
				+ "at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)	"
				+ "at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:128)	"
				+ "at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)	"
				+ "at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)	"
				+ "at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:286)	"
				+ "at org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:845)	"
				+ "at org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:583)"
				+ "	at org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:447)	"
				+ "at java.lang.Thread.run(Unknown Source)Caused by: org.hibernate.exception.ConstraintViolationException: Could not execute JDBC batch update"
				+ "	at org.hibernate.exception.SQLStateConverter.convert(SQLStateConverter.java:71)	at org.hibernate.exception.JDBCExceptionHelper.convert(JDBCExceptionHelper.java:43)	at org.hibernate.jdbc.AbstractBatcher.executeBatch(AbstractBatcher.java:253)	at org.hibernate.engine.ActionQueue.executeActions(ActionQueue.java:237)	at org.hibernate.engine.ActionQueue.executeActions(ActionQueue.java:146)	at org.hibernate.event.def.AbstractFlushingEventListener.performExecutions(AbstractFlushingEventListener.java:298)	at org.hibernate.event.def.DefaultFlushEventListener.onFlush(DefaultFlushEventListener.java:27)	"
				+ "at org.hibernate.impl.SessionImpl.flush(SessionImpl.java:1000)	at org.hibernate.impl.SessionImpl.managedFlush(SessionImpl.java:338)	"
				+ "at org.hibernate.transaction.JDBCTransaction.commit(JDBCTransaction.java:106)	"
				+ "at org.springframework.orm.hibernate3.HibernateTransactionManager.doCommit(HibernateTransactionManager.java:655)	"
				+ "... 45 moreCaused by: java.sql.BatchUpdateException: Cannot delete or update a parent row: a foreign key constraint fails (`crm/crm_privilege`, CONSTRAINT `FK_module_privilege ENDFOREIGN KEY (`moduleId`) REFERENCES `crm_module` (`id`))	at com.mysql.jdbc.PreparedStatement.executeBatchSerially(PreparedStatement.java:1269)	"
				+ "at com.mysql.jdbc.PreparedStatement.executeBatch(PreparedStatement.java:955)"
				+ "	at com.mchange.v2.c3p0.impl.NewProxyPreparedStatement.executeBatch(NewProxyPreparedStatement.java:1723)	"
				+ "at org.hibernate.jdbc.BatchingBatcher.doExecuteBatch(BatchingBatcher.java:48)	"
				+ "at org.hibernate.jdbc.AbstractBatcher.executeBatch(AbstractBatcher.java:246)	... 53 more";

		Pattern p = Pattern.compile("FK_[a-zA-Z]*_[a-zA-Z]*");
		Matcher m = p.matcher(str);
		String message = null;
		if (m.find()) {
			int i = m.groupCount();
			if (i == 0) {
				message = m.group(0);
			}
			if (i > 0) {
				message = m.group(0);
			}
		}
		System.out.println(message);
	}
}