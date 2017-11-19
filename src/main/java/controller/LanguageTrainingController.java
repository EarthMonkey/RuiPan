package controller;

import constant.StatesConstant;
import model.News;
import model.TrainIntroduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.LanguageTrainingService;
import util.SystemLog;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/LanguageTraining")
public class LanguageTrainingController {

    @Autowired
    LanguageTrainingService languageTrainingService;

    //根据分类获取最近四条资讯信息
    @GetMapping(value = "/getLatestNewsByCategory")
    public List<News> getLatestNewsByCategory(String category){
        return languageTrainingService.getLatestNewsByCategory(category);
    }

    //根据分类获得所有已发布资讯信息列表
    @GetMapping(value = "/getAllNewsByCategory")
    public List<News> getAllNewsByCategory(String category){
        return languageTrainingService.getAllNewsByCategory(category, StatesConstant.PUBLISHED);
    }

    //根据分类获得所有发布介绍信息列表
    @GetMapping(value = "/getTrainIntroducePublish")
    public List<TrainIntroduce> getTrainIntroducePublish(String category){
        return languageTrainingService.getTrainIntroduce(category,StatesConstant.PUBLISHED);
    }


    //***********************后台管理接口******************************

    //在分类下增加一条资讯信息
    @PostMapping(value = "/addNews")
    @SystemLog(module = "语言培训", methods = "增加资讯")
    public News addNews(String category,String title,String synopsis,
            String textPath,Integer flag){
        News news=new News();
        news.setCategory(category);
        news.setTitle(title);
        news.setSynopsis(synopsis);
        news.setTextPath(textPath);
        news.setFlag(flag);
        news.setPulishTime(new Timestamp(System.currentTimeMillis()));
        return languageTrainingService.addNews(news);
    }

    //在分类下编辑一条资讯信息
    @PutMapping(value = "/updateNews")
    @SystemLog(module = "语言培训", methods = "编辑资讯")
    public News updateNews(Integer id,String category,String title,String synopsis,
                           String textPath,Integer flag){
        News news=new News();
        news.setId(id);
        news.setCategory(category);
        news.setTitle(title);
        news.setSynopsis(synopsis);
        news.setTextPath(textPath);
        news.setFlag(flag);
        news.setPulishTime(new Timestamp(System.currentTimeMillis()));
        return languageTrainingService.updateNews(news);
    }

    //在分类下删除一条资讯信息
    @DeleteMapping(value = "/deleteNews")
    @SystemLog(module = "语言培训", methods = "删除资讯")
    public String deleteNews(Integer id){
        return languageTrainingService.deleteNews(id);
    }

    //在分类下获取所有草稿状态的咨询信息
    @GetMapping(value = "/getAllNewsByCategoryDraft")
    @SystemLog(module = "语言培训", methods = "获取资讯草稿")
    public List<News> getAllNewsDraftByCategory(String category){
        return languageTrainingService.getAllNewsByCategory(category, StatesConstant.DRAFT);
    }

    //在分类下增加一条介绍信息
    @PostMapping(value = "/addTrainIntroduce")
    @SystemLog(module = "语言培训", methods = "增加培训介绍")
    public TrainIntroduce addTrainIntroduce(String category,String title,String textPath,Integer flag){
        TrainIntroduce trainIntroduce=new TrainIntroduce();
        trainIntroduce.setCategory(category);
        trainIntroduce.setTitle(title);
        trainIntroduce.setTextPath(textPath);
        trainIntroduce.setFlag(flag);
        trainIntroduce.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return languageTrainingService.addTrainIntroduce(trainIntroduce);
    }

    //根据id编辑一条介绍信息
    @PutMapping(value = "/updateTrainIntroduce")
    @SystemLog(module = "语言培训", methods = "更新培训介绍")
    public TrainIntroduce updateTrainIntroduce(Integer id,String category,String title,String textPath,Integer flag){
        TrainIntroduce trainIntroduce=new TrainIntroduce();
        trainIntroduce.setId(id);
        trainIntroduce.setCategory(category);
        trainIntroduce.setTitle(title);
        trainIntroduce.setTextPath(textPath);
        trainIntroduce.setFlag(flag);
        trainIntroduce.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return languageTrainingService.updateTrainIntroduce(trainIntroduce);
    }

    //根据id删除一条介绍信息
    @DeleteMapping(value = "/deleteTrainIntroduce")
    @SystemLog(module = "语言培训", methods = "删除培训介绍")
    public String deleteTrainIntroduce(Integer id){
        return languageTrainingService.deleteTrainIntroduce(id);
    }

    //根据分类获取所有草稿状态的介绍信息
    @GetMapping(value = "/getTrainIntroduceDraft")
    @SystemLog(module = "语言培训", methods = "获取培训介绍草稿")
    public List<TrainIntroduce> getTrainIntroduceDraft(String category){
        return languageTrainingService.getTrainIntroduce(category,StatesConstant.DRAFT);
    }
}
