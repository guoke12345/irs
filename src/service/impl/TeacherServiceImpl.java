package service.impl;

import java.util.List;

import pojo.Teacher;
import service.ITeacherService;

import com.framework.core.page.Pagination;

import dao.ITeacherDao;

public class TeacherServiceImpl implements ITeacherService{
	private ITeacherDao teacherDao;
	
	public Pagination<Teacher> getAllPag(int pageno, int pageSize){
		return this.teacherDao.getAllPag(pageno, pageSize);
	}
	public void add(Teacher teacher){
		this.teacherDao.add(teacher);
	}
	public void delete(int id){
		this.teacherDao.delete(id);
	}
	public Teacher GetById(int id){
		return this.teacherDao.GetById(id);
	}
	public void update(Teacher teacher){
		this.teacherDao.update(teacher);
	}
	public List<Teacher> getAllList(){
		return this.teacherDao.getAllList();
	}
	public List<Teacher> getByTeaTeam_id(int teaTeam_id){
		return this.teacherDao.getByTeaTeam_id(teaTeam_id);
	}
	
	/**
	 * 以下是 get set方法
	 * @return
	 */
	public ITeacherDao getTeacherDao() {
		return teacherDao;
	}
	public void setTeacherDao(ITeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
	public List<Teacher> findByteaName(String teaName) {
		return this.teacherDao.findByteaName(teaName);
	}
	public Pagination<Teacher> getbiaoji(int pageno, int pageSize) {
		return this.teacherDao.getbiaoji(pageno, pageSize);
	}
}
