package com.example.designpatterns.loader03;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ImageCache03 {

    LruCache<String, Bitmap> mImageCache;

    public ImageCache03(){
        initImageCache();
    }

    private void initImageCache(){

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 4;

        mImageCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public void put(String url, Bitmap bitmap){
        mImageCache.put(url,bitmap);
    }

    public Bitmap get(String url){
        return mImageCache.get(url);
    }

}
