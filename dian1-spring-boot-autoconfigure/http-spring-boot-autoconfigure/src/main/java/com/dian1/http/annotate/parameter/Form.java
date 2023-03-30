package com.dian1.http.annotate.parameter;

import java.lang.annotation.*;

/**
 * 如果不添加任何注解.默认生效该注解
 * <p>
 * 默认使用参数的名称作为key.值为value
 *
 * @author zhangzhi
 * @date 2023/3/27
 * @see com.dian1.http.handle.parameter.FormHandle
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Form {

    /**
     * 当前form的key.如果参数为map,则不需要
     *
     * @return form的key
     */
    String value() default "";

}
