package dao;

import model.CooperativeScheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface CooperativeSchemeDao extends JpaRepository<CooperativeScheme,Serializable> {
    public List<CooperativeScheme> findAllByCcidAndFlagOrderByUpdateAtDesc(Integer ccid,Integer flag);
}
