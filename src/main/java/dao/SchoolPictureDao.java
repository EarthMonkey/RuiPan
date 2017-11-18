package dao;

import model.SchoolPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface SchoolPictureDao extends JpaRepository<SchoolPicture,Serializable> {

    public List<SchoolPicture> findAllBySid(Integer sid);
}
