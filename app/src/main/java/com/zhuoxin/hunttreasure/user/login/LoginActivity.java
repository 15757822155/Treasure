package com.zhuoxin.hunttreasure.user.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.zhuoxin.hunttreasure.R;
import com.zhuoxin.hunttreasure.commons.ActivityUtils;
import com.zhuoxin.hunttreasure.commons.RegexUtils;
import com.zhuoxin.hunttreasure.custom.AlertDialogFragment;
import com.zhuoxin.hunttreasure.treasure.HomeActivity;
import com.zhuoxin.hunttreasure.treasure.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_Username)
    EditText mEtUsername;
    @BindView(R.id.et_Password)
    EditText mEtPassword;
    @BindView(R.id.btn_Login)
    Button mBtnLogin;
    private String mUsername;
    private String mPassword;
    private ProgressDialog mDialog;
    private ActivityUtils mActivityUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            //左上角返回图标
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //标题
            getSupportActionBar().setTitle(R.string.login);
        }
        mEtUsername.addTextChangedListener(textWatcher);
        mEtPassword.addTextChangedListener(textWatcher);

    }


    //文本改变的监听
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mUsername = mEtUsername.getText().toString();
            mPassword = mEtPassword.getText().toString();
            boolean canLogin = !(TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPassword));
            //设置是否能够点击
            mBtnLogin.setEnabled(canLogin);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回箭头的事件
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_Login)
    public void onClick() {
        //用户名错误
        if (RegexUtils.verifyUsername(mUsername) != RegexUtils.VERIFY_SUCCESS) {
            AlertDialogFragment.getInstances(getString(R.string.username_error),
                    getString(R.string.username_rules))
                    .show(getSupportFragmentManager(), "usernameError");
            return;
        }
        //登录密码错误
        if (RegexUtils.verifyUsername(mPassword) != RegexUtils.VERIFY_SUCCESS) {
            AlertDialogFragment.getInstances(getString(R.string.password_error),
                    getString(R.string.password_rules))
                    .show(getSupportFragmentManager(), "passwordError");
            return;
        }

        new LoginPresenter(this).login();
    }

    @Override
    public void showProgress() {
        mDialog = ProgressDialog.show(this, "登录", "亲,正在登录中,请稍后~");
    }

    @Override
    public void hideProgress() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String msg) {
        mActivityUtils.showToast(msg);
    }

    @Override
    public void navigationToHome() {
        mActivityUtils.startActivity(HomeActivity.class);
        finish();
        //发送本地广播
        Intent intent = new Intent(MainActivity.MAIN_ACTION);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
