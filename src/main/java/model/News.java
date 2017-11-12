package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ldchao on 2017/11/12.
 */
@Entity
public class News {
    private int id;
    private String category;
    private String title;
    private String synopsis;
    private Timestamp pulishTime;
    private String textPath;
    private Integer flag;

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
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "synopsis")
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Basic
    @Column(name = "pulish_time")
    public Timestamp getPulishTime() {
        return pulishTime;
    }

    public void setPulishTime(Timestamp pulishTime) {
        this.pulishTime = pulishTime;
    }

    @Basic
    @Column(name = "text_path")
    public String getTextPath() {
        return textPath;
    }

    public void setTextPath(String textPath) {
        this.textPath = textPath;
    }

    @Basic
    @Column(name = "flag")
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != news.id) return false;
        if (category != null ? !category.equals(news.category) : news.category != null) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (synopsis != null ? !synopsis.equals(news.synopsis) : news.synopsis != null) return false;
        if (pulishTime != null ? !pulishTime.equals(news.pulishTime) : news.pulishTime != null) return false;
        if (textPath != null ? !textPath.equals(news.textPath) : news.textPath != null) return false;
        if (flag != null ? !flag.equals(news.flag) : news.flag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (synopsis != null ? synopsis.hashCode() : 0);
        result = 31 * result + (pulishTime != null ? pulishTime.hashCode() : 0);
        result = 31 * result + (textPath != null ? textPath.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        return result;
    }
}
