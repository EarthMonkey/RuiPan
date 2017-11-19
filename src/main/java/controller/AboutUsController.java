package controller;

import model.CooperativePartner;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.SystemLog;

import java.util.List;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/AboutUs")
public class AboutUsController {



    //获取推荐的顾问列表 参考 ConsultantController.java

    //获取所有合作过的学校和企业列表
    @GetMapping(value = "/getCooperativePartner")
    public List<CooperativePartner> getCooperativePartner(){
        return null;
    }

    //提交一条联系我们记录


    //***********************后台修改接口******************************


    //增加一条合作过的学校和企业信息
    @GetMapping(value = "/addCooperativePartner")
    @SystemLog(module = "关于我们", methods = "增加合作伙伴")
    public CooperativePartner addCooperativePartner(String category,String name,String country,
            String cooperateAt,String imagePath,String birefIntroduce){
        return null;
    }

    //根据id编辑一条合作过的学校和企业信息
    @PutMapping(value = "/updateCooperativePartner")
    @SystemLog(module = "关于我们", methods = "更新合作伙伴")
    public CooperativePartner updateCooperativePartner(Integer id,String category,String name,String country,
             String cooperateAt,String imagePath,String birefIntroduce){
        return null;
    }

    //根据id删除一条合作过的学校和企业信息
    @DeleteMapping(value = "/deleteCooperativePartner")
    @SystemLog(module = "关于我们", methods = "删除合作伙伴")
    public String deleteCooperativePartner(Integer id){
        return null;
    }

    //获取所有未答复的联系我们信息列表

    //获取所有已答复的联系我们信息列表

    //答复某条联系我们信息

    //删除某条联系我们信息
}
