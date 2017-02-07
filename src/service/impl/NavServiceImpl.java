package service.impl;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Nav;
import dao.INavDao;
import service.INavService;


public class NavServiceImpl implements INavService{
	private INavDao navDao;

	public INavDao getNavDao() {
		return navDao;
	}

	public void setNavDao(INavDao navDao) {
		this.navDao = navDao;
	}
	/**
	 * 添加方法
	 */
	public void addNav(Nav nav) {
		this.navDao.addNav(nav);
	}
	/**
	 * 获取分页列表
	 */

	public Pagination<Nav> navPg(int pageno, int pageSize) {
		return this.navDao.navPg(pageno, pageSize);
	}

	public List<Nav> findBynavName(String navName) {
		return this.navDao.findBynavName(navName);
	}
	public List<Nav> findBynav(){
		return this.navDao.findBynav();
	}

	public Nav nav(int id) {
		 return this.navDao.nav(id);
	}

	public List<Nav> findByoneName(int id) {
		return this.navDao.findByoneName_id(id);
	}

	public void deleteByid(Integer id) {
		this.navDao.deleteByid(id);
	}

	public List<Nav> findByoneName(String oneName) {
		return this.navDao.findByoneName(oneName);
	}

	
	
	
}
