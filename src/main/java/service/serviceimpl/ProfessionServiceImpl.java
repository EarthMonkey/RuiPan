package service.serviceimpl;

import dao.*;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ProfessionService;
import vo.ProfessionCategoryVO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/15.
 */
@Service
public class ProfessionServiceImpl implements ProfessionService{

    @Autowired
    ProfessionCategoryDao professionCategoryDao;

    @Autowired
    ProfessionIntroduceDao professionIntroduceDao;

    @Autowired
    ProfessionCourseDao professionCourseDao;

    @Autowired
    EmploymentCompanyDao employmentCompanyDao;

    @Autowired
    EmploymentPostDao employmentPostDao;

    @Autowired
    SalaryDao salaryDao;

    @Autowired
    ApplicationAdviceDao applicationAdviceDao;

    @Override
    public List<String> getAllCountry() {
        return professionCategoryDao.getDistinctCountry();
    }

    @Override
    public Map<String, List<String>> getAllCategoryNameByCountry(String country) {
        Map<String,List<String>> result=new LinkedHashMap<String, List<String>>();
        List<ProfessionCategory> professionCategories=professionCategoryDao.findAllByCountryOrderByCategory(country);
        for (ProfessionCategory professionCategory:professionCategories) {
            String category=professionCategory.getCategory();
            if(result.containsKey(category)){
                result.get(category).add(professionCategory.getSubclassification());
            }else{
                List<String> strings=new ArrayList<String>();
                strings.add(professionCategory.getSubclassification());
                result.put(category,strings);
            }
        }
        return result;
    }

    @Override
    public Integer getPidByCountryAndSubclassification(String country, String subclassification) {
        return professionCategoryDao.findDistinctByCountryAndSubclassification(country,subclassification).getPid();
    }

    @Override
    public Map<String, List<ProfessionCategoryVO>> getAllCategoryByCountry(String country) {
        Map<String,List<ProfessionCategoryVO>> result=new LinkedHashMap<String, List<ProfessionCategoryVO>>();
        List<ProfessionCategory> professionCategories=professionCategoryDao.findAllByCountryOrderByCategory(country);
        for (ProfessionCategory professionCategory:professionCategories) {
            ProfessionCategoryVO professionCategoryVO=new ProfessionCategoryVO();
            professionCategoryVO.update(professionCategory);
            String category=professionCategory.getCategory();
            if(result.containsKey(category)){
                result.get(category).add(professionCategoryVO);
            }else{
                List<ProfessionCategoryVO> professionCategoryVOS=new ArrayList<ProfessionCategoryVO>();
                professionCategoryVOS.add(professionCategoryVO);
                result.put(category,professionCategoryVOS);
            }
        }
        return result;
    }

    @Override
    public void addProfessionCategory(ProfessionCategoryVO professionCategoryVO) {
        ProfessionCategory professionCategory=professionCategoryVO.toEntity();
        professionCategoryDao.saveAndFlush(professionCategory);
        professionCategoryVO.setPid(professionCategory.getPid());
    }

    @Override
    public String deleteProfessionCategory(Integer pid) {
        if(professionCategoryDao.exists(pid)){
            professionCategoryDao.delete(pid);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public ProfessionIntroduce getProfessionIntroduce(Integer pid,Integer flag) {
        ProfessionIntroduce professionIntroduce=professionIntroduceDao.findOne(pid);
        return flag==professionIntroduce.getFlag()?professionIntroduce:null;
    }

    @Override
    public ProfessionIntroduce addProfessionIntroduce(ProfessionIntroduce professionIntroduce) {
        return professionIntroduceDao.saveAndFlush(professionIntroduce);
    }

    @Override
    public String deleteProfessionIntroduce(Integer pid) {
        if(professionIntroduceDao.exists(pid)){
            professionIntroduceDao.delete(pid);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public List<ProfessionCourse> getProfessionCourse(Integer pid) {
        return professionCourseDao.findAllByPid(pid);
    }

    @Override
    public ProfessionCourse addProfessionCourse(ProfessionCourse professionCourse) {
        return professionCourseDao.saveAndFlush(professionCourse);
    }

    @Override
    public ProfessionCourse updateProfessionCourse(ProfessionCourse professionCourse) {
        return professionCourseDao.saveAndFlush(professionCourse);
    }

    @Override
    public String deleteProfessionCourse(Integer id) {
        if(professionCourseDao.exists(id)){
            professionCourseDao.delete(id);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public List<EmploymentCompany> getEmploymentCompany(Integer pid) {
        return employmentCompanyDao.findAllByPid(pid);
    }

    @Override
    public EmploymentCompany addEmploymentCompany(EmploymentCompany employmentCompany) {
        return employmentCompanyDao.saveAndFlush(employmentCompany);
    }

    @Override
    public EmploymentCompany updateEmploymentCompany(EmploymentCompany employmentCompany) {
        return employmentCompanyDao.saveAndFlush(employmentCompany);
    }

    @Override
    public String deleteEmploymentCompany(Integer id) {
        if(employmentCompanyDao.exists(id)){
            employmentCompanyDao.delete(id);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public List<EmploymentPost> getEmploymentPost(Integer pid) {
        return employmentPostDao.findAllByPid(pid);
    }

    @Override
    public EmploymentPost addEmploymentPost(EmploymentPost employmentPost) {
        return employmentPostDao.saveAndFlush(employmentPost);
    }

    @Override
    public EmploymentPost updateEmploymentPost(EmploymentPost employmentPost) {
        return employmentPostDao.saveAndFlush(employmentPost);
    }

    @Override
    public String deleteEmploymentPost(Integer id) {
        if(employmentPostDao.exists(id)){
            employmentPostDao.delete(id);
            return "success";
        }
        return "false";
    }

    @Override
    public List<Salary> getSalary(Integer pid) {
        return salaryDao.findAllByPid(pid);
    }

    @Override
    public Salary addSalary(Salary salary) {
        return salaryDao.saveAndFlush(salary);
    }

    @Override
    public Salary updateSalary(Salary salary) {
        return salaryDao.saveAndFlush(salary);
    }

    @Override
    public String deleteSalary(Integer id) {
        if(salaryDao.exists(id)){
            salaryDao.delete(id);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public List<ApplicationAdvice> getApplicationAdvice(Integer pid) {
        return applicationAdviceDao.findAllByPid(pid);
    }

    @Override
    public ApplicationAdvice addApplicationAdvice(ApplicationAdvice applicationAdvice) {
        return applicationAdviceDao.saveAndFlush(applicationAdvice);
    }

    @Override
    public ApplicationAdvice updateApplicationAdvice(ApplicationAdvice applicationAdvice) {
        return applicationAdviceDao.saveAndFlush(applicationAdvice);
    }

    @Override
    public String deleteApplicationAdvice(Integer id) {
        if(applicationAdviceDao.exists(id)){
            applicationAdviceDao.delete(id);
            return "success";
        }
        return "not_exist";
    }
}
