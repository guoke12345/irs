package dao;

import java.util.List;

import pojo.Teachergroup;

import com.framework.core.page.Pagination;

public interface ITeachergroupDao {
	public List<Teachergroup> getAll();
	public void add(Teachergroup teachergroup);
	public void delete(int id);
	public Teachergroup GetById(int id);
	public void update(Teachergroup teachergroup);
}
