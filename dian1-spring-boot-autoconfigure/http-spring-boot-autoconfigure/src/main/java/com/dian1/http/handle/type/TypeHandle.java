package com.dian1.http.handle.type;


import com.dian1.http.handle.HttpHandle;
import com.dian1.http.properties.HttpProperties;

import java.lang.annotation.Annotation;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
public interface TypeHandle<T extends Annotation> extends HttpHandle {

    HttpProperties resolving(HttpProperties properties, T t);

}
