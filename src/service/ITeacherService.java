package service;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Teacher;
import pojo.User;


public interface ITeacherService {
	public Pagination<Teacher> getAllPag(int pageno, int pageSize);
	public Pagination<Teacher> getbiaoji(int pageno, int pageSize);
	public void add(Teacher teacher);
	public void delete(int id);
	public Teacher GetById(int id);
	public void update(Teacher teacher);
	public List<Teacher> getAllList();
	public List<Teacher> getByTeaTeam_id(int teaTeam_id);
	public List<Teacher> findByteaName(String teaName);
}
