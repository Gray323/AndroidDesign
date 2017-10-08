package com.example.gray.androiddesign.replace;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Gray on 2017/10/8.
 */

public class MemoryCache implements ImageCache {

    private LruCache<String, Bitmap> mMemeryCache;

    public MemoryCache(){
        //初始化LRU缓存
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
