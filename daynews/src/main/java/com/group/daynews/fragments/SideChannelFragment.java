package com.group.daynews.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.group.daynews.adapters.SideChannelLvAdapter;
import com.group.daynews.bean.SideChannelBean;
import com.group.daynews.interfaces.ISideChannel;
import com.group.daynews.precenter.SideChannelPrecenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.group.daynews.R.id.ivLayout;
import static com.group.daynews.R.id.s_refresh;


public class SideChannelFragment extends Fragment implements ISideChannel, AbsListView.OnScrollListener {

    @BindView(R.id.lv_sidechanel)
    ListView lv;
    @BindView(R.id.s_refresh)
    SwipeRefreshLayout s_refresh;
    @BindView(R.id.liLayout)
    LinearLayout liLayout;

    private SideChannelPrecenter precenter = new SideChannelPrecenter(this);

    String url = "http://dailyapi.ibaozou.com/api/v31/channels/index?page=%d&per_page=10&";

    private int page = 1;

    private ArrayList<SideChannelBean> arrayList;

    private LayoutInflater mInflater;
    private LinearLayout ivLayout;
    private SideChannelLvAdapter lvAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                ivLayout.setVisibility(View.INVISIBLE);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sidechannel_fg_layout, container, false);
        ButterKnife.bind(this, view);
        mInflater = inflater;
        init();
        String url1 = String.format(url, 1);
        precenter.start(SideChannelPrecenter.SC_FIRST_MODEL, url1);
        s_refresh.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED);
        s_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                String url2 = String.format(url, 1);
                precenter.start(SideChannelPrecenter.SC_REFRESH_MODEL, url2);
            }
        });
        return view;
    }

    private void init() {
        arrayList = new ArrayList<>();

        View footerView = mInflater.inflate(R.layout.top_fg_foot_layout, null);
        ImageView iv = (ImageView) footerView.findViewById(R.id.iv);
        ivLayout = (LinearLayout) footerView.findViewById(R.id.ivLayout);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        LinearInterpolator uniform = new LinearInterpolator();
        animation.setInterpolator(uniform);
        iv.startAnimation(animation);

        lv.addFooterView(footerView);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.favorite_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateView(ArrayList<SideChannelBean> list, int model) {
        switch (model) {
            case SideChannelPrecenter.SC_FIRST_MODEL:
                initList(list);
                break;
            case SideChannelPrecenter.SC_REFRESH_MODEL:
                reFreshList(list, 0);
                s_refresh.setRefreshing(false);
                break;
            case SideChannelPrecenter.SC_LOADING_MODEL:
                reFreshList(list, arrayList.size());
                mHandler.sendEmptyMessageDelayed(0, 2000);
                break;
        }
    }

    private void reFreshList(ArrayList<SideChannelBean> list, int index) {

        for (SideChannelBean bean : list) {
            if (lvAdapter.isContains(bean)) {
                continue;
            }
            arrayList.add(index, bean);
            lvAdapter.notifyDataSetChanged();
            index++;
        }
    }

    private void initList(ArrayList<SideChannelBean> list) {
        arrayList.addAll(list);
        lvAdapter = new SideChannelLvAdapter(getActivity(), arrayList);
        lv.setAdapter(lvAdapter);
        liLayout.setVisibility(View.GONE);
        lv.setOnScrollListener(this);

    }

    @Override
    public void showMsg(String msg) {

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

                String url3 = String.format(url, ++page);
                page = 1;
                precenter.start(SideChannelPrecenter.SC_LOADING_MODEL, url3);

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
}
