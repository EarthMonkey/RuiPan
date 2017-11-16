import model.HardCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import service.StudyAboardService;
import vo.HardConditionVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class StudyAboardTest {
    @Autowired
    StudyAboardService studyAboardService;

    @Test
    public void getAllCountry(){
        List<String> countrys=studyAboardService.getAllCountry();
        for (String s:countrys) {
            System.out.println(s);
        }
    }

    @Test
    public void getGid() {
        System.out.println(studyAboardService.getGidByCountryAndGrade("美国","本科生"));
    }

    @Test
    public void addCountry(){
        System.out.println(studyAboardService.addOneCountry("美国"));
        System.out.println(studyAboardService.addOneCountry("美国"));
    }

    @Test
    public void deleteCountry(){
        System.out.println(studyAboardService.deleteOneCountry("美国"));
        System.out.println(studyAboardService.deleteOneCountry("美国"));
    }

    @Test
    public void addHardCondition(){
        String[] ranks={"10","30","50"};
        String[] subjects={"GRE","GMAT","GPA","TOEFL"};
        String[] scores={"10.0","8.0","4.0"};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                HardConditionVO hardConditionVO=new HardConditionVO();
                hardConditionVO.setGid(7);
                hardConditionVO.setRank(ranks[i]);
                hardConditionVO.setSubject(subjects[j]);
                hardConditionVO.setScore(scores[i]);
                studyAboardService.addHardCondition(hardConditionVO);
            }
        }
    }

    @Test
    public void getHardCondition(){
        Map<String, List<HardConditionVO>> result=studyAboardService.getHardConditionByGid(7);
        for (Map.Entry<String,List<HardConditionVO>> entry:result.entrySet()) {
            System.out.println(entry.getKey());
            for (HardConditionVO harConditionVO:entry.getValue()) {
                System.out.println(harConditionVO.getSubject());
            }
        }
    }

    @Test
    public void updateHardConditon(){
        HardConditionVO hardConditionVO=new HardConditionVO();
        hardConditionVO.setId(1);
        hardConditionVO.setGid(7);
        hardConditionVO.setRank("10");
        hardConditionVO.setSubject("GRE");
        hardConditionVO.setScore("10.1");
        studyAboardService.changeHardCondition(hardConditionVO);
    }

    @Test
    public void addHardConditon(){
        HardConditionVO hardConditionVO=new HardConditionVO();
        hardConditionVO.setGid(8);
        hardConditionVO.setRank("10");
        hardConditionVO.setSubject("GRE");
        hardConditionVO.setScore("10.1");
        studyAboardService.addHardCondition(hardConditionVO);
        System.out.println(hardConditionVO.getUpdateAt());
    }

}
