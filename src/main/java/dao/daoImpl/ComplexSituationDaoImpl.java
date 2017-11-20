package dao.daoImpl;

import dao.ComplexSituationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vo.SuccessfulCaseVO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;

/**
 * Created by ldchao on 2017/11/17.
 */
@Repository
public class ComplexSituationDaoImpl implements ComplexSituationDao{
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public Map<String, List<SuccessfulCaseVO>> getSuccessfulCaseByCountry(String country) {

        EntityManager em=entityManagerFactory.createEntityManager();
        em.close();
        return null;
    }


//    public HostelPlan getAvailableRoomByHidAndTimeAndPeopleNum(int hid, int peopleNum, Timestamp startAt, Timestamp endAt, double money) {
//        EntityManager em=entityManagerFactory.createEntityManager();
//        String sql = "SELECT * " +
//                "FROM hostel_plan p " +
//                "WHERE p.hid=? " +
//                "   AND p.peopleNum=? "+
//                "   AND p.money=? "+
//                "   AND p.roomNum not in( " +
//                "       SELECT o.roomNum " +
//                "       FROM hostel_order o " +
//                "       WHERE o.deleteAt is NULL " +
//                "           AND o.hid = ? " +
//                "           AND o.inTime BETWEEN ? and ? " +
//                "           OR o.outTime BETWEEN ? and ? " +
//                "       UNION " +
//                "       SELECT s.roomNum " +
//                "       FROM hostel_stay s " +
//                "       WHERE s.hid =? " +
//                "           AND s.outTime is NULL )";
//        Query query =  em.createNativeQuery(sql, HostelPlan.class);
//        query.setParameter(1,hid);
//        query.setParameter(2,peopleNum);
//        query.setParameter(3,money);
//        query.setParameter(4,hid);
//        query.setParameter(5,startAt);
//        query.setParameter(6,endAt);
//        query.setParameter(7,startAt);
//        query.setParameter(8,endAt);
//        query.setParameter(9,hid);
//        List<HostelPlan> list=query.getResultList();
//        em.close();
//        HostelPlan hostelPlan=null;
//        if(list.size()>0){
//            hostelPlan=list.get(0);
//            return  hostelPlan;
//        }
//        return hostelPlan;
//    }
//
//    public List<AvailableRoom> getCountAvailableRoomByHidAndTimeAndPeopleNum(int hid, int peopleNum, Timestamp startAt, Timestamp endAt) {
//        List<AvailableRoom> list=new ArrayList<AvailableRoom>();
//        EntityManager em=entityManagerFactory.createEntityManager();
//        String sql = "SELECT COUNT(*) AS num, p.money AS m " +
//                "FROM hostel_plan p " +
//                "WHERE p.hid=? " +
//                "   AND p.peopleNum=? "+
//                "   AND p.roomNum not in( " +
//                "       SELECT o.roomNum " +
//                "       FROM hostel_order o " +
//                "       WHERE o.deleteAt is NULL " +
//                "           AND o.hid = ? " +
//                "           AND o.inTime BETWEEN ? and ? " +
//                "           OR o.outTime BETWEEN ? and ? " +
//                "       UNION " +
//                "       SELECT s.roomNum " +
//                "       FROM hostel_stay s " +
//                "       WHERE s.hid =? " +
//                "           AND s.outTime is NULL ) "+
//                "GROUP BY m " +
//                "ORDER BY m";
//        Query query =  em.createNativeQuery(sql);
//        query.setParameter(1,hid);
//        query.setParameter(2,peopleNum);
//        query.setParameter(3,hid);
//        query.setParameter(4,startAt);
//        query.setParameter(5,endAt);
//        query.setParameter(6,startAt);
//        query.setParameter(7,endAt);
//        query.setParameter(8,hid);
//        List<Object[]> objecArraytList = query.getResultList();
//        for (Object[] o:objecArraytList) {
//            AvailableRoom availableRoom=new AvailableRoom();
//            availableRoom.setRoomNum(((BigInteger)o[0]).intValue());
//            availableRoom.setMoney((Double)o[1]);
//            list.add(availableRoom);
//        }
//        em.close();
//        return list;
//    }

}
