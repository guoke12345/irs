package action;



import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import pojo.Article;
import pojo.User;

import service.IUserService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private IUserService userService;
	private int id;
	private String userName;
	private String password;
	private String personName;
	private int states;
	HttpSession session2;
	private String result;
	private List<User> userList;
	private User user;
	private int pageno=1;
	private int pageSize=15;
	private Pagination<User> pagination;
	Log log = LogFactory.getLog(this.getClass()); 
	public String tiao(){
		return "tiao";
	}
	
	/**
	 * 退出
	 */
	public String tuichu(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.invalidate();
		return "tuichu";
	}
	/**
	 * 我的信息方法
	 * 
	 * @return
	 */
	public String myInfo() {
			try {
				session2 = ServletActionContext.getRequest().getSession();
				userName = (String) session2.getAttribute("userName");
				userList = userService.getByuserName(userName);
			} catch (RuntimeException e) {
				log.error(e.getMessage());
				return "error";
			}
			return "myInfo";
		}
	/**
	 * 跳转到修改信息界面
	 */
	public String updateInfoto(){
		try {
			session2 = ServletActionContext.getRequest().getSession();
			userName = (String) session2.getAttribute("userName");
			userList = userService.getByuserName(userName);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "updateInfo";
	}
	public String updateInfo(){
		try {
			User user = new User();
			user.setId(id);
			
			MD5 getmd5 = new MD5();
			//strObj 为要加密的内容
			String passwd = getmd5.GetMD5Code(password);
			user.setPassword(passwd);
			
			user.setPersonName(personName);
			user.setStates(states);
			user.setUserName(userName);
			this.userService.updateByid(user);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return myInfo();
	}
	/**
	 * 登陆方法
	 */
	public void Login() throws IOException{
			userList = userService.getByuserName(userName);
			if(userList.size()==0){
				result="0";							//用户名不存在
			}else{
				user=userList.get(0);	
				MD5 getMD5 = new MD5();
				String passwd = getMD5.GetMD5Code(password);
				if(!user.getPassword().equals(passwd)){
					result="2";						//密码不正确
				}else{			
			        	session2 = ServletActionContext.getRequest().getSession();
						session2.setAttribute("userName", userName);
						result="1";					//登陆成功

				}
			}
			ServletActionContext.getResponse().getWriter().write(result);
	}
	
	/**
	 * 登陆后调转到主界面
	 */
	public String zhujiemian(){
		return "main";
	}
	public String top(){
		return "top";
	}
	public String center(){
		return "center";
	}
	public String dibu(){
		return "dibu";
	}
	public String left(){
		try {
			session2 = ServletActionContext.getRequest().getSession();
			userName = (String) session2.getAttribute("userName");
			user = this.userService.getByuserName(userName).get(0);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "left";
	}
	public String right(){
		return "right";
	}
	/**
	 * 跳转到添加界面的方法
	 */
	public String addUserto(){
		return "addUserto";
	}
	/**
	 * 添加管理员方法
	 */
	public void addUser() throws IOException{
			userList = userService.getByuserName(userName);
			if(userList.size()==0){
			User user = new User();
			user.setUserName(userName);
			user.setPersonName(personName);
			user.setStates(states);
			
			MD5 getmd5 = new MD5();
			//strObj 为要加密的内容
			String passwd = "000000";
			password = getmd5.GetMD5Code(passwd);
			user.setPassword(password);
			
			this.userService.addUser(user);
			result="2";
			}else{
				result="1";
			}
			ServletActionContext.getResponse().getWriter().write(result);
	}
	/**
	 * 分页遍历 获取用户信息
	 */
	public String getAllPg(){
		try {
			pagination = this.userService.getAllPg(pageno, pageSize);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "getAllPg";
	}
	/**
	 * 删除方法
	 */
	public String deleteByid(){
		try {
			this.userService.deleteByid(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return getAllPg();
	}
	/**
	 * 重置密码
	 */
	public String updatePassword(){
		try {
			user = this.userService.user(id);
			user.getId();
			user.getUserName();
			user.getPersonName();
			user.getStates();
			MD5 getmd5 = new MD5();
			//strObj 为要加密的内容
			String passwd = "000000";
			password = getmd5.GetMD5Code(passwd);
			user.setPassword(password);
			
			this.userService.updateByid(user);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return getAllPg();
	}
	/**
	 * 跳转到修改界面
	 */
	public String updateUserto(){
		try {
			user = this.userService.user(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "updateUserto";
	}
	/**
	 * 修改方法
	 */
	public String updateUser(){
		try {
			User user = new User();
			user.setId(id);
			user.setUserName(userName);
			user.setPersonName(personName);
			user.setStates(states);
			
			MD5 getmd5 = new MD5();
			//strObj 为要加密的内容
			String passwd = getmd5.GetMD5Code(password);
			user.setPassword(passwd);
			
			this.userService.updateByid(user);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return getAllPg();
	}
	/**
	 * get set  方法
	 * @return
	 */
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
	public HttpSession getSession2() {
		return session2;
	}
	public void setSession2(HttpSession session2) {
		this.session2 = session2;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public Pagination<User> getPagination() {
		return pagination;
	}
	public void setPagination(Pagination<User> pagination) {
		this.pagination = pagination;
	}
	
}
