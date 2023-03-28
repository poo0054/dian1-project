package com.dian1.http.annotate.type;

import cn.hutool.http.Method;

import java.lang.annotation.*;

/**
 * Delete请求
 *
 * @author zhangzhi
 * @date 2023/3/27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@BaseHttpType(Method.DELETE)
public @interface Delete {
}
