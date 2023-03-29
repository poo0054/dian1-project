package com.dian1.http.handle.base;

import com.dian1.http.handle.HttpHandle;
import com.dian1.http.properties.HttpProperties;

import java.lang.annotation.Annotation;

/**
 * @author zhangzhi
 * @date 2023/3/28
 */
public interface ClassHandle<T extends Annotation> extends HttpHandle {
    HttpProperties resolving(HttpProperties httpProperties, T annotation);
}
