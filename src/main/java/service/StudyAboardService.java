package service;

import java.util.List;

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

}
