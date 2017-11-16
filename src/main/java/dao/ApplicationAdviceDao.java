package dao;

import model.ApplicationAdvice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface ApplicationAdviceDao extends JpaRepository<ApplicationAdvice,Serializable> {
    public List<ApplicationAdvice> findAllByPid(Integer pid);
}
