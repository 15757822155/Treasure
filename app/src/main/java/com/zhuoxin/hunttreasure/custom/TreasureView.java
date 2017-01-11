package com.zhuoxin.hunttreasure.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.zhuoxin.hunttreasure.R;
import com.zhuoxin.hunttreasure.treasure.Treasure;
import com.zhuoxin.hunttreasure.treasure.map.MapFragment;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by gqq on 2017/1/10.
 */

public class TreasureView extends RelativeLayout {

    @BindView(R.id.tv_treasureTitle)
    TextView mTvTreasureTitle;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_treasureLocation)
    TextView mTvTreasureLocation;

    public TreasureView(Context context) {
        this(context, null);
    }

    public TreasureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TreasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_treasure, this, true);
        ButterKnife.bind(this);
    }

    // 可以对外提供一个方法：根据宝藏的信息，填充需要的内容
    public void bindTreasure(@NonNull Treasure treasure) {

        // 标题和地址
        mTvTreasureTitle.setText(treasure.getTitle());
        mTvTreasureLocation.setText(treasure.getLocation());

        double distance = 0.00d;// 距离

        // 宝藏的位置
        LatLng latLng = new LatLng(treasure.getLatitude(), treasure.getLongitude());

        // 我们的定位(位置)
        LatLng myLocation = MapFragment.getMyLocation();
        if (myLocation == null) {
            distance = 0.00d;
        }

        // 规范显示的样式
        distance = DistanceUtil.getDistance(latLng, myLocation);
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String text = decimalFormat.format(distance / 1000) + "km";
        mTvDistance.setText(text);
    }
}
