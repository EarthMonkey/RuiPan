package service.serviceimpl;

import dao.CooperativeCategoryDao;
import model.CooperativeCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CooperativeEducationService;
import vo.CooperativeCategoryVO;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldchao on 2017/11/19.
 */
@Service
public class CooperativeEducationServiceImpl implements CooperativeEducationService {

    @Autowired
    CooperativeCategoryDao cooperativeCategoryDao;

    @Override
    public List<CooperativeCategoryVO> getSubclassificationByCategory(String category) {
        List<CooperativeCategory> cooperativeCategories=cooperativeCategoryDao.findAllByCategory(category);
        List<CooperativeCategoryVO> cooperativeCategoryVOS=new ArrayList<CooperativeCategoryVO>();
        for (CooperativeCategory cooperativeCategory:cooperativeCategories) {
            CooperativeCategoryVO cooperativeCategoryVO=new CooperativeCategoryVO();
            cooperativeCategoryVO.update(cooperativeCategory);
            cooperativeCategoryVOS.add(cooperativeCategoryVO);
        }
        return cooperativeCategoryVOS;
    }

    @Override
    public void addCooperativeCategory(CooperativeCategoryVO cooperativeCategoryVO) {
        CooperativeCategory cooperativeCategory=cooperativeCategoryVO.toEntity();
        cooperativeCategoryDao.saveAndFlush(cooperativeCategory);
        cooperativeCategoryVO.setId(cooperativeCategory.getId());
    }

    @Override
    public void updateCooperativeCategory(CooperativeCategoryVO cooperativeCategoryVO) {
        CooperativeCategory cooperativeCategory=cooperativeCategoryVO.toEntity();
        cooperativeCategoryDao.saveAndFlush(cooperativeCategory);
    }

    @Override
    public String deleteCooperativeCategory(Integer id) {
        if(cooperativeCategoryDao.exists(id)){
            cooperativeCategoryDao.delete(id);
            return "success";
        }
        return "not_exist";
    }
}
