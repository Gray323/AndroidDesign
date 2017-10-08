package com.example.gray.androiddesign.replace;

import android.graphics.Bitmap;

/**
 * Created by Gray on 2017/10/8.
 */

public interface ImageCache {
    public Bitmap get(String url);
    public void put(String url, Bitmap bitmap);
}
