package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import pojo.Research;
import pojo.Teacher;
import pojo.Teachergroup;
import service.IResearchService;
import service.ITeacherService;
import service.ITeachergroupService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class TeacherAction extends ActionSupport{
	Log log = LogFactory.getLog(this.getClass()); 
	private ITeacherService teacherService;
	private IResearchService researchService;
	private ITeachergroupService  teachergroupService;
	private int pageno=1;
	private int pageSize=10;
	private Pagination<Teacher> pagination;
	private String tgName;
	private int id;
	private Teacher teacher;
	private List<Teachergroup> teachergroup;
	private List<Research> researchList;
	private String teaAwardOrActive;
	private String teaEducationalBackground;
	private String teaDirection;
	private String teaEmail;
	private String teaFax;
	private String teaMajorThesis;
	private String teaMajorThesisTitle;
	private String teaName;
	private String teaPhone;
	private String teaPhoto;
	private String teaposition;
	private int teaTeam_id;
	private String teaworkexperience;
	private List<Teacher> teacherList;
	private String password;
	private String result;
	HttpSession session2;
	private int dengji;
	
	private File photoImg;//上传到的临时文件
	private String photoImgFileName;
	
	
	
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	public int getDengji() {
		return dengji;
	}
	public void setDengji(int dengji) {
		this.dengji = dengji;
	}
	public IResearchService getResearchService() {
		return researchService;
	}
	public void setResearchService(IResearchService researchService) {
		this.researchService = researchService;
	}
	public List<Research> getResearchList() {
		return researchList;
	}
	public void setResearchList(List<Research> researchList) {
		this.researchList = researchList;
	}
	public String getTeaMajorThesisTitle() {
		return teaMajorThesisTitle;
	}
	public void setTeaMajorThesisTitle(String teaMajorThesisTitle) {
		this.teaMajorThesisTitle = teaMajorThesisTitle;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public HttpSession getSession2() {
		return session2;
	}
	public void setSession2(HttpSession session2) {
		this.session2 = session2;
	}
	public List<Teacher> getTeacherList() {
		return teacherList;
	}
	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}
	public String getTeaAwardOrActive() {
		return teaAwardOrActive;
	}
	public void setTeaAwardOrActive(String teaAwardOrActive) {
		this.teaAwardOrActive = teaAwardOrActive;
	}
	public String getTeaEducationalBackground() {
		return teaEducationalBackground;
	}
	public void setTeaEducationalBackground(String teaEducationalBackground) {
		this.teaEducationalBackground = teaEducationalBackground;
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
	public String getTeaFax() {
		return teaFax;
	}
	public void setTeaFax(String teaFax) {
		this.teaFax = teaFax;
	}
	public String getTeaMajorThesis() {
		return teaMajorThesis;
	}
	public void setTeaMajorThesis(String teaMajorThesis) {
		this.teaMajorThesis = teaMajorThesis;
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
	public String getTeaposition() {
		return teaposition;
	}
	public void setTeaposition(String teaposition) {
		this.teaposition = teaposition;
	}
	public int getTeaTeam_id() {
		return teaTeam_id;
	}
	public void setTeaTeam_id(int teaTeam_id) {
		this.teaTeam_id = teaTeam_id;
	}
	public String getTeaworkexperience() {
		return teaworkexperience;
	}
	public void setTeaworkexperience(String teaworkexperience) {
		this.teaworkexperience = teaworkexperience;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Teachergroup> getTeachergroup() {
		return teachergroup;
	}

	public void setTeachergroup(List<Teachergroup> teachergroup) {
		this.teachergroup = teachergroup;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ITeachergroupService getTeachergroupService() {
		return teachergroupService;
	}

	public void setTeachergroupService(ITeachergroupService teachergroupService) {
		this.teachergroupService = teachergroupService;
	}

	public String getTgName() {
		return tgName;
	}

	public void setTgName(String tgName) {
		this.tgName = tgName;
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

	public Pagination<Teacher> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Teacher> pagination) {
		this.pagination = pagination;
	}
	public ITeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(ITeacherService teacherService) {
		this.teacherService = teacherService;
	}
	public String getTeaPhoto() {
		return teaPhoto;
	}
	public void setTeaPhoto(String teaPhoto) {
		this.teaPhoto = teaPhoto;
	}
	public File getPhotoImg() {
		return photoImg;
	}
	public void setPhotoImg(File photoImg) {
		this.photoImg = photoImg;
	}
	public String getPhotoImgFileName() {
		return photoImgFileName;
	}
	public void setPhotoImgFileName(String photoImgFileName) {
		this.photoImgFileName = photoImgFileName;
	}
	
	/**
	 * 标记方法
	 */
	public String biaoji(){
		try {
			teacher = this.teacherService.GetById(id);
			teacher.setDengji(1);
			teacherService.update(teacher);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return teacherListto();
	}
	/**
	 * 标记的列表
	 */
	public String biaojiList(){
		try {
			pagination = this.teacherService.getbiaoji(pageno, pageSize);
			for(int i = 0;i<pagination.getList().size();i++){
				Teacher pag = pagination.getList().get(i);
				tgName = teachergroupService.GetById(pag.getTeaTeam_id()).getTgName();
				pag.setTgName(tgName);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "biaojiList";
	}
	/**
	 * 取消标记
	 */
	public String quxiaobiaoji(){
		try {
			teacher = this.teacherService.GetById(id);
			teacher.setDengji(0);
			this.teacherService.update(teacher);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return biaojiList();
	}

	/**
	 * 登陆方法
	 */
	public void Login() throws IOException{
			teacherList = teacherService.findByteaName(teaName);
			if(teacherList.size()==0){
				result="0";							//用户名不存在
			}else{
				teacher=teacherList.get(0);	
				MD5 getMD5 = new MD5();
				String passwd = getMD5.GetMD5Code(password);
				if(!teacher.getPassword().equals(passwd)){
					result="2";						//密码不正确
				}else{			
			        	session2 = ServletActionContext.getRequest().getSession();
						session2.setAttribute("teaName", teaName);
						result="1";					//登陆成功

				}
			}
			ServletActionContext.getResponse().getWriter().write(result);
	}
	/**
	 * 登陆界面
	 */
	public String teacherto(){
		return "teacherto";
	}
	public String center(){
		return "center";
	}
	public String left(){
		try {
			session2 = ServletActionContext.getRequest().getSession();
			teaName = (String) session2.getAttribute("teaName");
			teacherList =  teacherService.findByteaName(teaName);
			teacher = teacherList.get(0);
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "left";
	}
	/**
	 * 教师列表
	 */
	public String teacherListto(){
		try {
			pagination = teacherService.getAllPag(pageno, pageSize);
			for(int i = 0;i<pagination.getList().size();i++){
				Teacher pag = pagination.getList().get(i);
				tgName = teachergroupService.GetById(pag.getTeaTeam_id()).getTgName();
				pag.setTgName(tgName);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "list";
	}
	/**
	 * 我的个人信息
	 */
	public String myinfo(){
		try {
			session2 = ServletActionContext.getRequest().getSession();
			teaName = (String) session2.getAttribute("teaName");
			teacherList =  teacherService.findByteaName(teaName);
			for(int i = 0;i<teacherList.size();i++){
				Teacher pag = teacherList.get(i);
				tgName = teachergroupService.GetById(pag.getTeaTeam_id()).getTgName();
				pag.setTgName(tgName);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "myinfo";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String toadd(){
		try {
			teachergroup = teachergroupService.getAll();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "add";
	}
	/**
	 * 添加
	 * @throws IOException 
	 */
	public String add() throws IOException{
		try {
			teacher = new Teacher();
			savePhoto(teacher);
			teacher.setTeaAwardOrActive(teaAwardOrActive);
			teacher.setTeaDirection(teaDirection);
			teacher.setTeaEducationalBackground(teaEducationalBackground);
			teacher.setTeaEmail(teaEmail);
			teacher.setTeaFax(teaFax);
			teacher.setTeaMajorThesis(teaMajorThesis);
			teacher.setTeaName(teaName);
			MD5 getMD5 = new MD5();
			String passwd = getMD5.GetMD5Code(password);
			teacher.setPassword(passwd);
			teacher.setTeaPhone(teaPhone);
			teacher.setTeaposition(teaposition);
			teacher.setTeaTeam_id(teaTeam_id);
			teacher.setTeaworkexperience(teaworkexperience);
			teacherService.add(teacher);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return teacherListto();
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String toUpdate(){
		try {
			teachergroup = teachergroupService.getAll();
			teacher = teacherService.GetById(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "update";
	}
	
	/**
	 * 修改
	 * @throws IOException 
	 */
	public String update() throws IOException{
		try {
			teacher = new Teacher();
			teacher = teacherService.GetById(id);
			savePhoto(teacher);
			teacher.setTeaAwardOrActive(teaAwardOrActive);
			teacher.setTeaDirection(teaDirection);
			teacher.setTeaEducationalBackground(teaEducationalBackground);
			teacher.setTeaEmail(teaEmail);
			teacher.setTeaFax(teaFax);
			teacher.setTeaMajorThesis(teaMajorThesis);
			teacher.setTeaName(teaName);
			MD5 getMD5 = new MD5();
			String passwd = getMD5.GetMD5Code(password);
			teacher.setPassword(passwd);
			teacher.setTeaPhone(teaPhone);
			teacher.setTeaposition(teaposition);
			teacher.setTeaTeam_id(teaTeam_id);
			teacher.setTeaworkexperience(teaworkexperience);
			teacher.setDengji(dengji);
			teacherService.update(teacher);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return getContent();
	}
	/**
	 * 删除
	 */
	public String delete(){
		try {
			teacherService.delete(id);
			researchList = researchService.getListByTeaId(id);
			for(int i=0;i<researchList.size();i++){
				Research research = researchList.get(i);
				int id1 = research.getId();
				researchService.delete(id1);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return teacherListto();
	}
	
	/**
	 * 跳转到详细信息页面
	 */
	public String content(){
		try {
			teacher = teacherService.GetById(id);
			teacher.setTgName(teachergroupService.GetById(id).getTgName());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "content";
	}
	/**
	 * 获取详细信息
	 */
	public String getContent(){
		try {
			teacher = teacherService.GetById(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "content";
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
		private void savePhoto(Teacher teacher) throws IOException{
			if(photoImg != null){
			//当前时间
			Date d = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateNow = sdf1.format(d);
			//文件重命名为  “teacher_+id” 的形式
			teaPhoto ="front/net/teacherPhoto/"+ dateNow + photoImgFileName.substring(photoImgFileName.lastIndexOf("."));
			//将上传后struts保存的临时文件photoImg 复制到指定文件夹
			FileOutputStream fos = new FileOutputStream(getFileSavePath()+teaPhoto);
			FileInputStream fis = new FileInputStream(photoImg);
			byte[] b = new byte[512];
			int length = 0;
			while ((length = fis.read(b)) > 0)
				fos.write(b, 0, length);
			fis.close();
			fos.close();
			teacher.setTeaPhoto(teaPhoto);
			}
		}
		/**
		 * 删除图片
		 */
		public void deletePhoto(Teacher teacher){
			File p = new File(getFileSavePath() + teacher.getTeaPhoto());
			
			if(p.exists()){
				p.delete();  //删除文件
			}
		}
}