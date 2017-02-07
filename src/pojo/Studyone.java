package pojo;

public class Studyone {
	private int id;
	private String studyOneName;
	
	
	public Studyone(int id, String studyOneName) {
		super();
		this.id = id;
		this.studyOneName = studyOneName;
	}
	public Studyone() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudyOneName() {
		return studyOneName;
	}
	public void setStudyOneName(String studyOneName) {
		this.studyOneName = studyOneName;
	}
	
	
}	
