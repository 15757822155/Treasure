package com.zhuoxin.hunttreasure.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/1/9.
 */

public class NetClient {
    private static NetClient sNetClient;
    private TreasureApi mTreasureApi;
    public  static final String BASE_URL = "http://admin.syfeicuiedu.com";
    private final Retrofit mRetrofit;

    private NetClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static NetClient getInstances() {
        if (sNetClient == null) {
            sNetClient = new NetClient();
        }
        return sNetClient;
    }

    public TreasureApi getTreasureApi() {
        if (mTreasureApi == null) {
            mTreasureApi = mRetrofit.create(TreasureApi.class);
        }
        return mTreasureApi;
    }
}
