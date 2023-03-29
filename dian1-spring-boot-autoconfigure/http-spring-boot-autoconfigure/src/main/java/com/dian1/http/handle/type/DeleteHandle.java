package com.dian1.http.handle.type;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Method;
import com.dian1.http.annotate.type.Delete;
import com.dian1.http.properties.HttpProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Component
public class DeleteHandle implements TypeHandle<Delete> {

    @Override
    public HttpProperties resolving(HttpProperties properties, Delete delete) {
        properties.setMethod(Method.DELETE);
        properties.setRelativePath(delete.value());
        if (-1 != delete.timeout()) {
            properties.setTimeout(delete.timeout());
        }
        if (StrUtil.isNotBlank(delete.charSet())) {
            properties.setCharset(CharsetUtil.charset(delete.charSet()));
        }
        return properties;
    }
}
