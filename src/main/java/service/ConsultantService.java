package service;

import vo.ConsultantVO;

import java.util.List;

/**
 * Created by ldchao on 2017/11/14.
 */
public interface ConsultantService {

    //根据gid获取所有顾问
    public List<ConsultantVO> getConsultantByGid(Integer gid);
}
