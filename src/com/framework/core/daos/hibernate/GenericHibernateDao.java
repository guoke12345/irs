package com.framework.core.daos.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.framework.core.daos.IGenericDao;
import com.framework.core.daos.jdbc.JdbcUtils;
import com.framework.core.page.Pagination;
import com.framework.core.utils.StringUtils;

/**
 * 数据访问层Dao的通用实现类<BR>
 * <ul>
 * <li>数据访问层的所有自定义Dao实现在创建时都要继承该类,并实现自定义的Dao接口。</li>
 * </ul>
 * <br>
 * <b>以创建IFooDao接口的实现类FooDaoImpl为例讲解使用方法:</b> <br/>
 * 
 * <pre>
 * public class FooDaoImpl extends GenericHibernateDao&lt;Foo, String&gt;
 * 	implements IGenericDao {
 * 构造方法
 * public FooDaoImpl() {
 *   //该方法用于初始化FooDaoImpl中的T类型,该处代码必不可少。
 *    //如果需要设置查询缓存
 *     super(Foo.class);
 *    //如果需要设置查询缓存
 *    this.setCacheQueries(true);
 * }
 *  //实现接口的其他方法
 * public void test() {		
 * 	//处理具体业务方法
 * }
 * 
 * </pre>
 * 
 * 上述代码中，FooDaoImpl的T用实际的类型替代，ID使用该类的标识符属性替代<br/>
 * FooDaoImpl继承的方法中的T都会改变为具体的类型Foo，ID都会改变为String <br/>
 * 
 * @author gaof
 * @version 1.0
 * @since 2011-04-06
 */
