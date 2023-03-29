package com.dian1.http.handle;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.http.HttpRequest;
import com.dian1.http.handle.base.ClassHandle;
import com.dian1.http.handle.parameter.ParameterHandle;
import com.dian1.http.handle.result.DefaultResultHandle;
import com.dian1.http.handle.result.ResultHandle;
import com.dian1.http.handle.type.TypeHandle;
import com.dian1.http.properties.HttpProperties;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Data
@Component
public class HttpHandleCompose {
    private static Map<Class, HttpHandle> handleHashMap = new HashMap<>();
    @Autowired
    DefaultResultHandle defaultResultHandle;
    private Map<Class, List<ClassHandle>> classHandle = new ConcurrentHashMap<>();
    private Map<Method, List<TypeHandle>> typeHandle = new ConcurrentHashMap<>();
    private Map<Method, List<ParameterHandle>> parameterHandle = new ConcurrentHashMap<>();
    private Map<Method, List<ResultHandle>> resultHandle = new ConcurrentHashMap<>();

    public HttpHandleCompose(ObjectProvider<HttpHandle> typeHandles) {
        Iterator<HttpHandle> iterator = typeHandles.iterator();
        while (iterator.hasNext()) {
            HttpHandle handle = iterator.next();
            Class typeArgument = ClassUtil.getTypeArgument(handle.getClass());
            handleHashMap.put(typeArgument, handle);
        }
    }

    public static List<ClassHandle> classHandle(Class httpinterfaces) {
        List<ClassHandle> typeHandles = new LinkedList<>();
        for (Class key : handleHashMap.keySet()) {
            HttpHandle httpHandle = handleHashMap.get(key);
            if (httpHandle instanceof ClassHandle) {
                Annotation annotation = AnnotationUtils.findAnnotation(httpinterfaces, key);
                if (null != annotation) {
                    typeHandles.add((ClassHandle) httpHandle);
                }
            }
        }
        typeHandles.sort(Comparator.comparingInt(sort::order));
        return typeHandles;
    }

    public static List<TypeHandle> typeHandle(AnnotatedElement element) {
        List<TypeHandle> typeHandles = new LinkedList<>();
        for (Class key : handleHashMap.keySet()) {
            HttpHandle httpHandle = handleHashMap.get(key);
            if (httpHandle instanceof TypeHandle) {
                Annotation annotation = AnnotationUtils.findAnnotation(element, key);
                if (null != annotation) {
                    typeHandles.add((TypeHandle) httpHandle);
                }
            }
        }
        typeHandles.sort(Comparator.comparingInt(sort::order));
        return typeHandles;
    }

    public static List<ParameterHandle> parameterHandle(Method method) {
        List<ParameterHandle> typeHandles = new LinkedList<>();
        for (Class key : handleHashMap.keySet()) {
            HttpHandle httpHandle = handleHashMap.get(key);
            if (httpHandle instanceof ParameterHandle) {
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                for (Annotation[] annotations : parameterAnnotations) {
                    for (Annotation annotation1 : annotations) {
                        if (annotation1.annotationType().isAssignableFrom(key)) {
                            typeHandles.add((ParameterHandle) httpHandle);
                        }
                    }
                }
            }
        }
        typeHandles.sort(Comparator.comparingInt(sort::order));
        return typeHandles;
    }

    public static List<ResultHandle> resultHandle(AnnotatedElement element) {
        List<ResultHandle> typeHandles = new LinkedList<>();
        for (Class key : handleHashMap.keySet()) {
            HttpHandle httpHandle = handleHashMap.get(key);
            if (httpHandle instanceof ResultHandle) {
                Annotation annotation = AnnotationUtils.findAnnotation(element, key);
                if (null != annotation) {
                    typeHandles.add((ResultHandle) httpHandle);
                }
            }
        }
        typeHandles.sort(Comparator.comparingInt(sort::order));
        return typeHandles;
    }

    public static Map<Class, HttpHandle> getHandleHashMap() {
        return handleHashMap;
    }

    public List<ClassHandle> getAllClassHandle(Class aClass) {
        return classHandle.computeIfAbsent(aClass, HttpHandleCompose::classHandle);
    }

    public <T> List<TypeHandle> getAllTypeHandle(Method tClass) {
        return typeHandle.computeIfAbsent(tClass, HttpHandleCompose::typeHandle);
    }

    public <T> List<ParameterHandle> getAllParameterHandle(Method method) {
        return parameterHandle.computeIfAbsent(method, HttpHandleCompose::parameterHandle);
    }

    public <T> List<ResultHandle> getAllResultHandle(Method method) {
        return resultHandle.computeIfAbsent(method, HttpHandleCompose::resultHandle);
    }

    public Object DefaultResultResolving(HttpRequest httpRequest, HttpProperties method, Annotation annotation) {
        return defaultResultHandle.resolving(httpRequest, method, annotation);
    }
}
