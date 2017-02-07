package dao.impl;

import java.util.List;

import pojo.Carousel;

import com.framework.core.daos.hibernate.GenericHibernateDao;
import com.framework.core.page.Pagination;

import dao.ICarouselDao;





public class CarouselDaoImpl  extends GenericHibernateDao<Carousel,Integer>
implements ICarouselDao{
	private CarouselDaoImpl(){
		super(Carousel.class);//实现User类的重载
	}
	/**
	 * 添加方法
	 */
	public void addCarousel(Carousel carouesl) {
		this.save(carouesl);
	}
	/**
	 * 删除放法
	 */
	public void deleteByid(Integer id) {
		this.remove(id);
	}
	/**
	 * 通过id查询一条数据
	 */
	public Carousel carousel(Integer id) {
		return this.findById(id);
	}
	/**
	 * 修改方法
	 */
	public void updateCarousel(Carousel carousel) {
		this.update(carousel);
	}
	/**
	 * 分页遍历所有信息
	 */
	public Pagination<Carousel> carouselPg(int pageno, int pageSize) {
		String hql = "from Carousel as carousel order by id desc";
		return this.findByPage(pageno, pageSize, hql);
	}
	public List<Carousel> carouselList(){
		return this.findAll();
	}
}
