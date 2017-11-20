package service.serviceimpl;

import dao.CarouselFigureDao;
import dao.HonorDao;
import dao.ServedCompanyDao;
import model.CarouselFigure;
import model.Honor;
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

    @Autowired
    ServedCompanyDao servedCompanyDao;

    @Autowired
    HonorDao honorDao;

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
        return servedCompanyDao.findAll();
    }

    @Override
    public ServedCompany addServedCompany(ServedCompany servedCompany) {
        return servedCompanyDao.saveAndFlush(servedCompany);
    }

    @Override
    public ServedCompany updateServedCompany(ServedCompany servedCompany) {
        return servedCompanyDao.saveAndFlush(servedCompany);
    }

    @Override
    public String deleteServedCompany(Integer id) {
        if(servedCompanyDao.exists(id)){
            servedCompanyDao.delete(id);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public List<Honor> getHonor() {
        return honorDao.findAll();
    }

    @Override
    public Honor addHonor(Honor honor) {
        return honorDao.saveAndFlush(honor);
    }

    @Override
    public Honor updateHonor(Honor honor) {
        return honorDao.saveAndFlush(honor);
    }

    @Override
    public String deleteHonor(Integer id) {
        if(honorDao.exists(id)){
            honorDao.delete(id);
            return "success";
        }
        return "not_exist";
    }
}
