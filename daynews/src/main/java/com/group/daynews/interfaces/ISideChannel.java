package com.group.daynews.interfaces;

import com.group.daynews.bean.SideChannelBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public interface ISideChannel {
    void updateView(ArrayList<SideChannelBean> list, int model);
    void showMsg(String msg);
}
