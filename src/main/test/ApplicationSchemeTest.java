import constant.StatesConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import service.ApplicationSchemeService;
import vo.ApplicationSchemeVO;
import vo.RecommendApplicationScheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class ApplicationSchemeTest {

    @Autowired
    ApplicationSchemeService applicationSchemeService;

    @Test
    public void getApplicationSchemeByGidTest(){
//        public Map<String, List<ApplicationSchemeVO>> getApplicationSchemeByGid(Integer gid);
        Map<String, List<ApplicationSchemeVO>> result=applicationSchemeService.getApplicationSchemeByGid(7);
        for (Map.Entry<String,List<ApplicationSchemeVO>> entry:result.entrySet()) {
            System.out.println(entry.getKey());
            for (ApplicationSchemeVO applicationSchemeVO:entry.getValue()) {
                System.out.println(applicationSchemeVO.getUpdateAt());
            }
        }

    }

    @Test
    public void getApplicationSchemeByIdTest(){
//        public ApplicationSchemeVO getApplicationSchemeById(Integer id);
        ApplicationSchemeVO applicationSchemeVO=applicationSchemeService.getApplicationSchemeById(1);
        System.out.println(applicationSchemeVO.getId());

    }

    @Test
    public void addApplicationSchemeTest(){
//        public void addApplicationScheme(ApplicationSchemeVO applicationSchemeVO);


        String subdivisionGrades[] ={"高一","高二","高三","大一","大二","大三","大四","研一"};
        for (int i = 0; i < subdivisionGrades.length; i++) {
            for (int j = 1; j < 4; j++) {
                ApplicationSchemeVO applicationSchemeVO = new ApplicationSchemeVO();
                applicationSchemeVO.setGid(7);
                applicationSchemeVO.setSubdivisionGrade(subdivisionGrades[i]);
                applicationSchemeVO.setTitle("方案"+j);
                applicationSchemeVO.setSynopsis("这是方案的简介");
                applicationSchemeVO.setTextPath("update/text/test.html");
                applicationSchemeVO.setFlag(StatesConstant.PUBLISHED);
                applicationSchemeService.addApplicationScheme(applicationSchemeVO);
            }
        }

    }

    @Test
    public void updateApplicationSchemeTest(){
//        public void updateApplicationScheme(ApplicationSchemeVO applicationSchemeVO);
        Map<String, List<ApplicationSchemeVO>> result=applicationSchemeService.getApplicationSchemeByGid(7);
        for (Map.Entry<String,List<ApplicationSchemeVO>> entry:result.entrySet()) {
            System.out.println(entry.getKey());
            for (ApplicationSchemeVO applicationSchemeVO:entry.getValue()) {
                applicationSchemeVO.setTextPath("upload/text/test.html");
                applicationSchemeService.updateApplicationScheme(applicationSchemeVO);
            }
        }

    }

    @Test
    public void deleteApplicationScheme(){
//        public String deleteApplicationScheme(Integer id);
    }

    @Test
    public void getApplicationSchemeDraftByGid(){
//        public List<ApplicationSchemeVO> getApplicationSchemeDraftByGid(Integer gid);
        List<ApplicationSchemeVO> applicationSchemeVOS=applicationSchemeService.getApplicationSchemeDraftByGid(7);
        for (ApplicationSchemeVO applicationSchemeVO:applicationSchemeVOS) {
            System.out.println(applicationSchemeVO.getId());
        }
    }
    @Test
    public void getRecommendApplicationScheme(){
//        public List<RecommendApplicationScheme> getRecommendApplicationScheme();
    }
    @Test
    public void getHistoryRecommendApplicationScheme(){
//        public List<RecommendApplicationScheme> getHistoryRecommendApplicationScheme();
    }

    @Test
    public void addRecommendApplicationScheme(){

        for (int i = 9; i <10  ; i++) {
            RecommendApplicationScheme recommendApplicationScheme=new RecommendApplicationScheme();
            recommendApplicationScheme.setRid(i);
            recommendApplicationScheme.setPicturePath("upload/image/test.jpg");
            applicationSchemeService.addRecommendApplicationScheme(recommendApplicationScheme);
        }
//        public void addRecommendApplicationScheme(RecommendApplicationScheme recommendApplicationScheme);
    }

    @Test
    public void updateRecommendApplicationScheme(){
//        public void updateRecommendApplicationScheme(RecommendApplicationScheme recommendApplicationScheme);
        RecommendApplicationScheme recommendApplicationScheme=new RecommendApplicationScheme();
        recommendApplicationScheme.setId(1);
        recommendApplicationScheme.setRid(5);
        recommendApplicationScheme.setPicturePath("upload/image/test.jpg");
        applicationSchemeService.updateRecommendApplicationScheme(recommendApplicationScheme);
    }

    @Test
    public void deleteRecommendApplicationScheme(){
//        public String deleteRecommendApplicationScheme(Integer id);
    }






}
