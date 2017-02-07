package service;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Study;

public interface IStudyService {
	public void addStudy(Study study);
	public Pagination<Study> studyPg(int pageno,int pageSize);
	public Pagination<Study> studyByonePg(int pageno,int pageSize,int studyOneName_id);
	public Pagination<Study> studyBytwoPg(int pageno,int pageSize,int studyTwoName_id);
	public void deleteByid(int id);
	public List<Study> studyListByTwo(int studyTwoName_id);
	public List<Study> studyListByOne(int studyOneName_id);
	public Study study(int id);
	public void updateStudy(Study study);
}
