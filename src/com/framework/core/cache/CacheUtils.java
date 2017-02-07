package com.framework.core.cache;
import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


/**
 * Cache实用类，用于在缓存中对对象进行CRUD等操作
 * @author gaofeng
 *
 */
public final class CacheUtils {
	/** 定义日志类 */
	private static final Log logger = LogFactory.getLog(CacheUtils.class);
    /**获取CacheManager实例*/
	private static CacheManager manager = CacheManager.getInstance();
	private CacheUtils(){
		
	}
	/**
	 * 往cacheName指定的缓存区域内添加数据
	 * @param key 数据的键
	 * @param value 数据的值
	 * @param cacheName 缓存区域的名称
	 */
	public  static void add(Object key, Object value, String cacheName) {
		if (logger.isDebugEnabled()) {
			logger.debug("CacheUtils-add(Object key, Object value, String cacheName)--in");
		}
		if (!manager.cacheExists(cacheName)) {
			if (logger.isDebugEnabled()) {
				logger.debug("名字为" + cacheName + "的Cache对象不存在，请创建!");
			}
			throw new RuntimeException("名字为" + cacheName + "的Cache对象不存在，请创建!");
		}
		// 获取Cache对象
		Cache cache = manager.getCache(cacheName);
		// 创建Element对象
		Element element = new Element(key, value);
		cache.put(element);
		if (logger.isDebugEnabled()) {
			logger.debug("添加键为"+key+"的元素！");
			logger.debug("CacheUtils-add(Object key, Object value, String cacheName)--out");
		}
	}

   /**
    * 从cacheName指定的缓存区域获取值
    * @param key 对象的键值
    * @param cacheName 缓存的区域名称
    * @return 缓存的对象
    */
	public static Object get(Object key,String cacheName) {
		if (logger.isDebugEnabled()) {
			logger.debug("CacheUtils-get(Object key,String cacheName)--in");
		}
		if (!manager.cacheExists(cacheName)) {
			if (logger.isDebugEnabled()) {
				logger.debug("名字为" + cacheName + "的Cache对象不存在，请创建!");
			}
			throw new RuntimeException("名字为" + cacheName + "的Cache对象不存在，请创建!");
		}
		// 获取Cache对象
		Cache cache = manager.getCache(cacheName);
		// 创建Element对象
		Element element = cache.get(key);
		if (logger.isDebugEnabled()) {
			logger.debug("CacheUtils-get(Object key,String cacheName)--out");
		}
		return element.getObjectValue();
	}
    /**
     * 从cacheName指定的缓存区域删除特定的对象
     * @param key 对象的键值
     * @param cacheName 缓存的区域名称
     */
	public static void remove(Object key,String cacheName) {
		if (logger.isDebugEnabled()) {
			logger.debug("CacheUtils-remove(Object key,String cacheName)--in");
		}
		if (!manager.cacheExists(cacheName)) {
			if (logger.isDebugEnabled()) {
				logger.debug("名字为" + cacheName + "的Cache对象不存在，请创建!");
			}
			throw new RuntimeException("名字为" + cacheName + "的Cache对象不存在，请创建!");
		}
		// 获取Cache对象
		Cache cache = manager.getCache(cacheName);
		// 创建Element对象
		cache.remove(key);
		
		if (logger.isDebugEnabled()) {
			logger.debug("删除键为"+key+"的元素！");
			logger.debug("CacheUtils-remove(Object key,String cacheName)--out");
		}
	}
    /**
     * 删除cacheName指定的缓存区域
     * @param cacheName 缓存区域名称
     */
	public static void remove(String cacheName){
		if (logger.isDebugEnabled()) {
			logger.debug("CacheUtils-remove(String cacheName)--in");
		}
		//移除Cache名称
		manager.removeCache(cacheName);
		if (logger.isDebugEnabled()) {
			logger.debug("CacheUtils-remove(String cacheName)--in");
		}
	}
	/**
	 * 移除所有的缓存区域
	 */
	public static void removeAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("CacheUtils-removeAll()--in");
		}
		//移除所有的Cache
         manager.removalAll();
		
		if (logger.isDebugEnabled()) {
			logger.debug("移除所有的Cache对象！");
			logger.debug("CacheUtils-removeAll()--out");
		}
	}
	/**
	 * 根据名称获取具体的Cache对象
	 * @param cacheName
	 * @return
	 */
	public static Cache getCache(String cacheName) {		
		Cache cache =  manager.getCache(cacheName);
		return cache;
	}
	///////////////////////////////////////////////////////
	////处理Hibernate本身处理的缓存
	//////////////////////////////////////////////////////
	/**
	 * 在二级缓存中清除实体对象
	 * @param clazz 清除对象的类型
	 * @param id 清除对象的id
	 * @param session 传入的session对象
	 */
	public static void evictEntity(Object obj,Serializable id,Session session){
		//获取二级缓存
		org.hibernate.Cache cache = session.getSessionFactory().getCache();
		//从二级缓存中清除对象
		cache.evictEntity(obj.getClass(), id);
		//从一级缓存中清除对象,不清空,不能造成数据的及时更新
		session.evict(obj);
	}
	/**
	 * 从二级缓存中清除关联集合对象
	 * @param cacheName cacheName的名称
	 * @param id  集合所关联的对象的id，one-to-many 的one处的id
	 * @param session 传入的session对象
	 */
	public static void evictCollection(String cacheName,Serializable id,Session session){
		//获取二级缓存
		org.hibernate.Cache cache = session.getSessionFactory().getCache();
		cache.evictCollection(cacheName, id);
	}
	/**
	 * 从二级缓存中清除所有关联集合对象
	 * @param cacheName cacheName的名称
	 * @param session 传入的session对象
	 */
	public static void evictCollectionRegion(String cacheName,Session session){
		//获取二级缓存
		org.hibernate.Cache cache = session.getSessionFactory().getCache();
		cache.evictCollectionRegion(cacheName);
	}
	/**
	 * 从二级缓存中清除所有集合对象
	 * @param clazz 类的名称
	 * @param session 传入的session对象
	 */
	public static void evictEntityRegion(String cacheName,Session session){
		//获取二级缓存
		org.hibernate.Cache cache = session.getSessionFactory().getCache();
		cache.evictEntityRegion(cacheName);
	}
	
}
