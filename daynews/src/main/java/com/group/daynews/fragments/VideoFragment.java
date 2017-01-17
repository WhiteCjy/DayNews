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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.group.daynews.R;
import com.group.daynews.activitys.MsgWebActivity;
import com.group.daynews.adapters.VideoAdapter;
import com.group.daynews.bean.VideoDataBean;
import com.group.daynews.utils.JsonUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.group.daynews.R.id.ivLayout;


public class VideoFragment extends Fragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    @BindView(R.id.video_listview)
    public ListView mVideoList;
    @BindView(R.id.video_swipefresh)
    public SwipeRefreshLayout mVideoSwipe;
    private VideoAdapter mVideoAdapter;
    private int index = 0;
    @BindView(R.id.liLayout)
    public LinearLayout isShowLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.video_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        initView(inflater);
        mVideoList.setOnScrollListener(this);
        return rootView;
    }

    private ArrayList<VideoDataBean> data = new ArrayList<>();
    private LinearLayout footLayout;
    private static final String VIDEO_URL = "http://dailyapi.ibaozou.com/api/v31/documents/videos/latest";

    private void initView(LayoutInflater inflate) {
        mVideoAdapter = new VideoAdapter(getContext());
        View footView = inflate.from(getActivity()).inflate(R.layout.top_fg_foot_layout, mVideoList, false);
        ImageView iv = (ImageView) footView.findViewById(R.id.iv);
        footLayout = (LinearLayout) footView.findViewById(ivLayout);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);

        LinearInterpolator uniform = new LinearInterpolator();
        animation.setInterpolator(uniform);
        iv.startAnimation(animation);
        initData(VIDEO_URL);
        mVideoList.addFooterView(footView);
        mVideoList.setAdapter(mVideoAdapter);
        isShowLayout.setVisibility(View.GONE);
        mVideoSwipe.setColorSchemeColors(Color.RED);
        mVideoSwipe.setOnRefreshListener(this);
        mVideoList.setOnItemClickListener(this);

    }

    private boolean isGetNext;

    private void initData(String url) {
        RequestParams rp = new RequestParams(url);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                data = (ArrayList<VideoDataBean>) JsonUtils.getVideoList(result);
                isGetNext = false;
                mVideoAdapter.setVideoDataBeen(data);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    private int firstVisibleItem, visibleItemCount, totalItemCount;

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {

        String url2 = VIDEO_URL + "?timestamp=" + JsonUtils.timestamp_video + "&";
        if (scrollState == SCROLL_STATE_IDLE) {
            if (firstVisibleItem + visibleItemCount == totalItemCount) {
                footLayout.setVisibility(View.VISIBLE);
                if (isGetNext) {
                    return;
                }
                initData(url2);
                isGetNext = true;
            }
        }
    }


    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
        this.visibleItemCount = visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    @Override
    public void onRefresh() {
        initRefreshData(VIDEO_URL);
    }

    private void initRefreshData(String videoUrl) {
        RequestParams rp = new RequestParams(videoUrl);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                data = (ArrayList<VideoDataBean>) JsonUtils.getVideoList(result);
                for (VideoDataBean videoDataBean : data) {
                    if (mVideoAdapter.isContainer(videoDataBean)) {

                        continue;
                    }
                    mVideoAdapter.setSingleVideoDataBeen(index, videoDataBean);
                }
                mVideoSwipe.setRefreshing(false);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
        VideoDataBean videoDataBean = (VideoDataBean) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(getActivity(), MsgWebActivity.class);

        intent.putExtra("comment_count", videoDataBean.getComment_count());

        intent.putExtra("vote_count", videoDataBean.getVote_count());
        intent.putExtra("share_url", videoDataBean.getShare_url());
        startActivity(intent);
    }
}
