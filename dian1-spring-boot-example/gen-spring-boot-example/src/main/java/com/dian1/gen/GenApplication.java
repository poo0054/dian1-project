package com.dian1.gen;

import com.dian1.gen.config.EnableGen;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangzhi
 * @date 2023/3/16
 */

@SpringBootApplication
@EnableGen
public class GenApplication {
    public static void main(String[] args) {
        SpringApplication.run(GenApplication.class, args);
    }
}
