package com.dian1.http.configuration;

import com.dian1.http.utils.ClassUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangzhi
 * @date 2023/3/28
 */
@Configuration
public class HttpConfiguration extends HttpRegistrar {

    @Bean
    public ClassUtils classUtils(ApplicationContext applicationContext) {
        ClassUtils classUtils = new ClassUtils();
        classUtils.setApplicationContext(applicationContext);
        return classUtils;
    }
}
