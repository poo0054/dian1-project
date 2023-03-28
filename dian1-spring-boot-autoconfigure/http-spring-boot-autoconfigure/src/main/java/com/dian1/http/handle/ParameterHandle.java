package com.dian1.http.handle;


import com.dian1.http.properties.HttpProperties;

import java.lang.annotation.Annotation;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
public interface ParameterHandle<T extends Annotation> extends HttpHandle {


    void resolving(HttpProperties httpProperties);


}
