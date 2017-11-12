package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
public class School {
    private int sid;
    private String collegeName;
    private String schoolBadge;
    private String synopsis;
    private String address;
    private String officialWebsite;
    private String textPath;
    private Integer flag;
    private Timestamp updateAt;
    private Collection<SchoolPicture> schoolPicturesBySid;
    private Collection<SchoolRanking> schoolRankingsBySid;
    private Collection<SuccessfulCase> successfulCasesBySid;

    @Id
    @Column(name = "sid")

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "college_name")
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Basic
    @Column(name = "school_badge")
    public String getSchoolBadge() {
        return schoolBadge;
    }

    public void setSchoolBadge(String schoolBadge) {
        this.schoolBadge = schoolBadge;
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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "official_website")
    public String getOfficialWebsite() {
        return officialWebsite;
    }

    public void setOfficialWebsite(String officialWebsite) {
        this.officialWebsite = officialWebsite;
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

        School school = (School) o;

        if (sid != school.sid) return false;
        if (collegeName != null ? !collegeName.equals(school.collegeName) : school.collegeName != null) return false;
        if (schoolBadge != null ? !schoolBadge.equals(school.schoolBadge) : school.schoolBadge != null) return false;
        if (synopsis != null ? !synopsis.equals(school.synopsis) : school.synopsis != null) return false;
        if (address != null ? !address.equals(school.address) : school.address != null) return false;
        if (officialWebsite != null ? !officialWebsite.equals(school.officialWebsite) : school.officialWebsite != null)
            return false;
        if (textPath != null ? !textPath.equals(school.textPath) : school.textPath != null) return false;
        if (flag != null ? !flag.equals(school.flag) : school.flag != null) return false;
        if (updateAt != null ? !updateAt.equals(school.updateAt) : school.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid;
        result = 31 * result + (collegeName != null ? collegeName.hashCode() : 0);
        result = 31 * result + (schoolBadge != null ? schoolBadge.hashCode() : 0);
        result = 31 * result + (synopsis != null ? synopsis.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (officialWebsite != null ? officialWebsite.hashCode() : 0);
        result = 31 * result + (textPath != null ? textPath.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "schoolBySid")
    public Collection<SchoolPicture> getSchoolPicturesBySid() {
        return schoolPicturesBySid;
    }

    public void setSchoolPicturesBySid(Collection<SchoolPicture> schoolPicturesBySid) {
        this.schoolPicturesBySid = schoolPicturesBySid;
    }

    @OneToMany(mappedBy = "schoolBySid")
    public Collection<SchoolRanking> getSchoolRankingsBySid() {
        return schoolRankingsBySid;
    }

    public void setSchoolRankingsBySid(Collection<SchoolRanking> schoolRankingsBySid) {
        this.schoolRankingsBySid = schoolRankingsBySid;
    }

    @OneToMany(mappedBy = "schoolBySid")
    public Collection<SuccessfulCase> getSuccessfulCasesBySid() {
        return successfulCasesBySid;
    }

    public void setSuccessfulCasesBySid(Collection<SuccessfulCase> successfulCasesBySid) {
        this.successfulCasesBySid = successfulCasesBySid;
    }
}
