package com.dian1.http.configuration;

import com.dian1.http.proxy.HttpFactoryBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Slf4j
@Data
public class HttpBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor {

    private Class<? extends FactoryBean> httpFactoryBean = HttpFactoryBean.class;
    private String[] scan;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        HttpBeanDefinitionScanner scanner = new HttpBeanDefinitionScanner(registry);
        scanner.setHttpFactoryBean(httpFactoryBean);
        scanner.initIncludeFilter();
        scanner.scan(scan);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //ignore
    }
}
