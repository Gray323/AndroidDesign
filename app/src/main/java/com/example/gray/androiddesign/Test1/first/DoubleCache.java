package com.example.gray.androiddesign.Test1.first;

import android.graphics.Bitmap;

/**
 * Created by Gray on 2017/10/8.
 * 双缓存，获取图片时先从内存缓存中获取，如果内存中没有缓存该图片，再从SD卡中获取
 * 缓存图片也是在内存和SD卡中都缓存一份
 */

public class DoubleCache {
    ImageCache mMemoryCache = new ImageCache();
    DiskCache mDiskCache = new DiskCache();

    //先从内存缓存中获取图片，如果没有，再从SD卡中获取
    public Bitmap get(String url){
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    //将图片缓存到内存和SD卡中
    public void put(String url, Bitmap bitmap){
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }

}
