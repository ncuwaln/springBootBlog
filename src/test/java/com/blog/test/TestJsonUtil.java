package com.blog.test;

import com.blog.model.User;
import com.blog.util.JsonUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/4.
 */
public class TestJsonUtil {
    @Test
    public void hello() throws IOException {
        JsonUtil jsonUtil = new JsonUtil();
        User user = new User("sad", "sdada", "2963103258@qq.com");
        Map m = new HashMap();
        m.put("username", user.getUsername());
        String json = JsonUtil.object2json(user);
        String json1 = JsonUtil.object2json(m);
        System.out.println(json);
        System.out.println(json1);
        User user1 = (User)JsonUtil.json2object(json, User.class);
        System.out.println(user1.getHashPassword());
    }
}
