package controller;

import model.AnswerForOrder;
import model.ConsultantBusiness;
import model.OrderForConsultant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ConsultantService;
import util.SystemLog;
import vo.ConsultantVO;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ldchao on 2017/11/5.
 */
@RestController
@RequestMapping(value = "/Consultant")
public class ConsultantController {

    @Autowired
    ConsultantService consultantService;

    //获取所有顾问
    @GetMapping(value = "/get")
    public List<ConsultantVO> getConsultant(){
        return consultantService.getConsultant();
    }

    //根据gid获取所有顾问列表
    @GetMapping(value = "/getByGid")
    public List<ConsultantVO> getConsultantByGid(Integer gid){
        return consultantService.getConsultantByGid(gid);
    }

    //根据pid获取所有顾问列表
    @GetMapping(value = "/getByPid")
    public List<ConsultantVO> getConsultantByPid(Integer pid){
        return consultantService.getConsultantByPid(pid);
    }

    @GetMapping(value = "/getById")
    public ConsultantVO getConsultantById(Integer id){
        return consultantService.getConsultantById(id);
    }

    //根据顾问id增加一条预约记录
    @PostMapping(value = "/addOrder")
    public OrderForConsultant addOrderForConsultant(Integer cid,String name,String phone,String illustration){
        OrderForConsultant orderForConsultant=new OrderForConsultant();
        orderForConsultant.setCid(cid);
        orderForConsultant.setName(name);
        orderForConsultant.setPhone(phone);
        orderForConsultant.setIllustration(illustration);
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        orderForConsultant.setOrderTime(timestamp);
        orderForConsultant.setIsAnswer("false");
        orderForConsultant.setUpdateAt(timestamp);
        return consultantService.addOrderForConsultant(orderForConsultant);
    }

    @GetMapping(value = "/getRecommendConsultant")
    public List<ConsultantVO> getRecommendConsultant(){
        return consultantService.getRecommendConsultant();
    }

    //******************后台管理接口**********************


    //增加一条顾问信息（默认不推荐）
    @PostMapping(value = "/add")
    @SystemLog(module = "顾问管理", methods = "增加顾问")
    public ConsultantVO addConsultant(String name,String country,String profession,String headSculpture,
            Double workingHours,Integer offerNumber,String applicationSuccessRate,String synopsis,
            String textPath){
        ConsultantVO consultantVO=new ConsultantVO();
        consultantVO.setName(name);
        consultantVO.setCountry(country);
        consultantVO.setProfession(profession);
        consultantVO.setHeadSculpture(headSculpture);
        consultantVO.setWorkingHours(workingHours);
        consultantVO.setOfferNumber(offerNumber);
        consultantVO.setApplicationSuccessRate(applicationSuccessRate);
        consultantVO.setSynopsis(synopsis);
        consultantVO.setTextPath(textPath);
        consultantVO.setIsRecommend("false");
        consultantService.addConsultant(consultantVO);
        return consultantVO;
    }

    //根据id编辑一条顾问信息
    @PutMapping(value = "/updateConsultant")
    @SystemLog(module = "顾问管理", methods = "修改顾问信息")
    public ConsultantVO updateConsultant(Integer id,String name,String country,String profession,String headSculpture,
                                      Double workingHours,Integer offerNumber,String applicationSuccessRate,String synopsis,
                                      String textPath){
        ConsultantVO consultantVO=new ConsultantVO();
        consultantVO.setId(id);
        consultantVO.setName(name);
        consultantVO.setCountry(country);
        consultantVO.setProfession(profession);
        consultantVO.setHeadSculpture(headSculpture);
        consultantVO.setWorkingHours(workingHours);
        consultantVO.setOfferNumber(offerNumber);
        consultantVO.setApplicationSuccessRate(applicationSuccessRate);
        consultantVO.setSynopsis(synopsis);
        consultantVO.setTextPath(textPath);
        consultantService.updateConsultant(consultantVO);
        return consultantVO;
    }

    //根据id将顾问设为推荐
    @PutMapping(value = "/recommend")
    @SystemLog(module = "顾问管理", methods = "推荐顾问")
    public String recommendConsultant(Integer id){
        return consultantService.changeRecommend(id,"true");
    }


