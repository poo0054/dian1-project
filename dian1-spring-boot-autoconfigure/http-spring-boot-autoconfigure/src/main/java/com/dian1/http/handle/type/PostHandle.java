package com.dian1.http.handle.type;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Method;
import com.dian1.http.annotate.type.Post;
import com.dian1.http.properties.HttpProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Component
public class PostHandle implements TypeHandle<Post> {

    @Override
    public HttpProperties resolving(HttpProperties properties, Post t) {
        properties.setMethod(Method.POST);
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
