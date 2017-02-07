package pojo;

public class Carousel {
	private int id;
	private String carouselName;
	private String carouselPhoto;
	private String carouselTime;
	private String carouselContent;
	private int carouselNum;
	
	
	
	public Carousel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Carousel(int id, String carouselName, String carouselPhoto,
			String carouselTime, String carouselContent, int carouselNum) {
		super();
		this.id = id;
		this.carouselName = carouselName;
		this.carouselPhoto = carouselPhoto;
		this.carouselTime = carouselTime;
		this.carouselContent = carouselContent;
		this.carouselNum = carouselNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarouselName() {
		return carouselName;
	}
	public void setCarouselName(String carouselName) {
		this.carouselName = carouselName;
	}
	public String getCarouselPhoto() {
		return carouselPhoto;
	}
	public void setCarouselPhoto(String carouselPhoto) {
		this.carouselPhoto = carouselPhoto;
	}
	public String getCarouselTime() {
		return carouselTime;
	}
	public void setCarouselTime(String carouselTime) {
		this.carouselTime = carouselTime;
	}
	public String getCarouselContent() {
		return carouselContent;
	}
	public void setCarouselContent(String carouselContent) {
		this.carouselContent = carouselContent;
	}
	public int getCarouselNum() {
		return carouselNum;
	}
	public void setCarouselNum(int carouselNum) {
		this.carouselNum = carouselNum;
	}
	
	
	
	

}
