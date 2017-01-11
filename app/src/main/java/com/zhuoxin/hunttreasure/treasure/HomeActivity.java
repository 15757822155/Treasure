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

import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.Glide;
import com.zhuoxin.hunttreasure.R;
import com.zhuoxin.hunttreasure.commons.ActivityUtils;
import com.zhuoxin.hunttreasure.treasure.map.MapFragment;
import com.zhuoxin.hunttreasure.user.UserPrefs;

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
    private ImageView mIvIcon;
    private MapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);
        mMapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);

        // 进入页面，将宝藏数据的缓存清空
        TreasureRepo.getInstance().clear();

        //toolbar的处理
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle("");
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
        mIvIcon = (ImageView) mNavigation.getHeaderView(0).findViewById(R.id.iv_usericon);
        mIvIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/1/4 更换头像
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // 更新侧滑上面的头像信息(从用户仓库获取保存的用户头像地址)
        String photo = UserPrefs.getInstance().getPhoto();
        if (photo != null) {
            // 加载头像
            Glide.with(this)
                    .load(photo)
                    .error(R.mipmap.user_icon)  //错误时展示的图
                    .placeholder(R.mipmap.user_icon)// 设置占位图
                    .into(mIvIcon);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_hide:
                //修改到藏宝藏界面
                mMapFragment.changeUIMode(2);
                break;
            case R.id.menu_logout:
                //退出登录
                UserPrefs.getInstance().clearUser();
                mActivityUtils.startActivity(MainActivity.class);
                finish();
                break;
            case R.id.menu_help:
                mActivityUtils.showToast("哈撒开");
                break;
            case R.id.menu_my_list:
                mActivityUtils.showToast("哈撒开");
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //处理返回键的事件
    @Override
    public void onBackPressed() {
        //如果抽屉是打开状态就关闭抽屉
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            // MapFragment里面视图的普通的视图，可以退出
            if (mMapFragment.clickbackPresed()) {
                super.onBackPressed();
            }
        }
    }
}
