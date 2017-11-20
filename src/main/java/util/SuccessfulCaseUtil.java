package util;

import dao.ComplexSituationDao;
import org.springframework.beans.factory.annotation.Autowired;
import vo.SuccessfulCaseVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/20.
 */
public class SuccessfulCaseUtil {
    private static SuccessfulCaseUtil ourInstance = null;
    private Map<String,List<SuccessfulCaseVO>> successfulCaseVO = null;

    @Autowired
    ComplexSituationDao complexSituationDao;

    public static SuccessfulCaseUtil getInstance() {
        if(ourInstance==null){
            synchronized (SuccessfulCaseUtil.class){
                if(ourInstance==null) {
                    ourInstance = new SuccessfulCaseUtil();
                }
            }
        }

        return ourInstance;
    }

    private SuccessfulCaseUtil() {

    }
}
