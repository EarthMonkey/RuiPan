package vo;

import model.ProfessionCategory;

import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/15.
 */
public class ProfessionCategoryVO {
    private int pid;
    private String country;
    private String category;
    private String subclassification;
    private Timestamp createAt;

    public ProfessionCategoryVO() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubclassification() {
        return subclassification;
    }

    public void setSubclassification(String subclassification) {
        this.subclassification = subclassification;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public ProfessionCategory toEntity() {
        ProfessionCategory professionCategory = new ProfessionCategory();
        professionCategory.setPid(pid);
        professionCategory.setCountry(country);
        professionCategory.setCategory(category);
        professionCategory.setSubclassification(subclassification);
        createAt = new Timestamp(System.currentTimeMillis());
        professionCategory.setCreateAt(createAt);
        return professionCategory;
    }

    public void update(ProfessionCategory professionCategory) {
        if (professionCategory != null) {
            pid = professionCategory.getPid();
            country = professionCategory.getCountry();
            category = professionCategory.getCategory();
            subclassification = professionCategory.getSubclassification();
            createAt = professionCategory.getCreateAt();
        }
    }
}
