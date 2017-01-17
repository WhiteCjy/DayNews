package com.group.daynews.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.group.daynews.R;
import com.group.daynews.activitys.MsgWebActivity;
import com.group.daynews.adapters.MainAdapter;
import com.group.daynews.bean.User;
import com.group.daynews.net.UserNet;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.leefeng.lfrecyclerview.LFRecyclerView;
import me.leefeng.lfrecyclerview.OnItemClickListener;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class UserFragment extends Fragment implements AdapterView.OnItemClickListener, LFRecyclerView.LFRecyclerViewListener, LFRecyclerView.LFRecyclerViewScrollChange, OnItemClickListener {
    private boolean isFirstclick = true;//是否第一次点击加载更多按钮；
    private boolean b = true;
    private boolean hasError = false;
    final String url = "http://dailyapi.ibaozou.com/api/v31/documents/contributes/latest";
    private LFRecyclerView recycleview;
    private static String user_result = null;
    private MainAdapter mainAdapter = new MainAdapter();
    ArrayList<User> list = new ArrayList<>();

    @BindView(R.id.liLayout)
    LinearLayout liLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View UserView = inflater.inflate(R.layout.user_fragment_listview, container, false);

        recycleview = (LFRecyclerView) UserView.findViewById(R.id.recycleview);
        recycleview.setLoadMore(true);//设置为可上拉加载,默认false
        recycleview.setRefresh(true);// 设置为可下拉刷新,默认true
        recycleview.setNoDateShow();//没有数据时,底部显示"没有数据"字样,默认不显示
        recycleview.setAutoLoadMore(true);//设置滑动到底部自动加载,默认false
        recycleview.setOnItemClickListener(this);
        recycleview.setLFRecyclerViewListener(this);
        recycleview.setScrollChangeListener(this);
        recycleview.setItemAnimator(new DefaultItemAnimator());

        ButterKnife.bind(this, UserView);
        x.view().inject(getActivity());

        getDate(url);//利用Xutils3请求数据;
        return UserView;
    }

    public void getDate(String user_url) {
        RequestParams userparams = new RequestParams(user_url);
        x.http().get(userparams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                UserNet usernet = new UserNet();
                list = usernet.getData(result);//把数据解析后返回给list;
                for (User user : list) {//过滤重复的数据
                    if (mainAdapter.isContanins(user)) {
                        continue;
                    }
                    mainAdapter.getData(list);
                }
                if (isFirstclick) {
                    recycleview.setAdapter(mainAdapter);
                    liLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
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
    public void onClick(int position) {
    }

    @Override
    public void onLongClick(int po) {
    }

    @Override
    public void onRefresh() {//下拉刷新
        isFirstclick = false;
        getDate(url);
        recycleview.stopRefresh(b);
    }

    @Override
    public void onLoadMore() {//上拉加载更多
        recycleview.stopLoadMore();
        isFirstclick = false;
        //再次请求数据
        String timestamp = UserNet.timestamp.toString();
        //拼接请求地址
        String new_url = url + "?timestamp=" + timestamp + "&";
        //利用Xutils3请求数据;
        getDate(new_url);
    }

    @Override
    public void onRecyclerViewScrollChange(View view, int i, int i1) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user = (User) parent.getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), MsgWebActivity.class);
        startActivity(intent);
    }
}
