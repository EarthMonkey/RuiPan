package dao;

import model.BackgroundPromote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface BackgroundPromoteDao extends JpaRepository<BackgroundPromote,Serializable> {

    public List<BackgroundPromote> findAllByCategoryAndFlagOrderByUpdateAtDesc(String category,Integer flag);
}
