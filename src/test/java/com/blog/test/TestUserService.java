package com.blog.test;

import com.blog.Application;
import com.blog.error.UserDefinedException;
import com.blog.model.User;
import com.blog.service.UserService;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/2/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void test() throws UserDefinedException, IOException, NoSuchAlgorithmException, MessagingException, TemplateException, InterruptedException {
//        long start = System.currentTimeMillis();
        userService.addUser(new User("stcode", "tjq980303", "2963103258@qq.com"));
        User u = userService.findUserByUsername("stcode");
        System.out.println(u.getHashPassword());
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }

}
