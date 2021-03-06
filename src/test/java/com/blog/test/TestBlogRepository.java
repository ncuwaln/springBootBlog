package com.blog.test;

import com.blog.Application;
import com.blog.model.Blog;
import com.blog.repository.BlogRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestBlogRepository {
    @Autowired
    private BlogRepository blogRepository;

    @Before
    public void init() throws InterruptedException {
        Blog blog = null;
        for (int i = 0; i<10; ++i){
            String title = "Test"+String.valueOf(i);
            blog = new Blog("test", title, "test_user", new Date(System.currentTimeMillis()));
            blogRepository.save(blog);
            blog = null;
            //为了让时间间隔体现出来
            Thread.sleep(1000);
        }
    }

    @Test
    public void test(){
        Blog blog = new Blog("博客测试", "博客测试", "tjq", new Date(System.currentTimeMillis()));
        blogRepository.save(blog);
        List<Blog> blogs = blogRepository.findBlogByAuthor("tjq");
        List<Blog> blogs1 = blogRepository.findBlogByTitle("%博客%");
        System.out.println(blogs.get(0).getBody());
        System.out.println(blogs1.get(0).getCreate_date());
    }

    @Test
    public void testList(){
        List<Blog> blogs = blogRepository.listBlog(0, 3);
        for (Blog b:blogs){
            System.out.println(b.getTitle());
        }
    }

    @After
    public void destroy(){
        blogRepository.deleteAll();
    }
}
