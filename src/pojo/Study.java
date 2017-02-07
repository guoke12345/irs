package pojo;

public class Study {
	private int id;
	private int studyOneName_id;
	private int studyTwoName_id;
	private String studyTitle;
	private String studyContent;
	private String studyTime;
	private String fileName;
	private String filePath;
	private String studyOneName;
	private String studyTwoName;
	
	
	public Study(int id, int studyOneName_id, int studyTwoName_id,
			String studyTitle, String studyContent, String studyTime,
			String fileName, String filePath, String studyOneName,
			String studyTwoName) {
		super();
		this.id = id;
		this.studyOneName_id = studyOneName_id;
		this.studyTwoName_id = studyTwoName_id;
		this.studyTitle = studyTitle;
		this.studyContent = studyContent;
		this.studyTime = studyTime;
		this.fileName = fileName;
		this.filePath = filePath;
		this.studyOneName = studyOneName;
		this.studyTwoName = studyTwoName;
	}
	public Study() {
		}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudyOneName_id() {
		return studyOneName_id;
	}
	public void setStudyOneName_id(int studyOneName_id) {
		this.studyOneName_id = studyOneName_id;
	}
	public int getStudyTwoName_id() {
		return studyTwoName_id;
	}
	public void setStudyTwoName_id(int studyTwoName_id) {
		this.studyTwoName_id = studyTwoName_id;
	}
	public String getStudyTitle() {
		return studyTitle;
	}
	public void setStudyTitle(String studyTitle) {
		this.studyTitle = studyTitle;
	}
	public String getStudyContent() {
		return studyContent;
	}
	public void setStudyContent(String studyContent) {
		this.studyContent = studyContent;
	}
	public String getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getStudyOneName() {
		return studyOneName;
	}
	public void setStudyOneName(String studyOneName) {
		this.studyOneName = studyOneName;
	}
	public String getStudyTwoName() {
		return studyTwoName;
	}
	public void setStudyTwoName(String studyTwoName) {
		this.studyTwoName = studyTwoName;
	}
	
	
}
