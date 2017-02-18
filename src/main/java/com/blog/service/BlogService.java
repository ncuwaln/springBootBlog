package com.blog.service;

import com.blog.model.Blog;
import com.blog.model.User;
import com.blog.repository.BlogRepository;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/12.
 */
@Component
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    public Blog writeBlog(Integer user_id, String title, String body){
        Date create_date = new Date(System.currentTimeMillis());
        Blog blog = new Blog(body, title, user_id, create_date);
        return blogRepository.save(blog);
    }

    public List<Blog> findBlogByAuthorId(Integer user_id){
        return blogRepository.findBlogByAuthor(user_id);
    }

    public List<Blog> findBlogsByTitle(String title){
        String t = "%"+title+"%";
        return blogRepository.findBlogByTitle(t);
    }

    public List<Blog> findBlogsByAuthorName(String keywords){
        keywords = "%"+keywords+"%";
        List<User> users = userRepository.findUserByKeywords(keywords);
        ArrayList<Blog> blogs = new ArrayList<Blog>();
        for (User x: users){
            for (Blog b: blogRepository.findBlogByAuthor(x.getId())){
                blogs.add(b);
            }
        }
        return blogs;
    }
}
