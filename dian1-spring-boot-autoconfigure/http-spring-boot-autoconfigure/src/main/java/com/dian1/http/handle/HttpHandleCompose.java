package com.dian1.http.handle;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import com.dian1.http.handle.parameter.FormHandle;
import com.dian1.http.handle.result.DefaultResultHandle;
import com.dian1.http.properties.HttpProperties;
import com.dian1.http.proxy.HttpProxy;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理器的Compose
 *
 * @author zhangzhi
 * @date 2023/3/27
 */
@Data
@Component
public class HttpHandleCompose {
    /**
     * 用来存储spring中所有的HttpHandle
     */
    private static Map<Class, HttpHandle> handleHashMap = new HashMap<>();
    @Autowired
    private DefaultResultHandle defaultResultHandle;

    @Autowired
    private FormHandle formHandle;

    /**
     * 对应的ClassHandle的实现
     */
    private Map<Class, List<ClassHandle>> classHandle = new ConcurrentHashMap<>();
    /**
     * 对应的TypeHandle的实现
     */
    private Map<Method, List<TypeHandle>> typeHandle = new ConcurrentHashMap<>();
    /**
     * 对应ParameterHandle的实现
     */
    private Map<Method, List<ParameterHandle>> parameterHandle = new ConcurrentHashMap<>();
    /**
     * 对应ResultHandle的实现
     */
    private Map<Method, List<ResultHandle>> resultHandle = new ConcurrentHashMap<>();

    public HttpHandleCompose(ObjectProvider<HttpHandle> typeHandles) {
        Iterator<HttpHandle> iterator = typeHandles.iterator();
        while (iterator.hasNext()) {
            HttpHandle handle = iterator.next();
            Class typeArgument = ClassUtil.getTypeArgument(handle.getClass());
            if (null != typeArgument) {
                handleHashMap.put(typeArgument, handle);
            }
        }
    }

    public static List<ClassHandle> classHandle(Class httpinterfaces) {
        List<ClassHandle> typeHandles = new LinkedList<>();
        Annotation[] annotations = AnnotationUtil.getAnnotations(httpinterfaces, true);
        for (Annotation annotation : annotations) {
            for (Class key : handleHashMap.keySet()) {
                HttpHandle httpHandle = handleHashMap.get(key);
                if (httpHandle instanceof ClassHandle && annotation.annotationType().isAssignableFrom(key)) {
                    typeHandles.add((ClassHandle) httpHandle);
                }
            }
        }
        typeHandles.sort(Comparator.comparingInt(sort::order));
        return typeHandles;
    }

    public static List<TypeHandle> typeHandle(Method method) {
        List<TypeHandle> typeHandles = new LinkedList<>();
        Annotation[] annotations = AnnotationUtil.getAnnotations(method, true);
        for (Annotation annotation : annotations) {
            for (Class key : handleHashMap.keySet()) {
                HttpHandle httpHandle = handleHashMap.get(key);
                if (httpHandle instanceof TypeHandle && annotation.annotationType().isAssignableFrom(key)) {
                    typeHandles.add((TypeHandle) httpHandle);
                }
            }
        }
        typeHandles.sort(Comparator.comparingInt(sort::order));
        return typeHandles;
    }

    public static List<ResultHandle> resultHandle(Method method) {
        List<ResultHandle> typeHandles = new LinkedList<>();
        for (Class key : handleHashMap.keySet()) {
            HttpHandle httpHandle = handleHashMap.get(key);
            if (httpHandle instanceof ResultHandle) {
                Annotation[] annotations = AnnotationUtil.getAnnotations(method, true);
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().isAssignableFrom(key)) {
                        typeHandles.add((ResultHandle) httpHandle);
                    }
                }
            }
        }
        typeHandles.sort(Comparator.comparingInt(sort::order));
        return typeHandles;
    }

    public List<ClassHandle> getAllClassHandle(Class aClass) {
        return classHandle.computeIfAbsent(aClass, HttpHandleCompose::classHandle);
    }

    public <T> List<TypeHandle> getAllTypeHandle(Method method) {
        return typeHandle.computeIfAbsent(method, HttpHandleCompose::typeHandle);
    }

    public <T> List<ParameterHandle> getAllParameterHandle(Method method) {
        return parameterHandle.computeIfAbsent(method, (key) -> parameterHandle(method));
    }

    public <T> List<ResultHandle> getAllResultHandle(Method method) {
        return resultHandle.computeIfAbsent(method, HttpHandleCompose::resultHandle);
    }

    public List<ParameterHandle> parameterHandle(Method method) {
        List<ParameterHandle> typeHandles = new LinkedList<>();
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            Annotation[] annotations = AnnotationUtil.getAnnotations(parameter, true);
            if (ObjectUtil.isEmpty(annotations) && !HttpProxy.linkedList.contains(parameter.getType())) {
                typeHandles.add(formHandle);
            }
            for (Annotation annotation : annotations) {
                for (Class key : handleHashMap.keySet()) {
                    HttpHandle httpHandle = handleHashMap.get(key);
                    if (httpHandle instanceof ParameterHandle && annotation.annotationType().isAssignableFrom(key)) {
                        typeHandles.add((ParameterHandle) httpHandle);
                    }
                }
            }
        }
        typeHandles.sort(Comparator.comparingInt(sort::order));
        return typeHandles;
    }

    /**
     * 默认的返回处理对象
     *
     * @param httpRequest httpRequest
     * @param method      方法
     * @param annotation  注解
     * @return 方法的返回值
     */
    public Object DefaultResultResolving(HttpRequest httpRequest, HttpProperties method, Annotation annotation) {
        return defaultResultHandle.resolving(httpRequest, method, annotation);
    }
}
