package vo;

import model.School;
import model.SchoolRanking;

import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/17.
 */
public class SchoolRankingVO {
    private int id;
    private Integer pid;
    private Integer sid;
    private String scoreRequirements;
    private String applicationDifficulty;
    private Integer ranking;
    private Timestamp updateAt;
    private SchoolVO schoolBySid;

    public SchoolRankingVO() {
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

    public String getScoreRequirements() {
        return scoreRequirements;
    }

    public void setScoreRequirements(String scoreRequirements) {
        this.scoreRequirements = scoreRequirements;
    }

    public String getApplicationDifficulty() {
        return applicationDifficulty;
    }

    public void setApplicationDifficulty(String applicationDifficulty) {
        this.applicationDifficulty = applicationDifficulty;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public SchoolVO getSchoolBySid() {
        return schoolBySid;
    }

    public void setSchoolBySid(SchoolVO schoolBySid) {
        this.schoolBySid = schoolBySid;
    }

    public SchoolRanking toEntity() {
        SchoolRanking schoolRanking = new SchoolRanking();
        schoolRanking.setId(id);
        schoolRanking.setPid(pid);
        schoolRanking.setSid(sid);
        schoolRanking.setScoreRequirements(scoreRequirements);
        schoolRanking.setApplicationDifficulty(applicationDifficulty);
        schoolRanking.setRanking(ranking);
        updateAt = new Timestamp(System.currentTimeMillis());
        schoolRanking.setUpdateAt(updateAt);
        return schoolRanking;
    }

    public void update(SchoolRanking schoolRanking) {
        if(schoolRanking!=null) {
            id = schoolRanking.getId();
            pid = schoolRanking.getPid();
            sid = schoolRanking.getSid();
            scoreRequirements = schoolRanking.getScoreRequirements();
            applicationDifficulty = schoolRanking.getApplicationDifficulty();
            ranking = schoolRanking.getRanking();
            updateAt = schoolRanking.getUpdateAt();
            if (schoolRanking.getSchoolBySid() != null) {
                schoolBySid = new SchoolVO();
                schoolBySid.update(schoolRanking.getSchoolBySid());
            }
        }
    }
}
