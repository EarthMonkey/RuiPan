package vo;

import constant.GlobalRecommedationCategory;
import constant.StatesConstant;
import model.GlobalRecommendation;

import java.sql.Timestamp;

public class RecommendSuccessfulCase {
    private int id;
    private String slogan;
    private Integer rid;
    private Timestamp recommendAt;
    private SuccessfulCaseVO successfulCaseVO;

    public RecommendSuccessfulCase() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
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

    public SuccessfulCaseVO getSuccessfulCaseVO() {
        return successfulCaseVO;
    }

    public void setSuccessfulCaseVO(SuccessfulCaseVO successfulCaseVO) {
        this.successfulCaseVO = successfulCaseVO;
    }

    public GlobalRecommendation toEntity(){
        GlobalRecommendation globalRecommendation = new GlobalRecommendation();
        globalRecommendation.setId(id);
        globalRecommendation.setCategory(GlobalRecommedationCategory.CASE);
        globalRecommendation.setSlogan(slogan);
        globalRecommendation.setRid(rid);
        recommendAt = new Timestamp(System.currentTimeMillis());
        globalRecommendation.setRecommendAt(recommendAt);
        globalRecommendation.setFlag(StatesConstant.RECOMMEND);
        return globalRecommendation;
    }

    public void update(GlobalRecommendation globalRecommendation) {
        if (globalRecommendation != null) {
            id = globalRecommendation.getId();
            slogan = globalRecommendation.getSlogan();
            rid = globalRecommendation.getRid();
            recommendAt = globalRecommendation.getRecommendAt();
        }
    }
}
