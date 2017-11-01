package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/1.
 */
@Entity
public class Honor {
    private int id;
    private String name;
    private String imagePath;
    private Timestamp getAt;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "getAt")
    public Timestamp getGetAt() {
        return getAt;
    }

    public void setGetAt(Timestamp getAt) {
        this.getAt = getAt;
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

        Honor honor = (Honor) o;

        if (id != honor.id) return false;
        if (name != null ? !name.equals(honor.name) : honor.name != null) return false;
        if (imagePath != null ? !imagePath.equals(honor.imagePath) : honor.imagePath != null) return false;
        if (getAt != null ? !getAt.equals(honor.getAt) : honor.getAt != null) return false;
        if (updateAt != null ? !updateAt.equals(honor.updateAt) : honor.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (getAt != null ? getAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
