package com.feicuiedu.hunttreasure.user.register;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2017/1/3.
 */

public class RegisterPresenter {
    RegisterView mRegisterView;

    public RegisterPresenter(RegisterView registerView) {
        mRegisterView = registerView;
    }

    public void register() {
        new AsyncTask<Void, Integer, Void>() {
            // 可以使用进度条增加用户体验度。 此方法在主线程执行，用于显示任务执行的进度。
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mRegisterView.showProgress();
            }

            // 后台执行，比较耗时的操作都可以放在这里,后台线程，不可以做UI的更新
            @Override
            protected Void doInBackground(Void... params) {
                //睡3秒,模拟注册
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            // 相当于Handler 处理UI的方式，在这里面可以使用在doInBackground 得到的结果处理操作UI
            @Override
            protected void onPostExecute(Void aVoid) {
                // 拿到数据，做UI更新
                // 注册成功之后的处理
                mRegisterView.hideProgress();
                mRegisterView.showMessage("注册成功");
                mRegisterView.navigationToHome();
                super.onPostExecute(aVoid);
            }
        }.execute();
    }


    ;
}
