package pojo;

public class Research {
	private int id;
	private int teaId;
	private String  resNavigation;				//导航
	private String resContent;  				//内容
	private String teaName;
	
	public Research(int id, int teaId, String resNavigation, String resContent,String teaName) {
		this.id = id;
		this.teaId = teaId;
		this.resContent = resContent;
		this.resNavigation = resNavigation;
		this.teaName = teaName;
		}


	public Research() {
	}


	public String getTeaName() {
		return teaName;
	}


	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getTeaId() {
		return teaId;
	}


	public void setTeaId(int teaId) {
		this.teaId = teaId;
	}


	public String getResNavigation() {
		return resNavigation;
	}


	public void setResNavigation(String resNavigation) {
		this.resNavigation = resNavigation;
	}


	public String getResContent() {
		return resContent;
	}


	public void setResContent(String resContent) {
		this.resContent = resContent;
	}
	
}