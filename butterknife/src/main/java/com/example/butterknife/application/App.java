package com.example.butterknife.application;

import android.app.Application;
import android.graphics.Bitmap;

import com.example.butterknife.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        /*ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this)
                .diskCacheSize(50*1024*1024)
                .memoryCacheSizePercentage(10)
                .defaultDisplayImageOptions(new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .showImageOnLoading(R.mipmap.ic_launcher)
                        .showImageOnFail(R.mipmap.ic_launcher)
                        .showImageForEmptyUri(R.mipmap.ic_launcher)
                        .build())
                .build());*/
    }
}
