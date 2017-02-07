package service.impl;

import java.util.List;

import pojo.Teachergroup;
import service.ITeachergroupService;

import com.framework.core.page.Pagination;

import dao.ITeachergroupDao;

public class TeachergroupServiceImpl implements ITeachergroupService{
	private ITeachergroupDao teachergroupDao;
	
	public List<Teachergroup> getAll(){
		return this.teachergroupDao.getAll();
	}
	public void add(Teachergroup teachergroup){
		this.teachergroupDao.add(teachergroup);
	}
	public void delete(int id){
		this.teachergroupDao.delete(id);
	}
	public Teachergroup GetById(int id){
		return this.teachergroupDao.GetById(id);
	}
	public void update(Teachergroup teachergroup){
		this.teachergroupDao.update(teachergroup);
	}
	
	
	/**
	 * 以下是 get set方法
	 * @return
	 */
	public ITeachergroupDao getTeachergroupDao() {
		return teachergroupDao;
	}
	public void setTeachergroupDao(ITeachergroupDao teachergroupDao) {
		this.teachergroupDao = teachergroupDao;
	}
}
