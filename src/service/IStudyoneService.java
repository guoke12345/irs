package service;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Studyone;

public interface IStudyoneService {
	public void addStudyOneName(Studyone studyone);
	public Pagination<Studyone> studyOnePg(int pageno, int pageSize);
	public List<Studyone> studyoneList();
	public Studyone studyone(int id);
	public void deleteByud(int id);
}
