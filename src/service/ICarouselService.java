package service;

import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Carousel;

public interface ICarouselService {
	public void addCarousel(Carousel carousel);
	public void deleteByid(Integer id);
	public Carousel carousel(Integer id);
	public void updateCarousel(Carousel carousel);
	public Pagination<Carousel> carouselPg(int pageno,int pageSize);
	public List<Carousel> carouselList();
}
