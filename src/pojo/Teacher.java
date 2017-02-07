package pojo;

public class Teacher {
	private int id;
	private String teaName;
	private int serviceExist; //实验室状态
	private String teaPhone;
	private String teaFax;  				//传真
	private String teaDirection;  
	private String teaEmail;
	private int teaTeam_id;				// 团队id		表关联
	private String teaposition;				//职位
	private String teaEducationalBackground;//教育背景
	private String teaworkexperience;		//工作经历
	private String teaAwardOrActive;		//奖项和活动
	private String teaMajorThesis;			//代表性论文
	private String tgName;					//团队
	private String teaPhoto;					//照片
	private String password;
	private String teaMajorThesisTitle;   //论文标题
	private int result;
	private int dengji;
	public Teacher(int id, String teaName, String password,String teaPhone, String teaFax,
			String teaDirection,String teaEmail,int teaTeam_id,String teaposition,String teaEducationalBackground
			,String teaworkexperience,String teaAwardOrActive,String teaMajorThesis,String tgName,String teaPhoto,int result
			,String teaMajorThesisTitle,int serviceExist,int dengji) {
		this.id = id;
		this.teaName = teaName;
		this.teaPhone = teaPhone;
		this.teaFax = teaFax;
		this.teaDirection = teaDirection;
		this.teaEmail = teaEmail;
		this.teaTeam_id = teaTeam_id;
		this.teaposition = teaposition;
		this.teaEducationalBackground = teaEducationalBackground;
		this.teaworkexperience = teaworkexperience;
		this.teaAwardOrActive = teaAwardOrActive;
		this.teaMajorThesis = teaMajorThesis;
		this.tgName = tgName;
		this.teaPhoto = teaPhoto;
		this.result = result;
		this.password = password;
		this.teaMajorThesisTitle = teaMajorThesisTitle;
		this.serviceExist = serviceExist;
		this.dengji = dengji;
	}

	
	public int getServiceExist() {
		return serviceExist;
	}


	public void setServiceExist(int serviceExist) {
		this.serviceExist = serviceExist;
	}


	public String getTeaMajorThesisTitle() {
		return teaMajorThesisTitle;
	}


	public void setTeaMajorThesisTitle(String teaMajorThesisTitle) {
		this.teaMajorThesisTitle = teaMajorThesisTitle;
	}


	public Teacher() {
	}
	
	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}
	public int getTeaTeam_id() {
		return teaTeam_id;
	}


	public void setTeaTeam_id(int teaTeam_id) {
		this.teaTeam_id = teaTeam_id;
	}


	public String getTeaposition() {
		return teaposition;
	}


	public void setTeaposition(String teaposition) {
		this.teaposition = teaposition;
	}


	public String getTeaEducationalBackground() {
		return teaEducationalBackground;
	}


	public void setTeaEducationalBackground(String teaEducationalBackground) {
		this.teaEducationalBackground = teaEducationalBackground;
	}


	public String getTeaworkexperience() {
		return teaworkexperience;
	}


	public void setTeaworkexperience(String teaworkexperience) {
		this.teaworkexperience = teaworkexperience;
	}


	public String getTeaAwardOrActive() {
		return teaAwardOrActive;
	}


	public void setTeaAwardOrActive(String teaAwardOrActive) {
		this.teaAwardOrActive = teaAwardOrActive;
	}


	public String getTeaMajorThesis() {
		return teaMajorThesis;
	}


	public void setTeaMajorThesis(String teaMajorThesis) {
		this.teaMajorThesis = teaMajorThesis;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTeaName() {
		return teaName;
	}


	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getTeaPhone() {
		return teaPhone;
	}


	public void setTeaPhone(String teaPhone) {
		this.teaPhone = teaPhone;
	}


	public String getTeaFax() {
		return teaFax;
	}
	public void setTeaFax(String teaFax) {
		this.teaFax = teaFax;
	}
	public String getTeaDirection() {
		return teaDirection;
	}
	public void setTeaDirection(String teaDirection) {
		this.teaDirection = teaDirection;
	}
	public String getTeaEmail() {
		return teaEmail;
	}
	public void setTeaEmail(String teaEmail) {
		this.teaEmail = teaEmail;
	}


	public String getTgName() {
		return tgName;
	}


	public void setTgName(String tgName) {
		this.tgName = tgName;
	}


	public String getTeaPhoto() {
		return teaPhoto;
	}


	public void setTeaPhoto(String teaPhoto) {
		this.teaPhoto = teaPhoto;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getDengji() {
		return dengji;
	}


	public void setDengji(int dengji) {
		this.dengji = dengji;
	}
	
}