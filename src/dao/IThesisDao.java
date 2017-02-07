package dao;

import java.util.List;

import pojo.Thesis;

import com.framework.core.page.Pagination;


public interface IThesisDao {
	public Thesis getById(int id);
	public void add(Thesis thesis);
	public Pagination<Thesis> getAllPag(int pageno,int pageSize);
	public void delete(int id);
	public void alter(Thesis thesis);
	public List<Thesis> getAllList();
}
