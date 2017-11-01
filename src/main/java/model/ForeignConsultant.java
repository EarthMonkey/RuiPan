package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by ldchao on 2017/11/1.
 */
@Entity
@Table(name = "foreign_consultant", schema = "ruipan", catalog = "")
public class ForeignConsultant {
    private int id;
    private Integer gid;
    private String name;
    private String headSculpture;
    private String college;
    private String profession;
    private String briefIntroduce;
    private Timestamp updateAt;
    private GradeCategory gradeCategoryByGid;
    private Collection<OrderForForeign> orderForForeignsById;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "head_sculpture")
    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    @Basic
    @Column(name = "college")
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Basic
    @Column(name = "profession")
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Basic
    @Column(name = "brief_introduce")
    public String getBriefIntroduce() {
        return briefIntroduce;
    }

    public void setBriefIntroduce(String briefIntroduce) {
        this.briefIntroduce = briefIntroduce;
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

        ForeignConsultant that = (ForeignConsultant) o;

        if (id != that.id) return false;
        if (gid != null ? !gid.equals(that.gid) : that.gid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (headSculpture != null ? !headSculpture.equals(that.headSculpture) : that.headSculpture != null)
            return false;
        if (college != null ? !college.equals(that.college) : that.college != null) return false;
        if (profession != null ? !profession.equals(that.profession) : that.profession != null) return false;
        if (briefIntroduce != null ? !briefIntroduce.equals(that.briefIntroduce) : that.briefIntroduce != null)
            return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gid != null ? gid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (headSculpture != null ? headSculpture.hashCode() : 0);
        result = 31 * result + (college != null ? college.hashCode() : 0);
        result = 31 * result + (profession != null ? profession.hashCode() : 0);
        result = 31 * result + (briefIntroduce != null ? briefIntroduce.hashCode() : 0);
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

    @OneToMany(mappedBy = "foreignConsultantByFid")
    public Collection<OrderForForeign> getOrderForForeignsById() {
        return orderForForeignsById;
    }

    public void setOrderForForeignsById(Collection<OrderForForeign> orderForForeignsById) {
        this.orderForForeignsById = orderForForeignsById;
    }
}
