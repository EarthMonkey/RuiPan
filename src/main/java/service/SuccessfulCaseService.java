package service;

import org.springframework.transaction.annotation.Transactional;
import vo.RecommendSuccessfulCase;
import vo.SuccessfulCaseVO;

import java.util.List;
import java.util.Map;

public interface SuccessfulCaseService {


    public List<SuccessfulCaseVO> getSuccessfulCaseByPid(Integer pid,Integer flag);

    public List<SuccessfulCaseVO> getSuccessfulCaseBySid(Integer sid,Integer flag);

    public List<SuccessfulCaseVO> getSuccessfulCaseByCid(Integer cid,Integer flag);

    public SuccessfulCaseVO getSuccessfulCase(Integer id);

    public Map<String,List<SuccessfulCaseVO>> getSuccessfulCaseGroupByCountry(Integer limit);

    public void addSuccessfulCase(SuccessfulCaseVO successfulCaseVO);

    public void updateSuccessfulCase(SuccessfulCaseVO successfulCaseVO);

    public String deleteSuccessfulCase(Integer id);

    public List<RecommendSuccessfulCase> getRecommendSuccessfulCase(Integer flag);

    @Transactional
    public void addRecommendSuccessfulCase(RecommendSuccessfulCase recommendSuccessfulCase);

    @Transactional
    public void updateRecommendSuccessfulCase(RecommendSuccessfulCase recommendSuccessfulCase);

    public String deleteRecommendSuccessfulCase(Integer id);
}
