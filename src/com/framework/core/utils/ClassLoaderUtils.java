package com.framework.core.utils;
/**
 * 负责返回ClassLoader
 * @author gaofeng
 *
 */
public class ClassLoaderUtils {
   /**
    * 返回ClassLoader
    * @return
    */
	public static ClassLoader getClassLoader() {
        //返回当前线程的ClassLoader
    	ClassLoader clsLoader = Thread.currentThread().getContextClassLoader();
        if(null == clsLoader) {
            //返回默认的系统ClassLoader
        	clsLoader = ClassLoader.getSystemClassLoader();
        }
        if(null==clsLoader ){
        	clsLoader = ClassLoaderUtils.class.getClassLoader();
        }
        return clsLoader;
    }
}
