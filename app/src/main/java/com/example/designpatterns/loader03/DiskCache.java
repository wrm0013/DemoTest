package com.example.designpatterns.loader03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DiskCache {

    static String cacheDir = "sdcard/cache/";

    //从缓存中获取图片
    public Bitmap get(String url){
        return BitmapFactory.decodeFile(cacheDir+url);
    }

    //将图片缓存到内存中
    public void put(String url, Bitmap bitmap){
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


















}
