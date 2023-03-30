package com.dian1.http.handle.parameter;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpUtil;
import com.dian1.http.annotate.parameter.BasicAuth;
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
public class BasicAuthHandle implements ParameterHandle<BasicAuth>, TypeHandle<BasicAuth> {

    @Override
    public HttpProperties resolving(HttpProperties properties, Object arg, BasicAuth basicAuth) {
        if (Map.class.isAssignableFrom(arg.getClass())) {
            properties.getHeaders().computeIfAbsent(Header.AUTHORIZATION.getValue(), s -> {
                Map arg1 = (Map) arg;
                Object username = arg1.get(basicAuth.username());
                Object password = arg1.get(basicAuth.password());
                return HttpUtil.buildBasicAuth(username.toString(), password.toString(), CharsetUtil.charset(basicAuth.charset()));
            });
        }
        return properties;
    }

    @Override
    public HttpProperties resolving(HttpProperties properties, BasicAuth basicAuth) {
        properties.getHeaders().computeIfAbsent(Header.AUTHORIZATION.getValue(), s -> HttpUtil.buildBasicAuth(basicAuth.username(), basicAuth.password(), CharsetUtil.charset(basicAuth.charset())));
        return properties;
    }

    @Override
    public int order() {
        return 1;
    }
}
