package service.impl;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Studyone;
import service.IStudyoneService;
import dao.IStudyoneDao;




public class StudyoneServiceImpl implements IStudyoneService{
	private IStudyoneDao studyoneDao;

	public IStudyoneDao getStudyoneDao() {
		return studyoneDao;
	}

	public void setStudyoneDao(IStudyoneDao studyoneDao) {
		this.studyoneDao = studyoneDao;
	}

	public void addStudyOneName(Studyone studyone) {
		this.studyoneDao.addStudyOneName(studyone);
	}

	public Pagination<Studyone> studyOnePg(int pageno, int pageSize) {
		return this.studyoneDao.studyOnePg(pageno, pageSize);
	}

	public List<Studyone> studyoneList() {
		return this.studyoneDao.studyoneList();
	}

	public Studyone studyone(int id) {
		return this.studyoneDao.studyone(id);
	}

	public void deleteByud(int id) {
		this.studyoneDao.deleteByid(id);
	}
	
	
}
