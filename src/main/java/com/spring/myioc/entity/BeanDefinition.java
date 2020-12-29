package com.spring.myioc.entity;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/11/15 15:23
 * @Description:Bean的定义
 */
public class BeanDefinition {

    /**
     * bean
     */
    private Object bean;

    /**
     * 全限定类名
     */
    private String className;

    /**
     * bean的Class对象
     */
    private Class beanClass;

    /**
     * 类的属性集合
     */
    private PropertyValues propertyValues = new PropertyValues();

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public void setClassName(String className) {
        this.className = className;
        try {
            Class<?> aClass = Class.forName(className);
            this.beanClass = aClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public Object getBean() {
        return bean;
    }

    public String getClassName() {
        return className;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }


}
