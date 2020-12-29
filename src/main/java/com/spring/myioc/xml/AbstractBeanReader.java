package com.spring.myioc.xml;

import com.spring.myioc.entity.BeanDefinition;
import com.spring.myioc.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/11/16 20:10
 * @Description:
 */
public abstract class AbstractBeanReader implements BeanReader {
    /**
     * Bean容器
     */
    private Map<String , BeanDefinition> register;
    /**
     *资源加载器
     */
    private ResourceLoader resourceLoader;

    AbstractBeanReader(ResourceLoader resourceLoader) {
        this.register = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegister() {
        return register;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
