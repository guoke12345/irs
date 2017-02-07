package action;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pojo.Education;
import service.IEducationService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class EducationAction extends ActionSupport{
	private IEducationService educationService;
	private int id;
	private String eduOneName;
	private String eduTwoName;
	private String eduTitle;
	private String eduContent;
	private String eduTime;
	private Education education;
	private int eduNum;
	private int pageno=1;
	private int pageSize=10;
	private Pagination<Education> pagination;
	Log log = LogFactory.getLog(this.getClass());
	/**
	 * 获取研究生教育列表的方法
	 * @return
	 */
	public String eduListto(){
		try {
			pagination = this.educationService.educationPg(pageno, pageSize);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "eduListto";
	}
	/**
	 * 跳转到添加界面的方法
	 */
	public String addEducationto(){
		return "addEducationto";
	}
	/**
	 * 添加方法
	 */
	public String addEducation(){
		try {
			Date d1 = new Date(); 
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");  
			String dateNowStr1 = date.format(d1);
			String date1 = dateNowStr1;
			Education education = new Education();
			education.setEduOneName(eduOneName);
			education.setEduTwoName(eduTwoName);
			education.setEduContent(eduContent);
			education.setEduTitle(eduTitle);
			education.setEduTime(date1);
			this.educationService.addEducation(education);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return eduListto();
	}
	/**
	 * 删除方法
	 */
	public String deleteByid(){
		try {
			this.educationService.deleteByid(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return eduListto();
	}
	/**
	 * 跳转到修改界面的方法
	 */
	public String updateEducationto(){
		try {
			education = this.educationService.education(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "updateEducationto";
	}
	/**
	 * 获取内容界面
	 */
	public String getContent(){
		try {
			education = this.educationService.education(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "getContent";
	}
	/**
	 * 修改方法
	 */
	public String updateEducation(){
		try {
			Date d1 = new Date(); 
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");  
			String dateNowStr1 = date.format(d1);
			String date1 = dateNowStr1;
			Education education = new Education();
			education.setId(id);
			education.setEduOneName(eduOneName);
			education.setEduTwoName(eduTwoName);
			education.setEduTitle(eduTitle);
			education.setEduContent(eduContent);
			education.setEduTime(date1);
			this.educationService.updateEducation(education);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return eduListto();
	}
	/**
	 * get  set 
	 * @return
	 */
	public IEducationService getEducationService() {
		return educationService;
	}

	public void setEducationService(IEducationService educationService) {
		this.educationService = educationService;
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
	public Pagination<Education> getPagination() {
		return pagination;
	}
	public void setPagination(Pagination<Education> pagination) {
		this.pagination = pagination;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	
	
}
