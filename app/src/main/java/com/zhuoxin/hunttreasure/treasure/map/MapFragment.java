package com.zhuoxin.hunttreasure.treasure.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.zhuoxin.hunttreasure.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/4.
 */

public class MapFragment extends Fragment {
    @BindView(R.id.map_frame)
    FrameLayout mMapFrame;
    @BindView(R.id.center)
    Space mCenter;
    @BindView(R.id.iv_located)
    ImageView mIvLocated;
    @BindView(R.id.btn_HideHere)
    Button mBtnHideHere;
    @BindView(R.id.centerLayout)
    RelativeLayout mCenterLayout;
    @BindView(R.id.iv_scaleUp)
    ImageView mIvScaleUp;
    @BindView(R.id.iv_scaleDown)
    ImageView mIvScaleDown;
    @BindView(R.id.tv_located)
    TextView mTvLocated;
    @BindView(R.id.tv_satellite)
    TextView mTvSatellite;
    @BindView(R.id.tv_compass)
    TextView mTvCompass;
    @BindView(R.id.tv_currentLocation)
    TextView mTvCurrentLocation;
    @BindView(R.id.iv_toTreasureInfo)
    ImageView mIvToTreasureInfo;
    @BindView(R.id.et_treasureTitle)
    EditText mEtTreasureTitle;
    @BindView(R.id.layout_bottom)
    FrameLayout mLayoutBottom;
    private BaiduMap mBaiduMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化百度地图
        initMapView();
    }

    private void initMapView() {
        //设置地图状态
        MapStatus mapStatus = new MapStatus.Builder()
                .overlook(0)//仰角
                .rotate(0)//旋转角度
                .zoom(20)//缩放3--21默认12
                .build();

        //设置百度的设置信息
        BaiduMapOptions options = new BaiduMapOptions()
                .mapStatus(mapStatus)
                .compassEnabled(true)//是否显示指南针
                .zoomGesturesEnabled(true)//是否允许缩放手势
                .scaleControlEnabled(false)//不显示比例尺(比如5公里)
                .zoomControlsEnabled(false)//不显示缩放控件(加减控件)
                ;

        //创建
        MapView mapView = new MapView(getContext(), options);

        //在布局上添加地图控件:0,代表第一位,最下的一层
        mMapFrame.addView(mapView, 0);

        //拿到地图的操作类(控制器,操作地图使用这个)
        mBaiduMap = mapView.getMap();
    }

    //卫星视图和普通视图的切换
    @OnClick(R.id.tv_satellite)
    public void switchMapType() {
        //获取当前地图类型
        int mapType = mBaiduMap.getMapType();
        //切换类型
        mapType = (mapType == BaiduMap.MAP_TYPE_NORMAL) ? BaiduMap.MAP_TYPE_SATELLITE : BaiduMap.MAP_TYPE_NORMAL;
        //卫星和普通的文字显示
        String msg = (mapType == BaiduMap.MAP_TYPE_NORMAL) ? "卫星" : "普通";
        mBaiduMap.setMapType(mapType);
        mTvSatellite.setText(msg);
    }

    //指南针
    @OnClick(R.id.tv_compass)
    public void switchCompass() {
        boolean compassEnabled = mBaiduMap.getUiSettings().isCompassEnabled();
        //如果显示就隐藏,如果隐藏就显示
        mBaiduMap.getUiSettings().setCompassEnabled(!compassEnabled);
    }

    //地图的缩放
    @OnClick({R.id.iv_scaleDown, R.id.iv_scaleUp})
    public void scaleMap(View view) {
        switch (view.getId()) {
            case R.id.iv_scaleDown:
                //缩放等级降低(地图变小,显示更多的地方)
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomOut());
                break;
            case R.id.iv_scaleUp:
                //缩放等级提高
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomIn());
                break;
        }
    }

    @OnClick(R.id.tv_located)
    public void located() {
//        LocationClientOption locOption = new LocationClientOption();
//        locOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
//        locOption.setCoorType("bd09ll");// 设置定位结果类型
//        locOption.setScanSpan(5000);// 设置发起定位请求的间隔时间,ms
//        locOption.setIsNeedAddress(true);// 返回的定位结果包含地址信息
//        locOption.setNeedDeviceDirect(true);// 设置返回结果包含手机的方向
//
//
//        LocationClient locationClient =new LocationClient(getActivity());
//        locationClient.setLocOption(locOption);
    }
}
