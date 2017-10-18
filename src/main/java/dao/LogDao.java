package dao;

import model.Logentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by ldchao on 2017/10/15.
 */
public interface LogDao extends JpaRepository<Logentity,Serializable> {

}
