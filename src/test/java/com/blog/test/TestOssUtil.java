package com.blog.test;

import com.aliyun.oss.model.PutObjectResult;
import com.blog.util.OssUtil;
import org.junit.Test;

import java.io.File;

/**
 * Created by Administrator on 2017/2/19.
 */
public class TestOssUtil {
    private OssUtil ossUtil = OssUtil.getInstance();

    @Test
    public void hello(){
        System.out.println(ossUtil.uploadFile("test.jpg", new File("target/classes/test.jpg")));
    }
}
