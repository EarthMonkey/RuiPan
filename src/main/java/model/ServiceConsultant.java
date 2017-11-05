package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by ldchao on 2017/11/1.
 */
@Entity
@Table(name = "service_consultant", schema = "ruipan", catalog = "")
public class ServiceConsultant {
    private int id;
    private Integer pid;
    private String name;
    private String headSculpture;
    private Double workingHours;
    private Integer offerNumber;
    private String applicationSuccessRate;
    private String synopsis;
    private String textPath;
    private Timestamp updateAt;
    private ProfessionCategory professionCategoryByPid;
    private Collection<OrderForService> orderForServicesById;
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
    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

        ServiceConsultant that = (ServiceConsultant) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (headSculpture != null ? !headSculpture.equals(that.headSculpture) : that.headSculpture != null)
            return false;
        if (workingHours != null ? !workingHours.equals(that.workingHours) : that.workingHours != null) return false;
        if (offerNumber != null ? !offerNumber.equals(that.offerNumber) : that.offerNumber != null) return false;
        if (applicationSuccessRate != null ? !applicationSuccessRate.equals(that.applicationSuccessRate) : that.applicationSuccessRate != null)
            return false;
        if (synopsis != null ? !synopsis.equals(that.synopsis) : that.synopsis != null) return false;
        if (textPath != null ? !textPath.equals(that.textPath) : that.textPath != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (headSculpture != null ? headSculpture.hashCode() : 0);
        result = 31 * result + (workingHours != null ? workingHours.hashCode() : 0);
        result = 31 * result + (offerNumber != null ? offerNumber.hashCode() : 0);
        result = 31 * result + (applicationSuccessRate != null ? applicationSuccessRate.hashCode() : 0);
        result = 31 * result + (synopsis != null ? synopsis.hashCode() : 0);
        result = 31 * result + (textPath != null ? textPath.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", insertable = false, updatable = false)
    public ProfessionCategory getProfessionCategoryByPid() {
        return professionCategoryByPid;
    }

    public void setProfessionCategoryByPid(ProfessionCategory professionCategoryByPid) {
        this.professionCategoryByPid = professionCategoryByPid;
    }

    @OneToMany(mappedBy = "serviceConsultantByCid")
    public Collection<OrderForService> getOrderForServicesById() {
        return orderForServicesById;
    }

    public void setOrderForServicesById(Collection<OrderForService> orderForServicesById) {
        this.orderForServicesById = orderForServicesById;
    }

    @OneToMany(mappedBy = "serviceConsultantByCid")
    public Collection<SuccessfulCase> getSuccessfulCasesById() {
        return successfulCasesById;
    }

    public void setSuccessfulCasesById(Collection<SuccessfulCase> successfulCasesById) {
        this.successfulCasesById = successfulCasesById;
    }
}
