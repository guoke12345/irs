package action;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pojo.Study;
import pojo.Studyone;
import pojo.Studytwo;
import service.IStudyService;
import service.IStudyoneService;
import service.IStudytwoService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class StudyoneAction extends ActionSupport{
	private IStudyoneService studyoneService;
	private IStudytwoService studytwoService;
	private IStudyService studyService;
	private int id;
	private String studyOneName;
	private int pageno=1;
	private int pageSize=10;
	private Pagination<Studyone> pagination;
	private List<Studytwo> studytwoList;
	private List<Study> studyList;
	Log log = LogFactory.getLog(this.getClass()); 
	/**
	 * 跳转到一级标题添加界面
	 */
	public String addstudyOneNameto(){
		return "addstudyOneNameto";
	}
	/**
	 * 添加
	 */
	public String addstudyOneName(){
		try {
			Studyone studyone = new Studyone(); 
			studyone.setStudyOneName(studyOneName);
			this.studyoneService.addStudyOneName(studyone);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return studyOnePg();
	}
	/**
	 * 分页遍历列表
	 */
	public String studyOnePg(){
		try {
			pagination = this.studyoneService.studyOnePg(pageno, pageSize);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "studyOnePg";
	}
	/**
	 * 删除方法
	 */
	public String deleteByid(){
		try {
			this.studyoneService.deleteByud(id);
			int studyOneName_id = id;
			studytwoList = this.studytwoService.studytwoListByid(studyOneName_id);
			int a = studytwoList.size();
			for(int i=0;i<a;i++){
				Studytwo studytwo = new Studytwo();
				studytwo = studytwoList.get(i);
				int id = studytwo.getId();
				this.studytwoService.deleteByid(id);
			}
			studyList = this.studyService.studyListByOne(studyOneName_id);
			int b = studyList.size();
			for(int i=0;i<b;i++){
				Study study = new Study();
				study = studyList.get(i);
				int id = study.getId();
				this.studyService.deleteByid(id);
			}
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return studyOnePg();
	}
	/**
	 * get  set 
	 * @return
	 */
	
	public int getId() {
		return id;
	}
	public IStudyoneService getStudyoneService() {
		return studyoneService;
	}
	public void setStudyoneService(IStudyoneService studyoneService) {
		this.studyoneService = studyoneService;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudyOneName() {
		return studyOneName;
	}
	public void setStudyOneName(String studyOneName) {
		this.studyOneName = studyOneName;
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
	public Pagination<Studyone> getPagination() {
		return pagination;
	}
	public void setPagination(Pagination<Studyone> pagination) {
		this.pagination = pagination;
	}
	public IStudytwoService getStudytwoService() {
		return studytwoService;
	}
	public void setStudytwoService(IStudytwoService studytwoService) {
		this.studytwoService = studytwoService;
	}
	public IStudyService getStudyService() {
		return studyService;
	}
	public void setStudyService(IStudyService studyService) {
		this.studyService = studyService;
	}
	public List<Studytwo> getStudytwoList() {
		return studytwoList;
	}
	public void setStudytwoList(List<Studytwo> studytwoList) {
		this.studytwoList = studytwoList;
	}
	public List<Study> getStudyList() {
		return studyList;
	}
	public void setStudyList(List<Study> studyList) {
		this.studyList = studyList;
	}
	
	
}
