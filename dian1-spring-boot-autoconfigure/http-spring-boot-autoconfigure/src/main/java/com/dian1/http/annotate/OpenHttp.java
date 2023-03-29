package com.dian1.http.annotate;

import java.lang.annotation.*;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpenHttp {

    /**
     * 当前类下所有前缀  例如 http://www.baodu.com
     * 可以为空
     *
     * @return 请求前缀
     */
    String value() default "";


}
