package com.gupao.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {
 
    /**
     * 对外发布的服务接口地址
     */
    Class<?> value();
 
    /**
     * 版本号
     */
    String version() default "";
 
}
