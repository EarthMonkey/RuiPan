import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import service.StudyAboardService;

import java.util.List;

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
    public void addCountry(){
        System.out.println(studyAboardService.addOneCountry("美国"));
        System.out.println(studyAboardService.addOneCountry("美国"));
    }

    @Test
    public void deleteCountry(){
        System.out.println(studyAboardService.deleteOneCountry("美国"));
        System.out.println(studyAboardService.deleteOneCountry("美国"));
    }
}
