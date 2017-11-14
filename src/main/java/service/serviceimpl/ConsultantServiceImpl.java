package service.serviceimpl;

import constant.ConsultantBusinesType;
import dao.ConsultantBusinessDao;
import dao.ConsultantDao;
import model.Consultant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ConsultantService;
import vo.ConsultantVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldchao on 2017/11/14.
 */
@Service
public class ConsultantServiceImpl implements ConsultantService{

    @Autowired
    ConsultantBusinessDao consultantBusinessDao;

    @Autowired
    ConsultantDao consultantDao;

    @Override
    public List<ConsultantVO> getConsultantByGid(Integer gid) {

        List<Integer> cids=consultantBusinessDao.getConsultantIdByGid(ConsultantBusinesType.ClassifyByGrade,gid);
        List<Consultant> consultants=consultantDao.getAllByCids(cids);
        List<ConsultantVO> consultantVOS=new ArrayList<ConsultantVO>();
        for (Consultant consultant:consultants) {
            ConsultantVO consultantVO=new ConsultantVO();
            consultantVO.update(consultant);
            consultantVOS.add(consultantVO);
        }
        return consultantVOS;
    }
}
