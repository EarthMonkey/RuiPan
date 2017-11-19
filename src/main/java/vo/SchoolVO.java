package vo;

import model.School;

import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/17.
 */
public class SchoolVO {

    private int sid;
    private String country;
    private String collegeName;
    private String schoolBadge;
    private String synopsis;
    private String address;
    private String officialWebsite;
    private String textPath;
    private Integer flag;
    private Timestamp updateAt;

    public SchoolVO() {
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getSchoolBadge() {
        return schoolBadge;
    }

    public void setSchoolBadge(String schoolBadge) {
        this.schoolBadge = schoolBadge;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOfficialWebsite() {
        return officialWebsite;
    }

    public void setOfficialWebsite(String officialWebsite) {
        this.officialWebsite = officialWebsite;
    }

    public String getTextPath() {
        return textPath;
    }

    public void setTextPath(String textPath) {
        this.textPath = textPath;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public School toEntity() {
        School school = new School();
        school.setSid(sid);
        school.setCountry(country);
        school.setCollegeName(collegeName);
        school.setSchoolBadge(schoolBadge);
        school.setSynopsis(synopsis);
        school.setAddress(address);
        school.setOfficialWebsite(officialWebsite);
        school.setTextPath(textPath);
        school.setFlag(flag);
        updateAt = new Timestamp(System.currentTimeMillis());
        school.setUpdateAt(updateAt);
        return school;
    }

    public void update(School school) {
        if (school != null) {
            sid = school.getSid();
            country=school.getCountry();
            collegeName = school.getCollegeName();
            schoolBadge = school.getSchoolBadge();
            synopsis = school.getSynopsis();
            address = school.getAddress();
            officialWebsite = school.getOfficialWebsite();
            textPath = school.getTextPath();
            flag = school.getFlag();
            updateAt = school.getUpdateAt();
        }
    }
}
