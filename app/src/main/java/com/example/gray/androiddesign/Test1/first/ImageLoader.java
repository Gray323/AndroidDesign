package com.example.gray.androiddesign.Test1.first;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Gray on 2017/10/8.
 * 图片记载类
 */

public class ImageLoader {
    //内存缓存
    ImageCache mImageCache = new ImageCache();
    //SD卡缓存
    DiskCache mDiskCache = new DiskCache();
    //双缓存
    DoubleCache mDoubleCache = new DoubleCache();
    //是否使用SD卡缓存
    boolean isUseDiskCache = false;
    //使用双缓存
    boolean isUseDoubleCache = false;
    //线程池，线程数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void useDiskCache(boolean useDiskCache){
        isUseDiskCache = useDiskCache;
    }

    public void useDoubleCache(boolean useDoubleCache){
        isUseDoubleCache = useDoubleCache;
    }

    public void displayImage(final String url, final ImageView imageView){
        Bitmap bmp = null;
        if (isUseDoubleCache){
           bmp = mDoubleCache.get(url);
        }else if (isUseDiskCache){
            bmp = mDiskCache.get(url);
        }else{
            bmp = mImageCache.get(url);
        }
        if (bmp != null){
            imageView.setImageBitmap(bmp);
        }else{//没有缓存，则提交给线程池进行下载
            imageView.setTag(url);
            mExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = downloadImage(url);
                    if (bitmap == null){
                        return;
                    }
                    if (imageView.getTag().equals(url)){
                        imageView.setImageBitmap(bitmap);
                    }
                    mImageCache.put(url, bitmap);
                }
            });
        }


    }

    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

}
