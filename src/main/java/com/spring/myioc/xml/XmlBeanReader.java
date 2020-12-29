package com.spring.myioc.xml;

import com.spring.myioc.entity.BeanDefinition;
import com.spring.myioc.entity.BeanReference;
import com.spring.myioc.entity.PropertyValue;
import com.spring.myioc.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.Map;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/11/16 20:10
 * @Description:
 */
public class XmlBeanReader extends AbstractBeanReader {
    public XmlBeanReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    public void readXml(String path) throws Exception {
        //创建一个资源加载器
        ResourceLoader resourceLoader = new ResourceLoader();
        //从资源加载器中获取输入流
        InputStream inputStream = resourceLoader.getResourceUrl(path).getInputStream();
        //获取文档建造者工厂实例
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //工厂创建文档创建者
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //返回文档对象
        Document document = documentBuilder.parse(inputStream);
        //解析文档对象，并注册到Bean容器中
        registerBean(document);
        inputStream.close();
    }

    /**
     * 根据文档对象进行解析
     * @param document
     */
    private void registerBean(Document document) {
        //获取根节点
        Element element = document.getDocumentElement();
        //解析文档的根节点以及以下元素并注册到Bean容器
        praseBean(element);
    }

    /**
     * 解析元素的根节点及根节点下的所有子节点并添加进注册容器
     * @param element
     */
    private void praseBean(Element element) {
        //读取根元素的所有子元素
        NodeList childNodes = element.getChildNodes();
        for (int i = 0;i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item instanceof Element) {
                Element node = (Element) item;
                //解析给定的节点
                /** <bean name="hello" class="com.HelloWorld">
                *    <property name="text" value="Hello World!"></property>
                *  </bean>
                */
                 parseNode(node);
            }
        }
    }

    /**
     * 解析给定的节点
     * @param node
     */
    private void parseNode(Element node) {
        //获取元素的name属性
        String name = node.getAttribute("name");
        //获取元素的class属性
        String className = node.getAttribute("class");
        //创建一个BeanDefinition对象
        BeanDefinition beanDefinition = new BeanDefinition();
        //设置bean对象的全限定类名
        beanDefinition.setClassName(className);
        //向Bean中注入成员变量  <property name="text" value="Hello World!"></property>
        addProperty(beanDefinition,node);
        //将Bean注册到容器之中
        getRegister().put(name, beanDefinition);
    }

    /**
     * 向Bean中注入成员变量
     * @param beanDefinition
     * @param node
     */
    private void addProperty(BeanDefinition beanDefinition, Element node) {
        //获取给定元素的property集合
        NodeList property = node.getElementsByTagName("property");
        for (int i = 0; i < property.getLength(); i++) {
            Node item = property.item(i);
            if (item instanceof Element) {
                Element ele = (Element) item;
                //获取proeprty的name
                String propertyName = ele.getAttribute("name");
                //获取property的value
                String value = ele.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(propertyName,value));
                } else {
                    //如果为空，则获取ref属性  <property name="hello" ref="hello"></property>
                    String ref = ele.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        // 如果属性ref为空，则抛出异常
                        throw new IllegalArgumentException(
                                "Configuration problem: <property> element for property '"
                                        + propertyName + "' must specify a ref or value");
                    }
                    // 如果不为空，测创建一个 “bean的引用” 实例，构造参数为名称，实例暂时为空
                    BeanReference beanReference = new BeanReference(ref);
                    // 向给定的 “bean定义” 中添加成员变量
                    // 后面获取bean递归获取
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(propertyName,beanReference));
                }
            }
        }
    }
}
