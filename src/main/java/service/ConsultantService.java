package service;

import model.AnswerForOrder;
import model.ConsultantBusiness;
import model.OrderForConsultant;
import org.springframework.transaction.annotation.Transactional;
import vo.ConsultantVO;

import java.util.List;

/**
 * Created by ldchao on 2017/11/14.
 */
public interface ConsultantService {

    //根据年级类别gid获取所有顾问
    public List<ConsultantVO> getConsultantByGid(Integer gid);

    //根据专业类别fid获取所有顾问
    public List<ConsultantVO> getConsultantByFid(Integer fid);

    //增加一条预订记录
    public OrderForConsultant addOrderForConsultant(OrderForConsultant orderForConsultant);

    //增加一条顾问
    public void addConsultant(ConsultantVO consultantVO);

    //编辑一条顾问
    public void updateConsultant(ConsultantVO consultantVO);

    //设置顾问是否推荐
    public String changeRecommend(Integer cid, String isRecommand);

    //删除一条顾问
    public String deleteRecommend(Integer cid);

    //查看预订记录
    public List<OrderForConsultant> checkOrder(Integer id,String isAnswer);

    //根据顾问预约id答复某条预约
    @Transactional
    public AnswerForOrder addAnswerForOrder(Integer oid, String answer);

    //根据顾问预约答复id修改答复
    public AnswerForOrder updateAnswerForOrder(AnswerForOrder answerForOrder);

    //根据顾问预约答复id删除答复
    public String deleteAnswerForOrder(Integer id);

    //根据顾问id增加一条顾问业务
    public ConsultantBusiness addConsultantBusiness(ConsultantBusiness consultantBusiness);

    //根据顾问业务id删除一条顾问业务
    public String deleteConsultantBusiness(Integer id);
}
