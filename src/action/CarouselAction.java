package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import pojo.Article;
import pojo.Carousel;
import service.ICarouselService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class CarouselAction extends ActionSupport{
	private ICarouselService carouselService;
	private int id;
	private String carouselName;
	private String carouselPhoto;
	private String carouselTime;
	private String carouselContent;
	private int carouselNum;
	private int pageno=1;
	private int pageSize=10;
	private Pagination<Carousel> pagination;
	private Carousel carousel;
	private File photoImg;//上传到的临时文件
	private String photoImgFileName;
	Log log = LogFactory.getLog(this.getClass());
	/**
	 * 获取列表
	 */
	public String carouselListto(){
		pagination = this.carouselService.carouselPg(pageno, pageSize);
		return "carouselListto";
	}
	/**
	 *跳转到添加界面的方法
	 */
	public String addCarouselto(){
		return "addCarouselto";
	}
	/**
	 * 添加方法
	 * @throws IOException 
	 */
	public String addCarousel() throws IOException{
		try {
			carousel = new Carousel();
			savePhoto(carousel);
			Date d1 = new Date(); 
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");  
			String dateNowStr1 = date.format(d1);
			String date1 = dateNowStr1;
			carousel.setCarouselName(carouselName);
			carousel.setCarouselContent(carouselContent);
			carousel.setCarouselTime(date1);
			carouselService.addCarousel(carousel);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return carouselListto();
	}
	/**
	 * 删除方法
	 */
	public String deleteByid(){
		try {
			this.carouselService.deleteByid(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return carouselListto();
	}
	/**
	 * 跳转到修改界面
	 */
	public String updateCarouselto(){
		try {
			carousel = this.carouselService.carousel(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "updateCarouselto";
	}
	/**
	 * 修改方法
	 * @throws IOException 
	 */
	public String updateCarousel() throws IOException{
		try {
			Date d1 = new Date(); 
			carousel = carouselService.carousel(id);
			savePhoto(carousel);
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");  
			String dateNowStr1 = date.format(d1);
			String date1 = dateNowStr1;
			carousel.setCarouselContent(carouselContent);
			carousel.setCarouselName(carouselName);
			carousel.setCarouselTime(date1);
			carouselService.updateCarousel(carousel);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
        return carouselListto();
	}
	/**
	 * 得到文件的保存路径
	 * @return
	 */
		private String getFileSavePath(){
			return  ServletActionContext.getServletContext().getRealPath("/");
		}
	/**
	 * 保存文件
	 * @param teacher
	 * @throws IOException
	 */
		private void saveArticleImg() throws IOException{
			if(photoImg != null){
				//当前时间
			Date d = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateNow = sdf1.format(d);
			//文件重命名为  “teacher_+id” 的形式
			carouselPhoto ="front/net/images/article/"+ dateNow + photoImgFileName.substring(photoImgFileName.lastIndexOf("."));
			//将上传后struts保存的临时文件photoImg 复制到指定文件夹
			FileOutputStream fos = new FileOutputStream(getFileSavePath()+carouselPhoto);
			FileInputStream fis = new FileInputStream(photoImg);
			byte[] b = new byte[512];
			int length = 0;
			while ((length = fis.read(b)) > 0)
				fos.write(b, 0, length);
			fis.close();
			fos.close();
			}
		}
		/**
		 * 文章图片
		 * @param carousel
		 * @throws IOException
		 */
		private void savePhoto(Carousel carousel) throws IOException{
			if(photoImg != null){
				//当前时间
			Date d = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateNow = sdf1.format(d);
			//文件重命名为  “teacher_+id” 的形式
			carouselPhoto ="front/net/images/"+ dateNow + photoImgFileName.substring(photoImgFileName.lastIndexOf("."));
			//保存图片路径
			carousel.setCarouselPhoto(carouselPhoto);
			//将上传后struts保存的临时文件photoImg 复制到指定文件夹
			FileOutputStream fos = new FileOutputStream(getFileSavePath()+carouselPhoto);
			FileInputStream fis = new FileInputStream(photoImg);
			byte[] b = new byte[512];
			int length = 0;
			while ((length = fis.read(b)) > 0)
				fos.write(b, 0, length);
			fis.close();
			fos.close();
			}
		}
		/**
		 * 删除图片
		 */
		public void deletePhoto(Carousel carousel){
			File p = new File(getFileSavePath() + carousel.getCarouselPhoto());
			if(p.exists()){
				p.delete();  //删除文件
			}
		}
	
	/**
	 * getset
	 * @return
	 */
	
	public ICarouselService getCarouselService() {
		return carouselService;
	}
	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Pagination<Carousel> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Carousel> pagination) {
		this.pagination = pagination;
	}

	public void setCarouselService(ICarouselService carouselService) {
		this.carouselService = carouselService;
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
	public Carousel getCarousel() {
		return carousel;
	}
	public void setCarousel(Carousel carousel) {
		this.carousel = carousel;
	}
	public String getPhotoImgFileName() {
		return photoImgFileName;
	}
	public void setPhotoImgFileName(String photoImgFileName) {
		this.photoImgFileName = photoImgFileName;
	}
	public File getPhotoImg() {
		return photoImg;
	}
	public void setPhotoImg(File photoImg) {
		this.photoImg = photoImg;
	}
	
	
	
}
