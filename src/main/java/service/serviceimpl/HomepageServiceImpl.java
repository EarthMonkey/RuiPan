package service.serviceimpl;

import dao.CarouselFigureDao;
import model.CarouselFigure;
import model.ServedCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HomepageService;

import java.util.List;

/**
 * Created by ldchao on 2017/11/20.
 */
@Service
public class HomepageServiceImpl implements HomepageService{

    @Autowired
    CarouselFigureDao carouselFigureDao;

    @Override
    public List<CarouselFigure> getCarouselFigure(String category) {
        return carouselFigureDao.findAllByCategory(category);
    }

    @Override
    public CarouselFigure addCarouselFigure(CarouselFigure carouselFigure) {
        return carouselFigureDao.saveAndFlush(carouselFigure);
    }

    @Override
    public CarouselFigure updateCarouselFigure(CarouselFigure carouselFigure) {
        return carouselFigureDao.saveAndFlush(carouselFigure);
    }

    @Override
    public String deleteCarouselFigure(Integer id) {
        if(carouselFigureDao.exists(id)){
            carouselFigureDao.delete(id);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public List<ServedCompany> getServedCompany() {
        return null;
    }

    @Override
    public ServedCompany addServedCompany(ServedCompany servedCompany) {
        return null;
    }

    @Override
    public ServedCompany updateServedCompany(ServedCompany servedCompany) {
        return null;
    }

    @Override
    public String deleteServedCompany(Integer id) {
        return null;
    }
}
