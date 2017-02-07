package dao.impl;



import java.util.List;

import pojo.Studytwo;

import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.IStudytwoDao;



public class StudytwoDaoImpl extends GenericHibernateDao< Studytwo,Integer>
implements IStudytwoDao{
	private StudytwoDaoImpl(){
		super(Studytwo.class);//实现User类的重载
	}

	public List<Studytwo> studytwoList() {
		String hql="from Studytwo as studytwo";
		return this.findByHql(hql);
	}
	/**
	 * 添加方法
	 */
	public void addStudytwo(Studytwo studytwo) {
		this.save(studytwo);
	}
	/**
	 * 分页遍历
	 */
	public Pagination<Studytwo> studytwoPg(int pageno, int pageSize) {
		String hql="from Studytwo as studytwo";
		return this.findByPage(pageno, pageSize, hql);
	}

	public List<Studytwo> studytwoListByid(int studyOneName_id) {
		String hql = "from Studytwo as studytwo where studytwo.studyOneName_id= "+"'"+studyOneName_id+"' ";
		return this.findByHql(hql);
	}
	/**
	 * 通过id查询一条数据u
	 */
	public Studytwo studytwo(int id) {
		return this.findById(id);
	}
	/**
	 * 删除方法
	 */
	public void deleteByid(int id) {
		this.remove(id);
	}
}
