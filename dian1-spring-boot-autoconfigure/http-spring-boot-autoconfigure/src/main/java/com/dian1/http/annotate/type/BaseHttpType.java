package com.dian1.http.annotate.type;

import cn.hutool.http.Method;

import java.lang.annotation.*;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseHttpType {
    Method value();
}
