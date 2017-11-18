package dao;

import model.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface SchoolDao extends JpaRepository<School,Serializable> {
    public School findDistinctBySidAndFlag(Integer sid,Integer flag);

    public List<School> findAllByFlag(Integer flag);
}
