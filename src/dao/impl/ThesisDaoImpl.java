package dao.impl;

import java.util.List;

import pojo.Thesis;

import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.IThesisDao;


public class ThesisDaoImpl extends GenericHibernateDao<Thesis,Integer>
implements IThesisDao{
	private ThesisDaoImpl(){
		super(Thesis.class);//实现thesis类的重载
	}
	/**
	 * 分页
	 * 查询表
	 */
	public Pagination<Thesis> getAllPag(int pageno, int pageSize){
		String hql = "from Thesis as thesis order by id desc";
		return this.findByPage(pageno, pageSize, hql);
	}
	/**
	 * 列表
	 * 查询表
	 */
	public List<Thesis> getAllList(){
		String hql = " from Thesis as thesis order by id desc";
		return this.findByHql(hql);
	}
	/**
	 * 添加
	 */
	public void add(Thesis thesis){
		this.save(thesis);
	}
	
	/**
	 * 删除
	 */
	public void delete(int id){
		this.remove(id);
	}
	/**
	 * 通过id查找
	 */
	public Thesis getById(int id){
		return this.findById(id);
	}
	/**
	 * 修改
	 */
	public void alter(Thesis thesis){
		this.update(thesis);
	}
}
