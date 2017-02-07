package dao.impl;

import java.util.List;

import pojo.Teacher;
import pojo.Teachergroup;

import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.ITeachergroupDao;

public class TeachergroupDaoImpl extends GenericHibernateDao<Teachergroup,Integer>
implements ITeachergroupDao{
	private TeachergroupDaoImpl(){
		super(Teachergroup.class);//实现类的重载
	}
	/**
	 * 
	 * 查询表
	 */
	public List<Teachergroup> getAll(){
		return this.findAll();
	}
	/**
	 * 添加
	 */
	public void add(Teachergroup teachergroup){
		this.save(teachergroup);
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
	public Teachergroup GetById(int id){
		return this.findById(id);
	}
	/**
	 * 修改
	 */
	public void update(Teachergroup teachergroup){
		this.saveOrUpdate(teachergroup);
	}
}
