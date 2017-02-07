package action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pojo.Research;
import pojo.Teacher;
import pojo.Teachergroup;
import service.ITeacherService;
import service.ITeachergroupService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class TeachergroupAction extends ActionSupport{
	private ITeachergroupService teachergroupService;
	private int pageno=1;
	private int pageSize=10;
	private Pagination<Teachergroup> pagination;
	private List<Teachergroup> teachergroupList;
	private Teachergroup teachergroup;
	private int id;
	private String tgName;
	Log log = LogFactory.getLog(this.getClass()); 
	
	public List<Teachergroup> getTeachergroupList() {
		return teachergroupList;
	}


	public void setTeachergroupList(List<Teachergroup> teachergroupList) {
		this.teachergroupList = teachergroupList;
	}


	public Teachergroup getTeachergroup() {
		return teachergroup;
	}


	public void setTeachergroup(Teachergroup teachergroup) {
		this.teachergroup = teachergroup;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTgName() {
		return tgName;
	}


	public void setTgName(String tgName) {
		this.tgName = tgName;
	}


	public ITeachergroupService getTeachergroupService() {
		return teachergroupService;
	}


	public void setTeachergroupService(ITeachergroupService teachergroupService) {
		this.teachergroupService = teachergroupService;
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


	public Pagination<Teachergroup> getPagination() {
		return pagination;
	}


	public void setPagination(Pagination<Teachergroup> pagination) {
		this.pagination = pagination;
	}
	
	/**
	 * 列表
	 */
	public	String list(){
		try {
			teachergroupList = teachergroupService.getAll();
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "list";
	}
	/**
	 * 跳转到添加页面
	 */
	public String toAdd(){
		
		return "add";
	}
	/**
	 * 添加
	 */
	public String add(){
		try {
			teachergroup = new Teachergroup();
			teachergroup.setTgName(tgName);
			teachergroupService.add(teachergroup);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return list();
	}
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String toUpdate(){
		try {
			teachergroup = teachergroupService.GetById(id);
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
			teachergroup = new Teachergroup();
			teachergroup.setId(id);
			teachergroup.setTgName(tgName);
			teachergroupService.update(teachergroup);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return list();
	}
	/**
	 * 删除
	 */
	public String delete(){
		try {
			teachergroupService.delete(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return list();
	}

}
