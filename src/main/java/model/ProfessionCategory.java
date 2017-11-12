package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
@Table(name = "profession_category", schema = "ruipan", catalog = "")
public class ProfessionCategory {
    private int pid;
    private String country;
    private String category;
    private String subclassification;
    private Timestamp createAt;
    private Collection<ApplicationAdvice> applicationAdvicesByPid;
    private Collection<EmploymentCompany> employmentCompaniesByPid;
    private Collection<EmploymentPost> employmentPostsByPid;
    private Collection<ProfessionCourse> professionCoursesByPid;
    private ProfessionIntroduce professionIntroduceByPid;
    private Collection<Salary> salariesByPid;
    private Collection<SchoolRanking> schoolRankingsByPid;
    private Collection<SuccessfulCase> successfulCasesByPid;

    @Id
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "subclassification")
    public String getSubclassification() {
        return subclassification;
    }

    public void setSubclassification(String subclassification) {
        this.subclassification = subclassification;
    }

    @Basic
    @Column(name = "createAt")
    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfessionCategory that = (ProfessionCategory) o;

        if (pid != that.pid) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (subclassification != null ? !subclassification.equals(that.subclassification) : that.subclassification != null)
            return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (subclassification != null ? subclassification.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "professionCategoryByPid")
    public Collection<ApplicationAdvice> getApplicationAdvicesByPid() {
        return applicationAdvicesByPid;
    }

    public void setApplicationAdvicesByPid(Collection<ApplicationAdvice> applicationAdvicesByPid) {
        this.applicationAdvicesByPid = applicationAdvicesByPid;
    }

    @OneToMany(mappedBy = "professionCategoryByPid")
    public Collection<EmploymentCompany> getEmploymentCompaniesByPid() {
        return employmentCompaniesByPid;
    }

    public void setEmploymentCompaniesByPid(Collection<EmploymentCompany> employmentCompaniesByPid) {
        this.employmentCompaniesByPid = employmentCompaniesByPid;
    }

    @OneToMany(mappedBy = "professionCategoryByPid")
    public Collection<EmploymentPost> getEmploymentPostsByPid() {
        return employmentPostsByPid;
    }

    public void setEmploymentPostsByPid(Collection<EmploymentPost> employmentPostsByPid) {
        this.employmentPostsByPid = employmentPostsByPid;
    }

    @OneToMany(mappedBy = "professionCategoryByPid")
    public Collection<ProfessionCourse> getProfessionCoursesByPid() {
        return professionCoursesByPid;
    }

    public void setProfessionCoursesByPid(Collection<ProfessionCourse> professionCoursesByPid) {
        this.professionCoursesByPid = professionCoursesByPid;
    }

    @OneToOne(mappedBy = "professionCategoryByPid")
    public ProfessionIntroduce getProfessionIntroduceByPid() {
        return professionIntroduceByPid;
    }

    public void setProfessionIntroduceByPid(ProfessionIntroduce professionIntroduceByPid) {
        this.professionIntroduceByPid = professionIntroduceByPid;
    }

    @OneToMany(mappedBy = "professionCategoryByPid")
    public Collection<Salary> getSalariesByPid() {
        return salariesByPid;
    }

    public void setSalariesByPid(Collection<Salary> salariesByPid) {
        this.salariesByPid = salariesByPid;
    }

    @OneToMany(mappedBy = "professionCategoryByPid")
    public Collection<SchoolRanking> getSchoolRankingsByPid() {
        return schoolRankingsByPid;
    }

    public void setSchoolRankingsByPid(Collection<SchoolRanking> schoolRankingsByPid) {
        this.schoolRankingsByPid = schoolRankingsByPid;
    }

    @OneToMany(mappedBy = "professionCategoryByPid")
    public Collection<SuccessfulCase> getSuccessfulCasesByPid() {
        return successfulCasesByPid;
    }

    public void setSuccessfulCasesByPid(Collection<SuccessfulCase> successfulCasesByPid) {
        this.successfulCasesByPid = successfulCasesByPid;
    }
}
