package com.dian1.http.proxy;

import cn.hutool.core.util.ClassUtil;
import com.dian1.http.handle.HttpHandleCompose;
import com.dian1.http.handle.TypeHandle;
import com.dian1.http.properties.HttpProperties;
import lombok.Data;
import org.springframework.aop.support.AopUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Data
public class HttpProxy<T> implements InvocationHandler {
    /**
     * mapper接口
     */
    private final Class<T> httpInterfaces;

    private HttpHandleCompose httpHandleCompose;

    public HttpProxy(Class<T> httpInterfaces) {
        this.httpInterfaces = httpInterfaces;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Method mostSpecificMethod = AopUtils.getMostSpecificMethod(method, httpInterfaces);
        HttpProperties httpProperties = new HttpProperties();
        Set<TypeHandle<? extends Annotation>> allTypeHandle = httpHandleCompose.getAllTypeHandle(httpInterfaces);
        allTypeHandle.forEach(typeHandle -> {
            Class<T> aClass = (Class<T>) typeHandle.getClass();
            Class<T> typeArgument = (Class<T>) ClassUtil.getTypeArgument(aClass);
            System.out.println();
        });
        httpHandleCompose.getAllParameterHandle(mostSpecificMethod);


        return null;
    }


}
