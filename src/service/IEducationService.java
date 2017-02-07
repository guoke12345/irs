package service;

import java.util.List;

import pojo.Education;

import com.framework.core.page.Pagination;

public interface IEducationService {
	public Pagination<Education> educationPg(int pageno,int pageSize);
	public void addEducation(Education education);
	public void deleteByid(Integer id);
	public Education education(int id);
	public void updateEducation(Education education);
	public List<Education> eduList();
}
