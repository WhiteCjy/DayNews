package com.group.daynews.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.group.daynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MsgWebActivity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    public Toolbar mToolbar;
    @BindView(R.id.msg_web)
    public WebView mWebView;
    @BindView(R.id.liLayout)
    public LinearLayout isShowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_web);
        ButterKnife.bind(this);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.back_btn);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String comment_count = intent.getStringExtra("comment_count");
        String vote_count = intent.getStringExtra("vote_count");
        String share_url = intent.getStringExtra("share_url");
        isShowLayout.setVisibility(View.GONE);
        mWebView.loadUrl(share_url);

        //防止网页中的链接在打开的时候打开系统内置的浏览器
        mWebView.setWebViewClient(new WebViewClient());
        //开启缓存
        mWebView.getSettings().setAppCacheEnabled(true);
        //设置缓存使用策略
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //设置webview的缓存目录
        mWebView.getSettings().setAppCachePath(getCacheDir().getPath());
        //页面缩放
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.msg_web_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "点击了" + item.getTitle().toString(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
