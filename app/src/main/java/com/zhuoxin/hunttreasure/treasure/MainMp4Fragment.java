package com.zhuoxin.hunttreasure.treasure;

import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoxin.hunttreasure.commons.ActivityUtils;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/29.
 */

public class MainMp4Fragment extends Fragment implements TextureView.SurfaceTextureListener {

    private TextureView mTextureView;
    private MediaPlayer mMediaPlayer;
    private ActivityUtils mActivityUtils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivityUtils = new ActivityUtils(this);
        //fragment 全屏显示播放视频的控件
        mTextureView = new TextureView(getContext());
        return mTextureView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置监听:因为播放显示内容需要SurfaceTexture，所以设置一个监听，看SurfaceTexture有没有准备好或有没有变化等
        mTextureView.setSurfaceTextureListener(this);
    }

    //#############   texture监听开始
    @Override
    //确实准备好了
    public void onSurfaceTextureAvailable(final SurfaceTexture surface, int width, int height) {
        try {
            // 打开播放的资源文件
            AssetFileDescriptor openFd = getContext().getAssets().openFd("welcome.mp4");
            // 拿到MediaPlayer需要的资源类型
            FileDescriptor fileDescriptor = openFd.getFileDescriptor();
            mMediaPlayer = new MediaPlayer();
            // 设置播放的资源给MediaPlayer
            mMediaPlayer.setDataSource(fileDescriptor,openFd.getStartOffset(),openFd.getLength());
            mMediaPlayer.prepareAsync();
            // 设置监听：看有没有准备好，好了，开始播放
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Surface mySurface = new Surface(surface);
                    mMediaPlayer.setSurface(mySurface);
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.seekTo(0);
                    mMediaPlayer.start();
                }
            });
        } catch (IOException e) {
            mActivityUtils.showToast("媒体文件播放失败了");
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
    //#############   texture监听结束

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
