package dao;

import model.ApplicationScheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface ApplicationSchemeDao extends JpaRepository<ApplicationScheme,Serializable> {

    public List<ApplicationScheme> findAllByGidAndFlagOrderBySubdivisionGradeAscUpdateAtDesc(Integer gid,Integer flag);
}
