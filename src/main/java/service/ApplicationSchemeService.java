package service;

import org.springframework.transaction.annotation.Transactional;
import vo.ApplicationSchemeVO;
import vo.RecommendApplicationScheme;

import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/9.
 */
public interface ApplicationSchemeService {

    public Map<String, List<ApplicationSchemeVO>> getApplicationSchemeByGid(Integer gid);

    public ApplicationSchemeVO getApplicationSchemeById(Integer id);

    public void addApplicationScheme(ApplicationSchemeVO applicationSchemeVO);

    public void updateApplicationScheme(ApplicationSchemeVO applicationSchemeVO);

    public String deleteApplicationScheme(Integer id);

    public List<ApplicationSchemeVO> getApplicationSchemeDraftByGid(Integer gid);

    public List<RecommendApplicationScheme> getRecommendApplicationScheme();

    public List<RecommendApplicationScheme> getHistoryRecommendApplicationScheme();

    @Transactional
    public void addRecommendApplicationScheme(RecommendApplicationScheme recommendApplicationScheme);

    @Transactional
    public void updateRecommendApplicationScheme(RecommendApplicationScheme recommendApplicationScheme);

    public String deleteRecommendApplicationScheme(Integer id);


}
