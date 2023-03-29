package com.dian1.http.annotate.parameter;

import java.lang.annotation.*;

/**
 * username    password
 * 构建简单的账号秘密验证信息，使用Base64.构建后类似于：
 * Basic YWxhZGRpbjpvcGVuc2VzYW1l
 * ------------------------------
 * 在方法上username和password必填
 * 在参数上参数为map,key为username和password的值 默认为username和password
 *
 * @author zhangzhi
 * @date 2023/3/27
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BasicAuth {

    String username() default "username";

    String password() default "password";

    String charset() default "UTF-8";
}
