package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import pojo.Study;
import pojo.Studyone;
import pojo.Studytwo;
import pojo.Teacher;

import service.IStudyService;
import service.IStudyoneService;
import service.IStudytwoService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class StudyAction extends ActionSupport{
	private IStudyService studyService;
	private IStudyoneService studyoneService;
	private IStudytwoService studytwoService;
	private int id;
	private int studyOneName_id;
	private int studyTwoName_id;
	private String studyTitle;
	private String studyContent;
	private String studyTime;
	private String fileName;
	private String filePath;
	//Studyone
	private List<Studyone> studyoneList;
	private List<Studytwo> studytwoList;
	private int pageno=1;
	private int pageSize=10;
	private Pagination<Study> pagination;
	private Studyone studyone;
	private Studytwo studytwo;
	private Study study;
	private File upfile;
	private String upfileFileName;
	private String upFileContentType;
	Log log = LogFactory.getLog(this.getClass()); 
	/** 
	 * 添加界面跳转方法
	 * 选择月份
	 */
	public String addStudyto(){
		try {
			studyoneList = this.studyoneService.studyoneList();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "addStudyto";
	}
	/**
	 * 跳转到添加界面
	 */
	public String addStudytoto(){
		try {
			studytwoList = this.studytwoService.studytwoListByid(studyOneName_id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "addStudytoto";
	}
	/**
	 * 添加方法
	 * @throws IOException 
	 */
	public String addStudy() throws IOException{
		try {
			Date d1 = new Date(); 
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");  
			String dateNowStr1 = date.format(d1);
			String date1 = dateNowStr1;
			study = new Study();
			saveFile();
			study.setStudyOneName_id(studyOneName_id);
			study.setStudyTwoName_id(studyTwoName_id);
			study.setStudyTitle(studyTitle);
			study.setStudyContent(studyContent);
			study.setStudyTime(date1);
			this.studyService.addStudy(study);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return studyPg();
	}
	/**
	 * 分页遍历
	 */
	public String studyPg(){
		try {
			pagination = this.studyService.studyPg(pageno, pageSize);
			
			int studySize = pagination.getMaxElements();
			//添加一条数据
			for(int i=0;i<pagination.getList().size();i++){
				int id=pagination.getList().get(i).getStudyOneName_id();
				studyone = this.studyoneService.studyone(id);
				String studyOneName = "";
				studyOneName = studyOneName + studyone.getStudyOneName();
				pagination.getList().get(i).setStudyOneName(studyOneName);
				
				int id1=pagination.getList().get(i).getStudyTwoName_id();
				if(id1 != 0){
				studytwo = this.studytwoService.studytwo(id1);
				String studyTwoName = "";
				studyTwoName = studyTwoName + studytwo.getStudyTwoName();
				pagination.getList().get(i).setStudyTwoName(studyTwoName);
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "studyPg";
	}
	/**
	 *删除方法 
	 * 
	 */
	public String deleteByid(){
		try {
			this.studyService.deleteByid(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return studyPg();
	}
	/**
	 * 跳转到修改界面
	 */
	public String updateStudyto(){
		try {
			study = this.studyService.study(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "updateStudyto";
	}
	/**
	 * 修改方法
	 * @throws IOException 
	 */
	public String updateStudy() throws IOException{
		try {
			Date d1 = new Date(); 
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");  
			String dateNowStr1 = date.format(d1);
			String date1 = dateNowStr1;
//        study = new Study();
//		study.setId(id);
			study = studyService.study(id);
			saveFile();
			study.setStudyOneName_id(studyOneName_id);
			study.setStudyTwoName_id(studyTwoName_id);
			study.setStudyTitle(studyTitle);
			study.setStudyContent(studyContent);
			study.setStudyTime(date1);
			this.studyService.updateStudy(study);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return studyPg();
	}
	/**
	 * 获取内容页
	 */
	public String getContentto(){
		try {
			study = this.studyService.study(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "getContentto";
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
		private void saveFile() throws IOException{
			if(upfile != null){
			//当前时间
			Date d = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateNow = sdf1.format(d);
			//文件重命名为  “teacher_+id” 的形式
			filePath ="front/net/uploadFile/"+ dateNow + upfileFileName.substring(upfileFileName.lastIndexOf("."));
			//将上传后struts保存的临时文件photoImg 复制到指定文件夹
			FileOutputStream fos = new FileOutputStream(getFileSavePath()+filePath);
			FileInputStream fis = new FileInputStream(upfile);
			byte[] b = new byte[512];
			int length = 0;
			while ((length = fis.read(b)) > 0)
				fos.write(b, 0, length);
			fis.close();
			fos.close();
			study.setFilePath(filePath);
			study.setFileName(upfileFileName);
			}
		}
	/**
	 * get set
	 * @return
	 */
	public IStudyService getStudyService() {
		return studyService;
	}
	public void setStudyService(IStudyService studyService) {
		this.studyService = studyService;
	}
	public IStudyoneService getStudyoneService() {
		return studyoneService;
	}
	public void setStudyoneService(IStudyoneService studyoneService) {
		this.studyoneService = studyoneService;
	}
	public IStudytwoService getStudytwoService() {
		return studytwoService;
	}
	public void setStudytwoService(IStudytwoService studytwoService) {
		this.studytwoService = studytwoService;
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
	public List<Studyone> getStudyoneList() {
		return studyoneList;
	}
	public void setStudyoneList(List<Studyone> studyoneList) {
		this.studyoneList = studyoneList;
	}
	public List<Studytwo> getStudytwoList() {
		return studytwoList;
	}
	public void setStudytwoList(List<Studytwo> studytwoList) {
		this.studytwoList = studytwoList;
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
	public Pagination<Study> getPagination() {
		return pagination;
	}
	public void setPagination(Pagination<Study> pagination) {
		this.pagination = pagination;
	}
	public Studyone getStudyone() {
		return studyone;
	}
	public void setStudyone(Studyone studyone) {
		this.studyone = studyone;
	}
	public Studytwo getStudytwo() {
		return studytwo;
	}
	public void setStudytwo(Studytwo studytwo) {
		this.studytwo = studytwo;
	}
	public Study getStudy() {
		return study;
	}
	public void setStudy(Study study) {
		this.study = study;
	}
	public File getUpfile() {
		return upfile;
	}
	public void setUpfile(File upfile) {
		this.upfile = upfile;
	}
	public String getUpfileFileName() {
		return upfileFileName;
	}
	public void setUpfileFileName(String upfileFileName) {
		this.upfileFileName = upfileFileName;
	}
	public String getUpFileContentType() {
		return upFileContentType;
	}
	public void setUpFileContentType(String upFileContentType) {
		this.upFileContentType = upFileContentType;
	}
}
