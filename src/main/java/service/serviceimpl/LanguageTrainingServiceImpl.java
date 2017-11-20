package service.serviceimpl;

import constant.StatesConstant;
import dao.NewsDao;
import dao.TrainIntroduceDao;
import model.News;
import model.TrainIntroduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LanguageTrainingService;

import java.util.List;

@Service
public class LanguageTrainingServiceImpl implements LanguageTrainingService{

    @Autowired
    TrainIntroduceDao trainIntroduceDao;

    @Override
    public List<TrainIntroduce> getTrainIntroduce(String category, Integer flag) {
        return trainIntroduceDao.findAllByCategoryAndFlagOrderByUpdateAtDesc(category,flag);
    }

    @Override
    public TrainIntroduce getTrainIntroduceById(Integer id) {
        return trainIntroduceDao.findOne(id);
    }

    @Override
    public TrainIntroduce addTrainIntroduce(TrainIntroduce trainIntroduce) {
        return trainIntroduceDao.saveAndFlush(trainIntroduce);
    }

    @Override
    public TrainIntroduce updateTrainIntroduce(TrainIntroduce trainIntroduce) {
        return trainIntroduceDao.saveAndFlush(trainIntroduce);
    }

    @Override
    public String deleteTrainIntroduce(Integer id) {
        if(trainIntroduceDao.exists(id)){
            trainIntroduceDao.delete(id);
            return "success";
        }
        return "not_exist";
    }
}
