package service.serviceimpl;

import dao.GradeCategoryDao;
import dao.HardConditionDao;
import model.GradeCategory;
import model.HardCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StudyAboardService;
import vo.HardConditionVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ldchao on 2017/11/6.
 */
@Service
public class StudyAboardServiceImpl implements StudyAboardService{

    @Autowired
    GradeCategoryDao gradeCategoryDao;

    @Autowired
    HardConditionDao hardConditionDao;

    @Override
    public List<String> getAllCountry() {
        return gradeCategoryDao.getDistinctCountry();
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

        Map<String,List<HardConditionVO>> result=new TreeMap<String, List<HardConditionVO>>();
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
        HardCondition hardCondition=hardConditionVO.getModel();
        hardConditionDao.saveAndFlush(hardCondition);
        hardConditionVO.setId(hardCondition.getId());
    }

    @Override
    public String changeHardCondition(HardConditionVO hardConditionVO) {
        HardCondition hardCondition=hardConditionVO.getModel();
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
}
