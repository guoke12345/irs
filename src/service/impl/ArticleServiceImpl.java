package service.impl;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Article;
import dao.IArticleDao;
import service.IArticleService;
//import service.IUserService;

public class ArticleServiceImpl implements IArticleService{
	private IArticleDao articleDao;

	public IArticleDao getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(IArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public void addArticle(Article article) {
		this.articleDao.addArticle(article);
	}

	public Pagination<Article> articlePg(int pageno, int pageSize,
			String nav_navName) {
		return this.articleDao.artilePg(pageno, pageSize, nav_navName);
	}

	public void deleteByid(Integer id) {
		this.articleDao.deleteByid(id);
	}

	public Article article(int id) {
		return this.articleDao.article(id);
	}

	public void updateArticle(Article article) {
		this.articleDao.updateArticle(article);
	}

	public Pagination<Article> artTitlePg(int pageno, int pageSize,
			int nav_oneName) {
		return this.articleDao.artTitlePg(pageno, pageSize, nav_oneName);
	}

	public List<Article> getArticleByid(int nav_oneName) {
		return this.articleDao.getArticleByid(nav_oneName);
	}

	public Pagination<Article> zhidingPg(int pageno, int pageSize,
			String nav_navName) {
		return this.articleDao.zhidingPg(pageno, pageSize, nav_navName);
	}

	public Pagination<Article> zhidingTitlePg(int pageno, int pageSize,
			int nav_oneName) {
		return this.articleDao.zhidingTitlePg(pageno, pageSize, nav_oneName);
	}

	public List<Article> getArticles() {
		return this.articleDao.getArticles();
	}
	
	

}
