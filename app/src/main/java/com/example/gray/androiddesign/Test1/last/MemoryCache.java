package com.example.gray.androiddesign.Test1.last;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Gray on 2017/10/8.
 */

public class MemoryCache implements ImageCache {

    private LruCache<String, Bitmap> mMemeryCache;

    public MemoryCache(){
        //计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取四分之一的可用内存作为缓存
        final int cacheSize = maxMemory / 4;
        //初始化LRU缓存
        mMemeryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap get(String url) {
        return mMemeryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemeryCache.put(url, bitmap);
    }
}
