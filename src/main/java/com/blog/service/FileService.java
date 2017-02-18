package com.blog.service;

import com.blog.util.MD5Util;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.NoSuchAlgorithmException;

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
    public String upload(int user_id, byte[] bytes, String type, String filename) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        StringBuilder filepath = new StringBuilder("target/classes/static/upload/");
        filepath = filepath.append(String.valueOf(user_id)).append("/").append(type).append("/");
        File f = new File(filepath.toString());
        if (!f.exists()){
            f.mkdir();
        }
        filename = filename+String.valueOf(System.currentTimeMillis());
        filename = MD5Util.String2MD5(filename);
        filename = filename.replaceAll("/", "_");
        filename = filename.replaceAll("\\\\", "_");
        filepath = filepath.append(filename).append(".").append(type);
        String url = null;
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filepath.toString());
            dos = new DataOutputStream(fos);
            dos.write(bytes);
            url = filepath.substring(22);
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