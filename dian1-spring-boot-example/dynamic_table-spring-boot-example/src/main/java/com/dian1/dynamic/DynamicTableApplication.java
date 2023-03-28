package com.dian1.dynamic;

import com.dian1.dynamic.config.EnableDynamic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/10/8 15:10
 */
@SpringBootApplication
@EnableDynamic
public class DynamicTableApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicTableApplication.class, args);
    }
}
