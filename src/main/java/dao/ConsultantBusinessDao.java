package dao;

import model.Consultant;
import model.ConsultantBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldchao on 2017/11/4.
 */
public interface ConsultantBusinessDao extends JpaRepository<ConsultantBusiness,Serializable> {

    @Query("select distinct cid from ConsultantBusiness where businessType=?1 and bid=?2")
    List<Integer> getConsultantIdByBid(String businessType,Integer bid);

    public List<ConsultantBusiness> findAllByBusinessTypeAndBidAndCid(String businessType,Integer bid,Integer cid);
}
