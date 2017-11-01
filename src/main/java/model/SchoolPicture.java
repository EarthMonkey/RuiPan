package model;

import javax.persistence.*;

/**
 * Created by ldchao on 2017/11/1.
 */
@Entity
@Table(name = "school_picture", schema = "ruipan", catalog = "")
public class SchoolPicture {
    private int id;
    private Integer sid;
    private String picturePath;
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
    @Column(name = "sid")
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "picture_path")
    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchoolPicture that = (SchoolPicture) o;

        if (id != that.id) return false;
        if (sid != null ? !sid.equals(that.sid) : that.sid != null) return false;
        if (picturePath != null ? !picturePath.equals(that.picturePath) : that.picturePath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        result = 31 * result + (picturePath != null ? picturePath.hashCode() : 0);
        return result;
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
