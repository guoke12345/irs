package dao.impl;

import java.util.List;

import pojo.Research;


import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.IResearchDao;


public class ResearchDaoImpl extends GenericHibernateDao<Research,Integer>
implements IResearchDao{
	private ResearchDaoImpl(){
		super(Research.class);//实现User类的重载
	}
	/**
	 * 分页
	 * 查询表
	 */
	public Pagination<Research> getAllPag(int pageno, int pageSize){
		String hql = "from Research as Research ";
		return this.findByPage(pageno, pageSize, hql);
	}
	/**
	 * 分页
	 * 根据tea查询表
	 */
	public Pagination<Research> getTeaPag(int teaId,int pageno,int pageSize){
		String hql = "from Research as research where research.teaId = " +teaId;
		return this.findByPage(pageno, pageSize, hql);
	}
	/**
	 * 添加
	 */
	public void add(Research research){
		this.save(research);
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
	public Research GetById(int id){
		return this.findById(id);
	}
	/**
	 * 修改
	 */
	public void update(Research research){
		this.saveOrUpdate(research);
	}
	
	/**
	 * 通过  teaId 查找列表
	 */
	public List<Research> getListByTeaId(int id){
		String hql = "from Research as research where research.teaId = "+id;
		return this.findByHql(hql);
	}
}
