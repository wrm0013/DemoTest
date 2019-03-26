package com.example.designpatterns.loader02;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ImageCache {

    //图片缓存
    LruCache<String, Bitmap> mImageCache;

    public ImageCache(){
        initImageCache();
    }

    private void initImageCache(){
        //计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 4;

        mImageCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public Bitmap get(String imageUrl){
        return mImageCache.get(imageUrl);
    }

    public void put(String url,Bitmap bitmap){
        mImageCache.put(url,bitmap);
    }

}
