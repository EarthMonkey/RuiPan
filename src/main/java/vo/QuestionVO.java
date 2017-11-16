package vo;

import model.Question;

import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/14.
 */
public class QuestionVO {
    private int id;
    private Integer gid;
    private String question;
    private String answer;
    private String isShow;
    private Timestamp createAt;

    public QuestionVO() {
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Question toEntity() {
        Question question = new Question();
        question.setId(id);
        question.setGid(gid);
        question.setQuestion(this.question);
        question.setAnswer(answer);
        question.setIsShow(isShow);
        createAt = new Timestamp(System.currentTimeMillis());
        question.setCreateAt(createAt);
        return question;
    }

    public void update(Question question) {
        id = question.getId();
        gid = question.getGid();
        this.question = question.getQuestion();
        answer = question.getAnswer();
        isShow = question.getIsShow();
        createAt = question.getCreateAt();
    }
}
