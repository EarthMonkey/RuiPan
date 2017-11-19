package service.serviceimpl;

import model.News;
import model.TrainIntroduce;
import org.springframework.stereotype.Service;
import service.LanguageTrainingService;

import java.util.List;

@Service
public class LanguageTrainingServiceImpl implements LanguageTrainingService{
    @Override
    public List<News> getLatestNewsByCategory(String category, Integer limit) {
        return null;
    }

    @Override
    public List<News> getAllNewsByCategory(String category, Integer flag) {
        return null;
    }

    @Override
    public News addNews(News news) {
        return null;
    }

    @Override
    public News updateNews(News news) {
        return null;
    }

    @Override
    public News deleteNews(Integer id) {
        return null;
    }

    @Override
    public List<TrainIntroduce> getTrainIntroducePublish(String category, Integer flag) {
        return null;
    }

    @Override
    public TrainIntroduce addTrainIntroduce(TrainIntroduce trainIntroduce) {
        return null;
    }

    @Override
    public TrainIntroduce updateTrainIntroduce(TrainIntroduce trainIntroduce) {
        return null;
    }

    @Override
    public String deleteTrainIntroduce(Integer id) {
        return null;
    }
}
