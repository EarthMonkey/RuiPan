package dao;

import model.ApplicationElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface ApplicationElementDao extends JpaRepository<ApplicationElement,Serializable> {

    public List<ApplicationElement> findAllByGidAndFlagOrderByCategory(Integer gid,Integer flag);
}
