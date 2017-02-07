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

public class StudytwoAction extends ActionSupport{
	private IStudytwoService studytwoService;
	private IStudyoneService studyoneService;
	private IStudyService studyService;
	private int id;
	private String studyTwoName;
	private int studyOneName_id;
	private List<Studyone> studyoneList;
	private List<Study> studyList;
	private int pageno=1;
	private int pageSize=10;
	private Pagination<Studytwo> pagination;
	private Studyone studyone;
	Log log = LogFactory.getLog(this.getClass()); 
	
	/**
	 * 跳转到添加界面
	 */
	public String addStudytwoto(){
		try {
			studyoneList = this.studyoneService.studyoneList();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return "addStudytwoto";
	}
	/**
	 * 添加方法
	 */
	public String addStudytwo(){
		try {
			Studytwo studytwo = new Studytwo();
			studytwo.setStudyTwoName(studyTwoName);
			studytwo.setStudyOneName_id(studyOneName_id);
			this.studytwoService.addStudytwo(studytwo);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return studytwoPg();
		
	}
	/**
	 * 分页遍历列表
	 */
	public String studytwoPg(){
		try {
			pagination = this.studytwoService.studytwoPg(pageno, pageSize);
			
			int studytwoSize = pagination.getMaxElements();
			//添加一条数据
			for(int i=0;i<pagination.getList().size();i++){
				int id=pagination.getList().get(i).getStudyOneName_id();
				studyone = this.studyoneService.studyone(id);
				String studyOneName = "";
				studyOneName = studyOneName + studyone.getStudyOneName();
				pagination.getList().get(i).setStudyOneName(studyOneName);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		
		return "studytwoPg";
	}
	/**
	 * 删除方法
	 */
	public String deleteByid(){
		try {
			this.studytwoService.deleteByid(id);
			int studyTwoName_id = id; 
			studyList = this.studyService.studyListByTwo(studyTwoName_id);
			int a = studyList.size();
			for(int i=0;i<a;i++){
				Study study = new Study();
				study = studyList.get(i);
				int id = study.getId();
				this.studyService.deleteByid(id);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return "error";
		}
		return studytwoPg();
	}
	/**
	 * get  set 
	 * @return
	 */
	
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
	public String getStudyTwoName() {
		return studyTwoName;
	}
	public void setStudyTwoName(String studyTwoName) {
		this.studyTwoName = studyTwoName;
	}
	public int getStudyOneName_id() {
		return studyOneName_id;
	}
	public void setStudyOneName_id(int studyOneName_id) {
		this.studyOneName_id = studyOneName_id;
	}
	public IStudyoneService getStudyoneService() {
		return studyoneService;
	}
	public void setStudyoneService(IStudyoneService studyoneService) {
		this.studyoneService = studyoneService;
	}
	public List<Studyone> getStudyoneList() {
		return studyoneList;
	}
	public void setStudyoneList(List<Studyone> studyoneList) {
		this.studyoneList = studyoneList;
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
	public Pagination<Studytwo> getPagination() {
		return pagination;
	}
	public void setPagination(Pagination<Studytwo> pagination) {
		this.pagination = pagination;
	}
	public IStudyService getStudyService() {
		return studyService;
	}
	public void setStudyService(IStudyService studyService) {
		this.studyService = studyService;
	}
	public List<Study> getStudyList() {
		return studyList;
	}
	public void setStudyList(List<Study> studyList) {
		this.studyList = studyList;
	}
	public Studyone getStudyone() {
		return studyone;
	}
	public void setStudyone(Studyone studyone) {
		this.studyone = studyone;
	}
	
	
	

}
