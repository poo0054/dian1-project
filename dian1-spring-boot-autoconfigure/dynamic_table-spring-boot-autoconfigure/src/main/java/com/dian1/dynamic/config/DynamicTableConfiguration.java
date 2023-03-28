package com.dian1.dynamic.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhangzhi
 * @date 2023/3/15
 */
@ComponentScan("com.dian1.dynamic")
@MapperScan("com.dian1.dynamic.dao")
public class DynamicTableConfiguration {

    @Bean
    public DbCreateListener dbCreateListener() {
        return new DbCreateListener();
    }
}
