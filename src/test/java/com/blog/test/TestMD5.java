package com.blog.test;

import com.blog.util.MD5Util;
import org.junit.Test;

/**
 * Created by Administrator on 2017/2/13.
 */
public class TestMD5 {

    @Test
    public void test(){
        String s = "tjq980303";
        System.out.println(MD5Util.String2MD5(s));
    }

}
