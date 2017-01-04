package com.zhuoxin.hunttreasure.user.register;

/**
 * Created by Administrator on 2017/1/3.
 *
 */

public interface RegisterView {
    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    void navigationToHome();
}
