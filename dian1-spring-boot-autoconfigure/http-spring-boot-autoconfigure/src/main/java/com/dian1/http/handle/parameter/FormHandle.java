package com.dian1.http.handle.parameter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.dian1.http.annotate.parameter.Form;
import com.dian1.http.properties.HttpProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhi
 * @date 2023/3/28
 */
@Component
public class FormHandle implements ParameterHandle<Form> {


    @Override
    public HttpProperties resolving(HttpProperties properties, Object arg, Form restful) {
        Map<String, Object> form = properties.getForm();
        if (null == form) {
            form = new HashMap<>();
        }
        if (StrUtil.isNotBlank(restful.value())) {
            form.put(restful.value(), JSON.toJSONString(arg));
        } else {
            if (Map.class.isAssignableFrom(arg.getClass())) {
                form.putAll((Map) arg);
            }
        }
        properties.setForm(form);
        return properties;
    }

    /**
     * 适配不填写参数的方式添加form
     * 必须添加启动参数 -parameters
     * <p>
     * maven项目可以添加
     * --------------------------
     * <plugin>
     * <groupId>org.apache.maven.plugins</groupId>
     * <artifactId>maven-compiler-plugin</artifactId>
     * <configuration>
     * <source>${java.version}</source>
     * <target>${java.version}</target>
     * <encoding>${project.build.sourceEncoding}</encoding>
     * <compilerArgs>
     * <arg>-parameters</arg>
     * </compilerArgs>
     * </configuration>
     * </plugin>
     */
    public HttpProperties autoResolving(HttpProperties properties, Object arg, String name) {
        Map<String, Object> form = properties.getForm();
        if (null == form) {
            form = new HashMap<>();
        }
        if (Map.class.isAssignableFrom(arg.getClass())) {
            form.putAll((Map) arg);
        } else {
            form.put(name, arg);
        }
        properties.setForm(form);
        return properties;
    }

}
