package dao;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Article;

public interface IArticleDao {
	public void addArticle(Article article);
	public Pagination<Article> artilePg(int pageno,int pageSize,String nav_navName);
	public Pagination<Article> zhidingPg(int pageno,int pageSize,String nav_navName);
	public Pagination<Article> artTitlePg(int pageno,int pageSize,int nav_oneName);
	public Pagination<Article> zhidingTitlePg(int pageno,int pageSize,int nav_oneName);
	public void deleteByid(Integer id);
	public Article article(int id);
	public void updateArticle(Article article);
	public List<Article> getArticleByid(int nav_oneName);
	public List<Article> getArticles();
}
