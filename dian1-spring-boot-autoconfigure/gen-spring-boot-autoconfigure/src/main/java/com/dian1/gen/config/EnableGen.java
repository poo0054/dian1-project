package com.dian1.gen.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhangzhi
 * @date 2023/3/16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GenConfigurationSelector.class)
public @interface EnableGen {
}
