package controller;

import model.CooperativeScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CooperativeEducationService;
import util.SystemLog;
import vo.CooperativeCategoryVO;

import java.util.List;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/CooperativeEducation")
public class CooperativeEducationController {

    @Autowired
    CooperativeEducationService cooperativeEducationService;

    /**
     * 轮播图相关参考 HomePageController.java
     * 资讯相关参考 NewsController.java
     */

    //根据大分类，获取所有小分类列表
    @GetMapping(value="/getSubclassificationByCategory")
    public List<CooperativeCategoryVO> getSubclassificationByCategory(String category){
        return cooperativeEducationService.getSubclassificationByCategory(category);
    }

    //根据分类编号（cid）获取所有合作方案列表
    @GetMapping(value="/getCooperativeSchemeByCid")
    public List<CooperativeScheme> getCooperativeSchemeByCid(Integer cid){
        return null;
    }


    //***********************后台管理接口******************************

    //在大分类下增加一条小分类
    @PostMapping(value="/addCooperativeCategory")
    @SystemLog(module = "合作办学", methods = "增加分类")
    public CooperativeCategoryVO addCooperativeCategory(String category,String subclassification){
        CooperativeCategoryVO cooperativeCategoryVO=new CooperativeCategoryVO();
        cooperativeCategoryVO.setCategory(category);
        cooperativeCategoryVO.setSubclassification(subclassification);
        cooperativeEducationService.addCooperativeCategory(cooperativeCategoryVO);
        return cooperativeCategoryVO;
    }

    //在大分类下编辑一条小分类
    @PutMapping(value="/updateCooperativeCategory")
    @SystemLog(module = "合作办学", methods = "编辑分类")
    public CooperativeCategoryVO updateCooperativeCategory(Integer id, String category,String subclassification){
        CooperativeCategoryVO cooperativeCategoryVO=new CooperativeCategoryVO();
        cooperativeCategoryVO.setId(id);
        cooperativeCategoryVO.setCategory(category);
        cooperativeCategoryVO.setSubclassification(subclassification);
        cooperativeEducationService.updateCooperativeCategory(cooperativeCategoryVO);
        return cooperativeCategoryVO;
    }

    //在大分类下删除一条小分类
    @DeleteMapping(value="/deleteCooperativeCategory")
    @SystemLog(module = "合作办学", methods = "删除分类")
    public String deleteCooperativeCategory(Integer id){
        return cooperativeEducationService.deleteCooperativeCategory(id);
    }

    //在cid下增加一条合作方案

    //根据id编辑一条合作方案

    //根据id删除一条合作方案

    //在cid下获取所有草稿状态的合作方案
}
