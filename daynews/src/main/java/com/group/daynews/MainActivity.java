package com.group.daynews;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.group.daynews.activitys.CommentActivity;
import com.group.daynews.activitys.DownloadActivity;
import com.group.daynews.activitys.FavoriteActivity;
import com.group.daynews.activitys.ReadActivity;
import com.group.daynews.activitys.SettingActivity;
import com.group.daynews.activitys.UserNameActivity;
import com.group.daynews.fragments.MainFragment;
import com.group.daynews.fragments.RankFragment;
import com.group.daynews.fragments.SideChannelFragment;
import com.group.daynews.fragments.SideFavoriteFragment;
import com.group.daynews.fragments.SideSearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    @BindView(R.id.toolBar)
    public Toolbar mToolbar;
    @BindView(R.id.drawer)
    public DrawerLayout mDrawLayout;
    @BindView(R.id.navigation_view)
    public NavigationView drawView;//侧滑菜单
    //头布局
    public TextView mFavorite_tv;
    public TextView mComment_tv;
    public TextView mRead_tv;
    private TextView mLogin_tv;
    private ActionBarDrawerToggle mDrawerToggle;

    private MainFragment mainFragment;
    private RankFragment rankFragment;
    private SideFavoriteFragment favoriteFragment;
    private SideChannelFragment channelFragment;
    private SideSearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToobar();
        initFragment();
        initHeadView();

    }

    private void initFragment() {
        mainFragment = new MainFragment();
        rankFragment = new RankFragment();
        favoriteFragment = new SideFavoriteFragment();
        channelFragment = new SideChannelFragment();
        searchFragment = new SideSearchFragment();
        addFragment(mainFragment, "mainfragment");
    }

    private void initHeadView() {
        View headView = drawView.getHeaderView(0);
        mFavorite_tv = (TextView) headView.findViewById(R.id.side_favorite);
        mLogin_tv = (TextView) headView.findViewById(R.id.user_head_name);
        mComment_tv = (TextView) headView.findViewById(R.id.side_comment);
        mRead_tv = (TextView) headView.findViewById(R.id.side_read);
        mFavorite_tv.setOnClickListener(this);
        mLogin_tv.setOnClickListener(this);
        mRead_tv.setOnClickListener(this);
        mComment_tv.setOnClickListener(this);
    }

    private void initToobar() {
        mToolbar.setTitle("暴走日报");
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        drawView.setNavigationItemSelectedListener(this);
        drawView.setItemIconTintList(null);
        MenuItem item = drawView.getMenu().getItem(0);
        item.setCheckable(true);
        item.setChecked(true);
        //设置ActionBar的指示图标可见，设置ActionBar上的应用图标位置处可以被单击
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //侧滑栏监听器
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawLayout.addDrawerListener(mDrawerToggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pen:
                Toast.makeText(this, "启动popupwidndow", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.action_notice:
                Toast.makeText(this, "启动新界面", Toast.LENGTH_SHORT).show();
                System.out.println("点击了闹铃");
                break;

        }
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setCheckable(true);//设置选项可选
        switch (item.getItemId()) {
            case R.id.head_page:
                addFragment(mainFragment, "mainfragment");
                mToolbar.setTitle("暴走日报");
                break;
            case R.id.side_rank:
                addFragment(rankFragment, "rankfragment");
                mToolbar.setTitle(item.getTitle());
                break;
            case R.id.side_chanel:
                addFragment(channelFragment, "channelfragment");
                mToolbar.setTitle(item.getTitle());
                break;
            case R.id.side_search:
                addFragment(searchFragment, "searchFragment");
                break;
            case R.id.side_setting:
                Intent intent = new Intent(this, SettingActivity.class);
                ;
                startActivity(intent);
                break;
            case R.id.side_offline:
                startActivity(new Intent(MainActivity.this, DownloadActivity.class));
                break;
            case R.id.side_night:
                break;
            default:
                break;
        }
        mDrawLayout.closeDrawer(drawView);
        return true;
    }

    //判断当前fragment
    private Fragment curShowFragment;

    public void addFragment(Fragment fragment, String Tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //先用Tag去内存中查找有没有这样的Fragment
        Fragment tempFragment = manager.findFragmentByTag(Tag);
        if (tempFragment == null) {
            if (curShowFragment != null) {
                transaction.hide(curShowFragment);
            }
            //第一个参数 fragment添加的容器的id   第三个参数，Fragment的标识  注意：同一个Fragment不能连续添加两次
            transaction.add(R.id.main_fragment, fragment, Tag);
            curShowFragment = fragment;
        } else {
            if (tempFragment != curShowFragment) {//说明要加添的Fragment当前没在显示
                transaction.hide(curShowFragment);
                transaction.show(tempFragment);
                curShowFragment = tempFragment;
            }
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.side_favorite:
                startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
                break;
            case R.id.side_comment:
                startActivity(new Intent(MainActivity.this, CommentActivity.class));
                break;
            case R.id.side_read:
                startActivity(new Intent(MainActivity.this, ReadActivity.class));
                break;
            case R.id.user_head_name:
                startActivity(new Intent(MainActivity.this, UserNameActivity.class));
                break;
            default:
                break;
        }

    }

    long waitTime = 2000;
    long touchTime = 0;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= waitTime) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            touchTime = currentTime;
        } else {
            finish();
        }
    }
}
