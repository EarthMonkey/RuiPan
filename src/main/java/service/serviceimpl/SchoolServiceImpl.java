package service.serviceimpl;

import dao.ProfessionCategoryDao;
import dao.SchoolDao;
import dao.SchoolPictureDao;
import dao.SchoolRankingDao;
import model.ProfessionCategory;
import model.School;
import model.SchoolPicture;
import model.SchoolRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SchoolService;
import vo.ProfessionCategoryVO;
import vo.SchoolPictureVO;
import vo.SchoolRankingVO;
import vo.SchoolVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldchao on 2017/11/17.
 */
@Service
public class SchoolServiceImpl implements SchoolService{

    @Autowired
    ProfessionCategoryDao professionCategoryDao;

    @Autowired
    SchoolRankingDao schoolRankingDao;

    @Autowired
    SchoolDao schoolDao;

    @Autowired
    SchoolPictureDao schoolPictureDao;

    @Override
    public List<ProfessionCategoryVO> getProfessionCategory(String country) {
        List<ProfessionCategory> professionCategories=professionCategoryDao.findAllByCountryOrderByCategory(country);
        List<ProfessionCategoryVO> result=new ArrayList<ProfessionCategoryVO>();
        for (ProfessionCategory professionCategory:professionCategories) {
            ProfessionCategoryVO professionCategoryVO=new ProfessionCategoryVO();
            professionCategoryVO.update(professionCategory);
            result.add(professionCategoryVO);
        }
        return result;
    }

    @Override
    public List<SchoolRankingVO> getAllSchoolRankingByPid(Integer pid) {
        List<SchoolRankingVO> result=new ArrayList<SchoolRankingVO>();
        List<SchoolRanking> schoolRankings=schoolRankingDao.findAllByPidOrderByRankingAsc(pid);
        for (SchoolRanking schoolRanking:schoolRankings) {
            SchoolRankingVO schoolRankingVO=new SchoolRankingVO();
            schoolRankingVO.update(schoolRanking);
            result.add(schoolRankingVO);
        }
        return result;
    }

    @Override
    public void addSchoolRanking(SchoolRankingVO schoolRankingVO) {
        SchoolRanking schoolRanking=schoolRankingVO.toEntity();
        schoolRankingDao.saveAndFlush(schoolRanking);
        schoolRankingVO.update(schoolRanking);
        SchoolVO schoolVO=new SchoolVO();
        schoolVO.update(schoolDao.findOne(schoolRanking.getSid()));
        schoolRankingVO.setSchoolBySid(schoolVO);
    }

    @Override
    public void updateSchoolRanking(SchoolRankingVO schoolRankingVO) {
        SchoolRanking schoolRanking=schoolRankingVO.toEntity();
        schoolRankingDao.saveAndFlush(schoolRanking);
        schoolRankingVO.update(schoolRanking);
        SchoolVO schoolVO=new SchoolVO();
        schoolVO.update(schoolDao.findOne(schoolRanking.getSid()));
        schoolRankingVO.setSchoolBySid(schoolVO);
    }

    @Override
    public String deleteSchoolRanking(Integer id) {
        if(schoolRankingDao.exists(id)){
            schoolRankingDao.delete(id);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public SchoolVO getSchoolBySid(Integer sid,Integer flag) {
        School school=schoolDao.findDistinctBySidAndFlag(sid,flag);
        SchoolVO schoolVO=new SchoolVO();
        schoolVO.update(school);
        return schoolVO;
    }

    @Override
    public List<SchoolVO> getSchools(Integer flag) {
        List<School> schools=schoolDao.findAllByFlag(flag);
        List<SchoolVO> schoolVOS=new ArrayList<SchoolVO>();
        for (School school:schools) {
            SchoolVO schoolVO=new SchoolVO();
            schoolVO.update(school);
            schoolVOS.add(schoolVO);
        }
        return schoolVOS;
    }

    @Override
    public void addSchool(SchoolVO schoolVO) {
        School school=schoolVO.toEntity();
        schoolDao.saveAndFlush(school);
        schoolVO.setSid(school.getSid());
    }

    @Override
    public void updateSchool(SchoolVO schoolVO) {
        School school=schoolVO.toEntity();
        schoolDao.saveAndFlush(school);
    }

    @Override
    public String deleteSchool(Integer sid) {
        if(schoolDao.exists(sid)){
            schoolDao.delete(sid);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public List<SchoolPictureVO> getSchoolPicture(Integer sid) {
        List<SchoolPicture> schoolPictures=schoolPictureDao.findAllBySid(sid);
        List<SchoolPictureVO> schoolPictureVOS=new ArrayList<SchoolPictureVO>();
        for (SchoolPicture schoolPicture:schoolPictures) {
            SchoolPictureVO schoolPictureVO=new SchoolPictureVO();
            schoolPictureVO.update(schoolPicture);
            schoolPictureVOS.add(schoolPictureVO);
        }
        return schoolPictureVOS;
    }

    @Override
    public void addSchoolPicture(SchoolPictureVO schoolPictureVO) {
        SchoolPicture schoolPicture=schoolPictureVO.toEntity();
        schoolPictureDao.saveAndFlush(schoolPicture);
        schoolPictureVO.setId(schoolPicture.getId());
    }

    @Override
    public String deleteSchoolPicture(Integer id) {
        if(schoolPictureDao.exists(id)){
            schoolPictureDao.delete(id);
            return "success";
        }
        return "not_exist";
    }
}
