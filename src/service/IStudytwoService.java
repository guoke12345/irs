package service;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Studytwo;

public interface IStudytwoService {
	public List<Studytwo> studytwoList();
	public List<Studytwo> studytwoListByid(int studyOneName_id);
	public void addStudytwo(Studytwo studytwo);
	public Pagination<Studytwo> studytwoPg(int pageno,int pageSize);
	public Studytwo studytwo(int id);
	public void deleteByid(int id);
}
