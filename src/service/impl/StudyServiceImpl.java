package service.impl;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Study;
import dao.IStudyDao;
import service.IStudyService;


public class StudyServiceImpl implements IStudyService{
	private IStudyDao studyDao;

	public IStudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(IStudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public void addStudy(Study study) {
		this.studyDao.addStudy(study);
	}

	public Pagination<Study> studyPg(int pageno, int pageSize) {
		return this.studyDao.studyPg(pageno, pageSize);
	}

	public void deleteByid(int id) {
		this.studyDao.deleteByid(id);
	}

	public List<Study> studyListByTwo(int studyTwoName_id) {
		return this.studyDao.studyListByTwo(studyTwoName_id);
	}

	public List<Study> studyListByOne(int studyOneName_id) {
		return this.studyDao.studyListByOne(studyOneName_id);
	}

	public Study study(int id) {
		return this.studyDao.study(id);
	}

	public void updateStudy(Study study) {
		this.studyDao.updateStudy(study);
	}

	public Pagination<Study> studyByonePg(int pageno, int pageSize,
			int studyOneName_id) {
		return this.studyDao.studyByone(pageno, pageSize, studyOneName_id);
	}

	public Pagination<Study> studyBytwoPg(int pageno, int pageSize,
			int studyTwoName_id) {
		return this.studyDao.studyBytwo(pageno, pageSize, studyTwoName_id);
	}
	
}
