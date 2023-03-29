package com.dian1.http.annotate.parameter;

import java.lang.annotation.*;

/**
 * @author zhangzhi
 * @date 2023/3/28
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Restful {

    /**
     * 当前参数的值
     *
     * @return {}表示
     */
    String value();
}
