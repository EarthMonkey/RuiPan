package controller;

import constant.StatesConstant;
import model.HardCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ApplicationSchemeService;
import service.ConsultantService;
import service.StudyAboardService;
import util.SystemLog;
import vo.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/StudyAbroad")
public class StudyAbroadController {

    @Autowired
    StudyAboardService studyAboardService;

    @Autowired
    ApplicationSchemeService applicationSchemeService;

    @Autowired
    ConsultantService consultantService;

    //获取所有国家
    @GetMapping(value = "/getAllCountry")
    public List<String> getAllCountry() {
        return studyAboardService.getAllCountry();
    }

    //根据国家和年级获取Gid
    @GetMapping(value = "/getGid")
    public Integer getGidByCountryAndGrade(String country,String grade) {
        return studyAboardService.getGidByCountryAndGrade(country,grade);
    }

    //根据国家、年级类别编号（gid 下同）获取所有硬性条件要求
    @GetMapping(value = "/getHardCondionByGid")
    public Map<String, List<HardConditionVO>> getHardCondionByGid(Integer gid) {
        return studyAboardService.getHardConditionByGid(gid);

    }

    //根据gid获取所有申请方案列表
    @GetMapping(value = "/getApplicationSchemeByGid")
    public Map<String, List<ApplicationSchemeVO>> getApplicationSchemeByGid(Integer gid) {
        return applicationSchemeService.getApplicationSchemeByGid(gid);
    }


    //获取三个全局推荐的留学方案
    @GetMapping(value = "/getRecommendApplicationScheme")
    public List<RecommendApplicationScheme> getRecommendApplicationScheme() {
        return applicationSchemeService.getRecommendApplicationScheme();
    }


    //根据申请方案id获取申请方案详情页信息
    @GetMapping(value = "/getApplicationSchemeById")
    public ApplicationSchemeVO getApplicationSchemeById(Integer id) {
        return applicationSchemeService.getApplicationSchemeById(id);
    }


    //根据gid获取所有发布的申请要素
    @GetMapping(value = "/getApplicationElementByGid")
    public List<ApplicationElementVO> getApplicationElementByGid(Integer gid) {
        return studyAboardService.getApplicationElementByGid(gid,StatesConstant.PUBLISHED);
    }



    //根据申请要素id获取详情页信息
    @GetMapping(value = "/getApplicationElementById")
    public ApplicationElementVO getApplicationElementById(Integer id) {
        return studyAboardService.getApplicationElementById(id);
    }


    //根据gid获取所有顾问列表
    @GetMapping(value = "/getConsultandByGid")
    public List<ConsultantVO> getConsultantsByGid(Integer gid) {
        return consultantService.getConsultantByGid(gid);
    }

    //根据gid获取所有可见的问题列表
    @GetMapping(value = "/getPublishQuestionsByGid")
    public List<QuestionVO> getPublishQuestionsByGid(Integer gid) {
        return studyAboardService.getPublishQuestionsByGid(gid);
    }


    //***********************后台管理接口******************************

    //增加一个国家(对应增加研究生、本科生、高中生三个分类)
    @PostMapping(value = "/addCountry")
    @SystemLog(module = "留学服务", methods = "增加一个国家")
    public String addCountry(String country) {
        return studyAboardService.addOneCountry(country);
    }

    //删除一个国家（对应的研究生、本科生、高中生分类都删除掉）
    @DeleteMapping(value = "/deleteCountry")
    @SystemLog(module = "留学服务", methods = "删除一个国家")
    public String deleteCountry(String country) {
        return studyAboardService.deleteOneCountry(country);
    }

    //在某个国家和年级（gid）下增加一条硬性条件要求
    @PostMapping(value = "/addHardCondition")
    @SystemLog(module = "留学服务", methods = "增加硬性条件")
    public HardConditionVO addHardCondition(Integer gid, String rank, String subject, String score) {
        HardConditionVO hardConditionVO = new HardConditionVO();
        hardConditionVO.setGid(gid);
        hardConditionVO.setRank(rank);
        hardConditionVO.setSubject(subject);
        hardConditionVO.setScore(score);
        studyAboardService.addHardCondition(hardConditionVO);
        return hardConditionVO;
    }

    //根据id更改某条硬性条件要求
    @PutMapping(value = "/changeHardCondition")
    @SystemLog(module = "留学服务", methods = "修改硬性条件")
    public HardConditionVO changeHardCondition(Integer id, Integer gid, String rank, String subject, String score) {
        HardConditionVO hardConditionVO = new HardConditionVO();
        hardConditionVO.setId(id);
        hardConditionVO.setGid(gid);
        hardConditionVO.setRank(rank);
        hardConditionVO.setSubject(subject);
        hardConditionVO.setScore(score);
        studyAboardService.changeHardCondition(hardConditionVO);
        return hardConditionVO;
    }

