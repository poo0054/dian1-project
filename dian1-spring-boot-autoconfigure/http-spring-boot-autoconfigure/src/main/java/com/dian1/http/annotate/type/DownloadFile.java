package com.dian1.http.annotate.type;

import cn.hutool.http.Method;

import java.lang.annotation.*;

/**
 * 1. 文件下载,如果需要感知下载进度.请实现 {@link cn.hutool.core.io.StreamProgress}放入参数
 * <p>
 * 2. 如果需要将返回内容写入到指定的 {@link java.io.OutputStream} 中.参数使用 {@link java.io.OutputStream}
 *
 * @author zhangzhi
 * @date 2023/3/27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@BaseHttpType(Method.DELETE)
public @interface DownloadFile {

}
