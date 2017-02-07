package pojo;

public class Studytwo {
	private int id;
	private String studyTwoName;
	private int studyOneName_id;   //关联studyone表
	private String studyOneName;
	
	
	
	
	public Studytwo() {
	}
	
	public Studytwo(int id, String studyTwoName, int studyOneName_id,
			String studyOneName) {
		super();
		this.id = id;
		this.studyTwoName = studyTwoName;
		this.studyOneName_id = studyOneName_id;
		this.studyOneName = studyOneName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudyTwoName() {
		return studyTwoName;
	}
	public void setStudyTwoName(String studyTwoName) {
		this.studyTwoName = studyTwoName;
	}
	public int getStudyOneName_id() {
		return studyOneName_id;
	}
	public void setStudyOneName_id(int studyOneName_id) {
		this.studyOneName_id = studyOneName_id;
	}
	public String getStudyOneName() {
		return studyOneName;
	}
	public void setStudyOneName(String studyOneName) {
		this.studyOneName = studyOneName;
	}
	
	

}
