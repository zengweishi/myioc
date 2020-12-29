package com.spring.myioc.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/11/15 15:27
 * @Description:属性集合
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public PropertyValues() {

    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    /**
     * 添加进属性集合
     */
    public void addPropertyValue(PropertyValue pv) {
        propertyValueList.add(pv);
    }
}
