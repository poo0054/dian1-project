package com.dian1.http.utils;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author zhangzhi
 * @date 2023/3/29
 */
public class AnnotationUtil {

    public Annotation[] getAllAnnotations(Method method) {
        return AnnotationUtils.getAnnotations(method);
    }

    public Annotation[] getAnnotations(Annotation[] annotations) {
        for (Annotation annotation : annotations) {

            Annotation[] annotations2 = annotation.annotationType().getAnnotations();
        }
        return null;
    }
}
