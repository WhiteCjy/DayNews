package com.group.daynews.bean;

/**
 * Created by CJY on 2016/12/29.
 */

public class RankDataBean {
    private String document_id;
    private String display_type;
    private String title;
    private String image;
    private String video_file_url;
    private String thumbnail;
    private String author_avatar;
    private String author_name;
    private String share_image;
    private String key_words;
    private String video_image_url;
    private String section_id;
    private String display_date;
    private String ga_prefix;
    private String share_url;
    private String section_name;
    private String section_color;
    private String section_image;
    private String author_summary;

    public RankDataBean(String author_avatar, String author_name, String author_summary, String display_date, String document_id, String display_type, String ga_prefix, String image, String key_words, String section_color, String section_id, String section_image, String section_name, String share_image, String share_url,
                        String thumbnail, String title, String video_file_url, String video_image_url) {
        this.author_avatar = author_avatar;
        this.author_name = author_name;
        this.author_summary = author_summary;
        this.display_date = display_date;
        this.document_id = document_id;
        this.display_type = display_type;
        this.ga_prefix = ga_prefix;
        this.image = image;
        this.key_words = key_words;
        this.section_color = section_color;
        this.section_id = section_id;
        this.section_image = section_image;
        this.section_name = section_name;
        this.share_image = share_image;
        this.share_url = share_url;
        this.thumbnail = thumbnail;
        this.title = title;
        this.video_file_url = video_file_url;
        this.video_image_url = video_image_url;
    }

    public String getAuthor_avatar() {
        return author_avatar;
    }

    public void setAuthor_avatar(String author_avatar) {
        this.author_avatar = author_avatar;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_summary() {
        return author_summary;
    }

    public void setAuthor_summary(String author_summary) {
        this.author_summary = author_summary;
    }

    public String getDisplay_date() {
        return display_date;
    }

    public void setDisplay_date(String display_date) {
        this.display_date = display_date;
    }

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey_words() {
        return key_words;
    }

    public void setKey_words(String key_words) {
        this.key_words = key_words;
    }

    public String getSection_color() {
        return section_color;
    }

    public void setSection_color(String section_color) {
        this.section_color = section_color;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
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

    public String getShare_image() {
        return share_image;
    }

    public void setShare_image(String share_image) {
        this.share_image = share_image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo_file_url() {
        return video_file_url;
    }

    public void setVideo_file_url(String video_file_url) {
        this.video_file_url = video_file_url;
    }

    public String getVideo_image_url() {
        return video_image_url;
    }

    public void setVideo_image_url(String video_image_url) {
        this.video_image_url = video_image_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RankDataBean that = (RankDataBean) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return share_url != null ? share_url.equals(that.share_url) : that.share_url == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (share_url != null ? share_url.hashCode() : 0);
        return result;
    }
}
