package com.dian1.http.annotate.type;

import java.lang.annotation.*;

/**
 * get请求
 *
 * @author zhangzhi
 * @date 2023/3/27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Get {
    /**
     * 路径
     *
     * @return 路径
     */
    String value();
}
