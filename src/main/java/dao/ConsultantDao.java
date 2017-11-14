package dao;

import model.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface ConsultantDao extends JpaRepository<Consultant,Serializable> {

    @Query("select Consultant from Consultant where id in ?1")
    public List<Consultant> getAllByCids(List<Integer> cid);

}
