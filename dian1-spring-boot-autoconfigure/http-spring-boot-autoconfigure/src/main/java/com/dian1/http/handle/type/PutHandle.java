package com.dian1.http.handle.type;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Method;
import com.dian1.http.annotate.type.Put;
import com.dian1.http.handle.TypeHandle;
import com.dian1.http.properties.HttpProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Component
public class PutHandle implements TypeHandle<Put> {
    @Override
    public HttpProperties resolving(HttpProperties properties, Put t) {
        properties.setMethod(Method.PUT);
        properties.setRelativePath(t.value());
        if (-1 != t.timeout()) {
            properties.setTimeout(t.timeout());
        }
        if (StrUtil.isNotBlank(t.charSet())) {
            properties.setCharset(CharsetUtil.charset(t.charSet()));
        }
        return properties;
    }

}
