package dao;

import model.GlobalRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface GlobalRecommendationDao extends JpaRepository<GlobalRecommendation,Serializable> {

    List<GlobalRecommendation> findAllByCategoryAndFlagOrderByRecommendAtDesc(String category,Integer flag);

    int countByCategoryEqualsAndFlagEquals(String category,Integer flag);

    GlobalRecommendation findFirstByCategoryAndFlagOrderByRecommendAtAsc(String category,Integer flag);
}
