package com.framework.core.utils;

/**
 * 处理Object的使用类，
 * 
 * @author gaofeng
 * @since 2011-4-1
 * 
 */
public final class ObjectUtils {
	/**
	 * 判断所给的对象数组是否为null，或元素个数为0.
	 * 
	 * @param array
	 *            待检查的数组
	 * @return 判断给定的数组是否为空
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}
	/**
	 * 空值判断,主要针对String Integer Long等类型
	 * 
	 * @param obj
	 *            Object
	 * @return result boolean
	 */
	public static boolean isEmpty(Object obj) {

		if (obj == null) {
			return true;
		}else if(obj instanceof String) {
			//判断String
			String objStr = (String) obj;
			if(objStr.trim().equals("")){
				return true;
			}			
		}else if(obj instanceof Integer){
			//判断Integer
			Integer objInt = (Integer) obj;
			if(objInt == 0){
				return true;
			}	
		}else if(obj instanceof Long){
			//判断Long
			Long objLong = (Long) obj;
			if(objLong == 0){
				return true;
			}	
		}
		return false;
	}
	/**
	 * 判断数组中元素是否包含空值元素
	 * 
	 * @param 给定的数组
	 * @return 判断给定的数组是否为空
	 */
	public static boolean containsNullElement(Object[] array) {
		if (array == null) {
			throw new RuntimeException("array为空，请检查!");
		}
		for (Object object : array) {
			if (object == null) {
				return true;
			}
		}
		return false;
	}

}
