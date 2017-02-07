package dao.impl;



import java.util.List;

import pojo.Study;

import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.IStudyDao;



public class StudyDaoImpl extends GenericHibernateDao<Study,Integer>
implements IStudyDao{
	private StudyDaoImpl(){
		super(Study.class);//实现User类的重载
	}
	/**
	 * 添加方法
	 */
	public void addStudy(Study study) {
		this.save(study);
	}
	/**
	 * 分页遍历
	 */
	public Pagination<Study> studyPg(int pageno, int pageSize) {
		String hql ="from Study as study order by id desc";
		return this.findByPage(pageno, pageSize, hql);
	}
	/**
	 * 删除方法
	 */
	public void deleteByid(int id) {
		this.remove(id);
	}
	/**
	 * 通过two——id查询列表
	 */
	public List<Study> studyListByTwo(int studyTwoName_id) {
		String hql = "from Study as study where study.studyTwoName_id= "+"'"+studyTwoName_id+"' ";
		return this.findByHql(hql);
	}
	/**
	 * 通过one_id
	 */
	public List<Study> studyListByOne(int studyOneName_id) {
		String hql = "from Study as study where study.studyOneName_id= "+"'"+studyOneName_id+"' ";
		return this.findByHql(hql);
	}
	/**
	 * 通过id差一条数据
	 */
	public Study study(int id) {
		return this.findById(id);
	}
	/**
	 * 修改方法
	 */
	public void updateStudy(Study study) {
		this.update(study);
	}
	/**
	 * 通过one_id分页遍历
	 */
	public Pagination<Study> studyByone(int pageno, int pageSize,
			int studyOneName_id) {
		String hql = "from Study as study where study.studyOneName_id= "+"'"+studyOneName_id+"' ";
		return this.findByPage(pageno, pageSize, hql);
	}
	public Pagination<Study> studyBytwo(int pageno, int pageSize,
			int studyTwoName_id) {
		String hql = "from Study as study where study.studyTwoName_id= "+"'"+studyTwoName_id+"' ";
		return this.findByPage(pageno, pageSize, hql);
	}
}
