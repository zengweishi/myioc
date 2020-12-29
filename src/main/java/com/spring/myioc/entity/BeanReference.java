package com.spring.myioc.entity;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/11/15 16:55
 * @Description:
 */
public class BeanReference {
    private String name;
    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Object getBean() {
        return bean;
    }
}
