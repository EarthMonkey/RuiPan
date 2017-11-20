package controller;

import constant.StatesConstant;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProfessionService;
import service.SchoolService;
import util.SystemLog;
import vo.ProfessionCategoryVO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/Profession")
public class ProfessionController {

    @Autowired
    ProfessionService professionService;

    @Autowired
    SchoolService schoolService;

    //获取所有国家
    @GetMapping(value = "/getAllCountry")
    public List<String> getAllCountry() {
        return professionService.getAllCountry();
    }

    //根据国家获取大分类及其下所有小分类名称
    @GetMapping(value = "/getAllCategoryNameByCountry")
    public Map<String,List<String>> getAllCategoryNameByCountry(String country) {
        return professionService.getAllCategoryNameByCountry(country);
    }

    //根据国家和小分类获取id
    @GetMapping(value = "/getPid")
    public Integer getPidByCountryAndSubclassification(String country,String subclassification) {
        return professionService.getPidByCountryAndSubclassification(country,subclassification);
    }

    //根据分类（pid）获取专业信息、申请建议信息
    @GetMapping(value = "/getProfessionIntroducePublished")
    public ProfessionIntroduce getProfessionIntroducePublished(Integer pid) {
        return professionService.getProfessionIntroduce(pid, StatesConstant.PUBLISHED);
    }

    //根据pid获取该专业所有专业方向及相关课程列表
    @GetMapping(value = "/getProfessionCourse")
    public List<ProfessionCourse> getProfessionCourse(Integer pid) {
        return professionService.getProfessionCourse(pid);
    }

    //根据pid获取该专业所有就业去向列表
    @GetMapping(value="/getEmploymentCompany")
    public List<EmploymentCompany> getEmploymentCompany(Integer pid){
        return professionService.getEmploymentCompany(pid);
    }

    //根据pid获取该专业所有就业岗位列表
    @GetMapping(value="/getEmploymentPost")
    public List<EmploymentPost> getEmploymentPost(Integer pid){
        return professionService.getEmploymentPost(pid);
    }

    //根据pid获取该专业所有就业薪资列表
    @GetMapping(value="/getSalary")
    public List<Salary> getSalary(Integer pid){
        return professionService.getSalary(pid);
    }


    //根据pid获取该专业所有申请条件列表
    @GetMapping(value="/getApplicationAdvice")
    public List<ApplicationAdvice> getApplicationAdvice(Integer pid){
        return professionService.getApplicationAdvice(pid);
    }

    //根据pid获取该专业所有院校排名列表，见 SchoolController.java


    //根据pid获取该专业所有成功案例列表，见 SuccessfulCaseController.java



    //***********************后台管理接口******************************

    //根据国家获取大分类及其下所有小分类具体信息
    @GetMapping(value = "/getAllCategoryByCountry")
    @SystemLog(module = "专业管理", methods = "获取专业分类信息")
    public Map<String,List<ProfessionCategoryVO>> getAllCategoryByCountry(String country) {
        return professionService.getAllCategoryByCountry(country);
    }

    //在某个国家和大专业下增加一个小专业
    @PostMapping(value = "/addProfessionCategory")
    @SystemLog(module = "专业管理", methods = "增加专业分类信息")
    public ProfessionCategoryVO addProfessionCategory(String country,String category,String subclassification) {
        ProfessionCategoryVO professionCategoryVO=new ProfessionCategoryVO();
        professionCategoryVO.setCountry(country);
        professionCategoryVO.setCategory(category);
        professionCategoryVO.setSubclassification(subclassification);
        professionService.addProfessionCategory(professionCategoryVO);
        return professionCategoryVO;
    }

    //根据pid删除一条专业分类记录
    @DeleteMapping(value = "/deleteProfessionCategory")
    @SystemLog(module = "专业管理", methods = "删除专业分类信息")
    public String deleteProfessionCategory(Integer pid){
        return professionService.deleteProfessionCategory(pid);
    }

