package pojo;

public class Education {
	private int id;
	private String eduOneName;		//一级标题
	private String eduTwoName;		//二级标题
	private String eduTitle;		//文章标题
	private String eduContent;		//文章内容
	private String eduTime;			//发表时间
	private int eduNum;				//点击次数
	
	
	public Education() {	
	}
	public Education(int id, String eduOneName, String eduTwoName,
			String eduTitle, String eduContent, String eduTime, int eduNum) {
		super();
		this.id = id;
		this.eduOneName = eduOneName;
		this.eduTwoName = eduTwoName;
		this.eduTitle = eduTitle;
		this.eduContent = eduContent;
		this.eduTime = eduTime;
		this.eduNum = eduNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEduOneName() {
		return eduOneName;
	}
	public void setEduOneName(String eduOneName) {
		this.eduOneName = eduOneName;
	}
	public String getEduTwoName() {
		return eduTwoName;
	}
	public void setEduTwoName(String eduTwoName) {
		this.eduTwoName = eduTwoName;
	}
	public String getEduTitle() {
		return eduTitle;
	}
	public void setEduTitle(String eduTitle) {
		this.eduTitle = eduTitle;
	}
	public String getEduContent() {
		return eduContent;
	}
	public void setEduContent(String eduContent) {
		this.eduContent = eduContent;
	}
	public String getEduTime() {
		return eduTime;
	}
	public void setEduTime(String eduTime) {
		this.eduTime = eduTime;
	}
	public int getEduNum() {
		return eduNum;
	}
	public void setEduNum(int eduNum) {
		this.eduNum = eduNum;
	}
	
	
}
