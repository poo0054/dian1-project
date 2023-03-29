package com.dian1.http.proxy;

import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.*;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.dian1.http.annotate.parameter.Form;
import com.dian1.http.handle.HttpHandle;
import com.dian1.http.handle.HttpHandleCompose;
import com.dian1.http.handle.base.ClassHandle;
import com.dian1.http.handle.parameter.FormHandle;
import com.dian1.http.handle.parameter.ParameterHandle;
import com.dian1.http.handle.result.ResultHandle;
import com.dian1.http.handle.type.TypeHandle;
import com.dian1.http.properties.HttpProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.File;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Data
@Slf4j
public class HttpProxy<T> implements InvocationHandler {
    public static List<Class> linkedList = new LinkedList<>();

    static {
        linkedList.add(Consumer.class);
        linkedList.add(File.class);
        linkedList.add(OutputStream.class);
        linkedList.add(StreamProgress.class);
        linkedList.add(HttpResponse.class);
    }

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
        HttpProperties properties = new HttpProperties();
        properties.setMostSpecificMethod(method);
        properties.setArgs(args);
        //首先处理一些特殊的参数,  Consumer<HttpResponse> file  OutputStream
        handleSpecialArgs(method, args, properties);
        buildClassHandle(httpInterfaces, properties);
        buildTypeHandles(mostSpecificMethod, properties);
        //TODO 需要循环每个参数,分步解析进行解耦
        buildParameterHandle(mostSpecificMethod, args, properties);
        HttpRequest httpRequest = buildHttpRequest(properties);
        return buildResultHandle(httpRequest, mostSpecificMethod, properties);
    }

    private void handleSpecialArgs(Method method, Object[] args, HttpProperties properties) {
        //首先处理一些特殊的参数, Consumer < HttpResponse > file OutputStream
        if (null == args) {
            return;
        }
        for (int i = 0; i < method.getParameterTypes().length; i++) {
            Class<?> parameterType = method.getParameterTypes()[i];
            if (linkedList.get(0).isAssignableFrom(parameterType)) {
                properties.setConsumer((Consumer) args[i]);
                properties.setFollowRedirects(true);
            } else if (linkedList.get(1).isAssignableFrom(parameterType)) {
                properties.setFile((File) args[i]);
                properties.setFollowRedirects(true);
            } else if (linkedList.get(2).isAssignableFrom(parameterType)) {
                properties.setOutputStream((OutputStream) args[i]);
                properties.setFollowRedirects(true);
            } else if (linkedList.get(3).isAssignableFrom(parameterType)) {
                properties.setStreamProgress((StreamProgress) args[i]);
            }
        }

    }

    private HttpRequest buildHttpRequest(HttpProperties properties) {
        String baseUrl = properties.getBaseUrl();
        String url = null;
        if (StrUtil.isNotBlank(baseUrl)) {
            url = URLUtil.completeUrl(UrlBuilder.ofHttp(baseUrl).build(), properties.getRelativePath());
        }
        url = (null == url) ? properties.getRelativePath() : url;
        properties.setUrl(url);
        HttpRequest httpRequest = new HttpRequest(url);
        httpRequest.setMethod(properties.getMethod());
        httpRequest.setRest(properties.isRest());
        if (ObjectUtil.isNotEmpty(properties.getForm())) {
            httpRequest.form(properties.getForm());
        }
        if (ObjectUtil.isNotEmpty(properties.getBody())) {
            httpRequest.body(JSON.toJSONString(properties.getBody()));
        }
        httpRequest.timeout(properties.getTimeout());
        httpRequest.setUrlHandler(properties.getUrlHandler());
        httpRequest.cookie(properties.getCookie());
        if (properties.isDisableCache()) {
            httpRequest.disableCache();
        }
        if (properties.isDisableCookie()) {
            httpRequest.disableCookie();
        }
        httpRequest.setProxy(properties.getProxy());
        httpRequest.setHostnameVerifier(properties.getHostnameVerifier());
        httpRequest.setSSLSocketFactory(properties.getSsf());
        Charset charset = properties.getCharset();
        httpRequest.charset(null == charset ? CharsetUtil.CHARSET_UTF_8 : charset);
        Map<String, String> headers = properties.getHeaders();
        if (ObjectUtil.isNotEmpty(headers)) {
            headers.forEach(httpRequest::header);
        }
        httpRequest.contentType(properties.getContentType());
        httpRequest.setFollowRedirects(properties.isFollowRedirects());
        return httpRequest;
    }

    private <V extends Annotation> void buildClassHandle(Class httpInterfaces, HttpProperties httpProperties) {
        List<ClassHandle> allClassHandle = httpHandleCompose.getAllClassHandle(httpInterfaces);
        for (ClassHandle<V> typeHandle : allClassHandle) {
            Class<V> typeArgument = (Class<V>) ClassUtil.getTypeArgument(typeHandle.getClass());
            V annotation = AnnotationUtils.findAnnotation(httpInterfaces, typeArgument);
            typeHandle.resolving(httpProperties, annotation);
        }
    }

    private <V extends Annotation> void buildTypeHandles(Method method, HttpProperties httpProperties) {
        List<TypeHandle> allTypeHandle = httpHandleCompose.getAllTypeHandle(method);
        for (TypeHandle<V> typeHandle : allTypeHandle) {
            Class<V> typeArgument = (Class<V>) ClassUtil.getTypeArgument(typeHandle.getClass());
            V annotation = AnnotationUtils.findAnnotation(method, typeArgument);
            typeHandle.resolving(httpProperties, annotation);
        }
    }

    private <V extends Annotation> void buildParameterHandle(Method method, Object[] args, HttpProperties httpProperties) {
        List<ParameterHandle> allParameterHandle = httpHandleCompose.getAllParameterHandle(method);
        for (ParameterHandle<V> typeHandle : allParameterHandle) {
            Class<V> typeArgument = (Class<V>) ClassUtil.getTypeArgument(typeHandle.getClass());
            for (int i = 0; i < method.getParameters().length; i++) {
                Parameter parameter = method.getParameters()[i];
                Annotation[] annotations = parameter.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().isAssignableFrom(typeArgument)) {
                        typeHandle.resolving(httpProperties, args[i], (V) annotation);
                    }
                }
                //form默认进行处理
                if (ObjectUtil.isEmpty(annotations) &&
                        ObjectUtil.isEmpty(httpProperties.getBody()) &&
                        !linkedList.contains(parameter.getType())) {
                    String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
                    HttpHandle httpHandle = HttpHandleCompose.getHandleHashMap().get(Form.class);
                    if (FormHandle.class.isAssignableFrom(httpHandle.getClass())) {
                        //TODO 适配不填写参数的方式添加form
                        FormHandle httpHandle1 = (FormHandle) httpHandle;
                        httpHandle1.autoResolving(httpProperties, args[i], parameterNames[i]);
                    }

                }
            }

        }
    }

    private <V extends Annotation> Object buildResultHandle(HttpRequest httpRequest, Method method, HttpProperties httpProperties) {
        log.info("请求:" + httpRequest + "\n参数:" + httpProperties);
        List<ResultHandle> allParameterHandle = httpHandleCompose.getAllResultHandle(method);
        if (ObjectUtil.isEmpty(allParameterHandle)) {
            return httpHandleCompose.DefaultResultResolving(httpRequest, httpProperties, null);
        }
        for (ResultHandle<V> typeHandle : allParameterHandle) {
            Class<V> typeArgument = (Class<V>) ClassUtil.getTypeArgument(typeHandle.getClass());
            V annotation = AnnotationUtils.findAnnotation(method, typeArgument);
            return typeHandle.resolving(httpRequest, httpProperties, annotation);
        }
        return null;
    }


}
