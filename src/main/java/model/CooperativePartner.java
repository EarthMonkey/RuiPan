package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/1.
 */
@Entity
@Table(name = "cooperative_partner", schema = "ruipan", catalog = "")
public class CooperativePartner {
    private int id;
    private String category;
    private String name;
    private String country;
    private Timestamp cooperateAt;
    private String imagePath;
    private String birefIntroduce;
    private Timestamp updateAt;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "cooperateAt")
    public Timestamp getCooperateAt() {
        return cooperateAt;
    }

    public void setCooperateAt(Timestamp cooperateAt) {
        this.cooperateAt = cooperateAt;
    }

    @Basic
    @Column(name = "image_path")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Basic
    @Column(name = "biref_introduce")
    public String getBirefIntroduce() {
        return birefIntroduce;
    }

    public void setBirefIntroduce(String birefIntroduce) {
        this.birefIntroduce = birefIntroduce;
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

        CooperativePartner that = (CooperativePartner) o;

        if (id != that.id) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (cooperateAt != null ? !cooperateAt.equals(that.cooperateAt) : that.cooperateAt != null) return false;
        if (imagePath != null ? !imagePath.equals(that.imagePath) : that.imagePath != null) return false;
        if (birefIntroduce != null ? !birefIntroduce.equals(that.birefIntroduce) : that.birefIntroduce != null)
            return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (cooperateAt != null ? cooperateAt.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (birefIntroduce != null ? birefIntroduce.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