    //根据id删除某项硬性条件
    @DeleteMapping(value = "/deleteHardCondition")
    @SystemLog(module = "留学服务", methods = "删除硬性条件")
    public String deleteHardCondition(Integer id) {
        return studyAboardService.deleteHardCondition(id);
    }

    //在gid下增加一条方案,flag=0为草稿，flag=1为正式存储
    @PostMapping(value = "/addApplicationScheme")
    @SystemLog(module = "留学服务", methods = "增加申请方案")
    public ApplicationSchemeVO addApplicationScheme(Integer gid, String subdivisionGrade,
                                                    String title, String synopsis, String textPath, Integer flag) {
        ApplicationSchemeVO applicationSchemeVO = new ApplicationSchemeVO();
        applicationSchemeVO.setGid(gid);
        applicationSchemeVO.setSubdivisionGrade(subdivisionGrade);
        applicationSchemeVO.setTitle(title);
        applicationSchemeVO.setSynopsis(synopsis);
        applicationSchemeVO.setTextPath(textPath);
        applicationSchemeVO.setFlag(flag);
        applicationSchemeService.addApplicationScheme(applicationSchemeVO);
        return applicationSchemeVO;

    }

    //根据id更改某条方案,flag=0为草稿，flag=1为正式存储
    @PutMapping(value = "/updateApplicationScheme")
    @SystemLog(module = "留学服务", methods = "更新申请方案")
    public ApplicationSchemeVO updateApplicationScheme(Integer id, Integer gid, String subdivisionGrade,
                                                       String title, String synopsis, String textPath, Integer flag) {
        ApplicationSchemeVO applicationSchemeVO = new ApplicationSchemeVO();
        applicationSchemeVO.setId(id);
        applicationSchemeVO.setGid(gid);
        applicationSchemeVO.setSubdivisionGrade(subdivisionGrade);
        applicationSchemeVO.setTitle(title);
        applicationSchemeVO.setSynopsis(synopsis);
        applicationSchemeVO.setTextPath(textPath);
        applicationSchemeVO.setFlag(flag);
        applicationSchemeService.updateApplicationScheme(applicationSchemeVO);
        return applicationSchemeVO;

    }

    //根据id删除某条方案
    @DeleteMapping(value = "/deleteApplicationScheme")
    @SystemLog(module = "留学服务", methods = "更新申请方案")
    public String deleteApplicationScheme(Integer id) {
        return applicationSchemeService.deleteApplicationScheme(id);
    }


    //根据gid获取所有草稿状态的方案
    @GetMapping(value = "/getApplicationSchemeDraftByGid")
    @SystemLog(module = "留学服务", methods = "查看申请方案草稿")
    public List<ApplicationSchemeVO> getApplicationSchemeDraftByGid(Integer gid) {
        return applicationSchemeService.getApplicationSchemeDraftByGid(gid);
    }


    //根据方案id将某条方案添加到重点推荐中（最多三个，若超了，添加时间最早的那条自动转为历史推荐）
    @PostMapping(value = "/addRecommendApplicationScheme")
    @SystemLog(module = "留学服务", methods = "增加申请方案推荐")
    public RecommendApplicationScheme addRecommendApplicationScheme(String picturePath, Integer rid) {

        RecommendApplicationScheme recommendApplicationScheme = new RecommendApplicationScheme();
        recommendApplicationScheme.setPicturePath(picturePath);
        recommendApplicationScheme.setRid(rid);
        applicationSchemeService.addRecommendApplicationScheme(recommendApplicationScheme);
        return recommendApplicationScheme;

    }

    //查看所有历史推荐状态的全局推荐方案
    @GetMapping(value = "/getHistoryRecommendApplicationScheme")
    @SystemLog(module = "留学服务", methods = "查看申请方案历史推荐")
    public List<RecommendApplicationScheme> getHistoryRecommendApplicationScheme() {
        return applicationSchemeService.getHistoryRecommendApplicationScheme();
    }

    // 根据id修改某条重点推荐

