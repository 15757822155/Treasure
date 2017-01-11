package com.zhuoxin.hunttreasure.net;

import com.zhuoxin.hunttreasure.treasure.Area;
import com.zhuoxin.hunttreasure.treasure.Treasure;
import com.zhuoxin.hunttreasure.user.User;
import com.zhuoxin.hunttreasure.user.login.LoginResult;
import com.zhuoxin.hunttreasure.user.register.RegisterResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface TreasureApi {

    //登录
    @POST("/Handler/UserHandler.ashx?action=login")
    Call<LoginResult> login(@Body User user);

    //注册
    @POST("/Handler/UserHandler.ashx?action=register")
    Call<RegisterResult> register(@Body User user);

    //获取数据
    @POST("/Handler/TreasureHandler.ashx?action=show")
    Call<List<Treasure>> getTreasureInArea(@Body Area area);
}
