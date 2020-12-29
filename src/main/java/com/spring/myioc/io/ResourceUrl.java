package com.spring.myioc.io;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Auther: weishi.zeng
 * @Date: 2020/11/16 20:01
 * @Description:资源URL
 */
public class ResourceUrl implements Resource {
    private final URL url ;

    public ResourceUrl(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        URLConnection connection = url.openConnection();
        connection.connect();
        return connection.getInputStream();
    }
}
