package com.dian1.http.annotate;

import com.dian1.http.configuration.HttpSelector;
import com.dian1.http.proxy.HttpFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动类
 *
 * @author zhangzhi
 * @date 2023/3/27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HttpSelector.class)
public @interface EnableHttp {

    /**
     * 扫描路径
     *
     * @return 扫描路径
     */
    String[] scan();

    /**
     * 构建的 {@link FactoryBean}
     *
     * @return 实现类
     */
    Class<? extends FactoryBean> httpFactoryBean() default HttpFactoryBean.class;
}
