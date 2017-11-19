package vo;

import model.CooperativeCategory;

import java.sql.Timestamp;

public class CooperativeCategoryVO {
    private int id;
    private String category;
    private String subclassification;
    private Timestamp updateAt;

    public CooperativeCategoryVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public CooperativeCategory toEntity(){
        CooperativeCategory cooperativeCategory=new CooperativeCategory();
        cooperativeCategory.setId(id);
        cooperativeCategory.setCategory(category);
        cooperativeCategory.setSubclassification(subclassification);
        updateAt=new Timestamp(System.currentTimeMillis());
        cooperativeCategory.setUpdateAt(updateAt);
        return cooperativeCategory;
    }

    public void update(CooperativeCategory cooperativeCategory){
        id=cooperativeCategory.getId();
        category=cooperativeCategory.getCategory();
        subclassification=cooperativeCategory.getSubclassification();
        updateAt=cooperativeCategory.getUpdateAt();
    }
}
