package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/5.
 */
@Entity
@Table(name = "contact_us", schema = "ruipan", catalog = "")
public class ContactUs {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String content;
    private Timestamp contactAt;
    private String replyMessage;
    private Timestamp replyAt;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "contactAt")
    public Timestamp getContactAt() {
        return contactAt;
    }

    public void setContactAt(Timestamp contactAt) {
        this.contactAt = contactAt;
    }

    @Basic
    @Column(name = "replyMessage")
    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    @Basic
    @Column(name = "replyAt")
    public Timestamp getReplyAt() {
        return replyAt;
    }

    public void setReplyAt(Timestamp replyAt) {
        this.replyAt = replyAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactUs contactUs = (ContactUs) o;

        if (id != contactUs.id) return false;
        if (name != null ? !name.equals(contactUs.name) : contactUs.name != null) return false;
        if (email != null ? !email.equals(contactUs.email) : contactUs.email != null) return false;
        if (phone != null ? !phone.equals(contactUs.phone) : contactUs.phone != null) return false;
        if (content != null ? !content.equals(contactUs.content) : contactUs.content != null) return false;
        if (contactAt != null ? !contactAt.equals(contactUs.contactAt) : contactUs.contactAt != null) return false;
        if (replyMessage != null ? !replyMessage.equals(contactUs.replyMessage) : contactUs.replyMessage != null)
            return false;
        if (replyAt != null ? !replyAt.equals(contactUs.replyAt) : contactUs.replyAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (contactAt != null ? contactAt.hashCode() : 0);
        result = 31 * result + (replyMessage != null ? replyMessage.hashCode() : 0);
        result = 31 * result + (replyAt != null ? replyAt.hashCode() : 0);
        return result;
    }
}
