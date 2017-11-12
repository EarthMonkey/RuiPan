package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
@Table(name = "application_element", schema = "ruipan", catalog = "")
public class ApplicationElement {
    private int id;
    private Integer gid;
    private String category;
    private String synopsis;
    private String textPath;
    private Integer flag;
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
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

        ApplicationElement that = (ApplicationElement) o;

        if (id != that.id) return false;
        if (gid != null ? !gid.equals(that.gid) : that.gid != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (synopsis != null ? !synopsis.equals(that.synopsis) : that.synopsis != null) return false;
        if (textPath != null ? !textPath.equals(that.textPath) : that.textPath != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gid != null ? gid.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (synopsis != null ? synopsis.hashCode() : 0);
        result = 31 * result + (textPath != null ? textPath.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
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
