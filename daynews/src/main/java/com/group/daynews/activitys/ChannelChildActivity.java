package com.group.daynews.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.group.daynews.R;
import com.group.daynews.adapters.TopLvAdapter;
import com.group.daynews.bean.TopDataBean;
import com.group.daynews.interfaces.ITopView;
import com.group.daynews.precenter.TopPrecenter;
import com.group.daynews.utils.JsonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.group.daynews.R.id.ivLayout;
import static com.group.daynews.R.id.liLayout;

public class ChannelChildActivity extends AppCompatActivity implements ITopView, AbsListView.OnScrollListener {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.lv_channel)
    ListView lv;
    @BindView(R.id.c_refresh)
    SwipeRefreshLayout c_refresh;
    @BindView(R.id.liLayout)
    LinearLayout liLayout;

    private int id = -1;
    private TopLvAdapter mAdapter;
    private TopPrecenter precenter = new TopPrecenter(this);
    private ArrayList<TopDataBean> arrayList;
    private LinearLayout ivLayout;

    String url = "http://dailyapi.ibaozou.com/api/v31/channels/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_child);
        ButterKnife.bind(this);
        init();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        id = intent.getIntExtra("id", -1);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_btn);
        url = url + id;
        precenter.start(url, TopPrecenter.FIRST_MODEL);
        c_refresh.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED);
        c_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                precenter.start(url, TopPrecenter.REFRESH_MODEL);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void init() {

        arrayList = new ArrayList<>();
        LayoutInflater mInflater = LayoutInflater.from(this);
        View footerView = mInflater.inflate(R.layout.top_fg_foot_layout, null);
        ImageView iv = (ImageView) footerView.findViewById(R.id.iv);
        ivLayout = (LinearLayout) footerView.findViewById(R.id.ivLayout);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        LinearInterpolator uniform = new LinearInterpolator();
        animation.setInterpolator(uniform);
        iv.startAnimation(animation);

        lv.addFooterView(footerView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void updateView(ArrayList<TopDataBean> list, int model) {
        switch (model) {
            case TopPrecenter.FIRST_MODEL:
                initList(list);
                break;
            case TopPrecenter.REFRESH_MODEL:
                refreshList(list);
                c_refresh.setRefreshing(false);
                break;
            case TopPrecenter.LOADING_MODEL:
                arrayList.addAll(list);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void refreshList(ArrayList<TopDataBean> list) {
        int index = 0;
        for (TopDataBean bean : list) {
            if (arrayList.contains(bean)) {
                continue;
            }
            arrayList.add(index, bean);
            mAdapter.notifyDataSetChanged();
            index++;
        }

    }

    private void initList(ArrayList<TopDataBean> list) {
        arrayList.addAll(list);
        mAdapter = new TopLvAdapter(this, arrayList, TopPrecenter.CHANNEL_TYPE);
        lv.setAdapter(mAdapter);
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
}
