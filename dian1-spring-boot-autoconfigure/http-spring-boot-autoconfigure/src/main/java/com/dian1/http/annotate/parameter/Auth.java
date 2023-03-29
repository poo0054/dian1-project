package com.dian1.http.annotate.parameter;

import java.lang.annotation.*;

/**
 * 当前值会放入head Authorization 的 value
 * ------------------------------
 * 在方法上必填
 * <p>
 * 在参数上参数必须为map.取其中value的值放入 Authorization
 *
 * @author zhangzhi
 * @date 2023/3/29
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

    String value() default "";

}
