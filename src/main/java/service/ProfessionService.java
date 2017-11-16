package service;

import model.*;
import vo.ProfessionCategoryVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/15.
 */
public interface ProfessionService {

    public List<String> getAllCountry();

    public Map<String,List<String>> getAllCategoryNameByCountry(String country);

    public Integer getPidByCountryAndSubclassification(String country,String subclassification);

    public Map<String, List<ProfessionCategoryVO>> getAllCategoryByCountry(String country);

    public void addProfessionCategory(ProfessionCategoryVO professionCategoryVO);

    public String deleteProfessionCategory(Integer pid);

    public ProfessionIntroduce getProfessionIntroduce(Integer pid,Integer flag);

    public ProfessionIntroduce addProfessionIntroduce(ProfessionIntroduce professionIntroduce) ;

    public String deleteProfessionIntroduce(Integer pid);

    public List<ProfessionCourse> getProfessionCourse(Integer pid);

    public ProfessionCourse addProfessionCourse(ProfessionCourse professionCourse);

    public ProfessionCourse updateProfessionCourse(ProfessionCourse professionCourse);

    public String deleteProfessionCourse(Integer id);

    public List<EmploymentCompany> getEmploymentCompany(Integer pid);

    public EmploymentCompany addEmploymentCompany(EmploymentCompany employmentCompany);

    public EmploymentCompany updateEmploymentCompany(EmploymentCompany employmentCompany);

    public String deleteEmploymentCompany(Integer id);

    public List<EmploymentPost> getEmploymentPost(Integer pid);

    public EmploymentPost addEmploymentPost(EmploymentPost employmentPost);

    public EmploymentPost updateEmploymentPost(EmploymentPost employmentPost);

    public String deleteEmploymentPost(Integer id);

    public List<Salary> getSalary(Integer pid);

    public Salary addSalary(Salary salary);

    public Salary updateSalary(Salary salary);

    public String deleteSalary(Integer id);

    public List<ApplicationAdvice> getApplicationAdvice(Integer pid);

    public ApplicationAdvice addApplicationAdvice(ApplicationAdvice applicationAdvice);

    public ApplicationAdvice updateApplicationAdvice(ApplicationAdvice applicationAdvice);

    public String deleteApplicationAdvice(Integer id);


}
