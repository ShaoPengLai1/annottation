package com.example.customannotations;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class AnnotationHelper {

    private static final String TAG = "AnnotationHelper";

    private static class InstanceHelper{
        public static final AnnotationHelper instance=new AnnotationHelper();
    }
    public static AnnotationHelper getInstance(){
        return InstanceHelper.instance;
    }

    public void init(Activity activity){
        Class clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(InjectViewAnnotation.class)){
                InjectViewAnnotation injectViewAnnotation = field.getAnnotation(InjectViewAnnotation.class);
                try {
                    field.set(activity,activity.findViewById(injectViewAnnotation.value()));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (field.isAnnotationPresent(FindClick.class)){
                try {
                    FindClick findClick = field.getAnnotation(FindClick.class);
                    View view = activity.findViewById(findClick.id());
                    view.setOnClickListener((View.OnClickListener) activity);
                    field.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
