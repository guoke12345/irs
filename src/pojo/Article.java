package pojo;

public class Article {
	public int id;
	public int nav_oneName;		//关联nav表中的oneName
	public String title;
	public String content;
	public String time;
	public String nav_navName;
	public int num;
	public String oneName;
	public int dengji;
	
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Article(int id, int nav_oneName, String title, String content,
			String time, String nav_navName, int num,String oneName,int dengji) {
		super();
		this.id = id;
		this.nav_oneName = nav_oneName;
		this.title = title;
		this.content = content;
		this.time = time;
		this.nav_navName = nav_navName;
		this.num = num;
		this.oneName = oneName;
		this.dengji = dengji;
	}


	public String getOneName() {
		return oneName;
	}
	public void setOneName(String oneName) {
		this.oneName = oneName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getNav_oneName() {
		return nav_oneName;
	}

	public void setNav_oneName(int nav_oneName) {
		this.nav_oneName = nav_oneName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}



	public String getNav_navName() {
		return nav_navName;
	}



	public void setNav_navName(String nav_navName) {
		this.nav_navName = nav_navName;
	}



	public int getDengji() {
		return dengji;
	}



	public void setDengji(int dengji) {
		this.dengji = dengji;
	}

	
}
