package com.example.administrator.viewutilsdemo.utils;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/5/3.
 */


/*
*   实现思路  注解 +反射   找到含有注解的类成员，遍历暴力反射赋值
* */
public class ViewUtils {

    /*
    *注解的入口
    */
    public static void inject(Activity activity) {
        bindView(activity);
        bindOnClick(activity);
    }

    /*
    * 绑定点击事件
    * */
    private static void bindOnClick(final Activity activity) {
        // 1 获得class
        Class clazz = activity.getClass();
        // 获得字节码中所有的Method （方法）
        Method[] methods = clazz.getMethods();
        // 遍历 methods获得含有注解的method
        for (final Method method : methods) {
            OnClick onClick = method.getAnnotation(OnClick.class);
            if (onClick != null) {

                int resId = onClick.value();
                final View view = activity.findViewById(resId);
                // 给view绑定点击事件
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 回调用户的点击方法
                        method.setAccessible(true);
                        try {
                            method.invoke(activity,view);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });

            } else {
                //TODO do nothing
            }

        }

    }

    /*
    *绑定View控件
    * */
    private static void bindView(Activity activity) {
        // 1 获得class
        Class clazz = activity.getClass();
        // 获得字节码中所有的field
        Field[] fields = clazz.getDeclaredFields();
        // 遍历 Field 获得含有注解的field
        int len = fields.length;
        for (int i = 0; i < len; i++) {
            // 获得字段上面注解的对象
            ViewInject viewInject = fields[i].getAnnotation(ViewInject.class);
            if (viewInject != null) {
                // 获得注解的值
                int resId = viewInject.value();
                // 通过 activity  的findViewById(R.id.) 找到 id为resId的控件
                View view = activity.findViewById(resId);
                // 通过反射将当前的view 设置给当前的field 控件
                fields[i].setAccessible(true);
                try {
                    fields[i].set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            } else {
                // 没有注解时 viewInject返回null
                //TODO do nothing !!!
            }
        }
    }
}
