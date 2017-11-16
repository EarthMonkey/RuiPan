package service;

import vo.ApplicationElementVO;
import vo.HardConditionVO;
import vo.QuestionVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/6.
 */
public interface StudyAboardService {

    //获取留学服务中所有国家
    public List<String> getAllCountry();

    //根据国家和年级获取分类ID
    public Integer getGidByCountryAndGrade(String country,String grade);

    //在年级分类表中增加一个国家（对应补齐三条记录）
    public String addOneCountry(String country);

    //在年级分类表中删除一个国家
    public String deleteOneCountry(String country);

    //通过gid获取不同等级下的硬性条件
    public Map<String,List<HardConditionVO>> getHardConditionByGid(Integer gid);

    //在gid下增加一条硬性条件
    public void addHardCondition(HardConditionVO hardConditionVO);

    //更新一条硬性条件
    public String changeHardCondition(HardConditionVO hardConditionVO);

    //删除一条硬性条件
    public String deleteHardCondition(Integer hardConditionVO);

    //通过gid和发布状态所有申请要素
    public List<ApplicationElementVO> getApplicationElementByGid(Integer gid,Integer flag);

    //通过id获取一条申请要素
    public ApplicationElementVO getApplicationElementById(Integer id);

    //增加一条申请要素
    public void addApplicationElement(ApplicationElementVO applicationElementVO);

    //通过id编辑一条申请要素
    public void updateApplicationElement(ApplicationElementVO applicationElementVO);

    //通过id删除一条申请要素
    public String deleteApplicationElement(Integer id);

    //根据gid获取所有可见的问题列表
    public List<QuestionVO> getPublishQuestionsByGid(Integer gid);

    //根据gid获取所有问题列表
    public List<QuestionVO> getQuestionsByGid(Integer gid);

    //根据gid增加一条问答
    public void addQuestion(QuestionVO questionVO);

    //根据id编辑一条问答
    public void updateQuestion(QuestionVO questionVO);

    //根据id删除一条问答
    public String deleteQuestion(Integer id);
}
