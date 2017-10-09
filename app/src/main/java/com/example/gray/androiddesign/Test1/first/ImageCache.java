package com.example.gray.androiddesign.Test1.first;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Gray on 2017/10/8.
 */

public class ImageCache {

    //图片缓存
    LruCache<String, Bitmap> mImageCache;

    public ImageCache(){
        initImageCache();
    }

    private void initImageCache() {
        //计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取四分之一的可用内存作为缓存
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public Bitmap get(String url) {
        return mImageCache.get(url);
    }

    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }
}
