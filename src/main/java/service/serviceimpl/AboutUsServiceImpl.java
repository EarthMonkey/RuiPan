package service.serviceimpl;

import dao.ContactUsDao;
import dao.CooperativePartnerDao;
import model.ContactUs;
import model.CooperativePartner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AboutUsService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ldchao on 2017/11/19.
 */
@Service
public class AboutUsServiceImpl implements AboutUsService{

    @Autowired
    CooperativePartnerDao cooperativePartnerDao;

    @Autowired
    ContactUsDao contactUsDao;

    @Override
    public List<CooperativePartner> getCooperativePartner() {
        return cooperativePartnerDao.findAll();
    }

    @Override
    public CooperativePartner addCooperativePartner(CooperativePartner cooperativePartner) {
        return cooperativePartnerDao.saveAndFlush(cooperativePartner);
    }

    @Override
    public CooperativePartner updateCooperativePartner(CooperativePartner cooperativePartner) {
        return cooperativePartnerDao.saveAndFlush(cooperativePartner);
    }

    @Override
    public String deleteCooperativePartner(Integer id) {
        if(cooperativePartnerDao.exists(id)){
            cooperativePartnerDao.delete(id);
            return "success";
        }
        return "not_exist";
    }

    @Override
    public ContactUs addContactUs(ContactUs contactUs) {
        return contactUsDao.saveAndFlush(contactUs);
    }

    @Override
    public List<ContactUs> getUnreplyedContactUs() {
        return contactUsDao.findAllByReplyAtIsNull();
    }

    @Override
    public List<ContactUs> getReplyedContactUs() {
        return contactUsDao.findAllByReplyAtIsNotNull();
    }

    @Override
    public ContactUs replyContactUs(Integer id, String replyMessage) {
        ContactUs contactUs=contactUsDao.findOne(id);
        if(contactUs!=null){
            contactUs.setReplyMessage(replyMessage);
            contactUs.setReplyAt(new Timestamp(System.currentTimeMillis()));
            return contactUsDao.saveAndFlush(contactUs);
        }
        return new ContactUs();
    }

    @Override
    public String deleteContactUs(Integer id) {
        if(contactUsDao.exists(id)){
            contactUsDao.delete(id);
            return "success";
        }
        return "not_exist";
    }
}
