package service.serviceimpl;

import dao.ApplicationElementDao;
import dao.GradeCategoryDao;
import dao.HardConditionDao;
import dao.QuestionDao;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StudyAboardService;
import util.FileManager;
import vo.ApplicationElementVO;
import vo.HardConditionVO;
import vo.QuestionVO;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by ldchao on 2017/11/6.
 */
@Service
public class StudyAboardServiceImpl implements StudyAboardService{

    @Autowired
    GradeCategoryDao gradeCategoryDao;

    @Autowired
    HardConditionDao hardConditionDao;

    @Autowired
    ApplicationElementDao applicationElementDao;

    @Autowired
    QuestionDao questionDao;

    @Override
    public List<String> getAllCountry() {
        return gradeCategoryDao.getDistinctCountry();
    }

    @Override
    public Integer getGidByCountryAndGrade(String country, String grade) {
        return gradeCategoryDao.findDistinctByCountryAndGrade(country,grade).getGid();
    }

    @Override
    public String addOneCountry(String country) {
        if(gradeCategoryDao.countByCountry(country)>0){
            return "country_exist";
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
            return "country_not_exist";
        }else{
            gradeCategoryDao.deleteByCountry(country);
            return "success";
        }
    }

    @Override
    public Map<String, List<HardConditionVO>> getHardConditionByGid(Integer gid) {

        Map<String,List<HardConditionVO>> result=new LinkedHashMap<String, List<HardConditionVO>>();
        List<HardCondition> hardConditions=hardConditionDao.findAllByGidOrderByRankAscSubjectAsc(gid);
        for (HardCondition hardCondition:hardConditions) {
            HardConditionVO hardConditionVO=new HardConditionVO();
            hardConditionVO.update(hardCondition);
            String rank=hardCondition.getRank();
            if(result.containsKey(rank)){
                result.get(rank).add(hardConditionVO);
            }else{
                List<HardConditionVO> hardConditionVOS=new ArrayList<HardConditionVO>();
                hardConditionVOS.add(hardConditionVO);
                result.put(rank,hardConditionVOS);
            }
        }
        return result;
    }

    @Override
    public void addHardCondition(HardConditionVO hardConditionVO) {
        HardCondition hardCondition=hardConditionVO.toEntity();
        hardConditionDao.saveAndFlush(hardCondition);
        hardConditionVO.setId(hardCondition.getId());
    }

    @Override
    public String changeHardCondition(HardConditionVO hardConditionVO) {
        HardCondition hardCondition=hardConditionVO.toEntity();
        hardConditionDao.saveAndFlush(hardCondition);
        return "success";
    }

    @Override
    public String deleteHardCondition(Integer id) {
        if(hardConditionDao.exists(id)){
            hardConditionDao.delete(id);
            return "success";
        }else{
            return "not_exist";
        }
    }

    @Override
    public List<ApplicationElementVO> getApplicationElementByGid(Integer gid,Integer flag) {
        List<ApplicationElementVO> applicationElementVOS=new ArrayList<ApplicationElementVO>();
        List<ApplicationElement> applicationElements=applicationElementDao.findAllByGidAndFlagOrderByCategory(gid, flag);
        for (ApplicationElement applicationElement:applicationElements) {
            ApplicationElementVO applicationElementVO=new ApplicationElementVO();
            applicationElementVO.update(applicationElement);
            applicationElementVOS.add(applicationElementVO);
        }
        return applicationElementVOS;
    }

    @Override
    public ApplicationElementVO getApplicationElementById(Integer id) {
        ApplicationElementVO applicationElementVO=new ApplicationElementVO();
        applicationElementVO.update(applicationElementDao.findOne(id));
        return applicationElementVO;
    }

    @Override
    public void addApplicationElement(ApplicationElementVO applicationElementVO) {
        ApplicationElement applicationElement=applicationElementVO.toEntity();
        applicationElementDao.saveAndFlush(applicationElement);
        applicationElementVO.setId(applicationElement.getId());
    }

    @Override
    public void updateApplicationElement(ApplicationElementVO applicationElementVO) {
        ApplicationElement applicationElement=applicationElementVO.toEntity();
        applicationElementDao.saveAndFlush(applicationElement);
    }

    @Override
    public String deleteApplicationElement(Integer id) {
        if(applicationElementDao.exists(id)){
            ApplicationElement applicationElement=applicationElementDao.findOne(id);
            applicationElementDao.delete(id);
            FileManager.deleteText(applicationElement.getTextPath());
            return "success";
        }else{
            return "not_exist";
        }
    }

    @Override
    public List<QuestionVO> getPublishQuestionsByGid(Integer gid) {
        List<Question> questions=questionDao.findAllByGidAndIsShowOrderByCreateAtDesc(gid,"true");
        List<QuestionVO> questionVOS=new ArrayList<QuestionVO>();
        for (Question question:questions) {
            QuestionVO questionVO=new QuestionVO();
            questionVO.update(question);
            questionVOS.add(questionVO);
        }
        return questionVOS;
    }

    @Override
    public List<QuestionVO> getQuestionsByGid(Integer gid) {
        List<Question> questions=questionDao.findAllByGidOrderByCreateAtDesc(gid);
        List<QuestionVO> questionVOS=new ArrayList<QuestionVO>();
        for (Question question:questions) {
            QuestionVO questionVO=new QuestionVO();
            questionVO.update(question);
            questionVOS.add(questionVO);
        }
        return questionVOS;
    }

    @Override
    public void addQuestion(QuestionVO questionVO) {
        Question question=questionVO.toEntity();
        questionDao.saveAndFlush(question);
        questionVO.setId(question.getId());
    }

    @Override
    public void updateQuestion(QuestionVO questionVO) {
        Question question=questionVO.toEntity();
        questionDao.saveAndFlush(question);
    }

    @Override
    public String deleteQuestion(Integer id) {
        if(questionDao.exists(id)){
            questionDao.delete(id);
            return "success";
        }else{
            return "not_exist";
        }
    }
}
