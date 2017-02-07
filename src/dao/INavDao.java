package dao;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Nav;

public interface INavDao {
	public void addNav(Nav nav);
	public Pagination<Nav> navPg(int pageno,int pageSize);
	public List<Nav> findBynavName(String navName);
	public List<Nav> findBynav();
	public Nav nav(int id);
	public List<Nav> findByoneName_id(int id);
	public void deleteByid(Integer id);
	public List<Nav> findByoneName(String oneName);
}
