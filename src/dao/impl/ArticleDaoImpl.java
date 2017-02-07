package dao.impl;

import java.util.List;

import pojo.Article;

import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.IArticleDao;



public class ArticleDaoImpl extends GenericHibernateDao<Article,Integer>
implements IArticleDao{
	private ArticleDaoImpl(){
		super(Article.class);//实现User类的重载
	}
	/**
	 * 添加文章的方法
	 */
	public void addArticle(Article article) {
		this.save(article);
	}
	/**
	 * 分页列表显示
	 */
	public Pagination<Article> artilePg(int pageno, int pageSize,String nav_navName) {
		String hql="from Article as Article where Article.nav_navName=? order by id desc";
		Object [] params={nav_navName};
		return this.findByPage(pageno, pageSize, hql, params);
	}
	/**
	 * 删除方法
	 */
	public void deleteByid(Integer id) {
		this.remove(id);
	}
	/**
	 * 跳转到 修改界面的方法
	 */
	public Article article(int id) {
		return this.findById(id);
	}
	/**
	 * 修改方法
	 */
	public void updateArticle(Article article) {
		this.update(article);
	}
	/**
	 * 通过nav_oneName查询分页列表
	 */
	public Pagination<Article> artTitlePg(int pageno, int pageSize,
			int nav_oneName) {
		String hql="from Article as Article where Article.nav_oneName=? order by id desc";
		Object [] params={nav_oneName};
		return this.findByPage(pageno, pageSize, hql, params);
	}
	/**
	 * 通过nav——oneName获取列表
	 */
	public List<Article> getArticleByid(int nav_oneName) {
		String hql="from Article as article where article.nav_oneName=? order by id desc";
		Object [] params={nav_oneName};
		return this.findByHql(hql, params);
	}
	public Pagination<Article> zhidingPg(int pageno, int pageSize,
			String nav_navName) {
		String hql="from Article as Article where Article.nav_navName=? and Article.dengji = 1";
		Object [] params={nav_navName};
		return this.findByPage(pageno, pageSize, hql, params);
	}
	public Pagination<Article> zhidingTitlePg(int pageno, int pageSize,
			int nav_oneName) {
		String hql="from Article as Article where Article.nav_oneName=? and Article.dengji=1";
		Object [] params={nav_oneName};
		return this.findByPage(pageno, pageSize, hql, params);
	}
	public List<Article> getArticles() {
		String hql = "from Article as article where article.nav_oneName=10 or article.nav_oneName=11 order by dengji desc,id desc";
		return this.findByHql(hql);
	}
}
