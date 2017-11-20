package controller;

import model.CarouselFigure;
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
    @GetMapping(value = "getCarouselFigure")
    public List<CarouselFigure> getCarouselFigure(String category){
        return homepageService.getCarouselFigure(category);
    }


    //获取不同国家下最近5个成功案例列表 参考 SuccessfulCaseController.java

    //获取推荐顾问列表 参考 ConsultantController.java

    //获取所有服务过的学校和企业列表

    //获取所有荣誉资质列表

    //************************后台管理接口************************

    //在某个分类下添加一张轮播图
    @PostMapping(value = "addCarouselFigure")
    @SystemLog(module = "首页管理", methods = "增加轮播图")
    public CarouselFigure addCarouselFigure(String category,String imagePath,String link){
        CarouselFigure carouselFigure=new CarouselFigure();
        carouselFigure.setCategory(category);
        carouselFigure.setImagePath(imagePath);
        carouselFigure.setLink(UrlFormatUtils.formatUrl(link));
        return homepageService.addCarouselFigure(carouselFigure);
    }

    //根据id编辑一张轮播图
    @PutMapping(value = "updateCarouselFigure")
    @SystemLog(module = "首页管理", methods = "编辑轮播图")
    public CarouselFigure updateCarouselFigure(Integer id,String category,String imagePath,String link){
        CarouselFigure carouselFigure=new CarouselFigure();
        carouselFigure.setId(id);
        carouselFigure.setCategory(category);
        carouselFigure.setImagePath(imagePath);
        carouselFigure.setLink(UrlFormatUtils.formatUrl(link));
        return homepageService.updateCarouselFigure(carouselFigure);
    }

    //根据id删除一张轮播图
    @DeleteMapping(value = "deleteCarouselFigure")
    @SystemLog(module = "首页管理", methods = "删除轮播图")
    public String deleteCarouselFigure(Integer id){
        return homepageService.deleteCarouselFigure(id);
    }

    //增加一条服务过的学校和企业信息

    //根据id编辑一条服务过的学校和企业信息

    //根据id删除一条服务过的学校和企业信息

    //增加一条荣誉资质信息

    //根据id编辑一条荣誉资质信息

    //根据id删除一条荣誉资质信息

}
