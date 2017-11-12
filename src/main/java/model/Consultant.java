package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
public class Consultant {
    private int id;
    private String name;
    private String country;
    private String profession;
    private String headSculpture;
    private Double workingHours;
    private Integer offerNumber;
    private String applicationSuccessRate;
    private String synopsis;
    private String textPath;
    private String isRecommend;
    private Timestamp updateAt;
    private Collection<ConsultantBusiness> consultantBusinessesById;
    private Collection<OrderForConsultant> orderForConsultantsById;
    private Collection<SuccessfulCase> successfulCasesById;

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
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "profession")
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Basic
    @Column(name = "head_sculpture")
    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    @Basic
    @Column(name = "working_hours")
    public Double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Double workingHours) {
        this.workingHours = workingHours;
    }

    @Basic
    @Column(name = "offer_number")
    public Integer getOfferNumber() {
        return offerNumber;
    }

    public void setOfferNumber(Integer offerNumber) {
        this.offerNumber = offerNumber;
    }

    @Basic
    @Column(name = "application_success_rate")
    public String getApplicationSuccessRate() {
        return applicationSuccessRate;
    }

    public void setApplicationSuccessRate(String applicationSuccessRate) {
        this.applicationSuccessRate = applicationSuccessRate;
    }

    @Basic
    @Column(name = "synopsis")
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Basic
    @Column(name = "text_path")
    public String getTextPath() {
        return textPath;
    }

    public void setTextPath(String textPath) {
        this.textPath = textPath;
    }


    @Basic
    @Column(name = "isRecommend")
    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
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

        Consultant that = (Consultant) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (profession != null ? !profession.equals(that.profession) : that.profession != null) return false;
        if (headSculpture != null ? !headSculpture.equals(that.headSculpture) : that.headSculpture != null)
            return false;
        if (workingHours != null ? !workingHours.equals(that.workingHours) : that.workingHours != null) return false;
        if (offerNumber != null ? !offerNumber.equals(that.offerNumber) : that.offerNumber != null) return false;
        if (applicationSuccessRate != null ? !applicationSuccessRate.equals(that.applicationSuccessRate) : that.applicationSuccessRate != null)
            return false;
        if (synopsis != null ? !synopsis.equals(that.synopsis) : that.synopsis != null) return false;
        if (textPath != null ? !textPath.equals(that.textPath) : that.textPath != null) return false;
        if (isRecommend != null ? !isRecommend.equals(that.isRecommend) : that.isRecommend != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (profession != null ? profession.hashCode() : 0);
        result = 31 * result + (headSculpture != null ? headSculpture.hashCode() : 0);
        result = 31 * result + (workingHours != null ? workingHours.hashCode() : 0);
        result = 31 * result + (offerNumber != null ? offerNumber.hashCode() : 0);
        result = 31 * result + (applicationSuccessRate != null ? applicationSuccessRate.hashCode() : 0);
        result = 31 * result + (synopsis != null ? synopsis.hashCode() : 0);
        result = 31 * result + (textPath != null ? textPath.hashCode() : 0);
        result = 31 * result + (isRecommend != null ? isRecommend.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "consultantByCid")
    public Collection<ConsultantBusiness> getConsultantBusinessesById() {
        return consultantBusinessesById;
    }

    public void setConsultantBusinessesById(Collection<ConsultantBusiness> consultantBusinessesById) {
        this.consultantBusinessesById = consultantBusinessesById;
    }

    @OneToMany(mappedBy = "consultantByCid")
    public Collection<OrderForConsultant> getOrderForConsultantsById() {
        return orderForConsultantsById;
    }

    public void setOrderForConsultantsById(Collection<OrderForConsultant> orderForConsultantsById) {
        this.orderForConsultantsById = orderForConsultantsById;
    }

    @OneToMany(mappedBy = "consultantByCid")
    public Collection<SuccessfulCase> getSuccessfulCasesById() {
        return successfulCasesById;
    }

    public void setSuccessfulCasesById(Collection<SuccessfulCase> successfulCasesById) {
        this.successfulCasesById = successfulCasesById;
    }
}
