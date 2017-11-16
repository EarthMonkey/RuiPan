package dao;

import model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface SalaryDao extends JpaRepository<Salary,Serializable> {
    public List<Salary> findAllByPid(Integer pid);
}
