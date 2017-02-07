package service.impl;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Studytwo;
import dao.IStudytwoDao;
import service.IStudytwoService;


public class StudytwoServiceImpl implements IStudytwoService{
	private IStudytwoDao studytwoDao;

	public IStudytwoDao getStudytwoDao() {
		return studytwoDao;
	}

	public void setStudytwoDao(IStudytwoDao studytwoDao) {
		this.studytwoDao = studytwoDao;
	}

	public List<Studytwo> studytwoList() {
		return this.studytwoDao.studytwoList();
	}

	public void addStudytwo(Studytwo studytwo) {
		this.studytwoDao.addStudytwo(studytwo);
	}

	public Pagination<Studytwo> studytwoPg(int pageno, int pageSize) {
		return this.studytwoDao.studytwoPg(pageno, pageSize);
	}

	public List<Studytwo> studytwoListByid(int studyOneName_id) {
		return this.studytwoDao.studytwoListByid(studyOneName_id);
	}

	public Studytwo studytwo(int id) {
		return this.studytwoDao.studytwo(id);
	}

	public void deleteByid(int id) {
		this.studytwoDao.deleteByid(id);
	}
}
