package vo;

import model.SuccessfulCase;

import java.sql.Timestamp;

public class SuccessfulCaseVO {

    private int id;
    private Integer pid;
    private Integer sid;
    private Integer cid;
    private String schoolName;
    private String consultantName;
    private String name;
    private String degree;
    private String enrollmentTime;
    private String languageScore;
    private String gpa;
    private String gmatSatGre;
    private String undergraduateMajor;
    private String textPath;
    private Integer flag;
    private Timestamp updateAt;


    public SuccessfulCaseVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getEnrollmentTime() {
        return enrollmentTime;
    }

    public void setEnrollmentTime(String enrollmentTime) {
        this.enrollmentTime = enrollmentTime;
    }

    public String getLanguageScore() {
        return languageScore;
    }

    public void setLanguageScore(String languageScore) {
        this.languageScore = languageScore;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getGmatSatGre() {
        return gmatSatGre;
    }

    public void setGmatSatGre(String gmatSatGre) {
        this.gmatSatGre = gmatSatGre;
    }

    public String getUndergraduateMajor() {
        return undergraduateMajor;
    }

    public void setUndergraduateMajor(String undergraduateMajor) {
        this.undergraduateMajor = undergraduateMajor;
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

    public SuccessfulCase toEntity(){
        SuccessfulCase successfulCase=new SuccessfulCase();
        successfulCase.setId(id);
        successfulCase.setPid(pid);
        successfulCase.setSid(sid);
        successfulCase.setCid(cid);
        successfulCase.setName(name);
        successfulCase.setDegree(degree);
        successfulCase.setEnrollmentTime(enrollmentTime);
        successfulCase.setLanguageScore(languageScore);
        successfulCase.setGpa(gpa);
        successfulCase.setGmatSatGre(gmatSatGre);
        successfulCase.setUndergraduateMajor(undergraduateMajor);
        successfulCase.setTextPath(textPath);
        successfulCase.setFlag(flag);
        updateAt=new Timestamp(System.currentTimeMillis());
        successfulCase.setUpdateAt(updateAt);
        return successfulCase;
    }

    public void update(SuccessfulCase successfulCase){
        id=successfulCase.getId();
        pid=successfulCase.getPid();
        sid=successfulCase.getSid();
        cid=successfulCase.getCid();
        name=successfulCase.getName();
        degree=successfulCase.getDegree();
        enrollmentTime=successfulCase.getEnrollmentTime();
        languageScore=successfulCase.getLanguageScore();
        gpa=successfulCase.getGpa();
        gmatSatGre=successfulCase.getGmatSatGre();
        undergraduateMajor=successfulCase.getUndergraduateMajor();
        textPath=successfulCase.getTextPath();
        flag=successfulCase.getFlag();
        updateAt=successfulCase.getUpdateAt();
    }
}
