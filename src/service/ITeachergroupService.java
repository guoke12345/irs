package service;

import java.util.List;

import com.framework.core.page.Pagination;
import pojo.Teachergroup;


public interface ITeachergroupService {
	public List<Teachergroup> getAll();
	public void add(Teachergroup teachergroup);
	public void delete(int id);
	public Teachergroup GetById(int id);
	public void update(Teachergroup teachergroup);
}
