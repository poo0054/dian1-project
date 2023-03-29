package com.dian1.http.annotate.parameter;

import java.lang.annotation.*;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Body {
    /**
     * 当前form的key.如果参数为map,则不需要
     *
     * @return form的key
     */
    String value() default "";
}
