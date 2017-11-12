package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
public class Logentity {
    private int id;
    private String username;
    private String module;
    private String method;
    private String ip;
    private Integer responsetime;
    private Timestamp time;
    private String commite;

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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "module")
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Basic
    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
    @Column(name = "responsetime")
    public Integer getResponsetime() {
        return responsetime;
    }

    public void setResponsetime(Integer responsetime) {
        this.responsetime = responsetime;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "commite")
    public String getCommite() {
        return commite;
    }

    public void setCommite(String commite) {
        this.commite = commite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logentity logentity = (Logentity) o;

        if (id != logentity.id) return false;
        if (username != null ? !username.equals(logentity.username) : logentity.username != null) return false;
        if (module != null ? !module.equals(logentity.module) : logentity.module != null) return false;
        if (method != null ? !method.equals(logentity.method) : logentity.method != null) return false;
        if (ip != null ? !ip.equals(logentity.ip) : logentity.ip != null) return false;
        if (responsetime != null ? !responsetime.equals(logentity.responsetime) : logentity.responsetime != null)
            return false;
        if (time != null ? !time.equals(logentity.time) : logentity.time != null) return false;
        if (commite != null ? !commite.equals(logentity.commite) : logentity.commite != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (module != null ? module.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (responsetime != null ? responsetime.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (commite != null ? commite.hashCode() : 0);
        return result;
    }
}
