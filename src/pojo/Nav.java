package pojo;

public class Nav {
	private int id;
	private String navName;
	private String oneName;
	private int status;
	
	
	public Nav() {
	}
	public Nav(int id, String navName, String oneName,int status) {
		super();
		this.id = id;
		this.navName = navName;
		this.oneName = oneName;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNavName() {
		return navName;
	}
	public void setNavName(String navName) {
		this.navName = navName;
	}
	public String getOneName() {
		return oneName;
	}
	public void setOneName(String oneName) {
		this.oneName = oneName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
