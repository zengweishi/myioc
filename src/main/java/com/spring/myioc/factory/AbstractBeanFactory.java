package com.spring.myioc.factory;

import com.spring.myioc.entity.BeanDefinition;

import java.util.HashMap;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/11/15 15:35
 * @Description:Bean工厂抽象实现类
 */
public abstract class AbstractBeanFactory implements BeanFactory {
    /**
     * 存放Bean的容器
     */
   private  HashMap<String,BeanDefinition> map = new HashMap<>();

    /**
     * 获取Bean
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = map.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("Not bean nameed " + name+ " is defined");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreate(beanDefinition);
        }
        return bean;
    }

    /**
     * 注册Bean
     * @param name
     * @param beanDefinition
     */
    @Override
    public void registerBean(String name, BeanDefinition beanDefinition) throws Exception {
        Object bean = doCreate(beanDefinition);
        beanDefinition.setBean(bean);
        map.put(name,beanDefinition);
    }

    /**
     * 创建Bean实例
     * @param beanDefinition
     * @return
     */
    protected abstract Object doCreate(BeanDefinition beanDefinition) throws Exception;

}
