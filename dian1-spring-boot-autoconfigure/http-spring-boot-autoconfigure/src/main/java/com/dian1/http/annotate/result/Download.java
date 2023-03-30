package com.dian1.http.annotate.result;

import java.lang.annotation.*;

/**
 * 下载的参数
 * 为了区分上传和下载,加上无用注解
 *
 * @author zhangzhi
 * @date 2023/3/30
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Download {
}
