package service.impl;

import java.util.List;

import pojo.Thesis;

import com.framework.core.page.Pagination;

import dao.IThesisDao;

import service.IThesisService;

public class ThesisServiceImpl implements IThesisService{
	public IThesisDao thesisDao;
	
	public Thesis getById(int id){
		return this.thesisDao.getById(id);
	}
	public void add(Thesis thesis){
		this.thesisDao.add(thesis);
	}
	public Pagination<Thesis> getAllPag(int pageno,int pageSize){
		return this.thesisDao.getAllPag(pageno, pageSize);
	}
	public void delete(int id){
		this.thesisDao.delete(id);
	}
	public void alter(Thesis thesis){
		this.thesisDao.alter(thesis);
	}
	public List<Thesis> getAllList(){
		return this.thesisDao.getAllList();
	}
	
	/**
	 * get set 方法
	 * @return
	 */
	public IThesisDao getThesisDao() {
		return thesisDao;
	}
	public void setThesisDao(IThesisDao thesisDao) {
		this.thesisDao = thesisDao;
	}
	
}
