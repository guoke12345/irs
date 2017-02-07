package com.framework.core.daos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import com.framework.core.page.Pagination;

/**
 * 数据访问层的通用接口<BR>
 * <ul>
 * <li>数据访问层的所有自定义Dao接口在创建时都要实现该接口。</li>
 * </ul>
 * <br>
 * <b>以创建IFooDao接口为例讲解使用方法:</b><br/>
 * 
 * <pre>
 * public interface IFooDao extends IGenericDao&lt;Foo, String&gt; {
 * 	//此处定义特有的处理方法,如下面的test方法。
 * 	void testFoo();
 * }
 * </pre>
 * 
 * 上述代码中，IGenericDao的T用实际的类型替代，如实体类Foo;ID使用Foo类的标识符属性替代<br/>
 * IFooDao继承的方法中的T都会改变为具体的类型Foo，ID都会改变为String <br/>
 * 
 * @author gaof
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public interface IGenericDao<T, ID extends Serializable> {

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
	 * 根据hql更新对象
	 * 
	 * @param hql
	 * @param params
	 *            占位参数
	 */
	void updateByHql(String hql, Object... params);

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
	 * 根据HQL构造动态查询
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            条件数组
	 * @return 符合条件的List泛型对象
	 */
	List<T> findByHql(final String hql, final Object... params);

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
	Pagination<T> findByPage(final int pageNo, final int pageSize,
			final String hql, final Object... params);
	/**
	 * 按照HQL查询某条记录总数
	 * 
	 * @param hql
	 *            查询hql
	 * @param params
	 *            条件数组
	 * @return 返回符合条件的记录总数
	 */
	public int findTotalRowsNum(final String hql, final Object... params);

	/**
	 * 按照HQL查询某个字段值总和
	 * @param sumParam  
	 * 			字段名    o.param
	 * @param hql
	 * 			查询hql
	 * @param params
	 * 			条件数组
	 * @return	返回符合条件的字段值的总和
	 */
	public int findSumNum(String sumParam, final String hql, final Object... params);
	// //////////////////////////////////////////
	// ////JDBC查询的各种方法
	// //////////////////////////////////////////

	/**
	 * 根据预编译sql语句,返回查询结果
	 * 
	 * @param sql
	 *            预编译Sql语句
	 * @param params
	 *            占位参数数组
	 * @return 结果集,每个Map对应一条记录
	 */
	List<Map<String, Object>> findByJdbc(String sql, Object... params);

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
	List<Map<String, Object>> findByJdbc(String sql, Object[] params,
			String[] fieldNames);
	/**
	 * 通过执行jdbc操作
	 * 
	 * @param sql
	 *            执行的预编译语句
	 * @param params
	 *            占位参数数组
	 * @return 1 成功 0 失败
	 */
	int updateByJdbc(String sql, Object... params);

	/**
	 * 批量进行delete,update,insert操作
	 * 
	 * @param sql
	 *            待执行的sql.
	 * @param params
	 *            参数数组,每行对应一维数组
	 * @return 更新的每个statment的数量.
	 */
	int[] batchByJdbc(String sql, Object[][] params);

	/**
	 * 批量进行save操作
	 * 
	 * @param list
	 *            待执行的对象list
	 * @return
	 */
	int[] batchSave(List list);

	/**
	 * 批量进行update操作
	 * 
	 * @param list
	 * @return
	 */
	int[] batchUpdate(List list);

	/**
	 * 根据预编译sql语句,分页返回查询结果
	 * 
	 * @param sql  预编译Sql语句
	 * @param params  占位参数数组
	 * @return pagination
	 */
	Pagination findByJdbcByPage(final int pageNo, final int pageSize,
			final String sql, final Object... params);
}
