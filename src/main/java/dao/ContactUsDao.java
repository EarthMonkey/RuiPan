package dao;

import model.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/5.
 */
public interface ContactUsDao extends JpaRepository<ContactUs,Serializable> {

    public List<ContactUs> findAllByReplyAtIsNotNull();

    public List<ContactUs> findAllByReplyAtIsNull();
}
