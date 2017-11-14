package dao;

import model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface QuestionDao extends JpaRepository<Question,Serializable> {

    public List<Question> findAllByGidOrderByCreateAtDesc(Integer gid);

    public List<Question> findAllByGidAndIsShowOrderByCreateAtDesc(Integer gid,String isShow);
}
