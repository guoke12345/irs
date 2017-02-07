package com.framework.core.cache.ehcache;

import java.util.Properties;
import java.net.URL;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CacheProvider;
import org.hibernate.cache.EhCache;
import org.hibernate.cache.Timestamper;
import org.hibernate.cfg.Environment;
import org.hibernate.util.StringHelper;
import org.hibernate.util.ConfigHelper;

/**
 * Hibernate的Cache插件
 *
 * 使用方法如下:
 * <code>hibernate.cache.provider_class=com.framework.core.cache.ehcache.EhCacheProvider</code>
 * 使得Hibernate能够提供自身的二级缓存
 * 上述用法，在Hibernate3.x或更高版本
 * <p>
 * 本代码修改自org.hibernate.cache.EhCacheProvider，实现CacheManager的单例模式
 * @author Greg Luck
 * @author Emmanuel Bernard
 * @author gaof
 */
@SuppressWarnings("deprecation")
public class EhCacheProvider implements CacheProvider {

    private static final Logger log = LoggerFactory.getLogger(EhCacheProvider.class);

	private CacheManager manager;

    /**
     * 创建一个Cache.
     * <p>
     * 即使该方法提供了属性，也没有被使用到.
     * 对于EHcache而言，属性在ehcache.xml文件中配置.
     * Configuration 会从 ehcache.xml 读取相关信息，来声明一个cache对象.
     *如果指定name的Cache不存在,就会创建一个与defaultCache配置相同的Cache对象.
     * @param name 缓存的名称. 必须与ehcache.xml中的某个名称匹配
     * @param properties 没有被使用
     * @return 一个新创建的cache会被初始化
     * @throws CacheException inter alia,如果名字相同的cache已经存在，则会抛出异常
     */
    public Cache buildCache(String name, Properties properties) throws CacheException {
	    try {
            net.sf.ehcache.Cache cache = manager.getCache(name);
            if (cache == null) {
                log.warn("Could not find configuration [" + name + "]; using defaults.");
                manager.addCache(name);
                cache = manager.getCache(name);
                log.debug("started EHCache region: " + name);
            }
            //返回包装好的EhCache对象
            return new EhCache(cache);
	    } catch (net.sf.ehcache.CacheException e) {
            throw new CacheException(e);
        }
    }

    /**
     * 返回下一个时间戳 timestamp.
     */
    public long nextTimestamp() {
        return Timestamper.next();
    }

	/**
	 * 在SessionFactory创建期间,通过回调接口来执行任何必要的cache实现的初始化操作
	 * @param properties 当前的配置设置.
	 */
	public void start(Properties properties) throws CacheException {
		if (manager != null) {
            log.warn("Attempt to restart an already started EhCacheProvider. Use sessionFactory.close() " +
                    " between repeated calls to buildSessionFactory. Using previously created EhCacheProvider." +
                    " If this behaviour is required, consider using net.sf.ehcache.hibernate.SingletonEhCacheProvider.");
            return;
        }
        try {
            String configurationResourceName = null;
            if (properties != null) {
                configurationResourceName = (String) properties.get( Environment.CACHE_PROVIDER_CONFIG );
            }
            if ( StringHelper.isEmpty( configurationResourceName ) ) {
                //实现CacheManager的单例模式，
            	manager = CacheManager.getInstance();
            } else {
                //实现单例模式调用
            	URL url = loadResource(configurationResourceName);
                manager = CacheManager.create(url);
            }
        } catch (net.sf.ehcache.CacheException e) {
			if (e.getMessage().startsWith("Cannot parseConfiguration CacheManager. Attempt to create a new instance of " +
                    "CacheManager using the diskStorePath")) {
                throw new CacheException("Attempt to restart an already started EhCacheProvider. Use sessionFactory.close() " +
                    " between repeated calls to buildSessionFactory. Consider using net.sf.ehcache.hibernate.SingletonEhCacheProvider."
						, e );
            } else {
                throw e;
            }
        }
	}
    //返回资源的URL
	private URL loadResource(String configurationResourceName) {
		URL url = ConfigHelper.locateConfig( configurationResourceName );
        if (log.isDebugEnabled()) {
            log.debug("Creating EhCacheProvider from a specified resource: "
                    + configurationResourceName + " Resolved to URL: " + url);
        }
        return url;
    }

	/**
	 * 在SessionFacotry期间，利用回调接口执行cache的一些必要的清理化操作。
	 * during SessionFactory.close().
	 */
	public void stop() {
		if (manager != null) {
            manager.shutdown();
            manager = null;
        }
	}
    
	public boolean isMinimalPutsEnabledByDefault() {
		return false;
	}

}
