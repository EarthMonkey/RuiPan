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
    private String subject;
    private String score;
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
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "score")
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gid != null ? gid.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
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
