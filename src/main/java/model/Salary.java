package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
public class Salary {
    private int id;
    private Integer pid;
    private String item;
    private Double salary;
    private Timestamp updateAt;
    private ProfessionCategory professionCategoryByPid;

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
    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "item")
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Basic
    @Column(name = "salary")
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "updateAt")
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salary salary1 = (Salary) o;

        if (id != salary1.id) return false;
        if (pid != null ? !pid.equals(salary1.pid) : salary1.pid != null) return false;
        if (item != null ? !item.equals(salary1.item) : salary1.item != null) return false;
        if (salary != null ? !salary.equals(salary1.salary) : salary1.salary != null) return false;
        if (updateAt != null ? !updateAt.equals(salary1.updateAt) : salary1.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", insertable = false, updatable = false)
    public ProfessionCategory getProfessionCategoryByPid() {
        return professionCategoryByPid;
    }

    public void setProfessionCategoryByPid(ProfessionCategory professionCategoryByPid) {
        this.professionCategoryByPid = professionCategoryByPid;
    }
}
