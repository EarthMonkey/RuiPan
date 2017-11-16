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
import vo.ConsultantVO;

import java.sql.Timestamp;
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

    @Autowired
    OrderForConsultantDao orderForConsultantDao;

    @Autowired
    AnswerForOrderDao answerForOrderDao;

    @Override
    public List<ConsultantVO> getConsultantByGid(Integer gid) {

        List<Integer> cids=consultantBusinessDao.getConsultantIdByBid(ConsultantBusinesType.ClassifyByGrade,gid);
        List<Consultant> consultants=consultantDao.checkConsultantInCids(cids);
        List<ConsultantVO> consultantVOS=new ArrayList<ConsultantVO>();
        for (Consultant consultant:consultants) {
            ConsultantVO consultantVO=new ConsultantVO();
            consultantVO.update(consultant);
            consultantVOS.add(consultantVO);
        }
        return consultantVOS;
    }

    @Override
    public List<ConsultantVO> getConsultantByFid(Integer fid) {

        List<Integer> cids=consultantBusinessDao.getConsultantIdByBid(ConsultantBusinesType.ClassifyByProfession,fid);
        List<Consultant> consultants=consultantDao.checkConsultantInCids(cids);
        List<ConsultantVO> consultantVOS=new ArrayList<ConsultantVO>();
        for (Consultant consultant:consultants) {
            ConsultantVO consultantVO=new ConsultantVO();
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
        Consultant consultant=consultantVO.toEntity();
        consultantDao.saveAndFlush(consultant);
        consultantVO.setId(consultant.getId());
    }

    @Override
    public void updateConsultant(ConsultantVO consultantVO) {
        Consultant consultant=consultantVO.toEntity();
        consultantDao.saveAndFlush(consultant);
    }

    @Override
    public String changeRecommend(Integer cid, String isRecommand) {
        Consultant consultant=consultantDao.findOne(cid);
        if(consultant==null){
            return "not_exist";
        }else{
            consultant.setIsRecommend(isRecommand);
            return "success";
        }
    }

    @Override
    public String deleteRecommend(Integer cid) {
        if(consultantDao.exists(cid)){
            consultantDao.delete(cid);
            return "success";
        }else{
            return "not_exist";
        }
    }

    @Override
    public List<OrderForConsultant> checkOrder(Integer id, String isAnswer) {
        List<OrderForConsultant> orderForConsultants=orderForConsultantDao.findAllByCidAndIsAnswerOrderByOrderTimeAsc(id,isAnswer);
        return orderForConsultants;
    }


    @Override
    public AnswerForOrder addAnswerForOrder(Integer oid, String answer) {
        OrderForConsultant orderForConsultant=orderForConsultantDao.findOne(oid);
        if(orderForConsultant.getIsAnswer().equals("false")){
            orderForConsultant.setIsAnswer("true");
            orderForConsultantDao.saveAndFlush(orderForConsultant);
        }
        AnswerForOrder answerForOrder=new AnswerForOrder();
        answerForOrder.setOid(oid);
        answerForOrder.setAnswer(answer);
        answerForOrder.setCreatAt(new Timestamp(System.currentTimeMillis()));
        return answerForOrderDao.saveAndFlush(answerForOrder);
    }

    @Override
    public AnswerForOrder updateAnswerForOrder(Integer id, String answer) {
        AnswerForOrder answerForOrder=new AnswerForOrder();
        answerForOrder.setId(id);
        answerForOrder.setAnswer(answer);
        answerForOrder.setCreatAt(new Timestamp(System.currentTimeMillis()));
        return answerForOrderDao.saveAndFlush(answerForOrder);
    }

    @Override
    public String deleteAnswerForOrder(Integer id) {
        if(answerForOrderDao.exists(id)){
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
        if(consultantBusinessDao.exists(id)){
            consultantBusinessDao.delete(id);
            return "success";
        }
        return "not_exist";
    }
}
