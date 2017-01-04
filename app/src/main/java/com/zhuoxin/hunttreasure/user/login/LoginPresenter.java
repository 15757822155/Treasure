package com.zhuoxin.hunttreasure.user.login;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2017/1/3.
 */

public class LoginPresenter {
    LoginView mLoginView;

    public LoginPresenter(LoginView loginView) {
        mLoginView = loginView;
    }

    public void login() {
        new AsyncTask<Void, Integer, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mLoginView.showProgress();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mLoginView.hideProgress();
                mLoginView.showMessage("登录成功");
                mLoginView.navigationToHome();
            }
        }.execute();
    }
}
