package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.CooperativeCategoryVO;

import java.util.List;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/CooperativeEducation")
public class CooperativeEducationController {

    /**
     * 轮播图相关参考 HomePageController.java
     * 资讯相关参考 NewsController.java
     */

    //根据大分类，获取所有小分类列表
    @GetMapping(value="/getSubclassificationByCategory")
    public List<CooperativeCategoryVO> getSubclassificationByCategory(String category){
        return null;
    }

    //根据分类编号（cid）获取所有合作方案列表

    //***********************后台管理接口******************************

    //在大分类下增加一条小分类

    //在大分类下编辑一条小分类

    //在大分类下删除一条小分类

    //在cid下增加一条合作方案

    //根据id编辑一条合作方案

    //根据id删除一条合作方案

    //在cid下获取所有草稿状态的合作方案
}
