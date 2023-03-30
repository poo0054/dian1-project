package com.dian1.http.annotate.parameter;

import java.lang.annotation.*;

/**
 * 处理{@link com.dian1.http.properties.HttpProperties#getRelativePath()}的一种方法.
 * <p>
 * 例如 /getProductCode/{code}  {@link  #value()}填写code就可以自动替换
 *
 * @author zhangzhi
 * @date 2023/3/28
 * @see com.dian1.http.handle.parameter.RestfulHandle
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
