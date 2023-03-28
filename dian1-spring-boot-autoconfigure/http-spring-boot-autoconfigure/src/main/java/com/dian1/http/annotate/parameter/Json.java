package com.dian1.http.annotate.parameter;

import java.lang.annotation.*;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Form
public @interface Json {
}
