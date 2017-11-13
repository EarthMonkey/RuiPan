package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
@Table(name = "profession_introduce", schema = "ruipan", catalog = "")
public class ProfessionIntroduce {
    private int pid;
    private String detailSynopsis;
    private String detailTextPath;
    private String applicationAdvice;
    private Integer flag;
    private Timestamp updateAt;
    private ProfessionCategory professionCategoryByPid;

    @Id
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "detail_synopsis")
    public String getDetailSynopsis() {
        return detailSynopsis;
    }

    public void setDetailSynopsis(String detailSynopsis) {
        this.detailSynopsis = detailSynopsis;
    }

    @Basic
    @Column(name = "detail_text_path")
    public String getDetailTextPath() {
        return detailTextPath;
    }

    public void setDetailTextPath(String detailTextPath) {
        this.detailTextPath = detailTextPath;
    }

    @Basic
    @Column(name = "application_advice")
    public String getApplicationAdvice() {
        return applicationAdvice;
    }

    public void setApplicationAdvice(String applicationAdvice) {
        this.applicationAdvice = applicationAdvice;
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

        ProfessionIntroduce that = (ProfessionIntroduce) o;

        if (pid != that.pid) return false;
        if (detailSynopsis != null ? !detailSynopsis.equals(that.detailSynopsis) : that.detailSynopsis != null)
            return false;
        if (detailTextPath != null ? !detailTextPath.equals(that.detailTextPath) : that.detailTextPath != null)
            return false;
        if (applicationAdvice != null ? !applicationAdvice.equals(that.applicationAdvice) : that.applicationAdvice != null)
            return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + (detailSynopsis != null ? detailSynopsis.hashCode() : 0);
        result = 31 * result + (detailTextPath != null ? detailTextPath.hashCode() : 0);
        result = 31 * result + (applicationAdvice != null ? applicationAdvice.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)
    public ProfessionCategory getProfessionCategoryByPid() {
        return professionCategoryByPid;
    }

    public void setProfessionCategoryByPid(ProfessionCategory professionCategoryByPid) {
        this.professionCategoryByPid = professionCategoryByPid;
    }
}
