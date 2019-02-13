package com.example.butterknife;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.butterknife.adapter.MainPageAdapter;
import com.example.butterknife.annotation.AnnotationHelper;
import com.example.butterknife.annotation.InjectViewAnnotation;

public class MainActivity extends AppCompatActivity {


    @InjectViewAnnotation(R.id.bottom_indicator)
    TabLayout bottom_indicator;
    @InjectViewAnnotation(R.id.contents)
    ViewPager contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnnotationHelper.bindView(this);
        initView();
    }

    private void initView() {
        contents.setAdapter(new MainPageAdapter(getSupportFragmentManager()));
        bottom_indicator.setupWithViewPager(contents);
    }
}
