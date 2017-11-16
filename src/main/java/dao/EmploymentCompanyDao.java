package dao;

import model.EmploymentCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface EmploymentCompanyDao extends JpaRepository<EmploymentCompany,Serializable> {

    public List<EmploymentCompany> findAllByPid(Integer pid);
}
