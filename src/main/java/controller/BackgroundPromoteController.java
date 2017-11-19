package controller;

import constant.StatesConstant;
import model.BackgroundPromote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.BackgroudPromoteService;
import util.SystemLog;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/SuccessfulCase")
public class BackgroundPromoteController {

    @Autowired
    BackgroudPromoteService backgroudPromoteService;

    //根据分类获取所有背景提升列表
    @GetMapping(value="/getBackgroundPromoteByCategory")
    public List<BackgroundPromote> getBackgroundPromoteByCategory(String category){
        return backgroudPromoteService.getBackgroundPromoteByCategory(category, StatesConstant.PUBLISHED);
    }


    //根据背景提升id获取详情信息
    @GetMapping(value="/getBackgroundPromote")
    public BackgroundPromote getBackgroundPromote(Integer id){
        return backgroudPromoteService.getBackgroundPromote(id);
    }

    //***********************后台管理接口******************************

    //增加一条背景提升项目
    @PostMapping(value="/addBackgroundPromote")
    @SystemLog(module = "背景提升" ,methods = "增加项目")
    public BackgroundPromote addBackgroundPromote(String category,String title,String thumbnail,String synopsis,String textPath,Integer flag){
        BackgroundPromote backgroundPromote=new BackgroundPromote();
        backgroundPromote.setCategory(category);
        backgroundPromote.setTitle(title);
        backgroundPromote.setThumbnail(thumbnail);
        backgroundPromote.setSynopsis(synopsis);
        backgroundPromote.setTextPath(textPath);
        backgroundPromote.setFlag(flag);
        backgroundPromote.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return backgroudPromoteService.addBackgroundPromote(backgroundPromote);
    }

    //根据id编辑一条背景提升项目
    @PutMapping(value="/updateBackgroundPromote")
    @SystemLog(module = "背景提升" ,methods = "更新项目")
    public BackgroundPromote updateBackgroundPromote(Integer id,String category,String title,String thumbnail,String synopsis,String textPath,Integer flag){
        BackgroundPromote backgroundPromote=new BackgroundPromote();
        backgroundPromote.setId(id);
        backgroundPromote.setCategory(category);
        backgroundPromote.setTitle(title);
        backgroundPromote.setThumbnail(thumbnail);
        backgroundPromote.setSynopsis(synopsis);
        backgroundPromote.setTextPath(textPath);
        backgroundPromote.setFlag(flag);
        backgroundPromote.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return backgroudPromoteService.updateBackgroundPromote(backgroundPromote);
    }


    //根据id删除一条背景提升项目
    @DeleteMapping(value="/deleteBackgroundPromote")
    @SystemLog(module = "背景提升" ,methods = "删除项目")
    public String deleteBackgroundPromote(Integer id){
        return backgroudPromoteService.deleteBackgroundPromote(id);
    }


    //获取所有草稿状态的背景提升项目
    @GetMapping(value="/getBackgroundPromoteDraftByCategory")
    @SystemLog(module = "背景提升" ,methods = "查看项目草稿")
    public List<BackgroundPromote> getBackgroundPromoteDraftByCategory(String category){
        return backgroudPromoteService.getBackgroundPromoteByCategory(category, StatesConstant.DRAFT);
    }

}
