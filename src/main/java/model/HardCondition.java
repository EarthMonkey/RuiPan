package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/1.
 */
@Entity
@Table(name = "hard_condition", schema = "ruipan", catalog = "")
public class HardCondition {
    private int id;
    private Integer gid;
    private String rank;
    private String tofel;
    private String gpa;
    private String gre;
    private String gmat;
    private Timestamp updateAt;
    private GradeCategory gradeCategoryByGid;

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
    @Column(name = "gid")
    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "rank")
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "TOFEL")
    public String getTofel() {
        return tofel;
    }

    public void setTofel(String tofel) {
        this.tofel = tofel;
    }

    @Basic
    @Column(name = "GPA")
    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    @Basic
    @Column(name = "GRE")
    public String getGre() {
        return gre;
    }

    public void setGre(String gre) {
        this.gre = gre;
    }

    @Basic
    @Column(name = "GMAT")
    public String getGmat() {
        return gmat;
    }

    public void setGmat(String gmat) {
        this.gmat = gmat;
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

        HardCondition that = (HardCondition) o;

        if (id != that.id) return false;
        if (gid != null ? !gid.equals(that.gid) : that.gid != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;
        if (tofel != null ? !tofel.equals(that.tofel) : that.tofel != null) return false;
        if (gpa != null ? !gpa.equals(that.gpa) : that.gpa != null) return false;
        if (gre != null ? !gre.equals(that.gre) : that.gre != null) return false;
        if (gmat != null ? !gmat.equals(that.gmat) : that.gmat != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gid != null ? gid.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (tofel != null ? tofel.hashCode() : 0);
        result = 31 * result + (gpa != null ? gpa.hashCode() : 0);
        result = 31 * result + (gre != null ? gre.hashCode() : 0);
        result = 31 * result + (gmat != null ? gmat.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "gid", referencedColumnName = "gid", insertable = false, updatable = false)
    public GradeCategory getGradeCategoryByGid() {
        return gradeCategoryByGid;
    }

    public void setGradeCategoryByGid(GradeCategory gradeCategoryByGid) {
        this.gradeCategoryByGid = gradeCategoryByGid;
    }
}
