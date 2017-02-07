package pojo;

public class User {
	private int id;
	private String userName;
	private String password;
	private int states;    			//1:超级管理员  2：普通会员  3：教授
	private String personName;
	
	
	public User(int id, String userName, String password, int states,
			String personName) {
		this.userName = userName;
		this.password = password;
		this.states = states;
		this.personName = personName;
	}


	public User() {
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
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
}
