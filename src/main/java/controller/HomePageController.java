package controller;

import model.CarouselFigure;
import model.Honor;
import model.ServedCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.HomepageService;
import util.SystemLog;
import util.UrlFormatUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/homepage")
public class HomePageController {

    @Autowired
    HomepageService homepageService;

    //根据分类获取对应模块下所有轮播图列表
    @GetMapping(value = "/getCarouselFigure")
    public List<CarouselFigure> getCarouselFigure(String category){
        return homepageService.getCarouselFigure(category);
    }


    //获取不同国家下最近5个成功案例列表 参考 SuccessfulCaseController.java

    //获取推荐顾问列表 参考 ConsultantController.java

    //获取所有服务过的学校和企业列表
    @GetMapping(value = "/getServedCompany")
    public List<ServedCompany> getServedCompany(){
        return homepageService.getServedCompany();
    }

    //获取所有荣誉资质列表
    @GetMapping(value = "/getHonor")
    public List<Honor> getHonor(){
        return homepageService.getHonor();
    }

    //************************后台管理接口************************

    //在某个分类下添加一张轮播图
    @PostMapping(value = "/addCarouselFigure")
    @SystemLog(module = "首页管理", methods = "增加轮播图")
    public CarouselFigure addCarouselFigure(String category,String imagePath,String titel,String subTitle,String link){
        CarouselFigure carouselFigure=new CarouselFigure();
        carouselFigure.setCategory(category);
        carouselFigure.setImagePath(imagePath);
        carouselFigure.setTitle(titel);
        carouselFigure.setSubTitle(subTitle);
        carouselFigure.setLink(UrlFormatUtils.formatUrl(link));
        return homepageService.addCarouselFigure(carouselFigure);
    }

    //根据id编辑一张轮播图
    @PutMapping(value = "/updateCarouselFigure")
    @SystemLog(module = "首页管理", methods = "编辑轮播图")
    public CarouselFigure updateCarouselFigure(Integer id,String category,String imagePath,String titel,String subTitle,String link){
        CarouselFigure carouselFigure=new CarouselFigure();
        carouselFigure.setId(id);
        carouselFigure.setCategory(category);
        carouselFigure.setImagePath(imagePath);
        carouselFigure.setTitle(titel);
        carouselFigure.setSubTitle(subTitle);
        carouselFigure.setLink(UrlFormatUtils.formatUrl(link));
        return homepageService.updateCarouselFigure(carouselFigure);
    }

    //根据id删除一张轮播图
    @DeleteMapping(value = "/deleteCarouselFigure")
    @SystemLog(module = "首页管理", methods = "删除轮播图")
    public String deleteCarouselFigure(Integer id){
        return homepageService.deleteCarouselFigure(id);
    }

    //增加一条服务过的学校和企业信息
    @PostMapping(value = "/addServedCompany")
    @SystemLog(module = "首页管理", methods = "增加合作对象")
    public ServedCompany addServedCompany(String country,String category,String name,String briefIntroduce,String imagePath){
        ServedCompany servedCompany=new ServedCompany();
        servedCompany.setCountry(country);
        servedCompany.setCategory(category);
        servedCompany.setName(name);
        servedCompany.setBriefIntroduce(briefIntroduce);
        servedCompany.setImagePath(imagePath);
        servedCompany.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return homepageService.addServedCompany(servedCompany);
    }

    //根据id编辑一条服务过的学校和企业信息
    @PutMapping(value = "/updateServedCompany")
    @SystemLog(module = "首页管理", methods = "编辑合作对象")
    public ServedCompany updateServedCompany(Integer id,String country,String category,String name,String briefIntroduce,String imagePath){
        ServedCompany servedCompany=new ServedCompany();
        servedCompany.setId(id);
        servedCompany.setCountry(country);
        servedCompany.setCategory(category);
        servedCompany.setName(name);
        servedCompany.setBriefIntroduce(briefIntroduce);
        servedCompany.setImagePath(imagePath);
        servedCompany.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return homepageService.updateServedCompany(servedCompany);
    }

    //根据id删除一条服务过的学校和企业信息
    @DeleteMapping(value = "/deleteServedCompany")
    @SystemLog(module = "首页管理", methods = "删除服务对象")
    public String deleteServedCompany(Integer id){
        return homepageService.deleteServedCompany(id);
    }

    //增加一条荣誉资质信息
    @PostMapping(value = "/addHonor")
    @SystemLog(module = "首页管理", methods = "增加荣誉资质")
    public Honor addHonor(String name,String imagePath,String getAt){
        Honor honor=new Honor();
        honor.setName(name);
        honor.setImagePath(imagePath);
        honor.setGetAt(getAt);
        honor.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return homepageService.addHonor(honor);
    }

    //根据id编辑一条荣誉资质信息
    @PutMapping(value = "/updateHonor")
    @SystemLog(module = "首页管理", methods = "编辑荣誉资质")
    public Honor updateHonor(Integer id,String name,String imagePath,String getAt){
        Honor honor=new Honor();
        honor.setId(id);
        honor.setName(name);
        honor.setImagePath(imagePath);
        honor.setGetAt(getAt);
        honor.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return homepageService.updateHonor(honor);
    }

    //根据id删除一条荣誉资质信息
    @DeleteMapping(value = "/deleteHonor")
    @SystemLog(module = "首页管理", methods = "删除荣誉资质")
    public String deleteHonor(Integer id){
        return homepageService.deleteHonor(id);
    }

}
