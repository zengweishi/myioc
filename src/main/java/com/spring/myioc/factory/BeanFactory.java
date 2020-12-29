package com.spring.myioc.factory;

import com.spring.myioc.entity.BeanDefinition;

public interface BeanFactory {
    /**
     * 获取bean
     */
    Object getBean(String name) throws Exception;

    /**
     * 将Bean注册到bean工厂
     */
    void registerBean(String name, BeanDefinition bean) throws Exception;
}
