package vo;

import constant.GlobalRecommedationCategory;
import constant.StatesConstant;
import model.GlobalRecommendation;

import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/9.
 */
public class RecommendApplicationScheme {
    private int id;
    private String picturePath;
    private Integer rid;
    private Timestamp recommendAt;
    private ApplicationSchemeVO applicationSchemeVO;

    public RecommendApplicationScheme() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Timestamp getRecommendAt() {
        return recommendAt;
    }

    public void setRecommendAt(Timestamp recommendAt) {
        this.recommendAt = recommendAt;
    }

    public ApplicationSchemeVO getApplicationSchemeVO() {
        return applicationSchemeVO;
    }

    public void setApplicationSchemeVO(ApplicationSchemeVO applicationSchemeVO) {
        this.applicationSchemeVO = applicationSchemeVO;
    }

    public GlobalRecommendation getModel(){
        GlobalRecommendation globalRecommendation=new GlobalRecommendation();
        globalRecommendation.setId(id);
        globalRecommendation.setCategory(GlobalRecommedationCategory.APPLICATION_SCHEME);
        globalRecommendation.setSlogan(picturePath);
        globalRecommendation.setRid(rid);
        recommendAt=new Timestamp(System.currentTimeMillis());
        globalRecommendation.setRecommendAt(recommendAt);
        globalRecommendation.setFlag(StatesConstant.RECOMMEND);
        return  globalRecommendation;
    }

    public void update(GlobalRecommendation globalRecommendation){
        id=globalRecommendation.getId();
        picturePath =globalRecommendation.getSlogan();
        rid=globalRecommendation.getRid();
        recommendAt=globalRecommendation.getRecommendAt();
    }
}
