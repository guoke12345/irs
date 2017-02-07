package pojo;

public class Teachergroup {
	private int id;
	private String tgName;
	
	public Teachergroup(int id, String teaName) {
		this.tgName = tgName;
	}


	public Teachergroup() {
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getTgName() {
		return tgName;
	}


	public void setTgName(String tgName) {
		this.tgName = tgName;
	}
	
}
