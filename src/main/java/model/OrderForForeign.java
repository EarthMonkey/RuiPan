package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/1.
 */
@Entity
@Table(name = "order_for_foreign", schema = "ruipan", catalog = "")
public class OrderForForeign {
    private int id;
    private Integer fid;
    private String name;
    private String phone;
    private String illustrate;
    private Timestamp ordertime;
    private String isAnswer;
    private Timestamp updateAt;
    private ForeignConsultant foreignConsultantByFid;

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
    @Column(name = "fid")
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
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
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "illustrate")
    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    @Basic
    @Column(name = "ordertime")
    public Timestamp getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Timestamp ordertime) {
        this.ordertime = ordertime;
    }

    @Basic
    @Column(name = "isAnswer")
    public String getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(String isAnswer) {
        this.isAnswer = isAnswer;
    }

    @Basic
    @Column(name = "updateAt")
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderForForeign that = (OrderForForeign) o;

        if (id != that.id) return false;
        if (fid != null ? !fid.equals(that.fid) : that.fid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (illustrate != null ? !illustrate.equals(that.illustrate) : that.illustrate != null) return false;
        if (ordertime != null ? !ordertime.equals(that.ordertime) : that.ordertime != null) return false;
        if (isAnswer != null ? !isAnswer.equals(that.isAnswer) : that.isAnswer != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (illustrate != null ? illustrate.hashCode() : 0);
        result = 31 * result + (ordertime != null ? ordertime.hashCode() : 0);
        result = 31 * result + (isAnswer != null ? isAnswer.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "fid", referencedColumnName = "id", insertable = false, updatable = false)
    public ForeignConsultant getForeignConsultantByFid() {
        return foreignConsultantByFid;
    }

    public void setForeignConsultantByFid(ForeignConsultant foreignConsultantByFid) {
        this.foreignConsultantByFid = foreignConsultantByFid;
    }
}
