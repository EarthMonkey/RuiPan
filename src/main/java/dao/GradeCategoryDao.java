package dao;

import model.GradeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface GradeCategoryDao extends JpaRepository<GradeCategory,Serializable> {

    @Query("select distinct g.country from GradeCategory g")
    public List<String> getDistinctCountry();

    public Long countByCountry(String country);

    @Transactional
    public void deleteByCountry(String country);

    public GradeCategory findDistinctByCountryAndGrade(String country,String grade);
}
