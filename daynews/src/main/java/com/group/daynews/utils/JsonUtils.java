package com.group.daynews.utils;

import com.group.daynews.bean.RankDataBean;
import com.group.daynews.bean.SideChannelBean;
import com.group.daynews.bean.TopDataBean;
import com.group.daynews.bean.VideoDataBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/27 0027.
 */

public class JsonUtils {
    public static int timestamp_video = 0;

    public static int home_timestamp = 0;

    public static ArrayList<TopDataBean> getTopList(String s) {
        ArrayList<TopDataBean> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.has("top_stories")) {
                JSONArray top_stories = jsonObject.getJSONArray("top_stories");
                for (int i = 0; i < top_stories.length(); i++) {
                    JSONObject js = top_stories.getJSONObject(i);
                    TopDataBean bean = new TopDataBean();
                    bean.setTitle(js.getString("title"));
                    bean.setImage(js.getString("image"));
                    bean.setThumbnail(js.getString("thumbnail"));
                    bean.setAuthor_avatar(js.getString("author_avatar"));
                    bean.setAuthor_name(js.getString("author_name"));
                    String share_url = "";
                    if (js.has("share_url")) {
                        share_url = js.getString("share_url");
                    }
                    bean.setShare_url(share_url);
                    String url = "";
                    if (js.has("url")) {
                        url = js.getString("url");
                    }
                    bean.setUrl(url);
                    bean.setComment_count(js.getInt("comment_count"));
                    bean.setVote_count(js.getInt("vote_count"));
                    bean.setType(0);
                    list.add(bean);
                }
            }

            JSONArray data = jsonObject.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject js = data.getJSONObject(i);
                TopDataBean bean = new TopDataBean();
                bean.setTitle(js.getString("title"));
                bean.setThumbnail(js.getString("thumbnail"));
                bean.setAuthor_avatar(js.getString("author_avatar"));
                bean.setAuthor_name(js.getString("author_name"));
                String share_url = "";
                if (js.has("share_url")) {
                    share_url = js.getString("share_url");
                }
                bean.setShare_url(share_url);
                bean.setUrl(js.getString("url"));
                bean.setComment_count(js.getInt("comment_count"));
                bean.setVote_count(js.getInt("vote_count"));
                String section_name = "";
                if (js.has("section_name")) {
                    section_name = js.getString("section_name");
                }
                bean.setSection_name(section_name);
                bean.setType(1);
                list.add(bean);
            }
            home_timestamp = jsonObject.getInt("timestamp");
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<VideoDataBean> getVideoList(String json) {
        ArrayList<VideoDataBean> data = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject js = jsonArray.getJSONObject(i);
                String document_id = js.getString("document_id");
                String display_type = js.getString("display_type");
                String title = js.getString("title");
                String image = js.getString("image");
                String play_time = js.getString("play_time");
                String play_count = js.getString("play_count");
                String comment_count = js.getString("comment_count");
                String vote_count = js.getString("vote_count");
                String file_url = js.getString("file_url");
                String share_url = js.getString("share_url");
                String publish_time = js.getString("publish_time");
                String play_count_string = js.getString("play_count_string");
                VideoDataBean videoBean = new VideoDataBean(comment_count, display_type, document_id, file_url, image, play_count, play_count_string, play_time,
                        publish_time, share_url, title, vote_count);
                data.add(videoBean);
                System.out.println(videoBean);
            }
            timestamp_video = jsonObject.getInt("timestamp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static ArrayList<SideChannelBean> getSideChannelList(String s) {
        ArrayList<SideChannelBean> list = new ArrayList<>();
        try {
            JSONArray data = new JSONObject(s).getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject js = data.getJSONObject(i);

                int id = js.getInt("id");
                String name = js.getString("name");
                String summary = js.getString("summary");
                String thumbnail = js.getString("thumbnail");
                String image = js.getString("image");
                list.add(new SideChannelBean(id, image, name, summary, thumbnail));
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final List<RankDataBean> getRankList(String json) {
        ArrayList<RankDataBean> data = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonob = jsonArray.getJSONObject(i);
                String document_id = jsonob.getString("document_id");
                String display_type = jsonob.getString("display_type");
                String title = jsonob.getString("title");
                String image = jsonob.getString("image");
                String video_file_url = jsonob.getString("video_file_url");
                String thumbnail = jsonob.getString("thumbnail");
                String author_avatar = jsonob.getString("author_avatar");
                String author_name = jsonob.getString("author_name");
                String share_image = "";
                if (jsonob.has("share_image")) {
                    share_image = jsonob.getString("share_image");
                }
                String key_words = jsonob.getString("key_words");
                String video_image_url = jsonob.getString("video_image_url");
                String section_id = jsonob.getString("section_id");
                String display_date = jsonob.getString("display_date");
                String ga_prefix = jsonob.getString("ga_prefix");
                String share_url = jsonob.getString("share_url");
                String url = jsonob.getString("url");
                String section_name = jsonob.getString("section_name");
                String section_color = jsonob.getString("section_color");
                String section_image = jsonob.getString("section_image");
                String author_summary = jsonob.getString("author_summary");
                RankDataBean databean = new RankDataBean(author_avatar, author_name, author_summary,
                        display_date, document_id, display_type, ga_prefix,
                        image, key_words, section_color, section_id,
                        section_image, section_name, share_image, share_url,
                        thumbnail, title, video_file_url, video_image_url);
                data.add(databean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }


}
