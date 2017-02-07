package action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import pojo.Research;
import pojo.Teacher;
import service.IResearchService;
import service.ITeacherService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class ResearchAction extends ActionSupport{
	private IResearchService researchService;
	private ITeacherService teacherService;
	private int id;
	private int teaId;
	private String  resNavigation;				//导航
	private String resContent;  				//内容
	private Research research;					
	private Pagination<Research> pagination;	
	private int pageno=1;
	private int pageSize=10;
	private List<Teacher> teacherList;
	private Teacher teacher;
	HttpSession session2;
	private String userName;
	private String teaName;
	Log log = LogFactory.getLog(this.getClass());
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public ITeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(ITeacherService teacherService) {
		this.teacherService = teacherService;
	}
	public List<Teacher> getTeacherList() {
		return teacherList;
	}
	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}
	public Research getResearch() {
		return research;
	}
	public void setResearch(Research research) {
		this.research = research;
	}
	public Pagination<Research> getPagination() {
		return pagination;
	}
	public void setPagination(Pagination<Research> pagination) {
		this.pagination = pagination;
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
	public IResearchService getResearchService() {
		return researchService;
	}
	public void setResearchService(IResearchService researchService) {
		this.researchService = researchService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTeaId() {
		return teaId;
	}
	public void setTeaId(int teaId) {
		this.teaId = teaId;
	}
	public String getResNavigation() {
		return resNavigation;
	}
	public void setResNavigation(String resNavigation) {
		this.resNavigation = resNavigation;
	}
	public String getResContent() {
		return resContent;
	}
	public void setResContent(String resContent) {
		this.resContent = resContent;
	}
	
	public String list(){
		try {
			pagination = researchService.getAllPag(pageno, pageSize);
			for(int i = 0;i<pagination.getList().size();i++){
				research = new Research();
				research = pagination.getList().get(i);
				int research1 = research.getTeaId();
				Teacher tea = teacherService.GetById(research1);
				String teaName = tea.getTeaName();
				research.setTeaName(teaName);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list";
	}
	
	
	/**
	 * 跳转到添加页面
	 */
	public String toAdd(){
		try {
			teacherList = teacherService.getAllList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "add";
	}
	/**
	 * 跳转到tea添加页面
	 */
	public String toTeaAdd(){
		try {
			teacher = teacherService.GetById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "add";
	}	
	/**
	 * 添加
	 */
	public String add(){
		try {
			research = new Research();
			research.setResContent(resContent);
			research.setResNavigation(resNavigation);
			research.setTeaId(teaId);
			researchService.add(research);
			
			//修改teacher的 ServiceExist状态。
			teacher = teacherService.GetById(teaId);
			teacher.setServiceExist(1);
			teacherService.update(teacher);
			
			session2 = ServletActionContext.getRequest().getSession();
			userName = (String) session2.getAttribute("userName");
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		if(userName == null){
			return tealist();
		}else
			return list();
	}
	
	public String tealist(){
		try {
			pagination = researchService.getTeaPag(teaId,pageno, pageSize);
			for(int i = 0;i<pagination.getList().size();i++){
				research = new Research();
				research = pagination.getList().get(i);
				int id1 = research.getTeaId();
				Teacher teacher1 = teacherService.GetById(id1);
				String teaName = teacher1.getTeaName();
				research.setTeaName(teaName);
			}
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "list";
	}
	
	
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String toUpdate(){
		try {
			research = researchService.GetById(id);
			teacherList = teacherService.getAllList();
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "update";
	}
	
	/**
	 * 修改
	 */
	public String update(){
		try {
			research = new Research();
			research.setId(id);
			research.setResContent(resContent);
			research.setResNavigation(resNavigation);
			research.setTeaId(teaId);
			researchService.update(research);
			
			session2 = ServletActionContext.getRequest().getSession();
			userName = (String) session2.getAttribute("userName");
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		if(userName == null){
			return tealist();
		}else
			return list();
	}
	/**
	 * 删除
	 */
	public String delete(){
		try {
			researchService.delete(id);
			session2 = ServletActionContext.getRequest().getSession();
			userName = (String) session2.getAttribute("userName");
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		if(userName == null){
			return tealist();
		}else
			return list();
	}
	
	/**
	 * 详细信息
	 */
	public String getContent(){
		try {
			research = researchService.GetById(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "content";
	}
}
