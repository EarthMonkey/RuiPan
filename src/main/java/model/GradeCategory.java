package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by ldchao on 2017/11/1.
 */
@Entity
@Table(name = "grade_category", schema = "ruipan", catalog = "")
public class GradeCategory {
    private int gid;
    private String country;
    private String grade;
    private Timestamp creatAt;
    private Collection<ApplicationElement> applicationElementsByGid;
    private Collection<ApplicationScheme> applicationSchemesByGid;
    private Collection<ForeignConsultant> foreignConsultantsByGid;
    private Collection<HardCondition> hardConditionsByGid;
    private Collection<Question> questionsByGid;

    @Id
    @Column(name = "gid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
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
    @Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "creatAt")
    public Timestamp getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Timestamp creatAt) {
        this.creatAt = creatAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradeCategory that = (GradeCategory) o;

        if (gid != that.gid) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (creatAt != null ? !creatAt.equals(that.creatAt) : that.creatAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gid;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (creatAt != null ? creatAt.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "gradeCategoryByGid")
    public Collection<ApplicationElement> getApplicationElementsByGid() {
        return applicationElementsByGid;
    }

    public void setApplicationElementsByGid(Collection<ApplicationElement> applicationElementsByGid) {
        this.applicationElementsByGid = applicationElementsByGid;
    }

    @OneToMany(mappedBy = "gradeCategoryByGid")
    public Collection<ApplicationScheme> getApplicationSchemesByGid() {
        return applicationSchemesByGid;
    }

    public void setApplicationSchemesByGid(Collection<ApplicationScheme> applicationSchemesByGid) {
        this.applicationSchemesByGid = applicationSchemesByGid;
    }

    @OneToMany(mappedBy = "gradeCategoryByGid")
    public Collection<ForeignConsultant> getForeignConsultantsByGid() {
        return foreignConsultantsByGid;
    }

    public void setForeignConsultantsByGid(Collection<ForeignConsultant> foreignConsultantsByGid) {
        this.foreignConsultantsByGid = foreignConsultantsByGid;
    }

    @OneToMany(mappedBy = "gradeCategoryByGid")
    public Collection<HardCondition> getHardConditionsByGid() {
        return hardConditionsByGid;
    }

    public void setHardConditionsByGid(Collection<HardCondition> hardConditionsByGid) {
        this.hardConditionsByGid = hardConditionsByGid;
    }

    @OneToMany(mappedBy = "gradeCategoryByGid")
    public Collection<Question> getQuestionsByGid() {
        return questionsByGid;
    }

    public void setQuestionsByGid(Collection<Question> questionsByGid) {
        this.questionsByGid = questionsByGid;
    }
}
