package service.serviceimpl;

import constant.GlobalRecommedationCategory;
import constant.StatesConstant;
import dao.ComplexSituationDao;
import dao.GlobalRecommendationDao;
import dao.SuccessfulCaseDao;
import model.GlobalRecommendation;
import model.SuccessfulCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SuccessfulCaseService;
import util.FileManager;
import vo.RecommendSuccessfulCase;
import vo.SuccessfulCaseVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SuccessfulCaseServiceImpl implements SuccessfulCaseService{

    @Autowired
    SuccessfulCaseDao successfulCaseDao;

    @Autowired
    GlobalRecommendationDao globalRecommendationDao;

    @Autowired
    ComplexSituationDao complexSituationDao;


    @Override
    public List<SuccessfulCaseVO> getSuccessfulCaseByPid(Integer pid, Integer flag) {
        List<SuccessfulCase> successfulCases=successfulCaseDao.findAllByPidAndFlagOrderByEnrollmentTimeDesc(pid,flag);
        return entitysToVOs(successfulCases);
    }

    @Override
    public List<SuccessfulCaseVO> getSuccessfulCaseBySid(Integer sid, Integer flag) {
        List<SuccessfulCase> successfulCases=successfulCaseDao.findAllBySidAndFlagOrderByEnrollmentTimeDesc(sid,flag);
        return entitysToVOs(successfulCases);
    }

    @Override
    public List<SuccessfulCaseVO> getSuccessfulCaseByCid(Integer cid, Integer flag) {
        List<SuccessfulCase> successfulCases=successfulCaseDao.findAllByCidAndFlagOrderByEnrollmentTimeDesc(cid,flag);
        return entitysToVOs(successfulCases);
    }

    private List<SuccessfulCaseVO> entitysToVOs(List<SuccessfulCase> successfulCases){
        List<SuccessfulCaseVO> result=new ArrayList<SuccessfulCaseVO>();
        for (SuccessfulCase successfulCase:successfulCases) {
            SuccessfulCaseVO successfulCaseVO=new SuccessfulCaseVO();
            successfulCaseVO.update(successfulCase);
            successfulCaseVO.setSchoolName(successfulCase.getSchoolBySid().getCollegeName());
            successfulCaseVO.setConsultantName(successfulCase.getConsultantByCid().getName());
            result.add(successfulCaseVO);
        }
        return result;
    }

    @Override
    public SuccessfulCaseVO getSuccessfulCase(Integer id) {
        SuccessfulCase successfulCase=successfulCaseDao.findOne(id);
        SuccessfulCaseVO successfulCaseVO=new SuccessfulCaseVO();
        successfulCaseVO.update(successfulCase);
        successfulCaseVO.setSchoolName(successfulCase.getSchoolBySid().getCollegeName());
        successfulCaseVO.setConsultantName(successfulCase.getConsultantByCid().getName());
        return successfulCaseVO;
    }

    @Override
    public Map<String, List<SuccessfulCaseVO>> getSuccessfulCaseGroupByCountry(Integer limit) {
        return complexSituationDao.getSuccessfulCaseByCountry(limit);
    }

    @Override
    public void addSuccessfulCase(SuccessfulCaseVO successfulCaseVO) {
        SuccessfulCase successfulCase=successfulCaseVO.toEntity();
        successfulCaseDao.saveAndFlush(successfulCase);
        successfulCaseVO.setId(successfulCase.getId());
    }

    @Override
    public void updateSuccessfulCase(SuccessfulCaseVO successfulCaseVO) {
        SuccessfulCase successfulCase=successfulCaseVO.toEntity();
        successfulCaseDao.saveAndFlush(successfulCase);
    }

    @Override
    public String deleteSuccessfulCase(Integer id) {
        if(successfulCaseDao.exists(id)){
            SuccessfulCase successfulCase=successfulCaseDao.findOne(id);
            successfulCaseDao.delete(id);
            FileManager.deleteText(successfulCase.getTextPath());
            return "success";
        }
        return "not_exist";
    }

    @Override
    public List<RecommendSuccessfulCase> getRecommendSuccessfulCase(Integer flag) {
        List<RecommendSuccessfulCase> recommendSuccessfulCases=new ArrayList<RecommendSuccessfulCase>();
        List<GlobalRecommendation> globalRecommendations=globalRecommendationDao.findAllByCategoryAndFlagOrderByRecommendAtDesc(GlobalRecommedationCategory.CASE,flag);
        for (GlobalRecommendation globalRecommendation:globalRecommendations) {
            RecommendSuccessfulCase recommendSuccessfulCase=new RecommendSuccessfulCase();
            recommendSuccessfulCase.update(globalRecommendation);
            recommendSuccessfulCase.setSuccessfulCaseVO(getSuccessfulCase(globalRecommendation.getRid()));
            recommendSuccessfulCases.add(recommendSuccessfulCase);
        }
        return recommendSuccessfulCases;
    }

    @Override
    public void addRecommendSuccessfulCase(RecommendSuccessfulCase recommendSuccessfulCase) {
        GlobalRecommendation globalRecommendation=recommendSuccessfulCase.toEntity();
        if(globalRecommendationDao.countByCategoryEqualsAndFlagEquals(GlobalRecommedationCategory.CASE, StatesConstant.RECOMMEND)==4){
            GlobalRecommendation earliestGlobalRecommendation=globalRecommendationDao.findFirstByCategoryAndFlagOrderByRecommendAtAsc(
                    GlobalRecommedationCategory.CASE,StatesConstant.RECOMMEND);
            earliestGlobalRecommendation.setFlag(StatesConstant.HISTORY_RECOMMEND);
            globalRecommendationDao.saveAndFlush(earliestGlobalRecommendation);
        }
        globalRecommendationDao.saveAndFlush(globalRecommendation);
        recommendSuccessfulCase.setId(globalRecommendation.getId());
        recommendSuccessfulCase.setSuccessfulCaseVO(getSuccessfulCase(globalRecommendation.getRid()));
    }

    @Override
    public void updateRecommendSuccessfulCase(RecommendSuccessfulCase recommendSuccessfulCase) {
        GlobalRecommendation globalRecommendation=recommendSuccessfulCase.toEntity();
        globalRecommendationDao.saveAndFlush(globalRecommendation);
        recommendSuccessfulCase.setSuccessfulCaseVO(getSuccessfulCase(globalRecommendation.getRid()));

        if(globalRecommendationDao.countByCategoryEqualsAndFlagEquals(GlobalRecommedationCategory.CASE,StatesConstant.RECOMMEND)==5){
            GlobalRecommendation earliestGlobalRecommendation=globalRecommendationDao.findFirstByCategoryAndFlagOrderByRecommendAtAsc(
                    GlobalRecommedationCategory.CASE,StatesConstant.RECOMMEND);
            earliestGlobalRecommendation.setFlag(StatesConstant.HISTORY_RECOMMEND);
            globalRecommendationDao.saveAndFlush(earliestGlobalRecommendation);
        }
    }

    @Override
    public String deleteRecommendSuccessfulCase(Integer id) {
        if(globalRecommendationDao.exists(id)){
            globalRecommendationDao.delete(id);
            return "success";
        }else{
            return "not_exist";
        }
    }
}