    //根据id将顾问设为不推荐
    @PutMapping(value = "/cancleRecommend")
    @SystemLog(module = "顾问管理", methods = "取消推荐顾问")
    public String cancelRecommendConsultant(Integer id){
        return consultantService.changeRecommend(id,"false");
    }

    //根据id删除一条顾问信息
    @DeleteMapping(value = "/delete")
    @SystemLog(module = "顾问管理", methods = "删除顾问")
    public String deleteConsultant(Integer id){
        return consultantService.deleteConsultant(id);
    }

    //根据顾问id获得该顾问所有未答复的预约
    @GetMapping(value = "/checkUnAnsweredOrder")
    @SystemLog(module = "顾问管理", methods = "查看未答复预约")
    public List<OrderForConsultant> checkUnAnsweredOrder(Integer id){
        return consultantService.checkOrder(id,"false");
    }


    //根据顾问id获得该顾问所有已经答复的预约
    @GetMapping(value = "/checkAnsweredOrder")
    @SystemLog(module = "顾问管理", methods = "查看已答复预约")
    public List<OrderForConsultant> checkAnsweredOrder(Integer id){
        return consultantService.checkOrder(id,"true");
    }

    //根据顾问预约id查看所有答复
    @GetMapping(value = "/getAnswerForOrder")
    @SystemLog(module = "顾问管理", methods = "查看答复记录")
    public List<AnswerForOrder> getAnswerForOrder(Integer oid){
        return null;
    }


    //根据顾问预约id答复某条预约
    @PostMapping(value = "/addAnswerForOrder")
    @SystemLog(module = "顾问管理", methods = "新增答复记录")
    public AnswerForOrder addAnswerForOrder(Integer oid,String answer){
        return consultantService.addAnswerForOrder(oid,answer);
    }

    //根据顾问预约答复id修改答复
    @PutMapping(value = "/updateAnswerForOrder")
    @SystemLog(module = "顾问管理", methods = "更新答复记录")
    public AnswerForOrder updateAnswerForOrder(Integer id,Integer oid,String answer){
        AnswerForOrder answerForOrder=new AnswerForOrder();
        answerForOrder.setId(id);
        answerForOrder.setOid(oid);
        answerForOrder.setAnswer(answer);
        answerForOrder.setCreatAt(new Timestamp(System.currentTimeMillis()));
        return consultantService.updateAnswerForOrder(answerForOrder);
    }

    //根据顾问预约答复id删除答复
    @DeleteMapping(value = "/deleteAnswerForOrder")
    @SystemLog(module = "顾问管理", methods = "删除答复记录")
    public String deleteAnswerForOrder(Integer id){
        return consultantService.deleteAnswerForOrder(id);
    }

    //根据顾问预约id删除某条预约
    @DeleteMapping(value = "/deleteOrder")
    @SystemLog(module = "顾问管理", methods = "删预约记录")
    public String deleteOrder(Integer id){
        return deleteOrder(id);
    }


    //根据顾问id和gid增加一条顾问业务
    @PostMapping(value = "/addConsultantBusinessByGid")
    @SystemLog(module = "顾问管理", methods = "按年级增加顾问业务")
    public ConsultantBusiness addConsultantBusinessByGid(Integer cid, Integer gid){
        ConsultantBusiness consultantBusiness=new ConsultantBusiness();
        consultantBusiness.setCid(cid);
        consultantBusiness.setBid(gid);
        return consultantService.addConsultantBusiness(consultantBusiness);
    }

    //根据顾问id和pid增加一条顾问业务
    @PutMapping(value = "/addConsultantBusinessByPid")
    @SystemLog(module = "顾问管理", methods = "按专业增加顾问业务")
    public ConsultantBusiness addConsultantBusinessByPid(Integer cid, Integer pid){
        ConsultantBusiness consultantBusiness=new ConsultantBusiness();
        consultantBusiness.setCid(cid);
        consultantBusiness.setBid(pid);
        return consultantService.addConsultantBusiness(consultantBusiness);
    }

    //根据顾问业务id删除一条顾问业务
    @DeleteMapping(value = "/deleteConsultantBusiness")
    @SystemLog(module = "顾问管理", methods = "删除顾问业务")
    public String deleteConsultantBusiness(Integer id){
        return consultantService.deleteConsultantBusiness(id);
    }
}
