package dao;

import model.SuccessfulCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface SuccessfulCaseDao extends JpaRepository<SuccessfulCase,Serializable> {

    public List<SuccessfulCase> findAllByPidAndFlagOrderByEnrollmentTimeDesc(Integer pid,Integer flag);

    public List<SuccessfulCase> findAllBySidAndFlagOrderByEnrollmentTimeDesc(Integer sid,Integer flag);

    public List<SuccessfulCase> findAllByCidAndFlagOrderByEnrollmentTimeDesc(Integer cid,Integer flag);
}
