package service.serviceimpl;

import dao.GradeCategoryDao;
import model.GradeCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StudyAboardService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldchao on 2017/11/6.
 */
@Service
public class StudyAboardServiceImpl implements StudyAboardService{

    @Autowired
    GradeCategoryDao gradeCategoryDao;

    @Override
    public List<String> getAllCountry() {
        return gradeCategoryDao.getDistinctCountry();
    }

    @Override
    public String addOneCountry(String country) {
        if(gradeCategoryDao.countByCountry(country)>0){
            return "country_exit";
        }else{
            String grades[]={"研究生","本科生","高中生"};
            List<GradeCategory> gradeCategories=new ArrayList<GradeCategory>();
            for (int i = 0; i < grades.length; i++) {
                GradeCategory gradeCategory=new GradeCategory();
                gradeCategory.setCountry(country);
                gradeCategory.setGrade(grades[i]);
                gradeCategory.setCreatAt(new Timestamp(System.currentTimeMillis()));
                gradeCategories.add(gradeCategory);
            }
            gradeCategoryDao.save(gradeCategories);
            gradeCategoryDao.flush();
            return "success";
        }
    }

    @Override
    public String deleteOneCountry(String country) {
        if(gradeCategoryDao.countByCountry(country)==0){
            return "country_not_exit";
        }else{
            gradeCategoryDao.deleteByCountry(country);
            return "success";
        }
    }
}
