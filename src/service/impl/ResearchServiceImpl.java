package service.impl;


import java.util.List;

import pojo.Research;
import service.IResearchService;

import com.framework.core.page.Pagination;

import dao.IResearchDao;


public class ResearchServiceImpl implements IResearchService{
	private IResearchDao researchDao;
	
	public Pagination<Research> getAllPag(int pageno, int pageSize){
		return this.researchDao.getAllPag(pageno, pageSize);
	}
	public Pagination<Research> getTeaPag(int teaId,int pageno,int pageSize){
		return this.researchDao.getTeaPag(teaId, pageno, pageSize);
	}
	public void add(Research research){
		this.researchDao.add(research);
	}
	public void delete(int id){
		this.researchDao.delete(id);
	}
	public Research GetById(int id){
		return this.researchDao.GetById(id);
	}
	public void update(Research research){
		this.researchDao.update(research);
	}
	public List<Research> getListByTeaId(int id){
		return this.researchDao.getListByTeaId(id);
	}
	
	/**
	 * 以下是 get set方法
	 * @return
	 */
	public IResearchDao getResearchDao() {
		return researchDao;
	}
	public void setResearchDao(IResearchDao researchDao) {
		this.researchDao = researchDao;
	}
	
}
