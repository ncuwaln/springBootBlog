package com.blog.service;

import com.blog.model.Blog;
import com.blog.model.User;
import com.blog.repository.BlogRepository;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Blog writeBlog(Integer user_id, String title, String body){
        Date create_date = new Date(System.currentTimeMillis());
        User user = userRepository.findOne(user_id);
        Blog blog = new Blog(body, title, user.getUsername(), create_date);
        return blogRepository.save(blog);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<Blog> findBlogsByTitle(String title){
        String t = "%"+title+"%";
        return blogRepository.findBlogByTitle(t);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<Blog> findBlogsByAuthorName(String keywords){
        keywords = "%"+keywords+"%";
        return blogRepository.findBlogByAuthorKeywords(keywords);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<Blog> listBlog(Integer pages, Integer limits){
        Integer start = (pages-1)*limits;
        Integer end = start+limits;
        return blogRepository.listBlog(start, end);
    }
}
