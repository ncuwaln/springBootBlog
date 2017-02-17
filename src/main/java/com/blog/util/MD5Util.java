package com.blog.util;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/2/13.
 */
@Component
public class MD5Util {
    private static MessageDigest md5 = null;
    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String String2MD5(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr=base64en.encode(md5.digest(s.getBytes("utf-8")));
        return newstr;
    }

    private static String bytesToHex(byte[] bytes){
        StringBuilder md5buffer = new StringBuilder();
        for (byte x:bytes){
            if (x < 0){
                x += 255;
            }
            if (x < 16){
                md5buffer.append('0');
            }
            md5buffer.append(Integer.toHexString(x));
        }
        return new String(md5buffer);
    }
}
