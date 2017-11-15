package dao;

import model.OrderForConsultant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface OrderForConsultantDao extends JpaRepository<OrderForConsultant,Serializable> {

    public List<OrderForConsultant> findAllByCidAndIsAnswerOrderByOrderTimeAsc(Integer cid,String isAnswer);
}
