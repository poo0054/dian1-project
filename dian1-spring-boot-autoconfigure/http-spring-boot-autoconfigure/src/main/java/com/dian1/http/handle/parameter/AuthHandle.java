package com.dian1.http.handle.parameter;

import cn.hutool.http.Header;
import com.dian1.http.annotate.parameter.Auth;
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
public class AuthHandle implements ParameterHandle<Auth>, TypeHandle<Auth> {

    @Override
    public HttpProperties resolving(HttpProperties properties, Object arg, Auth basicAuth) {
        if (Map.class.isAssignableFrom(arg.getClass())) {
            properties.getHeaders().computeIfAbsent(Header.AUTHORIZATION.getValue(), s -> {
                Map arg1 = (Map) arg;
                Object username = arg1.get(basicAuth.value());
                return username.toString();
            });
        }
        return properties;
    }

    @Override
    public HttpProperties resolving(HttpProperties properties, Auth auth) {
        properties.getHeaders().computeIfAbsent(Header.AUTHORIZATION.getValue(), s -> auth.value());
        return properties;
    }

    @Override
    public int order() {
        return 1;
    }
}
