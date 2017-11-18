package service;

import vo.ProfessionCategoryVO;
import vo.SchoolPictureVO;
import vo.SchoolRankingVO;
import vo.SchoolVO;

import java.util.List;

/**
 * Created by ldchao on 2017/11/17.
 */
public interface SchoolService {

    public List<ProfessionCategoryVO> getProfessionCategory(String country);

    public List<SchoolRankingVO> getAllSchoolRankingByPid(Integer pid);

    public void addSchoolRanking(SchoolRankingVO schoolRankingVO);

    public void updateSchoolRanking(SchoolRankingVO schoolRankingVO);

    public String deleteSchoolRanking(Integer id);

    public SchoolVO getSchoolBySid(Integer sid,Integer flag);

    public List<SchoolVO> getSchools(Integer flag);

    public void addSchool(SchoolVO schoolVO);

    public void updateSchool(SchoolVO schoolVO);

    public String deleteSchool(Integer sid);

    public List<SchoolPictureVO> getSchoolPicture(Integer sid);

    public void addSchoolPicture(SchoolPictureVO schoolPictureVO);

    public String deleteSchoolPicture(Integer id);

}
