package service.serviceimpl;

import constant.StatesConstant;
import dao.NewsDao;
import dao.TrainIntroduceDao;
import model.News;
import model.TrainIntroduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LanguageTrainingService;
import service.NewsService;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    NewsDao newsDao;


    @Override
    public List<News> getLatestNewsByCategory(String category) {

        return newsDao.findTop4ByCategoryAndFlagOrderByPulishTimeDesc(category, StatesConstant.PUBLISHED);
    }

    @Override
    public List<News> getAllNewsByCategory(String category, Integer flag) {
        return newsDao.findAllByCategoryAndFlagOrderByPulishTimeDesc(category,StatesConstant.PUBLISHED);
    }

    @Override
    public News getNewsById(Integer id) {
        return newsDao.findOne(id);
    }

    @Override
    public News addNews(News news) {
        return newsDao.saveAndFlush(news);
    }

    @Override
    public News updateNews(News news) {
        return newsDao.saveAndFlush(news);
    }

    @Override
    public String deleteNews(Integer id) {
        if(newsDao.exists(id)){
            newsDao.delete(id);
            return "success";
        }
        return "not_exist";
    }

}
