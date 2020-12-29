package com.test;

import com.spring.myioc.entity.BeanDefinition;
import com.spring.myioc.factory.AutowiredBeanFactory;
import com.spring.myioc.io.ResourceLoader;
import com.spring.myioc.xml.XmlBeanReader;
import org.junit.Test;

import java.util.Map;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/11/18 15:18
 * @Description:
 */
public class BeanReaderTest {
    @Test
    public void test() throws Exception {
        //注册Bean
        AutowiredBeanFactory beanFactory = registBean();

        //获取Bean
        HelloWorld hello = (HelloWorld)beanFactory.getBean("hello");
        System.out.println(hello.getText());
    }

    private AutowiredBeanFactory registBean() throws Exception {
        XmlBeanReader xmlBeanReader = new XmlBeanReader(new ResourceLoader());
        //读取配置文件
        xmlBeanReader.readXml("myspring.xml");
        //创建Bean工厂
        AutowiredBeanFactory beanFactory = new AutowiredBeanFactory();
        //获取XML读取的Bean实例
        Map<String, BeanDefinition> register = xmlBeanReader.getRegister();
        for (Map.Entry<String, BeanDefinition> entry : register.entrySet()) {
            //将Bean注册到Bean工厂
            beanFactory.registerBean(entry.getKey(),entry.getValue());
        }
        return beanFactory;
    }
}
