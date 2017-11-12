package dao;

import model.OrderForConsultant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface OrderForConsultantDao extends JpaRepository<OrderForConsultant,Serializable> {
}
