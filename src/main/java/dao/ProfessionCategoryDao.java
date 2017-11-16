package dao;

import model.ProfessionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface ProfessionCategoryDao extends JpaRepository<ProfessionCategory,Serializable> {

    @Query("select distinct p.country from ProfessionCategory p")
    public List<String> getDistinctCountry();

    public ProfessionCategory findDistinctByCountryAndSubclassification(String country,String subclassification);

    public List<ProfessionCategory> findAllByCountryOrderByCategory(String country);
}
