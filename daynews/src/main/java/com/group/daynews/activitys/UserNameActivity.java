package com.group.daynews.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.group.daynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserNameActivity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        ButterKnife.bind(this);
        mToolbar.setTitle("个人");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.back_btn);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "点击了" + item.getTitle().toString(), Toast.LENGTH_SHORT).show();
        return true;
    }

}
