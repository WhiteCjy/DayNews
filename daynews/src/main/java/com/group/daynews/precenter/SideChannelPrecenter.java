package com.group.daynews.precenter;

import com.group.daynews.bean.SideChannelBean;
import com.group.daynews.interfaces.ISideChannel;
import com.group.daynews.utils.JsonUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class SideChannelPrecenter {

    private ISideChannel iSideChannel;

    public static final int SC_FIRST_MODEL = 0;
    public static final int SC_REFRESH_MODEL = 1;
    public static final int SC_LOADING_MODEL = 2;


    public SideChannelPrecenter(ISideChannel iSideChannel) {
        this.iSideChannel = iSideChannel;
    }

    public void start(final int model, String url) {
        RequestParams rp = new RequestParams(url);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ArrayList<SideChannelBean> list = JsonUtils.getSideChannelList(result);
                iSideChannel.updateView(list, model);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                iSideChannel.showMsg(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
