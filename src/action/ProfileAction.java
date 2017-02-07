package action;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pojo.Profile;
import service.IProfileService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class ProfileAction extends ActionSupport{
	private IProfileService profileService;
	private int id;
	private String profileName;
	private String profileContent;
	private int pageno=1;
	private int pageSize=10;
	private Pagination<Profile> pagination;
	private List<Profile> profileList;
	private Profile profile;
	Log log = LogFactory.getLog(this.getClass());
	
	/**
	 *跳转到简介列表界面
	 * @return
	 */
	public String profileListto(){
		try {
			pagination = this.profileService.profilePg(pageno, pageSize);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "profileListto";
	}
	/**
	 * 跳转到添加简介界面
	 */
	public String addProfileto(){
		
		return "addProfileto";
	}
	public String add(){
		try {
			Profile profile = new Profile();
			profile.setProfileName(profileName);
			profile.setProfileContent(profileContent);
			this.profileService.addProfile(profile);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return profileListto();
	}
	/**
	 * 删除简介的方法
	 */
	public String deleteByid(){
		try {
			this.profileService.deleteByid(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return profileListto();
	}
	/**
	 * 跳转到修改界面
	 */
	public String updateProfileto(){
		try {
			profile = this.profileService.profile(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "updateProfileto";
	}
	/**
	 * 修改方法
	 */
	public String updateProfile(){
		try {
			Profile profile = new Profile();
			profile.setId(id);
			profile.setProfileContent(profileContent);
			profile.setProfileName(profileName);
			this.profileService.updateProfile(profile);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return updateProfileto();
	}
	/**
	 * 获取简介的内容
	 */
	public String getContent(){
		try {
			profile = this.profileService.profile(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "getContent";
	}
	/**
	 * get  set
	 * @return
	 */
	public IProfileService getProfileService() {
		return profileService;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getProfileContent() {
		return profileContent;
	}
	public void setProfileContent(String profileContent) {
		this.profileContent = profileContent;
	}
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Pagination<Profile> getPagination() {
		return pagination;
	}
	public void setPagination(Pagination<Profile> pagination) {
		this.pagination = pagination;
	}
	public List<Profile> getProfileList() {
		return profileList;
	}
	public void setProfileList(List<Profile> profileList) {
		this.profileList = profileList;
	}
}
