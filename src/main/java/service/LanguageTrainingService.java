package service;

import model.News;
import model.TrainIntroduce;

import java.util.List;

public interface LanguageTrainingService {

    //根据分类获得所有介绍信息列表
    public List<TrainIntroduce> getTrainIntroduce(String category,Integer flag);

    //在分类下增加一条介绍信息
    public TrainIntroduce addTrainIntroduce(TrainIntroduce trainIntroduce);

    //根据id编辑一条介绍信息
    public TrainIntroduce updateTrainIntroduce(TrainIntroduce trainIntroduce);

    //根据id删除一条介绍信息
    public String deleteTrainIntroduce(Integer id);

}
