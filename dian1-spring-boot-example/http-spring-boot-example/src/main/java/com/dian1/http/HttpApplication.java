package com.dian1.http;

import com.dian1.http.annotate.EnableHttp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangzhi
 * @date 2023/3/28
 */
@EnableHttp(scan = "com.dian1.http.http")
@SpringBootApplication
public class HttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpApplication.class, args);
    }

}
