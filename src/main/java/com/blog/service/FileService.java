package com.blog.service;

import com.blog.util.MD5Util;
import org.springframework.stereotype.Component;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/2/17.
 */
@Component
public class FileService {
    /**
     *
     * @param user_id 用户ID
     * @param bytes 上传文件内容
     * @param type 文件类型
     * @param filename 文件名
     * @return
     */
    public String upload(int user_id, byte[] bytes, String type, String filename){
        StringBuilder filepath = new StringBuilder("target/file/");
        filepath = filepath.append(String.valueOf(user_id)).append("/").append(type);
        filepath = filepath.append(MD5Util.String2MD5(filename)).append(".").append(type);
        String url = null;
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filepath.toString());
            dos = new DataOutputStream(fos);
            dos.write(bytes);
            url = filepath.substring(10);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null){
                    dos.close();
                    dos = null;
                }
                if (fos != null){
                    fos.close();
                    fos = null;
                }
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return url;
    }
}