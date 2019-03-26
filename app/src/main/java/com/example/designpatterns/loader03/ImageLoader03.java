package com.example.designpatterns.loader03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader03 {

    //内存缓存
    ImageCache03 mImageCache = new ImageCache03();

    //SD卡缓存
    DiskCache mDiskCache = new DiskCache();

    //是否使用SD卡缓存
    boolean isUseDiskCache = false;

    //线程池
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void displayImage(final String url, final ImageView imageView){
         Bitmap bitmap = isUseDiskCache ? mDiskCache.get(url) : mImageCache.get(url);
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bm = downloadImage(url);
                if (bm == null)
                    return;
                if (imageView.getTag().equals(url))
                    imageView.setImageBitmap(bm);
                if (isUseDiskCache)
                    mDiskCache.put(url,bm);
                else
                    mImageCache.put(url,bm);
            }
        });

    }

    public Bitmap downloadImage(String imageUrl){
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    public void setUseDiskCache(boolean useDiskCache){
        isUseDiskCache = useDiskCache;
    }


















}
