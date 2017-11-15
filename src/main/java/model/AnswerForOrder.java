package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/15.
 */
@Entity
@Table(name = "answer_for_order", schema = "ruipan", catalog = "")
public class AnswerForOrder {
    private int id;
    private Integer oid;
    private String answer;
    private Timestamp creatAt;

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
    @Column(name = "oid")
    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
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
    @Column(name = "creatAt")
    public Timestamp getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Timestamp creatAt) {
        this.creatAt = creatAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerForOrder that = (AnswerForOrder) o;

        if (id != that.id) return false;
        if (oid != null ? !oid.equals(that.oid) : that.oid != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (creatAt != null ? !creatAt.equals(that.creatAt) : that.creatAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (oid != null ? oid.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (creatAt != null ? creatAt.hashCode() : 0);
        return result;
    }
}
