package service;

import vo.CooperativeCategoryVO;

import java.util.List;

/**
 * Created by ldchao on 2017/11/19.
 */
public interface CooperativeEducationService {

    public List<CooperativeCategoryVO> getSubclassificationByCategory(String category);

    public void addCooperativeCategory(CooperativeCategoryVO cooperativeCategoryVO);

    public void updateCooperativeCategory(CooperativeCategoryVO cooperativeCategoryVO);

    public String deleteCooperativeCategory(Integer id);
}
