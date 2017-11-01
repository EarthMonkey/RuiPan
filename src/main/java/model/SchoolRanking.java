package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/1.
 */
@Entity
@Table(name = "school_ranking", schema = "ruipan", catalog = "")
public class SchoolRanking {
    private int id;
    private Integer pid;
    private Integer sid;
    private String scoreRequirements;
    private String applicationDifficulty;
    private Integer ranking;
    private Timestamp updateAt;
    private ProfessionCategory professionCategoryByPid;
    private School schoolBySid;

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
    @Column(name = "score_requirements")
    public String getScoreRequirements() {
        return scoreRequirements;
    }

    public void setScoreRequirements(String scoreRequirements) {
        this.scoreRequirements = scoreRequirements;
    }

    @Basic
    @Column(name = "application_difficulty")
    public String getApplicationDifficulty() {
        return applicationDifficulty;
    }

    public void setApplicationDifficulty(String applicationDifficulty) {
        this.applicationDifficulty = applicationDifficulty;
    }

    @Basic
    @Column(name = "ranking")
    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
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

        SchoolRanking that = (SchoolRanking) o;

        if (id != that.id) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (sid != null ? !sid.equals(that.sid) : that.sid != null) return false;
        if (scoreRequirements != null ? !scoreRequirements.equals(that.scoreRequirements) : that.scoreRequirements != null)
            return false;
        if (applicationDifficulty != null ? !applicationDifficulty.equals(that.applicationDifficulty) : that.applicationDifficulty != null)
            return false;
        if (ranking != null ? !ranking.equals(that.ranking) : that.ranking != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        result = 31 * result + (scoreRequirements != null ? scoreRequirements.hashCode() : 0);
        result = 31 * result + (applicationDifficulty != null ? applicationDifficulty.hashCode() : 0);
        result = 31 * result + (ranking != null ? ranking.hashCode() : 0);
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
}
