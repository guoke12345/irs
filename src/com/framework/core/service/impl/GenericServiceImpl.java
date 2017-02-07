package com.framework.core.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import com.framework.core.daos.IGenericDao;
import com.framework.core.exception.BaseAppRuntimeException;
import com.framework.core.page.Pagination;
import com.framework.core.service.IGenericService;

/**
 * 业务层Service的通用实现类<BR>
 * <ul>
 * <li>业务层的所有自定义Service实现在创建时都要继承该类，并实现自定义的Service接口。</li>
 * </ul>
 * <br>
 * <b>以创建IFooService接口的实现类FooServiceImpl为例讲解使用方法:</b> <br/>
 * 
 * <pre>
 * public class FooServiceImpl extends GenericServiceImpl&lt;Foo, String&gt;
 * 	implements IFooService {
 * //构造方法
 * public FooServiceImpl() {
 *   //该方法用于初始化GenericServiceImpl中的T类型,该处代码必不可少。
 *     super(Foo.class);
 * }
 *  //实现接口的其他方法
 * public void test() {		
 * 	//处理具体业务方法
 * }
 * 
 * </pre>
 * 
 * 上述代码中，GenericService的T用实际的类型替代，ID使用该类的标识符属性替代<br/>
 * FooService继承的方法中的T都会改变为具体的类型Foo，ID都会改变为String <br/>
 * 
 * @author gaof
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public class GenericServiceImpl<T, ID extends Serializable> implements
		IGenericService<T, ID> {

	/* 通用DAO接口 */
	private IGenericDao genericDao;
	// 保持实体对象类的类型,用于替换T
	private Class<T> entityClass;

	/**
	 * 构造方法,得到子类传过来的对象类型
	 */
	public GenericServiceImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 空构造方法,直接得到子类的类型
	 */
	public GenericServiceImpl() {
		Class<?> c = getClass();
		Type t = c.getGenericSuperclass();
		if (!(t instanceof ParameterizedType)) {
			throw new IllegalArgumentException(c + " 没有指定具体的泛型类型");
		}
		this.entityClass = (Class<T>) ((ParameterizedType) t)
				.getActualTypeArguments()[0];
	}

	/**
	 * 得到持久化对象的类型
	 * 
	 * @return 持久化类的类型
	 */
	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 设置genericDAO
	 * 
	 * @param genericDAO
	 */
	public IGenericDao getGenericDao() {
		return genericDao;
	}

	/**
	 * 获取genericDAO对象
	 * 
	 * @return
	 */
	public void setGenericDao(IGenericDao genericDao) {
		this.genericDao = genericDao;
	}

	/**
	 * 将实体对象持久化<br/> 根据实体的标识符属性值来判断保存还是更新操作。
	 * 
	 * @param entity
	 *            需要进行持久化操作的实体对象
	 * @return 持久化的实体对象
	 */
	public void saveOrUpdate(T entity) {
		try {
			genericDao.saveOrUpdate(entity);
		} catch (Exception ex) {
			throw new BaseAppRuntimeException(ex.getMessage(), ex);
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
		try {
			genericDao.save(entity);
		} catch (Exception ex) {
			throw new BaseAppRuntimeException(ex.getMessage(), ex);
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
		try {
			genericDao.update(entity);
		} catch (Exception ex) {
			throw new BaseAppRuntimeException(ex.getMessage(), ex);
		}
	}

	/**
	 * 根据ID删除对象
	 * <ul>
	 * <li>先根据Id获取实体对象</li>
	 * <li>然后再进行删除操作</li>
	 * <ul>
	 * 
	 * @param id实体对象的标识符
	 */
	public void remove(ID id) {
		try {
			genericDao.remove(id);
		} catch (Exception ex) {
			throw new BaseAppRuntimeException(ex.getMessage(), ex);
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

		return (T) genericDao.findById(id);
	}

	/**
	 * 根据一组ID得到所有的实体对象
	 * 
	 * @param ID数组
	 * @return 符合条件的List泛型对象
	 */
	public List<T> findAll(ID[] ids, String idName) {

		return genericDao.findAll(ids, idName);
	}

	/**
	 * 查询得到所有的实体对象
	 * 
	 * @return 符合条件的List泛型对象
	 */
	public List<T> findAll() {

		return genericDao.findAll();
	}

	/**
	 * 根据对象的属性和属性的值来得到满足条件的实体对象
	 * 
	 * @param exampleInstance
	 *            查询的条件
	 * @return 满足条件的实体对象
	 */
	public List<T> findByExample(T exampleInstance) {

		return genericDao.findByExample(exampleInstance);
	}

	public List<T> findByCriteria(DetachedCriteria dc) {
		return genericDao.findByCriteria(dc);
	}

	public Pagination<T> findByPage(int pageNo, int pageSize,
			DetachedCriteria dc) {
		return genericDao.findByPage(pageNo, pageSize, dc);
	}

	public Pagination<T> findByPage(int pageNo, int pageSize, String hql) {
		return genericDao.findByPage(pageNo, pageSize, hql);
	}

	public Pagination<T> findByPage(int pageNo, int pageSize, String hql,
			Object[] params) {
		return genericDao.findByPage(pageNo, pageSize, hql, params);
	}
}
