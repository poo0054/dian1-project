package com.dian1.http.handle.parameter;

import com.dian1.http.annotate.parameter.Header;
import com.dian1.http.handle.ParameterHandle;
import com.dian1.http.handle.TypeHandle;
import com.dian1.http.properties.HttpProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhangzhi
 * @date 2023/3/29
 */
@Component
public class HeadHandle implements ParameterHandle<Header>, TypeHandle<Header> {

    @Override
    public HttpProperties resolving(HttpProperties properties, Object arg, Header basicAuth) {
        if (Map.class.isAssignableFrom(arg.getClass())) {
            Map map = (Map) arg;
            for (Object key : map.keySet()) {
                properties.getHeaders().computeIfAbsent(key.toString(), s -> map.get(key).toString());
            }
        } else {
            String key = basicAuth.value()[0];
            properties.getHeaders().computeIfAbsent(key, s -> arg.toString());
        }
        return properties;
    }

    @Override
    public HttpProperties resolving(HttpProperties properties, Header header) {
        for (String headStr : header.value()) {
            String[] split = headStr.split(":");
            properties.getHeaders().computeIfAbsent(split[0], key -> split[1]);
        }
        return properties;
    }

    @Override
    public int order() {
        return 1;
    }
}
