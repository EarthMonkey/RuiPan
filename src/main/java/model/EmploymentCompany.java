package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
@Table(name = "employment_company", schema = "ruipan", catalog = "")
public class EmploymentCompany {
    private int id;
    private Integer pid;
    private String logo;
    private String employmentCompany;
    private Timestamp updateAt;
    private ProfessionCategory professionCategoryByPid;

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
    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "employment_company")
    public String getEmploymentCompany() {
        return employmentCompany;
    }

    public void setEmploymentCompany(String employmentCompany) {
        this.employmentCompany = employmentCompany;
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

        EmploymentCompany that = (EmploymentCompany) o;

        if (id != that.id) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (employmentCompany != null ? !employmentCompany.equals(that.employmentCompany) : that.employmentCompany != null)
            return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (employmentCompany != null ? employmentCompany.hashCode() : 0);
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
}
