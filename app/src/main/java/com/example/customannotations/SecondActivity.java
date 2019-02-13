package com.example.customannotations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    @InjectViewAnnotation(R.id.dianwo)
    Button button;
    @FindClick(id = R.id.dianwo)
    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        AnnotationHelper.getInstance().init(this);

    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dianwo:
                jumpActivity();
                break;
        }
    }

    private void jumpActivity() {
        EventBus.getDefault().post(new MessageEvent("欢迎大家浏览我写的博客"));
        finish();
    }
}
