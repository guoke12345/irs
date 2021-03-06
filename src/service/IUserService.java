package service;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.User;


public interface IUserService {
	public List<User> getByuserName(String userName);
	public void addUser(User user);
	public Pagination<User> getAllPg(int pageno,int pageSize);
	public void deleteByid(int id);
	public User user(int id);
	public void updateByid(User user);
}
