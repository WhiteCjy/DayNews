package com.group.daynews.bean;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class TopDataBean {

    /*
            "document_id":40854,
            "display_type":1,
            "title":"真人秀专职洗白？可能没那么容易！",
            "image":"http://ww3.sinaimg.cn/large/005NwfMxjw1fb3yok50adj30hs0hsmzc.jpg",
            "thumbnail":"http://ww4.sinaimg.cn/large/005OovnYjw1fb3yokjxvfj303c03c0so.jpg",
            "author_avatar":"http://ww4.sinaimg.cn/small/0061W1jvjw1f95hoo1m80j31hc0u00xb.jpg",
            "author_name":"鲤食白鹭",
            "author_id":820490,
            "section_id":58,
            "share_url":"http://baozouribao.com/documents/40854",
            "url":"http://dailyapi.ibaozou.com/api/v31/documents/40854",
            "hit_count":1596,
            "section_name":"暴走娱乐资讯",
            "section_image":"http://ww3.sinaimg.cn/small/00621Qfrjw1ewkb8dmt0bj30f00a0q3u.jpg",
            "section_color":"7CD095",
            "hit_count_string":"1千",
            "timestamp":1482732028,
            "comment_count":54,
            "vote_count":68
     */

    private int document_id;
    private int display_type;
    private String image;
    private String title;
    private String thumbnail;
    private String author_avatar;
    private String author_name;
    private String share_url;
    private String url;
    private int comment_count;
    private int vote_count;
    private int author_id;
    private int section_id;
    private int hit_count;
    private String section_name;
    private String section_image;
    private String section_color;
    private String hit_count_string;
    private int timestamp;
    private int type;

    public TopDataBean() {
    }

    public TopDataBean(String author_avatar, int author_id, String author_name, int display_type, int comment_count, int document_id, int hit_count, String hit_count_string, String image, String section_color, int section_id, String section_image, String section_name, String share_url, String thumbnail, int timestamp, String title, int type, String url, int vote_count) {
        this.author_avatar = author_avatar;
        this.author_id = author_id;
        this.author_name = author_name;
        this.display_type = display_type;
        this.comment_count = comment_count;
        this.document_id = document_id;
        this.hit_count = hit_count;
        this.hit_count_string = hit_count_string;
        this.image = image;
        this.section_color = section_color;
        this.section_id = section_id;
        this.section_image = section_image;
        this.section_name = section_name;
        this.share_url = share_url;
        this.thumbnail = thumbnail;
        this.timestamp = timestamp;
        this.title = title;
        this.type = type;
        this.url = url;
        this.vote_count = vote_count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAuthor_avatar() {
        return author_avatar;
    }

    public void setAuthor_avatar(String author_avatar) {
        this.author_avatar = author_avatar;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(int display_type) {
        this.display_type = display_type;
    }

    public int getDocument_id() {
        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public int getHit_count() {
        return hit_count;
    }

    public void setHit_count(int hit_count) {
        this.hit_count = hit_count;
    }

    public String getHit_count_string() {
        return hit_count_string;
    }

    public void setHit_count_string(String hit_count_string) {
        this.hit_count_string = hit_count_string;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSection_color() {
        return section_color;
    }

    public void setSection_color(String section_color) {
        this.section_color = section_color;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public String getSection_image() {
        return section_image;
    }

    public void setSection_image(String section_image) {
        this.section_image = section_image;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopDataBean bean = (TopDataBean) o;

        if (title != null ? !title.equals(bean.title) : bean.title != null) return false;
        return author_name != null ? author_name.equals(bean.author_name) : bean.author_name == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author_name != null ? author_name.hashCode() : 0);
        return result;
    }
}
