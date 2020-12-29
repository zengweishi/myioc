package com.spring.myioc.factory;

import com.spring.myioc.entity.BeanDefinition;
import com.spring.myioc.entity.BeanReference;
import com.spring.myioc.entity.PropertyValue;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/11/15 15:42
 * @Description:Bean工厂实现类
 */
public class AutowiredBeanFactory extends AbstractBeanFactory{
    /**
     * 创建Bean实例
     * @param beanDefinition
     * @return
     */
    @Override
    protected Object doCreate(BeanDefinition beanDefinition) throws Exception {
        Object bean = Class.forName(beanDefinition.getClassName()).newInstance();
        addPropertyValue(bean,beanDefinition);
        return bean;
    }

    /**
     * 采用反射，为Bean中的属性注入实例
     * @param bean
     * @param beanDefinition
     */
    private void addPropertyValue(Object bean, BeanDefinition beanDefinition) throws Exception {
        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues().getPropertyValueList();
        //遍历Bean的属性
        for (PropertyValue propertyValue : propertyValueList) {
            //获取字段
            Field field = bean.getClass().getDeclaredField(propertyValue.getName());
            //设置属性的访问权限
            field.setAccessible(true);
            //获取属性对象值
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                //将Object value转为BeanReference
                BeanReference beanReference = (BeanReference)value;
                //用父类的 AbstractBeanFactory 的 getBean 方法，根据bean引用的名称获取实例，此处即是递归
                value = getBean(beanReference.getName());
            }
            //反射注入bean的属性
            field.set(bean,value);
        }
    }
}
