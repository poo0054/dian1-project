package com.dian1.http.configuration;

import com.dian1.http.annotate.OpenHttp;
import com.dian1.http.proxy.HttpFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

import java.util.Set;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Slf4j
public class HttpBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    private final BeanDefinitionRegistry registry;
    private Class<? extends FactoryBean> httpFactoryBean = HttpFactoryBean.class;
    private boolean useDefaultFilters = false;

    public HttpBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
        this.registry = registry;
    }

    public HttpBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
        this.registry = registry;
        this.useDefaultFilters = useDefaultFilters;
    }

    public void initIncludeFilter() {
        addIncludeFilter(new AnnotationTypeFilter(OpenHttp.class));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if (ObjectUtils.isEmpty(beanDefinitionHolders)) {
            log.warn("HttpBeanDefinitionScanner #doScan is null");
        } else {
            //注入 BeanDefinition
            registryBeanDefinition(beanDefinitionHolders);
        }
        return beanDefinitionHolders;
    }

    private void registryBeanDefinition(Set<BeanDefinitionHolder> beanDefinitionHolders) {
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            Class<?> aClass;
            try {
                aClass = ClassUtils.forName(beanDefinition.getBeanClassName(), this.getClass().getClassLoader());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            beanDefinition.getPropertyValues().add("httpInterfaces", aClass);
            BeanDefinitionHolder scopedProxy = ScopedProxyUtils.createScopedProxy(beanDefinitionHolder, registry, true);
            beanDefinition.setBeanClass(httpFactoryBean);
            if (registry.containsBeanDefinition(scopedProxy.getBeanName())) {
                registry.removeBeanDefinition(scopedProxy.getBeanName());
            }
            registry.registerBeanDefinition(scopedProxy.getBeanName(), scopedProxy.getBeanDefinition());
        }
    }

    public Class<? extends FactoryBean> getHttpFactoryBean() {
        return httpFactoryBean;
    }

    public void setHttpFactoryBean(Class<? extends FactoryBean> httpFactoryBean) {
        this.httpFactoryBean = httpFactoryBean;
    }

    public boolean isUseDefaultFilters() {
        return useDefaultFilters;
    }

    public void setUseDefaultFilters(boolean useDefaultFilters) {
        this.useDefaultFilters = useDefaultFilters;
    }
}
