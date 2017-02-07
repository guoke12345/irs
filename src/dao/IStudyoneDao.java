package dao;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Studyone;

public interface IStudyoneDao {
	public void addStudyOneName(Studyone studyone);
	public Pagination<Studyone> studyOnePg(int pageno, int pageSize);
	public List<Studyone> studyoneList();
	public Studyone studyone(int id);
	public void deleteByid(int id);
}
