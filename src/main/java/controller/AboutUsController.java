package controller;

import model.ContactUs;
import model.CooperativePartner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AboutUsService;
import util.SystemLog;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/AboutUs")
public class AboutUsController {

    @Autowired
    AboutUsService aboutUsService;

    //获取推荐的顾问列表 参考 ConsultantController.java

    //获取所有合作过的学校和企业列表
    @GetMapping(value = "/getCooperativePartner")
    public List<CooperativePartner> getCooperativePartner(){
        return aboutUsService.getCooperativePartner();
    }

    //提交一条联系我们记录
    @PostMapping(value = "/addContactUs")
    public ContactUs addContactUs(String name,String email,String phone,String content){
        ContactUs contactUs=new ContactUs();
        contactUs.setName(name);
        contactUs.setEmail(email);
        contactUs.setPhone(phone);
        contactUs.setContent(content);
        contactUs.setContactAt(new Timestamp(System.currentTimeMillis()));
        return aboutUsService.addContactUs(contactUs);
    }


    //***********************后台修改接口******************************


    //增加一条合作过的学校和企业信息
    @PostMapping(value = "/addCooperativePartner")
    @SystemLog(module = "关于我们", methods = "增加合作伙伴")
    public CooperativePartner addCooperativePartner(String category,String name,String country,
            String cooperateAt,String imagePath,String birefIntroduce){
        CooperativePartner cooperativePartner=new CooperativePartner();
        cooperativePartner.setCategory(category);
        cooperativePartner.setName(name);
        cooperativePartner.setCountry(country);
        cooperativePartner.setCooperateAt(cooperateAt);
        cooperativePartner.setImagePath(imagePath);
        cooperativePartner.setBirefIntroduce(birefIntroduce);
        cooperativePartner.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return aboutUsService.addCooperativePartner(cooperativePartner);
    }

    //根据id编辑一条合作过的学校和企业信息
    @PutMapping(value = "/updateCooperativePartner")
    @SystemLog(module = "关于我们", methods = "更新合作伙伴")
    public CooperativePartner updateCooperativePartner(Integer id,String category,String name,String country,
             String cooperateAt,String imagePath,String birefIntroduce){
        CooperativePartner cooperativePartner=new CooperativePartner();
        cooperativePartner.setId(id);
        cooperativePartner.setCategory(category);
        cooperativePartner.setName(name);
        cooperativePartner.setCountry(country);
        cooperativePartner.setCooperateAt(cooperateAt);
        cooperativePartner.setImagePath(imagePath);
        cooperativePartner.setBirefIntroduce(birefIntroduce);
        cooperativePartner.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return aboutUsService.updateCooperativePartner(cooperativePartner);
    }

    //根据id删除一条合作过的学校和企业信息
    @DeleteMapping(value = "/deleteCooperativePartner")
    @SystemLog(module = "关于我们", methods = "删除合作伙伴")
    public String deleteCooperativePartner(Integer id){
        return aboutUsService.deleteCooperativePartner(id);
    }

    //获取所有未答复的联系我们信息列表
    @GetMapping(value = "/getUnreplyedContactUs")
    @SystemLog(module = "关于我们", methods = "获取未答复消息")
    public List<ContactUs> getUnreplyedContactUs(){
        return aboutUsService.getUnreplyedContactUs();
    }

    //获取所有已答复的联系我们信息列表
    @GetMapping(value = "/getReplyedContactUs")
    @SystemLog(module = "关于我们", methods = "获取已答复消息")
    public List<ContactUs> getReplyedContactUs(){
        return aboutUsService.getReplyedContactUs();
    }

    //答复某条联系我们信息
    @PutMapping(value = "/replyContactUs")
    @SystemLog(module = "关于我们", methods = "答复消息")
    public ContactUs replyContactUs(Integer id, String replyMessage){
        return aboutUsService.replyContactUs(id,replyMessage);
    }

    //删除某条联系我们信息
    @DeleteMapping(value = "/deleteContactUs")
    @SystemLog(module = "关于我们", methods = "删除消息")
    public String deleteContactUs(Integer id){
        return aboutUsService.deleteCooperativePartner(id);
    }
}
