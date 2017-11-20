package service;

import model.CarouselFigure;
import model.Honor;
import model.ServedCompany;

import java.util.List;

/**
 * Created by ldchao on 2017/11/20.
 */
public interface HomepageService {

    public List<CarouselFigure> getCarouselFigure(String category);

    public CarouselFigure addCarouselFigure(CarouselFigure carouselFigure);

    public CarouselFigure updateCarouselFigure(CarouselFigure carouselFigure);

    public String deleteCarouselFigure(Integer id);

    public List<ServedCompany> getServedCompany();

    public ServedCompany addServedCompany(ServedCompany servedCompany);

    public ServedCompany updateServedCompany(ServedCompany servedCompany);

    public String deleteServedCompany(Integer id);

    public List<Honor> getHonor();

    public Honor addHonor(Honor honor);

    public Honor updateHonor(Honor honor);

    public String deleteHonor(Integer id);

}
