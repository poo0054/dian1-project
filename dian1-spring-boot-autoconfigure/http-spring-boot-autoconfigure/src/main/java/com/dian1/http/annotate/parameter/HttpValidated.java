package com.dian1.http.annotate.parameter;

import com.dian1.http.handle.ParameterHandle;

import javax.validation.groups.Default;
import java.lang.annotation.*;

/**
 * 在方法上所有参数都会校验,在参数上,校验当前参数
 *
 * @author zhangzhi
 * @date 2023/4/4
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpValidated {
    /**
     * 分组
     */
    Class<?>[] groups() default Default.class;

    /**
     * 指定处理类,必须为{@link ParameterHandle}的子类
     */
    String handleClassName() default "";
}
