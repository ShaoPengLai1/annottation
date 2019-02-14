package com.example.demo_annottation;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo_annottation.entity.MessageEvent;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity{


//
    @ViewInject(R.id.cicleView)
    Button cicleView;

    @ViewInject(R.id.radiuView)
    Button radiuView;
    @ViewInject(R.id.wH)
    Button wH;
    @ViewInject(R.id.loginGif)
    Button loginGif;
    @ViewInject(R.id.simpleView)
    SimpleDraweeView simpleDraweeView;
    @ViewInject(R.id.simpleView)
    SimpleDraweeView simpleDraweeView2;
    @ViewInject(R.id.reflex)
    Button reflex;
    @ViewInject(R.id.reflexList)
    Button reflexList;


    Uri uri2=Uri.parse("http://img.huofar.com/data/jiankangrenwu/shizi.gif");
    Uri uri=Uri.parse("http://img.bimg.126.net/photo/ZZ5EGyuUCp9hBPk6_s4Ehg==/5727171351132208489.jpg");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        AnnotationHelper.initView(this);
    }

    @OnClick(R.id.cicleView)
    public void aa(View view){
        Toast.makeText(MainActivity.this,"1111",Toast.LENGTH_LONG).show();

        simpleDraweeView.setImageURI(uri);
        RoundingParams params = RoundingParams.fromCornersRadius(5f);
        params.setRoundAsCircle(true);
        simpleDraweeView.getHierarchy().setRoundingParams(params);
    }
    @OnClick(R.id.radiuView)
    public void bb(View view){
        simpleDraweeView.setImageURI(uri);
        RoundingParams params1 = RoundingParams.fromCornersRadius(7f);
        //params1.setCornersRadius(100f);
        simpleDraweeView.getHierarchy().setRoundingParams(params1);
    }
    @OnClick(R.id.wH)
    public void cc(View view){
        simpleDraweeView.setVisibility(View.GONE);
        simpleDraweeView2.setVisibility(View.VISIBLE);
        simpleDraweeView2.setImageURI(uri);
        simpleDraweeView2.setAspectRatio(1.2f);
    }
    @OnClick(R.id.loginGif)
    public void dd(View view){
        DraweeController controller2 = Fresco.newDraweeControllerBuilder()
                .setUri(uri2)
//                        .setControllerListener(controllerListener)
                .setAutoPlayAnimations(true)
                // 其他设置（如果有的话）
                .build();
        simpleDraweeView.setController(controller2);
    }

    @OnClick(R.id.reflex)
    public void ee(View view){
        MessageEvent event=new MessageEvent();
        MyAnno annotation = event.getClass().getAnnotation(MyAnno.class);
        String[] request = annotation.request();
        try {
            Field f = event.getClass().getDeclaredField("name");
            Fields annotation1 = f.getAnnotation(Fields.class);
            event.setName(annotation1.value());
            Toast.makeText(MainActivity.this,event.getName(),Toast.LENGTH_LONG).show();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    @OnClick(R.id.reflexList)
    public void ff(View view){
        ArrayList list1=new ArrayList();
        list1.add("1234");
        list1.add(100);
        Class c2 = list1.getClass();
        ArrayList<String> list=new ArrayList<>();
        Class c = list.getClass();
        try {
            Method method = c2.getMethod("add", Object.class);
            method.invoke(list1,100);
            for (Object s : list1) {
                Toast.makeText(MainActivity.this,s.toString(),Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
