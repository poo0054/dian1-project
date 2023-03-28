package com.dian1.http.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangzhi
 * @date 2023/3/28
 */
@Configuration
@ComponentScan("com.dian1.http.handle")
public class HttpConfiguration extends HttpRegistrar {


}
