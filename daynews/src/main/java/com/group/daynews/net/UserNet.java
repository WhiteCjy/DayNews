package com.group.daynews.net;


import com.group.daynews.bean.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class UserNet {
    public static String timestamp;
    private static ArrayList<User> userNets = new ArrayList<>();

    public static ArrayList<User> getData(String data) {
        try {
            userNets = parseJson(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userNets;
    }

    public static ArrayList<User> parseJson(String json) throws JSONException {
        final ArrayList<User> userNetList = new ArrayList<User>();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String title = obj.getString("title");
            String author_name = obj.getString("author_name");
            String author_avatar = obj.getString("author_avatar");
            User user = new User(author_avatar, title, author_name);
            userNetList.add(user);
        }
        timestamp = jsonObject.getString("timestamp");
        return userNetList;
    }
}
