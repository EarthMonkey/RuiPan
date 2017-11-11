package service.serviceimpl;

import dao.ApplicationSchemeDao;
import model.ApplicationScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ApplicationSchemeService;
import vo.ApplicationSchemeVO;
import vo.RecommendApplicationScheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ldchao on 2017/11/9.
 */
@Service
public class ApplicationSchemeServiceImpl implements ApplicationSchemeService{

    @Autowired
    ApplicationSchemeDao applicationSchemeDao;

    @Override
    public Map<String, List<ApplicationSchemeVO>> getApplicationSchemeByGid(Integer gid) {
        Map<String,List<ApplicationSchemeVO>> result=new TreeMap<String, List<ApplicationSchemeVO>>();
        List<ApplicationScheme> applicationSchemes=applicationSchemeDao.findAllByGidAndFlagOrderBySubdivisionGradeAscUpdateAtDesc(gid,1);
        for (ApplicationScheme applicationScheme:applicationSchemes) {
            ApplicationSchemeVO applicationSchemeVO=new ApplicationSchemeVO();
            applicationSchemeVO.update(applicationScheme);
            String subdivisionGrade=applicationScheme.getSubdivisionGrade();
            if(result.containsKey(subdivisionGrade)){
                result.get(subdivisionGrade).add(applicationSchemeVO);
            }else{
                List<ApplicationSchemeVO> applicationSchemeVOS=new ArrayList<ApplicationSchemeVO>();
                applicationSchemeVOS.add(applicationSchemeVO);
                result.put(subdivisionGrade,applicationSchemeVOS);
            }
        }
        return result;
    }

    @Override
    public ApplicationSchemeVO getApplicationSchemeById(Integer id) {
        return null;
    }

    @Override
    public void addApplicationScheme(ApplicationSchemeVO applicationSchemeVO) {

    }

    @Override
    public void updateApplicationScheme(ApplicationSchemeVO applicationSchemeVO) {

    }

    @Override
    public String deleteApplicationScheme(Integer id) {
        return null;
    }

    @Override
    public List<ApplicationSchemeVO> getApplicationSchemeDraftByGid(Integer gid) {
        return null;
    }

    @Override
    public List<RecommendApplicationScheme> getRecommendApplicationScheme() {
        return null;
    }

    @Override
    public List<RecommendApplicationScheme> getHistoryRecommendApplicationScheme() {
        return null;
    }

    @Override
    public void addRecommendApplicationScheme(RecommendApplicationScheme recommendApplicationScheme) {

    }

    @Override
    public void updateRecommendApplicationScheme(RecommendApplicationScheme recommendApplicationScheme) {

    }

    @Override
    public String deleteRecommendApplicationScheme(Integer id) {
        return null;
    }
}
