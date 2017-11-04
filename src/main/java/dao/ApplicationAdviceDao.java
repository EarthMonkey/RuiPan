package dao;

import model.ApplicationAdvice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface ApplicationAdviceDao extends JpaRepository<ApplicationAdvice,Serializable> {
}
