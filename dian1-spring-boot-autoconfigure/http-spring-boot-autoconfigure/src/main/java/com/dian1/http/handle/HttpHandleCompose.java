package com.dian1.http.handle;

import cn.hutool.core.util.ClassUtil;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Data
@Component
public class HttpHandleCompose {
    private static Map<Class<? extends Annotation>, HttpHandle> handleHashMap = new HashMap<>();
    private Map<Class, Set<TypeHandle<? extends Annotation>>> typeHandle = new ConcurrentHashMap<>();
    private Map<Method, Set<ParameterHandle<? extends Annotation>>> parameterHandle = new ConcurrentHashMap<>();
    private Map<Class, Set<ResultHandle<? extends Annotation>>> resultHandle = new ConcurrentHashMap<>();

    public HttpHandleCompose(ObjectProvider<HttpHandle> typeHandles) {
        while (typeHandles.iterator().hasNext()) {
            HttpHandle handle = typeHandles.iterator().next();
            Class typeArgument = ClassUtil.getTypeArgument(typeHandle.getClass());
            handleHashMap.put(typeArgument, handle);
        }
    }

    public static Set<TypeHandle<? extends Annotation>> typeHandle(AnnotatedElement element) {
        Set<TypeHandle<? extends Annotation>> typeHandles = new HashSet<>();
        for (Class<? extends Annotation> key : handleHashMap.keySet()) {
            HttpHandle httpHandle = handleHashMap.get(key);
            if (httpHandle instanceof TypeHandle) {
                Annotation annotation = AnnotationUtils.findAnnotation(element, key);
                if (null != annotation) {
                    typeHandles.add((TypeHandle<? extends Annotation>) httpHandle);
                }
            }
        }
        return typeHandles;
    }

    public static Set<ParameterHandle<? extends Annotation>> parameterHandle(AnnotatedElement element) {
        Set<ParameterHandle<? extends Annotation>> typeHandles = new HashSet<>();
        for (Class<? extends Annotation> key : handleHashMap.keySet()) {
            HttpHandle httpHandle = handleHashMap.get(key);
            if (httpHandle instanceof ParameterHandle) {
                Annotation annotation = AnnotationUtils.findAnnotation(element, key);
                if (null != annotation) {
                    typeHandles.add((ParameterHandle<? extends Annotation>) httpHandle);
                }
            }
        }
        return typeHandles;
    }

    public static Set<ResultHandle<? extends Annotation>> resultHandle(AnnotatedElement element) {
        Set<ResultHandle<? extends Annotation>> typeHandles = new HashSet<>();
        for (Class<? extends Annotation> key : handleHashMap.keySet()) {
            HttpHandle httpHandle = handleHashMap.get(key);
            if (httpHandle instanceof ResultHandle) {
                Annotation annotation = AnnotationUtils.findAnnotation(element, key);
                if (null != annotation) {
                    typeHandles.add((ResultHandle<? extends Annotation>) httpHandle);
                }
            }
        }
        return typeHandles;
    }

    public <T> Set<TypeHandle<? extends Annotation>> getAllTypeHandle(Class tClass) {
        return typeHandle.computeIfAbsent(tClass, HttpHandleCompose::typeHandle);
    }

    public <T> Set<ParameterHandle<? extends Annotation>> getAllParameterHandle(Method method) {
        return parameterHandle.computeIfAbsent(method, HttpHandleCompose::parameterHandle);
    }

    public <T> Set<ResultHandle<? extends Annotation>> getAllResultHandle(Class aClass) {
        return resultHandle.computeIfAbsent(aClass, HttpHandleCompose::resultHandle);
    }

}
