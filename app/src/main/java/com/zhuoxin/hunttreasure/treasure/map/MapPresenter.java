package com.zhuoxin.hunttreasure.treasure.map;

import com.zhuoxin.hunttreasure.net.NetClient;
import com.zhuoxin.hunttreasure.treasure.Area;
import com.zhuoxin.hunttreasure.treasure.Treasure;
import com.zhuoxin.hunttreasure.treasure.TreasureRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/1/10.
 */

public class MapPresenter {
    private MapMvpView mMapMvpView;
    private Area mArea;

    public MapPresenter(MapMvpView mapMvpView) {
        this.mMapMvpView = mapMvpView;
    }

    public void getTreasure(Area area) {
        //如果是已经缓存过的
        if (TreasureRepo.getInstance().isCached(area)) {
            return;
        }
        this.mArea = area;
        Call<List<Treasure>> listCall = NetClient.getInstances().getTreasureApi().getTreasureInArea(area);
        listCall.enqueue(mCallback);
    }

    private Callback<List<Treasure>> mCallback = new Callback<List<Treasure>>() {
        @Override
        public void onResponse(Call<List<Treasure>> call, Response<List<Treasure>> response) {
            if (response.isSuccessful()) {
                List<Treasure> treasureList = response.body();
                if (treasureList == null) {
                    mMapMvpView.showMessage("发生了未知的错误");
                    return;
                }

                // 做缓存
                TreasureRepo.getInstance().addTreasure(treasureList);
                TreasureRepo.getInstance().cache(mArea);

                mMapMvpView.setData(treasureList);
            }
        }

        @Override
        public void onFailure(Call<List<Treasure>> call, Throwable t) {
            mMapMvpView.showMessage("请求失败");
        }
    };
}
