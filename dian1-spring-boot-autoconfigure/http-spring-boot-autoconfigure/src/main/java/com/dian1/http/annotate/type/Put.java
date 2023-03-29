package com.dian1.http.annotate.type;

import java.lang.annotation.*;

/**
 * put请求
 *
 * @author zhangzhi
 * @date 2023/3/27
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Put {
    /**
     * 路径
     *
     * @return 路径
     */
    String value();

    /**
     * 超时时间
     *
     * @return 毫秒
     */
    int timeout() default -1;

    /**
     * 编码
     *
     * @return 编码格式
     */
    String charSet() default "UTF-8";
}
