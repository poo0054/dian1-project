package com.dian1.http.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Slf4j
public class HttpSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HttpConfiguration.class.getName()};
    }


}
