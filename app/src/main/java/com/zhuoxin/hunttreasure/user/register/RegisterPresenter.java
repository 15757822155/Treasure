package com.zhuoxin.hunttreasure.user.register;

import com.zhuoxin.hunttreasure.net.NetClient;
import com.zhuoxin.hunttreasure.user.User;
import com.zhuoxin.hunttreasure.user.UserPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/1/3.
 * 用于处理注册的相关业务
 */

public class RegisterPresenter {
    RegisterView mRegisterView;

    public RegisterPresenter(RegisterView registerView) {
        mRegisterView = registerView;
    }

    public void register(User user) {
        mRegisterView.showProgress();
        Call<RegisterResult> register = NetClient.getInstances().getTreasureApi().register(user);
        register.enqueue(mCallback);
    }

    private Callback<RegisterResult> mCallback = new Callback<RegisterResult>() {
        @Override
        public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
            mRegisterView.hideProgress();
            if (response.isSuccessful()) {
                RegisterResult result = response.body();
                if (result == null) {
                    mRegisterView.showMessage("发生了未知的错误");
                    return;
                }
                if (result.getCode() == 1) {
                    //保存tokenid
                    UserPrefs.getInstance().setTokenid(result.getTokenId());
                    mRegisterView.navigationToHome();
                }
                mRegisterView.showMessage(result.getMsg());
            }
        }

        @Override
        public void onFailure(Call<RegisterResult> call, Throwable t) {
            mRegisterView.hideProgress();
            mRegisterView.showMessage("请求失败" + t.getMessage());
        }
    };
}
