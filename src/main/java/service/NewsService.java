package service;

import model.News;
import model.TrainIntroduce;

import java.util.List;

public interface NewsService {

    //根据分类获取最近四条资讯信息
    public List<News> getLatestNewsByCategory(String category);

    //根据分类获得所有资讯信息列表
    public List<News> getAllNewsByCategory(String category, Integer flag);

    //在分类下增加一条资讯信息
    public News addNews(News news);

    //在分类下编辑一条资讯信息
    public News updateNews(News news);

    //在分类下删除一条资讯信息
    public String deleteNews(Integer id);

}
