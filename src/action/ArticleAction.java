package action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import pojo.Article;
import pojo.Education;
import pojo.Nav;
import service.IArticleService;
import service.INavService;
import com.framework.core.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport{
	private IArticleService articleService;
	private	INavService navService;
	private int id;
	private int nav_oneName_id;
	private List<Nav> navList;
	private int nav_oneName;
	private Nav nav;
	private String nav_navName;
	private String title;
	private String content;
	private String time;
	private int num;
	private List<Nav> navList1;
	private int pageno=1;
	private int pageSize=10;
	private Pagination<Article> pagination1;
	private Article article;
	private List<Article> articleList;
	private String oneName;
	private int dengji;
	Log log = LogFactory.getLog(this.getClass());
	/**
	 * 跳转到科学研究添加界面方法
	 */
	public String addnavThree(){ //导航栏第三栏
		navList1 = this.navService.findBynavName("科学研究");
		return "addnavThree";
	}
	public String addnavFour(){ //导航栏第三栏
		navList1 = this.navService.findBynavName("通知公告");
		return "addnavFour";
	}
	public String addnavFive(){ //导航栏第三栏
		navList1 = this.navService.findBynavName("学术前沿");
		return "addnavFive";
	}
	/**
	 * 文章置顶
	 */
	public String zhiding(){
		try {
			article = this.articleService.article(id);
			article.setDengji(1);
			this.articleService.updateArticle(article);
		}catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return articlePgFour();
	}
	/**
	 * 取消置顶
	 */
	public String nozhiding(){
		try {
			article = this.articleService.article(id);
			article.setDengji(0);
			this.articleService.updateArticle(article);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return zhidingPgFour();
	}
	
	/**
	 * 置顶文章列表
	 */
	public String zhidingPgFour(){
			try {
				pagination1 = this.articleService.zhidingPg(pageno, pageSize, "通知公告");
				int articleSize = pagination1.getMaxElements();
				for(int i=0;i<pagination1.getList().size();i++){
					int id=pagination1.getList().get(i).getNav_oneName();
					nav = this.navService.nav(id);
					String oneName = "";
					oneName = oneName + nav.getOneName();
					pagination1.getList().get(i).setOneName(oneName);
				}
			} catch (RuntimeException e) {
				log.error(e.getMessage());
				return "error";
			}
		return "articlePgFour";
	}
	/**
	 * 添加方法
	 */
	public String addArticle(){
		try {
			Date d1 = new Date(); 
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");  
			String dateNowStr1 = date.format(d1);
			String date1 = dateNowStr1;
			Article article = new Article();
			article.setNav_oneName(nav_oneName);
			article.setTitle(title);
			article.setContent(content);
			article.setTime(date1);
			article.setNav_navName("科学研究");
			this.articleService.addArticle(article);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return articlePg();
	}
	public String addArticleFour(){
		try {
			Date d1 = new Date(); 
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");  
			String dateNowStr1 = date.format(d1);
			String date1 = dateNowStr1;
			Article article = new Article();
			article.setNav_oneName(nav_oneName);
			article.setTitle(title);
			article.setContent(content);
			article.setTime(date1);
			article.setNav_navName("通知公告");
			this.articleService.addArticle(article);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return articlePgFour();
	}
	public String addArticleFive(){
		try {
			Date d1 = new Date(); 
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");  
			String dateNowStr1 = date.format(d1);
			String date1 = dateNowStr1;
			Article article = new Article();
			article.setNav_oneName(nav_oneName);
			article.setTitle(title);
			article.setContent(content);
			article.setTime(date1);
			article.setNav_navName("学术前沿");
			this.articleService.addArticle(article);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return articlePgFive();
	}
	/**
	 * 获取新闻列表 
	 */
	public String articlePg(){
		try {
			pagination1 = this.articleService.articlePg(pageno, pageSize, "科学研究");
			int articleSize = pagination1.getMaxElements();
			//set 总积分
			for(int i=0;i<pagination1.getList().size();i++){
				int id=pagination1.getList().get(i).getNav_oneName();
				nav = this.navService.nav(id);
				String oneName = "";
				oneName = oneName + nav.getOneName();
				pagination1.getList().get(i).setOneName(oneName);
			}
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "articlePg";
	}
	public String articlePgFour(){
		try {
			pagination1 = this.articleService.articlePg(pageno, pageSize, "通知公告");
			int articleSize = pagination1.getMaxElements();
			//set 总积分
			for(int i=0;i<pagination1.getList().size();i++){
				int id=pagination1.getList().get(i).getNav_oneName();
				nav = this.navService.nav(id);
				String oneName = "";
				oneName = oneName + nav.getOneName();
				pagination1.getList().get(i).setOneName(oneName);
			}
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "articlePgFour";
	}
	public String articlePgFive(){
		try {
			pagination1 = this.articleService.articlePg(pageno, pageSize, "学术前沿");
			int articleSize = pagination1.getMaxElements();
			//set 总积分
			for(int i=0;i<pagination1.getList().size();i++){
				int id=pagination1.getList().get(i).getNav_oneName();
				nav = this.navService.nav(id);
				String oneName = "";
				oneName = oneName + nav.getOneName();
				pagination1.getList().get(i).setOneName(oneName);
			}
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "articlePgFive";
	}
	/**
	 * 删除方法
	 */
	public String deleteByid(){
		try {
			this.articleService.deleteByid(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return articlePg();
	}
	public String deleteFourByid(){
		try {
			this.articleService.deleteByid(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return articlePgFour();
	}
	/**
	 * 跳转到修改界面的方法
	 */
	public String updateArticleto(){
		try {
			article = this.articleService.article(id);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "updateArticleto";
	}
	/**
	 * 修改方法
	 */
	public String updateArticle(){
		try {
			Date d1 = new Date(); 
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");  
			String dateNowStr1 = date.format(d1);
			String date1 = dateNowStr1;
			Article article = new Article();
			article.setId(id);
			article.setNav_navName(nav_navName);
			article.setNav_oneName(nav_oneName);
			article.setTitle(title);
			article.setContent(content);
			article.setTime(date1);
			article.setDengji(dengji);
			this.articleService.updateArticle(article);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return updateArticleto();
	}
	/**
	 * 获取内容
	 */
	public String getContentto(){
		try {
			article = this.articleService.article(id);
			int id = article.getNav_oneName();
			nav = this.navService.nav(id);
			String oneName = "";
			oneName = oneName + nav.getOneName();
			article.setOneName(oneName);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			return "error";
		}
		return "getContentto";
	}
	
	/**
	 * get set
	 * @return
	 */
	
	public IArticleService getArticleService() {
		return articleService;
	}
	public String getOneName() {
		return oneName;
	}
	public void setOneName(String oneName) {
		this.oneName = oneName;
	}
	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNav_oneName_id() {
		return nav_oneName_id;
	}
	public void setNav_oneName_id(int nav_oneName_id) {
		this.nav_oneName_id = nav_oneName_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public INavService getNavService() {
		return navService;
	}
	public void setNavService(INavService navService) {
		this.navService = navService;
	}
	public List<Nav> getNavList1() {
		return navList1;
	}
	public void setNavList1(List<Nav> navList1) {
		this.navList1 = navList1;
	}

	public int getNav_oneName() {
		return nav_oneName;
	}
	public void setNav_oneName(int nav_oneName) {
		this.nav_oneName = nav_oneName;
	}
	public String getNav_navName() {
		return nav_navName;
	}
	public void setNav_navName(String nav_navName) {
		this.nav_navName = nav_navName;
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
	public Pagination<Article> getPagination1() {
		return pagination1;
	}
	public void setPagination1(Pagination<Article> pagination1) {
		this.pagination1 = pagination1;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public List<Nav> getNavList() {
		return navList;
	}
	public void setNavList(List<Nav> navList) {
		this.navList = navList;
	}
	public List<Article> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}
	public Nav getNav() {
		return nav;
	}
	public void setNav(Nav nav) {
		this.nav = nav;
	}
	public int getDengji() {
		return dengji;
	}
	public void setDengji(int dengji) {
		this.dengji = dengji;
	}
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	
	
}
