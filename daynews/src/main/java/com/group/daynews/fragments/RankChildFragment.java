package com.group.daynews.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.group.daynews.Constant.Constant;
import com.group.daynews.R;
import com.group.daynews.activitys.MsgWebActivity;
import com.group.daynews.adapters.RankAdapter;
import com.group.daynews.bean.RankDataBean;
import com.group.daynews.utils.JsonUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CJY on 2016/12/29.
 */

public class RankChildFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    @BindView(R.id.video_listview)
    public ListView mRankList;
    @BindView(R.id.video_swipefresh)
    public SwipeRefreshLayout mRankRefresh;
    @BindView(R.id.liLayout)
    public LinearLayout isShowLayout;
    private int mPosition = 0;
    private RankAdapter rankAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.video_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        initData(mPosition);
        initView();
        return rootView;
    }

    private void initData(int position) {
        switch (position) {
            case 0:
                getNetData(Constant.RANK_READ_URL);
                break;
            case 1:
                getNetData(Constant.RANK_LIKE_URL);
                break;
            case 2:
                getNetData(Constant.RANK_COMMENT_URL);
                break;
            default:
                break;
        }

    }

    private ArrayList<RankDataBean> rankData;

    private void getNetData(String url) {

        RequestParams rp = new RequestParams(url);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("数据请求成功");
                rankData = (ArrayList<RankDataBean>) JsonUtils.getRankList(result);
                rankAdapter.setDatas(rankData);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("数据请求失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void initView() {
        rankAdapter = new RankAdapter();
        mRankList.setAdapter(rankAdapter);
        isShowLayout.setVisibility(View.GONE);
        mRankRefresh.setColorSchemeColors(Color.RED);
        mRankRefresh.setOnRefreshListener(this);
        mRankList.setOnItemClickListener(this);
    }

    public void upPosition(int position) {
        mPosition = position;
        initData(mPosition);
    }


    @Override
    public void onRefresh() {
        getRefreshData(mPosition);
    }

    private void getRefreshData(int mPosition) {
        String url = "";
        switch (mPosition) {
            case 0:
                url = Constant.RANK_READ_URL;
                break;
            case 1:
                url = Constant.RANK_LIKE_URL;
                break;
            case 2:
                url = Constant.RANK_COMMENT_URL;
                break;
        }
        RequestParams rp = new RequestParams(url);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                rankData = (ArrayList<RankDataBean>) JsonUtils.getRankList(result);
                for (RankDataBean rankDataBean : rankData) {
                    if (rankAdapter.isContainer(rankDataBean)) {
                        continue;
                    }
                    rankAdapter.setSingleData(0, rankDataBean);
                }
                mRankRefresh.setRefreshing(false);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("数据请求失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        RankDataBean rankDataBean = (RankDataBean) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(getActivity(), MsgWebActivity.class);

        intent.putExtra("share_url", rankDataBean.getShare_url());
        startActivity(intent);
    }
}
