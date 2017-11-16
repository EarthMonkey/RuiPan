package dao;

import model.EmploymentPost;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface EmploymentPostDao extends JpaRepository<EmploymentPost,Serializable> {
    public List<EmploymentPost> findAllByPid(Integer pid);
}
