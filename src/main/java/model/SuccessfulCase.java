package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
@Table(name = "successful_case", schema = "ruipan", catalog = "")
public class SuccessfulCase {
    private int id;
    private Integer pid;
    private Integer sid;
    private Integer cid;
    private String name;
    private String degree;
    private String enrollmentTime;
    private String languageScore;
    private String gpa;
    private String gmatSatGre;
    private String undergraduateMajor;
    private String textPath;
    private Integer flag;
    private Timestamp updateAt;
    private ProfessionCategory professionCategoryByPid;
    private School schoolBySid;
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
    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "sid")
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "degree")
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Basic
    @Column(name = "enrollment_time")
    public String getEnrollmentTime() {
        return enrollmentTime;
    }

    public void setEnrollmentTime(String enrollmentTime) {
        this.enrollmentTime = enrollmentTime;
    }

    @Basic
    @Column(name = "language_score")
    public String getLanguageScore() {
        return languageScore;
    }

    public void setLanguageScore(String languageScore) {
        this.languageScore = languageScore;
    }

    @Basic
    @Column(name = "gpa")
    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    @Basic
    @Column(name = "gmat_sat_gre")
    public String getGmatSatGre() {
        return gmatSatGre;
    }

    public void setGmatSatGre(String gmatSatGre) {
        this.gmatSatGre = gmatSatGre;
    }

    @Basic
    @Column(name = "undergraduate_major")
    public String getUndergraduateMajor() {
        return undergraduateMajor;
    }

    public void setUndergraduateMajor(String undergraduateMajor) {
        this.undergraduateMajor = undergraduateMajor;
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
    @Column(name = "flag")
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

        SuccessfulCase that = (SuccessfulCase) o;

        if (id != that.id) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (sid != null ? !sid.equals(that.sid) : that.sid != null) return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
        if (degree != null ? !degree.equals(that.degree) : that.degree != null) return false;
        if (enrollmentTime != null ? !enrollmentTime.equals(that.enrollmentTime) : that.enrollmentTime != null)
            return false;
        if (languageScore != null ? !languageScore.equals(that.languageScore) : that.languageScore != null)
            return false;
        if (gpa != null ? !gpa.equals(that.gpa) : that.gpa != null) return false;
        if (gmatSatGre != null ? !gmatSatGre.equals(that.gmatSatGre) : that.gmatSatGre != null) return false;
        if (undergraduateMajor != null ? !undergraduateMajor.equals(that.undergraduateMajor) : that.undergraduateMajor != null)
            return false;
        if (textPath != null ? !textPath.equals(that.textPath) : that.textPath != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        result = 31 * result + (enrollmentTime != null ? enrollmentTime.hashCode() : 0);
        result = 31 * result + (languageScore != null ? languageScore.hashCode() : 0);
        result = 31 * result + (gpa != null ? gpa.hashCode() : 0);
        result = 31 * result + (gmatSatGre != null ? gmatSatGre.hashCode() : 0);
        result = 31 * result + (undergraduateMajor != null ? undergraduateMajor.hashCode() : 0);
        result = 31 * result + (textPath != null ? textPath.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "sid", referencedColumnName = "sid", insertable = false, updatable = false)
    public School getSchoolBySid() {
        return schoolBySid;
    }

    public void setSchoolBySid(School schoolBySid) {
        this.schoolBySid = schoolBySid;
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
