package com.example.demo_annottation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.OnClick;


public class AnnotationHelper {

    public static void initView(Activity activity){
        initContentView(activity);
        initBindView(activity);
        setonclick(activity);
    }

    public static void setonclick(final Activity activity) {
        Class clazz = activity.getClass();
        Method[] methods = clazz.getMethods();
        for (final Method method : methods) {
            OnClick click = method.getAnnotation(OnClick.class);
            if (click != null) {
                int[] value = click.value();
                for (int i : value) {
                    if (i == -1) return;
                    View view = activity.findViewById(i);
                    if (view == null) return;
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                method.invoke(activity);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        }
    }


    private static void initContentView(Activity activity) {

        Class<? extends Activity> aClass = activity.getClass();
        ContentView contentView = aClass.getAnnotation(ContentView.class);
        if (contentView!=null){

            try {
                int value = contentView.value();
                Method setContentView = aClass.getMethod("setContentView", int.class);
                setContentView.invoke(activity,value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static void initBindView(Activity activity) {

        Class<? extends Activity> aClass = activity.getClass();
        Field[] fields = aClass.getDeclaredFields();
        if (fields!=null&&fields.length>0){
            for (Field field : fields) {
                ViewInject annotation = field.getAnnotation(ViewInject.class);
                if (annotation!=null){
                    try {
                        int value = annotation.value();
                        View view=activity.findViewById(value);
                        field.setAccessible(true);
                        field.set(activity,view);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }
}
