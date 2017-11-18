package vo;

import model.School;
import model.SchoolPicture;

/**
 * Created by ldchao on 2017/11/18.
 */
public class SchoolPictureVO {
    private int id;
    private Integer sid;
    private String picturePath;

    public SchoolPictureVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public SchoolPicture toEntity(){
        SchoolPicture schoolPicture=new SchoolPicture();
        schoolPicture.setId(id);
        schoolPicture.setSid(sid);
        schoolPicture.setPicturePath(picturePath);
        return schoolPicture;
    }

    public void update(SchoolPicture schoolPicture){
        if(schoolPicture!=null){
            id=schoolPicture.getId();
            sid=schoolPicture.getSid();
            picturePath=schoolPicture.getPicturePath();
        }
    }
}
