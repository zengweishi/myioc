package com.spring.myioc.io;

import java.io.InputStream;

/**
 * 资源定义
 */
public interface Resource {
    /**
     * 获取输入流
     * @return
     */
    InputStream getInputStream() throws Exception;

}
