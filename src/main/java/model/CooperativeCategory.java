package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
@Table(name = "cooperative_category", schema = "ruipan", catalog = "")
public class CooperativeCategory {
    private int id;
    private String category;
    private String subclassification;
    private Timestamp updateAt;
    private Collection<CooperativeScheme> cooperativeSchemesById;

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

        CooperativeCategory that = (CooperativeCategory) o;

        if (id != that.id) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (subclassification != null ? !subclassification.equals(that.subclassification) : that.subclassification != null)
            return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (subclassification != null ? subclassification.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "cooperativeCategoryByCcid")
    public Collection<CooperativeScheme> getCooperativeSchemesById() {
        return cooperativeSchemesById;
    }

    public void setCooperativeSchemesById(Collection<CooperativeScheme> cooperativeSchemesById) {
        this.cooperativeSchemesById = cooperativeSchemesById;
    }
}