    //在pid下增加/修改一条专业详情介绍+专业课程介绍+独家申请建议（每个pid只能由一条）
    @RequestMapping(value = {"/addProfessionIntroduce","/updateProfessionIntroduce"})
    @SystemLog(module = "专业管理", methods = "修改专业介绍")
    public ProfessionIntroduce addProfessionIntroduce(Integer pid ,String detailSynopsis,String textPath,
            String applicationAdvice,Integer flag) {
        ProfessionIntroduce professionIntroduce=new ProfessionIntroduce();
        professionIntroduce.setPid(pid);
        professionIntroduce.setDetailSynopsis(detailSynopsis);
        professionIntroduce.setTextPath(textPath);
        professionIntroduce.setApplicationAdvice(applicationAdvice);
        professionIntroduce.setFlag(flag);
        professionIntroduce.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return professionService.addProfessionIntroduce(professionIntroduce);
    }

    //根据pid删除某条专业详情介绍+专业课程介绍+独家申请建议
    @DeleteMapping(value = "/deleteProfessionIntroduce")
    @SystemLog(module = "专业管理", methods = "删除专业介绍")
    public String deleteProfessionIntroduce(Integer pid) {
        return professionService.deleteProfessionIntroduce(pid);
    }

    //根据pid获取草稿状态的专业详情介绍+专业课程介绍+独家申请建议
    //根据分类（pid）获取专业信息、申请建议信息
    @GetMapping(value = "/getProfessionIntroduceDraft")
    @SystemLog(module = "专业管理", methods = "获取专业介绍草稿")
    public ProfessionIntroduce getProfessionIntroduceDraft(Integer pid) {
        return professionService.getProfessionIntroduce(pid,StatesConstant.DRAFT);
    }

    //在pid下增加一条课程
    @PostMapping(value = "/addProfessionCourse")
    @SystemLog(module = "专业管理", methods = "增加专业课程")
    public ProfessionCourse addProfessionCourse(Integer pid, String majorField, String majorCourse) {
        ProfessionCourse professionCourse=new ProfessionCourse();
        professionCourse.setPid(pid);
        professionCourse.setMajorField(majorField);
        professionCourse.setMajorCourse(majorCourse);
        professionCourse.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return professionService.addProfessionCourse(professionCourse);
    }

    //在pid下修改一条课程
    @PutMapping(value = "/updateProfessionCourse")
    @SystemLog(module = "专业管理", methods = "修改专业课程")
    public ProfessionCourse updateProfessionCourse(Integer id,Integer pid, String majorField, String majorCourse) {
        ProfessionCourse professionCourse=new ProfessionCourse();
        professionCourse.setId(id);
        professionCourse.setPid(pid);
        professionCourse.setMajorField(majorField);
        professionCourse.setMajorCourse(majorCourse);
        professionCourse.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return professionService.updateProfessionCourse(professionCourse);
    }

    //在pid下删除一条课程
    @DeleteMapping(value = "/deleteProfessionCourse")
    @SystemLog(module = "专业管理", methods = "删除专业课程")
    public String deleteProfessionCourse(Integer id) {
        return professionService.deleteProfessionCourse(id);
    }

    //在pid下增加一条就业去向
    @PostMapping(value="/addEmploymentCompany")
    @SystemLog(module = "专业管理", methods = "增加就业去向")
    public EmploymentCompany addEmploymentCompany(Integer pid,String logo,String employmentCompany){
        EmploymentCompany ec=new EmploymentCompany();
        ec.setPid(pid);
        ec.setLogo(logo);
        ec.setEmploymentCompany(employmentCompany);
        ec.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return professionService.addEmploymentCompany(ec);
    }

    //在pid下修改一条就业去向
    @PutMapping(value="/updateEmploymentCompany")
    @SystemLog(module = "专业管理", methods = "修改就业去向")
    public EmploymentCompany updateEmploymentCompany(Integer id,Integer pid,String logo,String employmentCompany){
        EmploymentCompany ec=new EmploymentCompany();
        ec.setId(id);
        ec.setPid(pid);
        ec.setLogo(logo);
        ec.setEmploymentCompany(employmentCompany);
        ec.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return professionService.updateEmploymentCompany(ec);
    }

