package dao;

import model.GlobalConfigure;
import vo.SuccessfulCaseVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/17.
 */
public interface ComplexSituationDao {

    public Map<String,List<SuccessfulCaseVO>> getSuccessfulCaseByCountry(Integer limit);

    public Map<String, GlobalConfigure> getGlobalConfigures(String start);
}
