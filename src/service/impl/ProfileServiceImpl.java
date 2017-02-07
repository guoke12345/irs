package service.impl;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Profile;
import dao.IProfileDao;
import service.IProfileService;

public class ProfileServiceImpl implements IProfileService{
	public IProfileDao profileDao;

	public IProfileDao getProfileDao() {
		return profileDao;
	}

	public void setProfileDao(IProfileDao profileDao) {
		this.profileDao = profileDao;
	}

	public void addProfile(Profile profile) {
		this.profileDao.addProfile(profile);
	}

	public Pagination<Profile> profilePg(int pageno, int pageSize) {
		return this.profileDao.profilePg(pageno, pageSize);
	}

	public void deleteByid(Integer id) {
		this.profileDao.deleteByid(id);
	}

	public List<Profile> getProfile(Integer id) {
		return this.profileDao.getProfile(id);
	}

	public Profile profile(int id) {
		return this.profileDao.profile(id);
	}

	public void updateProfile(Profile profile) {
		this.profileDao.updateProfile(profile);
	}

	public List<Profile> profileList() {
		return this.profileDao.profileList();
	}
	
}
