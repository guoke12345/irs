package dao.impl;



import java.util.List;

import pojo.Education;

import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.IEducationDao;



public class EducationDaoImpl extends GenericHibernateDao<Education,Integer>
implements IEducationDao{
	private EducationDaoImpl(){
		super(Education.class);//实现User类的重载
	}
	/**
	 * 获取列表的方法
	 */
	public Pagination<Education> educationPg(int pageno, int pageSize) {
		String hql = "from Education as education order by id desc";
		return this.findByPage(pageno, pageSize, hql);
	}
	
	/**
	 * 添加方法
	 */
	public void addEducation(Education education) {
		this.save(education);
	}
	/**
	 * 删除方法
	 */
	public void deleteByid(Integer id) {
		this.remove(id);
	}
	/**
	 * 修改方法
	 */
	public Education education(int id) {
		return this.findById(id);
	}
	public void updateEducation(Education education) {
		this.update(education);
	}
	/**
	 * 获取列表
	 */
	public List<Education> eduList() {
		return this.findAll();
	}

}
