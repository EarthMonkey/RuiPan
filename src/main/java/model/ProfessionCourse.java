package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
@Table(name = "profession_course", schema = "ruipan", catalog = "")
public class ProfessionCourse {
    private int id;
    private Integer pid;
    private String majorField;
    private String majorCourse;
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
    @Column(name = "major_field")
    public String getMajorField() {
        return majorField;
    }

    public void setMajorField(String majorField) {
        this.majorField = majorField;
    }

    @Basic
    @Column(name = "major_course")
    public String getMajorCourse() {
        return majorCourse;
    }

    public void setMajorCourse(String majorCourse) {
        this.majorCourse = majorCourse;
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

        ProfessionCourse that = (ProfessionCourse) o;

        if (id != that.id) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (majorField != null ? !majorField.equals(that.majorField) : that.majorField != null) return false;
        if (majorCourse != null ? !majorCourse.equals(that.majorCourse) : that.majorCourse != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (majorField != null ? majorField.hashCode() : 0);
        result = 31 * result + (majorCourse != null ? majorCourse.hashCode() : 0);
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
