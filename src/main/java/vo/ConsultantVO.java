package vo;

import model.Consultant;

import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/14.
 */
public class ConsultantVO {
    private int id;
    private String name;
    private String country;
    private String profession;
    private String headSculpture;
    private Double workingHours;
    private Integer offerNumber;
    private String applicationSuccessRate;
    private String synopsis;
    private String textPath;
    private String isRecommend;
    private Timestamp updateAt;

    public ConsultantVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    public Double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Double workingHours) {
        this.workingHours = workingHours;
    }

    public Integer getOfferNumber() {
        return offerNumber;
    }

    public void setOfferNumber(Integer offerNumber) {
        this.offerNumber = offerNumber;
    }

    public String getApplicationSuccessRate() {
        return applicationSuccessRate;
    }

    public void setApplicationSuccessRate(String applicationSuccessRate) {
        this.applicationSuccessRate = applicationSuccessRate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTextPath() {
        return textPath;
    }

    public void setTextPath(String textPath) {
        this.textPath = textPath;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public Consultant getModel(){
        Consultant consultant=new Consultant();
        consultant.setId(id);
        consultant.setName(name);
        consultant.setCountry(country);
        consultant.setProfession(profession);
        consultant.setHeadSculpture(headSculpture);
        consultant.setWorkingHours(workingHours);
        consultant.setOfferNumber(offerNumber);
        consultant.setApplicationSuccessRate(applicationSuccessRate);
        consultant.setSynopsis(synopsis);
        consultant.setTextPath(textPath);
        consultant.setIsRecommend(isRecommend);
        updateAt=new Timestamp(System.currentTimeMillis());
        consultant.setUpdateAt(updateAt);
        return consultant;
    }

    public void update(Consultant consultant){
        id=consultant.getId();
        name=consultant.getName();
        country=consultant.getCountry();
        profession=consultant.getProfession();
        headSculpture=consultant.getHeadSculpture();
        workingHours=consultant.getWorkingHours();
        offerNumber=consultant.getOfferNumber();
        applicationSuccessRate=consultant.getApplicationSuccessRate();
        synopsis=consultant.getSynopsis();
        textPath=consultant.getTextPath();
        isRecommend=consultant.getIsRecommend();
        updateAt=consultant.getUpdateAt();
    }
}
