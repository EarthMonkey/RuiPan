package dao;

import model.GlobalConfigure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by ldchao on 2017/11/21.
 */
public interface GlobalConfigureDao extends JpaRepository<GlobalConfigure,Serializable> {

    public GlobalConfigure getDistinctByKey(String key);
}
