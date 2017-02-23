package com.blog.util;

import com.aliyun.oss.OSSClient;

import java.io.ByteArrayInputStream;
import java.io.File;

/**
 * Created by Administrator on 2017/2/19.
 */
public class OssUtil {
    private final static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private final static String accessKeyId = "";
    private final static String accessKeySecret = "";
    private final static String bucket = "spring-boot-blog";
    private final OSSClient client;
    private static OssUtil ossUtil = new OssUtil();

    private OssUtil(){
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public static OssUtil getInstance(){
        return ossUtil;
    }

    public String uploadFile(String key, byte[] bytes){
        client.putObject(bucket, key, new ByteArrayInputStream(bytes));
        return "http://spring-boot-blog.oss-cn-shanghai.aliyuncs.com/"+key;
    }

    public String uploadFile(String key, File file){
        client.putObject(bucket, key, file);
        return "http://spring-boot-blog.oss-cn-shanghai.aliyuncs.com/"+key;
    }
}
