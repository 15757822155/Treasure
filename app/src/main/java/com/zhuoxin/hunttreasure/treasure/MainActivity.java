package com.zhuoxin.hunttreasure.treasure;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhuoxin.hunttreasure.R;
import com.zhuoxin.hunttreasure.commons.ActivityUtils;
import com.zhuoxin.hunttreasure.user.UserPrefs;
import com.zhuoxin.hunttreasure.user.login.LoginActivity;
import com.zhuoxin.hunttreasure.user.register.RegisterActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    public static final String MAIN_ACTION = "navigation_to_home";
    private ActivityUtils mActivityUtils;
    private Unbinder mUnbinder;

    // 接收器
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityUtils = new ActivityUtils(this);
        mUnbinder = ButterKnife.bind(this);
        //判断是否登录过
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        if (preferences != null) {
            //跟用户仓库保存的数据进行比对
            if (preferences.getInt("key_tokenid", 0) == UserPrefs.getInstance().getTokenid()) {
                mActivityUtils.startActivity(HomeActivity.class);
                finish();
            }
        }

        // 注册本地的广播
        IntentFilter intentFilter = new IntentFilter(MAIN_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @OnClick({R.id.btn_Register, R.id.btn_Login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Register:
                mActivityUtils.startActivity(RegisterActivity.class);
                break;
            case R.id.btn_Login:
                mActivityUtils.startActivity(LoginActivity.class);
                break;
        }
    }

}
