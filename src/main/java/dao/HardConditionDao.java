package dao;

import model.HardCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface HardConditionDao extends JpaRepository<HardCondition,Serializable> {

    public List<HardCondition> findAllByGidOrderByRankAscSubjectAsc(Integer gid);
}
