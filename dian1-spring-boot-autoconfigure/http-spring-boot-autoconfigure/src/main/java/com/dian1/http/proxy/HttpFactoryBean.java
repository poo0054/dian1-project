package com.dian1.http.proxy;

import com.dian1.http.handle.HttpHandleCompose;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Proxy;

/**
 * 当前默认的FactoryBean.把 {@link com.dian1.http.annotate.OpenHttp}注解的类创建代理对象
 *
 * @author zhangzhi
 * @date 2023/3/27
 */
@Data
public class HttpFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {

    /**
     * 当前接口类------被OpenHttp注释的接口
     */
    private Class<T> httpInterfaces;

    private ApplicationContext applicationContext;

    @Override
    public T getObject() {
        HttpProxy<T> httpProxy = new HttpProxy<>(httpInterfaces);
        HttpHandleCompose httpHandleCompose = applicationContext.getBean(HttpHandleCompose.class);
        httpProxy.setHttpHandleCompose(httpHandleCompose);
        return (T) Proxy.newProxyInstance(httpInterfaces.getClassLoader(), new Class[]{httpInterfaces}, httpProxy);
    }


    @Override
    public Class<T> getObjectType() {
        return httpInterfaces;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
