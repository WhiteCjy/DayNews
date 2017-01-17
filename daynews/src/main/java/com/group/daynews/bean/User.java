package com.group.daynews.bean;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class User {
    private String user_head;
    private String user_title;
    private String user_name;

    public User(String user_head, String user_title, String user_name) {
        this.user_head = user_head;
        this.user_title = user_title;
        this.user_name = user_name;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public String getUser_title() {
        return user_title;
    }

    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_head='" + user_head + '\'' +
                ", user_title='" + user_title + '\'' +
                ", user_name='" + user_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!user_head.equals(user.user_head)) return false;
        if (!user_title.equals(user.user_title)) return false;
        return user_name.equals(user.user_name);

    }

    @Override
    public int hashCode() {
        int result = user_head.hashCode();
        result = 31 * result + user_title.hashCode();
        result = 31 * result + user_name.hashCode();
        return result;
    }
}

