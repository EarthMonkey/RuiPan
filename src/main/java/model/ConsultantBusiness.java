package model;

import javax.persistence.*;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
@Table(name = "consultant_business", schema = "ruipan", catalog = "")
public class ConsultantBusiness {
    private int id;
    private Integer cid;
    private String businessType;
    private Integer bid;
    private Consultant consultantByCid;

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
    @Column(name = "cid")
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "business_type")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "bid")
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsultantBusiness that = (ConsultantBusiness) o;

        if (id != that.id) return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
        if (businessType != null ? !businessType.equals(that.businessType) : that.businessType != null) return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (businessType != null ? businessType.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "id", insertable = false, updatable = false)
    public Consultant getConsultantByCid() {
        return consultantByCid;
    }

    public void setConsultantByCid(Consultant consultantByCid) {
        this.consultantByCid = consultantByCid;
    }
}
