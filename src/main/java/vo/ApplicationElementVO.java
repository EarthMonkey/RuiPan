package vo;

import model.ApplicationElement;

import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/14.
 */
public class ApplicationElementVO {

    private int id;
    private Integer gid;
    private String category;
    private String synopsis;
    private String textPath;
    private Integer flag;
    private Timestamp updateAt;

    public ApplicationElementVO() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public ApplicationElement toEntity() {
        ApplicationElement applicationElement = new ApplicationElement();
        applicationElement.setId(id);
        applicationElement.setGid(gid);
        applicationElement.setCategory(category);
        applicationElement.setSynopsis(synopsis);
        applicationElement.setTextPath(textPath);
        applicationElement.setFlag(flag);
        updateAt = new Timestamp(System.currentTimeMillis());
        applicationElement.setUpdateAt(updateAt);
        return applicationElement;
    }

    public void update(ApplicationElement applicationElement) {
        if (applicationElement != null) {
            id = applicationElement.getId();
            gid = applicationElement.getGid();
            category = applicationElement.getCategory();
            synopsis = applicationElement.getSynopsis();
            textPath = applicationElement.getTextPath();
//        flag=applicationElement.getFlag();
            updateAt = applicationElement.getUpdateAt();
        }
    }
}
