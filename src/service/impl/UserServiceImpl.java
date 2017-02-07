package service.impl;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Profile;
import pojo.User;
import dao.IUserDao;
import service.IUserService;



public class UserServiceImpl implements IUserService{
	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getByuserName(String userName) {
		return this.userDao.findByuserName(userName);
	}

	public void addUser(User user) {
		this.userDao.addUser(user);
	}

	public Pagination<User> getAllPg(int pageno, int pageSize) {
		return this.userDao.getAllPg(pageno, pageSize);
	}

	public void deleteByid(int id) {
		this.userDao.deleteByid(id);
	}

	public User user(int id) {
		return this.userDao.user(id);
	}

	public void updateByid(User user) {
		this.userDao.updateByid(user);
	}

	

	
	
}
