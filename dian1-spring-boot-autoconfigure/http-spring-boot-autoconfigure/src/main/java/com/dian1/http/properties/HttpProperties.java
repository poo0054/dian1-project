package com.dian1.http.properties;

import lombok.Data;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Data
public class HttpProperties {

    /**
     * baseUrl – 基准URL
     */
    private String baseUrl;
    /**
     * relativePath – 相对URL
     */
    private String relativePath;


}
