package service.impl;

import java.util.List;

import pojo.Education;

import com.framework.core.page.Pagination;

import dao.IEducationDao;
import service.IEducationService;



public class EducationServiceImpl implements IEducationService{
	private IEducationDao educationDao;

	public IEducationDao getEducationDao() {
		return educationDao;
	}

	public void setEducationDao(IEducationDao educationDao) {
		this.educationDao = educationDao;
	}

	public Pagination<Education> educationPg(int pageno, int pageSize) {
		return this.educationDao.educationPg(pageno, pageSize);
	}

	public void addEducation(Education education) {
		this.educationDao.addEducation(education);
	}

	public void deleteByid(Integer id) {
		this.educationDao.deleteByid(id);
	}

	public Education education(int id) {
		return this.educationDao.education(id);
	}

	public void updateEducation(Education education) {
		this.educationDao.updateEducation(education);
	}

	public List<Education> eduList() {
		// TODO Auto-generated method stub
		return this.educationDao.eduList();
	}
	
	
}
