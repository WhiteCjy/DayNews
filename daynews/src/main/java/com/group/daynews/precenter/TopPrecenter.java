package com.group.daynews.precenter;

import android.util.Log;

import com.group.daynews.bean.TopDataBean;
import com.group.daynews.interfaces.ITopView;
import com.group.daynews.utils.JsonUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class TopPrecenter {

    private ITopView iTopView;

    public static final int FIRST_MODEL = 0;
    public static final int REFRESH_MODEL = 1;//refresh
    public static final int LOADING_MODEL = 2;//loading

    public static final int TOP_TYPE = 0;
    public static final int CHANNEL_TYPE = 1;

    public TopPrecenter(ITopView iTopView) {
        this.iTopView = iTopView;
    }

    public void start(String url, final int model) {
        RequestParams rp = new RequestParams(url);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ArrayList<TopDataBean> list = JsonUtils.getTopList(result);
                iTopView.updateView(list, model);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                iTopView.showMsg(ex.toString());
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
