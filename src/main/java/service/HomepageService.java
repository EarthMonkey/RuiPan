package service;

import model.CarouselFigure;

import java.util.List;

/**
 * Created by ldchao on 2017/11/20.
 */
public interface HomepageService {

    public List<CarouselFigure> getCarouselFigure(String category);

    public CarouselFigure addCarouselFigure(CarouselFigure carouselFigure);

    public CarouselFigure updateCarouselFigure(CarouselFigure carouselFigure);

    public String deleteCarouselFigure(Integer id);
}
