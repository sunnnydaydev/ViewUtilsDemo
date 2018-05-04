package com.example.administrator.viewutilsdemo.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/5/3.
 */

@Retention(RetentionPolicy.RUNTIME) // 作用的周期
@Target(ElementType.FIELD)         // 注解作用范围  类的字段
public @interface ViewInject {
    int value();
}
