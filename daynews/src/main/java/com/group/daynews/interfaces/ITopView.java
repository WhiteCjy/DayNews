package com.group.daynews.interfaces;

import com.group.daynews.bean.TopDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public interface ITopView {

    void updateView(ArrayList<TopDataBean> list, int model);

    void showMsg(String msg);

}
