package service;

import model.ContactUs;
import model.CooperativePartner;

import java.util.List;

/**
 * Created by ldchao on 2017/11/19.
 */
public interface AboutUsService {
    public List<CooperativePartner> getCooperativePartner();

    public CooperativePartner addCooperativePartner(CooperativePartner cooperativePartner);

    public CooperativePartner updateCooperativePartner(CooperativePartner cooperativePartner);

    public String deleteCooperativePartner(Integer id);

    public ContactUs addContactUs(ContactUs contactUs);

    public List<ContactUs> getUnreplyedContactUs();

    public List<ContactUs> getReplyedContactUs();

    public ContactUs replyContactUs(Integer id, String replyMessage);

    public String deleteContactUs(Integer id);
}