@SuppressWarnings("unchecked")
public class GenericHibernateDao<T, ID extends Serializable> extends
		HibernateDaoSupport implements IGenericDao<T, ID> {
	// 日志记录
	private static Log logger = LogFactory.getLog(GenericHibernateDao.class);
	// 保持实体对象类的类型
	private Class<T> entityClass;
	/**
	 * 设置查询缓存
	 */
	private boolean cacheQueries = false;
	/**
	 * 设置查询缓存区域
	 */
	private String queryCacheRegion;
	/**
	 * 设置fetch大小
	 */
	private int fetchSize = 0;

	/**
	 * 构造方法,得到子类传过来的对象类型
	 */
	public GenericHibernateDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 通过父类获取子类的类型
	 */
	public GenericHibernateDao() {
		Class<?> c = getClass();
		Type t = c.getGenericSuperclass();
		if (!(t instanceof ParameterizedType)) {
			throw new IllegalArgumentException(c + " 没有指定具体的泛型类型");
		}
		this.entityClass = (Class<T>) ((ParameterizedType) t)
				.getActualTypeArguments()[0];
	}

	// ///////////////////////////
	// ///getter/setter方法
	// ///////////////////////////
	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setCacheQueries(boolean cacheQueries) {
		this.cacheQueries = cacheQueries;
	}

	public boolean isCacheQueries() {
		return this.cacheQueries;
	}

	public void setQueryCacheRegion(String queryCacheRegion) {
		this.queryCacheRegion = queryCacheRegion;
	}

	public String getQueryCacheRegion() {
		return this.queryCacheRegion;
	}

	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	public int getFetchSize() {
		return this.fetchSize;
	}

	/**
	 * 为Query对象设置查询缓存,查询区域和抓取大小.
	 * 
	 * @param query
	 *            查询对象
	 */
	public void prepareQuery(Query query) {
		if (isCacheQueries()) {
			query.setCacheable(true);
			if (getQueryCacheRegion() != null) {
				query.setCacheRegion(getQueryCacheRegion());
			}
		}
		if (getFetchSize() > 0) {
			query.setFetchSize(getFetchSize());
		}
	}

	/**
	 * 为criteria对象设置查询缓存,查询区域和抓取大小.
	 * 
	 * @param criteria
	 *            查询对象
	 */
	private void prepareCriteria(Criteria criteria) {
		if (isCacheQueries()) {
			criteria.setCacheable(true);
			if (getQueryCacheRegion() != null) {
				criteria.setCacheRegion(getQueryCacheRegion());
			}
		}
		if (getFetchSize() > 0) {
			criteria.setFetchSize(getFetchSize());
		}
	}

	// ////////////////////////////////////////////
	// //基础操作
	// ////////////////////////////////////////////

	/**
	 * 将实体对象持久化<br/> 根据实体的标识符属性值来判断保存还是更新操作。
	 * 
	 * @param entity
	 *            需要进行持久化操作的实体对象
	 * @return 持久化的实体对象
	 */
	public void saveOrUpdate(T entity) {
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-saveOrUpdate()--in");
		}
		if (entity == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("保存/更新的实体对象为null,请确认！");
			}
			throw new RuntimeException("保存/更新的实体对象为null,请确认！");
		}
		this.getHibernateTemplate().saveOrUpdate(entity);

		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-saveOrUpdate()--out");
		}
	}

	/**
	 * 将瞬时对象进行持久化
	 * 
	 * @param entity
	 *            需要进行持久化操作的实体对象
	 * @return 持久化的实体对象
	 */
	public void save(T entity) {
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-save()--in");
		}
		if (entity == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("保存的实体对象为null,请确认！");
			}
			throw new RuntimeException("保存的实体对象为null,请确认！");
		}
		this.getHibernateTemplate().save(entity);

		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-save()--out");
		}
	}

	/**
	 * 更新对象<br/> 把脱管状态的对象进行持久化
	 * 
	 * @param entity
	 *            需要进行更新操作的实体对象
	 * @return 持久化的实体对象
	 */
	public void update(T entity) {
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-update()--in");
		}
		if (entity == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("更新的实体对象为null,请确认！");
			}
			throw new RuntimeException("更新的实体对象为null,请确认！");
		}
		this.getHibernateTemplate().update(entity);
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-update()--out");
		}
	}

	/**
	 * 根据ID删除对象，在实际应用中不进行实际的删除操作，而是更新操作
	 * <ul>
	 * <li>先根据Id获取实体对象</li>
	 * <li>然后再进行删除操作</li>
	 * <ul>
	 * 
	 * @param id实体对象的标识符
	 */
	public void remove(ID id) {
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-remove()--in");
		}
		if (id == null) {
			throw new RuntimeException("id值不能为空,请检查!");
		}
		// 先获取对象，保证该对象存在且处于持久化状态
		Object obj = this.getHibernateTemplate().get(entityClass, id);
		// 把持久化状态的对象进行删除
		this.getHibernateTemplate().delete(obj);
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-remove()--out");
		}
	}

	/**
	 * 通过ID来得到实体对象<br/>
	 * <ul>
	 * <li>ID为实体对象的标识符属性。</li>
	 * <li>T为泛型格式</li>
	 * <ul>
	 * 
	 * @param id实体对象的标识符
	 * @return 该主键值对应的实体对象
	 */
	public T findById(ID id) {
		// 根据Id获取实例
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 查询得到所有的实体对象
	 * 
	 * @return 符合条件的List泛型对象
	 */
	public List<T> findAll() {
		return findByCriteria();
	}

	/**
	 * 根据一组ID得到所有的实体对象
	 * 
	 * @param ID数组
	 * @param idName
	 *            实体对象id的名称,默认值“id”
	 * @return 符合条件的List泛型对象
	 */
	public List<T> findAll(final ID[] ids, final String idName) {

		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-findAll(ids,idName)--in");
		}
		if (ids == null) {
			throw new RuntimeException("ids为null，请检查！");
		}
		List<T> list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// 根据具体类型，返回Criteria实例
						Criteria cri = session.createCriteria(entityClass);
						if (StringUtils.isEmpty(idName)) {
							// 添加in操作,默认为id
							cri.add(Restrictions.in("id", ids));
						} else {
							// 添加in操作
							cri.add(Restrictions.in(idName, ids));
						}
						// 返回符合条件的列表
						return cri.list();

					}
				});
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-findAll(ids,idName)--out");
		}
		// 返回查询列表
		return list;
	}

	/**
	 * 批量保存
	 */
	public int[] batchSave(final List list) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			// 执行
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				for (int i = 0; i < list.size(); i++) {
					Object obj = list.get(i);
					session.save(obj);
					// flush 插入数据和释放内存:
					session.flush();
					session.evict(obj);
				}
				return null;
			}
		});
		return null;
	}

	/**
	 * 批量更新
	 */
	public int[] batchUpdate(final List list) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			// 执行
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				for (int i = 0; i < list.size(); i++) {
					Object obj = list.get(i);
					session.update(obj);
					// flush 插入数据和释放内存:
					session.flush();
					session.evict(obj);
				}
				return null;
			}
		});
		return null;
	}

	// ///////////////////////////////////////////////////////
	// /////所有HQL的操作
	// ////////////////////////////////////////////////////////

	/**
	 * 根据HQL构造动态查询
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            条件数组
	 * @return 符合条件的List泛型对象
	 */
	public List<T> findByHql(final String hql, final Object... params) {

		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-findByHql(hql,params)--in");
		}
		if (StringUtils.isEmpty(hql)) {
			throw new RuntimeException("Hql为空，请确认！");
		}
		// 利用回调结果返回list结果集
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// 根据session对象和hql获取Query对象
						Query query = session.createQuery(hql);
						// 设置站位参数，从0开始
						for (int i = 0; i < params.length; i++) {
							query.setParameter(i, params[i]);
						}
						// 返回列表
						return query.list();
					}
				});
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-findByHql(hql,params)--out");
		}
		// 返回列表
		return list;
	}

	/**
	 * 根据hql更新对象
	 * 
	 * @param hql
	 * @param params
	 *            占位参数
	 */
	public void updateByHql(final String hql, final Object... params) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			// 执行
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
				query.executeUpdate();
				return null;
			}
		});

	}

	/**
	 * 利用hql进行动态分页查询
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            记录数量
	 * @param hql
	 *            HQL语句
	 * @param params[]
	 *            动态参数数组
	 * @return 符合条件的Pagination分页对象
	 */
	public Pagination<T> findByPage(final int pageNo, final int pageSize,
			final String hql, final Object... params) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("GenericHibernateDao-findByPage(pageNo,pageSize,hql,params)--in");
		}
		if (StringUtils.isEmpty(hql)) {
			throw new RuntimeException("Hql为空，请确认！");
		}

		if (params == null) {
			throw new RuntimeException("params为null,请确认！");
		}
		// 获取总记录数
		int totalRowsNum = findTotalRowsNum(hql, params);
		// 创建Pagination类型的对象
		final Pagination<T> pagination = new Pagination<T>();
		// 设置最大页数
		pagination.setMaxElements(totalRowsNum);
		// 设置最大页数
		pagination.setPageSize(pageSize);
		// 利用回调结果返回结果集
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// 根据session和Hql获取Query对象
						Query query = session.createQuery(hql);
						// 设置站位参数，从0开始
						for (int i = 0; i < params.length; i++) {
							query.setParameter(i, params[i]);
						}
						// 设置页码
						int begin = (pageNo - 1) * pageSize;
						if (begin >= 0)
							query.setFirstResult(begin);
						// 设置最大记录数
						if (pageSize > 0)
							query.setMaxResults(pageSize);
						// 返回结果集
						return query.list();
					}
				});
		// 把记过集封装在Pagination对象中
		pagination.getList().addAll(list);
		if (logger.isDebugEnabled()) {
			logger
					.debug("GenericHibernateDao-findAllByPage(pageNo,pageSize,dc)--out");
		}
		// 返回Pagination对象
		return pagination;
	}

	/**
	 * 按照HQL查询某条记录总数
	 * 
	 * @param hql
	 *            查询hql
	 * @param params
	 *            条件数组
	 * @return 返回符合条件的记录总数
	 */
	public int findTotalRowsNum(final String hql, final Object... params) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("GenericHibernateDao-findTotalRowsNum(hql,params)--in");
		}
		// 返回结果
		int result = 0;
		// 封装sql
		final StringBuffer buffer = new StringBuffer();
		buffer.append("select count(*) ");
		buffer.append(hql);
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					// 创建回调接口
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// 根据传入的HQL语句，获取Query对象
						Query query = session.createQuery(buffer.toString());
						// 设置站位参数，从0开始
						for (int i = 0; i < params.length; i++) {
							query.setParameter(i, params[i]);
						}
						// 返回结果集
						return query.list();
					}
				});

		if (list == null || list.isEmpty()) {
			// 如果结果集为空或null，返回0
			if (logger.isDebugEnabled()) {
				logger.debug("GenericHibernateDao-findTotalRowsNum(hql)--out");
			}
			return result;
		}
		result = NumberUtils.toInt(list.get(0).toString(), 0);
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-findTotalRowsNum(hql)--out");
		}
		// 默认返回
		return result;
	}

	/**
	 * 按照HQL查询某个字段值总和
	 * @param sumParam  
	 * 			字段名    o.param   该字段在数据库中的类型为数值型
	 * @param hql
	 * 			查询hql
	 * @param params
	 * 			条件数组
	 * @return	返回符合条件的字段值的总和
	 */
	public int findSumNum(String sumParam, final String hql, final Object... params) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("GenericHibernateDao-findTotalRowsNum(hql,params)--in");
		}
		// 返回结果
		int result = 0;
		// 封装sql
		final StringBuffer buffer = new StringBuffer();
		buffer.append("select SUM(").append(sumParam).append(") ");
		buffer.append(hql);
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					// 创建回调接口
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// 根据传入的HQL语句，获取Query对象
						Query query = session.createQuery(buffer.toString());
						// 设置站位参数，从0开始
						for (int i = 0; i < params.length; i++) {
							query.setParameter(i, params[i]);
						}
						// 返回结果集
						return query.list();
					}
				});
		if (list == null || list.isEmpty()) {
			// 如果结果集为空或null，返回0
			if (logger.isDebugEnabled()) {
				logger.debug("GenericHibernateDao-findTotalRowsNum(hql)--out");
			}
			return result;
		}
		if(list.get(0) == null || list.get(0).equals("")){
			if (logger.isDebugEnabled()) {
				logger.debug("GenericHibernateDao-findTotalRowsNum(hql)--out");
			}
		return result;	
		}
		result = NumberUtils.toInt(list.get(0).toString(), 0);
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-findTotalRowsNum(hql)--out");
		}
		// 默认返回
		return result;
	}

	// ////////////////////////////////////////////////////////
	// ////所有Criteria操作
	// ///////////////////////////////////////////////////////////
	/**
	 * 利用DetachedCriteria进行分页查询
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            记录数量
	 * @param dc
	 *            DetachedCriteria对象
	 * @return 符合条件的Pagination分页对象
	 */
	public Pagination<T> findByPage(final int pageNo, final int pageSize,
			final DetachedCriteria dc) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("GenericHibernateDao-findByPage(pageNo,pageSize,dc)--in");
		}
		if (dc == null) {
			throw new RuntimeException("DetachedCriteria类型的dc对象为空，请确认！");
		}
		// 获取总记录数
		int totalRowsNum = findTotalRowsNum(dc);
		// 创建Pagination类型的对象
		final Pagination<T> pagination = new Pagination<T>();
		// 设置符合条件的最大记录数
		pagination.setMaxElements(totalRowsNum);
		// 设置最大页数
		pagination.setPageSize(pageSize);
		// 利用回调结果返回结果集
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// 根据Session和dc对象，获取Criteria对象
						Criteria cri = dc.getExecutableCriteria(session);
						// 设置查询缓存
						cri.setProjection(null);
						// 设置页码
						int begin = (pageNo - 1) * pageSize;
						if (begin >= 0)
							cri.setFirstResult(begin);
						// 设置最大记录数
						if (pageSize > 0)
							cri.setMaxResults(pageSize);
						// 返回结果集
						return cri.list();
					}
				});
		// 把记过集封装在Pagination对象中
		pagination.getList().addAll(list);
		if (logger.isDebugEnabled()) {
			logger
					.debug("GenericHibernateDao-findAllByPage(pageNo,pageSize,dc)--out");
		}
		// 返回Pagination对象
		return pagination;
	}

	/**
	 * 按照DetachedCriteria查询某个实体对象的总记录总数
	 * 
	 * @param dc
	 *            DetachedCriteria对象
	 * @return 符合条件的记录的总数
	 */
	private int findTotalRowsNum(final DetachedCriteria dc) {
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-findTotalRowsNum(dc)--in");
		}
		// 返回结果
		int result = 0;
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					// 创建回调接口
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// 根据Session和dc对象，获取Criteria对象
						Criteria cri = dc.getExecutableCriteria(session);
						// 设置分组行数
						cri.setProjection(Projections.rowCount());
						// 返回查询结果
						return cri.list();
					}
				});

		if (list == null || list.isEmpty()) {
			// 如果结果集为空或null，返回0
			if (logger.isDebugEnabled()) {
				logger.debug("GenericHibernateDao-findTotalRowsNum(dc)--out");
			}
			return result;
		}
		// 获取返回结果
		result = NumberUtils.toInt(list.get(0).toString(), 0);
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-findTotalRowsNum(dc)--out");
		}
		// 默认返回
		return result;
	}

	/**
	 * 根据Crierion查询
	 * <ul>
	 * <li>Crierion可以为单个条件。</li>
	 * <li>Crierion可以为数组形式</li>
	 * <ul>
	 * 
	 * @param criterion
	 * @return 符合条件的List泛型对象
	 */
	private List<T> findByCriteria(Criterion... criterion) {
		// 根据传入的类型，获取Criteria对象
		Criteria criteria = getSession().createCriteria(getEntityClass());
		// 添加传入的criterion条件
		if (criterion != null) {
			for (Criterion c : criterion) {
				criteria.add(c);
			}
		}
		// 设置查询缓存和区域
		this.prepareCriteria(criteria);
		// 返回符合条件的记录列表
		return criteria.list();
	}

	/**
	 * 根据对象的属性和属性的值来得到满足条件的实体对象
	 * 
	 * @param exampleInstance
	 *            查询的条件
	 * @return 满足条件的实体对象
	 */
	public List<T> findByExample(T exampleInstance) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("GenericHibernateDao-findByExample(exampleInstance)--in");
		}
		if (exampleInstance == null) {
			throw new RuntimeException(
					"QBE的实例条件为null，请确保exampleInstance对象不为null!");
		}
		// 根据传入的具体类型的实例获取Example对象
		Example example = Example.create(exampleInstance);
		example.ignoreCase()// 忽略大小写
				.enableLike(MatchMode.ANYWHERE)// 设置like，匹配的模式是任何地方 类似于“like
				// %value%”
				.excludeZeroes();// QBE默认会把属性值为null的属性排除在外,属性值为0的属性不排除，利用该方法可以排除属性值为0的属性
		if (logger.isDebugEnabled()) {
			logger
					.debug("GenericHibernateDao-findByExample(exampleInstance)--out");
		}
		return findByCriteria(example);
	}

	/**
	 * 利用DetachedCriteria进行条件查询
	 * 
	 * @param dc
	 *            DetachedCriteria类型对象
	 * @return 符合条件的List泛型对象
	 */
	public List<T> findByCriteria(final DetachedCriteria dc) {
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-findAll(dc)--in");
		}
		if (dc == null) {
			throw new RuntimeException("DetachedCriteria类型的dc对象为空，请确认！");
		}
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// 根据Session和dc对象，获取Criteria对象
						Criteria cri = dc.getExecutableCriteria(session);
						// 返回结果集
						return cri.list();
					}
				});
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-findAll(dc)--out");
		}
		// 返回结果集
		return list;
	}

	// ////////////////////////////////////////////////
	// //////所有JDBC操作
	// ////////////////////////////////////////////////
	/**
	 * 根据预编译sql语句,返回查询结果
	 * 
	 * @param sql
	 *            预编译Sql语句
	 * @param params
	 *            占位参数数组
	 * @return 结果集,每个Map对应一条记录
	 */
	public List<Map<String, Object>> findByJdbc(final String sql,
			final Object... params) {
		// 结果集合
		final List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>(
				0);
		// 执行查询
		this.getHibernateTemplate().executeFind(new HibernateCallback() {
			// 执行
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 执行JDBC查询
				session.doWork(new Work() {
					public void execute(Connection conn) throws SQLException {
						List<Map<String, Object>> list = JdbcUtils
								.executeQuery(conn, sql, params);
						// 把结果保存到mapList中
						mapList.addAll(list);
					}
				});
				return null;
			}

		});
		return mapList;
	}

	/**
	 * 根据预编译sql语句和别名列表,返回查询结果
	 * 
	 * @param sql
	 *            预编译Sql语句
	 * @param params
	 *            占位参数数组
	 * @param fieldNames
	 *            别名数组
	 * @return 结果集,每个Map对应一条记录
	 */
	public List<Map<String, Object>> findByJdbc(final String sql,
			final Object[] params, final String[] fieldNames) {
		// 结果集合
		final List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>(
				0);
		// 执行查询
		this.getHibernateTemplate().executeFind(new HibernateCallback() {
			// 执行
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 执行JDBC查询
				session.doWork(new Work() {
					public void execute(Connection conn) throws SQLException {
						List<Map<String, Object>> list = JdbcUtils
								.executeQuery(conn, sql, params, fieldNames);
						// 把结果保存到mapList中
						mapList.addAll(list);
					}
				});
				return null;
			}

		});
		return mapList;
	}

	/**
	 * 根据预编译sql语句,分页返回查询结果
	 * 
	 * @param sql
	 *            预编译Sql语句
	 * @param params
	 *            占位参数数组
	 * @return pagination
	 */
	public Pagination findByJdbcByPage(final int pageNo, final int pageSize,
			final String sql, final Object... params) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("GenericHibernateDao-findByJdbcByPage(pageNo, pageSize, sql, params)--in");
		}
		// 结果集合
		final List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>(
				0);
		// 获取总记录数
		int totalRowsNum = findTotalRowsCount(sql, params);
		// 创建Pagination类型的对象
		final Pagination pagination = new Pagination();
		// 设置符合条件的最大记录数
		pagination.setMaxElements(totalRowsNum);
		// 设置最大页数
		pagination.setPageSize(pageSize);
		// 利用回调结果返回结果集
		this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				// 执行JDBC查询
				session.doWork(new Work() {
					public void execute(Connection conn) throws SQLException {
						String dbName = conn.getMetaData()
								.getDatabaseProductName();
						List<Map<String, Object>> list = JdbcUtils.queryByPage(
								conn, sql, pageNo, pageSize, dbName, params);
						// 把结果保存到mapList中
						mapList.addAll(list);
					}
				});
				return null;
			}
		});
		// 把记过集封装在Pagination对象中
		pagination.getList().addAll(mapList);
		if (logger.isDebugEnabled()) {
			logger
					.debug("GenericHibernateDao-findAllByPage(pageNo,pageSize,dc)--out");
		}
		// 返回Pagination对象
		return pagination;
	}

	/**
	 * 返回行数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	private int findTotalRowsCount(final String sql, final Object... params) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("GenericHibernateDao-findTotalRowsCount(sql,params)--in");
		}
		// 返回结果
		int result = 0;
		// 结果集合
		final List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>(
				0);
		final StringBuffer buffer = new StringBuffer();
		buffer.append("select count(*) NUM_ from (");
		buffer.append(sql);
		buffer.append(")");
		// 利用回调结果返回结果集
		this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 执行JDBC查询
				session.doWork(new Work() {
					public void execute(Connection conn) throws SQLException {
						List<Map<String, Object>> list = JdbcUtils
								.executeQuery(conn, buffer.toString(), params);
						// 把结果保存到mapList中
						mapList.addAll(list);
					}
				});
				return null;
			}
		});
		if (mapList == null || mapList.isEmpty()) {
			// 如果结果集为空或null，返回0
			if (logger.isDebugEnabled()) {
				logger
						.debug("GenericHibernateDao-findTotalRowsCount(sql,params)--out");
			}
			return result;
		}
		String num = mapList.get(0).get("NUM_").toString();
		result = NumberUtils.toInt(num, 0);
		if (logger.isDebugEnabled()) {
			logger.debug("GenericHibernateDao-findTotalRowsCount(hql)--out");
		}
		// 默认返回
		return result;
	}

	/**
	 * 通过jdbc进行更新操作
	 * 
	 * @param sql
	 *            执行的sql语句
	 * @return 1 成功 0 失败
	 */
	public int updateByJdbc(final String sql, final Object... params) {
		// 结果
		final Map<String, Integer> result = new HashMap<String, Integer>();
		// 执行更新操作
		this.getHibernateTemplate().execute(new HibernateCallback() {
			// 执行
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 执行JDBC更新操作
				session.doWork(new Work() {
					public void execute(Connection conn) throws SQLException {
						int r = JdbcUtils.executeUpdate(conn, sql, params);
						result.put("result", r);
					}
				});
				return null;
			}

		});
		return result.get("result");
	}

	/**
	 * 批量进行delete,update,insert操作
	 * 
	 * @param sql
	 *            待执行的sql.
	 * @param params
	 *            参数数组,每行对应一维数组
	 * @return 更新的每个statment的数量.
	 */
	public int[] batchByJdbc(final String sql, final Object[][] params) {
		// 存放结果
		final Map<String, int[]> result = new HashMap<String, int[]>();
		// 执行更新操作
		this.getHibernateTemplate().execute(new HibernateCallback() {
			// 执行
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 执行JDBC更新操作
				session.doWork(new Work() {
					public void execute(Connection conn) throws SQLException {
						int[] r = JdbcUtils.batch(conn, sql, params);
						result.put("result", r);
					}

				});
				return null;
			}

		});
		return result.get("result");
	}
	
}
