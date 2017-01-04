package com.zhuoxin.hunttreasure.treasure;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.zhuoxin.hunttreasure.R;
import com.zhuoxin.hunttreasure.commons.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private ActivityUtils mActivityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);

        //toolbar的处理
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        //drawerLayout设置监听
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();//同步状态,使返回按钮能随抽屉动作同步动画
        mDrawerLayout.addDrawerListener(toggle);

        //设置Navigation每一条的选中监听
        mNavigation.setNavigationItemSelectedListener(this);

        //设置头像的监听事件
        ImageView ivIcon = (ImageView) mNavigation.getHeaderView(0).findViewById(R.id.iv_usericon);
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/1/4 更换头像
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_hide:
                mActivityUtils.showToast("哈撒开");
                break;
            case R.id.menu_logout:
                mActivityUtils.showToast("哈撒开");
                break;
            case R.id.menu_help:
                mActivityUtils.showToast("哈撒开");
                break;
            case R.id.menu_my_list:
                mActivityUtils.showToast("哈撒开");
                break;
        }
        return true;
    }

    //处理返回键的事件
    @Override
    public void onBackPressed() {
        //如果抽屉是打开状态就关闭抽屉
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
