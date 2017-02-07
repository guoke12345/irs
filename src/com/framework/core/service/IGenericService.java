package com.framework.core.service;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import com.framework.core.page.Pagination;

/**
 * 业务层Service的通用接口<BR>
 * <ul>
 * <li>业务层的所有自定义Service接口在创建时都要实现该接口。</li>
 * </ul>
 * <br>
 * <b>以创建IFooService接口为例讲解使用方法:</b><br/>
 * 
 * <pre>
 * public interface IFooService extends IGenericService&lt;Foo, String&gt; {
 * 	//此处定义特有的处理方法,如下面的test方法。
 * 	void testFoo();
 * }
 * </pre>
 * 
 * 上述代码中，IGenericService的T用实际的类型替代，如实体类Foo;ID使用Foo类的标识符属性替代<br/>
 * IFooService继承的方法中的T都会改变为具体的类型Foo，ID都会改变为String <br/>
 * 
 * @author gaof
 * @version 1.0
 * @since 1.0
 */
public interface IGenericService<T, ID extends Serializable> {

	/**
	 * 将实体对象持久化<br/> 根据实体的标识符属性值来判断保存还是更新操作。
	 * 
	 * @param entity
	 *            需要进行持久化操作的实体对象
	 * @return 持久化的实体对象
	 */
	void saveOrUpdate(T entity);

	/**
	 * 将瞬时对象进行持久化
	 * 
	 * @param entity
	 *            需要进行持久化操作的实体对象
	 * @return 持久化的实体对象
	 */
	void save(T entity);

	/**
	 * 更新对象<br/> 把脱管状态的对象进行持久化
	 * 
	 * @param entity
	 *            需要进行更新操作的实体对象
	 * @return 持久化的实体对象
	 */
	void update(T entity);

	/**
	 * 根据ID删除对象
	 * <ul>
	 * <li>先根据Id获取实体对象</li>
	 * <li>然后再进行删除操作</li>
	 * <ul>
	 * 
	 * @param id实体对象的标识符
	 */
	void remove(ID id);

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
	T findById(ID id);

	/**
	 * 查询得到所有的实体对象
	 * 
	 * @return 符合条件的List泛型对象
	 */
	List<T> findAll();

	/**
	 * 根据一组ID得到所有的实体对象
	 * 
	 * @param ID数组
	 * @param idName
	 *            实体对象id的名称,默认值“id”
	 * @return 符合条件的List泛型对象
	 */
	List<T> findAll(ID[] ids, String idName);

	/**
	 * 根据对象的属性和属性的值来得到满足条件的实体对象
	 * 
	 * @param exampleInstance
	 *            查询的条件
	 * @return 满足条件的实体对象
	 */
	List<T> findByExample(T exampleInstance);

	/**
	 * 利用DetachedCriteria进行条件查询
	 * 
	 * @param dc
	 *            DetachedCriteria类型对象
	 * @return 符合条件的List泛型对象
	 */
	List<T> findByCriteria(final DetachedCriteria dc);
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
	Pagination<T> findByPage(final int pageNo, final int pageSize,
			final DetachedCriteria dc);
	 /**
	  * 利用hql进行分页查询
	  * 
	  * @param pageNo  页码
	  * @param pageSize 记录数量
	  * @param hql    HQL语句
	  * @return  符合条件的Pagination分页对象
	  */
	 Pagination<T> findByPage(final int pageNo, final int pageSize, final String hql);
	 /**
	  * 利用hql进行动态分页查询
	  * 
	  * @param pageNo  页码
	  * @param pageSize 记录数量
	  * @param hql    HQL语句
	  * @param params[] 动态参数数组
	  * @return  符合条件的Pagination分页对象
	  */
	 Pagination<T> findByPage(final int pageNo, final int pageSize, final String hql,final Object[] params);
}
