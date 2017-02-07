package com.framework.core.utils;

import org.apache.commons.lang.text.StrBuilder;

public final class StringUtils {
	/**
	 * 空字符数组
	 */
	public static final String[] EMPTY_STRING_ARRAY = new String[0];
	/**
	 * 空字符串
	 */
    public static final String EMPTY = "";
	/**
	 * 返回转化后的内容
	 * 
	 * @param obj
	 * @return
	 */
	public static String valueOf(Object obj) {
		return (obj == null) ? "" : obj.toString().trim();
	}
	/**
	 * 判断字符串是否为null或""字符串
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value){
		return (value == null || value.trim().equals(""));
	}
    /**
     * 如果传入的字符串为null，则返回空字符串.
     * <pre>
     * StringUtils.trimToEmpty(null)          = ""
     * StringUtils.trimToEmpty("")            = ""
     * StringUtils.trimToEmpty("     ")       = ""
     * StringUtils.trimToEmpty("abc")         = "abc"
     * StringUtils.trimToEmpty("    abc    ") = "abc"
     * </pre>
     */
    public static String trimToEmpty(String str) {
        return str == null ? EMPTY : str.trim();
    }

    /**
     * <p>根据指定的字符,把数组连接起来.</p>
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }
    /**
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }
        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0) {
            return EMPTY;
        }
        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length())
                        + separator.length());
        StrBuilder buf = new StrBuilder(bufSize);
        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }
}
