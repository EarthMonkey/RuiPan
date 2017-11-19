package service;

import model.CooperativeScheme;
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

    public List<CooperativeScheme> getCooperativeSchemeByCcid(Integer ccid,Integer flag);

    public CooperativeScheme addCooperativeScheme(CooperativeScheme cooperativeScheme);

    public CooperativeScheme updateCooperativeScheme(CooperativeScheme cooperativeScheme);

    public String deleteCooperativeScheme(Integer id);
}
