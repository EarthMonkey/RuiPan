package dao;

import model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface NewsDao extends JpaRepository<News,Serializable> {

    public List<News> findTop4ByCategoryAndFlagOrderByPulishTimeDesc(String category,Integer flag);

    public List<News> findAllByCategoryAndFlagOrderByPulishTimeDesc(String category,Integer flag);
}
