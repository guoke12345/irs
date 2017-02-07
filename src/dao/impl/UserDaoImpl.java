package dao.impl;

import java.util.List;

import pojo.User;

import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.IUserDao;


public class UserDaoImpl extends GenericHibernateDao<User,Integer>
implements IUserDao{
	private UserDaoImpl(){
		super(User.class);//实现User类的重载
	}
	/**
	 * 登陆方法
	 * 根据用户名查询一条数据
	 */
	public List<User> findByuserName(String userName) {
		String hql="from User as user where user.userName=?";
		Object [] params={userName};
		return this.findByHql(hql, params);
	}
	/**
	 * 添加方法
	 */
	public void addUser(User user) {
		this.save(user);
	}
	/**
	 * 分页方法遍历列表
	 */
	public Pagination<User> getAllPg(int pageno, int pageSize) {
		String hql="from User as user";
		return this.findByPage(pageno, pageSize, hql);
	}
	/**
	 * 删除方法
	 */
	public void deleteByid(int id) {
		this.remove(id);
	}
	/**
	 * 重置密码
	 */
	public User user(int id) {
		return this.findById(id);
	}
	/**
	 * 修改方法
	 */
	public void updateByid(User user) {
		this.update(user);
	}
	
}
