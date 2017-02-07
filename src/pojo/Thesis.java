package pojo;

public class Thesis {
	private int id;
	private String title;
	private String author;
	private String content;
	private String date;
	
	
	


	public Thesis(int id, String title, String author, String content,String date) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.content = content;
		this.date = date;
	}


	public Thesis() {
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

}
