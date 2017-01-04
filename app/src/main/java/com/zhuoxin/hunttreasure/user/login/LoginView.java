package com.zhuoxin.hunttreasure.user.login;

/**
 * Created by Administrator on 2017/1/3.
 */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    void navigationToHome();
}
