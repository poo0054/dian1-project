package com.dian1.http.annotate.parameter;

import java.lang.annotation.*;

/**
 * 当前值会放入header Authorization 的 value上
 * ------------------------------
 * value: 在方法上必填,参数上可以为空
 * <p>
 * 在参数上参数必须为map.取其中value的值放入 Authorization
 *
 * @author zhangzhi
 * @date 2023/3/29
 * @see com.dian1.http.handle.parameter.AuthHandle
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

    String value() default "";

}
