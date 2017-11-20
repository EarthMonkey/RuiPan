package controller;

import constant.StatesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.SuccessfulCaseService;
import util.SuccessfulCaseUtil;
import util.SystemLog;
import vo.RecommendSuccessfulCase;
import vo.SuccessfulCaseVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/SuccessfulCase")
public class SuccessfulCaseController {

    @Autowired
    SuccessfulCaseService successfulCaseService;

    //获取所有国家及专业分类列表 参考ProfessionController.java


    //根据专业id（pid）获取所有发布状态成功案例列表
    @GetMapping(value="/getSuccessfulCaseByPid")
    public List<SuccessfulCaseVO> getSuccessfulCaseByPid(Integer pid){
        return successfulCaseService.getSuccessfulCaseByPid(pid, StatesConstant.PUBLISHED);
    }

    //根据学校id（sid）获取所有发布状态成功案例列表(已发布)
    @GetMapping(value="/getSuccessfulCaseBySid")
    public List<SuccessfulCaseVO> getSuccessfulCaseBySid(Integer sid){
        return successfulCaseService.getSuccessfulCaseBySid(sid, StatesConstant.PUBLISHED);
    }

    //根据顾问id（cid）获取所有发布状态成功案例列表(已发布)
    @GetMapping(value="/getSuccessfulCaseByCid")
    public List<SuccessfulCaseVO> getSuccessfulCaseByCid(Integer cid){
        return successfulCaseService.getSuccessfulCaseByCid(cid, StatesConstant.PUBLISHED);
    }

    //根据成功案例id获取成功案例详情
    @GetMapping(value="/getSuccessfulCase")
    public SuccessfulCaseVO getSuccessfulCase(Integer id){
        return successfulCaseService.getSuccessfulCase(id);
    }


    //获取四个全局推荐的成功案例
    @GetMapping(value="/getRecommendSuccessfulCase")
    public List<RecommendSuccessfulCase> getRecommendSuccessfulCase(){
        return successfulCaseService.getRecommendSuccessfulCase(StatesConstant.RECOMMEND);
    }

    //获取不同国家下最近limit个成功案例列表
    @GetMapping(value="/getSuccessfulCaseGroupByCountry")
    public Map<String,List<SuccessfulCaseVO>> getSuccessfulCaseGroupByCountry(Integer limit){
        return successfulCaseService.getSuccessfulCaseGroupByCountry(limit);
    }


    //***********************后台管理接口******************************


    //根据专业id（pid）获取所有草稿状态成功案例列表
    @GetMapping(value="/getSuccessfulCaseDraftByPid")
    @SystemLog(module = "成功案例" ,methods = "根据专业查看案例草稿")
    public List<SuccessfulCaseVO> getSuccessfulCaseDraftByPid(Integer pid){
        return successfulCaseService.getSuccessfulCaseByPid(pid, StatesConstant.DRAFT);
    }

    //根据学校id（sid）获取所有草稿状态成功案例列表
    @GetMapping(value="/getSuccessfulCaseDraftBySid")
    @SystemLog(module = "成功案例" ,methods = "根据学校查看案例草稿")
    public List<SuccessfulCaseVO> getSuccessfulCaseDraftBySid(Integer sid){
        return successfulCaseService.getSuccessfulCaseBySid(sid, StatesConstant.DRAFT);
    }

    //根据顾问id（cid）获取所有草稿状态成功案例列表
    @GetMapping(value="/getSuccessfulCaseDraftByCid")
    @SystemLog(module = "成功案例" ,methods = "根据顾问查看案例草稿")
    public List<SuccessfulCaseVO> getSuccessfulCaseDraftByCid(Integer cid){
        return successfulCaseService.getSuccessfulCaseByCid(cid, StatesConstant.DRAFT);
    }

    //增加一条案例
    @PostMapping(value="/addSuccessfulCase")
    @SystemLog(module = "成功案例" ,methods = "增加案例")
    public SuccessfulCaseVO addSuccessfulCase(Integer pid,Integer sid,Integer cid,String name,
            String degree,String enrollmentTime,String languageScore,String gpa,
            String gmatSatGre,String undergraduateMajor,String textPath,Integer flag){
        SuccessfulCaseVO successfulCaseVO=new SuccessfulCaseVO();
        successfulCaseVO.setPid(pid);
        successfulCaseVO.setSid(sid);
        successfulCaseVO.setCid(cid);
        successfulCaseVO.setName(name);
        successfulCaseVO.setDegree(degree);
        successfulCaseVO.setEnrollmentTime(enrollmentTime);
        successfulCaseVO.setLanguageScore(languageScore);
        successfulCaseVO.setGpa(gpa);
        successfulCaseVO.setGmatSatGre(gmatSatGre);
        successfulCaseVO.setUndergraduateMajor(undergraduateMajor);
        successfulCaseVO.setTextPath(textPath);
        successfulCaseVO.setFlag(flag);
        successfulCaseService.addSuccessfulCase(successfulCaseVO);
        return successfulCaseVO;
    }