    //在pid下删除一条就业去向
    @DeleteMapping(value="/deleteEmploymentCompany")
    @SystemLog(module = "专业管理", methods = "删除就业去向")
    public String deleteEmploymentCompany(Integer id){
        return professionService.deleteEmploymentCompany(id);
    }

    //在pid下增加一条就业岗位
    @PostMapping(value="/addEmploymentPost")
    @SystemLog(module = "专业管理", methods = "增加就业岗位")
    public EmploymentPost addEmploymentPost(Integer pid,String post){
        EmploymentPost employmentPost=new EmploymentPost();
        employmentPost.setPid(pid);
        employmentPost.setPost(post);
        employmentPost.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return professionService.addEmploymentPost(employmentPost);
    }

    //在pid下修改一条就业岗位
    @PutMapping(value="/updateEmploymentPost")
    @SystemLog(module = "专业管理", methods = "修改就业岗位")
    public EmploymentPost updateEmploymentPost(Integer id,Integer pid,String post){
        EmploymentPost employmentPost=new EmploymentPost();
        employmentPost.setId(id);
        employmentPost.setPid(pid);
        employmentPost.setPost(post);
        employmentPost.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return professionService.updateEmploymentPost(employmentPost);
    }

    //根据id下删除一条就业岗位
    @DeleteMapping(value="/deleteEmploymentPost")
    @SystemLog(module = "专业管理", methods = "删除就业岗位")
    public String deleteEmploymentPost(Integer id){
        return professionService.deleteEmploymentPost(id);
    }

    //在pid下增加一条就业薪资
    @PostMapping(value="/addSalary")
    @SystemLog(module = "专业管理", methods = "增加就业薪资")
    public Salary addSalary(Integer pid,String item,Double salary){
        Salary s=new Salary();
        s.setPid(pid);
        s.setItem(item);
        s.setSalary(salary);
        return professionService.addSalary(s);
    }


    //在pid下修改一条就业薪资
    @PutMapping(value="/updateSalary")
    @SystemLog(module = "专业管理", methods = "编辑就业薪资")
    public Salary updateSalary(Integer id,Integer pid,String item,Double salary){
        Salary s=new Salary();
        s.setId(id);
        s.setPid(pid);
        s.setItem(item);
        s.setSalary(salary);
        return professionService.updateSalary(s);
    }

    //根据id删除一条就业薪资
    @DeleteMapping(value="/deleteSalary")
    @SystemLog(module = "专业管理", methods = "删除就业薪资")
    public String deleteSalary(Integer id){
        return professionService.deleteSalary(id);
    }

    //在pid下增加一条申请条件
    @PostMapping(value="/addApplicationAdvice")
    @SystemLog(module = "专业管理", methods = "增加申请条件")
    public ApplicationAdvice addApplicationAdvice(Integer pid,String item,String advice){
        ApplicationAdvice applicationAdvice=new ApplicationAdvice();
        applicationAdvice.setPid(pid);
        applicationAdvice.setItem(item);
        applicationAdvice.setAdvice(advice);
        applicationAdvice.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return professionService.addApplicationAdvice(applicationAdvice);
    }

    //在pid下修改一条申请条件
    @PutMapping(value="/updateApplicationAdvice")
    @SystemLog(module = "专业管理", methods = "更新申请条件")
    public ApplicationAdvice updateApplicationAdvice(Integer id,Integer pid,String item,String advice){
        ApplicationAdvice applicationAdvice=new ApplicationAdvice();
        applicationAdvice.setId(id);
        applicationAdvice.setPid(pid);
        applicationAdvice.setItem(item);
        applicationAdvice.setAdvice(advice);
        applicationAdvice.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return professionService.updateApplicationAdvice(applicationAdvice);
    }

    //在pid下删除一条申请条件
    @DeleteMapping(value="/deleteApplicationAdvice")
    @SystemLog(module = "专业管理", methods = "删除申请条件")
    public String deleteApplicationAdvice(Integer id){
        return professionService.deleteApplicationAdvice(id);
    }



}
