package com.group.daynews.bean;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class SideChannelBean {

    /*
    "id":3,
            "name":"全网最好笑",
            "summary":"搜罗网络上最好笑的内容，带给你最好笑的日报",
            "thumbnail":"http://ww3.sinaimg.cn/mw690/da4a9471tw1eqz3q6av98j203c03ct8q.jpg",
            "image":"http://ww2.sinaimg.cn/large/0062aae5jw1ewkb9nkir3j30f00a0q3e.jpg"
     */

    private int id;
    private String name;
    private String summary;
    private String thumbnail;
    private String image;

    public SideChannelBean(int id, String image, String name, String summary, String thumbnail) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.summary = summary;
        this.thumbnail = thumbnail;
    }

    public SideChannelBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SideChannelBean that = (SideChannelBean) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (thumbnail != null ? !thumbnail.equals(that.thumbnail) : that.thumbnail != null)
            return false;
        return image != null ? image.equals(that.image) : that.image == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
