package service.impl;


import java.util.List;

import com.framework.core.page.Pagination;

import pojo.Carousel;
import service.ICarouselService;
import dao.ICarouselDao;

public class CarouselServiceImpl implements ICarouselService{
	private ICarouselDao carouselDao;

	public ICarouselDao getCarouselDao() {
		return carouselDao;
	}

	public void setCarouselDao(ICarouselDao carouselDao) {
		this.carouselDao = carouselDao;
	}

	public void addCarousel(Carousel carousel) {
		this.carouselDao.addCarousel(carousel);
	}
	public List<Carousel> carouselList(){
		return  this.carouselDao.carouselList();
	}
	public void deleteByid(Integer id) {
		this.carouselDao.deleteByid(id);
	}

	public Carousel carousel(Integer id) {
		return this.carouselDao.carousel(id);
	}

	public void updateCarousel(Carousel carousel) {
		this.carouselDao.updateCarousel(carousel);
	}

	public Pagination<Carousel> carouselPg(int pageno, int pageSize) {
		return this.carouselDao.carouselPg(pageno, pageSize);
	}

	
}
