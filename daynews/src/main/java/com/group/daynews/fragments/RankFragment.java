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


public class RankFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    @BindView(R.id.tab_title)
    public TabLayout mTab;
    @BindView(R.id.viewpager)
    public ViewPager mViewpager;
    private TabMainAdapter mTabAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    //标题数组
    private String[] titles;
    private List<Fragment> mFragments = new ArrayList<>();

    private void initView() {
        titles = getResources().getStringArray(R.array.rank_tab_title);
        for (int i = 0; i < titles.length; i++) {
            RankChildFragment childFragment = new RankChildFragment();
            mFragments.add(childFragment);
        }
        mTabAdapter = new TabMainAdapter(getChildFragmentManager(), mFragments, titles);
        mTab.setupWithViewPager(mViewpager);
        mViewpager.setAdapter(mTabAdapter);
        mTab.addOnTabSelectedListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.rank_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        RankChildFragment fragment = (RankChildFragment) mFragments.get(position);
        System.out.println("位置更新了" + position);
        fragment.upPosition(position);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
