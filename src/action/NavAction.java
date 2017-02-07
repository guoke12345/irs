package action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pojo.Article;
import pojo.Nav;
import pojo.Profile;
import service.IArticleService;
import service.INavService;

import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class NavAction extends ActionSupport{
	private INavService navService;
	private IArticleService articleService;
	private String navName;
	private String oneName;
	private int id;
	private int pageno=1;
	private int pageSize=10;
	private Pagination<Nav> pagination;
	private List<Article> artcileList;
	private String nav_oneName;
	Log log = LogFactory.getLog(this.getClass());
	/**
	 * 跳转到添加界面的方法
	 */
	public String addNavto(){
		return "addNavto";
	}
	/**
	 * 添加方法
	 */
	public String addNav(){
		try {
			Nav nav = new Nav();
			nav.setNavName(navName);
			nav.setOneName(oneName);
			this.navService.addNav(nav);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "addNavto";
	}
	/**
	 *分页方法列表 
	 * @return
	 */
	public String navPgto(){
		try {
			pagination = this.navService.navPg(pageno, pageSize);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "navPgto";
	}
	/**
	 * 删除方法
	 */
	public String deleteByid(){
		try {
			this.navService.deleteByid(id);
			int nav_oneName = id;
			artcileList = this.articleService.getArticleByid(nav_oneName);
			int num = artcileList.size();
			for(int i=0;i<num;i++){
				Article article = new Article();
				article = artcileList.get(i);
				int id = article.getId();
				this.articleService.deleteByid(id);
			}
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return navPgto();
	}
	/**
	 * get set
	 * @return
	 */
	
	public INavService getNavService() {
		return navService;
	}
	public void setNavService(INavService navService) {
		this.navService = navService;
	}
	public String getNavName() {
		return navName;
	}
	public void setNavName(String navName) {
		this.navName = navName;
	}
	public String getOneName() {
		return oneName;
	}
	public void setOneName(String oneName) {
		this.oneName = oneName;
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
	public Pagination<Nav> getPagination() {
		return pagination;
	}
	public void setPagination(Pagination<Nav> pagination) {
		this.pagination = pagination;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public IArticleService getArticleService() {
		return articleService;
	}
	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}
	public List<Article> getArtcileList() {
		return artcileList;
	}
	public void setArtcileList(List<Article> artcileList) {
		this.artcileList = artcileList;
	}
	public String getNav_oneName() {
		return nav_oneName;
	}
	public void setNav_oneName(String nav_oneName) {
		this.nav_oneName = nav_oneName;
	}
	
	
}
