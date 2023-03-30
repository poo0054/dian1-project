package com.dian1.http.handle.parameter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.dian1.http.annotate.parameter.Body;
import com.dian1.http.properties.HttpProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhi
 * @date 2023/3/28
 */
@Component
public class BodyHandle implements ParameterHandle<Body> {


    @Override
    public HttpProperties resolving(HttpProperties properties, Object arg, Body body) {
        Map<String, Object> objectMap = properties.getBody();
        if (null == objectMap) {
            objectMap = new HashMap<>();
        }
        if (StrUtil.isNotBlank(body.value())) {
            objectMap.put(body.value(), JSON.toJSONString(arg));
        } else {
            if (Map.class.isAssignableFrom(arg.getClass())) {
                objectMap.putAll((Map) arg);
            } else {
                objectMap.putAll(BeanUtil.beanToMap(arg));
            }
        }
        properties.setBody(objectMap);
        return properties;
    }

}
