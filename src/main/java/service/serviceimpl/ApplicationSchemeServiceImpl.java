package service.serviceimpl;

import constant.GlobalRecommedationCategory;
import dao.ApplicationSchemeDao;
import dao.GlobalRecommendationDao;
import model.ApplicationScheme;
import model.GlobalRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ApplicationSchemeService;
import constant.StatesConstant;
import util.FileManager;
import vo.ApplicationSchemeVO;
import vo.RecommendApplicationScheme;

import java.util.*;

/**
 * Created by ldchao on 2017/11/9.
 */
@Service
public class ApplicationSchemeServiceImpl implements ApplicationSchemeService{

    @Autowired
    ApplicationSchemeDao applicationSchemeDao;

    @Autowired
    GlobalRecommendationDao globalRecommendationDao;

    @Override
    public Map<String, List<ApplicationSchemeVO>> getApplicationSchemeByGid(Integer gid) {
        Map<String,List<ApplicationSchemeVO>> result=new LinkedHashMap<String, List<ApplicationSchemeVO>>();
        List<ApplicationScheme> applicationSchemes=applicationSchemeDao.findAllByGidAndFlagOrderBySubdivisionGradeAscUpdateAtDesc(gid, StatesConstant.PUBLISHED);
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
        ApplicationScheme applicationScheme=applicationSchemeDao.findOne(id);
        ApplicationSchemeVO applicationSchemeVO=new ApplicationSchemeVO();
        applicationSchemeVO.update(applicationScheme);
        return applicationSchemeVO;
    }

    @Override
    public void addApplicationScheme(ApplicationSchemeVO applicationSchemeVO) {
        ApplicationScheme applicationScheme=applicationSchemeVO.toEntity();
        applicationSchemeDao.saveAndFlush(applicationScheme);
        applicationSchemeVO.setId(applicationScheme.getId());
    }

    @Override
    public void updateApplicationScheme(ApplicationSchemeVO applicationSchemeVO) {
        ApplicationScheme applicationScheme=applicationSchemeVO.toEntity();
        applicationSchemeDao.saveAndFlush(applicationScheme);
    }

    @Override
    public String deleteApplicationScheme(Integer id) {
        if(applicationSchemeDao.exists(id)){
            ApplicationScheme applicationScheme=applicationSchemeDao.findOne(id);
            applicationSchemeDao.delete(id);
            FileManager.deleteText(applicationScheme.getTextPath());
            return "success";
        }else{
            return "not_exist";
        }
    }

    @Override
    public List<ApplicationSchemeVO> getApplicationSchemeDraftByGid(Integer gid) {
        List<ApplicationSchemeVO> applicationSchemeVOS=new ArrayList<ApplicationSchemeVO>();
        List<ApplicationScheme> applicationSchemes= applicationSchemeDao.findAllByGidAndFlagOrderByUpdateAtDesc(gid,StatesConstant.DRAFT);
        for (ApplicationScheme applicationScheme:applicationSchemes) {
            ApplicationSchemeVO applicationSchemeVO=new ApplicationSchemeVO();
            applicationSchemeVO.update(applicationScheme);
            applicationSchemeVOS.add(applicationSchemeVO);
        }
        return applicationSchemeVOS;
    }

    @Override
    public List<RecommendApplicationScheme> getRecommendApplicationScheme() {
        return getRecommendApplicationSchemeByFlag(StatesConstant.RECOMMEND);
    }

    @Override
    public List<RecommendApplicationScheme> getHistoryRecommendApplicationScheme() {
        return getRecommendApplicationSchemeByFlag(StatesConstant.HISTORY_RECOMMEND);
    }

    private List<RecommendApplicationScheme> getRecommendApplicationSchemeByFlag(Integer flag){
        List<RecommendApplicationScheme> recommendApplicationSchemes=new ArrayList<RecommendApplicationScheme>();
        List<GlobalRecommendation> globalRecommendations=globalRecommendationDao.findAllByCategoryAndFlagOrderByRecommendAtDesc(GlobalRecommedationCategory.APPLICATION_SCHEME,flag);
        for (GlobalRecommendation globalRecommendation:globalRecommendations) {
            RecommendApplicationScheme recommendApplicationScheme=new RecommendApplicationScheme();
            recommendApplicationScheme.update(globalRecommendation);
            recommendApplicationScheme.setApplicationSchemeVO(getApplicationSchemeById(globalRecommendation.getRid()));
            recommendApplicationSchemes.add(recommendApplicationScheme);
        }
        return recommendApplicationSchemes;
    }

    @Override
    public void addRecommendApplicationScheme(RecommendApplicationScheme recommendApplicationScheme) {
        GlobalRecommendation globalRecommendation=recommendApplicationScheme.toEntity();
        if(globalRecommendationDao.countByCategoryEqualsAndFlagEquals(GlobalRecommedationCategory.APPLICATION_SCHEME,StatesConstant.RECOMMEND)==3){
            GlobalRecommendation earliestGlobalRecommendation=globalRecommendationDao.findFirstByCategoryAndFlagOrderByRecommendAtAsc(
                    GlobalRecommedationCategory.APPLICATION_SCHEME,StatesConstant.RECOMMEND);
            earliestGlobalRecommendation.setFlag(StatesConstant.HISTORY_RECOMMEND);
            globalRecommendationDao.saveAndFlush(earliestGlobalRecommendation);
        }
        globalRecommendationDao.saveAndFlush(globalRecommendation);
        recommendApplicationScheme.setId(globalRecommendation.getId());
        recommendApplicationScheme.setApplicationSchemeVO(getApplicationSchemeById(globalRecommendation.getRid()));
    }

    @Override
    public void updateRecommendApplicationScheme(RecommendApplicationScheme recommendApplicationScheme) {
        GlobalRecommendation globalRecommendation=recommendApplicationScheme.toEntity();
        globalRecommendationDao.saveAndFlush(globalRecommendation);
        recommendApplicationScheme.setApplicationSchemeVO(getApplicationSchemeById(globalRecommendation.getRid()));

        if(globalRecommendationDao.countByCategoryEqualsAndFlagEquals(GlobalRecommedationCategory.APPLICATION_SCHEME,StatesConstant.RECOMMEND)==4){
            GlobalRecommendation earliestGlobalRecommendation=globalRecommendationDao.findFirstByCategoryAndFlagOrderByRecommendAtAsc(
                    GlobalRecommedationCategory.APPLICATION_SCHEME,StatesConstant.RECOMMEND);
            earliestGlobalRecommendation.setFlag(StatesConstant.HISTORY_RECOMMEND);
            globalRecommendationDao.saveAndFlush(earliestGlobalRecommendation);
        }
    }

    @Override
    public String deleteRecommendApplicationScheme(Integer id) {
        if(globalRecommendationDao.exists(id)){
            GlobalRecommendation globalRecommendation=new GlobalRecommendation();
            globalRecommendationDao.delete(id);
            FileManager.deleteImage(globalRecommendation.getSlogan());
            return "success";
        }else{
            return "not_exist";
        }
    }
}
