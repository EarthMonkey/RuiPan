package vo;

import model.HardCondition;

import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/8.
 */
public class HardConditionVO {

    private int id;
    private Integer gid;
    private String rank;
    private String subject;
    private String score;
    private Timestamp updateAt;

    public HardConditionVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public HardCondition toEntity() {
        HardCondition hardCondition = new HardCondition();
        hardCondition.setId(id);
        hardCondition.setGid(gid);
        hardCondition.setRank(rank);
        hardCondition.setSubject(subject);
        hardCondition.setScore(score);
        updateAt=new Timestamp(System.currentTimeMillis());
        hardCondition.setUpdateAt(updateAt);
        return hardCondition;
    }

    public void update(HardCondition hardCondition) {
        if (hardCondition != null) {
            id = hardCondition.getId();
            gid = hardCondition.getGid();
            rank = hardCondition.getRank();
            subject = hardCondition.getSubject();
            score = hardCondition.getScore();
            updateAt = hardCondition.getUpdateAt();
        }
    }

}
