package com.dian1.http.handle;


import com.dian1.http.properties.HttpProperties;

import java.lang.annotation.Annotation;

/**
 * 类别处理器比如 {@link com.dian1.http.annotate.type.Get} {@link com.dian1.http.annotate.type.Post}等
 *
 * @author zhangzhi
 * @date 2023/3/27
 */
public interface TypeHandle<T extends Annotation> extends HttpHandle {

    /**
     * 处理方法
     *
     * @param properties 配置类对象
     * @param t          当前注解
     * @return
     */
    HttpProperties resolving(HttpProperties properties, T t);

}
