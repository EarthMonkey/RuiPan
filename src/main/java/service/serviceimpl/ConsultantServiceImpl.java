package service.serviceimpl;

import constant.ConsultantBusinesType;
import dao.AnswerForOrderDao;
import dao.ConsultantBusinessDao;
import dao.ConsultantDao;
import dao.OrderForConsultantDao;
import model.AnswerForOrder;
import model.Consultant;
import model.ConsultantBusiness;
import model.OrderForConsultant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ConsultantService;
import util.FileManager;
import vo.ConsultantVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldchao on 2017/11/14.
 */
@Service
public class ConsultantServiceImpl implements ConsultantService {

    @Autowired
    ConsultantBusinessDao consultantBusinessDao;

    @Autowired
    ConsultantDao consultantDao;

    @Autowired
    OrderForConsultantDao orderForConsultantDao;

    @Autowired
    AnswerForOrderDao answerForOrderDao;


    @Override
    public List<ConsultantVO> getConsultant() {
        List<Consultant> consultants = consultantDao.findAll();
        List<ConsultantVO> consultantVOS = new ArrayList<ConsultantVO>();
        for (Consultant consultant : consultants) {
            ConsultantVO consultantVO = new ConsultantVO();
            consultantVO.update(consultant);
            consultantVOS.add(consultantVO);
        }
        return consultantVOS;
    }

    @Override
    public List<ConsultantVO> getConsultantByGid(Integer gid) {

        List<Integer> cids = consultantBusinessDao.getConsultantIdByBid(ConsultantBusinesType.ClassifyByGrade, gid);

        List<ConsultantVO> consultantVOS = new ArrayList<ConsultantVO>();
        if (cids != null&&cids.size()!=0) {
            List<Consultant> consultants = consultantDao.checkConsultantInCids(cids);
            for (Consultant consultant : consultants) {
                ConsultantVO consultantVO = new ConsultantVO();
                consultantVO.update(consultant);
                consultantVOS.add(consultantVO);
            }
        }
        return consultantVOS;
    }

    @Override
    public List<ConsultantVO> getConsultantByPid(Integer pid) {

        List<Integer> cids = consultantBusinessDao.getConsultantIdByBid(ConsultantBusinesType.ClassifyByProfession, pid);
        List<ConsultantVO> consultantVOS = new ArrayList<ConsultantVO>();
        if (cids != null&&cids.size()!=0) {
            List<Consultant> consultants = consultantDao.checkConsultantInCids(cids);
            for (Consultant consultant : consultants) {
                ConsultantVO consultantVO = new ConsultantVO();
                consultantVO.update(consultant);
                consultantVOS.add(consultantVO);
            }
        }
        return consultantVOS;
    }

    @Override
    public ConsultantVO getConsultantById(Integer id) {
        Consultant consultant = consultantDao.findOne(id);
        ConsultantVO consultantVO = new ConsultantVO();
        consultantVO.update(consultant);
        return consultantVO;
    }

    @Override
    public List<ConsultantVO> getRecommendConsultant() {
        List<Consultant> consultants = consultantDao.findAllByIsRecommendIgnoreCase("true");
        List<ConsultantVO> consultantVOS = new ArrayList<ConsultantVO>();
        for (Consultant consultant : consultants) {
            ConsultantVO consultantVO = new ConsultantVO();
            consultantVO.update(consultant);
            consultantVOS.add(consultantVO);
        }
        return consultantVOS;
    }

    @Override
    public OrderForConsultant addOrderForConsultant(OrderForConsultant orderForConsultant) {
        return orderForConsultantDao.saveAndFlush(orderForConsultant);
    }

    @Override
    public void addConsultant(ConsultantVO consultantVO) {
        Consultant consultant = consultantVO.toEntity();
        consultantDao.saveAndFlush(consultant);
        consultantVO.setId(consultant.getId());
    }

    @Override
    public void updateConsultant(ConsultantVO consultantVO) {
        Consultant consultantOld=consultantDao.findOne(consultantVO.getId());
        Consultant consultant = consultantVO.toEntity();
        consultant.setIsRecommend(consultantOld.getIsRecommend());
        consultantDao.saveAndFlush(consultant);
    }

    @Override
    public String deleteConsultant(Integer id) {
        if (consultantDao.exists(id)) {
            Consultant consultant = consultantDao.findOne(id);
            consultantDao.delete(id);
            FileManager.deleteImage(consultant.getHeadSculpture());
            FileManager.deleteText(consultant.getTextPath());
            return "success";
        }
        return "not_exist";
    }

    @Override
    public String changeRecommend(Integer cid, String isRecommand) {
        Consultant consultant = consultantDao.findOne(cid);
        if (consultant == null) {
            return "not_exist";
        } else {
            consultant.setIsRecommend(isRecommand);
            consultantDao.saveAndFlush(consultant);
            return "success";
        }
    }

    @Override
    public List<OrderForConsultant> checkOrder(Integer id, String isAnswer) {
        List<OrderForConsultant> orderForConsultants = orderForConsultantDao.findAllByCidAndIsAnswerOrderByOrderTimeAsc(id, isAnswer);
        for (OrderForConsultant orderForConsultant : orderForConsultants) {
            orderForConsultant.setConsultantByCid(null);
        }
        return orderForConsultants;
    }


    @Override
    public AnswerForOrder addAnswerForOrder(Integer oid, String answer) {
        OrderForConsultant orderForConsultant = orderForConsultantDao.findOne(oid);
        if (orderForConsultant.getIsAnswer().equals("false")) {
            orderForConsultant.setIsAnswer("true");
            orderForConsultantDao.saveAndFlush(orderForConsultant);
        }
        AnswerForOrder answerForOrder = new AnswerForOrder();
        answerForOrder.setOid(oid);
        answerForOrder.setAnswer(answer);
        answerForOrder.setCreatAt(new Timestamp(System.currentTimeMillis()));
        return answerForOrderDao.saveAndFlush(answerForOrder);
    }

    @Override
    public AnswerForOrder updateAnswerForOrder(AnswerForOrder answerForOrder) {
        return answerForOrderDao.saveAndFlush(answerForOrder);
    }

    @Override
    public String deleteAnswerForOrder(Integer id) {
        if (answerForOrderDao.exists(id)) {
            answerForOrderDao.delete(id);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public ConsultantBusiness addConsultantBusiness(ConsultantBusiness consultantBusiness) {
        return consultantBusinessDao.saveAndFlush(consultantBusiness);
    }

    @Override
    public String deleteConsultantBusiness(Integer id) {
        if (consultantBusinessDao.exists(id)) {
            consultantBusinessDao.delete(id);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public String deleteConsultantBusinessByBid(String category, Integer bid, Integer cid) {
        List<ConsultantBusiness> consultantBusinesses=consultantBusinessDao.findAllByBusinessTypeAndBidAndCid(category,bid,cid);
        if(consultantBusinesses!=null&&consultantBusinesses.size()!=0){
            consultantBusinessDao.delete(consultantBusinesses);
            return "success";
        }
        return "not_exist";
    }
}
