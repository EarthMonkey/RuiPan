package controller;

import constant.StatesConstant;
import model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.NewsService;
import util.SystemLog;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/News")
public class NewsController {

    @Autowired
    NewsService newsService;


    //根据分类获取最近四条资讯信息
    @GetMapping(value = "/getLatestNewsByCategory")
    public List<News> getLatestNewsByCategory(String category){
        return newsService.getLatestNewsByCategory(category);
    }

    //根据分类获得所有已发布资讯信息列表
    @GetMapping(value = "/getAllNewsByCategory")
    public List<News> getAllNewsByCategory(String category){
        return newsService.getAllNewsByCategory(category, StatesConstant.PUBLISHED);
    }

    //根据id获得一条资讯
    @GetMapping(value = "/getNewsById")
    public News getNewsById(Integer id){
        return newsService.getNewsById(id);
    }

    //***********************后台管理接口******************************

    //在分类下增加一条资讯信息
    @PostMapping(value = "/addNews")
    @SystemLog(module = "资讯管理", methods = "增加资讯")
    public News addNews(String category,String title,String synopsis,
                        String textPath,Integer flag){
        News news=new News();
        news.setCategory(category);
        news.setTitle(title);
        news.setSynopsis(synopsis);
        news.setTextPath(textPath);
        news.setFlag(flag);
        news.setPulishTime(new Timestamp(System.currentTimeMillis()));
        return newsService.addNews(news);
    }

    //在分类下编辑一条资讯信息
    @PutMapping(value = "/updateNews")
    @SystemLog(module = "资讯管理", methods = "编辑资讯")
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
        return newsService.updateNews(news);
    }

    //在分类下删除一条资讯信息
    @DeleteMapping(value = "/deleteNews")
    @SystemLog(module = "资讯管理", methods = "删除资讯")
    public String deleteNews(Integer id){
        return newsService.deleteNews(id);
    }

    //在分类下获取所有草稿状态的资讯信息
    @GetMapping(value = "/getAllNewsByCategoryDraft")
    @SystemLog(module = "资讯管理", methods = "获取资讯草稿")
    public List<News> getAllNewsDraftByCategory(String category){
        return newsService.getAllNewsByCategory(category, StatesConstant.DRAFT);
    }
}
