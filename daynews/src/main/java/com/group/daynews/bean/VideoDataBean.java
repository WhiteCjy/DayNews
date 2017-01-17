package com.group.daynews.bean;

import java.io.Serializable;

/**
 * Created by CJY on 2016/12/27.
 */

public class VideoDataBean implements Serializable {
    /*
    *        "document_id":40930,
            "display_type":3,
            "play_time":536,
            "title":"《大明宫词》一派盛唐气韵，一曲爱情悲歌",
            "image":"http://baozouribao-qiniu.b0.upaiyun.com/ribaovideo/2016/12/1482812070719-xpmy72poszi5rkdc7djem7-6685d526aa0340937af31a1573d435a3",
            "play_count":22,
            "comment_count":0,
            "vote_count":1,
            "file_url":"http://gslb.miaopai.com/stream/3Tcf4~7T~caDb0e~06P2bA__.mp4",
            "share_url":"http://baozouribao.com/documents/40930",
            "publish_time":1482836400000,
            "play_count_string":"22"
    * */

    private String document_id;
    private String display_type;
    private String play_time;
    private String title;
    private String image;
    private String play_count;
    private String comment_count;
    private String vote_count;
    private String file_url;
    private String share_url;
    private String publish_time;
    private String play_count_string;

    public VideoDataBean(String comment_count, String display_type, String document_id, String file_url, String image, String play_count, String play_count_string, String play_time,
                         String publish_time, String share_url, String title, String vote_count) {
        this.comment_count = comment_count;
        this.display_type = display_type;
        this.document_id = document_id;
        this.file_url = file_url;
        this.image = image;
        this.play_count = play_count;
        this.play_count_string = play_count_string;
        this.play_time = play_time;
        this.publish_time = publish_time;
        this.share_url = share_url;
        this.title = title;
        this.vote_count = vote_count;
    }


    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
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

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlay_count() {
        return play_count;
    }

    public void setPlay_count(String play_count) {
        this.play_count = play_count;
    }

    public String getPlay_count_string() {
        return play_count_string;
    }

    public void setPlay_count_string(String play_count_string) {
        this.play_count_string = play_count_string;
    }

    public String getPlay_time() {
        return play_time;
    }

    public void setPlay_time(String play_time) {
        this.play_time = play_time;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
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

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    @Override
    public String toString() {
        return "VideoDataBean{" +
                "comment_count='" + comment_count + '\'' +
                ", document_id='" + document_id + '\'' +
                ", display_type='" + display_type + '\'' +
                ", play_time='" + play_time + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", play_count='" + play_count + '\'' +
                ", vote_count='" + vote_count + '\'' +
                ", file_url='" + file_url + '\'' +
                ", share_url='" + share_url + '\'' +
                ", publish_time='" + publish_time + '\'' +
                ", play_count_string='" + play_count_string + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoDataBean that = (VideoDataBean) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return file_url != null ? file_url.equals(that.file_url) : that.file_url == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (file_url != null ? file_url.hashCode() : 0);
        return result;
    }
}
