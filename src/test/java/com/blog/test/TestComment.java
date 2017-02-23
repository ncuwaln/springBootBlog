package com.blog.test;

import com.blog.Application;
import com.blog.error.UserDefinedException;
import com.blog.model.Comment;
import com.blog.model.User;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
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
 * Created by Administrator on 2017/2/22.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestComment {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @Test
    public void test() throws UserDefinedException, IOException, NoSuchAlgorithmException, MessagingException, TemplateException, InterruptedException {
        userService.addUser(new User("admin", "admin", "admin@qq.com"));
        blogService.writeBlog(1, "adasd", "test");
        commentService.addComment("good blog", 1, 1, 0);
    }
}
