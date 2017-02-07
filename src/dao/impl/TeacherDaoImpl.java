package dao.impl;

import java.util.List;


import pojo.Teacher;
import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.ITeacherDao;

public class TeacherDaoImpl extends GenericHibernateDao<Teacher,Integer>
implements ITeacherDao{
	private TeacherDaoImpl(){
		super(Teacher.class);//实现User类的重载
	}
	/**
	 * 分页
	 * 查询表
	 */
	public Pagination<Teacher> getAllPag(int pageno, int pageSize){
		String hql = "from Teacher as teacher order by id desc";
		return this.findByPage(pageno, pageSize, hql);
	}
	/**
	 * 列表
	 * 查询表
	 */
	public List<Teacher> getAllList(){
		return this.findAll();
	}
	/**
	 * 添加
	 */
	public void add(Teacher teacher){
		this.save(teacher);
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
	public Teacher GetById(int id){
		return this.findById(id);
	}
	/**
	 * 修改
	 */
	public void update(Teacher teacher){
		this.saveOrUpdate(teacher);
	}
	/**
	 * 通过 teaTeam_id 查找 
	 */
	public List<Teacher> getByTeaTeam_id(int teaTeam_id){
		String hql = "from Teacher as teacher where teacher.teaTeam_id ="+teaTeam_id;
		return this.findByHql(hql);
	}
	/**
	 * 通过teaName查询列表
	 */
	public List<Teacher> findByteaName(String teaName) {
		String hql="from Teacher as teacher where teacher.teaName=?";
		Object [] params={teaName};
		return this.findByHql(hql, params);
	}
	public Pagination<Teacher> getbiaoji(int pageno, int pageSize) {
		String hql = "from Teacher as teacher where dengji = 1";
		return this.findByPage(pageno, pageSize, hql);
	}
}
