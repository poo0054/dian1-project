package com.dian1.http.handle.result;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.dian1.http.handle.HttpHandle;
import com.dian1.http.properties.HttpProperties;
import com.dian1.http.proxy.HttpProxy;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Slf4j
public abstract class ResultHandle<T extends Annotation> implements HttpHandle {

    /**
     * 值生效一个,第一个生效
     */
    public Object resolving(HttpRequest httpRequest, HttpProperties properties, T annotation) {
        //流 文件 HttpResponse处理
        Class<?> returnType = properties.getMostSpecificMethod().getReturnType();
        if (ObjectUtil.isNotEmpty(properties.getFile())) {
            HttpResponse response = httpRequest.setFollowRedirects(true).executeAsync();
            long l = response.writeBody(properties.getFile(), properties.getStreamProgress());
            return returnDownload(returnType, response, l);
        } else if (ObjectUtil.isNotEmpty(properties.getOutputStream())) {
            HttpResponse response = httpRequest.setFollowRedirects(true).executeAsync();
            //TODO  可以把是否关闭流抽象出来
            long l = response.writeBody(properties.getOutputStream(), true, properties.getStreamProgress());
            return returnDownload(returnType, response, l);
        } else if (ObjectUtil.isNotEmpty(properties.getConsumer())) {
            httpRequest.then(properties.getConsumer());
            return null;
        } else if (HttpProxy.linkedList.get(4).isAssignableFrom(returnType)) {
            HttpResponse execute = httpRequest.execute(properties.isAsync());
            return execute;
        }
        Object beforeResolving = beforeResolving(httpRequest, properties, annotation);
        if (null != beforeResolving) {
            return beforeResolving;
        }
        //默认
        HttpResponse response = httpRequest.execute(properties.isAsync());
        log.info("response :{}", response.toString());
        String body = response.body();
        if (response.isOk()) {
            return returnType(body, returnType);
        }
        Object afterResolving = afterresolving(httpRequest, properties, annotation);
        if (null != afterResolving) {
            return afterResolving;
        }
        throw new HttpException("请求:" + httpRequest + "\n返回:" + response);
    }

    public Object afterresolving(HttpRequest httpRequest, HttpProperties properties, T annotation) {
        return null;
    }

    public Object beforeResolving(HttpRequest httpRequest, HttpProperties properties, T annotation) {
        return null;
    }

    public <T> Object returnType(String body, Class<T> returnType) {
        if (returnType.isAssignableFrom(void.class)) {
            return null;
        }
        if (returnType.isAssignableFrom(List.class)) {
            return JSON.parseArray(body, returnType);
        } else {
            return JSON.parseObject(body, returnType);
        }
    }


    public Object returnDownload(Class<?> returnType, HttpResponse response, long l) {
        if (returnType.isAssignableFrom(HttpResponse.class)) {
            return response;
        } else if (returnType.isAssignableFrom(long.class) || returnType.isAssignableFrom(Long.class)) {
            return l;
        }
        return null;
    }

}