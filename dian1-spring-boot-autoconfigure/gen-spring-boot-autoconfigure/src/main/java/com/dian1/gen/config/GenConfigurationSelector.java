package com.dian1.gen.config;

import com.dian1.gen.utils.JdbcTypeUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhangzhi
 * @date 2023/3/16
 */
public class GenConfigurationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{GenConfiguration.class.getName(), JdbcTypeUtils.class.getName()};
    }
}
