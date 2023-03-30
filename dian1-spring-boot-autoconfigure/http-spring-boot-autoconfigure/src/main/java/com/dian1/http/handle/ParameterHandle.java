package com.dian1.http.handle;


import com.dian1.http.properties.HttpProperties;

import java.lang.annotation.Annotation;

/**
 * 参数处理对象 一般添加在方法上面 如{@link com.dian1.http.annotate.parameter.Form}
 *
 * @author zhangzhi
 * @date 2023/3/27
 */
public interface ParameterHandle<T extends Annotation> extends HttpHandle {


    HttpProperties resolving(HttpProperties properties, Object arg, T t);


}
