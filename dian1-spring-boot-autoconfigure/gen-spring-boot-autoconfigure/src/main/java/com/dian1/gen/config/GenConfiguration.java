package com.dian1.gen.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhangzhi
 * @date 2023/3/16
 */
@ComponentScan("com.dian1.gen")
@MapperScan("com.dian1.gen.mapper")
public class GenConfiguration {

}
