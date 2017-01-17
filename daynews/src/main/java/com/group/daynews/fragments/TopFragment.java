package com.group.daynews.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.group.daynews.R;
import com.group.daynews.adapters.TopHeadVpAdapter;
import com.group.daynews.adapters.TopLvAdapter;
import com.group.daynews.bean.TopDataBean;
import com.group.daynews.interfaces.ITopView;
import com.group.daynews.precenter.TopPrecenter;
import com.group.daynews.utils.JsonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/12/26 0026.
 */


public class TopFragment extends Fragment implements ITopView, ViewPager.OnPageChangeListener, AbsListView.OnScrollListener {

    @BindView(R.id.top_lv)
    ListView lv;
    @BindView(R.id.liLayout)
    LinearLayout liLayout;
    @BindView(R.id.top_refresh)
    SwipeRefreshLayout top_refresh;


    private LinearLayout liContainer, ivLayout;
    private ViewPager head_vp;
    private TopLvAdapter lvAdapter;

    private TopHeadVpAdapter vpAdapter;

    private ArrayList<TopDataBean> contantLists;
    private ArrayList<TopDataBean> headLists;

    private LayoutInflater mInflater;

    String url = "http://dailyapi.ibaozou.com/api/v31/documents/latest";

    private int perPosition = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int size = headLists.size();
                if (size != 0) {
                    int currentItem = head_vp.getCurrentItem();
                    currentItem++;
                    currentItem = currentItem % size;
                    head_vp.setCurrentItem(currentItem);
                    Log.d("guige", "handleMessage: " + currentItem);
                    vpAdapter.notifyDataSetChanged();
                    mHandler.sendEmptyMessageDelayed(0, 5000);
                }
            }
        }
    };


    private TopPrecenter precenter = new TopPrecenter(this);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_fg_layout, container, false);
        ButterKnife.bind(this, view);
        mInflater = inflater;
        init();
        precenter.start(url, TopPrecenter.FIRST_MODEL);
        top_refresh.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN);
        top_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                precenter.start(url, TopPrecenter.REFRESH_MODEL);
            }

        });
        return view;
    }


    private void init() {
        contantLists = new ArrayList<>();
        headLists = new ArrayList<>();

        View headerView = mInflater.inflate(R.layout.top_fg_head_layout, null);
        head_vp = (ViewPager) headerView.findViewById(R.id.head_vp);
        liContainer = (LinearLayout) headerView.findViewById(R.id.liContainer);
        initData();
        lv.addHeaderView(headerView);

        View footerView = mInflater.inflate(R.layout.top_fg_foot_layout, null);
        ImageView iv = (ImageView) footerView.findViewById(R.id.iv);
        ivLayout = (LinearLayout) footerView.findViewById(R.id.ivLayout);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        LinearInterpolator uniform = new LinearInterpolator();
        animation.setInterpolator(uniform);
        iv.startAnimation(animation);

        lv.addFooterView(footerView);
    }

    private void initList(ArrayList<TopDataBean> list) {
        for (int i = 0; i < list.size(); i++) {
            int type = list.get(i).getType();
            Log.d("guige", "onSuccess: " + list.get(i).getTitle());
            if (type == 0) {
                headLists.add(list.get(i));
            } else {
                contantLists.add(list.get(i));
            }
        }
        lvAdapter = new TopLvAdapter(getActivity(), contantLists, TopPrecenter.TOP_TYPE);
        vpAdapter = new TopHeadVpAdapter(getActivity(), headLists);
        head_vp.setAdapter(vpAdapter);
        //设置预加载页数，最小值为1，默认为1
        lv.setAdapter(lvAdapter);
        liLayout.setVisibility(View.GONE);

        head_vp.setOffscreenPageLimit(5);
        head_vp.addOnPageChangeListener(TopFragment.this);
        mHandler.sendEmptyMessageDelayed(0, 5000);
        lv.setOnScrollListener(this);

    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            View view = new View(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics())),
                    ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()))
            );
            lp.leftMargin = 12;
            view.setLayoutParams(lp);
            view.setEnabled(false);
            view.setBackgroundResource(R.drawable.head_bg_check);
            liContainer.addView(view);
            liContainer.getChildAt(0).setEnabled(true);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        liContainer.getChildAt(perPosition).setEnabled(false);
        liContainer.getChildAt(position).setEnabled(true);
        perPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {

            if (firstVisibleItem + visibleItemCount == totalItemCount) {

                View childAt = lv.getChildAt(totalItemCount - firstVisibleItem - 1);
                if (childAt.getBottom() == view.getHeight()) {
                    ivLayout.setVisibility(View.VISIBLE);
                } else {
                    ivLayout.setVisibility(View.INVISIBLE);
                }

                String url2 = url + "?timestamp=" + JsonUtils.home_timestamp + "&";
                precenter.start(url2, TopPrecenter.LOADING_MODEL);

            }
        }
    }

    private int firstVisibleItem, visibleItemCount, totalItemCount;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
        this.visibleItemCount = visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    @Override
    public void updateView(ArrayList<TopDataBean> list, int model) {
        switch (model) {
            case TopPrecenter.FIRST_MODEL:
                initList(list);
                break;
            case TopPrecenter.REFRESH_MODEL:
                refreshList(list);
                top_refresh.setRefreshing(false);
                Log.d("qwer", "updateView: qwer");
                break;
            case TopPrecenter.LOADING_MODEL:
                contantLists.addAll(list);
                lvAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void refreshList(ArrayList<TopDataBean> list) {
        int headIndex = 0;
        int contentIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            TopDataBean bean = list.get(i);
            int type = bean.getType();
            Log.d("asdf", "refreshList: " + bean.getTitle());
            if (type == 0) {
                if (headLists.contains(bean)) {
                    Log.d("asdf", "refreshList: 555555");
                    continue;
                }
                Log.d("asdf", "refreshList: 666666");
                headLists.add(bean);
                int size = headLists.size();
                headLists.remove(size - 1);
                vpAdapter.notifyDataSetChanged();
                headIndex++;
            } else {
                if (contantLists.contains(bean)) {
                    continue;
                }
                contantLists.add(bean);
                lvAdapter.notifyDataSetChanged();
                contentIndex++;
            }
        }
    }

    @Override
    public void showMsg(String msg) {

    }
}
