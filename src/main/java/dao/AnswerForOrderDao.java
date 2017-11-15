package dao;

import model.AnswerForOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by ldchao on 2017/11/15.
 */
public interface AnswerForOrderDao extends JpaRepository<AnswerForOrder,Serializable> {
}
