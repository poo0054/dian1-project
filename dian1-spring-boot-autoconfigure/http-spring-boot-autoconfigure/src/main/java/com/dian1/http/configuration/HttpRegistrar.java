package com.dian1.http.configuration;

import com.dian1.http.annotate.EnableHttp;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhangzhi
 * @date 2023/3/28
 */
public class HttpRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(EnableHttp.class.getName()));
        if (null != mapperScanAttrs) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(HttpBeanDefinitionRegistry.class);
            Class<?> httpFactoryBean = mapperScanAttrs.getClass("httpFactoryBean");
            builder.addPropertyValue("httpFactoryBean", httpFactoryBean);
            builder.addPropertyValue("scan", mapperScanAttrs.getStringArray("scan"));
            builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
            registry.registerBeanDefinition(HttpBeanDefinitionRegistry.class.getName(), builder.getBeanDefinition());
        }
    }
}
