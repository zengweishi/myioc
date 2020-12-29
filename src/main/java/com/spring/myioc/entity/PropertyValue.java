package com.spring.myioc.entity;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/11/15 15:27
 * @Description:
 */
public class PropertyValue {
    /**
     * 属性名称
     */
    private final String name;
    /**
     * 属性对象
     */
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
