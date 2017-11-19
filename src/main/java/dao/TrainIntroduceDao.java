package dao;

import model.TrainIntroduce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface TrainIntroduceDao extends JpaRepository<TrainIntroduce,Serializable> {
    public List<TrainIntroduce> findAllByCategoryAndFlagOrderByUpdateAtDesc(String category,Integer flag);
}
