package com.blog.test;

import com.blog.Application;
import com.blog.error.UserDefinedException;
import com.blog.model.Blog;
import com.blog.model.User;
import com.blog.repository.BlogRepository;
import com.blog.service.BlogService;
import com.blog.service.UserService;
import freemarker.template.TemplateException;
import org.junit.After;
import org.junit.Before;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestBlogService {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @Before
    public void init() throws InterruptedException, UserDefinedException, IOException, NoSuchAlgorithmException, TemplateException, MessagingException {
        User u =  userService.addUser(new User("admin", "admin", "admin@qq.com"));
        Blog blog = null;
        for (int i = 0; i < 10; ++i){
            String title = "Test"+String.valueOf(i);
            blog = new Blog("test", title, "test_user", new Date(System.currentTimeMillis()));
            blogService.writeBlog(u.getId(), title, "test");
            blog = null;
            Thread.sleep(1000);
        }
    }

    @Test
    public void test(){
        List<Blog> blogs = blogService.listBlog(1, 3);
        for (Blog b: blogs){
            System.out.println(b.getTitle());
        }
    }
}