    @PutMapping(value = "/updateRecommendApplicationScheme")
    @SystemLog(module = "留学服务", methods = "更新申请方案推荐")
    public RecommendApplicationScheme updateRecommendApplicationScheme(Integer id, String picturePath, Integer rid) {
        RecommendApplicationScheme recommendApplicationScheme = new RecommendApplicationScheme();
        recommendApplicationScheme.setId(id);
        recommendApplicationScheme.setPicturePath(picturePath);
        recommendApplicationScheme.setRid(rid);
        applicationSchemeService.updateRecommendApplicationScheme(recommendApplicationScheme);
        return recommendApplicationScheme;

    }

    //根据id将某条方案从重点推荐中移除

    @DeleteMapping(value = "/deleteRecommendApplicationScheme")
    @SystemLog(module = "留学服务", methods = "删除申请方案推荐")
    public String deleteRecommendApplicationScheme(Integer id) {
        return applicationSchemeService.deleteRecommendApplicationScheme(id);
    }

    //根据gid获取所有草稿的申请要素
    @GetMapping(value = "/getApplicationElementDraftByGid")
    @SystemLog(module = "留学服务", methods = "获取申请要素草稿")
    public List<ApplicationElementVO> getApplicationElementDraftByGid(Integer gid) {
        return studyAboardService.getApplicationElementByGid(gid, StatesConstant.DRAFT);
    }

    //在gid下增加一条申请要素
    @PostMapping(value = "/addApplicationElement")
    @SystemLog(module = "留学服务", methods = "增加申请要素")
    public ApplicationElementVO addApplicationElement(Integer gid,String category,String synopsis,
                                                      String textPath,Integer flag) {
        ApplicationElementVO applicationElementVO=new ApplicationElementVO();
        applicationElementVO.setGid(gid);
        applicationElementVO.setCategory(category);
        applicationElementVO.setSynopsis(synopsis);
        applicationElementVO.setTextPath(textPath);
        applicationElementVO.setFlag(flag);
        studyAboardService.addApplicationElement(applicationElementVO);
        return applicationElementVO;
    }

    //根据id更改某条申请要素
    @PutMapping(value = "/updateApplicationElement")
    @SystemLog(module = "留学服务", methods = "更新申请要素")
    public ApplicationElementVO updateApplicationElement(Integer id,Integer gid,String category,
                                                      String synopsis,String textPath,Integer flag) {
        ApplicationElementVO applicationElementVO=new ApplicationElementVO();
        applicationElementVO.setId(id);
        applicationElementVO.setGid(gid);
        applicationElementVO.setCategory(category);
        applicationElementVO.setSynopsis(synopsis);
        applicationElementVO.setTextPath(textPath);
        applicationElementVO.setFlag(flag);
        studyAboardService.updateApplicationElement(applicationElementVO);
        return applicationElementVO;
    }


    //根据id删除某条申请要素
    @DeleteMapping(value = "/deleteApplicationElement")
    @SystemLog(module = "留学服务", methods = "删除申请要素")
    public String deleteApplicationElement(Integer id) {
        return studyAboardService.deleteApplicationElement(id);
    }


    //根据gid获取所有问题列表
    @GetMapping(value = "/getQuestionsByGid")
    @SystemLog(module = "留学服务", methods = "查看留学问答")
    public List<QuestionVO> getQuestionsByGid(Integer gid) {
        return studyAboardService.getQuestionsByGid(gid);
    }


    //根据gid增加一条问答
    @PostMapping(value = "/addQuestion")
    @SystemLog(module = "留学服务", methods = "增加留学问答")
    public QuestionVO addQuestion(Integer gid ,String question,String answer,String isShow) {
        QuestionVO questionVO=new QuestionVO();
        questionVO.setGid(gid);
        questionVO.setQuestion(question);
        questionVO.setAnswer(answer);
        questionVO.setIsShow(isShow);
        studyAboardService.addQuestion(questionVO);
        return questionVO;
    }


    //根据id编辑一条问答
    @PutMapping(value = "/updateQuestion")
    @SystemLog(module = "留学服务", methods = "更新留学问答")
    public QuestionVO updateQuestion(Integer id,Integer gid ,
                                              String question,String answer,String isShow) {
        QuestionVO questionVO=new QuestionVO();
        questionVO.setId(id);
        questionVO.setGid(gid);
        questionVO.setQuestion(question);
        questionVO.setAnswer(answer);
        questionVO.setIsShow(isShow);
        studyAboardService.updateQuestion(questionVO);
        return questionVO;
    }

    //根据id删除一条问答
    @DeleteMapping(value = "/deleteQuestion")
    @SystemLog(module = "留学服务", methods = "删除留学问答")
    public String deleteQuestion(Integer id) {
        return studyAboardService.deleteQuestion(id);
    }

}