    //根据案例id修改一条案例
    @PutMapping(value="/updateSuccessfulCase")
    @SystemLog(module = "成功案例" ,methods = "编辑案例")
    public SuccessfulCaseVO updateSuccessfulCase(Integer id,Integer pid,Integer sid,Integer cid,String name,
                                                 String degree,String enrollmentTime,String languageScore,String gpa,
                                                 String gmatSatGre,String undergraduateMajor,String textPath,Integer flag){
        SuccessfulCaseVO successfulCaseVO=new SuccessfulCaseVO();
        successfulCaseVO.setId(id);
        successfulCaseVO.setPid(pid);
        successfulCaseVO.setSid(sid);
        successfulCaseVO.setCid(cid);
        successfulCaseVO.setName(name);
        successfulCaseVO.setDegree(degree);
        successfulCaseVO.setEnrollmentTime(enrollmentTime);
        successfulCaseVO.setLanguageScore(languageScore);
        successfulCaseVO.setGpa(gpa);
        successfulCaseVO.setGmatSatGre(gmatSatGre);
        successfulCaseVO.setUndergraduateMajor(undergraduateMajor);
        successfulCaseVO.setTextPath(textPath);
        successfulCaseVO.setFlag(flag);
        successfulCaseService.updateSuccessfulCase(successfulCaseVO);
        return successfulCaseVO;
    }

    //根据案例id删除一条案例
    @DeleteMapping(value="/deleteSuccessfulCase")
    @SystemLog(module = "成功案例" ,methods = "删除案例")
    public String deleteSuccessfulCase(Integer id){
        return successfulCaseService.deleteSuccessfulCase(id);
    }

    //根据案例id将某条案例添加到全局推荐（最多四条，超了，添加时间最早的自动变成历史推荐）
    @PostMapping(value="/addRecommendSuccessfulCase")
    @SystemLog(module = "成功案例" ,methods = "增加推荐")
    public RecommendSuccessfulCase addRecommendSuccessfulCase(String slogan, Integer rid){
        RecommendSuccessfulCase recommendSuccessfulCase=new RecommendSuccessfulCase();
        recommendSuccessfulCase.setSlogan(slogan);
        recommendSuccessfulCase.setRid(rid);
        successfulCaseService.addRecommendSuccessfulCase(recommendSuccessfulCase);
        return recommendSuccessfulCase;
    }

    //查看所有历史推荐状态的全局推荐案例
    @GetMapping(value="/getHistoryRecommendSuccessfulCase")
    @SystemLog(module = "成功案例" ,methods = "查看历史推荐")
    public List<RecommendSuccessfulCase> getHistoryRecommendSuccessfulCase(){
        return successfulCaseService.getRecommendSuccessfulCase(StatesConstant.HISTORY_RECOMMEND);
    }

    //根据id编辑某条全局推荐的案例
    @PutMapping(value="/updateRecommendSuccessfulCase")
    @SystemLog(module = "成功案例" ,methods = "编辑推荐")
    public RecommendSuccessfulCase updateRecommendSuccessfulCase(Integer id,String slogan,Integer rid){
        RecommendSuccessfulCase recommendSuccessfulCase=new RecommendSuccessfulCase();
        recommendSuccessfulCase.setId(id);
        recommendSuccessfulCase.setSlogan(slogan);
        recommendSuccessfulCase.setRid(rid);
        successfulCaseService.updateRecommendSuccessfulCase(recommendSuccessfulCase);
        return recommendSuccessfulCase;
    }

    //根据id将某条案例从全局推荐中移除
    @DeleteMapping(value="/deleteRecommendSuccessfulCase")
    @SystemLog(module = "成功案例" ,methods = "删除推荐")
    public String deleteRecommendSuccessfulCase(Integer id){
        return successfulCaseService.deleteRecommendSuccessfulCase(id);
    }
}
