package action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pojo.Thesis;
import service.IThesisService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class ThesisAction extends ActionSupport{
	private int id;
	private String title;
	private String author;
	private String content;
	private Thesis thesis;
	private int pageno=1;
	private int pageSize=10;
	private Pagination<Thesis> pagination;
	private List<Thesis> thesisList;
	private IThesisService thesisService;
	private String date;
	Log log = LogFactory.getLog(this.getClass()); 
	
	/**
	 * 获取所有列表
	 * @return
	 */
//	public String getAll(){
//		thesisService.getAllList();
//		return "";
//	}
	
	/**
	 * 获取所有分页列表
	 * @return
	 */
	public String getAllPag(){
		try {
			pagination = thesisService.getAllPag(pageno, pageSize);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "list";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String toAdd(){
		return  "add";
	}
	/**
	 * 添加
	 * @return
	 */
	public String add(){
		try {
			Date d = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateNow = sdf1.format(d);
			thesis = new Thesis();
			thesis.setAuthor(author);
			thesis.setContent(content);
			thesis.setTitle(title);
			thesis.setDate(dateNow);
			thesisService.add(thesis);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return getAllPag();
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		try {
			thesisService.delete(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return getAllPag();
	}
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String toAlter(){
		try {
			thesis = thesisService.getById(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "alter";
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String alter(){
		try {
			Date d = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateNow = sdf1.format(d);
			thesis = new Thesis();
			thesis = thesisService.getById(id);
			thesis.setContent(content);
			thesis.setDate(dateNow);
			thesis.setTitle(title);
			thesisService.alter(thesis);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return getAllPag();
	}
	
	/**
	 * get set 方法
	 * @return
	 */
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

	public Thesis getThesis() {
		return thesis;
	}

	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
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

	public IThesisService getThesisService() {
		return thesisService;
	}

	public void setThesisService(IThesisService thesisService) {
		this.thesisService = thesisService;
	}

	public Pagination<Thesis> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Thesis> pagination) {
		this.pagination = pagination;
	}

	public List<Thesis> getThesisList() {
		return thesisList;
	}

	public void setThesisList(List<Thesis> thesisList) {
		this.thesisList = thesisList;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
