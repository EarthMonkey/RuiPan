package service;

import vo.HardConditionVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/6.
 */
public interface StudyAboardService {

    //获取留学服务中所有国家
    public List<String> getAllCountry();

    //在年级分类表中增加一个国家（对应补齐三条记录）
    public String addOneCountry(String country);

    //在年级分类表中删除一个国家
    public String deleteOneCountry(String country);

    //通过gid获取不同等级下的硬性条件
    public Map<String,List<HardConditionVO>> getHardConditionByGid(Integer gid);

    //在gid下增加一条硬性条件
    public Integer addHardCondition(HardConditionVO hardConditionVO);

    //更新一条硬性条件
    public String changeHardCondition(HardConditionVO hardConditionVO);

    //删除一条硬性条件
    public String deleteHardCondition(Integer id);

}
