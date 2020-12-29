package com.spring.myioc.io;

import java.net.URL;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/11/16 20:01
 * @Description:资源加载器
 */
public class ResourceLoader {
    /**
     * 给定一个位置， 使用累加器的资源加载URL，并创建一个“资源URL”对象，便于获取输入流
     */
    public ResourceUrl getResourceUrl(String path) {
        URL resource = this.getClass().getClassLoader().getResource(path);
        return new ResourceUrl(resource);
    }
}
