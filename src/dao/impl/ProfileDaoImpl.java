package dao.impl;



import java.util.List;

import pojo.Profile;
import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.IProfileDao;



public class ProfileDaoImpl extends GenericHibernateDao<Profile,Integer>
implements IProfileDao{
	private ProfileDaoImpl(){
		super(Profile.class);//实现User类的重载
	}
	/**
	 * 添加简介方法
	 */
	public void addProfile(Profile profile) {
		this.save(profile);
	}
	/**
	 * 查询简介列表的方法
	 */
	public Pagination<Profile> profilePg(int pageno, int pageSize) {
		String  hql = "from Profile as profile";
		return this.findByPage(pageno, pageSize, hql);
	}
	/**
	 * 删除简介的方法
	 */
	public void deleteByid(Integer id) {
		this.remove(id);
	}
	/**
	 * 跳转到修改界面的方法
	 */
	public List<Profile> getProfile(Integer id) {
		
		return null;
	}
	/**
	 * 通过id查找获取一条记录
	 */
	public Profile profile(int id) {
		return this.findById(id);
	}
	/**
	 * 修改方法
	 */
	public void updateProfile(Profile profile) {
		this.saveOrUpdate(profile);
	}
	public List<Profile> profileList() {
		return this.findAll();
	}
	
}
