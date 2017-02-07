package dao.impl;

import java.util.List;

import pojo.Studyone;

import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.IStudyoneDao;



public class StudyoneDaoImpl  extends GenericHibernateDao< Studyone,Integer>
implements IStudyoneDao{
	private StudyoneDaoImpl(){
		super(Studyone.class);//实现User类的重载
	}
	/**
	 * 添加方法
	 */
	public void addStudyOneName(Studyone studyone) {
		this.save(studyone);
	}
	/**
	 * 遍历列表
	 */
	public Pagination<Studyone> studyOnePg(int pageno, int pageSize) {
		String hql = "from Studyone as studyone";
		return this.findByPage(pageno, pageSize, hql);
	}
	/**
	 * list列表
	 */
	public List<Studyone> studyoneList() {
		String hql = "from Studyone as studyone";
		return this.findByHql(hql);
	}
	/**
	 * 通过id查询一条数据
	 */
	public Studyone studyone(int id) {
		return this.findById(id);
	}
	/**
	 * 删除方法
	 */
	public void deleteByid(int id) {
		this.remove(id);
	}
}
