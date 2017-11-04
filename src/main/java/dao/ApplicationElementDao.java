package dao;

import model.ApplicationElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface ApplicationElementDao extends JpaRepository<ApplicationElement,Serializable> {
}
