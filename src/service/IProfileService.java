package service;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Profile;

public interface IProfileService {
	public void addProfile(Profile profile);
	public Pagination<Profile> profilePg(int pageno,int pageSize);
	public void deleteByid(Integer id); 
	public List<Profile> getProfile(Integer id);
	public Profile profile(int id);
	public void updateProfile(Profile profile);
	public List<Profile> profileList();
}
