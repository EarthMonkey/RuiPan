package controller;

import constant.StatesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SchoolService;
import util.SystemLog;
import vo.*;

import java.util.List;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/School")
public class SchoolController {

    @Autowired
    SchoolService schoolService;


    //根据国家获取所有小专业
    @GetMapping(value = "/getAllCategory")
    public List<ProfessionCategoryVO> getProfessionCategory(String country){
        return schoolService.getProfessionCategory(country);
    }

    //根据专业分类（pid）获取该专业所有院校排名信息
    @GetMapping(value = "/getAllSchoolRankingByPid")
    public List<SchoolRankingVO> getAllSchoolRankingByPid(Integer pid){
        return schoolService.getAllSchoolRankingByPid(pid);
    }


    //根据院校编号（sid）获取该学校的详细信息（已发布）
    @GetMapping(value = "/getSchoolBySid")
    public SchoolVO getSchoolBySid(Integer sid){
        return schoolService.getSchoolBySid(sid, StatesConstant.PUBLISHED);
    }


    //根据sid获取该学校所有相关图片路径
    @GetMapping(value = "/getPictureBySid")
    public List<SchoolPictureVO> getSchoolPicture(Integer sid){
        return null;
    }

    //根据sid获取所有专业的录取要求
    @GetMapping(value = "/getProfessionRequirementsBySid")
    public List<ProfessionRequirementVO> getProfessionRequirementsBySid(Integer sid){
        return schoolService.getProfessionRequirementsBySid(sid);
    }

    //根据sid获取所有成功案例列表，见 SuccessfulCaseController.java


    //***********************后台管理接口******************************

    //根据学校id增加一所学校记录
    @PostMapping(value = "/addSchool")
    @SystemLog(module = "院校管理", methods = "增加学校")
    public SchoolVO addSchool(String collegeName,String schoolBadge,String synopsis,
            String address,String officialWebsite,String textPath,Integer flag){
        SchoolVO schoolVO=new SchoolVO();
        schoolVO.setCollegeName(collegeName);
        schoolVO.setSchoolBadge(schoolBadge);
        schoolVO.setSynopsis(synopsis);
        schoolVO.setAddress(address);
        schoolVO.setOfficialWebsite(officialWebsite);
        schoolVO.setTextPath(textPath);
        schoolVO.setFlag(flag);
        schoolService.addSchool(schoolVO);
        return schoolVO;
    }

    //根据学校id编辑一所学校记录
    @PutMapping(value = "/updateSchool")
    @SystemLog(module = "院校管理", methods = "更新学校")
    public SchoolVO updateSchool(Integer sid, String collegeName,String schoolBadge,String synopsis,
                                 String address,String officialWebsite,String textPath,Integer flag){
        SchoolVO schoolVO=new SchoolVO();
        schoolVO.setSid(sid);
        schoolVO.setCollegeName(collegeName);
        schoolVO.setSchoolBadge(schoolBadge);
        schoolVO.setSynopsis(synopsis);
        schoolVO.setAddress(address);
        schoolVO.setOfficialWebsite(officialWebsite);
        schoolVO.setTextPath(textPath);
        schoolVO.setFlag(flag);
        schoolService.updateSchool(schoolVO);
        return schoolVO;
    }

    //根据学校id删除一所学校记录
    @DeleteMapping(value = "/deleteSchool")
    @SystemLog(module = "院校管理", methods = "删除学校")
    public String deleteSchool(Integer sid){
        return schoolService.deleteSchool(sid);
    }

    //获取所有发布状态下的学校记录
    @GetMapping(value = "/getSchoolPublished")
    @SystemLog(module = "院校管理", methods = "查看所有发布学校")
    public List<SchoolVO> getSchoolPublished(){
        return schoolService.getSchools(StatesConstant.PUBLISHED);
    }

    //获取所有草稿状态下的学校记录
    @GetMapping(value = "/getSchoolDraft")
    @SystemLog(module = "院校管理", methods = "查看所有学校草稿")
    public List<SchoolVO> getSchoolDraft(){
        return schoolService.getSchools(StatesConstant.DRAFT);
    }

    //根据sid增加一张学校图片
    @PostMapping(value = "/addSchoolPicture")
    @SystemLog(module = "院校管理", methods = "增加学校图片")
    public SchoolPictureVO addSchoolPicture(Integer sid,String picturePath){
        SchoolPictureVO schoolPictureVO=new SchoolPictureVO();
        schoolPictureVO.setSid(sid);
        schoolPictureVO.setPicturePath(picturePath);
        schoolService.addSchoolPicture(schoolPictureVO);
        return schoolPictureVO;
    }

    //根据sid删除一张图片
    @DeleteMapping(value = "/deleteSchoolPicture")
    @SystemLog(module = "院校管理", methods = "删除学校图片")
    public String deleteSchoolPicture(Integer id){
        return schoolService.deleteSchoolPicture(id);
    }

    //pid增加一条院校排名记录
    @PostMapping(value = "/addSchoolRanking")
    @SystemLog(module = "院校管理", methods = "增加排名")
    public SchoolRankingVO addSchoolRanking(Integer pid,Integer sid,String scoreRequirements,String applicationDifficulty,Integer ranking){
        SchoolRankingVO schoolRankingVO=new SchoolRankingVO();
        schoolRankingVO.setPid(pid);
        schoolRankingVO.setSid(sid);
        schoolRankingVO.setScoreRequirements(scoreRequirements);
        schoolRankingVO.setApplicationDifficulty(applicationDifficulty);
        schoolRankingVO.setRanking(ranking);
        schoolService.addSchoolRanking(schoolRankingVO);
        return schoolRankingVO;
    }


    //根据id编辑一条院校排名记录
    @PutMapping(value = "/updateSchoolRanking")
    @SystemLog(module = "院校管理", methods = "修改排名")
    public SchoolRankingVO updateSchoolRanking(Integer id,String scoreRequirements,String applicationDifficulty,Integer ranking){
        SchoolRankingVO schoolRankingVO=new SchoolRankingVO();
        schoolRankingVO.setId(id);
        schoolRankingVO.setScoreRequirements(scoreRequirements);
        schoolRankingVO.setApplicationDifficulty(applicationDifficulty);
        schoolRankingVO.setRanking(ranking);
        schoolService.updateSchoolRanking(schoolRankingVO);
        return schoolRankingVO;
    }

    //根据id删除一条院校排名记录
    @DeleteMapping(value = "/deleteSchoolRanking")
    @SystemLog(module = "院校管理", methods = "删除排名")
    public String deleteSchoolRanking(Integer id){
        return schoolService.deleteSchoolRanking(id);
    }

}
