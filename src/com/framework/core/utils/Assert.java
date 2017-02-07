
package com.framework.core.utils;
/**
 * 该类把最基本的异常处理进行封装，避免代码中出现大量的异常的抛出.
 * 例如:
 * <pre class="code">
 * Assert.notNull(clazz, "The class must not be null");
 * Assert.isTrue(i > 0, "The value must be greater than zero");
 * </pre>
 * 可以参考Spring Assert类的用法

 * @author gaof
 * @since 2011-4-1
 */
public abstract class Assert {


	/**
	 * 确保传入的Object对象为null，否则抛出异常 .
	 * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
	 * @param 需检查的对象
	 * @param 验证失败，既object为null时，抛出的异常信息
	 * @throws IllegalArgumentException 如果object为null，抛出的异常
	 */
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}	
	/**
	 * 确保传入的对象不为null,否则抛出异常 .
	 * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
	 * @param object 需要检出的对象
	 * @param message 异常发生时抛出的异常信息
	 * @throws IllegalArgumentException 如果object为null，抛出的异常类型
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}
	/**
	 * 确保传入的对象为true,否则抛出异常>
	 * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
	 * @param expression boolean类型的表达式
	 * @param message 发生异常时的异常信息
	 * @throws IllegalArgumentException 如果表达式为false时抛出的异常类型
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}
}
