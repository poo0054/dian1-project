package com.dian1.http.handle;

import com.dian1.http.properties.HttpProperties;

import java.lang.annotation.Annotation;

/**
 * 类接口,当前支持OpenHttp.
 *
 * @author zhangzhi
 * @date 2023/3/28
 */
public interface ClassHandle<T extends Annotation> extends HttpHandle {
    /**
     * 解析当前接口,
     *
     * @param httpProperties
     * @param annotation
     * @return
     */
    HttpProperties resolving(HttpProperties httpProperties, T annotation);
}
