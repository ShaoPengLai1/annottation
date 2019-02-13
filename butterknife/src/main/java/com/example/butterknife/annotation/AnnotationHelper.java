package com.example.butterknife.annotation;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.reflect.Field;

public class AnnotationHelper {
    /*private static final String TAG="AnnotationHelper";
    private static class InstanceHelper{
        public static final AnnotationHelper instance=new AnnotationHelper();
    }
    public static AnnotationHelper getInstance(){
        return InstanceHelper.instance;
    }*/
    public static void bindView(Object object) {
        try {
            init(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void init(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();
        View view = null;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){

            if (field.isAnnotationPresent(InjectViewAnnotation.class)){
                InjectViewAnnotation injectViewAnnotation = field.getAnnotation(InjectViewAnnotation.class);
                int id = injectViewAnnotation.value();
                if (id<0){
                    try {
                        throw new Exception("error");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    field.setAccessible(true);
                    if (object instanceof View){
                        view = ((View) object).findViewById(id);
                    }else if (object instanceof Activity){
                        view = ((Activity) object).findViewById(id);
                    }else if (object instanceof Fragment){
                        view = ((View) object).findViewById(id);
                    }
                    field.set(object,view);
                }
            }
        }
    }
}
