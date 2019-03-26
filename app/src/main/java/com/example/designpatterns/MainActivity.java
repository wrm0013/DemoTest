package com.example.designpatterns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.designpatterns.loader02.ImageLoader02;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
//        new ImageLoader().displayImage("https://www.baidu.com/img/bd_logo1.png?where=super",imageView);
        new ImageLoader02().displayImage("https://www.baidu.com/img/bd_logo1.png?where=super",imageView);
    }
}
