package com.dian1.http.handle.parameter;


import com.dian1.http.handle.HttpHandle;
import com.dian1.http.properties.HttpProperties;

import java.lang.annotation.Annotation;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
public interface ParameterHandle<T extends Annotation> extends HttpHandle {


    HttpProperties resolving(HttpProperties properties, Object arg, T t);


}
