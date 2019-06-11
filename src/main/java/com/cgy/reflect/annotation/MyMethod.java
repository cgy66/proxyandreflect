package com.cgy.reflect.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyMethod {
    //方法名
    String name() default "";

    //方法描述
    String desc() default "";
}
