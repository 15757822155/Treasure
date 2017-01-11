package com.zhuoxin.hunttreasure.treasure.map;

import com.zhuoxin.hunttreasure.treasure.Treasure;

import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 */

public interface MapMvpView {
    //弹吐司
    void showMessage(String msg);
    //设置数据
    void setData(List<Treasure> list);
}
