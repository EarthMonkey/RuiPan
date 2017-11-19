package controller;

import model.BackgroundPromote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.SystemLog;

import java.util.List;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/SuccessfulCase")
public class BackgroundPromoteController {

    //根据分类获取所有背景提升列表
    @GetMapping(value="/getBackgroundPromoteByCategory")
    public List<BackgroundPromote> getBackgroundPromoteByCategory(String category){
        return null;
    }


    //根据背景提升id获取详情信息
    @GetMapping(value="/getBackgroundPromote")
    public List<BackgroundPromote> getBackgroundPromote(Integer id){
        return null;
    }

    //***********************后台管理接口******************************

    //增加一条背景提升项目
//    @SystemLog(module = "成功案例" ,methods = "根据专业查看案例草稿")


    //根据id编辑一条背景提升项目

    //根据id删除一条背景提升项目

    //获取所有草稿状态的背景提升项目
}
