package controller;

import model.HardCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.StudyAboardService;
import util.SystemLog;
import vo.HardConditionVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/2.
 */
@Controller
@RequestMapping(value = "/StudyAbroad")
public class StudyAbroadController {

    @Autowired
    StudyAboardService studyAboardService;

    //获取所有国家
    @RequestMapping(value = "/getAllCountry")
    @ResponseBody
    public List<String> getAllCountry() {
        return studyAboardService.getAllCountry();
    }

    //根据国家、年级类别编号（gid 下同）获取所有硬性条件要求
    @RequestMapping(value = "/getHardCondionByGid")
    @ResponseBody
    public Map<String, List<HardConditionVO>> getHardCondionByGid(Integer gid) {
        return null;

    }


    //根据gid获取所有申请方案列表


    //获取三个全局推荐的留学方案


    //根据申请方案id获取申请方案详情页信息


    //根据gid获取所有研究生申请要素


    //根据申请要素id获取详情页信息


    //根据gid获取前四个外籍顾问信息


    //根据gid获取所有问题列表


    //***********************后台管理接口******************************

    //增加一个国家(对应增加研究生、本科生、高中生三个分类)
    @RequestMapping(value = "/addCountry")
    @ResponseBody
    @SystemLog(module = "留学服务", methods = "增加一个国家")
    public String addCountry(String country) {
        return studyAboardService.addOneCountry(country);
    }

    //删除一个国家（对应的研究生、本科生、高中生分类都删除掉）
    @RequestMapping(value = "/deleteCountry")
    @ResponseBody
    @SystemLog(module = "留学服务", methods = "删除一个国家")
    public String deleteCountry(String country) {
        return studyAboardService.deleteOneCountry(country);
    }

    //在某个国家和年级（gid）下增加一条硬性条件要求
    @RequestMapping(value = "/addHardCondition")
    @ResponseBody
    @SystemLog(module = "留学服务", methods = "增加硬性条件")
    public HardConditionVO addHardCondition(Integer gid, String rank, String subject, String score) {
        return null;
    }

    //根据id更改某条硬性条件要求
    @RequestMapping(value = "/changeHardCondition")
    @ResponseBody
    @SystemLog(module = "留学服务", methods = "修改硬性条件")
    public HardConditionVO changeHardCondition(Integer id, Integer gid, String rank, String subject, String score) {
        return null;
    }

    //根据id删除某项硬性条件
    @RequestMapping(value = "/deleteHardCondition")
    @ResponseBody
    @SystemLog(module = "留学服务", methods = "删除硬性条件")
    public HardConditionVO deleteHardCondition(Integer id) {
        return null;
    }

    //在gid下增加一条方案

    //根据id更改某条方案

    //根据id删除某条方案

    //根据gid获取所有草稿状态的方案

    //根据方案id将某条方案添加到重点推荐中（最多三个，若超了，添加时间最早的那条自动转为历史推荐）

    //查看所有历史推荐状态的全局推荐方案

    // 根据id修改某条重点推荐

    //根据id将某条方案从重点推荐中移除

    //在gid下增加一条申请要素

    //根据id更改某条申请要素

    //根据id删除某条申请要素

    //根据gid获取所有草稿状态的申请要素

    //根据gid增加一条问答

    //根据id编辑一条问答

    //根据id删除一条问答

}
