package com.group.daynews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.group.daynews.R;
import com.group.daynews.adapters.TabMainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CJY on 2016/12/27.
 */

public class MainFragment extends Fragment {
    @BindView(R.id.tab_title)
    public TabLayout mTab;
    @BindView(R.id.viewpager)
    public ViewPager mViewpager;

    private UserFragment userFragment;
    private TopFragment topFragment;
    private VideoFragment videoFragment;
    private TabMainAdapter mTabAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //标题数组
    private String[] titles;
    private List<Fragment> mFragments = new ArrayList<>();

    private void initView() {
        titles = getResources().getStringArray(R.array.tab_title);
        userFragment = new UserFragment();
        topFragment = new TopFragment();
        videoFragment = new VideoFragment();
        mFragments.add(topFragment);
        mFragments.add(userFragment);
        mFragments.add(videoFragment);
        mTabAdapter = new TabMainAdapter(getChildFragmentManager(), mFragments, titles);
        mViewpager.setAdapter(mTabAdapter);
        mTab.setupWithViewPager(mViewpager);


    }
}
