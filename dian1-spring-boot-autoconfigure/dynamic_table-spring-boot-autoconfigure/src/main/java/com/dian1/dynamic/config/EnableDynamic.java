package com.dian1.dynamic.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhangzhi
 * @date 2023/3/15
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DynamicTableEnableConfiguration.class)
public @interface EnableDynamic {
}
