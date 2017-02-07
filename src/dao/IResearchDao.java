package dao;

import java.util.List;

import pojo.Research;

import com.framework.core.page.Pagination;


public interface IResearchDao {
	public Pagination<Research> getAllPag(int pageno, int pageSize);
	public Pagination<Research> getTeaPag(int teaId,int pageno,int pageSize);
	public void add(Research research);
	public void delete(int id);
	public Research GetById(int id);
	public void update(Research research);
	public List<Research> getListByTeaId(int id);
}
