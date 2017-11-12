package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
public class Question {
    private int id;
    private Integer gid;
    private String question;
    private String answer;
    private String isShow;
    private Timestamp createAt;
    private GradeCategory gradeCategoryByGid;

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
    @Column(name = "gid")
    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "isShow")
    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    @Basic
    @Column(name = "createAt")
    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (id != question1.id) return false;
        if (gid != null ? !gid.equals(question1.gid) : question1.gid != null) return false;
        if (question != null ? !question.equals(question1.question) : question1.question != null) return false;
        if (answer != null ? !answer.equals(question1.answer) : question1.answer != null) return false;
        if (isShow != null ? !isShow.equals(question1.isShow) : question1.isShow != null) return false;
        if (createAt != null ? !createAt.equals(question1.createAt) : question1.createAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gid != null ? gid.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (isShow != null ? isShow.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "gid", referencedColumnName = "gid", insertable = false, updatable = false)
    public GradeCategory getGradeCategoryByGid() {
        return gradeCategoryByGid;
    }

    public void setGradeCategoryByGid(GradeCategory gradeCategoryByGid) {
        this.gradeCategoryByGid = gradeCategoryByGid;
    }
}
