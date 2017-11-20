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

    //轮播图相关参看 HomePageController.java
    //资讯相关参考 NewsController.java

    @Autowired
    LanguageTrainingService languageTrainingService;

    //根据分类获得所有发布介绍信息列表
    @GetMapping(value = "/getTrainIntroducePublish")
    public List<TrainIntroduce> getTrainIntroducePublish(String category){
        return languageTrainingService.getTrainIntroduce(category,StatesConstant.PUBLISHED);
    }

    @GetMapping(value = "/getTrainIntroduceById")
    public TrainIntroduce getTrainIntroduceById(Integer id){
        return languageTrainingService.getTrainIntroduceById(id);
    }

    //***********************后台管理接口******************************

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
