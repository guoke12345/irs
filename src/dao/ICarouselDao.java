package dao;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Carousel;

public interface ICarouselDao {
	public void addCarousel(Carousel carouesl);
	public void deleteByid(Integer id);
	public Carousel carousel(Integer id);
	public void updateCarousel(Carousel carousel);
	public Pagination<Carousel> carouselPg(int pageno,int pageSize);
	public List<Carousel> carouselList();

}
