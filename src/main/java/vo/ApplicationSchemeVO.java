package vo;

import model.ApplicationScheme;
import model.GradeCategory;

import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/1.
 */

public class ApplicationSchemeVO {
    private int id;
    private Integer gid;
    private String subdivisionGrade;
    private String title;
    private String synopsis;
    private String textPath;
    private Integer flag;
    private Timestamp updateAt;

    public ApplicationSchemeVO() {
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

    public String getSubdivisionGrade() {
        return subdivisionGrade;
    }

    public void setSubdivisionGrade(String subdivisionGrade) {
        this.subdivisionGrade = subdivisionGrade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTextPath() {
        return textPath;
    }

    public void setTextPath(String textPath) {
        this.textPath = textPath;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public ApplicationScheme toEntity() {
        ApplicationScheme applicationScheme = new ApplicationScheme();
        applicationScheme.setId(id);
        applicationScheme.setGid(gid);
        applicationScheme.setSubdivisionGrade(subdivisionGrade);
        applicationScheme.setTitle(title);
        applicationScheme.setSynopsis(synopsis);
        applicationScheme.setTextPath(textPath);
        applicationScheme.setFlag(flag);
        updateAt=new Timestamp(System.currentTimeMillis());
        applicationScheme.setUpdateAt(updateAt);
        return applicationScheme;
    }

    public void update(ApplicationScheme applicationScheme) {
        id = applicationScheme.getId();
        gid = applicationScheme.getGid();
        subdivisionGrade = applicationScheme.getSubdivisionGrade();
        title = applicationScheme.getTitle();
        synopsis = applicationScheme.getSynopsis();
        textPath = applicationScheme.getTextPath();
        flag = applicationScheme.getFlag();
        updateAt = applicationScheme.getUpdateAt();
    }
}
