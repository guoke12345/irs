package dao.impl;


import java.util.List;

import pojo.Nav;


import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;
import dao.INavDao;



public class NavDaoImpl extends GenericHibernateDao<Nav,Integer>
implements INavDao{
	private NavDaoImpl(){
		super(Nav.class);//实现User类的重载
	}
	/**
	 * 添加方法
	 */
	public void addNav(Nav nav) {
		this.save(nav);
	}
	/**
	 * 获取分页列表
	 */
	public Pagination<Nav> navPg(int pageno, int pageSize) {
		String hql="from Nav as nav"; 
		return this.findByPage(pageno, pageSize, hql);
	}
	public List<Nav> findBynavName(String navName) {
		String hql="from Nav as nav where nav.navName=? ";
		Object [] params={navName};
		return this.findByHql(hql, params);
	}
	
	public List<Nav> findBynav(){
		String hql="from Nav as nav where nav.navName=? or nav.navName=? ";
		Object [] params={"科学研究","通知公告"};
		return this.findByHql(hql, params);
	}
	
	public Nav nav(int id) {
		return this.findById(id);
	}
	/**
	 * 通过id查询列表
	 */
	public List<Nav> findByoneName_id(int id) {
		String hql = "from Nav as nav where nav.id?";
		return this.findByHql(hql);
	}
	/**
	 * 删除方法
	 */
	public void deleteByid(Integer id) {
		this.remove(id);
	}
	/**
	 * 通过oneName查询
	 */
	public List<Nav> findByoneName(String oneName) {
		String hql="from Nav as nav where nav.oneName=?";
		Object [] params={oneName};
		return this.findByHql(hql, params);
	}
	
}
