package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/5.
 */
@Entity
public class Logiptopreventdos {
    private int id;
    private String ip;
    private String action;
    private Timestamp actionAt;

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
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Basic
    @Column(name = "actionAt")
    public Timestamp getActionAt() {
        return actionAt;
    }

    public void setActionAt(Timestamp actionAt) {
        this.actionAt = actionAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logiptopreventdos that = (Logiptopreventdos) o;

        if (id != that.id) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (actionAt != null ? !actionAt.equals(that.actionAt) : that.actionAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (actionAt != null ? actionAt.hashCode() : 0);
        return result;
    }
}
